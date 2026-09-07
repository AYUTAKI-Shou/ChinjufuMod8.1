package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class CM_RecipeType {
	
	public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ChinjufuMod.MOD_ID);
	
	public static final DeferredHolder<RecipeType<?>, RecipeType<Recipe_Chill>> CHILL_RECIPE = register("chill_recipe");
	public static final DeferredHolder<RecipeType<?>, RecipeType<Recipe_Freeze>> FREEZE_RECIPE = register("freeze_recipe");
	
	public static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> register(String name) {
		return TYPES.register(name, () -> RecipeType.simple(ChinjufuMod.id(name)));
	}
}
