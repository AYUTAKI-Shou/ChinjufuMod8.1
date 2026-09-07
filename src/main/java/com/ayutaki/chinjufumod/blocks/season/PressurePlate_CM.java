package com.ayutaki.chinjufumod.blocks.season;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.state.StateContainer;
import net.minecraftforge.common.ToolType;

public class PressurePlate_CM extends PressurePlateBlock {

	public PressurePlate_CM(PressurePlateBlock.Sensitivity sensitivity, Block.Properties props) {
		super(sensitivity, props);
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}
}
