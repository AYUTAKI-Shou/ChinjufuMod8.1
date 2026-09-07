package com.ayutaki.chinjufumod.items.fuel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Seasonal_Fuel100 extends Seasonal_noFuel {

	public Seasonal_Fuel100(Block block, Item.Properties props) {
		super(block, props);
	}

	@Override
	public int getBurnTime(ItemStack stack) {
		return 100;
	}
}
