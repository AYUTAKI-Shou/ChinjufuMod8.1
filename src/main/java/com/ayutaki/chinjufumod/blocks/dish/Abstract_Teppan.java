package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class Abstract_Teppan extends Abstract_CookPan {
	/* Property */
	public static final BooleanProperty DOWN = BooleanProperty.create("down");

	public Abstract_Teppan(BlockBehaviour.Properties props) {
		super(props);
	}
	
	protected boolean connectTeppan(BlockGetter worldIn, BlockPos pos, Direction direct) {
		BlockPos newPos = pos.relative(direct);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();
		return (block instanceof AbstractOvenBlock || block instanceof Kit_Cooktop);
	}
}
