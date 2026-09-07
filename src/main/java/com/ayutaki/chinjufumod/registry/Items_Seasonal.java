package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_ItemBlock;
import com.ayutaki.chinjufumod.items.armor.Costume_Santa;
import com.ayutaki.chinjufumod.items.armor.Costume_YUKATA;
import com.ayutaki.chinjufumod.items.armor.MaterialArmor_CM;
import com.ayutaki.chinjufumod.items.dish.Dish_DrinkGlass;
import com.ayutaki.chinjufumod.items.foods.FoodEffects;
import com.ayutaki.chinjufumod.items.foods.FoodPoints;
import com.ayutaki.chinjufumod.items.foods.Watagashi_Item;
import com.ayutaki.chinjufumod.items.fuel.Fuel_100;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Fuel_200;
import com.ayutaki.chinjufumod.items.fuel.Fuel_300;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.items.remain.KuriIga_Item;
import com.ayutaki.chinjufumod.items.remain.ZundouAku_Item;
import com.ayutaki.chinjufumod.items.teatime.Warahai_Item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_Seasonal {
	/* 318 = 261 + (3 * 17) + (3 * 2) */
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	public static final DeferredItem<Item> WARAHAI = register("item_warahai", Warahai_Item::new, new Item.Properties());
	public static final DeferredItem<Item> ORIITO = register("item_oriito", Item::new, new Item.Properties());
	public static final DeferredItem<Item> TANMONO = register("item_tanmono", Item::new, new Item.Properties());

	public static final DeferredItem<Item> ZUNDOU_AKU = register("block_zundou_aku", (props) -> new ZundouAku_Item(Dish_Blocks.ZUNDOU_AKU.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SUIDEN = register("block_suiden", (props) -> new Not_Fuel(Wood_Blocks.SUIDEN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FALL_LEAF = register("block_fall_leaf", (props) -> new Not_Fuel(Wood_Blocks.FALL_LEAF.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_flow = register("block_tree_sakura_flow", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_flow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_leaf = register("block_tree_kaede_leaf", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_leaf.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_leaf = register("block_tree_ichoh_leaf", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_leaf.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OAKKARE_leaf = register("block_tree_oakkare_leaf", (props) -> new Not_Fuel(Wood_Blocks.OAKKARE_leaf.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_log = register("block_tree_sakura_log", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_log.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_log = register("block_tree_kaede_log", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_log.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_log = register("block_tree_ichoh_log", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_log.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_nae = register("block_tree_sakura_nae", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_nae.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_nae = register("block_tree_kaede_nae", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_nae.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_nae = register("block_tree_ichoh_nae", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_nae.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OAKKARE_nae = register("block_tree_oakkare_nae", (props) -> new Not_Fuel(Wood_Blocks.OAKKARE_nae.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> TAKENOKO = register("block_takenoko", (props) -> new Not_Fuel(Wood_Blocks.TAKENOKO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKENOKO_ROAST = register("item_food_takenoko", Item::new, new Item.Properties().food(FoodPoints.TAKENOKO_ROAST, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> KURI_IGA = register("block_chestnuts", (props) -> new KuriIga_Item(Wood_Blocks.KURIIGA_FALL.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KURI = register("item_chestnut", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> KURI_ROAST = register("item_food_chestnut", Item::new, new Item.Properties().food(FoodPoints.KURI_ROAST, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> KURI_BOIL = register("item_chestnut_boil", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> KURI_MASH = register("item_chestnut_mash", Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> KURI_NABE = register("block_food_nabekuri_n", (props) -> new Not_Fuel(Dish_Blocks.KURI_NABE_nama.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KURI_SWEET = register("item_food_chestnutsweet", AddInfo_Item::new, new Item.Properties().food(FoodPoints.KURI_SWEET, FoodEffects.DEFAULT_FOOD));
	public static final DeferredItem<Item> KURI_CHOCO = register("item_food_chestnutchoco", AddInfo_Item::new, new Item.Properties().food(FoodPoints.KURI_CHOCO, FoodEffects.KURI_CHOCO));

	public static final DeferredItem<Item> SAKURA_planks = register("block_planks_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_planks.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_planks = register("block_planks_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_planks.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_planks = register("block_planks_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_planks.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_stairs = register("block_stairs_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_stairs.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_stairs = register("block_stairs_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_stairs.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_stairs = register("block_stairs_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_stairs.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_slabhalf = register("block_slabhalf_sakura", (props) -> new Seasonal_Slab150(Wood_Blocks.SAKURA_slabhalf.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_slabhalf = register("block_slabhalf_kaede", (props) -> new Seasonal_Slab150(Wood_Blocks.KAEDE_slabhalf.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_slabhalf = register("block_slabhalf_ichoh", (props) -> new Seasonal_Slab150(Wood_Blocks.ICHOH_slabhalf.get(), props), new Item.Properties());

	public static final DeferredItem<Item> PILLAR_saku = register("block_pillar_sakura", (props) -> new Not_Fuel(Wood_Blocks.PILLAR_saku.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_kae = register("block_pillar_kaede", (props) -> new Not_Fuel(Wood_Blocks.PILLAR_kae.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_ich = register("block_pillar_ichoh", (props) -> new Not_Fuel(Wood_Blocks.PILLAR_ich.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_saku = register("block_kamoi_sakura", (props) -> new Fuel_150(Wood_Blocks.PILLARSLAB_saku.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_kae = register("block_kamoi_kaede", (props) -> new Fuel_150(Wood_Blocks.PILLARSLAB_kae.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_ich = register("block_kamoi_ichoh", (props) -> new Fuel_150(Wood_Blocks.PILLARSLAB_ich.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_FENCE = register("block_fence_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_FENCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_FENCE = register("block_fence_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_FENCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_FENCE = register("block_fence_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_FENCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SAKURA_FGATE = register("block_fencegate_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_FGATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_FGATE = register("block_fencegate_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_FGATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_FGATE = register("block_fencegate_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_FGATE.get(), props), new Item.Properties());

	public static final DeferredItem<Item> DOOR_SAKURA = register("block_door_sakura", (props) -> new Not_Fuel(Wood_Blocks.DOOR_SAKURA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DOOR_KAEDE = register("block_door_kaede", (props) -> new Not_Fuel(Wood_Blocks.DOOR_KAEDE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DOOR_ICHOH = register("block_door_ichoh", (props) -> new Not_Fuel(Wood_Blocks.DOOR_ICHOH.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_TRAPDOOR = register("block_trapdoor_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_TRAPDOOR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_TRAPDOOR = register("block_trapdoor_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_TRAPDOOR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_TRAPDOOR = register("block_trapdoor_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_TRAPDOOR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SAKURA_PLATE = register("block_plate_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_PLATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_PLATE = register("block_plate_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_PLATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_PLATE = register("block_plate_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_PLATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SAKURA_BUTTON = register("block_button_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_BUTTON.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_BUTTON = register("block_button_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_BUTTON.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_BUTTON = register("block_button_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_BUTTON.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SAKURA_carpet = register("block_carpet_sakura", (props) -> new Not_Fuel(Wood_Blocks.SAKURA_carpet.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAEDE_carpet = register("block_carpet_kaede", (props) -> new Not_Fuel(Wood_Blocks.KAEDE_carpet.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ICHOH_carpet = register("block_carpet_ichoh", (props) -> new Not_Fuel(Wood_Blocks.ICHOH_carpet.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OCHIBA_carpet = register("block_carpet_ochiba", (props) -> new Not_Fuel(Wood_Blocks.OCHIBA_carpet.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_LOG_sakura = register("block_wp_log_sakura", (props) -> new Not_Fuel(Wood_Blocks.WP_LOG_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_kaede = register("block_wp_log_kaede", (props) -> new Not_Fuel(Wood_Blocks.WP_LOG_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_ichoh = register("block_wp_log_ichoh", (props) -> new Not_Fuel(Wood_Blocks.WP_LOG_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_sakura = register("block_wp_plank_sakura", (props) -> new Not_Fuel(Wood_Blocks.WP_PLANK_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_kaede = register("block_wp_plank_kaede", (props) -> new Not_Fuel(Wood_Blocks.WP_PLANK_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_ichoh = register("block_wp_plank_ichoh", (props) -> new Not_Fuel(Wood_Blocks.WP_PLANK_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> DRESSINGTABLE_sakura = register("block_dressingtable_saku", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_kaede = register("block_dressingtable_kae", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_ichoh = register("block_dressingtable_ich", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_sakura = register("block_unitdesk_sakura", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_kaede = register("block_unitdesk_kaede", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_ichoh = register("block_unitdesk_ichoh", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_sakura = register("block_cafetable_sakura", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_kaede = register("block_cafetable_kaede", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_ichoh = register("block_cafetable_ichoh", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> DININGCHAIR_sakura = register("block_diningchair_saku", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_kaede = register("block_diningchair_kae", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_ichoh = register("block_diningchair_ich", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_sakura = register("block_logchair_sakura", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_kaede = register("block_logchair_kaede", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_ichoh = register("block_logchair_ichoh", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_sakura = register("block_bench_saku", (props) -> new Fuel_150(Chair_Blocks.BENCH_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_kaede = register("block_bench_kae", (props) -> new Fuel_150(Chair_Blocks.BENCH_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_ichoh = register("block_bench_ich", (props) -> new Fuel_150(Chair_Blocks.BENCH_ichoh.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> SCHOOLCHAIR_sakura = register("block_schoolchair_saku", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_kaede = register("block_schoolchair_kae", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_ichoh = register("block_schoolchair_ich", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_sakura = register("block_schooldesk_saku", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_kaede = register("block_schooldesk_kae", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_ichoh = register("block_schooldesk_ich", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_sakura = register("block_teacherdesk_saku", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_kaede = register("block_teacherdesk_kae", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_ichoh = register("block_teacherdesk_ich", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_sakura = register("block_lowdesk_sakura", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_kaede = register("block_lowdesk_kaede", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_ichoh = register("block_lowdesk_ichoh", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_SAKURA = register("block_board_sakura", (props) -> new Not_Fuel(School_Blocks.BOARD_SAKURA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_KAEDE = register("block_board_kaede", (props) -> new Not_Fuel(School_Blocks.BOARD_KAEDE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_ICHOH = register("block_board_ichoh", (props) -> new Not_Fuel(School_Blocks.BOARD_ICHOH.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> WINDOW_sakura = register("block_window_sakura", (props) -> new Not_Fuel(Window_Blocks.WINDOW_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_kaede = register("block_window_kaede", (props) -> new Not_Fuel(Window_Blocks.WINDOW_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_ichoh = register("block_window_ichoh", (props) -> new Not_Fuel(Window_Blocks.WINDOW_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_sakura = register("block_windowb_sakura", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_kaede = register("block_windowb_kaede", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_ichoh = register("block_windowb_ichoh", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_sakura = register("block_windowtallbot_sakura", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_kaede = register("block_windowtallbot_kaede", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_ichoh = register("block_windowtallbot_ichoh", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_sakura = register("block_windowtall_sakura", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_kaede = register("block_windowtall_kaede", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_ichoh = register("block_windowtall_ichoh", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> OFFICEDESK_SAKURA = register("block_officedesk_sakura", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_SAKURA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_KAEDE = register("block_officedesk_kaede", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_KAEDE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_ICHOH = register("block_officedesk_ichoh", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_ICHOH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_SAKURA = register("block_tansu_sakura", (props) -> new Fuel_300(Furniture_Blocks.TANSU_SAKURA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_KAEDE = register("block_tansu_kaede", (props) -> new Fuel_300(Furniture_Blocks.TANSU_KAEDE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_ICHOH = register("block_tansu_ichoh", (props) -> new Fuel_300(Furniture_Blocks.TANSU_ICHOH.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> GARASUDO_SAKU = register("block_garasudo_sakura", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_KAE = register("block_garasudo_kaede", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_ICH = register("block_garasudo_ichoh", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_ICH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_SAKU = register("block_garasudob_sakura", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_KAE = register("block_garasudob_kaede", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_ICH = register("block_garasudob_ichoh", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_ICH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_SAKU = register("block_garasudohalf_sakura", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_KAE = register("block_garasudohalf_kaede", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_ICH = register("block_garasudohalf_ichoh", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_ICH.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUJI_SAKU = register("block_shouji_sakura", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_KAE = register("block_shouji_kaede", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_ICH = register("block_shouji_ichoh", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_ICH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_SAKU = register("block_shoujib_sakura", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_KAE = register("block_shoujib_kaede", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_ICH = register("block_shoujib_ichoh", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_ICH.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUJIH_SAKU = register("block_shoujihalf_sakura", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_KAE = register("block_shoujihalf_kaede", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_ICH = register("block_shoujihalf_ichoh", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_ICH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_SAKU = register("block_shoujih_sakura", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_SAKU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_KAE = register("block_shoujih_kaede", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_KAE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_ICH = register("block_shoujih_ichoh", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_ICH.get(), props), new Item.Properties());

	public static final DeferredItem<Item> RANMA_sakura = register("block_ranma_saku", (props) -> new Fuel_150(Ranma_Blocks.RANMA_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_kaede = register("block_ranma_kae", (props) -> new Fuel_150(Ranma_Blocks.RANMA_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_ichoh = register("block_ranma_ich", (props) -> new Fuel_150(Ranma_Blocks.RANMA_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_sakura = register("block_ranmab_saku", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_kaede = register("block_ranmab_kae", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_ichoh = register("block_ranmab_ich", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_sakura = register("block_ranmac_saku", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_kaede = register("block_ranmac_kae", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_ichoh = register("block_ranmac_ich", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KANKI_sakura = register("block_kanki_saku", (props) -> new Fuel_150(Ranma_Blocks.KANKI_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_kaede = register("block_kanki_kae", (props) -> new Fuel_150(Ranma_Blocks.KANKI_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_ichoh = register("block_kanki_ich", (props) -> new Fuel_150(Ranma_Blocks.KANKI_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KOUSHI_sakura = register("block_koushi_saku", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_kaede = register("block_koushi_kae", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_ichoh = register("block_koushi_ich", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_sakura = register("block_koushib_saku", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_kaede = register("block_koushib_kae", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_ichoh = register("block_koushib_ich", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BONSAI_sakura = register("block_bonsai_sakura", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_kaede = register("block_bonsai_kaede", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_ichoh = register("block_bonsai_ichoh", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_kare = register("block_bonsai_oakkare", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_kare.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KANYOU_sakura = register("block_kanyousakura_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_kaede = register("block_kanyoukaede_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_ichoh = register("block_kanyouichoh_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_kare = register("block_kanyouoakkare_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_kare.get(), props), new Item.Properties());

	public static final DeferredItem<Item> IKEGAKI_sakura = register("block_low_sakura", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_kaede = register("block_low_kaede", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_ichoh = register("block_low_ichoh", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_kare = register("block_low_oakkare", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_kare.get(), props), new Item.Properties());

	public static final DeferredItem<Item> IKEGAKILONG_sakura = register("block_longsakura_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_kaede = register("block_longkaede_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_ichoh = register("block_longichoh_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_ichoh.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_kare = register("block_longoakkare_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_kare.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ITABEI_sakura = register("block_itabei_sakura", (props) -> new Fuel_200(Garden_Blocks.ITABEI_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_kaede = register("block_itabei_kaede", (props) -> new Fuel_200(Garden_Blocks.ITABEI_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_ichoh = register("block_itabei_ichoh", (props) -> new Fuel_200(Garden_Blocks.ITABEI_ichoh.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> KIDO_sakura = register("block_kido_sakura", (props) -> new Fuel_200(Garden_Blocks.KIDO_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_kaede = register("block_kido_kaede", (props) -> new Fuel_200(Garden_Blocks.KIDO_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_ichoh = register("block_kido_ichoh", (props) -> new Fuel_200(Garden_Blocks.KIDO_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CHABUDAI = register("block_chabudai", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_spruce = register("block_chabudai_spruce", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_birch = register("block_chabudai_birch", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_jungle = register("block_chabudai_jungle", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_acacia = register("block_chabudai_acacia", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_darkoak = register("block_chabudai_darkoak", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_mangrove = register("block_chabudai_mangrove", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_cherry = register("block_chabudai_cherry", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_paleoak = register("block_chabudai_paleoak", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_paleoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_sakura = register("block_chabudai_sakura", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_kaede = register("block_chabudai_kaede", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHABUDAI_ichoh = register("block_chabudai_ichoh", (props) -> new Fuel_150(Unit_Blocks.CHABUDAI_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KOTATSU = register("block_kotatsu", (props) -> new Fuel_200(Unit_Blocks.KOTATSU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_spruce = register("block_kotatsu_spruce", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_birch = register("block_kotatsu_birch", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_jungle = register("block_kotatsu_jungle", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_acacia = register("block_kotatsu_acacia", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_darkoak = register("block_kotatsu_darkoak", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_mangrove = register("block_kotatsu_mangrove", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_cherry = register("block_kotatsu_cherry", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_paleoak = register("block_kotatsu_paleoak", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_paleoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_sakura = register("block_kotatsu_sakura", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_sakura.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_kaede = register("block_kotatsu_kaede", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_kaede.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOTATSU_ichoh = register("block_kotatsu_ichoh", (props) -> new Fuel_200(Unit_Blocks.KOTATSU_ichoh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KUSATABA = register("block_tabakusa", (props) -> new Fuel_200(Seasonal_Blocks.KUSATABA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WARATABA = register("block_tabawara", (props) -> new Fuel_200(Seasonal_Blocks.WARATABA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAYATABA = register("block_tabakaya", (props) -> new Fuel_200(Seasonal_Blocks.KAYATABA.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KUSATABA_RF = register("block_tabakusa_roof", (props) -> new Fuel_100(Seasonal_Blocks.KUSATABA_RF.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WARATABA_RF = register("block_tabawara_roof", (props) -> new Fuel_100(Seasonal_Blocks.WARATABA_RF.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAYATABA_RF = register("block_tabakaya_roof", (props) -> new Fuel_100(Seasonal_Blocks.KAYATABA_RF.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KUSATABA_STAIRS = register("block_tabakusa_stairs", (props) -> new Fuel_200(Seasonal_Blocks.KUSATABA_STAIRS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WARATABA_STAIRS = register("block_tabawara_stairs", (props) -> new Fuel_200(Seasonal_Blocks.WARATABA_STAIRS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAYATABA_STAIRS = register("block_tabakaya_stairs", (props) -> new Fuel_200(Seasonal_Blocks.KAYATABA_STAIRS.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KADOMATSU = register("block_kadomatsu", (props) -> new Not_Fuel(Seasonal_Blocks.KADOMATSU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHIMENAWA = register("block_shimenawa", (props) -> new Fuel_100(Seasonal_Blocks.SHIMENAWA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAGAMIMOCHI = register("block_kagamimochi", (props) -> new Fuel_200(Seasonal_Blocks.KAGAMIMOCHI.get(), props), new Item.Properties());

	public static final DeferredItem<Item> HINAKAZARI = register("block_hinakazari", (props) -> new Not_Fuel(Seasonal_Blocks.HINAKAZARI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> HINADAN = register("block_hinadan", (props) -> new Not_Fuel(Seasonal_Blocks.HINADAN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> XMASTREE = register("block_xmastree", (props) -> new Fuel_100(Seasonal_Blocks.XMASTREE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> XMASTREE_W = register("block_xmastree_w", (props) -> new Fuel_100(Seasonal_Blocks.XMASTREE_W.get(), props), new Item.Properties());

	public static final DeferredItem<Item> PRESENT_app = register("block_present_app", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_app.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PRESENT_bok = register("block_present_bok", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_bok.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PRESENT_dia = register("block_present_dia", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_dia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PRESENT_lap = register("block_present_lap", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_lap.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PRESENT_bla = register("block_present_bla", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_bla.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PRESENT_chc = register("block_present_chc", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_chc.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PRESENT_chh = register("block_present_chh", (props) -> new Not_Fuel(Seasonal_Blocks.PRESENT_chh.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SNOWCORE = register("block_snowcore", (props) -> new AddInfo_ItemBlock(Seasonal_Blocks.SNOWCORE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SNOWMAN = register("block_snowman", (props) -> new AddInfo_ItemBlock(Seasonal_Blocks.SNOWMAN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> COCOA_F = register("item_cocoa_ferm", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> COCOA_R = register("item_cocoa_roast", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> COCOA_M = register("item_cocoa_mass", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> CHOCO_raw = register("item_choco_raw", AddInfo_Item::new, new Item.Properties().craftRemainder(Items.BOWL));
	public static final DeferredItem<Item> COCOA_TARU = register("block_taru_cocoa_f", (props) -> new Not_Fuel(Hakkou_Blocks.COCOA_TARU.get(), props), new Item.Properties());

	public static final DeferredItem<Item> FOOD_CHOCO = register("item_food_choco", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.CHOCO));
	public static final DeferredItem<Item> FOOD_CHOCO_apple = register("item_food_choco_apple", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.APPLE));
	public static final DeferredItem<Item> FOOD_CHOCO_cherry = register("item_food_choco_cherry", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.CHERRY));
	public static final DeferredItem<Item> FOOD_CHOCO_citrus = register("item_food_choco_citrus", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.CITRUS));
	public static final DeferredItem<Item> FOOD_CHOCO_grape = register("item_food_choco_grape", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.GRAPE));
	public static final DeferredItem<Item> FOOD_CHOCO_tea = register("item_food_choco_greentea", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.TEA));
	public static final DeferredItem<Item> FOOD_CHOCO_heart = register("item_food_choco_heart", Item::new, new Item.Properties().food(FoodPoints.CHOCO, FoodEffects.HEART));

	public static final DeferredItem<Item> UCHIWA_white = register("block_uchiwa_white", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_orange = register("block_uchiwa_orange", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_magenta = register("block_uchiwa_magenta", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_lightb = register("block_uchiwa_lightb", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_yellow = register("block_uchiwa_yellow", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_lime = register("block_uchiwa_lime", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_pink = register("block_uchiwa_pink", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_gray = register("block_uchiwa_gray", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_lightg = register("block_uchiwa_lightg", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_cyan = register("block_uchiwa_cyan", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_purple = register("block_uchiwa_purple", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_blue = register("block_uchiwa_blue", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_brown = register("block_uchiwa_brown", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_green = register("block_uchiwa_green", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_red = register("block_uchiwa_red", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UCHIWA_black = register("block_uchiwa_black", (props) -> new Not_Fuel(Seasonal_Blocks.UCHIWA_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> FOOD_WATAGASHI = register("item_food_watagashi", Watagashi_Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.SUGAR));
	public static final DeferredItem<Item> FOOD_WATAGASHI_apple = register("item_food_watagashi_y", Watagashi_Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.APPLE));
	public static final DeferredItem<Item> FOOD_WATAGASHI_cherry = register("item_food_watagashi_p", Watagashi_Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.CHERRY));
	public static final DeferredItem<Item> FOOD_WATAGASHI_citrus = register("item_food_watagashi_o", Watagashi_Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.CITRUS));
	public static final DeferredItem<Item> FOOD_WATAGASHI_grape = register("item_food_watagashi_r", Watagashi_Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.GRAPE));
	public static final DeferredItem<Item> FOOD_WATAGASHI_tea = register("item_food_watagashi_g", Watagashi_Item::new, new Item.Properties().food(FoodPoints.FPS1_01A, FoodEffects.TEA));

	public static final DeferredItem<Item> WATAGASHI_block = register("block_watagashi", (props) -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_block.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WATAGASHI_apple = register("block_watagashi_yellow", (props) -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_apple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WATAGASHI_cherry = register("block_watagashi_pink", (props) -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WATAGASHI_citrus = register("block_watagashi_orange", (props) -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_citrus.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WATAGASHI_grape = register("block_watagashi_red", (props) -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_grape.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WATAGASHI_tea = register("block_watagashi_green", (props) -> new Not_Fuel(Seasonal_Blocks.WATAGASHI_tea.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KAKIGOURI_hata = register("block_kakigouri_hata", (props) -> new Not_Fuel(Seasonal_Blocks.KAKIGOURI_hata.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KAKIGOURI_block = register("block_kakigouri_block1", (props) -> new Dish_DrinkGlass(Seasonal_Blocks.KAKIGOURI_block.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.K_SUGAR));
	public static final DeferredItem<Item> KAKIGOURI_apple = register("block_kakigouri_yellow1", (props) -> new Dish_DrinkGlass(Seasonal_Blocks.KAKIGOURI_apple.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.K_APPLE));
	public static final DeferredItem<Item> KAKIGOURI_cherry = register("block_kakigouri_pink1", (props) -> new Dish_DrinkGlass(Seasonal_Blocks.KAKIGOURI_cherry.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.K_CHERRY));
	public static final DeferredItem<Item> KAKIGOURI_citrus = register("block_kakigouri_orange1", (props) -> new Dish_DrinkGlass(Seasonal_Blocks.KAKIGOURI_citrus.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.K_CITRUS));
	public static final DeferredItem<Item> KAKIGOURI_grape = register("block_kakigouri_red1", (props) -> new Dish_DrinkGlass(Seasonal_Blocks.KAKIGOURI_grape.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.K_GRAPE));
	public static final DeferredItem<Item> KAKIGOURI_tea = register("block_kakigouri_green1", (props) -> new Dish_DrinkGlass(Seasonal_Blocks.KAKIGOURI_tea.get(), props), new Item.Properties().food(FoodPoints.ALWAYS_0, FoodEffects.K_TEA));

	/* YUKATA */
	public static final DeferredItem<Item> YKTD_GETA = register("item_ykt_getadoak", (props) -> new Costume_YUKATA(MaterialArmor_CM.IKADUCHIYKT_BOOTS, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> YKTO_GETA = register("item_ykt_getaoak", (props) -> new Costume_YUKATA(MaterialArmor_CM.TTOKUYKT_BOOTS, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> IKADUCHIYKT_HELMET = register("item_ykt_ikaduchi_kazari", (props) -> new Costume_YUKATA(MaterialArmor_CM.IKADUCHIYKT_HELMET, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> IKADUCHIYKT_CHESTPLATE = register("item_ykt_ikaduchi_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.IKADUCHIYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> IKADUCHIYKT_LEGGINGS = register("item_ykt_ikaduchi_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.IKADUCHIYKT, ArmorType.LEGGINGS, props), new Item.Properties());

	public static final DeferredItem<Item> INADUMAYKT_HELMET = register("item_ykt_inaduma_kazari", (props) -> new Costume_YUKATA(MaterialArmor_CM.INADUMAYKT_HELMET, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> INADUMAYKT_CHESTPLATE = register("item_ykt_inaduma_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.INADUMAYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> INADUMAYKT_LEGGINGS = register("item_ykt_inaduma_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.INADUMAYKT, ArmorType.LEGGINGS, props), new Item.Properties());

	public static final DeferredItem<Item> HAMAKAZEYKT_HELMET = register("item_ykt_hamakaze_kazari", (props) -> new Costume_YUKATA(MaterialArmor_CM.HAMAKAZEYKT_HELMET, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> HAMAKAZEYKT_CHESTPLATE = register("item_ykt_hamakaze_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.HAMAKAZEYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> HAMAKAZEYKT_LEGGINGS = register("item_ykt_hamakaze_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.HAMAKAZEYKT, ArmorType.LEGGINGS, props), new Item.Properties());

	public static final DeferredItem<Item> URAKAZEYKT_HELMET = register("item_ykt_urakaze_kazari", (props) -> new Costume_YUKATA(MaterialArmor_CM.URAKAZEYKT_HELMET, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> URAKAZEYKT_CHESTPLATE = register("item_ykt_urakaze_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.URAKAZEYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> URAKAZEYKT_LEGGINGS = register("item_ykt_urakaze_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.URAKAZEYKT, ArmorType.LEGGINGS, props), new Item.Properties());

	public static final DeferredItem<Item> KAWAKAZEYKT_HELMET = register("item_ykt_kawakaze_kazari", (props) -> new Costume_YUKATA(MaterialArmor_CM.KAWAKAZEYKT_HELMET, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> KAWAKAZEYKT_CHESTPLATE = register("item_ykt_kawakaze_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.KAWAKAZEYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> KAWAKAZEYKT_LEGGINGS = register("item_ykt_kawakaze_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.KAWAKAZEYKT, ArmorType.LEGGINGS, props), new Item.Properties());

	public static final DeferredItem<Item> OBOROYKT_HELMET = register("item_ykt_oboro_kazari", (props) -> new Costume_YUKATA(MaterialArmor_CM.OBOROYKT_HELMET, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> OBOROYKT_CHESTPLATE = register("item_ykt_oboro_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.OBOROYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> OBOROYKT_LEGGINGS = register("item_ykt_oboro_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.OBOROYKT, ArmorType.LEGGINGS, props), new Item.Properties());

	public static final DeferredItem<Item> TTOKUYKT_CHESTPLATE = register("item_ykt_ttoku_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.TTOKUYKT, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> TTOKUYKT_LEGGINGS = register("item_ykt_ttoku_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.TTOKUYKT, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> TTOKUYKTB_CHESTPLATE = register("item_ykt_ttokub_mini", (props) -> new Costume_YUKATA(MaterialArmor_CM.TTOKUYKTB, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> TTOKUYKTB_LEGGINGS = register("item_ykt_ttokub_long", (props) -> new Costume_YUKATA(MaterialArmor_CM.TTOKUYKTB, ArmorType.LEGGINGS, props), new Item.Properties());

	/* SantaCos */
	public static final DeferredItem<Item> AKASHISANTA_HELMET = register("item_santaakashi_helmet", (props) -> new Costume_Santa(MaterialArmor_CM.AKASHISANTA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> AKASHISANTA_CHESTPLATE = register("item_santaakashi_chestplate", (props) -> new Costume_Santa(MaterialArmor_CM.AKASHISANTA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> AKASHISANTA_LEGGINGS = register("item_santaakashi_leggings", (props) -> new Costume_Santa(MaterialArmor_CM.AKASHISANTA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> AKASHISANTA_BOOTS = register("item_santaakashi_boots", (props) -> new Costume_Santa(MaterialArmor_CM.AKASHISANTA_BOOTS, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> SUZUYASANTA_HELMET = register("item_santasuzuya_helmet", (props) -> new Costume_Santa(MaterialArmor_CM.SUZUYASANTA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> SUZUYASANTA_CHESTPLATE = register("item_santasuzuya_chestplate", (props) -> new Costume_Santa(MaterialArmor_CM.SUZUYASANTA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> SUZUYASANTA_LEGGINGS = register("item_santasuzuya_leggings", (props) -> new Costume_Santa(MaterialArmor_CM.SUZUYASANTA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> SUZUYASANTA_BOOTS = register("item_santasuzuya_boots", (props) -> new Costume_Santa(MaterialArmor_CM.SUZUYASANTA_BOOTS, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> KUMANOSANTA_HELMET = register("item_santakumano_helmet", (props) -> new Costume_Santa(MaterialArmor_CM.KUMANOSANTA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> KUMANOSANTA_CHESTPLATE = register("item_santakumano_chestplate", (props) -> new Costume_Santa(MaterialArmor_CM.KUMANOSANTA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> KUMANOSANTA_LEGGINGS = register("item_santakumano_leggings", (props) -> new Costume_Santa(MaterialArmor_CM.KUMANOSANTA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> KUMANOSANTA_BOOTS = register("item_santakumano_boots", (props) -> new Costume_Santa(MaterialArmor_CM.KUMANOSANTA_BOOTS, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> RJ_SANTA_HELMET = register("item_santaryujou_helmet", (props) -> new Costume_Santa(MaterialArmor_CM.RJ_SANTA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_SANTA_CHESTPLATE = register("item_santaryujou_chestplate", (props) -> new Costume_Santa(MaterialArmor_CM.RJ_SANTA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_SANTA_LEGGINGS = register("item_santaryujou_leggings", (props) -> new Costume_Santa(MaterialArmor_CM.RJ_SANTA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_SANTA_BOOTS = register("item_santaryujou_boots", (props) -> new Costume_Santa(MaterialArmor_CM.RJ_SANTA_BOOTS, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> TEITOKUSANTA_HELMET = register("item_santattk_helmet", (props) -> new Costume_Santa(MaterialArmor_CM.TEITOKUSANTA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> TEITOKUSANTA_CHESTPLATE = register("item_santattk_chestplate", (props) -> new Costume_Santa(MaterialArmor_CM.TEITOKUSANTA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> TEITOKUSANTA_LEGGINGS = register("item_santattk_leggings", (props) -> new Costume_Santa(MaterialArmor_CM.TEITOKUSANTA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> TEITOKUSANTA_BOOTS = register("item_santattk_boots", (props) -> new Costume_Santa(MaterialArmor_CM.TEITOKUSANTA_BOOTS, ArmorType.BOOTS, props), new Item.Properties());

	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
