package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Zundou_ShioAku extends BaseZundou_InOUT {
	/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/
	public Zundou_ShioAku(String name) {
		super(name);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		boolean AKU = (i == 3 || i == 4);
		if (AKU) {
			if (hItem == Items_Seasonal.KUSATABA) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				worldIn.setBlockState(pos, Dish_Blocks.ORIITONABE.getDefaultState()
						.withProperty(Zundou4_Oriito.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou4_Oriito.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
			
			if (hItem != Items_Seasonal.KUSATABA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		else {
			/** Too early to use **/
			if (i == 1) { CMEvents.textEarlyUse(worldIn, pos, playerIn); }
			
			if (i == 2) {
				if (hItem == Items_Teatime.PASTA && k == 1) {
					CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_PASTA.getDefaultState()
							.withProperty(Zundou4_Pasta.H_FACING, state.getValue(H_FACING))
							.withProperty(Zundou4_Pasta.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
		
				if (hItem == Items.FISH && k == 0) {
					CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_FISH.getDefaultState()
							.withProperty(Zundou4_Fish.H_FACING, state.getValue(H_FACING))
							.withProperty(Zundou4_Fish.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
				
				if (hItem != Items_Teatime.PASTA && k != 1 && hItem != Items.FISH && k != 0) {
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (cookingIn(worldIn, pos) && COLD(state)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, this.getDefaultState()
					.withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
		}
		
		if (cookingOUT(worldIn, pos) && HOT(state)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, this.getDefaultState()
					.withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(STAGE_1_4, Integer.valueOf(i - 1)));
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/** drop は寸胴に戻して回収 **/
		stack.add(new ItemStack(Items_Teatime.ZUNDOU, 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/** pick はそれぞれを回収 **/
		if (i == 3 || i == 4) { return new ItemStack(Items_Seasonal.AKUNABE, 1, 0); }
		return new ItemStack(Items_Teatime.ZUNDOUSHIO, 1, 0);
	}
}
