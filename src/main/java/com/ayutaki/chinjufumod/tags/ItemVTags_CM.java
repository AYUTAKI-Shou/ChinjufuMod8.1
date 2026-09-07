package com.ayutaki.chinjufumod.tags;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemVTags_CM extends ItemTagsProvider {
	
	public ItemVTags_CM(DataGenerator gen, BlockTagsProvider blockTag, ExistingFileHelper helper) {
		super(gen, blockTag, ChinjufuMod.MOD_ID, helper);
	}

	/* addTags */
	@Override
	protected void addTags() {
		this.tag(ItemTags.BUTTONS)
		.addTag(ItemTags.WOODEN_BUTTONS)
		.add(Items_Wadeco.TAKE_BUTTON, Items_Wadeco.TAKE_BUTTON_Y, Items_Wadeco.TAKE_BUTTON_K);

		this.tag(ItemTags.DOORS)
		.addTag(ItemTags.WOODEN_DOORS)
		.add(Items_Wadeco.TAKEDOOR, Items_Wadeco.TAKEDOOR_Y, Items_Wadeco.TAKEDOOR_K);

		this.tag(ItemTags.FENCES)
		.addTag(ItemTags.WOODEN_FENCES)
		.add(Items_Wadeco.TAKEFENCE, Items_Wadeco.TAKEFENCE_Y, Items_Wadeco.TAKEFENCE_K);

		this.tag(ItemTags.LOGS_THAT_BURN)
		.add(Items_Seasonal.SAKURA_log, Items_Seasonal.KAEDE_log, Items_Seasonal.ICHOH_log);
	
		this.tag(ItemTags.LOGS)
		.add(Items_Seasonal.SAKURA_log, Items_Seasonal.KAEDE_log, Items_Seasonal.ICHOH_log);
	
		this.tag(ItemTags.LEAVES)
		.add(Items_Seasonal.SAKURA_flow, Items_Seasonal.KAEDE_leaf, Items_Seasonal.ICHOH_leaf, Items_Seasonal.OAKKARE_leaf);

		this.tag(ItemTags.PLANKS)
		.add(Items_Seasonal.SAKURA_planks, Items_Seasonal.KAEDE_planks, Items_Seasonal.ICHOH_planks);

		this.tag(ItemTags.SAPLINGS)
		.add(Items_Seasonal.SAKURA_nae, Items_Seasonal.KAEDE_nae, Items_Seasonal.ICHOH_nae, Items_Seasonal.OAKKARE_nae);

		this.tag(ItemTags.SLABS)
		.addTag(ItemTags.WOODEN_SLABS)
		.add(Items_Wablock.KAWARA_SH_yellow, Items_Wablock.KAWARA_SH_lime, Items_Wablock.KAWARA_SH_pink, Items_Wablock.KAWARA_SH_gray,
				Items_Wablock.KAWARA_SH_lightg, Items_Wablock.KAWARA_SH_cyan, Items_Wablock.KAWARA_SH_purple, Items_Wablock.KAWARA_SH_blue,
				Items_Wablock.KAWARA_SH_brown, Items_Wablock.KAWARA_SH_green, Items_Wablock.KAWARA_SH_red, Items_Wablock.KAWARA_SH_black,
				Items_Wablock.DIRTWALL_SH,
				Items_Wablock.SHIKKUI_SH_white, Items_Wablock.SHIKKUI_SH_orange, Items_Wablock.SHIKKUI_SH_magenta, Items_Wablock.SHIKKUI_SH_lightb,
				Items_Wablock.SHIKKUI_SH_yellow, Items_Wablock.SHIKKUI_SH_lime, Items_Wablock.SHIKKUI_SH_pink, Items_Wablock.SHIKKUI_SH_gray,
				Items_Wablock.SHIKKUI_SH_lightg, Items_Wablock.SHIKKUI_SH_cyan, Items_Wablock.SHIKKUI_SH_purple, Items_Wablock.SHIKKUI_SH_blue,
				Items_Wablock.SHIKKUI_SH_brown, Items_Wablock.SHIKKUI_SH_green, Items_Wablock.SHIKKUI_SH_red, Items_Wablock.SHIKKUI_SH_black,
				
				Items_Wablock.NAMAKO_SH_white, Items_Wablock.NAMAKO_SH_orange, Items_Wablock.NAMAKO_SH_magenta, Items_Wablock.NAMAKO_SH_lightb,
				Items_Wablock.NAMAKO_SH_yellow, Items_Wablock.NAMAKO_SH_lime, Items_Wablock.NAMAKO_SH_pink, Items_Wablock.NAMAKO_SH_gray,
				Items_Wablock.NAMAKO_SH_lightg, Items_Wablock.NAMAKO_SH_cyan, Items_Wablock.NAMAKO_SH_purple, Items_Wablock.NAMAKO_SH_blue,
				Items_Wablock.NAMAKO_SH_brown, Items_Wablock.NAMAKO_SH_green, Items_Wablock.NAMAKO_SH_red, Items_Wablock.NAMAKO_SH_black,
				Items_Wablock.NAMAKOB_SH_white, Items_Wablock.NAMAKOB_SH_orange, Items_Wablock.NAMAKOB_SH_magenta, Items_Wablock.NAMAKOB_SH_lightb,
				Items_Wablock.NAMAKOB_SH_yellow, Items_Wablock.NAMAKOB_SH_lime, Items_Wablock.NAMAKOB_SH_pink, Items_Wablock.NAMAKOB_SH_gray,
				Items_Wablock.NAMAKOB_SH_lightg, Items_Wablock.NAMAKOB_SH_cyan, Items_Wablock.NAMAKOB_SH_purple, Items_Wablock.NAMAKOB_SH_blue,
				Items_Wablock.NAMAKOB_SH_brown, Items_Wablock.NAMAKOB_SH_green, Items_Wablock.NAMAKOB_SH_red, Items_Wablock.NAMAKOB_SH_black,
				
				Items_WallPanel.BGC_slabhalf, Items_WallPanel.BDC_slabhalf, Items_WallPanel.BAC_slabhalf);
	
		this.tag(ItemTags.STAIRS)
		.addTag(ItemTags.WOODEN_STAIRS)
		.add(Items_Wablock.KAWARA_ST_yellow, Items_Wablock.KAWARA_ST_lime, Items_Wablock.KAWARA_ST_pink, Items_Wablock.KAWARA_ST_gray,
				Items_Wablock.KAWARA_ST_lightg, Items_Wablock.KAWARA_ST_cyan, Items_Wablock.KAWARA_ST_purple, Items_Wablock.KAWARA_ST_blue,
				Items_Wablock.KAWARA_ST_brown, Items_Wablock.KAWARA_ST_green, Items_Wablock.KAWARA_ST_red, Items_Wablock.KAWARA_ST_black,
				Items_Wablock.DIRTWALL_stairs,
				Items_Wablock.SHIKKUI_ST_white, Items_Wablock.SHIKKUI_ST_orange, Items_Wablock.SHIKKUI_ST_magenta, Items_Wablock.SHIKKUI_ST_lightb,
				Items_Wablock.SHIKKUI_ST_yellow, Items_Wablock.SHIKKUI_ST_lime, Items_Wablock.SHIKKUI_ST_pink, Items_Wablock.SHIKKUI_ST_gray,
				Items_Wablock.SHIKKUI_ST_lightg, Items_Wablock.SHIKKUI_ST_cyan, Items_Wablock.SHIKKUI_ST_purple, Items_Wablock.SHIKKUI_ST_blue,
				Items_Wablock.SHIKKUI_ST_brown, Items_Wablock.SHIKKUI_ST_green, Items_Wablock.SHIKKUI_ST_red, Items_Wablock.SHIKKUI_ST_black,
				
				Items_Wablock.NAMAKO_ST_white, Items_Wablock.NAMAKO_ST_orange, Items_Wablock.NAMAKO_ST_magenta, Items_Wablock.NAMAKO_ST_lightb,
				Items_Wablock.NAMAKO_ST_yellow, Items_Wablock.NAMAKO_ST_lime, Items_Wablock.NAMAKO_ST_pink, Items_Wablock.NAMAKO_ST_gray,
				Items_Wablock.NAMAKO_ST_lightg, Items_Wablock.NAMAKO_ST_cyan, Items_Wablock.NAMAKO_ST_purple, Items_Wablock.NAMAKO_ST_blue,
				Items_Wablock.NAMAKO_ST_brown, Items_Wablock.NAMAKO_ST_green, Items_Wablock.NAMAKO_ST_red, Items_Wablock.NAMAKO_ST_black,
				Items_Wablock.NAMAKOB_ST_white, Items_Wablock.NAMAKOB_ST_orange, Items_Wablock.NAMAKOB_ST_magenta, Items_Wablock.NAMAKOB_ST_lightb,
				Items_Wablock.NAMAKOB_ST_yellow, Items_Wablock.NAMAKOB_ST_lime, Items_Wablock.NAMAKOB_ST_pink, Items_Wablock.NAMAKOB_ST_gray,
				Items_Wablock.NAMAKOB_ST_lightg, Items_Wablock.NAMAKOB_ST_cyan, Items_Wablock.NAMAKOB_ST_purple, Items_Wablock.NAMAKOB_ST_blue,
				Items_Wablock.NAMAKOB_ST_brown, Items_Wablock.NAMAKOB_ST_green, Items_Wablock.NAMAKOB_ST_red, Items_Wablock.NAMAKOB_ST_black,
				
				Items_WallPanel.BRICKSTAIRS_GRA, Items_WallPanel.BRICKSTAIRS_DIO, Items_WallPanel.BRICKSTAIRS_AND);
	
		this.tag(ItemTags.STONE_BRICKS)
		.add(Items_WallPanel.BRICK_GRA, Items_WallPanel.BRICK_DIO, Items_WallPanel.BRICK_AND);
	
		this.tag(ItemTags.TRAPDOORS)
		.addTag(ItemTags.WOODEN_TRAPDOORS)
		.add(Items_Wadeco.TAKE_TRAPDOOR, Items_Wadeco.TAKE_TRAPDOOR_Y, Items_Wadeco.TAKE_TRAPDOOR_K);
		
		this.tag(ItemTags.WALLS)
		.add(Items_Chinjufu.WINDOW_oak, Items_Chinjufu.WINDOW_spruce, Items_Chinjufu.WINDOW_birch,
				Items_Chinjufu.WINDOW_jungle, Items_Chinjufu.WINDOW_acacia, Items_Chinjufu.WINDOW_darkoak,
				Items_Chinjufu.WINDOW_sakura, Items_Chinjufu.WINDOW_kaede, Items_Chinjufu.WINDOW_ichoh,
				Items_Chinjufu.WINDOWB_oak, Items_Chinjufu.WINDOWB_spruce, Items_Chinjufu.WINDOWB_birch,
				Items_Chinjufu.WINDOWB_jungle, Items_Chinjufu.WINDOWB_acacia, Items_Chinjufu.WINDOWB_darkoak,
				Items_Chinjufu.WINDOWB_sakura, Items_Chinjufu.WINDOWB_kaede, Items_Chinjufu.WINDOWB_ichoh,
				Items_Chinjufu.WINDOWTALLBOT_oak, Items_Chinjufu.WINDOWTALLBOT_spruce, Items_Chinjufu.WINDOWTALLBOT_birch,
				Items_Chinjufu.WINDOWTALLBOT_jungle, Items_Chinjufu.WINDOWTALLBOT_acacia, Items_Chinjufu.WINDOWTALLBOT_darkoak,
				Items_Chinjufu.WINDOWTALLBOT_sakura, Items_Chinjufu.WINDOWTALLBOT_kaede, Items_Chinjufu.WINDOWTALLBOT_ichoh,
				Items_Chinjufu.WINDOWTALL_oak, Items_Chinjufu.WINDOWTALL_spruce, Items_Chinjufu.WINDOWTALL_birch,
				Items_Chinjufu.WINDOWTALL_jungle, Items_Chinjufu.WINDOWTALL_acacia, Items_Chinjufu.WINDOWTALL_darkoak,
				Items_Chinjufu.WINDOWTALL_sakura, Items_Chinjufu.WINDOWTALL_kaede, Items_Chinjufu.WINDOWTALL_ichoh,
				
				Items_Wadeco.FUSUMA_white, Items_Wadeco.FUSUMA_orange, Items_Wadeco.FUSUMA_magenta, Items_Wadeco.FUSUMA_lightb,
				Items_Wadeco.FUSUMA_yellow, Items_Wadeco.FUSUMA_lime, Items_Wadeco.FUSUMA_pink, Items_Wadeco.FUSUMA_gray,
				Items_Wadeco.FUSUMA_lightg, Items_Wadeco.FUSUMA_cyan, Items_Wadeco.FUSUMA_purple, Items_Wadeco.FUSUMA_blue,
				Items_Wadeco.FUSUMA_brown, Items_Wadeco.FUSUMA_green, Items_Wadeco.FUSUMA_red, Items_Wadeco.FUSUMA_black,
				Items_Wadeco.FUSUMAB_white, Items_Wadeco.FUSUMAB_orange, Items_Wadeco.FUSUMAB_magenta, Items_Wadeco.FUSUMAB_lightb,
				Items_Wadeco.FUSUMAB_yellow, Items_Wadeco.FUSUMAB_lime, Items_Wadeco.FUSUMAB_pink, Items_Wadeco.FUSUMAB_gray,
				Items_Wadeco.FUSUMAB_lightg, Items_Wadeco.FUSUMAB_cyan, Items_Wadeco.FUSUMAB_purple, Items_Wadeco.FUSUMAB_blue,
				Items_Wadeco.FUSUMAB_brown, Items_Wadeco.FUSUMAB_green, Items_Wadeco.FUSUMAB_red, Items_Wadeco.FUSUMAB_black,
				
				Items_Wadeco.GARASUDO, Items_Wadeco.GARASUDO_SPRU, Items_Wadeco.GARASUDO_BIR,
				Items_Wadeco.GARASUDO_JUN, Items_Wadeco.GARASUDO_ACA, Items_Wadeco.GARASUDO_DOAK,
				Items_Seasonal.GARASUDO_SAKU, Items_Seasonal.GARASUDO_KAE, Items_Seasonal.GARASUDO_ICH,
				Items_Wadeco.GARASUDOB, Items_Wadeco.GARASUDOB_SPRU, Items_Wadeco.GARASUDOB_BIR,
				Items_Wadeco.GARASUDOB_JUN, Items_Wadeco.GARASUDOB_ACA, Items_Wadeco.GARASUDOB_DOAK,
				Items_Seasonal.GARASUDOB_SAKU, Items_Seasonal.GARASUDOB_KAE, Items_Seasonal.GARASUDOB_ICH,
				Items_Wadeco.GARASUDOH, Items_Wadeco.GARASUDOH_SPRU, Items_Wadeco.GARASUDOH_BIR,
				Items_Wadeco.GARASUDOH_JUN, Items_Wadeco.GARASUDOH_ACA, Items_Wadeco.GARASUDOH_DOAK,
				Items_Seasonal.GARASUDOH_SAKU, Items_Seasonal.GARASUDOH_KAE, Items_Seasonal.GARASUDOH_ICH,
				
				Items_Wadeco.SHOUJI, Items_Wadeco.SHOUJI_SPRU, Items_Wadeco.SHOUJI_BIR,
				Items_Wadeco.SHOUJI_JUN, Items_Wadeco.SHOUJI_ACA, Items_Wadeco.SHOUJI_DOAK,
				Items_Seasonal.SHOUJI_SAKU, Items_Seasonal.SHOUJI_KAE, Items_Seasonal.SHOUJI_ICH,
				Items_Wadeco.SHOUJIB, Items_Wadeco.SHOUJIB_SPRU, Items_Wadeco.SHOUJIB_BIR,
				Items_Wadeco.SHOUJIB_JUN, Items_Wadeco.SHOUJIB_ACA, Items_Wadeco.SHOUJIB_DOAK,
				Items_Seasonal.SHOUJIB_SAKU, Items_Seasonal.SHOUJIB_KAE, Items_Seasonal.SHOUJIB_ICH,
				Items_Wadeco.SHOUJIH, Items_Wadeco.SHOUJIH_SPRU, Items_Wadeco.SHOUJIH_BIR,
				Items_Wadeco.SHOUJIH_JUN, Items_Wadeco.SHOUJIH_ACA, Items_Wadeco.SHOUJIH_DOAK,
				Items_Seasonal.SHOUJIH_SAKU, Items_Seasonal.SHOUJIH_KAE, Items_Seasonal.SHOUJIH_ICH,
				Items_Wadeco.SHOUJI_WIN, Items_Wadeco.SHOUJI_WIN_SPRU, Items_Wadeco.SHOUJI_WIN_BIR,
				Items_Wadeco.SHOUJI_WIN_JUN, Items_Wadeco.SHOUJI_WIN_ACA, Items_Wadeco.SHOUJI_WIN_DOAK,
				Items_Seasonal.SHOUJI_WIN_SAKU, Items_Seasonal.SHOUJI_WIN_KAE, Items_Seasonal.SHOUJI_WIN_ICH,
				
				Items_Wadeco.RANMA_oak, Items_Wadeco.RANMA_spruce, Items_Wadeco.RANMA_birch,
				Items_Wadeco.RANMA_jungle, Items_Wadeco.RANMA_acacia, Items_Wadeco.RANMA_darkoak,
				Items_Seasonal.RANMA_sakura, Items_Seasonal.RANMA_kaede, Items_Seasonal.RANMA_ichoh,
				Items_Wadeco.RANMAB_oak, Items_Wadeco.RANMAB_spruce, Items_Wadeco.RANMAB_birch,
				Items_Wadeco.RANMAB_jungle, Items_Wadeco.RANMAB_acacia, Items_Wadeco.RANMAB_darkoak,
				Items_Seasonal.RANMAB_sakura, Items_Seasonal.RANMAB_kaede, Items_Seasonal.RANMAB_ichoh,
				Items_Wadeco.RANMAC_oak, Items_Wadeco.RANMAC_spruce, Items_Wadeco.RANMAC_birch,
				Items_Wadeco.RANMAC_jungle, Items_Wadeco.RANMAC_acacia, Items_Wadeco.RANMAC_darkoak,
				Items_Seasonal.RANMAC_sakura, Items_Seasonal.RANMAC_kaede, Items_Seasonal.RANMAC_ichoh,
				
				Items_Wadeco.KANKI_oak, Items_Wadeco.KANKI_spruce, Items_Wadeco.KANKI_birch,
				Items_Wadeco.KANKI_jungle, Items_Wadeco.KANKI_acacia, Items_Wadeco.KANKI_darkoak,
				Items_Seasonal.KANKI_sakura, Items_Seasonal.KANKI_kaede, Items_Seasonal.KANKI_ichoh,
				Items_Wadeco.KOUSHI_oak, Items_Wadeco.KOUSHI_spruce, Items_Wadeco.KOUSHI_birch,
				Items_Wadeco.KOUSHI_jungle, Items_Wadeco.KOUSHI_acacia, Items_Wadeco.KOUSHI_darkoak,
				Items_Seasonal.KOUSHI_sakura, Items_Seasonal.KOUSHI_kaede, Items_Seasonal.KOUSHI_ichoh,
				Items_Wadeco.KOUSHIB_oak, Items_Wadeco.KOUSHIB_spruce, Items_Wadeco.KOUSHIB_birch,
				Items_Wadeco.KOUSHIB_jungle, Items_Wadeco.KOUSHIB_acacia, Items_Wadeco.KOUSHIB_darkoak,
				Items_Seasonal.KOUSHIB_sakura, Items_Seasonal.KOUSHIB_kaede, Items_Seasonal.KOUSHIB_ichoh,
				
				Items_Wablock.DIRTWALL_WALL,
				Items_Wablock.SHIKKUI_WALL_white, Items_Wablock.SHIKKUI_WALL_orange, Items_Wablock.SHIKKUI_WALL_magenta, Items_Wablock.SHIKKUI_WALL_lightb,
				Items_Wablock.SHIKKUI_WALL_yellow, Items_Wablock.SHIKKUI_WALL_lime, Items_Wablock.SHIKKUI_WALL_pink, Items_Wablock.SHIKKUI_WALL_gray,
				Items_Wablock.SHIKKUI_WALL_lightg, Items_Wablock.SHIKKUI_WALL_cyan, Items_Wablock.SHIKKUI_WALL_purple, Items_Wablock.SHIKKUI_WALL_blue,
				Items_Wablock.SHIKKUI_WALL_brown,Items_Wablock.SHIKKUI_WALL_green, Items_Wablock.SHIKKUI_WALL_red, Items_Wablock.SHIKKUI_WALL_black,
				Items_Wablock.NAMAKO_WALL_white, Items_Wablock.NAMAKO_WALL_orange, Items_Wablock.NAMAKO_WALL_magenta, Items_Wablock.NAMAKO_WALL_lightb,
				Items_Wablock.NAMAKO_WALL_yellow, Items_Wablock.NAMAKO_WALL_lime, Items_Wablock.NAMAKO_WALL_pink, Items_Wablock.NAMAKO_WALL_gray, 
				Items_Wablock.NAMAKO_WALL_lightg, Items_Wablock.NAMAKO_WALL_cyan, Items_Wablock.NAMAKO_WALL_purple, Items_Wablock.NAMAKO_WALL_blue,
				Items_Wablock.NAMAKO_WALL_brown, Items_Wablock.NAMAKO_WALL_green, Items_Wablock.NAMAKO_WALL_red, Items_Wablock.NAMAKO_WALL_black,
				Items_Wablock.NAMAKOB_WALL_white, Items_Wablock.NAMAKOB_WALL_orange, Items_Wablock.NAMAKOB_WALL_magenta, Items_Wablock.NAMAKOB_WALL_lightb,
				Items_Wablock.NAMAKOB_WALL_yellow, Items_Wablock.NAMAKOB_WALL_lime, Items_Wablock.NAMAKOB_WALL_pink, Items_Wablock.NAMAKOB_WALL_gray,
				Items_Wablock.NAMAKOB_WALL_lightg, Items_Wablock.NAMAKOB_WALL_cyan, Items_Wablock.NAMAKOB_WALL_purple, Items_Wablock.NAMAKOB_WALL_blue,
				Items_Wablock.NAMAKOB_WALL_brown, Items_Wablock.NAMAKOB_WALL_green, Items_Wablock.NAMAKOB_WALL_red, Items_Wablock.NAMAKOB_WALL_black,
				Items_Wablock.DIRTWALL_SAMA,
				Items_Wablock.SHIKKUI_SAMA_white, Items_Wablock.SHIKKUI_SAMA_orange, Items_Wablock.SHIKKUI_SAMA_magenta, Items_Wablock.SHIKKUI_SAMA_lightb, 
				Items_Wablock.SHIKKUI_SAMA_yellow, Items_Wablock.SHIKKUI_SAMA_lime, Items_Wablock.SHIKKUI_SAMA_pink, Items_Wablock.SHIKKUI_SAMA_gray,
				Items_Wablock.SHIKKUI_SAMA_lightg, Items_Wablock.SHIKKUI_SAMA_cyan, Items_Wablock.SHIKKUI_SAMA_purple, Items_Wablock.SHIKKUI_SAMA_blue,
				Items_Wablock.SHIKKUI_SAMA_brown, Items_Wablock.SHIKKUI_SAMA_green, Items_Wablock.SHIKKUI_SAMA_red, Items_Wablock.SHIKKUI_SAMA_black,
				Items_Wablock.KAWARA_WALL_white, Items_Wablock.KAWARA_WALL_orange, Items_Wablock.KAWARA_WALL_magenta, Items_Wablock.KAWARA_WALL_lightb,
				Items_Wablock.KAWARA_WALL_yellow, Items_Wablock.KAWARA_WALL_lime,Items_Wablock.KAWARA_WALL_pink, Items_Wablock.KAWARA_WALL_gray,
				Items_Wablock.KAWARA_WALL_lightg, Items_Wablock.KAWARA_WALL_cyan, Items_Wablock.KAWARA_WALL_purple, Items_Wablock.KAWARA_WALL_blue,
				Items_Wablock.KAWARA_WALL_brown, Items_Wablock.KAWARA_WALL_green, Items_Wablock.KAWARA_WALL_red, Items_Wablock.KAWARA_WALL_black, 
	
				Items_Wadeco.IKEGAKI, Items_Wadeco.IKEGAKI_spruce, Items_Wadeco.IKEGAKI_birch, 
				Items_Wadeco.IKEGAKI_jungle, Items_Wadeco.IKEGAKI_acacia, Items_Wadeco.IKEGAKI_darkoak, 
				Items_Seasonal.IKEGAKI_sakura, Items_Seasonal.IKEGAKI_kaede, Items_Seasonal.IKEGAKI_ichoh, Items_Seasonal.IKEGAKI_kare, 
				Items_Wadeco.IKEGAKILONG, Items_Wadeco.IKEGAKILONG_spruce, Items_Wadeco.IKEGAKILONG_birch, 
				Items_Wadeco.IKEGAKILONG_jungle, Items_Wadeco.IKEGAKILONG_acacia, Items_Wadeco.IKEGAKILONG_darkoak, 
				Items_Seasonal.IKEGAKILONG_sakura, Items_Seasonal.IKEGAKILONG_kaede, Items_Seasonal.IKEGAKILONG_ichoh, Items_Seasonal.IKEGAKILONG_kare,
				
				Items_Wadeco.ITABEI, Items_Wadeco.ITABEI_spruce, Items_Wadeco.ITABEI_birch, 
				Items_Wadeco.ITABEI_jungle, Items_Wadeco.ITABEI_acacia, Items_Wadeco.ITABEI_darkoak, 
				Items_Seasonal.ITABEI_sakura, Items_Seasonal.ITABEI_kaede, Items_Seasonal.ITABEI_ichoh, 
				Items_Wadeco.KIDO, Items_Wadeco.KIDO_spruce, Items_Wadeco.KIDO_birch, 
				Items_Wadeco.KIDO_jungle, Items_Wadeco.KIDO_acacia, Items_Wadeco.KIDO_darkoak, 
				Items_Seasonal.KIDO_sakura, Items_Seasonal.KIDO_kaede, Items_Seasonal.KIDO_ichoh);
		
		this.tag(ItemTags.WOODEN_BUTTONS)
		.add(Items_Seasonal.SAKURA_BUTTON, Items_Seasonal.KAEDE_BUTTON, Items_Seasonal.ICHOH_BUTTON);
		
		this.tag(ItemTags.WOODEN_DOORS)
		.add(Items_Seasonal.DOOR_SAKURA, Items_Seasonal.DOOR_KAEDE, Items_Seasonal.DOOR_ICHOH);
		
		this.tag(ItemTags.WOODEN_FENCES)
		.add(Items_Seasonal.SAKURA_FENCE, Items_Seasonal.KAEDE_FENCE, Items_Seasonal.ICHOH_FENCE);
		
		this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
		.add(Items_Seasonal.SAKURA_PLATE, Items_Seasonal.KAEDE_PLATE, Items_Seasonal.ICHOH_PLATE);
		
		this.tag(ItemTags.WOODEN_SLABS)
		.add(Items_Seasonal.SAKURA_slabhalf, Items_Seasonal.KAEDE_slabhalf, Items_Seasonal.ICHOH_slabhalf);
		
		this.tag(ItemTags.WOODEN_STAIRS)
		.add(Items_Seasonal.SAKURA_stairs, Items_Seasonal.KAEDE_stairs, Items_Seasonal.ICHOH_stairs);
		
		this.tag(ItemTags.WOODEN_TRAPDOORS)
		.add(Items_Seasonal.SAKURA_TRAPDOOR, Items_Seasonal.KAEDE_TRAPDOOR, Items_Seasonal.ICHOH_TRAPDOOR);
	}
	
	@Override
	public String getName() {
		return "ChinjufuMod Vanilla Item Tags";
	}
}
