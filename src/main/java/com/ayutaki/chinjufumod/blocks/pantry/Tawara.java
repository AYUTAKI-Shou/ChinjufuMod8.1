package com.ayutaki.chinjufumod.blocks.pantry;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Pillar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class Tawara extends Abstract_Pillar {

	public Tawara(Block.Properties props) {
		super(props);
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
