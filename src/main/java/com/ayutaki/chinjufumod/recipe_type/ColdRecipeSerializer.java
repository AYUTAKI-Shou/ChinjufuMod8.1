package com.ayutaki.chinjufumod.recipe_type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ColdRecipeSerializer<T extends AbstractColdRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
	private final int defaultCookTime;
	private final ColdRecipeSerializer.IFactory<T> factory;
	
	public ColdRecipeSerializer(ColdRecipeSerializer.IFactory<T> factoryIn, int defaultTime) {
		this.defaultCookTime = defaultTime;
		this.factory = factoryIn;
	}

	@SuppressWarnings("deprecation")
	@Override
	public T read(ResourceLocation name, JsonObject jsonObject) {
		String groupName = JSONUtils.getString(jsonObject, "group", "");
		JsonElement jsonelement = (JsonElement)(JSONUtils.isJsonArray(jsonObject, "ingredient") ? JSONUtils.getJsonArray(jsonObject, "ingredient") : JSONUtils.getJsonObject(jsonObject, "ingredient"));
		Ingredient inputList = Ingredient.deserialize(jsonelement);
		//Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
		if (!jsonObject.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
		ItemStack output;
		if (jsonObject.get("result").isJsonObject()) output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(jsonObject, "result"));
		else {
		String s1 = JSONUtils.getString(jsonObject, "result");
		ResourceLocation resourcelocation = new ResourceLocation(s1);
		output = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> {
			return new IllegalStateException("Item: " + s1 + " does not exist");
		}));
		}
		float  exp = JSONUtils.getFloat(jsonObject, "experience", 0.0F);
		int time = JSONUtils.getInt(jsonObject, "cookingtime", this.defaultCookTime);
		return this.factory.create(name, groupName, inputList, output, exp, time);
	}

	@Override
	public T read(ResourceLocation name, PacketBuffer buffer) {
		String groupName = buffer.readString(32767);
		Ingredient inputList = Ingredient.read(buffer);
		ItemStack output = buffer.readItemStack();
		float exp = buffer.readFloat();
		int time = buffer.readVarInt();
		return this.factory.create(name, groupName, inputList, output, exp, time);
	}

	@Override
	public void write(PacketBuffer buffer, T iRecipe) {
		buffer.writeString(iRecipe.group);
		iRecipe.ingredient.write(buffer);
		buffer.writeItemStack(iRecipe.result);
		buffer.writeFloat(iRecipe.experience);
		buffer.writeVarInt(iRecipe.cookingTime);
	}
	
	interface IFactory<T extends AbstractColdRecipe> {
		T create(ResourceLocation name, String groupName, Ingredient inputList, ItemStack output, float exp, int time);
	}
}
