package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Nimame_TT extends IR_Teatime {

	public Nimame_TT(String name) {
		super(name);
		setUnlocalizedName(name);
		setMaxStackSize(64);
		
		setContainerItem(Items.BOWL);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items.BOWL);
	}
}
