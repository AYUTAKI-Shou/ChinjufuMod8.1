package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Acacia;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Birch;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_DarkOak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Ichoh;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Jungle;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Kaede;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Oak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Sakura;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Spruce;
import com.ayutaki.chinjufumod.blocks.kamoislab.Kamoi_DirtWall;

import net.minecraft.block.Block;

public class KamoiPlaster_Blocks {

	public static Block KAMOI_dirt_oak, KAMOI_dirt_spru, KAMOI_dirt_bir, KAMOI_dirt_jun, KAMOI_dirt_aca, KAMOI_dirt_doak,
								KAMOI_dirt_sakura, KAMOI_dirt_kaede, KAMOI_dirt_ichoh;

	public static Block KAMOI_white_oak, KAMOI_orange_oak, KAMOI_magenta_oak, KAMOI_lightb_oak,
								KAMOI_yellow_oak, KAMOI_lime_oak, KAMOI_pink_oak, KAMOI_gray_oak,
								KAMOI_lightg_oak, KAMOI_cyan_oak, KAMOI_purple_oak, KAMOI_blue_oak,
								KAMOI_brown_oak, KAMOI_green_oak, KAMOI_red_oak, KAMOI_black_oak;

	public static Block KAMOI_white_spru, KAMOI_orange_spru, KAMOI_magenta_spru, KAMOI_lightb_spru,
								KAMOI_yellow_spru, KAMOI_lime_spru, KAMOI_pink_spru, KAMOI_gray_spru,
								KAMOI_lightg_spru, KAMOI_cyan_spru, KAMOI_purple_spru, KAMOI_blue_spru,
								KAMOI_brown_spru, KAMOI_green_spru, KAMOI_red_spru, KAMOI_black_spru;

	public static Block KAMOI_white_bir, KAMOI_orange_bir, KAMOI_magenta_bir, KAMOI_lightb_bir,
								KAMOI_yellow_bir, KAMOI_lime_bir, KAMOI_pink_bir, KAMOI_gray_bir,
								KAMOI_lightg_bir, KAMOI_cyan_bir, KAMOI_purple_bir, KAMOI_blue_bir,
								KAMOI_brown_bir, KAMOI_green_bir, KAMOI_red_bir, KAMOI_black_bir;

	public static Block KAMOI_white_jun, KAMOI_orange_jun, KAMOI_magenta_jun, KAMOI_lightb_jun,
								KAMOI_yellow_jun, KAMOI_lime_jun, KAMOI_pink_jun, KAMOI_gray_jun,
								KAMOI_lightg_jun, KAMOI_cyan_jun, KAMOI_purple_jun, KAMOI_blue_jun,
								KAMOI_brown_jun, KAMOI_green_jun, KAMOI_red_jun, KAMOI_black_jun;

	public static Block KAMOI_white_aca, KAMOI_orange_aca, KAMOI_magenta_aca, KAMOI_lightb_aca,
								KAMOI_yellow_aca, KAMOI_lime_aca, KAMOI_pink_aca, KAMOI_gray_aca,
								KAMOI_lightg_aca, KAMOI_cyan_aca, KAMOI_purple_aca, KAMOI_blue_aca,
								KAMOI_brown_aca, KAMOI_green_aca, KAMOI_red_aca, KAMOI_black_aca;

	public static Block KAMOI_white_doak, KAMOI_orange_doak, KAMOI_magenta_doak, KAMOI_lightb_doak,
								KAMOI_yellow_doak, KAMOI_lime_doak, KAMOI_pink_doak, KAMOI_gray_doak,
								KAMOI_lightg_doak, KAMOI_cyan_doak, KAMOI_purple_doak, KAMOI_blue_doak,
								KAMOI_brown_doak, KAMOI_green_doak, KAMOI_red_doak, KAMOI_black_doak;

	public static Block KAMOI_white_sakura, KAMOI_orange_sakura, KAMOI_magenta_sakura, KAMOI_lightb_sakura,
								KAMOI_yellow_sakura, KAMOI_lime_sakura, KAMOI_pink_sakura, KAMOI_gray_sakura,
								KAMOI_lightg_sakura, KAMOI_cyan_sakura, KAMOI_purple_sakura, KAMOI_blue_sakura,
								KAMOI_brown_sakura, KAMOI_green_sakura, KAMOI_red_sakura, KAMOI_black_sakura;

