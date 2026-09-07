package com.ayutaki.chinjufumod.items.jpdeco;

import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Andon2_DT extends DT_SubAndon {

	public Andon2_DT(String name) {
		super(name, Lamp_Blocks.ANDON_2);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_andon_yellow";
		case 2:
			return "item." + "block_andon_lime";
		case 3:
			return "item." + "block_andon_pink";
		case 4:
			return "item." + "block_andon_gray";
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
		return Lamp_Blocks.ANDON_2;
	}
}
