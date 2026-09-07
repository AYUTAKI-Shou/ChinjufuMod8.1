package com.ayutaki.chinjufumod.items.addtab;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;

import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;

public class Food_Teatime extends ItemFood {

	public Food_Teatime(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setCreativeTab(ChinjufuModTabs.TEATIME);
	}
}
