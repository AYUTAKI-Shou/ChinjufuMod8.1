package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items_WallPanel {
	/* 170 = 158 + (2 * 4) + 4 */
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	public static final RegistryObject<Item> BRICK_GRA = register("block_brick_gra_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_GRA.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICK_DIO = register("block_brick_dio_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_DIO.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICK_AND = register("block_brick_and_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_AND.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKGRA_CH = register("block_brick_gra_ch_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKGRA_CH.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKDIO_CH = register("block_brick_dio_ch_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKDIO_CH.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKAND_CH = register("block_brick_and_ch_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKAND_CH.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKGRA_CR = register("block_brick_gra_cr_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKGRA_CR.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKDIO_CR = register("block_brick_dio_cr_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKDIO_CR.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKAND_CR = register("block_brick_and_cr_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKAND_CR.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKGRA_MOS = register("block_brick_gra_mos_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKGRA_MOS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKDIO_MOS = register("block_brick_dio_mos_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKDIO_MOS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKAND_MOS = register("block_brick_and_mos_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKAND_MOS.get(), new Item.Properties()));

	public static final RegistryObject<Item> BRICKSTAIRS_GRA = register("block_brickstairs_gra_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKSTAIRS_GRA.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKSTAIRS_DIO = register("block_brickstairs_dio_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKSTAIRS_DIO.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICKSTAIRS_AND = register("block_brickstairs_and_c", () -> new Not_Fuel(WallPanel_Blocks.BRICKSTAIRS_AND.get(), new Item.Properties()));

	public static final RegistryObject<Item> BGC_slabhalf = register("block_bgc_slabhalf", () -> new Not_Fuel(WallPanel_Blocks.BGC_slabhalf.get(), new Item.Properties()));
	public static final RegistryObject<Item> BDC_slabhalf = register("block_bdc_slabhalf", () -> new Not_Fuel(WallPanel_Blocks.BDC_slabhalf.get(), new Item.Properties()));
	public static final RegistryObject<Item> BAC_slabhalf = register("block_bac_slabhalf", () -> new Not_Fuel(WallPanel_Blocks.BAC_slabhalf.get(), new Item.Properties()));

	public static final RegistryObject<Item> BRICK_STONE_PIL = register("block_brick_stone_pil_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_STONE_PIL.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICK_GRA_PIL = register("block_brick_gra_pil_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_GRA_PIL.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICK_DIO_PIL = register("block_brick_dio_pil_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_DIO_PIL.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRICK_AND_PIL = register("block_brick_and_pil_c", () -> new Not_Fuel(WallPanel_Blocks.BRICK_AND_PIL.get(), new Item.Properties()));

	public static final RegistryObject<Item> PILLAR_oak = register("block_pillar_oak_c", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_oak.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_spru = register("block_pillar_spru_c", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_spru.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_bir = register("block_pillar_bir_c", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_bir.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_jun = register("block_pillar_jun_c", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_jun.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_aca = register("block_pillar_aca_c", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_aca.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_doak = register("block_pillar_doak_c", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_doak.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_mangrove = register("block_pillar_mangrove", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_mangrove.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLAR_cherry = register("block_pillar_cherry", () -> new Not_Fuel(WallPanel_Blocks.PILLAR_cherry.get(), new Item.Properties()));

	public static final RegistryObject<Item> PILLARSLAB_oak = register("block_kamoi_oak", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_oak.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_spru = register("block_kamoi_spruce", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_spru.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_bir = register("block_kamoi_birch", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_bir.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_jun = register("block_kamoi_jungle", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_jun.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_aca = register("block_kamoi_acacia", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_aca.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_doak = register("block_kamoi_darkoak", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_doak.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_mangrove = register("block_kamoi_mangrove", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_mangrove.get(), new Item.Properties()));
	public static final RegistryObject<Item> PILLARSLAB_cherry = register("block_kamoi_cherry", () -> new Fuel_150(WallPanel_Blocks.PILLARSLAB_cherry.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_LOG_oak = register("block_wp_log_oak", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_oak.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_spru = register("block_wp_log_spru", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_spru.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_bir = register("block_wp_log_bir", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_bir.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_jun = register("block_wp_log_jun", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_jun.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_aca = register("block_wp_log_aca", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_aca.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_doak = register("block_wp_log_doak", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_doak.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_mangrove = register("block_wp_log_mangrove", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_mangrove.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_LOG_cherry = register("block_wp_log_cherry", () -> new Not_Fuel(WallPanel_Blocks.WP_LOG_cherry.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_PLANK_oak = register("block_wp_plank_oak", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_oak.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_spru = register("block_wp_plank_spru", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_spru.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_bir = register("block_wp_plank_bir", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_bir.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_jun = register("block_wp_plank_jun", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_jun.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_aca = register("block_wp_plank_aca", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_aca.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_doak = register("block_wp_plank_doak", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_doak.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_mangrove = register("block_wp_plank_mangrove", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_mangrove.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLANK_cherry = register("block_wp_plank_cherry", () -> new Not_Fuel(WallPanel_Blocks.WP_PLANK_cherry.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_STONE = register("block_wp_stone", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_M = register("block_wp_stone_m", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_M.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_STONE_gra = register("block_wp_stone_gra", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_gra.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_dio = register("block_wp_stone_dio", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_dio.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_and = register("block_wp_stone_and", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_and.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_STONE_B = register("block_wp_stone_b", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_B.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_graB = register("block_wp_stone_grab", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_graB.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_dioB = register("block_wp_stone_diob", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_dioB.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_andB = register("block_wp_stone_andb", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_andB.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_STONE_P = register("block_wp_stone_p", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_P.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_graP = register("block_wp_stone_grap", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_graP.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_dioP = register("block_wp_stone_diop", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_dioP.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_STONE_andP = register("block_wp_stone_andp", () -> new Not_Fuel(WallPanel_Blocks.WP_STONE_andP.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_BRICK = register("block_wp_brick", () -> new Not_Fuel(WallPanel_Blocks.WP_BRICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_SANDSTONE = register("block_wp_sand_stone", () -> new Not_Fuel(WallPanel_Blocks.WP_SANDSTONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_REDSANDSTONE = register("block_wp_redsand_stone", () -> new Not_Fuel(WallPanel_Blocks.WP_REDSANDSTONE.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_DEEPSLATE = register("block_wp_deepslate", () -> new Not_Fuel(WallPanel_Blocks.WP_DEEPSLATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_DEEPSLATE_B = register("block_wp_deepslate_b", () -> new Not_Fuel(WallPanel_Blocks.WP_DEEPSLATE_B.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_TUFF = register("block_wp_tuff", () -> new Not_Fuel(WallPanel_Blocks.WP_TUFF.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_MUDPACK = register("block_wp_mudpacked", () -> new Not_Fuel(WallPanel_Blocks.WP_MUDPACK.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_PRISMA = register("block_wp_prisma", () -> new Not_Fuel(WallPanel_Blocks.WP_PRISMA.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_OBSIDIAN = register("block_wp_obsidian", () -> new Not_Fuel(WallPanel_Blocks.WP_OBSIDIAN.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_CLAY = register("block_wp_clay", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_white = register("block_wp_clay_white", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_white.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_orange = register("block_wp_clay_orange", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_orange.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_magenta = register("block_wp_clay_magenta", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_magenta.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_lightb = register("block_wp_clay_lightb", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_lightb.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_yellow = register("block_wp_clay_yellow", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_yellow.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_lime = register("block_wp_clay_lime", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_lime.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_pink = register("block_wp_clay_pink", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_pink.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_gray = register("block_wp_clay_gray", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_gray.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_lightg = register("block_wp_clay_lightg", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_lightg.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_cyan = register("block_wp_clay_cyan", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_cyan.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_purple = register("block_wp_clay_purple", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_purple.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_blue = register("block_wp_clay_blue", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_blue.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_brown = register("block_wp_clay_brown", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_brown.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_green = register("block_wp_clay_green", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_red = register("block_wp_clay_red", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_CLAY_black = register("block_wp_clay_black", () -> new Not_Fuel(WallPanel_Blocks.WP_CLAY_black.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_GLASS = register("block_wp_glass", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_white = register("block_wp_glass_white", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_white.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_orange = register("block_wp_glass_orange", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_orange.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_magenta = register("block_wp_glass_magenta", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_magenta.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_lightb = register("block_wp_glass_lightb", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_lightb.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_yellow = register("block_wp_glass_yellow", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_yellow.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_lime = register("block_wp_glass_lime", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_lime.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_pink = register("block_wp_glass_pink", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_pink.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_gray = register("block_wp_glass_gray", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_gray.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_lightg = register("block_wp_glass_lightg", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_lightg.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_cyan = register("block_wp_glass_cyan", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_cyan.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_purple = register("block_wp_glass_purple", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_purple.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_blue = register("block_wp_glass_blue", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_blue.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_brown = register("block_wp_glass_brown", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_brown.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_green = register("block_wp_glass_green", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_red = register("block_wp_glass_red", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_GLASS_black = register("block_wp_glass_black", () -> new Not_Fuel(WallPanel_Blocks.WP_GLASS_black.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_NETHE_rack = register("block_wp_netherrack", () -> new Not_Fuel(WallPanel_Blocks.WP_NETHE_rack.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NETHE_b = register("block_wp_netherb", () -> new Not_Fuel(WallPanel_Blocks.WP_NETHE_b.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_QUARTZ = register("block_wp_quartz", () -> new Not_Fuel(WallPanel_Blocks.WP_QUARTZ.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_QUARTZ_PIL = register("block_wp_quartz_pil", () -> new Not_Fuel(WallPanel_Blocks.WP_QUARTZ_PIL.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_ENDSTONE = register("block_wp_endstone", () -> new Not_Fuel(WallPanel_Blocks.WP_ENDSTONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_ENDBRICKS = register("block_wp_endstone_b", () -> new Not_Fuel(WallPanel_Blocks.WP_ENDBRICKS.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_PURPUR = register("block_wp_purpur", () -> new Not_Fuel(WallPanel_Blocks.WP_PURPUR.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PURPUR_PIL = register("block_wp_purpur_pil", () -> new Not_Fuel(WallPanel_Blocks.WP_PURPUR_PIL.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_BAMBOO = register("block_wp_bamboo", () -> new Not_Fuel(WallPanel_Blocks.WP_BAMBOO.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_BAMBOO_Y = register("block_wp_bamboo_y", () -> new Not_Fuel(WallPanel_Blocks.WP_BAMBOO_Y.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_BAMBOO_K = register("block_wp_bamboo_k", () -> new Not_Fuel(WallPanel_Blocks.WP_BAMBOO_K.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_DIRTWALL = register("block_wp_dirtwall", () -> new Not_Fuel(WallPanel_Blocks.WP_DIRTWALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_white = register("block_wp_plaster_white", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_white.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_orange = register("block_wp_plaster_orange", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_orange.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_magenta = register("block_wp_plaster_magenta", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_magenta.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_lightb = register("block_wp_plaster_lightb", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_lightb.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_yellow = register("block_wp_plaster_yellow", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_yellow.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_lime = register("block_wp_plaster_lime", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_lime.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_pink = register("block_wp_plaster_pink", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_pink.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_gray = register("block_wp_plaster_gray", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_gray.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_lightg = register("block_wp_plaster_lightg", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_lightg.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_cyan = register("block_wp_plaster_cyan", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_cyan.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_purple = register("block_wp_plaster_purple", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_purple.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_blue = register("block_wp_plaster_blue", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_blue.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_brown = register("block_wp_plaster_brown", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_brown.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_green = register("block_wp_plaster_green", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_red = register("block_wp_plaster_red", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_PLASTER_black = register("block_wp_plaster_black", () -> new Not_Fuel(WallPanel_Blocks.WP_PLASTER_black.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_NAMAKO_white = register("block_wp_namako_white", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_white.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_orange = register("block_wp_namako_orange", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_orange.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_magenta = register("block_wp_namako_magenta", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_magenta.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_lightb = register("block_wp_namako_lightb", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_lightb.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_yellow = register("block_wp_namako_yellow", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_yellow.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_lime = register("block_wp_namako_lime", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_lime.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_pink = register("block_wp_namako_pink", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_pink.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_gray = register("block_wp_namako_gray", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_gray.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_lightg = register("block_wp_namako_lightg", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_lightg.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_cyan = register("block_wp_namako_cyan", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_cyan.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_purple = register("block_wp_namako_purple", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_purple.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_blue = register("block_wp_namako_blue", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_blue.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_brown = register("block_wp_namako_brown", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_brown.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_green = register("block_wp_namako_green", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_red = register("block_wp_namako_red", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKO_black = register("block_wp_namako_black", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKO_black.get(), new Item.Properties()));

	public static final RegistryObject<Item> WP_NAMAKOB_white = register("block_wp_namako_b_white", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_white.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_orange = register("block_wp_namako_b_orange", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_orange.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_magenta = register("block_wp_namako_b_magenta", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_magenta.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_lightb = register("block_wp_namako_b_lightb", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_lightb.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_yellow = register("block_wp_namako_b_yellow", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_yellow.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_lime = register("block_wp_namako_b_lime", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_lime.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_pink = register("block_wp_namako_b_pink", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_pink.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_gray = register("block_wp_namako_b_gray", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_gray.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_lightg = register("block_wp_namako_b_lightg", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_lightg.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_cyan = register("block_wp_namako_b_cyan", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_cyan.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_purple = register("block_wp_namako_b_purple", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_purple.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_blue = register("block_wp_namako_b_blue", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_blue.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_brown = register("block_wp_namako_b_brown", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_brown.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_green = register("block_wp_namako_b_green", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_green.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_red = register("block_wp_namako_b_red", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_red.get(), new Item.Properties()));
	public static final RegistryObject<Item> WP_NAMAKOB_black = register("block_wp_namako_b_black", () -> new Not_Fuel(WallPanel_Blocks.WP_NAMAKOB_black.get(), new Item.Properties()));
	
	
	///* Register *///
	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}
}
