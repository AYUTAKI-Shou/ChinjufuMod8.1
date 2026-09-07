package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.OfficeDesk_TileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractOfficeDesk<E extends TileEntity> extends ContainerBlock {

	protected final Supplier<TileEntityType<? extends E>> blockEntityType;

	protected AbstractOfficeDesk(AbstractBlock.Properties props, Supplier<TileEntityType<? extends E>> tileEntity) {
		super(props);
		this.blockEntityType = tileEntity;
	}

	@OnlyIn(Dist.CLIENT)
	public abstract TileEntityMerger.ICallbackWrapper<? extends OfficeDesk_TileEntity> combine(BlockState state, World worldIn, BlockPos pos, boolean flag);

}
