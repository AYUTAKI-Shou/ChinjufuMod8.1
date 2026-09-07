package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Zundou_NamaCurry extends BaseZundou_2Stage {

	public Zundou_NamaCurry(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		CMEvents.textRequestHeat(worldIn, pos, playerIn);
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
			if (cookingIn(worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				int i = state.get(STAGE_1_2);
				/** 1=raw, 2=cook **/
				
				if (i == 1) {
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
					worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2))); }
				
				else { //i != 1
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
					worldIn.setBlockState(pos, this.takeBlock().getDefaultState()
							.with(BaseZundou_4Stage.H_FACING, state.get(H_FACING))
							.with(BaseZundou_4Stage.STAGE_1_4, Integer.valueOf(1)));
					CMEvents.addEXP(1, worldIn, pos); } }
			
			else { }
		}

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
					.with(Zundou.H_FACING, state.get(H_FACING))
					.with(Zundou.STAGE_1_2, Integer.valueOf(2))
					.with(Zundou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	private Block takeBlock() {
		if (this == Dish_Blocks.ZUNDOU_NCURRY) { return Dish_Blocks.ZUNDOU_CURRY; }
		if (this == Dish_Blocks.ZUNDOU_NCURRY_C) { return Dish_Blocks.ZUNDOU_CURRY_C; }
		if (this == Dish_Blocks.ZUNDOU_NCURRY_T) { return Dish_Blocks.ZUNDOU_CURRY_T; }
		else { return Dish_Blocks.ZUNDOU_STEW; }
	}
}
