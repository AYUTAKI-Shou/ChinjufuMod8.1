package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.hakkou.Zundou_ColdMilk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Zundou_Milk extends BaseZundou_2Stage {
	/** 1=cold, 2=hot **/
	public Zundou_Milk(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_2);
		
		if (i == 1) { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
		
		if (i == 2) {
			if (hItem != Items_Teatime.NYUSAN) { CMEvents.textRequestCool(worldIn, pos, playerIn); }
			if (hItem == Items_Teatime.NYUSAN) { CMEvents.textEarlyUse(worldIn, pos, playerIn); } }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			/** 1=cold, 2=hot **/
			
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2)), 3); }
			
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME);
				worldIn.setBlockState(pos, Hakkou_Blocks.COLD_MILK.getDefaultState()
						.with(Zundou_ColdMilk.H_FACING, state.get(H_FACING))
						.with(Zundou_ColdMilk.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			else { }
		}
		
		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
					.with(Zundou.H_FACING, state.get(H_FACING))
					.with(Zundou.STAGE_1_2, Integer.valueOf(2))
					.with(Zundou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}
}
