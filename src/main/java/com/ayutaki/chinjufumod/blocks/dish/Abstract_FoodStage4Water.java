package com.ayutaki.chinjufumod.blocks.dish;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectWater;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class Abstract_FoodStage4Water extends Base_ConnectWater {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);

	public Abstract_FoodStage4Water(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(DOWN, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);

		if (context.getFace() == Direction.UP) {
			return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
					.with(DOWN, Base_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER); }

		else { return null; }
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}

	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (connectWater(worldIn, pos, Direction.DOWN)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.with(DOWN, down);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, STAGE_1_4, WATERLOGGED);
	}
}
