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

public class FrypanNama1_TT extends Abstract_CookPan4 {

	public FrypanNama1_TT(String name) {
		super(name, Dish_Blocks.FRYPAN_NAMA_1);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/**1=玉子焼き, 2=ハンバーグ, 3=トマトソース, 4=きのこソース**/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_frypan_n_tamago";
		case 2:
			return "item." + "block_food_frypan_n_eggb";
		case 3:
			return "item." + "block_food_frypan_n_tomatos";
		case 4:
			return "item." + "block_food_frypan_n_kinokos";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.FRYPAN_NAMA_1;
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_frypan.name"));
	}
}
