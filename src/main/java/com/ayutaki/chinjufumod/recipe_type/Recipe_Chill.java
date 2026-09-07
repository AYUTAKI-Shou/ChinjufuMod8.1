package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class Recipe_Chill extends AbstractColdRecipe {

	public Recipe_Chill(String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time) {
		super(groupName, name, inputList, output, exp, time);
	}

	public RecipeSerializer<Recipe_Chill> getSerializer() {
		return CM_RecipeSerializers.CHILL_SERIALIZER.get();
	}

	@Override
	public RecipeType<Recipe_Chill> getType() {
		return CM_RecipeType.CHILL_RECIPE.get();
	}

	@Override
	protected Item furnaceIcon() {
		return Items_Teatime.KIT_REIZOU.get();
	}
}
