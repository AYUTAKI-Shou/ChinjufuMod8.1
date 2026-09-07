package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.ReizouTop_TileEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractReizouTop<E extends BlockEntity> extends BaseEntityBlock {
	@Override
	protected abstract MapCodec<? extends AbstractReizouTop<E>> codec();
	protected final Supplier<BlockEntityType<? extends E>> blockEntityType;

	protected AbstractReizouTop(BlockBehaviour.Properties props, Supplier<BlockEntityType<? extends E>> tileEntity) {
		super(props);
		this.blockEntityType = tileEntity;
	}

	public abstract DoubleBlockCombiner.NeighborCombineResult<? extends ReizouTop_TileEntity> combine(BlockState state, Level worldIn, BlockPos pos, boolean flag);
}
