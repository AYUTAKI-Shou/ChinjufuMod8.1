package com.ayutaki.chinjufumod.blocks.jpblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.ToolType;

public class JpBlockDummy extends Abstract_Harvest {

	public JpBlockDummy(AbstractBlock.Properties props) {
		super(props);
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
}
