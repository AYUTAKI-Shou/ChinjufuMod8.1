package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.dish.*;
import com.ayutaki.chinjufumod.blocks.hakkou.Bin_DashiMayoSauce;
import com.ayutaki.chinjufumod.blocks.hakkou.Bin_KomezuOil;

import net.minecraft.block.Block;

public final class Dish_Blocks {

	public static Block ZUNDOU, ZUNDOU_RCURRY;
	public static Block ZUNDOU_CURRY, ZUNDOU_CURRY_C, ZUNDOU_CURRY_T, 
								CURRY, CURRY_C, CURRY_T, CURRYSET, CURRYSET_C, CURRYSET_T;

	public static Block ZUNDOU_STEW, STEW;

	public static Block ZUNDOU_MIZUMILK;
	public static Block ZUNDOU_FISH, NIBOSHI;

	public static Block DASHINABE, ZUNDOU_UDON;
	public static Block UDON_SU, UDON_NIKU, UDON_TSUKIMI;

	public static Block ZUNDOU_RSOUP_nama, ZUNDOU_RSOUP, ZUNDOU_RAMEN;
	public static Block RAMEN_SHOUYU, RAMEN_MISO, RAMEN_SHIO;
	
	public static Block NABE_kara, NABE_nama, NABE_nama_SNTA, NABE_nama_TK, NABE_nama_Azuki, NABE_nama_Pudding,
								NABE_cooked_SNT, NABE_cooked_Azuki, NABE_cooked_Pudding, NABE_CREAM, NABE_CREAM_sub;
	public static Block NABETORI, TONSUITORI;
	public static Block NABEMISO, MISOSOUP, NABEZENZAI_M, NABEZENZAI_K, ZENZAI_M, ZENZAI_K;
	public static Block NABEGOHAN, NABEGOHAN_TAKE, NABEGOHAN_KURI, NABESEKIHAN,
								GOHAN, GOHAN_TAKE, GOHAN_KURI, RICE, SEKIHAN;
	public static Block DONBURI_MESHI, DONBURI_GYU, DONBURI_KATSU, DONBURI_OYAKO, DONBURI_KAISEN;

	public static Block HAKUSAIDUKE;
	public static Block TAMAGOYAKI;
	public static Block TAMAGOYAKITEI, YAKIZAKANATEI, YAKIJYAKETEI,
								TAMAGOYAKITEI_TAKE, YAKIZAKANATEI_TAKE, YAKIJYAKETEI_TAKE,
								TAMAGOYAKITEI_KURI, YAKIZAKANATEI_KURI, YAKIJYAKETEI_KURI,
								TAMAGOYAKITEI_SEKI, YAKIZAKANATEI_SEKI, YAKIJYAKETEI_SEKI;

	public static Block SUSHIMESHI;

	public static Block SUSHIGETA_kara1_4, SUSHIGETA_salmon, SUSHIGETA_fish, SUSHIGETA_beef, SUSHIGETA_tamago;
	public static Block SUSHISET_salmon, SUSHISET_fish, SUSHISET_beef, SUSHISET_tamago, SUSHISET_4shoku;

	public static Block SUSHIOKE, SUSHIOKE_FULL_1, SUSHIOKE_FULL_5, SUSHIOKE_FULL_9, SUSHIOKE_FULL_d;
	public static Block SHOUYUSARA_1, SHOUYUSARA_5;

	public static Block NABECORNSOUP, CORNSOUP;
	public static Block OSAUCE_bot, MAYO_bot, SOYOIL_bot ;
	public static Block KEIRYO_CUP;
	public static Block FRYPAN_kara, FRYPAN_NAMA_1, FRYPAN_BAKE_1, FRYPAN_NAMA_2, FRYPAN_NAMA_3,
								FRYPAN_BAKE_2, FPKINOKOAK_nama;

