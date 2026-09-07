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

public class Window_CT extends CT_SubWindow {

	public Window_CT(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_window";
		case 1:
			return "item." + "block_window_spruce";
		case 2:
			return "item." + "block_window_birch";
		case 3:
			return "item." + "block_window_jungle";
		case 4:
			return "item." + "block_window_acacia";
		case 5:
			return "item." + "block_window_darkoak";
		case 6:
			return "item." + "block_window_sakura";
		case 7:
			return "item." + "block_window_kaede";
		case 8:
			return "item." + "block_window_ichoh";
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
		if (k == 0) { return Window_Blocks.WINDOW_oak; }
		if (k == 1) { return Window_Blocks.WINDOW_spruce; }
		if (k == 2) { return Window_Blocks.WINDOW_birch; }
		if (k == 3) { return Window_Blocks.WINDOW_jungle; }
		if (k == 4) { return Window_Blocks.WINDOW_acacia; }
		if (k == 5) { return Window_Blocks.WINDOW_darkoak; }
		if (k == 6) { return Window_Blocks.WINDOW_sakura; }
		if (k == 7) { return Window_Blocks.WINDOW_kaede; }
		else{ return Window_Blocks.WINDOW_ichoh; }
	}
	
	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
	}
}
