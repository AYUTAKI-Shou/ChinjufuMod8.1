package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Button_DN extends TN_SubButton {

	public Button_DN(String name) {
		super(name);
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_bamboo_button";
		case 1:
			return "item." + "block_bamboo_y_button";
		case 2:
			return "item." + "block_bamboo_k_button";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return JPDeco_Blocks.TAKE_BUTTON; }
		if (k == 1) { return JPDeco_Blocks.TAKE_BUTTON_Y; }
		else { return JPDeco_Blocks.TAKE_BUTTON_K; }
	}
}
