package com.ayutaki.chinjufumod.items.jpdeco;

import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Andon4_DT extends DT_SubAndon {

	public Andon4_DT(String name) {
		super(name, Lamp_Blocks.ANDON_4);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_andon_brown";
		case 2:
			return "item." + "block_andon_green";
		case 3:
			return "item." + "block_andon_red";
		case 4:
			return "item." + "block_andon_black";
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
		return Lamp_Blocks.ANDON_4;
	}
}
