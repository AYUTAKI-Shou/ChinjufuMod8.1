package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.RegisterHandler_CM;
import com.ayutaki.chinjufumod.blocks.harbor.Keikai;
import com.ayutaki.chinjufumod.blocks.harbor.Keiryukui;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Amp;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Amp2;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Cable;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Cable2;

import net.minecraft.block.Block;

public class Harbor_Blocks {

	public static Block KEIKAIBLOCK;

	public static Block KEIRYUKUI,KEIRYUKUI_b;

	public static Block TRUSS, TRUSS_white, TRUSS_orange, TRUSS_magenta,
								TRUSS_lightb, TRUSS_yellow, TRUSS_lime, TRUSS_pink,
								TRUSS_gray, TRUSS_cyan, TRUSS_purple, TRUSS_blue,
								TRUSS_brown, TRUSS_green, TRUSS_red, TRUSS_black;

	public static Block TRUSS2, TRUSS2_white, TRUSS2_orange, TRUSS2_magenta,
								TRUSS2_lightb, TRUSS2_yellow, TRUSS2_lime, TRUSS2_pink,
								TRUSS2_gray, TRUSS2_cyan, TRUSS2_purple, TRUSS2_blue,
								TRUSS2_brown, TRUSS2_green, TRUSS2_red, TRUSS2_black;
	
	public static Block AMP, AMP_white, AMP_orange, AMP_magenta,
								AMP_lightb, AMP_yellow, AMP_lime, AMP_pink,
								AMP_gray, AMP_cyan, AMP_purple, AMP_blue,
								AMP_brown, AMP_green, AMP_red, AMP_black;

	public static Block AMP2, AMP2_white, AMP2_orange, AMP2_magenta,
								AMP2_lightb, AMP2_yellow, AMP2_lime, AMP2_pink,
								AMP2_gray, AMP2_cyan, AMP2_purple, AMP2_blue,
								AMP2_brown, AMP2_green, AMP2_red, AMP2_black;

	public static void init() {
		KEIKAIBLOCK = new Keikai("block_keikai");

		KEIRYUKUI = new Keiryukui("block_keiryukui");
		KEIRYUKUI_b = new Keiryukui("block_keiryukui_b");

		TRUSS = new Truss_Cable("block_ctruss");
		TRUSS_white = new Truss_Cable("block_ctruss_white");
		TRUSS_orange = new Truss_Cable("block_ctruss_orange");
		TRUSS_magenta = new Truss_Cable("block_ctruss_magenta");
		TRUSS_lightb = new Truss_Cable("block_ctruss_lightb");
		TRUSS_yellow = new Truss_Cable("block_ctruss_yellow");
		TRUSS_lime = new Truss_Cable("block_ctruss_lime");
		TRUSS_pink = new Truss_Cable("block_ctruss_pink");
		TRUSS_gray = new Truss_Cable("block_ctruss_gray");
		TRUSS_cyan = new Truss_Cable("block_ctruss_cyan");
		TRUSS_purple = new Truss_Cable("block_ctruss_purple");
		TRUSS_blue = new Truss_Cable("block_ctruss_blue");
		TRUSS_brown = new Truss_Cable("block_ctruss_brown");
		TRUSS_green = new Truss_Cable("block_ctruss_green");
		TRUSS_red = new Truss_Cable("block_ctruss_red");
		TRUSS_black = new Truss_Cable("block_ctruss_black");
		
		TRUSS2 = new Truss_Cable2("block_ctruss2");
		TRUSS2_white = new Truss_Cable2("block_ctruss2_white");
		TRUSS2_orange = new Truss_Cable2("block_ctruss2_orange");
		TRUSS2_magenta = new Truss_Cable2("block_ctruss2_magenta");
		TRUSS2_lightb = new Truss_Cable2("block_ctruss2_lightb");
		TRUSS2_yellow = new Truss_Cable2("block_ctruss2_yellow");
		TRUSS2_lime = new Truss_Cable2("block_ctruss2_lime");
		TRUSS2_pink = new Truss_Cable2("block_ctruss2_pink");
		TRUSS2_gray = new Truss_Cable2("block_ctruss2_gray");
		TRUSS2_cyan = new Truss_Cable2("block_ctruss2_cyan");
		TRUSS2_purple = new Truss_Cable2("block_ctruss2_purple");
		TRUSS2_blue = new Truss_Cable2("block_ctruss2_blue");
		TRUSS2_brown = new Truss_Cable2("block_ctruss2_brown");
		TRUSS2_green = new Truss_Cable2("block_ctruss2_green");
		TRUSS2_red = new Truss_Cable2("block_ctruss2_red");
		TRUSS2_black = new Truss_Cable2("block_ctruss2_black");
		
		AMP = new Truss_Amp(false, "block_amp");
		AMP_white = new Truss_Amp(false, "block_amp_white");
		AMP_orange = new Truss_Amp(false, "block_amp_orange");
		AMP_magenta = new Truss_Amp(false, "block_amp_magenta");
		AMP_lightb = new Truss_Amp(false, "block_amp_lightblue");
		AMP_yellow = new Truss_Amp(false, "block_amp_yellow");
		AMP_lime = new Truss_Amp(false, "block_amp_lime");
		AMP_pink = new Truss_Amp(false, "block_amp_pink");
		AMP_gray = new Truss_Amp(false, "block_amp_gray");
		AMP_cyan = new Truss_Amp(false, "block_amp_cyan");
		AMP_purple = new Truss_Amp(false, "block_amp_purple");
		AMP_blue = new Truss_Amp(false, "block_amp_blue");
		AMP_brown = new Truss_Amp(false, "block_amp_brown");
		AMP_green = new Truss_Amp(false, "block_amp_green");
		AMP_red = new Truss_Amp(false, "block_amp_red");
		AMP_black = new Truss_Amp(false, "block_amp_black");
		
		AMP2 = new Truss_Amp2(true, "block_amp2");
		AMP2_white = new Truss_Amp2(true, "block_amp2_white");
		AMP2_orange = new Truss_Amp2(true, "block_amp2_orange");
		AMP2_magenta = new Truss_Amp2(true, "block_amp2_magenta");
		AMP2_lightb = new Truss_Amp2(true, "block_amp2_lightblue");
		AMP2_yellow = new Truss_Amp2(true, "block_amp2_yellow");
		AMP2_lime = new Truss_Amp2(true, "block_amp2_lime");
		AMP2_pink = new Truss_Amp2(true, "block_amp2_pink");
		AMP2_gray = new Truss_Amp2(true, "block_amp2_gray");
		AMP2_cyan = new Truss_Amp2(true, "block_amp2_cyan");
		AMP2_purple = new Truss_Amp2(true, "block_amp2_purple");
		AMP2_blue = new Truss_Amp2(true, "block_amp2_blue");
		AMP2_brown = new Truss_Amp2(true, "block_amp2_brown");
		AMP2_green = new Truss_Amp2(true, "block_amp2_green");
		AMP2_red = new Truss_Amp2(true, "block_amp2_red");
		AMP2_black = new Truss_Amp2(true, "block_amp2_black");
	}


