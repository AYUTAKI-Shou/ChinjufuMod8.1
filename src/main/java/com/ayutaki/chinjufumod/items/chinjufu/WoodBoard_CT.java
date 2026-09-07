package com.ayutaki.chinjufumod.items.chinjufu;

import com.ayutaki.chinjufumod.items.addtab.Chinjufu_SubBlockFace1;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class WoodBoard_CT extends Chinjufu_SubBlockFace1 {

	public WoodBoard_CT(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_board_oak";
		case 1:
			return "item." + "block_board_spruce";
		case 2:
			return "item." + "block_board_birch";
		case 3:
			return "item." + "block_board_jungle";
		case 4:
			return "item." + "block_board_acacia";
		case 5:
			return "item." + "block_board_darkoak";
		case 6:
			return "item." + "block_board_sakura";
		case 7:
			return "item." + "block_board_kaede";
		case 8:
			return "item." + "block_board_ichoh";
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
		if (k == 0) { return School_Blocks.BOARD_OAK; }
		if (k == 1) { return School_Blocks.BOARD_SPRUCE; }
		if (k == 2) { return School_Blocks.BOARD_BIRCH; }
		if (k == 3) { return School_Blocks.BOARD_JUNGLE; }
		if (k == 4) { return School_Blocks.BOARD_ACACIA; }
		if (k == 5) { return School_Blocks.BOARD_DOAK; }
		if (k == 6) { return School_Blocks.BOARD_SAKURA; }
		if (k == 7) { return School_Blocks.BOARD_KAEDE; }
		else { return School_Blocks.BOARD_ICHOH; }
	}
}
