package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;

import net.minecraft.block.state.IBlockState;

public class BaseStage4_FDA extends BaseStage4_FaceDown {

	public BaseStage4_FDA(String name) {
		super(name);
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
