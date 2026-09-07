package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4WA;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Base_DeskBook23 extends BaseFood_Stage4WA {

	public Base_DeskBook23(Block.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		int i = state.get(STAGE_1_4);
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		if (hItem == Items.BOOK) {
			if (i == 4) { return ActionResultType.PASS; }
			
			else { //i != 4
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1)), 3);
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand); 
				return ActionResultType.SUCCESS; }
		}
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items.BOOK);
			
			if (i == 1) { worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3); }
			else { //i != 1
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.BOOK);
	}
}
