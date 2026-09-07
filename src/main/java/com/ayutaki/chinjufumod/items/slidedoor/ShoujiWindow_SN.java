package com.ayutaki.chinjufumod.items.slidedoor;

import com.ayutaki.chinjufumod.blocks.slidedoor.ShoujiWindow;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

public class ShoujiWindow_SN extends TN_SubSlideHalf {

	public ShoujiWindow_SN(String name) {
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
			return "item." + "block_shoujih_sakura";
		case 1:
			return "item." + "block_shoujih_kaede";
		case 2:
			return "item." + "block_shoujih_ichoh";
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Slidedoor_Blocks.SHOUJIWIN_sakura; }
		if (k == 1) { return Slidedoor_Blocks.SHOUJIWIN_kaede; }
		else { return Slidedoor_Blocks.SHOUJIWIN_ichoh; }
	}
	
	protected Block sneakBlock(int k) {
		if (k == 0) { return Slidedoor_Blocks.SHOUJIWINR_sakura; }
		if (k == 1) { return Slidedoor_Blocks.SHOUJIWINR_kaede; }
		else { return Slidedoor_Blocks.SHOUJIWINR_ichoh; }
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
