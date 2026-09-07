package com.ayutaki.chinjufumod.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public abstract class Abstract_Harvest extends Block {

	public Abstract_Harvest(Material material) {
		super(material);
	}

	/** The best harvesting tool. **/
	@Override
	public abstract String getHarvestTool(IBlockState state);

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}
}
