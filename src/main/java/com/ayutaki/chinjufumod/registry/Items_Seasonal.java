package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_ItemBlock;
import com.ayutaki.chinjufumod.items.armor.CMArmorMaterial;
import com.ayutaki.chinjufumod.items.armor.Costume_Santa;
import com.ayutaki.chinjufumod.items.armor.Costume_YUKATA;
import com.ayutaki.chinjufumod.items.dish.Dish_Kakigouri;
import com.ayutaki.chinjufumod.items.foods.FoodAnytime_addItem;
import com.ayutaki.chinjufumod.items.foods.FoodBuilders;
import com.ayutaki.chinjufumod.items.fuel.Fuel_100;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Fuel_200;
import com.ayutaki.chinjufumod.items.fuel.Fuel_300;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.items.teatime.Warahai_Item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items_Seasonal {
	/* 316 = 261 + (2 * 2) + (3 * 17) */
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	public static final RegistryObject<Item> WARAHAI = register("item_warahai", () -> new Warahai_Item(new Item.Properties()));
	public static final RegistryObject<Item> ORIITO = register("item_oriito", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TANMONO = register("item_tanmono", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> ZUNDOU_AKU = register("block_zundou_aku", () -> new Not_Fuel(Dish_Blocks.ZUNDOU_AKU.get(), new Item.Properties().craftRemainder(Items_Teatime.ZUNDOU.get())));

	public static final RegistryObject<Item> SUIDEN = register("block_suiden", () -> new Not_Fuel(Wood_Blocks.SUIDEN.get(), new Item.Properties()));
	public static final RegistryObject<Item> FALL_LEAF = register("block_fall_leaf", () -> new Not_Fuel(Wood_Blocks.FALL_LEAF.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAKURA_flow = register("block_tree_sakura_flow", () -> new Not_Fuel(Wood_Blocks.SAKURA_flow.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_leaf = register("block_tree_kaede_leaf", () -> new Not_Fuel(Wood_Blocks.KAEDE_leaf.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_leaf = register("block_tree_ichoh_leaf", () -> new Not_Fuel(Wood_Blocks.ICHOH_leaf.get(), new Item.Properties()));
	public static final RegistryObject<Item> OAKKARE_leaf = register("block_tree_oakkare_leaf", () -> new Not_Fuel(Wood_Blocks.OAKKARE_leaf.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAKURA_log = register("block_tree_sakura_log", () -> new Not_Fuel(Wood_Blocks.SAKURA_log.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_log = register("block_tree_kaede_log", () -> new Not_Fuel(Wood_Blocks.KAEDE_log.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_log = register("block_tree_ichoh_log", () -> new Not_Fuel(Wood_Blocks.ICHOH_log.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> SAKURA_nae = register("block_tree_sakura_nae", () -> new Not_Fuel(Wood_Blocks.SAKURA_nae.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_nae = register("block_tree_kaede_nae", () -> new Not_Fuel(Wood_Blocks.KAEDE_nae.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_nae = register("block_tree_ichoh_nae", () -> new Not_Fuel(Wood_Blocks.ICHOH_nae.get(), new Item.Properties()));
	public static final RegistryObject<Item> OAKKARE_nae = register("block_tree_oakkare_nae", () -> new Not_Fuel(Wood_Blocks.OAKKARE_nae.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> TAKENOKO = register("block_takenoko", () -> new Not_Fuel(Wood_Blocks.TAKENOKO.get(), new Item.Properties()));
	public static final RegistryObject<Item> TAKENOKO_ROAST = register("item_food_takenoko", () -> new Item(new Item.Properties().food(FoodBuilders.TAKENOKO_ROAST)));
	public static final RegistryObject<Item> KURI_IGA = register("block_chestnuts", () -> new Not_Fuel(Wood_Blocks.KURIIGA_FALL.get(), new Item.Properties().craftRemainder(Items_NoTab.IGA.get())));
	public static final RegistryObject<Item> KURI = register("item_chestnut", () -> new AddInfo_Item(new Item.Properties()));
	public static final RegistryObject<Item> KURI_ROAST = register("item_food_chestnut", () -> new Item(new Item.Properties().food(FoodBuilders.KURI_ROAST)));
	public static final RegistryObject<Item> KURI_BOIL = register("item_chestnut_boil", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> KURI_MASH = register("item_chestnut_mash", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> KURI_NABE = register("block_food_nabekuri_n", () -> new Not_Fuel(Dish_Blocks.KURI_NABE_nama.get(), new Item.Properties()));
	public static final RegistryObject<Item> KURI_SWEET = register("item_food_chestnutsweet", () -> new AddInfo_Item(new Item.Properties().food(FoodBuilders.KURI_SWEET)));
	public static final RegistryObject<Item> KURI_CHOCO = register("item_food_chestnutchoco", () -> new AddInfo_Item(new Item.Properties().food(FoodBuilders.KURI_CHOCO)));

	public static final RegistryObject<Item> SAKURA_planks = register("block_planks_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_planks.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_planks = register("block_planks_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_planks.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_planks = register("block_planks_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_planks.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAKURA_stairs = register("block_stairs_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_stairs.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_stairs = register("block_stairs_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_stairs.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_stairs = register("block_stairs_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_stairs.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAKURA_slabhalf = register("block_slabhalf_sakura", () -> new Seasonal_Slab150(Wood_Blocks.SAKURA_slabhalf.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_slabhalf = register("block_slabhalf_kaede", () -> new Seasonal_Slab150(Wood_Blocks.KAEDE_slabhalf.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_slabhalf = register("block_slabhalf_ichoh", () -> new Seasonal_Slab150(Wood_Blocks.ICHOH_slabhalf.get(), new Item.Properties()));

	public static final RegistryObject<Item> PILLAR_saku = register("block_pillar_sakura", () -> new Not_Fuel(Wood_Blocks.PILLAR_saku.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_kae = register("block_pillar_kaede", () -> new Not_Fuel(Wood_Blocks.PILLAR_kae.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_ich = register("block_pillar_ichoh", () -> new Not_Fuel(Wood_Blocks.PILLAR_ich.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_saku = register("block_kamoi_sakura", () -> new Fuel_150(Wood_Blocks.PILLARSLAB_saku.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_kae = register("block_kamoi_kaede", () -> new Fuel_150(Wood_Blocks.PILLARSLAB_kae.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_ich = register("block_kamoi_ichoh", () -> new Fuel_150(Wood_Blocks.PILLARSLAB_ich.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> SAKURA_FENCE = register("block_fence_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_FENCE = register("block_fence_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_FENCE = register("block_fence_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SAKURA_FGATE = register("block_fencegate_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_FGATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_FGATE = register("block_fencegate_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_FGATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_FGATE = register("block_fencegate_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_FGATE.get(), new Item.Properties()));

	public static final RegistryObject<Item> DOOR_SAKURA = register("block_door_sakura", () -> new Not_Fuel(Wood_Blocks.DOOR_SAKURA.get(), new Item.Properties()));
	public static final RegistryObject<Item> DOOR_KAEDE = register("block_door_kaede", () -> new Not_Fuel(Wood_Blocks.DOOR_KAEDE.get(), new Item.Properties()));
	public static final RegistryObject<Item> DOOR_ICHOH = register("block_door_ichoh", () -> new Not_Fuel(Wood_Blocks.DOOR_ICHOH.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAKURA_TRAPDOOR = register("block_trapdoor_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_TRAPDOOR = register("block_trapdoor_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_TRAPDOOR = register("block_trapdoor_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<Item> SAKURA_PLATE = register("block_plate_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_PLATE = register("block_plate_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_PLATE = register("block_plate_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SAKURA_BUTTON = register("block_button_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_BUTTON = register("block_button_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_BUTTON = register("block_button_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_BUTTON.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAKURA_carpet = register("block_carpet_sakura", () -> new Not_Fuel(Wood_Blocks.SAKURA_carpet.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAEDE_carpet = register("block_carpet_kaede", () -> new Not_Fuel(Wood_Blocks.KAEDE_carpet.get(), new Item.Properties()));
	public static final RegistryObject<Item> ICHOH_carpet = register("block_carpet_ichoh", () -> new Not_Fuel(Wood_Blocks.ICHOH_carpet.get(), new Item.Properties()));
	public static final RegistryObject<Item> OCHIBA_carpet = register("block_carpet_ochiba", () -> new Not_Fuel(Wood_Blocks.OCHIBA_carpet.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_LOG_sakura = register("block_wp_log_sakura", () -> new Not_Fuel(Wood_Blocks.WP_LOG_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_kaede = register("block_wp_log_kaede", () -> new Not_Fuel(Wood_Blocks.WP_LOG_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_ichoh = register("block_wp_log_ichoh", () -> new Not_Fuel(Wood_Blocks.WP_LOG_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_sakura = register("block_wp_plank_sakura", () -> new Not_Fuel(Wood_Blocks.WP_PLANK_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_kaede = register("block_wp_plank_kaede", () -> new Not_Fuel(Wood_Blocks.WP_PLANK_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_ichoh = register("block_wp_plank_ichoh", () -> new Not_Fuel(Wood_Blocks.WP_PLANK_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> DRESSINGTABLE_sakura = register("block_dressingtable_saku", () -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> DRESSINGTABLE_kaede = register("block_dressingtable_kae", () -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> DRESSINGTABLE_ichoh = register("block_dressingtable_ich", () -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> UNITDESK_sakura = register("block_unitdesk_sakura", () -> new Fuel_300(Unit_Blocks.UNITDESK_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> UNITDESK_kaede = register("block_unitdesk_kaede", () -> new Fuel_300(Unit_Blocks.UNITDESK_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> UNITDESK_ichoh = register("block_unitdesk_ichoh", () -> new Fuel_300(Unit_Blocks.UNITDESK_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> CAFETABLE_sakura = register("block_cafetable_sakura", () -> new Fuel_300(Unit_Blocks.CAFETABLE_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> CAFETABLE_kaede = register("block_cafetable_kaede", () -> new Fuel_300(Unit_Blocks.CAFETABLE_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> CAFETABLE_ichoh = register("block_cafetable_ichoh", () -> new Fuel_300(Unit_Blocks.CAFETABLE_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> DININGCHAIR_sakura = register("block_diningchair_saku", () -> new Fuel_150(Chair_Blocks.DININGCHAIR_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> DININGCHAIR_kaede = register("block_diningchair_kae", () -> new Fuel_150(Chair_Blocks.DININGCHAIR_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> DININGCHAIR_ichoh = register("block_diningchair_ich", () -> new Fuel_150(Chair_Blocks.DININGCHAIR_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> LOGCHAIR_sakura = register("block_logchair_sakura", () -> new Fuel_150(Chair_Blocks.LOGCHAIR_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> LOGCHAIR_kaede = register("block_logchair_kaede", () -> new Fuel_150(Chair_Blocks.LOGCHAIR_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> LOGCHAIR_ichoh = register("block_logchair_ichoh", () -> new Fuel_150(Chair_Blocks.LOGCHAIR_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> BENCH_sakura = register("block_bench_saku", () -> new Fuel_150(Chair_Blocks.BENCH_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> BENCH_kaede = register("block_bench_kae", () -> new Fuel_150(Chair_Blocks.BENCH_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> BENCH_ichoh = register("block_bench_ich", () -> new Fuel_150(Chair_Blocks.BENCH_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> SCHOOLCHAIR_sakura = register("block_schoolchair_saku", () -> new Fuel_150(School_Blocks.SCHOOLCHAIR_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> SCHOOLCHAIR_kaede = register("block_schoolchair_kae", () -> new Fuel_150(School_Blocks.SCHOOLCHAIR_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> SCHOOLCHAIR_ichoh = register("block_schoolchair_ich", () -> new Fuel_150(School_Blocks.SCHOOLCHAIR_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> SCHOOLDESK_sakura = register("block_schooldesk_saku", () -> new Fuel_200(School_Blocks.SCHOOLDESK_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> SCHOOLDESK_kaede = register("block_schooldesk_kae", () -> new Fuel_200(School_Blocks.SCHOOLDESK_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> SCHOOLDESK_ichoh = register("block_schooldesk_ich", () -> new Fuel_200(School_Blocks.SCHOOLDESK_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> TEACHERDESK_sakura = register("block_teacherdesk_saku", () -> new Fuel_300(School_Blocks.TEACHERDESK_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> TEACHERDESK_kaede = register("block_teacherdesk_kae", () -> new Fuel_300(School_Blocks.TEACHERDESK_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> TEACHERDESK_ichoh = register("block_teacherdesk_ich", () -> new Fuel_300(School_Blocks.TEACHERDESK_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> LOWDESK_sakura = register("block_lowdesk_sakura", () -> new Fuel_150(Unit_Blocks.LOWDESK_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> LOWDESK_kaede = register("block_lowdesk_kaede", () -> new Fuel_150(Unit_Blocks.LOWDESK_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> LOWDESK_ichoh = register("block_lowdesk_ichoh", () -> new Fuel_150(Unit_Blocks.LOWDESK_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOARD_SAKURA = register("block_board_sakura", () -> new Not_Fuel(School_Blocks.BOARD_SAKURA.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOARD_KAEDE = register("block_board_kaede", () -> new Not_Fuel(School_Blocks.BOARD_KAEDE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BOARD_ICHOH = register("block_board_ichoh", () -> new Not_Fuel(School_Blocks.BOARD_ICHOH.get(), new Item.Properties()));

	public static final RegistryObject<Item> WINDOW_sakura = register("block_window_sakura", () -> new Not_Fuel(Window_Blocks.WINDOW_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOW_kaede = register("block_window_kaede", () -> new Not_Fuel(Window_Blocks.WINDOW_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOW_ichoh = register("block_window_ichoh", () -> new Not_Fuel(Window_Blocks.WINDOW_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWB_sakura = register("block_windowb_sakura", () -> new Not_Fuel(Window_Blocks.WINDOWB_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWB_kaede = register("block_windowb_kaede", () -> new Not_Fuel(Window_Blocks.WINDOWB_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWB_ichoh = register("block_windowb_ichoh", () -> new Not_Fuel(Window_Blocks.WINDOWB_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWTALLBOT_sakura = register("block_windowtallbot_sakura", () -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWTALLBOT_kaede = register("block_windowtallbot_kaede", () -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWTALLBOT_ichoh = register("block_windowtallbot_ichoh", () -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWTALL_sakura = register("block_windowtall_sakura", () -> new Not_Fuel(Window_Blocks.WINDOWTALL_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWTALL_kaede = register("block_windowtall_kaede", () -> new Not_Fuel(Window_Blocks.WINDOWTALL_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> WINDOWTALL_ichoh = register("block_windowtall_ichoh", () -> new Not_Fuel(Window_Blocks.WINDOWTALL_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> OFFICEDESK_SAKURA = register("block_officedesk_sakura", () -> new Fuel_300(Furniture_Blocks.OFFICEDESK_SAKURA.get(), new Item.Properties()));
	public static final RegistryObject<Item> OFFICEDESK_KAEDE = register("block_officedesk_kaede", () -> new Fuel_300(Furniture_Blocks.OFFICEDESK_KAEDE.get(), new Item.Properties()));
	public static final RegistryObject<Item> OFFICEDESK_ICHOH = register("block_officedesk_ichoh", () -> new Fuel_300(Furniture_Blocks.OFFICEDESK_ICHOH.get(), new Item.Properties()));
	public static final RegistryObject<Item> TANSU_SAKURA = register("block_tansu_sakura", () -> new Fuel_300(Furniture_Blocks.TANSU_SAKURA.get(), new Item.Properties()));
	public static final RegistryObject<Item> TANSU_KAEDE = register("block_tansu_kaede", () -> new Fuel_300(Furniture_Blocks.TANSU_KAEDE.get(), new Item.Properties()));
	public static final RegistryObject<Item> TANSU_ICHOH = register("block_tansu_ichoh", () -> new Fuel_300(Furniture_Blocks.TANSU_ICHOH.get(), new Item.Properties()));

	public static final RegistryObject<Item> GARASUDO_SAKU = register("block_garasudo_sakura", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDO_KAE = register("block_garasudo_kaede", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDO_ICH = register("block_garasudo_ichoh", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_ICH.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDOB_SAKU = register("block_garasudob_sakura", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDOB_KAE = register("block_garasudob_kaede", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDOB_ICH = register("block_garasudob_ichoh", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_ICH.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDOH_SAKU = register("block_garasudohalf_sakura", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDOH_KAE = register("block_garasudohalf_kaede", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GARASUDOH_ICH = register("block_garasudohalf_ichoh", () -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_ICH.get(), new Item.Properties()));

	public static final RegistryObject<Item> SHOUJI_SAKU = register("block_shouji_sakura", () -> new Fuel_200(Slidedoor_Blocks.SHOUJI_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJI_KAE = register("block_shouji_kaede", () -> new Fuel_200(Slidedoor_Blocks.SHOUJI_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJI_ICH = register("block_shouji_ichoh", () -> new Fuel_200(Slidedoor_Blocks.SHOUJI_ICH.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJIB_SAKU = register("block_shoujib_sakura", () -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJIB_KAE = register("block_shoujib_kaede", () -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJIB_ICH = register("block_shoujib_ichoh", () -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_ICH.get(), new Item.Properties()));

	public static final RegistryObject<Item> SHOUJIH_SAKU = register("block_shoujihalf_sakura", () -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJIH_KAE = register("block_shoujihalf_kaede", () -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJIH_ICH = register("block_shoujihalf_ichoh", () -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_ICH.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJI_WIN_SAKU = register("block_shoujih_sakura", () -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_SAKU.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJI_WIN_KAE = register("block_shoujih_kaede", () -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_KAE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHOUJI_WIN_ICH = register("block_shoujih_ichoh", () -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_ICH.get(), new Item.Properties()));

	public static final RegistryObject<Item> RANMA_sakura = register("block_ranma_saku", () -> new Fuel_150(Ranma_Blocks.RANMA_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMA_kaede = register("block_ranma_kae", () -> new Fuel_150(Ranma_Blocks.RANMA_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMA_ichoh = register("block_ranma_ich", () -> new Fuel_150(Ranma_Blocks.RANMA_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMAB_sakura = register("block_ranmab_saku", () -> new Fuel_150(Ranma_Blocks.RANMAB_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMAB_kaede = register("block_ranmab_kae", () -> new Fuel_150(Ranma_Blocks.RANMAB_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMAB_ichoh = register("block_ranmab_ich", () -> new Fuel_150(Ranma_Blocks.RANMAB_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMAC_sakura = register("block_ranmac_saku", () -> new Not_Fuel(Ranma_Blocks.RANMAC_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMAC_kaede = register("block_ranmac_kae", () -> new Not_Fuel(Ranma_Blocks.RANMAC_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> RANMAC_ichoh = register("block_ranmac_ich", () -> new Not_Fuel(Ranma_Blocks.RANMAC_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> KANKI_sakura = register("block_kanki_saku", () -> new Fuel_150(Ranma_Blocks.KANKI_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANKI_kaede = register("block_kanki_kae", () -> new Fuel_150(Ranma_Blocks.KANKI_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANKI_ichoh = register("block_kanki_ich", () -> new Fuel_150(Ranma_Blocks.KANKI_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> KOUSHI_sakura = register("block_koushi_saku", () -> new Fuel_150(Ranma_Blocks.KOUSHI_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUSHI_kaede = register("block_koushi_kae", () -> new Fuel_150(Ranma_Blocks.KOUSHI_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUSHI_ichoh = register("block_koushi_ich", () -> new Fuel_150(Ranma_Blocks.KOUSHI_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUSHIB_sakura = register("block_koushib_saku", () -> new Fuel_150(Ranma_Blocks.KOUSHIB_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUSHIB_kaede = register("block_koushib_kae", () -> new Fuel_150(Ranma_Blocks.KOUSHIB_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOUSHIB_ichoh = register("block_koushib_ich", () -> new Fuel_150(Ranma_Blocks.KOUSHIB_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> BONSAI_sakura = register("block_bonsai_sakura", () -> new Not_Fuel(Garden_Blocks.BONSAI_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> BONSAI_kaede = register("block_bonsai_kaede", () -> new Not_Fuel(Garden_Blocks.BONSAI_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> BONSAI_ichoh = register("block_bonsai_ichoh", () -> new Not_Fuel(Garden_Blocks.BONSAI_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> BONSAI_kare = register("block_bonsai_oakkare", () -> new Not_Fuel(Garden_Blocks.BONSAI_kare.get(), new Item.Properties()));

	public static final RegistryObject<Item> KANYOU_sakura = register("block_kanyousakura_bot", () -> new Not_Fuel(Garden_Blocks.KANYOU_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANYOU_kaede = register("block_kanyoukaede_bot", () -> new Not_Fuel(Garden_Blocks.KANYOU_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANYOU_ichoh = register("block_kanyouichoh_bot", () -> new Not_Fuel(Garden_Blocks.KANYOU_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> KANYOU_kare = register("block_kanyouoakkare_bot", () -> new Not_Fuel(Garden_Blocks.KANYOU_kare.get(), new Item.Properties()));

	public static final RegistryObject<Item> IKEGAKI_sakura = register("block_low_sakura", () -> new Fuel_150(Garden_Blocks.IKEGAKI_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> IKEGAKI_kaede = register("block_low_kaede", () -> new Fuel_150(Garden_Blocks.IKEGAKI_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> IKEGAKI_ichoh = register("block_low_ichoh", () -> new Fuel_150(Garden_Blocks.IKEGAKI_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> IKEGAKI_kare = register("block_low_oakkare", () -> new Fuel_150(Garden_Blocks.IKEGAKI_kare.get(), new Item.Properties()));

	public static final RegistryObject<Item> IKEGAKILONG_sakura = register("block_longsakura_bot", () -> new Fuel_300(Garden_Blocks.IKEGAKILONG_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> IKEGAKILONG_kaede = register("block_longkaede_bot", () -> new Fuel_300(Garden_Blocks.IKEGAKILONG_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> IKEGAKILONG_ichoh = register("block_longichoh_bot", () -> new Fuel_300(Garden_Blocks.IKEGAKILONG_ichoh.get(), new Item.Properties()));
	public static final RegistryObject<Item> IKEGAKILONG_kare = register("block_longoakkare_bot", () -> new Fuel_300(Garden_Blocks.IKEGAKILONG_kare.get(), new Item.Properties()));

	public static final RegistryObject<Item> ITABEI_sakura = register("block_itabei_sakura", () -> new Fuel_200(Garden_Blocks.ITABEI_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> ITABEI_kaede = register("block_itabei_kaede", () -> new Fuel_200(Garden_Blocks.ITABEI_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> ITABEI_ichoh = register("block_itabei_ichoh", () -> new Fuel_200(Garden_Blocks.ITABEI_ichoh.get(), new Item.Properties()));
	
	public static final RegistryObject<Item> KIDO_sakura = register("block_kido_sakura", () -> new Fuel_200(Garden_Blocks.KIDO_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIDO_kaede = register("block_kido_kaede", () -> new Fuel_200(Garden_Blocks.KIDO_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> KIDO_ichoh = register("block_kido_ichoh", () -> new Fuel_200(Garden_Blocks.KIDO_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> CHABUDAI = register("block_chabudai", () -> new Fuel_150(Unit_Blocks.CHABUDAI.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_spruce = register("block_chabudai_spruce", () -> new Fuel_150(Unit_Blocks.CHABUDAI_spruce.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_birch = register("block_chabudai_birch", () -> new Fuel_150(Unit_Blocks.CHABUDAI_birch.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_jungle = register("block_chabudai_jungle", () -> new Fuel_150(Unit_Blocks.CHABUDAI_jungle.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_acacia = register("block_chabudai_acacia", () -> new Fuel_150(Unit_Blocks.CHABUDAI_acacia.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_darkoak = register("block_chabudai_darkoak", () -> new Fuel_150(Unit_Blocks.CHABUDAI_darkoak.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_mangrove = register("block_chabudai_mangrove", () -> new Fuel_150(Unit_Blocks.CHABUDAI_mangrove.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_cherry = register("block_chabudai_cherry", () -> new Fuel_150(Unit_Blocks.CHABUDAI_cherry.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_sakura = register("block_chabudai_sakura", () -> new Fuel_150(Unit_Blocks.CHABUDAI_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_kaede = register("block_chabudai_kaede", () -> new Fuel_150(Unit_Blocks.CHABUDAI_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHABUDAI_ichoh = register("block_chabudai_ichoh", () -> new Fuel_150(Unit_Blocks.CHABUDAI_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> KOTATSU = register("block_kotatsu", () -> new Fuel_200(Unit_Blocks.KOTATSU.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_spruce = register("block_kotatsu_spruce", () -> new Fuel_200(Unit_Blocks.KOTATSU_spruce.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_birch = register("block_kotatsu_birch", () -> new Fuel_200(Unit_Blocks.KOTATSU_birch.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_jungle = register("block_kotatsu_jungle", () -> new Fuel_200(Unit_Blocks.KOTATSU_jungle.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_acacia = register("block_kotatsu_acacia", () -> new Fuel_200(Unit_Blocks.KOTATSU_acacia.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_darkoak = register("block_kotatsu_darkoak", () -> new Fuel_200(Unit_Blocks.KOTATSU_darkoak.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_mangrove = register("block_kotatsu_mangrove", () -> new Fuel_200(Unit_Blocks.KOTATSU_mangrove.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_cherry = register("block_kotatsu_cherry", () -> new Fuel_200(Unit_Blocks.KOTATSU_cherry.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_sakura = register("block_kotatsu_sakura", () -> new Fuel_200(Unit_Blocks.KOTATSU_sakura.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_kaede = register("block_kotatsu_kaede", () -> new Fuel_200(Unit_Blocks.KOTATSU_kaede.get(), new Item.Properties()));
	public static final RegistryObject<Item> KOTATSU_ichoh = register("block_kotatsu_ichoh", () -> new Fuel_200(Unit_Blocks.KOTATSU_ichoh.get(), new Item.Properties()));

	public static final RegistryObject<Item> KUSATABA = register("block_tabakusa", () -> new Fuel_200(Seasonal_Blocks.KUSATABA.get(), new Item.Properties()));
	public static final RegistryObject<Item> WARATABA = register("block_tabawara", () -> new Fuel_200(Seasonal_Blocks.WARATABA.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAYATABA = register("block_tabakaya", () -> new Fuel_200(Seasonal_Blocks.KAYATABA.get(), new Item.Properties()));

	public static final RegistryObject<Item> KUSATABA_RF = register("block_tabakusa_roof", () -> new Fuel_100(Seasonal_Blocks.KUSATABA_RF.get(), new Item.Properties()));
	public static final RegistryObject<Item> WARATABA_RF = register("block_tabawara_roof", () -> new Fuel_100(Seasonal_Blocks.WARATABA_RF.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAYATABA_RF = register("block_tabakaya_roof", () -> new Fuel_100(Seasonal_Blocks.KAYATABA_RF.get(), new Item.Properties()));

	public static final RegistryObject<Item> KUSATABA_STAIRS = register("block_tabakusa_stairs", () -> new Fuel_200(Seasonal_Blocks.KUSATABA_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> WARATABA_STAIRS = register("block_tabawara_stairs", () -> new Fuel_200(Seasonal_Blocks.WARATABA_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAYATABA_STAIRS = register("block_tabakaya_stairs", () -> new Fuel_200(Seasonal_Blocks.KAYATABA_STAIRS.get(), new Item.Properties()));

	public static final RegistryObject<Item> KADOMATSU = register("block_kadomatsu", () -> new Not_Fuel(Seasonal_Blocks.KADOMATSU.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHIMENAWA = register("block_shimenawa", () -> new Fuel_100(Seasonal_Blocks.SHIMENAWA.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAGAMIMOCHI = register("block_kagamimochi", () -> new Fuel_200(Seasonal_Blocks.KAGAMIMOCHI.get(), new Item.Properties()));

	public static final RegistryObject<Item> HINAKAZARI = register("block_hinakazari", () -> new Not_Fuel(Seasonal_Blocks.HINAKAZARI.get(), new Item.Properties()));
	public static final RegistryObject<Item> HINADAN = register("block_hinadan", () -> new Not_Fuel(Seasonal_Blocks.HINADAN.get(), new Item.Properties()));

	public static final RegistryObject<Item> XMASTREE = register("block_xmastree", () -> new Fuel_100(Seasonal_Blocks.XMASTREE.get(), new Item.Properties()));
	public static final RegistryObject<Item> XMASTREE_W = register("block_xmastree_w", () -> new Fuel_100(Seasonal_Blocks.XMASTREE_W.get(), new Item.Properties()));

	public static final RegistryObject<Item> PRESENT_app = register("block_present_app", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_app.get(), new Item.Properties()));
	public static final RegistryObject<Item> PRESENT_bok = register("block_present_bok", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_bok.get(), new Item.Properties()));
	public static final RegistryObject<Item> PRESENT_dia = register("block_present_dia", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_dia.get(), new Item.Properties()));
	public static final RegistryObject<Item> PRESENT_lap = register("block_present_lap", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_lap.get(), new Item.Properties()));
	public static final RegistryObject<Item> PRESENT_bla = register("block_present_bla", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_bla.get(), new Item.Properties()));
	public static final RegistryObject<Item> PRESENT_chc = register("block_present_chc", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_chc.get(), new Item.Properties()));
	public static final RegistryObject<Item> PRESENT_chh = register("block_present_chh", () -> new Not_Fuel(Seasonal_Blocks.PRESENT_chh.get(), new Item.Properties()));

	public static final RegistryObject<Item> SNOWCORE = register("block_snowcore", () -> new AddInfo_ItemBlock(Seasonal_Blocks.SNOWCORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SNOWMAN = register("block_snowman", () -> new AddInfo_ItemBlock(Seasonal_Blocks.SNOWMAN.get(), new Item.Properties()));

	public static final RegistryObject<Item> COCOA_F = register("item_cocoa_ferm", () -> new AddInfo_Item(new Item.Properties()));
	public static final RegistryObject<Item> COCOA_R = register("item_cocoa_roast", () -> new AddInfo_Item(new Item.Properties()));
	public static final RegistryObject<Item> COCOA_M = register("item_cocoa_mass", () -> new AddInfo_Item(new Item.Properties()));
	public static final RegistryObject<Item> CHOCO_raw = register("item_choco_raw", () -> new AddInfo_Item(new Item.Properties().craftRemainder(Items.BOWL)));
	public static final RegistryObject<Item> COCOA_TARU = register("block_taru_cocoa_f", () -> new Not_Fuel(Hakkou_Blocks.COCOA_TARU.get(), new Item.Properties()));

	public static final RegistryObject<Item> FOOD_CHOCO = register("item_food_choco", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO)));
	public static final RegistryObject<Item> FOOD_CHOCO_apple = register("item_food_choco_apple", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO_apple)));
	public static final RegistryObject<Item> FOOD_CHOCO_cherry = register("item_food_choco_cherry", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO_cherry)));
	public static final RegistryObject<Item> FOOD_CHOCO_citrus = register("item_food_choco_citrus", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO_citrus)));
	public static final RegistryObject<Item> FOOD_CHOCO_grape = register("item_food_choco_grape", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO_grape)));
	public static final RegistryObject<Item> FOOD_CHOCO_tea = register("item_food_choco_greentea", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO_tea)));
	public static final RegistryObject<Item> FOOD_CHOCO_heart = register("item_food_choco_heart", () -> new Item(new Item.Properties().food(FoodBuilders.CHOCO_heart)));

	public static final RegistryObject<Item> UCHIWA_white = register("block_uchiwa_white", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_white.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_orange = register("block_uchiwa_orange", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_orange.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_magenta = register("block_uchiwa_magenta", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_magenta.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_lightb = register("block_uchiwa_lightb", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_lightb.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_yellow = register("block_uchiwa_yellow", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_yellow.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_lime = register("block_uchiwa_lime", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_lime.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_pink = register("block_uchiwa_pink", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_pink.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_gray = register("block_uchiwa_gray", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_gray.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_lightg = register("block_uchiwa_lightg", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_lightg.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_cyan = register("block_uchiwa_cyan", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_cyan.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_purple = register("block_uchiwa_purple", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_purple.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_blue = register("block_uchiwa_blue", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_blue.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_brown = register("block_uchiwa_brown", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_brown.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_green = register("block_uchiwa_green", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_red = register("block_uchiwa_red", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> UCHIWA_black = register("block_uchiwa_black", () -> new Not_Fuel(Seasonal_Blocks.UCHIWA_black.get(), new Item.Properties()));

	public static final RegistryObject<Item> FOOD_WATAGASHI = register("item_food_watagashi", () -> new FoodAnytime_addItem(new Item.Properties()));
	public static final RegistryObject<Item> FOOD_WATAGASHI_apple = register("item_food_watagashi_y", () -> new FoodAnytime_addItem(new Item.Properties()));
	public static final RegistryObject<Item> FOOD_WATAGASHI_cherry = register("item_food_watagashi_p", () -> new FoodAnytime_addItem(new Item.Properties()));
	public static final RegistryObject<Item> FOOD_WATAGASHI_citrus = register("item_food_watagashi_o", () -> new FoodAnytime_addItem(new Item.Properties()));
	public static final RegistryObject<Item> FOOD_WATAGASHI_grape = register("item_food_watagashi_r", () -> new FoodAnytime_addItem(new Item.Properties()));
	public static final RegistryObject<Item> FOOD_WATAGASHI_tea = register("item_food_watagashi_g", () -> new FoodAnytime_addItem(new Item.Properties()));

	public static final RegistryObject<Item> WATAGASHI_block = register("block_watagashi", () -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_block.get(), new Item.Properties()));
	public static final RegistryObject<Item> WATAGASHI_apple = register("block_watagashi_yellow", () -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_apple.get(), new Item.Properties()));
	public static final RegistryObject<Item> WATAGASHI_cherry = register("block_watagashi_pink", () -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_cherry.get(), new Item.Properties()));
	public static final RegistryObject<Item> WATAGASHI_citrus = register("block_watagashi_orange", () -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_citrus.get(), new Item.Properties()));
	public static final RegistryObject<Item> WATAGASHI_grape = register("block_watagashi_red", () -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_grape.get(), new Item.Properties()));
	public static final RegistryObject<Item> WATAGASHI_tea = register("block_watagashi_green", () -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_tea.get(), new Item.Properties()));

	public static final RegistryObject<Item> KAKIGOURI_hata = register("block_kakigouri_hata", () -> new Not_Fuel(Seasonal_Blocks.KAKIGOURI_hata.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAKIGOURI_block = register("block_kakigouri_block1", () -> new Dish_Kakigouri(Seasonal_Blocks.KAKIGOURI_block.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAKIGOURI_apple = register("block_kakigouri_yellow1", () -> new Dish_Kakigouri(Seasonal_Blocks.KAKIGOURI_apple.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAKIGOURI_cherry = register("block_kakigouri_pink1", () -> new Dish_Kakigouri(Seasonal_Blocks.KAKIGOURI_cherry.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAKIGOURI_citrus = register("block_kakigouri_orange1", () -> new Dish_Kakigouri(Seasonal_Blocks.KAKIGOURI_citrus.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAKIGOURI_grape = register("block_kakigouri_red1", () -> new Dish_Kakigouri(Seasonal_Blocks.KAKIGOURI_grape.get(), new Item.Properties()));
	public static final RegistryObject<Item> KAKIGOURI_tea = register("block_kakigouri_green1", () -> new Dish_Kakigouri(Seasonal_Blocks.KAKIGOURI_tea.get(), new Item.Properties()));

	/* YUKATA */
	public static final RegistryObject<Item> YKTD_GETA = register("item_ykt_getadoak", () -> new Costume_YUKATA(CMArmorMaterial.IKADUCHIYKT, ArmorItem.Type.BOOTS, boots(6)));
	public static final RegistryObject<Item> YKTO_GETA = register("item_ykt_getaoak", () -> new Costume_YUKATA(CMArmorMaterial.TTOKUYKT, ArmorItem.Type.BOOTS, boots(6)));

	public static final RegistryObject<Item> IKADUCHIYKT_HELMET = register("item_ykt_ikaduchi_kazari", () -> new Costume_YUKATA(CMArmorMaterial.IKADUCHIYKT, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> IKADUCHIYKT_CHESTPLATE = register("item_ykt_ikaduchi_mini", () -> new Costume_YUKATA(CMArmorMaterial.IKADUCHIYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> IKADUCHIYKT_LEGGINGS = register("item_ykt_ikaduchi_long", () -> new Costume_YUKATA(CMArmorMaterial.IKADUCHIYKT, ArmorItem.Type.LEGGINGS, leggings(6)));

	public static final RegistryObject<Item> INADUMAYKT_HELMET = register("item_ykt_inaduma_kazari", () -> new Costume_YUKATA(CMArmorMaterial.INADUMAYKT, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> INADUMAYKT_CHESTPLATE = register("item_ykt_inaduma_mini", () -> new Costume_YUKATA(CMArmorMaterial.INADUMAYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> INADUMAYKT_LEGGINGS = register("item_ykt_inaduma_long", () -> new Costume_YUKATA(CMArmorMaterial.INADUMAYKT, ArmorItem.Type.LEGGINGS, leggings(6)));

	public static final RegistryObject<Item> HAMAKAZEYKT_HELMET = register("item_ykt_hamakaze_kazari", () -> new Costume_YUKATA(CMArmorMaterial.HAMAKAZEYKT, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> HAMAKAZEYKT_CHESTPLATE = register("item_ykt_hamakaze_mini", () -> new Costume_YUKATA(CMArmorMaterial.HAMAKAZEYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> HAMAKAZEYKT_LEGGINGS = register("item_ykt_hamakaze_long", () -> new Costume_YUKATA(CMArmorMaterial.HAMAKAZEYKT, ArmorItem.Type.LEGGINGS, leggings(6)));

	public static final RegistryObject<Item> URAKAZEYKT_HELMET = register("item_ykt_urakaze_kazari", () -> new Costume_YUKATA(CMArmorMaterial.URAKAZEYKT, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> URAKAZEYKT_CHESTPLATE = register("item_ykt_urakaze_mini", () -> new Costume_YUKATA(CMArmorMaterial.URAKAZEYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> URAKAZEYKT_LEGGINGS = register("item_ykt_urakaze_long", () -> new Costume_YUKATA(CMArmorMaterial.URAKAZEYKT, ArmorItem.Type.LEGGINGS, leggings(6)));

	public static final RegistryObject<Item> KAWAKAZEYKT_HELMET = register("item_ykt_kawakaze_kazari", () -> new Costume_YUKATA(CMArmorMaterial.KAWAKAZEYKT, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> KAWAKAZEYKT_CHESTPLATE = register("item_ykt_kawakaze_mini", () -> new Costume_YUKATA(CMArmorMaterial.KAWAKAZEYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> KAWAKAZEYKT_LEGGINGS = register("item_ykt_kawakaze_long", () -> new Costume_YUKATA(CMArmorMaterial.KAWAKAZEYKT, ArmorItem.Type.LEGGINGS, leggings(6)));

	public static final RegistryObject<Item> OBOROYKT_HELMET = register("item_ykt_oboro_kazari", () -> new Costume_YUKATA(CMArmorMaterial.OBOROYKT, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> OBOROYKT_CHESTPLATE = register("item_ykt_oboro_mini", () -> new Costume_YUKATA(CMArmorMaterial.OBOROYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> OBOROYKT_LEGGINGS = register("item_ykt_oboro_long", () -> new Costume_YUKATA(CMArmorMaterial.OBOROYKT, ArmorItem.Type.LEGGINGS, leggings(6)));

	public static final RegistryObject<Item> TTOKUYKT_CHESTPLATE = register("item_ykt_ttoku_mini", () -> new Costume_YUKATA(CMArmorMaterial.TTOKUYKT, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> TTOKUYKT_LEGGINGS = register("item_ykt_ttoku_long", () -> new Costume_YUKATA(CMArmorMaterial.TTOKUYKT, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> TTOKUYKTB_CHESTPLATE = register("item_ykt_ttokub_mini", () -> new Costume_YUKATA(CMArmorMaterial.TTOKUYKTB, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> TTOKUYKTB_LEGGINGS = register("item_ykt_ttokub_long", () -> new Costume_YUKATA(CMArmorMaterial.TTOKUYKTB, ArmorItem.Type.LEGGINGS, leggings(6)));

	/* SantaCos */
	public static final RegistryObject<Item> AKASHISANTA_HELMET = register("item_santaakashi_helmet", () -> new Costume_Santa(CMArmorMaterial.AKASHISANTA, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> AKASHISANTA_CHESTPLATE = register("item_santaakashi_chestplate", () -> new Costume_Santa(CMArmorMaterial.AKASHISANTA, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> AKASHISANTA_LEGGINGS = register("item_santaakashi_leggings", () -> new Costume_Santa(CMArmorMaterial.AKASHISANTA, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> AKASHISANTA_BOOTS = register("item_santaakashi_boots", () -> new Costume_Santa(CMArmorMaterial.AKASHISANTA, ArmorItem.Type.BOOTS, boots(6)));

	public static final RegistryObject<Item> SUZUYASANTA_HELMET = register("item_santasuzuya_helmet", () -> new Costume_Santa(CMArmorMaterial.SUZUYASANTA, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> SUZUYASANTA_CHESTPLATE = register("item_santasuzuya_chestplate", () -> new Costume_Santa(CMArmorMaterial.SUZUYASANTA, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> SUZUYASANTA_LEGGINGS = register("item_santasuzuya_leggings", () -> new Costume_Santa(CMArmorMaterial.SUZUYASANTA, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> SUZUYASANTA_BOOTS = register("item_santasuzuya_boots", () -> new Costume_Santa(CMArmorMaterial.SUZUYASANTA, ArmorItem.Type.BOOTS, boots(6)));

	public static final RegistryObject<Item> KUMANOSANTA_HELMET = register("item_santakumano_helmet", () -> new Costume_Santa(CMArmorMaterial.KUMANOSANTA, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> KUMANOSANTA_CHESTPLATE = register("item_santakumano_chestplate", () -> new Costume_Santa(CMArmorMaterial.KUMANOSANTA, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> KUMANOSANTA_LEGGINGS = register("item_santakumano_leggings", () -> new Costume_Santa(CMArmorMaterial.KUMANOSANTA, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> KUMANOSANTA_BOOTS = register("item_santakumano_boots", () -> new Costume_Santa(CMArmorMaterial.KUMANOSANTA, ArmorItem.Type.BOOTS, boots(6)));

	public static final RegistryObject<Item> RYUJOUSANTA_HELMET = register("item_santaryujou_helmet", () -> new Costume_Santa(CMArmorMaterial.RYUJOUSANTA, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> RYUJOUSANTA_CHESTPLATE = register("item_santaryujou_chestplate", () -> new Costume_Santa(CMArmorMaterial.RYUJOUSANTA, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> RYUJOUSANTA_LEGGINGS = register("item_santaryujou_leggings", () -> new Costume_Santa(CMArmorMaterial.RYUJOUSANTA, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> RYUJOUSANTA_BOOTS = register("item_santaryujou_boots", () -> new Costume_Santa(CMArmorMaterial.RYUJOUSANTA, ArmorItem.Type.BOOTS, boots(6)));

	public static final RegistryObject<Item> TEITOKUSANTA_HELMET = register("item_santattk_helmet", () -> new Costume_Santa(CMArmorMaterial.TEITOKUSANTA, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> TEITOKUSANTA_CHESTPLATE = register("item_santattk_chestplate", () -> new Costume_Santa(CMArmorMaterial.TEITOKUSANTA, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> TEITOKUSANTA_LEGGINGS = register("item_santattk_leggings", () -> new Costume_Santa(CMArmorMaterial.TEITOKUSANTA, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> TEITOKUSANTA_BOOTS = register("item_santattk_boots", () -> new Costume_Santa(CMArmorMaterial.TEITOKUSANTA, ArmorItem.Type.BOOTS, boots(6)));
	
	
	/* Share variables */
	private static Item.Properties helmet(int i) {
		return new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(i));
	}
	
	private static Item.Properties chestPlate(int i) {
		return new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(i));
	}
	
	private static Item.Properties leggings(int i) {
		return new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(i));
	}
	
	private static Item.Properties boots(int i) {
		return new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(i));
	}
	
	///* Register *///
	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}
}
