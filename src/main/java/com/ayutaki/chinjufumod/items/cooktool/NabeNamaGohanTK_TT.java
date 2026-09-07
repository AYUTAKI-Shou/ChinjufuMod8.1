package com.ayutaki.chinjufumod.items.cooktool;

import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class NabeNamaGohanTK_TT extends Abstract_NabeNama4 {

	public NabeNamaGohanTK_TT(String name) {
		super(name, Dish_Blocks.NABE_nama_TK);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 1=タケノコ, 2=栗 **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_nabegohantake_n";
		case 2:
			return "item." + "block_food_nabegohankuri_n";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.NABE_nama_TK;
	}
}
