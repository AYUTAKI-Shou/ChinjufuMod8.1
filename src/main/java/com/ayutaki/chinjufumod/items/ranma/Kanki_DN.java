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

public class Kanki_DN extends TN_SubRanma {

	public Kanki_DN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int k = stack.getMetadata();
		return (k <= 5)? -1 : 150;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_ranmac_oak";
		case 1:
			return "item." + "block_ranmac_spru";
		case 2:
			return "item." + "block_ranmac_bir";
		case 3:
			return "item." + "block_ranmac_jun";
		case 4:
			return "item." + "block_ranmac_aca";
		case 5:
			return "item." + "block_ranmac_doak";
			
		case 6:
			return "item." + "block_kanki_oak";
		case 7:
			return "item." + "block_kanki_spru";
		case 8:
			return "item." + "block_kanki_bir";
		case 9:
			return "item." + "block_kanki_jun";
		case 10:
			return "item." + "block_kanki_aca";
		case 11:
			return "item." + "block_kanki_doak";
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
		if (k == 0) { return JPDeco_Blocks.RANMAC_oak; }
		if (k == 1) { return JPDeco_Blocks.RANMAC_spru; }
		if (k == 2) { return JPDeco_Blocks.RANMAC_bir; }
		if (k == 3) { return JPDeco_Blocks.RANMAC_jun; }
		if (k == 4) { return JPDeco_Blocks.RANMAC_aca; }
		if (k == 5) { return JPDeco_Blocks.RANMAC_doak; }
		
		if (k == 6) { return JPDeco_Blocks.KANKI_oak; }
		if (k == 7) { return JPDeco_Blocks.KANKI_spru; }
		if (k == 8) { return JPDeco_Blocks.KANKI_bir; }
		if (k == 9) { return JPDeco_Blocks.KANKI_jun; }
		if (k == 10) { return JPDeco_Blocks.KANKI_aca; }
		else { return JPDeco_Blocks.KANKI_doak; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k <= 5) { itemTip.add(I18n.format("tips.block_ranma.name")); }
		super.addInformation(stack, worldIn, itemTip, advanced);
	}
}
