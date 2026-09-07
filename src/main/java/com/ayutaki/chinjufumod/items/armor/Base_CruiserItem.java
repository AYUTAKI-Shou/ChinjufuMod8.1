package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class Base_CruiserItem extends Abstract_ArmorItem {

	public Base_CruiserItem(ArmorMaterial material, EquipmentSlot slot, Item.Properties props) {
		super(material, slot, props);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_cruiser").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_cruiser2").withStyle(ChatFormatting.GRAY));
	}
}
