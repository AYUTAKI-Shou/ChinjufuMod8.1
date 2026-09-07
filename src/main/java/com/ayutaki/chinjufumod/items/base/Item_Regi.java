package com.ayutaki.chinjufumod.items.base;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class Item_Regi extends Item {

	public Item_Regi(String name) {
		super();
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
	}
}
