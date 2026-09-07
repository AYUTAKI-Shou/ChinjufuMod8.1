package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Acacia;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Birch;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Cherry;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_DarkOak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Ichoh;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Jungle;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Kaede;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Mangrove;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Oak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Paleoak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Sakura;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Spruce;
import com.ayutaki.chinjufumod.blocks.kamoislab.Kamoi_DirtWall;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KamoiPlaster_Blocks {
	/* 204 = 153 + 3 + (3 * 16) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> KAMOI_dirt_oak = register("block_ka_dirt_oak", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_spru = register("block_ka_dirt_spru", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_bir = register("block_ka_dirt_bir", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_jun = register("block_ka_dirt_jun", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_aca = register("block_ka_dirt_aca", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_doak = register("block_ka_dirt_doak", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_mangrove = register("block_ka_dirt_mangrove", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_cherry = register("block_ka_dirt_cherry", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_paleoak = register("block_ka_dirt_paleoak", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_sakura = register("block_ka_dirt_saku", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_kaede = register("block_ka_dirt_kae", Kamoi_DirtWall::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_dirt_ichoh = register("block_ka_dirt_ich", Kamoi_DirtWall::new, stoneState());


	public static final DeferredBlock<Block> KAMOI_white_oak = register("block_ka_white_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_oak = register("block_ka_orange_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_oak = register("block_ka_magenta_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_oak = register("block_ka_lightb_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_oak = register("block_ka_yellow_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_oak = register("block_ka_lime_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_oak = register("block_ka_pink_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_oak = register("block_ka_gray_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_oak = register("block_ka_lightg_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_oak = register("block_ka_cyan_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_oak = register("block_ka_purple_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_oak = register("block_ka_blue_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_oak = register("block_ka_brown_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_oak = register("block_ka_green_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_oak = register("block_ka_red_o", KamoiPlaster_Oak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_oak = register("block_ka_black_o", KamoiPlaster_Oak::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_spru = register("block_ka_white_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_spru = register("block_ka_orange_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_spru = register("block_ka_magenta_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_spru = register("block_ka_lightb_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_spru = register("block_ka_yellow_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_spru = register("block_ka_lime_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_spru = register("block_ka_pink_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_spru = register("block_ka_gray_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_spru = register("block_ka_lightg_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_spru = register("block_ka_cyan_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_spru = register("block_ka_purple_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_spru = register("block_ka_blue_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_spru = register("block_ka_brown_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_spru = register("block_ka_green_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_spru = register("block_ka_red_s", KamoiPlaster_Spruce::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_spru = register("block_ka_black_s", KamoiPlaster_Spruce::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_bir = register("block_ka_white_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_bir = register("block_ka_orange_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_bir = register("block_ka_magenta_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_bir = register("block_ka_lightb_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_bir = register("block_ka_yellow_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_bir = register("block_ka_lime_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_bir = register("block_ka_pink_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_bir = register("block_ka_gray_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_bir = register("block_ka_lightg_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_bir = register("block_ka_cyan_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_bir = register("block_ka_purple_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_bir = register("block_ka_blue_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_bir = register("block_ka_brown_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_bir = register("block_ka_green_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_bir = register("block_ka_red_b", KamoiPlaster_Birch::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_bir = register("block_ka_black_b", KamoiPlaster_Birch::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_jun = register("block_ka_white_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_jun = register("block_ka_orange_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_jun = register("block_ka_magenta_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_jun = register("block_ka_lightb_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_jun = register("block_ka_yellow_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_jun = register("block_ka_lime_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_jun = register("block_ka_pink_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_jun = register("block_ka_gray_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_jun = register("block_ka_lightg_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_jun = register("block_ka_cyan_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_jun = register("block_ka_purple_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_jun = register("block_ka_blue_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_jun = register("block_ka_brown_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_jun = register("block_ka_green_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_jun = register("block_ka_red_j", KamoiPlaster_Jungle::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_jun = register("block_ka_black_j", KamoiPlaster_Jungle::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_aca = register("block_ka_white_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_aca = register("block_ka_orange_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_aca = register("block_ka_magenta_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_aca = register("block_ka_lightb_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_aca = register("block_ka_yellow_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_aca = register("block_ka_lime_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_aca = register("block_ka_pink_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_aca = register("block_ka_gray_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_aca = register("block_ka_lightg_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_aca = register("block_ka_cyan_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_aca = register("block_ka_purple_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_aca = register("block_ka_blue_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_aca = register("block_ka_brown_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_aca = register("block_ka_green_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_aca = register("block_ka_red_a", KamoiPlaster_Acacia::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_aca = register("block_ka_black_a", KamoiPlaster_Acacia::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_doak = register("block_ka_white_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_doak = register("block_ka_orange_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_doak = register("block_ka_magenta_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_doak = register("block_ka_lightb_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_doak = register("block_ka_yellow_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_doak = register("block_ka_lime_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_doak = register("block_ka_pink_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_doak = register("block_ka_gray_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_doak = register("block_ka_lightg_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_doak = register("block_ka_cyan_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_doak = register("block_ka_purple_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_doak = register("block_ka_blue_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_doak = register("block_ka_brown_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_doak = register("block_ka_green_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_doak = register("block_ka_red_d", KamoiPlaster_DarkOak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_doak = register("block_ka_black_d", KamoiPlaster_DarkOak::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_mangrove = register("block_ka_white_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_mangrove = register("block_ka_orange_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_mangrove = register("block_ka_magenta_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_mangrove = register("block_ka_lightb_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_mangrove = register("block_ka_yellow_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_mangrove = register("block_ka_lime_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_mangrove = register("block_ka_pink_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_mangrove = register("block_ka_gray_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_mangrove = register("block_ka_lightg_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_mangrove = register("block_ka_cyan_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_mangrove = register("block_ka_purple_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_mangrove = register("block_ka_blue_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_mangrove = register("block_ka_brown_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_mangrove = register("block_ka_green_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_mangrove = register("block_ka_red_mangrove", KamoiPlaster_Mangrove::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_mangrove = register("block_ka_black_mangrove", KamoiPlaster_Mangrove::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_cherry = register("block_ka_white_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_cherry = register("block_ka_orange_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_cherry = register("block_ka_magenta_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_cherry = register("block_ka_lightb_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_cherry = register("block_ka_yellow_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_cherry = register("block_ka_lime_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_cherry = register("block_ka_pink_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_cherry = register("block_ka_gray_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_cherry = register("block_ka_lightg_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_cherry = register("block_ka_cyan_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_cherry = register("block_ka_purple_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_cherry = register("block_ka_blue_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_cherry = register("block_ka_brown_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_cherry = register("block_ka_green_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_cherry = register("block_ka_red_cherry", KamoiPlaster_Cherry::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_cherry = register("block_ka_black_cherry", KamoiPlaster_Cherry::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_paleoak = register("block_ka_white_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_paleoak = register("block_ka_orange_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_paleoak = register("block_ka_magenta_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_paleoak = register("block_ka_lightb_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_paleoak = register("block_ka_yellow_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_paleoak = register("block_ka_lime_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_paleoak = register("block_ka_pink_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_paleoak = register("block_ka_gray_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_paleoak = register("block_ka_lightg_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_paleoak = register("block_ka_cyan_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_paleoak = register("block_ka_purple_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_paleoak = register("block_ka_blue_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_paleoak = register("block_ka_brown_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_paleoak = register("block_ka_green_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_paleoak = register("block_ka_red_paleoak", KamoiPlaster_Paleoak::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_paleoak = register("block_ka_black_paleoak", KamoiPlaster_Paleoak::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_sakura = register("block_ka_white_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_sakura = register("block_ka_orange_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_sakura = register("block_ka_magenta_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_sakura = register("block_ka_lightb_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_sakura = register("block_ka_yellow_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_sakura = register("block_ka_lime_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_sakura = register("block_ka_pink_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_sakura = register("block_ka_gray_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_sakura = register("block_ka_lightg_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_sakura = register("block_ka_cyan_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_sakura = register("block_ka_purple_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_sakura = register("block_ka_blue_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_sakura = register("block_ka_brown_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_sakura = register("block_ka_green_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_sakura = register("block_ka_red_saku", KamoiPlaster_Sakura::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_sakura = register("block_ka_black_saku", KamoiPlaster_Sakura::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_kaede = register("block_ka_white_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_kaede = register("block_ka_orange_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_kaede = register("block_ka_magenta_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_kaede = register("block_ka_lightb_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_kaede = register("block_ka_yellow_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_kaede = register("block_ka_lime_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_kaede = register("block_ka_pink_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_kaede = register("block_ka_gray_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_kaede = register("block_ka_lightg_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_kaede = register("block_ka_cyan_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_kaede = register("block_ka_purple_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_kaede = register("block_ka_blue_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_kaede = register("block_ka_brown_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_kaede = register("block_ka_green_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_kaede = register("block_ka_red_kae", KamoiPlaster_Kaede::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_kaede = register("block_ka_black_kae", KamoiPlaster_Kaede::new, stoneState());

	public static final DeferredBlock<Block> KAMOI_white_ichoh = register("block_ka_white_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_orange_ichoh = register("block_ka_orange_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_magenta_ichoh = register("block_ka_magenta_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightb_ichoh = register("block_ka_lightb_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_yellow_ichoh = register("block_ka_yellow_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lime_ichoh = register("block_ka_lime_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_pink_ichoh = register("block_ka_pink_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_gray_ichoh = register("block_ka_gray_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_lightg_ichoh = register("block_ka_lightg_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_cyan_ichoh = register("block_ka_cyan_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_purple_ichoh = register("block_ka_purple_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_blue_ichoh = register("block_ka_blue_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_brown_ichoh = register("block_ka_brown_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_green_ichoh = register("block_ka_green_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_red_ichoh = register("block_ka_red_ich", KamoiPlaster_Ichoh::new, stoneState());
	public static final DeferredBlock<Block> KAMOI_black_ichoh = register("block_ka_black_ich", KamoiPlaster_Ichoh::new, stoneState());

	
	/* Share variables */
	private static Properties stoneState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 4.2F).sound(SoundType.STONE);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
