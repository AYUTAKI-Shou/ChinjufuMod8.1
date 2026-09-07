package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AddInfo_TT extends IG_Teatime {

	public AddInfo_TT(Item.Properties props) {
		super(props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {

		if (this == Items_Teatime.NIGARI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_nigari").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SAKEKASU.get()) {
			itemTip.add(new TranslatableComponent("tips.item_sakekasu").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KOMEKOUJI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_komekouji").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHUBO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_shubo").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOROMI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_moromi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BUN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_bun").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BURG.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_burg").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SCONE.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_scone").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SENBEI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_senbei").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MATCH.get()) {
			itemTip.add(new TranslatableComponent("tips.item_match_cm").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.CHAWAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_chawan").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHIKKI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_shikki").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.TONSUI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_tonsui").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.YUNOMI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_yunomi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_donburi").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.CLAY_YUNOMI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_yunomi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_KYUSU.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_kyusu").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TCUP.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_teacup").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TPOT.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_teapot").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_SARA.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_sara").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_CHAWAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_chawan").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_NABE.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_nabe").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TONSUI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_tonsui").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_DONBURI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_clay_donburi").withStyle(ChatFormatting.GRAY)); }
			
		if (this == Items_Teatime.FPKINOKOAK.get()) {
			itemTip.add(new TranslatableComponent("tips.item_frypan_kinokoak").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KOUBO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_koubo").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_nyusan").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.RENNET.get()) {
			itemTip.add(new TranslatableComponent("tips.item_rennet").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.KIRIMI_S.get() || this == Items_Teatime.KIRIMI_F.get() || this == Items_Teatime.KIRIMI_B.get() || this == Items_Teatime.KIRIMI_T.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_kirimi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KUSHI_SAKANA.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kushi_sakana").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.IKA.get()) {
			itemTip.add(new TranslatableComponent("tips.item_squid_raw").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.PEPPER_RAW.get() || this == Items_Teatime.VANILLA_RAW.get() || this == Items_Teatime.NORI_N.get()) {
			itemTip.add(new TranslatableComponent("tips.item_crop_pepper").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.PEPPER_DRY.get() || this == Items_Teatime.CHILIPEPPER.get()) {
			itemTip.add(new TranslatableComponent("tips.item_crop_pepperdry").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Teatime.SHOUYU_donburi.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_shouyu_don").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.TSUYU_donburi.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_tsuyu_don").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_TARE.get() || this == Items_Teatime.MISO_TARE.get() || this == Items_Teatime.SHIO_TARE.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_tare").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_Rsoup.get() || this == Items_Teatime.MISO_Rsoup.get() || this == Items_Teatime.SHIO_Rsoup.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_rsoup").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SOBA_PLATE.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_sobaplate").withStyle(ChatFormatting.GRAY)); }
		
		/** AZUKI **/
		if (this == Items_Teatime.ANKO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_anko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_ANPAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_pananko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CUSTARD_PAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_pancustard").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_APPLE_PAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_panapple").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CHERRY_PAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_pancherry").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GRAPE_PAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_pangrape").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GREENTEA_PAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kiji_pangreentea").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Teatime.SENBEI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_senbei").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_NORI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_mochinori").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_KINAKO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_mochikinako").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_ANKO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_mochianko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_OHAGI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_mochiohagi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_SAKURA.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_mochisakura").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.ANPAN.get()) {
			itemTip.add(new TranslatableComponent("tips.item_food_pananko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.TENCHA.get()) {
			itemTip.add(new TranslatableComponent("tips.item_chaba_tencha").withStyle(ChatFormatting.GRAY)); }
	}
}
