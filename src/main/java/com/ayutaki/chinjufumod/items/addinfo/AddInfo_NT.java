package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_NoTabLater;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AddInfo_NT extends Item {

	public AddInfo_NT(Item.Properties props) {
		super(props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		
		if (this == Items_NoTab.RAW_ICE_VANILLA.get() || this == Items_NoTab.RAW_ICE_GREEN.get() ||
				this == Items_NoTab.RAW_ICE_RED.get() || this == Items_NoTab.RAW_ICE_CACAO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_raw_icecream").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_NoTab.RAW_KANTEN_APPLE.get() || this == Items_NoTab.RAW_KANTEN_CHERRY.get() ||
				this == Items_NoTab.RAW_KANTEN_CITRUS.get() || this == Items_NoTab.RAW_KANTEN_GRAPE.get() ||
				this == Items_NoTab.RAW_KANTEN_MILK.get() ||
				this == Items_NoTab.RAW_YOKAN.get() || this == Items_NoTab.RAW_YOKAN_MATCHA.get() ||
				this == Items_NoTabLater.RAW_PUDDING.get() || this == Items_NoTabLater.RAW_PUDDING_GREEN.get() || 
				this == Items_NoTabLater.RAW_PUDDING_RED.get() || this == Items_NoTabLater.RAW_PUDDING_CACAO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_raw_kanten").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_NoTab.ROTTEN_FOOD.get()) {
			itemTip.add(new TranslatableComponent("tips.item_rotten_food").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Chinjufu.SHOUHOU_empty.get()) {
			itemTip.add(new TranslatableComponent("tips.item_shouhou_empty").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Chinjufu.SUMI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_sumi").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Wadeco.HAKE.get()) {
			itemTip.add(new TranslatableComponent("tips.item_hake").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Wablock.SHOUSEKKAI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_shousekkai_c").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Wablock.CLAYKAWARA.get()) {
			itemTip.add(new TranslatableComponent("tips.item_claykawara").withStyle(ChatFormatting.GRAY)); }
	}
}
