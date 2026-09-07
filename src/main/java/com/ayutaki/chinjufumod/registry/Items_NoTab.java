package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.items.base.Item_LocalizedName;
import com.ayutaki.chinjufumod.items.chinjufu.AdmiralStamp_CN;
import com.ayutaki.chinjufumod.items.cooktool.NabeCookedSNT_NT;
import com.ayutaki.chinjufumod.items.hakkou.Dashi2_NT;
import com.ayutaki.chinjufumod.items.hakkou.Dashi3_NT;
import com.ayutaki.chinjufumod.items.hakkou.Dashi4_NT;
import com.ayutaki.chinjufumod.items.hakkou.Komezu2_NT;
import com.ayutaki.chinjufumod.items.hakkou.Shouyu2_NT;
import com.ayutaki.chinjufumod.items.hakkou.Shouyu3_NT;
import com.ayutaki.chinjufumod.items.hakkou.Shouyu4_NT;
import com.ayutaki.chinjufumod.items.hakkou.Vanilla2_NT;
import com.ayutaki.chinjufumod.items.hakkou.Vanilla3_NT;
import com.ayutaki.chinjufumod.items.hakkou.Vanilla4_NT;
import com.ayutaki.chinjufumod.items.seasonal.Iga_SN;
import com.ayutaki.chinjufumod.items.teatime.KantenRaw_NT;
import com.ayutaki.chinjufumod.items.teatime.Mayo2_NT;
import com.ayutaki.chinjufumod.items.teatime.Mayo3_NT;
import com.ayutaki.chinjufumod.items.teatime.Mayo4_NT;
import com.ayutaki.chinjufumod.items.teatime.OSauce2_NT;
import com.ayutaki.chinjufumod.items.teatime.OSauce3_NT;
import com.ayutaki.chinjufumod.items.teatime.OSauce4_NT;
import com.ayutaki.chinjufumod.items.teatime.PuddingRaw_NT;
import com.ayutaki.chinjufumod.items.teatime.Soyoil2_NT;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class Items_NoTab {

	public static Item EMBLEM_C, ADMIRAL_STAMP;
	
	public static Item SHOUYU_bot_24, SHOUYU_bot_34, SHOUYU_bot_44;
	public static Item KOMEZU_bot_22, SOYOIL_bot_22;
	public static Item DASHI_bot_24, DASHI_bot_34, DASHI_bot_44;
	public static Item OSAUCE_bot_24, OSAUCE_bot_34, OSAUCE_bot_44;
	public static Item MAYO_bot_24, MAYO_bot_34, MAYO_bot_44;
	public static Item VANILLA_bot_24, VANILLA_bot_34, VANILLA_bot_44;
	public static Item HAMAGURI_KARA;
	public static Item NABE_SNT, KANTEN_RAW, PUDDING_RAW;
	public static Item IGA;
	
	public static Item CARTRIDGE_L, CARTRIDGE_M, CARTRIDGE_S, CARTRIDGE_K;

	
	public static void init() {

		EMBLEM_C = new Item_LocalizedName("item_emblem_c");
		ADMIRAL_STAMP = new AdmiralStamp_CN("item_admiralstamp", Chinjufu_Blocks.ADMIRAL_STAMP1);
		
		SHOUYU_bot_24 = new Shouyu2_NT("block_shouyu_bot_2");
		SHOUYU_bot_34 = new Shouyu3_NT("block_shouyu_bot_3");
		SHOUYU_bot_44 = new Shouyu4_NT("block_shouyu_bot_4");

		KOMEZU_bot_22 = new Komezu2_NT("block_komezu_bot_2");
		SOYOIL_bot_22 = new Soyoil2_NT("block_soyoil_bot_2");
		
		DASHI_bot_24 = new Dashi2_NT("block_dashi_bot_2");
		DASHI_bot_34 = new Dashi3_NT("block_dashi_bot_3");
		DASHI_bot_44 = new Dashi4_NT("block_dashi_bot_4");

		OSAUCE_bot_24 = new OSauce2_NT("block_osauce_bot_2");
		OSAUCE_bot_34 = new OSauce3_NT("block_osauce_bot_3");
		OSAUCE_bot_44 = new OSauce4_NT("block_osauce_bot_4");

		MAYO_bot_24 = new Mayo2_NT("block_mayo_bot_2");
		MAYO_bot_34 = new Mayo3_NT("block_mayo_bot_3");
		MAYO_bot_44 = new Mayo4_NT("block_mayo_bot_4");

		VANILLA_bot_24 = new Vanilla2_NT("block_vanilla_bot_2");
		VANILLA_bot_34 = new Vanilla3_NT("block_vanilla_bot_3");
		VANILLA_bot_44 = new Vanilla4_NT("block_vanilla_bot_4");
		
		HAMAGURI_KARA = new Item_LocalizedName("item_hamaguri_shell");
		NABE_SNT = new NabeCookedSNT_NT("block_food_nabecooked_snt");
		KANTEN_RAW = new KantenRaw_NT("item_raw_kanten");
		PUDDING_RAW = new PuddingRaw_NT("item_raw_pudding");
		
		IGA = new Iga_SN("item_chestnuts_burr");
		
		CARTRIDGE_L = new Item_LocalizedName("item_cartridge_kc");
		CARTRIDGE_M = new Item_LocalizedName("item_cartridge_medium");
		CARTRIDGE_S = new Item_LocalizedName("item_cartridge_small");
		CARTRIDGE_K = new Item_LocalizedName("item_cartridge_kijyuu");
	}

	public static void register() {
		registerItem(EMBLEM_C);
		registerItem(ADMIRAL_STAMP);

		registerItem(SHOUYU_bot_24);
		registerItem(SHOUYU_bot_34);
		registerItem(SHOUYU_bot_44);

		registerItem(KOMEZU_bot_22);
		registerItem(SOYOIL_bot_22);
		
		registerItem(DASHI_bot_24);
		registerItem(DASHI_bot_34);
		registerItem(DASHI_bot_44);

		registerItem(OSAUCE_bot_24);
		registerItem(OSAUCE_bot_34);
		registerItem(OSAUCE_bot_44);

		registerItem(MAYO_bot_24);
		registerItem(MAYO_bot_34);
		registerItem(MAYO_bot_44);
		registerItem(VANILLA_bot_24);
		registerItem(VANILLA_bot_34);
		registerItem(VANILLA_bot_44);
		
		registerItem(HAMAGURI_KARA);
		registerItem(NABE_SNT);
		registerItem(KANTEN_RAW);
		registerItem(PUDDING_RAW);
		
		registerItem(IGA);
		
		registerItem(CARTRIDGE_L);
		registerItem(CARTRIDGE_M);
		registerItem(CARTRIDGE_S);
		registerItem(CARTRIDGE_K);
	}

	public static void registerItem(Item item) {
		RegisterHandler_CM.Items.ITEMS.add(item);
	}

	public static void registerRenders() {

		registerRender(EMBLEM_C);
		registerRender(ADMIRAL_STAMP);
		
		registerRender(SHOUYU_bot_24);
		registerRender(SHOUYU_bot_34);
		registerRender(SHOUYU_bot_44);

		registerRender(KOMEZU_bot_22);
		registerRender(SOYOIL_bot_22);
		
		registerRender(DASHI_bot_24);
		registerRender(DASHI_bot_34);
		registerRender(DASHI_bot_44);

		registerRender(OSAUCE_bot_24);
		registerRender(OSAUCE_bot_34);
		registerRender(OSAUCE_bot_44);

		registerRender(MAYO_bot_24);
		registerRender(MAYO_bot_34);
		registerRender(MAYO_bot_44);
		registerRender(VANILLA_bot_24);
		registerRender(VANILLA_bot_34);
		registerRender(VANILLA_bot_44);
		
		registerRender(HAMAGURI_KARA);

		registerRenderMeta(NABE_SNT, 1, "block_food_nabeshio_b");
		registerRenderMeta(NABE_SNT, 2, "block_food_nabenimame_b");
		registerRenderMeta(NABE_SNT, 3, "block_food_nabetoufu_b");

		registerRenderMeta(KANTEN_RAW, 10, "item_raw_icecream");
		registerRenderMeta(KANTEN_RAW, 11, "item_raw_icecream_greentea");
		registerRenderMeta(KANTEN_RAW, 12, "item_raw_icecream_redtea");
		registerRenderMeta(KANTEN_RAW, 13, "item_raw_icecream_cacao");
		registerRenderMeta(KANTEN_RAW, 0, "item_raw_kanten_apple");
		registerRenderMeta(KANTEN_RAW, 1, "item_raw_kanten_cherry");
		registerRenderMeta(KANTEN_RAW, 2, "item_raw_kanten_citrus");
		registerRenderMeta(KANTEN_RAW, 3, "item_raw_kanten_grape");
		registerRenderMeta(KANTEN_RAW, 4, "item_raw_kanten_milk");
		registerRenderMeta(KANTEN_RAW, 5, "item_raw_yokan");
		registerRenderMeta(KANTEN_RAW, 6, "item_raw_yokan_matcha");
		
		registerRenderMeta(PUDDING_RAW, 1, "item_raw_pudding_custard");
		registerRenderMeta(PUDDING_RAW, 2, "item_raw_pudding_greentea");
		registerRenderMeta(PUDDING_RAW, 3, "item_raw_pudding_redtea");
		registerRenderMeta(PUDDING_RAW, 4, "item_raw_pudding_cacao");
		
		registerRender(IGA);
		
		registerRender(CARTRIDGE_L);
		registerRender(CARTRIDGE_M);
		registerRender(CARTRIDGE_S);
		registerRender(CARTRIDGE_K);
	}

	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(),"inventory"));
	}

	private static void registerRenderMeta(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(new ResourceLocation(ChinjufuMod.MOD_ID, fileName), "inventory"));
	}
}
