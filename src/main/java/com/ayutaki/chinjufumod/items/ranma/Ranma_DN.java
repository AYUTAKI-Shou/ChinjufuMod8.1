package com.ayutaki.chinjufumod.items.ranma;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Ranma_DN extends TN_SubRanma {

	public Ranma_DN(String name) {
		super(name);
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
			return "item." + "block_ranma_oak";
		case 1:
			return "item." + "block_ranma_spru";
		case 2:
			return "item." + "block_ranma_bir";
		case 3:
			return "item." + "block_ranma_jun";
		case 4:
			return "item." + "block_ranma_aca";
		case 5:
			return "item." + "block_ranma_doak";
			
		case 6:
			return "item." + "block_ranmab_oak";
		case 7:
			return "item." + "block_ranmab_spru";
		case 8:
			return "item." + "block_ranmab_bir";
		case 9:
			return "item." + "block_ranmab_jun";
		case 10:
			return "item." + "block_ranmab_aca";
		case 11:
			return "item." + "block_ranmab_doak";
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
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return JPDeco_Blocks.RANMA_oak; }
		if (k == 1) { return JPDeco_Blocks.RANMA_spru; }
		if (k == 2) { return JPDeco_Blocks.RANMA_bir; }
		if (k == 3) { return JPDeco_Blocks.RANMA_jun; }
		if (k == 4) { return JPDeco_Blocks.RANMA_aca; }
		if (k == 5) { return JPDeco_Blocks.RANMA_doak; }
		
		if (k == 6) { return JPDeco_Blocks.RANMAB_oak; }
		if (k == 7) { return JPDeco_Blocks.RANMAB_spru; }
		if (k == 8) { return JPDeco_Blocks.RANMAB_bir; }
		if (k == 9) { return JPDeco_Blocks.RANMAB_jun; }
		if (k == 10) { return JPDeco_Blocks.RANMAB_aca; }
		else { return JPDeco_Blocks.RANMAB_doak; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_ranma.name"));
		super.addInformation(stack, worldIn, itemTip, advanced);
	}
}
