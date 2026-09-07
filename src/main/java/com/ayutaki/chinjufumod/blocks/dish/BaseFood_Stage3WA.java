package com.ayutaki.chinjufumod.blocks.dish;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class BaseFood_Stage3WA extends Abstract_FoodStage3Water {

	public BaseFood_Stage3WA(Block.Properties props) {
		super(props);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
