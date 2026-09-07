package com.ayutaki.chinjufumod.recipe_type;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class CM_RecipeType {
	
	public static final IRecipeType<Recipe_Chill> CHILL_RECIPE = register("chinjufumod:chill_recipe");
	public static final IRecipeType<Recipe_Freeze> FREEZE_RECIPE = register("chinjufumod:freeze_recipe");
	
	static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(key), new IRecipeType<T>() {
			public String toString() { return key; }
		});
	}
}
