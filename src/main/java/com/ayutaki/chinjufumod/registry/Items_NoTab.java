package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_NT;
import com.ayutaki.chinjufumod.items.addinfo.Iga_NT;
import com.ayutaki.chinjufumod.items.foods.FoodBuilders;
import com.ayutaki.chinjufumod.items.fuel.NoGroup_Fuel100;
import com.ayutaki.chinjufumod.items.hakkou.HakkouTips_NT;
import com.ayutaki.chinjufumod.items.weapon.AdmiralStamp;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items_NoTab {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);
	
	/* Chinjufu */
	public static final RegistryObject<Item> EMBLEM_C = register("item_emblem_c", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ADMIRAL_STAMP = register("item_admiralstamp", () -> new AdmiralStamp(Chinjufu_Blocks.I_ADMIRAL_STAMP.get(), new Item.Properties().durability(16)));
	
	/* Teatime */
	public static final RegistryObject<Item> BOX_H_FISH = register("block_boxh_fish", () -> new NoGroup_Fuel100(Pantry_Blocks.BOX_H_FISH.get(), new Item.Properties()));
	public static final RegistryObject<Item> ROTTEN_FOOD = register("item_rotten_food", () -> new AddInfo_NT(new Item.Properties().food(FoodBuilders.ROTTEN_FOOD)));

	public static final RegistryObject<Item> SHOUYU_bot_44 = register("block_shouyu_bot_4", () -> new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_44.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SHOUYU_bot_34 = register("block_shouyu_bot_3", () -> new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_34.get(), new Item.Properties().craftRemainder(Items_NoTab.SHOUYU_bot_44.get())));
	public static final RegistryObject<Item> SHOUYU_bot_24 = register("block_shouyu_bot_2", () -> new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_24.get(), new Item.Properties().craftRemainder(Items_NoTab.SHOUYU_bot_34.get())));
	public static final RegistryObject<Item> KOMEZU_bot_22 = register("block_komezu_bot_2", () -> new HakkouTips_NT(Hakkou_Blocks.KOMEZU_bot_22.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYOIL_bot_22 = register("block_soyoil_bot_2", () -> new HakkouTips_NT(Dish_Blocks.SOYOIL_bot_22.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> DASHI_bot_44 = register("block_dashi_bot_4", () -> new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_44.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> DASHI_bot_34 = register("block_dashi_bot_3", () -> new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_34.get(), new Item.Properties().craftRemainder(Items_NoTab.DASHI_bot_44.get())));
	public static final RegistryObject<Item> DASHI_bot_24 = register("block_dashi_bot_2", () -> new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_24.get(), new Item.Properties().craftRemainder(Items_NoTab.DASHI_bot_34.get())));
	public static final RegistryObject<Item> VANILLA_bot_44 = register("block_vanilla_bot_4", () -> new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_44.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> VANILLA_bot_34 = register("block_vanilla_bot_3", () -> new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_34.get(), new Item.Properties().craftRemainder(Items_NoTab.VANILLA_bot_44.get())));
	public static final RegistryObject<Item> VANILLA_bot_24 = register("block_vanilla_bot_2", () -> new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_24.get(), new Item.Properties().craftRemainder(Items_NoTab.VANILLA_bot_34.get())));

	public static final RegistryObject<Item> OSAUCE_bot_44 = register("block_osauce_bot_4", () -> new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_44.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> OSAUCE_bot_34 = register("block_osauce_bot_3", () -> new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_34.get(), new Item.Properties().craftRemainder(Items_NoTab.OSAUCE_bot_44.get())));
	public static final RegistryObject<Item> OSAUCE_bot_24 = register("block_osauce_bot_2", () -> new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_24.get(), new Item.Properties().craftRemainder(Items_NoTab.OSAUCE_bot_34.get())));
	public static final RegistryObject<Item> MAYO_bot_44 = register("block_mayo_bot_4", () -> new HakkouTips_NT(Dish_Blocks.MAYO_bot_44.get(), new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> MAYO_bot_34 = register("block_mayo_bot_3", () -> new HakkouTips_NT(Dish_Blocks.MAYO_bot_34.get(), new Item.Properties().craftRemainder(Items_NoTab.MAYO_bot_44.get())));
	public static final RegistryObject<Item> MAYO_bot_24 = register("block_mayo_bot_2", () -> new HakkouTips_NT(Dish_Blocks.MAYO_bot_24.get(), new Item.Properties().craftRemainder(Items_NoTab.MAYO_bot_34.get())));

	public static final RegistryObject<Item> HAMAGURI_KARA = register("item_hamaguri_shell", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> RAW_ICE_VANILLA = register("item_raw_icecream", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_ICE_GREEN = register("item_raw_icecream_greentea", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_ICE_RED = register("item_raw_icecream_redtea", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_ICE_CACAO = register("item_raw_icecream_cacao", () -> new AddInfo_NT(new Item.Properties()));

	public static final RegistryObject<Item> RAW_KANTEN_APPLE = register("item_raw_kanten_apple", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_KANTEN_CHERRY = register("item_raw_kanten_cherry", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_KANTEN_CITRUS = register("item_raw_kanten_citrus", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_KANTEN_GRAPE = register("item_raw_kanten_grape", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_KANTEN_MILK = register("item_raw_kanten_milk", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_YOKAN = register("item_raw_yokan", () -> new AddInfo_NT(new Item.Properties()));
	public static final RegistryObject<Item> RAW_YOKAN_MATCHA = register("item_raw_yokan_matcha", () -> new AddInfo_NT(new Item.Properties()));
	
	/* Seasonal */
	public static final RegistryObject<Item> IGA = register("item_chestnuts_burr", () -> new Iga_NT(new Item.Properties()));
	
	/* Weapon */
	public static final RegistryObject<Item> CARTRIDGE_L = register("item_cartridge_kc", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CARTRIDGE_M = register("item_cartridge_medium", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CARTRIDGE_S = register("item_cartridge_small", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CARTRIDGE_K = register("item_cartridge_kijyuu", () -> new Item(new Item.Properties()));

	
	///* Register *///
	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}
}
