package com.ayutaki.chinjufumod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeHolder;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBook_CM {

	@Inject(method = "getCategory", at = @At(value = "HEAD"), cancellable = true)
	private static void addCategory(RecipeHolder<?> recipe, CallbackInfoReturnable<RecipeBookCategories> callback) {
		if (recipe.value() instanceof AbstractColdRecipe) {
			callback.setReturnValue(RecipeBookCategories.UNKNOWN);
		}
	}
}
