package com.ayutaki.chinjufumod.items.foods;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class Bentou extends Item {

	public Bentou(Item.Properties props) {
		super(props.food(FoodPoints.DPS10_10, FoodEffects.E_DR3500).usingConvertsTo(Items_Teatime.BENTOUHAKO.get()));
	 }

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_bentou").withStyle(ChatFormatting.GRAY));
	}
}
