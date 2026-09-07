package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Item_TTab extends IR_Teatime {

	public Item_TTab(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		if (this == Items_Teatime.KOMEKOUJI) { itemTip.add(I18n.format("tips.item_komekouji.name")); }
		if (this == Items_Teatime.KUSHI_SAKANA) { itemTip.add(I18n.format("tips.item_kushi_sakana.name")); }
	}
}
