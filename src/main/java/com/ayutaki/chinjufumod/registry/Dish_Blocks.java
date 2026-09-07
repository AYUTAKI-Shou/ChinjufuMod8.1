package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.dish.*;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Dashi;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Shouyu;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Dish_Blocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChinjufuMod.MOD_ID);

	public static final RegistryObject<Block> OSAUCE_bot_14 = register("block_osauce_bot", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> OSAUCE_bot_24 = register("block_osauce_bot_2", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> OSAUCE_bot_34 = register("block_osauce_bot_3", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> OSAUCE_bot_44 = register("block_osauce_bot_4", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> MAYO_bot_14 = register("block_mayo_bot", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> MAYO_bot_24 = register("block_mayo_bot_2", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> MAYO_bot_34 = register("block_mayo_bot_3", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> MAYO_bot_44 = register("block_mayo_bot_4", () -> new Bot_Dashi(stoneState()));
	public static final RegistryObject<Block> SOYOIL_bot_12 = register("block_soyoil_bot", () -> new Bot_Shouyu(stoneState()));
	public static final RegistryObject<Block> SOYOIL_bot_22 = register("block_soyoil_bot_2", () -> new Bot_Shouyu(stoneState()));
	public static final RegistryObject<Block> TENGUSA_WASH = register("block_tengusawash", () -> new TengusaWashed(grassRandom()));
	
	public static final RegistryObject<Block> ZUNDOU = register("block_food_zundou", () -> new Zundou(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_MIZU = register("block_zundou_mizu", () -> new Zundou_Mizu(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_SHIO = register("block_zundou_shiomizu", () -> new Zundou_Shio(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_MILK = register("block_zundou_milk", () -> new Zundou_Milk(metalDish()));

	public static final RegistryObject<Block> ZUNDOU_NCURRY = register("block_food_cunabe_n", () -> new Zundou_NamaCurry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_CURRY = register("block_food_cunabe_1", () -> new Zundou4_Curry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_NCURRY_C = register("block_food_cunabe_cn", () -> new Zundou_NamaCurry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_CURRY_C = register("block_food_cunabe_c1", () -> new Zundou4_Curry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_NCURRY_T = register("block_food_cunabe_tn", () -> new Zundou_NamaCurry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_CURRY_T = register("block_food_cunabe_t1", () -> new Zundou4_Curry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_NSTEW = register("block_food_stewnabe_n", () -> new Zundou_NamaCurry(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_STEW = register("block_food_stewnabe_1", () -> new Zundou4_Stew(metalDish()));

	public static final RegistryObject<Block> ZUNDOU_FISH = register("block_zundou_fish", () -> new Zundou4_Fish(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_DASHI = register("block_food_dashinabe_1", () -> new Zundou4_Dashi(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_UDON = register("block_zundou_udon", () -> new Zundou4_Udon(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_PASTA = register("block_zundou_pasta", () -> new Zundou4_Pasta(metalDish()));
	
	public static final RegistryObject<Block> ZUNDOU_RSOUP_nama = register("block_food_rsoup_n", () -> new Zundou4_RSoup_nama(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_RSOUP = register("block_food_rsoup_1", () -> new Zundou4_RSoup(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_RAMEN = register("block_zundou_ramen", () -> new Zundou4_Ramen(metalDish()));

	public static final RegistryObject<Block> ZUNDOU_AKU = register("block_zundou_aku", () -> new Zundou_Aku(metalDish()));
	public static final RegistryObject<Block> ZUNDOU_ORIITO = register("block_zundou_oriito", () -> new Zundou4_Oriito(metalDish()));

	public static final RegistryObject<Block> NABE_kara = register("block_food_karanabe", () -> new Nabe_kara(stoneDish()));
	public static final RegistryObject<Block> NABETORI_nama = register("block_food_nabe_n", () -> new Nabe_NamaSoup(stoneDish()));
	public static final RegistryObject<Block> NABEMISO_nama = register("block_food_nabemiso_n", () -> new Nabe_NamaSoup(stoneDish()));
	public static final RegistryObject<Block> NABEGOHAN_nama = register("block_food_nabegohan_n", () -> new Nabe_NamaGohan(stoneDish()));
	public static final RegistryObject<Block> NABEGOHANTAKE_nama = register("block_food_nabegohantake_n", () -> new Nabe_NamaGohan(stoneDish()));
	public static final RegistryObject<Block> NABEGOHANKURI_nama = register("block_food_nabegohankuri_n", () -> new Nabe_NamaGohan(stoneDish()));
	public static final RegistryObject<Block> NABESEKIHAN_nama = register("block_food_nabesekihan_n", () -> new Nabe_NamaGohan(stoneDish()));
	public static final RegistryObject<Block> NABECORN_nama = register("block_food_nabecorns_n", () -> new Nabe_NamaSoup(stoneDish()));
	public static final RegistryObject<Block> NABESHIO_nama = register("block_food_nabeshio_n", () -> new Nabe2_TakeSude(stoneDish()));
	public static final RegistryObject<Block> NABENIMAME_nama = register("block_food_nabenimame_n", () -> new Nabe2_TakeBowl(stoneDish()));
	public static final RegistryObject<Block> NABETOUFU_nama = register("block_food_nabetoufu_n", () -> new Nabe2_TakeSude(stoneDish()));

	public static final RegistryObject<Block> NABEAZUKI_nama = register("block_food_nabeazuki_n", () -> new Nabe2_TakeSude(stoneDish()));
	public static final RegistryObject<Block> NABEANKO_nama = register("block_food_nabeanko_n", () -> new Nabe2_TakeBowl(stoneDish()));
	public static final RegistryObject<Block> NABEPUDDING_nama = register("block_food_nabepudding_n", () -> new Nabe2_Pudding(stoneDish()));
	public static final RegistryObject<Block> NABEPUDDING_green = register("block_food_nabepudding_g", () -> new Nabe2_Pudding(stoneDish()));
	public static final RegistryObject<Block> NABEPUDDING_red = register("block_food_nabepudding_r", () -> new Nabe2_Pudding(stoneDish()));
	public static final RegistryObject<Block> NABEPUDDING_cacao = register("block_food_nabepudding_c", () -> new Nabe2_Pudding(stoneDish()));
	public static final RegistryObject<Block> NABE_CREAM = register("block_food_nabecream", () -> new NabeCream(stoneDish()));
	public static final RegistryObject<Block> NABE_CREAM_sub = register("block_food_nabecream_sub", () -> new NabeCream(stoneDish()));
	public static final RegistryObject<Block> KURI_NABE_nama = register("block_food_nabekuri_n", () -> new Nabe2_Pudding(stoneDish()));
	public static final RegistryObject<Block> NABETENGUSA_nama = register("block_food_nabetengusa_n", () -> new NabeTengusa(stoneDish()));
	
	public static final RegistryObject<Block> NABETORI = register("block_food_nabe_1", () -> new NabeTori(stoneDish()));
	public static final RegistryObject<Block> NABEMISO = register("block_food_nabemiso_1", () -> new Nabe_Shikki(stoneDish()));
	public static final RegistryObject<Block> NABEZENZAI_M = register("block_food_nabezenzai_m", () -> new Nabe_Shikki(stoneDish()));
	public static final RegistryObject<Block> NABEZENZAI_K = register("block_food_nabezenzai_k", () -> new Nabe_Shikki(stoneDish()));
	public static final RegistryObject<Block> NABEGOHAN = register("block_food_nabegohan_1", () -> new NabeGohan(stoneDish()));
	public static final RegistryObject<Block> NABEGOHAN_TAKE = register("block_food_nabegohantake_1", () -> new NabeGohan_Other(stoneDish()));
	public static final RegistryObject<Block> NABEGOHAN_KURI = register("block_food_nabegohankuri_1", () -> new NabeGohan_Other(stoneDish()));
	public static final RegistryObject<Block> NABESEKIHAN = register("block_food_nabesekihan_1", () -> new NabeGohan_Other(stoneDish()));
	public static final RegistryObject<Block> NABECORN = register("block_food_nabecorns_1", () -> new NabeCorn(stoneDish()));

	public static final RegistryObject<Block> KEIRYO_CUP = register("block_measurecup", () -> new MeasureCup(stoneDish()));

	public static final RegistryObject<Block> FRYPAN_kara = register("block_food_frypan", () -> new Frypan_kara(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPTAMAGO_nama = register("block_food_frypan_n_tamago", () -> new Frypan_TakePlate(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPGYUDON_nama = register("block_food_frypan_n_gyudon", () -> new Frypan_DonNama(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPOYAKODON_nama = register("block_food_frypan_n_oyakodon", () -> new Frypan_DonNama(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPKATSU_nama = register("block_food_frypan_n_katsu", () -> new Frypan_Katsu(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPKATSUDON_nama = register("block_food_frypan_n_katsudon", () -> new Frypan_DonNama(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPEGGBURG_nama = register("block_food_frypan_n_eggb", () -> new Frypan_TakePlate(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPTOMATO_nama = register("block_food_frypan_n_tomatos", () -> new Frypan_PastaNama(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPKINOKO_nama = register("block_food_frypan_n_kinokos", () -> new Frypan_PastaNama(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPSEAFOOD_nama = register("block_food_frypan_n_seafood", () -> new Frypan_PastaNama(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPKINOKOAK_nama = register("block_food_frypan_n_kinokoak", () -> new Frypan_KinokoAK(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block> FPCURRY_nama = register("block_food_frypan_n_roux", () -> new Frypan_Curry(metalDish().lightLevel(litAntiShadow(1))));

	public static final RegistryObject<Block> FPOSAUCE_nama = register("block_food_frypan_n_osauce", () -> new Frypan_OSauce(metalDish().lightLevel(litAntiShadow(1))));
	public static final RegistryObject<Block>OKONOMIYAKI_nama = register("block_food_teppan_n_okonomiyaki", () -> new Teppan_4empty(snowDish()));
	public static final RegistryObject<Block>OKONOMIS_nama = register("block_food_teppan_n_okonomis", () -> new Teppan_4empty(snowDish()));
	public static final RegistryObject<Block>OKONOMIC_nama = register("block_food_teppan_n_okonomic", () -> new Teppan_4empty(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBA_nama = register("block_food_teppan_n_okonomisoba", () -> new Teppan_Stage4(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBAS_nama = register("block_food_teppan_n_okonomisobas", () -> new Teppan_Stage4(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBAC_nama = register("block_food_teppan_n_okonomisobac", () -> new Teppan_Stage4(snowDish()));
	public static final RegistryObject<Block>YAKISOBA_nama = register("block_food_teppan_n_yakisoba", () -> new Teppan_Stage4(snowDish()));
	public static final RegistryObject<Block>YAKISOBASHIO_nama = register("block_food_teppan_n_yakisobashio", () -> new Teppan_Stage4(snowDish()));
	
	public static final RegistryObject<Block>OKONOMIYAKI_click = register("block_food_teppan_c_okonomiyaki", () -> new Teppan_4emptyC(snowDish()));
	public static final RegistryObject<Block>OKONOMIS_click = register("block_food_teppan_c_okonomis", () -> new Teppan_4emptyC(snowDish()));
	public static final RegistryObject<Block>OKONOMIC_click = register("block_food_teppan_c_okonomic", () -> new Teppan_4emptyC(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBA_click = register("block_food_teppan_c_okonomisoba", () -> new Teppan_Stage4C(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBAS_click = register("block_food_teppan_c_okonomisobas", () -> new Teppan_Stage4C(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBAC_click = register("block_food_teppan_c_okonomisobac", () -> new Teppan_Stage4C(snowDish()));
	public static final RegistryObject<Block>YAKISOBA_click = register("block_food_teppan_c_yakisoba", () -> new Teppan_Stage4C(snowDish()));
	public static final RegistryObject<Block>YAKISOBASHIO_click = register("block_food_teppan_c_yakisobashio", () -> new Teppan_Stage4C(snowDish()));

	public static final RegistryObject<Block>OKONOMISOBA_5 = register("block_food_teppan_5_okonomisoba", () -> new Teppan_5th(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBAS_5 = register("block_food_teppan_5_okonomisobas", () -> new Teppan_5th(snowDish()));
	public static final RegistryObject<Block>OKONOMISOBAC_5 = register("block_food_teppan_5_okonomisobac", () -> new Teppan_5th(snowDish()));
	
	public static final RegistryObject<Block> NIBOSHI = register("block_niboshi", () -> new Niboshi(snowDish().randomTicks()));

	public static final RegistryObject<Block> CURRY = register("block_food_curry_1", () -> new Curry(stoneDish()));
	public static final RegistryObject<Block> CURRYSET = register("block_food_curryset_1", () -> new Curry_set(stoneDish()));
	public static final RegistryObject<Block> CURRY_C = register("block_food_curry_c1", () -> new Curry(stoneDish()));
	public static final RegistryObject<Block> CURRYSET_C = register("block_food_curryset_c1", () -> new Curry_set(stoneDish()));
	public static final RegistryObject<Block> CURRY_T = register("block_food_curry_t1", () -> new Curry(stoneDish()));
	public static final RegistryObject<Block> CURRYSET_T = register("block_food_curryset_t1", () -> new Curry_set(stoneDish()));
	public static final RegistryObject<Block> STEW = register("block_food_stew_1", () -> new Stew(stoneDish()));

	public static final RegistryObject<Block> UDON_SU = register("block_food_udonsu_1", () -> new Udon(stoneDish()));
	public static final RegistryObject<Block> UDON_NIKU = register("block_food_udonniku_1", () -> new Udon(stoneDish()));
	public static final RegistryObject<Block> UDON_TSUKIMI = register("block_food_udontsukimi_1", () -> new Udon(stoneDish()));
	
	public static final RegistryObject<Block> RAMEN_SHOUYU = register("block_food_ramenshouyu_1", () -> new Ramen(stoneDish()));
	public static final RegistryObject<Block> RAMEN_MISO = register("block_food_ramenmiso_1", () -> new Ramen(stoneDish()));
	public static final RegistryObject<Block> RAMEN_SHIO = register("block_food_ramenshio_1", () -> new Ramen(stoneDish()));

	public static final RegistryObject<Block> TONSUITORI = register("block_food_tonsui_1", () -> new Tonsui(stoneDish()));
	public static final RegistryObject<Block> MISOSOUP = register("block_food_misosp_1", () -> new Soup_Shikki(woodDish()));
	public static final RegistryObject<Block> ZENZAI_M = register("block_food_zenzai_m", () -> new Soup_Shikki(woodDish()));
	public static final RegistryObject<Block> ZENZAI_K = register("block_food_zenzai_k", () -> new Soup_Shikki(woodDish()));
	public static final RegistryObject<Block> GOHAN = register("block_food_gohan_1", () -> new Gohan(stoneDish()));
	public static final RegistryObject<Block> GOHAN_TAKE = register("block_food_gohantake_1", () -> new Gohan(stoneDish()));
	public static final RegistryObject<Block> GOHAN_KURI = register("block_food_gohankuri_1", () -> new Gohan(stoneDish()));
	public static final RegistryObject<Block> SEKIHAN = register("block_food_sekihan", () -> new Gohan(stoneDish()));
	public static final RegistryObject<Block> RICE = register("block_food_rice_1", () -> new RiceDish(stoneDish()));
	public static final RegistryObject<Block> DONBURI_MESHI = register("block_food_donmeshi_1", () -> new Donburi(stoneDish()));

	public static final RegistryObject<Block> DONBURI_GYU = register("block_food_dongyu_1", () -> new Donburi(stoneDish()));
	public static final RegistryObject<Block> DONBURI_OYAKO = register("block_food_donoyako_1", () -> new Donburi(stoneDish()));
	public static final RegistryObject<Block> DONBURI_KATSU = register("block_food_donkatsu_1", () -> new Donburi(stoneDish()));
	public static final RegistryObject<Block> DONBURI_KAISEN = register("block_food_donkaisen_1", () -> new Donburi(stoneDish()));
	
	public static final RegistryObject<Block> HAKUSAIDUKE = register("block_food_hsd_1", () -> new Hakusai_Duke(stoneDish()));
	public static final RegistryObject<Block> TAMAGOYAKI = register("block_food_tgy_1", () -> new Tamagoyaki(stoneDish()));
	
	public static final RegistryObject<Block> TAMAGOYAKITEI = register("block_food_tgytei_1", () -> new Tamagoyaki_set(stoneDish()));
	public static final RegistryObject<Block> YAKIZAKANATEI = register("block_food_yakizakanatei_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> YAKIJYAKETEI = register("block_food_yakijyaketei_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> TAMAGOYAKITEI_TAKE = register("block_food_tgyteitake_1", () -> new Tamagoyaki_set(stoneDish()));
	public static final RegistryObject<Block> YAKIZAKANATEI_TAKE = register("block_food_yakizakanateitake_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> YAKIJYAKETEI_TAKE = register("block_food_yakijyaketeitake_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> TAMAGOYAKITEI_KURI = register("block_food_tgyteikuri_1", () -> new Tamagoyaki_set(stoneDish()));
	public static final RegistryObject<Block> YAKIZAKANATEI_KURI = register("block_food_yakizakanateikuri_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> YAKIJYAKETEI_KURI = register("block_food_yakijyaketeikuri_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> TAMAGOYAKITEI_SEKI = register("block_food_tgytei_sekihan_1", () -> new Tamagoyaki_set(stoneDish()));
	public static final RegistryObject<Block> YAKIZAKANATEI_SEKI = register("block_food_yakizakanatei_sekihan_1", () -> new YakiZakana_set(stoneDish()));
	public static final RegistryObject<Block> YAKIJYAKETEI_SEKI = register("block_food_yakijyaketei_sekihan_1", () -> new YakiZakana_set(stoneDish()));

	public static final RegistryObject<Block> CORNSOUP = register("block_food_cornsp_1", () -> new Corn_Soup(stoneDish()));
	public static final RegistryObject<Block> EGGBURG = register("block_food_egb_1", () -> new EggBurg(stoneDish()));
	public static final RegistryObject<Block> EGGBURGSET = register("block_food_egbset_1", () -> new EggBurg_set(stoneDish()));

	public static final RegistryObject<Block> PASTATOMATO = register("block_food_pastatoma_1", () -> new Pasta(stoneDish()));
	public static final RegistryObject<Block> PASTACHEESE = register("block_food_pastacheese_1", () -> new Pasta(stoneDish()));
	public static final RegistryObject<Block> PASTAKINOKO = register("block_food_pastakinoko_1", () -> new Pasta(stoneDish()));
	public static final RegistryObject<Block> PASTASEAFOOD = register("block_food_pastaseafood_1", () -> new PastaSeafood(stoneDish()));
	public static final RegistryObject<Block> PIZZA = register("block_food_pizza_1", () -> new Pizza_5(woodDish()));
	public static final RegistryObject<Block> PIZZA_C = register("block_food_pizza_c1", () -> new Pizza_5(woodDish()));
	public static final RegistryObject<Block> PIZZA_T = register("block_food_pizza_t1", () -> new Pizza_5(woodDish()));
	public static final RegistryObject<Block> PIZZA_S = register("block_food_pizza_s1", () -> new Pizza_5(woodDish()));
	public static final RegistryObject<Block> OKONOMIYAKI = register("block_food_okonomiyaki_1", () -> new Okonomiyaki(stoneDish()));
	public static final RegistryObject<Block> OKONOMIS = register("block_food_okonomis_1", () -> new Okonomiyaki(stoneDish()));
	public static final RegistryObject<Block> OKONOMIC = register("block_food_okonomic_1", () -> new Okonomiyaki(stoneDish()));
	public static final RegistryObject<Block> OKONOMISOBA = register("block_food_okonomisoba_1", () -> new Okonomiyaki(stoneDish()));
	public static final RegistryObject<Block> OKONOMISOBAS = register("block_food_okonomisobas_1", () -> new Okonomiyaki(stoneDish()));
	public static final RegistryObject<Block> OKONOMISOBAC = register("block_food_okonomisobac_1", () -> new Okonomiyaki(stoneDish()));
	public static final RegistryObject<Block> YAKISOBA = register("block_food_yakisoba_1", () -> new Pasta(stoneDish()));
	public static final RegistryObject<Block> YAKISOBASHIO = register("block_food_yakisobashio_1", () -> new Pasta(stoneDish()));

	public static final RegistryObject<Block> CHICKEN = register("block_food_roastchicken_1", () -> new Chicken_Roast(stoneDish()));
	public static final RegistryObject<Block> CHICKEN_small = register("block_food_chickenb_1", () -> new Chicken_small(stoneDish()));

	public static final RegistryObject<Block> SUSHIMESHI = register("block_food_sushimeshi", () -> new SushiMeshi(woodDish()));
	public static final RegistryObject<Block> SUSHIGETA_kara = register("block_food_sushigeta_kara", () -> new SushiGeta_kara1_5(woodDish()));
	public static final RegistryObject<Block> SUSHIGETA_salmon = register("block_food_sushigeta_salmon", () -> new SushiGeta_full(woodDish()));
	public static final RegistryObject<Block> SUSHIGETA_fish = register("block_food_sushigeta_fish", () -> new SushiGeta_full(woodDish()));
	public static final RegistryObject<Block> SUSHIGETA_beef = register("block_food_sushigeta_beef", () -> new SushiGeta_full(woodDish()));
	public static final RegistryObject<Block> SUSHIGETA_tamago = register("block_food_sushigeta_tamago", () -> new SushiGeta_full(woodDish()));

	public static final RegistryObject<Block> SUSHISET_salmon = register("block_food_sushiset_salmon", () -> new SushiSet(woodDish()));
	public static final RegistryObject<Block> SUSHISET_fish = register("block_food_sushiset_fish", () -> new SushiSet(woodDish()));
	public static final RegistryObject<Block> SUSHISET_beef = register("block_food_sushiset_beef", () -> new SushiSet(woodDish()));
	public static final RegistryObject<Block> SUSHISET_tamago = register("block_food_sushiset_tamago", () -> new SushiSet(woodDish()));
	public static final RegistryObject<Block> SUSHISET_4shoku = register("block_food_sushiset_4shoku", () -> new SushiSet(woodDish()));

	public static final RegistryObject<Block> SUSHIOKE = register("block_food_sushioke_kara", () -> new SushiOke(woodDish()));
	public static final RegistryObject<Block> SUSHIOKE_FULL_1 = register("block_food_sushiokefull_1", () -> new SushiOkeFull_1(woodDish()));
	public static final RegistryObject<Block> SUSHIOKE_FULL_9 = register("block_food_sushiokefull_9", () -> new SushiOkeFull_9(woodDish()));
	public static final RegistryObject<Block> SHOUYUSARA_1 = register("block_food_shouyusara_1", () -> new ShouyuSara_1(stoneDish()));

	public static final RegistryObject<Block> KETTLE_kara = register("item_kettle_kara", () -> new Kettle_kara(metalDish()));
	public static final RegistryObject<Block> KETTLE_full = register("block_kettle_full", () -> new Kettle_full(metalDish()));

	public static final RegistryObject<Block> KYUSU = register("block_food_kyusu_1", () -> new JPTeaKyusu(stoneDish()));
	public static final RegistryObject<Block> JPTEACUP = register("block_food_jpteacup_1", () -> new JPTeaCup(stoneDish()));
	public static final RegistryObject<Block> JPTEASET = register("block_food_jpteaset_1", () -> new JPTea_Set(stoneDish()));
	public static final RegistryObject<Block> CHAUKE_SENBEI = register("block_food_senbei", () -> new Chauke_Senbei(woodDish()));
	public static final RegistryObject<Block> CHAUKE_MIKAN = register("block_food_mikan", () -> new Chauke_Mikan(woodDish()));

	public static final RegistryObject<Block> TEAPOT = register("block_food_teapot_1", () -> new TeaPot(stoneDish()));
	public static final RegistryObject<Block> TEACUP = register("block_food_teacup_1", () -> new TeaCup(stoneDish()));
	public static final RegistryObject<Block> TEASET = register("block_food_teaset_1", () -> new Tea_Set(stoneDish()));
	public static final RegistryObject<Block> CHAUKE_SCONE = register("block_food_scone", () -> new Chauke_Scone(stoneDish()));

	public static final RegistryObject<Block> SCONESET_kara = register("block_food_teastand", () -> new SconeSet_kara(metalDish()));
	public static final RegistryObject<Block> SCONESET_1 = register("block_food_sconeset_1", () -> new SconeSet_1(metalDish()));
	public static final RegistryObject<Block> ICECREAM = register("block_food_icecream_1", () -> new Ice_Pudding(stoneDish()));
	public static final RegistryObject<Block> ICECREAM_GREEN = register("block_food_icecream_greentea", () -> new Ice_Pudding(stoneDish()));
	public static final RegistryObject<Block> ICECREAM_RED = register("block_food_icecream_redtea", () -> new Ice_Pudding(stoneDish()));
	public static final RegistryObject<Block> ICECREAM_CACAO = register("block_food_icecream_cacao", () -> new Ice_Pudding(stoneDish()));

	public static final RegistryObject<Block> CUSTARD_PUDDING = register("block_food_pudding_custard", () -> new Ice_Pudding(stoneDish()));
	public static final RegistryObject<Block> GREENTEA_PUDDING = register("block_food_pudding_greentea", () -> new Ice_Pudding(stoneDish()));
	public static final RegistryObject<Block> REDTEA_PUDDING = register("block_food_pudding_redtea", () -> new Ice_Pudding(stoneDish()));
	public static final RegistryObject<Block> CACAO_PUDDING = register("block_food_pudding_cacao", () -> new Ice_Pudding(stoneDish()));

	public static final RegistryObject<Block> NABEK_APPLE = register("block_food_nabek_apple", () -> new NabeKanten(stoneDish()));
	public static final RegistryObject<Block> NABEK_CHERRY = register("block_food_nabek_cherry", () -> new NabeKanten(stoneDish()));
	public static final RegistryObject<Block> NABEK_CITRUS = register("block_food_nabek_citrus", () -> new NabeKanten(stoneDish()));
	public static final RegistryObject<Block> NABEK_GRAPE = register("block_food_nabek_grape", () -> new NabeKanten(stoneDish()));
	public static final RegistryObject<Block> NABEK_MILK = register("block_food_nabek_milk", () -> new NabeKantenMilk(stoneDish()));
	public static final RegistryObject<Block> NABEK_YOKAN = register("block_food_nabek_yokan", () -> new NabeYokan(stoneDish()));
	public static final RegistryObject<Block> NABEK_MATCHA = register("block_food_nabek_matcha", () -> new NabeYokan(stoneDish()));
	public static final RegistryObject<Block> KANTEN_APPLE = register("block_food_kanten_apple", () -> new Kanten(stoneDish()));
	public static final RegistryObject<Block> KANTEN_CHERRY = register("block_food_kanten_cherry", () -> new Kanten(stoneDish()));
	public static final RegistryObject<Block> KANTEN_CITRUS = register("block_food_kanten_citrus", () -> new Kanten(stoneDish()));
	public static final RegistryObject<Block> KANTEN_GRAPE = register("block_food_kanten_grape", () -> new Kanten(stoneDish()));
	public static final RegistryObject<Block> KANTEN_MILK = register("block_food_kanten_milk", () -> new Kanten(stoneDish()));
	public static final RegistryObject<Block> YOKAN = register("block_food_yokan", () -> new Kanten(stoneDish()));
	public static final RegistryObject<Block> YOKAN_MATCHA = register("block_food_yokan_matcha", () -> new Kanten(stoneDish()));
	
	public static final RegistryObject<Block> IRORISAKANA_E1 = register("block_irori_sakana_e1", () -> new Irori_Sakana_E1(snowDish()));
	public static final RegistryObject<Block> IRORISAKANA_E2 = register("block_irori_sakana_e2", () -> new Irori_Sakana_E2(snowDish()));
	public static final RegistryObject<Block> IRORISAKANA_R1 = register("block_irori_sakana_r1", () -> new Irori_Sakana_R1(snowDish()));
	public static final RegistryObject<Block> IRORISAKANA_R2 = register("block_irori_sakana_r2", () -> new Irori_Sakana_R2(snowDish()));
	public static final RegistryObject<Block> IRORISAKANA_C = register("block_irori_sakana_c", () -> new Irori_Sakana_C(snowDish()));


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
	private static RegistryObject<Block> register(String name, Supplier<Block> block) {
		return BLOCKS.register(name, block);
	}
}
