package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.items.addinfo.AddInfoBlock_NT;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_NT;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_TT;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.items.dish.Dish_Chawan;
import com.ayutaki.chinjufumod.items.dish.Dish_Donburi;
import com.ayutaki.chinjufumod.items.dish.Dish_Kanten;
import com.ayutaki.chinjufumod.items.dish.Dish_LUCK;
import com.ayutaki.chinjufumod.items.dish.Dish_Plate;
import com.ayutaki.chinjufumod.items.dish.Dish_Shikki;
import com.ayutaki.chinjufumod.items.dish.Dish_Tonsui;
import com.ayutaki.chinjufumod.items.dish.DonabeKara_TT;
import com.ayutaki.chinjufumod.items.dish.TeaCup_TT;
import com.ayutaki.chinjufumod.items.dish.Yunomi_TT;
import com.ayutaki.chinjufumod.items.dish.ZundouKara_TT;
import com.ayutaki.chinjufumod.items.foods.Bentou;
import com.ayutaki.chinjufumod.items.foods.FoodBuilders;
import com.ayutaki.chinjufumod.items.foods.FoodNeed_TT;
import com.ayutaki.chinjufumod.items.foods.Food_Cherry;
import com.ayutaki.chinjufumod.items.fuel.Teatime_Fuel100;
import com.ayutaki.chinjufumod.items.fuel.Teatime_Fuel150;
import com.ayutaki.chinjufumod.items.fuel.Teatime_Fuel200;
import com.ayutaki.chinjufumod.items.fuel.Teatime_Fuel300;
import com.ayutaki.chinjufumod.items.fuel.Teatime_noFuel;
import com.ayutaki.chinjufumod.items.hakkou.HakkouTips_NT;
import com.ayutaki.chinjufumod.items.hakkou.MizuokeMilk_TT;
import com.ayutaki.chinjufumod.items.hakkou.Mizuoke_TT;
import com.ayutaki.chinjufumod.items.hakkou.SakeGlass;
import com.ayutaki.chinjufumod.items.teatime.AmiShikake_TT;
import com.ayutaki.chinjufumod.items.teatime.AmiYoushoku_TT;
import com.ayutaki.chinjufumod.items.teatime.Kaihori_TT;
import com.ayutaki.chinjufumod.items.teatime.KineYoko_TT;
import com.ayutaki.chinjufumod.items.teatime.Match_TT;
import com.ayutaki.chinjufumod.items.teatime.MeasureCupFull_TT;
import com.ayutaki.chinjufumod.items.teatime.MeasureCup_TT;
import com.ayutaki.chinjufumod.items.teatime.Tengusa_TT;
import com.ayutaki.chinjufumod.items.teatime.Toami_TT;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items_Teatime {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);
	
	/* SEEDS, CROPS */
	public static final RegistryObject<Item> SEEDSBOX = register("block_seedsbox", () -> new Teatime_noFuel(Crop_Blocks.SEEDSBOX.get(), new Item.Properties()));

	public static final RegistryObject<Item> SEEDS_AZUKI = register("item_seeds_azuki", () -> new Teatime_noFuel(Crop_Blocks.AZUKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_CABBAGE = register("item_seeds_cabbage", () -> new Teatime_noFuel(Crop_Blocks.CABBAGE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_HAKUSAI = register("item_seeds_hakusai", () -> new Teatime_noFuel(Crop_Blocks.HAKUSAI.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_CORN = register("item_seeds_corn", () -> new Teatime_noFuel(Crop_Blocks.CORN.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_GREENONION = register("item_seeds_greenonion", () -> new Teatime_noFuel(Crop_Blocks.GREENONION.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_ONION = register("item_seeds_onion", () -> new Teatime_noFuel(Crop_Blocks.ONION.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_RICE = register("item_seeds_rice", () -> new Teatime_noFuel(Crop_Blocks.RICE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_SOY = register("item_seeds_soy", () -> new Teatime_noFuel(Crop_Blocks.SOY.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_SPINACH = register("item_seeds_spinach", () -> new Teatime_noFuel(Crop_Blocks.SPINACH.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_TOMATO = register("item_seeds_tomato", () -> new Teatime_noFuel(Crop_Blocks.TOMATO.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_CHERRY = register("item_seeds_cherry", () -> new Teatime_noFuel(Crop_Blocks.SAKURA.get(), new Item.Properties()));

	public static final RegistryObject<Item> CHANOKI = register("block_wood_chanoki_nae", () -> new Teatime_noFuel(Crop_Blocks.CHANOKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> BUDOUNOKI = register("block_wood_grape_nae", () -> new Teatime_noFuel(Crop_Blocks.BUDOUNOKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> MIKAN_NAE = register("block_wood_mikan", () -> new Teatime_noFuel(Crop_Blocks.MIKAN.get(), new Item.Properties()));
	public static final RegistryObject<Item> HODAGI = register("block_hodagi_a_bot", () -> new Teatime_noFuel(Crop_Blocks.HODAGI_A_BOT.get(), new Item.Properties()));

	public static final RegistryObject<Item> SEEDS_PEPPER = register("item_seeds_pepper", () -> new Teatime_noFuel(Crop_Blocks.PEPPER.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_CUMIN = register("item_seeds_cumin", () -> new Teatime_noFuel(Crop_Blocks.CUMIN.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_TURMERIC = register("item_seeds_turmeric", () -> new Teatime_noFuel(Crop_Blocks.TURMERIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_CHILI = register("item_seeds_chilipepper", () -> new Teatime_noFuel(Crop_Blocks.CHILI.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEEDS_VANILLA = register("item_seeds_vanilla", () -> new Teatime_noFuel(Crop_Blocks.VANILLA.get(), new Item.Properties()));
	public static final RegistryObject<Item> CROP_TENGUSA = register("item_crop_tengusa", () -> new Tengusa_TT(Crop_Blocks.TENGUSA.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> CHABA = register("item_chaba", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> CHABA_GREEN = register("item_chaba_green", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> CHABA_RED = register("item_chaba_red", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> TENCHA = register("item_chaba_tencha", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> MATCHA = register("item_chaba_matcha", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> TENGUSA_WASH = register("item_crop_tengusawash", () -> new Teatime_noFuel(Dish_Blocks.TENGUSA_WASH.get(), new Item.Properties()));
	public static final RegistryObject<Item> TENGUSA_DRY = register("item_crop_tengusadry", () -> new IG_Teatime(new Item.Properties()));

	public static final RegistryObject<Item> INAGI = register("block_inagi", () -> new Teatime_noFuel(Crop_Blocks.INAGI.get(), new Item.Properties()));
	public static final RegistryObject<Item> INEWARA = register("item_inewara", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> INE = register("item_ine", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items_Teatime.INEWARA.get())));
	public static final RegistryObject<Item> INE_D = register("item_ine_dry", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items_Teatime.INEWARA.get())));
	public static final RegistryObject<Item> KOME = register("item_kome", () -> new IG_Teatime(new Item.Properties()));

	public static final RegistryObject<Item> SAYA = register("item_saya", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> SOY = register("item_soy", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> PEPPER_RAW = register("item_crop_pepper", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> PEPPER_DRY = register("item_crop_pepperdry", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CHILIPEPPER = register("item_crop_chilipepper", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> DUST_PEPPER = register("item_dust_blackpepper", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> DUST_CUMIN = register("item_dust_cumin", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> DUST_TURMERIC = register("item_dust_turmeric", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> DUST_CHILI = register("item_dust_chili", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> CURRY_ROUX = register("item_curry_roux", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> VANILLA_RAW = register("item_vanillabeans", () -> new AddInfo_TT(new Item.Properties()));

	public static final RegistryObject<Item> FOOD_CABBAGE = register("item_food_cabbage", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.CABBAGE)));
	public static final RegistryObject<Item> FOOD_HAKUSAI = register("item_food_hakusai", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.HAKUSAI)));
	public static final RegistryObject<Item> FOOD_CHERRY = register("item_food_cherry", () -> new Food_Cherry(new Item.Properties()));
	public static final RegistryObject<Item> FOOD_MIKAN = register("item_food_mikan", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.MIKAN)));
	public static final RegistryObject<Item> FOOD_CORN = register("item_food_corn", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.CORN)));
	public static final RegistryObject<Item> FOOD_GRAPE = register("item_food_grape", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.GRAPE)));
	public static final RegistryObject<Item> FOOD_GREENONION = register("item_food_greenonion", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.GREENONION)));
	public static final RegistryObject<Item> FOOD_ONION = register("item_food_onion", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.ONION)));
	public static final RegistryObject<Item> FOOD_SPINACH = register("item_food_spinach", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SPINACH)));
	public static final RegistryObject<Item> FOOD_TOMATO = register("item_food_tomato", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.TOMATO)));

	public static final RegistryObject<Item> FOOD_CORN_B = register("item_food_cornb", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.CORN_B)));

	public static final RegistryObject<Item> BOX_H_EMPTY = register("block_boxh_empty", () -> new Teatime_noFuel(Pantry_Blocks.BOX_H_EMPTY.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_APPLE = register("block_boxh_apple", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_APPLE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_BEEF = register("block_boxh_beef", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_BEEF.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_BEETROOT = register("block_boxh_beetroot", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_BEETROOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_BREAD = register("block_boxh_bread", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_BREAD.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CARROT = register("block_boxh_carrot", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CARROT.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CHICKEN = register("block_boxh_chicken", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CHICKEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CHORUS = register("block_boxh_chorus", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CHORUS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_COCO = register("block_boxh_coco", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_COCO.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_COD = register("block_boxh_cod", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_COD.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_EGG = register("block_boxh_egg", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_EGG.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_FLOUR = register("block_boxh_flour", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_FLOUR.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_MUTTON = register("block_boxh_mutton", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_MUTTON.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_PORK = register("block_boxh_pork", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_PORK.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_POTATO = register("block_boxh_potato", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_POTATO.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_RABBIT = register("block_boxh_rabbit", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_RABBIT.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_SALMON = register("block_boxh_salmon", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_SALMON.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_SWBERRY = register("block_boxh_swberry", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_SWBERRY.get(), new Item.Properties()));

	public static final RegistryObject<Item> BOX_H_AZUKI = register("block_boxh_azuki", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_AZUKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CABBAGE = register("block_boxh_cabbage", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CABBAGE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_HAKUSAI = register("block_boxh_hakusai", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_HAKUSAI.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CITRUS = register("block_boxh_citrus", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CITRUS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CORN = register("block_boxh_corn", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CORN.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_GRAPE = register("block_boxh_grape", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_GRAPE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_GREENONION = register("block_boxh_greenonion", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_GREENONION.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_ONION = register("block_boxh_onion", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_ONION.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_ORIENTCLAM = register("block_boxh_hamaguri", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_ORIENTCLAM.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_RICE = register("block_boxh_rice", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_RICE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_SOY = register("block_boxh_soy", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_SOY.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_SPINACH = register("block_boxh_spinach", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_SPINACH.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_SQUID = register("block_boxh_squid", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_SQUID.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_TOMATO = register("block_boxh_tomato", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_TOMATO.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CHERRY = register("block_boxh_cherry", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CHERRY.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_TAKENOKO = register("block_boxh_takenoko", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_TAKENOKO.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_KURI = register("block_boxh_chestnut", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_KURI.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_TGREEN = register("block_boxh_tgreen", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_TGREEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_TRED = register("block_boxh_tred", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_TRED.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> BOX_H_BPEPPER = register("block_boxh_bpepper", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_BPEPPER.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CUMIN = register("block_boxh_cumin", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CUMIN.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_TURMERIC = register("block_boxh_turmeric", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_TURMERIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOX_H_CHILI = register("block_boxh_chili", () -> new Teatime_Fuel100(Pantry_Blocks.BOX_H_CHILI.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> CHADUTSU = register("block_tea_chadutsu", () -> new Teatime_noFuel(Pantry_Blocks.CHADUTSU.get(), new Item.Properties()));
	public static final RegistryObject<Item> CANTEA = register("block_tea_can", () -> new Teatime_noFuel(Pantry_Blocks.CANTEA.get(), new Item.Properties()));
	public static final RegistryObject<Item> TAWARA = register("block_tawara_cm", () -> new Teatime_Fuel200(Pantry_Blocks.TAWARA.get(), new Item.Properties()));

	/* Barrel */
	public static final RegistryObject<Item> KOUBOBOT_full = register("block_bin_koubo_f", () -> new AddInfoBlock_NT(Hakkou_Blocks.KOUBOBOT_full.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME)));
	public static final RegistryObject<Item> NYUSANBOT_full = register("block_bin_nyusan_f", () -> new AddInfoBlock_NT(Hakkou_Blocks.NYUSANBOT_full.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME)));
	public static final RegistryObject<Item> KOUBO = register("item_koubo", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> NYUSAN = register("item_nyusan", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));

	public static final RegistryObject<Item> MIZUOKE = register("block_mizuoke", () -> new Mizuoke_TT(Fluids.EMPTY, Hakkou_Blocks.MIZUOKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> MIZUOKE_full = register("block_mizuoke_full", () -> new Mizuoke_TT(Fluids.WATER, Hakkou_Blocks.MIZUOKE_full.get(), new Item.Properties().craftRemainder(Items_Teatime.MIZUOKE.get())));
	public static final RegistryObject<Item> MIZUOKE_Milk = register("item_mizuoke_milk", () -> new MizuokeMilk_TT(new Item.Properties().craftRemainder(Items_Teatime.MIZUOKE.get())));

	public static final RegistryObject<Item> HAKKOU_TARU = register("block_taru_hakkou", () -> new Teatime_noFuel(Hakkou_Blocks.HAKKOU_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUJI_TARU = register("block_taru_kouji_f", () -> new Teatime_noFuel(Hakkou_Blocks.KOUJI_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHUBO_TARU = register("block_taru_shubo_f", () -> new Teatime_noFuel(Hakkou_Blocks.SHUBO_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOROMI_TARU = register("block_taru_moromi_f", () -> new Teatime_noFuel(Hakkou_Blocks.MOROMI_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUSEI_TARU = register("block_taru_jukusei_f", () -> new Teatime_noFuel(Hakkou_Blocks.JUKUSEI_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> RINGOSHU_TARU = register("block_taru_ringoshu_f", () -> new Teatime_noFuel(Hakkou_Blocks.RINGOSHU_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> BUDOUSHU_TARU = register("block_taru_budoushu_f", () -> new Teatime_noFuel(Hakkou_Blocks.BUDOUSHU_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> HACHIMITSU_TARU = register("block_taru_hachimitsushu_f", () -> new Teatime_noFuel(Hakkou_Blocks.HACHIMITSU_TARU.get(), new Item.Properties()));

	/* Bottle */
	public static final RegistryObject<Item> NAMASAKEBOT = register("block_bot_sakenama_1", () -> new Teatime_noFuel(Hakkou_Blocks.NAMASAKEBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> SAKEBOT = register("block_bot_sake_1", () -> new Teatime_noFuel(Hakkou_Blocks.SAKEBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUSAKEBOT = register("block_bot_sakejuku_1", () -> new Teatime_noFuel(Hakkou_Blocks.JUKUSAKEBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEAMAZAKE_nama = register("block_food_nabeaz_n", () -> new Teatime_noFuel(Hakkou_Blocks.NABEAMAZAKE_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEAMAZAKE = register("block_food_nabeaz_1", () -> new Teatime_noFuel(Hakkou_Blocks.NABEAMAZAKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CIDERBOT = register("block_bot_cider_1", () -> new Teatime_noFuel(Hakkou_Blocks.CIDERBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUCIDERBOT = register("block_bot_ciderjuku_1", () -> new Teatime_noFuel(Hakkou_Blocks.JUKUCIDERBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINEBOT = register("block_bot_wine_1", () -> new Teatime_noFuel(Hakkou_Blocks.WINEBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUWINEBOT = register("block_bot_winejuku_1", () -> new Teatime_noFuel(Hakkou_Blocks.JUKUWINEBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> MEADBOT = register("block_bot_mead_1", () -> new Teatime_noFuel(Hakkou_Blocks.MEADBOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUMEADBOT = register("block_bot_meadjuku_1", () -> new Teatime_noFuel(Hakkou_Blocks.JUKUMEADBOT.get(), new Item.Properties()));

	/* Glass */
	public static final RegistryObject<Item> NAMASAKEGLASS = register("block_glass_sakenama", () -> new SakeGlass(Hakkou_Blocks.NAMASAKEGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SAKEGLASS = register("block_glass_sake", () -> new SakeGlass(Hakkou_Blocks.SAKEGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUSAKEGLASS = register("block_glass_sakejuku", () -> new SakeGlass(Hakkou_Blocks.JUKUSAKEGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> AMAZAKEGLASS = register("block_glass_amazake", () -> new Yunomi_TT(Hakkou_Blocks.AMAZAKEGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CIDERGLASS = register("block_glass_cider", () -> new SakeGlass(Hakkou_Blocks.CIDERGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUCIDERGLASS = register("block_glass_ciderjuku", () -> new SakeGlass(Hakkou_Blocks.JUKUCIDERGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINEGLASS = register("block_glass_wine", () -> new SakeGlass(Hakkou_Blocks.WINEGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUWINEGLASS = register("block_glass_winejuku", () -> new SakeGlass(Hakkou_Blocks.JUKUWINEGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> MEADGLASS = register("block_glass_mead", () -> new SakeGlass(Hakkou_Blocks.MEADGLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUKUMEADGLASS = register("block_glass_meadjuku", () -> new SakeGlass(Hakkou_Blocks.JUKUMEADGLASS.get(), new Item.Properties()));

	public static final RegistryObject<Item> WINE_TANA = register("block_kit2_tana", () -> new Teatime_Fuel150(Hakkou_Blocks.WINE_TANA.get(), new Item.Properties()));

	public static final RegistryObject<Item> MISO_TARU = register("block_taru_miso_f", () -> new Teatime_noFuel(Hakkou_Blocks.MISO_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> HAKUSAI_TARU1 = register("block_taru_hakusai_f", () -> new Teatime_noFuel(Hakkou_Blocks.HAKUSAI_TARU1.get(), new Item.Properties()));
	public static final RegistryObject<Item> HAKUSAI_TARU2 = register("block_taru_hakusai_f2", () -> new Teatime_noFuel(Hakkou_Blocks.HAKUSAI_TARU2.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUYU_TARU = register("block_taru_shouyu_f", () -> new Teatime_noFuel(Hakkou_Blocks.SHOUYU_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOMEZU_TARU = register("block_taru_komezu_f", () -> new Teatime_noFuel(Hakkou_Blocks.KOMEZU_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> KINOKO_TARU = register("block_taru_kinoko_f", () -> new Teatime_noFuel(Hakkou_Blocks.KINOKO_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> KONBU_TARU = register("block_taru_konbu_f", () -> new Teatime_noFuel(Hakkou_Blocks.KONBU_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> NORI_TARU = register("block_taru_nori_f", () -> new Teatime_noFuel(Hakkou_Blocks.NORI_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUCHA_TARU = register("block_taru_koucha_f", () -> new Teatime_noFuel(Hakkou_Blocks.KOUCHA_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> PEPPER_TARU = register("block_taru_pepper_f", () -> new Teatime_noFuel(Hakkou_Blocks.PEPPER_TARU.get(), new Item.Properties()));
	public static final RegistryObject<Item> VANILLA_TARU = register("block_taru_vanilla_f", () -> new Teatime_noFuel(Hakkou_Blocks.VANILLA_TARU.get(), new Item.Properties()));

	public static final RegistryObject<Item> SHOUYU_bot_14 = register("block_shouyu_bot", () -> new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_14.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.SHOUYU_bot_24.get())));
	public static final RegistryObject<Item> KOMEZU_bot_12 = register("block_komezu_bot", () -> new HakkouTips_NT(Hakkou_Blocks.KOMEZU_bot_12.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.KOMEZU_bot_22.get())));
	public static final RegistryObject<Item> SOYOIL_bot_12 = register("block_soyoil_bot", () -> new HakkouTips_NT(Dish_Blocks.SOYOIL_bot_12.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.SOYOIL_bot_22.get())));
	public static final RegistryObject<Item> DASHI_bot_14 = register("block_dashi_bot", () -> new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_14.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.DASHI_bot_24.get())));
	public static final RegistryObject<Item> OSAUCE_bot_14 = register("block_osauce_bot", () -> new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_14.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.OSAUCE_bot_24.get())));
	public static final RegistryObject<Item> MAYO_bot_14 = register("block_mayo_bot", () -> new HakkouTips_NT(Dish_Blocks.MAYO_bot_14.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.MAYO_bot_24.get())));
	public static final RegistryObject<Item> VANILLA_bot_14 = register("block_vanilla_bot", () -> new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_14.get(), new Item.Properties().tab(ItemGroups_CM.TEATIME).craftRemainder(Items_NoTab.VANILLA_bot_24.get())));

	public static final RegistryObject<Item> MATCH = register("item_match_cm", () -> new Match_TT(new Item.Properties()));

	public static final RegistryObject<Item> SARA = register("item_food_sara", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> YUNOMI = register("item_food_yunomi", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> TCUP_kara = register("item_food_teacup", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> CHAWAN = register("item_food_chawan", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> SHIKKI = register("item_food_shikki", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> TONSUI = register("item_food_tonsui", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> DONBURI = register("item_food_donburi", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> DRINKGLASS = register("item_food_driglass", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> SAKEBOTTLE = register("item_food_sakebot", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> BENTOUHAKO = register("item_bentouhako", () -> new IG_Teatime(new Item.Properties()));
	
	public static final RegistryObject<Item> CLAY_SARA = register("item_clay_sara", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_YUNOMI = register("item_clay_yunomi", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_KYUSU = register("item_clay_kyusu", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_TCUP = register("item_clay_teacup", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_TPOT = register("item_clay_teapot", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_CHAWAN = register("item_clay_chawan", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_NABE = register("item_clay_nabe", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_TONSUI = register("item_clay_tonsui", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> CLAY_DONBURI = register("item_clay_donburi", () -> new AddInfo_TT(new Item.Properties()));

	/* Salt */
	public static final RegistryObject<Item> SHIO = register("item_salt", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> NIGARI = register("item_nigari", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> RENNET = register("item_rennet", () -> new AddInfo_TT(new Item.Properties()));

	public static final RegistryObject<Item> MUSHIGOME = register("item_mushigome", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> MUSHIGOME_TAKE = register("item_mushigome_take", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> MUSHIGOME_KURI = register("item_mushigome_kuri", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> MUSHI_SEKIHAN = register("item_mushisekihan", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> KOMEKOUJI = register("item_komekouji", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> SHUBO = register("item_shubo", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> SAKEKASU = register("item_sakekasu", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> MOROMI = register("item_moromi", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items_Teatime.SAKEKASU.get())));

	public static final RegistryObject<Item> NIMAME = register("item_nimame", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> MISO = register("item_miso", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> YUDEAZUKI = register("item_azuki_boil", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> ANKO = register("item_anko", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> CUSTARD = register("item_custard", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> CUSTARD_CREAM = register("item_custard_cream", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));

	public static final RegistryObject<Item> BOWL_ICE_VANILLA = register("item_bowl_icecream", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_ICE_GREEN = register("item_bowl_icecream_greentea", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_ICE_RED = register("item_bowl_icecream_redtea", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_ICE_CACAO = register("item_bowl_icecream_cacao", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	
	public static final RegistryObject<Item> BOWL_KANTEN_APPLE = register("item_bowl_kanten_apple", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_KANTEN_CHERRY = register("item_bowl_kanten_cherry", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_KANTEN_CITRUS = register("item_bowl_kanten_citrus", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_KANTEN_GRAPE = register("item_bowl_kanten_grape", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_KANTEN_MILK = register("item_bowl_kanten_milk", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_YOKAN = register("item_bowl_yokan", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> BOWL_YOKAN_MATCHA = register("item_bowl_yokan_matcha", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items.BOWL)));
	
	public static final RegistryObject<Item> KOMUGI = register("item_flour", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> BUTTER = register("item_butter", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_BURG = register("item_kiji_burg", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_BUN = register("item_kiji_bun", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_ANPAN = register("item_kiji_pananko", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_CUSTARD_PAN = register("item_kiji_pancustard", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_APPLE_PAN = register("item_kiji_panapple", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_CHERRY_PAN = register("item_kiji_pancherry", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_CITRUS_PAN = register("item_kiji_pancitrus", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_GRAPE_PAN = register("item_kiji_pangrape", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_GREENTEA_PAN = register("item_kiji_pangreentea", () -> new AddInfo_TT(new Item.Properties()));

	public static final RegistryObject<Item> KIJI_SCONE= register("item_kiji_scone", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_SENBEI = register("item_kiji_senbei", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KIJI_PIZA = register("item_kiji_pizza", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> PIZZA_nama = register("item_food_pizza_n", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> PIZZAC_nama = register("item_food_pizza_cn", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> PIZZAT_nama = register("item_food_pizza_tn", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> PIZZAS_nama = register("item_food_pizza_sn", () -> new IG_Teatime(new Item.Properties()));

	public static final RegistryObject<Item> PASTA_nama = register("item_food_pasta_n", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> PASTA_sara = register("item_food_pasta_s", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> UDON_nama = register("item_food_udon_n", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> SHOUYU_donburi = register("item_food_shouyu_don", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> TSUYU_donburi = register("item_food_tsuyu_don", () -> new AddInfo_TT(new Item.Properties()));

	public static final RegistryObject<Item> KEIRYO_CUP = register("block_measurecup", () -> new MeasureCup_TT(Fluids.EMPTY, Dish_Blocks.KEIRYO_CUP.get(), new Item.Properties()));
	public static final RegistryObject<Item> KEIRYO_CUP_full = register("item_measurecup_full", () -> new MeasureCupFull_TT(new Item.Properties().craftRemainder(Items_Teatime.KEIRYO_CUP.get())));
	public static final RegistryObject<Item> KANSUI = register("item_kansui", () -> new MeasureCupFull_TT(new Item.Properties().craftRemainder(Items_Teatime.KEIRYO_CUP.get())));
	public static final RegistryObject<Item> RAMEN_nama = register("item_food_ramen_n", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> SHOUYU_TARE = register("item_food_tare_shouyu", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> MISO_TARE = register("item_food_tare_miso", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> SHIO_TARE = register("item_food_tare_shio", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> SHOUYU_Rsoup = register("item_food_rsoup_shouyu", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> MISO_Rsoup = register("item_food_rsoup_miso", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> SHIO_Rsoup = register("item_food_rsoup_shio", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> SOBA_PLATE = register("item_food_sobaplate", () -> new AddInfo_TT(new Item.Properties()));
	
	/* Cooking */
	public static final RegistryObject<Item> ZUNDOU = register("block_food_zundou", () -> new ZundouKara_TT(Dish_Blocks.ZUNDOU.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_MIZU = register("block_zundou_mizu", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_MIZU.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_SHIO = register("block_zundou_shiomizu", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_SHIO.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_MILK = register("block_zundou_milk", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_MILK.get(), new Item.Properties()));

	public static final RegistryObject<Item> ZUNDOU_NCURRY = register("block_food_cunabe_n", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_NCURRY.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_CURRY = register("block_food_cunabe_1", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_CURRY.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_NCURRY_C = register("block_food_cunabe_cn", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_NCURRY_C.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_CURRY_C = register("block_food_cunabe_c1", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_CURRY_C.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_NCURRY_T = register("block_food_cunabe_tn", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_NCURRY_T.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_CURRY_T = register("block_food_cunabe_t1", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_CURRY_T.get(), new Item.Properties()));

	public static final RegistryObject<Item> ZUNDOU_NSTEW = register("block_food_stewnabe_n", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_NSTEW.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_STEW = register("block_food_stewnabe_1", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_STEW.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_DASHI = register("block_food_dashinabe_1", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_DASHI.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_RSOUP_nama = register("block_food_rsoup_n", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_RSOUP_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZUNDOU_RSOUP = register("block_food_rsoup_1", () -> new Teatime_noFuel(Dish_Blocks.ZUNDOU_RSOUP.get(), new Item.Properties()));

	public static final RegistryObject<Item> NABE_kara = register("block_food_karanabe", () -> new DonabeKara_TT(Fluids.EMPTY, Dish_Blocks.NABE_kara.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABETORI_nama = register("block_food_nabe_n", () -> new Teatime_noFuel(Dish_Blocks.NABETORI_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEMISO_nama = register("block_food_nabemiso_n", () -> new Teatime_noFuel(Dish_Blocks.NABEMISO_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEGOHAN_nama = register("block_food_nabegohan_n", () -> new Teatime_noFuel(Dish_Blocks.NABEGOHAN_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEGOHANTAKE_nama = register("block_food_nabegohantake_n", () -> new Teatime_noFuel(Dish_Blocks.NABEGOHANTAKE_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEGOHANKURI_nama = register("block_food_nabegohankuri_n", () -> new Teatime_noFuel(Dish_Blocks.NABEGOHANKURI_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABESEKIHAN_nama = register("block_food_nabesekihan_n", () -> new Teatime_noFuel(Dish_Blocks.NABESEKIHAN_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABECORN_nama = register("block_food_nabecorns_n", () -> new Teatime_noFuel(Dish_Blocks.NABECORN_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABESHIO_nama = register("block_food_nabeshio_n", () -> new Teatime_noFuel(Dish_Blocks.NABESHIO_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABENIMAME_nama = register("block_food_nabenimame_n", () -> new Teatime_noFuel(Dish_Blocks.NABENIMAME_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABETOUFU_nama = register("block_food_nabetoufu_n", () -> new Teatime_noFuel(Dish_Blocks.NABETOUFU_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEAZUKI_nama = register("block_food_nabeazuki_n", () -> new Teatime_noFuel(Dish_Blocks.NABEAZUKI_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEANKO_nama = register("block_food_nabeanko_n", () -> new Teatime_noFuel(Dish_Blocks.NABEANKO_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEPUDDING_nama = register("block_food_nabepudding_n", () -> new Teatime_noFuel(Dish_Blocks.NABEPUDDING_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEPUDDING_green = register("block_food_nabepudding_g", () -> new Teatime_noFuel(Dish_Blocks.NABEPUDDING_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEPUDDING_red = register("block_food_nabepudding_r", () -> new Teatime_noFuel(Dish_Blocks.NABEPUDDING_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEPUDDING_cacao = register("block_food_nabepudding_c", () -> new Teatime_noFuel(Dish_Blocks.NABEPUDDING_cacao.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABE_CREAM = register("block_food_nabecream", () -> new Teatime_noFuel(Dish_Blocks.NABE_CREAM.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABETENGUSA_nama = register("block_food_nabetengusa_n", () -> new Teatime_noFuel(Dish_Blocks.NABETENGUSA_nama.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> NABETORI = register("block_food_nabe_1", () -> new Teatime_noFuel(Dish_Blocks.NABETORI.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEMISO = register("block_food_nabemiso_1", () -> new Teatime_noFuel(Dish_Blocks.NABEMISO.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEZENZAI_M = register("block_food_nabezenzai_m", () -> new Teatime_noFuel(Dish_Blocks.NABEZENZAI_M.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEZENZAI_K = register("block_food_nabezenzai_k", () -> new Teatime_noFuel(Dish_Blocks.NABEZENZAI_K.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEGOHAN = register("block_food_nabegohan_1", () -> new Teatime_noFuel(Dish_Blocks.NABEGOHAN.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEGOHAN_TAKE = register("block_food_nabegohantake_1", () -> new Teatime_noFuel(Dish_Blocks.NABEGOHAN_TAKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABEGOHAN_KURI = register("block_food_nabegohankuri_1", () -> new Teatime_noFuel(Dish_Blocks.NABEGOHAN_KURI.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABESEKIHAN = register("block_food_nabesekihan_1", () -> new Teatime_noFuel(Dish_Blocks.NABESEKIHAN.get(), new Item.Properties()));
	public static final RegistryObject<Item> NABECORN = register("block_food_nabecorns_1", () -> new Teatime_noFuel(Dish_Blocks.NABECORN.get(), new Item.Properties()));

	public static final RegistryObject<Item> FRYPAN_kara = register("block_food_frypan", () -> new Teatime_noFuel(Dish_Blocks.FRYPAN_kara.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPTAMAGO_nama = register("block_food_frypan_n_tamago", () -> new Teatime_noFuel(Dish_Blocks.FPTAMAGO_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPGYUDON_nama = register("block_food_frypan_n_gyudon", () -> new Teatime_noFuel(Dish_Blocks.FPGYUDON_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPOYAKODON_nama = register("block_food_frypan_n_oyakodon", () -> new Teatime_noFuel(Dish_Blocks.FPOYAKODON_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPKATSU_nama = register("block_food_frypan_n_katsu", () -> new Teatime_noFuel(Dish_Blocks.FPKATSU_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPKATSU_bake = register("block_food_frypan_b_katsu", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> FPKATSUDON_nama = register("block_food_frypan_n_katsudon", () -> new Teatime_noFuel(Dish_Blocks.FPKATSUDON_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPEGGBURG_nama = register("block_food_frypan_n_eggb", () -> new Teatime_noFuel(Dish_Blocks.FPEGGBURG_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPTOMATO_nama = register("block_food_frypan_n_tomatos", () -> new Teatime_noFuel(Dish_Blocks.FPTOMATO_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPKINOKO_nama = register("block_food_frypan_n_kinokos", () -> new Teatime_noFuel(Dish_Blocks.FPKINOKO_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPSEAFOOD_nama = register("block_food_frypan_n_seafood", () -> new Teatime_noFuel(Dish_Blocks.FPSEAFOOD_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPKINOKOAK_nama = register("block_food_frypan_n_kinokoak", () -> new Teatime_noFuel(Dish_Blocks.FPKINOKOAK_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPKINOKOAK = register("item_food_frypan_b_kinokoak", () -> new AddInfo_NT(new Item.Properties().craftRemainder(Items_Teatime.FRYPAN_kara.get()).tab(ItemGroups_CM.TEATIME)));
	public static final RegistryObject<Item> FPCURRY_nama = register("block_food_frypan_n_roux", () -> new Teatime_noFuel(Dish_Blocks.FPCURRY_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> FPOSAUCE_nama = register("block_food_frypan_n_osauce", () -> new Teatime_noFuel(Dish_Blocks.FPOSAUCE_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMIYAKI_nama = register("block_food_teppan_n_okonomiyaki", () -> new Teatime_noFuel(Dish_Blocks.OKONOMIYAKI_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMIS_nama = register("block_food_teppan_n_okonomis", () -> new Teatime_noFuel(Dish_Blocks.OKONOMIS_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMIC_nama = register("block_food_teppan_n_okonomic", () -> new Teatime_noFuel(Dish_Blocks.OKONOMIC_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMISOBA_nama = register("block_food_teppan_n_okonomisoba", () -> new Teatime_noFuel(Dish_Blocks.OKONOMISOBA_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMISOBAS_nama = register("block_food_teppan_n_okonomisobas", () -> new Teatime_noFuel(Dish_Blocks.OKONOMISOBAS_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMISOBAC_nama = register("block_food_teppan_n_okonomisobac", () -> new Teatime_noFuel(Dish_Blocks.OKONOMISOBAC_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKISOBA_nama = register("block_food_teppan_n_yakisoba", () -> new Teatime_noFuel(Dish_Blocks.YAKISOBA_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKISOBASHIO_nama = register("block_food_teppan_n_yakisobashio", () -> new Teatime_noFuel(Dish_Blocks.YAKISOBASHIO_nama.get(), new Item.Properties()));

	public static final RegistryObject<Item> NIBOSHI = register("block_niboshi", () -> new Teatime_noFuel(Dish_Blocks.NIBOSHI.get(), new Item.Properties()));

	public static final RegistryObject<Item> CURRY = register("block_food_curry_1", () -> new Dish_Plate(Dish_Blocks.CURRY.get(), new Item.Properties()));
	public static final RegistryObject<Item> CURRYSET = register("block_food_curryset_1", () -> new Teatime_noFuel(Dish_Blocks.CURRYSET.get(), new Item.Properties()));
	public static final RegistryObject<Item> CURRY_C = register("block_food_curry_c1", () -> new Dish_Plate(Dish_Blocks.CURRY_C.get(), new Item.Properties()));
	public static final RegistryObject<Item> CURRYSET_C = register("block_food_curryset_c1", () -> new Teatime_noFuel(Dish_Blocks.CURRYSET_C.get(), new Item.Properties()));
	public static final RegistryObject<Item> CURRY_T = register("block_food_curry_t1", () -> new Dish_Plate(Dish_Blocks.CURRY_T.get(), new Item.Properties()));
	public static final RegistryObject<Item> CURRYSET_T = register("block_food_curryset_t1", () -> new Teatime_noFuel(Dish_Blocks.CURRYSET_T.get(), new Item.Properties()));

	public static final RegistryObject<Item> STEW = register("block_food_stew_1", () -> new Dish_Plate(Dish_Blocks.STEW.get(), new Item.Properties()));
	public static final RegistryObject<Item> UDON_SU = register("block_food_udonsu_1", () -> new Dish_Donburi(Dish_Blocks.UDON_SU.get(), new Item.Properties()));
	public static final RegistryObject<Item> UDON_NIKU = register("block_food_udonniku_1", () -> new Dish_Donburi(Dish_Blocks.UDON_NIKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> UDON_TSUKIMI = register("block_food_udontsukimi_1", () -> new Dish_Donburi(Dish_Blocks.UDON_TSUKIMI.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAMEN_SHOUYU = register("block_food_ramenshouyu_1", () -> new Dish_Donburi(Dish_Blocks.RAMEN_SHOUYU.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAMEN_MISO = register("block_food_ramenmiso_1", () -> new Dish_Donburi(Dish_Blocks.RAMEN_MISO.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAMEN_SHIO = register("block_food_ramenshio_1", () -> new Dish_Donburi(Dish_Blocks.RAMEN_SHIO.get(), new Item.Properties()));

	public static final RegistryObject<Item> TONSUITORI = register("block_food_tonsui_1", () -> new Dish_Tonsui(Dish_Blocks.TONSUITORI.get(), new Item.Properties()));
	public static final RegistryObject<Item> MISOSOUP = register("block_food_misosp_1", () -> new Dish_Shikki(Dish_Blocks.MISOSOUP.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZENZAI_M = register("block_food_zenzai_m", () -> new Dish_Shikki(Dish_Blocks.ZENZAI_M.get(), new Item.Properties()));
	public static final RegistryObject<Item> ZENZAI_K = register("block_food_zenzai_k", () -> new Dish_Shikki(Dish_Blocks.ZENZAI_K.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOHAN = register("block_food_gohan_1", () -> new Dish_Chawan(Dish_Blocks.GOHAN.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOHAN_TAKE = register("block_food_gohantake_1", () -> new Dish_Chawan(Dish_Blocks.GOHAN_TAKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOHAN_KURI = register("block_food_gohankuri_1", () -> new Dish_Chawan(Dish_Blocks.GOHAN_KURI.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEKIHAN = register("block_food_sekihan", () -> new Dish_Chawan(Dish_Blocks.SEKIHAN.get(), new Item.Properties()));
	public static final RegistryObject<Item> RICE = register("block_food_rice_1", () -> new Dish_Plate(Dish_Blocks.RICE.get(), new Item.Properties()));
	public static final RegistryObject<Item> DONBURI_MESHI = register("block_food_donmeshi_1", () -> new Dish_Donburi(Dish_Blocks.DONBURI_MESHI.get(), new Item.Properties()));

	public static final RegistryObject<Item> DONBURI_GYU = register("block_food_dongyu_1", () -> new Dish_Donburi(Dish_Blocks.DONBURI_GYU.get(), new Item.Properties()));
	public static final RegistryObject<Item> DONBURI_OYAKO = register("block_food_donoyako_1", () -> new Dish_Donburi(Dish_Blocks.DONBURI_OYAKO.get(), new Item.Properties()));
	public static final RegistryObject<Item> DONBURI_KATSU = register("block_food_donkatsu_1", () -> new Dish_Donburi(Dish_Blocks.DONBURI_KATSU.get(), new Item.Properties()));
	public static final RegistryObject<Item> DONBURI_KAISEN = register("block_food_donkaisen_1", () -> new Dish_Donburi(Dish_Blocks.DONBURI_KAISEN.get(), new Item.Properties()));

	public static final RegistryObject<Item> FOOD_HAKUSAI2 = register("item_food_hakusai2", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.HAKUSAI2)));
	public static final RegistryObject<Item> HAKUSAIDUKE = register("block_food_hsd_1", () -> new Dish_Plate(Dish_Blocks.HAKUSAIDUKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> TAMAGOYAKI = register("block_food_tgy_1", () -> new Dish_Plate(Dish_Blocks.TAMAGOYAKI.get(), new Item.Properties().craftRemainder(Items_Teatime.SARA.get())));

	public static final RegistryObject<Item> TAMAGOYAKITEI = register("block_food_tgytei_1", () -> new Teatime_noFuel(Dish_Blocks.TAMAGOYAKITEI.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIZAKANATEI = register("block_food_yakizakanatei_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIZAKANATEI.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIJYAKETEI = register("block_food_yakijyaketei_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIJYAKETEI.get(), new Item.Properties()));
	public static final RegistryObject<Item> TAMAGOYAKITEI_TAKE = register("block_food_tgyteitake_1", () -> new Teatime_noFuel(Dish_Blocks.TAMAGOYAKITEI_TAKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIZAKANATEI_TAKE = register("block_food_yakizakanateitake_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIZAKANATEI_TAKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIJYAKETEI_TAKE = register("block_food_yakijyaketeitake_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIJYAKETEI_TAKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> TAMAGOYAKITEI_KURI = register("block_food_tgyteikuri_1", () -> new Teatime_noFuel(Dish_Blocks.TAMAGOYAKITEI_KURI.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIZAKANATEI_KURI = register("block_food_yakizakanateikuri_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIZAKANATEI_KURI.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIJYAKETEI_KURI = register("block_food_yakijyaketeikuri_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIJYAKETEI_KURI.get(), new Item.Properties()));
	public static final RegistryObject<Item> TAMAGOYAKITEI_SEKI = register("block_food_tgytei_sekihan_1", () -> new Teatime_noFuel(Dish_Blocks.TAMAGOYAKITEI_SEKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIZAKANATEI_SEKI = register("block_food_yakizakanatei_sekihan_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIZAKANATEI_SEKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKIJYAKETEI_SEKI = register("block_food_yakijyaketei_sekihan_1", () -> new Teatime_noFuel(Dish_Blocks.YAKIJYAKETEI_SEKI.get(), new Item.Properties()));

	public static final RegistryObject<Item> CORNSOUP = register("block_food_cornsp_1", () -> new Dish_Plate(Dish_Blocks.CORNSOUP.get(), new Item.Properties()));

	public static final RegistryObject<Item> EGGBURG = register("block_food_egb_1", () -> new Dish_Plate(Dish_Blocks.EGGBURG.get(), new Item.Properties()));
	public static final RegistryObject<Item> EGGBURGSET = register("block_food_egbset_1", () -> new Teatime_noFuel(Dish_Blocks.EGGBURGSET.get(), new Item.Properties()));

	public static final RegistryObject<Item> PASTATOMATO = register("block_food_pastatoma_1", () -> new Dish_Plate(Dish_Blocks.PASTATOMATO.get(), new Item.Properties()));
	public static final RegistryObject<Item> PASTACHEESE = register("block_food_pastacheese_1", () -> new Dish_Plate(Dish_Blocks.PASTACHEESE.get(), new Item.Properties()));
	public static final RegistryObject<Item> PASTAKINOKO = register("block_food_pastakinoko_1", () -> new Dish_Plate(Dish_Blocks.PASTAKINOKO.get(), new Item.Properties()));
	public static final RegistryObject<Item> PASTASEAFOOD = register("block_food_pastaseafood_1", () -> new Dish_Plate(Dish_Blocks.PASTASEAFOOD.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> PIZZA = register("block_food_pizza_1", () -> new Teatime_noFuel(Dish_Blocks.PIZZA.get(), new Item.Properties()));
	public static final RegistryObject<Item> PIZZA_C = register("block_food_pizza_c1", () -> new Teatime_noFuel(Dish_Blocks.PIZZA_C.get(), new Item.Properties()));
	public static final RegistryObject<Item> PIZZA_T = register("block_food_pizza_t1", () -> new Teatime_noFuel(Dish_Blocks.PIZZA_T.get(), new Item.Properties()));
	public static final RegistryObject<Item> PIZZA_S = register("block_food_pizza_s1", () -> new Teatime_noFuel(Dish_Blocks.PIZZA_S.get(), new Item.Properties()));
	public static final RegistryObject<Item> PC_PIZZA = register("item_food_pizza", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PC_PIZZA)));
	public static final RegistryObject<Item> PC_PIZZAC = register("item_food_pizzac", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PC_PIZZAC)));
	public static final RegistryObject<Item> PC_PIZZAT = register("item_food_pizzat", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PC_PIZZAT)));
	public static final RegistryObject<Item> PC_PIZZAS = register("item_food_pizzas", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PC_PIZZAS)));
	public static final RegistryObject<Item> OKONOMIYAKI = register("block_food_okonomiyaki_1", () -> new Dish_Plate(Dish_Blocks.OKONOMIYAKI.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMIS = register("block_food_okonomis_1", () -> new Dish_Plate(Dish_Blocks.OKONOMIS.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMIC = register("block_food_okonomic_1", () -> new Dish_Plate(Dish_Blocks.OKONOMIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMISOBA = register("block_food_okonomisoba_1", () -> new Dish_Plate(Dish_Blocks.OKONOMISOBA.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMISOBAS = register("block_food_okonomisobas_1", () -> new Dish_Plate(Dish_Blocks.OKONOMISOBAS.get(), new Item.Properties()));
	public static final RegistryObject<Item> OKONOMISOBAC = register("block_food_okonomisobac_1", () -> new Dish_Plate(Dish_Blocks.OKONOMISOBAC.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKISOBA = register("block_food_yakisoba_1", () -> new Dish_Plate(Dish_Blocks.YAKISOBA.get(), new Item.Properties()));
	public static final RegistryObject<Item> YAKISOBASHIO = register("block_food_yakisobashio_1", () -> new Dish_Plate(Dish_Blocks.YAKISOBASHIO.get(), new Item.Properties()));

	public static final RegistryObject<Item> CHICKEN = register("block_food_roastchicken_1", () -> new Teatime_noFuel(Dish_Blocks.CHICKEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHICKEN_small = register("block_food_chickenb_1", () -> new Dish_Plate(Dish_Blocks.CHICKEN_small.get(), new Item.Properties()));

	public static final RegistryObject<Item> SUSHIMESHI = register("block_food_sushimeshi", () -> new Teatime_noFuel(Dish_Blocks.SUSHIMESHI.get(), new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> SUSHIGETA_kara = register("block_food_sushigeta_kara", () -> new Teatime_noFuel(Dish_Blocks.SUSHIGETA_kara.get(), new Item.Properties()));

	public static final RegistryObject<Item> SUSHISET_salmon = register("block_food_sushiset_salmon", () -> new Teatime_noFuel(Dish_Blocks.SUSHISET_salmon.get(), new Item.Properties()));
	public static final RegistryObject<Item> SUSHISET_fish = register("block_food_sushiset_fish", () -> new Teatime_noFuel(Dish_Blocks.SUSHISET_fish.get(), new Item.Properties()));
	public static final RegistryObject<Item> SUSHISET_beef = register("block_food_sushiset_beef", () -> new Teatime_noFuel(Dish_Blocks.SUSHISET_beef.get(), new Item.Properties()));
	public static final RegistryObject<Item> SUSHISET_tamago = register("block_food_sushiset_tamago", () -> new Teatime_noFuel(Dish_Blocks.SUSHISET_tamago.get(), new Item.Properties()));
	public static final RegistryObject<Item> SUSHISET_4shoku = register("block_food_sushiset_4shoku", () -> new Teatime_noFuel(Dish_Blocks.SUSHISET_4shoku.get(), new Item.Properties()));

	public static final RegistryObject<Item> SUSHIOKE = register("block_food_sushioke_kara", () -> new Teatime_noFuel(Dish_Blocks.SUSHIOKE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SUSHIOKE_FULL_1 = register("block_food_sushiokefull_1", () -> new Teatime_noFuel(Dish_Blocks.SUSHIOKE_FULL_1.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUYUSARA_1 = register("block_food_shouyusara_1", () -> new Teatime_noFuel(Dish_Blocks.SHOUYUSARA_1.get(), new Item.Properties()));

	public static final RegistryObject<Item> KETTLE_kara = register("item_kettle_kara", () -> new Teatime_noFuel(Dish_Blocks.KETTLE_kara.get(), new Item.Properties()));
	public static final RegistryObject<Item> KETTLE_full = register("block_kettle_full", () -> new Teatime_noFuel(Dish_Blocks.KETTLE_full.get(), new Item.Properties()));
	public static final RegistryObject<Item> KETTLE_boil = register("item_kettle_boil", () -> new IG_Teatime(new Item.Properties().craftRemainder(Items_Teatime.KETTLE_kara.get())));

	public static final RegistryObject<Item> KYUSU_kara = register("block_food_kyusu", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> KYUSU = register("block_food_kyusu_1", () -> new Teatime_noFuel(Dish_Blocks.KYUSU.get(), new Item.Properties()));
	public static final RegistryObject<Item> JPTEACUP = register("block_food_jpteacup_1", () -> new Yunomi_TT(Dish_Blocks.JPTEACUP.get(), new Item.Properties()));

	public static final RegistryObject<Item> JPTEASET = register("block_food_jpteaset_1", () -> new Teatime_noFuel(Dish_Blocks.JPTEASET.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHAUKE_SENBEI = register("block_food_senbei", () -> new Teatime_noFuel(Dish_Blocks.CHAUKE_SENBEI.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHAUKE_MIKAN = register("block_food_mikan", () -> new Teatime_noFuel(Dish_Blocks.CHAUKE_MIKAN.get(), new Item.Properties()));

	public static final RegistryObject<Item> TEAPOT_kara = register("block_food_teapot", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> TEAPOT = register("block_food_teapot_1", () -> new Teatime_noFuel(Dish_Blocks.TEAPOT.get(), new Item.Properties()));
	public static final RegistryObject<Item> TEACUP = register("block_food_teacup_1", () -> new TeaCup_TT(Dish_Blocks.TEACUP.get(), new Item.Properties()));

	public static final RegistryObject<Item> TEASET = register("block_food_teaset_1", () -> new Teatime_noFuel(Dish_Blocks.TEASET.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHAUKE_SCONE = register("block_food_scone", () -> new Teatime_noFuel(Dish_Blocks.CHAUKE_SCONE.get(), new Item.Properties()));

	public static final RegistryObject<Item> SCONESET_kara = register("block_food_teastand", () -> new Teatime_noFuel(Dish_Blocks.SCONESET_kara.get(), new Item.Properties()));
	public static final RegistryObject<Item> SCONESET_1 = register("block_food_sconeset_1", () -> new Teatime_noFuel(Dish_Blocks.SCONESET_1.get(), new Item.Properties()));

	public static final RegistryObject<Item> ICECREAM = register("block_food_icecream_1", () -> new Dish_LUCK(Dish_Blocks.ICECREAM.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICECREAM_GREEN = register("block_food_icecream_greentea", () -> new Dish_LUCK(Dish_Blocks.ICECREAM_GREEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICECREAM_RED = register("block_food_icecream_redtea", () -> new Dish_LUCK(Dish_Blocks.ICECREAM_RED.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICECREAM_CACAO = register("block_food_icecream_cacao", () -> new Dish_LUCK(Dish_Blocks.ICECREAM_CACAO.get(), new Item.Properties()));

	public static final RegistryObject<Item> CUSTARD_PUDDING = register("block_food_pudding_custard", () -> new Dish_LUCK(Dish_Blocks.CUSTARD_PUDDING.get(), new Item.Properties()));
	public static final RegistryObject<Item> GREENTEA_PUDDING = register("block_food_pudding_greentea", () -> new Dish_LUCK(Dish_Blocks.GREENTEA_PUDDING.get(), new Item.Properties()));
	public static final RegistryObject<Item> REDTEA_PUDDING = register("block_food_pudding_redtea", () -> new Dish_LUCK(Dish_Blocks.REDTEA_PUDDING.get(), new Item.Properties()));
	public static final RegistryObject<Item> CACAO_PUDDING = register("block_food_pudding_cacao", () -> new Dish_LUCK(Dish_Blocks.CACAO_PUDDING.get(), new Item.Properties()));

	public static final RegistryObject<Item> KANTEN_APPLE = register("block_food_kanten_apple", () -> new Dish_Kanten(Dish_Blocks.KANTEN_APPLE.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANTEN_CHERRY = register("block_food_kanten_cherry", () -> new Dish_Kanten(Dish_Blocks.KANTEN_CHERRY.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANTEN_CITRUS = register("block_food_kanten_citrus", () -> new Dish_Kanten(Dish_Blocks.KANTEN_CITRUS.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANTEN_GRAPE = register("block_food_kanten_grape", () -> new Dish_Kanten(Dish_Blocks.KANTEN_GRAPE .get(), new Item.Properties()));
	public static final RegistryObject<Item> KANTEN_MILK = register("block_food_kanten_milk", () -> new Dish_Kanten(Dish_Blocks.KANTEN_MILK.get(), new Item.Properties()));
	public static final RegistryObject<Item> YOKAN = register("block_food_yokan", () -> new Dish_Kanten(Dish_Blocks.YOKAN.get(), new Item.Properties()));
	public static final RegistryObject<Item> YOKAN_MATCHA = register("block_food_yokan_matcha", () -> new Dish_Kanten(Dish_Blocks.YOKAN_MATCHA.get(), new Item.Properties()));

	public static final RegistryObject<Item> CHEESE_CURD = register("block_food_cheesecurd", () -> new Teatime_noFuel(Hakkou_Blocks.CHEESE_CURD.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHEESE = register("block_food_cheese_1", () -> new Teatime_noFuel(Hakkou_Blocks.CHEESE.get(), new Item.Properties()));
	public static final RegistryObject<Item> FCHEESE = register("item_food_cheesef", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.FCHEESE)));
	public static final RegistryObject<Item> PC_CHEESE = register("item_food_cheese", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PC_CHEESE)));
	public static final RegistryObject<Item> CHEESE_TANA = register("block_kit_cheese_tana", () -> new Teatime_Fuel150(Hakkou_Blocks.CHEESE_TANA.get(), new Item.Properties()));

	/* FOOD */
	public static final RegistryObject<Item> CAKE = register("item_food_cake", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.CAKE)));
	public static final RegistryObject<Item> BUN = register("item_food_bun", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.BUN)));
	public static final RegistryObject<Item> SCONE = register("item_food_scone", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SCONE)));
	public static final RegistryObject<Item> SENBEI = register("item_food_senbei", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.SENBEI)));
	public static final RegistryObject<Item> TOUFU = register("item_food_toufu", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.TOUFU)));

	public static final RegistryObject<Item> CHICKENSAND = register("item_food_chickensand", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.CHICKENSAND)));
	public static final RegistryObject<Item> EGGSAND = register("item_food_eggsand", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.EGGSAND)));

	public static final RegistryObject<Item> KIRIMI_S = register("item_food_kirimi_salmon", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.KIRIMI)));
	public static final RegistryObject<Item> KIRIMI_F = register("item_food_kirimi_fish", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.KIRIMI)));
	public static final RegistryObject<Item> KIRIMI_B = register("item_food_kirimi_beef", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.KIRIMI)));
	public static final RegistryObject<Item> KIRIMI_T = register("item_food_kirimi_tamago", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.KIRIMI)));

	public static final RegistryObject<Item> SUSHI_S = register("item_food_sushi_salmon", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SUSHI)));
	public static final RegistryObject<Item> SUSHI_F = register("item_food_sushi_fish", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SUSHI)));
	public static final RegistryObject<Item> SUSHI_B = register("item_food_sushi_beef", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SUSHI)));
	public static final RegistryObject<Item> SUSHI_T = register("item_food_sushi_tamago", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SUSHI)));

	public static final RegistryObject<Item> SHOUYUSUSHI_S = register("item_food_sushishouyu_salmon", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SHOUYUSUSHI_S)));
	public static final RegistryObject<Item> SHOUYUSUSHI_F = register("item_food_sushishouyu_fish", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SHOUYUSUSHI_F)));
	public static final RegistryObject<Item> SHOUYUSUSHI_B = register("item_food_sushishouyu_beef", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SHOUYUSUSHI_B)));
	public static final RegistryObject<Item> SHOUYUSUSHI_T = register("item_food_sushishouyu_tamago", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.SHOUYUSUSHI_T)));

	public static final RegistryObject<Item> NORIAMI = register("block_noriami", () -> new Teatime_noFuel(Crop_Blocks.NORIAMI.get(), new Item.Properties()));
	public static final RegistryObject<Item> NORI_N = register("item_food_norinama", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> NORI_I = register("item_food_noriita", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.NORI_I)));
	public static final RegistryObject<Item> ONIGIRI = register("item_food_onigiri", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.ONIGIRI)));
	public static final RegistryObject<Item> ONIGIRI_SHAKE = register("item_food_onigirishake", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.ONIGIRISHAKE)));
	public static final RegistryObject<Item> ONIGIRI_TAKE = register("item_food_onigiritakenoko", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.ONIGIRI_TAKEKURI)));
	public static final RegistryObject<Item> ONIGIRI_KURI = register("item_food_onigirikuri", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.ONIGIRI_TAKEKURI)));
	public static final RegistryObject<Item> ONIGIRI_SEKIHAN = register("item_food_onigirisekihan", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.ONIGIRI_TAKEKURI)));
	public static final RegistryObject<Item> FUTOMAKI = register("item_food_futomaki", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.FUTOMAKI)));

	public static final RegistryObject<Item> KUSHI_SAKANA = register("item_kushi_sakana", () -> new AddInfo_TT(new Item.Properties()));
	public static final RegistryObject<Item> KUSHI_SAKANA_C = register("item_kushi_sakana_c", () -> new FoodNeed_TT(new Item.Properties()));

	public static final RegistryObject<Item> BENTOU = register("item_bentou", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> SHAKEBEN = register("item_bentoushake", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> BENTOU_TAKE = register("item_bentou_take", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> SHAKEBEN_TAKE = register("item_bentoushake_take", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> BENTOU_KURI = register("item_bentou_kuri", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> SHAKEBEN_KURI = register("item_bentoushake_kuri", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> BENTOU_SEKI = register("item_bentou_sekihan", () -> new Bentou(new Item.Properties()));
	public static final RegistryObject<Item> SHAKEBEN_SEKI = register("item_bentoushake_sekihan", () -> new Bentou(new Item.Properties()));

	public static final RegistryObject<Item> MOCHI = register("item_mochi", () -> new IG_Teatime(new Item.Properties()));
	public static final RegistryObject<Item> MOCHI_NORI = register("item_food_mochinori", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.MOCHI)));
	public static final RegistryObject<Item> MOCHI_KINAKO = register("item_food_mochikinako", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.MOCHI)));
	public static final RegistryObject<Item> MOCHI_ANKO = register("item_food_mochianko", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.MOCHI_ANKO)));
	public static final RegistryObject<Item> MOCHI_OHAGI = register("item_food_mochiohagi", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.MOCHI_ANKO)));
	public static final RegistryObject<Item> MOCHI_SAKURA = register("item_food_mochisakura", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.MOCHI_SAKURA)));

	public static final RegistryObject<Item> ANPAN = register("item_food_pananko", () -> new AddInfo_TT(new Item.Properties().food(FoodBuilders.MOCHI)));
	public static final RegistryObject<Item> CUSTARD_PAN = register("item_food_pancustard", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.MOCHI)));
	public static final RegistryObject<Item> APPLE_PAN = register("item_food_panapple", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PAN_APPLE)));
	public static final RegistryObject<Item> CHERRY_PAN = register("item_food_pancherry", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PAN_CHERRY)));
	public static final RegistryObject<Item> CITRUS_PAN = register("item_food_pancitrus", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PAN_CITRUS)));
	public static final RegistryObject<Item> GRAPE_PAN = register("item_food_pangrape", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PAN_GRAPE)));
	public static final RegistryObject<Item> GREENTEA_PAN = register("item_food_pangreentea", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.PAN_TEA)));

	/* Kitchen */
	public static final RegistryObject<Item> KIT_TANA = register("block_kit_tana", () -> new Teatime_Fuel150(Kitchen_Blocks.KIT_TANA.get(), new Item.Properties()));
	public static final RegistryObject<Item> KITCHEN = register("block_kitchen", () -> new Teatime_Fuel150(Kitchen_Blocks.KITCHEN.get(), new Item.Properties()));

	public static final RegistryObject<Item> KIT_BOARD = register("block_kit_board", () -> new Teatime_Fuel300(Kitchen_Blocks.KIT_BOARD.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIT_SINK = register("block_kit_sink", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_SINK.get(), new Item.Properties()));

	public static final RegistryObject<Item> KIT_COOKTOP = register("block_kit_stove", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_COOKTOP.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIT_OVEN = register("block_kit_oven", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_OVEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIT_OVEN_B = register("block_kit_oven_black", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_OVEN_B.get(), new Item.Properties()));
	public static final RegistryObject<Item> IRORI = register("block_irori", () -> new Teatime_noFuel(Kitchen_Blocks.IRORI.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIT_REIZOU = register("block_kit_reizou", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_REIZOU.get(), new Item.Properties()));

	public static final RegistryObject<Item> KIT_KANKI_1 = register("block_kit_kanki", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_KANKI_1.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIT_HAIKIDUCT = register("block_kit_duct", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_HAIKIDUCT.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIT_DUCTEND_1 = register("block_kit_ductend", () -> new Teatime_noFuel(Kitchen_Blocks.KIT_DUCTEND_1.get(), new Item.Properties()));

	public static final RegistryObject<Item> TEATABLE = register("block_teatable", () -> new Teatime_Fuel300(Unit_Blocks.TEATABLE.get(), new Item.Properties()));
	public static final RegistryObject<Item> ENDEN = register("block_enden", () -> new Teatime_noFuel(Crop_Blocks.ENDEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> TOAMI = register("item_toami", () -> new Toami_TT(Crop_Blocks.TOAMI.get(), new Item.Properties().durability(32)));
	public static final RegistryObject<Item> SHIKAKE_AMI = register("block_ami_shikake", () -> new AmiShikake_TT(Crop_Blocks.SHIKAKE_AMI.get(), new Item.Properties().durability(12)));
	public static final RegistryObject<Item> YOUSHOKU_AMI = register("block_ami_youshoku", () -> new AmiYoushoku_TT(Crop_Blocks.YOUSHOKU_AMI.get(), new Item.Properties().durability(12)));
	
	public static final RegistryObject<Item> CUT_IKA = register("item_squid_cut", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.CUT_IKA)));
	public static final RegistryObject<Item> COOKED_IKA = register("item_squid_cooked", () -> new IG_Teatime(new Item.Properties().food(FoodBuilders.COOKED_IKA)));
	public static final RegistryObject<Item> IKA = register("item_squid_raw", () -> new AddInfo_TT(new Item.Properties().craftRemainder(Items_Teatime.CUT_IKA.get())));	
	
	public static final RegistryObject<Item> KAIHORI = register("item_kaihori", () -> new Kaihori_TT(new Item.Properties().durability(256)));
	public static final RegistryObject<Item> HAMAGURI_COOK = register("item_food_hamaguri", () -> new FoodNeed_TT(new Item.Properties()));
	public static final RegistryObject<Item> HAMAGURI = register("block_hamaguri", () -> new Teatime_noFuel(Crop_Blocks.HAMAGURI.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> KINE_YOKO = register("item_kineyoko", () -> new KineYoko_TT(new Item.Properties().durability(128)));
	public static final RegistryObject<Item> USU_TSUKI = register("block_usutsuki", () -> new Teatime_Fuel300(Kitchen_Blocks.USU_TSUKI.get(), new Item.Properties()));

	
	///* Register *///
	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}
}
