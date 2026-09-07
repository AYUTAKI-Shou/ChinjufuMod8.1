package com.ayutaki.chinjufumod.blocks.dish;

import net.minecraft.block.state.IBlockState;

public class BaseStage3_FDA extends Abstract_FoodStage3 {

	public BaseStage3_FDA(String name) {
		super(name);
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
