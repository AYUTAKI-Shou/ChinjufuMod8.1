package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_NoTabLater;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AddInfo_NT extends Item {

	public AddInfo_NT(Properties props) {
		super(props);
	}

	/* ToolTip ...Item.class 222(1.16.5) */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		
		if (this == Items_NoTab.RAW_ICE_VANILLA || this == Items_NoTab.RAW_ICE_GREEN ||
				this == Items_NoTab.RAW_ICE_RED || this == Items_NoTab.RAW_ICE_CACAO) {
			itemTip.add(new TranslationTextComponent("tips.item_raw_icecream").withStyle(TextFormatting.GRAY)); }

		if (this == Items_NoTab.RAW_KANTEN_APPLE || this == Items_NoTab.RAW_KANTEN_CHERRY ||
				this == Items_NoTab.RAW_KANTEN_CITRUS || this == Items_NoTab.RAW_KANTEN_GRAPE ||
				this == Items_NoTab.RAW_KANTEN_MILK ||
				this == Items_NoTab.RAW_YOKAN || this == Items_NoTab.RAW_YOKAN_MATCHA ||
				this == Items_NoTabLater.RAW_PUDDING || this == Items_NoTabLater.RAW_PUDDING_GREEN || 
				this == Items_NoTabLater.RAW_PUDDING_RED || this == Items_NoTabLater.RAW_PUDDING_CACAO) {
			itemTip.add(new TranslationTextComponent("tips.item_raw_kanten").withStyle(TextFormatting.GRAY)); }

		if (this == Items_NoTab.ROTTEN_FOOD) {
			itemTip.add(new TranslationTextComponent("tips.item_rotten_food").withStyle(TextFormatting.GRAY)); }
		
		if (this == Items_Chinjufu.SHOUHOU_empty) {
			itemTip.add(new TranslationTextComponent("tips.item_shouhou_empty").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Chinjufu.SUMI) {
			itemTip.add(new TranslationTextComponent("tips.item_sumi").withStyle(TextFormatting.GRAY)); }
		
		if (this == Items_Wadeco.HAKE) {
			itemTip.add(new TranslationTextComponent("tips.item_hake").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Wablock.SHOUSEKKAI) {
			itemTip.add(new TranslationTextComponent("tips.item_shousekkai_c").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Wablock.CLAYKAWARA) {
			itemTip.add(new TranslationTextComponent("tips.item_claykawara").withStyle(TextFormatting.GRAY)); }
	}
}
