package com.ayutaki.chinjufumod.blocks.wallpane;

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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallPane_Glass extends WaterloggedTransparentBlock {
	public static final MapCodec<WallPane_Glass> CODEC = simpleCodec(WallPane_Glass::new);
	@Override
	public MapCodec<? extends WallPane_Glass> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	private static final VoxelShape AABB_WEST = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
	
	public WallPane_Glass(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		this.registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
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
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, WATERLOGGED);
	}
	
	@Override
	protected float getShadeBrightness(BlockState p_312407_, BlockGetter p_310193_, BlockPos p_311965_) {
		return 1.0F;
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState p_312717_) {
		return true;
	}
	
	@Override
	protected boolean skipRendering(BlockState p_53972_, BlockState p_53973_, Direction p_53974_) {
		return p_53973_.is(this) ? true : super.skipRendering(p_53972_, p_53973_, p_53974_);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}
}
