package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.hakkou.Zundou_ColdMilk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
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

public class Zundou_MizuMilk extends BaseZundou_InOUT {
	/** 1=水, 2=湯, 3=牛乳, 4=ホットミルク **/
	public Zundou_MizuMilk(String name) {
		super(name);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		/** 1=水, 2=湯, 3=牛乳, 4=ホットミルク **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		boolean MILK = (i == 3 || i == 4);
		if (MILK) {
			if (i == 3) { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
			
			if (i == 4) {
				if (hItem != Items_Teatime.NYUSAN) { CMEvents.textRequestCool(worldIn, pos, playerIn); }
				if (hItem == Items_Teatime.NYUSAN) { CMEvents.textEarlyUse(worldIn, pos, playerIn); } }
		}
		
		else {
			/* 塩水へ */
			if (hItem == Items_Teatime.SHIO) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/
				worldIn.setBlockState(pos, Dish_Blocks.SHIOAKUNABE.getDefaultState()
						.withProperty(Zundou_ShioAku.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou_ShioAku.STAGE_1_4, Integer.valueOf(i))); 
			}
			
			/* 灰汁水へ */
			if (hItem == Items_Seasonal.WARAHAI) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/
				worldIn.setBlockState(pos, Dish_Blocks.SHIOAKUNABE.getDefaultState()
						.withProperty(Zundou_ShioAku.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou_ShioAku.STAGE_1_4, Integer.valueOf(i + 2))); 
			}
			
			/* 出汁へ */
			if (hItem == Items_Teatime.DASHI_bot_14) {
				if (i == 2) {
					CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE, 0);
					worldIn.setBlockState(pos, Dish_Blocks.DASHINABE.getDefaultState()
							.withProperty(Zundou4_Dashi.H_FACING, state.getValue(H_FACING))
							.withProperty(Zundou4_Dashi.STAGE_1_4, Integer.valueOf(1))); }
				
				/** Too early to use **/
				else { //i != 2
					CMEvents.textEarlyUse(worldIn, pos, playerIn); }
			}
			
			/* うどんへ */
			if (hItem == Items_Teatime.PASTA && k == 3) {
				if (i == 2) {
					CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_UDON.getDefaultState()
							.withProperty(Zundou4_Udon.H_FACING, state.getValue(H_FACING))
							.withProperty(Zundou4_Udon.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
				
				/** Too early to use **/
				else { //i != 2
					CMEvents.textEarlyUse(worldIn, pos, playerIn); }
			}
			
			/** ラーメン**/
			if (hItem == Items_Teatime.RAMEN_nama && k == 0) {
				if (i == 2) {
					CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_RAMEN.getDefaultState()
							.withProperty(Zundou4_Ramen.H_FACING, state.getValue(H_FACING))
							.withProperty(Zundou4_Ramen.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
				
				/** Too early to use **/
				else { //i != 2
					CMEvents.textEarlyUse(worldIn, pos, playerIn); }
			}
			
			if (hItem != Items_Teatime.SHIO && hItem != Items_Seasonal.WARAHAI && hItem != Items_Teatime.DASHI_bot_14 && 
					hItem != Items_Teatime.PASTA && hItem != Items_Teatime.RAMEN_nama) { }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** 1=水, 2=湯, 3=牛乳, 4=ホットミルク **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (cookingIn(worldIn, pos) && COLD(state)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, this.getDefaultState()
					.withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
		}
		
		if (cookingOUT(worldIn, pos) && HOT(state)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			if (i == 2) {
				worldIn.setBlockState(pos, this.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(STAGE_1_4, Integer.valueOf(1))); }
			
			if (i == 4) {
				worldIn.setBlockState(pos, Hakkou_Blocks.COLD_MILK.getDefaultState()
						.withProperty(Zundou_ColdMilk.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou_ColdMilk.STAGE_1_4, Integer.valueOf(1))); } }
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
		/** 1=水, 2=湯, 3=牛乳, 4=ホットミルク **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/** pick はそれぞれを回収 **/
		if (i == 3 || i == 4) { return new ItemStack(Items_Teatime.ZUNDOU_MILK, 1, 0); }
		else { return new ItemStack(Items_Teatime.ZUNDOU_MIZU, 1, 0); }
	}
// 動きのあるブロックに BlockRenderLayer.TRANSLUCENT は使えない
}
