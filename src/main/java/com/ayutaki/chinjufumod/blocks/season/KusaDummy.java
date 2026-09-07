package com.ayutaki.chinjufumod.blocks.season;

import com.ayutaki.chinjufumod.blocks.base.Regi_Name;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class KusaDummy extends Regi_Name {

	public KusaDummy(String name) {
		super(name, Material.WOOD);
		setSoundType(SoundType.PLANT);
		setHardness(1.0F);
		setResistance(5.0F);
	}

	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.FOLIAGE;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
