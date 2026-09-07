package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Chinjufu;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Board_Eraser extends IG_Chinjufu {

	public Board_Eraser(Item.Properties props) {
		super(props.durability(32));
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_eraser").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_eraser2").withStyle(ChatFormatting.GRAY));
	}
}
