package com.ayutaki.chinjufumod.blocks.season;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_Stairs;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class KusaTaba_Stairs extends Regi_Stairs {

	public KusaTaba_Stairs(String name, IBlockState state) {
		super(name, state);
		setCreativeTab(ChinjufuModTabs.SEASONAL);

		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(2);
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
