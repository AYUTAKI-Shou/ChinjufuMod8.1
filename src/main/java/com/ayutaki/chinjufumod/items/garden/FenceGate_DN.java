package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.doors.Door_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class FenceGate_DN extends TN_SubFenceGate {

	public FenceGate_DN(String name) {
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
			return "item." + "block_bamboo_fencegate";
		case 1:
			return "item." + "block_bamboo_y_fencegate";
		case 2:
			return "item." + "block_bamboo_k_fencegate";
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
		if (k == 0) { return Door_Blocks.TAKEFENCEGATE; }
		if (k == 1) { return Door_Blocks.TAKEFENCEGATE_Y; }
		else { return Door_Blocks.TAKEFENCEGATE_K; }
	}
}
