package com.ayutaki.chinjufumod.recipe_type;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CM_RecipeSerializers {
	
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ChinjufuMod.MOD_ID);

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Recipe_Chill>> CHILL_SERIALIZER = register("chill_recipe", () -> new ColdRecipeSerializer<>(Recipe_Chill::new, 400));
	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<Recipe_Freeze>> FREEZE_SERIALIZER = register("freeze_recipe", () -> new ColdRecipeSerializer<>(Recipe_Freeze::new, 400));
	
	public static <T extends RecipeSerializer<?>> DeferredHolder<RecipeSerializer<?>, T> register(String name, Supplier<T> serializer) {
		return SERIALIZERS.register(name, serializer);
	}
}
