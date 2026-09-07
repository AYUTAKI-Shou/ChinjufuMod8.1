package com.ayutaki.chinjufumod.blocks.wallpane;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Pillar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class BrickPillar_CM extends Abstract_Pillar {

	public BrickPillar_CM(Block.Properties props) {
		super(props);
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
}
