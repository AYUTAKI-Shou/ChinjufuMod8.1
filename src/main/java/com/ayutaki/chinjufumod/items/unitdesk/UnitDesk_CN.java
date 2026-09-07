package com.ayutaki.chinjufumod.items.unitdesk;

import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class UnitDesk_CN extends TN_SubUnitDesk {

	public UnitDesk_CN(String name) {
		super(name, Unit_Blocks.UNITDESK);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_unitdesk";
		case 1:
			return "item." + "block_unitdesk_spruce";
		case 2:
			return "item." + "block_unitdesk_birch";
		case 3:
			return "item." + "block_unitdesk_jungle";
		case 4:
			return "item." + "block_unitdesk_acacia";
		case 5:
			return "item." + "block_unitdesk_darkoak";
		case 6:
			return "item." + "block_unitdesk_sakura";
		case 7:
			return "item." + "block_unitdesk_kaede";
		case 8:
			return "item." + "block_unitdesk_ichoh";
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
		return Unit_Blocks.UNITDESK;
	}
}
