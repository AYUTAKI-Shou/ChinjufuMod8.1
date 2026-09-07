package com.ayutaki.chinjufumod.handler;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public class RecipeRunner_CM extends RecipeProvider.Runner {

	public RecipeRunner_CM(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
		return new RecipeProvider_CM(output, provider);
	}

	@Override
	public String getName() {
		return "ChinjufuMod Recipes";
	}
}
