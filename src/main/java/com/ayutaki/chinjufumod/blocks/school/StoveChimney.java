package com.ayutaki.chinjufumod.blocks.school;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoveChimney extends Abstract_WaterLogged {
	public static final MapCodec<StoveChimney> CODEC = simpleCodec(StoveChimney::new);
	@Override
	public MapCodec<? extends StoveChimney> codec() { return CODEC; }
	
	/* Property */
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");

	/* Collision */
	private static final VoxelShape AABB_CENTER = Block.box(5.9D, 5.9D, 5.9D, 10.1D, 10.1D, 10.1D);
	private static final VoxelShape AABB_UP = Block.box(5.9D, 10.1D, 5.9D, 10.1D, 16.0D, 10.1D);
	private static final VoxelShape AABB_DOWN = Block.box(5.9D, 0.0D, 5.9D, 10.1D, 10.1D, 10.1D);
	private static final VoxelShape AABB_NORTH = Block.box(5.9D, 5.9D, 0.0D, 10.1D, 10.1D, 5.9D);
	private static final VoxelShape AABB_EAST = Block.box(10.1D, 5.9D, 5.9D, 16.0D, 10.1D, 10.1D);
	private static final VoxelShape AABB_SOUTH = Block.box(5.9D, 5.9D, 10.1D, 10.1D, 10.1D, 16.0D);
	private static final VoxelShape AABB_WEST = Block.box(0.0D, 5.9D, 5.9D, 5.9D, 10.1D, 10.1D);

	public StoveChimney(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(UP, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(BlockGetter worldIn, BlockPos pos, Direction direction) {
		BlockState state = worldIn.getBlockState(pos.relative(direction));
		return state.is(this);
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		boolean up = canConnectTo(worldIn, pos, Direction.UP);
		boolean down = canConnectTo(worldIn, pos, Direction.DOWN);
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		return state.setValue(UP, up).setValue(DOWN, down).setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean up = state.getValue(UP).booleanValue();
		boolean down = state.getValue(DOWN).booleanValue();
		boolean north = state.getValue(NORTH).booleanValue();
		boolean east = state.getValue(EAST).booleanValue();
		boolean south = state.getValue(SOUTH).booleanValue();
		boolean west = state.getValue(WEST).booleanValue();

		VoxelShape shape = AABB_CENTER;

		if (!up && !north && !south && !east && !west && !down) {
			shape = Shapes.or(shape, AABB_UP, AABB_DOWN); }

		if (up) {
			if (down) { shape = Shapes.or(shape, AABB_UP, AABB_DOWN); }
			if (!north && !south && !east && !west && !down) { shape = Shapes.or(shape, AABB_UP, AABB_DOWN); }
			shape = Shapes.or(shape, AABB_UP);
		}

		if (down) {
			if (up) { shape = Shapes.or(shape, AABB_UP, AABB_DOWN); }
			if (!north && !south && !east && !west && !up) { shape = Shapes.or(shape, AABB_UP, AABB_DOWN); }
			shape = Shapes.or(shape, AABB_DOWN);
		}

		if (north) {
			if (south) { shape = Shapes.or(shape, AABB_NORTH, AABB_SOUTH); }
			if (!up && !down && !east && !west && !south) { shape = Shapes.or(shape, AABB_NORTH, AABB_SOUTH); }
			shape = Shapes.or(shape, AABB_NORTH);
		}

		if (south) {
			if (north) { shape = Shapes.or(shape, AABB_NORTH, AABB_SOUTH); }
			if (!up && !down && !east && !west && !north) { shape = Shapes.or(shape, AABB_NORTH, AABB_SOUTH); }
			shape = Shapes.or(shape, AABB_SOUTH);
		}

		if (east) {
			if (west) { shape = Shapes.or(shape, AABB_WEST, AABB_EAST); }
			if (!up && !down && !north && !south && !west) { shape = Shapes.or(shape, AABB_WEST, AABB_EAST); }
			shape = Shapes.or(shape, AABB_EAST);
		}

		if (west) {
			if (east) { shape = Shapes.or(shape, AABB_WEST, AABB_EAST); }
			if (!up && !down && !north && !south && !east) { shape = Shapes.or(shape, AABB_WEST, AABB_EAST); }
			shape = Shapes.or(shape, AABB_WEST);
		}

		return shape;
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}
}
