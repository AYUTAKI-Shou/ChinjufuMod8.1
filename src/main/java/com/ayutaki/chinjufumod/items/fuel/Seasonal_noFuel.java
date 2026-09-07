package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Seasonal_noFuel extends NoGroup_noFuel {

	public Seasonal_noFuel(Block block, Item.Properties props) {
		super(block, props.group(ItemGroups_CM.SEASONAL));
	}
}
