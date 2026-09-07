package com.ayutaki.chinjufumod.recipe_type;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CM_RecipeSerializers {
	
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ChinjufuMod.MOD_ID);

	public static final RegistryObject<RecipeSerializer<Recipe_Chill>> CHILL_SERIALIZER = register("chill_recipe", () -> new ColdRecipeSerializer<>(Recipe_Chill::new, 400));
	public static final RegistryObject<RecipeSerializer<Recipe_Freeze>> FREEZE_SERIALIZER = register("freeze_recipe", () -> new ColdRecipeSerializer<>(Recipe_Freeze::new, 400));

	private static <T extends RecipeSerializer<? extends Recipe<?>>> RegistryObject<T> register(String name, Supplier<T> serializer) {
		return SERIALIZERS.register(name, serializer);
	}
}
