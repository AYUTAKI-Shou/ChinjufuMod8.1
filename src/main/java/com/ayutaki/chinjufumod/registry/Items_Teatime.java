package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_ItemBlock;
import com.ayutaki.chinjufumod.items.dish.Dish_Chawan;
import com.ayutaki.chinjufumod.items.dish.Dish_Donburi;
import com.ayutaki.chinjufumod.items.dish.Dish_DrinkGlass;
import com.ayutaki.chinjufumod.items.dish.Dish_Kanten;
import com.ayutaki.chinjufumod.items.dish.Dish_Plate;
import com.ayutaki.chinjufumod.items.dish.Dish_Shikki;
import com.ayutaki.chinjufumod.items.dish.Dish_Tamagoyaki;
import com.ayutaki.chinjufumod.items.dish.Dish_Tonsui;
import com.ayutaki.chinjufumod.items.dish.DonabeKara_Item;
import com.ayutaki.chinjufumod.items.dish.TeaCup_Item;
import com.ayutaki.chinjufumod.items.dish.Yunomi_Item;
import com.ayutaki.chinjufumod.items.dish.ZundouKara_Item;
import com.ayutaki.chinjufumod.items.foods.Bentou;
import com.ayutaki.chinjufumod.items.foods.CherryFruit;
import com.ayutaki.chinjufumod.items.foods.FoodEffects;
import com.ayutaki.chinjufumod.items.foods.FoodPoints;
import com.ayutaki.chinjufumod.items.fuel.Fuel_100;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Fuel_200;
import com.ayutaki.chinjufumod.items.fuel.Fuel_300;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.items.hakkou.MizuokeFull_Item;
import com.ayutaki.chinjufumod.items.hakkou.MizuokeMilk_Item;
import com.ayutaki.chinjufumod.items.hakkou.Mizuoke_Item;
import com.ayutaki.chinjufumod.items.hakkou.SakeGlass;
import com.ayutaki.chinjufumod.items.remain.BottleDashi_1;
import com.ayutaki.chinjufumod.items.remain.BottleKomezu_1;
import com.ayutaki.chinjufumod.items.remain.BottleMayo_1;
import com.ayutaki.chinjufumod.items.remain.BottleOsauce_1;
import com.ayutaki.chinjufumod.items.remain.BottleShouyu_1;
import com.ayutaki.chinjufumod.items.remain.BottleSoyoil_1;
import com.ayutaki.chinjufumod.items.remain.BottleVanilla_1;
import com.ayutaki.chinjufumod.items.remain.HamaguriCooked;
import com.ayutaki.chinjufumod.items.remain.IkaRaw;
import com.ayutaki.chinjufumod.items.remain.IneCrop;
import com.ayutaki.chinjufumod.items.remain.KettleBoil;
import com.ayutaki.chinjufumod.items.remain.KinokoAK_Item;
import com.ayutaki.chinjufumod.items.remain.KouboNyusan;
import com.ayutaki.chinjufumod.items.remain.Moromi;
import com.ayutaki.chinjufumod.items.teatime.AmiShikake_Item;
import com.ayutaki.chinjufumod.items.teatime.AmiYoushoku_Item;
import com.ayutaki.chinjufumod.items.teatime.Kaihori_Item;
import com.ayutaki.chinjufumod.items.teatime.KineYoko_Item;
import com.ayutaki.chinjufumod.items.teatime.Match_Item;
import com.ayutaki.chinjufumod.items.teatime.MeasureCup_Item;
import com.ayutaki.chinjufumod.items.teatime.MeasurecupFull_Item;
import com.ayutaki.chinjufumod.items.teatime.Tengusa_Item;
import com.ayutaki.chinjufumod.items.teatime.Toami_Item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_Teatime {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	public static final DeferredItem<Item> SEEDSBOX = register("block_seedsbox", (props) -> new Not_Fuel(Crop_Blocks.SEEDSBOX.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SEEDS_AZUKI = register("item_seeds_azuki", (props) -> new Not_Fuel(Crop_Blocks.AZUKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_CABBAGE = register("item_seeds_cabbage", (props) -> new Not_Fuel(Crop_Blocks.CABBAGE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_HAKUSAI = register("item_seeds_hakusai", (props) -> new Not_Fuel(Crop_Blocks.HAKUSAI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_CORN = register("item_seeds_corn", (props) -> new Not_Fuel(Crop_Blocks.CORN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_GREENONION = register("item_seeds_greenonion", (props) -> new Not_Fuel(Crop_Blocks.GREENONION.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_ONION = register("item_seeds_onion", (props) -> new Not_Fuel(Crop_Blocks.ONION.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_RICE = register("item_seeds_rice", (props) -> new Not_Fuel(Crop_Blocks.RICE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_SOY = register("item_seeds_soy", (props) -> new Not_Fuel(Crop_Blocks.SOY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_SPINACH = register("item_seeds_spinach", (props) -> new Not_Fuel(Crop_Blocks.SPINACH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_TOMATO = register("item_seeds_tomato", (props) -> new Not_Fuel(Crop_Blocks.TOMATO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_CHERRY = register("item_seeds_cherry", (props) -> new Not_Fuel(Crop_Blocks.SAKURA.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CHANOKI = register("block_wood_chanoki_nae", (props) -> new Not_Fuel(Crop_Blocks.CHANOKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BUDOUNOKI = register("block_wood_grape_nae", (props) -> new Not_Fuel(Crop_Blocks.BUDOUNOKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MIKAN_NAE = register("block_wood_mikan", (props) -> new Not_Fuel(Crop_Blocks.MIKAN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> HODAGI = register("block_hodagi_a_bot", (props) -> new Not_Fuel(Crop_Blocks.HODAGI_A_BOT.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SEEDS_PEPPER = register("item_seeds_pepper", (props) -> new Not_Fuel(Crop_Blocks.PEPPER.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_CUMIN = register("item_seeds_cumin", (props) -> new Not_Fuel(Crop_Blocks.CUMIN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_TURMERIC = register("item_seeds_turmeric", (props) -> new Not_Fuel(Crop_Blocks.TURMERIC.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_CHILI = register("item_seeds_chilipepper", (props) -> new Not_Fuel(Crop_Blocks.CHILI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SEEDS_VANILLA = register("item_seeds_vanilla", (props) -> new Not_Fuel(Crop_Blocks.VANILLA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CROP_TENGUSA = register("item_crop_tengusa", (props) -> new Tengusa_Item(Crop_Blocks.TENGUSA.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> CHABA = register("item_chaba", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CHABA_GREEN = register("item_chaba_green", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CHABA_RED = register("item_chaba_red", Item::new, new Item.Properties());
	public static final DeferredItem<Item> TENCHA = register("item_chaba_tencha", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> MATCHA = register("item_chaba_matcha", Item::new, new Item.Properties());
	public static final DeferredItem<Item> TENGUSA_WASH = register("item_crop_tengusawash", (props) -> new Not_Fuel(Dish_Blocks.TENGUSA_WASH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TENGUSA_DRY = register("item_crop_tengusadry", Item::new, new Item.Properties());
	
	public static final DeferredItem<Item> INAGI = register("block_inagi", (props) -> new Not_Fuel(Crop_Blocks.INAGI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> INEWARA = register("item_inewara", Item::new, new Item.Properties());
	public static final DeferredItem<Item> INE = register("item_ine", IneCrop::new, new Item.Properties());
	public static final DeferredItem<Item> INE_D = register("item_ine_dry", IneCrop::new, new Item.Properties());
	public static final DeferredItem<Item> KOME = register("item_kome", Item::new, new Item.Properties());

	public static final DeferredItem<Item> SAYA = register("item_saya", Item::new, new Item.Properties());
	public static final DeferredItem<Item> SOY = register("item_soy", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PEPPER_RAW = register("item_crop_pepper", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> PEPPER_DRY = register("item_crop_pepperdry", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CHILIPEPPER = register("item_crop_chilipepper", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> DUST_PEPPER = register("item_dust_blackpepper", Item::new, new Item.Properties());
	public static final DeferredItem<Item> DUST_CUMIN = register("item_dust_cumin", Item::new, new Item.Properties());
	public static final DeferredItem<Item> DUST_TURMERIC = register("item_dust_turmeric", Item::new, new Item.Properties());
	public static final DeferredItem<Item> DUST_CHILI = register("item_dust_chili", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CURRY_ROUX = register("item_curry_roux", Item::new, new Item.Properties());
	public static final DeferredItem<Item> VANILLA_RAW = register("item_vanillabeans", AddInfo_Item::new, new Item.Properties());

	public static final DeferredItem<Item> FOOD_CABBAGE = register("item_food_cabbage", Item::new, new Item.Properties().food(FoodPoints.FPS2_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_HAKUSAI = register("item_food_hakusai", Item::new, new Item.Properties().food(FoodPoints.FPS2_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_CHERRY = register("item_food_cherry", CherryFruit::new, new Item.Properties());
	public static final DeferredItem<Item> FOOD_MIKAN = register("item_food_mikan", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_CORN = register("item_food_corn", Item::new, new Item.Properties().food(FoodPoints.FPS2_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_GRAPE = register("item_food_grape", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_GREENONION = register("item_food_greenonion", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_ONION = register("item_food_onion", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_SPINACH = register("item_food_spinach", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> FOOD_TOMATO = register("item_food_tomato", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));

	public static final DeferredItem<Item> FOOD_CORN_B = register("item_food_cornb", Item::new, new Item.Properties().food(FoodPoints.CORN_B, FoodEffects.DEFAULT_FOOD));

	public static final DeferredItem<Item> BOX_H_EMPTY = register("block_boxh_empty", (props) -> new Not_Fuel(Pantry_Blocks.BOX_H_EMPTY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_APPLE = register("block_boxh_apple", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_APPLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_BEEF = register("block_boxh_beef", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_BEEF.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_BEETROOT = register("block_boxh_beetroot", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_BEETROOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_BREAD = register("block_boxh_bread", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_BREAD.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CARROT = register("block_boxh_carrot", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CARROT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CHICKEN = register("block_boxh_chicken", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CHICKEN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CHORUS = register("block_boxh_chorus", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CHORUS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_COCO = register("block_boxh_coco", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_COCO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_COD = register("block_boxh_cod", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_COD.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_EGG = register("block_boxh_egg", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_EGG.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_FLOUR = register("block_boxh_flour", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_FLOUR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_MUTTON = register("block_boxh_mutton", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_MUTTON.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_PORK = register("block_boxh_pork", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_PORK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_POTATO = register("block_boxh_potato", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_POTATO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_RABBIT = register("block_boxh_rabbit", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_RABBIT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_SALMON = register("block_boxh_salmon", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_SALMON.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_SWBERRY = register("block_boxh_swberry", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_SWBERRY.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BOX_H_AZUKI = register("block_boxh_azuki", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_AZUKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CABBAGE = register("block_boxh_cabbage", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CABBAGE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_HAKUSAI = register("block_boxh_hakusai", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_HAKUSAI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CITRUS = register("block_boxh_citrus", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CITRUS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CORN = register("block_boxh_corn", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CORN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_GRAPE = register("block_boxh_grape", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_GRAPE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_GREENONION = register("block_boxh_greenonion", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_GREENONION.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_ONION = register("block_boxh_onion", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_ONION.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_ORIENTCLAM = register("block_boxh_hamaguri", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_ORIENTCLAM.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_RICE = register("block_boxh_rice", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_RICE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_SOY = register("block_boxh_soy", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_SOY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_SPINACH = register("block_boxh_spinach", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_SPINACH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_SQUID = register("block_boxh_squid", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_SQUID.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_TOMATO = register("block_boxh_tomato", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_TOMATO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CHERRY = register("block_boxh_cherry", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_TAKENOKO = register("block_boxh_takenoko", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_TAKENOKO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_KURI = register("block_boxh_chestnut", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_KURI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_TGREEN = register("block_boxh_tgreen", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_TGREEN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_TRED = register("block_boxh_tred", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_TRED.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> BOX_H_BPEPPER = register("block_boxh_bpepper", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_BPEPPER.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CUMIN = register("block_boxh_cumin", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CUMIN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_TURMERIC = register("block_boxh_turmeric", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_TURMERIC.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOX_H_CHILI = register("block_boxh_chili", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_CHILI.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> CHADUTSU = register("block_tea_chadutsu", (props) -> new Not_Fuel(Pantry_Blocks.CHADUTSU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANTEA = register("block_tea_can", (props) -> new Not_Fuel(Pantry_Blocks.CANTEA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAWARA = register("block_tawara_cm", (props) -> new Fuel_200(Pantry_Blocks.TAWARA.get(), props), new Item.Properties());

	/* Plate ... Need to register 1st for 'usingConvertsTo'. */
	public static final DeferredItem<Item> SARA = register("item_food_sara", Item::new, new Item.Properties());
	public static final DeferredItem<Item> YUNOMI = register("item_food_yunomi", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> TCUP_kara = register("item_food_teacup", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CHAWAN = register("item_food_chawan", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHIKKI = register("item_food_shikki", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> TONSUI = register("item_food_tonsui", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> DONBURI = register("item_food_donburi", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> DRINKGLASS = register("item_food_driglass", Item::new, new Item.Properties());
	public static final DeferredItem<Item> SAKEBOTTLE = register("item_food_sakebot", Item::new, new Item.Properties());
	public static final DeferredItem<Item> BENTOUHAKO = register("item_bentouhako", Item::new, new Item.Properties());

	public static final DeferredItem<Item> CLAY_SARA = register("item_clay_sara", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_YUNOMI = register("item_clay_yunomi", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_KYUSU = register("item_clay_kyusu", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_TCUP = register("item_clay_teacup", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_TPOT = register("item_clay_teapot", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_CHAWAN = register("item_clay_chawan", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_NABE = register("item_clay_nabe", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_TONSUI = register("item_clay_tonsui", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CLAY_DONBURI = register("item_clay_donburi", AddInfo_Item::new, new Item.Properties());

	/* Barrel */
	public static final DeferredItem<Item> KOUBOBOT_full = register("block_bin_koubo_f", (props) -> new AddInfo_ItemBlock(Hakkou_Blocks.KOUBOBOT_full.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NYUSANBOT_full = register("block_bin_nyusan_f", (props) -> new AddInfo_ItemBlock(Hakkou_Blocks.NYUSANBOT_full.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUBO = register("item_koubo", KouboNyusan::new, new Item.Properties());
	public static final DeferredItem<Item> NYUSAN = register("item_nyusan", KouboNyusan::new, new Item.Properties());

	public static final DeferredItem<Item> MIZUOKE = register("block_mizuoke", (props) -> new Mizuoke_Item(Fluids.EMPTY, Hakkou_Blocks.MIZUOKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MIZUOKE_full = register("block_mizuoke_full", (props) -> new MizuokeFull_Item(Fluids.WATER, Hakkou_Blocks.MIZUOKE_full.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MIZUOKE_Milk = register("item_mizuoke_milk", MizuokeMilk_Item::new, new Item.Properties());

	public static final DeferredItem<Item> CHEESE_CURD = register("block_food_cheesecurd", (props) -> new Not_Fuel(Hakkou_Blocks.CHEESE_CURD.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHEESE = register("block_food_cheese_1", (props) -> new Not_Fuel(Hakkou_Blocks.CHEESE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FCHEESE = register("item_food_cheesef", Item::new, new Item.Properties().food(FoodPoints.FPS1_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> PC_CHEESE = register("item_food_cheese", Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.PC_CHEESE));
	public static final DeferredItem<Item> CHEESE_TANA = register("block_kit_cheese_tana", (props) -> new Fuel_150(Hakkou_Blocks.CHEESE_TANA.get(), props), new Item.Properties());

	public static final DeferredItem<Item> HAKKOU_TARU = register("block_taru_hakkou", (props) -> new Not_Fuel(Hakkou_Blocks.HAKKOU_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUJI_TARU = register("block_taru_kouji_f", (props) -> new Not_Fuel(Hakkou_Blocks.KOUJI_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHUBO_TARU = register("block_taru_shubo_f", (props) -> new Not_Fuel(Hakkou_Blocks.SHUBO_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MOROMI_TARU = register("block_taru_moromi_f", (props) -> new Not_Fuel(Hakkou_Blocks.MOROMI_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> JUKUSEI_TARU = register("block_taru_jukusei_f", (props) -> new Not_Fuel(Hakkou_Blocks.JUKUSEI_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RINGOSHU_TARU = register("block_taru_ringoshu_f", (props) -> new Not_Fuel(Hakkou_Blocks.RINGOSHU_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BUDOUSHU_TARU = register("block_taru_budoushu_f", (props) -> new Not_Fuel(Hakkou_Blocks.BUDOUSHU_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> HACHIMITSU_TARU = register("block_taru_hachimitsushu_f", (props) -> new Not_Fuel(Hakkou_Blocks.HACHIMITSU_TARU.get(), props), new Item.Properties());

	/* Bottle */
	public static final DeferredItem<Item> NAMASAKEBOT = register("block_bot_sakenama_1", (props) -> new Not_Fuel(Hakkou_Blocks.NAMASAKEBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SAKEBOT = register("block_bot_sake_1", (props) -> new Not_Fuel(Hakkou_Blocks.SAKEBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> JUKUSAKEBOT = register("block_bot_sakejuku_1", (props) -> new Not_Fuel(Hakkou_Blocks.JUKUSAKEBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEAMAZAKE_nama = register("block_food_nabeaz_n", (props) -> new Not_Fuel(Hakkou_Blocks.NABEAMAZAKE_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEAMAZAKE = register("block_food_nabeaz_1", (props) -> new Not_Fuel(Hakkou_Blocks.NABEAMAZAKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CIDERBOT = register("block_bot_cider_1", (props) -> new Not_Fuel(Hakkou_Blocks.CIDERBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> JUKUCIDERBOT = register("block_bot_ciderjuku_1", (props) -> new Not_Fuel(Hakkou_Blocks.JUKUCIDERBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINEBOT = register("block_bot_wine_1", (props) -> new Not_Fuel(Hakkou_Blocks.WINEBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> JUKUWINEBOT = register("block_bot_winejuku_1", (props) -> new Not_Fuel(Hakkou_Blocks.JUKUWINEBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MEADBOT = register("block_bot_mead_1", (props) -> new Not_Fuel(Hakkou_Blocks.MEADBOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> JUKUMEADBOT = register("block_bot_meadjuku_1", (props) -> new Not_Fuel(Hakkou_Blocks.JUKUMEADBOT.get(), props), new Item.Properties());
	
	/* Glass */
	public static final DeferredItem<Item> NAMASAKEGLASS = register("block_glass_sakenama", (props) -> new SakeGlass(Hakkou_Blocks.NAMASAKEGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.NAMASAKE));
	public static final DeferredItem<Item> SAKEGLASS = register("block_glass_sake", (props) -> new SakeGlass(Hakkou_Blocks.SAKEGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.SAKE));
	public static final DeferredItem<Item> JUKUSAKEGLASS = register("block_glass_sakejuku", (props) -> new SakeGlass(Hakkou_Blocks.JUKUSAKEGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.JUKUSAKE));
	public static final DeferredItem<Item> AMAZAKEGLASS = register("block_glass_amazake", (props) -> new Yunomi_Item(Hakkou_Blocks.AMAZAKEGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.D_D2000));
	public static final DeferredItem<Item> CIDERGLASS = register("block_glass_cider", (props) -> new SakeGlass(Hakkou_Blocks.CIDERGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.CIDERWINE));
	public static final DeferredItem<Item> JUKUCIDERGLASS = register("block_glass_ciderjuku", (props) -> new SakeGlass(Hakkou_Blocks.JUKUCIDERGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.JUKUCIDERWINE));
	public static final DeferredItem<Item> WINEGLASS = register("block_glass_wine", (props) -> new SakeGlass(Hakkou_Blocks.WINEGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.CIDERWINE));
	public static final DeferredItem<Item> JUKUWINEGLASS = register("block_glass_winejuku", (props) -> new SakeGlass(Hakkou_Blocks.JUKUWINEGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.JUKUCIDERWINE));
	public static final DeferredItem<Item> MEADGLASS = register("block_glass_mead", (props) -> new SakeGlass(Hakkou_Blocks.MEADGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.MEAD));
	public static final DeferredItem<Item> JUKUMEADGLASS = register("block_glass_meadjuku", (props) -> new SakeGlass(Hakkou_Blocks.JUKUMEADGLASS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.JUKUMEAD));

	public static final DeferredItem<Item> WINE_TANA = register("block_kit2_tana", (props) -> new Fuel_150(Hakkou_Blocks.WINE_TANA.get(), props), new Item.Properties());

	public static final DeferredItem<Item> MISO_TARU = register("block_taru_miso_f", (props) -> new Not_Fuel(Hakkou_Blocks.MISO_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> HAKUSAI_TARU1 = register("block_taru_hakusai_f", (props) -> new Not_Fuel(Hakkou_Blocks.HAKUSAI_TARU1.get(), props), new Item.Properties());
	public static final DeferredItem<Item> HAKUSAI_TARU2 = register("block_taru_hakusai_f2", (props) -> new Not_Fuel(Hakkou_Blocks.HAKUSAI_TARU2.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUYU_TARU = register("block_taru_shouyu_f", (props) -> new Not_Fuel(Hakkou_Blocks.SHOUYU_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOMEZU_TARU = register("block_taru_komezu_f", (props) -> new Not_Fuel(Hakkou_Blocks.KOMEZU_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KINOKO_TARU = register("block_taru_kinoko_f", (props) -> new Not_Fuel(Hakkou_Blocks.KINOKO_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KONBU_TARU = register("block_taru_konbu_f", (props) -> new Not_Fuel(Hakkou_Blocks.KONBU_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NORI_TARU = register("block_taru_nori_f", (props) -> new Not_Fuel(Hakkou_Blocks.NORI_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUCHA_TARU = register("block_taru_koucha_f", (props) -> new Not_Fuel(Hakkou_Blocks.KOUCHA_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PEPPER_TARU = register("block_taru_pepper_f", (props) -> new Not_Fuel(Hakkou_Blocks.PEPPER_TARU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> VANILLA_TARU = register("block_taru_vanilla_f", (props) -> new Not_Fuel(Hakkou_Blocks.VANILLA_TARU.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUYU_bot_14 = register("block_shouyu_bot", (props) -> new BottleShouyu_1(Hakkou_Blocks.SHOUYU_bot_14.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOMEZU_bot_12 = register("block_komezu_bot", (props) -> new BottleKomezu_1(Hakkou_Blocks.KOMEZU_bot_12.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOYOIL_bot_12 = register("block_soyoil_bot", (props) -> new BottleSoyoil_1(Dish_Blocks.SOYOIL_bot_12.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DASHI_bot_14 = register("block_dashi_bot", (props) -> new BottleDashi_1(Hakkou_Blocks.DASHI_bot_14.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OSAUCE_bot_14 = register("block_osauce_bot", (props) -> new BottleOsauce_1(Dish_Blocks.OSAUCE_bot_14.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MAYO_bot_14 = register("block_mayo_bot", (props) -> new BottleMayo_1(Dish_Blocks.MAYO_bot_14.get(), props), new Item.Properties());
	public static final DeferredItem<Item> VANILLA_bot_14 = register("block_vanilla_bot", (props) -> new BottleVanilla_1(Hakkou_Blocks.VANILLA_bot_14.get(), props), new Item.Properties());

	public static final DeferredItem<Item> MATCH = register("item_match_cm", Match_Item::new, new Item.Properties());

	/* Salt */
	public static final DeferredItem<Item> ENDEN = register("block_enden", (props) -> new Not_Fuel(Crop_Blocks.ENDEN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHIO = register("item_salt", Item::new, new Item.Properties());
	public static final DeferredItem<Item> NIGARI = register("item_nigari", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RENNET = register("item_rennet", AddInfo_Item::new, new Item.Properties());

	public static final DeferredItem<Item> MUSHIGOME = register("item_mushigome", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> MUSHIGOME_TAKE = register("item_mushigome_take", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> MUSHIGOME_KURI = register("item_mushigome_kuri", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> MUSHI_SEKIHAN = register("item_mushisekihan", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> KOMEKOUJI = register("item_komekouji", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHUBO = register("item_shubo", AddInfo_Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> SAKEKASU = register("item_sakekasu", AddInfo_Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> MOROMI = register("item_moromi", Moromi::new, new Item.Properties());

	public static final DeferredItem<Item> NIMAME = register("item_nimame", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> MISO = register("item_miso", Item::new, new Item.Properties());
	public static final DeferredItem<Item> YUDEAZUKI = register("item_azuki_boil", Item::new, new Item.Properties());
	public static final DeferredItem<Item> ANKO = register("item_anko", AddInfo_Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> CUSTARD = register("item_custard", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> CUSTARD_CREAM = register("item_custard_cream", Item::new, new Item.Properties().craftRemainder(Items.BOWL));

	public static final DeferredItem<Item> BOWL_ICE_VANILLA = register("item_bowl_icecream", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_ICE_GREEN = register("item_bowl_icecream_greentea", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_ICE_RED = register("item_bowl_icecream_redtea", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_ICE_CACAO = register("item_bowl_icecream_cacao", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	
	public static final DeferredItem<Item> BOWL_KANTEN_APPLE = register("item_bowl_kanten_apple", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_KANTEN_CHERRY = register("item_bowl_kanten_cherry", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_KANTEN_CITRUS = register("item_bowl_kanten_citrus", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_KANTEN_GRAPE = register("item_bowl_kanten_grape", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_KANTEN_MILK = register("item_bowl_kanten_milk", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_YOKAN = register("item_bowl_yokan", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> BOWL_YOKAN_MATCHA = register("item_bowl_yokan_matcha", Item::new, new Item.Properties().craftRemainder(Items.BOWL));

	public static final DeferredItem<Item> KOMUGI = register("item_flour", Item::new, new Item.Properties());
	public static final DeferredItem<Item> BUTTER = register("item_butter", Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_BURG = register("item_kiji_burg", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_BUN = register("item_kiji_bun", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_ANPAN = register("item_kiji_pananko", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_CUSTARD_PAN = register("item_kiji_pancustard", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_APPLE_PAN = register("item_kiji_panapple", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_CHERRY_PAN = register("item_kiji_pancherry", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_CITRUS_PAN = register("item_kiji_pancitrus", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_GRAPE_PAN = register("item_kiji_pangrape", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_GREENTEA_PAN = register("item_kiji_pangreentea", AddInfo_Item::new, new Item.Properties());

	public static final DeferredItem<Item> KIJI_SCONE= register("item_kiji_scone", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_SENBEI = register("item_kiji_senbei", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KIJI_PIZA = register("item_kiji_pizza", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PIZZA_nama = register("item_food_pizza_n", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PIZZAC_nama = register("item_food_pizza_cn", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PIZZAT_nama = register("item_food_pizza_tn", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PIZZAS_nama = register("item_food_pizza_sn", Item::new, new Item.Properties());

	public static final DeferredItem<Item> PASTA_nama = register("item_food_pasta_n", Item::new, new Item.Properties());
	public static final DeferredItem<Item> PASTA_sara = register("item_food_pasta_s", Item::new, new Item.Properties());
	public static final DeferredItem<Item> UDON_nama = register("item_food_udon_n", Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHOUYU_donburi = register("item_food_shouyu_don", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> TSUYU_donburi = register("item_food_tsuyu_don", AddInfo_Item::new, new Item.Properties());

	public static final DeferredItem<Item> KEIRYO_CUP = register("block_measurecup", (props) -> new MeasureCup_Item(Fluids.EMPTY, Dish_Blocks.KEIRYO_CUP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KEIRYO_CUP_full = register("item_measurecup_full", MeasurecupFull_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KANSUI = register("item_kansui", MeasurecupFull_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAMEN_nama = register("item_food_ramen_n", Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHOUYU_TARE = register("item_food_tare_shouyu", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> MISO_TARE = register("item_food_tare_miso", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHIO_TARE = register("item_food_tare_shio", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHOUYU_Rsoup = register("item_food_rsoup_shouyu", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> MISO_Rsoup = register("item_food_rsoup_miso", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHIO_Rsoup = register("item_food_rsoup_shio", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SOBA_PLATE = register("item_food_sobaplate", AddInfo_Item::new, new Item.Properties());

	/* Cooking */
	public static final DeferredItem<Item> ZUNDOU = register("block_food_zundou", (props) -> new ZundouKara_Item(Dish_Blocks.ZUNDOU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_MIZU = register("block_zundou_mizu", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_MIZU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_SHIO = register("block_zundou_shiomizu", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_SHIO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_MILK = register("block_zundou_milk", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_MILK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ZUNDOU_NCURRY = register("block_food_cunabe_n", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_NCURRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_CURRY = register("block_food_cunabe_1", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_CURRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_NCURRY_C = register("block_food_cunabe_cn", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_NCURRY_C.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_CURRY_C = register("block_food_cunabe_c1", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_CURRY_C.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_NCURRY_T = register("block_food_cunabe_tn", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_NCURRY_T.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_CURRY_T = register("block_food_cunabe_t1", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_CURRY_T.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ZUNDOU_NSTEW = register("block_food_stewnabe_n", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_NSTEW.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_STEW = register("block_food_stewnabe_1", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_STEW.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_DASHI = register("block_food_dashinabe_1", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_DASHI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_RSOUP_nama = register("block_food_rsoup_n", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_RSOUP_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZUNDOU_RSOUP = register("block_food_rsoup_1", (props) -> new Not_Fuel(Dish_Blocks.ZUNDOU_RSOUP.get(), props), new Item.Properties());

	public static final DeferredItem<Item> NABE_kara = register("block_food_karanabe", (props) -> new DonabeKara_Item(Fluids.EMPTY, Dish_Blocks.NABE_kara.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABETORI_nama = register("block_food_nabe_n", (props) -> new Not_Fuel(Dish_Blocks.NABETORI_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEMISO_nama = register("block_food_nabemiso_n", (props) -> new Not_Fuel(Dish_Blocks.NABEMISO_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEGOHAN_nama = register("block_food_nabegohan_n", (props) -> new Not_Fuel(Dish_Blocks.NABEGOHAN_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEGOHANTAKE_nama = register("block_food_nabegohantake_n", (props) -> new Not_Fuel(Dish_Blocks.NABEGOHANTAKE_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEGOHANKURI_nama = register("block_food_nabegohankuri_n", (props) -> new Not_Fuel(Dish_Blocks.NABEGOHANKURI_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABESEKIHAN_nama = register("block_food_nabesekihan_n", (props) -> new Not_Fuel(Dish_Blocks.NABESEKIHAN_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABECORN_nama = register("block_food_nabecorns_n", (props) -> new Not_Fuel(Dish_Blocks.NABECORN_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABESHIO_nama = register("block_food_nabeshio_n", (props) -> new Not_Fuel(Dish_Blocks.NABESHIO_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABENIMAME_nama = register("block_food_nabenimame_n", (props) -> new Not_Fuel(Dish_Blocks.NABENIMAME_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABETOUFU_nama = register("block_food_nabetoufu_n", (props) -> new Not_Fuel(Dish_Blocks.NABETOUFU_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEAZUKI_nama = register("block_food_nabeazuki_n", (props) -> new Not_Fuel(Dish_Blocks.NABEAZUKI_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEANKO_nama = register("block_food_nabeanko_n", (props) -> new Not_Fuel(Dish_Blocks.NABEANKO_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEPUDDING_nama = register("block_food_nabepudding_n", (props) -> new Not_Fuel(Dish_Blocks.NABEPUDDING_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEPUDDING_green = register("block_food_nabepudding_g", (props) -> new Not_Fuel(Dish_Blocks.NABEPUDDING_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEPUDDING_red = register("block_food_nabepudding_r", (props) -> new Not_Fuel(Dish_Blocks.NABEPUDDING_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEPUDDING_cacao = register("block_food_nabepudding_c", (props) -> new Not_Fuel(Dish_Blocks.NABEPUDDING_cacao.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABE_CREAM = register("block_food_nabecream", (props) -> new Not_Fuel(Dish_Blocks.NABE_CREAM.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABETENGUSA_nama = register("block_food_nabetengusa_n", (props) -> new Not_Fuel(Dish_Blocks.NABETENGUSA_nama.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> NABETORI = register("block_food_nabe_1", (props) -> new Not_Fuel(Dish_Blocks.NABETORI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEMISO = register("block_food_nabemiso_1", (props) -> new Not_Fuel(Dish_Blocks.NABEMISO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEZENZAI_M = register("block_food_nabezenzai_m", (props) -> new Not_Fuel(Dish_Blocks.NABEZENZAI_M.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEZENZAI_K = register("block_food_nabezenzai_k", (props) -> new Not_Fuel(Dish_Blocks.NABEZENZAI_K.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEGOHAN = register("block_food_nabegohan_1", (props) -> new Not_Fuel(Dish_Blocks.NABEGOHAN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEGOHAN_TAKE = register("block_food_nabegohantake_1", (props) -> new Not_Fuel(Dish_Blocks.NABEGOHAN_TAKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABEGOHAN_KURI = register("block_food_nabegohankuri_1", (props) -> new Not_Fuel(Dish_Blocks.NABEGOHAN_KURI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABESEKIHAN = register("block_food_nabesekihan_1", (props) -> new Not_Fuel(Dish_Blocks.NABESEKIHAN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NABECORN = register("block_food_nabecorns_1", (props) -> new Not_Fuel(Dish_Blocks.NABECORN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> FRYPAN_kara = register("block_food_frypan", (props) -> new Not_Fuel(Dish_Blocks.FRYPAN_kara.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPTAMAGO_nama = register("block_food_frypan_n_tamago", (props) -> new Not_Fuel(Dish_Blocks.FPTAMAGO_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPGYUDON_nama = register("block_food_frypan_n_gyudon", (props) -> new Not_Fuel(Dish_Blocks.FPGYUDON_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPOYAKODON_nama = register("block_food_frypan_n_oyakodon", (props) -> new Not_Fuel(Dish_Blocks.FPOYAKODON_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPKATSU_nama = register("block_food_frypan_n_katsu", (props) -> new Not_Fuel(Dish_Blocks.FPKATSU_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPKATSU_bake = register("block_food_frypan_b_katsu", Item::new, new Item.Properties());
	public static final DeferredItem<Item> FPKATSUDON_nama = register("block_food_frypan_n_katsudon", (props) -> new Not_Fuel(Dish_Blocks.FPKATSUDON_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPEGGBURG_nama = register("block_food_frypan_n_eggb", (props) -> new Not_Fuel(Dish_Blocks.FPEGGBURG_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPTOMATO_nama = register("block_food_frypan_n_tomatos", (props) -> new Not_Fuel(Dish_Blocks.FPTOMATO_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPKINOKO_nama = register("block_food_frypan_n_kinokos", (props) -> new Not_Fuel(Dish_Blocks.FPKINOKO_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPSEAFOOD_nama = register("block_food_frypan_n_seafood", (props) -> new Not_Fuel(Dish_Blocks.FPSEAFOOD_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPKINOKOAK_nama = register("block_food_frypan_n_kinokoak", (props) -> new Not_Fuel(Dish_Blocks.FPKINOKOAK_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPKINOKOAK = register("item_food_frypan_b_kinokoak", KinokoAK_Item::new, new Item.Properties());
	public static final DeferredItem<Item> FPCURRY_nama = register("block_food_frypan_n_roux", (props) -> new Not_Fuel(Dish_Blocks.FPCURRY_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FPOSAUCE_nama = register("block_food_frypan_n_osauce", (props) -> new Not_Fuel(Dish_Blocks.FPOSAUCE_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OKONOMIYAKI_nama = register("block_food_teppan_n_okonomiyaki", (props) -> new Not_Fuel(Dish_Blocks.OKONOMIYAKI_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OKONOMIS_nama = register("block_food_teppan_n_okonomis", (props) -> new Not_Fuel(Dish_Blocks.OKONOMIS_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OKONOMIC_nama = register("block_food_teppan_n_okonomic", (props) -> new Not_Fuel(Dish_Blocks.OKONOMIC_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OKONOMISOBA_nama = register("block_food_teppan_n_okonomisoba", (props) -> new Not_Fuel(Dish_Blocks.OKONOMISOBA_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OKONOMISOBAS_nama = register("block_food_teppan_n_okonomisobas", (props) -> new Not_Fuel(Dish_Blocks.OKONOMISOBAS_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OKONOMISOBAC_nama = register("block_food_teppan_n_okonomisobac", (props) -> new Not_Fuel(Dish_Blocks.OKONOMISOBAC_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKISOBA_nama = register("block_food_teppan_n_yakisoba", (props) -> new Not_Fuel(Dish_Blocks.YAKISOBA_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKISOBASHIO_nama = register("block_food_teppan_n_yakisobashio", (props) -> new Not_Fuel(Dish_Blocks.YAKISOBASHIO_nama.get(), props), new Item.Properties());

	public static final DeferredItem<Item> NIBOSHI = register("block_niboshi", (props) -> new Not_Fuel(Dish_Blocks.NIBOSHI.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CURRY = register("block_food_curry_1", (props) -> new Dish_Plate(Dish_Blocks.CURRY.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3600));
	public static final DeferredItem<Item> CURRYSET = register("block_food_curryset_1", (props) -> new Not_Fuel(Dish_Blocks.CURRYSET.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURRY_C = register("block_food_curry_c1", (props) -> new Dish_Plate(Dish_Blocks.CURRY_C.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3600));
	public static final DeferredItem<Item> CURRYSET_C = register("block_food_curryset_c1", (props) -> new Not_Fuel(Dish_Blocks.CURRYSET_C.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURRY_T = register("block_food_curry_t1", (props) -> new Dish_Plate(Dish_Blocks.CURRY_T.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> CURRYSET_T = register("block_food_curryset_t1", (props) -> new Not_Fuel(Dish_Blocks.CURRYSET_T.get(), props), new Item.Properties());

	public static final DeferredItem<Item> STEW = register("block_food_stew_1", (props) -> new Dish_Plate(Dish_Blocks.STEW.get(), props), new Item.Properties().food(FoodPoints.DPS8_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> UDON_SU = register("block_food_udonsu_1", (props) -> new Dish_Donburi(Dish_Blocks.UDON_SU.get(), props), new Item.Properties().food(FoodPoints.DPS5_06, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> UDON_NIKU = register("block_food_udonniku_1", (props) -> new Dish_Donburi(Dish_Blocks.UDON_NIKU.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> UDON_TSUKIMI = register("block_food_udontsukimi_1", (props) -> new Dish_Donburi(Dish_Blocks.UDON_TSUKIMI.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> RAMEN_SHOUYU = register("block_food_ramenshouyu_1", (props) -> new Dish_Donburi(Dish_Blocks.RAMEN_SHOUYU.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3500));
	public static final DeferredItem<Item> RAMEN_MISO = register("block_food_ramenmiso_1", (props) -> new Dish_Donburi(Dish_Blocks.RAMEN_MISO.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3500));
	public static final DeferredItem<Item> RAMEN_SHIO = register("block_food_ramenshio_1", (props) -> new Dish_Donburi(Dish_Blocks.RAMEN_SHIO.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3500));
	
	public static final DeferredItem<Item> TONSUITORI = register("block_food_tonsui_1", (props) -> new Dish_Tonsui(Dish_Blocks.TONSUITORI.get(), props), new Item.Properties().food(FoodPoints.DPS2_03, FoodEffects.E_DR1000));
	public static final DeferredItem<Item> MISOSOUP = register("block_food_misosp_1", (props) -> new Dish_Shikki(Dish_Blocks.MISOSOUP.get(), props), new Item.Properties().food(FoodPoints.DPS2_03, FoodEffects.E_D2000));
	public static final DeferredItem<Item> ZENZAI_M = register("block_food_zenzai_m", (props) -> new Dish_Shikki(Dish_Blocks.ZENZAI_M.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_D2000));
	public static final DeferredItem<Item> ZENZAI_K = register("block_food_zenzai_k", (props) -> new Dish_Shikki(Dish_Blocks.ZENZAI_K.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_D2000));
	public static final DeferredItem<Item> GOHAN = register("block_food_gohan_1", (props) -> new Dish_Chawan(Dish_Blocks.GOHAN.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> GOHAN_TAKE = register("block_food_gohantake_1", (props) -> new Dish_Chawan(Dish_Blocks.GOHAN_TAKE.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_D500));
	public static final DeferredItem<Item> GOHAN_KURI = register("block_food_gohankuri_1", (props) -> new Dish_Chawan(Dish_Blocks.GOHAN_KURI.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_D500));
	public static final DeferredItem<Item> SEKIHAN = register("block_food_sekihan", (props) -> new Dish_Chawan(Dish_Blocks.SEKIHAN.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_D500));
	public static final DeferredItem<Item> RICE = register("block_food_rice_1", (props) -> new Dish_Plate(Dish_Blocks.RICE.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> DONBURI_MESHI = register("block_food_donmeshi_1", (props) -> new Dish_Donburi(Dish_Blocks.DONBURI_MESHI.get(), props), new Item.Properties().food(FoodPoints.DPS5_06, FoodEffects.DEFAULT_FOOD));
	
	public static final DeferredItem<Item> DONBURI_GYU = register("block_food_dongyu_1", (props) -> new Dish_Donburi(Dish_Blocks.DONBURI_GYU.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> DONBURI_OYAKO = register("block_food_donoyako_1", (props) -> new Dish_Donburi(Dish_Blocks.DONBURI_OYAKO.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> DONBURI_KATSU = register("block_food_donkatsu_1", (props) -> new Dish_Donburi(Dish_Blocks.DONBURI_KATSU.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3500));
	public static final DeferredItem<Item> DONBURI_KAISEN = register("block_food_donkaisen_1", (props) -> new Dish_Donburi(Dish_Blocks.DONBURI_KAISEN.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));

	public static final DeferredItem<Item> FOOD_HAKUSAI2 = register("item_food_hakusai2", Item::new, new Item.Properties().food(FoodPoints.HAKUSAI2, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> HAKUSAIDUKE = register("block_food_hsd_1", (props) -> new Dish_Plate(Dish_Blocks.HAKUSAIDUKE.get(), props), new Item.Properties().food(FoodPoints.DPS1_02, FoodEffects.E_D500));
	public static final DeferredItem<Item> TAMAGOYAKI = register("block_food_tgy_1", (props) -> new Dish_Tamagoyaki(Dish_Blocks.TAMAGOYAKI.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_R200));

	public static final DeferredItem<Item> TAMAGOYAKITEI = register("block_food_tgytei_1", (props) -> new Not_Fuel(Dish_Blocks.TAMAGOYAKITEI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIZAKANATEI = register("block_food_yakizakanatei_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIZAKANATEI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIJYAKETEI = register("block_food_yakijyaketei_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIJYAKETEI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAMAGOYAKITEI_TAKE = register("block_food_tgyteitake_1", (props) -> new Not_Fuel(Dish_Blocks.TAMAGOYAKITEI_TAKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIZAKANATEI_TAKE = register("block_food_yakizakanateitake_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIZAKANATEI_TAKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIJYAKETEI_TAKE = register("block_food_yakijyaketeitake_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIJYAKETEI_TAKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAMAGOYAKITEI_KURI = register("block_food_tgyteikuri_1", (props) -> new Not_Fuel(Dish_Blocks.TAMAGOYAKITEI_KURI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIZAKANATEI_KURI = register("block_food_yakizakanateikuri_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIZAKANATEI_KURI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIJYAKETEI_KURI = register("block_food_yakijyaketeikuri_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIJYAKETEI_KURI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAMAGOYAKITEI_SEKI = register("block_food_tgytei_sekihan_1", (props) -> new Not_Fuel(Dish_Blocks.TAMAGOYAKITEI_SEKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIZAKANATEI_SEKI = register("block_food_yakizakanatei_sekihan_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIZAKANATEI_SEKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> YAKIJYAKETEI_SEKI = register("block_food_yakijyaketei_sekihan_1", (props) -> new Not_Fuel(Dish_Blocks.YAKIJYAKETEI_SEKI.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CORNSOUP = register("block_food_cornsp_1", (props) -> new Dish_Plate(Dish_Blocks.CORNSOUP.get(), props), new Item.Properties().food(FoodPoints.DPS2_03, FoodEffects.E_D2000));

	public static final DeferredItem<Item> EGGBURG = register("block_food_egb_1", (props) -> new Dish_Plate(Dish_Blocks.EGGBURG.get(), props), new Item.Properties().food(FoodPoints.DPS5_06, FoodEffects.E_R300));
	public static final DeferredItem<Item> EGGBURGSET = register("block_food_egbset_1", (props) -> new Not_Fuel(Dish_Blocks.EGGBURGSET.get(), props), new Item.Properties());

	public static final DeferredItem<Item> PASTATOMATO = register("block_food_pastatoma_1", (props) -> new Dish_Plate(Dish_Blocks.PASTATOMATO.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> PASTACHEESE = register("block_food_pastacheese_1", (props) -> new Dish_Plate(Dish_Blocks.PASTACHEESE.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> PASTAKINOKO = register("block_food_pastakinoko_1", (props) -> new Dish_Plate(Dish_Blocks.PASTAKINOKO.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> PASTASEAFOOD = register("block_food_pastaseafood_1", (props) -> new Dish_Plate(Dish_Blocks.PASTASEAFOOD.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3500));
	
	public static final DeferredItem<Item> PIZZA = register("block_food_pizza_1", (props) -> new Not_Fuel(Dish_Blocks.PIZZA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PIZZA_C = register("block_food_pizza_c1", (props) -> new Not_Fuel(Dish_Blocks.PIZZA_C.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PIZZA_T = register("block_food_pizza_t1", (props) -> new Not_Fuel(Dish_Blocks.PIZZA_T.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PIZZA_S = register("block_food_pizza_s1", (props) -> new Not_Fuel(Dish_Blocks.PIZZA_S.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PC_PIZZA = register("item_food_pizza", Item::new, new Item.Properties().food(FoodPoints.PC_PIZZA, FoodEffects.PC_PIZZA));
	public static final DeferredItem<Item> PC_PIZZAC = register("item_food_pizzac", Item::new, new Item.Properties().food(FoodPoints.PC_PIZZA, FoodEffects.PC_PIZZA));
	public static final DeferredItem<Item> PC_PIZZAT = register("item_food_pizzat", Item::new, new Item.Properties().food(FoodPoints.PC_PIZZA, FoodEffects.PC_PIZZA_TS));
	public static final DeferredItem<Item> PC_PIZZAS = register("item_food_pizzas", Item::new, new Item.Properties().food(FoodPoints.PC_PIZZA, FoodEffects.PC_PIZZA_TS));
	public static final DeferredItem<Item> OKONOMIYAKI = register("block_food_okonomiyaki_1", (props) -> new Dish_Plate(Dish_Blocks.OKONOMIYAKI.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> OKONOMIS = register("block_food_okonomis_1", (props) -> new Dish_Plate(Dish_Blocks.OKONOMIS.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> OKONOMIC = register("block_food_okonomic_1", (props) -> new Dish_Plate(Dish_Blocks.OKONOMIC.get(), props), new Item.Properties().food(FoodPoints.DPS8_10, FoodEffects.E_DR2400));
	public static final DeferredItem<Item> OKONOMISOBA = register("block_food_okonomisoba_1", (props) -> new Dish_Plate(Dish_Blocks.OKONOMISOBA.get(), props), new Item.Properties().food(FoodPoints.DPS12_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> OKONOMISOBAS = register("block_food_okonomisobas_1", (props) -> new Dish_Plate(Dish_Blocks.OKONOMISOBAS.get(), props), new Item.Properties().food(FoodPoints.DPS12_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> OKONOMISOBAC = register("block_food_okonomisobac_1", (props) -> new Dish_Plate(Dish_Blocks.OKONOMISOBAC.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR2400));
	public static final DeferredItem<Item> YAKISOBA = register("block_food_yakisoba_1", (props) -> new Dish_Plate(Dish_Blocks.YAKISOBA.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));
	public static final DeferredItem<Item> YAKISOBASHIO = register("block_food_yakisobashio_1", (props) -> new Dish_Plate(Dish_Blocks.YAKISOBASHIO.get(), props), new Item.Properties().food(FoodPoints.DPS10_10, FoodEffects.E_DR3000));

	public static final DeferredItem<Item> CHICKEN = register("block_food_roastchicken_1", (props) -> new Not_Fuel(Dish_Blocks.CHICKEN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHICKEN_small = register("block_food_chickenb_1", (props) -> new Dish_Plate(Dish_Blocks.CHICKEN_small.get(), props), new Item.Properties().food(FoodPoints.DPS3_04, FoodEffects.E_R200));

	public static final DeferredItem<Item> SUSHIMESHI = register("block_food_sushimeshi", (props) -> new Not_Fuel(Dish_Blocks.SUSHIMESHI.get(), props), new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> SUSHIGETA_kara = register("block_food_sushigeta_kara", (props) -> new Not_Fuel(Dish_Blocks.SUSHIGETA_kara.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SUSHISET_salmon = register("block_food_sushiset_salmon", (props) -> new Not_Fuel(Dish_Blocks.SUSHISET_salmon.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SUSHISET_fish = register("block_food_sushiset_fish", (props) -> new Not_Fuel(Dish_Blocks.SUSHISET_fish.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SUSHISET_beef = register("block_food_sushiset_beef", (props) -> new Not_Fuel(Dish_Blocks.SUSHISET_beef.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SUSHISET_tamago = register("block_food_sushiset_tamago", (props) -> new Not_Fuel(Dish_Blocks.SUSHISET_tamago.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SUSHISET_4shoku = register("block_food_sushiset_4shoku", (props) -> new Not_Fuel(Dish_Blocks.SUSHISET_4shoku.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SUSHIOKE = register("block_food_sushioke_kara", (props) -> new Not_Fuel(Dish_Blocks.SUSHIOKE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SUSHIOKE_FULL_1 = register("block_food_sushiokefull_1", (props) -> new Not_Fuel(Dish_Blocks.SUSHIOKE_FULL_1.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUYUSARA_1 = register("block_food_shouyusara_1", (props) -> new Not_Fuel(Dish_Blocks.SHOUYUSARA_1.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KIRIMI_S = register("item_food_kirimi_salmon", AddInfo_Item::new, new Item.Properties().food(FoodPoints.KIRIMI, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> KIRIMI_F = register("item_food_kirimi_fish", AddInfo_Item::new, new Item.Properties().food(FoodPoints.KIRIMI, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> KIRIMI_B = register("item_food_kirimi_beef", AddInfo_Item::new, new Item.Properties().food(FoodPoints.KIRIMI, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> KIRIMI_T = register("item_food_kirimi_tamago", AddInfo_Item::new, new Item.Properties().food(FoodPoints.KIRIMI, FoodEffects.DEFAULT_FOOD));

	public static final DeferredItem<Item> SUSHI_S = register("item_food_sushi_salmon", Item::new, new Item.Properties().food(FoodPoints.SUSHI, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> SUSHI_F = register("item_food_sushi_fish", Item::new, new Item.Properties().food(FoodPoints.SUSHI, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> SUSHI_B = register("item_food_sushi_beef", Item::new, new Item.Properties().food(FoodPoints.SUSHI, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> SUSHI_T = register("item_food_sushi_tamago", Item::new, new Item.Properties().food(FoodPoints.SUSHI, FoodEffects.DEFAULT_FOOD));

	public static final DeferredItem<Item> SHOUYUSUSHI_S = register("item_food_sushishouyu_salmon", Item::new, new Item.Properties().food(FoodPoints.SHOUYUSUSHI, FoodEffects.SHOUYUSUSHI_S));
	public static final DeferredItem<Item> SHOUYUSUSHI_F = register("item_food_sushishouyu_fish", Item::new, new Item.Properties().food(FoodPoints.SHOUYUSUSHI, FoodEffects.SHOUYUSUSHI_F));
	public static final DeferredItem<Item> SHOUYUSUSHI_B = register("item_food_sushishouyu_beef", Item::new, new Item.Properties().food(FoodPoints.SHOUYUSUSHI, FoodEffects.SHOUYUSUSHI_B));
	public static final DeferredItem<Item> SHOUYUSUSHI_T = register("item_food_sushishouyu_tamago", Item::new, new Item.Properties().food(FoodPoints.SHOUYUSUSHI, FoodEffects.SHOUYUSUSHI_T));
	public static final DeferredItem<Item> FUTOMAKI = register("item_food_futomaki", Item::new, new Item.Properties().food(FoodPoints.FUTOMAKI, FoodEffects.FUTOMAKI));

	public static final DeferredItem<Item> KETTLE_kara = register("item_kettle_kara", (props) -> new Not_Fuel(Dish_Blocks.KETTLE_kara.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KETTLE_full = register("block_kettle_full", (props) -> new Not_Fuel(Dish_Blocks.KETTLE_full.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KETTLE_boil = register("item_kettle_boil", KettleBoil::new, new Item.Properties());

	public static final DeferredItem<Item> KYUSU_kara = register("block_food_kyusu", Item::new, new Item.Properties());
	public static final DeferredItem<Item> KYUSU = register("block_food_kyusu_1", (props) -> new Not_Fuel(Dish_Blocks.KYUSU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> JPTEACUP = register("block_food_jpteacup_1", (props) -> new Yunomi_Item(Dish_Blocks.JPTEACUP.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.D_D2000));

	public static final DeferredItem<Item> JPTEASET = register("block_food_jpteaset_1", (props) -> new Not_Fuel(Dish_Blocks.JPTEASET.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHAUKE_SENBEI = register("block_food_senbei", (props) -> new Not_Fuel(Dish_Blocks.CHAUKE_SENBEI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHAUKE_MIKAN = register("block_food_mikan", (props) -> new Not_Fuel(Dish_Blocks.CHAUKE_MIKAN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TEAPOT_kara = register("block_food_teapot", Item::new, new Item.Properties());
	public static final DeferredItem<Item> TEAPOT = register("block_food_teapot_1", (props) -> new Not_Fuel(Dish_Blocks.TEAPOT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACUP = register("block_food_teacup_1", (props) -> new TeaCup_Item(Dish_Blocks.TEACUP.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.D_D2000));

	public static final DeferredItem<Item> TEASET = register("block_food_teaset_1", (props) -> new Not_Fuel(Dish_Blocks.TEASET.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHAUKE_SCONE = register("block_food_scone", (props) -> new Not_Fuel(Dish_Blocks.CHAUKE_SCONE.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SCONESET_kara = register("block_food_teastand", (props) -> new Not_Fuel(Dish_Blocks.SCONESET_kara.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCONESET_1 = register("block_food_sconeset_1", (props) -> new Not_Fuel(Dish_Blocks.SCONESET_1.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ICECREAM = register("block_food_icecream_1", (props) -> new Dish_DrinkGlass(Dish_Blocks.ICECREAM.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L3600));
	public static final DeferredItem<Item> ICECREAM_GREEN = register("block_food_icecream_greentea", (props) -> new Dish_DrinkGlass(Dish_Blocks.ICECREAM_GREEN.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L3600));
	public static final DeferredItem<Item> ICECREAM_RED = register("block_food_icecream_redtea", (props) -> new Dish_DrinkGlass(Dish_Blocks.ICECREAM_RED.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L3600));
	public static final DeferredItem<Item> ICECREAM_CACAO = register("block_food_icecream_cacao", (props) -> new Dish_DrinkGlass(Dish_Blocks.ICECREAM_CACAO.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L3600));

	public static final DeferredItem<Item> CUSTARD_PUDDING = register("block_food_pudding_custard", (props) -> new Dish_DrinkGlass(Dish_Blocks.CUSTARD_PUDDING.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L4000));
	public static final DeferredItem<Item> GREENTEA_PUDDING = register("block_food_pudding_greentea", (props) -> new Dish_DrinkGlass(Dish_Blocks.GREENTEA_PUDDING.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L4000));
	public static final DeferredItem<Item> REDTEA_PUDDING = register("block_food_pudding_redtea", (props) -> new Dish_DrinkGlass(Dish_Blocks.REDTEA_PUDDING.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L4000));
	public static final DeferredItem<Item> CACAO_PUDDING = register("block_food_pudding_cacao", (props) -> new Dish_DrinkGlass(Dish_Blocks.CACAO_PUDDING.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.E_L4000));

	public static final DeferredItem<Item> KANTEN_APPLE = register("block_food_kanten_apple", (props) -> new Dish_Kanten(Dish_Blocks.KANTEN_APPLE.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.KANTEN_APPLE));
	public static final DeferredItem<Item> KANTEN_CHERRY = register("block_food_kanten_cherry", (props) -> new Dish_Kanten(Dish_Blocks.KANTEN_CHERRY.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.KANTEN_CHERRY));
	public static final DeferredItem<Item> KANTEN_CITRUS = register("block_food_kanten_citrus", (props) -> new Dish_Kanten(Dish_Blocks.KANTEN_CITRUS.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.KANTEN_CITRUS));
	public static final DeferredItem<Item> KANTEN_GRAPE = register("block_food_kanten_grape", (props) -> new Dish_Kanten(Dish_Blocks.KANTEN_GRAPE .get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.KANTEN_GRAPE));
	public static final DeferredItem<Item> KANTEN_MILK = register("block_food_kanten_milk", (props) -> new Dish_Kanten(Dish_Blocks.KANTEN_MILK.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.KANTEN_MILK));
	public static final DeferredItem<Item> YOKAN = register("block_food_yokan", (props) -> new Dish_Kanten(Dish_Blocks.YOKAN.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.YOKAN));
	public static final DeferredItem<Item> YOKAN_MATCHA = register("block_food_yokan_matcha", (props) -> new Dish_Kanten(Dish_Blocks.YOKAN_MATCHA.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.YOKAN_MATCHA));

	
	/* FOOD */
	public static final DeferredItem<Item> CAKE = register("item_food_cake", Item::new, new Item.Properties().food(FoodPoints.CAKE, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> BUN = register("item_food_bun", Item::new, new Item.Properties().food(FoodPoints.BUN, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> SCONE = register("item_food_scone", Item::new, new Item.Properties().food(FoodPoints.FPS3_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> SENBEI = register("item_food_senbei", AddInfo_Item::new, new Item.Properties().food(FoodPoints.FPS3_03, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> TOUFU = register("item_food_toufu", Item::new, new Item.Properties().food(FoodPoints.FPS3_03, FoodEffects.DEFAULT_FOOD));

	public static final DeferredItem<Item> CHICKENSAND = register("item_food_chickensand", Item::new, new Item.Properties().food(FoodPoints.SANDWICH));
	public static final DeferredItem<Item> EGGSAND = register("item_food_eggsand", Item::new, new Item.Properties().food(FoodPoints.SANDWICH));

	public static final DeferredItem<Item> NORIAMI = register("block_noriami", (props) -> new Not_Fuel(Crop_Blocks.NORIAMI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NORI_N = register("item_food_norinama", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> NORI_I = register("item_food_noriita", Item::new, new Item.Properties().food(FoodPoints.NORI_I, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> ONIGIRI = register("item_food_onigiri", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> ONIGIRI_SHAKE = register("item_food_onigirishake", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.ONIGIRISHAKE));
	public static final DeferredItem<Item> ONIGIRI_TAKE = register("item_food_onigiritakenoko", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));
	public static final DeferredItem<Item> ONIGIRI_KURI = register("item_food_onigirikuri", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));
	public static final DeferredItem<Item> ONIGIRI_SEKIHAN = register("item_food_onigirisekihan", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));

	public static final DeferredItem<Item> KUSHI_SAKANA = register("item_kushi_sakana", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KUSHI_SAKANA_C = register("item_kushi_sakana_c", Item::new, new Item.Properties().food(FoodPoints.DPS5_06, FoodEffects.E_D1000).usingConvertsTo(Items.STICK));

	public static final DeferredItem<Item> BENTOU = register("item_bentou", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> SHAKEBEN = register("item_bentoushake", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> BENTOU_TAKE = register("item_bentou_take", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> SHAKEBEN_TAKE = register("item_bentoushake_take", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> BENTOU_KURI = register("item_bentou_kuri", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> SHAKEBEN_KURI = register("item_bentoushake_kuri", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> BENTOU_SEKI = register("item_bentou_sekihan", Bentou::new, new Item.Properties());
	public static final DeferredItem<Item> SHAKEBEN_SEKI = register("item_bentoushake_sekihan", Bentou::new, new Item.Properties());
	
	public static final DeferredItem<Item> MOCHI = register("item_mochi", Item::new, new Item.Properties());
	public static final DeferredItem<Item> MOCHI_NORI = register("item_food_mochinori", AddInfo_Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));
	public static final DeferredItem<Item> MOCHI_KINAKO = register("item_food_mochikinako", AddInfo_Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));
	public static final DeferredItem<Item> MOCHI_ANKO = register("item_food_mochianko", AddInfo_Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.MOCHI_ANKO));
	public static final DeferredItem<Item> MOCHI_OHAGI = register("item_food_mochiohagi", AddInfo_Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.MOCHI_ANKO));
	public static final DeferredItem<Item> MOCHI_SAKURA = register("item_food_mochisakura", AddInfo_Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.MOCHI_SAKURA));

	public static final DeferredItem<Item> ANPAN = register("item_food_pananko", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));
	public static final DeferredItem<Item> CUSTARD_PAN = register("item_food_pancustard", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.E_D500));
	public static final DeferredItem<Item> APPLE_PAN = register("item_food_panapple", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.PAN_APPLE));
	public static final DeferredItem<Item> CHERRY_PAN = register("item_food_pancherry", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.PAN_CHERRY));
	public static final DeferredItem<Item> CITRUS_PAN = register("item_food_pancitrus", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.PAN_CITRUS));
	public static final DeferredItem<Item> GRAPE_PAN = register("item_food_pangrape", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.PAN_GRAPE));
	public static final DeferredItem<Item> GREENTEA_PAN = register("item_food_pangreentea", Item::new, new Item.Properties().food(FoodPoints.FPS4_06, FoodEffects.PAN_TEA));

	/* Kitchen */
	public static final DeferredItem<Item> KIT_TANA = register("block_kit_tana", (props) -> new Fuel_150(Kitchen_Blocks.KIT_TANA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KITCHEN = register("block_kitchen", (props) -> new Fuel_150(Kitchen_Blocks.KITCHEN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KIT_BOARD = register("block_kit_board", (props) -> new Fuel_300(Kitchen_Blocks.KIT_BOARD.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIT_SINK = register("block_kit_sink", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_SINK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KIT_COOKTOP = register("block_kit_stove", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_COOKTOP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIT_OVEN = register("block_kit_oven", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_OVEN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIT_OVEN_B = register("block_kit_oven_black", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_OVEN_B.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IRORI = register("block_irori", (props) -> new Not_Fuel(Kitchen_Blocks.IRORI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIT_REIZOU = register("block_kit_reizou", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_REIZOU.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> KIT_KANKI_1 = register("block_kit_kanki", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_KANKI_1.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIT_HAIKIDUCT = register("block_kit_duct", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_HAIKIDUCT.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIT_DUCTEND_1 = register("block_kit_ductend", (props) -> new Not_Fuel(Kitchen_Blocks.KIT_DUCTEND_1.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TEATABLE = register("block_teatable", (props) -> new Fuel_300(Unit_Blocks.TEATABLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TOAMI = register("item_toami", (props) -> new Toami_Item(Crop_Blocks.TOAMI.get(), props), new Item.Properties().durability(32));
	public static final DeferredItem<Item> SHIKAKE_AMI = register("block_ami_shikake", (props) -> new AmiShikake_Item(Crop_Blocks.SHIKAKE_AMI.get(), props), new Item.Properties().durability(12));
	public static final DeferredItem<Item> YOUSHOKU_AMI = register("block_ami_youshoku", (props) -> new AmiYoushoku_Item(Crop_Blocks.YOUSHOKU_AMI.get(), props), new Item.Properties().durability(12));

	public static final DeferredItem<Item> CUT_IKA = register("item_squid_cut", Item::new, new Item.Properties().food(FoodPoints.CUT_IKA, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> COOKED_IKA = register("item_squid_cooked", Item::new, new Item.Properties().food(FoodPoints.COOKED_IKA, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> IKA = register("item_squid_raw", IkaRaw::new, new Item.Properties());

	public static final DeferredItem<Item> KAIHORI = register("item_kaihori", Kaihori_Item::new, new Item.Properties().durability(256));
	public static final DeferredItem<Item> HAMAGURI_COOK = register("item_food_hamaguri", HamaguriCooked::new, new Item.Properties());
	public static final DeferredItem<Item> HAMAGURI = register("block_hamaguri", (props) -> new Not_Fuel(Crop_Blocks.HAMAGURI.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KINE_YOKO = register("item_kineyoko", KineYoko_Item::new, new Item.Properties().durability(128));
	public static final DeferredItem<Item> USU_TSUKI = register("block_usutsuki", (props) -> new Fuel_300(Kitchen_Blocks.USU_TSUKI.get(), props), new Item.Properties());

	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
