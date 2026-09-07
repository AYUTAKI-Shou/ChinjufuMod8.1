package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.items.dish.BaseDishAlways;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SakeGlass extends BaseDishAlways {

	public SakeGlass(Block block, Item.Properties props) {
		super(block, props.usingConvertsTo(Items_Teatime.DRINKGLASS.get()));
	}
}
