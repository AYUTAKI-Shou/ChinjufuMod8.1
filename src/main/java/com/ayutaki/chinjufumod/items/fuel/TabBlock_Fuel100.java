package com.ayutaki.chinjufumod.items.fuel;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TabBlock_Fuel100 extends TabBlock_noFuel {

	public TabBlock_Fuel100(String name, Block putBlock) {
		super(name, putBlock);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 100;
	}
}
