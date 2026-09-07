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
 
	/* アイテムは @Nullable World worldIn, ブロックは @Nullable IBlockReader worldIn*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {

		if (this == Items_Teatime.NIGARI) {
			itemTip.add(new TranslationTextComponent("tips.item_nigari").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SAKEKASU) {
			itemTip.add(new TranslationTextComponent("tips.item_sakekasu").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KOMEKOUJI) {
			itemTip.add(new TranslationTextComponent("tips.item_komekouji").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHUBO) {
			itemTip.add(new TranslationTextComponent("tips.item_shubo").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOROMI) {
			itemTip.add(new TranslationTextComponent("tips.item_moromi").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BUN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_bun").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BURG) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_burg").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SCONE) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_scone").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SENBEI) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_senbei").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MATCH) {
			itemTip.add(new TranslationTextComponent("tips.item_match_cm").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CHAWAN) {
			itemTip.add(new TranslationTextComponent("tips.item_food_chawan").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHIKKI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_shikki").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.TONSUI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_tonsui").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.YUNOMI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_yunomi").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_YUNOMI) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_yunomi").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_KYUSU) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_kyusu").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TCUP) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_teacup").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TPOT) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_teapot").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_SARA) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_sara").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_CHAWAN) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_chawan").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_NABE) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_nabe").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TONSUI) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_tonsui").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.FPKINOKOAK) {
			itemTip.add(new TranslationTextComponent("tips.item_frypan_kinokoak").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.KOUBO) {
			itemTip.add(new TranslationTextComponent("tips.item_koubo").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSAN) {
			itemTip.add(new TranslationTextComponent("tips.item_nyusan").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.RENNET) {
			itemTip.add(new TranslationTextComponent("tips.item_rennet").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.KIRIMI_S || this == Items_Teatime.KIRIMI_F || this == Items_Teatime.KIRIMI_B || this == Items_Teatime.KIRIMI_T) {
			itemTip.add(new TranslationTextComponent("tips.item_food_kirimi").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.KUSHI_SAKANA) {
			itemTip.add(new TranslationTextComponent("tips.item_kushi_sakana").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.CLAY_DONBURI) {
			itemTip.add(new TranslationTextComponent("tips.item_clay_donburi").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_donburi").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.IKA) {
			itemTip.add(new TranslationTextComponent("tips.item_squid_raw").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.PEPPER_RAW || this == Items_Teatime.VANILLA_RAW || this == Items_Teatime.NORI_N) {
			itemTip.add(new TranslationTextComponent("tips.item_crop_pepper").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.PEPPER_DRY || this == Items_Teatime.CHILIPEPPER) {
			itemTip.add(new TranslationTextComponent("tips.item_crop_pepperdry").applyTextStyle(TextFormatting.GRAY)); }
		
		if (this == Items_Teatime.SHOUYU_donburi) {
			itemTip.add(new TranslationTextComponent("tips.item_food_shouyu_don").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.TSUYU_donburi) {
			itemTip.add(new TranslationTextComponent("tips.item_food_tsuyu_don").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_TARE || this == Items_Teatime.MISO_TARE || this == Items_Teatime.SHIO_TARE) {
			itemTip.add(new TranslationTextComponent("tips.item_food_tare").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_Rsoup || this == Items_Teatime.MISO_Rsoup || this == Items_Teatime.SHIO_Rsoup) {
			itemTip.add(new TranslationTextComponent("tips.item_food_rsoup").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.SOBA_PLATE) {
			itemTip.add(new TranslationTextComponent("tips.item_food_sobaplate").applyTextStyle(TextFormatting.GRAY)); }
		
		/** AZUKI **/
		if (this == Items_Teatime.ANKO) {
			itemTip.add(new TranslationTextComponent("tips.item_anko").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_ANPAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pananko").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CUSTARD_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pancustard").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_APPLE_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_panapple").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CHERRY_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pancherry").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CITRUS_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pancitrus").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GRAPE_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pangrape").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GREENTEA_PAN) {
			itemTip.add(new TranslationTextComponent("tips.item_kiji_pangreentea").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Teatime.SENBEI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_senbei").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_NORI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochinori").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_KINAKO) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochikinako").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_ANKO) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochianko").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_OHAGI) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochiohagi").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_SAKURA) {
			itemTip.add(new TranslationTextComponent("tips.item_food_mochisakura").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.ANPAN) {
			itemTip.add(new TranslationTextComponent("tips.item_food_pananko").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.TENCHA) {
			itemTip.add(new TranslationTextComponent("tips.item_chaba_tencha").applyTextStyle(TextFormatting.GRAY)); }
	}
}
