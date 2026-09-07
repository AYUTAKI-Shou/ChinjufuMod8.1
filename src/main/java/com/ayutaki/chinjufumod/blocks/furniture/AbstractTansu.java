package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.Tansu_TileEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractTansu<E extends BlockEntity> extends BaseEntityBlock {
	@Override
	protected abstract MapCodec<? extends AbstractTansu<E>> codec();
	protected final Supplier<BlockEntityType<? extends E>> blockEntityType;

	protected AbstractTansu(BlockBehaviour.Properties props, Supplier<BlockEntityType<? extends E>> tileEntity) {
		super(props);
		this.blockEntityType = tileEntity;
	}

	public abstract DoubleBlockCombiner.NeighborCombineResult<? extends Tansu_TileEntity> combine(BlockState state, Level worldIn, BlockPos pos, boolean flag);
}
