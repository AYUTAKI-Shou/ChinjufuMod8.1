package com.ayutaki.chinjufumod.blocks.cmblock;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.state.AlumiType;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AlumiBlock extends Abstract_WaterLogged {
	public static final MapCodec<AlumiBlock> CODEC = simpleCodec(AlumiBlock::new);
	@Override
	public MapCodec<? extends AlumiBlock> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<AlumiType> TYPE = EnumProperty.create("type", AlumiType.class);
	
	/* Collision */
	private static final VoxelShape AABB_BOTTOM = Block.box(0.5D, 0.0D, 0.5D, 15.5D, 8.0D, 15.5D);
	private static final VoxelShape AABB_BOX = Block.box(0.5D, 0.0D, 0.5D, 15.5D, 16.0D, 15.5D);
	
	public AlumiBlock(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(TYPE, AlumiType.BOTTOM)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = worldIn.getBlockState(pos);
		FluidState fluid = worldIn.getFluidState(pos);

		if (state.is(this)) {
			/** Change to SlabType.DOUBLE. **/
			return state.setValue(TYPE, AlumiType.DOUBLE); }

		else {
			BlockState blockState2 = this.defaultBlockState().setValue(TYPE, AlumiType.BOTTOM)
					.setValue(WATERLOGGED, Boolean.valueOf(Boolean.valueOf(fluid.getType() == Fluids.WATER)))
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
			Direction hitFace = context.getClickedFace();

			return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getClickLocation().y - (double)pos.getY() <= 0.5D) ? blockState2 : blockState2;
		}
	}

	/* Replace to DOUBLE. boolean t/f */
	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		ItemStack hStack = context.getItemInHand();
		AlumiType slabType = state.getValue(TYPE);

		if (slabType != AlumiType.DOUBLE && hStack.getItem() == this.asItem()) {

			if (context.replacingClickedOnBlock()) {
				boolean flag = context.getClickLocation().y - (double)context.getClickedPos().getY() > 0.5D;
				Direction hitFace = context.getClickedFace();

				if (slabType == AlumiType.BOTTOM) {
					return hitFace == Direction.UP || flag && hitFace.getAxis().isHorizontal(); }
				
				else { return false; }
			}
			else { return true; }
		}
		else { return false; }
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
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		switch (type) {
		case LAND:
			return false;
		case WATER:
			return state.getFluidState().is(FluidTags.WATER);
		case AIR:
			return false;
		default:
			return false;
		}
	} // for 1.20.6
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, TYPE, WATERLOGGED);
	}
	
	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return state.getValue(TYPE) != AlumiType.DOUBLE;
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		AlumiType slabType = state.getValue(TYPE);
		switch (slabType) {
		case DOUBLE:
			return AABB_BOX;
		default:
			return AABB_BOTTOM;
		}
	}
}
