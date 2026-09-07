package com.ayutaki.chinjufumod.recipe_type;

import java.util.Map;
import java.util.Map.Entry;

import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Recipe_Freeze {

	private static final Recipe_Freeze FREEZE_RECIPE = new Recipe_Freeze();
	private final Map<ItemStack, ItemStack> freezeList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> expList = Maps.<ItemStack, Float>newHashMap();
	
	public static Recipe_Freeze instance() {
		return FREEZE_RECIPE;
	}
	
	public Recipe_Freeze() {
		this.addCookRecipe(new ItemStack(Items.WATER_BUCKET, 1, 0), new ItemStack(Item.getItemFromBlock(Blocks.ICE), 1, 0), 0.25F);
		this.addCookRecipe(new ItemStack(Items_Teatime.MIZUOKE_full, 1, 0), new ItemStack(Item.getItemFromBlock(Blocks.ICE), 1, 0), 0.25F);
		
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 10), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 10), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 11), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 11), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 12), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 12), 0.5F);
		this.addCookRecipe(new ItemStack(Items_NoTab.KANTEN_RAW, 1, 13), new ItemStack(Items_Teatime.KANTEN_BOWL, 1, 13), 0.5F);
	}
	
	public void addCookRecipe(ItemStack input, ItemStack result, float exp) {
		if (getCookResult(input) != ItemStack.EMPTY) { 
			net.minecraftforge.fml.common.FMLLog.log.info("Add freeze recipe: {} = {}", input, result); return; }
		
		this.freezeList.put(input, result);
		this.expList.put(result, Float.valueOf(exp));
	}
	
	public ItemStack getCookResult(ItemStack input) {
		for (Entry<ItemStack, ItemStack> entry : this.freezeList.entrySet()) {
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
		return this.freezeList;
	}
	
	public Map<ItemStack, Float> getExpList() {
		return this.expList;
	}
}
