package com.ayutaki.chinjufumod.items.chinjufu;

import com.ayutaki.chinjufumod.items.addtab.Chinjufu_SubBlockFace1;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SchoolDesk_CT extends Chinjufu_SubBlockFace1 {

	public SchoolDesk_CT(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_schooldesk";
		case 1:
			return "item." + "block_schooldesk_s";
		case 2:
			return "item." + "block_schooldesk_b";
		case 3:
			return "item." + "block_schooldesk_j";
		case 4:
			return "item." + "block_schooldesk_a";
		case 5:
			return "item." + "block_schooldesk_d";
		case 6:
			return "item." + "block_schooldesk_saku";
		case 7:
			return "item." + "block_schooldesk_kae";
		case 8:
			return "item." + "block_schooldesk_ich";
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
		if (k == 0) { return School_Blocks.SCHOOLDESK; }
		if (k == 1) { return School_Blocks.SCHOOLDESK_s; }
		if (k == 2) { return School_Blocks.SCHOOLDESK_b; }
		if (k == 3) { return School_Blocks.SCHOOLDESK_j; }
		if (k == 4) { return School_Blocks.SCHOOLDESK_a; }
		if (k == 5) { return School_Blocks.SCHOOLDESK_d; }
		if (k == 6) { return School_Blocks.SCHOOLDESK_saku; }
		if (k == 7) { return School_Blocks.SCHOOLDESK_kae; }
		else { return School_Blocks.SCHOOLDESK_ich; }
	}
}
