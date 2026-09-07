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

public class Garasudo_SN extends TN_SubSlideDoor {

	public Garasudo_SN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_garasudo_sakura";
		case 1:
			return "item." + "block_garasudo_kaede";
		case 2:
			return "item." + "block_garasudo_ichoh";
		case 3:
			return "item." + "block_garasudob_sakura";
		case 4:
			return "item." + "block_garasudob_kaede";
		case 5:
			return "item." + "block_garasudob_ichoh";
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
		if (k == 0) { return Garasudo_Blocks.GARASUDO_SAKU; }
		if (k == 1) { return Garasudo_Blocks.GARASUDO_KAE; }
		if (k == 2) { return Garasudo_Blocks.GARASUDO_ICH; }
		if (k == 3) { return Garasudo_Blocks.GARASUDOB_SAKU; }
		if (k == 4) { return Garasudo_Blocks.GARASUDOB_KAE; }
		else { return Garasudo_Blocks.GARASUDOB_ICH; }
	}
	
	/* tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_garasudo.name"));
	}
}
