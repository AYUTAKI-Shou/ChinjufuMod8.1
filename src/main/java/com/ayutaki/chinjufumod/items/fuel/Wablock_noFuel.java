package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Wablock_noFuel extends NoGroup_noFuel {

	public Wablock_noFuel(Block block, Item.Properties props) {
		super(block, props.group(ItemGroups_CM.WABLOCK));
	}
}
