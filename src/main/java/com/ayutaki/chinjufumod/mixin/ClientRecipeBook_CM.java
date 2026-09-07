package com.ayutaki.chinjufumod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBook_CM {

	@OnlyIn(Dist.CLIENT)
	@Inject(method = "getCategory", at = @At(value = "HEAD"), cancellable = true)
	private static void addCategory(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookCategories> callback) {
		if (recipe instanceof AbstractColdRecipe) {
			callback.setReturnValue(RecipeBookCategories.CAMPFIRE);
		}
	}
}
