package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.dish.*;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Dashi;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Shouyu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Dish_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> OSAUCE_bot_14 = register("block_osauce_bot", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> OSAUCE_bot_24 = register("block_osauce_bot_2", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> OSAUCE_bot_34 = register("block_osauce_bot_3", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> OSAUCE_bot_44 = register("block_osauce_bot_4", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> MAYO_bot_14 = register("block_mayo_bot", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> MAYO_bot_24 = register("block_mayo_bot_2", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> MAYO_bot_34 = register("block_mayo_bot_3", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> MAYO_bot_44 = register("block_mayo_bot_4", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> SOYOIL_bot_12 = register("block_soyoil_bot", Bot_Shouyu::new, stoneState());
	public static final DeferredBlock<Block> SOYOIL_bot_22 = register("block_soyoil_bot_2", Bot_Shouyu::new, stoneState());
	public static final DeferredBlock<Block> TENGUSA_WASH = register("block_tengusawash", TengusaWashed::new, grassRandom());

	public static final DeferredBlock<Block> ZUNDOU = register("block_food_zundou", Zundou::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_MIZU = register("block_zundou_mizu", Zundou_Mizu::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_SHIO = register("block_zundou_shiomizu", Zundou_Shio::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_MILK = register("block_zundou_milk", Zundou_Milk::new, metalDish());

	public static final DeferredBlock<Block> ZUNDOU_NCURRY = register("block_food_cunabe_n", Zundou_NamaCurry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_CURRY = register("block_food_cunabe_1", Zundou4_Curry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_NCURRY_C = register("block_food_cunabe_cn", Zundou_NamaCurry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_CURRY_C = register("block_food_cunabe_c1", Zundou4_Curry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_NCURRY_T = register("block_food_cunabe_tn", Zundou_NamaCurry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_CURRY_T = register("block_food_cunabe_t1", Zundou4_Curry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_NSTEW = register("block_food_stewnabe_n", Zundou_NamaCurry::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_STEW = register("block_food_stewnabe_1", Zundou4_Stew::new, metalDish());

	public static final DeferredBlock<Block> ZUNDOU_FISH = register("block_zundou_fish", Zundou4_Fish::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_DASHI = register("block_food_dashinabe_1", Zundou4_Dashi::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_UDON = register("block_zundou_udon", Zundou4_Udon::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_PASTA = register("block_zundou_pasta", Zundou4_Pasta::new, metalDish());
	
	public static final DeferredBlock<Block> ZUNDOU_RSOUP_nama = register("block_food_rsoup_n", Zundou4_RSoup_nama::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_RSOUP = register("block_food_rsoup_1", Zundou4_RSoup::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_RAMEN = register("block_zundou_ramen", Zundou4_Ramen::new, metalDish());
	
	public static final DeferredBlock<Block> ZUNDOU_AKU = register("block_zundou_aku", Zundou_Aku::new, metalDish());
	public static final DeferredBlock<Block> ZUNDOU_ORIITO = register("block_zundou_oriito", Zundou4_Oriito::new, metalDish());
	
	public static final DeferredBlock<Block> NABE_kara = register("block_food_karanabe", Nabe_kara::new, stoneDish());
	public static final DeferredBlock<Block> NABETORI_nama = register("block_food_nabe_n", Nabe_NamaSoup::new, stoneDish());
	public static final DeferredBlock<Block> NABEMISO_nama = register("block_food_nabemiso_n", Nabe_NamaSoup::new, stoneDish());
	public static final DeferredBlock<Block> NABEGOHAN_nama = register("block_food_nabegohan_n", Nabe_NamaGohan::new, stoneDish());
	public static final DeferredBlock<Block> NABEGOHANTAKE_nama = register("block_food_nabegohantake_n", Nabe_NamaGohan::new, stoneDish());
	public static final DeferredBlock<Block> NABEGOHANKURI_nama = register("block_food_nabegohankuri_n", Nabe_NamaGohan::new, stoneDish());
	public static final DeferredBlock<Block> NABESEKIHAN_nama = register("block_food_nabesekihan_n", Nabe_NamaGohan::new, stoneDish());
	public static final DeferredBlock<Block> NABECORN_nama = register("block_food_nabecorns_n", Nabe_NamaSoup::new, stoneDish());
	public static final DeferredBlock<Block> NABESHIO_nama = register("block_food_nabeshio_n", Nabe2_TakeSude::new, stoneDish());
	public static final DeferredBlock<Block> NABENIMAME_nama = register("block_food_nabenimame_n", Nabe2_TakeBowl::new, stoneDish());
	public static final DeferredBlock<Block> NABETOUFU_nama = register("block_food_nabetoufu_n", Nabe2_TakeSude::new, stoneDish());

	public static final DeferredBlock<Block> NABEAZUKI_nama = register("block_food_nabeazuki_n", Nabe2_TakeSude::new, stoneDish());
	public static final DeferredBlock<Block> NABEANKO_nama = register("block_food_nabeanko_n", Nabe2_TakeBowl::new, stoneDish());
	public static final DeferredBlock<Block> NABEPUDDING_nama = register("block_food_nabepudding_n", Nabe2_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> NABEPUDDING_green = register("block_food_nabepudding_g", Nabe2_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> NABEPUDDING_red = register("block_food_nabepudding_r", Nabe2_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> NABEPUDDING_cacao = register("block_food_nabepudding_c", Nabe2_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> NABE_CREAM = register("block_food_nabecream", NabeCream::new, stoneDish());
	public static final DeferredBlock<Block> NABE_CREAM_sub = register("block_food_nabecream_sub", NabeCream::new, stoneDish());
	public static final DeferredBlock<Block> KURI_NABE_nama = register("block_food_nabekuri_n", Nabe2_TakeBowl::new, stoneDish());
	public static final DeferredBlock<Block> NABETENGUSA_nama = register("block_food_nabetengusa_n", NabeTengusa::new, stoneDish());

	public static final DeferredBlock<Block> NABETORI = register("block_food_nabe_1", NabeTori::new, stoneDish());
	public static final DeferredBlock<Block> NABEMISO = register("block_food_nabemiso_1", Nabe_Shikki::new, stoneDish());
	public static final DeferredBlock<Block> NABEZENZAI_M = register("block_food_nabezenzai_m", Nabe_Shikki::new, stoneDish());
	public static final DeferredBlock<Block> NABEZENZAI_K = register("block_food_nabezenzai_k", Nabe_Shikki::new, stoneDish());
	public static final DeferredBlock<Block> NABEGOHAN = register("block_food_nabegohan_1", NabeGohan::new, stoneDish());
	public static final DeferredBlock<Block> NABEGOHAN_TAKE = register("block_food_nabegohantake_1", NabeGohan_Other::new, stoneDish());
	public static final DeferredBlock<Block> NABEGOHAN_KURI = register("block_food_nabegohankuri_1", NabeGohan_Other::new, stoneDish());
	public static final DeferredBlock<Block> NABESEKIHAN = register("block_food_nabesekihan_1", NabeGohan_Other::new, stoneDish());
	public static final DeferredBlock<Block> NABECORN = register("block_food_nabecorns_1", NabeCorn::new, stoneDish());

	public static final DeferredBlock<Block> KEIRYO_CUP = register("block_measurecup", MeasureCup::new, stoneDish());

	public static final DeferredBlock<Block> FRYPAN_kara = register("block_food_frypan", Frypan_kara::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPTAMAGO_nama = register("block_food_frypan_n_tamago", Frypan_TakePlate::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPGYUDON_nama = register("block_food_frypan_n_gyudon", Frypan_DonNama::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPOYAKODON_nama = register("block_food_frypan_n_oyakodon", Frypan_DonNama::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPKATSU_nama = register("block_food_frypan_n_katsu", Frypan_Katsu::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPKATSUDON_nama = register("block_food_frypan_n_katsudon", Frypan_DonNama::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPEGGBURG_nama = register("block_food_frypan_n_eggb", Frypan_TakePlate::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPTOMATO_nama = register("block_food_frypan_n_tomatos", Frypan_PastaNama::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPKINOKO_nama = register("block_food_frypan_n_kinokos", Frypan_PastaNama::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPSEAFOOD_nama = register("block_food_frypan_n_seafood", Frypan_PastaNama::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPKINOKOAK_nama = register("block_food_frypan_n_kinokoak", Frypan_KinokoAK::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> FPCURRY_nama = register("block_food_frypan_n_roux", Frypan_Curry::new, metalDish().lightLevel(litAntiShadow(1)));

	public static final DeferredBlock<Block> FPOSAUCE_nama = register("block_food_frypan_n_osauce", Frypan_OSauce::new, metalDish().lightLevel(litAntiShadow(1)));
	public static final DeferredBlock<Block> OKONOMIYAKI_nama = register("block_food_teppan_n_okonomiyaki", Teppan_4empty::new, snowDish());
	public static final DeferredBlock<Block> OKONOMIS_nama = register("block_food_teppan_n_okonomis", Teppan_4empty::new, snowDish());
	public static final DeferredBlock<Block> OKONOMIC_nama = register("block_food_teppan_n_okonomic", Teppan_4empty::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBA_nama = register("block_food_teppan_n_okonomisoba", Teppan_Stage4::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBAS_nama = register("block_food_teppan_n_okonomisobas", Teppan_Stage4::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBAC_nama = register("block_food_teppan_n_okonomisobac", Teppan_Stage4::new, snowDish());
	public static final DeferredBlock<Block> YAKISOBA_nama = register("block_food_teppan_n_yakisoba", Teppan_Stage4::new, snowDish());
	public static final DeferredBlock<Block> YAKISOBASHIO_nama = register("block_food_teppan_n_yakisobashio", Teppan_Stage4::new, snowDish());
	
	public static final DeferredBlock<Block> OKONOMIYAKI_click = register("block_food_teppan_c_okonomiyaki", Teppan_4emptyC::new, snowDish());
	public static final DeferredBlock<Block> OKONOMIS_click = register("block_food_teppan_c_okonomis", Teppan_4emptyC::new, snowDish());
	public static final DeferredBlock<Block> OKONOMIC_click = register("block_food_teppan_c_okonomic", Teppan_4emptyC::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBA_click = register("block_food_teppan_c_okonomisoba", Teppan_Stage4C::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBAS_click = register("block_food_teppan_c_okonomisobas", Teppan_Stage4C::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBAC_click = register("block_food_teppan_c_okonomisobac", Teppan_Stage4C::new, snowDish());
	public static final DeferredBlock<Block> YAKISOBA_click = register("block_food_teppan_c_yakisoba", Teppan_Stage4C::new, snowDish());
	public static final DeferredBlock<Block> YAKISOBASHIO_click = register("block_food_teppan_c_yakisobashio", Teppan_Stage4C::new, snowDish());

	public static final DeferredBlock<Block> OKONOMISOBA_5 = register("block_food_teppan_5_okonomisoba", Teppan_5th::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBAS_5 = register("block_food_teppan_5_okonomisobas", Teppan_5th::new, snowDish());
	public static final DeferredBlock<Block> OKONOMISOBAC_5 = register("block_food_teppan_5_okonomisobac", Teppan_5th::new, snowDish());

	public static final DeferredBlock<Block> NIBOSHI = register("block_niboshi", Niboshi::new, snowDish().randomTicks());
	
	public static final DeferredBlock<Block> CURRY = register("block_food_curry_1", Curry::new, stoneDish());
	public static final DeferredBlock<Block> CURRYSET = register("block_food_curryset_1", Curry_set::new, stoneDish());
	public static final DeferredBlock<Block> CURRY_C = register("block_food_curry_c1", Curry::new, stoneDish());
	public static final DeferredBlock<Block> CURRYSET_C = register("block_food_curryset_c1", Curry_set::new, stoneDish());
	public static final DeferredBlock<Block> CURRY_T = register("block_food_curry_t1", Curry::new, stoneDish());
	public static final DeferredBlock<Block> CURRYSET_T = register("block_food_curryset_t1", Curry_set::new, stoneDish());
	public static final DeferredBlock<Block> STEW = register("block_food_stew_1", Stew::new, stoneDish());

	public static final DeferredBlock<Block> UDON_SU = register("block_food_udonsu_1", Udon::new, stoneDish());
	public static final DeferredBlock<Block> UDON_NIKU = register("block_food_udonniku_1", Udon::new, stoneDish());
	public static final DeferredBlock<Block> UDON_TSUKIMI = register("block_food_udontsukimi_1", Udon::new, stoneDish());
	
	public static final DeferredBlock<Block> RAMEN_SHOUYU = register("block_food_ramenshouyu_1", Ramen::new, stoneDish());
	public static final DeferredBlock<Block> RAMEN_MISO = register("block_food_ramenmiso_1", Ramen::new, stoneDish());
	public static final DeferredBlock<Block> RAMEN_SHIO = register("block_food_ramenshio_1", Ramen::new, stoneDish());
	
	public static final DeferredBlock<Block> TONSUITORI = register("block_food_tonsui_1", Tonsui::new, stoneDish());
	public static final DeferredBlock<Block> MISOSOUP = register("block_food_misosp_1", Soup_Shikki::new, woodDish());
	public static final DeferredBlock<Block> ZENZAI_M = register("block_food_zenzai_m", Soup_Shikki::new, woodDish());
	public static final DeferredBlock<Block> ZENZAI_K = register("block_food_zenzai_k", Soup_Shikki::new, woodDish());
	public static final DeferredBlock<Block> GOHAN = register("block_food_gohan_1", Gohan::new, stoneDish());
	public static final DeferredBlock<Block> GOHAN_TAKE = register("block_food_gohantake_1", Gohan::new, stoneDish());
	public static final DeferredBlock<Block> GOHAN_KURI = register("block_food_gohankuri_1", Gohan::new, stoneDish());
	public static final DeferredBlock<Block> SEKIHAN = register("block_food_sekihan", Gohan::new, stoneDish());
	public static final DeferredBlock<Block> RICE = register("block_food_rice_1", RiceDish::new, stoneDish());
	public static final DeferredBlock<Block> DONBURI_MESHI = register("block_food_donmeshi_1", Donburi::new, stoneDish());
	
	public static final DeferredBlock<Block> DONBURI_GYU = register("block_food_dongyu_1", Donburi::new, stoneDish());
	public static final DeferredBlock<Block> DONBURI_OYAKO = register("block_food_donoyako_1", Donburi::new, stoneDish());
	public static final DeferredBlock<Block> DONBURI_KATSU = register("block_food_donkatsu_1", Donburi::new, stoneDish());
	public static final DeferredBlock<Block> DONBURI_KAISEN = register("block_food_donkaisen_1", Donburi::new, stoneDish());
	
	public static final DeferredBlock<Block> HAKUSAIDUKE = register("block_food_hsd_1", Hakusai_Duke::new, stoneDish());
	public static final DeferredBlock<Block> TAMAGOYAKI = register("block_food_tgy_1", Tamagoyaki::new, stoneDish());
	
	public static final DeferredBlock<Block> TAMAGOYAKITEI = register("block_food_tgytei_1", Tamagoyaki_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIZAKANATEI = register("block_food_yakizakanatei_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIJYAKETEI = register("block_food_yakijyaketei_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> TAMAGOYAKITEI_TAKE = register("block_food_tgyteitake_1", Tamagoyaki_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIZAKANATEI_TAKE = register("block_food_yakizakanateitake_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIJYAKETEI_TAKE = register("block_food_yakijyaketeitake_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> TAMAGOYAKITEI_KURI = register("block_food_tgyteikuri_1", Tamagoyaki_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIZAKANATEI_KURI = register("block_food_yakizakanateikuri_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIJYAKETEI_KURI = register("block_food_yakijyaketeikuri_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> TAMAGOYAKITEI_SEKI = register("block_food_tgytei_sekihan_1", Tamagoyaki_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIZAKANATEI_SEKI = register("block_food_yakizakanatei_sekihan_1", YakiZakana_set::new, stoneDish());
	public static final DeferredBlock<Block> YAKIJYAKETEI_SEKI = register("block_food_yakijyaketei_sekihan_1", YakiZakana_set::new, stoneDish());

	public static final DeferredBlock<Block> CORNSOUP = register("block_food_cornsp_1", Corn_Soup::new, stoneDish());
	public static final DeferredBlock<Block> EGGBURG = register("block_food_egb_1", EggBurg::new, stoneDish());
	public static final DeferredBlock<Block> EGGBURGSET = register("block_food_egbset_1", EggBurg_set::new, stoneDish());

	public static final DeferredBlock<Block> PASTATOMATO = register("block_food_pastatoma_1", Pasta::new, stoneDish());
	public static final DeferredBlock<Block> PASTACHEESE = register("block_food_pastacheese_1", Pasta::new, stoneDish());
	public static final DeferredBlock<Block> PASTAKINOKO = register("block_food_pastakinoko_1", Pasta::new, stoneDish());
	public static final DeferredBlock<Block> PASTASEAFOOD = register("block_food_pastaseafood_1", PastaSeafood::new, stoneDish());
	public static final DeferredBlock<Block> PIZZA = register("block_food_pizza_1", Pizza_5::new, woodDish());
	public static final DeferredBlock<Block> PIZZA_C = register("block_food_pizza_c1", Pizza_5::new, woodDish());
	public static final DeferredBlock<Block> PIZZA_T = register("block_food_pizza_t1", Pizza_5::new, woodDish());
	public static final DeferredBlock<Block> PIZZA_S = register("block_food_pizza_s1", Pizza_5::new, woodDish());
	public static final DeferredBlock<Block> OKONOMIYAKI = register("block_food_okonomiyaki_1", Okonomiyaki::new, stoneDish());
	public static final DeferredBlock<Block> OKONOMIS = register("block_food_okonomis_1", Okonomiyaki::new, stoneDish());
	public static final DeferredBlock<Block> OKONOMIC = register("block_food_okonomic_1", Okonomiyaki::new, stoneDish());
	public static final DeferredBlock<Block> OKONOMISOBA = register("block_food_okonomisoba_1", Okonomiyaki::new, stoneDish());
	public static final DeferredBlock<Block> OKONOMISOBAS = register("block_food_okonomisobas_1", Okonomiyaki::new, stoneDish());
	public static final DeferredBlock<Block> OKONOMISOBAC = register("block_food_okonomisobac_1", Okonomiyaki::new, stoneDish());
	public static final DeferredBlock<Block> YAKISOBA = register("block_food_yakisoba_1", Pasta::new, stoneDish());
	public static final DeferredBlock<Block> YAKISOBASHIO = register("block_food_yakisobashio_1", Pasta::new, stoneDish());

	public static final DeferredBlock<Block> CHICKEN = register("block_food_roastchicken_1", Chicken_Roast::new, stoneDish());
	public static final DeferredBlock<Block> CHICKEN_small = register("block_food_chickenb_1", Chicken_small::new, stoneDish());

	public static final DeferredBlock<Block> SUSHIMESHI = register("block_food_sushimeshi", SushiMeshi::new, woodDish());
	public static final DeferredBlock<Block> SUSHIGETA_kara = register("block_food_sushigeta_kara", SushiGeta_kara1_5::new, woodDish());
	public static final DeferredBlock<Block> SUSHIGETA_salmon = register("block_food_sushigeta_salmon", SushiGeta_full::new, woodDish());
	public static final DeferredBlock<Block> SUSHIGETA_fish = register("block_food_sushigeta_fish", SushiGeta_full::new, woodDish());
	public static final DeferredBlock<Block> SUSHIGETA_beef = register("block_food_sushigeta_beef", SushiGeta_full::new, woodDish());
	public static final DeferredBlock<Block> SUSHIGETA_tamago = register("block_food_sushigeta_tamago", SushiGeta_full::new, woodDish());

	public static final DeferredBlock<Block> SUSHISET_salmon = register("block_food_sushiset_salmon", SushiSet::new, woodDish());
	public static final DeferredBlock<Block> SUSHISET_fish = register("block_food_sushiset_fish", SushiSet::new, woodDish());
	public static final DeferredBlock<Block> SUSHISET_beef = register("block_food_sushiset_beef", SushiSet::new, woodDish());
	public static final DeferredBlock<Block> SUSHISET_tamago = register("block_food_sushiset_tamago", SushiSet::new, woodDish());
	public static final DeferredBlock<Block> SUSHISET_4shoku = register("block_food_sushiset_4shoku", SushiSet::new, woodDish());

	public static final DeferredBlock<Block> SUSHIOKE = register("block_food_sushioke_kara", SushiOke::new, woodDish());
	public static final DeferredBlock<Block> SUSHIOKE_FULL_1 = register("block_food_sushiokefull_1", SushiOkeFull_1::new, woodDish());
	public static final DeferredBlock<Block> SUSHIOKE_FULL_9 = register("block_food_sushiokefull_9", SushiOkeFull_9::new, woodDish());
	public static final DeferredBlock<Block> SHOUYUSARA_1 = register("block_food_shouyusara_1", ShouyuSara_1::new, stoneDish());

	public static final DeferredBlock<Block> KETTLE_kara = register("item_kettle_kara", Kettle_kara::new, metalDish());
	public static final DeferredBlock<Block> KETTLE_full = register("block_kettle_full", Kettle_full::new, metalDish());

	public static final DeferredBlock<Block> KYUSU = register("block_food_kyusu_1", JPTeaKyusu::new, stoneDish());
	public static final DeferredBlock<Block> JPTEACUP = register("block_food_jpteacup_1", JPTeaCup::new, stoneDish());
	public static final DeferredBlock<Block> JPTEASET = register("block_food_jpteaset_1", JPTea_Set::new, stoneDish());
	public static final DeferredBlock<Block> CHAUKE_SENBEI = register("block_food_senbei", Chauke_Senbei::new, woodDish());
	public static final DeferredBlock<Block> CHAUKE_MIKAN = register("block_food_mikan", Chauke_Mikan::new, woodDish());

	public static final DeferredBlock<Block> TEAPOT = register("block_food_teapot_1", TeaPot::new, stoneDish());
	public static final DeferredBlock<Block> TEACUP = register("block_food_teacup_1", TeaCup::new, stoneDish());
	public static final DeferredBlock<Block> TEASET = register("block_food_teaset_1", Tea_Set::new, stoneDish());
	public static final DeferredBlock<Block> CHAUKE_SCONE = register("block_food_scone", Chauke_Scone::new, stoneDish());

	public static final DeferredBlock<Block> SCONESET_kara = register("block_food_teastand", SconeSet_kara::new, metalDish());
	public static final DeferredBlock<Block> SCONESET_1 = register("block_food_sconeset_1", SconeSet_1::new, metalDish());
	public static final DeferredBlock<Block> ICECREAM = register("block_food_icecream_1", Ice_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> ICECREAM_GREEN = register("block_food_icecream_greentea", Ice_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> ICECREAM_RED = register("block_food_icecream_redtea", Ice_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> ICECREAM_CACAO = register("block_food_icecream_cacao", Ice_Pudding::new, stoneDish());

	public static final DeferredBlock<Block> CUSTARD_PUDDING = register("block_food_pudding_custard", Ice_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> GREENTEA_PUDDING = register("block_food_pudding_greentea", Ice_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> REDTEA_PUDDING = register("block_food_pudding_redtea", Ice_Pudding::new, stoneDish());
	public static final DeferredBlock<Block> CACAO_PUDDING = register("block_food_pudding_cacao", Ice_Pudding::new, stoneDish());

	public static final DeferredBlock<Block> NABEK_APPLE = register("block_food_nabek_apple", NabeKanten::new, stoneDish());
	public static final DeferredBlock<Block> NABEK_CHERRY = register("block_food_nabek_cherry", NabeKanten::new, stoneDish());
	public static final DeferredBlock<Block> NABEK_CITRUS = register("block_food_nabek_citrus", NabeKanten::new, stoneDish());
	public static final DeferredBlock<Block> NABEK_GRAPE = register("block_food_nabek_grape", NabeKanten::new, stoneDish());
	public static final DeferredBlock<Block> NABEK_MILK = register("block_food_nabek_milk", NabeKantenMilk::new, stoneDish());
	public static final DeferredBlock<Block> NABEK_YOKAN = register("block_food_nabek_yokan", NabeYokan::new, stoneDish());
	public static final DeferredBlock<Block> NABEK_MATCHA = register("block_food_nabek_matcha", NabeYokan::new, stoneDish());
	public static final DeferredBlock<Block> KANTEN_APPLE = register("block_food_kanten_apple", Kanten::new, stoneDish());
	public static final DeferredBlock<Block> KANTEN_CHERRY = register("block_food_kanten_cherry", Kanten::new, stoneDish());
	public static final DeferredBlock<Block> KANTEN_CITRUS = register("block_food_kanten_citrus", Kanten::new, stoneDish());
	public static final DeferredBlock<Block> KANTEN_GRAPE = register("block_food_kanten_grape", Kanten::new, stoneDish());
	public static final DeferredBlock<Block> KANTEN_MILK = register("block_food_kanten_milk", Kanten::new, stoneDish());
	public static final DeferredBlock<Block> YOKAN = register("block_food_yokan", Kanten::new, stoneDish());
	public static final DeferredBlock<Block> YOKAN_MATCHA = register("block_food_yokan_matcha", Kanten::new, stoneDish());
	
	public static final DeferredBlock<Block> IRORISAKANA_E1 = register("block_irori_sakana_e1", Irori_Sakana_E1::new, snowDish());
	public static final DeferredBlock<Block> IRORISAKANA_E2 = register("block_irori_sakana_e2", Irori_Sakana_E2::new, snowDish());
	public static final DeferredBlock<Block> IRORISAKANA_R1 = register("block_irori_sakana_r1", Irori_Sakana_R1::new, snowDish());
	public static final DeferredBlock<Block> IRORISAKANA_R2 = register("block_irori_sakana_r2", Irori_Sakana_R2::new, snowDish());
	public static final DeferredBlock<Block> IRORISAKANA_C = register("block_irori_sakana_c", Irori_Sakana_C::new, snowDish());

	
	/* Share variables */
	public static ToIntFunction<BlockState> litAntiShadow(int value) {
		return (state) -> { return value; };
	}
	
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties metalDish() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noCollission().strength(1.0F, 3.0F).sound(SoundType.METAL).noOcclusion()
				.isValidSpawn(Dish_Blocks::neverEntity).isSuffocating(Dish_Blocks::never);
	}
	
	private static Properties stoneDish() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noCollission().strength(1.0F, 3.0F).sound(SoundType.STONE).noOcclusion()
				.isValidSpawn(Dish_Blocks::neverEntity).isSuffocating(Dish_Blocks::never);
	}
	
	private static Properties woodDish() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()
				.isValidSpawn(Dish_Blocks::neverEntity).isSuffocating(Dish_Blocks::never);
	}
	
	private static Properties snowDish() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.SNOW).noOcclusion()
				.isValidSpawn(Dish_Blocks::neverEntity).isSuffocating(Dish_Blocks::never);
	}

	private static Properties stoneState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noCollission().strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion()
				.isValidSpawn(Dish_Blocks::neverEntity).isSuffocating(Dish_Blocks::never);
	}
	
	private static Properties grassRandom() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).noCollission().strength(1.0F).sound(SoundType.GRASS).noOcclusion()
				.isValidSpawn(Dish_Blocks::neverEntity).isSuffocating(Dish_Blocks::never).randomTicks();
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
