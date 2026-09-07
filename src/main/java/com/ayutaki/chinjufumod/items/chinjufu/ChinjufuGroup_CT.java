package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChinjufuGroup_CT extends IR_Chinjufu {

	public ChinjufuGroup_CT(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		if (this == Items_Chinjufu.SUMI) { itemTip.add(I18n.format("tips.item_sumi.name")); }
		if (this == Items_Chinjufu.SHOUHOU_empty) { itemTip.add(I18n.format("tips.item_shouhou_empty.name")); }
	}
}
