package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class Base_BattleshipItem extends Base_ArmorItem {

	public Base_BattleshipItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties props) {
		super(material, slot, props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_battleship").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_battleship2").withStyle(ChatFormatting.GRAY));
	}
}
