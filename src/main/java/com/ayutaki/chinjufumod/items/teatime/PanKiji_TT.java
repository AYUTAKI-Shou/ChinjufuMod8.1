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

public class PanKiji_TT extends IR_Teatime {

	public PanKiji_TT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_azuki_boil";
		case 1:
			return "item." + "item_kiji_pananko";
		case 2:
			return "item." + "item_kiji_pancustard";
		case 3:
			return "item." + "item_kiji_panapple";
		case 4:
			return "item." + "item_kiji_pancherry";
		case 5:
			return "item." + "item_kiji_pancitrus";
		case 6:
			return "item." + "item_kiji_pangrape";
		case 7:
			return "item." + "item_kiji_pangreentea";
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
		}
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.item_kiji_pananko.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.item_kiji_pancustard.name")); }
		if (k == 3) { itemTip.add(I18n.format("tips.item_kiji_panapple.name")); }
		if (k == 4) { itemTip.add(I18n.format("tips.item_kiji_pancherry.name")); }
		if (k == 5) { itemTip.add(I18n.format("tips.item_kiji_pancitrus.name")); }
		if (k == 6) { itemTip.add(I18n.format("tips.item_kiji_pangrape.name")); }
		if (k == 7) { itemTip.add(I18n.format("tips.item_kiji_pangreentea.name")); }
	}
}
