package com.ayutaki.chinjufumod.recipe_type;

import java.util.Map;
import java.util.Map.Entry;

import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

public class Recipe_Chill {

	private static final Recipe_Chill CHILL_RECIPE = new Recipe_Chill();
	private final Map<ItemStack, ItemStack> chillList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> expList = Maps.<ItemStack, Float>newHashMap();
	
	public static Recipe_Chill instance() {
		return CHILL_RECIPE;
	}
	
	public Recipe_Chill() {
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 0), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 0), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 1), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 1), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 2), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 2), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 3), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 3), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 4), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 4), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 5), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 5), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 6), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 6), 0.5F);

		this.addCookRecipe(new ItemStack(Items_NoTab.PUDDING_RAW, 1, 1), new ItemStack(Items_Teatime.ICECREAM, 3, 4), 1.0F);
		this.addCookRecipe(new ItemStack(Items_NoTab.PUDDING_RAW, 1, 2), new ItemStack(Items_Teatime.ICECREAM, 3, 5), 1.0F);
		this.addCookRecipe(new ItemStack(Items_NoTab.PUDDING_RAW, 1, 3), new ItemStack(Items_Teatime.ICECREAM, 3, 6), 1.0F);
		this.addCookRecipe(new ItemStack(Items_NoTab.PUDDING_RAW, 1, 4), new ItemStack(Items_Teatime.ICECREAM, 3, 7), 1.0F);
	}
	
	public void addCookRecipe(ItemStack input, ItemStack result, float exp) {
		if (getCookResult(input) != ItemStack.EMPTY) { 
			net.minecraftforge.fml.common.FMLLog.log.info("Add chill recipe: {} = {}", input, result); return; }
		
		this.chillList.put(input, result);
		this.expList.put(result, Float.valueOf(exp));
	}
	
	public ItemStack getCookResult(ItemStack input) {
		for (Entry<ItemStack, ItemStack> entry : this.chillList.entrySet()) {
			if (this.compareItemStacks(input, entry.getKey())) { return entry.getValue(); }
		}
		return ItemStack.EMPTY;
	}
	
	public float getCookExp(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.expList.entrySet()) {
			if (this.compareItemStacks(stack, entry.getKey())) { return ((Float)entry.getValue()).floatValue(); }
		}
		return 0.0F;
	}
	
	private boolean compareItemStacks(ItemStack input1, ItemStack input2) {
		return input2.getItem() == input1.getItem() && (input2.getMetadata() == 32767 || input2.getMetadata() == input1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getFreezeList() {
		return this.chillList;
	}
	
	public Map<ItemStack, Float> getExpList() {
		return this.expList;
	}
}
