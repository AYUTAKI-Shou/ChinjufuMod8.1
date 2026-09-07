package com.ayutaki.chinjufumod.items.fuel;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Chinjufu_noFuel extends NoGroup_noFuel {

	public Chinjufu_noFuel(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.CHINJUFU));
	}
}
