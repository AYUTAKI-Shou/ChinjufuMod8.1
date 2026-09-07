package com.ayutaki.chinjufumod.items.jpblock;

import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class WallPlaster_WT extends WT_SubWall {

	public WallPlaster_WT(String name) {
		super(name, JPBlock_Blocks.SHIKKUI_WALL);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_pwall_white";
		case 1:
			return "item." + "block_pwall_orange";
		case 2:
			return "item." + "block_pwall_magenta";
		case 3:
			return "item." + "block_pwall_lightb";
		case 4:
			return "item." + "block_pwall_yellow";
		case 5:
			return "item." + "block_pwall_lime";
		case 6:
			return "item." + "block_pwall_pink";
		case 7:
			return "item." + "block_pwall_gray";
		case 8:
			return "item." + "block_pwall_lightg";
		case 9:
			return "item." + "block_pwall_cyan";
		case 10:
			return "item." + "block_pwall_purple";
		case 11:
			return "item." + "block_pwall_blue";
		case 12:
			return "item." + "block_pwall_brown";
		case 13:
			return "item." + "block_pwall_green";
		case 14:
			return "item." + "block_pwall_red";
		case 15:
			return "item." + "block_pwall_black";
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
			items.add(new ItemStack(this, 1, 9));
			items.add(new ItemStack(this, 1, 10));
			items.add(new ItemStack(this, 1, 11));
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return JPBlock_Blocks.SHIKKUI_WALL;
	}
}
