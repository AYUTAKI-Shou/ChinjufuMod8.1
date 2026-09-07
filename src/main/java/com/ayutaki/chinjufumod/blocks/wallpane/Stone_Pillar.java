package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.blocks.base.Regi_Pillar;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Stone_Pillar extends Regi_Pillar {

	public Stone_Pillar(String name) {
		super(name);
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
