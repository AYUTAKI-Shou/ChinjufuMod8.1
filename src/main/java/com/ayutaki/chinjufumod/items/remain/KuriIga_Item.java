package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class KuriIga_Item extends Not_Fuel {

	public KuriIga_Item(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.IGA.get()));
	}
}
