package com.ayutaki.chinjufumod.items.cooktool;

import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class NabeNama1_TT extends Abstract_NabeNama4 {

	public NabeNama1_TT(String name) {
		super(name, Dish_Blocks.NABE_nama);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 1=鶏鍋, 2=味噌, 3=ご飯, 4=コーンスープ **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_nabe_n";
		case 2:
			return "item." + "block_food_nabemiso_n";
		case 3:
			return "item." + "block_food_nabegohan_n";
		case 4:
			return "item." + "block_food_nabecorns_n";
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
		return Dish_Blocks.NABE_nama;
	}
}
