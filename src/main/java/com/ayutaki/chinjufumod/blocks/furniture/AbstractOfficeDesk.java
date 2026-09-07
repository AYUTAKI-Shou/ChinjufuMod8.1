package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.OfficeDesk_TileEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner.NeighborCombineResult;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractOfficeDesk <E extends BlockEntity> extends BaseEntityBlock {
	protected abstract MapCodec<? extends AbstractOfficeDesk<E>> codec();
	protected final Supplier<BlockEntityType<? extends E>> blockEntityType;
	
	public AbstractOfficeDesk(BlockBehaviour.Properties props, Supplier<BlockEntityType<? extends E>> tileEntity) {
		super(props);
		blockEntityType = tileEntity;
	}

	public abstract NeighborCombineResult<? extends OfficeDesk_TileEntity> combine(BlockState state, Level worldIn, BlockPos pos, boolean flag);
}
