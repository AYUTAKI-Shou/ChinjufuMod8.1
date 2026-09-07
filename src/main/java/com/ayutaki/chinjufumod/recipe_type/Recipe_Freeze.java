package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class Recipe_Freeze extends AbstractColdRecipe {

	public Recipe_Freeze(String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time) {
		super(CM_RecipeType.FREEZE_RECIPE.get(), groupName, name, inputList, output, exp, time);
	}

	public ItemStack getToastSymbol() {
		return new ItemStack(Items_Teatime.KIT_REIZOU.get());
	}

	public RecipeSerializer<?> getSerializer() {
		return CM_RecipeSerializers.FREEZE_SERIALIZER.get();
	}
}
