package com.ayutaki.chinjufumod.items.slidedoor;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

public class ShoujiHalf_SN extends TN_SubSlideHalf {

	public ShoujiHalf_SN(String name) {
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
			return "item." + "block_shoujihalf_sakura";
		case 1:
			return "item." + "block_shoujihalf_kaede";
		case 2:
			return "item." + "block_shoujihalf_ichoh";
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
		if (k == 0) { return Slidedoor_Blocks.SHOUJIH_SAKU; }
		if (k == 1) { return Slidedoor_Blocks.SHOUJIH_KAE; }
		else { return Slidedoor_Blocks.SHOUJIH_ICH; }
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
