package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.dish.BaseZundou_4Stage;
import com.ayutaki.chinjufumod.blocks.dish.Zundou;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
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

public class Zundou_ColdMilk extends BaseZundou_4Stage {

	protected static final int COOK_TIME = 300;
	
	public Zundou_ColdMilk(String name) {
		super(name);
		setTickRandomly(true);
	}

	/*TickRandom*/
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (cookingOUT(worldIn, pos) && i == 3) { 
			if (rand.nextInt(1) == 0 && rand.nextInt(1) == 0) { 
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(4))); } }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		/** 1=冷めた牛乳, 2=乳酸菌, 3=レンネット, 4=カード **/
		
		if (cookingIn(worldIn, pos)) { CMEvents.textRequestCool(worldIn, pos, playerIn); }
		
		else {
			if (i == 1) {
				if (hItem == Items_Teatime.NYUSAN) {
					CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE, 0);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(2))); }
				
				if (hItem != Items_Teatime.NYUSAN) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}

			if (i == 2) {
				if (hItem == Items_Teatime.SHIO && k == 2) {
					CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(3))); }
				
				if (hItem != Items_Teatime.SHIO || k != 2) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			if (i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
			
			if (i == 4) {
				if (hStack.isEmpty()) {
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD, 0);
					CMEvents.addEXP(1, worldIn, pos, playerIn);
					
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
						.withProperty(Zundou.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou.STAGE_1_3, Integer.valueOf(2))); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
		}
		/** 'true' to not put anything on top. **/
		return true;
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
		/** pick は牛乳入り寸胴 **/
		return new ItemStack(Items_Teatime.ZUNDOU_MIZU, 1, 2);
	}
}
