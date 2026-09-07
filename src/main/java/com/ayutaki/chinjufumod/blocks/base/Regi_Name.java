package com.ayutaki.chinjufumod.blocks.base;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public abstract class Regi_Name extends Abstract_Harvest {

	public Regi_Name(String name, Material material) {
		super(material);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
	}
}
