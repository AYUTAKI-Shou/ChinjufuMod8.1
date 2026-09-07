package com.ayutaki.chinjufumod.items.foods;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;

public class CherryFruit extends Item {

	public CherryFruit(Item.Properties props) {
		super(props.food(FoodPoints.FPS1_01A, FoodEffects.DEFAULT_FOOD).usingConvertsTo(Items_Teatime.SEEDS_CHERRY.get()));
	}
}
