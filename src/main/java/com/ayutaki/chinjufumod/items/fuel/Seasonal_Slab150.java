package com.ayutaki.chinjufumod.items.fuel;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Seasonal_Slab150 extends TabBlock_noFuel {

	public Seasonal_Slab150(String name, Block putBlock) {
		super(name, putBlock);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}
}
