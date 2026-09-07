package com.ayutaki.chinjufumod.recipe_type;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Blocks;

public class Recipe_Freeze extends AbstractColdRecipe {

	public static final RecipeSerializer<?> SERIALIZER = new ColdRecipeSerializer<>(Recipe_Freeze::new, 400);
	
	public Recipe_Freeze(ResourceLocation name, String groupName, Ingredient inputList, ItemStack output, float exp, int time) {
		super(CM_RecipeInit.FREEZE_RECIPE, name, groupName, inputList, output, exp, time);
	}

	public ItemStack getToastSymbol() {
		return new ItemStack(Blocks.ICE);
	}

	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
}
