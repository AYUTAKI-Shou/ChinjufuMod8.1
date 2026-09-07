package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Dish_Kanten extends BaseDishAlways {

	public Dish_Kanten(Block block, Item.Properties props) {
		super(block, props.usingConvertsTo(Items_Teatime.SARA.get()));
	}
}
