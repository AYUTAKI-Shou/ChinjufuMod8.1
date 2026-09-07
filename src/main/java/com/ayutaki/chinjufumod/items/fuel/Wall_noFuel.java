package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Wall_noFuel extends NoGroup_noFuel {

	public Wall_noFuel(Block block, Item.Properties props) {
		super(block, props.group(ItemGroups_CM.WALLPANEL));
	}
}