	public static void register() {
		registerBlock(KEIKAIBLOCK);
		registerBlock(KEIRYUKUI);
		registerBlock(KEIRYUKUI_b);

		registerBlock(TRUSS);
		registerBlock(TRUSS_white);
		registerBlock(TRUSS_orange);
		registerBlock(TRUSS_magenta);
		registerBlock(TRUSS_lightb);
		registerBlock(TRUSS_yellow);
		registerBlock(TRUSS_lime);
		registerBlock(TRUSS_pink);
		registerBlock(TRUSS_gray);
		registerBlock(TRUSS_cyan);
		registerBlock(TRUSS_purple);
		registerBlock(TRUSS_blue);
		registerBlock(TRUSS_brown);
		registerBlock(TRUSS_green);
		registerBlock(TRUSS_red);
		registerBlock(TRUSS_black);
		
		registerBlock(TRUSS2);
		registerBlock(TRUSS2_white);
		registerBlock(TRUSS2_orange);
		registerBlock(TRUSS2_magenta);
		registerBlock(TRUSS2_lightb);
		registerBlock(TRUSS2_yellow);
		registerBlock(TRUSS2_lime);
		registerBlock(TRUSS2_pink);
		registerBlock(TRUSS2_gray);
		registerBlock(TRUSS2_cyan);
		registerBlock(TRUSS2_purple);
		registerBlock(TRUSS2_blue);
		registerBlock(TRUSS2_brown);
		registerBlock(TRUSS2_green);
		registerBlock(TRUSS2_red);
		registerBlock(TRUSS2_black);
		
		registerBlock(AMP);
		registerBlock(AMP_white);
		registerBlock(AMP_orange);
		registerBlock(AMP_magenta);
		registerBlock(AMP_lightb);
		registerBlock(AMP_yellow);
		registerBlock(AMP_lime);
		registerBlock(AMP_pink);
		registerBlock(AMP_gray);
		registerBlock(AMP_cyan);
		registerBlock(AMP_purple);
		registerBlock(AMP_blue);
		registerBlock(AMP_brown);
		registerBlock(AMP_green);
		registerBlock(AMP_red);
		registerBlock(AMP_black);
		
		registerBlock(AMP2);
		registerBlock(AMP2_white);
		registerBlock(AMP2_orange);
		registerBlock(AMP2_magenta);
		registerBlock(AMP2_lightb);
		registerBlock(AMP2_yellow);
		registerBlock(AMP2_lime);
		registerBlock(AMP2_pink);
		registerBlock(AMP2_gray);
		registerBlock(AMP2_cyan);
		registerBlock(AMP2_purple);
		registerBlock(AMP2_blue);
		registerBlock(AMP2_brown);
		registerBlock(AMP2_green);
		registerBlock(AMP2_red);
		registerBlock(AMP2_black);
	}

	public static void registerBlock(Block block) {
		RegisterHandler_CM.Blocks.BLOCKS.add(block);
	}
}
