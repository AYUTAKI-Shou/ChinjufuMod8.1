package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public abstract class Abstract_Teppan extends Abstract_CookPan {
	/* Property */
	public static final BooleanProperty DOWN = BooleanProperty.create("down");

	public Abstract_Teppan(Block.Properties props) {
		super(props);
	}

	protected boolean connectTeppan(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();
		return (block instanceof AbstractOvenBlock || block instanceof Kit_Cooktop);
	}
}
