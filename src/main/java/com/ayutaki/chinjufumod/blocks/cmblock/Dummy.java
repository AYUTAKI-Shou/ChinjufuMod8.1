package com.ayutaki.chinjufumod.blocks.cmblock;

import com.ayutaki.chinjufumod.blocks.base.Regi_Name;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Dummy extends Regi_Name {

	public Dummy(String name) {
		super(name, Material.WOOD);
		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
