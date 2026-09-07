package com.ayutaki.chinjufumod.recipe_type;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class Recipe_Freeze extends AbstractColdRecipe {

	public Recipe_Freeze(ResourceLocation name, String groupName, Ingredient inputList, ItemStack output, float exp, int time) {
		super(CM_RecipeType.FREEZE_RECIPE, name, groupName, inputList, output, exp, time);
	}

	public ItemStack getToastSymbol() {
	  return new ItemStack(Blocks.ICE);
	}

	public IRecipeSerializer<?> getSerializer() {
	  return CM_RecipeSerializers.FREEZE_SERIALIZER.get();
	}
}
