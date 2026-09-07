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

public class NabeNama2_TT extends Abstract_NabeNama4 {

	public NabeNama2_TT(String name) {
		super(name, Dish_Blocks.NABE_nama_SNTA);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 2=煮豆, 3=豆腐, 甘酒は発酵側で用意 -> **/
		switch (stack.getMetadata()) {
		case 2:
		default:
			return "item." + "block_food_nabenimame_n";
		case 3:
			return "item." + "block_food_nabetoufu_n";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.NABE_nama_SNTA;
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		super.addInformation(stack, worldIn, itemTip, advanced);
		int k = stack.getMetadata();
		if (k == 2) { itemTip.add(I18n.format("tips.take_bowl.name")); }
		if (k != 2) { itemTip.add(I18n.format("tips.take_emptyhand.name")); }
	}
}
