package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.registry.doors.Door_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Kido_SN extends TN_SubKido {

	public Kido_SN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_kido_sakura";
		case 1:
			return "item." + "block_kido_kaede";
		case 2:
			return "item." + "block_kido_ichoh";
		}
	}

	@Override
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
		if (k == 0) { return Door_Blocks.KIDO_sakura; }
		if (k == 1) { return Door_Blocks.KIDO_kaede; }
		else { return Door_Blocks.KIDO_ichoh; }
	}
}
