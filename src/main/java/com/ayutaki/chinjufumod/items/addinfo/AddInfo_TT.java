package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AddInfo_TT extends IG_Teatime {

	public AddInfo_TT(Properties props) {
		super(props);
	}

	/* ToolTip ...Item.class 222(1.16.5) */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {

		if (this == Items_Teatime.NIGARI) {
			itemTip.add(new TranslationTextComponent("tips.item_nigari").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SAKEKASU) {
			itemTip.add(new TranslationTextComponent("tips.item_sakekasu").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KOMEKOUJI) {
			itemTip.add(new TranslationTextComponent("tips.item_komekouji").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHUBO) {
			itemTip.add(new TranslationTextComponent("tips.item_shubo").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOROMI) {
			itemTip.add(new TranslationTextComponent("tips.item_moromi").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BUN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_bun").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BURG) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_burg").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SCONE) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_scone").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SENBEI) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_senbei").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MATCH) {
			itemTip.add(new TranslationTextComponent("tips.item_match_cm").withStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.CHAWAN) {
			itemTip.add(new TranslationTextComponent("tips.item_food_chawan").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHIKKI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_shikki").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.TONSUI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_tonsui").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.YUNOMI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_yunomi").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_donburi").withStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.CLAY_YUNOMI) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_yunomi").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_KYUSU) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_kyusu").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TCUP) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_teacup").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TPOT) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_teapot").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_SARA) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_sara").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_CHAWAN) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_chawan").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_NABE) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_nabe").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TONSUI) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_tonsui").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_DONBURI) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_donburi").withStyle(TextFormatting.GRAY)); }
			
		if (this == Items_Teatime.FPKINOKOAK) {
			itemTip.add(new TranslationTextComponent("tips.item_frypan_kinokoak").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KOUBO) {
			itemTip.add(new TranslationTextComponent("tips.item_koubo").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSAN) {
			itemTip.add(new TranslationTextComponent("tips.item_nyusan").withStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.RENNET) {
			itemTip.add(new TranslationTextComponent("tips.item_rennet").withStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.KIRIMI_S || this == Items_Teatime.KIRIMI_F || this == Items_Teatime.KIRIMI_B || this == Items_Teatime.KIRIMI_T) {
			itemTip.add(new TranslationTextComponent("tips.item_food_kirimi").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KUSHI_SAKANA) {
			itemTip.add(new TranslationTextComponent("tips.item_kushi_sakana").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.IKA) {
			itemTip.add(new TranslationTextComponent("tips.item_squid_raw").withStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.PEPPER_RAW || this == Items_Teatime.VANILLA_RAW || this == Items_Teatime.NORI_N) {
			itemTip.add(new TranslationTextComponent("tips.item_crop_pepper").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.PEPPER_DRY || this == Items_Teatime.CHILIPEPPER) {
			itemTip.add(new TranslationTextComponent("tips.item_crop_pepperdry").withStyle(TextFormatting.GRAY)); }
		
		if (this == Items_Teatime.SHOUYU_donburi) {
			itemTip.add(new TranslationTextComponent("tips.item_food_shouyu_don").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.TSUYU_donburi) {
			itemTip.add(new TranslationTextComponent("tips.item_food_tsuyu_don").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_TARE || this == Items_Teatime.MISO_TARE || this == Items_Teatime.SHIO_TARE) {
			itemTip.add(new TranslationTextComponent("tips.item_food_tare").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_Rsoup || this == Items_Teatime.MISO_Rsoup || this == Items_Teatime.SHIO_Rsoup) {
			itemTip.add(new TranslationTextComponent("tips.item_food_rsoup").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SOBA_PLATE) {
			itemTip.add(new TranslationTextComponent("tips.item_food_sobaplate").withStyle(TextFormatting.GRAY)); }
		
		/** AZUKI **/
		if (this == Items_Teatime.ANKO) {
			itemTip.add(new TranslationTextComponent("tips.item_anko").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_ANPAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pananko").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CUSTARD_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pancustard").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_APPLE_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_panapple").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CHERRY_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pancherry").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CITRUS_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pancitrus").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GRAPE_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pangrape").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GREENTEA_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pangreentea").withStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.SENBEI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_senbei").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_NORI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochinori").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_KINAKO) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochikinako").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_ANKO) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochianko").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_OHAGI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochiohagi").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_SAKURA) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochisakura").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.ANPAN) {
			itemTip.add(new TranslationTextComponent("tips.item_food_pananko").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.TENCHA) {
			itemTip.add(new TranslationTextComponent("tips.item_chaba_tencha").withStyle(TextFormatting.GRAY)); }
	}
}
