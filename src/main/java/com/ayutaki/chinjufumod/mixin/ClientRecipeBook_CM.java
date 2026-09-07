package com.ayutaki.chinjufumod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;

import net.minecraft.client.util.ClientRecipeBook;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.item.crafting.IRecipe;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBook_CM {

	@Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
	private static void addCategory(IRecipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> callback) {
		if (recipe instanceof AbstractColdRecipe) {
			callback.setReturnValue(RecipeBookCategories.MISC);
		}
	}
}
