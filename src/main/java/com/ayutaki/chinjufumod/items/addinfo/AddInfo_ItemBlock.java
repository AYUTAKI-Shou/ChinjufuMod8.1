package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class AddInfo_ItemBlock extends Not_Fuel {

	public AddInfo_ItemBlock(Block block, Item.Properties props) {
		super(block, props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		if (this == Items_Teatime.KOUBOBOT_full.get()) {
			itemTip.add(Component.translatable("tips.block_bin_koubo").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSANBOT_full.get()) {
			itemTip.add(Component.translatable("tips.block_bin_nyusan").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Seasonal.SNOWCORE.get()) {
			itemTip.add(Component.translatable("tips.block_snowcore").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.SNOWMAN.get()) {
			itemTip.add(Component.translatable("tips.block_snowman").withStyle(ChatFormatting.GRAY)); }
	}
}
