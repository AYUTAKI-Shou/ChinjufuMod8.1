package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ZundouAku_Item extends Not_Fuel {

	public ZundouAku_Item(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_Teatime.ZUNDOU.get()));
	}
}
