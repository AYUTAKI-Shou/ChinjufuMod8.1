package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class PresentB_ST extends PresentSub_ST {

	public PresentB_ST(String name) {
		super(name, Seasonal_Blocks.PRESENT_B);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/**1=Blaze, 2=Choco, 3=HeartChoco, 4= **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_present_bla";
		case 2:
			return "item." + "block_present_chc";
		case 3:
			return "item." + "block_present_chh";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Seasonal_Blocks.PRESENT_B;
	}
}
