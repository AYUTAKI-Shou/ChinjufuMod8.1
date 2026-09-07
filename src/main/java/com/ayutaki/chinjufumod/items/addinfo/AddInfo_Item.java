package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class AddInfo_Item extends Item {

	public AddInfo_Item(Item.Properties props) {
		super(props);
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {

		if (this == Items_NoTab.ROTTEN_FOOD.get()) {
			itemTip.add(Component.translatable("tips.item_rotten_food").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Chinjufu.SHOUHOU_empty.get()) {
			itemTip.add(Component.translatable("tips.item_shouhou_empty").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Chinjufu.SUMI.get()) {
			itemTip.add(Component.translatable("tips.item_sumi").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.NIGARI.get()) {
			itemTip.add(Component.translatable("tips.item_nigari").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SAKEKASU.get()) {
			itemTip.add(Component.translatable("tips.item_sakekasu").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KOMEKOUJI.get()) {
			itemTip.add(Component.translatable("tips.item_komekouji").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHUBO.get()) {
			itemTip.add(Component.translatable("tips.item_shubo").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOROMI.get()) {
			itemTip.add(Component.translatable("tips.item_moromi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BUN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_bun").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_BURG.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_burg").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SCONE.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_scone").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_SENBEI.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_senbei").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MATCH.get()) {
			itemTip.add(Component.translatable("tips.item_match_cm").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.CHAWAN.get()) {
			itemTip.add(Component.translatable("tips.item_food_chawan").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHIKKI.get()) {
			itemTip.add(Component.translatable("tips.item_food_shikki").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.TONSUI.get()) {
			itemTip.add(Component.translatable("tips.item_food_tonsui").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.YUNOMI.get()) {
			itemTip.add(Component.translatable("tips.item_food_yunomi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI.get()) {
			itemTip.add(Component.translatable("tips.item_food_donburi").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.CLAY_YUNOMI.get()) {
			itemTip.add(Component.translatable("tips.item_clay_yunomi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_KYUSU.get()) {
			itemTip.add(Component.translatable("tips.item_clay_kyusu").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TCUP.get()) {
			itemTip.add(Component.translatable("tips.item_clay_teacup").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TPOT.get()) {
			itemTip.add(Component.translatable("tips.item_clay_teapot").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_SARA.get()) {
			itemTip.add(Component.translatable("tips.item_clay_sara").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_CHAWAN.get()) {
			itemTip.add(Component.translatable("tips.item_clay_chawan").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_NABE.get()) {
			itemTip.add(Component.translatable("tips.item_clay_nabe").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_TONSUI.get()) {
			itemTip.add(Component.translatable("tips.item_clay_tonsui").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.CLAY_DONBURI.get()) {
			itemTip.add(Component.translatable("tips.item_clay_donburi").withStyle(ChatFormatting.GRAY)); }
		
		//FPKINOKOAK
		//KOUBO
		//NYUSAN
		
		if (this == Items_Teatime.RENNET.get()) {
			itemTip.add(Component.translatable("tips.item_rennet").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.KIRIMI_S.get() || this == Items_Teatime.KIRIMI_F.get() || this == Items_Teatime.KIRIMI_B.get() || this == Items_Teatime.KIRIMI_T.get()) {
			itemTip.add(Component.translatable("tips.item_food_kirimi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KUSHI_SAKANA.get()) {
			itemTip.add(Component.translatable("tips.item_kushi_sakana").withStyle(ChatFormatting.GRAY)); }
		//IKA

		if (this == Items_Teatime.PEPPER_RAW.get() || this == Items_Teatime.VANILLA_RAW.get() || this == Items_Teatime.NORI_N.get()) {
			itemTip.add(Component.translatable("tips.item_crop_pepper").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.PEPPER_DRY.get() || this == Items_Teatime.CHILIPEPPER.get()) {
			itemTip.add(Component.translatable("tips.item_crop_pepperdry").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Teatime.SHOUYU_donburi.get()) {
			itemTip.add(Component.translatable("tips.item_food_shouyu_don").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.TSUYU_donburi.get()) {
			itemTip.add(Component.translatable("tips.item_food_tsuyu_don").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_TARE.get() || this == Items_Teatime.MISO_TARE.get() || this == Items_Teatime.SHIO_TARE.get()) {
			itemTip.add(Component.translatable("tips.item_food_tare").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SHOUYU_Rsoup.get() || this == Items_Teatime.MISO_Rsoup.get() || this == Items_Teatime.SHIO_Rsoup.get()) {
			itemTip.add(Component.translatable("tips.item_food_rsoup").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.SOBA_PLATE.get()) {
			itemTip.add(Component.translatable("tips.item_food_sobaplate").withStyle(ChatFormatting.GRAY)); }

		/** AZUKI **/
		if (this == Items_Teatime.ANKO.get()) {
			itemTip.add(Component.translatable("tips.item_anko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_ANPAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_pananko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CUSTARD_PAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_pancustard").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_APPLE_PAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_panapple").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CHERRY_PAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_pancherry").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_CITRUS_PAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_pancitrus").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GRAPE_PAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_pangrape").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KIJI_GREENTEA_PAN.get()) {
			itemTip.add(Component.translatable("tips.item_kiji_pangreentea").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Teatime.SENBEI.get()) {
			itemTip.add(Component.translatable("tips.item_food_senbei").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_NORI.get()) {
			itemTip.add(Component.translatable("tips.item_food_mochinori").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_KINAKO.get()) {
			itemTip.add(Component.translatable("tips.item_food_mochikinako").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_ANKO.get()) {
			itemTip.add(Component.translatable("tips.item_food_mochianko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_OHAGI.get()) {
			itemTip.add(Component.translatable("tips.item_food_mochiohagi").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.MOCHI_SAKURA.get()) {
			itemTip.add(Component.translatable("tips.item_food_mochisakura").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.ANPAN.get()) {
			itemTip.add(Component.translatable("tips.item_food_pananko").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.TENCHA.get()) {
			itemTip.add(Component.translatable("tips.item_chaba_tencha").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_NoTab.RAW_ICE_VANILLA.get() || this == Items_NoTab.RAW_ICE_GREEN.get() ||
				this == Items_NoTab.RAW_ICE_RED.get() || this == Items_NoTab.RAW_ICE_CACAO.get()) {
			itemTip.add(Component.translatable("tips.item_raw_icecream").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_NoTab.RAW_KANTEN_APPLE.get() || this == Items_NoTab.RAW_KANTEN_CHERRY.get() ||
				this == Items_NoTab.RAW_KANTEN_CITRUS.get() || this == Items_NoTab.RAW_KANTEN_GRAPE.get() ||
				this == Items_NoTab.RAW_KANTEN_MILK.get() ||
				this == Items_NoTab.RAW_YOKAN.get() || this == Items_NoTab.RAW_YOKAN_MATCHA.get()) {
			itemTip.add(Component.translatable("tips.item_raw_kanten").withStyle(ChatFormatting.GRAY)); }
		
		/** Seasonal **/
		if (this == Items_Seasonal.KURI.get()) {
			itemTip.add(Component.translatable("tips.item_chestnut").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.KURI_SWEET.get() || this == Items_Seasonal.KURI_CHOCO.get()) {
			itemTip.add(Component.translatable("tips.item_chestnutsweet").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Seasonal.COCOA_F.get()) {
			itemTip.add(Component.translatable("tips.item_cocoa_ferm").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.COCOA_R.get()) {
			itemTip.add(Component.translatable("tips.item_cocoa_roast").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.COCOA_M.get()) {
			itemTip.add(Component.translatable("tips.item_cocoa_mass").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.CHOCO_raw.get()) {
			itemTip.add(Component.translatable("tips.item_choco_raw").withStyle(ChatFormatting.GRAY)); }
		
		if (this == Items_Wadeco.HAKE.get()) {
			itemTip.add(Component.translatable("tips.item_hake").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Wablock.SHOUSEKKAI.get()) {
			itemTip.add(Component.translatable("tips.item_shousekkai_c").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Wablock.CLAYKAWARA.get()) {
			itemTip.add(Component.translatable("tips.item_claykawara").withStyle(ChatFormatting.GRAY)); }
	}
}
