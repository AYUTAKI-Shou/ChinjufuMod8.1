package com.ayutaki.chinjufumod.blocks.jpdeco;

import java.util.Optional;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class BambooCube extends RotatedPillarBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<BambooCube> CODEC = simpleCodec(BambooCube::new);
	@Override
	public MapCodec<? extends BambooCube> codec() { return CODEC; }
	
	/* Property */
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BambooCube(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis())
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Waterlogged */
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean canPlaceLiquid(@Nullable Player playerIn, BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return fluid == Fluids.WATER;
	} // for 20.2
	
	@Override
	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluid.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(worldIn)); }
			return true; }
		
		else { return false; }
	}
	
	@Override
	public ItemStack pickupBlock(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(BlockStateProperties.WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			
			if (!state.canSurvive(worldIn, pos)) { worldIn.destroyBlock(pos, true); }
			return new ItemStack(Items.WATER_BUCKET);
		}
		
		else { return ItemStack.EMPTY; }
	} // for 20.2
	
	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
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
		builder.add(AXIS, WATERLOGGED);
	}
	
	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
	
}
