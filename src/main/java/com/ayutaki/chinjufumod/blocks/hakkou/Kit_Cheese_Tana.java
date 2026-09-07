package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kit_Cheese_Tana extends BaseCheese_Tana {

	/* stage1=OOO, stage2=OOA, stage3=OOB */
	public Kit_Cheese_Tana(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		/** Hand is empty. **/
		if (hStack.isEmpty() && hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) {
			/** stage1=OOO **/
			if (i == 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			
			/** stage2=OOA **/
			if (i == 2 && hStack.isEmpty() && hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(1))); } /* -> OOO */
			
			/** stage3=OOB **/
			if (i == 3 && hStack.isEmpty() && hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(1))); } /* -> OOO */
		}
		
		/** Hand is not empty. **/
		if (!hStack.isEmpty()) {
			if (hItem == Items_Teatime.CHEESE_CURD) {
				if (i == 1) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(2))); } /* -> OOA */
				
				if (i == 2) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
							.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1))); } /* -> OAA */
				
				if (i == 3) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
							.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2))); } /* -> OBA */
			}
			
			if (hItem == Items_Teatime.CHEESE) {
				if (i == 1 && hItem == Items_Teatime.CHEESE) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(3))); } /* -> OOB */
				
				if (i == 2 && hItem == Items_Teatime.CHEESE) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
							.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2))); } /* -> OBA */
				
				if (i == 3 && hItem == Items_Teatime.CHEESE) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
							.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); } /* -> OBB */
			}
			
			if (hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) { 
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* TickRandom */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/* stage1=OOO, stage2=OOA, stage3=OOB */
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 2) { 
			worldIn.scheduleUpdate(pos, Kitchen_Blocks.CHEESE_TANA, COOK_TIME + (500 * rand.nextInt(5)));
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(3))); }
		else { }
	}

	/* Drop Item and Clone Item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/* stage1=OAA, stage2=OBA, stage3=OBB */
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0)); }

		if (i == 2) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE_CURD, 1, 0)); }

		if (i == 3) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE, 1, 0)); }
		return stack;
	}
}
