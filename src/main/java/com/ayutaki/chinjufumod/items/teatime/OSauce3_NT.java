package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class OSauce3_NT extends BaseOSauce_NT {

	public OSauce3_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.OSAUCE_bot_44);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.OSAUCE_bot_44);
	}
}
