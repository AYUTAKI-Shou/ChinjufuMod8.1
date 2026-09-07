package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.items.color.Chalk_Black;
import com.ayutaki.chinjufumod.items.color.Chalk_Blue;
import com.ayutaki.chinjufumod.items.color.Chalk_Brown;
import com.ayutaki.chinjufumod.items.color.Chalk_Cyan;
import com.ayutaki.chinjufumod.items.color.Chalk_Gray;
import com.ayutaki.chinjufumod.items.color.Chalk_Green;
import com.ayutaki.chinjufumod.items.color.Chalk_LightBlue;
import com.ayutaki.chinjufumod.items.color.Chalk_LightGray;
import com.ayutaki.chinjufumod.items.color.Chalk_Lime;
import com.ayutaki.chinjufumod.items.color.Chalk_Magenta;
import com.ayutaki.chinjufumod.items.color.Chalk_Orange;
import com.ayutaki.chinjufumod.items.color.Chalk_Pink;
import com.ayutaki.chinjufumod.items.color.Chalk_Purple;
import com.ayutaki.chinjufumod.items.color.Chalk_Red;
import com.ayutaki.chinjufumod.items.color.Chalk_White;
import com.ayutaki.chinjufumod.items.color.Chalk_Yellow;
import com.ayutaki.chinjufumod.items.fuel.Fuel_100;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Fuel_200;
import com.ayutaki.chinjufumod.items.fuel.Fuel_300;
import com.ayutaki.chinjufumod.items.fuel.Fuel_7200;
import com.ayutaki.chinjufumod.items.fuel.ItemCurtain;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.items.weapon.AdmiralStamp;
import com.ayutaki.chinjufumod.items.weapon.Shouhou;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_Chinjufu {
	/* 335 = 334 - (3 * 17) + (3 * 17) + ORE_DEEP */
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	public static final DeferredItem<Item> ADMIRAL_STAMPB = register("item_admiralstamp_b", (props) -> new AdmiralStamp(Chinjufu_Blocks.I_ADMIRAL_STAMP.get(), props), new Item.Properties().durability(16));
	public static final DeferredItem<Item> WORK_ORDER = register("item_workorder", Item::new, new Item.Properties());
	public static final DeferredItem<Item> REPORT_BOX = register("block_report_box", (props) -> new Not_Fuel(Chinjufu_Blocks.REPORT_BOX.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUHOU_empty = register("item_shouhou_empty", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> SHOUHOU = register("item_shouhou", Shouhou::new, new Item.Properties());

	public static final DeferredItem<Item> BAUXITE = register("item_bauxite", Item::new, new Item.Properties());
	public static final DeferredItem<Item> ALUMINUM = register("item_ingot_alumi", Item::new, new Item.Properties());
	public static final DeferredItem<Item> SUMI = register("item_sumi_c", AddInfo_Item::new, new Item.Properties());
	
	public static final DeferredItem<Item> BAUXITE_ORE = register("block_bauxite_ore", (props) -> new Not_Fuel(Chinjufu_Blocks.BAUXITE_ORE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BAUXITE_ORE_DEEP = register("block_bauxite_ore_deep", (props) -> new Not_Fuel(Chinjufu_Blocks.BAUXITE_ORE_DEEP.get(), props), new Item.Properties());

	public static final DeferredItem<Item> OIL_DRUM = register("block_fuel_can", (props) -> new Fuel_7200(Chinjufu_Blocks.OIL_DRUM.get(), props), new Item.Properties());
	public static final DeferredItem<Item> EMPTY_BOX = register("block_empty_box", (props) -> new Fuel_100(Chinjufu_Blocks.EMPTY_BOX.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMMO_BOX = register("block_ammunition_box", (props) -> new Not_Fuel(Chinjufu_Blocks.AMMO_BOX.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BAUXITE_BOX = register("block_bauxite_box", (props) -> new Not_Fuel(Chinjufu_Blocks.BAUXITE_BOX.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ALUMI_BLOCK = register("block_alumi_block", (props) -> new Not_Fuel(Chinjufu_Blocks.ALUMI_BLOCK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> STEEL_BLOCK = register("block_steel_block", (props) -> new Not_Fuel(Chinjufu_Blocks.STEEL_BLOCK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> COPPER_BLOCK = register("block_copper_block", (props) -> new Not_Fuel(Chinjufu_Blocks.COPPER_BLOCK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GOLD_BLOCK = register("block_gold_block", (props) -> new Not_Fuel(Chinjufu_Blocks.GOLD_BLOCK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NETHERITE_BLOCK = register("block_netherite_block", (props) -> new Not_Fuel(Chinjufu_Blocks.NETHERITE_BLOCK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> DRESSINGTABLE = register("block_dressingtable", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_spruce = register("block_dressingtable_s", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_birch = register("block_dressingtable_b", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_jungle = register("block_dressingtable_j", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_acacia = register("block_dressingtable_a", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_darkoak = register("block_dressingtable_d", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_mangrove = register("block_dressingtable_mangrove", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_cherry = register("block_dressingtable_cherry", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DRESSINGTABLE_paleoak = register("block_dressingtable_paleoak", (props) -> new Not_Fuel(Furniture_Blocks.DRESSINGTABLE_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> UNITDESK = register("block_unitdesk", (props) -> new Fuel_300(Unit_Blocks.UNITDESK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_spruce = register("block_unitdesk_spruce", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_birch = register("block_unitdesk_birch", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_jungle = register("block_unitdesk_jungle", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_acacia = register("block_unitdesk_acacia", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_darkoak = register("block_unitdesk_darkoak", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_mangrove = register("block_unitdesk_mangrove", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_cherry = register("block_unitdesk_cherry", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> UNITDESK_paleoak = register("block_unitdesk_paleoak", (props) -> new Fuel_300(Unit_Blocks.UNITDESK_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CAFETABLE = register("block_cafetable", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_spruce = register("block_cafetable_spruce", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_birch = register("block_cafetable_birch", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_jungle = register("block_cafetable_jungle", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_acacia = register("block_cafetable_acacia", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_darkoak = register("block_cafetable_darkoak", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_mangrove = register("block_cafetable_mangrove", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_cherry = register("block_cafetable_cherry", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFETABLE_paleoak = register("block_cafetable_paleoak", (props) -> new Fuel_300(Unit_Blocks.CAFETABLE_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> DININGCHAIR = register("block_diningchair", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_spruce = register("block_diningchair_s", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_birch = register("block_diningchair_b", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_jungle = register("block_diningchair_j", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_acacia = register("block_diningchair_a", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_darkoak = register("block_diningchair_d", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_mangrove = register("block_diningchair_mangrove", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_cherry = register("block_diningchair_cherry", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> DININGCHAIR_paleoak = register("block_diningchair_paleoak", (props) -> new Fuel_150(Chair_Blocks.DININGCHAIR_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> LOGCHAIR = register("block_logchair", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_spruce = register("block_logchair_spruce", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_birch = register("block_logchair_birch", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_jungle = register("block_logchair_jungle", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_acacia = register("block_logchair_acacia", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_darkoak = register("block_logchair_darkoak", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_mangrove = register("block_logchair_mangrove", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_cherry = register("block_logchair_cherry", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOGCHAIR_paleoak = register("block_logchair_paleoak", (props) -> new Fuel_150(Chair_Blocks.LOGCHAIR_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CAFECHAIR_white = register("block_cafechair_white", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_orange = register("block_cafechair_orange", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_magenta = register("block_cafechair_magenta", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_lightb = register("block_cafechair_lightb", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_yellow = register("block_cafechair_yellow", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_lime = register("block_cafechair_lime", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_pink = register("block_cafechair_pink", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_gray = register("block_cafechair_gray", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_lightg = register("block_cafechair_lightg", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_cyan = register("block_cafechair_cyan", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_purple = register("block_cafechair_purple", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_blue = register("block_cafechair_blue", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_brown = register("block_cafechair_brown", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_green = register("block_cafechair_green", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_red = register("block_cafechair_red", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CAFECHAIR_black = register("block_cafechair_black", (props) -> new Fuel_150(Chair_Blocks.CAFECHAIR_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SOFA_leather = register("block_sofa_leather", (props) -> new Fuel_150(Chair_Blocks.SOFA_leather.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_white = register("block_sofa_white", (props) -> new Fuel_150(Chair_Blocks.SOFA_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_orange = register("block_sofa_orange", (props) -> new Fuel_150(Chair_Blocks.SOFA_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_magenta = register("block_sofa_magenta", (props) -> new Fuel_150(Chair_Blocks.SOFA_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_lightb = register("block_sofa_lightblue", (props) -> new Fuel_150(Chair_Blocks.SOFA_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_yellow = register("block_sofa_yellow", (props) -> new Fuel_150(Chair_Blocks.SOFA_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_lime = register("block_sofa_lime", (props) -> new Fuel_150(Chair_Blocks.SOFA_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_pink = register("block_sofa_pink", (props) -> new Fuel_150(Chair_Blocks.SOFA_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_gray = register("block_sofa_gray", (props) -> new Fuel_150(Chair_Blocks.SOFA_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_lightg = register("block_sofa_lightgray", (props) -> new Fuel_150(Chair_Blocks.SOFA_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_cyan = register("block_sofa_cyan", (props) -> new Fuel_150(Chair_Blocks.SOFA_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_purple = register("block_sofa_purple", (props) -> new Fuel_150(Chair_Blocks.SOFA_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_blue = register("block_sofa_blue", (props) -> new Fuel_150(Chair_Blocks.SOFA_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_brown = register("block_sofa_brown", (props) -> new Fuel_150(Chair_Blocks.SOFA_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_green = register("block_sofa_green", (props) -> new Fuel_150(Chair_Blocks.SOFA_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_red = register("block_sofa_red", (props) -> new Fuel_150(Chair_Blocks.SOFA_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SOFA_black = register("block_sofa_black", (props) -> new Fuel_150(Chair_Blocks.SOFA_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BENCH = register("block_bench", (props) -> new Fuel_150(Chair_Blocks.BENCH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_spru = register("block_bench_spru", (props) -> new Fuel_150(Chair_Blocks.BENCH_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_bir = register("block_bench_bir", (props) -> new Fuel_150(Chair_Blocks.BENCH_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_jun = register("block_bench_jun", (props) -> new Fuel_150(Chair_Blocks.BENCH_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_aca = register("block_bench_aca", (props) -> new Fuel_150(Chair_Blocks.BENCH_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_doak = register("block_bench_doak", (props) -> new Fuel_150(Chair_Blocks.BENCH_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_mangrove = register("block_bench_mangrove", (props) -> new Fuel_150(Chair_Blocks.BENCH_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_cherry = register("block_bench_cherry", (props) -> new Fuel_150(Chair_Blocks.BENCH_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BENCH_paleoak = register("block_bench_paleoak", (props) -> new Fuel_150(Chair_Blocks.BENCH_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SCHOOLCHAIR = register("block_schoolchair", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_spruce = register("block_schoolchair_s", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_birch = register("block_schoolchair_b", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_jungle = register("block_schoolchair_j", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_acacia = register("block_schoolchair_a", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_darkoak = register("block_schoolchair_d", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_mangrove = register("block_schoolchair_mangrove", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_cherry = register("block_schoolchair_cherry", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLCHAIR_paleoak = register("block_schoolchair_paleoak", (props) -> new Fuel_150(School_Blocks.SCHOOLCHAIR_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SCHOOLDESK = register("block_schooldesk", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_spruce = register("block_schooldesk_s", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_birch = register("block_schooldesk_b", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_jungle = register("block_schooldesk_j", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_acacia = register("block_schooldesk_a", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_darkoak = register("block_schooldesk_d", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_mangrove = register("block_schooldesk_mangrove", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_cherry = register("block_schooldesk_cherry", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SCHOOLDESK_paleoak = register("block_schooldesk_paleoak", (props) -> new Fuel_200(School_Blocks.SCHOOLDESK_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TEACHERDESK = register("block_teacherdesk", (props) -> new Fuel_300(School_Blocks.TEACHERDESK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_spruce = register("block_teacherdesk_s", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_birch = register("block_teacherdesk_b", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_jungle = register("block_teacherdesk_j", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_acacia = register("block_teacherdesk_a", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_darkoak = register("block_teacherdesk_d", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_mangrove = register("block_teacherdesk_mangrove", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_cherry = register("block_teacherdesk_cherry", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TEACHERDESK_paleoak = register("block_teacherdesk_paleoak", (props) -> new Fuel_300(School_Blocks.TEACHERDESK_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> LOWDESK = register("block_lowdesk", (props) -> new Fuel_150(Unit_Blocks.LOWDESK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_spruce = register("block_lowdesk_spruce", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_birch = register("block_lowdesk_birch", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_jungle = register("block_lowdesk_jungle", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_acacia = register("block_lowdesk_acacia", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_darkoak = register("block_lowdesk_darkoak", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_mangrove = register("block_lowdesk_mangrove", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_cherry = register("block_lowdesk_cherry", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LOWDESK_paleoak = register("block_lowdesk_paleoak", (props) -> new Fuel_150(Unit_Blocks.LOWDESK_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> LETTERTRAY = register("block_lettertray_c", (props) -> new Not_Fuel(Unit_Blocks.LETTERTRAY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUDETRAY = register("block_fudetray_c", (props) -> new Not_Fuel(Unit_Blocks.FUDETRAY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BLACKBOARD = register("block_blackboard", (props) -> new Not_Fuel(School_Blocks.BLACKBOARD.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_OAK = register("block_board_oak", (props) -> new Not_Fuel(School_Blocks.BOARD_OAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_SPRUCE = register("block_board_spruce", (props) -> new Not_Fuel(School_Blocks.BOARD_SPRUCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_BIRCH = register("block_board_birch", (props) -> new Not_Fuel(School_Blocks.BOARD_BIRCH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_JUNGLE = register("block_board_jungle", (props) -> new Not_Fuel(School_Blocks.BOARD_JUNGLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_ACACIA = register("block_board_acacia", (props) -> new Not_Fuel(School_Blocks.BOARD_ACACIA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_DOAK = register("block_board_darkoak", (props) -> new Not_Fuel(School_Blocks.BOARD_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_MANGROVE = register("block_board_mangrove", (props) -> new Not_Fuel(School_Blocks.BOARD_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_CHERRY = register("block_board_cherry", (props) -> new Not_Fuel(School_Blocks.BOARD_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BOARD_PALEOAK = register("block_board_paleoak", (props) -> new Not_Fuel(School_Blocks.BOARD_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CHALK_white = register("item_chalk_white", Chalk_White::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_orange = register("item_chalk_orange", Chalk_Orange::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_magenta = register("item_chalk_magenta", Chalk_Magenta::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_lightb = register("item_chalk_lightblue", Chalk_LightBlue::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_yellow = register("item_chalk_yellow", Chalk_Yellow::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_lime = register("item_chalk_lime", Chalk_Lime::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_pink = register("item_chalk_pink", Chalk_Pink::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_gray = register("item_chalk_gray", Chalk_Gray::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_lightg = register("item_chalk_lightgray", Chalk_LightGray::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_cyan = register("item_chalk_cyan", Chalk_Cyan::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_purple = register("item_chalk_purple", Chalk_Purple::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_blue = register("item_chalk_blue", Chalk_Blue::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_brown = register("item_chalk_brown", Chalk_Brown::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_green = register("item_chalk_green", Chalk_Green::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_red = register("item_chalk_red", Chalk_Red::new, new Item.Properties());
	public static final DeferredItem<Item> CHALK_black = register("item_chalk_black", Chalk_Black::new, new Item.Properties());
	public static final DeferredItem<Item> BOARD_ERASER = register("item_blackboard_eraser", Board_Eraser::new, new Item.Properties());
	
	public static final DeferredItem<Item> WINDOW_oak = register("block_window", (props) -> new Not_Fuel(Window_Blocks.WINDOW_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_spruce = register("block_window_spruce", (props) -> new Not_Fuel(Window_Blocks.WINDOW_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_birch = register("block_window_birch", (props) -> new Not_Fuel(Window_Blocks.WINDOW_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_jungle = register("block_window_jungle", (props) -> new Not_Fuel(Window_Blocks.WINDOW_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_acacia = register("block_window_acacia", (props) -> new Not_Fuel(Window_Blocks.WINDOW_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_darkoak = register("block_window_darkoak", (props) -> new Not_Fuel(Window_Blocks.WINDOW_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_mangrove = register("block_window_mangrove", (props) -> new Not_Fuel(Window_Blocks.WINDOW_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_cherry = register("block_window_cherry", (props) -> new Not_Fuel(Window_Blocks.WINDOW_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOW_paleoak = register("block_window_paleoak", (props) -> new Not_Fuel(Window_Blocks.WINDOW_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WINDOWB_oak = register("block_windowb", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_spruce = register("block_windowb_spruce", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_birch = register("block_windowb_birch", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_jungle = register("block_windowb_jungle", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_acacia = register("block_windowb_acacia", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_darkoak = register("block_windowb_darkoak", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_mangrove = register("block_windowb_mangrove", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_cherry = register("block_windowb_cherry", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWB_paleoak = register("block_windowb_paleoak", (props) -> new Not_Fuel(Window_Blocks.WINDOWB_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WINDOWTALLBOT_oak = register("block_windowtallbot", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_spruce = register("block_windowtallbot_spruce", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_birch = register("block_windowtallbot_birch", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_jungle = register("block_windowtallbot_jungle", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_acacia = register("block_windowtallbot_acacia", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_darkoak = register("block_windowtallbot_darkoak", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_mangrove = register("block_windowtallbot_mangrove", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_cherry = register("block_windowtallbot_cherry", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALLBOT_paleoak = register("block_windowtallbot_paleoak", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALLBOT_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WINDOWTALL_oak = register("block_windowtall", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_spruce = register("block_windowtall_spruce", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_birch = register("block_windowtall_birch", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_jungle = register("block_windowtall_jungle", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_acacia = register("block_windowtall_acacia", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_darkoak = register("block_windowtall_darkoak", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_mangrove = register("block_windowtall_mangrove", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_cherry = register("block_windowtall_cherry", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WINDOWTALL_paleoak = register("block_windowtall_paleoak", (props) -> new Not_Fuel(Window_Blocks.WINDOWTALL_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CURTAIN_white = register("block_curtain_white", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_orange = register("block_curtain_orange", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_magenta = register("block_curtain_magenta", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_lightblue = register("block_curtain_lightblue", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_lightblue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_yellow = register("block_curtain_yellow", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_lime = register("block_curtain_lime", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_pink = register("block_curtain_pink", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_gray = register("block_curtain_gray", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_lightgray = register("block_curtain_lightgray", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_lightgray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_cyan = register("block_curtain_cyan", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_purple = register("block_curtain_purple", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_blue = register("block_curtain_blue", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_brown = register("block_curtain_brown", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_green = register("block_curtain_green", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_red = register("block_curtain_red", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAIN_black = register("block_curtain_black", (props) -> new ItemCurtain(Window_Blocks.CURTAIN_black.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> CURTAINTALL_white = register("block_curtaintall_white", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_orange = register("block_curtaintall_orange", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_magenta = register("block_curtaintall_magenta", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_lightblue = register("block_curtaintall_lightblue", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_lightblue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_yellow = register("block_curtaintall_yellow", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_lime = register("block_curtaintall_lime", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_pink = register("block_curtaintall_pink", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_gray = register("block_curtaintall_gray", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_lightgray = register("block_curtaintall_lightgray", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_lightgray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_cyan = register("block_curtaintall_cyan", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_purple = register("block_curtaintall_purple", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_blue = register("block_curtaintall_blue", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_brown = register("block_curtaintall_brown", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_green = register("block_curtaintall_green", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_red = register("block_curtaintall_red", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINTALL_black = register("block_curtaintall_black", (props) -> new ItemCurtain(Window_Blocks.CURTAINTALL_black.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> CURTAINL_white = register("block_curtainlarge_white", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_orange = register("block_curtainlarge_orange", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_magenta = register("block_curtainlarge_magenta", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_lightblue = register("block_curtainlarge_lightblue", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_lightblue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_yellow = register("block_curtainlarge_yellow", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_lime = register("block_curtainlarge_lime", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_pink = register("block_curtainlarge_pink", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_gray = register("block_curtainlarge_gray", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_lightgray = register("block_curtainlarge_lightgray", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_lightgray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_cyan = register("block_curtainlarge_cyan", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_purple = register("block_curtainlarge_purple", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_blue = register("block_curtainlarge_blue", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_brown = register("block_curtainlarge_brown", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_green = register("block_curtainlarge_green", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_red = register("block_curtainlarge_red", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CURTAINL_black = register("block_curtainlarge_black", (props) -> new ItemCurtain(Window_Blocks.CURTAINL_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> STOVECHIMNEY = register("block_stovechimney", (props) -> new Not_Fuel(School_Blocks.STOVECHIMNEY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> STOVECHIMNEY_joint = register("block_stovechimney_joint", (props) -> new Not_Fuel(School_Blocks.STOVECHIMNEY_joint.get(), props), new Item.Properties());
	public static final DeferredItem<Item> STOVECHIMNEY_topk = register("block_stovechimney_topk", (props) -> new Not_Fuel(School_Blocks.STOVECHIMNEY_topk.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CSTOVE_bot = register("block_cstove_bot", (props) -> new Not_Fuel(School_Blocks.CSTOVE_bot.get(), props), new Item.Properties());

	public static final DeferredItem<Item> CANDLE_white = register("block_candle_white", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_orange = register("block_candle_orange", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_magenta = register("block_candle_magenta", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_lightb = register("block_candle_lightb", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_yellow = register("block_candle_yellow", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_lime = register("block_candle_lime", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_pink = register("block_candle_pink", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_gray = register("block_candle_gray", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_lightg = register("block_candle_lightg", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_cyan = register("block_candle_cyan", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_purple = register("block_candle_purple", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_blue = register("block_candle_blue", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_brown = register("block_candle_brown", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_green = register("block_candle_green", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_red = register("block_candle_red", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CANDLE_black = register("block_candle_black", (props) -> new Not_Fuel(Furniture_Blocks.CANDLE_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> LAMP = register("block_lamp", (props) -> new Not_Fuel(Furniture_Blocks.LAMP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> STANDARM = register("block_standarm", (props) -> new Not_Fuel(Furniture_Blocks.STANDARM.get(), props), new Item.Properties());
	public static final DeferredItem<Item> STAND = register("block_standbedroom", (props) -> new Not_Fuel(Furniture_Blocks.STAND.get(), props), new Item.Properties());
	public static final DeferredItem<Item> M_LAMP = register("block_marinelamp", (props) -> new Not_Fuel(Furniture_Blocks.M_LAMP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> E_LIGHT = register("block_lightembed", (props) -> new Not_Fuel(Furniture_Blocks.E_LIGHT.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> ADMIRALCHAIR = register("block_admiralchair", (props) -> new Fuel_300(Chair_Blocks.ADMIRALCHAIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ADMIRALCHAIR_red = register("block_admiralchair_red", (props) -> new Fuel_300(Chair_Blocks.ADMIRALCHAIR_red.get(), props), new Item.Properties());

	public static final DeferredItem<Item> OFFICEDESK_OAK = register("block_officedesk_oak", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_OAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_SPRUCE = register("block_officedesk_spruce", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_SPRUCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_BIRCH = register("block_officedesk_birch", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_BIRCH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_JUNGLE = register("block_officedesk_jungle", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_JUNGLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_ACACIA = register("block_officedesk_acacia", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_ACACIA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_DOAK = register("block_officedesk_darkoak", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_MANGROVE = register("block_officedesk_mangrove", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_CHERRY = register("block_officedesk_cherry", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> OFFICEDESK_PALEOAK = register("block_officedesk_paleoak", (props) -> new Fuel_300(Furniture_Blocks.OFFICEDESK_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TANSU_OAK = register("block_tansu_oak", (props) -> new Fuel_300(Furniture_Blocks.TANSU_OAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_SPRUCE = register("block_tansu_spruce", (props) -> new Fuel_300(Furniture_Blocks.TANSU_SPRUCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_BIRCH = register("block_tansu_birch", (props) -> new Fuel_300(Furniture_Blocks.TANSU_BIRCH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_JUNGLE = register("block_tansu_jungle", (props) -> new Fuel_300(Furniture_Blocks.TANSU_JUNGLE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_ACACIA = register("block_tansu_acacia", (props) -> new Fuel_300(Furniture_Blocks.TANSU_ACACIA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_DOAK = register("block_tansu_doak", (props) -> new Fuel_300(Furniture_Blocks.TANSU_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_MANGROVE = register("block_tansu_mangrove", (props) -> new Fuel_300(Furniture_Blocks.TANSU_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_CHERRY = register("block_tansu_cherry", (props) -> new Fuel_300(Furniture_Blocks.TANSU_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TANSU_PALEOAK = register("block_tansu_paleoak", (props) -> new Fuel_300(Furniture_Blocks.TANSU_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KEIKAIBLOCK = register("block_keikai", (props) -> new Not_Fuel(Harbor_Blocks.KEIKAIBLOCK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KEIRYUKUI = register("block_keiryukui", (props) -> new Not_Fuel(Harbor_Blocks.KEIRYUKUI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KEIRYUKUI_b = register("block_keiryukui_b", (props) -> new Not_Fuel(Harbor_Blocks.KEIRYUKUI_b.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TRUSS = register("block_ctruss", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_white = register("block_ctruss_white", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_orange = register("block_ctruss_orange", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_magenta = register("block_ctruss_magenta", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_lightb = register("block_ctruss_lightb", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_yellow = register("block_ctruss_yellow", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_lime = register("block_ctruss_lime", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_pink = register("block_ctruss_pink", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_gray = register("block_ctruss_gray", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_cyan = register("block_ctruss_cyan", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_purple = register("block_ctruss_purple", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_blue = register("block_ctruss_blue", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_brown = register("block_ctruss_brown", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_green = register("block_ctruss_green", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_red = register("block_ctruss_red", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TRUSS_black = register("block_ctruss_black", (props) -> new Not_Fuel(Harbor_Blocks.TRUSS_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> AMP = register("block_amp", (props) -> new Not_Fuel(Harbor_Blocks.AMP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_white = register("block_amp_white", (props) -> new Not_Fuel(Harbor_Blocks.AMP_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_orange = register("block_amp_orange", (props) -> new Not_Fuel(Harbor_Blocks.AMP_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_magenta = register("block_amp_magenta", (props) -> new Not_Fuel(Harbor_Blocks.AMP_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_lightb = register("block_amp_lightblue", (props) -> new Not_Fuel(Harbor_Blocks.AMP_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_yellow = register("block_amp_yellow", (props) -> new Not_Fuel(Harbor_Blocks.AMP_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_lime = register("block_amp_lime", (props) -> new Not_Fuel(Harbor_Blocks.AMP_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_pink = register("block_amp_pink", (props) -> new Not_Fuel(Harbor_Blocks.AMP_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_gray = register("block_amp_gray", (props) -> new Not_Fuel(Harbor_Blocks.AMP_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_cyan = register("block_amp_cyan", (props) -> new Not_Fuel(Harbor_Blocks.AMP_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_purple = register("block_amp_purple", (props) -> new Not_Fuel(Harbor_Blocks.AMP_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_blue = register("block_amp_blue", (props) -> new Not_Fuel(Harbor_Blocks.AMP_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_brown = register("block_amp_brown", (props) -> new Not_Fuel(Harbor_Blocks.AMP_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_green = register("block_amp_green", (props) -> new Not_Fuel(Harbor_Blocks.AMP_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_red = register("block_amp_red", (props) -> new Not_Fuel(Harbor_Blocks.AMP_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> AMP_black = register("block_amp_black", (props) -> new Not_Fuel(Harbor_Blocks.AMP_black.get(), props), new Item.Properties());

	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
