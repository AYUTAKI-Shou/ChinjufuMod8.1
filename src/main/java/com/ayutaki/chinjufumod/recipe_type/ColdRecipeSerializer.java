package com.ayutaki.chinjufumod.recipe_type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ColdRecipeSerializer<T extends AbstractColdRecipe> extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T> {
	private final int defaultCookTime;
	private final ColdRecipeSerializer.IFactory<T> factory;
	
	public ColdRecipeSerializer(ColdRecipeSerializer.IFactory<T> factoryIn, int defaultTime) {
		this.defaultCookTime = defaultTime;
		this.factory = factoryIn;
	}

	@SuppressWarnings("deprecation")
	@Override
	public T fromJson(ResourceLocation name, JsonObject jsonObject) {
		String groupName = GsonHelper.getAsString(jsonObject, "group", "");
		JsonElement jsonelement = (JsonElement)(GsonHelper.isArrayNode(jsonObject, "ingredient") ? GsonHelper.getAsJsonArray(jsonObject, "ingredient") : GsonHelper.getAsJsonObject(jsonObject, "ingredient"));
		Ingredient inputList = Ingredient.fromJson(jsonelement);
		//Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
		if (!jsonObject.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
		ItemStack output;
		if (jsonObject.get("result").isJsonObject()) output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
		else {
		String s1 = GsonHelper.getAsString(jsonObject, "result");
		ResourceLocation resourcelocation = new ResourceLocation(s1);
		output = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
			return new IllegalStateException("Item: " + s1 + " does not exist");
		}));
		}
		float exp = GsonHelper.getAsFloat(jsonObject, "experience", 0.0F);
		int time = GsonHelper.getAsInt(jsonObject, "cookingtime", this.defaultCookTime);
		return this.factory.create(name, groupName, inputList, output, exp, time);
	}

	@Override
	public T fromNetwork(ResourceLocation name, FriendlyByteBuf buffer) {
		String groupName = buffer.readUtf(32767);
		Ingredient inputList = Ingredient.fromNetwork(buffer);
		ItemStack output = buffer.readItem();
		float exp = buffer.readFloat();
		int time = buffer.readVarInt();
		return this.factory.create(name, groupName, inputList, output, exp, time);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, T iRecipe) {
		buffer.writeUtf(iRecipe.group);
		iRecipe.ingredient.toNetwork(buffer);
		buffer.writeItem(iRecipe.result);
		buffer.writeFloat(iRecipe.experience);
		buffer.writeVarInt(iRecipe.cookingTime);
	}
	
	interface IFactory<T extends AbstractColdRecipe> {
		T create(ResourceLocation name, String groupName, Ingredient inputList, ItemStack output, float exp, int time);
	}
}
