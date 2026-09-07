package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.addinfo.Iga_Item;
import com.ayutaki.chinjufumod.items.foods.FoodEffects;
import com.ayutaki.chinjufumod.items.foods.FoodPoints;
import com.ayutaki.chinjufumod.items.fuel.Fuel_100;
import com.ayutaki.chinjufumod.items.remain.BaseBottle_4;
import com.ayutaki.chinjufumod.items.remain.BottleDashi_2;
import com.ayutaki.chinjufumod.items.remain.BottleDashi_3;
import com.ayutaki.chinjufumod.items.remain.BottleMayo_2;
import com.ayutaki.chinjufumod.items.remain.BottleMayo_3;
import com.ayutaki.chinjufumod.items.remain.BottleOsauce_2;
import com.ayutaki.chinjufumod.items.remain.BottleOsauce_3;
import com.ayutaki.chinjufumod.items.remain.BottleShouyu_2;
import com.ayutaki.chinjufumod.items.remain.BottleShouyu_3;
import com.ayutaki.chinjufumod.items.remain.BottleVanilla_2;
import com.ayutaki.chinjufumod.items.remain.BottleVanilla_3;
import com.ayutaki.chinjufumod.items.weapon.AdmiralStamp;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_NoTab {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	/* Chinjufu */
	public static final DeferredItem<Item> EMBLEM_C = register("item_emblem_c", Item::new, new Item.Properties());
	public static final DeferredItem<Item> ADMIRAL_STAMP = register("item_admiralstamp", (props) -> new AdmiralStamp(Chinjufu_Blocks.I_ADMIRAL_STAMP.get(), props), new Item.Properties().durability(16));

	/* Teatime */
	public static final DeferredItem<Item> BOX_H_FISH = register("block_boxh_fish", (props) -> new Fuel_100(Pantry_Blocks.BOX_H_FISH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ROTTEN_FOOD = register("item_rotten_food", AddInfo_Item::new, new Item.Properties().food(FoodPoints.ROTTEN_FOOD, FoodEffects.ROTTEN_FOOD));

	public static final DeferredItem<Item> SHOUYU_bot_44 = register("block_shouyu_bot_4", (props) -> new BaseBottle_4(Hakkou_Blocks.SHOUYU_bot_44.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUYU_bot_34 = register("block_shouyu_bot_3", (props) -> new BottleShouyu_3(Hakkou_Blocks.SHOUYU_bot_34.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUYU_bot_24 = register("block_shouyu_bot_2", (props) -> new BottleShouyu_2(Hakkou_Blocks.SHOUYU_bot_24.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOMEZU_bot_22 = register("block_komezu_bot_2", (props) -> new BaseBottle_4(Hakkou_Blocks.KOMEZU_bot_22.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOYOIL_bot_22 = register("block_soyoil_bot_2", (props) -> new BaseBottle_4(Dish_Blocks.SOYOIL_bot_22.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DASHI_bot_44 = register("block_dashi_bot_4", (props) -> new BaseBottle_4(Hakkou_Blocks.DASHI_bot_44.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DASHI_bot_34 = register("block_dashi_bot_3", (props) -> new BottleDashi_3(Hakkou_Blocks.DASHI_bot_34.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DASHI_bot_24 = register("block_dashi_bot_2", (props) -> new BottleDashi_2(Hakkou_Blocks.DASHI_bot_24.get(), props), new Item.Properties());
	public static final DeferredItem<Item> VANILLA_bot_44 = register("block_vanilla_bot_4", (props) -> new BaseBottle_4(Hakkou_Blocks.VANILLA_bot_44.get(), props), new Item.Properties());
	public static final DeferredItem<Item> VANILLA_bot_34 = register("block_vanilla_bot_3", (props) -> new BottleVanilla_3(Hakkou_Blocks.VANILLA_bot_34.get(), props), new Item.Properties());
	public static final DeferredItem<Item> VANILLA_bot_24 = register("block_vanilla_bot_2", (props) -> new BottleVanilla_2(Hakkou_Blocks.VANILLA_bot_24.get(), props), new Item.Properties());

	public static final DeferredItem<Item> OSAUCE_bot_44 = register("block_osauce_bot_4", (props) -> new BaseBottle_4(Dish_Blocks.OSAUCE_bot_44.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OSAUCE_bot_34 = register("block_osauce_bot_3", (props) -> new BottleOsauce_3(Dish_Blocks.OSAUCE_bot_34.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OSAUCE_bot_24 = register("block_osauce_bot_2", (props) -> new BottleOsauce_2(Dish_Blocks.OSAUCE_bot_24.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MAYO_bot_44 = register("block_mayo_bot_4", (props) -> new BaseBottle_4(Dish_Blocks.MAYO_bot_44.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MAYO_bot_34 = register("block_mayo_bot_3", (props) -> new BottleMayo_3(Dish_Blocks.MAYO_bot_34.get(), props), new Item.Properties());
	public static final DeferredItem<Item> MAYO_bot_24 = register("block_mayo_bot_2", (props) -> new BottleMayo_2(Dish_Blocks.MAYO_bot_24.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> HAMAGURI_KARA = register("item_hamaguri_shell", Item::new, new Item.Properties());

	public static final DeferredItem<Item> RAW_ICE_VANILLA = register("item_raw_icecream", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_ICE_GREEN = register("item_raw_icecream_greentea", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_ICE_RED = register("item_raw_icecream_redtea", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_ICE_CACAO = register("item_raw_icecream_cacao", AddInfo_Item::new, new Item.Properties());
	
	public static final DeferredItem<Item> RAW_KANTEN_APPLE = register("item_raw_kanten_apple", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_KANTEN_CHERRY = register("item_raw_kanten_cherry", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_KANTEN_CITRUS = register("item_raw_kanten_citrus", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_KANTEN_GRAPE = register("item_raw_kanten_grape", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_KANTEN_MILK = register("item_raw_kanten_milk", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_YOKAN = register("item_raw_yokan", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_YOKAN_MATCHA = register("item_raw_yokan_matcha", AddInfo_Item::new, new Item.Properties());
	
	/* Seasonal */
	public static final DeferredItem<Item> IGA = register("item_chestnuts_burr", Iga_Item::new, new Item.Properties());
	
	/* Weapon */
	public static final DeferredItem<Item> CARTRIDGE_L = register("item_cartridge_kc", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CARTRIDGE_M = register("item_cartridge_medium", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CARTRIDGE_S = register("item_cartridge_small", Item::new, new Item.Properties());
	public static final DeferredItem<Item> CARTRIDGE_K = register("item_cartridge_kijyuu", Item::new, new Item.Properties());

	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
