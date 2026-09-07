package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.items.base.Item_Regi;

import net.minecraft.item.ItemStack;

public class Iga_SN extends Item_Regi {

	public Iga_SN(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	/* BurnTime in a Furnace */
	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 50;
	}
}
