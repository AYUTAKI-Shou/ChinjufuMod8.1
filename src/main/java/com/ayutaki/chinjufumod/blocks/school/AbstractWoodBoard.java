package com.ayutaki.chinjufumod.blocks.school;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class AbstractWoodBoard extends BaseEntityBlock implements SimpleWaterloggedBlock {
	protected abstract MapCodec<? extends AbstractWoodBoard> codec();
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected AbstractWoodBoard(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		this.registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public boolean isPossibleToRespawnInThis(BlockState state) {
		return true;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WoodBoard_TileEntity(pos, state);
	}

	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@SuppressWarnings("deprecation")
	protected boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.relative(state.getValue(H_FACING).getOpposite())).isSolid();
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

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, WATERLOGGED);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level worldIn, BlockState state, BlockEntityType<T> entityType) {
		return createTickerHelper(entityType, BlockEntity_CM.WOODBOARD.get(), WoodBoard_TileEntity::tick);
	}
}
