package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.pantry.CanTea;
import com.ayutaki.chinjufumod.blocks.pantry.Chadutsu;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Box;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_BoxCM;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Empty;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Sack;
import com.ayutaki.chinjufumod.blocks.pantry.Tawara;

import net.minecraft.block.Block;

public class Pantry_Blocks {

	public static Pantry_Empty BOX_H_EMPTY;
	public static Pantry_Empty BOX_H_EMPTY2;
	public static Pantry_Empty BOX_H_EMPTY3;
	public static Pantry_Box BOX_H_APPLE;
	public static Pantry_Box BOX_H_BEEF;
	public static Pantry_Box BOX_H_BEETROOT;
	public static Pantry_Box BOX_H_BREAD;
	public static Pantry_Box BOX_H_CARROT;
	public static Pantry_Box BOX_H_CHICKEN;
	public static Pantry_Box BOX_H_CHORUS;
	public static Pantry_Sack BOX_H_COCO;
	public static Pantry_Box BOX_H_EGG;
	public static Pantry_Box BOX_H_FISH;
	public static Pantry_Sack BOX_H_FLOUR;
	public static Pantry_Box BOX_H_MUTTON;
	public static Pantry_Box BOX_H_PORK;
	public static Pantry_Box BOX_H_POTATO;
	public static Pantry_Box BOX_H_RABBIT;
	public static Pantry_Box BOX_H_SALMON;

	public static Pantry_Sack BOX_H_AZUKI;
	public static Pantry_BoxCM BOX_H_CABBAGE;
	public static Pantry_BoxCM BOX_H_HAKUSAI;
	public static Pantry_BoxCM BOX_H_CITRUS;
	public static Pantry_BoxCM BOX_H_CORN;
	public static Pantry_BoxCM BOX_H_GREENONION;
	public static Pantry_BoxCM BOX_H_GRAPE;
	public static Pantry_BoxCM BOX_H_ONION;
	public static Pantry_BoxCM BOX_H_ORIENTCLAM;
	public static Pantry_Sack BOX_H_RICE;
	public static Pantry_Sack BOX_H_SOY;
	public static Pantry_BoxCM BOX_H_SPINACH;
	public static Pantry_BoxCM BOX_H_SQUID;
	public static Pantry_BoxCM BOX_H_TOMATO;
	public static Pantry_BoxCM BOX_H_CHERRY;
	public static Pantry_BoxCM BOX_H_TAKENOKO;
	public static Pantry_Sack BOX_H_KURI;
	public static Pantry_Sack BOX_H_TGREEN;
	public static Pantry_Sack BOX_H_TRED;
	public static Pantry_Sack BOX_H_BPEPPER;
	public static Pantry_Sack BOX_H_CUMIN;
	public static Pantry_Sack BOX_H_TURMERIC;
	public static Pantry_Sack BOX_H_CHILI;
	
	public static Block CHADUTSU, CANTEA, TAWARA;


