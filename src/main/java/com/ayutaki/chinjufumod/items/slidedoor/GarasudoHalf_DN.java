package com.ayutaki.chinjufumod.items.slidedoor;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

public class GarasudoHalf_DN extends TN_SubSlideHalf {

	public GarasudoHalf_DN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_garasudohalf";
		case 1:
			return "item." + "block_garasudohalf_spruce";
		case 2:
			return "item." + "block_garasudohalf_birch";
		case 3:
			return "item." + "block_garasudohalf_jungle";
		case 4:
			return "item." + "block_garasudohalf_acacia";
		case 5:
			return "item." + "block_garasudohalf_darkoak";
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
		if (k == 0) { return Slidedoor_Blocks.GARASUDOH; }
		if (k == 1) { return Slidedoor_Blocks.GARASUDOH_SPRU; }
		if (k == 2) { return Slidedoor_Blocks.GARASUDOH_BIR; }
		if (k == 3) { return Slidedoor_Blocks.GARASUDOH_JUN; }
		if (k == 4) { return Slidedoor_Blocks.GARASUDOH_ACA; }
		else { return Slidedoor_Blocks.GARASUDOH_DOAK; }
	}
	
	@Override
	protected IBlockState takeState(int k, EnumFacing direction) {
		return this.int2Block(k).getDefaultState().withProperty(BaseStage4_Face.H_FACING, direction)
				.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1));
	}
	
	@Override
	protected IBlockState sneakState(int k, EnumFacing direction) {
		return this.int2Block(k).getDefaultState().withProperty(BaseStage4_Face.H_FACING, direction)
				.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(3));
	}
}
