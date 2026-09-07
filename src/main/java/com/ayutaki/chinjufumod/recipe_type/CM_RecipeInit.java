package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.RegistryEvent;

//for 1.18.2 from Rustic-Reborn by RenaatiuX.
public class CM_RecipeInit {
	
	public static final RecipeType<Recipe_Chill> CHILL_RECIPE = register("chill_recipe");
	public static final RecipeType<Recipe_Freeze> FREEZE_RECIPE = register("freeze_recipe");

	protected static <T extends Recipe<?>> RecipeType<T> register(String key){
		return new RecipeType<T>() {
			@Override
			public String toString() { return new ResourceLocation(ChinjufuMod.MOD_ID, key).toString(); }
		};
	}
	
	public static void registerRecipes(RegistryEvent.Register<RecipeSerializer<?>> event) {
		registerRecipe(event, CHILL_RECIPE, Recipe_Chill.SERIALIZER);
		registerRecipe(event, FREEZE_RECIPE, Recipe_Freeze.SERIALIZER);
	}
	
	private static void registerRecipe(RegistryEvent.Register<RecipeSerializer<?>> event, RecipeType<?> type, RecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer.setRegistryName(new ResourceLocation(type.toString())));
	}
}
