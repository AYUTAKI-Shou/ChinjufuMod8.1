package com.ayutaki.chinjufumod.blocks.base;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.ResourceLocation;

public abstract class Regi_addState extends Abstract_Harvest {

	public Regi_addState(String name) {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
	}

	/* Create BlockStates in this block. */
	@Override
	protected abstract BlockStateContainer createBlockState();
}
