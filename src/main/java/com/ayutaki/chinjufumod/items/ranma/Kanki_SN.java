package com.ayutaki.chinjufumod.items.ranma;

import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Kanki_SN extends TN_SubRanma {

	public Kanki_SN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_kanki_saku";
		case 1:
			return "item." + "block_kanki_kae";
		case 2:
			return "item." + "block_kanki_ich";

		case 3:
			return "item." + "block_koushi_saku";
		case 4:
			return "item." + "block_koushi_kae";
		case 5:
			return "item." + "block_koushi_ich";
			
		case 6:
			return "item." + "block_koushib_saku";
		case 7:
			return "item." + "block_koushib_kae";
		case 8:
			return "item." + "block_koushib_ich";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Seasonal_Blocks.KANKI_saku; }
		if (k == 1) { return Seasonal_Blocks.KANKI_kae; }
		if (k == 2) { return Seasonal_Blocks.KANKI_ich; }
		
		if (k == 3) { return Seasonal_Blocks.KOUSHI_saku; }
		if (k == 4) { return Seasonal_Blocks.KOUSHI_kae; }
		if (k == 5) { return Seasonal_Blocks.KOUSHI_ich; }
		
		if (k == 6) { return Seasonal_Blocks.KOUSHIB_saku; }
		if (k == 7) { return Seasonal_Blocks.KOUSHIB_kae; }
		else { return Seasonal_Blocks.KOUSHIB_ich; }
	}
}
