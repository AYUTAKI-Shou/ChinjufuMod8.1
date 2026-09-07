package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Fence_SN extends TN_SubFence {

	public Fence_SN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_fence_sakura";
		case 1:
			return "item." + "block_fence_kaede";
		case 2:
			return "item." + "block_fence_ichoh";
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
		if (k == 0) { return Seasonal_Blocks.SAKURA_FENCE; }
		if (k == 1) { return Seasonal_Blocks.KAEDE_FENCE; }
		else { return Seasonal_Blocks.ICHOH_FENCE; }
	}
}
