package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.NoGroup_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class AddInfoBlock_NT extends NoGroup_noFuel {

	public AddInfoBlock_NT(Block block, Item.Properties props) {
		super(block, props);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		if (this == Items_Teatime.KOUBOBOT_full.get()) {
			itemTip.add(new TranslatableComponent("tips.block_bin_koubo").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSANBOT_full.get()) {
			itemTip.add(new TranslatableComponent("tips.block_bin_nyusan").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Seasonal.SNOWCORE.get()) {
			itemTip.add(new TranslatableComponent("tips.block_snowcore").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.SNOWMAN.get()) {
			itemTip.add(new TranslatableComponent("tips.block_snowman").withStyle(ChatFormatting.GRAY)); }
	}
}
