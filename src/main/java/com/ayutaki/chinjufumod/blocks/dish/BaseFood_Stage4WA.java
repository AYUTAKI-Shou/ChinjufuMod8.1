package com.ayutaki.chinjufumod.blocks.dish;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class BaseFood_Stage4WA extends Abstract_FoodStage4Water {

	public BaseFood_Stage4WA(Block.Properties props) {
		super(props);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
