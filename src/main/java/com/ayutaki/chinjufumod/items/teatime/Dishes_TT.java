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

public class Dishes_TT extends IR_Teatime {

	public Dishes_TT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "item_food_yunomi";
		case 2:
			return "item." + "item_food_teacup";
		case 3:
			return "item." + "item_food_chawan";
		case 4:
			return "item." + "item_food_shikki";
		case 5:
			return "item." + "item_food_tonsui";
		case 6:
			return "item." + "item_food_donburi";
		case 7:
			return "item." + "item_food_driglass";
		case 8:
			return "item." + "item_food_sakebot";
		case 9:
			return "item." + "item_bentouhako";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
			items.add(new ItemStack(this, 1, 9));
		}
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.item_food_yunomi.name")); }
		if (k == 3) { itemTip.add(I18n.format("tips.item_food_chawan.name")); }
		if (k == 4) { itemTip.add(I18n.format("tips.item_food_shikki.name")); }
		if (k == 5) { itemTip.add(I18n.format("tips.item_food_tonsui.name")); }
		if (k == 6) { itemTip.add(I18n.format("tips.item_food_donburi.name")); }
	}
}
