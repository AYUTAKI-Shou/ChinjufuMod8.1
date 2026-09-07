package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.items.fuel.TabBlock_noFuel;
import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class KuriIga_TB extends TabBlock_noFuel {

	public KuriIga_TB(String name, Block putBlock) {
		super(name, putBlock);
		setMaxStackSize(64);
		
		setContainerItem(Items_NoTab.IGA);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_NoTab.IGA);
	}
}
