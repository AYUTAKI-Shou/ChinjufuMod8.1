package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class Dashi3_NT extends BaseDashi_NT {

	public Dashi3_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.DASHI_bot_44);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.DASHI_bot_44);
	}
}
