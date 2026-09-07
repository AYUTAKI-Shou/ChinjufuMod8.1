package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Chair_Blocks;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.JPChair_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TintColors_CM {

	/* TintIndex によるブロック色 0は使わずに1以降から */
	public static void registerBlockColors() {
		Minecraft mClient = Minecraft.getInstance();
		/** 20 Water=1, 35 CornSoup=2 **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? BiomeColors.getWaterColor(worldIn, pos) : ((tint == 2)? 16441700 : -1); },
				Garden_Blocks.CHOUZUBACHI,
				Garden_Blocks.CHOUZUBACHI_gra,
				Garden_Blocks.CHOUZUBACHI_dio,
				Garden_Blocks.CHOUZUBACHI_and,
				Garden_Blocks.SHISHIODOSHI,
				Garden_Blocks.SHISHIODOSHI2,
				Kitchen_Blocks.KIT_SINK,
				Wood_Blocks.SUIDEN,

				Hakkou_Blocks.MIZUOKE,
				Hakkou_Blocks.MIZUOKE_full,
				Hakkou_Blocks.HAKUSAI_TARU1,
				Hakkou_Blocks.HAKUSAI_TARU2,
				
				Dish_Blocks.KEIRYO_CUP,
				Dish_Blocks.CURRY,
				Dish_Blocks.CURRY_C,
				Dish_Blocks.CURRY_T,
				Dish_Blocks.CURRYSET,
				Dish_Blocks.CURRYSET_C,
				Dish_Blocks.CURRYSET_T,
				Dish_Blocks.ZUNDOU_NCURRY,
				Dish_Blocks.ZUNDOU_NCURRY_C,
				Dish_Blocks.ZUNDOU_NCURRY_T,
				Dish_Blocks.KETTLE_full,
				Dish_Blocks.ZUNDOU_MIZU,
				Dish_Blocks.ZUNDOU_SHIO,
				Dish_Blocks.ZUNDOU_FISH,
				Dish_Blocks.ZUNDOU_UDON,
				Dish_Blocks.ZUNDOU_PASTA,
				Dish_Blocks.ZUNDOU_RAMEN,
				Dish_Blocks.ZUNDOU_RSOUP_nama,
				
				Dish_Blocks.NABETORI_nama,
				Dish_Blocks.NABEMISO_nama,
				Dish_Blocks.NABEGOHAN_nama,
				Dish_Blocks.NABEGOHANKURI_nama,
				Dish_Blocks.NABESEKIHAN_nama,
				Dish_Blocks.NABESHIO_nama,
				Dish_Blocks.NABENIMAME_nama,
				Dish_Blocks.NABEAZUKI_nama, /** 7.4.1 **/
				Dish_Blocks.NABEANKO_nama, /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_nama, /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_green, /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_red, /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_cacao, /** 7.4.1 **/
				Dish_Blocks.KURI_NABE_nama,
			
				Dish_Blocks.NABECORN_nama,
				Dish_Blocks.NABECORN,
				Dish_Blocks.CORNSOUP,
				Dish_Blocks.EGGBURGSET );
		
		/** 21 Raw_Sake **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 12509680 : -1; },
				Hakkou_Blocks.NAMASAKEGLASS );
		
		/** 22 Sake **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 9223890 : -1; },
				Hakkou_Blocks.SAKEGLASS );
		
		/** 23 Aged_Sake **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 11164250 : -1; },
				Hakkou_Blocks.JUKUSAKEGLASS );
		
		/** 24 Cider **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 15127945 : -1; },
				Hakkou_Blocks.CIDERGLASS );
		
		/** 25 Aged_Cider **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 14588001 : -1; },
				Hakkou_Blocks.JUKUCIDERGLASS );
		
		/** 26 Wine **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 8398655 : -1; },
				Hakkou_Blocks.WINEGLASS );
		
		/** 27 Aged_Wine **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 5715258 : -1; },
				Hakkou_Blocks.JUKUWINEGLASS );
		
		/** 28 Mead **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 13821640 : -1; },
				Hakkou_Blocks.MEADGLASS );
		
		/** 29 Aged_Mead **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 13808770 : -1; },
				Hakkou_Blocks.JUKUMEADGLASS );
		
		/** 30 Green_Tea=1, 32 MisoSoup=2 **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 16775010 : ((tint == 2)? 15772230 : -1); },
				Dish_Blocks.KYUSU, 
				Dish_Blocks.JPTEACUP,
				Dish_Blocks.JPTEASET,
				Dish_Blocks.SUSHISET_salmon,
				Dish_Blocks.SUSHISET_fish,
				Dish_Blocks.SUSHISET_beef,
				Dish_Blocks.SUSHISET_tamago,
				Dish_Blocks.SUSHISET_4shoku,
				
				Dish_Blocks.NABETORI,
				Dish_Blocks.NABEMISO,
				Dish_Blocks.TONSUITORI,
				Dish_Blocks.MISOSOUP,
				
				Dish_Blocks.TAMAGOYAKITEI,
				Dish_Blocks.YAKIZAKANATEI,
				Dish_Blocks.YAKIJYAKETEI,
				Dish_Blocks.TAMAGOYAKITEI_TAKE,
				Dish_Blocks.YAKIZAKANATEI_TAKE,
				Dish_Blocks.YAKIJYAKETEI_TAKE,
				Dish_Blocks.TAMAGOYAKITEI_KURI,
				Dish_Blocks.YAKIZAKANATEI_KURI,
				Dish_Blocks.YAKIJYAKETEI_KURI,
				Dish_Blocks.TAMAGOYAKITEI_SEKI, /** 7.4.1 **/
				Dish_Blocks.YAKIZAKANATEI_SEKI, /** 7.4.1 **/
				Dish_Blocks.YAKIJYAKETEI_SEKI /** 7.4.1 **/ );
		
		/** 31 Black_Tea **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 14493736 : -1; },
				Dish_Blocks.TEAPOT, 
				Dish_Blocks.TEACUP,
				Dish_Blocks.TEASET );

		/** 33 Aku **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 3297410 : -1; },
				Dish_Blocks.ZUNDOU_AKU,
				Dish_Blocks.ZUNDOU_ORIITO );

		/** 20 Water=1, 34 AMAZAKE=2 **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? BiomeColors.getWaterColor(worldIn, pos) : ((tint == 2)? 16443100 : -1); },
				Hakkou_Blocks.NABEAMAZAKE_nama,
				Hakkou_Blocks.NABEAMAZAKE,
				Hakkou_Blocks.AMAZAKEGLASS );
		
		/** 40 ENDEN=1, 41 ENDEN=2 **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 6587090 : ((tint == 2)? 8560880 : -1); },
				Crop_Blocks.ENDEN );
		
		/** 42 KAINASHI=1 **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> { return (tint == 1)? 15790315 : -1; },
				Crop_Blocks.KAINASHI );
		
		/* BiomeColors.class 設置場所の色を拾う BiomeColors.getFoliageColor(worldIn, pos) */
		/* Biome.class 気温と降雨量から色指定 FoliageColors.get(Temperature, Downfall) 1.0F以上はクラッシュする */
		/** 1 Oak **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColors.get(0.7F, 0.6F) : -1; },
				Garden_Blocks.BONSAI_oak,
				Garden_Blocks.KANYOU,
				Garden_Blocks.IKEGAKILONG,
				Garden_Blocks.IKEGAKI );
		
		/** 2 Spruce **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColors.getSpruce() : -1; },
				Garden_Blocks.BONSAI_spru,
				Garden_Blocks.KANYOU_spruce,
				Garden_Blocks.IKEGAKILONG_spruce,
				Garden_Blocks.IKEGAKI_spruce );
		
		/** 3 Birch **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColors.getBirch() : -1; },
				Garden_Blocks.BONSAI_bir,
				Garden_Blocks.KANYOU_birch,
				Garden_Blocks.IKEGAKILONG_birch,
				Garden_Blocks.IKEGAKI_birch );
		
		/** 4 Jungle **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColors.get(0.9F, 0.9F) : -1; }, //緑
				Garden_Blocks.BONSAI_jun,
				Garden_Blocks.KANYOU_jungle,
				Garden_Blocks.IKEGAKILONG_jungle,
				Garden_Blocks.IKEGAKI_jungle );
		
		/** 5 Acacia **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColors.get(0.9F, 0.0F) : -1; },
				Garden_Blocks.BONSAI_aca,
				Garden_Blocks.KANYOU_acacia,
				Garden_Blocks.IKEGAKILONG_acacia,
				Garden_Blocks.IKEGAKI_acacia );
		
		/** 6 DarkOak **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColors.get(0.7F, 0.8F) : -1; }, //黒
				Garden_Blocks.BONSAI_doak,
				Garden_Blocks.KANYOU_darkoak,
				Garden_Blocks.IKEGAKILONG_darkoak,
				Garden_Blocks.IKEGAKI_darkoak );
		
		/** 7 KAEDE ---> Delete **/
		
		/** 8 Autumn_Oak (170,115,50)11170610 **/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? 11170610 : -1; },
				Garden_Blocks.BONSAI_kare,
				Garden_Blocks.KANYOU_kare,
				Garden_Blocks.IKEGAKILONG_kare,
				Garden_Blocks.IKEGAKI_kare,
				Wood_Blocks.OAKKARE_leaf,
				Wood_Blocks.OCHIBA_carpet );
		
		/** Reizou 1=200,200,200 2=225,240,255**/
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? 13158600 : ((tint == 2)? 14807295 : -1); },
				Kitchen_Blocks.KIT_REIZOU,
				Kitchen_Blocks.KIT_REIZOU_TOP );
		
		/* 200, 100 */
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? 13158600 : ((tint == 2)? 6579300 : -1); },
				Chinjufu_Blocks.EMPTY_BOX,
				Chinjufu_Blocks.AMMOBOX,
				Chinjufu_Blocks.BAUXITE_BOX,
				
				Pantry_Blocks.BOX_H_EMPTY,
				Pantry_Blocks.BOX_H_EMPTY2,
				Pantry_Blocks.BOX_H_EMPTY3,
				Pantry_Blocks.BOX_H_APPLE,
				Pantry_Blocks.BOX_H_BEEF,
				Pantry_Blocks.BOX_H_BEETROOT,
				Pantry_Blocks.BOX_H_BREAD,
				Pantry_Blocks.BOX_H_CARROT,
				Pantry_Blocks.BOX_H_CHICKEN,
				Pantry_Blocks.BOX_H_CHORUS,
				Pantry_Blocks.BOX_H_COCO,
				Pantry_Blocks.BOX_H_COD,
				Pantry_Blocks.BOX_H_EGG,
				Pantry_Blocks.BOX_H_FISH,
				Pantry_Blocks.BOX_H_FLOUR,
				Pantry_Blocks.BOX_H_MUTTON,
				Pantry_Blocks.BOX_H_PORK,
				Pantry_Blocks.BOX_H_POTATO,
				Pantry_Blocks.BOX_H_RABBIT,
				Pantry_Blocks.BOX_H_SALMON,
				Pantry_Blocks.BOX_H_SWBERRY,

				Pantry_Blocks.BOX_H_AZUKI, /** 7.4.1 **/
				Pantry_Blocks.BOX_H_CABBAGE,
				Pantry_Blocks.BOX_H_HAKUSAI,
				Pantry_Blocks.BOX_H_CITRUS,
				Pantry_Blocks.BOX_H_CORN,
				Pantry_Blocks.BOX_H_GREENONION,
				Pantry_Blocks.BOX_H_GRAPE,
				Pantry_Blocks.BOX_H_ONION,
				Pantry_Blocks.BOX_H_ORIENTCLAM,
				Pantry_Blocks.BOX_H_RICE,
				Pantry_Blocks.BOX_H_SOY,
				Pantry_Blocks.BOX_H_SPINACH,
				Pantry_Blocks.BOX_H_SQUID,
				Pantry_Blocks.BOX_H_TOMATO,
				Pantry_Blocks.BOX_H_CHERRY,
				Pantry_Blocks.BOX_H_TAKENOKO,
				Pantry_Blocks.BOX_H_KURI,
				Pantry_Blocks.BOX_H_TGREEN,
				Pantry_Blocks.BOX_H_TRED,
				Pantry_Blocks.BOX_H_BPEPPER,
				Pantry_Blocks.BOX_H_CUMIN,
				Pantry_Blocks.BOX_H_TURMERIC,
				Pantry_Blocks.BOX_H_CHILI,
				Dish_Blocks.TENGUSA_WASH /** 8.1 **/);
		
		/* 235 15461355 */
		mClient.getBlockColors().register((state, worldIn, pos, tint) -> {
			return (tint == 1)? 15461355 : -1; },
				Chair_Blocks.SOFA_white,
				Chair_Blocks.SOFA_orange,
				Chair_Blocks.SOFA_magenta,
				Chair_Blocks.SOFA_lightb,
				Chair_Blocks.SOFA_yellow,
				Chair_Blocks.SOFA_lime,
				Chair_Blocks.SOFA_pink,
				Chair_Blocks.SOFA_gray,
				Chair_Blocks.SOFA_lightg,
				Chair_Blocks.SOFA_cyan,
				Chair_Blocks.SOFA_purple,
				Chair_Blocks.SOFA_blue,
				Chair_Blocks.SOFA_brown,
				Chair_Blocks.SOFA_green,
				Chair_Blocks.SOFA_red,
				Chair_Blocks.SOFA_black,
				
				JPChair_Blocks.ZABUTON_white,
				JPChair_Blocks.ZABUTON_orange,
				JPChair_Blocks.ZABUTON_magenta,
				JPChair_Blocks.ZABUTON_lightb,
				JPChair_Blocks.ZABUTON_yellow,
				JPChair_Blocks.ZABUTON_lime,
				JPChair_Blocks.ZABUTON_pink,
				JPChair_Blocks.ZABUTON_gray,
				JPChair_Blocks.ZABUTON_lightg,
				JPChair_Blocks.ZABUTON_cyan,
				JPChair_Blocks.ZABUTON_purple,
				JPChair_Blocks.ZABUTON_blue,
				JPChair_Blocks.ZABUTON_brown,
				JPChair_Blocks.ZABUTON_green,
				JPChair_Blocks.ZABUTON_red,
				JPChair_Blocks.ZABUTON_black,
				JPChair_Blocks.WARAZABUTON,
				JPChair_Blocks.ZAISU_white,
				JPChair_Blocks.ZAISU_orange,
				JPChair_Blocks.ZAISU_magenta,
				JPChair_Blocks.ZAISU_lightb,
				JPChair_Blocks.ZAISU_yellow,
				JPChair_Blocks.ZAISU_lime,
				JPChair_Blocks.ZAISU_pink,
				JPChair_Blocks.ZAISU_gray,
				JPChair_Blocks.ZAISU_lightg,
				JPChair_Blocks.ZAISU_cyan,
				JPChair_Blocks.ZAISU_purple,
				JPChair_Blocks.ZAISU_blue,
				JPChair_Blocks.ZAISU_brown,
				JPChair_Blocks.ZAISU_green,
				JPChair_Blocks.ZAISU_red,
				JPChair_Blocks.ZAISU_black );
	}

	
	/* TintIndex によるアイテム色 */
	public static void registerItemColors() {
		Minecraft mClient = Minecraft.getInstance();
		/** 20 Water waterColor(4159204) from Biome=1, 35 CornSoup=2 **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? 4159204 : ((tint == 2)? 16441700 : -1); },
				Items_Seasonal.SUIDEN,
				Items_Seasonal.KURI_NABE,
				
				Items_Teatime.MIZUOKE_full,
				Items_Teatime.HAKUSAI_TARU1,
				Items_Teatime.HAKUSAI_TARU2,

				Items_Teatime.KEIRYO_CUP_full,
				Items_Teatime.CURRY,
				Items_Teatime.CURRY_C,
				Items_Teatime.CURRY_T,
				Items_Teatime.CURRYSET,
				Items_Teatime.CURRYSET_C,
				Items_Teatime.CURRYSET_T,
				Items_Teatime.KETTLE_full,
				Items_Teatime.KETTLE_boil,
				Items_Teatime.ZUNDOU_MIZU,
				Items_Teatime.ZUNDOU_SHIO,
				Items_Teatime.ZUNDOU_NCURRY,
				Items_Teatime.ZUNDOU_NCURRY_C,
				Items_Teatime.ZUNDOU_NCURRY_T,
				Items_Teatime.ZUNDOU_RSOUP_nama,
				
				Items_Teatime.NABETORI_nama,
				Items_Teatime.NABEMISO_nama,
				Items_Teatime.NABEGOHAN_nama,
				Items_Teatime.NABEGOHANKURI_nama,
				Items_Teatime.NABESEKIHAN_nama,
				Items_Teatime.NABESHIO_nama,
				Items_Teatime.NABENIMAME_nama,
				Items_Teatime.NABEAZUKI_nama, /** 7.4.1 **/
				Items_Teatime.NABEANKO_nama, /** 7.4.1 **/
				Items_Teatime.NABEPUDDING_nama, /** 7.4.1 **/
				Items_Teatime.NABEPUDDING_green, /** 7.4.1 **/
				Items_Teatime.NABEPUDDING_red, /** 7.4.1 **/
				Items_Teatime.NABEPUDDING_cacao, /** 7.4.1 **/
				
				Items_Teatime.NABECORN_nama,
				Items_Teatime.NABECORN,
				Items_Teatime.CORNSOUP,
				Items_Teatime.EGGBURGSET );
		
		/** 42 KANSUI 63,98,168 **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 4154024 : -1; },
				Items_Teatime.KANSUI );
		
		/** 21 Raw_Sake **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 12509680 : -1; },
				Items_Teatime.NAMASAKEGLASS );
		
		/** 22 Sake **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 9223890 : -1; },
				Items_Teatime.SAKEGLASS );
		
		/** 23 Aged_Sake **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 11164250 : -1; },
				Items_Teatime.JUKUSAKEGLASS );
		
		/** 24 Cider **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 15127945 : -1; },
				Items_Teatime.CIDERGLASS );
		
		/** 25 Aged_Cider **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 14588001 : -1; },
				Items_Teatime.JUKUCIDERGLASS );
		
		/** 26 Wine **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 8398655 : -1; },
				Items_Teatime.WINEGLASS );
		
		/** 27 Aged_Wine **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 5715258 : -1; },
				Items_Teatime.JUKUWINEGLASS );
		
		/** 28 Mead **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 13821640 : -1; },
				Items_Teatime.MEADGLASS );
		
		/** 29 Aged_Mead **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 13808770 : -1; },
				Items_Teatime.JUKUMEADGLASS );
		
		/** 30 Green_Tea=1, 32 MisoSoup=2 **/
		mClient.getItemColors().register((stack, tint) -> { 
			return (tint == 1)? 16775010 : ((tint == 2)? 15772230 : -1); },
				Items_Teatime.KYUSU,
				Items_Teatime.JPTEACUP,
				Items_Teatime.JPTEASET,
				Items_Teatime.SUSHISET_salmon,
				Items_Teatime.SUSHISET_fish,
				Items_Teatime.SUSHISET_beef,
				Items_Teatime.SUSHISET_tamago,
				Items_Teatime.SUSHISET_4shoku,
				
				Items_Teatime.NABETORI,
				Items_Teatime.NABEMISO,
				Items_Teatime.TONSUITORI,
				Items_Teatime.MISOSOUP,
				
				Items_Teatime.TAMAGOYAKITEI,
				Items_Teatime.YAKIZAKANATEI,
				Items_Teatime.YAKIJYAKETEI,
				Items_Teatime.TAMAGOYAKITEI_TAKE,
				Items_Teatime.YAKIZAKANATEI_TAKE,
				Items_Teatime.YAKIJYAKETEI_TAKE,
				Items_Teatime.TAMAGOYAKITEI_KURI,
				Items_Teatime.YAKIZAKANATEI_KURI,
				Items_Teatime.YAKIJYAKETEI_KURI,
				Items_Teatime.TAMAGOYAKITEI_SEKI, /** 7.4.1 **/
				Items_Teatime.YAKIZAKANATEI_SEKI, /** 7.4.1 **/
				Items_Teatime.YAKIJYAKETEI_SEKI /** 7.4.1 **/ );
		
		/** 31 Black_Tea **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 14493736 : -1; },
				Items_Teatime.TEAPOT,
				Items_Teatime.TEACUP,
				Items_Teatime.TEASET );

		/** 33 Aku **/
		mClient.getItemColors().register((stack, tint) -> { return (tint == 1)? 3297410 : -1; },
				Items_Seasonal.ZUNDOU_AKU );

		/** 20 Water=1, 34 AMAZAKE=2 **/
		mClient.getItemColors().register((stack, tint) -> { 
			return (tint == 1)? 4159204 : ((tint == 2)? 16443100 : -1); },
				Items_Teatime.NABEAMAZAKE_nama,
				Items_Teatime.NABEAMAZAKE,
				Items_Teatime.AMAZAKEGLASS );
		
		/** 40 ENDEN=1, 41 ENDEN=2 **/
		mClient.getItemColors().register((stack, tint) -> { 
			return (tint == 1)? 6587090 : ((tint == 2)? 8560880 : -1); },
				Items_Teatime.ENDEN );
		
		
		/** 1 Oak **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? FoliageColors.get(0.7F, 0.6F) : -1; },
				Items_Wadeco.BONSAI_oak,
				Items_Wadeco.KANYOU,
				Items_Wadeco.IKEGAKILONG,
				Items_Wadeco.IKEGAKI );
		
		/** 2 Spruce **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? FoliageColors.getSpruce() : -1; },
				Items_Wadeco.BONSAI_spru,
				Items_Wadeco.KANYOU_spruce,
				Items_Wadeco.IKEGAKILONG_spruce,
				Items_Wadeco.IKEGAKI_spruce );

		/** 3 Birch **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? FoliageColors.getBirch() : -1; },
				Items_Wadeco.BONSAI_bir,
				Items_Wadeco.KANYOU_birch,
				Items_Wadeco.IKEGAKILONG_birch,
				Items_Wadeco.IKEGAKI_birch );
		
		/** 4 Jungle **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? FoliageColors.get(0.9F, 0.9F) : -1; }, //緑
				Items_Wadeco.BONSAI_jun,
				Items_Wadeco.KANYOU_jungle,
				Items_Wadeco.IKEGAKILONG_jungle,
				Items_Wadeco.IKEGAKI_jungle );
		
		/** 5 Acacia **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? FoliageColors.get(0.9F, 0.0F) : -1; },
				Items_Wadeco.BONSAI_aca,
				Items_Wadeco.KANYOU_acacia,
				Items_Wadeco.IKEGAKILONG_acacia,
				Items_Wadeco.IKEGAKI_acacia );
		
		/** 6 DarkOak **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? FoliageColors.get(0.7F, 0.8F) : -1; }, //黒
				Items_Wadeco.BONSAI_doak,
				Items_Wadeco.KANYOU_darkoak,
				Items_Wadeco.IKEGAKILONG_darkoak,
				Items_Wadeco.IKEGAKI_darkoak );
		
		/** 7 KAEDE ---> Delete **/
		
		/** 8 Autumn_Oak **/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? 11170610 : -1; },
				Items_Seasonal.BONSAI_kare,
				Items_Seasonal.KANYOU_kare,
				Items_Seasonal.IKEGAKILONG_kare,
				Items_Seasonal.IKEGAKI_kare,
				Items_Seasonal.OAKKARE_leaf,
				Items_Seasonal.OCHIBA_carpet );
		
		/** Reizou 1=200,200,200 2=225,240,255**/
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? 13158600 : ((tint == 2)? 14807295 : -1); },
				Items_Teatime.KIT_REIZOU );
		
		/* 200, 100 */
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? 13158600 : ((tint == 2)? 6579300 : -1); },
				Items_NoTab.BOX_H_FISH,
				Items_Chinjufu.EMPTY_BOX,
				Items_Chinjufu.AMMOBOX,
				Items_Chinjufu.BAUXITE_BOX,
				
				Items_Teatime.BOX_H_EMPTY,
				Items_Teatime.BOX_H_APPLE,
				Items_Teatime.BOX_H_BEEF,
				Items_Teatime.BOX_H_BEETROOT,
				Items_Teatime.BOX_H_BREAD,
				Items_Teatime.BOX_H_CARROT,
				Items_Teatime.BOX_H_CHICKEN,
				Items_Teatime.BOX_H_CHORUS,
				Items_Teatime.BOX_H_COCO,
				Items_Teatime.BOX_H_COD,
				Items_Teatime.BOX_H_EGG,
				Items_Teatime.BOX_H_FLOUR,
				Items_Teatime.BOX_H_MUTTON,
				Items_Teatime.BOX_H_PORK,
				Items_Teatime.BOX_H_POTATO,
				Items_Teatime.BOX_H_RABBIT,
				Items_Teatime.BOX_H_SALMON,
				Items_Teatime.BOX_H_SWBERRY,
				
				Items_Teatime.BOX_H_AZUKI, /** 7.4.1 **/
				Items_Teatime.BOX_H_CABBAGE,
				Items_Teatime.BOX_H_HAKUSAI,
				Items_Teatime.BOX_H_CITRUS,
				Items_Teatime.BOX_H_CORN,
				Items_Teatime.BOX_H_GREENONION,
				Items_Teatime.BOX_H_GRAPE,
				Items_Teatime.BOX_H_ONION,
				Items_Teatime.BOX_H_ORIENTCLAM,
				Items_Teatime.BOX_H_RICE,
				Items_Teatime.BOX_H_SOY,
				Items_Teatime.BOX_H_SPINACH,
				Items_Teatime.BOX_H_SQUID,
				Items_Teatime.BOX_H_TOMATO,
				Items_Teatime.BOX_H_CHERRY,
				Items_Teatime.BOX_H_TAKENOKO,
				Items_Teatime.BOX_H_KURI,
				Items_Teatime.BOX_H_TGREEN,
				Items_Teatime.BOX_H_TRED,
				Items_Teatime.BOX_H_BPEPPER,
				Items_Teatime.BOX_H_CUMIN,
				Items_Teatime.BOX_H_TURMERIC,
				Items_Teatime.BOX_H_CHILI,
				
				Items_Teatime.CROP_TENGUSA, /** 8.1 **/
				Items_Teatime.TENGUSA_WASH, /** 8.1 **/
				Items_Teatime.TENGUSA_DRY /** 8.1 **/);
		
		/* 235 15461355 */
		mClient.getItemColors().register((stack, tint) -> {
			return (tint == 1)? 15461355 : -1; },
				Items_Chinjufu.SOFA_white,
				Items_Chinjufu.SOFA_orange,
				Items_Chinjufu.SOFA_magenta,
				Items_Chinjufu.SOFA_lightb,
				Items_Chinjufu.SOFA_yellow,
				Items_Chinjufu.SOFA_lime,
				Items_Chinjufu.SOFA_pink,
				Items_Chinjufu.SOFA_gray,
				Items_Chinjufu.SOFA_lightg,
				Items_Chinjufu.SOFA_cyan,
				Items_Chinjufu.SOFA_purple,
				Items_Chinjufu.SOFA_blue,
				Items_Chinjufu.SOFA_brown,
				Items_Chinjufu.SOFA_green,
				Items_Chinjufu.SOFA_red,
				Items_Chinjufu.SOFA_black,
				
				Items_Wadeco.ZABUTON_white,
				Items_Wadeco.ZABUTON_orange,
				Items_Wadeco.ZABUTON_magenta,
				Items_Wadeco.ZABUTON_lightb,
				Items_Wadeco.ZABUTON_yellow,
				Items_Wadeco.ZABUTON_lime,
				Items_Wadeco.ZABUTON_pink,
				Items_Wadeco.ZABUTON_gray,
				Items_Wadeco.ZABUTON_lightg,
				Items_Wadeco.ZABUTON_cyan,
				Items_Wadeco.ZABUTON_purple,
				Items_Wadeco.ZABUTON_blue,
				Items_Wadeco.ZABUTON_brown,
				Items_Wadeco.ZABUTON_green,
				Items_Wadeco.ZABUTON_red,
				Items_Wadeco.ZABUTON_black,
				Items_Wadeco.WARAZABUTON,
				Items_Wadeco.ZAISU_white,
				Items_Wadeco.ZAISU_orange,
				Items_Wadeco.ZAISU_magenta,
				Items_Wadeco.ZAISU_lightb,
				Items_Wadeco.ZAISU_yellow,
				Items_Wadeco.ZAISU_lime,
				Items_Wadeco.ZAISU_pink,
				Items_Wadeco.ZAISU_gray,
				Items_Wadeco.ZAISU_lightg,
				Items_Wadeco.ZAISU_cyan,
				Items_Wadeco.ZAISU_purple,
				Items_Wadeco.ZAISU_blue,
				Items_Wadeco.ZAISU_brown,
				Items_Wadeco.ZAISU_green,
				Items_Wadeco.ZAISU_red,
				Items_Wadeco.ZAISU_black );
	}
}
