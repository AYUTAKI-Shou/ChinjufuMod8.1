package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.base.Abstract_ConnectWater;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class BaseFood_Stage5Water extends Abstract_ConnectWater {
	public static final MapCodec<BaseFood_Stage5Water> CODEC = simpleCodec(BaseFood_Stage5Water::new);
	@Override
	public MapCodec<? extends BaseFood_Stage5Water> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_5 = IntegerProperty.create("stage", 1, 5);

	public BaseFood_Stage5Water(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_5, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		if (context.getClickedFace() == Direction.UP) {
			return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
					.setValue(DOWN, Abstract_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
					.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }

		else { return null; }
	}

	/* HORIZONTAL Property */
	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (connectWater(worldIn, pos, Direction.DOWN)) {
			tick.scheduleTick(pos, this, Fluids.WATER.getTickDelay(worldIn)); }

		int i = state.getValue(STAGE_1_5);
		if (waterIn(state, worldIn, pos) && i != 5) { tick.scheduleTick(pos, this, 60); }

		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_5);
		if (waterIn(state, worldIn, pos) && i != 5) { worldIn.scheduleTick(pos, this, 60); }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, STAGE_1_5, WATERLOGGED);
	}
}
