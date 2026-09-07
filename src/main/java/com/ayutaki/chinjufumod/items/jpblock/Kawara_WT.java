package com.ayutaki.chinjufumod.items.jpblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Kawara_WT extends WT_SubFull {

	public Kawara_WT(String name) {
		super(name, JPBlock_Blocks.KAWARA);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
			return "item." + "block_kawara_white";
		case 1:
			return "item." + "block_kawara_orange";
		case 2:
			return "item." + "block_kawara_magenta";
		case 3:
			return "item." + "block_kawara_lightb";
		case 4:
			return "item." + "block_kawara_yellow";
		case 5:
			return "item." + "block_kawara_lime";
		case 6:
			return "item." + "block_kawara_pink";
		case 7:
			return "item." + "block_kawara_gray";
		case 8:
			return "item." + "block_kawara_lightg";
		case 9:
			return "item." + "block_kawara_cyan";
		case 10:
			return "item." + "block_kawara_purple";
		case 11:
			return "item." + "block_kawara_blue";
		case 12:
			return "item." + "block_kawara_brown";
		case 13:
			return "item." + "block_kawara_green";
		case 14:
			return "item." + "block_kawara_red";
		case 15:
		default:
			return "item." + "block_kawara_black";
		}
	}

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
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return JPBlock_Blocks.KAWARA;
	}

	/* Tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_kawara.name"));
	}
}
