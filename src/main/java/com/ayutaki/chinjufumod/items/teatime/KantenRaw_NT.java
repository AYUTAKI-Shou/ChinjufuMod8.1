package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.Item_Regi;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KantenRaw_NT extends Item_Regi {

	public KantenRaw_NT(String name) {
		super(name);
		setUnlocalizedName(name);
		setMaxStackSize(64);

		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_raw_kanten_apple";
		case 1:
			return "item." + "item_raw_kanten_cherry";
		case 2:
			return "item." + "item_raw_kanten_citrus";
		case 3:
			return "item." + "item_raw_kanten_grape";
		case 4:
			return "item." + "item_raw_kanten_milk";
		case 5:
			return "item." + "item_raw_yokan";
		case 6:
			return "item." + "item_raw_yokan_matcha";

		case 10:
			return "item." + "item_raw_icecream";
		case 11:
			return "item." + "item_raw_icecream_greentea";
		case 12:
			return "item." + "item_raw_icecream_redtea";
		case 13:
			return "item." + "item_raw_icecream_cacao";
		}
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k >= 0 && k <= 6) { itemTip.add(I18n.format("tips.item_raw_kanten.name")); }
		if (k >= 10 && k <= 13) { itemTip.add(I18n.format("tips.item_raw_icecream.name")); }
	}
}
