package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Ine_TT extends IR_Teatime {

	public Ine_TT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
		setMaxStackSize(64);
		
		setContainerItem(Items_Teatime.INEWARA);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_Teatime.INEWARA);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_ine";
		case 1:
			return "item." + "item_ine_dry";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
		}
	}
	/** 稲 item_ine -> (脱穀)稲わら＋種籾 item_seeds_rice -> 米 item_kome, 箱保管は種籾 **/

	/** さや item_saya -> (脱穀)大豆種 item_seeds_soy -> 大豆 item_soy, 箱保管は大豆種 **/
}
