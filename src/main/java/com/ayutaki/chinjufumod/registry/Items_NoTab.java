package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_NT;
import com.ayutaki.chinjufumod.items.addinfo.Iga_NT;
import com.ayutaki.chinjufumod.items.foods.FoodBuilders;
import com.ayutaki.chinjufumod.items.fuel.NoGroup_Fuel100;
import com.ayutaki.chinjufumod.items.hakkou.HakkouTips_NT;
import com.ayutaki.chinjufumod.items.weapon.AdmiralStamp_NT;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Items_NoTab {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	/* Chinjufu */
	public static Item EMBLEM_C = register("item_emblem_c", new Item(new Item.Properties()));
	public static Item ADMIRAL_STAMP = register("item_admiralstamp", new AdmiralStamp_NT(Chinjufu_Blocks.I_ADMIRAL_STAMP, new Item.Properties().durability(16)));

	/* Teatime */
	public static Item BOX_H_FISH = register("block_boxh_fish", new NoGroup_Fuel100(Pantry_Blocks.BOX_H_FISH, new Item.Properties()));
	public static Item ROTTEN_FOOD = register("item_rotten_food", new AddInfo_NT(new Item.Properties().food(FoodBuilders.ROTTEN_FOOD)));

	public static Item SHOUYU_bot_44 = register("block_shouyu_bot_4", new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_44, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item SHOUYU_bot_34 = register("block_shouyu_bot_3", new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_34, new Item.Properties().craftRemainder(Items_NoTab.SHOUYU_bot_44)));
	public static Item SHOUYU_bot_24 = register("block_shouyu_bot_2", new HakkouTips_NT(Hakkou_Blocks.SHOUYU_bot_24, new Item.Properties().craftRemainder(Items_NoTab.SHOUYU_bot_34)));
	public static Item KOMEZU_bot_22 = register("block_komezu_bot_2", new HakkouTips_NT(Hakkou_Blocks.KOMEZU_bot_22, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item SOYOIL_bot_22 = register("block_soyoil_bot_2", new HakkouTips_NT(Dish_Blocks.SOYOIL_bot_22, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item DASHI_bot_44 = register("block_dashi_bot_4", new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_44, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item DASHI_bot_34 = register("block_dashi_bot_3", new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_34, new Item.Properties().craftRemainder(Items_NoTab.DASHI_bot_44)));
	public static Item DASHI_bot_24 = register("block_dashi_bot_2", new HakkouTips_NT(Hakkou_Blocks.DASHI_bot_24, new Item.Properties().craftRemainder(Items_NoTab.DASHI_bot_34)));
	public static Item VANILLA_bot_44 = register("block_vanilla_bot_4", new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_44, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item VANILLA_bot_34 = register("block_vanilla_bot_3", new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_34, new Item.Properties().craftRemainder(Items_NoTab.VANILLA_bot_44)));
	public static Item VANILLA_bot_24 = register("block_vanilla_bot_2", new HakkouTips_NT(Hakkou_Blocks.VANILLA_bot_24, new Item.Properties().craftRemainder(Items_NoTab.VANILLA_bot_34)));

	public static Item OSAUCE_bot_44 = register("block_osauce_bot_4", new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_44, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item OSAUCE_bot_34 = register("block_osauce_bot_3", new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_34, new Item.Properties().craftRemainder(Items_NoTab.OSAUCE_bot_44)));
	public static Item OSAUCE_bot_24 = register("block_osauce_bot_2", new HakkouTips_NT(Dish_Blocks.OSAUCE_bot_24, new Item.Properties().craftRemainder(Items_NoTab.OSAUCE_bot_34)));
	public static Item MAYO_bot_44 = register("block_mayo_bot_4", new HakkouTips_NT(Dish_Blocks.MAYO_bot_44, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)));
	public static Item MAYO_bot_34 = register("block_mayo_bot_3", new HakkouTips_NT(Dish_Blocks.MAYO_bot_34, new Item.Properties().craftRemainder(Items_NoTab.MAYO_bot_44)));
	public static Item MAYO_bot_24 = register("block_mayo_bot_2", new HakkouTips_NT(Dish_Blocks.MAYO_bot_24, new Item.Properties().craftRemainder(Items_NoTab.MAYO_bot_34)));

	public static Item HAMAGURI_KARA = register("item_hamaguri_shell", new Item(new Item.Properties()));

	public static Item RAW_ICE_VANILLA = register("item_raw_icecream", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_ICE_GREEN = register("item_raw_icecream_greentea", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_ICE_RED = register("item_raw_icecream_redtea", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_ICE_CACAO = register("item_raw_icecream_cacao", new AddInfo_NT(new Item.Properties()));

	public static Item RAW_KANTEN_APPLE = register("item_raw_kanten_apple", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_KANTEN_CHERRY = register("item_raw_kanten_cherry", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_KANTEN_CITRUS = register("item_raw_kanten_citrus", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_KANTEN_GRAPE = register("item_raw_kanten_grape", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_KANTEN_MILK = register("item_raw_kanten_milk", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_YOKAN = register("item_raw_yokan", new AddInfo_NT(new Item.Properties()));
	public static Item RAW_YOKAN_MATCHA = register("item_raw_yokan_matcha", new AddInfo_NT(new Item.Properties()));
	
	/* Seasonal */
	public static Item IGA = register("item_chestnuts_burr", new Iga_NT(new Item.Properties()));
	
	/* Weapon */
	public static Item CARTRIDGE_L = register("item_cartridge_kc", new Item(new Item.Properties()));
	public static Item CARTRIDGE_M = register("item_cartridge_medium", new Item(new Item.Properties()));
	public static Item CARTRIDGE_S = register("item_cartridge_small", new Item(new Item.Properties()));
	public static Item CARTRIDGE_K = register("item_cartridge_kijyuu", new Item(new Item.Properties()));
	
	
	///* Register *///
	private static Item register(String name, Item item) {
		ITEMS.register(name, () -> item);
		return item;
	}
}
