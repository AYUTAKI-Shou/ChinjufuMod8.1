package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.NoGroup_noFuel;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HakkouTips_NT extends NoGroup_noFuel {

	public HakkouTips_NT(Block block, Item.Properties props) {
		super(block, props);
	}

	/* ToolTip Item.class 334 */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {

		if (this == Items_Teatime.SHOUYU_bot_14 || this == Items_Teatime.DASHI_bot_14 ||
				this == Items_Teatime.MAYO_bot_14 || this == Items_Teatime.OSAUCE_bot_14 ||
				this == Items_Teatime.VANILLA_bot_14) {
			itemTip.add(new TranslationTextComponent("tips.block_bot").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_NoTab.SHOUYU_bot_24 || this == Items_NoTab.DASHI_bot_24 ||
				this == Items_NoTab.MAYO_bot_24 || this == Items_NoTab.OSAUCE_bot_24 ||
				this == Items_NoTab.VANILLA_bot_24) {
			itemTip.add(new TranslationTextComponent("tips.block_bot_2").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_NoTab.SHOUYU_bot_34 || this == Items_NoTab.DASHI_bot_34 || 
				this == Items_Teatime.KOMEZU_bot_12 || this == Items_Teatime.SOYOIL_bot_12 ||
				this == Items_NoTab.MAYO_bot_34 || this == Items_NoTab.OSAUCE_bot_34 ||
				this == Items_NoTab.VANILLA_bot_34) {
			itemTip.add(new TranslationTextComponent("tips.block_bot_3").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_NoTab.SHOUYU_bot_44 || this == Items_NoTab.DASHI_bot_44 || 
				this == Items_NoTab.KOMEZU_bot_22 || this == Items_NoTab.SOYOIL_bot_22 ||
				this == Items_NoTab.MAYO_bot_44 || this == Items_NoTab.OSAUCE_bot_44 ||
				this == Items_NoTab.VANILLA_bot_44) {
			itemTip.add(new TranslationTextComponent("tips.block_bot_4").applyTextStyle(TextFormatting.GRAY)); }
		
		if (this == Items_Teatime.OSAUCE_bot_14 || this == Items_NoTab.OSAUCE_bot_24 || 
				this == Items_NoTab.OSAUCE_bot_34 || this == Items_NoTab.OSAUCE_bot_44) {
			itemTip.add(new TranslationTextComponent("tips.block_osauce_bot").applyTextStyle(TextFormatting.GRAY)); }
	}
}