	public static Block FPOSAUCE_nama, OKONOMIYAKI_nama, OKONOMIS_nama, OKONOMIC_nama, 
								OKONOMISOBA_nama, OKONOMISOBAS_nama, OKONOMISOBAC_nama, 
								YAKISOBA_nama, YAKISOBASHIO_nama;
	public static Block OKONOMIYAKI_click, OKONOMIS_click, OKONOMIC_click, 
								OKONOMISOBA_click, OKONOMISOBAS_click, OKONOMISOBAC_click, 
								YAKISOBA_click, YAKISOBASHIO_click;
	public static Block OKONOMISOBA_5, OKONOMISOBAS_5, OKONOMISOBAC_5;
	
	public static Block EGGBURG, EGGBURGSET;

	public static Block ZUNDOU_PASTA, PASTATOMATO, PASTACHEESE, PASTAKINOKO, PASTASEAFOOD;
	public static Block PIZZA, PIZZA_CTS;
	public static Block OKONOMIYAKI, OKONOMIS, OKONOMIC, OKONOMISOBA, OKONOMISOBAS, OKONOMISOBAC, 
								YAKISOBA, YAKISOBASHIO;
	
	public static Block CHICKEN, CHICKEN_small;

	public static Block KETTLE_full, KYUSU, JPTEACUP, JPTEASET, JPCHAUKE;
	public static Block TEAPOT_kara, TEAPOT, TEACUP, TEASET;
	public static Block SCONESET_kara, SCONESET_1, SCONESET_a;

	public static Block ICECREAM, ICECREAM_GREEN, ICECREAM_RED, ICECREAM_CACAO;
	public static Block CUSTARD_PUDDING, GREENTEA_PUDDING, REDTEA_PUDDING, CACAO_PUDDING;
	public static Block TENGUSA_WASH, NABEK_APPLE, NABEK_CHERRY, NABEK_CITRUS, NABEK_GRAPE, 
								NABEK_MILK, NABEK_YOKAN, NABEK_MATCHA;
	public static Block KANTEN_APPLE, KANTEN_CHERRY, KANTEN_CITRUS, KANTEN_GRAPE, 
								KANTEN_MILK, YOKAN, YOKAN_MATCHA;
	
	public static Block SHIOAKUNABE, ORIITONABE;

	public static Block FOODKARA_TEI, FOODKARA_SUSHI;

	public static Block IRORISAKANA_E1, IRORISAKANA_E2, IRORISAKANA_R1, IRORISAKANA_R2, IRORISAKANA_C;


