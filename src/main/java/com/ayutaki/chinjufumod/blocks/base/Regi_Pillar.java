package com.ayutaki.chinjufumod.blocks.base;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public abstract class Regi_Pillar extends BlockRotatedPillar {

	public Regi_Pillar(String name) {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
	}

	/** The best harvesting tool. **/
	@Override
	public abstract String getHarvestTool(IBlockState state);

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}
}