	public static Block KAMOI_white_kaede, KAMOI_orange_kaede, KAMOI_magenta_kaede, KAMOI_lightb_kaede,
								KAMOI_yellow_kaede, KAMOI_lime_kaede, KAMOI_pink_kaede, KAMOI_gray_kaede,
								KAMOI_lightg_kaede, KAMOI_cyan_kaede, KAMOI_purple_kaede, KAMOI_blue_kaede,
								KAMOI_brown_kaede, KAMOI_green_kaede, KAMOI_red_kaede, KAMOI_black_kaede;

	public static Block KAMOI_white_ichoh, KAMOI_orange_ichoh, KAMOI_magenta_ichoh, KAMOI_lightb_ichoh,
								KAMOI_yellow_ichoh, KAMOI_lime_ichoh, KAMOI_pink_ichoh, KAMOI_gray_ichoh,
								KAMOI_lightg_ichoh, KAMOI_cyan_ichoh, KAMOI_purple_ichoh, KAMOI_blue_ichoh,
								KAMOI_brown_ichoh, KAMOI_green_ichoh, KAMOI_red_ichoh, KAMOI_black_ichoh;


	public static void init() {

		KAMOI_dirt_oak = new Kamoi_DirtWall("block_ka_dirt_oak");
		KAMOI_dirt_spru = new Kamoi_DirtWall("block_ka_dirt_spru");
		KAMOI_dirt_bir = new Kamoi_DirtWall("block_ka_dirt_bir");
		KAMOI_dirt_jun = new Kamoi_DirtWall("block_ka_dirt_jun");
		KAMOI_dirt_aca = new Kamoi_DirtWall("block_ka_dirt_aca");
		KAMOI_dirt_doak = new Kamoi_DirtWall("block_ka_dirt_doak");
		KAMOI_dirt_sakura = new Kamoi_DirtWall("block_ka_dirt_saku");
		KAMOI_dirt_kaede = new Kamoi_DirtWall("block_ka_dirt_kae");
		KAMOI_dirt_ichoh = new Kamoi_DirtWall("block_ka_dirt_ich");

		/** しっくい-柱 **/
		KAMOI_white_oak = new KamoiPlaster_Oak("block_ka_white_o");
		KAMOI_orange_oak = new KamoiPlaster_Oak("block_ka_orange_o");
		KAMOI_magenta_oak = new KamoiPlaster_Oak("block_ka_magenta_o");
		KAMOI_lightb_oak = new KamoiPlaster_Oak("block_ka_lightb_o");
		KAMOI_yellow_oak = new KamoiPlaster_Oak("block_ka_yellow_o");
		KAMOI_lime_oak = new KamoiPlaster_Oak("block_ka_lime_o");
		KAMOI_pink_oak = new KamoiPlaster_Oak("block_ka_pink_o");
		KAMOI_gray_oak = new KamoiPlaster_Oak("block_ka_gray_o");
		KAMOI_lightg_oak = new KamoiPlaster_Oak("block_ka_lightg_o");
		KAMOI_cyan_oak = new KamoiPlaster_Oak("block_ka_cyan_o");
		KAMOI_purple_oak = new KamoiPlaster_Oak("block_ka_purple_o");
		KAMOI_blue_oak = new KamoiPlaster_Oak("block_ka_blue_o");
		KAMOI_brown_oak = new KamoiPlaster_Oak("block_ka_brown_o");
		KAMOI_green_oak = new KamoiPlaster_Oak("block_ka_green_o");
		KAMOI_red_oak = new KamoiPlaster_Oak("block_ka_red_o");
		KAMOI_black_oak = new KamoiPlaster_Oak("block_ka_black_o");

		KAMOI_white_spru = new KamoiPlaster_Spruce("block_ka_white_s");
		KAMOI_orange_spru = new KamoiPlaster_Spruce("block_ka_orange_s");
		KAMOI_magenta_spru = new KamoiPlaster_Spruce("block_ka_magenta_s");
		KAMOI_lightb_spru = new KamoiPlaster_Spruce("block_ka_lightb_s");
		KAMOI_yellow_spru = new KamoiPlaster_Spruce("block_ka_yellow_s");
		KAMOI_lime_spru = new KamoiPlaster_Spruce("block_ka_lime_s");
		KAMOI_pink_spru = new KamoiPlaster_Spruce("block_ka_pink_s");
		KAMOI_gray_spru = new KamoiPlaster_Spruce("block_ka_gray_s");
		KAMOI_lightg_spru = new KamoiPlaster_Spruce("block_ka_lightg_s");
		KAMOI_cyan_spru = new KamoiPlaster_Spruce("block_ka_cyan_s");
		KAMOI_purple_spru = new KamoiPlaster_Spruce("block_ka_purple_s");
		KAMOI_blue_spru = new KamoiPlaster_Spruce("block_ka_blue_s");
		KAMOI_brown_spru = new KamoiPlaster_Spruce("block_ka_brown_s");
		KAMOI_green_spru = new KamoiPlaster_Spruce("block_ka_green_s");
		KAMOI_red_spru = new KamoiPlaster_Spruce("block_ka_red_s");
		KAMOI_black_spru = new KamoiPlaster_Spruce("block_ka_black_s");

		KAMOI_white_bir = new KamoiPlaster_Birch("block_ka_white_b");
		KAMOI_orange_bir = new KamoiPlaster_Birch("block_ka_orange_b");
		KAMOI_magenta_bir = new KamoiPlaster_Birch("block_ka_magenta_b");
		KAMOI_lightb_bir = new KamoiPlaster_Birch("block_ka_lightb_b");
		KAMOI_yellow_bir = new KamoiPlaster_Birch("block_ka_yellow_b");
		KAMOI_lime_bir = new KamoiPlaster_Birch("block_ka_lime_b");
		KAMOI_pink_bir = new KamoiPlaster_Birch("block_ka_pink_b");
		KAMOI_gray_bir = new KamoiPlaster_Birch("block_ka_gray_b");
		KAMOI_lightg_bir = new KamoiPlaster_Birch("block_ka_lightg_b");
		KAMOI_cyan_bir = new KamoiPlaster_Birch("block_ka_cyan_b");
		KAMOI_purple_bir = new KamoiPlaster_Birch("block_ka_purple_b");
		KAMOI_blue_bir = new KamoiPlaster_Birch("block_ka_blue_b");
		KAMOI_brown_bir = new KamoiPlaster_Birch("block_ka_brown_b");
		KAMOI_green_bir = new KamoiPlaster_Birch("block_ka_green_b");
		KAMOI_red_bir = new KamoiPlaster_Birch("block_ka_red_b");
		KAMOI_black_bir = new KamoiPlaster_Birch("block_ka_black_b");

		KAMOI_white_jun = new KamoiPlaster_Jungle("block_ka_white_j");
		KAMOI_orange_jun = new KamoiPlaster_Jungle("block_ka_orange_j");
		KAMOI_magenta_jun = new KamoiPlaster_Jungle("block_ka_magenta_j");
		KAMOI_lightb_jun = new KamoiPlaster_Jungle("block_ka_lightb_j");
		KAMOI_yellow_jun = new KamoiPlaster_Jungle("block_ka_yellow_j");
		KAMOI_lime_jun = new KamoiPlaster_Jungle("block_ka_lime_j");
		KAMOI_pink_jun = new KamoiPlaster_Jungle("block_ka_pink_j");
		KAMOI_gray_jun = new KamoiPlaster_Jungle("block_ka_gray_j");
		KAMOI_lightg_jun = new KamoiPlaster_Jungle("block_ka_lightg_j");
		KAMOI_cyan_jun = new KamoiPlaster_Jungle("block_ka_cyan_j");
		KAMOI_purple_jun = new KamoiPlaster_Jungle("block_ka_purple_j");
		KAMOI_blue_jun = new KamoiPlaster_Jungle("block_ka_blue_j");
		KAMOI_brown_jun = new KamoiPlaster_Jungle("block_ka_brown_j");
		KAMOI_green_jun = new KamoiPlaster_Jungle("block_ka_green_j");
		KAMOI_red_jun = new KamoiPlaster_Jungle("block_ka_red_j");
		KAMOI_black_jun = new KamoiPlaster_Jungle("block_ka_black_j");

		KAMOI_white_aca = new KamoiPlaster_Acacia("block_ka_white_a");
		KAMOI_orange_aca = new KamoiPlaster_Acacia("block_ka_orange_a");
		KAMOI_magenta_aca = new KamoiPlaster_Acacia("block_ka_magenta_a");
		KAMOI_lightb_aca = new KamoiPlaster_Acacia("block_ka_lightb_a");
		KAMOI_yellow_aca = new KamoiPlaster_Acacia("block_ka_yellow_a");
		KAMOI_lime_aca = new KamoiPlaster_Acacia("block_ka_lime_a");
		KAMOI_pink_aca = new KamoiPlaster_Acacia("block_ka_pink_a");
		KAMOI_gray_aca = new KamoiPlaster_Acacia("block_ka_gray_a");
		KAMOI_lightg_aca = new KamoiPlaster_Acacia("block_ka_lightg_a");
		KAMOI_cyan_aca = new KamoiPlaster_Acacia("block_ka_cyan_a");
		KAMOI_purple_aca = new KamoiPlaster_Acacia("block_ka_purple_a");
		KAMOI_blue_aca = new KamoiPlaster_Acacia("block_ka_blue_a");
		KAMOI_brown_aca = new KamoiPlaster_Acacia("block_ka_brown_a");
		KAMOI_green_aca = new KamoiPlaster_Acacia("block_ka_green_a");
		KAMOI_red_aca = new KamoiPlaster_Acacia("block_ka_red_a");
		KAMOI_black_aca = new KamoiPlaster_Acacia("block_ka_black_a");

		KAMOI_white_doak = new KamoiPlaster_DarkOak("block_ka_white_d");
		KAMOI_orange_doak = new KamoiPlaster_DarkOak("block_ka_orange_d");
		KAMOI_magenta_doak = new KamoiPlaster_DarkOak("block_ka_magenta_d");
		KAMOI_lightb_doak = new KamoiPlaster_DarkOak("block_ka_lightb_d");
		KAMOI_yellow_doak = new KamoiPlaster_DarkOak("block_ka_yellow_d");
		KAMOI_lime_doak = new KamoiPlaster_DarkOak("block_ka_lime_d");
		KAMOI_pink_doak = new KamoiPlaster_DarkOak("block_ka_pink_d");
		KAMOI_gray_doak = new KamoiPlaster_DarkOak("block_ka_gray_d");
		KAMOI_lightg_doak = new KamoiPlaster_DarkOak("block_ka_lightg_d");
		KAMOI_cyan_doak = new KamoiPlaster_DarkOak("block_ka_cyan_d");
		KAMOI_purple_doak = new KamoiPlaster_DarkOak("block_ka_purple_d");
		KAMOI_blue_doak = new KamoiPlaster_DarkOak("block_ka_blue_d");
		KAMOI_brown_doak = new KamoiPlaster_DarkOak("block_ka_brown_d");
		KAMOI_green_doak = new KamoiPlaster_DarkOak("block_ka_green_d");
		KAMOI_red_doak = new KamoiPlaster_DarkOak("block_ka_red_d");
		KAMOI_black_doak = new KamoiPlaster_DarkOak("block_ka_black_d");

		KAMOI_white_sakura = new KamoiPlaster_Sakura("block_ka_white_saku");
		KAMOI_orange_sakura = new KamoiPlaster_Sakura("block_ka_orange_saku");
		KAMOI_magenta_sakura = new KamoiPlaster_Sakura("block_ka_magenta_saku");
		KAMOI_lightb_sakura = new KamoiPlaster_Sakura("block_ka_lightb_saku");
		KAMOI_yellow_sakura = new KamoiPlaster_Sakura("block_ka_yellow_saku");
		KAMOI_lime_sakura = new KamoiPlaster_Sakura("block_ka_lime_saku");
		KAMOI_pink_sakura = new KamoiPlaster_Sakura("block_ka_pink_saku");
		KAMOI_gray_sakura = new KamoiPlaster_Sakura("block_ka_gray_saku");
		KAMOI_lightg_sakura = new KamoiPlaster_Sakura("block_ka_lightg_saku");
		KAMOI_cyan_sakura = new KamoiPlaster_Sakura("block_ka_cyan_saku");
		KAMOI_purple_sakura = new KamoiPlaster_Sakura("block_ka_purple_saku");
		KAMOI_blue_sakura = new KamoiPlaster_Sakura("block_ka_blue_saku");
		KAMOI_brown_sakura = new KamoiPlaster_Sakura("block_ka_brown_saku");
		KAMOI_green_sakura = new KamoiPlaster_Sakura("block_ka_green_saku");
		KAMOI_red_sakura = new KamoiPlaster_Sakura("block_ka_red_saku");
		KAMOI_black_sakura = new KamoiPlaster_Sakura("block_ka_black_saku");

		KAMOI_white_kaede = new KamoiPlaster_Kaede("block_ka_white_kae");
		KAMOI_orange_kaede = new KamoiPlaster_Kaede("block_ka_orange_kae");
		KAMOI_magenta_kaede = new KamoiPlaster_Kaede("block_ka_magenta_kae");
		KAMOI_lightb_kaede = new KamoiPlaster_Kaede("block_ka_lightb_kae");
		KAMOI_yellow_kaede = new KamoiPlaster_Kaede("block_ka_yellow_kae");
		KAMOI_lime_kaede = new KamoiPlaster_Kaede("block_ka_lime_kae");
		KAMOI_pink_kaede = new KamoiPlaster_Kaede("block_ka_pink_kae");
		KAMOI_gray_kaede = new KamoiPlaster_Kaede("block_ka_gray_kae");
		KAMOI_lightg_kaede = new KamoiPlaster_Kaede("block_ka_lightg_kae");
		KAMOI_cyan_kaede = new KamoiPlaster_Kaede("block_ka_cyan_kae");
		KAMOI_purple_kaede = new KamoiPlaster_Kaede("block_ka_purple_kae");
		KAMOI_blue_kaede = new KamoiPlaster_Kaede("block_ka_blue_kae");
		KAMOI_brown_kaede = new KamoiPlaster_Kaede("block_ka_brown_kae");
		KAMOI_green_kaede = new KamoiPlaster_Kaede("block_ka_green_kae");
		KAMOI_red_kaede = new KamoiPlaster_Kaede("block_ka_red_kae");
		KAMOI_black_kaede = new KamoiPlaster_Kaede("block_ka_black_kae");

		KAMOI_white_ichoh = new KamoiPlaster_Ichoh("block_ka_white_ich");
		KAMOI_orange_ichoh = new KamoiPlaster_Ichoh("block_ka_orange_ich");
		KAMOI_magenta_ichoh = new KamoiPlaster_Ichoh("block_ka_magenta_ich");
		KAMOI_lightb_ichoh = new KamoiPlaster_Ichoh("block_ka_lightb_ich");
		KAMOI_yellow_ichoh = new KamoiPlaster_Ichoh("block_ka_yellow_ich");
		KAMOI_lime_ichoh = new KamoiPlaster_Ichoh("block_ka_lime_ich");
		KAMOI_pink_ichoh = new KamoiPlaster_Ichoh("block_ka_pink_ich");
		KAMOI_gray_ichoh = new KamoiPlaster_Ichoh("block_ka_gray_ich");
		KAMOI_lightg_ichoh = new KamoiPlaster_Ichoh("block_ka_lightg_ich");
		KAMOI_cyan_ichoh = new KamoiPlaster_Ichoh("block_ka_cyan_ich");
		KAMOI_purple_ichoh = new KamoiPlaster_Ichoh("block_ka_purple_ich");
		KAMOI_blue_ichoh = new KamoiPlaster_Ichoh("block_ka_blue_ich");
		KAMOI_brown_ichoh = new KamoiPlaster_Ichoh("block_ka_brown_ich");
		KAMOI_green_ichoh = new KamoiPlaster_Ichoh("block_ka_green_ich");
		KAMOI_red_ichoh = new KamoiPlaster_Ichoh("block_ka_red_ich");
		KAMOI_black_ichoh = new KamoiPlaster_Ichoh("block_ka_black_ich");
	}


