package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Yunomi_Item extends BaseDishAlways {

	public Yunomi_Item(Block block, Item.Properties props) {
		super(block, props.usingConvertsTo(Items_Teatime.YUNOMI.get()));
	}
}
