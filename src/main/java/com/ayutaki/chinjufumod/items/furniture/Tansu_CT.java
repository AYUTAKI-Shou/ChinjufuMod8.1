package com.ayutaki.chinjufumod.items.furniture;

import com.ayutaki.chinjufumod.items.addtab.Chinjufu_SubBlockFace1;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Tansu_CT extends Chinjufu_SubBlockFace1 {

	public Tansu_CT(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_tansu_oak";
		case 1:
			return "item." + "block_tansu_spruce";
		case 2:
			return "item." + "block_tansu_birch";
		case 3:
			return "item." + "block_tansu_jungle";
		case 4:
			return "item." + "block_tansu_acacia";
		case 5:
			return "item." + "block_tansu_doak";
		case 6:
			return "item." + "block_tansu_sakura";
		case 7:
			return "item." + "block_tansu_kaede";
		case 8:
			return "item." + "block_tansu_ichoh";
		}
	}

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
		if (k == 0) { return Furniture_Blocks.TANSU_OAK; }
		if (k == 1) { return Furniture_Blocks.TANSU_SPRUCE; }
		if (k == 2) { return Furniture_Blocks.TANSU_BIRCH; }
		if (k == 3) { return Furniture_Blocks.TANSU_JUNGLE; }
		if (k == 4) { return Furniture_Blocks.TANSU_ACACIA; }
		if (k == 5) { return Furniture_Blocks.TANSU_DOAK; }
		if (k == 6) { return Furniture_Blocks.TANSU_SAKURA; }
		if (k == 7) { return Furniture_Blocks.TANSU_KAEDE; }
		if (k == 8) { return Furniture_Blocks.TANSU_ICHOH; }
		return null;
	}
}
