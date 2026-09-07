package com.ayutaki.chinjufumod.items.cooktool;

import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.item.ItemStack;

public class KettleBoil_TT extends IBR_Teatime {

	public KettleBoil_TT(String name) {
		super(name, Dish_Blocks.KETTLE_full);
		setUnlocalizedName(name);

		/*クラフトで使うと, 空のやかんが返ってくる*/
		setMaxStackSize(1);
		setContainerItem(Items_Teatime.Item_YAKAN_kara);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_Teatime.Item_YAKAN_kara);
	}
}
