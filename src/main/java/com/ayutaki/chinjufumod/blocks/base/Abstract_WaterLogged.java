package com.ayutaki.chinjufumod.blocks.base;

import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class Abstract_WaterLogged extends Block implements SimpleWaterloggedBlock {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public Abstract_WaterLogged(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* Waterlogged */
	@Override
	protected FluidState getFluidState(BlockState state) {
			return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	protected boolean propagatesSkylightDown(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}
	
	@Override
	public boolean canPlaceLiquid(@Nullable Player playerIn, BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return fluid == Fluids.WATER;
	}

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
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
	
	/** Create Blockstate. must add WATERLOGGED **/
	@Override
	protected abstract void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);
}
