package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Spice_TT extends IR_Teatime {

	public Spice_TT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_crop_pepper";
		case 1:
			return "item." + "item_crop_pepperdry";
		case 2:
			return "item." + "item_crop_chilipepper";
		case 3:
			return "item." + "item_dust_blackpepper";
		case 4:
			return "item." + "item_dust_cumin";
		case 5:
			return "item." + "item_dust_turmeric";
		case 6:
			return "item." + "item_dust_chili";
		case 7:
			return "item." + "item_curry_roux";
		case 8:
			return "item." + "item_vanillabeans";
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
		}
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 0 || k == 8) { itemTip.add(I18n.format("tips.item_crop_pepper.name")); }
		if (k == 1 || k == 2) { itemTip.add(I18n.format("tips.item_crop_pepperdry.name")); }
	}
}
