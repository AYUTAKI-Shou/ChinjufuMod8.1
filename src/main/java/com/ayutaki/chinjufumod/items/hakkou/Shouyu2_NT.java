package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class Shouyu2_NT extends BaseShouyu_NT {

	public Shouyu2_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.SHOUYU_bot_34);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.SHOUYU_bot_34);
	}
}
