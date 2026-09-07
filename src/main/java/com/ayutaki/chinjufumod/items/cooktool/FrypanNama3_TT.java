package com.ayutaki.chinjufumod.items.cooktool;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FrypanNama3_TT extends Abstract_CookPan4 {

	public FrypanNama3_TT(String name) {
		super(name, Dish_Blocks.FRYPAN_NAMA_3);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_frypan_n_seafood";
		case 2:
			return "item." + "block_food_frypan_b_seafood";
		case 3:
			return "item." + "block_food_frypan_n_roux";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.FRYPAN_NAMA_3;
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1 || k == 3) { itemTip.add(I18n.format("tips.block_frypan.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.block_food_frypan_pasta.name")); } 
	}
}
