package com.ayutaki.chinjufumod.blocks.wood;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class WoodPlanks_CM extends Abstract_Harvest {

	public WoodPlanks_CM(Block.Properties props) {
		super(props);
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
