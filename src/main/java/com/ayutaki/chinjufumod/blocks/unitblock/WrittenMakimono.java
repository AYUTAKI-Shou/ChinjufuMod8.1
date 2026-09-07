package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.blocks.base.Abstract_ConnectWater;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WrittenMakimono extends Abstract_ConnectWater {
	public static final MapCodec<WrittenMakimono> CODEC = simpleCodec(WrittenMakimono::new);
	@Override
	public MapCodec<? extends WrittenMakimono> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_5 = IntegerProperty.create("stage", 1, 5);
	public static final BooleanProperty LOST = BooleanProperty.create("lost");
	
	/* Collision */
	private static final VoxelShape AABB_1S = Block.box(6.75D, 0.0D, 4.0D, 9.25D, 2.5D, 12.0D);
	private static final VoxelShape AABB_1W = Block.box(4.0D, 0.0D, 6.75D, 12.0D, 2.5D, 9.25D);
	private static final VoxelShape AABB_1N = Block.box(6.75D, 0.0D, 4.0D, 9.25D, 2.5D, 12.0D);
	private static final VoxelShape AABB_1E = Block.box(4.0D, 0.0D, 6.75D, 12.0D, 2.5D, 9.25D);
	
	private static final VoxelShape AABB_2S = Block.box(4.25D, 0.0D, 4.0D, 9.25D, 2.5D, 12.0D);
	private static final VoxelShape AABB_2W = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 2.5D, 9.25D);
	private static final VoxelShape AABB_2N = Block.box(6.75D, 0.0D, 4.0D, 11.75D, 2.5D, 12.0D);
	private static final VoxelShape AABB_2E = Block.box(4.0D, 0.0D, 6.75D, 12.0D, 2.5D, 11.75D);
	
	private static final VoxelShape AABB_3S = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 2.5D, 12.0D);
	private static final VoxelShape AABB_3W = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 2.5D, 11.75D);
	private static final VoxelShape AABB_3N = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 2.5D, 12.0D);
	private static final VoxelShape AABB_3E = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 2.5D, 11.75D);
	
	private static final VoxelShape AABB_4S = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 5.0D, 12.0D);
	private static final VoxelShape AABB_4W = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 5.0D, 11.75D);
	private static final VoxelShape AABB_4N = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 5.0D, 12.0D);
	private static final VoxelShape AABB_4E = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 5.0D, 11.75D);

	private static final VoxelShape DOWN_SOUTH = Block.box(4.25D, -8.0D, 4.0D, 11.75D, 0.1D, 12.0D);
	private static final VoxelShape DOWN_WEST = Block.box(4.0D, -8.0D, 4.25D, 12.0D, 0.1D, 11.75D);
	private static final VoxelShape DOWN_NORTH = Block.box(4.25D, -8.0D, 4.0D, 11.75D, 0.1D, 12.0D);
	private static final VoxelShape DOWN_EAST = Block.box(4.0D, -8.0D, 4.25D, 12.0D, 0.1D, 11.75D);
	
	public WrittenMakimono(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_5, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(LOST, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(DOWN, Abstract_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		if (connectWater(worldIn, pos, Direction.DOWN)) {
			worldIn.scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO.get(), Fluids.WATER.getTickDelay(worldIn)); }
		
		if (waterIn(state, worldIn, pos)) { worldIn.scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO.get(), 300); }
		
		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.scheduleTick(pos, Unit_Blocks.WRITTEN_BOOK.get(), 300); }
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO.get(), 300);
			worldIn.setBlock(pos, state.setValue(LOST, Boolean.valueOf(true)), 3); }
		
		else { }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, STAGE_1_5, WATERLOGGED, LOST);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		int i = state.getValue(STAGE_1_5);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? ((i == 1)? AABB_1N : ((i == 2)? AABB_2N : ((i == 3)? AABB_3N : AABB_4N))) : DOWN_NORTH;
		case SOUTH:
			return notDown? ((i == 1)? AABB_1S : ((i == 2)? AABB_2S : ((i == 3)? AABB_3S : AABB_4S))) : DOWN_SOUTH;
		case WEST:
			return notDown? ((i == 1)? AABB_1W : ((i == 2)? AABB_2W : ((i == 3)? AABB_3W : AABB_4W))) : DOWN_WEST;
		case EAST:
			return notDown? ((i == 1)? AABB_1E : ((i == 2)? AABB_2E : ((i == 3)? AABB_3E : AABB_4E))) : DOWN_EAST;
		}
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.BOOK);
	} // for 1.20.6
}
