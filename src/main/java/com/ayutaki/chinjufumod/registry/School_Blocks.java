package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.furnace.CStove_bot;
import com.ayutaki.chinjufumod.blocks.furnace.CStove_top;
import com.ayutaki.chinjufumod.blocks.school.BlackBoard;
import com.ayutaki.chinjufumod.blocks.school.SchoolChair;
import com.ayutaki.chinjufumod.blocks.school.SchoolDesk;
import com.ayutaki.chinjufumod.blocks.school.StoveChimney;
import com.ayutaki.chinjufumod.blocks.school.StoveChimney_joint;
import com.ayutaki.chinjufumod.blocks.school.StoveChimney_topk;
import com.ayutaki.chinjufumod.blocks.school.TeacherDesk;
import com.ayutaki.chinjufumod.blocks.school.WoodBoard;

import net.minecraft.block.Block;

public class School_Blocks {

	public static Block BLACKBOARD;
	public static Block BOARD_OAK, BOARD_SPRUCE, BOARD_BIRCH, BOARD_JUNGLE, BOARD_ACACIA, 
								BOARD_DOAK, BOARD_SAKURA, BOARD_KAEDE, BOARD_ICHOH;
	
	public static Block SCHOOLCHAIR, SCHOOLCHAIR_s,SCHOOLCHAIR_b,
								SCHOOLCHAIR_j, SCHOOLCHAIR_a, SCHOOLCHAIR_d,
								SCHOOLCHAIR_saku, SCHOOLCHAIR_kae, SCHOOLCHAIR_ich;

	public static Block SCHOOLDESK, SCHOOLDESK_s,SCHOOLDESK_b,
								SCHOOLDESK_j, SCHOOLDESK_a, SCHOOLDESK_d,
								SCHOOLDESK_saku, SCHOOLDESK_kae, SCHOOLDESK_ich;

	public static Block TEACHERDESK, TEACHERDESK_s,TEACHERDESK_b,
								TEACHERDESK_j, TEACHERDESK_a, TEACHERDESK_d,
								TEACHERDESK_saku, TEACHERDESK_kae, TEACHERDESK_ich;

	public static Block STOVECHIMNEY, STOVECHIMNEY_joint, STOVECHIMNEY_topk;

	public static Block CSTOVE_top, LIT_CSTOVE_top, CSTOVE_bot;


