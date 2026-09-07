package com.ayutaki.chinjufumod.items.remain;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;

public class KouboNyusan extends Item {

	public KouboNyusan(Item.Properties props) {
		super(props.craftRemainder(Items.GLASS_BOTTLE));
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		if (this == Items_Teatime.KOUBO.get()) {
			itemTip.add(Component.translatable("tips.item_koubo").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSAN.get()) {
			itemTip.add(Component.translatable("tips.item_nyusan").withStyle(ChatFormatting.GRAY)); }
	}
}