	public static void init() {

		BOX_H_EMPTY = new Pantry_Empty("block_boxh_empty");
		BOX_H_EMPTY2 = new Pantry_Empty("block_boxh_empty2");
		BOX_H_EMPTY3 = new Pantry_Empty("block_boxh_empty3");
		BOX_H_APPLE = new Pantry_Box("block_boxh_apple");
		BOX_H_BEEF = new Pantry_Box("block_boxh_beef");
		BOX_H_BEETROOT = new Pantry_Box("block_boxh_beetroot");
		BOX_H_BREAD = new Pantry_Box("block_boxh_bread");
		BOX_H_CARROT = new Pantry_Box("block_boxh_carrot");
		BOX_H_CHICKEN = new Pantry_Box("block_boxh_chicken");
		BOX_H_CHORUS = new Pantry_Box("block_boxh_chorus");
		BOX_H_COCO = new Pantry_Sack("block_boxh_coco");
		BOX_H_EGG = new Pantry_Box("block_boxh_egg");
		BOX_H_FISH = new Pantry_Box("block_boxh_fish");
		BOX_H_FLOUR = new Pantry_Sack("block_boxh_flour");
		BOX_H_MUTTON = new Pantry_Box("block_boxh_mutton");
		BOX_H_PORK = new Pantry_Box("block_boxh_pork");
		BOX_H_POTATO = new Pantry_Box("block_boxh_potato");
		BOX_H_RABBIT = new Pantry_Box("block_boxh_rabbit");
		BOX_H_SALMON = new Pantry_Box("block_boxh_salmon");

		BOX_H_AZUKI = new Pantry_Sack("block_boxh_azuki");
		BOX_H_CABBAGE = new Pantry_BoxCM("block_boxh_cabbage");
		BOX_H_HAKUSAI = new Pantry_BoxCM("block_boxh_hakusai");
		BOX_H_CITRUS = new Pantry_BoxCM("block_boxh_citrus");
		BOX_H_CORN = new Pantry_BoxCM("block_boxh_corn");
		BOX_H_GREENONION = new Pantry_BoxCM("block_boxh_greenonion");
		BOX_H_GRAPE = new Pantry_BoxCM("block_boxh_grape");
		BOX_H_ONION = new Pantry_BoxCM("block_boxh_onion");
		BOX_H_ORIENTCLAM = new Pantry_BoxCM("block_boxh_hamaguri");
		BOX_H_RICE = new Pantry_Sack("block_boxh_rice");
		BOX_H_SOY = new Pantry_Sack("block_boxh_soy");
		BOX_H_SPINACH = new Pantry_BoxCM("block_boxh_spinach");
		BOX_H_SQUID = new Pantry_BoxCM("block_boxh_squid");
		BOX_H_TOMATO = new Pantry_BoxCM("block_boxh_tomato");
		BOX_H_CHERRY = new Pantry_BoxCM("block_boxh_cherry");
		BOX_H_TAKENOKO = new Pantry_BoxCM("block_boxh_takenoko");
		BOX_H_KURI = new Pantry_Sack("block_boxh_chestnut");
		BOX_H_TGREEN = new Pantry_Sack("block_boxh_tgreen");
		BOX_H_TRED = new Pantry_Sack("block_boxh_tred");

		BOX_H_BPEPPER = new Pantry_Sack("block_boxh_bpepper");
		BOX_H_CUMIN = new Pantry_Sack("block_boxh_cumin");
		BOX_H_TURMERIC = new Pantry_Sack("block_boxh_turmeric");
		BOX_H_CHILI = new Pantry_Sack("block_boxh_chili");
		
		CHADUTSU = new Chadutsu("block_tea_chadutsu");
		CANTEA = new CanTea("block_tea_can");
		TAWARA = new Tawara("block_tawara_cm");
	}


	public static void register() {

		registerBlock(BOX_H_EMPTY);
		registerBlock(BOX_H_EMPTY2);
		registerBlock(BOX_H_EMPTY3);
		registerBlock(BOX_H_APPLE);
		registerBlock(BOX_H_BEEF);
		registerBlock(BOX_H_BEETROOT);
		registerBlock(BOX_H_BREAD);
		registerBlock(BOX_H_CARROT);
		registerBlock(BOX_H_CHICKEN);
		registerBlock(BOX_H_CHORUS);
		registerBlock(BOX_H_COCO);
		registerBlock(BOX_H_EGG);
		registerBlock(BOX_H_FISH);
		registerBlock(BOX_H_FLOUR);
		registerBlock(BOX_H_MUTTON);
		registerBlock(BOX_H_PORK);
		registerBlock(BOX_H_POTATO);
		registerBlock(BOX_H_RABBIT);
		registerBlock(BOX_H_SALMON);

		registerBlock(BOX_H_AZUKI);
		registerBlock(BOX_H_CABBAGE);
		registerBlock(BOX_H_HAKUSAI);
		registerBlock(BOX_H_CITRUS);
		registerBlock(BOX_H_CORN);
		registerBlock(BOX_H_GREENONION);
		registerBlock(BOX_H_GRAPE);
		registerBlock(BOX_H_ONION);
		registerBlock(BOX_H_ORIENTCLAM);
		registerBlock(BOX_H_RICE);
		registerBlock(BOX_H_SOY);
		registerBlock(BOX_H_SPINACH);
		registerBlock(BOX_H_SQUID);
		registerBlock(BOX_H_TOMATO);
		registerBlock(BOX_H_CHERRY);
		registerBlock(BOX_H_TAKENOKO);
		registerBlock(BOX_H_KURI);
		registerBlock(BOX_H_TGREEN);
		registerBlock(BOX_H_TRED);

		registerBlock(BOX_H_BPEPPER);
		registerBlock(BOX_H_CUMIN);
		registerBlock(BOX_H_TURMERIC);
		registerBlock(BOX_H_CHILI);
		
		registerBlock(CHADUTSU);
		registerBlock(CANTEA);
		registerBlock(TAWARA);
	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}
}
