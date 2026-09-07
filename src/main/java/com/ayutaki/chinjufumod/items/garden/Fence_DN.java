package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Fence_DN extends TN_SubFence {

	public Fence_DN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_bamboo_fence";
		case 1:
			return "item." + "block_bamboo_y_fence";
		case 2:
			return "item." + "block_bamboo_k_fence";
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
		if (k == 0) { return JPDeco_Blocks.TAKEFENCE; }
		if (k == 1) { return JPDeco_Blocks.TAKEFENCE_Y; }
		else { return JPDeco_Blocks.TAKEFENCE_K; }
	}
}
