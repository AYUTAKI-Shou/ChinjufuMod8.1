package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.tileentity.Reizou_TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public abstract class AbstractReizou<E extends TileEntity> extends ContainerBlock {

	protected final Supplier<TileEntityType<? extends E>> blockEntityType;

	protected AbstractReizou(Block.Properties props, Supplier<TileEntityType<? extends E>> tileEntity) {
		super(props);
		this.blockEntityType = tileEntity;
	}

	@OnlyIn(Dist.CLIENT)
	public abstract TileEntityMerger.ICallbackWrapper<? extends Reizou_TileEntity> combine(BlockState state, World worldIn, BlockPos pos, boolean flag);

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}
}
