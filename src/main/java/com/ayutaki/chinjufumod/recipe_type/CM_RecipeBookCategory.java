package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CM_RecipeBookCategory {
	public static final DeferredRegister<RecipeBookCategory> RECIPE_CATEGORY = DeferredRegister.create(BuiltInRegistries.RECIPE_BOOK_CATEGORY, ChinjufuMod.MOD_ID);
	
	public static final DeferredHolder<RecipeBookCategory, RecipeBookCategory> COLD_RECIPE = RECIPE_CATEGORY
			.register("cold_recipe", RecipeBookCategory::new);
}
