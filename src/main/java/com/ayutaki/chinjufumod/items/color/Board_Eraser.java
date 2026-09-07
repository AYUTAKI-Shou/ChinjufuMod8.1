package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class Board_Eraser extends Item {

	public Board_Eraser(Item.Properties props) {
		super(props.durability(32));
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack stack_1, ItemStack stack_2) {
		return false;
	} // for 1.20.6

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_eraser").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_eraser2").withStyle(ChatFormatting.GRAY));
	} // for 1.20.6
}
