package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class Fuel_KK extends Item {

	public Fuel_KK(Item.Properties props) {
		super(props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_kk_fuel").withStyle(ChatFormatting.GRAY));
	}
}