	public static void init() {

		ZUNDOU = new Zundou("block_food_zundou");
		ZUNDOU_RCURRY = new Zundou_NamaCurryStew("block_zundou_nama");
		ZUNDOU_CURRY = new Zundou4_Curry("block_food_cunabe_1");
		ZUNDOU_CURRY_C = new Zundou4_Curry("block_food_cunabe_c1");
		ZUNDOU_CURRY_T = new Zundou4_Curry("block_food_cunabe_t1");
		CURRY = new Curry("block_food_curry_1");
		CURRY_C = new Curry("block_food_curry_c1");
		CURRY_T = new Curry("block_food_curry_t1");
		CURRYSET = new Curry_set("block_food_curryset_1");
		CURRYSET_C = new Curry_set("block_food_curryset_c1");
		CURRYSET_T = new Curry_set("block_food_curryset_t1");
		
		ZUNDOU_STEW = new Zundou4_Stew("block_food_stewnabe_1");
		STEW = new Stew("block_food_stew_1");

		ZUNDOU_MIZUMILK = new Zundou_MizuMilk("block_zundou_mizumilk");
		ZUNDOU_FISH = new Zundou4_Fish("block_zundou_fish");
		NIBOSHI = new Niboshi("block_niboshi");

		DASHINABE = new Zundou4_Dashi("block_food_dashinabe_1");
		ZUNDOU_UDON = new Zundou4_Udon("block_zundou_udon");
		UDON_SU = new Udon("block_food_udonsu_1");
		UDON_NIKU = new Udon("block_food_udonniku_1");
		UDON_TSUKIMI = new Udon("block_food_udontsukimi_1");
		
		ZUNDOU_RSOUP_nama = new Zundou4_RSoup_nama("block_food_rsoup_n");
		ZUNDOU_RSOUP = new Zundou4_RSoup("block_food_rsoup_1");
		ZUNDOU_RAMEN = new Zundou4_Ramen("block_zundou_ramen");
		RAMEN_SHOUYU = new Ramen("block_food_ramenshouyu_1");
		RAMEN_MISO = new Ramen("block_food_ramenmiso_1");
		RAMEN_SHIO = new Ramen("block_food_ramenshio_1");
		
		NABE_kara = new Nabe_kara("block_food_karanabe");
		NABE_nama = new NabeNama_TMGC("block_food_nabenama_1");
		NABE_nama_SNTA = new NabeNama_SNTA("block_food_nabenama_2");
		NABE_nama_TK = new NabeNama_TK("block_food_nabegohantake_n");
		NABE_nama_Azuki = new NabeNama_Azuki("block_food_nabeazuki_n");
		NABE_nama_Pudding = new NabeNama_Pudding("block_food_nabepudding_n");
		NABE_cooked_SNT = new NabeCooked_SNT("block_food_nabecooked_snt");
		NABE_cooked_Azuki = new NabeCooked_Azuki("block_food_nabeazuki_b");
		NABE_cooked_Pudding = new NabeCooked_Pudding("block_food_nabepudding_b");
		NABE_CREAM = new NabeCream("block_food_nabecream");
		NABE_CREAM_sub = new NabeCream("block_food_nabecream_sub");
		NABETORI = new NabeTori("block_food_nabe_1");
		TONSUITORI = new Tonsui("block_food_tonsui_1");

		NABEMISO = new Nabe_Shikki("block_food_nabemiso_1");
		MISOSOUP = new Soup_Shikki("block_food_misosp_1");
		NABEZENZAI_M = new Nabe_Shikki("block_food_nabezenzai_m");
		NABEZENZAI_K = new Nabe_Shikki("block_food_nabezenzai_k");
		ZENZAI_M = new Soup_Shikki("block_food_zenzai_m");
		ZENZAI_K = new Soup_Shikki("block_food_zenzai_k");
		
		NABEGOHAN = new NabeGohan("block_food_nabegohan_1");
		NABEGOHAN_TAKE = new NabeGohan_Other("block_food_nabegohantake_1");
		NABEGOHAN_KURI = new NabeGohan_Other("block_food_nabegohankuri_1");
		NABESEKIHAN = new NabeGohan_Other("block_food_nabesekihan_1");
		GOHAN = new Gohan("block_food_gohan_1");
		GOHAN_TAKE = new Gohan("block_food_gohantake_1");
		GOHAN_KURI = new Gohan("block_food_gohankuri_1");
		SEKIHAN = new Gohan("block_food_sekihan");
		RICE = new RiceDish("block_food_rice_1");

		DONBURI_MESHI = new Donburi("block_food_donmeshi_1");
		DONBURI_GYU = new Donburi("block_food_dongyu_1");
		DONBURI_KATSU = new Donburi("block_food_donkatsu_1");
		DONBURI_OYAKO = new Donburi("block_food_donoyako_1");
		DONBURI_KAISEN = new Donburi("block_food_donkaisen_1");

		HAKUSAIDUKE = new Hakusai_Duke("block_food_hsd_1");

		TAMAGOYAKI = new Tamagoyaki("block_food_tgy_1");
		TAMAGOYAKITEI = new Tamagoyaki_set("block_food_tgytei_1");
		YAKIZAKANATEI = new YakiZakana_set("block_food_yakizakanatei_1");
		YAKIJYAKETEI = new YakiZakana_set("block_food_yakijyaketei_1");
		TAMAGOYAKITEI_TAKE = new Tamagoyaki_set("block_food_tgyteitake_1");
		YAKIZAKANATEI_TAKE = new YakiZakana_set("block_food_yakizakanateitake_1");
		YAKIJYAKETEI_TAKE = new YakiZakana_set("block_food_yakijyaketeitake_1");
		TAMAGOYAKITEI_KURI = new Tamagoyaki_set("block_food_tgyteikuri_1");
		YAKIZAKANATEI_KURI = new YakiZakana_set("block_food_yakizakanateikuri_1");
		YAKIJYAKETEI_KURI = new YakiZakana_set("block_food_yakijyaketeikuri_1");
		TAMAGOYAKITEI_SEKI = new Tamagoyaki_set("block_food_tgytei_sekihan_1");
		YAKIZAKANATEI_SEKI = new YakiZakana_set("block_food_yakizakanatei_sekihan_1");
		YAKIJYAKETEI_SEKI = new YakiZakana_set("block_food_yakijyaketei_sekihan_1");
		
		SUSHIMESHI = new SushiMeshi("block_food_sushimeshi");

		SUSHIGETA_kara1_4 = new SushiGeta_kara1_4("block_food_sushigeta_kara1_4");
		SUSHIGETA_salmon = new SushiGeta_full("block_food_sushigeta_salmon");
		SUSHIGETA_fish = new SushiGeta_full("block_food_sushigeta_fish");
		SUSHIGETA_beef = new SushiGeta_full("block_food_sushigeta_beef");
		SUSHIGETA_tamago = new SushiGeta_full("block_food_sushigeta_tamago");

		SUSHISET_salmon = new SushiSet("block_food_sushiset_salmon");
		SUSHISET_fish = new SushiSet("block_food_sushiset_fish");
		SUSHISET_beef = new SushiSet("block_food_sushiset_beef");
		SUSHISET_tamago = new SushiSet("block_food_sushiset_tamago");
		SUSHISET_4shoku = new SushiSet("block_food_sushiset_4shoku");

		SUSHIOKE = new SushiOke("block_food_sushioke_kara");
		SUSHIOKE_FULL_1 = new SushiOkeFull_1("block_food_sushiokefull_1");
		SUSHIOKE_FULL_5 = new SushiOkeFull_1("block_food_sushiokefull_5");
		SUSHIOKE_FULL_9 = new SushiOkeFull_1("block_food_sushiokefull_9");
		SUSHIOKE_FULL_d = new SushiOkeFull_d("block_food_sushiokefull_d");

		SHOUYUSARA_1 = new ShouyuSara_1("block_food_shouyusara_1");
		SHOUYUSARA_5 = new ShouyuSara_1("block_food_shouyusara_5");

		NABECORNSOUP = new NabeCorn("block_food_nabecorns_1");
		CORNSOUP = new Corn_Soup("block_food_cornsp_1");

		OSAUCE_bot = new Bin_DashiMayoSauce("block_osauce_bot");
		MAYO_bot = new Bin_DashiMayoSauce("block_mayo_bot");
		SOYOIL_bot = new Bin_KomezuOil("block_soyoil_bot");
		
		KEIRYO_CUP = new MeasureCup("block_measurecup");

		FRYPAN_kara = new Frypan_kara("block_food_frypan");
		FRYPAN_NAMA_1 = new FrypanNama_SaraPasta("block_food_frypan_n1");
		FRYPAN_NAMA_2 = new FrypanNama_Don("block_food_frypan_n2");
		FRYPAN_NAMA_3 = new Frypan_SeafoodRoux("block_food_frypan_n3");
		FRYPAN_BAKE_1 = new FrypanBake_SaraPasta("block_food_frypan_b1");
		FRYPAN_BAKE_2 = new FrypanBake_Don("block_food_frypan_b2");
		FPKINOKOAK_nama = new Frypan_KinokoAmaKara("block_food_frypan_n_kinokoak");
		
		FPOSAUCE_nama = new Frypan_OSauce("block_food_frypan_n_osauce");
		OKONOMIYAKI_nama = new Teppan_4empty("block_food_teppan_n_okonomiyaki");
		OKONOMIS_nama = new Teppan_4empty("block_food_teppan_n_okonomis");
		OKONOMIC_nama = new Teppan_4empty("block_food_teppan_n_okonomic");
		OKONOMISOBA_nama = new Teppan_Stage4("block_food_teppan_n_okonomisoba");
		OKONOMISOBAS_nama = new Teppan_Stage4("block_food_teppan_n_okonomisobas");
		OKONOMISOBAC_nama = new Teppan_Stage4("block_food_teppan_n_okonomisobac");
		YAKISOBA_nama = new Teppan_Stage4("block_food_teppan_n_yakisoba");
		YAKISOBASHIO_nama = new Teppan_Stage4("block_food_teppan_n_yakisobashio");
		
		OKONOMIYAKI_click = new Teppan_4emptyC("block_food_teppan_c_okonomiyaki");
		OKONOMIS_click = new Teppan_4emptyC("block_food_teppan_c_okonomis");
		OKONOMIC_click = new Teppan_4emptyC("block_food_teppan_c_okonomic");
		OKONOMISOBA_click = new Teppan_Stage4C("block_food_teppan_c_okonomisoba");
		OKONOMISOBAS_click = new Teppan_Stage4C("block_food_teppan_c_okonomisobas");
		OKONOMISOBAC_click = new Teppan_Stage4C("block_food_teppan_c_okonomisobac");
		YAKISOBA_click = new Teppan_Stage4C("block_food_teppan_c_yakisoba");
		YAKISOBASHIO_click = new Teppan_Stage4C("block_food_teppan_c_yakisobashio");

		OKONOMISOBA_5 = new Teppan_5th("block_food_teppan_5_okonomisoba");
		OKONOMISOBAS_5 = new Teppan_5th("block_food_teppan_5_okonomisobas");
		OKONOMISOBAC_5 = new Teppan_5th("block_food_teppan_5_okonomisobac");

		EGGBURG = new EggBurg("block_food_egb_1");
		EGGBURGSET = new EggBurg_set("block_food_egbset_1");

		ZUNDOU_PASTA = new Zundou4_Pasta("block_zundou_pasta");
		PASTATOMATO = new Pasta("block_food_pastatoma_1");
		PASTACHEESE = new Pasta("block_food_pastacheese_1");
		PASTAKINOKO = new Pasta("block_food_pastakinoko_1");
		PASTASEAFOOD = new PastaSeafood("block_food_pastaseafood_1");

		PIZZA = new Pizza("block_food_pizza_1");
		PIZZA_CTS = new Pizza_CTS("block_food_pizza_cts");
		OKONOMIYAKI = new Okonomiyaki("block_food_okonomiyaki_1");
		OKONOMIS = new Okonomiyaki("block_food_okonomis_1");
		OKONOMIC = new Okonomiyaki("block_food_okonomic_1");
		OKONOMISOBA = new Okonomiyaki("block_food_okonomisoba_1");
		OKONOMISOBAS = new Okonomiyaki("block_food_okonomisobas_1");
		OKONOMISOBAC = new Okonomiyaki("block_food_okonomisobac_1");
		YAKISOBA = new Pasta("block_food_yakisoba_1");
		YAKISOBASHIO = new Pasta("block_food_yakisobashio_1");

		CHICKEN = new Chicken_Roast("block_food_roastchicken_1");
		CHICKEN_small = new Chicken_small("block_food_chickenb_1");

		KETTLE_full = new Kettle_full("block_kettle_full");
		KYUSU = new JPTeaKyusu("block_food_kyusu_1");
		JPTEACUP = new JPTeaCup("block_food_jpteacup_1");
		JPTEASET = new JPTea_Set("block_food_jpteaset_1");
		JPCHAUKE = new Chauke_3shu("block_food_jpchauke");

		TEAPOT_kara = new TeaPot_kara("block_food_teapot_k");
		TEAPOT = new TeaPot("block_food_teapot_1");
		TEACUP = new TeaCup("block_food_teacup_1");
		TEASET = new Tea_Set("block_food_teaset_1");

		SCONESET_kara = new SconeSet_kara("block_food_teastand");
		SCONESET_1 = new SconeSet_1("block_food_sconeset_1");
		SCONESET_a = new SconeSet_a("block_food_sconeset_a");

		ICECREAM = new Ice_Pudding("block_food_icecream_1");
		ICECREAM_GREEN = new Ice_Pudding("block_food_icecream_greentea");
		ICECREAM_RED = new Ice_Pudding("block_food_icecream_redtea");
		ICECREAM_CACAO = new Ice_Pudding("block_food_icecream_cacao");
		CUSTARD_PUDDING = new Ice_Pudding("block_food_pudding_custard");
		GREENTEA_PUDDING = new Ice_Pudding("block_food_pudding_greentea");
		REDTEA_PUDDING = new Ice_Pudding("block_food_pudding_redtea");
		CACAO_PUDDING = new Ice_Pudding("block_food_pudding_cacao");
		
		TENGUSA_WASH = new TengusaWashed("block_tengusawash");
		NABEK_APPLE = new NabeKanten("block_food_nabek_apple");
		NABEK_CHERRY = new NabeKanten("block_food_nabek_cherry");
		NABEK_CITRUS = new NabeKanten("block_food_nabek_citrus");
		NABEK_GRAPE = new NabeKanten("block_food_nabek_grape");
		NABEK_MILK = new NabeKantenMilk("block_food_nabek_milk");
		NABEK_YOKAN = new NabeYokan("block_food_nabek_yokan");
		NABEK_MATCHA = new NabeYokan("block_food_nabek_matcha");
		KANTEN_APPLE = new Kanten("block_food_kanten_apple");
		KANTEN_CHERRY = new Kanten("block_food_kanten_cherry");
		KANTEN_CITRUS = new Kanten("block_food_kanten_citrus");
		KANTEN_GRAPE = new Kanten("block_food_kanten_grape");
		KANTEN_MILK = new Kanten("block_food_kanten_milk");
		YOKAN = new Kanten("block_food_yokan");
		YOKAN_MATCHA = new Kanten("block_food_yokan_matcha");
		
		SHIOAKUNABE = new Zundou_ShioAku("block_zundou_shioaku");
		ORIITONABE = new Zundou4_Oriito("block_zundou_oriito");

		FOODKARA_TEI = new FoodKara_Tei("block_food_kara_tei");
		FOODKARA_SUSHI = new FoodKara_Sushi("block_food_kara_sushi");

		IRORISAKANA_E1 = new Irori_Sakana_E1("block_irori_sakana_e1");
		IRORISAKANA_E2 = new Irori_Sakana_E2("block_irori_sakana_e2");
		IRORISAKANA_R1 = new Irori_Sakana_R1("block_irori_sakana_r1");
		IRORISAKANA_R2 = new Irori_Sakana_R2("block_irori_sakana_r2");
		IRORISAKANA_C = new Irori_Sakana_C("block_irori_sakana_c");
	}

