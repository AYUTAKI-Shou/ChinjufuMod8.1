package com.ayutaki.chinjufumod.items.window;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Window_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Curtain_CT extends CT_SubWindow {

	public Curtain_CT(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_curtain_white";
		case 1:
			return "item." + "block_curtain_orange";
		case 2:
			return "item." + "block_curtain_magenta";
		case 3:
			return "item." + "block_curtain_lightblue";
		case 4:
			return "item." + "block_curtain_yellow";
		case 5:
			return "item." + "block_curtain_lime";
		case 6:
			return "item." + "block_curtain_pink";
		case 7:
			return "item." + "block_curtain_gray";
		case 8:
			return "item." + "block_curtain_lightgray";
		case 9:
			return "item." + "block_curtain_cyan";
		case 10:
			return "item." + "block_curtain_purple";
		case 11:
			return "item." + "block_curtain_blue";
		case 12:
			return "item." + "block_curtain_brown";
		case 13:
			return "item." + "block_curtain_green";
		case 14:
			return "item." + "block_curtain_red";
		case 15:
			return "item." + "block_curtain_black";
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
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
		}
	}
	
	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Window_Blocks.CURTAIN_white; }
		if (k == 1) { return Window_Blocks.CURTAIN_orange; }
		if (k == 2) { return Window_Blocks.CURTAIN_magenta; }
		if (k == 3) { return Window_Blocks.CURTAIN_lightblue; }
		if (k == 4) { return Window_Blocks.CURTAIN_yellow; }
		if (k == 5) { return Window_Blocks.CURTAIN_lime; }
		if (k == 6) { return Window_Blocks.CURTAIN_pink; }
		if (k == 7) { return Window_Blocks.CURTAIN_gray; }
		if (k == 8) { return Window_Blocks.CURTAIN_lightgray; }
		if (k == 9) { return Window_Blocks.CURTAIN_cyan; }
		if (k == 10) { return Window_Blocks.CURTAIN_purple; }
		if (k == 11) { return Window_Blocks.CURTAIN_blue; }
		if (k == 12) { return Window_Blocks.CURTAIN_brown; }
		if (k == 13) { return Window_Blocks.CURTAIN_green; }
		if (k == 14) { return Window_Blocks.CURTAIN_red; }
		else { return Window_Blocks.CURTAIN_black; }
	}
	
	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Cloth(worldIn, pos, playerIn, hand);
	}
}
