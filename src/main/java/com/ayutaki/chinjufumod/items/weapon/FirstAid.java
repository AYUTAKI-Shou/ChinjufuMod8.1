package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class FirstAid extends Item {

	public FirstAid(Item.Properties props) {
		super(props);
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_kit_firstaid").withStyle(ChatFormatting.GRAY));
	}
}
