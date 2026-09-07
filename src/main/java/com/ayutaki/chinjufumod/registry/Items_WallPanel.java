package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_WallPanel {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	public static final DeferredItem<Item> BRICK_GRA = register("block_brick_gra_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_GRA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICK_DIO = register("block_brick_dio_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_DIO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICK_AND = register("block_brick_and_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_AND.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKGRA_CH = register("block_brick_gra_ch_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKGRA_CH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKDIO_CH = register("block_brick_dio_ch_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKDIO_CH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKAND_CH = register("block_brick_and_ch_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKAND_CH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKGRA_CR = register("block_brick_gra_cr_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKGRA_CR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKDIO_CR = register("block_brick_dio_cr_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKDIO_CR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKAND_CR = register("block_brick_and_cr_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKAND_CR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKGRA_MOS = register("block_brick_gra_mos_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKGRA_MOS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKDIO_MOS = register("block_brick_dio_mos_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKDIO_MOS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKAND_MOS = register("block_brick_and_mos_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKAND_MOS.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BRICKSTAIRS_GRA = register("block_brickstairs_gra_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKSTAIRS_GRA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKSTAIRS_DIO = register("block_brickstairs_dio_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKSTAIRS_DIO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICKSTAIRS_AND = register("block_brickstairs_and_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICKSTAIRS_AND.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BGC_slabhalf = register("block_bgc_slabhalf", (props) -> new Not_Fuel(WallPanel_Blocks.BGC_slabhalf.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BDC_slabhalf = register("block_bdc_slabhalf", (props) -> new Not_Fuel(WallPanel_Blocks.BDC_slabhalf.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BAC_slabhalf = register("block_bac_slabhalf", (props) -> new Not_Fuel(WallPanel_Blocks.BAC_slabhalf.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BRICK_STONE_PIL = register("block_brick_stone_pil_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_STONE_PIL.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICK_GRA_PIL = register("block_brick_gra_pil_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_GRA_PIL.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICK_DIO_PIL = register("block_brick_dio_pil_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_DIO_PIL.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BRICK_AND_PIL = register("block_brick_and_pil_c", (props) -> new Not_Fuel(WallPanel_Blocks.BRICK_AND_PIL.get(), props), new Item.Properties());

	public static final DeferredItem<Item> PILLAR_oak = register("block_pillar_oak_c", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_spru = register("block_pillar_spru_c", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_spru.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_bir = register("block_pillar_bir_c", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_bir.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_jun = register("block_pillar_jun_c", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_jun.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_aca = register("block_pillar_aca_c", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_aca.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_doak = register("block_pillar_doak_c", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_doak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_mangrove = register("block_pillar_mangrove", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_cherry = register("block_pillar_cherry", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLAR_paleoak = register("block_pillar_paleoak", (props) -> new Not_Fuel(WallPanel_Blocks.PILLAR_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> PILLARSLAB_oak = register("block_kamoi_oak", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_spru = register("block_kamoi_spruce", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_spru.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_bir = register("block_kamoi_birch", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_bir.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_jun = register("block_kamoi_jungle", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_jun.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_aca = register("block_kamoi_acacia", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_aca.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_doak = register("block_kamoi_darkoak", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_doak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_mangrove = register("block_kamoi_mangrove", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_cherry = register("block_kamoi_cherry", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> PILLARSLAB_paleoak = register("block_kamoi_paleoak", (props) -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_LOG_oak = register("block_wp_log_oak", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_spru = register("block_wp_log_spru", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_spru.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_bir = register("block_wp_log_bir", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_bir.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_jun = register("block_wp_log_jun", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_jun.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_aca = register("block_wp_log_aca", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_aca.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_doak = register("block_wp_log_doak", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_doak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_mangrove = register("block_wp_log_mangrove", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_cherry = register("block_wp_log_cherry", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_LOG_paleoak = register("block_wp_log_paleoak", (props) -> new Not_Fuel(WallPanel_Blocks.WP_LOG_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_PLANK_oak = register("block_wp_plank_oak", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_spru = register("block_wp_plank_spru", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_spru.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_bir = register("block_wp_plank_bir", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_bir.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_jun = register("block_wp_plank_jun", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_jun.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_aca = register("block_wp_plank_aca", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_aca.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_doak = register("block_wp_plank_doak", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_doak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_mangrove = register("block_wp_plank_mangrove", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_cherry = register("block_wp_plank_cherry", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLANK_paleoak = register("block_wp_plank_paleoak", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_STONE = register("block_wp_stone", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_M = register("block_wp_stone_m", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_M.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_STONE_gra = register("block_wp_stone_gra", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_gra.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_dio = register("block_wp_stone_dio", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_dio.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_and = register("block_wp_stone_and", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_and.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_STONE_B = register("block_wp_stone_b", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_B.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_graB = register("block_wp_stone_grab", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_graB.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_dioB = register("block_wp_stone_diob", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_dioB.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_andB = register("block_wp_stone_andb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_andB.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_STONE_P = register("block_wp_stone_p", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_P.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_graP = register("block_wp_stone_grap", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_graP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_dioP = register("block_wp_stone_diop", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_dioP.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_STONE_andP = register("block_wp_stone_andp", (props) -> new Not_Fuel(WallPanel_Blocks.WP_STONE_andP.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_BRICK = register("block_wp_brick", (props) -> new Not_Fuel(WallPanel_Blocks.WP_BRICK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_SANDSTONE = register("block_wp_sand_stone", (props) -> new Not_Fuel(WallPanel_Blocks.WP_SANDSTONE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_REDSANDSTONE = register("block_wp_redsand_stone", (props) -> new Not_Fuel(WallPanel_Blocks.WP_REDSANDSTONE.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_DEEPSLATE = register("block_wp_deepslate", (props) -> new Not_Fuel(WallPanel_Blocks.WP_DEEPSLATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_DEEPSLATE_B = register("block_wp_deepslate_b", (props) -> new Not_Fuel(WallPanel_Blocks.WP_DEEPSLATE_B.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_TUFF = register("block_wp_tuff", (props) -> new Not_Fuel(WallPanel_Blocks.WP_TUFF.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_TUFF_B = register("block_wp_tuff_b", (props) -> new Not_Fuel(WallPanel_Blocks.WP_TUFF_B.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_MUDPACK = register("block_wp_mudpacked", (props) -> new Not_Fuel(WallPanel_Blocks.WP_MUDPACK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_RESIN = register("block_wp_resin", (props) -> new Not_Fuel(WallPanel_Blocks.WP_RESIN.get(), props), new Item.Properties());
	
	public static final DeferredItem<Item> WP_PRISMA = register("block_wp_prisma", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PRISMA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_OBSIDIAN = register("block_wp_obsidian", (props) -> new Not_Fuel(WallPanel_Blocks.WP_OBSIDIAN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_CLAY = register("block_wp_clay", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_white = register("block_wp_clay_white", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_orange = register("block_wp_clay_orange", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_magenta = register("block_wp_clay_magenta", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_lightb = register("block_wp_clay_lightb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_yellow = register("block_wp_clay_yellow", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_lime = register("block_wp_clay_lime", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_pink = register("block_wp_clay_pink", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_gray = register("block_wp_clay_gray", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_lightg = register("block_wp_clay_lightg", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_cyan = register("block_wp_clay_cyan", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_purple = register("block_wp_clay_purple", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_blue = register("block_wp_clay_blue", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_brown = register("block_wp_clay_brown", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_green = register("block_wp_clay_green", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_red = register("block_wp_clay_red", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_CLAY_black = register("block_wp_clay_black", (props) -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_GLASS = register("block_wp_glass", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_white = register("block_wp_glass_white", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_orange = register("block_wp_glass_orange", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_magenta = register("block_wp_glass_magenta", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_lightb = register("block_wp_glass_lightb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_yellow = register("block_wp_glass_yellow", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_lime = register("block_wp_glass_lime", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_pink = register("block_wp_glass_pink", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_gray = register("block_wp_glass_gray", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_lightg = register("block_wp_glass_lightg", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_cyan = register("block_wp_glass_cyan", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_purple = register("block_wp_glass_purple", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_blue = register("block_wp_glass_blue", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_brown = register("block_wp_glass_brown", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_green = register("block_wp_glass_green", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_red = register("block_wp_glass_red", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_GLASS_black = register("block_wp_glass_black", (props) -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_NETHE_rack = register("block_wp_netherrack", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NETHE_rack.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NETHE_b = register("block_wp_netherb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NETHE_b.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_QUARTZ = register("block_wp_quartz", (props) -> new Not_Fuel(WallPanel_Blocks.WP_QUARTZ.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_QUARTZ_PIL = register("block_wp_quartz_pil", (props) -> new Not_Fuel(WallPanel_Blocks.WP_QUARTZ_PIL.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_ENDSTONE = register("block_wp_endstone", (props) -> new Not_Fuel(WallPanel_Blocks.WP_ENDSTONE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_ENDBRICKS = register("block_wp_endstone_b", (props) -> new Not_Fuel(WallPanel_Blocks.WP_ENDBRICKS.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_PURPUR = register("block_wp_purpur", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PURPUR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PURPUR_PIL = register("block_wp_purpur_pil", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PURPUR_PIL.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_BAMBOO = register("block_wp_bamboo", (props) -> new Not_Fuel(WallPanel_Blocks.WP_BAMBOO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_BAMBOO_Y = register("block_wp_bamboo_y", (props) -> new Not_Fuel(WallPanel_Blocks.WP_BAMBOO_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_BAMBOO_K = register("block_wp_bamboo_k", (props) -> new Not_Fuel(WallPanel_Blocks.WP_BAMBOO_K.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_DIRTWALL = register("block_wp_dirtwall", (props) -> new Not_Fuel(WallPanel_Blocks.WP_DIRTWALL.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_white = register("block_wp_plaster_white", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_orange = register("block_wp_plaster_orange", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_magenta = register("block_wp_plaster_magenta", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_lightb = register("block_wp_plaster_lightb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_yellow = register("block_wp_plaster_yellow", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_lime = register("block_wp_plaster_lime", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_pink = register("block_wp_plaster_pink", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_gray = register("block_wp_plaster_gray", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_lightg = register("block_wp_plaster_lightg", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_cyan = register("block_wp_plaster_cyan", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_purple = register("block_wp_plaster_purple", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_blue = register("block_wp_plaster_blue", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_brown = register("block_wp_plaster_brown", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_green = register("block_wp_plaster_green", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_red = register("block_wp_plaster_red", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_PLASTER_black = register("block_wp_plaster_black", (props) -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_NAMAKO_white = register("block_wp_namako_white", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_orange = register("block_wp_namako_orange", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_magenta = register("block_wp_namako_magenta", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_lightb = register("block_wp_namako_lightb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_yellow = register("block_wp_namako_yellow", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_lime = register("block_wp_namako_lime", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_pink = register("block_wp_namako_pink", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_gray = register("block_wp_namako_gray", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_lightg = register("block_wp_namako_lightg", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_cyan = register("block_wp_namako_cyan", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_purple = register("block_wp_namako_purple", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_blue = register("block_wp_namako_blue", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_brown = register("block_wp_namako_brown", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_green = register("block_wp_namako_green", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_red = register("block_wp_namako_red", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKO_black = register("block_wp_namako_black", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WP_NAMAKOB_white = register("block_wp_namako_b_white", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_orange = register("block_wp_namako_b_orange", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_magenta = register("block_wp_namako_b_magenta", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_lightb = register("block_wp_namako_b_lightb", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_yellow = register("block_wp_namako_b_yellow", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_lime = register("block_wp_namako_b_lime", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_pink = register("block_wp_namako_b_pink", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_gray = register("block_wp_namako_b_gray", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_lightg = register("block_wp_namako_b_lightg", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_cyan = register("block_wp_namako_b_cyan", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_purple = register("block_wp_namako_b_purple", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_blue = register("block_wp_namako_b_blue", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_brown = register("block_wp_namako_b_brown", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_green = register("block_wp_namako_b_green", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_red = register("block_wp_namako_b_red", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WP_NAMAKOB_black = register("block_wp_namako_b_black", (props) -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_black.get(), props), new Item.Properties());
	
	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
