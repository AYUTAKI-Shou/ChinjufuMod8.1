package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.items.addtab.IR_Seasonal;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class KuriBoil_ST extends IR_Seasonal {

	public KuriBoil_ST(String name) {
		super(name);
		setUnlocalizedName(name);
		setMaxStackSize(64);
		
		setContainerItem(Items.BOWL);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items.BOWL);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_chestnut_boil";
		case 1:
			return "item." + "item_chestnut_mash";
		}
	}
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
		}
	}
}
