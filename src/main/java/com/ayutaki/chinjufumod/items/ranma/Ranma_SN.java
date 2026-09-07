package com.ayutaki.chinjufumod.items.ranma;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Ranma_SN extends TN_SubRanma {

	public Ranma_SN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int k = stack.getMetadata();
		return (k >= 6)? -1 : 150;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_ranma_saku";
		case 1:
			return "item." + "block_ranma_kae";
		case 2:
			return "item." + "block_ranma_ich";

		case 3:
			return "item." + "block_ranmab_saku";
		case 4:
			return "item." + "block_ranmab_kae";
		case 5:
			return "item." + "block_ranmab_ich";
			
		case 6:
			return "item." + "block_ranmac_saku";
		case 7:
			return "item." + "block_ranmac_kae";
		case 8:
			return "item." + "block_ranmac_ich";
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
		if (k == 0) { return Seasonal_Blocks.RANMA_saku; }
		if (k == 1) { return Seasonal_Blocks.RANMA_kae; }
		if (k == 2) { return Seasonal_Blocks.RANMA_ich; }
		
		if (k == 3) { return Seasonal_Blocks.RANMAB_saku; }
		if (k == 4) { return Seasonal_Blocks.RANMAB_kae; }
		if (k == 5) { return Seasonal_Blocks.RANMAB_ich; }
		
		if (k == 6) { return Seasonal_Blocks.RANMAC_saku; }
		if (k == 7) { return Seasonal_Blocks.RANMAC_kae; }
		else { return Seasonal_Blocks.RANMAC_ich; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_ranma.name"));
		super.addInformation(stack, worldIn, itemTip, advanced);
	}
}
