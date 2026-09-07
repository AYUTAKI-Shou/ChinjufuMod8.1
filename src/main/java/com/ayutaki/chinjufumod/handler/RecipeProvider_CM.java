package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;
import com.ayutaki.chinjufumod.tags.ItemCTags_CM;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;

public class RecipeProvider_CM extends RecipeProvider {
	
	public RecipeProvider_CM(RecipeOutput output, HolderLookup.Provider provider) {
		super(provider, output);
	}
	
	@Override
	protected void buildRecipes() {
		HolderGetter<Item> getter = this.registries.lookupOrThrow(Registries.ITEM);

		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items_Weapon.DAMECON.get(), 1)
			.group("cm_firstaid")
			.requires(Items.GOLDEN_APPLE)
			.requires(CompoundIngredient.of(DataComponentIngredient
					.of(false, PotionContents.createItemStack(Items.POTION, Potions.WEAKNESS))))
			.requires(Items_Chinjufu.WORK_ORDER.get())
			.unlockedBy("has_item", has(Items.GOLDEN_APPLE))
		.save(this.output);
		
		ShapelessRecipeBuilder.shapeless(getter, RecipeCategory.MISC, Items_Weapon.MEGAMI.get(), 1)
			.group("cm_firstaid")
			.requires(Items.GOLDEN_APPLE)
			.requires(ItemCTags_CM.CROPS_APPLE)
			.requires(ItemCTags_CM.CROPS_APPLE)
			.requires(ItemCTags_CM.CROPS_APPLE)
			.requires(ItemCTags_CM.CROPS_APPLE)
			.requires(Items.TOTEM_OF_UNDYING)
			.requires(Items_Chinjufu.WORK_ORDER.get())
			.unlockedBy("has_item", has(Items.GOLDEN_APPLE))
		.save(this.output);
	}
}
