package com.ayutaki.chinjufumod.items.fuel;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TabBlock_Fuel300 extends TabBlock_noFuel {

	public TabBlock_Fuel300(String name, Block putBlock) {
		super(name, putBlock);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}
}
