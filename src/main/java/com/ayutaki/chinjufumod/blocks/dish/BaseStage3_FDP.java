package com.ayutaki.chinjufumod.blocks.dish;

import net.minecraft.block.state.IBlockState;

public class BaseStage3_FDP extends Abstract_FoodStage3 {

	public BaseStage3_FDP(String name) {
		super(name);
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
