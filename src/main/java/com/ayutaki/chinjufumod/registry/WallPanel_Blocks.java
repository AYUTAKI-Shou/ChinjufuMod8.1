package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.kamoi.Base_Kamoi;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Acacia;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Birch;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Cherry;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_DarkOak;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Jungle;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Mangrove;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Oak;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Paleoak;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Spruce;
import com.ayutaki.chinjufumod.blocks.wallpane.BrickBlock_CM;
import com.ayutaki.chinjufumod.blocks.wallpane.BrickSlabWater_CM;
import com.ayutaki.chinjufumod.blocks.wallpane.BrickStairs_CM;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Clay;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Glass;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Namako;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Namako_B;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Plaster;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Simple;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage2;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage3Stone;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage3Wood;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage4;
import com.ayutaki.chinjufumod.blocks.wood.WoodPillar_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodStairs_CM;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class WallPanel_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> BRICK_GRA = register("block_brick_gra_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICK_DIO = register("block_brick_dio_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICK_AND = register("block_brick_and_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKGRA_CH = register("block_brick_gra_ch_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKDIO_CH = register("block_brick_dio_ch_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKAND_CH = register("block_brick_and_ch_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKGRA_CR = register("block_brick_gra_cr_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKDIO_CR = register("block_brick_dio_cr_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKAND_CR = register("block_brick_and_cr_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKGRA_MOS = register("block_brick_gra_mos_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKDIO_MOS = register("block_brick_dio_mos_c", BrickBlock_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICKAND_MOS = register("block_brick_and_mos_c", BrickBlock_CM::new, stoneState());

	public static final DeferredBlock<Block> BRICKSTAIRS_GRA = register("block_brickstairs_gra_c", (props) -> new BrickStairs_CM(BRICK_GRA.get().defaultBlockState(), props), brickStairs());
	public static final DeferredBlock<Block> BRICKSTAIRS_DIO = register("block_brickstairs_dio_c", (props) -> new BrickStairs_CM(BRICK_DIO.get().defaultBlockState(), props), brickStairs());
	public static final DeferredBlock<Block> BRICKSTAIRS_AND = register("block_brickstairs_and_c", (props) -> new BrickStairs_CM(BRICK_AND.get().defaultBlockState(), props), brickStairs());

	public static final DeferredBlock<Block> BGC_slabhalf = register("block_bgc_slabhalf", BrickSlabWater_CM::new, brickSlab());
	public static final DeferredBlock<Block> BDC_slabhalf = register("block_bdc_slabhalf", BrickSlabWater_CM::new, brickSlab());
	public static final DeferredBlock<Block> BAC_slabhalf = register("block_bac_slabhalf", BrickSlabWater_CM::new, brickSlab());

	public static final DeferredBlock<Block> BRICK_STONE_PIL = register("block_brick_stone_pil_c", WoodPillar_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICK_GRA_PIL = register("block_brick_gra_pil_c", WoodPillar_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICK_DIO_PIL = register("block_brick_dio_pil_c", WoodPillar_CM::new, stoneState());
	public static final DeferredBlock<Block> BRICK_AND_PIL = register("block_brick_and_pil_c", WoodPillar_CM::new, stoneState());

	public static final DeferredBlock<Block> PILLAR_oak = register("block_pillar_oak_c", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_spru = register("block_pillar_spru_c", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_bir = register("block_pillar_bir_c", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_jun = register("block_pillar_jun_c", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_aca = register("block_pillar_aca_c", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_doak = register("block_pillar_doak_c", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_mangrove = register("block_pillar_mangrove", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_cherry = register("block_pillar_cherry", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_paleoak = register("block_pillar_paleoak", WoodPillar_CM::new, woodState());

	public static final DeferredBlock<Block> PILLARSLAB_oak = register("block_kamoi_oak", Kamoi_Oak::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_spru = register("block_kamoi_spruce", Kamoi_Spruce::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_bir = register("block_kamoi_birch", Kamoi_Birch::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_jun = register("block_kamoi_jungle", Kamoi_Jungle::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_aca = register("block_kamoi_acacia", Kamoi_Acacia::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_doak = register("block_kamoi_darkoak", Kamoi_DarkOak::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_mangrove = register("block_kamoi_mangrove", Kamoi_Mangrove::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_cherry = register("block_kamoi_cherry", Kamoi_Cherry::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_paleoak = register("block_kamoi_paleoak", Kamoi_Paleoak::new, kamoiState());
	
	public static final DeferredBlock<Block> WP_LOG_oak = register("block_wp_log_oak", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_spru = register("block_wp_log_spru", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_bir = register("block_wp_log_bir", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_jun = register("block_wp_log_jun", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_aca = register("block_wp_log_aca", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_doak = register("block_wp_log_doak", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_mangrove = register("block_wp_log_mangrove", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_cherry = register("block_wp_log_cherry", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_LOG_paleoak = register("block_wp_log_paleoak", WallPane_Stage3Wood::new, woodPane());
	
	public static final DeferredBlock<Block> WP_PLANK_oak = register("block_wp_plank_oak", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_spru = register("block_wp_plank_spru", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_bir = register("block_wp_plank_bir", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_jun = register("block_wp_plank_jun", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_aca = register("block_wp_plank_aca", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_doak = register("block_wp_plank_doak", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_mangrove = register("block_wp_plank_mangrove", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_cherry = register("block_wp_plank_cherry", WallPane_Stage4::new, woodPane());
	public static final DeferredBlock<Block> WP_PLANK_paleoak = register("block_wp_plank_paleoak", WallPane_Stage4::new, woodPane());
	
	public static final DeferredBlock<Block> WP_STONE = register("block_wp_stone", WallPane_Simple::new, baseState().strength(1.0F, 6.0F));
	public static final DeferredBlock<Block> WP_STONE_M = register("block_wp_stone_m", WallPane_Stage2::new, stonePane());

	public static final DeferredBlock<Block> WP_STONE_gra = register("block_wp_stone_gra", WallPane_Stage2::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_dio = register("block_wp_stone_dio", WallPane_Stage2::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_and = register("block_wp_stone_and", WallPane_Stage2::new, stonePane());

	public static final DeferredBlock<Block> WP_STONE_B = register("block_wp_stone_b", WallPane_Stage4::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_graB = register("block_wp_stone_grab", WallPane_Stage4::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_dioB = register("block_wp_stone_diob", WallPane_Stage4::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_andB = register("block_wp_stone_andb", WallPane_Stage4::new, stonePane());

	public static final DeferredBlock<Block> WP_STONE_P = register("block_wp_stone_p", WallPane_Stage3Stone::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_graP = register("block_wp_stone_grap", WallPane_Stage3Stone::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_dioP = register("block_wp_stone_diop", WallPane_Stage3Stone::new, stonePane());
	public static final DeferredBlock<Block> WP_STONE_andP = register("block_wp_stone_andp", WallPane_Stage3Stone::new, stonePane());

	public static final DeferredBlock<Block> WP_BRICK = register("block_wp_brick", WallPane_Simple::new, baseState().strength(1.0F, 6.0F));
	public static final DeferredBlock<Block> WP_SANDSTONE = register("block_wp_sand_stone", WallPane_Stage3Stone::new, stonePane());
	public static final DeferredBlock<Block> WP_REDSANDSTONE = register("block_wp_redsand_stone", WallPane_Stage3Stone::new, stonePane());

	public static final DeferredBlock<Block> WP_DEEPSLATE = register("block_wp_deepslate", WallPane_Stage4::new, stonePane());
	public static final DeferredBlock<Block> WP_DEEPSLATE_B = register("block_wp_deepslate_b", WallPane_Stage4::new, stonePane());
	public static final DeferredBlock<Block> WP_TUFF = register("block_wp_tuff", WallPane_Stage3Stone::new, stonePane());
	public static final DeferredBlock<Block> WP_TUFF_B = register("block_wp_tuff_b", WallPane_Stage2::new, stonePane());
	public static final DeferredBlock<Block> WP_MUDPACK = register("block_wp_mudpacked", WallPane_Stage2::new, stonePane());
	public static final DeferredBlock<Block> WP_RESIN = register("block_wp_resin", WallPane_Stage3Stone::new, stonePane());
	
	public static final DeferredBlock<Block> WP_PRISMA = register("block_wp_prisma", WallPane_Stage3Stone::new, stonePane());
	public static final DeferredBlock<Block> WP_OBSIDIAN = register("block_wp_obsidian", WallPane_Simple::new, baseState().strength(1.0F, 1200.0F));

	public static final DeferredBlock<Block> WP_CLAY = register("block_wp_clay", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_white = register("block_wp_clay_white", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_orange = register("block_wp_clay_orange", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_magenta = register("block_wp_clay_magenta", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_lightb = register("block_wp_clay_lightb", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_yellow = register("block_wp_clay_yellow", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_lime = register("block_wp_clay_lime", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_pink = register("block_wp_clay_pink", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_gray = register("block_wp_clay_gray", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_lightg = register("block_wp_clay_lightg", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_cyan = register("block_wp_clay_cyan", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_purple = register("block_wp_clay_purple", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_blue = register("block_wp_clay_blue", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_brown = register("block_wp_clay_brown", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_green = register("block_wp_clay_green", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_red = register("block_wp_clay_red", WallPane_Clay::new, stonePane());
	public static final DeferredBlock<Block> WP_CLAY_black = register("block_wp_clay_black", WallPane_Clay::new, stonePane());

	public static final DeferredBlock<Block> WP_GLASS = register("block_wp_glass", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_white = register("block_wp_glass_white", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_orange = register("block_wp_glass_orange", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_magenta = register("block_wp_glass_magenta", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_lightb = register("block_wp_glass_lightb", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_yellow = register("block_wp_glass_yellow", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_lime = register("block_wp_glass_lime", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_pink = register("block_wp_glass_pink", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_gray = register("block_wp_glass_gray", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_lightg = register("block_wp_glass_lightg", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_cyan = register("block_wp_glass_cyan", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_purple = register("block_wp_glass_purple", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_blue = register("block_wp_glass_blue", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_brown = register("block_wp_glass_brown", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_green = register("block_wp_glass_green", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_red = register("block_wp_glass_red", WallPane_Glass::new, glassPane());
	public static final DeferredBlock<Block> WP_GLASS_black = register("block_wp_glass_black", WallPane_Glass::new, glassPane());

	public static final DeferredBlock<Block> WP_NETHE_rack = register("block_wp_netherrack", WallPane_Simple::new, baseState().strength(1.0F, 0.4F));
	public static final DeferredBlock<Block> WP_NETHE_b = register("block_wp_netherb", WallPane_Stage2::new, stonePane());
	public static final DeferredBlock<Block> WP_QUARTZ = register("block_wp_quartz", WallPane_Stage2::new, stonePane());
	public static final DeferredBlock<Block> WP_QUARTZ_PIL = register("block_wp_quartz_pil", WallPane_Stage3Stone::new, stonePane());

	public static final DeferredBlock<Block> WP_ENDSTONE = register("block_wp_endstone", WallPane_Simple::new, baseState().strength(1.0F, 9.0F));
	public static final DeferredBlock<Block> WP_ENDBRICKS = register("block_wp_endstone_b", WallPane_Simple::new, baseState().strength(1.0F, 9.0F));

	public static final DeferredBlock<Block> WP_PURPUR = register("block_wp_purpur", WallPane_Simple::new, baseState().strength(1.0F, 6.0F));
	public static final DeferredBlock<Block> WP_PURPUR_PIL = register("block_wp_purpur_pil", WallPane_Stage3Stone::new, stonePane());

	public static final DeferredBlock<Block> WP_BAMBOO = register("block_wp_bamboo", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_BAMBOO_Y = register("block_wp_bamboo_y", WallPane_Stage3Wood::new, woodPane());
	public static final DeferredBlock<Block> WP_BAMBOO_K = register("block_wp_bamboo_k", WallPane_Stage3Wood::new, woodPane());

	public static final DeferredBlock<Block> WP_DIRTWALL = register("block_wp_dirtwall", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_white = register("block_wp_plaster_white", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_orange = register("block_wp_plaster_orange", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_magenta = register("block_wp_plaster_magenta", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_lightb = register("block_wp_plaster_lightb", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_yellow = register("block_wp_plaster_yellow", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_lime = register("block_wp_plaster_lime", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_pink = register("block_wp_plaster_pink", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_gray = register("block_wp_plaster_gray", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_lightg = register("block_wp_plaster_lightg", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_cyan = register("block_wp_plaster_cyan", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_purple = register("block_wp_plaster_purple", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_blue = register("block_wp_plaster_blue", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_brown = register("block_wp_plaster_brown", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_green = register("block_wp_plaster_green", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_red = register("block_wp_plaster_red", WallPane_Plaster::new, stonePane());
	public static final DeferredBlock<Block> WP_PLASTER_black = register("block_wp_plaster_black", WallPane_Plaster::new, stonePane());

	public static final DeferredBlock<Block> WP_NAMAKO_white = register("block_wp_namako_white", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_orange = register("block_wp_namako_orange", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_magenta = register("block_wp_namako_magenta", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_lightb = register("block_wp_namako_lightb", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_yellow = register("block_wp_namako_yellow", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_lime = register("block_wp_namako_lime", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_pink = register("block_wp_namako_pink", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_gray = register("block_wp_namako_gray", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_lightg = register("block_wp_namako_lightg", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_cyan = register("block_wp_namako_cyan", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_purple = register("block_wp_namako_purple", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_blue = register("block_wp_namako_blue", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_brown = register("block_wp_namako_brown", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_green = register("block_wp_namako_green", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_red = register("block_wp_namako_red", WallPane_Namako::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKO_black = register("block_wp_namako_black", WallPane_Namako::new, stonePane());

	public static final DeferredBlock<Block> WP_NAMAKOB_white = register("block_wp_namako_b_white", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_orange = register("block_wp_namako_b_orange", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_magenta = register("block_wp_namako_b_magenta", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_lightb = register("block_wp_namako_b_lightb", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_yellow = register("block_wp_namako_b_yellow", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_lime = register("block_wp_namako_b_lime", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_pink = register("block_wp_namako_b_pink", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_gray = register("block_wp_namako_b_gray", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_lightg = register("block_wp_namako_b_lightg", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_cyan = register("block_wp_namako_b_cyan", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_purple = register("block_wp_namako_b_purple", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_blue = register("block_wp_namako_b_blue", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_brown = register("block_wp_namako_b_brown", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_green = register("block_wp_namako_b_green", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_red = register("block_wp_namako_b_red", WallPane_Namako_B::new, stonePane());
	public static final DeferredBlock<Block> WP_NAMAKOB_black = register("block_wp_namako_b_black", WallPane_Namako_B::new, stonePane());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static boolean neverSlab(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE)? true : false;
	}

	private static Boolean neverEntitySlab(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityStairs(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(WoodStairs_CM.HALF) == Half.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityKamoi(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(Base_Kamoi.STAGE_1_4) != 4)? (boolean)false : (boolean)true;
	}
	
	private static Properties baseState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.STONE)
				.noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntity).isSuffocating(WallPanel_Blocks::never);
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	private static Properties kamoiState() {
		return woodState().noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntityKamoi).isSuffocating(WallPanel_Blocks::never);
	}
	
	private static Properties stoneState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE);
	}
	
	private static Properties brickSlab() {
		return stoneState().noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntitySlab).isSuffocating(WallPanel_Blocks::neverSlab);
	}

	private static Properties brickStairs() {
		return stoneState().noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntityStairs).isSuffocating(WallPanel_Blocks::never);
	}
	
	private static Properties woodPane() {
		return woodState().noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntity).isSuffocating(WallPanel_Blocks::never);
	}

	private static Properties stonePane() {
		return stoneState().noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntity).isSuffocating(WallPanel_Blocks::never);
	}

	private static Properties glassPane() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 0.3F).sound(SoundType.GLASS)
				.noOcclusion().isValidSpawn(WallPanel_Blocks::neverEntity).isSuffocating(WallPanel_Blocks::never).isViewBlocking(WallPanel_Blocks::never);
	}


	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
