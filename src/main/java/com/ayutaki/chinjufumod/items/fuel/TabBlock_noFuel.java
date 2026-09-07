package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class TabBlock_noFuel extends ItemBlock {

	public TabBlock_noFuel(String name, Block putBlock) {
		super(putBlock);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
	}
}
