package com.ayutaki.chinjufumod.items.slidedoor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.doors.Garasudo_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Garasudo_DN extends TN_SubSlideDoor {

	public Garasudo_DN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_garasudo";
		case 1:
			return "item." + "block_garasudo_spruce";
		case 2:
			return "item." + "block_garasudo_birch";
		case 3:
			return "item." + "block_garasudo_jungle";
		case 4:
			return "item." + "block_garasudo_acacia";
		case 5:
			return "item." + "block_garasudo_darkoak";
		case 6:
			return "item." + "block_garasudob";
		case 7:
			return "item." + "block_garasudob_spruce";
		case 8:
			return "item." + "block_garasudob_birch";
		case 9:
			return "item." + "block_garasudob_jungle";
		case 10:
			return "item." + "block_garasudob_acacia";
		case 11:
			return "item." + "block_garasudob_darkoak";
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
		if (k == 0) { return Garasudo_Blocks.GARASUDO; }
		if (k == 1) { return Garasudo_Blocks.GARASUDO_SPRU; }
		if (k == 2) { return Garasudo_Blocks.GARASUDO_BIR; }
		if (k == 3) { return Garasudo_Blocks.GARASUDO_JUN; }
		if (k == 4) { return Garasudo_Blocks.GARASUDO_ACA; }
		if (k == 5) { return Garasudo_Blocks.GARASUDO_DOAK; }
		if (k == 6) { return Garasudo_Blocks.GARASUDOB; }
		if (k == 7) { return Garasudo_Blocks.GARASUDOB_SPRU; }
		if (k == 8) { return Garasudo_Blocks.GARASUDOB_BIR; }
		if (k == 9) { return Garasudo_Blocks.GARASUDOB_JUN; }
		if (k == 10) { return Garasudo_Blocks.GARASUDOB_ACA; }
		else { return Garasudo_Blocks.GARASUDOB_DOAK; }
	}
	
	/* tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_garasudo.name"));
	}
}
