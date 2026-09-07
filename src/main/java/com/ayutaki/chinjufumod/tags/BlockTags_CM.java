package com.ayutaki.chinjufumod.tags;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.registry.JP_Blocks;
import com.ayutaki.chinjufumod.registry.Ranma_Blocks;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;
import com.ayutaki.chinjufumod.registry.WallPanel_Blocks;
import com.ayutaki.chinjufumod.registry.Window_Blocks;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags_CM extends BlockTagsProvider {

	public BlockTags_CM(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, ChinjufuMod.MOD_ID, helper);
	}
	
	/* TagKey */
	public static final ITag.INamedTag<Block> DIRT = forgeTag("dirt");
	
	private static ITag.INamedTag<Block> forgeTag(String name) {
		return BlockTags.createOptional(new ResourceLocation("forge", name));
	}
	
	/* addTags */
	@Override
	protected void addTags() {
		/** forge **/
		this.tag(DIRT).add(Wood_Blocks.FALL_LEAF);
		
		
		/** vanilla **/
		this.tag(BlockTags.VALID_SPAWN).add(Wood_Blocks.FALL_LEAF);

		this.tag(BlockTags.BUTTONS)
		.addTag(BlockTags.WOODEN_BUTTONS)
		.add(JPDeco_Blocks.TAKE_BUTTON, JPDeco_Blocks.TAKE_BUTTON_Y, JPDeco_Blocks.TAKE_BUTTON_K);
		
		this.tag(BlockTags.CROPS)
		.add(Crop_Blocks.AZUKI, Crop_Blocks.CABBAGE, Crop_Blocks.HAKUSAI, Crop_Blocks.CORN, 
				Crop_Blocks.GREENONION, Crop_Blocks.ONION, Crop_Blocks.RICE, 
				Crop_Blocks.SOY, Crop_Blocks.SPINACH, Crop_Blocks.TOMATO, Crop_Blocks.SAKURA,
				Crop_Blocks.CUMIN, Crop_Blocks.TURMERIC, Crop_Blocks.CHILI);

		this.tag(BlockTags.DOORS)
		.addTag(BlockTags.WOODEN_DOORS)
		.add(JPDeco_Blocks.TAKEDOOR, JPDeco_Blocks.TAKEDOOR_Y, JPDeco_Blocks.TAKEDOOR_K);
		
		this.tag(BlockTags.FLOWERS).add(Wood_Blocks.SAKURA_flow);
		
		this.tag(BlockTags.FENCE_GATES)
		.add(JPDeco_Blocks.TAKEFENCEGATE, JPDeco_Blocks.TAKEFENCEGATE_Y, JPDeco_Blocks.TAKEFENCEGATE_K,
				Wood_Blocks.SAKURA_FGATE, Wood_Blocks.KAEDE_FGATE, Wood_Blocks.ICHOH_FGATE);
		
		this.tag(BlockTags.FENCES)
		.addTag(BlockTags.WOODEN_FENCES)
		.add(JPDeco_Blocks.TAKEFENCE, JPDeco_Blocks.TAKEFENCE_Y, JPDeco_Blocks.TAKEFENCE_K);

		this.tag(BlockTags.IMPERMEABLE)
		.add(WallPanel_Blocks.WP_GLASS_white);
		
		this.tag(BlockTags.LOGS_THAT_BURN)
		.add(Wood_Blocks.SAKURA_log, Wood_Blocks.KAEDE_log, Wood_Blocks.ICHOH_log, Wood_Blocks.OAKKARE_log);
		
		this.tag(BlockTags.LOGS)
		.add(Wood_Blocks.SAKURA_log, Wood_Blocks.KAEDE_log, Wood_Blocks.ICHOH_log, Wood_Blocks.OAKKARE_log);
		
		this.tag(BlockTags.LEAVES)
		.add(Wood_Blocks.SAKURA_flow, Wood_Blocks.KAEDE_leaf, Wood_Blocks.ICHOH_leaf, Wood_Blocks.OAKKARE_leaf);

		this.tag(BlockTags.PLANKS)
		.add(Wood_Blocks.SAKURA_planks, Wood_Blocks.KAEDE_planks, Wood_Blocks.ICHOH_planks);
		
		this.tag(BlockTags.PRESSURE_PLATES)
		.addTag(BlockTags.WOODEN_PRESSURE_PLATES)
		.add(JPDeco_Blocks.TAKE_PLATE, JPDeco_Blocks.TAKE_PLATE_Y, JPDeco_Blocks.TAKE_PLATE_K);
		
		this.tag(BlockTags.SAPLINGS)
		.add(Wood_Blocks.SAKURA_nae, Wood_Blocks.KAEDE_nae, Wood_Blocks.ICHOH_nae, Wood_Blocks.OAKKARE_nae);

		this.tag(BlockTags.SLABS)
		.addTag(BlockTags.WOODEN_SLABS)
		.add(JP_Blocks.KAWARA_SH_yellow, JP_Blocks.KAWARA_SH_lime, JP_Blocks.KAWARA_SH_pink, JP_Blocks.KAWARA_SH_gray,
				JP_Blocks.KAWARA_SH_lightg, JP_Blocks.KAWARA_SH_cyan, JP_Blocks.KAWARA_SH_purple, JP_Blocks.KAWARA_SH_blue,
				JP_Blocks.KAWARA_SH_brown, JP_Blocks.KAWARA_SH_green, JP_Blocks.KAWARA_SH_red, JP_Blocks.KAWARA_SH_black,
				JP_Blocks.DIRTWALL_SH,
				JP_Blocks.SHIKKUI_SH_white, JP_Blocks.SHIKKUI_SH_orange, JP_Blocks.SHIKKUI_SH_magenta, JP_Blocks.SHIKKUI_SH_lightb,
				JP_Blocks.SHIKKUI_SH_yellow, JP_Blocks.SHIKKUI_SH_lime, JP_Blocks.SHIKKUI_SH_pink, JP_Blocks.SHIKKUI_SH_gray,
				JP_Blocks.SHIKKUI_SH_lightg, JP_Blocks.SHIKKUI_SH_cyan, JP_Blocks.SHIKKUI_SH_purple, JP_Blocks.SHIKKUI_SH_blue,
				JP_Blocks.SHIKKUI_SH_brown, JP_Blocks.SHIKKUI_SH_green, JP_Blocks.SHIKKUI_SH_red, JP_Blocks.SHIKKUI_SH_black,
				
				JP_Blocks.NAMAKO_SH_white, JP_Blocks.NAMAKO_SH_orange, JP_Blocks.NAMAKO_SH_magenta, JP_Blocks.NAMAKO_SH_lightb,
				JP_Blocks.NAMAKO_SH_yellow, JP_Blocks.NAMAKO_SH_lime, JP_Blocks.NAMAKO_SH_pink, JP_Blocks.NAMAKO_SH_gray,
				JP_Blocks.NAMAKO_SH_lightg, JP_Blocks.NAMAKO_SH_cyan, JP_Blocks.NAMAKO_SH_purple, JP_Blocks.NAMAKO_SH_blue,
				JP_Blocks.NAMAKO_SH_brown, JP_Blocks.NAMAKO_SH_green, JP_Blocks.NAMAKO_SH_red, JP_Blocks.NAMAKO_SH_black,
				JP_Blocks.NAMAKOB_SH_white, JP_Blocks.NAMAKOB_SH_orange, JP_Blocks.NAMAKOB_SH_magenta, JP_Blocks.NAMAKOB_SH_lightb,
				JP_Blocks.NAMAKOB_SH_yellow, JP_Blocks.NAMAKOB_SH_lime, JP_Blocks.NAMAKOB_SH_pink, JP_Blocks.NAMAKOB_SH_gray,
				JP_Blocks.NAMAKOB_SH_lightg, JP_Blocks.NAMAKOB_SH_cyan, JP_Blocks.NAMAKOB_SH_purple, JP_Blocks.NAMAKOB_SH_blue,
				JP_Blocks.NAMAKOB_SH_brown, JP_Blocks.NAMAKOB_SH_green, JP_Blocks.NAMAKOB_SH_red, JP_Blocks.NAMAKOB_SH_black,
				
				WallPanel_Blocks.BGC_slabhalf, WallPanel_Blocks.BDC_slabhalf, WallPanel_Blocks.BAC_slabhalf);
		
		this.tag(BlockTags.STAIRS)
		.addTag(BlockTags.WOODEN_STAIRS)
		.add(JP_Blocks.KAWARA_ST_yellow, JP_Blocks.KAWARA_ST_lime, JP_Blocks.KAWARA_ST_pink, JP_Blocks.KAWARA_ST_gray,
				JP_Blocks.KAWARA_ST_lightg, JP_Blocks.KAWARA_ST_cyan, JP_Blocks.KAWARA_ST_purple, JP_Blocks.KAWARA_ST_blue,
				JP_Blocks.KAWARA_ST_brown, JP_Blocks.KAWARA_ST_green, JP_Blocks.KAWARA_ST_red, JP_Blocks.KAWARA_ST_black,
				JP_Blocks.DIRTWALL_stairs,
				JP_Blocks.SHIKKUI_ST_white, JP_Blocks.SHIKKUI_ST_orange, JP_Blocks.SHIKKUI_ST_magenta, JP_Blocks.SHIKKUI_ST_lightb,
				JP_Blocks.SHIKKUI_ST_yellow, JP_Blocks.SHIKKUI_ST_lime, JP_Blocks.SHIKKUI_ST_pink, JP_Blocks.SHIKKUI_ST_gray,
				JP_Blocks.SHIKKUI_ST_lightg, JP_Blocks.SHIKKUI_ST_cyan, JP_Blocks.SHIKKUI_ST_purple, JP_Blocks.SHIKKUI_ST_blue,
				JP_Blocks.SHIKKUI_ST_brown, JP_Blocks.SHIKKUI_ST_green, JP_Blocks.SHIKKUI_ST_red, JP_Blocks.SHIKKUI_ST_black,
				
				JP_Blocks.NAMAKO_ST_white, JP_Blocks.NAMAKO_ST_orange, JP_Blocks.NAMAKO_ST_magenta, JP_Blocks.NAMAKO_ST_lightb,
				JP_Blocks.NAMAKO_ST_yellow, JP_Blocks.NAMAKO_ST_lime, JP_Blocks.NAMAKO_ST_pink, JP_Blocks.NAMAKO_ST_gray,
				JP_Blocks.NAMAKO_ST_lightg, JP_Blocks.NAMAKO_ST_cyan, JP_Blocks.NAMAKO_ST_purple, JP_Blocks.NAMAKO_ST_blue,
				JP_Blocks.NAMAKO_ST_brown, JP_Blocks.NAMAKO_ST_green, JP_Blocks.NAMAKO_ST_red, JP_Blocks.NAMAKO_ST_black,
				JP_Blocks.NAMAKOB_ST_white, JP_Blocks.NAMAKOB_ST_orange, JP_Blocks.NAMAKOB_ST_magenta, JP_Blocks.NAMAKOB_ST_lightb,
				JP_Blocks.NAMAKOB_ST_yellow, JP_Blocks.NAMAKOB_ST_lime, JP_Blocks.NAMAKOB_ST_pink, JP_Blocks.NAMAKOB_ST_gray,
				JP_Blocks.NAMAKOB_ST_lightg, JP_Blocks.NAMAKOB_ST_cyan, JP_Blocks.NAMAKOB_ST_purple, JP_Blocks.NAMAKOB_ST_blue,
				JP_Blocks.NAMAKOB_ST_brown, JP_Blocks.NAMAKOB_ST_green, JP_Blocks.NAMAKOB_ST_red, JP_Blocks.NAMAKOB_ST_black,
				
				WallPanel_Blocks.BRICKSTAIRS_GRA, WallPanel_Blocks.BRICKSTAIRS_DIO, WallPanel_Blocks.BRICKSTAIRS_AND);
		
		this.tag(BlockTags.STONE_BRICKS)
		.add(WallPanel_Blocks.BRICK_GRA, WallPanel_Blocks.BRICK_DIO, WallPanel_Blocks.BRICK_AND);
		
		this.tag(BlockTags.TRAPDOORS)
		.addTag(BlockTags.WOODEN_TRAPDOORS)
		.add(JPDeco_Blocks.TAKE_TRAPDOOR, JPDeco_Blocks.TAKE_TRAPDOOR_Y, JPDeco_Blocks.TAKE_TRAPDOOR_K);
		
		this.tag(BlockTags.WALLS)
		.add(Window_Blocks.WINDOW_oak, Window_Blocks.WINDOW_spruce, Window_Blocks.WINDOW_birch,
				Window_Blocks.WINDOW_jungle, Window_Blocks.WINDOW_acacia, Window_Blocks.WINDOW_darkoak,
				Window_Blocks.WINDOW_sakura, Window_Blocks.WINDOW_kaede, Window_Blocks.WINDOW_ichoh,
				Window_Blocks.WINDOWB_oak, Window_Blocks.WINDOWB_spruce, Window_Blocks.WINDOWB_birch,
				Window_Blocks.WINDOWB_jungle, Window_Blocks.WINDOWB_acacia, Window_Blocks.WINDOWB_darkoak,
				Window_Blocks.WINDOWB_sakura, Window_Blocks.WINDOWB_kaede, Window_Blocks.WINDOWB_ichoh,
				Window_Blocks.WINDOWTALLBOT_oak, Window_Blocks.WINDOWTALLBOT_spruce, Window_Blocks.WINDOWTALLBOT_birch,
				Window_Blocks.WINDOWTALLBOT_jungle, Window_Blocks.WINDOWTALLBOT_acacia, Window_Blocks.WINDOWTALLBOT_darkoak,
				Window_Blocks.WINDOWTALLBOT_sakura, Window_Blocks.WINDOWTALLBOT_kaede, Window_Blocks.WINDOWTALLBOT_ichoh,
				Window_Blocks.WINDOWTALLTOP_oak, Window_Blocks.WINDOWTALLTOP_spruce, Window_Blocks.WINDOWTALLTOP_birch,
				Window_Blocks.WINDOWTALLTOP_jungle, Window_Blocks.WINDOWTALLTOP_acacia, Window_Blocks.WINDOWTALLTOP_darkoak,
				Window_Blocks.WINDOWTALLTOP_sakura, Window_Blocks.WINDOWTALLTOP_kaede, Window_Blocks.WINDOWTALLTOP_ichoh,
				Window_Blocks.WINDOWTALL_oak, Window_Blocks.WINDOWTALL_spruce, Window_Blocks.WINDOWTALL_birch,
				Window_Blocks.WINDOWTALL_jungle, Window_Blocks.WINDOWTALL_acacia, Window_Blocks.WINDOWTALL_darkoak,
				Window_Blocks.WINDOWTALL_sakura, Window_Blocks.WINDOWTALL_kaede, Window_Blocks.WINDOWTALL_ichoh,
				
				Slidedoor_Blocks.FUSUMA_white, Slidedoor_Blocks.FUSUMA_orange, Slidedoor_Blocks.FUSUMA_magenta, Slidedoor_Blocks.FUSUMA_lightb,
				Slidedoor_Blocks.FUSUMA_yellow, Slidedoor_Blocks.FUSUMA_lime, Slidedoor_Blocks.FUSUMA_pink, Slidedoor_Blocks.FUSUMA_gray,
				Slidedoor_Blocks.FUSUMA_lightg, Slidedoor_Blocks.FUSUMA_cyan, Slidedoor_Blocks.FUSUMA_purple, Slidedoor_Blocks.FUSUMA_blue,
				Slidedoor_Blocks.FUSUMA_brown, Slidedoor_Blocks.FUSUMA_green, Slidedoor_Blocks.FUSUMA_red, Slidedoor_Blocks.FUSUMA_black,
				Slidedoor_Blocks.FUSUMAB_white, Slidedoor_Blocks.FUSUMAB_orange, Slidedoor_Blocks.FUSUMAB_magenta, Slidedoor_Blocks.FUSUMAB_lightb,
				Slidedoor_Blocks.FUSUMAB_yellow, Slidedoor_Blocks.FUSUMAB_lime, Slidedoor_Blocks.FUSUMAB_pink, Slidedoor_Blocks.FUSUMAB_gray,
				Slidedoor_Blocks.FUSUMAB_lightg, Slidedoor_Blocks.FUSUMAB_cyan, Slidedoor_Blocks.FUSUMAB_purple, Slidedoor_Blocks.FUSUMAB_blue,
				Slidedoor_Blocks.FUSUMAB_brown, Slidedoor_Blocks.FUSUMAB_green, Slidedoor_Blocks.FUSUMAB_red, Slidedoor_Blocks.FUSUMAB_black,
				
				Slidedoor_Blocks.GARASUDO, Slidedoor_Blocks.GARASUDO_SPRU, Slidedoor_Blocks.GARASUDO_BIR,
				Slidedoor_Blocks.GARASUDO_JUN, Slidedoor_Blocks.GARASUDO_ACA, Slidedoor_Blocks.GARASUDO_DOAK,
				Slidedoor_Blocks.GARASUDO_SAKU, Slidedoor_Blocks.GARASUDO_KAE, Slidedoor_Blocks.GARASUDO_ICH,
				Slidedoor_Blocks.GARASUDOB, Slidedoor_Blocks.GARASUDOB_SPRU, Slidedoor_Blocks.GARASUDOB_BIR,
				Slidedoor_Blocks.GARASUDOB_JUN, Slidedoor_Blocks.GARASUDOB_ACA, Slidedoor_Blocks.GARASUDOB_DOAK,
				Slidedoor_Blocks.GARASUDOB_SAKU, Slidedoor_Blocks.GARASUDOB_KAE, Slidedoor_Blocks.GARASUDOB_ICH,
				
				Slidedoor_Blocks.GARASUDOH, Slidedoor_Blocks.GARASUDOH_SPRU, Slidedoor_Blocks.GARASUDOH_BIR,
				Slidedoor_Blocks.GARASUDOH_JUN, Slidedoor_Blocks.GARASUDOH_ACA, Slidedoor_Blocks.GARASUDOH_DOAK,
				Slidedoor_Blocks.GARASUDOH_SAKU, Slidedoor_Blocks.GARASUDOH_KAE, Slidedoor_Blocks.GARASUDOH_ICH,
				
				Slidedoor_Blocks.SHOUJI, Slidedoor_Blocks.SHOUJI_SPRU, Slidedoor_Blocks.SHOUJI_BIR,
				Slidedoor_Blocks.SHOUJI_JUN, Slidedoor_Blocks.SHOUJI_ACA, Slidedoor_Blocks.SHOUJI_DOAK,
				Slidedoor_Blocks.SHOUJI_SAKU, Slidedoor_Blocks.SHOUJI_KAE, Slidedoor_Blocks.SHOUJI_ICH,
				Slidedoor_Blocks.SHOUJIB, Slidedoor_Blocks.SHOUJIB_SPRU, Slidedoor_Blocks.SHOUJIB_BIR,
				Slidedoor_Blocks.SHOUJIB_JUN, Slidedoor_Blocks.SHOUJIB_ACA, Slidedoor_Blocks.SHOUJIB_DOAK,
				Slidedoor_Blocks.SHOUJIB_SAKU, Slidedoor_Blocks.SHOUJIB_KAE, Slidedoor_Blocks.SHOUJIB_ICH,
				
				Slidedoor_Blocks.SHOUJIH, Slidedoor_Blocks.SHOUJIH_SPRU, Slidedoor_Blocks.SHOUJIH_BIR,
				Slidedoor_Blocks.SHOUJIH_JUN, Slidedoor_Blocks.SHOUJIH_ACA, Slidedoor_Blocks.SHOUJIH_DOAK,
				Slidedoor_Blocks.SHOUJIH_SAKU, Slidedoor_Blocks.SHOUJIH_KAE, Slidedoor_Blocks.SHOUJIH_ICH,
				Slidedoor_Blocks.SHOUJI_WIN, Slidedoor_Blocks.SHOUJI_WIN_SPRU, Slidedoor_Blocks.SHOUJI_WIN_BIR,
				Slidedoor_Blocks.SHOUJI_WIN_JUN, Slidedoor_Blocks.SHOUJI_WIN_ACA, Slidedoor_Blocks.SHOUJI_WIN_DOAK,
				Slidedoor_Blocks.SHOUJI_WIN_SAKU, Slidedoor_Blocks.SHOUJI_WIN_KAE, Slidedoor_Blocks.SHOUJI_WIN_ICH,
				Slidedoor_Blocks.SHOUJI_WINR, Slidedoor_Blocks.SHOUJI_WINR_SPRU, Slidedoor_Blocks.SHOUJI_WINR_BIR,
				Slidedoor_Blocks.SHOUJI_WINR_JUN, Slidedoor_Blocks.SHOUJI_WINR_ACA, Slidedoor_Blocks.SHOUJI_WINR_DOAK,
				Slidedoor_Blocks.SHOUJI_WINR_SAKU, Slidedoor_Blocks.SHOUJI_WINR_KAE, Slidedoor_Blocks.SHOUJI_WINR_ICH,
				
				Ranma_Blocks.RANMA_oak, Ranma_Blocks.RANMA_spruce, Ranma_Blocks.RANMA_birch,
				Ranma_Blocks.RANMA_jungle, Ranma_Blocks.RANMA_acacia, Ranma_Blocks.RANMA_darkoak,
				Ranma_Blocks.RANMA_sakura, Ranma_Blocks.RANMA_kaede, Ranma_Blocks.RANMA_ichoh,
				Ranma_Blocks.RANMAB_oak, Ranma_Blocks.RANMAB_spruce, Ranma_Blocks.RANMAB_birch,
				Ranma_Blocks.RANMAB_jungle, Ranma_Blocks.RANMAB_acacia, Ranma_Blocks.RANMAB_darkoak,
				Ranma_Blocks.RANMAB_sakura, Ranma_Blocks.RANMAB_kaede, Ranma_Blocks.RANMAB_ichoh,
				Ranma_Blocks.RANMAC_oak, Ranma_Blocks.RANMAC_spruce, Ranma_Blocks.RANMAC_birch,
				Ranma_Blocks.RANMAC_jungle, Ranma_Blocks.RANMAC_acacia, Ranma_Blocks.RANMAC_darkoak,
				Ranma_Blocks.RANMAC_sakura, Ranma_Blocks.RANMAC_kaede, Ranma_Blocks.RANMAC_ichoh,
				
				Ranma_Blocks.KANKI_oak, Ranma_Blocks.KANKI_spruce, Ranma_Blocks.KANKI_birch,
				Ranma_Blocks.KANKI_jungle, Ranma_Blocks.KANKI_acacia, Ranma_Blocks.KANKI_darkoak,
				Ranma_Blocks.KANKI_sakura, Ranma_Blocks.KANKI_kaede, Ranma_Blocks.KANKI_ichoh,
				Ranma_Blocks.KOUSHI_oak, Ranma_Blocks.KOUSHI_spruce, Ranma_Blocks.KOUSHI_birch,
				Ranma_Blocks.KOUSHI_jungle, Ranma_Blocks.KOUSHI_acacia, Ranma_Blocks.KOUSHI_darkoak,
				Ranma_Blocks.KOUSHI_sakura, Ranma_Blocks.KOUSHI_kaede, Ranma_Blocks.KOUSHI_ichoh,
				Ranma_Blocks.KOUSHIB_oak, Ranma_Blocks.KOUSHIB_spruce, Ranma_Blocks.KOUSHIB_birch,
				Ranma_Blocks.KOUSHIB_jungle, Ranma_Blocks.KOUSHIB_acacia, Ranma_Blocks.KOUSHIB_darkoak,
				Ranma_Blocks.KOUSHIB_sakura, Ranma_Blocks.KOUSHIB_kaede, Ranma_Blocks.KOUSHIB_ichoh,
				
				JP_Blocks.DIRTWALL_WALL,
				JP_Blocks.SHIKKUI_WALL_white, JP_Blocks.SHIKKUI_WALL_orange, JP_Blocks.SHIKKUI_WALL_magenta, JP_Blocks.SHIKKUI_WALL_lightb,
				JP_Blocks.SHIKKUI_WALL_yellow, JP_Blocks.SHIKKUI_WALL_lime, JP_Blocks.SHIKKUI_WALL_pink, JP_Blocks.SHIKKUI_WALL_gray,
				JP_Blocks.SHIKKUI_WALL_lightg, JP_Blocks.SHIKKUI_WALL_cyan, JP_Blocks.SHIKKUI_WALL_purple, JP_Blocks.SHIKKUI_WALL_blue,
				JP_Blocks.SHIKKUI_WALL_brown,JP_Blocks.SHIKKUI_WALL_green, JP_Blocks.SHIKKUI_WALL_red, JP_Blocks.SHIKKUI_WALL_black,
				JP_Blocks.NAMAKO_WALL_white, JP_Blocks.NAMAKO_WALL_orange, JP_Blocks.NAMAKO_WALL_magenta, JP_Blocks.NAMAKO_WALL_lightb,
				JP_Blocks.NAMAKO_WALL_yellow, JP_Blocks.NAMAKO_WALL_lime, JP_Blocks.NAMAKO_WALL_pink, JP_Blocks.NAMAKO_WALL_gray, 
				JP_Blocks.NAMAKO_WALL_lightg, JP_Blocks.NAMAKO_WALL_cyan, JP_Blocks.NAMAKO_WALL_purple, JP_Blocks.NAMAKO_WALL_blue,
				JP_Blocks.NAMAKO_WALL_brown, JP_Blocks.NAMAKO_WALL_green, JP_Blocks.NAMAKO_WALL_red, JP_Blocks.NAMAKO_WALL_black,
				JP_Blocks.NAMAKOB_WALL_white, JP_Blocks.NAMAKOB_WALL_orange, JP_Blocks.NAMAKOB_WALL_magenta, JP_Blocks.NAMAKOB_WALL_lightb,
				JP_Blocks.NAMAKOB_WALL_yellow, JP_Blocks.NAMAKOB_WALL_lime, JP_Blocks.NAMAKOB_WALL_pink, JP_Blocks.NAMAKOB_WALL_gray,
				JP_Blocks.NAMAKOB_WALL_lightg, JP_Blocks.NAMAKOB_WALL_cyan, JP_Blocks.NAMAKOB_WALL_purple, JP_Blocks.NAMAKOB_WALL_blue,
				JP_Blocks.NAMAKOB_WALL_brown, JP_Blocks.NAMAKOB_WALL_green, JP_Blocks.NAMAKOB_WALL_red, JP_Blocks.NAMAKOB_WALL_black,
				JP_Blocks.DIRTWALL_SAMA,
				JP_Blocks.SHIKKUI_SAMA_white, JP_Blocks.SHIKKUI_SAMA_orange, JP_Blocks.SHIKKUI_SAMA_magenta, JP_Blocks.SHIKKUI_SAMA_lightb, 
				JP_Blocks.SHIKKUI_SAMA_yellow, JP_Blocks.SHIKKUI_SAMA_lime, JP_Blocks.SHIKKUI_SAMA_pink, JP_Blocks.SHIKKUI_SAMA_gray,
				JP_Blocks.SHIKKUI_SAMA_lightg, JP_Blocks.SHIKKUI_SAMA_cyan, JP_Blocks.SHIKKUI_SAMA_purple, JP_Blocks.SHIKKUI_SAMA_blue,
				JP_Blocks.SHIKKUI_SAMA_brown, JP_Blocks.SHIKKUI_SAMA_green, JP_Blocks.SHIKKUI_SAMA_red, JP_Blocks.SHIKKUI_SAMA_black,
				JP_Blocks.KAWARA_WALL_white, JP_Blocks.KAWARA_WALL_orange, JP_Blocks.KAWARA_WALL_magenta, JP_Blocks.KAWARA_WALL_lightb,
				JP_Blocks.KAWARA_WALL_yellow, JP_Blocks.KAWARA_WALL_lime,JP_Blocks.KAWARA_WALL_pink, JP_Blocks.KAWARA_WALL_gray,
				JP_Blocks.KAWARA_WALL_lightg, JP_Blocks.KAWARA_WALL_cyan, JP_Blocks.KAWARA_WALL_purple, JP_Blocks.KAWARA_WALL_blue,
				JP_Blocks.KAWARA_WALL_brown, JP_Blocks.KAWARA_WALL_green, JP_Blocks.KAWARA_WALL_red, JP_Blocks.KAWARA_WALL_black, 
	
				Garden_Blocks.IKEGAKI, Garden_Blocks.IKEGAKI_spruce, Garden_Blocks.IKEGAKI_birch, 
				Garden_Blocks.IKEGAKI_jungle, Garden_Blocks.IKEGAKI_acacia, Garden_Blocks.IKEGAKI_darkoak,
				Garden_Blocks.IKEGAKI_sakura, Garden_Blocks.IKEGAKI_kaede, Garden_Blocks.IKEGAKI_ichoh, Garden_Blocks.IKEGAKI_kare, 
				Garden_Blocks.IKEGAKILONG, Garden_Blocks.IKEGAKILONG_spruce, Garden_Blocks.IKEGAKILONG_birch, 
				Garden_Blocks.IKEGAKILONG_jungle, Garden_Blocks.IKEGAKILONG_acacia, Garden_Blocks.IKEGAKILONG_darkoak,
				Garden_Blocks.IKEGAKILONG_sakura, Garden_Blocks.IKEGAKILONG_kaede, Garden_Blocks.IKEGAKILONG_ichoh, Garden_Blocks.IKEGAKILONG_kare,
				
				Garden_Blocks.ITABEI, Garden_Blocks.ITABEI_spruce, Garden_Blocks.ITABEI_birch, 
				Garden_Blocks.ITABEI_jungle, Garden_Blocks.ITABEI_acacia, Garden_Blocks.ITABEI_darkoak,
				Garden_Blocks.ITABEI_sakura, Garden_Blocks.ITABEI_kaede, Garden_Blocks.ITABEI_ichoh, 
				Garden_Blocks.KIDO, Garden_Blocks.KIDO_spruce, Garden_Blocks.KIDO_birch, 
				Garden_Blocks.KIDO_jungle, Garden_Blocks.KIDO_acacia, Garden_Blocks.KIDO_darkoak,
				Garden_Blocks.KIDO_sakura, Garden_Blocks.KIDO_kaede, Garden_Blocks.KIDO_ichoh);
		
		this.tag(BlockTags.WOODEN_BUTTONS)
		.add(Wood_Blocks.SAKURA_BUTTON, Wood_Blocks.KAEDE_BUTTON, Wood_Blocks.ICHOH_BUTTON);
		
		this.tag(BlockTags.WOODEN_DOORS)
		.add(Wood_Blocks.DOOR_SAKURA, Wood_Blocks.DOOR_KAEDE, Wood_Blocks.DOOR_ICHOH);
		
		this.tag(BlockTags.WOODEN_FENCES)
		.add(Wood_Blocks.SAKURA_FENCE, Wood_Blocks.KAEDE_FENCE, Wood_Blocks.ICHOH_FENCE);
		
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
		.add(Wood_Blocks.SAKURA_PLATE, Wood_Blocks.KAEDE_PLATE, Wood_Blocks.ICHOH_PLATE);
		
		this.tag(BlockTags.WOODEN_SLABS)
		.add(Wood_Blocks.SAKURA_slabhalf, Wood_Blocks.KAEDE_slabhalf, Wood_Blocks.ICHOH_slabhalf);
		
		this.tag(BlockTags.WOODEN_STAIRS)
		.add(Wood_Blocks.SAKURA_stairs, Wood_Blocks.KAEDE_stairs, Wood_Blocks.ICHOH_stairs);
		
		this.tag(BlockTags.WOODEN_TRAPDOORS)
		.add(Wood_Blocks.SAKURA_TRAPDOOR, Wood_Blocks.KAEDE_TRAPDOOR, Wood_Blocks.ICHOH_TRAPDOOR);
		
		
		/** MINEABLE **/
	}
	
	public String getName() {
		return "ChinjufuMod Block Tags";
	}
}
