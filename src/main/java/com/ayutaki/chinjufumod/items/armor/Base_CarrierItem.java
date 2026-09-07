package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class Base_CarrierItem extends Base_ArmorItem {

	public Base_CarrierItem(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_carrier").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_carrier2").withStyle(ChatFormatting.GRAY));
	}
}
