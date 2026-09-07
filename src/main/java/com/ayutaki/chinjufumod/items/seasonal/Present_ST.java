package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Present_ST extends PresentSub_ST {

	public Present_ST(String name) {
		super(name, Seasonal_Blocks.PRESENT);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_present_app";
		case 2:
			return "item." + "block_present_bok";
		case 3:
			return "item." + "block_present_dia";
		case 4:
			return "item." + "block_present_lap";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Seasonal_Blocks.PRESENT;
	}
}
