package com.ayutaki.chinjufumod.items.ranma;

import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Koushi_DN extends TN_SubRanma {

	public Koushi_DN(String name) {
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
			return "item." + "block_koushi_oak";
		case 1:
			return "item." + "block_koushi_spru";
		case 2:
			return "item." + "block_koushi_bir";
		case 3:
			return "item." + "block_koushi_jun";
		case 4:
			return "item." + "block_koushi_aca";
		case 5:
			return "item." + "block_koushi_doak";
			
		case 6:
			return "item." + "block_koushib_oak";
		case 7:
			return "item." + "block_koushib_spru";
		case 8:
			return "item." + "block_koushib_bir";
		case 9:
			return "item." + "block_koushib_jun";
		case 10:
			return "item." + "block_koushib_aca";
		case 11:
			return "item." + "block_koushib_doak";
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
			items.add(new ItemStack(this, 1, 9));
			items.add(new ItemStack(this, 1, 10));
			items.add(new ItemStack(this, 1, 11));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return JPDeco_Blocks.KOUSHI_oak; }
		if (k == 1) { return JPDeco_Blocks.KOUSHI_spru; }
		if (k == 2) { return JPDeco_Blocks.KOUSHI_bir; }
		if (k == 3) { return JPDeco_Blocks.KOUSHI_jun; }
		if (k == 4) { return JPDeco_Blocks.KOUSHI_aca; }
		if (k == 5) { return JPDeco_Blocks.KOUSHI_doak; }
		
		if (k == 6) { return JPDeco_Blocks.KOUSHIB_oak; }
		if (k == 7) { return JPDeco_Blocks.KOUSHIB_spru; }
		if (k == 8) { return JPDeco_Blocks.KOUSHIB_bir; }
		if (k == 9) { return JPDeco_Blocks.KOUSHIB_jun; }
		if (k == 10) { return JPDeco_Blocks.KOUSHIB_aca; }
		else { return JPDeco_Blocks.KOUSHIB_doak; }
	}
}
