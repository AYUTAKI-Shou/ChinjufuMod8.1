package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.items.cooktool.FryPanKara_TT;
import com.ayutaki.chinjufumod.items.cooktool.FryPan_KinokoAma_TT;
import com.ayutaki.chinjufumod.items.cooktool.FrypanBake1_NT;
import com.ayutaki.chinjufumod.items.cooktool.FrypanBake2_NT;
import com.ayutaki.chinjufumod.items.cooktool.FrypanNama1_TT;
import com.ayutaki.chinjufumod.items.cooktool.FrypanNama2_TT;
import com.ayutaki.chinjufumod.items.cooktool.FrypanNama3_TT;
import com.ayutaki.chinjufumod.items.cooktool.KettleBoil_TT;
import com.ayutaki.chinjufumod.items.cooktool.KettleFull_TT;
import com.ayutaki.chinjufumod.items.cooktool.KettleKara_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeCooked_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeKaisui_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeKara_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeNama1_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeNama2_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeNamaAzuki_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeNamaGohanTK_TT;
import com.ayutaki.chinjufumod.items.cooktool.NabeNamaPudding_TT;
import com.ayutaki.chinjufumod.items.cooktool.Teppan_TT;
import com.ayutaki.chinjufumod.items.cooktool.ZundouDashi_TT;
import com.ayutaki.chinjufumod.items.cooktool.ZundouMilk_TT;
import com.ayutaki.chinjufumod.items.cooktool.ZundouMizu_TT;
import com.ayutaki.chinjufumod.items.cooktool.ZundouNama_TT;
import com.ayutaki.chinjufumod.items.cooktool.ZundouRsoup_TT;
import com.ayutaki.chinjufumod.items.cooktool.ZundouShioMizu_TT;
import com.ayutaki.chinjufumod.items.cooktool.Zundou_TT;
import com.ayutaki.chinjufumod.items.crops.ChanokiNae_TT;
import com.ayutaki.chinjufumod.items.crops.Enden_TT;
import com.ayutaki.chinjufumod.items.crops.GrapeNae_TT;
import com.ayutaki.chinjufumod.items.crops.Hamaguri_TB;
import com.ayutaki.chinjufumod.items.crops.HodaGi_TT;
import com.ayutaki.chinjufumod.items.crops.Inagi_TT;
import com.ayutaki.chinjufumod.items.crops.MikanNae_TT;
import com.ayutaki.chinjufumod.items.crops.SeedCorn_TT;
import com.ayutaki.chinjufumod.items.crops.Seed_TT;
import com.ayutaki.chinjufumod.items.crops.SeedsBox_TT;
import com.ayutaki.chinjufumod.items.crops.SpiceNae_TT;
import com.ayutaki.chinjufumod.items.dish.CurryNabe_TT;
import com.ayutaki.chinjufumod.items.dish.CurrySet_TT;
import com.ayutaki.chinjufumod.items.dish.Dish_Chawan;
import com.ayutaki.chinjufumod.items.dish.Dish_Curry;
import com.ayutaki.chinjufumod.items.dish.Dish_Donburi;
import com.ayutaki.chinjufumod.items.dish.Dish_Kanten;
import com.ayutaki.chinjufumod.items.dish.Dish_LUCK;
import com.ayutaki.chinjufumod.items.dish.Dish_Okonomiyaki;
import com.ayutaki.chinjufumod.items.dish.Dish_Pasta;
import com.ayutaki.chinjufumod.items.dish.Dish_PlateStage3;
import com.ayutaki.chinjufumod.items.dish.Dish_Ramen;
import com.ayutaki.chinjufumod.items.dish.Dish_Shikki;
import com.ayutaki.chinjufumod.items.dish.Dish_SoupStew;
import com.ayutaki.chinjufumod.items.dish.Dish_Tamagoyaki;
import com.ayutaki.chinjufumod.items.dish.Dish_Tonsui;
import com.ayutaki.chinjufumod.items.dish.Dish_Udon;
import com.ayutaki.chinjufumod.items.dish.Face4_1Stone_TT;
import com.ayutaki.chinjufumod.items.dish.Face4_1Wood_TT;
import com.ayutaki.chinjufumod.items.dish.FishBoiled_TT;
import com.ayutaki.chinjufumod.items.dish.JPChaUke_TT;
import com.ayutaki.chinjufumod.items.dish.KyusuKara_TT;
import com.ayutaki.chinjufumod.items.dish.PizzaCooked_TT;
import com.ayutaki.chinjufumod.items.dish.SconeSet1_TT;
import com.ayutaki.chinjufumod.items.dish.ShouyuSara_TT;
import com.ayutaki.chinjufumod.items.dish.StewNabe_TT;
import com.ayutaki.chinjufumod.items.dish.SushiGetaKara_TT;
import com.ayutaki.chinjufumod.items.dish.SushiMeshi_TT;
import com.ayutaki.chinjufumod.items.dish.SushiOkeFull_TT;
import com.ayutaki.chinjufumod.items.dish.SushiOkeKara_TT;
import com.ayutaki.chinjufumod.items.dish.TeaCup_Item;
import com.ayutaki.chinjufumod.items.dish.TeaPotKara_TT;
import com.ayutaki.chinjufumod.items.dish.TeaStand_TT;
import com.ayutaki.chinjufumod.items.dish.Yunomi_TT;
import com.ayutaki.chinjufumod.items.food.Bentou;
import com.ayutaki.chinjufumod.items.food.Food_Cheese;
import com.ayutaki.chinjufumod.items.food.Food_Cherry;
import com.ayutaki.chinjufumod.items.food.Food_Hamaguri;
import com.ayutaki.chinjufumod.items.food.Food_Mochi;
import com.ayutaki.chinjufumod.items.food.Food_Pan;
import com.ayutaki.chinjufumod.items.food.Food_Pizza;
import com.ayutaki.chinjufumod.items.food.Food_TTabName;
import com.ayutaki.chinjufumod.items.food.Kirimi;
import com.ayutaki.chinjufumod.items.food.KushiSakana;
import com.ayutaki.chinjufumod.items.food.Onigiri;
import com.ayutaki.chinjufumod.items.food.Senbei;
import com.ayutaki.chinjufumod.items.food.Sushi;
import com.ayutaki.chinjufumod.items.food.Sushi_Shouyu;
import com.ayutaki.chinjufumod.items.fuel.TabBlock_Fuel100;
import com.ayutaki.chinjufumod.items.fuel.TabBlock_Fuel150;
import com.ayutaki.chinjufumod.items.fuel.TabBlock_Fuel200;
import com.ayutaki.chinjufumod.items.fuel.TabBlock_Fuel300;
import com.ayutaki.chinjufumod.items.fuel.TabBlock_noFuel;
import com.ayutaki.chinjufumod.items.hakkou.BinKouboFull_TT;
import com.ayutaki.chinjufumod.items.hakkou.BinKoubo_TT;
import com.ayutaki.chinjufumod.items.hakkou.BinNyusanFull_TT;
import com.ayutaki.chinjufumod.items.hakkou.BinNyusan_TT;
import com.ayutaki.chinjufumod.items.hakkou.CheeseBlock_TT;
import com.ayutaki.chinjufumod.items.hakkou.CheeseCurd_TT;
import com.ayutaki.chinjufumod.items.hakkou.Dashi1_NT;
import com.ayutaki.chinjufumod.items.hakkou.GlassSake_TT;
import com.ayutaki.chinjufumod.items.hakkou.GlassWine_TT;
import com.ayutaki.chinjufumod.items.hakkou.Komezu1_NT;
import com.ayutaki.chinjufumod.items.hakkou.MizuokeFull_TT;
import com.ayutaki.chinjufumod.items.hakkou.MizuokeKara_TT;
import com.ayutaki.chinjufumod.items.hakkou.MizuokeMilk_NT;
import com.ayutaki.chinjufumod.items.hakkou.Moromi_TT;
import com.ayutaki.chinjufumod.items.hakkou.NabeAmazakeNama_TT;
import com.ayutaki.chinjufumod.items.hakkou.Nimame_TT;
import com.ayutaki.chinjufumod.items.hakkou.Sakekasu_TT;
import com.ayutaki.chinjufumod.items.hakkou.Shouyu1_NT;
import com.ayutaki.chinjufumod.items.hakkou.Shubo_TT;
import com.ayutaki.chinjufumod.items.hakkou.TaruHakusai_TT;
import com.ayutaki.chinjufumod.items.hakkou.TaruItem_TT;
import com.ayutaki.chinjufumod.items.hakkou.TaruShouyu_TT;
import com.ayutaki.chinjufumod.items.hakkou.Vanilla1_NT;
import com.ayutaki.chinjufumod.items.teatime.Chaba_TT;
import com.ayutaki.chinjufumod.items.teatime.DishesClay_TT;
import com.ayutaki.chinjufumod.items.teatime.Dishes_TT;
import com.ayutaki.chinjufumod.items.teatime.IkaRaw_TT;
import com.ayutaki.chinjufumod.items.teatime.Ine_TT;
import com.ayutaki.chinjufumod.items.teatime.Item_TTab;
import com.ayutaki.chinjufumod.items.teatime.Kaihori_TT;
import com.ayutaki.chinjufumod.items.teatime.KansuiCup_TT;
import com.ayutaki.chinjufumod.items.teatime.KantenBowl_TT;
import com.ayutaki.chinjufumod.items.teatime.KineTsuki_TT;
import com.ayutaki.chinjufumod.items.teatime.KinokoAmakara_TT;
import com.ayutaki.chinjufumod.items.teatime.KitSink_TT;
import com.ayutaki.chinjufumod.items.teatime.Komugi_TT;
import com.ayutaki.chinjufumod.items.teatime.Match_TT;
import com.ayutaki.chinjufumod.items.teatime.Mayo1_NT;
import com.ayutaki.chinjufumod.items.teatime.MeasureCupFull_TT;
import com.ayutaki.chinjufumod.items.teatime.MeasureCup_TT;
import com.ayutaki.chinjufumod.items.teatime.Mushigome_TT;
import com.ayutaki.chinjufumod.items.teatime.OSauce1_NT;
import com.ayutaki.chinjufumod.items.teatime.PanKiji_TT;
import com.ayutaki.chinjufumod.items.teatime.PastaNama_TT;
import com.ayutaki.chinjufumod.items.teatime.RamenNama_TT;
import com.ayutaki.chinjufumod.items.teatime.Shio_TT;
import com.ayutaki.chinjufumod.items.teatime.Soyoil1_NT;
import com.ayutaki.chinjufumod.items.teatime.Spice_TT;
import com.ayutaki.chinjufumod.items.teatime.TeaTable_TT;
import com.ayutaki.chinjufumod.items.teatime.ToamiWide_TB;
import com.ayutaki.chinjufumod.items.teatime.Toami_TT;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class Items_Teatime {

	/* 種, 作物 */
	public static Item SEEDSBOX;
	public static Item SEEDS_AZUKI, SEEDS_CABBAGE, SEEDS_HAKUSAI, SEEDS_CORN, 
								SEEDS_GREENONION, SEEDS_ONION, SEEDS_RICE, SEEDS_SOY,
								SEEDS_SPINACH, SEEDS_TOMATO, SEEDS_CHERRY;
	public static Item CHANOKI, BUDOUNOKI, MIKAN_NAE, SPICE_NAE;
	public static Item HODAGI_BOT;

	public static Item CHABA;
	public static Item INAGI, INEWARA, INE, KOME, SAYA, SOY, SPICE;

	public static Item FOOD_CABBAGE, FOOD_HAKUSAI, FOOD_CORN, FOOD_GREENONION,
								FOOD_ONION, FOOD_SPINACH, FOOD_TOMATO,
								FOOD_GRAPE, FOOD_CHERRY, FOOD_MIKAN, FOOD_CORN_B;

	public static Item BOX_H_EMPTY, BOX_H_APPLE, BOX_H_BEEF,
								BOX_H_BEETROOT, BOX_H_BREAD, BOX_H_CARROT,
								BOX_H_CHICKEN, BOX_H_CHORUS, BOX_H_COCO,
								BOX_H_EGG, BOX_H_FISH, BOX_H_FLOUR,
								BOX_H_MUTTON, BOX_H_PORK, BOX_H_POTATO,
								BOX_H_RABBIT, BOX_H_SALMON;

	public static Item BOX_H_AZUKI, BOX_H_CABBAGE, BOX_H_HAKUSAI, BOX_H_CITRUS, 
								BOX_H_CORN, BOX_H_GRAPE, BOX_H_GREENONION, BOX_H_ONION, BOX_H_ORIENTCLAM,
								BOX_H_RICE, BOX_H_SOY, BOX_H_SPINACH, BOX_H_SQUID,
								BOX_H_TOMATO, BOX_H_CHERRY, BOX_H_TAKENOKO, BOX_H_KURI,
								BOX_H_TGREEN, BOX_H_TRED, BOX_H_BPEPPER, BOX_H_CUMIN, BOX_H_TURMERIC, BOX_H_CHILI;
	
	public static Item CHADUTSU, CANTEA, TAWARA;

	/* 発酵 */
	public static Item KOUBOBOT_full, NYUSANBOT_full;
	public static Item KOUBO, NYUSAN;

	public static Item MIZUOKE, MIZUOKE_full, MIZUOKE_Milk;
	public static Item HAKKOUTARU;
	public static Item RINGOSHU_TARU, BUDOUSHU_TARU, HACHIMITSUSHU_TARU;
	public static Item NAMASAKEBOT, SAKEBOT, JUKUSAKEBOT;
	public static Item NABEAMAZAKE_nama, NABEAMAZAKE;
	public static Item CIDERBOT, JUKUCIDERBOT;
	public static Item WINEBOT, JUKUWINEBOT;
	public static Item MEADBOT, JUKUMEADBOT;

	public static Item SAKEGLASS, WINEGLASS;
	public static Item WINE_TANA;

	public static Item HAKUSAI_TARU, SHOUYU_TARU;
	public static Item SHOUYU_bot_14, KOMEZU_bot_12, SOYOIL_bot_12, DASHI_bot_14, 
								OSAUCE_bot_14, MAYO_bot_14, VANILLA_bot_14;
	
	/* 食器 */
	public static Item Item_MATCH;
	public static Item Item_SARA, Item_DISH, CLAY_DISH;

	/* 中間素材 */
	public static Item SHIO;
	public static Item KANTEN_BOWL, MUSHIGOME, KOMEKOUJI, SHUBO, MORO, SAKEKASU;
	public static Item NIMAME, MISO;
	public static Item KOMUGI, PASTA, PAN_KIJI;
	public static Item KEIRYO_CUP, KEIRYO_CUP_full, KANSUI, RAMEN_nama;
	
	/* 調理済み 寸胴 -> 鍋 -> フライパン */
	public static Item ZUNDOU, ZUNDOU_RCURRY, ZUNDOU_MIZU, ZUNDOU_MILK, ZUNDOUSHIO;
	public static Item ZUNDOU_CURRY, ZUNDOU_STEW, DASHINABE, ZUNDOU_RSOUP;

	public static Item NABE_kara, NABE_NAMA_1, NABESHIO_nama, NABE_nama_SNTA, NABE_nama_TK, 
								NABE_NAMA_AZUKI, NABE_NAMA_PUDDING;
	public static Item NABETORI, NABEMISO, NABEZENZAI_M, NABEZENZAI_K,
								NABEGOHAN, NABEGOHAN_TAKE, NABEGOHAN_KURI, NABESEKIHAN,
								NABECORNSOUP;

	public static Item FRYPAN_kara, FRYPAN_NAMA_1, FRYPAN_NAMA_3, FRYPAN_NAMA_2, FPKINOKOAK_nama, FRYPAN_NAMA_4;
	public static Item FRYPAN_BAKE_1, FRYPAN_BAKE_2, FPKINOKOAK;

	public static Item NIBOSHI;
	public static Item CURRY, CURRYSET, STEW;
	public static Item UDON_SU, UDON_NIKU, UDON_TSUKIMI, RAMEN;
	public static Item TONSUITORI, MISOSOUP, ZENZAI_M, ZENZAI_K,
								GOHAN, GOHAN_TAKE, GOHAN_KURI, SEKIHAN, RICE;

	public static Item DONBURI_MESHI, DONBURI_GYU, DONBURI_OYAKO, DONBURI_KATSU, DONBURI_KAISEN;

	public static Item FOOD_HAKUSAI2, HAKUSAIDUKE;
	public static Item TAMAGOYAKI, TAMAGOYAKITEI ,YAKIZAKANATEI, YAKIJYAKETEI,
								TAMAGOYAKITEI_TAKE, YAKIZAKANATEI_TAKE, YAKIJYAKETEI_TAKE,
								TAMAGOYAKITEI_KURI, YAKIZAKANATEI_KURI, YAKIJYAKETEI_KURI,
								TAMAGOYAKITEI_SEKI, YAKIZAKANATEI_SEKI, YAKIJYAKETEI_SEKI;
	public static Item CORNSOUP, EGGBURG, EGGBURGSET;

	public static Item PASTATOMATO, PASTACHEESE, PASTAKINOKO, PASTASEAFOOD;
	public static Item PIZZA, PC_PIZZA, OKONOMIYAKI, CHICKEN, CHICKEN_small;

	public static Item SUSHIMESHI;
	public static Item SUSHIGETA_kara, SUSHISET_salmon, SUSHISET_fish, SUSHISET_beef, SUSHISET_tamago, SUSHISET_4shoku;
	public static Item SUSHIOKE, SUSHIOKE_FULL_1;
	public static Item SHOUYUSARA;

	public static Item Item_YAKAN_kara, KETTLE_full, Item_YAKAN_boil;
	public static Item KYUSU_kara, KYUSU, JPTEACUP, JPTEASET, JPCHAUKE;
	public static Item TEAPOT_kara, TEAPOT, TEACUP, TEASET;
	public static Item SCONESET_kara, SCONESET_1;

	public static Item ICECREAM, KANTEN;
	public static Item CHEESE_CURD, CHEESE;
	public static Item FRESH_CHEESE, PIECE_CHEESE;
	public static Item CHEESE_TANA;

	public static Item CAKE, BUN, SCONE, SENBEI, TOUFU;
	public static Item CHICKENSAND, EGGSAND;
	public static Item KIRIMI, SUSHI, SHOUYUSUSHI;

	public static Item NORIAMI, NORI_N, NORI_I, ONIGIRI;
	public static Item KUSHI_SAKANA, KUSHI_SAKANA_C, BENTOU;
	public static Item MOCHI, MOCHI_FOOD, PAN_FOOD;

	public static Item KIT_TANA;
	public static Item KITCHEN, KIT_BOARD, KIT_SINK1, KIT_STOVE, KIT_OVEN, KIT_OVEN_B, IRORI, KIT_REIZOU;

	public static Item KIT_KANKI_1, KIT_HAIKIDUCT, KIT_DUCTEND_1;

	public static Item TEATABLE;
	public static Item ENDEN;

	public static Item TOAMI, TOAMI_W, CUT_IKA, COOKED_IKA, IKA;
	public static Item KAIHORI, HAMAGURI, HAMAGURI_COOK;
	public static Item KINE_YOKO, USU_TSUKI;

	//public static Item TEST;
	
	/* アイテムのインスタンスを生成 Instantiate items. */
	public static void init() {

		/* 種, 作物 */
		SEEDSBOX = new SeedsBox_TT("block_seedsbox");
		SEEDS_AZUKI = new Seed_TT("item_seeds_azuki", Crop_Blocks.AZUKI);
		SEEDS_CABBAGE = new Seed_TT("item_seeds_cabbage", Crop_Blocks.CABBAGE);
		SEEDS_HAKUSAI = new Seed_TT("item_seeds_hakusai", Crop_Blocks.HAKUSAI);
		SEEDS_CORN = new SeedCorn_TT("item_seeds_corn");
		SEEDS_GREENONION = new Seed_TT("item_seeds_greenonion", Crop_Blocks.GREENONION);
		SEEDS_ONION = new Seed_TT("item_seeds_onion", Crop_Blocks.ONION);
		SEEDS_RICE = new Seed_TT("item_seeds_rice", Crop_Blocks.RICE);
		SEEDS_SOY = new Seed_TT("item_seeds_soy", Crop_Blocks.SOY);
		SEEDS_SPINACH = new Seed_TT("item_seeds_spinach", Crop_Blocks.SPINACH);
		SEEDS_TOMATO = new Seed_TT("item_seeds_tomato", Crop_Blocks.TOMATO);
		SEEDS_CHERRY = new Seed_TT("item_seeds_cherry", Crop_Blocks.SAKURA);
		CHANOKI = new ChanokiNae_TT("block_wood_chanoki_nae");
		BUDOUNOKI = new GrapeNae_TT("block_wood_grape_nae");
		MIKAN_NAE = new MikanNae_TT("block_wood_mikan");
		SPICE_NAE = new SpiceNae_TT("item_seeds_spice");
		HODAGI_BOT = new HodaGi_TT("block_hodagi_a_bot");

		CHABA = new Chaba_TT("item_chaba");

		INAGI = new Inagi_TT("block_inagi");
		INEWARA = new Item_TTab("item_inewara");
		INE = new Ine_TT("item_ine");
		KOME = new Item_TTab("item_kome");
		SAYA = new Item_TTab("item_saya");
		SOY = new Item_TTab("item_soy");
		SPICE = new Spice_TT("item_spice");
		
		FOOD_CABBAGE = new Food_TTabName("item_food_cabbage", 2, 0.3F, false);
		FOOD_HAKUSAI = new Food_TTabName("item_food_hakusai", 2, 0.3F, false);
		FOOD_CORN = new Food_TTabName("item_food_corn", 1, 0.3F, false);
		FOOD_GRAPE = new Food_TTabName("item_food_grape", 1, 0.3F, false);
		FOOD_GREENONION = new Food_TTabName("item_food_greenonion", 1, 0.3F, false);
		FOOD_ONION = new Food_TTabName("item_food_onion", 1, 0.3F, false);
		FOOD_SPINACH = new Food_TTabName("item_food_spinach", 1, 0.3F, false);
		FOOD_TOMATO = new Food_TTabName("item_food_tomato", 1, 0.3F, false);
		FOOD_CHERRY = new Food_Cherry("item_food_cherry", 1, 0.3F, false);
		FOOD_MIKAN = new Food_TTabName("item_food_mikan", 1, 0.3F, false);
		FOOD_CORN_B = new Food_TTabName("item_food_cornb", 5, 0.6F, false);

		BOX_H_EMPTY = new TabBlock_noFuel("block_boxh_empty", Pantry_Blocks.BOX_H_EMPTY);
		BOX_H_APPLE = new TabBlock_Fuel100("block_boxh_apple", Pantry_Blocks.BOX_H_APPLE);
		BOX_H_BEEF = new TabBlock_Fuel100("block_boxh_beef", Pantry_Blocks.BOX_H_BEEF);
		BOX_H_BEETROOT = new TabBlock_Fuel100("block_boxh_beetroot", Pantry_Blocks.BOX_H_BEETROOT);
		BOX_H_BREAD = new TabBlock_Fuel100("block_boxh_bread", Pantry_Blocks.BOX_H_BREAD);
		BOX_H_CARROT = new TabBlock_Fuel100("block_boxh_carrot", Pantry_Blocks.BOX_H_CARROT);
		BOX_H_CHICKEN = new TabBlock_Fuel100("block_boxh_chicken", Pantry_Blocks.BOX_H_CHICKEN);
		BOX_H_CHORUS = new TabBlock_Fuel100("block_boxh_chorus", Pantry_Blocks.BOX_H_CHORUS);
		BOX_H_COCO = new TabBlock_Fuel100("block_boxh_coco", Pantry_Blocks.BOX_H_COCO);
		BOX_H_EGG = new TabBlock_Fuel100("block_boxh_egg", Pantry_Blocks.BOX_H_EGG);
		BOX_H_FISH = new TabBlock_Fuel100("block_boxh_fish", Pantry_Blocks.BOX_H_FISH);
		BOX_H_FLOUR = new TabBlock_Fuel100("block_boxh_flour", Pantry_Blocks.BOX_H_FLOUR);
		BOX_H_MUTTON = new TabBlock_Fuel100("block_boxh_mutton", Pantry_Blocks.BOX_H_MUTTON);
		BOX_H_PORK = new TabBlock_Fuel100("block_boxh_pork", Pantry_Blocks.BOX_H_PORK);
		BOX_H_POTATO = new TabBlock_Fuel100("block_boxh_potato", Pantry_Blocks.BOX_H_POTATO);
		BOX_H_RABBIT = new TabBlock_Fuel100("block_boxh_rabbit", Pantry_Blocks.BOX_H_RABBIT);
		BOX_H_SALMON = new TabBlock_Fuel100("block_boxh_salmon", Pantry_Blocks.BOX_H_SALMON);

		BOX_H_AZUKI = new TabBlock_Fuel100("block_boxh_azuki", Pantry_Blocks.BOX_H_AZUKI);
		BOX_H_CABBAGE = new TabBlock_Fuel100("block_boxh_cabbage", Pantry_Blocks.BOX_H_CABBAGE);
		BOX_H_HAKUSAI = new TabBlock_Fuel100("block_boxh_hakusai", Pantry_Blocks.BOX_H_HAKUSAI);
		BOX_H_CITRUS = new TabBlock_Fuel100("block_boxh_citrus", Pantry_Blocks.BOX_H_CITRUS);
		BOX_H_CORN = new TabBlock_Fuel100("block_boxh_corn", Pantry_Blocks.BOX_H_CORN);
		BOX_H_GRAPE = new TabBlock_Fuel100("block_boxh_grape", Pantry_Blocks.BOX_H_GRAPE);
		BOX_H_GREENONION = new TabBlock_Fuel100("block_boxh_greenonion", Pantry_Blocks.BOX_H_GREENONION);
		BOX_H_ONION = new TabBlock_Fuel100("block_boxh_onion", Pantry_Blocks.BOX_H_ONION);
		BOX_H_ORIENTCLAM = new TabBlock_Fuel100("block_boxh_hamaguri", Pantry_Blocks.BOX_H_ORIENTCLAM);
		BOX_H_RICE = new TabBlock_Fuel100("block_boxh_rice", Pantry_Blocks.BOX_H_RICE);
		BOX_H_SOY = new TabBlock_Fuel100("block_boxh_soy", Pantry_Blocks.BOX_H_SOY);
		BOX_H_SPINACH = new TabBlock_Fuel100("block_boxh_spinach", Pantry_Blocks.BOX_H_SPINACH);
		BOX_H_SQUID = new TabBlock_Fuel100("block_boxh_squid", Pantry_Blocks.BOX_H_SQUID);
		BOX_H_TOMATO = new TabBlock_Fuel100("block_boxh_tomato", Pantry_Blocks.BOX_H_TOMATO);
		BOX_H_CHERRY = new TabBlock_Fuel100("block_boxh_cherry", Pantry_Blocks.BOX_H_CHERRY);
		BOX_H_TAKENOKO = new TabBlock_Fuel100("block_boxh_takenoko", Pantry_Blocks.BOX_H_TAKENOKO);
		BOX_H_KURI = new TabBlock_Fuel100("block_boxh_chestnut", Pantry_Blocks.BOX_H_KURI);
		BOX_H_TGREEN = new TabBlock_Fuel100("block_boxh_tgreen", Pantry_Blocks.BOX_H_TGREEN);
		BOX_H_TRED = new TabBlock_Fuel100("block_boxh_tred", Pantry_Blocks.BOX_H_TRED);

		BOX_H_BPEPPER = new TabBlock_Fuel100("block_boxh_bpepper", Pantry_Blocks.BOX_H_BPEPPER);
		BOX_H_CUMIN = new TabBlock_Fuel100("block_boxh_cumin", Pantry_Blocks.BOX_H_CUMIN);
		BOX_H_TURMERIC = new TabBlock_Fuel100("block_boxh_turmeric", Pantry_Blocks.BOX_H_TURMERIC);
		BOX_H_CHILI = new TabBlock_Fuel100("block_boxh_chili", Pantry_Blocks.BOX_H_CHILI);
		
		CHADUTSU = new TabBlock_noFuel("block_tea_chadutsu", Pantry_Blocks.CHADUTSU);
		CANTEA = new TabBlock_noFuel("block_tea_can", Pantry_Blocks.CANTEA);
		TAWARA = new TabBlock_Fuel200("block_tawara_cm", Pantry_Blocks.TAWARA);

		/* 発酵 */
		KOUBOBOT_full = new BinKouboFull_TT("block_bin_koubo_f");
		NYUSANBOT_full = new BinNyusanFull_TT("block_bin_nyusan_f");
		KOUBO = new BinKoubo_TT("item_koubo");
		NYUSAN = new BinNyusan_TT("item_nyusan");

		MIZUOKE = new MizuokeKara_TT("block_mizuoke", Blocks.AIR);
		MIZUOKE_full = new MizuokeFull_TT("block_mizuoke_full");
		MIZUOKE_Milk = new MizuokeMilk_NT("item_mizuoke_milk").setCreativeTab(ChinjufuModTabs.TEATIME);

		HAKKOUTARU = new TaruItem_TT("block_taru_hakkou");
		RINGOSHU_TARU = new TabBlock_noFuel("block_taru_ringoshu_f", Hakkou_Blocks.RINGOSHU_TARU);
		BUDOUSHU_TARU = new TabBlock_noFuel("block_taru_budoushu_f", Hakkou_Blocks.BUDOUSHU_TARU);
		HACHIMITSUSHU_TARU = new TabBlock_noFuel("block_taru_hachimitsushu_f", Hakkou_Blocks.HACHIMITSUSHU_TARU);

		NAMASAKEBOT = new TabBlock_noFuel("block_bot_sakenama_1", Hakkou_Blocks.NAMASAKEBOT);
		SAKEBOT = new TabBlock_noFuel("block_bot_sake_1", Hakkou_Blocks.SAKEBOT);
		JUKUSAKEBOT = new TabBlock_noFuel("block_bot_sakejuku_1", Hakkou_Blocks.JUKUSAKEBOT);
		NABEAMAZAKE_nama = new NabeAmazakeNama_TT("block_food_nabeaz_n");
		NABEAMAZAKE = new NabeCooked_TT("block_food_nabeaz_1", Hakkou_Blocks.NABEAMAZAKE);
		CIDERBOT = new TabBlock_noFuel("block_bot_cider_1", Hakkou_Blocks.CIDERBOT);
		JUKUCIDERBOT = new TabBlock_noFuel("block_bot_ciderjuku_1", Hakkou_Blocks.JUKUCIDERBOT);
		WINEBOT = new TabBlock_noFuel("block_bot_wine_1", Hakkou_Blocks.WINEBOT);
		JUKUWINEBOT = new TabBlock_noFuel("block_bot_winejuku_1", Hakkou_Blocks.JUKUWINEBOT);
		MEADBOT = new TabBlock_noFuel("block_bot_mead_1", Hakkou_Blocks.MEADBOT);
		JUKUMEADBOT = new TabBlock_noFuel("block_bot_meadjuku_1", Hakkou_Blocks.JUKUMEADBOT);

		SAKEGLASS = new GlassSake_TT("block_glass_sake");
		WINEGLASS = new GlassWine_TT("block_glass_wine");
		WINE_TANA = new TabBlock_Fuel300("block_kit2_tana", Kitchen_Blocks.WINE_TANA);
		HAKUSAI_TARU = new TaruHakusai_TT("block_taru_hakusai_f");
		SHOUYU_TARU = new TaruShouyu_TT("block_taru_hakkou_2");

		SHOUYU_bot_14 = new Shouyu1_NT("block_shouyu_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		KOMEZU_bot_12 = new Komezu1_NT("block_komezu_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		SOYOIL_bot_12 = new Soyoil1_NT("block_soyoil_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		DASHI_bot_14 = new Dashi1_NT("block_dashi_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		OSAUCE_bot_14 = new OSauce1_NT("block_osauce_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		MAYO_bot_14 = new Mayo1_NT("block_mayo_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		VANILLA_bot_14 = new Vanilla1_NT("block_vanilla_bot").setCreativeTab(ChinjufuModTabs.TEATIME);
		
		/* 食器 */
		Item_MATCH = new Match_TT("item_match_cm");
		Item_SARA = new Item_TTab("item_food_sara");
		Item_DISH = new Dishes_TT("item_food_dish");
		CLAY_DISH = new DishesClay_TT("item_clay_dish");

		/* 中間素材 */
		SHIO = new Shio_TT("item_salt");
		MUSHIGOME = new Mushigome_TT("item_mushigome");
		KANTEN_BOWL = new KantenBowl_TT("item_bowl_kanten");
		
		KOMEKOUJI = new Item_TTab("item_komekouji");
		SHUBO = new Shubo_TT("item_shubo");
		MORO = new Moromi_TT("item_moromi");
		SAKEKASU = new Sakekasu_TT("item_sakekasu");

		NIMAME = new Nimame_TT("item_nimame");
		MISO = new Item_TTab("item_miso");
		
		KOMUGI = new Komugi_TT("item_komugi");
		PASTA = new PastaNama_TT("item_pasta");
		PAN_KIJI = new PanKiji_TT("item_pan_kiji");
		
		KEIRYO_CUP = new MeasureCup_TT("block_measurecup", Blocks.AIR);
		KEIRYO_CUP_full = new MeasureCupFull_TT("item_measurecup_full");
		KANSUI = new KansuiCup_TT("item_kansui");
		RAMEN_nama = new RamenNama_TT("item_ramen_n");
		
		/* 調理済み 寸胴 -> 鍋 -> フライパン */
		ZUNDOU = new Zundou_TT("block_food_zundou");
		ZUNDOU_RCURRY = new ZundouNama_TT("block_food_cunabe_n");
		ZUNDOU_MIZU = new ZundouMizu_TT("block_zundou_mizu");
		ZUNDOU_MILK = new ZundouMilk_TT("block_zundou_milk");
		ZUNDOUSHIO = new ZundouShioMizu_TT("block_zundou_shiomizu");
		ZUNDOU_CURRY = new CurryNabe_TT("block_food_cunabe_1");
		ZUNDOU_STEW = new StewNabe_TT("block_food_stewnabe_1");
		DASHINABE = new ZundouDashi_TT("block_food_dashinabe_1");
		ZUNDOU_RSOUP = new ZundouRsoup_TT("block_food_rsoup_n");

		NABE_kara = new NabeKara_TT("block_food_karanabe", Blocks.AIR);
		NABE_NAMA_1 = new NabeNama1_TT("block_food_nabenama_1");
		NABESHIO_nama = new NabeKaisui_TT("block_food_nabeshio_n");
		NABE_nama_SNTA = new NabeNama2_TT("block_food_nabenama_2");
		NABE_nama_TK = new NabeNamaGohanTK_TT("block_food_nabegohantake_n");
		NABE_NAMA_AZUKI = new NabeNamaAzuki_TT("block_food_nabeazuki_n");
		NABE_NAMA_PUDDING = new NabeNamaPudding_TT("block_food_nabepudding_n");
		
		NABETORI = new NabeCooked_TT("block_food_nabe_1", Dish_Blocks.NABETORI);
		NABEMISO = new NabeCooked_TT("block_food_nabemiso_1", Dish_Blocks.NABEMISO);
		NABEZENZAI_M = new NabeCooked_TT("block_food_nabezenzai_m", Dish_Blocks.NABEZENZAI_M);
		NABEZENZAI_K = new NabeCooked_TT("block_food_nabezenzai_k", Dish_Blocks.NABEZENZAI_K);
		
		NABEGOHAN = new NabeCooked_TT("block_food_nabegohan_1", Dish_Blocks.NABEGOHAN);
		NABEGOHAN_TAKE = new NabeCooked_TT("block_food_nabegohantake_1", Dish_Blocks.NABEGOHAN_TAKE);
		NABEGOHAN_KURI = new NabeCooked_TT("block_food_nabegohankuri_1", Dish_Blocks.NABEGOHAN_KURI);
		NABESEKIHAN = new NabeCooked_TT("block_food_nabesekihan_1", Dish_Blocks.NABESEKIHAN);
		NABECORNSOUP = new NabeCooked_TT("block_food_nabecorns_1", Dish_Blocks.NABECORNSOUP);
		
		FRYPAN_kara = new FryPanKara_TT("block_food_frypan");
		FRYPAN_NAMA_1 = new FrypanNama1_TT("block_food_frypan_n1");
		FRYPAN_NAMA_3 = new FrypanNama3_TT("block_food_frypan_n3");
		FRYPAN_NAMA_2 = new FrypanNama2_TT("block_food_frypan_n2");
		FPKINOKOAK_nama = new FryPan_KinokoAma_TT("block_food_frypan_n_kinokoak");
		FRYPAN_BAKE_1 = new FrypanBake1_NT("block_food_frypan_b1").setCreativeTab(ChinjufuModTabs.TEATIME);
		FRYPAN_BAKE_2 = new FrypanBake2_NT("block_food_frypan_b2").setCreativeTab(ChinjufuModTabs.TEATIME);
		FPKINOKOAK = new KinokoAmakara_TT("item_food_frypan_b_kinokoak");
		FRYPAN_NAMA_4 = new Teppan_TT("block_food_frypan_n4");
		
		NIBOSHI = new FishBoiled_TT("block_niboshi");
		CURRY = new Dish_Curry("block_food_curry_1", 10, 1.0F, false);
		CURRYSET = new CurrySet_TT("block_food_curryset_1");
		STEW = new Dish_SoupStew("block_food_stew_1", 8, 0.6F, false);

		UDON_SU = new Dish_Udon("block_food_udonsu_1", 5, 0.6F, false);
		UDON_NIKU = new Dish_Udon("block_food_udonniku_1", 10, 0.8F, false);
		UDON_TSUKIMI = new Dish_Udon("block_food_udontsukimi_1", 10, 0.8F, false);
		RAMEN = new Dish_Ramen("block_food_ramen", 10, 1.0F, false);
		
		TONSUITORI = new Dish_Tonsui("block_food_tonsui_1", 2, 0.5F, false);
		MISOSOUP = new Dish_Shikki("block_food_misosp_1", 2, 0.3F, false);
		ZENZAI_M = new Dish_Shikki("block_food_zenzai_m", 3, 0.3F, false);
		ZENZAI_K = new Dish_Shikki("block_food_zenzai_k", 3, 0.3F, false);
		
		GOHAN = new Dish_Chawan("block_food_gohan_1", 3, 0.6F, false);
		GOHAN_TAKE = new Dish_Chawan("block_food_gohantake_1", 3, 0.6F, false);
		GOHAN_KURI = new Dish_Chawan("block_food_gohankuri_1", 3, 0.6F, false);
		SEKIHAN = new Dish_Chawan("block_food_sekihan", 3, 0.6F, false);
		RICE = new Dish_PlateStage3("block_food_rice_1", 3, 0.6F, false);
		
		DONBURI_MESHI = new Dish_Donburi("block_food_donmeshi_1", 5, 0.6F, false);
		DONBURI_GYU = new Dish_Donburi("block_food_dongyu_1", 10, 0.8F, false);
		DONBURI_OYAKO = new Dish_Donburi("block_food_donoyako_1", 10, 0.8F, false);
		DONBURI_KATSU = new Dish_Donburi("block_food_donkatsu_1", 10, 1.0F, false);
		DONBURI_KAISEN = new Dish_Donburi("block_food_donkaisen_1", 10, 0.8F, false);
		
		FOOD_HAKUSAI2 = new Food_TTabName("item_food_hakusai2", 2, 0.6F, false);
		HAKUSAIDUKE = new Dish_PlateStage3("block_food_hsd_1", 1, 0.2F, false);

		TAMAGOYAKI = new Dish_Tamagoyaki("block_food_tgy_1", 3, 0.5F, false);
		TAMAGOYAKITEI = new Face4_1Wood_TT("block_food_tgytei_1", Dish_Blocks.TAMAGOYAKITEI);
		YAKIZAKANATEI = new Face4_1Wood_TT("block_food_yakizakanatei_1", Dish_Blocks.YAKIZAKANATEI);
		YAKIJYAKETEI = new Face4_1Wood_TT("block_food_yakijyaketei_1", Dish_Blocks.YAKIJYAKETEI);
		TAMAGOYAKITEI_TAKE = new Face4_1Wood_TT("block_food_tgyteitake_1", Dish_Blocks.TAMAGOYAKITEI_TAKE);
		YAKIZAKANATEI_TAKE = new Face4_1Wood_TT("block_food_yakizakanateitake_1", Dish_Blocks.YAKIZAKANATEI_TAKE);
		YAKIJYAKETEI_TAKE = new Face4_1Wood_TT("block_food_yakijyaketeitake_1", Dish_Blocks.YAKIJYAKETEI_TAKE);
		TAMAGOYAKITEI_KURI = new Face4_1Wood_TT("block_food_tgyteikuri_1", Dish_Blocks.TAMAGOYAKITEI_KURI);
		YAKIZAKANATEI_KURI = new Face4_1Wood_TT("block_food_yakizakanateikuri_1",Dish_Blocks.YAKIZAKANATEI_KURI);
		YAKIJYAKETEI_KURI = new Face4_1Wood_TT("block_food_yakijyaketeikuri_1", Dish_Blocks.YAKIJYAKETEI_KURI);
		TAMAGOYAKITEI_SEKI = new Face4_1Wood_TT("block_food_tgytei_sekihan_1", Dish_Blocks.TAMAGOYAKITEI_SEKI);
		YAKIZAKANATEI_SEKI = new Face4_1Wood_TT("block_food_yakizakanatei_sekihan_1",Dish_Blocks.YAKIZAKANATEI_SEKI);
		YAKIJYAKETEI_SEKI = new Face4_1Wood_TT("block_food_yakijyaketei_sekihan_1", Dish_Blocks.YAKIJYAKETEI_SEKI);
		
		CORNSOUP = new Dish_SoupStew("block_food_cornsp_1", 2, 0.3F, false);
		EGGBURG = new Dish_PlateStage3("block_food_egb_1", 5, 0.6F, false);
		EGGBURGSET = new Face4_1Stone_TT("block_food_egbset_1", Dish_Blocks.EGGBURGSET);

		PASTATOMATO = new Dish_Pasta("block_food_pastatoma_1", 10, 0.8F, false);
		PASTACHEESE = new Dish_Pasta("block_food_pastacheese_1", 10, 0.8F, false);
		PASTAKINOKO = new Dish_Pasta("block_food_pastakinoko_1", 10, 0.8F, false);
		PASTASEAFOOD = new Dish_Pasta("block_food_pastaseafood_1", 10, 1.0F, false);
		
		PIZZA = new PizzaCooked_TT("block_food_pizza_1");
		PC_PIZZA = new Food_Pizza("item_food_pizza", 6, 0.6F, false);
		OKONOMIYAKI = new Dish_Okonomiyaki("block_food_okonomiyaki_1", 8, 1.0F, false);
		CHICKEN = new Face4_1Stone_TT("block_food_roastchicken_1", Dish_Blocks.CHICKEN);
		CHICKEN_small = new Dish_PlateStage3("block_food_chickenb_1", 3, 0.5F, false);

		SUSHIMESHI = new SushiMeshi_TT("block_food_sushimeshi");
		SUSHIGETA_kara = new SushiGetaKara_TT("block_food_sushigeta_kara");
		SUSHISET_salmon = new Face4_1Wood_TT("block_food_sushiset_salmon", Dish_Blocks.SUSHISET_salmon);
		SUSHISET_fish = new Face4_1Wood_TT("block_food_sushiset_fish", Dish_Blocks.SUSHISET_fish);
		SUSHISET_beef = new Face4_1Wood_TT("block_food_sushiset_beef", Dish_Blocks.SUSHISET_beef);
		SUSHISET_tamago = new Face4_1Wood_TT("block_food_sushiset_tamago", Dish_Blocks.SUSHISET_tamago);
		SUSHISET_4shoku = new Face4_1Wood_TT("block_food_sushiset_4shoku", Dish_Blocks.SUSHISET_4shoku);
		SUSHIOKE = new SushiOkeKara_TT("block_food_sushioke_kara");
		SUSHIOKE_FULL_1 = new SushiOkeFull_TT("block_food_sushiokefull_1");
		SHOUYUSARA = new ShouyuSara_TT("block_food_shouyusara_1");

		Item_YAKAN_kara = new KettleKara_TT("item_kettle_kara");
		KETTLE_full = new KettleFull_TT("block_kettle_full");
		Item_YAKAN_boil = new KettleBoil_TT("item_kettle_boil");
		KYUSU_kara = new KyusuKara_TT("block_food_kyusu");
		KYUSU = new Face4_1Stone_TT("block_food_kyusu_1", Dish_Blocks.KYUSU);
		JPTEACUP = new Yunomi_TT("block_food_jpteacup_1"); //drink
		JPTEASET = new Face4_1Stone_TT("block_food_jpteaset_1", Dish_Blocks.JPTEASET);
		JPCHAUKE = new JPChaUke_TT("block_food_jpchauke");

		TEAPOT_kara = new TeaPotKara_TT("block_food_teapot");
		TEAPOT = new Face4_1Stone_TT("block_food_teapot_1", Dish_Blocks.TEAPOT);
		TEACUP = new TeaCup_Item("block_food_teacup_1"); //drink
		TEASET = new Face4_1Stone_TT("block_food_teaset_1", Dish_Blocks.TEASET);
		SCONESET_kara = new TeaStand_TT("block_food_teastand");
		SCONESET_1 = new SconeSet1_TT("block_food_sconeset_1");

		ICECREAM = new Dish_LUCK("block_food_icecream_1", 0, 0.0F, false);
		KANTEN = new Dish_Kanten("block_food_kanten", 0, 0.0F, false);
		
		CHEESE_CURD = new CheeseCurd_TT("block_food_cheesecurd");
		CHEESE = new CheeseBlock_TT("block_food_cheese_1");

		/*バニラの食料
		registerItem(260, "apple", (new ItemFood(4, 0.3F, false)).setUnlocalizedName("apple"));
		registerItem(391, "carrot", (new ItemSeedFood(3, 0.6F, Blocks.CARROTS, Blocks.FARMLAND)).setUnlocalizedName("carrots"));

		registerItem(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.POTATOES, Blocks.FARMLAND)).setUnlocalizedName("potato"));
		registerItem(393, "baked_potato", (new ItemFood(5, 0.6F, false)).setUnlocalizedName("potatoBaked"));

		registerItem(297, "bread", (new ItemFood(5, 0.6F, false)).setUnlocalizedName("bread"));
		5=肉メモリ2.5個分
		registerItem(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).setUnlocalizedName("beefCooked"));
		registerItem(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).setUnlocalizedName("chickenCooked"));*/

		FRESH_CHEESE = new Food_TTabName("item_food_cheesef", 1, 0.3F, false);
		PIECE_CHEESE = new Food_Cheese("item_food_cheese", 1, 0.3F, false);
		CHEESE_TANA = new TabBlock_Fuel150("block_kit_cheese_tana", Kitchen_Blocks.CHEESE_TANA);

		CAKE = new Food_TTabName("item_food_cake", 5, 0.4F, false);
		BUN = new Food_TTabName("item_food_bun", 6, 0.4F, false);
		SCONE = new Food_TTabName("item_food_scone", 4, 0.3F, false);
		SENBEI = new Senbei("item_food_senbei", 4, 0.3F, false);
		TOUFU = new Food_TTabName("item_food_toufu", 4, 0.3F, false);

		CHICKENSAND = new Food_TTabName("item_food_chickensand", 6, 0.6F, false);
		EGGSAND = new Food_TTabName("item_food_eggsand", 6, 0.6F, false);
		KIRIMI = new Kirimi("item_food_kirimi", 1, 0.1F, false);
		SUSHI = new Sushi("item_food_sushi", 4, 0.6F, false);
		SHOUYUSUSHI = new Sushi_Shouyu("item_food_sushishouyu", 5, 0.8F, false);

		NORIAMI = new TabBlock_noFuel("block_noriami", Crop_Blocks.NORIAMI);
		NORI_N = new Item_TTab("item_food_norinama");
		NORI_I = new Food_TTabName("item_food_noriita", 1, 0.1F, false);
		ONIGIRI = new Onigiri("item_food_onigiri", 4, 0.6F, false);

		KUSHI_SAKANA = new Item_TTab("item_kushi_sakana");
		KUSHI_SAKANA_C = new KushiSakana("item_kushi_sakana_c", 5, 0.8F, false);
		BENTOU = new Bentou("item_bentou", 10, 0.8F, false);
		MOCHI = new Item_TTab("item_mochi");
		MOCHI_FOOD = new Food_Mochi("item_food_mochi", 4, 0.6F, false);
		PAN_FOOD = new Food_Pan("item_food_pan", 4, 0.6F, false);
		
		KIT_TANA = new TabBlock_Fuel150("block_kit_tana", Kitchen_Blocks.KIT_TANA);
		KITCHEN = new TabBlock_Fuel150("block_kitchen", Kitchen_Blocks.KITCHEN);

		KIT_BOARD = new TabBlock_Fuel300("block_kit_board", Kitchen_Blocks.KIT_BOARD);
		KIT_SINK1 = new KitSink_TT("block_kit_sink1");

		KIT_STOVE = new TabBlock_noFuel("block_kit_stove", Kitchen_Blocks.KIT_STOVE);
		KIT_OVEN = new TabBlock_noFuel("block_kit_oven", Kitchen_Blocks.KIT_OVEN);
		KIT_OVEN_B = new TabBlock_noFuel("block_kit_oven_black", Kitchen_Blocks.KIT_OVEN_B);
		IRORI = new TabBlock_noFuel("block_irori", Kitchen_Blocks.IRORI);
		KIT_REIZOU = new TabBlock_noFuel("block_kit_reizou", Kitchen_Blocks.KIT_REIZOU);

		KIT_KANKI_1 = new TabBlock_noFuel("block_kit_kanki", Kitchen_Blocks.KIT_KANKI_1);
		KIT_HAIKIDUCT = new TabBlock_noFuel("block_kit_duct", Kitchen_Blocks.KIT_HAIKIDUCT);
		KIT_DUCTEND_1 = new TabBlock_noFuel("block_kit_ductend", Kitchen_Blocks.KIT_DUCTEND_1);

		TEATABLE = new TeaTable_TT("block_teatable");
		ENDEN = new Enden_TT("block_enden");
		
		TOAMI = new Toami_TT("item_toami");
		TOAMI_W = new ToamiWide_TB("item_toami_wide", Crop_Blocks.TOAMI_W);
		CUT_IKA = new Food_TTabName("item_squid_cut", 2, 0.1F, false);
		COOKED_IKA = new Food_TTabName("item_squid_cooked", 6, 0.8F, false);
		IKA = new IkaRaw_TT("item_squid_raw");

		KAIHORI = new Kaihori_TT("item_kaihori");
		HAMAGURI = new Hamaguri_TB("block_hamaguri", Crop_Blocks.HAMAGURI);
		HAMAGURI_COOK = new Food_Hamaguri("item_food_hamaguri", 2, 0.3F, false);
		
		KINE_YOKO = new KineTsuki_TT("item_kineyoko");
		USU_TSUKI = new TabBlock_Fuel300("block_usutsuki", Kitchen_Blocks.USU_TSUKI);
		
		//TEST = new TestCrop_Item();
	}

	/* アイテムを登録する, ここから Register Items. From here. ↓*/
	public static void register() {

		registerItem(SEEDSBOX);
		registerItem(SEEDS_AZUKI);
		registerItem(SEEDS_CABBAGE);
		registerItem(SEEDS_HAKUSAI);
		registerItem(SEEDS_CORN);
		registerItem(SEEDS_GREENONION);
		registerItem(SEEDS_ONION);
		registerItem(SEEDS_RICE);
		registerItem(SEEDS_SOY);
		registerItem(SEEDS_SPINACH);
		registerItem(SEEDS_TOMATO);
		registerItem(SEEDS_CHERRY);
		registerItem(CHANOKI);
		registerItem(BUDOUNOKI);
		registerItem(MIKAN_NAE);
		registerItem(SPICE_NAE);
		registerItem(HODAGI_BOT);

		registerItem(CHABA);
		registerItem(INAGI);
		registerItem(INEWARA);
		registerItem(INE);
		registerItem(KOME);
		registerItem(SAYA);
		registerItem(SOY);
		registerItem(SPICE);
		
		registerItem(FOOD_CABBAGE);
		registerItem(FOOD_HAKUSAI);
		registerItem(FOOD_CORN);
		registerItem(FOOD_GREENONION);
		registerItem(FOOD_ONION);
		registerItem(FOOD_SPINACH);
		registerItem(FOOD_TOMATO);
		registerItem(FOOD_GRAPE);
		registerItem(FOOD_CHERRY);
		registerItem(FOOD_MIKAN);
		registerItem(FOOD_CORN_B);

		registerItem(BOX_H_EMPTY);
		registerItem(BOX_H_APPLE);
		registerItem(BOX_H_BEEF);
		registerItem(BOX_H_BEETROOT);
		registerItem(BOX_H_BREAD);
		registerItem(BOX_H_CARROT);
		registerItem(BOX_H_CHICKEN);
		registerItem(BOX_H_CHORUS);
		registerItem(BOX_H_COCO);
		registerItem(BOX_H_EGG);
		registerItem(BOX_H_FISH);
		registerItem(BOX_H_FLOUR);
		registerItem(BOX_H_MUTTON);
		registerItem(BOX_H_PORK);
		registerItem(BOX_H_POTATO);
		registerItem(BOX_H_RABBIT);
		registerItem(BOX_H_SALMON);

		registerItem(BOX_H_AZUKI);
		registerItem(BOX_H_CABBAGE);
		registerItem(BOX_H_HAKUSAI);
		registerItem(BOX_H_CITRUS);
		registerItem(BOX_H_CORN);
		registerItem(BOX_H_GRAPE);
		registerItem(BOX_H_GREENONION);
		registerItem(BOX_H_ONION);
		registerItem(BOX_H_ORIENTCLAM);
		registerItem(BOX_H_RICE);
		registerItem(BOX_H_SOY);
		registerItem(BOX_H_SPINACH);
		registerItem(BOX_H_SQUID);
		registerItem(BOX_H_TOMATO);
		registerItem(BOX_H_CHERRY);		
		registerItem(BOX_H_TAKENOKO);	
		registerItem(BOX_H_KURI);	
		registerItem(BOX_H_TGREEN);
		registerItem(BOX_H_TRED);

		registerItem(BOX_H_BPEPPER);
		registerItem(BOX_H_CUMIN);
		registerItem(BOX_H_TURMERIC);
		registerItem(BOX_H_CHILI);
		
		registerItem(CHADUTSU);
		registerItem(CANTEA);
		registerItem(TAWARA);

		registerItem(KOUBOBOT_full);
		registerItem(NYUSANBOT_full);
		registerItem(KOUBO);
		registerItem(NYUSAN);

		registerItem(MIZUOKE);
		registerItem(MIZUOKE_full);
		registerItem(MIZUOKE_Milk);

		registerItem(HAKKOUTARU);
		registerItem(RINGOSHU_TARU);
		registerItem(BUDOUSHU_TARU);
		registerItem(HACHIMITSUSHU_TARU);

		registerItem(NAMASAKEBOT);
		registerItem(SAKEBOT);
		registerItem(JUKUSAKEBOT);
		registerItem(NABEAMAZAKE_nama);
		registerItem(NABEAMAZAKE);
		registerItem(CIDERBOT);
		registerItem(JUKUCIDERBOT);
		registerItem(WINEBOT);
		registerItem(JUKUWINEBOT);
		registerItem(MEADBOT);
		registerItem(JUKUMEADBOT);

		registerItem(SAKEGLASS);
		registerItem(WINEGLASS);
		registerItem(WINE_TANA);

		registerItem(HAKUSAI_TARU);
		registerItem(SHOUYU_TARU);

		registerItem(SHOUYU_bot_14);
		registerItem(KOMEZU_bot_12);
		registerItem(SOYOIL_bot_12);
		registerItem(DASHI_bot_14);
		registerItem(OSAUCE_bot_14);
		registerItem(MAYO_bot_14);
		registerItem(VANILLA_bot_14);
		
		registerItem(Item_MATCH);
		registerItem(Item_SARA);
		registerItem(Item_DISH);
		registerItem(CLAY_DISH);

		registerItem(SHIO);

		registerItem(MUSHIGOME);
		registerItem(KANTEN_BOWL);
		registerItem(KOMEKOUJI);
		registerItem(SHUBO);
		registerItem(MORO);
		registerItem(SAKEKASU);

		registerItem(NIMAME);
		registerItem(MISO);

		registerItem(KOMUGI);
		registerItem(PASTA);
		registerItem(PAN_KIJI);
		registerItem(KEIRYO_CUP);
		registerItem(KEIRYO_CUP_full);
		registerItem(KANSUI);
		registerItem(RAMEN_nama);
		
		registerItem(ZUNDOU);
		registerItem(ZUNDOU_RCURRY);
		registerItem(ZUNDOU_MIZU);
		registerItem(ZUNDOU_MILK);
		registerItem(ZUNDOUSHIO);
		registerItem(ZUNDOU_CURRY);
		registerItem(ZUNDOU_STEW);
		registerItem(DASHINABE);
		registerItem(ZUNDOU_RSOUP);

		registerItem(NABE_kara);
		registerItem(NABE_NAMA_1);
		registerItem(NABESHIO_nama);
		registerItem(NABE_nama_SNTA);
		registerItem(NABE_nama_TK);
		registerItem(NABE_NAMA_AZUKI);
		registerItem(NABE_NAMA_PUDDING);
		registerItem(NABETORI);
		registerItem(NABEMISO);
		registerItem(NABEZENZAI_M);
		registerItem(NABEZENZAI_K);
		registerItem(NABEGOHAN);
		registerItem(NABEGOHAN_TAKE);
		registerItem(NABEGOHAN_KURI);
		registerItem(NABESEKIHAN);
		registerItem(NABECORNSOUP);
		
		registerItem(FRYPAN_kara);
		registerItem(FRYPAN_NAMA_1);
		registerItem(FRYPAN_NAMA_3);
		registerItem(FRYPAN_NAMA_2);
		registerItem(FPKINOKOAK_nama);
		registerItem(FRYPAN_NAMA_4);
		registerItem(FRYPAN_BAKE_1);
		registerItem(FRYPAN_BAKE_2);
		registerItem(FPKINOKOAK);

		registerItem(NIBOSHI);
		registerItem(CURRY);
		registerItem(CURRYSET);
		registerItem(STEW);

		registerItem(UDON_SU);
		registerItem(UDON_NIKU);
		registerItem(UDON_TSUKIMI);
		registerItem(RAMEN);
		
		registerItem(TONSUITORI);
		registerItem(MISOSOUP);
		registerItem(ZENZAI_M);
		registerItem(ZENZAI_K);
		registerItem(GOHAN);
		registerItem(GOHAN_TAKE);
		registerItem(GOHAN_KURI);
		registerItem(SEKIHAN);
		registerItem(RICE);

		registerItem(DONBURI_MESHI);
		registerItem(DONBURI_GYU);
		registerItem(DONBURI_OYAKO);
		registerItem(DONBURI_KATSU);
		registerItem(DONBURI_KAISEN);
		
		registerItem(FOOD_HAKUSAI2);
		registerItem(HAKUSAIDUKE);

		registerItem(TAMAGOYAKI);
		registerItem(TAMAGOYAKITEI);
		registerItem(YAKIZAKANATEI);
		registerItem(YAKIJYAKETEI);
		registerItem(TAMAGOYAKITEI_TAKE);
		registerItem(YAKIZAKANATEI_TAKE);
		registerItem(YAKIJYAKETEI_TAKE);
		registerItem(TAMAGOYAKITEI_KURI);
		registerItem(YAKIZAKANATEI_KURI);
		registerItem(YAKIJYAKETEI_KURI);
		registerItem(TAMAGOYAKITEI_SEKI);
		registerItem(YAKIZAKANATEI_SEKI);
		registerItem(YAKIJYAKETEI_SEKI);
		
		registerItem(CORNSOUP);
		registerItem(EGGBURG);
		registerItem(EGGBURGSET);

		registerItem(PASTATOMATO);
		registerItem(PASTACHEESE);
		registerItem(PASTAKINOKO);
		registerItem(PASTASEAFOOD);
		
		registerItem(PIZZA);
		registerItem(PC_PIZZA);
		registerItem(OKONOMIYAKI);
		registerItem(CHICKEN);
		registerItem(CHICKEN_small);

		registerItem(SUSHIMESHI);
		registerItem(SUSHIGETA_kara);
		registerItem(SUSHISET_salmon);
		registerItem(SUSHISET_fish);
		registerItem(SUSHISET_beef);
		registerItem(SUSHISET_tamago);
		registerItem(SUSHISET_4shoku);
		registerItem(SUSHIOKE);
		registerItem(SUSHIOKE_FULL_1);
		registerItem(SHOUYUSARA);

		registerItem(Item_YAKAN_kara);
		registerItem(KETTLE_full);
		registerItem(Item_YAKAN_boil);
		registerItem(KYUSU_kara);
		registerItem(KYUSU);
		registerItem(JPTEACUP);
		registerItem(JPTEASET);
		registerItem(JPCHAUKE);

		registerItem(TEAPOT_kara);
		registerItem(TEAPOT);
		registerItem(TEACUP);
		registerItem(TEASET);
		registerItem(SCONESET_kara);
		registerItem(SCONESET_1);

		registerItem(ICECREAM);
		registerItem(KANTEN);
		
		registerItem(CHEESE_CURD);
		registerItem(CHEESE);
		registerItem(FRESH_CHEESE);
		registerItem(PIECE_CHEESE);
		registerItem(CHEESE_TANA);

		registerItem(CAKE);
		registerItem(BUN);
		registerItem(SCONE);
		registerItem(SENBEI);
		registerItem(TOUFU);
		registerItem(CHICKENSAND);
		registerItem(EGGSAND);
		registerItem(KIRIMI);
		registerItem(SUSHI);
		registerItem(SHOUYUSUSHI);

		registerItem(NORIAMI);
		registerItem(NORI_N);
		registerItem(NORI_I);
		registerItem(ONIGIRI);

		registerItem(KUSHI_SAKANA);
		registerItem(KUSHI_SAKANA_C);
		registerItem(BENTOU);
		registerItem(MOCHI);
		registerItem(MOCHI_FOOD);
		registerItem(PAN_FOOD);
		
		registerItem(KIT_TANA);
		registerItem(KITCHEN);
		registerItem(KIT_BOARD);
		registerItem(KIT_SINK1);
		registerItem(KIT_STOVE);
		registerItem(KIT_OVEN);
		registerItem(KIT_OVEN_B);
		registerItem(IRORI);
		registerItem(KIT_REIZOU);

		registerItem(KIT_KANKI_1);
		registerItem(KIT_HAIKIDUCT);
		registerItem(KIT_DUCTEND_1);

		registerItem(TEATABLE);
		registerItem(ENDEN);
		
		registerItem(TOAMI);
		registerItem(TOAMI_W);
		registerItem(CUT_IKA);
		registerItem(COOKED_IKA);
		registerItem(IKA);
		
		registerItem(KAIHORI);
		registerItem(HAMAGURI);
		registerItem(HAMAGURI_COOK);
		registerItem(KINE_YOKO);
		registerItem(USU_TSUKI);

		//registerItem(TEST);
		/** registerItem の順番に沿って, registerRenders が表示される **/
	}

	public static void registerItem(Item item) {
		RegisterHandler_CM.Items.ITEMS.add(item);
	}
	/*ここまで So far↑ */


	/* ドロップ時やインベントリにおける, アイテムの描画を登録。ここから↓
	* Register rendering of Items in drop and inventory. From here↓*/
	public static void registerRenders() {
		registerRender(SEEDSBOX);
		registerRender(SEEDS_AZUKI);
		registerRender(SEEDS_CABBAGE);
		registerRender(SEEDS_HAKUSAI);
		registerRender(SEEDS_CORN);
		registerRender(SEEDS_GREENONION);
		registerRender(SEEDS_ONION);
		registerRender(SEEDS_RICE);
		registerRender(SEEDS_SOY);
		registerRender(SEEDS_SPINACH);
		registerRender(SEEDS_TOMATO);
		registerRender(SEEDS_CHERRY);
		registerRender(CHANOKI);
		registerRender(BUDOUNOKI);
		registerRender(MIKAN_NAE);
		registerRenderMeta(SPICE_NAE, 0, "item_seeds_pepper");
		registerRenderMeta(SPICE_NAE, 1, "item_seeds_cumin");
		registerRenderMeta(SPICE_NAE, 2, "item_seeds_turmeric");
		registerRenderMeta(SPICE_NAE, 3, "item_seeds_chilipepper");
		registerRenderMeta(SPICE_NAE, 4, "item_seeds_vanilla");
		registerRender(HODAGI_BOT);

		registerRenderMeta(CHABA, 0, "item_chaba");
		registerRenderMeta(CHABA, 1, "item_chaba_green");
		registerRenderMeta(CHABA, 2, "item_chaba_red");
		registerRenderMeta(CHABA, 3, "item_chaba_tencha");
		registerRenderMeta(CHABA, 4, "item_chaba_matcha");
		registerRender(INAGI);
		registerRender(INEWARA);
		registerRenderMeta(INE, 0, "item_ine");
		registerRenderMeta(INE, 1, "item_ine_dry");
		registerRender(KOME);
		registerRender(SAYA);
		registerRender(SOY);
		registerRenderMeta(SPICE, 0, "item_crop_pepper");
		registerRenderMeta(SPICE, 1, "item_crop_pepperdry");
		registerRenderMeta(SPICE, 2, "item_crop_chilipepper");
		registerRenderMeta(SPICE, 3, "item_dust_blackpepper");
		registerRenderMeta(SPICE, 4, "item_dust_cumin");
		registerRenderMeta(SPICE, 5, "item_dust_turmeric");
		registerRenderMeta(SPICE, 6, "item_dust_chili");
		registerRenderMeta(SPICE, 7, "item_curry_roux");
		registerRenderMeta(SPICE, 8, "item_vanillabeans");

		registerRender(FOOD_CABBAGE);
		registerRender(FOOD_HAKUSAI);
		registerRender(FOOD_CORN);
		registerRender(FOOD_GREENONION);
		registerRender(FOOD_ONION);
		registerRender(FOOD_SPINACH);
		registerRender(FOOD_TOMATO);
		registerRender(FOOD_GRAPE);
		registerRender(FOOD_CHERRY);
		registerRender(FOOD_MIKAN);
		registerRender(FOOD_CORN_B);

		registerRender(BOX_H_EMPTY);
		registerRender(BOX_H_APPLE);
		registerRender(BOX_H_BEEF);
		registerRender(BOX_H_BEETROOT);
		registerRender(BOX_H_BREAD);
		registerRender(BOX_H_CARROT);
		registerRender(BOX_H_CHICKEN);
		registerRender(BOX_H_CHORUS);
		registerRender(BOX_H_COCO);
		registerRender(BOX_H_EGG);
		registerRender(BOX_H_FISH);
		registerRender(BOX_H_FLOUR);
		registerRender(BOX_H_MUTTON);
		registerRender(BOX_H_PORK);
		registerRender(BOX_H_POTATO);
		registerRender(BOX_H_RABBIT);
		registerRender(BOX_H_SALMON);

		registerRender(BOX_H_AZUKI);
		registerRender(BOX_H_CABBAGE);
		registerRender(BOX_H_HAKUSAI);
		registerRender(BOX_H_CITRUS);
		registerRender(BOX_H_CORN);
		registerRender(BOX_H_GRAPE);
		registerRender(BOX_H_GREENONION);
		registerRender(BOX_H_ONION);
		registerRender(BOX_H_ORIENTCLAM);
		registerRender(BOX_H_RICE);
		registerRender(BOX_H_SOY);
		registerRender(BOX_H_SPINACH);
		registerRender(BOX_H_SQUID);
		registerRender(BOX_H_TOMATO);
		registerRender(BOX_H_CHERRY);		
		registerRender(BOX_H_TAKENOKO);	
		registerRender(BOX_H_KURI);	
		registerRender(BOX_H_TGREEN);
		registerRender(BOX_H_TRED);

		registerRender(BOX_H_BPEPPER);
		registerRender(BOX_H_CUMIN);
		registerRender(BOX_H_TURMERIC);
		registerRender(BOX_H_CHILI);
		
		registerRender(CHADUTSU);
		registerRender(CANTEA);
		registerRender(TAWARA);

		registerRender(KOUBOBOT_full);
		registerRender(NYUSANBOT_full);
		registerRender(KOUBO);
		registerRender(NYUSAN);

		registerRender(MIZUOKE);
		registerRender(MIZUOKE_full);
		registerRender(MIZUOKE_Milk);

		registerRenderMeta(HAKKOUTARU, 0, "block_taru_hakkou");
		registerRenderMeta(HAKKOUTARU, 1, "block_taru_kouji_f");
		registerRenderMeta(HAKKOUTARU, 2, "block_taru_shubo_f");
		registerRenderMeta(HAKKOUTARU, 3, "block_taru_moromi_f");
		registerRenderMeta(HAKKOUTARU, 4, "block_taru_jukusei_f");
		registerRender(RINGOSHU_TARU);
		registerRender(BUDOUSHU_TARU);
		registerRender(HACHIMITSUSHU_TARU);

		registerRender(NAMASAKEBOT);
		registerRender(SAKEBOT);
		registerRender(JUKUSAKEBOT);
		registerRender(NABEAMAZAKE_nama);
		registerRender(NABEAMAZAKE);
		registerRender(CIDERBOT);
		registerRender(JUKUCIDERBOT);
		registerRender(WINEBOT);
		registerRender(JUKUWINEBOT);
		registerRender(MEADBOT);
		registerRender(JUKUMEADBOT);

		registerRenderMeta(SAKEGLASS, 1, "block_glass_sakenama");
		registerRenderMeta(SAKEGLASS, 2, "block_glass_sake");
		registerRenderMeta(SAKEGLASS, 3, "block_glass_sakejuku");
		registerRenderMeta(SAKEGLASS, 4, "block_glass_amazake");
		registerRenderMeta(WINEGLASS, 1, "block_glass_wine");
		registerRenderMeta(WINEGLASS, 2, "block_glass_winejuku");
		registerRenderMeta(WINEGLASS, 3, "block_glass_cider");
		registerRenderMeta(WINEGLASS, 4, "block_glass_ciderjuku");
		registerRenderMeta(WINEGLASS, 5, "block_glass_mead");
		registerRenderMeta(WINEGLASS, 6, "block_glass_meadjuku");
		registerRender(WINE_TANA);

		registerRenderMeta(HAKKOUTARU, 5, "block_taru_miso_f");
		registerRenderMeta(HAKUSAI_TARU, 1, "block_taru_hakusai_f");
		registerRenderMeta(HAKUSAI_TARU, 2, "block_taru_hakusai_f2");

		registerRenderMeta(SHOUYU_TARU, 1, "block_taru_shouyu_f");
		registerRenderMeta(SHOUYU_TARU, 2, "block_taru_komezu_f");
		registerRenderMeta(SHOUYU_TARU, 3, "block_taru_kinoko_f");
		registerRenderMeta(SHOUYU_TARU, 4, "block_taru_nori_f");
		registerRenderMeta(SHOUYU_TARU, 5, "block_taru_pepper_f");
		registerRenderMeta(HAKKOUTARU, 7, "block_taru_vanilla_f");
		registerRenderMeta(HAKKOUTARU, 6, "block_taru_koucha_f");
		
		registerRender(SHOUYU_bot_14);
		registerRender(KOMEZU_bot_12);
		registerRender(SOYOIL_bot_12);
		registerRender(DASHI_bot_14);
		registerRender(OSAUCE_bot_14);
		registerRender(MAYO_bot_14);
		registerRender(VANILLA_bot_14);
		
		registerRender(Item_MATCH);
		registerRender(Item_SARA);
		registerRenderMeta(Item_DISH, 1, "item_food_yunomi");
		registerRenderMeta(Item_DISH, 2, "item_food_teacup");
		registerRenderMeta(Item_DISH, 3, "item_food_chawan");
		registerRenderMeta(Item_DISH, 4, "item_food_shikki");
		registerRenderMeta(Item_DISH, 5, "item_food_tonsui");
		registerRenderMeta(Item_DISH, 6, "item_food_donburi");
		registerRenderMeta(Item_DISH, 7, "item_food_driglass");
		registerRenderMeta(Item_DISH, 8, "item_food_sakebot");
		registerRenderMeta(Item_DISH, 9, "item_bentouhako");
		
		registerRenderMeta(CLAY_DISH, 1, "item_clay_sara");
		registerRenderMeta(CLAY_DISH, 2, "item_clay_yunomi");
		registerRenderMeta(CLAY_DISH, 3, "item_clay_kyusu");
		registerRenderMeta(CLAY_DISH, 4, "item_clay_teacup");
		registerRenderMeta(CLAY_DISH, 5, "item_clay_teapot");
		registerRenderMeta(CLAY_DISH, 6, "item_clay_chawan");
		registerRenderMeta(CLAY_DISH, 7, "item_clay_nabe");
		registerRenderMeta(CLAY_DISH, 8, "item_clay_tonsui");
		registerRenderMeta(CLAY_DISH, 9, "item_clay_donburi");

		registerRenderMeta(SHIO, 0, "item_salt");
		registerRenderMeta(SHIO, 1, "item_nigari");
		registerRenderMeta(SHIO, 2, "item_rennet");
		registerRenderMeta(SHIO, 3, "item_crop_tengusa");
		registerRenderMeta(SHIO, 4, "item_crop_tengusawash");
		registerRenderMeta(SHIO, 5, "item_crop_tengusadry");
		
		registerRenderMeta(MUSHIGOME, 0, "item_mushigome");
		registerRenderMeta(MUSHIGOME, 1, "item_mushigome_take");
		registerRenderMeta(MUSHIGOME, 2, "item_mushigome_kuri");
		registerRenderMeta(MUSHIGOME, 3, "item_mushisekihan");
		registerRenderMeta(MUSHIGOME, 4, "item_anko");
		registerRenderMeta(MUSHIGOME, 5, "item_custard");
		registerRenderMeta(MUSHIGOME, 6, "item_custard_cream");
		
		registerRenderMeta(KANTEN_BOWL, 10, "item_bowl_icecream");
		registerRenderMeta(KANTEN_BOWL, 11, "item_bowl_icecream_greentea");
		registerRenderMeta(KANTEN_BOWL, 12, "item_bowl_icecream_redtea");
		registerRenderMeta(KANTEN_BOWL, 13, "item_bowl_icecream_cacao");
		registerRenderMeta(KANTEN_BOWL, 0, "item_bowl_kanten_apple");
		registerRenderMeta(KANTEN_BOWL, 1, "item_bowl_kanten_cherry");
		registerRenderMeta(KANTEN_BOWL, 2, "item_bowl_kanten_citrus");
		registerRenderMeta(KANTEN_BOWL, 3, "item_bowl_kanten_grape");
		registerRenderMeta(KANTEN_BOWL, 4, "item_bowl_kanten_milk");
		registerRenderMeta(KANTEN_BOWL, 5, "item_bowl_yokan");
		registerRenderMeta(KANTEN_BOWL, 6, "item_bowl_yokan_matcha");
		
		registerRender(KOMEKOUJI);
		registerRender(SHUBO);
		registerRender(MORO);
		registerRender(SAKEKASU);

		registerRender(NIMAME);
		registerRender(MISO);

		registerRenderMeta(KOMUGI, 1, "item_flour");
		registerRenderMeta(KOMUGI, 2, "item_butter");
		registerRenderMeta(KOMUGI, 3, "item_kiji_bun");
		registerRenderMeta(KOMUGI, 4, "item_kiji_burg");
		registerRenderMeta(KOMUGI, 5, "item_kiji_scone");
		registerRenderMeta(KOMUGI, 6, "item_kiji_senbei");
		registerRenderMeta(KOMUGI, 7, "item_kiji_pizza");
		registerRenderMeta(KOMUGI, 8, "item_food_pizza_n");
		registerRenderMeta(KOMUGI, 9, "item_food_pizza_cn");
		registerRenderMeta(KOMUGI, 10, "item_food_pizza_tn");
		registerRenderMeta(KOMUGI, 11, "item_food_pizza_sn");
		
		registerRenderMeta(PASTA, 1, "item_food_pasta_n");
		registerRenderMeta(PASTA, 2, "item_food_pasta_s");
		registerRenderMeta(PASTA, 3, "item_food_udon_n");
		registerRenderMeta(PASTA, 4, "item_food_shouyu_don");
		registerRenderMeta(PASTA, 5, "item_food_tsuyu_don");
		registerRenderMeta(PAN_KIJI, 0, "item_azuki_boil");
		registerRenderMeta(PAN_KIJI, 1, "item_kiji_pananko");
		registerRenderMeta(PAN_KIJI, 2, "item_kiji_pancustard");
		registerRenderMeta(PAN_KIJI, 3, "item_kiji_panapple");
		registerRenderMeta(PAN_KIJI, 4, "item_kiji_pancherry");
		registerRenderMeta(PAN_KIJI, 5, "item_kiji_pancitrus");
		registerRenderMeta(PAN_KIJI, 6, "item_kiji_pangrape");
		registerRenderMeta(PAN_KIJI, 7, "item_kiji_pangreentea");
		
		registerRender(KEIRYO_CUP);
		registerRender(KEIRYO_CUP_full);
		registerRender(KANSUI);
		registerRenderMeta(RAMEN_nama, 0, "item_food_ramen_n");
		registerRenderMeta(RAMEN_nama, 1, "item_food_tare_shouyu");
		registerRenderMeta(RAMEN_nama, 2, "item_food_tare_miso");
		registerRenderMeta(RAMEN_nama, 3, "item_food_tare_shio");
		registerRenderMeta(RAMEN_nama, 4, "item_food_rsoup_shouyu");
		registerRenderMeta(RAMEN_nama, 5, "item_food_rsoup_miso");
		registerRenderMeta(RAMEN_nama, 6, "item_food_rsoup_shio");
		registerRenderMeta(RAMEN_nama, 7, "item_food_sobaplate");
		
		registerRender(ZUNDOU);
		registerRenderMeta(ZUNDOU_RCURRY, 2, "block_food_cunabe_n");
		registerRenderMeta(ZUNDOU_RCURRY, 1, "block_food_cunabe_cn");
		registerRenderMeta(ZUNDOU_RCURRY, 4, "block_food_cunabe_tn");
		registerRenderMeta(ZUNDOU_RCURRY, 3, "block_food_stewnabe_n");
		registerRender(ZUNDOU_MIZU);
		registerRender(ZUNDOU_MILK);
		registerRender(ZUNDOUSHIO);
		registerRenderMeta(ZUNDOU_CURRY, 0, "block_food_cunabe_1");
		registerRenderMeta(ZUNDOU_CURRY, 1, "block_food_cunabe_c1");
		registerRenderMeta(ZUNDOU_CURRY, 2, "block_food_cunabe_t1");
		registerRender(ZUNDOU_STEW);
		registerRender(DASHINABE);
		registerRenderMeta(ZUNDOU_RSOUP, 0, "block_food_rsoup_n");
		registerRenderMeta(ZUNDOU_RSOUP, 1, "block_food_rsoup_1");

		registerRender(NABE_kara);
		registerRenderMeta(NABE_NAMA_1, 1, "block_food_nabe_n");
		registerRenderMeta(NABE_NAMA_1, 2, "block_food_nabemiso_n");
		registerRenderMeta(NABE_NAMA_1, 3, "block_food_nabegohan_n");
		registerRenderMeta(NABE_NAMA_1, 4, "block_food_nabecorns_n");

		registerRender(NABESHIO_nama);
		registerRenderMeta(NABE_nama_SNTA, 2, "block_food_nabenimame_n");
		registerRenderMeta(NABE_nama_SNTA, 3, "block_food_nabetoufu_n");
		registerRenderMeta(NABE_nama_TK, 1, "block_food_nabegohantake_n");
		registerRenderMeta(NABE_nama_TK, 2, "block_food_nabegohankuri_n");
		registerRenderMeta(NABE_NAMA_AZUKI, 1, "block_food_nabesekihan_n");
		registerRenderMeta(NABE_NAMA_AZUKI, 2, "block_food_nabeazuki_n");
		registerRenderMeta(NABE_NAMA_AZUKI, 3, "block_food_nabeanko_n");
		registerRenderMeta(NABE_NAMA_AZUKI, 4, "block_food_nabecream");
		registerRenderMeta(NABE_NAMA_AZUKI, 5, "block_food_nabetengusa_n");
		registerRenderMeta(NABE_NAMA_PUDDING, 1, "block_food_nabepudding_n");
		registerRenderMeta(NABE_NAMA_PUDDING, 2, "block_food_nabepudding_g");
		registerRenderMeta(NABE_NAMA_PUDDING, 3, "block_food_nabepudding_r");
		registerRenderMeta(NABE_NAMA_PUDDING, 4, "block_food_nabepudding_c");
		
		registerRender(NABETORI);
		registerRender(NABEMISO);
		registerRender(NABEZENZAI_M);
		registerRender(NABEZENZAI_K);
		registerRender(NABEGOHAN);
		registerRender(NABEGOHAN_TAKE);
		registerRender(NABEGOHAN_KURI);
		registerRender(NABESEKIHAN);
		registerRender(NABECORNSOUP);
		
		registerRender(FRYPAN_kara);
		registerRenderMeta(FRYPAN_NAMA_1, 1, "block_food_frypan_n_tamago");
		registerRenderMeta(FRYPAN_NAMA_1, 2, "block_food_frypan_n_eggb");
		registerRenderMeta(FRYPAN_NAMA_1, 3, "block_food_frypan_n_tomatos");
		registerRenderMeta(FRYPAN_NAMA_1, 4, "block_food_frypan_n_kinokos");
		registerRenderMeta(FRYPAN_NAMA_3, 1, "block_food_frypan_n_seafood");
		registerRenderMeta(FRYPAN_NAMA_3, 3, "block_food_frypan_n_roux");
		
		registerRenderMeta(FRYPAN_NAMA_2, 1, "block_food_frypan_n_gyudon");
		registerRenderMeta(FRYPAN_NAMA_2, 2, "block_food_frypan_n_oyakodon");
		registerRenderMeta(FRYPAN_NAMA_2, 3, "block_food_frypan_n_katsu");
		registerRenderMeta(FRYPAN_NAMA_2, 4, "block_food_frypan_n_katsudon");
		registerRender(FPKINOKOAK_nama);
		registerRenderMeta(FRYPAN_NAMA_4, 1, "block_food_frypan_n_osauce");
		registerRenderMeta(FRYPAN_NAMA_4, 2, "block_food_teppan_n_okonomiyaki");
		registerRenderMeta(FRYPAN_NAMA_4, 3, "block_food_teppan_n_okonomis");
		registerRenderMeta(FRYPAN_NAMA_4, 4, "block_food_teppan_n_okonomic");
		registerRenderMeta(FRYPAN_NAMA_4, 5, "block_food_teppan_n_okonomisoba");
		registerRenderMeta(FRYPAN_NAMA_4, 6, "block_food_teppan_n_okonomisobas");
		registerRenderMeta(FRYPAN_NAMA_4, 7, "block_food_teppan_n_okonomisobac");
		registerRenderMeta(FRYPAN_NAMA_4, 8, "block_food_teppan_n_yakisoba");
		registerRenderMeta(FRYPAN_NAMA_4, 9, "block_food_teppan_n_yakisobashio");
		
		registerRenderMeta(FRYPAN_BAKE_1, 1, "block_food_frypan_b_tamago");
		registerRenderMeta(FRYPAN_BAKE_1, 2, "block_food_frypan_b_eggb");
		registerRenderMeta(FRYPAN_BAKE_1, 3, "block_food_frypan_b_tomatos");
		registerRenderMeta(FRYPAN_BAKE_1, 4, "block_food_frypan_b_kinokos");
		registerRenderMeta(FRYPAN_NAMA_3, 2, "block_food_frypan_b_seafood");
		
		registerRenderMeta(FRYPAN_BAKE_2, 1, "block_food_frypan_b_gyudon");
		registerRenderMeta(FRYPAN_BAKE_2, 2, "block_food_frypan_b_oyakodon");
		registerRenderMeta(FRYPAN_BAKE_2, 3, "block_food_frypan_b_katsu");
		registerRenderMeta(FRYPAN_BAKE_2, 4, "block_food_frypan_b_katsudon");
		registerRender(FPKINOKOAK);

		registerRender(NIBOSHI);
		registerRenderMeta(CURRY, 0, "block_food_curry_1");
		registerRenderMeta(CURRY, 1, "block_food_curry_c1");
		registerRenderMeta(CURRY, 2, "block_food_curry_t1");
		registerRenderMeta(CURRYSET, 0, "block_food_curryset_1");
		registerRenderMeta(CURRYSET, 1, "block_food_curryset_c1");
		registerRenderMeta(CURRYSET, 2, "block_food_curryset_t1");
		registerRender(STEW);

		registerRender(UDON_SU);
		registerRender(UDON_NIKU);
		registerRender(UDON_TSUKIMI);
		registerRenderMeta(RAMEN, 0, "block_food_ramenshouyu_1");
		registerRenderMeta(RAMEN, 1, "block_food_ramenmiso_1");
		registerRenderMeta(RAMEN, 2, "block_food_ramenshio_1");
		
		registerRender(TONSUITORI);
		registerRender(MISOSOUP);
		registerRender(ZENZAI_M);
		registerRender(ZENZAI_K);
		registerRender(GOHAN);
		registerRender(GOHAN_TAKE);
		registerRender(GOHAN_KURI);
		registerRender(SEKIHAN);
		registerRender(RICE);

		registerRender(DONBURI_MESHI);
		registerRender(DONBURI_GYU);
		registerRender(DONBURI_OYAKO);
		registerRender(DONBURI_KATSU);
		registerRender(DONBURI_KAISEN);
		
		registerRender(FOOD_HAKUSAI2);
		registerRender(HAKUSAIDUKE);

		registerRender(TAMAGOYAKI);
		registerRender(TAMAGOYAKITEI);
		registerRender(YAKIZAKANATEI);
		registerRender(YAKIJYAKETEI);
		registerRender(TAMAGOYAKITEI_TAKE);
		registerRender(YAKIZAKANATEI_TAKE);
		registerRender(YAKIJYAKETEI_TAKE);
		registerRender(TAMAGOYAKITEI_KURI);
		registerRender(YAKIZAKANATEI_KURI);
		registerRender(YAKIJYAKETEI_KURI);
		registerRender(TAMAGOYAKITEI_SEKI);
		registerRender(YAKIZAKANATEI_SEKI);
		registerRender(YAKIJYAKETEI_SEKI);
		
		registerRender(CORNSOUP);
		registerRender(EGGBURG);
		registerRender(EGGBURGSET);

		registerRender(PASTATOMATO);
		registerRender(PASTACHEESE);
		registerRender(PASTAKINOKO);
		registerRender(PASTASEAFOOD);
		
		registerRenderMeta(PIZZA, 0, "block_food_pizza_1");
		registerRenderMeta(PIZZA, 1, "block_food_pizza_c1");
		registerRenderMeta(PIZZA, 2, "block_food_pizza_t1");
		registerRenderMeta(PIZZA, 3, "block_food_pizza_s1");
		registerRenderMeta(PC_PIZZA, 0, "item_food_pizza");
		registerRenderMeta(PC_PIZZA, 1, "item_food_pizzac");
		registerRenderMeta(PC_PIZZA, 2, "item_food_pizzat");
		registerRenderMeta(PC_PIZZA, 3, "item_food_pizzas");
		registerRenderMeta(OKONOMIYAKI, 0, "block_food_okonomiyaki_1");
		registerRenderMeta(OKONOMIYAKI, 1, "block_food_okonomis_1");
		registerRenderMeta(OKONOMIYAKI, 2, "block_food_okonomic_1");
		registerRenderMeta(OKONOMIYAKI, 3, "block_food_okonomisoba_1");
		registerRenderMeta(OKONOMIYAKI, 4, "block_food_okonomisobas_1");
		registerRenderMeta(OKONOMIYAKI, 5, "block_food_okonomisobac_1");
		registerRenderMeta(OKONOMIYAKI, 6, "block_food_yakisoba_1");
		registerRenderMeta(OKONOMIYAKI, 7, "block_food_yakisobashio_1");
		
		registerRender(CHICKEN);
		registerRender(CHICKEN_small);

		registerRender(SUSHIMESHI);
		registerRender(SUSHIGETA_kara);
		registerRender(SUSHISET_salmon);
		registerRender(SUSHISET_fish);
		registerRender(SUSHISET_beef);
		registerRender(SUSHISET_tamago);
		registerRender(SUSHISET_4shoku);
		registerRender(SUSHIOKE);
		registerRender(SUSHIOKE_FULL_1);
		registerRender(SHOUYUSARA);

		registerRender(Item_YAKAN_kara);
		registerRender(KETTLE_full);
		registerRender(Item_YAKAN_boil);
		registerRender(KYUSU_kara);
		registerRender(KYUSU);
		registerRender(JPTEACUP);
		registerRender(JPTEASET);
		registerRenderMeta(JPCHAUKE, 0, "block_food_senbei");
		registerRenderMeta(JPCHAUKE, 1, "block_food_mikan");
		registerRenderMeta(JPCHAUKE, 2, "block_food_scone");

		registerRender(TEAPOT_kara);
		registerRender(TEAPOT);
		registerRender(TEACUP);
		registerRender(TEASET);
		registerRender(SCONESET_kara);
		registerRender(SCONESET_1);

		registerRenderMeta(ICECREAM, 0, "block_food_icecream_1");
		registerRenderMeta(ICECREAM, 1, "block_food_icecream_greentea");
		registerRenderMeta(ICECREAM, 2, "block_food_icecream_redtea");
		registerRenderMeta(ICECREAM, 3, "block_food_icecream_cacao");
		registerRenderMeta(ICECREAM, 4, "block_food_pudding_custard");
		registerRenderMeta(ICECREAM, 5, "block_food_pudding_greentea");
		registerRenderMeta(ICECREAM, 6, "block_food_pudding_redtea");
		registerRenderMeta(ICECREAM, 7, "block_food_pudding_cacao");
		
		registerRenderMeta(KANTEN, 0, "block_food_kanten_apple");
		registerRenderMeta(KANTEN, 1, "block_food_kanten_cherry");
		registerRenderMeta(KANTEN, 2, "block_food_kanten_citrus");
		registerRenderMeta(KANTEN, 3, "block_food_kanten_grape");
		registerRenderMeta(KANTEN, 4, "block_food_kanten_milk");
		registerRenderMeta(KANTEN, 5, "block_food_yokan");
		registerRenderMeta(KANTEN, 6, "block_food_yokan_matcha");
		
		registerRender(CHEESE_CURD);
		registerRender(CHEESE);
		registerRender(FRESH_CHEESE);
		registerRender(PIECE_CHEESE);
		registerRender(CHEESE_TANA);

		registerRender(CAKE);
		registerRender(BUN);
		registerRender(SCONE);
		registerRender(SENBEI);
		registerRender(TOUFU);
		registerRender(CHICKENSAND);
		registerRender(EGGSAND);
		registerRenderMeta(KIRIMI, 1, "item_food_kirimi_salmon");
		registerRenderMeta(KIRIMI, 2, "item_food_kirimi_fish");
		registerRenderMeta(KIRIMI, 3, "item_food_kirimi_beef");
		registerRenderMeta(KIRIMI, 4, "item_food_kirimi_tamago");

		registerRenderMeta(SUSHI, 1, "item_food_sushi_salmon");
		registerRenderMeta(SUSHI, 2, "item_food_sushi_fish");
		registerRenderMeta(SUSHI, 3, "item_food_sushi_beef");
		registerRenderMeta(SUSHI, 4, "item_food_sushi_tamago");

		registerRenderMeta(SHOUYUSUSHI, 1, "item_food_sushishouyu_salmon");
		registerRenderMeta(SHOUYUSUSHI, 2, "item_food_sushishouyu_fish");
		registerRenderMeta(SHOUYUSUSHI, 3, "item_food_sushishouyu_beef");
		registerRenderMeta(SHOUYUSUSHI, 4, "item_food_sushishouyu_tamago");

		registerRender(NORIAMI);
		registerRender(NORI_N);
		registerRender(NORI_I);
		registerRenderMeta(ONIGIRI, 0, "item_food_onigiri");
		registerRenderMeta(ONIGIRI, 1, "item_food_onigirishake");
		registerRenderMeta(ONIGIRI, 3, "item_food_onigiritakenoko");
		registerRenderMeta(ONIGIRI, 4, "item_food_onigirikuri");
		registerRenderMeta(ONIGIRI, 5, "item_food_onigirisekihan");
		registerRenderMeta(ONIGIRI, 2, "item_food_futomaki");

		registerRender(KUSHI_SAKANA);
		registerRender(KUSHI_SAKANA_C);

		registerRenderMeta(BENTOU, 0, "item_bentou");
		registerRenderMeta(BENTOU, 1, "item_bentoushake");
		registerRenderMeta(BENTOU, 2, "item_bentou_take");
		registerRenderMeta(BENTOU, 3, "item_bentoushake_take");
		registerRenderMeta(BENTOU, 4, "item_bentou_kuri");
		registerRenderMeta(BENTOU, 5, "item_bentoushake_kuri");
		registerRenderMeta(BENTOU, 6, "item_bentou_sekihan");
		registerRenderMeta(BENTOU, 7, "item_bentoushake_sekihan");
		
		registerRender(MOCHI);
		registerRenderMeta(MOCHI_FOOD, 0, "item_food_mochinori");
		registerRenderMeta(MOCHI_FOOD, 1, "item_food_mochikinako");
		registerRenderMeta(MOCHI_FOOD, 2, "item_food_mochianko");
		registerRenderMeta(MOCHI_FOOD, 3, "item_food_mochiohagi");
		registerRenderMeta(MOCHI_FOOD, 4, "item_food_mochisakura");
		registerRenderMeta(PAN_FOOD, 1, "item_food_pananko");
		registerRenderMeta(PAN_FOOD, 2, "item_food_pancustard");
		registerRenderMeta(PAN_FOOD, 3, "item_food_panapple");
		registerRenderMeta(PAN_FOOD, 4, "item_food_pancherry");
		registerRenderMeta(PAN_FOOD, 5, "item_food_pancitrus");
		registerRenderMeta(PAN_FOOD, 6, "item_food_pangrape");
		registerRenderMeta(PAN_FOOD, 7, "item_food_pangreentea");
		
		registerRender(KIT_TANA);
		registerRender(KITCHEN);
		registerRender(KIT_BOARD);
		registerRender(KIT_SINK1);

		registerRender(KIT_STOVE);
		registerRender(KIT_OVEN);
		registerRender(KIT_OVEN_B);
		registerRender(IRORI);
		registerRender(KIT_REIZOU);

		registerRender(KIT_KANKI_1);
		registerRender(KIT_HAIKIDUCT);
		registerRender(KIT_DUCTEND_1);

		registerRender(TEATABLE);
		registerRender(ENDEN);
		
		registerRender(TOAMI);
		registerRender(TOAMI_W);
		registerRender(CUT_IKA);
		registerRender(COOKED_IKA);
		registerRender(IKA);
		
		registerRender(KAIHORI);
		registerRender(HAMAGURI);
		registerRender(HAMAGURI_COOK);
		registerRender(KINE_YOKO);
		registerRender(USU_TSUKI);

		/*registerRenderMeta(TEST, 0, "test_crop_0");
		registerRenderMeta(TEST, 1, "test_crop_1");
		registerRenderMeta(TEST, 2, "test_crop_2");
		registerRenderMeta(TEST, 3, "test_crop_3");
		registerRenderMeta(TEST, 4, "test_crop_4");
		registerRenderMeta(TEST, 5, "test_crop_5");
		registerRenderMeta(TEST, 6, "test_crop_6");
		registerRenderMeta(TEST, 7, "test_crop_7");
		registerRenderMeta(TEST, 8, "test_crop_8");
		registerRenderMeta(TEST, 9, "test_crop_9");
		registerRenderMeta(TEST, 10, "test_crop_10");
		registerRenderMeta(TEST, 11, "test_crop_11");
		registerRenderMeta(TEST, 12, "test_crop_12");
		registerRenderMeta(TEST, 13, "test_crop_13");
		registerRenderMeta(TEST, 14, "test_crop_14");
		registerRenderMeta(TEST, 15, "test_crop_15"); */ 
	}

	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(),"inventory"));
	}

	private static void registerRenderMeta(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(new ResourceLocation(ChinjufuMod.MOD_ID, fileName), "inventory"));
	}
	/*ここまで So far↑ */
}
