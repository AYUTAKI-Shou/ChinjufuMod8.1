package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class Dish_Donburi extends BaseDishNeeds {

	public Dish_Donburi(Block block, Item.Properties props) {
		super(block, props.usingConvertsTo(Items_Teatime.DONBURI.get()));
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, itemTip, tipFlag);
		
		if (this == Items_Teatime.DONBURI_GYU.get()) {
			itemTip.add(Component.translatable("tips.block_food_dongyu_1").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_KATSU.get()) {
			itemTip.add(Component.translatable("tips.block_food_donkatsu_1").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_OYAKO.get()) {
			itemTip.add(Component.translatable("tips.block_food_donoyako_1").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_KAISEN.get()) {
			itemTip.add(Component.translatable("tips.block_food_donkaisen_1").withStyle(ChatFormatting.GRAY)); }
	}
}
