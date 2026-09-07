package com.ayutaki.chinjufumod.recipe_type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ColdRecipeSerializer<T extends AbstractColdRecipe> implements RecipeSerializer<T> {
	private final MapCodec<T> codec;
	private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;
	
	protected ColdRecipeSerializer(AbstractColdRecipe.Factory<T> factoryIn, int defaultTime) {
		this.codec = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
						Codec.STRING.optionalFieldOf("group", "").forGetter(AbstractColdRecipe::group),
						CookingBookCategory.CODEC.fieldOf("category").orElse(CookingBookCategory.MISC).forGetter(AbstractColdRecipe::category),
						Ingredient.CODEC.fieldOf("ingredient").forGetter(AbstractColdRecipe::input),
						ItemStack.CODEC.fieldOf("result").forGetter(AbstractColdRecipe::result),
						Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(AbstractColdRecipe::getExperience),
						Codec.INT.fieldOf("cookingtime").orElse(defaultTime).forGetter(AbstractColdRecipe::getCookingTime))
				.apply(instance, factoryIn::create));
		
		this.streamCodec = StreamCodec.composite(
				ByteBufCodecs.STRING_UTF8,
				AbstractColdRecipe::group,
				CookingBookCategory.STREAM_CODEC,
				AbstractColdRecipe::category,
				Ingredient.CONTENTS_STREAM_CODEC,
				AbstractColdRecipe::input,
				ItemStack.STREAM_CODEC,
				AbstractColdRecipe::result,
				ByteBufCodecs.FLOAT,
				AbstractColdRecipe::getExperience,
				ByteBufCodecs.INT,
				AbstractColdRecipe::getCookingTime,
				factoryIn::create);
	}

	public MapCodec<T> codec() {
		return this.codec;
	}
	
	public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
		return this.streamCodec;
	}
}
