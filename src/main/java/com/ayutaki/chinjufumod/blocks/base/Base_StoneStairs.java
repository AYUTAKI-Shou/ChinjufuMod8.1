package com.ayutaki.chinjufumod.blocks.base;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Base_StoneStairs extends Regi_Stairs {

	public Base_StoneStairs(String name, IBlockState state) {
		super(name, state);
		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
