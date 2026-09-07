package com.ayutaki.chinjufumod.items.fuel;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

public class Fuel_100 extends Not_Fuel {

	public Fuel_100(Block block, Item.Properties props) {
		super(block, props);
	}

	/* from IForgeItem. BurnTime in a Furnace */
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return 100;
	}
}
