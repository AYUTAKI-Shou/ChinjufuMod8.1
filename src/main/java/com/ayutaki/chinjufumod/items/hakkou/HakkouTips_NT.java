package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.NoGroup_noFuel;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class HakkouTips_NT extends NoGroup_noFuel {

	public HakkouTips_NT(Block block, Item.Properties props) {
		super(block, props);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {

		if (this == Items_Teatime.SHOUYU_bot_14.get() || this == Items_Teatime.DASHI_bot_14.get() ||
				this == Items_Teatime.MAYO_bot_14.get() || this == Items_Teatime.OSAUCE_bot_14.get() ||
				this == Items_Teatime.VANILLA_bot_14.get()) {
			itemTip.add(new TranslatableComponent("tips.block_bot").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_NoTab.SHOUYU_bot_24.get() || this == Items_NoTab.DASHI_bot_24.get() ||
				this == Items_NoTab.MAYO_bot_24.get() || this == Items_NoTab.OSAUCE_bot_24.get() ||
				this == Items_NoTab.VANILLA_bot_24.get()) {
			itemTip.add(new TranslatableComponent("tips.block_bot_2").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_NoTab.SHOUYU_bot_34.get() || this == Items_NoTab.DASHI_bot_34.get() || 
				this == Items_Teatime.KOMEZU_bot_12.get() || this == Items_Teatime.SOYOIL_bot_12.get() ||
				this == Items_NoTab.MAYO_bot_34.get() || this == Items_NoTab.OSAUCE_bot_34.get() ||
				this == Items_NoTab.VANILLA_bot_34.get()) {
			itemTip.add(new TranslatableComponent("tips.block_bot_3").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_NoTab.SHOUYU_bot_44.get() || this == Items_NoTab.DASHI_bot_44.get() || 
				this == Items_NoTab.KOMEZU_bot_22.get() || this == Items_NoTab.SOYOIL_bot_22.get() ||
				this == Items_NoTab.MAYO_bot_44.get() || this == Items_NoTab.OSAUCE_bot_44.get() ||
				this == Items_NoTab.VANILLA_bot_44.get()) {
			itemTip.add(new TranslatableComponent("tips.block_bot_4").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Teatime.OSAUCE_bot_14.get() || this == Items_NoTab.OSAUCE_bot_24.get() || 
				this == Items_NoTab.OSAUCE_bot_34.get() || this == Items_NoTab.OSAUCE_bot_44.get()) {
			itemTip.add(new TranslatableComponent("tips.block_osauce_bot").withStyle(ChatFormatting.GRAY)); }
	}
}
