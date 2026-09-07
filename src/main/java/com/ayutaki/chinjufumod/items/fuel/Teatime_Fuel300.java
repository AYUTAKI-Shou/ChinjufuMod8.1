package com.ayutaki.chinjufumod.items.fuel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Teatime_Fuel300 extends Teatime_noFuel {

	public Teatime_Fuel300(Block block, Item.Properties props) {
		super(block, props);
	}

	@Override
	public int getBurnTime(ItemStack stack) {
		return 300;
	}
}
