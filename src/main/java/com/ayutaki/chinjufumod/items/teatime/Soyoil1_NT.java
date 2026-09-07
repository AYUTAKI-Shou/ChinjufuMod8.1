package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class Soyoil1_NT extends BaseSoyoil_NT {

	public Soyoil1_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.SOYOIL_bot_22);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.SOYOIL_bot_22);
	}
}