	public static void register() {

		registerBlock(KAMOI_dirt_oak);
		registerBlock(KAMOI_dirt_spru);
		registerBlock(KAMOI_dirt_bir);
		registerBlock(KAMOI_dirt_jun);
		registerBlock(KAMOI_dirt_aca);
		registerBlock(KAMOI_dirt_doak);
		registerBlock(KAMOI_dirt_sakura);
		registerBlock(KAMOI_dirt_kaede);
		registerBlock(KAMOI_dirt_ichoh);

		registerBlock(KAMOI_white_oak);
		registerBlock(KAMOI_orange_oak);
		registerBlock(KAMOI_magenta_oak);
		registerBlock(KAMOI_lightb_oak);
		registerBlock(KAMOI_yellow_oak);
		registerBlock(KAMOI_lime_oak);
		registerBlock(KAMOI_pink_oak);
		registerBlock(KAMOI_gray_oak);
		registerBlock(KAMOI_lightg_oak);
		registerBlock(KAMOI_cyan_oak);
		registerBlock(KAMOI_purple_oak);
		registerBlock(KAMOI_blue_oak);
		registerBlock(KAMOI_brown_oak);
		registerBlock(KAMOI_green_oak);
		registerBlock(KAMOI_red_oak);
		registerBlock(KAMOI_black_oak);

		registerBlock(KAMOI_white_spru);
		registerBlock(KAMOI_orange_spru);
		registerBlock(KAMOI_magenta_spru);
		registerBlock(KAMOI_lightb_spru);
		registerBlock(KAMOI_yellow_spru);
		registerBlock(KAMOI_lime_spru);
		registerBlock(KAMOI_pink_spru);
		registerBlock(KAMOI_gray_spru);
		registerBlock(KAMOI_lightg_spru);
		registerBlock(KAMOI_cyan_spru);
		registerBlock(KAMOI_purple_spru);
		registerBlock(KAMOI_blue_spru);
		registerBlock(KAMOI_brown_spru);
		registerBlock(KAMOI_green_spru);
		registerBlock(KAMOI_red_spru);
		registerBlock(KAMOI_black_spru);

		registerBlock(KAMOI_white_bir);
		registerBlock(KAMOI_orange_bir);
		registerBlock(KAMOI_magenta_bir);
		registerBlock(KAMOI_lightb_bir);
		registerBlock(KAMOI_yellow_bir);
		registerBlock(KAMOI_lime_bir);
		registerBlock(KAMOI_pink_bir);
		registerBlock(KAMOI_gray_bir);
		registerBlock(KAMOI_lightg_bir);
		registerBlock(KAMOI_cyan_bir);
		registerBlock(KAMOI_purple_bir);
		registerBlock(KAMOI_blue_bir);
		registerBlock(KAMOI_brown_bir);
		registerBlock(KAMOI_green_bir);
		registerBlock(KAMOI_red_bir);
		registerBlock(KAMOI_black_bir);

		registerBlock(KAMOI_white_jun);
		registerBlock(KAMOI_orange_jun);
		registerBlock(KAMOI_magenta_jun);
		registerBlock(KAMOI_lightb_jun);
		registerBlock(KAMOI_yellow_jun);
		registerBlock(KAMOI_lime_jun);
		registerBlock(KAMOI_pink_jun);
		registerBlock(KAMOI_gray_jun);
		registerBlock(KAMOI_lightg_jun);
		registerBlock(KAMOI_cyan_jun);
		registerBlock(KAMOI_purple_jun);
		registerBlock(KAMOI_blue_jun);
		registerBlock(KAMOI_brown_jun);
		registerBlock(KAMOI_green_jun);
		registerBlock(KAMOI_red_jun);
		registerBlock(KAMOI_black_jun);

		registerBlock(KAMOI_white_aca);
		registerBlock(KAMOI_orange_aca);
		registerBlock(KAMOI_magenta_aca);
		registerBlock(KAMOI_lightb_aca);
		registerBlock(KAMOI_yellow_aca);
		registerBlock(KAMOI_lime_aca);
		registerBlock(KAMOI_pink_aca);
		registerBlock(KAMOI_gray_aca);
		registerBlock(KAMOI_lightg_aca);
		registerBlock(KAMOI_cyan_aca);
		registerBlock(KAMOI_purple_aca);
		registerBlock(KAMOI_blue_aca);
		registerBlock(KAMOI_brown_aca);
		registerBlock(KAMOI_green_aca);
		registerBlock(KAMOI_red_aca);
		registerBlock(KAMOI_black_aca);

		registerBlock(KAMOI_white_doak);
		registerBlock(KAMOI_orange_doak);
		registerBlock(KAMOI_magenta_doak);
		registerBlock(KAMOI_lightb_doak);
		registerBlock(KAMOI_yellow_doak);
		registerBlock(KAMOI_lime_doak);
		registerBlock(KAMOI_pink_doak);
		registerBlock(KAMOI_gray_doak);
		registerBlock(KAMOI_lightg_doak);
		registerBlock(KAMOI_cyan_doak);
		registerBlock(KAMOI_purple_doak);
		registerBlock(KAMOI_blue_doak);
		registerBlock(KAMOI_brown_doak);
		registerBlock(KAMOI_green_doak);
		registerBlock(KAMOI_red_doak);
		registerBlock(KAMOI_black_doak);

		registerBlock(KAMOI_white_sakura);
		registerBlock(KAMOI_orange_sakura);
		registerBlock(KAMOI_magenta_sakura);
		registerBlock(KAMOI_lightb_sakura);
		registerBlock(KAMOI_yellow_sakura);
		registerBlock(KAMOI_lime_sakura);
		registerBlock(KAMOI_pink_sakura);
		registerBlock(KAMOI_gray_sakura);
		registerBlock(KAMOI_lightg_sakura);
		registerBlock(KAMOI_cyan_sakura);
		registerBlock(KAMOI_purple_sakura);
		registerBlock(KAMOI_blue_sakura);
		registerBlock(KAMOI_brown_sakura);
		registerBlock(KAMOI_green_sakura);
		registerBlock(KAMOI_red_sakura);
		registerBlock(KAMOI_black_sakura);

		registerBlock(KAMOI_white_kaede);
		registerBlock(KAMOI_orange_kaede);
		registerBlock(KAMOI_magenta_kaede);
		registerBlock(KAMOI_lightb_kaede);
		registerBlock(KAMOI_yellow_kaede);
		registerBlock(KAMOI_lime_kaede);
		registerBlock(KAMOI_pink_kaede);
		registerBlock(KAMOI_gray_kaede);
		registerBlock(KAMOI_lightg_kaede);
		registerBlock(KAMOI_cyan_kaede);
		registerBlock(KAMOI_purple_kaede);
		registerBlock(KAMOI_blue_kaede);
		registerBlock(KAMOI_brown_kaede);
		registerBlock(KAMOI_green_kaede);
		registerBlock(KAMOI_red_kaede);
		registerBlock(KAMOI_black_kaede);

		registerBlock(KAMOI_white_ichoh);
		registerBlock(KAMOI_orange_ichoh);
		registerBlock(KAMOI_magenta_ichoh);
		registerBlock(KAMOI_lightb_ichoh);
		registerBlock(KAMOI_yellow_ichoh);
		registerBlock(KAMOI_lime_ichoh);
		registerBlock(KAMOI_pink_ichoh);
		registerBlock(KAMOI_gray_ichoh);
		registerBlock(KAMOI_lightg_ichoh);
		registerBlock(KAMOI_cyan_ichoh);
		registerBlock(KAMOI_purple_ichoh);
		registerBlock(KAMOI_blue_ichoh);
		registerBlock(KAMOI_brown_ichoh);
		registerBlock(KAMOI_green_ichoh);
		registerBlock(KAMOI_red_ichoh);
		registerBlock(KAMOI_black_ichoh);
	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}
}
