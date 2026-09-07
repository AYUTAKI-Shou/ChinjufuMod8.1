package com.ayutaki.chinjufumod.recipe_type;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record ColdRecipeInput(ItemStack item) implements RecipeInput {
	@Override
	public ItemStack getItem(int i) {
		return switch (i) {
			case 0 -> this.item;
			case 1 -> this.item;
			case 2 -> this.item;
			case 3 -> this.item;
			case 4 -> this.item;
			case 5 -> this.item;
			case 6 -> this.item;
			case 7 -> this.item;
			
			default -> throw new IllegalArgumentException("No item for index " + i);
		};
	}

	@Override
	public int size() {
		return 1;
	}
}
