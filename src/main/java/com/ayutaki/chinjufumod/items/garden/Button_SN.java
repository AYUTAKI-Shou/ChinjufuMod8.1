package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Button_SN extends TN_SubButton {

	public Button_SN(String name) {
		super(name);
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_button_sakura";
		case 1:
			return "item." + "block_button_kaede";
		case 2:
			return "item." + "block_button_ichoh";
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
		if (k == 0) { return Seasonal_Blocks.SAKURA_BUTTON; }
		if (k == 1) { return Seasonal_Blocks.KAEDE_BUTTON; }
		else { return Seasonal_Blocks.ICHOH_BUTTON; }
	}
}
