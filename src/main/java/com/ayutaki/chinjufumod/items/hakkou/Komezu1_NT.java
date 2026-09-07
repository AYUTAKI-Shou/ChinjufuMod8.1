package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class Komezu1_NT extends BaseKomezu_NT {

	public Komezu1_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.KOMEZU_bot_22);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.KOMEZU_bot_22);
	}
}