	public static void init() {

		BLACKBOARD = new BlackBoard("block_blackboard");
		BOARD_OAK = new WoodBoard("block_board_oak");
		BOARD_SPRUCE = new WoodBoard("block_board_spruce");
		BOARD_BIRCH = new WoodBoard("block_board_birch");
		BOARD_JUNGLE = new WoodBoard("block_board_jungle");
		BOARD_ACACIA = new WoodBoard("block_board_acacia");
		BOARD_DOAK = new WoodBoard("block_board_darkoak");
		BOARD_SAKURA = new WoodBoard("block_board_sakura");
		BOARD_KAEDE = new WoodBoard("block_board_kaede");
		BOARD_ICHOH = new WoodBoard("block_board_ichoh");
		
		SCHOOLCHAIR = new SchoolChair("block_schoolchair");
		SCHOOLCHAIR_s = new SchoolChair("block_schoolchair_s");
		SCHOOLCHAIR_b = new SchoolChair("block_schoolchair_b");
		SCHOOLCHAIR_j = new SchoolChair("block_schoolchair_j");
		SCHOOLCHAIR_a = new SchoolChair("block_schoolchair_a");
		SCHOOLCHAIR_d = new SchoolChair("block_schoolchair_d");
		SCHOOLCHAIR_saku = new SchoolChair("block_schoolchair_saku");
		SCHOOLCHAIR_kae = new SchoolChair("block_schoolchair_kae");
		SCHOOLCHAIR_ich = new SchoolChair("block_schoolchair_ich");

		SCHOOLDESK = new SchoolDesk("block_schooldesk");
		SCHOOLDESK_s = new SchoolDesk("block_schooldesk_s");
		SCHOOLDESK_b = new SchoolDesk("block_schooldesk_b");
		SCHOOLDESK_j = new SchoolDesk("block_schooldesk_j");
		SCHOOLDESK_a = new SchoolDesk("block_schooldesk_a");
		SCHOOLDESK_d = new SchoolDesk("block_schooldesk_d");
		SCHOOLDESK_saku = new SchoolDesk("block_schooldesk_saku");
		SCHOOLDESK_kae = new SchoolDesk("block_schooldesk_kae");
		SCHOOLDESK_ich = new SchoolDesk("block_schooldesk_ich");

		TEACHERDESK = new TeacherDesk("block_teacherdesk");
		TEACHERDESK_s = new TeacherDesk("block_teacherdesk_s");
		TEACHERDESK_b = new TeacherDesk("block_teacherdesk_b");
		TEACHERDESK_j = new TeacherDesk("block_teacherdesk_j");
		TEACHERDESK_a = new TeacherDesk("block_teacherdesk_a");
		TEACHERDESK_d = new TeacherDesk("block_teacherdesk_d");
		TEACHERDESK_saku = new TeacherDesk("block_teacherdesk_saku");
		TEACHERDESK_kae = new TeacherDesk("block_teacherdesk_kae");
		TEACHERDESK_ich = new TeacherDesk("block_teacherdesk_ich");

		STOVECHIMNEY = new StoveChimney("block_stovechimney");
		STOVECHIMNEY_joint = new StoveChimney_joint("block_stovechimney_joint");
		STOVECHIMNEY_topk = new StoveChimney_topk("block_stovechimney_topk");

		CSTOVE_top = new CStove_top(false, "block_cstove_top");
		LIT_CSTOVE_top = new CStove_top(true, "lit_block_cstove_top").setHardness(1.0F).setResistance(10.0F);
		CSTOVE_bot = new CStove_bot("block_cstove_bot");
	}


	public static void register() {

		registerBlock(BLACKBOARD);
		registerBlock(BOARD_OAK);
		registerBlock(BOARD_SPRUCE);
		registerBlock(BOARD_BIRCH);
		registerBlock(BOARD_JUNGLE);
		registerBlock(BOARD_ACACIA);
		registerBlock(BOARD_DOAK);
		registerBlock(BOARD_SAKURA);
		registerBlock(BOARD_KAEDE);
		registerBlock(BOARD_ICHOH);
		
		registerBlock(SCHOOLCHAIR);
		registerBlock(SCHOOLCHAIR_s);
		registerBlock(SCHOOLCHAIR_b);
		registerBlock(SCHOOLCHAIR_j);
		registerBlock(SCHOOLCHAIR_a);
		registerBlock(SCHOOLCHAIR_d);
		registerBlock(SCHOOLCHAIR_saku);
		registerBlock(SCHOOLCHAIR_kae);
		registerBlock(SCHOOLCHAIR_ich);

		registerBlock(SCHOOLDESK);
		registerBlock(SCHOOLDESK_s);
		registerBlock(SCHOOLDESK_b);
		registerBlock(SCHOOLDESK_j);
		registerBlock(SCHOOLDESK_a);
		registerBlock(SCHOOLDESK_d);
		registerBlock(SCHOOLDESK_saku);
		registerBlock(SCHOOLDESK_kae);
		registerBlock(SCHOOLDESK_ich);

		registerBlock(TEACHERDESK);
		registerBlock(TEACHERDESK_s);
		registerBlock(TEACHERDESK_b);
		registerBlock(TEACHERDESK_j);
		registerBlock(TEACHERDESK_a);
		registerBlock(TEACHERDESK_d);
		registerBlock(TEACHERDESK_saku);
		registerBlock(TEACHERDESK_kae);
		registerBlock(TEACHERDESK_ich);

		registerBlock(STOVECHIMNEY);
		registerBlock(STOVECHIMNEY_joint);
		registerBlock(STOVECHIMNEY_topk);

		registerBlock(CSTOVE_top);
		registerBlock(LIT_CSTOVE_top);
		registerBlock(CSTOVE_bot);
	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}
}
