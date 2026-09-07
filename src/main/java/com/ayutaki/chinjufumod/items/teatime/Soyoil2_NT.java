package com.ayutaki.chinjufumod.items.teatime;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Soyoil2_NT extends BaseSoyoil_NT {

	public Soyoil2_NT(String name) {
		super(name);
		setContainerItem(Items.GLASS_BOTTLE);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items.GLASS_BOTTLE);
	}
}
