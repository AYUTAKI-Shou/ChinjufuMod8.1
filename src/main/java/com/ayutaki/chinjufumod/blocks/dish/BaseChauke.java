package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.base.Abstract_ConnectWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class BaseChauke extends Abstract_ConnectWater {
	public static final MapCodec<BaseChauke> CODEC = simpleCodec(BaseChauke::new);
	@Override
	public MapCodec<? extends BaseChauke> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_1_5 = IntegerProperty.create("stage", 1, 5);

	public BaseChauke(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_1_5, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(DOWN, Abstract_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
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

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 5) {
			worldIn.scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(5)), 3); }
		
		else { }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, STAGE_1_5, WATERLOGGED);
	}
}
