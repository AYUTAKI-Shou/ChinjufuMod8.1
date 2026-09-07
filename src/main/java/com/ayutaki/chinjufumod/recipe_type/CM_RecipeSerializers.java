package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CM_RecipeSerializers {
	
	public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ChinjufuMod.MOD_ID);

	public static final RegistryObject<IRecipeSerializer<Recipe_Chill>> CHILL_SERIALIZER = SERIALIZER
			.register("chill_recipe", () -> new ColdRecipeSerializer<>(Recipe_Chill::new, 400));
	public static final RegistryObject<IRecipeSerializer<Recipe_Freeze>> FREEZE_SERIALIZER = SERIALIZER
			.register("freeze_recipe", () -> new ColdRecipeSerializer<>(Recipe_Freeze::new, 400));
	
}
