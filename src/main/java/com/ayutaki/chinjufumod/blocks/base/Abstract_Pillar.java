package com.ayutaki.chinjufumod.blocks.base;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraftforge.common.ToolType;

public abstract class Abstract_Pillar extends RotatedPillarBlock {

	public Abstract_Pillar(AbstractBlock.Properties props) {
		super(props);
	}

	/** The best harvesting tool. **/
	@Nullable
	@Override
	public abstract ToolType getHarvestTool(BlockState state);
	
	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}
}
