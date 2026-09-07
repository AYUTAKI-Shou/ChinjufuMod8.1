package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.items.foods.FoodPoints;
import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;

public class HamaguriCooked extends Item {

	public HamaguriCooked(Item.Properties props) {
		super(props.food(FoodPoints.COOKED_HAMAGURI).usingConvertsTo(Items_NoTab.HAMAGURI_KARA.get()));
	}
}
