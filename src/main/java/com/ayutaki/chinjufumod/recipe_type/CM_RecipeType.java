package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CM_RecipeType {
	
	public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, ChinjufuMod.MOD_ID);
	
	public static RegistryObject<RecipeType<Recipe_Chill>> CHILL_RECIPE = register("chill_recipe");
	public static RegistryObject<RecipeType<Recipe_Freeze>> FREEZE_RECIPE = register("freeze_recipe");
	
	private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String name) {
		return TYPES.register(name, () -> new RecipeType<T>() {
			public String toString() {
				return ChinjufuMod.MOD_ID + ":" + name; }
		});
	}
}
