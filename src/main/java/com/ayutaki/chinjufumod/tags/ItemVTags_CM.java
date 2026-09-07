package com.ayutaki.chinjufumod.tags;

import java.util.concurrent.CompletableFuture;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Items_Armor;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

public class ItemVTags_CM extends ItemTagsProvider {

	public ItemVTags_CM(PackOutput outPut, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTag) {
		super(outPut, provider, blockTag, ChinjufuMod.MOD_ID);
	}
	
	/* addTags */
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.BUTTONS)
		.addTag(ItemTags.WOODEN_BUTTONS)
		.add(Items_Wadeco.TAKE_BUTTON.get(), Items_Wadeco.TAKE_BUTTON_Y.get(), Items_Wadeco.TAKE_BUTTON_K.get());
		
		this.tag(ItemTags.DIRT).add(Items_Seasonal.FALL_LEAF.get());
		
		this.tag(ItemTags.DOORS)
		.addTag(ItemTags.WOODEN_DOORS)
		.add(Items_Wadeco.TAKEDOOR.get(), Items_Wadeco.TAKEDOOR_Y.get(), Items_Wadeco.TAKEDOOR_K.get());
		
		this.tag(ItemTags.FENCE_GATES)
		.add(Items_Wadeco.TAKEFENCEGATE.get(), Items_Wadeco.TAKEFENCEGATE_Y.get(), Items_Wadeco.TAKEFENCEGATE_K.get(),
				Items_Seasonal.SAKURA_FGATE.get(), Items_Seasonal.KAEDE_FGATE.get(), Items_Seasonal.ICHOH_FGATE.get());
		
		this.tag(ItemTags.FENCES)
		.addTag(ItemTags.WOODEN_FENCES)
		.add(Items_Wadeco.TAKEFENCE.get(), Items_Wadeco.TAKEFENCE_Y.get(), Items_Wadeco.TAKEFENCE_K.get());

		this.tag(ItemTags.LOGS_THAT_BURN)
		.add(Items_Seasonal.SAKURA_log.get(), Items_Seasonal.KAEDE_log.get(), Items_Seasonal.ICHOH_log.get());
	
		this.tag(ItemTags.LOGS)
		.add(Items_Seasonal.SAKURA_log.get(), Items_Seasonal.KAEDE_log.get(), Items_Seasonal.ICHOH_log.get());
	
		this.tag(ItemTags.LEAVES)
		.add(Items_Seasonal.SAKURA_flow.get(), Items_Seasonal.KAEDE_leaf.get(), Items_Seasonal.ICHOH_leaf.get(), Items_Seasonal.OAKKARE_leaf.get());

		this.tag(ItemTags.PLANKS)
		.add(Items_Seasonal.SAKURA_planks.get(), Items_Seasonal.KAEDE_planks.get(), Items_Seasonal.ICHOH_planks.get());

		this.tag(ItemTags.SAPLINGS)
		.add(Items_Seasonal.SAKURA_nae.get(), Items_Seasonal.KAEDE_nae.get(), Items_Seasonal.ICHOH_nae.get(), Items_Seasonal.OAKKARE_nae.get());

		this.tag(ItemTags.SLABS)
		.addTag(ItemTags.WOODEN_SLABS)
		.add(Items_Wablock.KAWARA_SH_yellow.get(), Items_Wablock.KAWARA_SH_lime.get(), Items_Wablock.KAWARA_SH_pink.get(), Items_Wablock.KAWARA_SH_gray.get(),
				Items_Wablock.KAWARA_SH_lightg.get(), Items_Wablock.KAWARA_SH_cyan.get(), Items_Wablock.KAWARA_SH_purple.get(), Items_Wablock.KAWARA_SH_blue.get(),
				Items_Wablock.KAWARA_SH_brown.get(), Items_Wablock.KAWARA_SH_green.get(), Items_Wablock.KAWARA_SH_red.get(), Items_Wablock.KAWARA_SH_black.get(),
				Items_Wablock.DIRTWALL_SH.get(),
				Items_Wablock.SHIKKUI_SH_white.get(), Items_Wablock.SHIKKUI_SH_orange.get(), Items_Wablock.SHIKKUI_SH_magenta.get(), Items_Wablock.SHIKKUI_SH_lightb.get(),
				Items_Wablock.SHIKKUI_SH_yellow.get(), Items_Wablock.SHIKKUI_SH_lime.get(), Items_Wablock.SHIKKUI_SH_pink.get(), Items_Wablock.SHIKKUI_SH_gray.get(),
				Items_Wablock.SHIKKUI_SH_lightg.get(), Items_Wablock.SHIKKUI_SH_cyan.get(), Items_Wablock.SHIKKUI_SH_purple.get(), Items_Wablock.SHIKKUI_SH_blue.get(),
				Items_Wablock.SHIKKUI_SH_brown.get(), Items_Wablock.SHIKKUI_SH_green.get(), Items_Wablock.SHIKKUI_SH_red.get(), Items_Wablock.SHIKKUI_SH_black.get(),
				
				Items_Wablock.NAMAKO_SH_white.get(), Items_Wablock.NAMAKO_SH_orange.get(), Items_Wablock.NAMAKO_SH_magenta.get(), Items_Wablock.NAMAKO_SH_lightb.get(),
				Items_Wablock.NAMAKO_SH_yellow.get(), Items_Wablock.NAMAKO_SH_lime.get(), Items_Wablock.NAMAKO_SH_pink.get(), Items_Wablock.NAMAKO_SH_gray.get(),
				Items_Wablock.NAMAKO_SH_lightg.get(), Items_Wablock.NAMAKO_SH_cyan.get(), Items_Wablock.NAMAKO_SH_purple.get(), Items_Wablock.NAMAKO_SH_blue.get(),
				Items_Wablock.NAMAKO_SH_brown.get(), Items_Wablock.NAMAKO_SH_green.get(), Items_Wablock.NAMAKO_SH_red.get(), Items_Wablock.NAMAKO_SH_black.get(),
				Items_Wablock.NAMAKOB_SH_white.get(), Items_Wablock.NAMAKOB_SH_orange.get(), Items_Wablock.NAMAKOB_SH_magenta.get(), Items_Wablock.NAMAKOB_SH_lightb.get(),
				Items_Wablock.NAMAKOB_SH_yellow.get(), Items_Wablock.NAMAKOB_SH_lime.get(), Items_Wablock.NAMAKOB_SH_pink.get(), Items_Wablock.NAMAKOB_SH_gray.get(),
				Items_Wablock.NAMAKOB_SH_lightg.get(), Items_Wablock.NAMAKOB_SH_cyan.get(), Items_Wablock.NAMAKOB_SH_purple.get(), Items_Wablock.NAMAKOB_SH_blue.get(),
				Items_Wablock.NAMAKOB_SH_brown.get(), Items_Wablock.NAMAKOB_SH_green.get(), Items_Wablock.NAMAKOB_SH_red.get(), Items_Wablock.NAMAKOB_SH_black.get(),
				
				Items_WallPanel.BGC_slabhalf.get(), Items_WallPanel.BDC_slabhalf.get(), Items_WallPanel.BAC_slabhalf.get());
	
		this.tag(ItemTags.STAIRS)
		.addTag(ItemTags.WOODEN_STAIRS)
		.add(Items_Wablock.KAWARA_ST_yellow.get(), Items_Wablock.KAWARA_ST_lime.get(), Items_Wablock.KAWARA_ST_pink.get(), Items_Wablock.KAWARA_ST_gray.get(),
				Items_Wablock.KAWARA_ST_lightg.get(), Items_Wablock.KAWARA_ST_cyan.get(), Items_Wablock.KAWARA_ST_purple.get(), Items_Wablock.KAWARA_ST_blue.get(),
				Items_Wablock.KAWARA_ST_brown.get(), Items_Wablock.KAWARA_ST_green.get(), Items_Wablock.KAWARA_ST_red.get(), Items_Wablock.KAWARA_ST_black.get(),
				Items_Wablock.DIRTWALL_stairs.get(),
				Items_Wablock.SHIKKUI_ST_white.get(), Items_Wablock.SHIKKUI_ST_orange.get(), Items_Wablock.SHIKKUI_ST_magenta.get(), Items_Wablock.SHIKKUI_ST_lightb.get(),
				Items_Wablock.SHIKKUI_ST_yellow.get(), Items_Wablock.SHIKKUI_ST_lime.get(), Items_Wablock.SHIKKUI_ST_pink.get(), Items_Wablock.SHIKKUI_ST_gray.get(),
				Items_Wablock.SHIKKUI_ST_lightg.get(), Items_Wablock.SHIKKUI_ST_cyan.get(), Items_Wablock.SHIKKUI_ST_purple.get(), Items_Wablock.SHIKKUI_ST_blue.get(),
				Items_Wablock.SHIKKUI_ST_brown.get(), Items_Wablock.SHIKKUI_ST_green.get(), Items_Wablock.SHIKKUI_ST_red.get(), Items_Wablock.SHIKKUI_ST_black.get(),
				
				Items_Wablock.NAMAKO_ST_white.get(), Items_Wablock.NAMAKO_ST_orange.get(), Items_Wablock.NAMAKO_ST_magenta.get(), Items_Wablock.NAMAKO_ST_lightb.get(),
				Items_Wablock.NAMAKO_ST_yellow.get(), Items_Wablock.NAMAKO_ST_lime.get(), Items_Wablock.NAMAKO_ST_pink.get(), Items_Wablock.NAMAKO_ST_gray.get(),
				Items_Wablock.NAMAKO_ST_lightg.get(), Items_Wablock.NAMAKO_ST_cyan.get(), Items_Wablock.NAMAKO_ST_purple.get(), Items_Wablock.NAMAKO_ST_blue.get(),
				Items_Wablock.NAMAKO_ST_brown.get(), Items_Wablock.NAMAKO_ST_green.get(), Items_Wablock.NAMAKO_ST_red.get(), Items_Wablock.NAMAKO_ST_black.get(),
				Items_Wablock.NAMAKOB_ST_white.get(), Items_Wablock.NAMAKOB_ST_orange.get(), Items_Wablock.NAMAKOB_ST_magenta.get(), Items_Wablock.NAMAKOB_ST_lightb.get(),
				Items_Wablock.NAMAKOB_ST_yellow.get(), Items_Wablock.NAMAKOB_ST_lime.get(), Items_Wablock.NAMAKOB_ST_pink.get(), Items_Wablock.NAMAKOB_ST_gray.get(),
				Items_Wablock.NAMAKOB_ST_lightg.get(), Items_Wablock.NAMAKOB_ST_cyan.get(), Items_Wablock.NAMAKOB_ST_purple.get(), Items_Wablock.NAMAKOB_ST_blue.get(),
				Items_Wablock.NAMAKOB_ST_brown.get(), Items_Wablock.NAMAKOB_ST_green.get(), Items_Wablock.NAMAKOB_ST_red.get(), Items_Wablock.NAMAKOB_ST_black.get(),
				
				Items_WallPanel.BRICKSTAIRS_GRA.get(), Items_WallPanel.BRICKSTAIRS_DIO.get(), Items_WallPanel.BRICKSTAIRS_AND.get());
	
		this.tag(ItemTags.STONE_BRICKS)
		.add(Items_WallPanel.BRICK_GRA.get(), Items_WallPanel.BRICK_DIO.get(), Items_WallPanel.BRICK_AND.get());
	
		this.tag(ItemTags.TRAPDOORS)
		.addTag(ItemTags.WOODEN_TRAPDOORS)
		.add(Items_Wadeco.TAKE_TRAPDOOR.get(), Items_Wadeco.TAKE_TRAPDOOR_Y.get(), Items_Wadeco.TAKE_TRAPDOOR_K.get());
		
		this.tag(ItemTags.WALLS)
		.add(Items_Chinjufu.WINDOW_oak.get(), Items_Chinjufu.WINDOW_spruce.get(), Items_Chinjufu.WINDOW_birch.get(),
				Items_Chinjufu.WINDOW_jungle.get(), Items_Chinjufu.WINDOW_acacia.get(), Items_Chinjufu.WINDOW_darkoak.get(),
				Items_Chinjufu.WINDOW_mangrove.get(), Items_Chinjufu.WINDOW_cherry.get(), Items_Chinjufu.WINDOW_paleoak.get(),
				Items_Seasonal.WINDOW_sakura.get(), Items_Seasonal.WINDOW_kaede.get(), Items_Seasonal.WINDOW_ichoh.get(),
				Items_Chinjufu.WINDOWB_oak.get(), Items_Chinjufu.WINDOWB_spruce.get(), Items_Chinjufu.WINDOWB_birch.get(),
				Items_Chinjufu.WINDOWB_jungle.get(), Items_Chinjufu.WINDOWB_acacia.get(), Items_Chinjufu.WINDOWB_darkoak.get(),
				Items_Chinjufu.WINDOWB_mangrove.get(), Items_Chinjufu.WINDOWB_cherry.get(), Items_Chinjufu.WINDOWB_paleoak.get(),
				Items_Seasonal.WINDOWB_sakura.get(), Items_Seasonal.WINDOWB_kaede.get(), Items_Seasonal.WINDOWB_ichoh.get(),
				Items_Chinjufu.WINDOWTALLBOT_oak.get(), Items_Chinjufu.WINDOWTALLBOT_spruce.get(), Items_Chinjufu.WINDOWTALLBOT_birch.get(),
				Items_Chinjufu.WINDOWTALLBOT_jungle.get(), Items_Chinjufu.WINDOWTALLBOT_acacia.get(), Items_Chinjufu.WINDOWTALLBOT_darkoak.get(),
				Items_Chinjufu.WINDOWTALLBOT_mangrove.get(), Items_Chinjufu.WINDOWTALLBOT_cherry.get(), Items_Chinjufu.WINDOWTALLBOT_paleoak.get(),
				Items_Chinjufu.WINDOWTALL_oak.get(), Items_Chinjufu.WINDOWTALL_spruce.get(), Items_Chinjufu.WINDOWTALL_birch.get(),
				Items_Chinjufu.WINDOWTALL_jungle.get(), Items_Chinjufu.WINDOWTALL_acacia.get(), Items_Chinjufu.WINDOWTALL_darkoak.get(),
				Items_Chinjufu.WINDOWTALL_mangrove.get(), Items_Chinjufu.WINDOWTALL_cherry.get(), Items_Chinjufu.WINDOWTALL_paleoak.get(),
				Items_Seasonal.WINDOWTALL_sakura.get(), Items_Seasonal.WINDOWTALL_kaede.get(), Items_Seasonal.WINDOWTALL_ichoh.get(),
				
				Items_Wadeco.FUSUMA_white.get(), Items_Wadeco.FUSUMA_orange.get(), Items_Wadeco.FUSUMA_magenta.get(), Items_Wadeco.FUSUMA_lightb.get(),
				Items_Wadeco.FUSUMA_yellow.get(), Items_Wadeco.FUSUMA_lime.get(), Items_Wadeco.FUSUMA_pink.get(), Items_Wadeco.FUSUMA_gray.get(),
				Items_Wadeco.FUSUMA_lightg.get(), Items_Wadeco.FUSUMA_cyan.get(), Items_Wadeco.FUSUMA_purple.get(), Items_Wadeco.FUSUMA_blue.get(),
				Items_Wadeco.FUSUMA_brown.get(), Items_Wadeco.FUSUMA_green.get(), Items_Wadeco.FUSUMA_red.get(), Items_Wadeco.FUSUMA_black.get(),
				Items_Wadeco.FUSUMAB_white.get(), Items_Wadeco.FUSUMAB_orange.get(), Items_Wadeco.FUSUMAB_magenta.get(), Items_Wadeco.FUSUMAB_lightb.get(),
				Items_Wadeco.FUSUMAB_yellow.get(), Items_Wadeco.FUSUMAB_lime.get(), Items_Wadeco.FUSUMAB_pink.get(), Items_Wadeco.FUSUMAB_gray.get(),
				Items_Wadeco.FUSUMAB_lightg.get(), Items_Wadeco.FUSUMAB_cyan.get(), Items_Wadeco.FUSUMAB_purple.get(), Items_Wadeco.FUSUMAB_blue.get(),
				Items_Wadeco.FUSUMAB_brown.get(), Items_Wadeco.FUSUMAB_green.get(), Items_Wadeco.FUSUMAB_red.get(), Items_Wadeco.FUSUMAB_black.get(),
				
				Items_Wadeco.GARASUDO.get(), Items_Wadeco.GARASUDO_SPRU.get(), Items_Wadeco.GARASUDO_BIR.get(),
				Items_Wadeco.GARASUDO_JUN.get(), Items_Wadeco.GARASUDO_ACA.get(), Items_Wadeco.GARASUDO_DOAK.get(),
				Items_Wadeco.GARASUDO_MANGROVE.get(), Items_Wadeco.GARASUDO_CHERRY.get(), Items_Wadeco.GARASUDO_PALEOAK.get(),
				Items_Seasonal.GARASUDO_SAKU.get(), Items_Seasonal.GARASUDO_KAE.get(), Items_Seasonal.GARASUDO_ICH.get(),
				Items_Wadeco.GARASUDOB.get(), Items_Wadeco.GARASUDOB_SPRU.get(), Items_Wadeco.GARASUDOB_BIR.get(),
				Items_Wadeco.GARASUDOB_JUN.get(), Items_Wadeco.GARASUDOB_ACA.get(), Items_Wadeco.GARASUDOB_DOAK.get(),
				Items_Wadeco.GARASUDOB_MANGROVE.get(), Items_Wadeco.GARASUDOB_CHERRY.get(), Items_Wadeco.GARASUDOB_PALEOAK.get(),
				Items_Seasonal.GARASUDOB_SAKU.get(), Items_Seasonal.GARASUDOB_KAE.get(), Items_Seasonal.GARASUDOB_ICH.get(),
				
				Items_Wadeco.GARASUDOH.get(), Items_Wadeco.GARASUDOH_SPRU.get(), Items_Wadeco.GARASUDOH_BIR.get(),
				Items_Wadeco.GARASUDOH_JUN.get(), Items_Wadeco.GARASUDOH_ACA.get(), Items_Wadeco.GARASUDOH_DOAK.get(),
				Items_Wadeco.GARASUDOH_MANGROVE.get(), Items_Wadeco.GARASUDOH_CHERRY.get(), Items_Wadeco.GARASUDOH_PALEOAK.get(),
				Items_Seasonal.GARASUDOH_SAKU.get(), Items_Seasonal.GARASUDOH_KAE.get(), Items_Seasonal.GARASUDOH_ICH.get(),
				
				Items_Wadeco.SHOUJI.get(), Items_Wadeco.SHOUJI_SPRU.get(), Items_Wadeco.SHOUJI_BIR.get(),
				Items_Wadeco.SHOUJI_JUN.get(), Items_Wadeco.SHOUJI_ACA.get(), Items_Wadeco.SHOUJI_DOAK.get(),
				Items_Wadeco.SHOUJI_MANGROVE.get(), Items_Wadeco.SHOUJI_CHERRY.get(), Items_Wadeco.SHOUJI_PALEOAK.get(),
				Items_Seasonal.SHOUJI_SAKU.get(), Items_Seasonal.SHOUJI_KAE.get(), Items_Seasonal.SHOUJI_ICH.get(),
				Items_Wadeco.SHOUJIB.get(), Items_Wadeco.SHOUJIB_SPRU.get(), Items_Wadeco.SHOUJIB_BIR.get(),
				Items_Wadeco.SHOUJIB_JUN.get(), Items_Wadeco.SHOUJIB_ACA.get(), Items_Wadeco.SHOUJIB_DOAK.get(),
				Items_Wadeco.SHOUJIB_MANGROVE.get(), Items_Wadeco.SHOUJIB_CHERRY.get(), Items_Wadeco.SHOUJIB_PALEOAK.get(),
				Items_Seasonal.SHOUJIB_SAKU.get(), Items_Seasonal.SHOUJIB_KAE.get(), Items_Seasonal.SHOUJIB_ICH.get(),
				
				Items_Wadeco.SHOUJIH.get(), Items_Wadeco.SHOUJIH_SPRU.get(), Items_Wadeco.SHOUJIH_BIR.get(),
				Items_Wadeco.SHOUJIH_JUN.get(), Items_Wadeco.SHOUJIH_ACA.get(), Items_Wadeco.SHOUJIH_DOAK.get(),
				Items_Wadeco.SHOUJIH_MANGROVE.get(), Items_Wadeco.SHOUJIH_CHERRY.get(), Items_Wadeco.SHOUJIH_PALEOAK.get(),
				Items_Seasonal.SHOUJIH_SAKU.get(), Items_Seasonal.SHOUJIH_KAE.get(), Items_Seasonal.SHOUJIH_ICH.get(),
				Items_Wadeco.SHOUJI_WIN.get(), Items_Wadeco.SHOUJI_WIN_SPRU.get(), Items_Wadeco.SHOUJI_WIN_BIR.get(),
				Items_Wadeco.SHOUJI_WIN_JUN.get(), Items_Wadeco.SHOUJI_WIN_ACA.get(), Items_Wadeco.SHOUJI_WIN_DOAK.get(),
				Items_Wadeco.SHOUJI_WIN_MANGROVE.get(), Items_Wadeco.SHOUJI_WIN_CHERRY.get(), Items_Wadeco.SHOUJI_WIN_PALEOAK.get(),
				Items_Seasonal.SHOUJI_WIN_SAKU.get(), Items_Seasonal.SHOUJI_WIN_KAE.get(), Items_Seasonal.SHOUJI_WIN_ICH.get(),
			
				Items_Wadeco.RANMA_oak.get(), Items_Wadeco.RANMA_spruce.get(), Items_Wadeco.RANMA_birch.get(),
				Items_Wadeco.RANMA_jungle.get(), Items_Wadeco.RANMA_acacia.get(), Items_Wadeco.RANMA_darkoak.get(),
				Items_Wadeco.RANMA_mangrove.get(), Items_Wadeco.RANMA_cherry.get(), Items_Wadeco.RANMA_paleoak.get(),
				Items_Seasonal.RANMA_sakura.get(), Items_Seasonal.RANMA_kaede.get(), Items_Seasonal.RANMA_ichoh.get(),
				Items_Wadeco.RANMAB_oak.get(), Items_Wadeco.RANMAB_spruce.get(), Items_Wadeco.RANMAB_birch.get(),
				Items_Wadeco.RANMAB_jungle.get(), Items_Wadeco.RANMAB_acacia.get(), Items_Wadeco.RANMAB_darkoak.get(),
				Items_Wadeco.RANMAB_mangrove.get(), Items_Wadeco.RANMAB_cherry.get(), Items_Wadeco.RANMAB_paleoak.get(),
				Items_Seasonal.RANMAB_sakura.get(), Items_Seasonal.RANMAB_kaede.get(), Items_Seasonal.RANMAB_ichoh.get(),
				Items_Wadeco.RANMAC_oak.get(), Items_Wadeco.RANMAC_spruce.get(), Items_Wadeco.RANMAC_birch.get(),
				Items_Wadeco.RANMAC_jungle.get(), Items_Wadeco.RANMAC_acacia.get(), Items_Wadeco.RANMAC_darkoak.get(),
				Items_Wadeco.RANMAC_mangrove.get(), Items_Wadeco.RANMAC_cherry.get(), Items_Wadeco.RANMAC_paleoak.get(),
				Items_Seasonal.RANMAC_sakura.get(), Items_Seasonal.RANMAC_kaede.get(), Items_Seasonal.RANMAC_ichoh.get(),
				
				Items_Wadeco.KANKI_oak.get(), Items_Wadeco.KANKI_spruce.get(), Items_Wadeco.KANKI_birch.get(),
				Items_Wadeco.KANKI_jungle.get(), Items_Wadeco.KANKI_acacia.get(), Items_Wadeco.KANKI_darkoak.get(),
				Items_Wadeco.KANKI_mangrove.get(), Items_Wadeco.KANKI_cherry.get(), Items_Wadeco.KANKI_paleoak.get(),
				Items_Seasonal.KANKI_sakura.get(), Items_Seasonal.KANKI_kaede.get(), Items_Seasonal.KANKI_ichoh.get(),
				Items_Wadeco.KOUSHI_oak.get(), Items_Wadeco.KOUSHI_spruce.get(), Items_Wadeco.KOUSHI_birch.get(),
				Items_Wadeco.KOUSHI_jungle.get(), Items_Wadeco.KOUSHI_acacia.get(), Items_Wadeco.KOUSHI_darkoak.get(),
				Items_Wadeco.KOUSHI_mangrove.get(), Items_Wadeco.KOUSHI_cherry.get(), Items_Wadeco.KOUSHI_paleoak.get(),
				Items_Seasonal.KOUSHI_sakura.get(), Items_Seasonal.KOUSHI_kaede.get(), Items_Seasonal.KOUSHI_ichoh.get(),
				Items_Wadeco.KOUSHIB_oak.get(), Items_Wadeco.KOUSHIB_spruce.get(), Items_Wadeco.KOUSHIB_birch.get(),
				Items_Wadeco.KOUSHIB_jungle.get(), Items_Wadeco.KOUSHIB_acacia.get(), Items_Wadeco.KOUSHIB_darkoak.get(),
				Items_Wadeco.KOUSHIB_mangrove.get(), Items_Wadeco.KOUSHIB_cherry.get(), Items_Wadeco.KOUSHIB_paleoak.get(),
				Items_Seasonal.KOUSHIB_sakura.get(), Items_Seasonal.KOUSHIB_kaede.get(), Items_Seasonal.KOUSHIB_ichoh.get(),
				
				Items_Wablock.DIRTWALL_WALL.get(),
				Items_Wablock.SHIKKUI_WALL_white.get(), Items_Wablock.SHIKKUI_WALL_orange.get(), Items_Wablock.SHIKKUI_WALL_magenta.get(), Items_Wablock.SHIKKUI_WALL_lightb.get(),
				Items_Wablock.SHIKKUI_WALL_yellow.get(), Items_Wablock.SHIKKUI_WALL_lime.get(), Items_Wablock.SHIKKUI_WALL_pink.get(), Items_Wablock.SHIKKUI_WALL_gray.get(),
				Items_Wablock.SHIKKUI_WALL_lightg.get(), Items_Wablock.SHIKKUI_WALL_cyan.get(), Items_Wablock.SHIKKUI_WALL_purple.get(), Items_Wablock.SHIKKUI_WALL_blue.get(),
				Items_Wablock.SHIKKUI_WALL_brown.get(),Items_Wablock.SHIKKUI_WALL_green.get(), Items_Wablock.SHIKKUI_WALL_red.get(), Items_Wablock.SHIKKUI_WALL_black.get(),
				Items_Wablock.NAMAKO_WALL_white.get(), Items_Wablock.NAMAKO_WALL_orange.get(), Items_Wablock.NAMAKO_WALL_magenta.get(), Items_Wablock.NAMAKO_WALL_lightb.get(),
				Items_Wablock.NAMAKO_WALL_yellow.get(), Items_Wablock.NAMAKO_WALL_lime.get(), Items_Wablock.NAMAKO_WALL_pink.get(), Items_Wablock.NAMAKO_WALL_gray.get(), 
				Items_Wablock.NAMAKO_WALL_lightg.get(), Items_Wablock.NAMAKO_WALL_cyan.get(), Items_Wablock.NAMAKO_WALL_purple.get(), Items_Wablock.NAMAKO_WALL_blue.get(),
				Items_Wablock.NAMAKO_WALL_brown.get(), Items_Wablock.NAMAKO_WALL_green.get(), Items_Wablock.NAMAKO_WALL_red.get(), Items_Wablock.NAMAKO_WALL_black.get(),
				Items_Wablock.NAMAKOB_WALL_white.get(), Items_Wablock.NAMAKOB_WALL_orange.get(), Items_Wablock.NAMAKOB_WALL_magenta.get(), Items_Wablock.NAMAKOB_WALL_lightb.get(),
				Items_Wablock.NAMAKOB_WALL_yellow.get(), Items_Wablock.NAMAKOB_WALL_lime.get(), Items_Wablock.NAMAKOB_WALL_pink.get(), Items_Wablock.NAMAKOB_WALL_gray.get(),
				Items_Wablock.NAMAKOB_WALL_lightg.get(), Items_Wablock.NAMAKOB_WALL_cyan.get(), Items_Wablock.NAMAKOB_WALL_purple.get(), Items_Wablock.NAMAKOB_WALL_blue.get(),
				Items_Wablock.NAMAKOB_WALL_brown.get(), Items_Wablock.NAMAKOB_WALL_green.get(), Items_Wablock.NAMAKOB_WALL_red.get(), Items_Wablock.NAMAKOB_WALL_black.get(),
				Items_Wablock.DIRTWALL_SAMA.get(),
				Items_Wablock.SHIKKUI_SAMA_white.get(), Items_Wablock.SHIKKUI_SAMA_orange.get(), Items_Wablock.SHIKKUI_SAMA_magenta.get(), Items_Wablock.SHIKKUI_SAMA_lightb.get(), 
				Items_Wablock.SHIKKUI_SAMA_yellow.get(), Items_Wablock.SHIKKUI_SAMA_lime.get(), Items_Wablock.SHIKKUI_SAMA_pink.get(), Items_Wablock.SHIKKUI_SAMA_gray.get(),
				Items_Wablock.SHIKKUI_SAMA_lightg.get(), Items_Wablock.SHIKKUI_SAMA_cyan.get(), Items_Wablock.SHIKKUI_SAMA_purple.get(), Items_Wablock.SHIKKUI_SAMA_blue.get(),
				Items_Wablock.SHIKKUI_SAMA_brown.get(), Items_Wablock.SHIKKUI_SAMA_green.get(), Items_Wablock.SHIKKUI_SAMA_red.get(), Items_Wablock.SHIKKUI_SAMA_black.get(),
				Items_Wablock.KAWARA_WALL_white.get(), Items_Wablock.KAWARA_WALL_orange.get(), Items_Wablock.KAWARA_WALL_magenta.get(), Items_Wablock.KAWARA_WALL_lightb.get(),
				Items_Wablock.KAWARA_WALL_yellow.get(), Items_Wablock.KAWARA_WALL_lime.get(),Items_Wablock.KAWARA_WALL_pink.get(), Items_Wablock.KAWARA_WALL_gray.get(),
				Items_Wablock.KAWARA_WALL_lightg.get(), Items_Wablock.KAWARA_WALL_cyan.get(), Items_Wablock.KAWARA_WALL_purple.get(), Items_Wablock.KAWARA_WALL_blue.get(),
				Items_Wablock.KAWARA_WALL_brown.get(), Items_Wablock.KAWARA_WALL_green.get(), Items_Wablock.KAWARA_WALL_red.get(), Items_Wablock.KAWARA_WALL_black.get(), 
	
				Items_Wadeco.IKEGAKI.get(), Items_Wadeco.IKEGAKI_spruce.get(), Items_Wadeco.IKEGAKI_birch.get(), 
				Items_Wadeco.IKEGAKI_jungle.get(), Items_Wadeco.IKEGAKI_acacia.get(), Items_Wadeco.IKEGAKI_darkoak.get(),
				Items_Wadeco.IKEGAKI_mangrove.get(), Items_Wadeco.IKEGAKI_cherry.get(), Items_Wadeco.IKEGAKI_paleoak.get(), 
				Items_Seasonal.IKEGAKI_sakura.get(), Items_Seasonal.IKEGAKI_kaede.get(), Items_Seasonal.IKEGAKI_ichoh.get(), Items_Seasonal.IKEGAKI_kare.get(), 
				Items_Wadeco.IKEGAKILONG.get(), Items_Wadeco.IKEGAKILONG_spruce.get(), Items_Wadeco.IKEGAKILONG_birch.get(), 
				Items_Wadeco.IKEGAKILONG_jungle.get(), Items_Wadeco.IKEGAKILONG_acacia.get(), Items_Wadeco.IKEGAKILONG_darkoak.get(),
				Items_Wadeco.IKEGAKILONG_mangrove.get(), Items_Wadeco.IKEGAKILONG_cherry.get(), Items_Wadeco.IKEGAKILONG_paleoak.get(), 
				Items_Seasonal.IKEGAKILONG_sakura.get(), Items_Seasonal.IKEGAKILONG_kaede.get(), Items_Seasonal.IKEGAKILONG_ichoh.get(), Items_Seasonal.IKEGAKILONG_kare.get(),
				
				Items_Wadeco.ITABEI.get(), Items_Wadeco.ITABEI_spruce.get(), Items_Wadeco.ITABEI_birch.get(), 
				Items_Wadeco.ITABEI_jungle.get(), Items_Wadeco.ITABEI_acacia.get(), Items_Wadeco.ITABEI_darkoak.get(),
				Items_Wadeco.ITABEI_mangrove.get(), Items_Wadeco.ITABEI_cherry.get(), Items_Wadeco.ITABEI_paleoak.get(), 
				Items_Seasonal.ITABEI_sakura.get(), Items_Seasonal.ITABEI_kaede.get(), Items_Seasonal.ITABEI_ichoh.get(), 
				Items_Wadeco.KIDO.get(), Items_Wadeco.KIDO_spruce.get(), Items_Wadeco.KIDO_birch.get(), 
				Items_Wadeco.KIDO_jungle.get(), Items_Wadeco.KIDO_acacia.get(), Items_Wadeco.KIDO_darkoak.get(),
				Items_Wadeco.KIDO_mangrove.get(), Items_Wadeco.KIDO_cherry.get(), Items_Wadeco.KIDO_paleoak.get(), 
				Items_Seasonal.KIDO_sakura.get(), Items_Seasonal.KIDO_kaede.get(), Items_Seasonal.KIDO_ichoh.get());
		
		this.tag(ItemTags.WOODEN_BUTTONS)
		.add(Items_Seasonal.SAKURA_BUTTON.get(), Items_Seasonal.KAEDE_BUTTON.get(), Items_Seasonal.ICHOH_BUTTON.get());
		
		this.tag(ItemTags.WOODEN_DOORS)
		.add(Items_Seasonal.DOOR_SAKURA.get(), Items_Seasonal.DOOR_KAEDE.get(), Items_Seasonal.DOOR_ICHOH.get());
		
		this.tag(ItemTags.WOODEN_FENCES)
		.add(Items_Seasonal.SAKURA_FENCE.get(), Items_Seasonal.KAEDE_FENCE.get(), Items_Seasonal.ICHOH_FENCE.get());
		
		this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
		.add(Items_Seasonal.SAKURA_PLATE.get(), Items_Seasonal.KAEDE_PLATE.get(), Items_Seasonal.ICHOH_PLATE.get());
		
		this.tag(ItemTags.WOODEN_SLABS)
		.add(Items_Seasonal.SAKURA_slabhalf.get(), Items_Seasonal.KAEDE_slabhalf.get(), Items_Seasonal.ICHOH_slabhalf.get());
		
		this.tag(ItemTags.WOODEN_STAIRS)
		.add(Items_Seasonal.SAKURA_stairs.get(), Items_Seasonal.KAEDE_stairs.get(), Items_Seasonal.ICHOH_stairs.get());
		
		this.tag(ItemTags.WOODEN_TRAPDOORS)
		.add(Items_Seasonal.SAKURA_TRAPDOOR.get(), Items_Seasonal.KAEDE_TRAPDOOR.get(), Items_Seasonal.ICHOH_TRAPDOOR.get());
		
		
		this.tag(ItemTags.HEAD_ARMOR)
		.add(Items_Armor.FUBUKI_HELMET.get(), Items_Armor.KASUMI_HELMET.get(), Items_Armor.SHIRATSUYU_HELMET.get(), 
				Items_Armor.SHIGURE_HELMET.get(), Items_Armor.AKATSUKI_HELMET.get())
		.add(Items_Armor.SENDAI_HELMET.get(), Items_Armor.YURA_HELMET.get(), 
				Items_Armor.MOGAMI_HELMET.get(), Items_Armor.TONE_HELMET.get())
		.add(Items_Armor.RJ_HELMET.get(), Items_Armor.ZUIHOU_HELMET.get(), 
				Items_Armor.AKAGI_HELMET.get(), Items_Armor.KAGA_HELMET.get())
		.add(Items_Armor.KONGOU_HELMET.get(), Items_Armor.FUSOU_HELMET.get(), 
				Items_Armor.ISE_HELMET.get(), Items_Armor.NAGATO_HELMET.get())
		.add(Items_Armor.I168_HELMET.get(), Items_Armor.I401_HELMET.get(), 
				Items_Armor.I13_HELMET.get(), Items_Armor.RO500_HELMET.get());

		this.tag(ItemTags.CHEST_ARMOR)
		.add(Items_Armor.FUBUKI_CHESTPLATE.get(), Items_Armor.KASUMI_CHESTPLATE.get(), Items_Armor.SHIRATSUYU_CHESTPLATE.get(), 
				Items_Armor.SHIGURE_CHESTPLATE.get(), Items_Armor.AKATSUKI_CHESTPLATE.get())
		.add(Items_Armor.SENDAI_CHESTPLATE.get(), Items_Armor.YURA_CHESTPLATE.get(), 
				Items_Armor.MOGAMI_CHESTPLATE.get(), Items_Armor.TONE_CHESTPLATE.get())
		.add(Items_Armor.RJ_CHESTPLATE.get(), Items_Armor.ZUIHOU_CHESTPLATE.get(), 
				Items_Armor.AKAGI_CHESTPLATE.get(), Items_Armor.KAGA_CHESTPLATE.get())
		.add(Items_Armor.KONGOU_CHESTPLATE.get(), Items_Armor.FUSOU_CHESTPLATE.get(), 
				Items_Armor.ISE_CHESTPLATE.get(), Items_Armor.NAGATO_CHESTPLATE.get())
		.add(Items_Armor.I168_CHESTPLATE.get(), Items_Armor.I401_CHESTPLATE.get(), 
				Items_Armor.I13_CHESTPLATE.get(), Items_Armor.RO500_CHESTPLATE.get());
		
		this.tag(ItemTags.LEG_ARMOR)
		.add(Items_Armor.FUBUKI_LEGGINGS.get(), Items_Armor.KASUMI_LEGGINGS.get(), Items_Armor.SHIRATSUYU_LEGGINGS.get(), 
				Items_Armor.SHIGURE_LEGGINGS.get(), Items_Armor.AKATSUKI_LEGGINGS.get())
		.add(Items_Armor.SENDAI_LEGGINGS.get(), Items_Armor.YURA_LEGGINGS.get(), Items_Armor.MOGAMI_LEGGINGS.get(), 
				Items_Armor.TONE_LEGGINGS.get())
		.add(Items_Armor.RJ_LEGGINGS.get(), Items_Armor.ZUIHOU_LEGGINGS.get(), 
				Items_Armor.AKAGI_LEGGINGS.get(), Items_Armor.KAGA_LEGGINGS.get())
		.add(Items_Armor.KONGOU_LEGGINGS.get(), Items_Armor.FUSOU_LEGGINGS.get(), 
				Items_Armor.ISE_LEGGINGS.get(), Items_Armor.NAGATO_LEGGINGS.get())
		.add(Items_Armor.I168_LEGGINGS.get(), Items_Armor.I401_LEGGINGS.get(), 
				Items_Armor.I13_LEGGINGS.get(), Items_Armor.RO500_LEGGINGS.get());

		this.tag(ItemTags.FOOT_ARMOR)
		.add(Items_Armor.FUBUKI_BOOTS.get(), Items_Armor.FUBUKI_BOOTS_KAI.get(), Items_Armor.KASUMI_BOOTS.get(), Items_Armor.KASUMI_BOOTS_KAI.get(),
				Items_Armor.SHIRATSUYU_BOOTS.get(), Items_Armor.SHIRATSUYU_BOOTS_KAI.get(), Items_Armor.SHIGURE_BOOTS.get(), Items_Armor.SHIGURE_BOOTS_KAI.get(), 
				Items_Armor.AKATSUKI_BOOTS.get(), Items_Armor.AKATSUKI_BOOTS_KAI.get())
		.add(Items_Armor.SENDAI_BOOTS.get(), Items_Armor.SENDAI_BOOTS_KAI.get(), Items_Armor.YURA_BOOTS.get(), Items_Armor.YURA_BOOTS_KAI.get(),
				Items_Armor.MOGAMI_BOOTS.get(), Items_Armor.MOGAMI_BOOTS_KAI.get(), Items_Armor.TONE_BOOTS.get(), Items_Armor.TONE_BOOTS_KAI.get())
		.add(Items_Armor.RJ_BOOTS.get(), Items_Armor.RJ_BOOTS_KAI.get(), Items_Armor.ZUIHOU_BOOTS.get(), Items_Armor.ZUIHOU_BOOTS_KAI.get(),
				Items_Armor.AKAGI_BOOTS.get(), Items_Armor.AKAGI_BOOTS_KAI.get(), Items_Armor.KAGA_BOOTS.get(), Items_Armor.KAGA_BOOTS_KAI.get())
		.add(Items_Armor.KONGOU_BOOTS.get(), Items_Armor.KONGOU_BOOTS_KAI.get(), Items_Armor.FUSOU_BOOTS.get(), Items_Armor.FUSOU_BOOTS_KAI.get(),
				Items_Armor.ISE_BOOTS.get(), Items_Armor.ISE_BOOTS_KAI.get(), Items_Armor.NAGATO_BOOTS.get(), Items_Armor.NAGATO_BOOTS_KAI.get())
		.add(Items_Armor.I168_BOOTS.get(), Items_Armor.I401_BOOTS.get(), 
				Items_Armor.I13_BOOTS.get(), Items_Armor.RO500_BOOTS.get());
		
		this.tag(ItemTags.SWORDS)
		.add(Items_Weapon.ANCHOR.get(), 
				Items_Weapon.SWORD_sakura.get(), Items_Weapon.SWORD_ichoh.get(), Items_Weapon.SWORD_kaede.get());
		this.tag(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(Items_Weapon.GYORAI_61cm.get());
		this.tag(ItemTags.BOW_ENCHANTABLE)
		.add(Items_Weapon.RENSOUHOU_127.get(), Items_Weapon.SHIGUREHOU.get(), Items_Weapon.KOUKAKUHOU_100.get(),
				Items_Weapon.RENSOUHOU_155.get(), Items_Weapon.RENSOUHOU_203.get(), Items_Weapon.RENSOUHOU_SKC.get(),
				Items_Weapon.RENSOUHOU_356.get(), Items_Weapon.RENSOUHOU_356S3.get(), Items_Weapon.RENSOUHOU_380.get(),
				Items_Weapon.RENSOUHOU_410.get(), Items_Weapon.KIJYUU.get());
	}
	
	@Override
	public String getName() {
		return "ChinjufuMod Vanilla Item Tags";
	}
}
