package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class KantenBowl_TT extends IR_Teatime {

	public KantenBowl_TT(String name) {
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
			return "item." + "item_bowl_kanten_apple";
		case 1:
			return "item." + "item_bowl_kanten_cherry";
		case 2:
			return "item." + "item_bowl_kanten_citrus";
		case 3:
			return "item." + "item_bowl_kanten_grape";
		case 4:
			return "item." + "item_bowl_kanten_milk";
		case 5:
			return "item." + "item_bowl_yokan";
		case 6:
			return "item." + "item_bowl_yokan_matcha";

		case 10:
			return "item." + "item_bowl_icecream";
		case 11:
			return "item." + "item_bowl_icecream_greentea";
		case 12:
			return "item." + "item_bowl_icecream_redtea";
		case 13:
			return "item." + "item_bowl_icecream_cacao";
		}
	}
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 10));
			items.add(new ItemStack(this, 1, 11));
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
		}
	}
}
