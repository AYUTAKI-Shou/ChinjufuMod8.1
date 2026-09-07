package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;

public class Iga_Item extends Item {

	public Iga_Item(Item.Properties props) {
		super(props);
	}

	/* BurnTime in a Furnace */
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
		return 50;
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_rotten_food").withStyle(ChatFormatting.GRAY));
	}
}
