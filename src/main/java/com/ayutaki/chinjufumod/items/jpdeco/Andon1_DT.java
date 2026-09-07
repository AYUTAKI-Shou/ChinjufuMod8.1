package com.ayutaki.chinjufumod.items.jpdeco;

import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Andon1_DT extends DT_SubAndon {

	public Andon1_DT(String name) {
		super(name, Lamp_Blocks.ANDON_1);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_andon_white";
		case 2:
			return "item." + "block_andon_orange";
		case 3:
			return "item." + "block_andon_magenta";
		case 4:
			return "item." + "block_andon_lightb";
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
		return Lamp_Blocks.ANDON_1;
	}
}
