package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.unitblock.CafeTable;
import com.ayutaki.chinjufumod.blocks.unitblock.CafeTable_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.ClothTable;
import com.ayutaki.chinjufumod.blocks.unitblock.ClothTable_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.Endai;
import com.ayutaki.chinjufumod.blocks.unitblock.Endai_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.TrayLetter;
import com.ayutaki.chinjufumod.blocks.unitblock.UnitDesk;
import com.ayutaki.chinjufumod.blocks.unitblock.UnitDesk_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.WrittenBook;
import com.ayutaki.chinjufumod.blocks.unitblock.WrittenMakimono;
import com.ayutaki.chinjufumod.blocks.unitblock.WrittenMakimono5;

import net.minecraft.block.Block;

public final class Unit_Blocks {

	public static Block UNITDESK, UNITDESK_sub;
	public static Block CAFETABLE, CAFETABLE_sub;
	public static Block ENDAI, ENDAI_sub;

	public static Block LOWDESK, LOWDESK_sub;
	public static Block LETTERTRAY;
	public static Block CHABUDAI, CHABUDAI_sub;
	public static Block KOTATSU, KOTATSU_sub;
	public static Block WRITTEN_BOOK, WRITTEN_MAKIMONO, WRITTEN_MAKIMONO5;

	public static Block CLOTHTABLE_oak, CLOTHTABLE_spruce, CLOTHTABLE_birch, 
								CLOTHTABLE_jungle, CLOTHTABLE_acacia, CLOTHTABLE_darkoak, 
								CLOTHTABLE_sakura, CLOTHTABLE_kaede, CLOTHTABLE_ichoh;
	public static Block CLOTHTABLE_oaksub, CLOTHTABLE_sprucesub, CLOTHTABLE_birchsub, 
								CLOTHTABLE_junglesub, CLOTHTABLE_acaciasub, CLOTHTABLE_darkoaksub, 
								CLOTHTABLE_sakurasub, CLOTHTABLE_kaedesub, CLOTHTABLE_ichohsub;
	
	public static void init() {

		UNITDESK = new UnitDesk("block_unitdesk");
		UNITDESK_sub = new UnitDesk_sub("block_unitdesk_sub");
		CAFETABLE = new CafeTable("block_cafetable");
		CAFETABLE_sub = new CafeTable_sub("block_cafetable_sub");
		ENDAI = new Endai("block_mendai");
		ENDAI_sub = new Endai_sub("block_mendai_sub");

		LOWDESK = new LowDesk("block_lowdesk");
		LOWDESK_sub = new LowDesk_sub("block_lowdesk_sub");
		LETTERTRAY = new TrayLetter("block_lettertray_c");
		CHABUDAI = new Chabudai("block_chabudai");
		CHABUDAI_sub = new Chabudai_sub("block_chabudai_sub");
		KOTATSU = new Kotatsu("block_kotatsu");
		KOTATSU_sub = new Kotatsu_sub("block_kotatsu_sub");
		
		WRITTEN_BOOK = new WrittenBook("block_written_book");
		WRITTEN_MAKIMONO = new WrittenMakimono("block_written_makimono");
		WRITTEN_MAKIMONO5 = new WrittenMakimono5("block_written_makimono5");
		
		CLOTHTABLE_oak = new ClothTable("block_clothtable_oak");
		CLOTHTABLE_spruce = new ClothTable("block_clothtable_spruce");
		CLOTHTABLE_birch = new ClothTable("block_clothtable_birch");
		CLOTHTABLE_jungle = new ClothTable("block_clothtable_jungle");
		CLOTHTABLE_acacia = new ClothTable("block_clothtable_acacia");
		CLOTHTABLE_darkoak = new ClothTable("block_clothtable_darkoak");
		CLOTHTABLE_sakura = new ClothTable("block_clothtable_sakura");
		CLOTHTABLE_kaede = new ClothTable("block_clothtable_kaede");
		CLOTHTABLE_ichoh = new ClothTable("block_clothtable_ichoh");
		
		CLOTHTABLE_oaksub = new ClothTable_sub("block_clothtable_oaksub");
		CLOTHTABLE_sprucesub = new ClothTable_sub("block_clothtable_sprucesub");
		CLOTHTABLE_birchsub = new ClothTable_sub("block_clothtable_birchsub");
		CLOTHTABLE_junglesub = new ClothTable_sub("block_clothtable_junglesub");
		CLOTHTABLE_acaciasub = new ClothTable_sub("block_clothtable_acaciasub");
		CLOTHTABLE_darkoaksub = new ClothTable_sub("block_clothtable_darkoaksub");
		CLOTHTABLE_sakurasub = new ClothTable_sub("block_clothtable_sakurasub");
		CLOTHTABLE_kaedesub = new ClothTable_sub("block_clothtable_kaedesub");
		CLOTHTABLE_ichohsub = new ClothTable_sub("block_clothtable_ichohsub");
	}

	public static void register() {

		registerBlock(UNITDESK);
		registerBlock(UNITDESK_sub);
		registerBlock(CAFETABLE);
		registerBlock(CAFETABLE_sub);
		registerBlock(ENDAI);
		registerBlock(ENDAI_sub);

		registerBlock(LOWDESK);
		registerBlock(LOWDESK_sub);
		registerBlock(LETTERTRAY);
		registerBlock(CHABUDAI);
		registerBlock(CHABUDAI_sub);
		registerBlock(KOTATSU);
		registerBlock(KOTATSU_sub);
		registerBlock(WRITTEN_BOOK);
		registerBlock(WRITTEN_MAKIMONO);
		registerBlock(WRITTEN_MAKIMONO5);
		
		registerBlock(CLOTHTABLE_oak);
		registerBlock(CLOTHTABLE_spruce);
		registerBlock(CLOTHTABLE_birch);
		registerBlock(CLOTHTABLE_jungle);
		registerBlock(CLOTHTABLE_acacia);
		registerBlock(CLOTHTABLE_darkoak);
		registerBlock(CLOTHTABLE_sakura);
		registerBlock(CLOTHTABLE_kaede);
		registerBlock(CLOTHTABLE_ichoh);
		
		registerBlock(CLOTHTABLE_oaksub);
		registerBlock(CLOTHTABLE_sprucesub);
		registerBlock(CLOTHTABLE_birchsub);
		registerBlock(CLOTHTABLE_junglesub);
		registerBlock(CLOTHTABLE_acaciasub);
		registerBlock(CLOTHTABLE_darkoaksub);
		registerBlock(CLOTHTABLE_sakurasub);
		registerBlock(CLOTHTABLE_kaedesub);
		registerBlock(CLOTHTABLE_ichohsub);
	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}
}
