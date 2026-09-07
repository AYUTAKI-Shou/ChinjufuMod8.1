package com.ayutaki.chinjufumod.items.slidedoor;

import com.ayutaki.chinjufumod.blocks.slidedoor.ShoujiWindow;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

public class ShoujiWindow_DN extends TN_SubSlideHalf {

	public ShoujiWindow_DN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 100;
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_shoujih";
		case 1:
			return "item." + "block_shoujih_spruce";
		case 2:
			return "item." + "block_shoujih_birch";
		case 3:
			return "item." + "block_shoujih_jungle";
		case 4:
			return "item." + "block_shoujih_acacia";
		case 5:
			return "item." + "block_shoujih_darkoak";
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
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Slidedoor_Blocks.SHOUJIWIN_oak; }
		if (k == 1) { return Slidedoor_Blocks.SHOUJIWIN_spruce; }
		if (k == 2) { return Slidedoor_Blocks.SHOUJIWIN_birch; }
		if (k == 3) { return Slidedoor_Blocks.SHOUJIWIN_jungle; }
		if (k == 4) { return Slidedoor_Blocks.SHOUJIWIN_acacia; }
		else { return Slidedoor_Blocks.SHOUJIWIN_darkoak; }
	}
	
	protected Block sneakBlock(int k) {
		if (k == 0) { return Slidedoor_Blocks.SHOUJIWINR_oak; }
		if (k == 1) { return Slidedoor_Blocks.SHOUJIWINR_spruce; }
		if (k == 2) { return Slidedoor_Blocks.SHOUJIWINR_birch; }
		if (k == 3) { return Slidedoor_Blocks.SHOUJIWINR_jungle; }
		if (k == 4) { return Slidedoor_Blocks.SHOUJIWINR_acacia; }
		else { return Slidedoor_Blocks.SHOUJIWINR_darkoak; }
	}
	
	@Override
	protected IBlockState takeState(int k, EnumFacing direction) {
		return this.int2Block(k).getDefaultState().withProperty(ShoujiWindow.H_FACING, direction.getOpposite())
				.withProperty(ShoujiWindow.STAGE_1_3, Integer.valueOf(1));
	}
	
	@Override
	protected IBlockState sneakState(int k, EnumFacing direction) {
		return this.sneakBlock(k).getDefaultState().withProperty(ShoujiWindow.H_FACING, direction.getOpposite())
				.withProperty(ShoujiWindow.STAGE_1_3, Integer.valueOf(1));
	}
}
