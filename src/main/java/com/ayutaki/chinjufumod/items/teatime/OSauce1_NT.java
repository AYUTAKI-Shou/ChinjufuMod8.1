package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class OSauce1_NT extends BaseOSauce_NT {

	public OSauce1_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.OSAUCE_bot_24);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.OSAUCE_bot_24);
	}
}
