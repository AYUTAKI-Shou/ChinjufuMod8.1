package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Taru_HakusaiDuke extends Base_Taru15 {
	/** 0=10, 1=11, 2=12, 3=13, 4=15*, 5=20, 6=21, 7=23, 8=45, 9=69*, 10=69, 11=69, 12=69, 
	 13=kara*, 14=1kara* **/

	public Taru_HakusaiDuke(String name) {
		super(name);
		setTickRandomly(true);
	}

	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 0=10, 1=11, 2=12, 3=13, 4=15*, 5=20, 6=21, 7=23, 8=45, 9=69*, 10=69, 11=69, 12=69, 
		 13=kara*, 14=1kara* **/
		boolean wait = (i == 4 || i >= 9);
		if (wait) { }
		
		else {
			if (rand.nextInt(2) == 0) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1))); } }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 0=10, 1=11, 2=12, 3=13, 4=15*, 5=20, 6=21, 7=23, 8=45, 9=69*, 10=69, 11=69, 12=69, 
		 13=kara*, 14=1kara* **/
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		/** Too early to collect **/
		if (i <= 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		if (i >= 5 && i <= 8) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 4) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.FOOD_HAKUSAI2, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(14)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i >= 9 && i <= 12) {
			if (hItem == Items_Teatime.Item_SARA) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.HAKUSAIDUKE, 0);
				if (i == 9) { CMEvents.addEXP(1, worldIn, pos, playerIn); }
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
			
			if (hItem != Items_Teatime.Item_SARA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** It is Empty. **/
		if (i >= 13) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 0=10, 1=11, 2=12, 3=13, 4=15*, 5=20, 6=21, 7=23, 8=45, 9=69*, 10=69, 11=69, 12=69, 
		 13=kara*, 14=1kara* **/
		
		if (i == 0) { stack.add(new ItemStack(Items_Teatime.HAKUSAI_TARU, 1, 1)); }

		if (i >= 1 && i <= 3) {
			stack.add(new ItemStack(Blocks.STONE_SLAB, 2, 0));
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		
		if (i == 4) {
			stack.add(new ItemStack(Blocks.STONE_SLAB, 2, 0));
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0));
			stack.add(new ItemStack(Items_Teatime.FOOD_HAKUSAI2, 1, 0)); }

		if (i == 5) { stack.add(new ItemStack(Items_Teatime.HAKUSAI_TARU, 1, 2)); }

		if (i >= 6 && i <= 13) {
			stack.add(new ItemStack(Blocks.STONE_SLAB, 1, 0));
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }

		if (i >= 14) {
			stack.add(new ItemStack(Blocks.STONE_SLAB, 2, 0));
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }

		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 0=10, 1=11, 2=12, 3=13, 4=15*, 5=20, 6=21, 7=23, 8=45, 9=69*, 10=69, 11=69, 12=69, 
		 13=kara*, 14=1kara* **/
		
		if (i <= 4) { return new ItemStack(Items_Teatime.HAKUSAI_TARU, 1, 1); }
		if (i >= 13) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0); }
		return new ItemStack(Items_Teatime.HAKUSAI_TARU, 1, 2);
	}
}
