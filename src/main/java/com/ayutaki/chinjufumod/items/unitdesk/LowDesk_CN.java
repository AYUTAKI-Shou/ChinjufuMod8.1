package com.ayutaki.chinjufumod.items.unitdesk;

import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class LowDesk_CN extends TN_SubUnitDesk {

	public LowDesk_CN(String name) {
		super(name, Unit_Blocks.LOWDESK);
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
			return "item." + "block_lowdesk";
		case 1:
			return "item." + "block_lowdesk_spruce";
		case 2:
			return "item." + "block_lowdesk_birch";
		case 3:
			return "item." + "block_lowdesk_jungle";
		case 4:
			return "item." + "block_lowdesk_acacia";
		case 5:
			return "item." + "block_lowdesk_darkoak";
		case 6:
			return "item." + "block_lowdesk_sakura";
		case 7:
			return "item." + "block_lowdesk_kaede";
		case 8:
			return "item." + "block_lowdesk_ichoh";
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
	protected Block takeBlock() {
		return Unit_Blocks.LOWDESK;
	}
}
