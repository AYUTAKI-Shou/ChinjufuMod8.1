package com.ayutaki.chinjufumod.items.slidedoor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.doors.Fusuma_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Fusuma_DN extends TN_SubSlideDoor {

	public Fusuma_DN(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_fusuma";
		case 1:
			return "item." + "block_fusuma_orange";
		case 2:
			return "item." + "block_fusuma_magenta";
		case 3:
			return "item." + "block_fusuma_lightb";
		case 4:
			return "item." + "block_fusuma_yellow";
		case 5:
			return "item." + "block_fusuma_lime";
		case 6:
			return "item." + "block_fusuma_pink";
		case 7:
			return "item." + "block_fusuma_gray";
		case 8:
			return "item." + "block_fusuma_lightg";
		case 9:
			return "item." + "block_fusuma_cyan";
		case 10:
			return "item." + "block_fusuma_purple";
		case 11:
			return "item." + "block_fusuma_blue";
		case 12:
			return "item." + "block_fusuma_brown";
		case 13:
			return "item." + "block_fusuma_green";
		case 14:
			return "item." + "block_fusuma_red";
		case 15:
			return "item." + "block_fusuma_black";
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
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Fusuma_Blocks.FUSUMA_white; }
		if (k == 1) { return Fusuma_Blocks.FUSUMA_orange; }
		if (k == 2) { return Fusuma_Blocks.FUSUMA_magenta; }
		if (k == 3) { return Fusuma_Blocks.FUSUMA_lightblue; }
		if (k == 4) { return Fusuma_Blocks.FUSUMA_yellow; }
		if (k == 5) { return Fusuma_Blocks.FUSUMA_lime; }
		if (k == 6) { return Fusuma_Blocks.FUSUMA_pink; }
		if (k == 7) { return Fusuma_Blocks.FUSUMA_gray; }
		if (k == 8) { return Fusuma_Blocks.FUSUMA_lightgray; }
		if (k == 9) { return Fusuma_Blocks.FUSUMA_cyan; }
		if (k == 10) { return Fusuma_Blocks.FUSUMA_purple; }
		if (k == 11) { return Fusuma_Blocks.FUSUMA_blue; }
		if (k == 12) { return Fusuma_Blocks.FUSUMA_brown; }
		if (k == 13) { return Fusuma_Blocks.FUSUMA_green; }
		if (k == 14) { return Fusuma_Blocks.FUSUMA_red; }
		else { return Fusuma_Blocks.FUSUMA_black; }
	}
	
	/* tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_fusuma.name"));
	}
}
