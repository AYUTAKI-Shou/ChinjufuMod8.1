package com.ayutaki.chinjufumod.recipe_type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ColdRecipeSerializer<T extends AbstractColdRecipe> implements RecipeSerializer<T> {
	private final AbstractColdRecipe.Factory<T> factory;
	private final MapCodec<T> codec;
	private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;
	
	protected ColdRecipeSerializer(AbstractColdRecipe.Factory<T> factoryIn, int defaultTime) {
		this.factory = factoryIn;
		
		this.codec = RecordCodecBuilder.mapCodec(
				instance -> instance.group(
						Codec.STRING.optionalFieldOf("group", "").forGetter(iRecipe -> iRecipe.group),
						CookingBookCategory.CODEC.fieldOf("category").orElse(CookingBookCategory.MISC).forGetter(iRecipe -> iRecipe.category),
						Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(iRecipe -> iRecipe.ingredient),
						ItemStack.STRICT_CODEC.fieldOf("result").forGetter(iRecipe -> iRecipe.result), //ItemStack L126,L103
						Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(iRecipe -> iRecipe.experience),
						Codec.INT.fieldOf("cookingtime").orElse(defaultTime).forGetter(iRecipe -> iRecipe.cookingTime))
				.apply(instance, factoryIn::create));
		
		this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
	}

	public MapCodec<T> codec() {
		return this.codec;
	}
	
	public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
		return this.streamCodec;
	}

	private T fromNetwork(RegistryFriendlyByteBuf buffer) {
		String groupName = buffer.readUtf(32767);
		CookingBookCategory name = buffer.readEnum(CookingBookCategory.class);
		Ingredient inputList = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
		ItemStack output = ItemStack.STREAM_CODEC.decode(buffer);
		float exp = buffer.readFloat();
		int time = buffer.readVarInt();
		return this.factory.create(groupName, name, inputList, output, exp, time);
	}

	private void toNetwork(RegistryFriendlyByteBuf buffer, T iRecipe) {
		buffer.writeUtf(iRecipe.group);
		buffer.writeEnum(iRecipe.category());
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, iRecipe.ingredient);
		ItemStack.STREAM_CODEC.encode(buffer, iRecipe.result);
		buffer.writeFloat(iRecipe.experience);
		buffer.writeVarInt(iRecipe.cookingTime);
	}

	public AbstractColdRecipe create(String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time) {
		return this.factory.create(groupName, name, inputList, output, exp, time);
	}
}
