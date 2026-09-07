package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Itabei_DN extends TN_SubItabei {

	public Itabei_DN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_itabei";
		case 1:
			return "item." + "block_itabei_spruce";
		case 2:
			return "item." + "block_itabei_birch";
		case 3:
			return "item." + "block_itabei_jungle";
		case 4:
			return "item." + "block_itabei_acacia";
		case 5:
			return "item." + "block_itabei_darkoak";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}
	
	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Garden_Blocks.ITABEI; }
		if (k == 1) { return Garden_Blocks.ITABEI_spruce; }
		if (k == 2) { return Garden_Blocks.ITABEI_birch; }
		if (k == 3) { return Garden_Blocks.ITABEI_jungle; }
		if (k == 4) { return Garden_Blocks.ITABEI_acacia; }
		else { return Garden_Blocks.ITABEI_darkoak; }
	}
}
