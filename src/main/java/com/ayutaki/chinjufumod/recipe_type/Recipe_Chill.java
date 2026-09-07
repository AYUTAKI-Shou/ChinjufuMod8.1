package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class Recipe_Chill extends AbstractColdRecipe {

	public Recipe_Chill(ResourceLocation name, String groupName, Ingredient inputList, ItemStack output, float exp, int time) {
		super(CM_RecipeType.CHILL_RECIPE, name, groupName, inputList, output, exp, time);
	}

	public ItemStack getToastSymbol() {
	  return new ItemStack(Items_Teatime.KIT_REIZOU);
	}

	public IRecipeSerializer<?> getSerializer() {
	  return CM_RecipeSerializers.CHILL_SERIALIZER.get();
	}
}