	public static void register() {

		registerBlock(ZUNDOU);
		registerBlock(ZUNDOU_RCURRY);
		registerBlock(ZUNDOU_CURRY);
		registerBlock(ZUNDOU_CURRY_C);
		registerBlock(ZUNDOU_CURRY_T);
		registerBlock(CURRY);
		registerBlock(CURRY_C);
		registerBlock(CURRY_T);
		registerBlock(CURRYSET);
		registerBlock(CURRYSET_C);
		registerBlock(CURRYSET_T);

		registerBlock(ZUNDOU_STEW);
		registerBlock(STEW);

		registerBlock(ZUNDOU_MIZUMILK);
		registerBlock(ZUNDOU_FISH);
		registerBlock(NIBOSHI);

		registerBlock(DASHINABE);
		registerBlock(ZUNDOU_UDON);
		registerBlock(UDON_SU);
		registerBlock(UDON_NIKU);
		registerBlock(UDON_TSUKIMI);

		registerBlock(ZUNDOU_RSOUP_nama);
		registerBlock(ZUNDOU_RSOUP);
		registerBlock(ZUNDOU_RAMEN);
		registerBlock(RAMEN_SHOUYU);
		registerBlock(RAMEN_MISO);
		registerBlock(RAMEN_SHIO);
		
		registerBlock(NABE_kara);
		registerBlock(NABE_nama);
		registerBlock(NABE_nama_SNTA);
		registerBlock(NABE_nama_TK);
		registerBlock(NABE_nama_Azuki);
		registerBlock(NABE_nama_Pudding);
		registerBlock(NABE_cooked_SNT);
		registerBlock(NABE_cooked_Azuki);
		registerBlock(NABE_cooked_Pudding);
		registerBlock(NABE_CREAM);
		registerBlock(NABE_CREAM_sub);
		registerBlock(NABETORI);
		registerBlock(TONSUITORI);

		registerBlock(NABEMISO);
		registerBlock(MISOSOUP);
		registerBlock(NABEZENZAI_M);
		registerBlock(NABEZENZAI_K);
		registerBlock(ZENZAI_M);
		registerBlock(ZENZAI_K);
		
		registerBlock(NABEGOHAN);
		registerBlock(NABEGOHAN_TAKE);
		registerBlock(NABEGOHAN_KURI);
		registerBlock(NABESEKIHAN);
		registerBlock(GOHAN);
		registerBlock(GOHAN_TAKE);
		registerBlock(GOHAN_KURI);
		registerBlock(RICE);
		registerBlock(SEKIHAN);
		
		registerBlock(DONBURI_MESHI);
		registerBlock(DONBURI_GYU);
		registerBlock(DONBURI_KATSU);
		registerBlock(DONBURI_OYAKO);
		registerBlock(DONBURI_KAISEN);
		
		registerBlock(HAKUSAIDUKE);

		registerBlock(TAMAGOYAKI);
		registerBlock(TAMAGOYAKITEI);
		registerBlock(YAKIZAKANATEI);
		registerBlock(YAKIJYAKETEI);
		registerBlock(TAMAGOYAKITEI_TAKE);
		registerBlock(YAKIZAKANATEI_TAKE);
		registerBlock(YAKIJYAKETEI_TAKE);
		registerBlock(TAMAGOYAKITEI_KURI);
		registerBlock(YAKIZAKANATEI_KURI);
		registerBlock(YAKIJYAKETEI_KURI);
		registerBlock(TAMAGOYAKITEI_SEKI);
		registerBlock(YAKIZAKANATEI_SEKI);
		registerBlock(YAKIJYAKETEI_SEKI);
		
		registerBlock(SUSHIMESHI);

		registerBlock(SUSHIGETA_kara1_4);
		registerBlock(SUSHIGETA_salmon);
		registerBlock(SUSHIGETA_fish);
		registerBlock(SUSHIGETA_beef);
		registerBlock(SUSHIGETA_tamago);

		registerBlock(SUSHISET_salmon);
		registerBlock(SUSHISET_fish);
		registerBlock(SUSHISET_beef);
		registerBlock(SUSHISET_tamago);
		registerBlock(SUSHISET_4shoku);

		registerBlock(SUSHIOKE);
		registerBlock(SUSHIOKE_FULL_1);
		registerBlock(SUSHIOKE_FULL_5);
		registerBlock(SUSHIOKE_FULL_9);
		registerBlock(SUSHIOKE_FULL_d);

		registerBlock(SHOUYUSARA_1);
		registerBlock(SHOUYUSARA_5);

		registerBlock(NABECORNSOUP);
		registerBlock(CORNSOUP);
		registerBlock(OSAUCE_bot);
		registerBlock(MAYO_bot);
		registerBlock(SOYOIL_bot);
		registerBlock(KEIRYO_CUP);

		registerBlock(FRYPAN_kara);
		registerBlock(FRYPAN_NAMA_1);
		registerBlock(FRYPAN_NAMA_2);
		registerBlock(FRYPAN_NAMA_3);
		registerBlock(FRYPAN_BAKE_1);
		registerBlock(FRYPAN_BAKE_2);
		registerBlock(FPKINOKOAK_nama);
		
		registerBlock(FPOSAUCE_nama);
		registerBlock(OKONOMIYAKI_nama);
		registerBlock(OKONOMIS_nama);
		registerBlock(OKONOMIC_nama);
		registerBlock(OKONOMISOBA_nama);
		registerBlock(OKONOMISOBAS_nama);
		registerBlock(OKONOMISOBAC_nama);
		registerBlock(YAKISOBA_nama);
		registerBlock(YAKISOBASHIO_nama);
		
		registerBlock(OKONOMIYAKI_click);
		registerBlock(OKONOMIS_click);
		registerBlock(OKONOMIC_click);
		registerBlock(OKONOMISOBA_click);
		registerBlock(OKONOMISOBAS_click);
		registerBlock(OKONOMISOBAC_click);
		registerBlock(YAKISOBA_click);
		registerBlock(YAKISOBASHIO_click);

		registerBlock(OKONOMISOBA_5);
		registerBlock(OKONOMISOBAS_5);
		registerBlock(OKONOMISOBAC_5);
		
		registerBlock(EGGBURG);
		registerBlock(EGGBURGSET);

		registerBlock(ZUNDOU_PASTA);
		registerBlock(PASTATOMATO);
		registerBlock(PASTACHEESE);
		registerBlock(PASTAKINOKO);
		registerBlock(PASTASEAFOOD);
		registerBlock(PIZZA);
		registerBlock(PIZZA_CTS);
		registerBlock(OKONOMIYAKI);
		registerBlock(OKONOMIS);
		registerBlock(OKONOMIC);
		registerBlock(OKONOMISOBA);
		registerBlock(OKONOMISOBAS);
		registerBlock(OKONOMISOBAC);
		registerBlock(YAKISOBA);
		registerBlock(YAKISOBASHIO);
		
		registerBlock(CHICKEN);
		registerBlock(CHICKEN_small);

		registerBlock(KETTLE_full);
		registerBlock(KYUSU);
		registerBlock(JPTEACUP);
		registerBlock(JPTEASET);
		registerBlock(JPCHAUKE);

		registerBlock(TEAPOT_kara);
		registerBlock(TEAPOT);
		registerBlock(TEACUP);
		registerBlock(TEASET);

		registerBlock(SCONESET_kara);
		registerBlock(SCONESET_1);
		registerBlock(SCONESET_a);
		
		registerBlock(ICECREAM);
		registerBlock(ICECREAM_GREEN);
		registerBlock(ICECREAM_RED);
		registerBlock(ICECREAM_CACAO);
		registerBlock(CUSTARD_PUDDING);
		registerBlock(GREENTEA_PUDDING);
		registerBlock(REDTEA_PUDDING);
		registerBlock(CACAO_PUDDING);
		
		registerBlock(TENGUSA_WASH);
		registerBlock(NABEK_APPLE);
		registerBlock(NABEK_CHERRY);
		registerBlock(NABEK_CITRUS);
		registerBlock(NABEK_GRAPE);
		registerBlock(NABEK_MILK);
		registerBlock(NABEK_YOKAN);
		registerBlock(NABEK_MATCHA);
		registerBlock(KANTEN_APPLE);
		registerBlock(KANTEN_CHERRY);
		registerBlock(KANTEN_CITRUS);
		registerBlock(KANTEN_GRAPE);
		registerBlock(KANTEN_MILK);
		registerBlock(YOKAN);
		registerBlock(YOKAN_MATCHA);
		
		registerBlock(SHIOAKUNABE);
		registerBlock(ORIITONABE);

		registerBlock(FOODKARA_TEI);
		registerBlock(FOODKARA_SUSHI);

		registerBlock(IRORISAKANA_E1);
		registerBlock(IRORISAKANA_E2);
		registerBlock(IRORISAKANA_R1);
		registerBlock(IRORISAKANA_R2);
		registerBlock(IRORISAKANA_C);
	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}
}
