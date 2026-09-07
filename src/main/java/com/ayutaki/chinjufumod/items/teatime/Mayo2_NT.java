package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.item.ItemStack;

public class Mayo2_NT extends BaseMayo_NT {

	public Mayo2_NT(String name) {
		super(name);
		setContainerItem(Items_NoTab.MAYO_bot_34);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.MAYO_bot_34);
	}
}
