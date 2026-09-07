package com.ayutaki.chinjufumod.tags;

import java.util.concurrent.CompletableFuture;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Chair_Blocks;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Gate_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Harbor_Blocks;
import com.ayutaki.chinjufumod.registry.JPChair_Blocks;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.registry.JP_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlanks_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.registry.Ranma_Blocks;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;
import com.ayutaki.chinjufumod.registry.WallPanel_Blocks;
import com.ayutaki.chinjufumod.registry.Window_Blocks;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags_CM extends IntrinsicHolderTagsProvider<Block> {

	@SuppressWarnings("deprecation")
	public BlockTags_CM(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		super(output, Registries.BLOCK, provider, block -> block.builtInRegistryHolder().key(), ChinjufuMod.MOD_ID, helper);
	}
	
	/* TagKey */
	public static final TagKey<Block> DIRT = forgeTag("dirt");
	
	@SuppressWarnings("removal")
	private static TagKey<Block> forgeTag(String name) {
		return BlockTags.create(new ResourceLocation("forge", name));
	}
	
	
	/* addTags */
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		/** forge **/
		this.tag(DIRT).add(Wood_Blocks.FALL_LEAF.get());
		
		
		/** vanilla **/
		this.tag(BlockTags.ANIMALS_SPAWNABLE_ON).add(Wood_Blocks.FALL_LEAF.get());
		this.tag(BlockTags.FOXES_SPAWNABLE_ON).add(Wood_Blocks.FALL_LEAF.get());
		this.tag(BlockTags.VALID_SPAWN).add(Wood_Blocks.FALL_LEAF.get());
		this.tag(BlockTags.WOLVES_SPAWNABLE_ON).add(Wood_Blocks.FALL_LEAF.get());
		
		this.tag(BlockTags.BUTTONS)
		.addTag(BlockTags.WOODEN_BUTTONS)
		.add(JPDeco_Blocks.TAKE_BUTTON.get(), JPDeco_Blocks.TAKE_BUTTON_Y.get(), JPDeco_Blocks.TAKE_BUTTON_K.get());
		
		this.tag(BlockTags.CROPS)
		.add(Crop_Blocks.AZUKI.get(), Crop_Blocks.CABBAGE.get(), Crop_Blocks.HAKUSAI.get(), Crop_Blocks.CORN.get(), 
				Crop_Blocks.GREENONION.get(), Crop_Blocks.ONION.get(), Crop_Blocks.RICE.get(), 
				Crop_Blocks.SOY.get(), Crop_Blocks.SPINACH.get(), Crop_Blocks.TOMATO.get(), Crop_Blocks.SAKURA.get(),
				Crop_Blocks.CUMIN.get(), Crop_Blocks.TURMERIC.get(), Crop_Blocks.CHILI.get(),
				Crop_Blocks.VANILLA.get(), Crop_Blocks.TENGUSA.get(), Crop_Blocks.NORI.get());
		
		this.tag(BlockTags.DIRT).add(Wood_Blocks.FALL_LEAF.get());
		
		this.tag(BlockTags.DOORS)
		.addTag(BlockTags.WOODEN_DOORS)
		.add(JPDeco_Blocks.TAKEDOOR.get(), JPDeco_Blocks.TAKEDOOR_Y.get(), JPDeco_Blocks.TAKEDOOR_K.get());
		
		this.tag(BlockTags.FLOWERS).add(Wood_Blocks.SAKURA_flow.get());
		
		this.tag(BlockTags.FENCE_GATES)
		.add(JPDeco_Blocks.TAKEFENCEGATE.get(), JPDeco_Blocks.TAKEFENCEGATE_Y.get(), JPDeco_Blocks.TAKEFENCEGATE_K.get(),
				Wood_Blocks.SAKURA_FGATE.get(), Wood_Blocks.KAEDE_FGATE.get(), Wood_Blocks.ICHOH_FGATE.get());
		
		this.tag(BlockTags.FENCES)
		.addTag(BlockTags.WOODEN_FENCES)
		.add(JPDeco_Blocks.TAKEFENCE.get(), JPDeco_Blocks.TAKEFENCE_Y.get(), JPDeco_Blocks.TAKEFENCE_K.get());

		this.tag(BlockTags.IMPERMEABLE)
		.add(WallPanel_Blocks.WP_GLASS_white.get());
		
		this.tag(BlockTags.LOGS_THAT_BURN)
		.add(Wood_Blocks.SAKURA_log.get(), Wood_Blocks.KAEDE_log.get(), Wood_Blocks.ICHOH_log.get(), Wood_Blocks.OAKKARE_log.get());
		
		this.tag(BlockTags.LOGS)
		.add(Wood_Blocks.SAKURA_log.get(), Wood_Blocks.KAEDE_log.get(), Wood_Blocks.ICHOH_log.get(), Wood_Blocks.OAKKARE_log.get());
		
		this.tag(BlockTags.LEAVES)
		.add(Wood_Blocks.SAKURA_flow.get(), Wood_Blocks.KAEDE_leaf.get(), Wood_Blocks.ICHOH_leaf.get(), Wood_Blocks.OAKKARE_leaf.get());
		
		this.tag(BlockTags.NEEDS_IRON_TOOL).add(Chinjufu_Blocks.BAUXITE_ORE.get(), Chinjufu_Blocks.BAUXITE_ORE_DEEP.get());
		
		this.tag(BlockTags.PLANKS)
		.add(Wood_Blocks.SAKURA_planks.get(), Wood_Blocks.KAEDE_planks.get(), Wood_Blocks.ICHOH_planks.get());
		
		this.tag(BlockTags.PRESSURE_PLATES)
		.addTag(BlockTags.WOODEN_PRESSURE_PLATES)
		.add(JPDeco_Blocks.TAKE_PLATE.get(), JPDeco_Blocks.TAKE_PLATE_Y.get(), JPDeco_Blocks.TAKE_PLATE_K.get());
		
		this.tag(BlockTags.SAPLINGS)
		.add(Wood_Blocks.SAKURA_nae.get(), Wood_Blocks.KAEDE_nae.get(), Wood_Blocks.ICHOH_nae.get(), Wood_Blocks.OAKKARE_nae.get());

		this.tag(BlockTags.SLABS)
		.addTag(BlockTags.WOODEN_SLABS)
		.add(JP_Blocks.KAWARA_SH_yellow.get(), JP_Blocks.KAWARA_SH_lime.get(), JP_Blocks.KAWARA_SH_pink.get(), JP_Blocks.KAWARA_SH_gray.get(),
				JP_Blocks.KAWARA_SH_lightg.get(), JP_Blocks.KAWARA_SH_cyan.get(), JP_Blocks.KAWARA_SH_purple.get(), JP_Blocks.KAWARA_SH_blue.get(),
				JP_Blocks.KAWARA_SH_brown.get(), JP_Blocks.KAWARA_SH_green.get(), JP_Blocks.KAWARA_SH_red.get(), JP_Blocks.KAWARA_SH_black.get(),
				JP_Blocks.DIRTWALL_SH.get(),
				JP_Blocks.SHIKKUI_SH_white.get(), JP_Blocks.SHIKKUI_SH_orange.get(), JP_Blocks.SHIKKUI_SH_magenta.get(), JP_Blocks.SHIKKUI_SH_lightb.get(),
				JP_Blocks.SHIKKUI_SH_yellow.get(), JP_Blocks.SHIKKUI_SH_lime.get(), JP_Blocks.SHIKKUI_SH_pink.get(), JP_Blocks.SHIKKUI_SH_gray.get(),
				JP_Blocks.SHIKKUI_SH_lightg.get(), JP_Blocks.SHIKKUI_SH_cyan.get(), JP_Blocks.SHIKKUI_SH_purple.get(), JP_Blocks.SHIKKUI_SH_blue.get(),
				JP_Blocks.SHIKKUI_SH_brown.get(), JP_Blocks.SHIKKUI_SH_green.get(), JP_Blocks.SHIKKUI_SH_red.get(), JP_Blocks.SHIKKUI_SH_black.get(),
				
				JP_Blocks.NAMAKO_SH_white.get(), JP_Blocks.NAMAKO_SH_orange.get(), JP_Blocks.NAMAKO_SH_magenta.get(), JP_Blocks.NAMAKO_SH_lightb.get(),
				JP_Blocks.NAMAKO_SH_yellow.get(), JP_Blocks.NAMAKO_SH_lime.get(), JP_Blocks.NAMAKO_SH_pink.get(), JP_Blocks.NAMAKO_SH_gray.get(),
				JP_Blocks.NAMAKO_SH_lightg.get(), JP_Blocks.NAMAKO_SH_cyan.get(), JP_Blocks.NAMAKO_SH_purple.get(), JP_Blocks.NAMAKO_SH_blue.get(),
				JP_Blocks.NAMAKO_SH_brown.get(), JP_Blocks.NAMAKO_SH_green.get(), JP_Blocks.NAMAKO_SH_red.get(), JP_Blocks.NAMAKO_SH_black.get(),
				JP_Blocks.NAMAKOB_SH_white.get(), JP_Blocks.NAMAKOB_SH_orange.get(), JP_Blocks.NAMAKOB_SH_magenta.get(), JP_Blocks.NAMAKOB_SH_lightb.get(),
				JP_Blocks.NAMAKOB_SH_yellow.get(), JP_Blocks.NAMAKOB_SH_lime.get(), JP_Blocks.NAMAKOB_SH_pink.get(), JP_Blocks.NAMAKOB_SH_gray.get(),
				JP_Blocks.NAMAKOB_SH_lightg.get(), JP_Blocks.NAMAKOB_SH_cyan.get(), JP_Blocks.NAMAKOB_SH_purple.get(), JP_Blocks.NAMAKOB_SH_blue.get(),
				JP_Blocks.NAMAKOB_SH_brown.get(), JP_Blocks.NAMAKOB_SH_green.get(), JP_Blocks.NAMAKOB_SH_red.get(), JP_Blocks.NAMAKOB_SH_black.get(),
				
				WallPanel_Blocks.BGC_slabhalf.get(), WallPanel_Blocks.BDC_slabhalf.get(), WallPanel_Blocks.BAC_slabhalf.get());
		
		this.tag(BlockTags.STAIRS)
		.addTag(BlockTags.WOODEN_STAIRS)
		.add(JP_Blocks.KAWARA_ST_yellow.get(), JP_Blocks.KAWARA_ST_lime.get(), JP_Blocks.KAWARA_ST_pink.get(), JP_Blocks.KAWARA_ST_gray.get(),
				JP_Blocks.KAWARA_ST_lightg.get(), JP_Blocks.KAWARA_ST_cyan.get(), JP_Blocks.KAWARA_ST_purple.get(), JP_Blocks.KAWARA_ST_blue.get(),
				JP_Blocks.KAWARA_ST_brown.get(), JP_Blocks.KAWARA_ST_green.get(), JP_Blocks.KAWARA_ST_red.get(), JP_Blocks.KAWARA_ST_black.get(),
				JP_Blocks.DIRTWALL_stairs.get(),
				JP_Blocks.SHIKKUI_ST_white.get(), JP_Blocks.SHIKKUI_ST_orange.get(), JP_Blocks.SHIKKUI_ST_magenta.get(), JP_Blocks.SHIKKUI_ST_lightb.get(),
				JP_Blocks.SHIKKUI_ST_yellow.get(), JP_Blocks.SHIKKUI_ST_lime.get(), JP_Blocks.SHIKKUI_ST_pink.get(), JP_Blocks.SHIKKUI_ST_gray.get(),
				JP_Blocks.SHIKKUI_ST_lightg.get(), JP_Blocks.SHIKKUI_ST_cyan.get(), JP_Blocks.SHIKKUI_ST_purple.get(), JP_Blocks.SHIKKUI_ST_blue.get(),
				JP_Blocks.SHIKKUI_ST_brown.get(), JP_Blocks.SHIKKUI_ST_green.get(), JP_Blocks.SHIKKUI_ST_red.get(), JP_Blocks.SHIKKUI_ST_black.get(),
				
				JP_Blocks.NAMAKO_ST_white.get(), JP_Blocks.NAMAKO_ST_orange.get(), JP_Blocks.NAMAKO_ST_magenta.get(), JP_Blocks.NAMAKO_ST_lightb.get(),
				JP_Blocks.NAMAKO_ST_yellow.get(), JP_Blocks.NAMAKO_ST_lime.get(), JP_Blocks.NAMAKO_ST_pink.get(), JP_Blocks.NAMAKO_ST_gray.get(),
				JP_Blocks.NAMAKO_ST_lightg.get(), JP_Blocks.NAMAKO_ST_cyan.get(), JP_Blocks.NAMAKO_ST_purple.get(), JP_Blocks.NAMAKO_ST_blue.get(),
				JP_Blocks.NAMAKO_ST_brown.get(), JP_Blocks.NAMAKO_ST_green.get(), JP_Blocks.NAMAKO_ST_red.get(), JP_Blocks.NAMAKO_ST_black.get(),
				JP_Blocks.NAMAKOB_ST_white.get(), JP_Blocks.NAMAKOB_ST_orange.get(), JP_Blocks.NAMAKOB_ST_magenta.get(), JP_Blocks.NAMAKOB_ST_lightb.get(),
				JP_Blocks.NAMAKOB_ST_yellow.get(), JP_Blocks.NAMAKOB_ST_lime.get(), JP_Blocks.NAMAKOB_ST_pink.get(), JP_Blocks.NAMAKOB_ST_gray.get(),
				JP_Blocks.NAMAKOB_ST_lightg.get(), JP_Blocks.NAMAKOB_ST_cyan.get(), JP_Blocks.NAMAKOB_ST_purple.get(), JP_Blocks.NAMAKOB_ST_blue.get(),
				JP_Blocks.NAMAKOB_ST_brown.get(), JP_Blocks.NAMAKOB_ST_green.get(), JP_Blocks.NAMAKOB_ST_red.get(), JP_Blocks.NAMAKOB_ST_black.get(),
				
				WallPanel_Blocks.BRICKSTAIRS_GRA.get(), WallPanel_Blocks.BRICKSTAIRS_DIO.get(), WallPanel_Blocks.BRICKSTAIRS_AND.get());
		
		this.tag(BlockTags.STONE_BRICKS)
		.add(WallPanel_Blocks.BRICK_GRA.get(), WallPanel_Blocks.BRICK_DIO.get(), WallPanel_Blocks.BRICK_AND.get());
		
		this.tag(BlockTags.TRAPDOORS)
		.addTag(BlockTags.WOODEN_TRAPDOORS)
		.add(JPDeco_Blocks.TAKE_TRAPDOOR.get(), JPDeco_Blocks.TAKE_TRAPDOOR_Y.get(), JPDeco_Blocks.TAKE_TRAPDOOR_K.get());
		
		this.tag(BlockTags.WALLS)
		.add(Window_Blocks.WINDOW_oak.get(), Window_Blocks.WINDOW_spruce.get(), Window_Blocks.WINDOW_birch.get(),
				Window_Blocks.WINDOW_jungle.get(), Window_Blocks.WINDOW_acacia.get(), Window_Blocks.WINDOW_darkoak.get(),
				Window_Blocks.WINDOW_mangrove.get(), Window_Blocks.WINDOW_cherry.get(),
				Window_Blocks.WINDOW_sakura.get(), Window_Blocks.WINDOW_kaede.get(), Window_Blocks.WINDOW_ichoh.get(),
				Window_Blocks.WINDOWB_oak.get(), Window_Blocks.WINDOWB_spruce.get(), Window_Blocks.WINDOWB_birch.get(),
				Window_Blocks.WINDOWB_jungle.get(), Window_Blocks.WINDOWB_acacia.get(), Window_Blocks.WINDOWB_darkoak.get(),
				Window_Blocks.WINDOWB_mangrove.get(), Window_Blocks.WINDOWB_cherry.get(),
				Window_Blocks.WINDOWB_sakura.get(), Window_Blocks.WINDOWB_kaede.get(), Window_Blocks.WINDOWB_ichoh.get(),
				Window_Blocks.WINDOWTALLBOT_oak.get(), Window_Blocks.WINDOWTALLBOT_spruce.get(), Window_Blocks.WINDOWTALLBOT_birch.get(),
				Window_Blocks.WINDOWTALLBOT_jungle.get(), Window_Blocks.WINDOWTALLBOT_acacia.get(), Window_Blocks.WINDOWTALLBOT_darkoak.get(),
				Window_Blocks.WINDOWTALLBOT_mangrove.get(), Window_Blocks.WINDOWTALLBOT_cherry.get(),
				Window_Blocks.WINDOWTALLBOT_sakura.get(), Window_Blocks.WINDOWTALLBOT_kaede.get(), Window_Blocks.WINDOWTALLBOT_ichoh.get(),
				Window_Blocks.WINDOWTALLTOP_oak.get(), Window_Blocks.WINDOWTALLTOP_spruce.get(), Window_Blocks.WINDOWTALLTOP_birch.get(),
				Window_Blocks.WINDOWTALLTOP_jungle.get(), Window_Blocks.WINDOWTALLTOP_acacia.get(), Window_Blocks.WINDOWTALLTOP_darkoak.get(),
				Window_Blocks.WINDOWTALLTOP_mangrove.get(), Window_Blocks.WINDOWTALLTOP_cherry.get(),
				Window_Blocks.WINDOWTALLTOP_sakura.get(), Window_Blocks.WINDOWTALLTOP_kaede.get(), Window_Blocks.WINDOWTALLTOP_ichoh.get(),
				Window_Blocks.WINDOWTALL_oak.get(), Window_Blocks.WINDOWTALL_spruce.get(), Window_Blocks.WINDOWTALL_birch.get(),
				Window_Blocks.WINDOWTALL_jungle.get(), Window_Blocks.WINDOWTALL_acacia.get(), Window_Blocks.WINDOWTALL_darkoak.get(),
				Window_Blocks.WINDOWTALL_mangrove.get(), Window_Blocks.WINDOWTALL_cherry.get(),
				Window_Blocks.WINDOWTALL_sakura.get(), Window_Blocks.WINDOWTALL_kaede.get(), Window_Blocks.WINDOWTALL_ichoh.get(),
				
				Slidedoor_Blocks.FUSUMA_white.get(), Slidedoor_Blocks.FUSUMA_orange.get(), Slidedoor_Blocks.FUSUMA_magenta.get(), Slidedoor_Blocks.FUSUMA_lightb.get(),
				Slidedoor_Blocks.FUSUMA_yellow.get(), Slidedoor_Blocks.FUSUMA_lime.get(), Slidedoor_Blocks.FUSUMA_pink.get(), Slidedoor_Blocks.FUSUMA_gray.get(),
				Slidedoor_Blocks.FUSUMA_lightg.get(), Slidedoor_Blocks.FUSUMA_cyan.get(), Slidedoor_Blocks.FUSUMA_purple.get(), Slidedoor_Blocks.FUSUMA_blue.get(),
				Slidedoor_Blocks.FUSUMA_brown.get(), Slidedoor_Blocks.FUSUMA_green.get(), Slidedoor_Blocks.FUSUMA_red.get(), Slidedoor_Blocks.FUSUMA_black.get(),
				Slidedoor_Blocks.FUSUMAB_white.get(), Slidedoor_Blocks.FUSUMAB_orange.get(), Slidedoor_Blocks.FUSUMAB_magenta.get(), Slidedoor_Blocks.FUSUMAB_lightb.get(),
				Slidedoor_Blocks.FUSUMAB_yellow.get(), Slidedoor_Blocks.FUSUMAB_lime.get(), Slidedoor_Blocks.FUSUMAB_pink.get(), Slidedoor_Blocks.FUSUMAB_gray.get(),
				Slidedoor_Blocks.FUSUMAB_lightg.get(), Slidedoor_Blocks.FUSUMAB_cyan.get(), Slidedoor_Blocks.FUSUMAB_purple.get(), Slidedoor_Blocks.FUSUMAB_blue.get(),
				Slidedoor_Blocks.FUSUMAB_brown.get(), Slidedoor_Blocks.FUSUMAB_green.get(), Slidedoor_Blocks.FUSUMAB_red.get(), Slidedoor_Blocks.FUSUMAB_black.get(),
				
				Slidedoor_Blocks.GARASUDO.get(), Slidedoor_Blocks.GARASUDO_SPRU.get(), Slidedoor_Blocks.GARASUDO_BIR.get(),
				Slidedoor_Blocks.GARASUDO_JUN.get(), Slidedoor_Blocks.GARASUDO_ACA.get(), Slidedoor_Blocks.GARASUDO_DOAK.get(),
				Slidedoor_Blocks.GARASUDO_MANGROVE.get(), Slidedoor_Blocks.GARASUDO_CHERRY.get(),
				Slidedoor_Blocks.GARASUDO_SAKU.get(), Slidedoor_Blocks.GARASUDO_KAE.get(), Slidedoor_Blocks.GARASUDO_ICH.get(),
				Slidedoor_Blocks.GARASUDOB.get(), Slidedoor_Blocks.GARASUDOB_SPRU.get(), Slidedoor_Blocks.GARASUDOB_BIR.get(),
				Slidedoor_Blocks.GARASUDOB_JUN.get(), Slidedoor_Blocks.GARASUDOB_ACA.get(), Slidedoor_Blocks.GARASUDOB_DOAK.get(),
				Slidedoor_Blocks.GARASUDOB_MANGROVE.get(), Slidedoor_Blocks.GARASUDOB_CHERRY.get(),
				Slidedoor_Blocks.GARASUDOB_SAKU.get(), Slidedoor_Blocks.GARASUDOB_KAE.get(), Slidedoor_Blocks.GARASUDOB_ICH.get(),
				
				Slidedoor_Blocks.GARASUDOH.get(), Slidedoor_Blocks.GARASUDOH_SPRU.get(), Slidedoor_Blocks.GARASUDOH_BIR.get(),
				Slidedoor_Blocks.GARASUDOH_JUN.get(), Slidedoor_Blocks.GARASUDOH_ACA.get(), Slidedoor_Blocks.GARASUDOH_DOAK.get(),
				Slidedoor_Blocks.GARASUDOH_MANGROVE.get(), Slidedoor_Blocks.GARASUDOH_CHERRY.get(),
				Slidedoor_Blocks.GARASUDOH_SAKU.get(), Slidedoor_Blocks.GARASUDOH_KAE.get(), Slidedoor_Blocks.GARASUDOH_ICH.get(),
				
				Slidedoor_Blocks.SHOUJI.get(), Slidedoor_Blocks.SHOUJI_SPRU.get(), Slidedoor_Blocks.SHOUJI_BIR.get(),
				Slidedoor_Blocks.SHOUJI_JUN.get(), Slidedoor_Blocks.SHOUJI_ACA.get(), Slidedoor_Blocks.SHOUJI_DOAK.get(),
				Slidedoor_Blocks.SHOUJI_MANGROVE.get(), Slidedoor_Blocks.SHOUJI_CHERRY.get(),
				Slidedoor_Blocks.SHOUJI_SAKU.get(), Slidedoor_Blocks.SHOUJI_KAE.get(), Slidedoor_Blocks.SHOUJI_ICH.get(),
				Slidedoor_Blocks.SHOUJIB.get(), Slidedoor_Blocks.SHOUJIB_SPRU.get(), Slidedoor_Blocks.SHOUJIB_BIR.get(),
				Slidedoor_Blocks.SHOUJIB_JUN.get(), Slidedoor_Blocks.SHOUJIB_ACA.get(), Slidedoor_Blocks.SHOUJIB_DOAK.get(),
				Slidedoor_Blocks.SHOUJIB_MANGROVE.get(), Slidedoor_Blocks.SHOUJIB_CHERRY.get(),
				Slidedoor_Blocks.SHOUJIB_SAKU.get(), Slidedoor_Blocks.SHOUJIB_KAE.get(), Slidedoor_Blocks.SHOUJIB_ICH.get(),
				
				Slidedoor_Blocks.SHOUJIH.get(), Slidedoor_Blocks.SHOUJIH_SPRU.get(), Slidedoor_Blocks.SHOUJIH_BIR.get(),
				Slidedoor_Blocks.SHOUJIH_JUN.get(), Slidedoor_Blocks.SHOUJIH_ACA.get(), Slidedoor_Blocks.SHOUJIH_DOAK.get(),
				Slidedoor_Blocks.SHOUJIH_MANGROVE.get(), Slidedoor_Blocks.SHOUJIH_CHERRY.get(),
				Slidedoor_Blocks.SHOUJIH_SAKU.get(), Slidedoor_Blocks.SHOUJIH_KAE.get(), Slidedoor_Blocks.SHOUJIH_ICH.get(),
				Slidedoor_Blocks.SHOUJI_WIN.get(), Slidedoor_Blocks.SHOUJI_WIN_SPRU.get(), Slidedoor_Blocks.SHOUJI_WIN_BIR.get(),
				Slidedoor_Blocks.SHOUJI_WIN_JUN.get(), Slidedoor_Blocks.SHOUJI_WIN_ACA.get(), Slidedoor_Blocks.SHOUJI_WIN_DOAK.get(),
				Slidedoor_Blocks.SHOUJI_WIN_MANGROVE.get(), Slidedoor_Blocks.SHOUJI_WIN_CHERRY.get(),
				Slidedoor_Blocks.SHOUJI_WIN_SAKU.get(), Slidedoor_Blocks.SHOUJI_WIN_KAE.get(), Slidedoor_Blocks.SHOUJI_WIN_ICH.get(),
				Slidedoor_Blocks.SHOUJI_WINR.get(), Slidedoor_Blocks.SHOUJI_WINR_SPRU.get(), Slidedoor_Blocks.SHOUJI_WINR_BIR.get(),
				Slidedoor_Blocks.SHOUJI_WINR_JUN.get(), Slidedoor_Blocks.SHOUJI_WINR_ACA.get(), Slidedoor_Blocks.SHOUJI_WINR_DOAK.get(),
				Slidedoor_Blocks.SHOUJI_WINR_MANGROVE.get(), Slidedoor_Blocks.SHOUJI_WINR_CHERRY.get(),
				Slidedoor_Blocks.SHOUJI_WINR_SAKU.get(), Slidedoor_Blocks.SHOUJI_WINR_KAE.get(), Slidedoor_Blocks.SHOUJI_WINR_ICH.get(),
				
				Ranma_Blocks.RANMA_oak.get(), Ranma_Blocks.RANMA_spruce.get(), Ranma_Blocks.RANMA_birch.get(),
				Ranma_Blocks.RANMA_jungle.get(), Ranma_Blocks.RANMA_acacia.get(), Ranma_Blocks.RANMA_darkoak.get(),
				Ranma_Blocks.RANMA_mangrove.get(), Ranma_Blocks.RANMA_cherry.get(), 
				Ranma_Blocks.RANMA_sakura.get(), Ranma_Blocks.RANMA_kaede.get(), Ranma_Blocks.RANMA_ichoh.get(),
				Ranma_Blocks.RANMAB_oak.get(), Ranma_Blocks.RANMAB_spruce.get(), Ranma_Blocks.RANMAB_birch.get(),
				Ranma_Blocks.RANMAB_jungle.get(), Ranma_Blocks.RANMAB_acacia.get(), Ranma_Blocks.RANMAB_darkoak.get(),
				Ranma_Blocks.RANMAB_mangrove.get(), Ranma_Blocks.RANMAB_cherry.get(),
				Ranma_Blocks.RANMAB_sakura.get(), Ranma_Blocks.RANMAB_kaede.get(), Ranma_Blocks.RANMAB_ichoh.get(),
				Ranma_Blocks.RANMAC_oak.get(), Ranma_Blocks.RANMAC_spruce.get(), Ranma_Blocks.RANMAC_birch.get(),
				Ranma_Blocks.RANMAC_jungle.get(), Ranma_Blocks.RANMAC_acacia.get(), Ranma_Blocks.RANMAC_darkoak.get(),
				Ranma_Blocks.RANMAC_mangrove.get(), Ranma_Blocks.RANMAC_cherry.get(),
				Ranma_Blocks.RANMAC_sakura.get(), Ranma_Blocks.RANMAC_kaede.get(), Ranma_Blocks.RANMAC_ichoh.get(),
				
				Ranma_Blocks.KANKI_oak.get(), Ranma_Blocks.KANKI_spruce.get(), Ranma_Blocks.KANKI_birch.get(),
				Ranma_Blocks.KANKI_jungle.get(), Ranma_Blocks.KANKI_acacia.get(), Ranma_Blocks.KANKI_darkoak.get(),
				Ranma_Blocks.KANKI_mangrove.get(), Ranma_Blocks.KANKI_cherry.get(),
				Ranma_Blocks.KANKI_sakura.get(), Ranma_Blocks.KANKI_kaede.get(), Ranma_Blocks.KANKI_ichoh.get(),
				Ranma_Blocks.KOUSHI_oak.get(), Ranma_Blocks.KOUSHI_spruce.get(), Ranma_Blocks.KOUSHI_birch.get(),
				Ranma_Blocks.KOUSHI_jungle.get(), Ranma_Blocks.KOUSHI_acacia.get(), Ranma_Blocks.KOUSHI_darkoak.get(),
				Ranma_Blocks.KOUSHI_mangrove.get(), Ranma_Blocks.KOUSHI_cherry.get(),
				Ranma_Blocks.KOUSHI_sakura.get(), Ranma_Blocks.KOUSHI_kaede.get(), Ranma_Blocks.KOUSHI_ichoh.get(),
				Ranma_Blocks.KOUSHIB_oak.get(), Ranma_Blocks.KOUSHIB_spruce.get(), Ranma_Blocks.KOUSHIB_birch.get(),
				Ranma_Blocks.KOUSHIB_jungle.get(), Ranma_Blocks.KOUSHIB_acacia.get(), Ranma_Blocks.KOUSHIB_darkoak.get(),
				Ranma_Blocks.KOUSHIB_mangrove.get(), Ranma_Blocks.KOUSHIB_cherry.get(),
				Ranma_Blocks.KOUSHIB_sakura.get(), Ranma_Blocks.KOUSHIB_kaede.get(), Ranma_Blocks.KOUSHIB_ichoh.get(),
				
				JP_Blocks.DIRTWALL_WALL.get(),
				JP_Blocks.SHIKKUI_WALL_white.get(), JP_Blocks.SHIKKUI_WALL_orange.get(), JP_Blocks.SHIKKUI_WALL_magenta.get(), JP_Blocks.SHIKKUI_WALL_lightb.get(),
				JP_Blocks.SHIKKUI_WALL_yellow.get(), JP_Blocks.SHIKKUI_WALL_lime.get(), JP_Blocks.SHIKKUI_WALL_pink.get(), JP_Blocks.SHIKKUI_WALL_gray.get(),
				JP_Blocks.SHIKKUI_WALL_lightg.get(), JP_Blocks.SHIKKUI_WALL_cyan.get(), JP_Blocks.SHIKKUI_WALL_purple.get(), JP_Blocks.SHIKKUI_WALL_blue.get(),
				JP_Blocks.SHIKKUI_WALL_brown.get(),JP_Blocks.SHIKKUI_WALL_green.get(), JP_Blocks.SHIKKUI_WALL_red.get(), JP_Blocks.SHIKKUI_WALL_black.get(),
				JP_Blocks.NAMAKO_WALL_white.get(), JP_Blocks.NAMAKO_WALL_orange.get(), JP_Blocks.NAMAKO_WALL_magenta.get(), JP_Blocks.NAMAKO_WALL_lightb.get(),
				JP_Blocks.NAMAKO_WALL_yellow.get(), JP_Blocks.NAMAKO_WALL_lime.get(), JP_Blocks.NAMAKO_WALL_pink.get(), JP_Blocks.NAMAKO_WALL_gray.get(), 
				JP_Blocks.NAMAKO_WALL_lightg.get(), JP_Blocks.NAMAKO_WALL_cyan.get(), JP_Blocks.NAMAKO_WALL_purple.get(), JP_Blocks.NAMAKO_WALL_blue.get(),
				JP_Blocks.NAMAKO_WALL_brown.get(), JP_Blocks.NAMAKO_WALL_green.get(), JP_Blocks.NAMAKO_WALL_red.get(), JP_Blocks.NAMAKO_WALL_black.get(),
				JP_Blocks.NAMAKOB_WALL_white.get(), JP_Blocks.NAMAKOB_WALL_orange.get(), JP_Blocks.NAMAKOB_WALL_magenta.get(), JP_Blocks.NAMAKOB_WALL_lightb.get(),
				JP_Blocks.NAMAKOB_WALL_yellow.get(), JP_Blocks.NAMAKOB_WALL_lime.get(), JP_Blocks.NAMAKOB_WALL_pink.get(), JP_Blocks.NAMAKOB_WALL_gray.get(),
				JP_Blocks.NAMAKOB_WALL_lightg.get(), JP_Blocks.NAMAKOB_WALL_cyan.get(), JP_Blocks.NAMAKOB_WALL_purple.get(), JP_Blocks.NAMAKOB_WALL_blue.get(),
				JP_Blocks.NAMAKOB_WALL_brown.get(), JP_Blocks.NAMAKOB_WALL_green.get(), JP_Blocks.NAMAKOB_WALL_red.get(), JP_Blocks.NAMAKOB_WALL_black.get(),
				JP_Blocks.DIRTWALL_SAMA.get(),
				JP_Blocks.SHIKKUI_SAMA_white.get(), JP_Blocks.SHIKKUI_SAMA_orange.get(), JP_Blocks.SHIKKUI_SAMA_magenta.get(), JP_Blocks.SHIKKUI_SAMA_lightb.get(), 
				JP_Blocks.SHIKKUI_SAMA_yellow.get(), JP_Blocks.SHIKKUI_SAMA_lime.get(), JP_Blocks.SHIKKUI_SAMA_pink.get(), JP_Blocks.SHIKKUI_SAMA_gray.get(),
				JP_Blocks.SHIKKUI_SAMA_lightg.get(), JP_Blocks.SHIKKUI_SAMA_cyan.get(), JP_Blocks.SHIKKUI_SAMA_purple.get(), JP_Blocks.SHIKKUI_SAMA_blue.get(),
				JP_Blocks.SHIKKUI_SAMA_brown.get(), JP_Blocks.SHIKKUI_SAMA_green.get(), JP_Blocks.SHIKKUI_SAMA_red.get(), JP_Blocks.SHIKKUI_SAMA_black.get(),
				JP_Blocks.KAWARA_WALL_white.get(), JP_Blocks.KAWARA_WALL_orange.get(), JP_Blocks.KAWARA_WALL_magenta.get(), JP_Blocks.KAWARA_WALL_lightb.get(),
				JP_Blocks.KAWARA_WALL_yellow.get(), JP_Blocks.KAWARA_WALL_lime.get(),JP_Blocks.KAWARA_WALL_pink.get(), JP_Blocks.KAWARA_WALL_gray.get(),
				JP_Blocks.KAWARA_WALL_lightg.get(), JP_Blocks.KAWARA_WALL_cyan.get(), JP_Blocks.KAWARA_WALL_purple.get(), JP_Blocks.KAWARA_WALL_blue.get(),
				JP_Blocks.KAWARA_WALL_brown.get(), JP_Blocks.KAWARA_WALL_green.get(), JP_Blocks.KAWARA_WALL_red.get(), JP_Blocks.KAWARA_WALL_black.get(), 
	
				Garden_Blocks.IKEGAKI.get(), Garden_Blocks.IKEGAKI_spruce.get(), Garden_Blocks.IKEGAKI_birch.get(), 
				Garden_Blocks.IKEGAKI_jungle.get(), Garden_Blocks.IKEGAKI_acacia.get(), Garden_Blocks.IKEGAKI_darkoak.get(),
				Garden_Blocks.IKEGAKI_mangrove.get(), Garden_Blocks.IKEGAKI_cherry.get(),
				Garden_Blocks.IKEGAKI_sakura.get(), Garden_Blocks.IKEGAKI_kaede.get(), Garden_Blocks.IKEGAKI_ichoh.get(), Garden_Blocks.IKEGAKI_kare.get(), 
				Garden_Blocks.IKEGAKILONG.get(), Garden_Blocks.IKEGAKILONG_spruce.get(), Garden_Blocks.IKEGAKILONG_birch.get(), 
				Garden_Blocks.IKEGAKILONG_jungle.get(), Garden_Blocks.IKEGAKILONG_acacia.get(), Garden_Blocks.IKEGAKILONG_darkoak.get(),
				Garden_Blocks.IKEGAKILONG_mangrove.get(), Garden_Blocks.IKEGAKILONG_cherry.get(),
				Garden_Blocks.IKEGAKILONG_sakura.get(), Garden_Blocks.IKEGAKILONG_kaede.get(), Garden_Blocks.IKEGAKILONG_ichoh.get(), Garden_Blocks.IKEGAKILONG_kare.get(),
				
				Garden_Blocks.ITABEI.get(), Garden_Blocks.ITABEI_spruce.get(), Garden_Blocks.ITABEI_birch.get(), 
				Garden_Blocks.ITABEI_jungle.get(), Garden_Blocks.ITABEI_acacia.get(), Garden_Blocks.ITABEI_darkoak.get(),
				Garden_Blocks.ITABEI_mangrove.get(), Garden_Blocks.ITABEI_cherry.get(), 
				Garden_Blocks.ITABEI_sakura.get(), Garden_Blocks.ITABEI_kaede.get(), Garden_Blocks.ITABEI_ichoh.get(), 
				Garden_Blocks.KIDO.get(), Garden_Blocks.KIDO_spruce.get(), Garden_Blocks.KIDO_birch.get(), 
				Garden_Blocks.KIDO_jungle.get(), Garden_Blocks.KIDO_acacia.get(), Garden_Blocks.KIDO_darkoak.get(),
				Garden_Blocks.KIDO_mangrove.get(), Garden_Blocks.KIDO_cherry.get(),
				Garden_Blocks.KIDO_sakura.get(), Garden_Blocks.KIDO_kaede.get(), Garden_Blocks.KIDO_ichoh.get());
		
		this.tag(BlockTags.WOODEN_BUTTONS)
		.add(Wood_Blocks.SAKURA_BUTTON.get(), Wood_Blocks.KAEDE_BUTTON.get(), Wood_Blocks.ICHOH_BUTTON.get());
		
		this.tag(BlockTags.WOODEN_DOORS)
		.add(Wood_Blocks.DOOR_SAKURA.get(), Wood_Blocks.DOOR_KAEDE.get(), Wood_Blocks.DOOR_ICHOH.get());
		
		this.tag(BlockTags.WOODEN_FENCES)
		.add(Wood_Blocks.SAKURA_FENCE.get(), Wood_Blocks.KAEDE_FENCE.get(), Wood_Blocks.ICHOH_FENCE.get());
		
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
		.add(Wood_Blocks.SAKURA_PLATE.get(), Wood_Blocks.KAEDE_PLATE.get(), Wood_Blocks.ICHOH_PLATE.get());
		
		this.tag(BlockTags.WOODEN_SLABS)
		.add(Wood_Blocks.SAKURA_slabhalf.get(), Wood_Blocks.KAEDE_slabhalf.get(), Wood_Blocks.ICHOH_slabhalf.get());
		
		this.tag(BlockTags.WOODEN_STAIRS)
		.add(Wood_Blocks.SAKURA_stairs.get(), Wood_Blocks.KAEDE_stairs.get(), Wood_Blocks.ICHOH_stairs.get());
		
		this.tag(BlockTags.WOODEN_TRAPDOORS)
		.add(Wood_Blocks.SAKURA_TRAPDOOR.get(), Wood_Blocks.KAEDE_TRAPDOOR.get(), Wood_Blocks.ICHOH_TRAPDOOR.get());
		
		
		/** MINEABLE **/
		this.tag(BlockTags.MINEABLE_WITH_AXE)
		.add(Chinjufu_Blocks.EMPTY_BOX.get(), Chinjufu_Blocks.AMMO_BOX.get(), Chinjufu_Blocks.BAUXITE_BOX.get(),
				Chinjufu_Blocks.I_ADMIRAL_STAMP.get(), 
				
				Chair_Blocks.ADMIRALCHAIR.get(), Chair_Blocks.ADMIRALCHAIR_red.get(),
				Chair_Blocks.DININGCHAIR.get(), Chair_Blocks.DININGCHAIR_spruce.get(), Chair_Blocks.DININGCHAIR_birch.get(),
				Chair_Blocks.DININGCHAIR_jungle.get(), Chair_Blocks.DININGCHAIR_acacia.get(), Chair_Blocks.DININGCHAIR_darkoak.get(),
				Chair_Blocks.DININGCHAIR_mangrove.get(), Chair_Blocks.DININGCHAIR_cherry.get(),
				Chair_Blocks.DININGCHAIR_sakura.get(), Chair_Blocks.DININGCHAIR_kaede.get(), Chair_Blocks.DININGCHAIR_ichoh.get(),
				Chair_Blocks.LOGCHAIR.get(), Chair_Blocks.LOGCHAIR_spruce.get(), Chair_Blocks.LOGCHAIR_birch.get(),
				Chair_Blocks.LOGCHAIR_jungle.get(), Chair_Blocks.LOGCHAIR_acacia.get(), Chair_Blocks.LOGCHAIR_darkoak.get(),
				Chair_Blocks.LOGCHAIR_mangrove.get(), Chair_Blocks.LOGCHAIR_cherry.get(),
				Chair_Blocks.LOGCHAIR_sakura.get(), Chair_Blocks.LOGCHAIR_kaede.get(), Chair_Blocks.LOGCHAIR_ichoh.get(),
				
				Chair_Blocks.CAFECHAIR_white.get(), Chair_Blocks.CAFECHAIR_orange.get(), Chair_Blocks.CAFECHAIR_magenta.get(),
				Chair_Blocks.CAFECHAIR_lightb.get(), Chair_Blocks.CAFECHAIR_yellow.get(), Chair_Blocks.CAFECHAIR_lime.get(),
				Chair_Blocks.CAFECHAIR_pink.get(), Chair_Blocks.CAFECHAIR_gray.get(), Chair_Blocks.CAFECHAIR_lightg.get(),
				Chair_Blocks.CAFECHAIR_cyan.get(), Chair_Blocks.CAFECHAIR_purple.get(), Chair_Blocks.CAFECHAIR_blue.get(),
				Chair_Blocks.CAFECHAIR_brown.get(), Chair_Blocks.CAFECHAIR_green.get(), Chair_Blocks.CAFECHAIR_red.get(),
				Chair_Blocks.CAFECHAIR_black.get(), Chair_Blocks.SOFA_leather.get(),
				Chair_Blocks.SOFA_white.get(), Chair_Blocks.SOFA_orange.get(), Chair_Blocks.SOFA_magenta.get(), Chair_Blocks.SOFA_lightb.get(), 
				Chair_Blocks.SOFA_yellow.get(), Chair_Blocks.SOFA_lime.get(), Chair_Blocks.SOFA_pink.get(), Chair_Blocks.SOFA_gray.get(), 
				Chair_Blocks.SOFA_lightg.get(), Chair_Blocks.SOFA_cyan.get(), Chair_Blocks.SOFA_purple.get(), Chair_Blocks.SOFA_blue.get(),
				Chair_Blocks.SOFA_brown.get(), Chair_Blocks.SOFA_green.get(), Chair_Blocks.SOFA_red.get(), Chair_Blocks.SOFA_black.get(),
				
				Chair_Blocks.BENCH.get(), Chair_Blocks.BENCH_spruce.get(), Chair_Blocks.BENCH_birch.get(),
				Chair_Blocks.BENCH_jungle.get(), Chair_Blocks.BENCH_acacia.get(), Chair_Blocks.BENCH_darkoak.get(),
				Chair_Blocks.BENCH_mangrove.get(), Chair_Blocks.BENCH_cherry.get(),
				Chair_Blocks.BENCH_sakura.get(), Chair_Blocks.BENCH_kaede.get(), Chair_Blocks.BENCH_ichoh.get(),
				
				Crop_Blocks.SEEDSBOX.get(), 
				Crop_Blocks.AZUKI.get(), Crop_Blocks.CABBAGE.get(), Crop_Blocks.HAKUSAI.get(),
				Crop_Blocks.CORN.get(), Crop_Blocks.GREENONION.get(), Crop_Blocks.ONION.get(),
				Crop_Blocks.RICE.get(), Crop_Blocks.RICE_8.get(), Crop_Blocks.SOY.get(),
				Crop_Blocks.SPINACH.get(), Crop_Blocks.TOMATO.get(), Crop_Blocks.SAKURA.get(),
				Crop_Blocks.CHANOKI.get(), Crop_Blocks.BUDOUNOKI.get(), Crop_Blocks.MIKAN.get(),
				Crop_Blocks.HODAGI_A_BOT.get(), Crop_Blocks.HODAGI_A_TOP.get(), 
				Crop_Blocks.HODAGI_B_BOT.get(),Crop_Blocks.HODAGI_B_TOP.get(), 
				Crop_Blocks.HODAGI_C_BOT.get(), Crop_Blocks.HODAGI_C_TOP.get(),
				Crop_Blocks.PEPPER.get(), Crop_Blocks.CUMIN.get(), Crop_Blocks.TURMERIC.get(), 
				Crop_Blocks.CHILI.get(), Crop_Blocks.VANILLA.get(), Crop_Blocks.INAGI.get(),
				Crop_Blocks.NORIAMI.get(), Crop_Blocks.SHIKAKE_AMI.get(), Crop_Blocks.YOUSHOKU_AMI.get(),
				Crop_Blocks.TENGUSA.get(), Crop_Blocks.NORI.get(),
				
				Dish_Blocks.NIBOSHI.get(), Dish_Blocks.MISOSOUP.get(), Dish_Blocks.ZENZAI_M.get(), Dish_Blocks.ZENZAI_K.get(),
				Dish_Blocks.PIZZA.get(), Dish_Blocks.PIZZA_C.get(), Dish_Blocks.PIZZA_T.get(), Dish_Blocks.PIZZA_S.get(),
				
				Dish_Blocks.SUSHIMESHI.get(), Dish_Blocks.SUSHIGETA_kara.get(), Dish_Blocks.SUSHIGETA_salmon.get(), 
				Dish_Blocks.SUSHIGETA_fish.get(), Dish_Blocks.SUSHIGETA_beef.get(), Dish_Blocks.SUSHIGETA_tamago.get(), 
				Dish_Blocks.SUSHISET_salmon.get(), Dish_Blocks.SUSHISET_fish.get(), Dish_Blocks.SUSHISET_beef.get(), 
				Dish_Blocks.SUSHISET_tamago.get(), Dish_Blocks.SUSHISET_4shoku.get(), 
				Dish_Blocks.SUSHIOKE.get(), Dish_Blocks.SUSHIOKE_FULL_1.get(), Dish_Blocks.SUSHIOKE_FULL_9.get(),
				
				Dish_Blocks.IRORISAKANA_E1.get(), Dish_Blocks.IRORISAKANA_E2.get(), 
				Dish_Blocks.IRORISAKANA_R1.get(), Dish_Blocks.IRORISAKANA_R2.get(), Dish_Blocks.IRORISAKANA_C.get(),
				
				Furniture_Blocks.DRESSINGTABLE.get(), Furniture_Blocks.DRESSINGTABLE_spruce.get(), Furniture_Blocks.DRESSINGTABLE_birch.get(), 
				Furniture_Blocks.DRESSINGTABLE_jungle.get(), Furniture_Blocks.DRESSINGTABLE_acacia.get(), Furniture_Blocks.DRESSINGTABLE_darkoak.get(),
				Furniture_Blocks.DRESSINGTABLE_mangrove.get(), Furniture_Blocks.DRESSINGTABLE_cherry.get(),
				Furniture_Blocks.DRESSINGTABLE_sakura.get(), Furniture_Blocks.DRESSINGTABLE_kaede.get(), Furniture_Blocks.DRESSINGTABLE_ichoh.get(),
				Furniture_Blocks.TANSU_OAK.get(), Furniture_Blocks.TANSU_SPRUCE.get(), Furniture_Blocks.TANSU_BIRCH.get(), 
				Furniture_Blocks.TANSU_JUNGLE.get(), Furniture_Blocks.TANSU_ACACIA.get(), Furniture_Blocks.TANSU_DOAK.get(),
				Furniture_Blocks.TANSU_MANGROVE.get(), Furniture_Blocks.TANSU_CHERRY.get(),
				Furniture_Blocks.TANSU_SAKURA.get(), Furniture_Blocks.TANSU_KAEDE.get(), Furniture_Blocks.TANSU_ICHOH.get(), 
	
				Furniture_Blocks.OFFICEDESK_OAK.get(), Furniture_Blocks.OFFICEDESK_SPRUCE.get(), Furniture_Blocks.OFFICEDESK_BIRCH.get(), 
				Furniture_Blocks.OFFICEDESK_JUNGLE.get(), Furniture_Blocks.OFFICEDESK_ACACIA.get(), Furniture_Blocks.OFFICEDESK_DOAK.get(),
				Furniture_Blocks.OFFICEDESK_MANGROVE.get(), Furniture_Blocks.OFFICEDESK_CHERRY.get(),
				Furniture_Blocks.OFFICEDESK_SAKURA.get(), Furniture_Blocks.OFFICEDESK_KAEDE.get(), Furniture_Blocks.OFFICEDESK_ICHOH.get(), 
				Furniture_Blocks.DESKCLOTH_03.get(), Furniture_Blocks.DESKCLOTH_47.get(), Furniture_Blocks.DESKCLOTH_811.get(), Furniture_Blocks.DESKCLOTH_1215.get(), 
				Furniture_Blocks.DESKBOOK_1.get(), Furniture_Blocks.DESKBOOK_2.get(), Furniture_Blocks.DESKBOOK_3.get(), 
				Furniture_Blocks.NOTEBOOK.get(), Furniture_Blocks.NOTEBOOK_B.get(), Furniture_Blocks.NOTEBOOK_2.get(), Furniture_Blocks.NOTEBOOK_3.get(),
				
				Garden_Blocks.SUDARE.get(),
				Garden_Blocks.TAKEAKARI.get(), Garden_Blocks.TAKEAKARI_Y.get(), Garden_Blocks.TAKEAKARI_K.get(),
				Garden_Blocks.BONSAI_oak.get(), Garden_Blocks.BONSAI_spru.get(), Garden_Blocks.BONSAI_bir.get(), 
				Garden_Blocks.BONSAI_jun.get(), Garden_Blocks.BONSAI_aca.get(), Garden_Blocks.BONSAI_doak.get(), 
				Garden_Blocks.BONSAI_mangrove.get(), Garden_Blocks.BONSAI_cherry.get(),
				Garden_Blocks.BONSAI_sakura .get(), Garden_Blocks.BONSAI_kaede.get(), Garden_Blocks.BONSAI_ichoh.get(), Garden_Blocks.BONSAI_kare.get(), 
				Garden_Blocks.KANYOU.get(), Garden_Blocks.KANYOU_spruce.get(), Garden_Blocks.KANYOU_birch.get(), 
				Garden_Blocks.KANYOU_jungle.get(), Garden_Blocks.KANYOU_acacia.get(), Garden_Blocks.KANYOU_darkoak.get(), 
				Garden_Blocks.KANYOU_mangrove.get(), Garden_Blocks.KANYOU_cherry.get(),
				Garden_Blocks.KANYOU_sakura.get(), Garden_Blocks.KANYOU_kaede.get(), Garden_Blocks.KANYOU_ichoh.get(), Garden_Blocks.KANYOU_kare.get(), 
	
				Garden_Blocks.IKEGAKI.get(), Garden_Blocks.IKEGAKI_spruce.get(), Garden_Blocks.IKEGAKI_birch.get(), 
				Garden_Blocks.IKEGAKI_jungle.get(), Garden_Blocks.IKEGAKI_acacia.get(), Garden_Blocks.IKEGAKI_darkoak.get(),
				Garden_Blocks.IKEGAKI_mangrove.get(), Garden_Blocks.IKEGAKI_cherry.get(),
				Garden_Blocks.IKEGAKI_sakura.get(), Garden_Blocks.IKEGAKI_kaede.get(), Garden_Blocks.IKEGAKI_ichoh.get(), Garden_Blocks.IKEGAKI_kare.get(), 
				Garden_Blocks.IKEGAKILONG.get(), Garden_Blocks.IKEGAKILONG_spruce.get(), Garden_Blocks.IKEGAKILONG_birch.get(), 
				Garden_Blocks.IKEGAKILONG_jungle.get(), Garden_Blocks.IKEGAKILONG_acacia.get(), Garden_Blocks.IKEGAKILONG_darkoak.get(),
				Garden_Blocks.IKEGAKILONG_mangrove.get(), Garden_Blocks.IKEGAKILONG_cherry.get(),
				Garden_Blocks.IKEGAKILONG_sakura.get(), Garden_Blocks.IKEGAKILONG_kaede.get(), Garden_Blocks.IKEGAKILONG_ichoh.get(), Garden_Blocks.IKEGAKILONG_kare.get(),
				
				Garden_Blocks.ITABEI.get(), Garden_Blocks.ITABEI_spruce.get(), Garden_Blocks.ITABEI_birch.get(), 
				Garden_Blocks.ITABEI_jungle.get(), Garden_Blocks.ITABEI_acacia.get(), Garden_Blocks.ITABEI_darkoak.get(),
				Garden_Blocks.ITABEI_mangrove.get(), Garden_Blocks.ITABEI_cherry.get(),
				Garden_Blocks.ITABEI_sakura.get(), Garden_Blocks.ITABEI_kaede.get(), Garden_Blocks.ITABEI_ichoh.get(), 
				Garden_Blocks.KIDO.get(), Garden_Blocks.KIDO_spruce.get(), Garden_Blocks.KIDO_birch.get(), 
				Garden_Blocks.KIDO_jungle.get(), Garden_Blocks.KIDO_acacia.get(), Garden_Blocks.KIDO_darkoak.get(),
				Garden_Blocks.KIDO_mangrove.get(), Garden_Blocks.KIDO_cherry.get(),
				Garden_Blocks.KIDO_sakura.get(), Garden_Blocks.KIDO_kaede.get(), Garden_Blocks.KIDO_ichoh.get(),
				Gate_Blocks.GATE_SPRUCE.get(), Gate_Blocks.GATE_SPRUCE_B.get(),
				
				Hakkou_Blocks.MIZUOKE.get(),Hakkou_Blocks.MIZUOKE_full.get(), 
				Hakkou_Blocks.HAKKOU_TARU.get(), Hakkou_Blocks.KOUJI_TARU.get(), 
				Hakkou_Blocks.SHUBO_TARU.get(), Hakkou_Blocks.MOROMI_TARU.get(), Hakkou_Blocks.JUKUSEI_TARU.get(),
				Hakkou_Blocks.RINGOSHU_TARU.get(), Hakkou_Blocks.BUDOUSHU_TARU.get(), Hakkou_Blocks.HACHIMITSU_TARU.get(), 
				Hakkou_Blocks.COCOA_TARU.get(),
				
				Hakkou_Blocks.MISO_TARU.get(), Hakkou_Blocks.HAKUSAI_TARU1.get(), Hakkou_Blocks.HAKUSAI_TARU2.get(), 
				Hakkou_Blocks.SHOUYU_TARU.get(), Hakkou_Blocks.KOMEZU_TARU.get(), Hakkou_Blocks.KINOKO_TARU.get(), 
				Hakkou_Blocks.KONBU_TARU.get(), Hakkou_Blocks.NORI_TARU.get(), Hakkou_Blocks.KOUCHA_TARU.get(), 
				Hakkou_Blocks.PEPPER_TARU.get(), Hakkou_Blocks.VANILLA_TARU.get(), Dish_Blocks.TENGUSA_WASH.get(), 
				
				Hakkou_Blocks.CHEESE_CURD.get(), Hakkou_Blocks.CHEESE.get(), Hakkou_Blocks.WINE_TANA.get(), 
				Hakkou_Blocks.KIT_SAKENAMA.get(), Hakkou_Blocks.KIT_SAKE.get(), Hakkou_Blocks.KIT_SAKEJUKU.get(), 
				Hakkou_Blocks.KIT_CIDER.get(), Hakkou_Blocks.KIT_CIDERJUKU.get(), 
				Hakkou_Blocks.KIT_WINE.get(), Hakkou_Blocks.KIT_WINEJUKU.get(), 
				Hakkou_Blocks.KIT_MEAD.get(), Hakkou_Blocks.KIT_MEADJUKU.get(),
				Hakkou_Blocks.CHEESE_TANA.get(), Hakkou_Blocks.CHEESE_OAA.get(), Hakkou_Blocks.CHEESE_AAA.get(),
				
				JPChair_Blocks.ZABUTON_white.get(), JPChair_Blocks.ZABUTON_orange.get(), JPChair_Blocks.ZABUTON_magenta.get(), JPChair_Blocks.ZABUTON_lightb.get(), 
				JPChair_Blocks.ZABUTON_yellow.get(), JPChair_Blocks.ZABUTON_lime.get(), JPChair_Blocks.ZABUTON_pink.get(), JPChair_Blocks.ZABUTON_gray.get(), 
				JPChair_Blocks.ZABUTON_lightg.get(), JPChair_Blocks.ZABUTON_cyan.get(), JPChair_Blocks.ZABUTON_purple.get(), JPChair_Blocks.ZABUTON_blue.get(),
				JPChair_Blocks.ZABUTON_brown.get(), JPChair_Blocks.ZABUTON_green.get(), JPChair_Blocks.ZABUTON_red.get(), JPChair_Blocks.ZABUTON_black.get(),
				JPChair_Blocks.WARAZABUTON.get(),
				JPChair_Blocks.ZAISU_white.get(), JPChair_Blocks.ZAISU_orange.get(), JPChair_Blocks.ZAISU_magenta.get(), JPChair_Blocks.ZAISU_lightb.get(), 
				JPChair_Blocks.ZAISU_yellow.get(), JPChair_Blocks.ZAISU_lime.get(), JPChair_Blocks.ZAISU_pink.get(), JPChair_Blocks.ZAISU_gray.get(), 
				JPChair_Blocks.ZAISU_lightg.get(), JPChair_Blocks.ZAISU_cyan.get(), JPChair_Blocks.ZAISU_purple.get(), JPChair_Blocks.ZAISU_blue.get(),
				JPChair_Blocks.ZAISU_brown.get(), JPChair_Blocks.ZAISU_green.get(), JPChair_Blocks.ZAISU_red.get(), JPChair_Blocks.ZAISU_black.get(),
				
				JPDeco_Blocks.TATAMI_H.get(), JPDeco_Blocks.TATAMI_HY.get(), 
				JPDeco_Blocks.TATAMI_H_white.get(), JPDeco_Blocks.TATAMI_H_orange.get(), JPDeco_Blocks.TATAMI_H_magenta.get(), JPDeco_Blocks.TATAMI_H_lightb.get(), 
				JPDeco_Blocks.TATAMI_H_yellow.get(), JPDeco_Blocks.TATAMI_H_lime.get(), JPDeco_Blocks.TATAMI_H_pink.get(), JPDeco_Blocks.TATAMI_H_gray.get(), 
				JPDeco_Blocks.TATAMI_H_lightg.get(), JPDeco_Blocks.TATAMI_H_cyan.get(), JPDeco_Blocks.TATAMI_H_purple.get(), JPDeco_Blocks.TATAMI_H_blue.get(),
				JPDeco_Blocks.TATAMI_H_brown.get(), JPDeco_Blocks.TATAMI_H_green.get(), JPDeco_Blocks.TATAMI_H_red.get(), JPDeco_Blocks.TATAMI_H_black.get(), 
				JPDeco_Blocks.TATAMI_HY_white.get(), JPDeco_Blocks.TATAMI_HY_orange.get(), JPDeco_Blocks.TATAMI_HY_magenta.get(), JPDeco_Blocks.TATAMI_HY_lightb.get(), 
				JPDeco_Blocks.TATAMI_HY_yellow.get(), JPDeco_Blocks.TATAMI_HY_lime.get(), JPDeco_Blocks.TATAMI_HY_pink.get(), JPDeco_Blocks.TATAMI_HY_gray.get(), 
				JPDeco_Blocks.TATAMI_HY_lightg.get(), JPDeco_Blocks.TATAMI_HY_cyan.get(), JPDeco_Blocks.TATAMI_HY_purple.get(), JPDeco_Blocks.TATAMI_HY_blue.get(),
				JPDeco_Blocks.TATAMI_HY_brown.get(), JPDeco_Blocks.TATAMI_HY_green.get(), JPDeco_Blocks.TATAMI_HY_red.get(), JPDeco_Blocks.TATAMI_HY_black.get(),
				
				JPDeco_Blocks.TAKECUBE.get(), JPDeco_Blocks.TAKECUBE_Y.get(), JPDeco_Blocks.TAKECUBE_K.get(),
				JPDeco_Blocks.TAKE_ST.get(), JPDeco_Blocks.TAKE_STY.get(), JPDeco_Blocks.TAKE_STK.get(),
				JPDeco_Blocks.TAKE_SH.get(), JPDeco_Blocks.TAKE_SHY.get(), JPDeco_Blocks.TAKE_SHK.get(),
				JPDeco_Blocks.TAKEFENCE.get(), JPDeco_Blocks.TAKEFENCE_Y.get(), JPDeco_Blocks.TAKEFENCE_K.get(),
				JPDeco_Blocks.TAKEFENCEGATE.get(), JPDeco_Blocks.TAKEFENCEGATE_Y.get(), JPDeco_Blocks.TAKEFENCEGATE_K.get(),
				JPDeco_Blocks.TAKEDOOR.get(), JPDeco_Blocks.TAKEDOOR_Y.get(), JPDeco_Blocks.TAKEDOOR_K.get(),
				JPDeco_Blocks.TAKE_TRAPDOOR.get(), JPDeco_Blocks.TAKE_TRAPDOOR_Y.get(), JPDeco_Blocks.TAKE_TRAPDOOR_K.get(),
				JPDeco_Blocks.TAKE_PLATE.get(), JPDeco_Blocks.TAKE_PLATE_Y.get(), JPDeco_Blocks.TAKE_PLATE_K.get(),
				JPDeco_Blocks.TAKE_BUTTON.get(), JPDeco_Blocks.TAKE_BUTTON_Y.get(), JPDeco_Blocks.TAKE_BUTTON_K.get(),
				
				JPDeco_Blocks.ANDON_white.get(), JPDeco_Blocks.ANDON_orange.get(), JPDeco_Blocks.ANDON_magenta.get(), JPDeco_Blocks.ANDON_lightb.get(), 
				JPDeco_Blocks.ANDON_yellow.get(), JPDeco_Blocks.ANDON_lime.get(), JPDeco_Blocks.ANDON_pink.get(), JPDeco_Blocks.ANDON_gray.get(), 
				JPDeco_Blocks.ANDON_lightg.get(), JPDeco_Blocks.ANDON_cyan.get(), JPDeco_Blocks.ANDON_purple.get(), JPDeco_Blocks.ANDON_blue.get(),
				JPDeco_Blocks.ANDON_brown.get(), JPDeco_Blocks.ANDON_green.get(), JPDeco_Blocks.ANDON_red.get(), JPDeco_Blocks.ANDON_black.get(),
				JPDeco_Blocks.FUTON_white.get(), JPDeco_Blocks.FUTON_orange.get(), JPDeco_Blocks.FUTON_magenta.get(), JPDeco_Blocks.FUTON_lightb.get(), 
				JPDeco_Blocks.FUTON_yellow.get(), JPDeco_Blocks.FUTON_lime.get(), JPDeco_Blocks.FUTON_pink.get(), JPDeco_Blocks.FUTON_gray.get(), 
				JPDeco_Blocks.FUTON_lightg.get(), JPDeco_Blocks.FUTON_cyan.get(), JPDeco_Blocks.FUTON_purple.get(), JPDeco_Blocks.FUTON_blue.get(),
				JPDeco_Blocks.FUTON_brown.get(), JPDeco_Blocks.FUTON_green.get(), JPDeco_Blocks.FUTON_red.get(), JPDeco_Blocks.FUTON_black.get(),
				JPDeco_Blocks.WADAIKO.get(), JPDeco_Blocks.WADAIKO_small.get(), 
				
				KamoiPlanks_Blocks.KAMOI_oak_oak.get(), KamoiPlanks_Blocks.KAMOI_spru_oak.get(), KamoiPlanks_Blocks.KAMOI_bir_oak.get(),
				KamoiPlanks_Blocks.KAMOI_jun_oak.get(), KamoiPlanks_Blocks.KAMOI_aca_oak.get(), KamoiPlanks_Blocks.KAMOI_doak_oak.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_oak.get(), KamoiPlanks_Blocks.KAMOI_cherry_oak.get(),
				KamoiPlanks_Blocks.KAMOI_saku_oak.get(), KamoiPlanks_Blocks.KAMOI_kae_oak.get(), KamoiPlanks_Blocks.KAMOI_ich_oak.get(),
				KamoiPlanks_Blocks.KAMOI_oak_spru.get(), KamoiPlanks_Blocks.KAMOI_spru_spru.get(), KamoiPlanks_Blocks.KAMOI_bir_spru.get(),
				KamoiPlanks_Blocks.KAMOI_jun_spru.get(), KamoiPlanks_Blocks.KAMOI_aca_spru.get(), KamoiPlanks_Blocks.KAMOI_doak_spru.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_spru.get(), KamoiPlanks_Blocks.KAMOI_cherry_spru.get(),
				KamoiPlanks_Blocks.KAMOI_saku_spru.get(), KamoiPlanks_Blocks.KAMOI_kae_spru.get(), KamoiPlanks_Blocks.KAMOI_ich_spru.get(),
				KamoiPlanks_Blocks.KAMOI_oak_bir.get(), KamoiPlanks_Blocks.KAMOI_spru_bir.get(), KamoiPlanks_Blocks.KAMOI_bir_bir.get(),
				KamoiPlanks_Blocks.KAMOI_jun_bir.get(), KamoiPlanks_Blocks.KAMOI_aca_bir.get(), KamoiPlanks_Blocks.KAMOI_doak_bir.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_bir.get(), KamoiPlanks_Blocks.KAMOI_cherry_bir.get(),
				KamoiPlanks_Blocks.KAMOI_saku_bir.get(), KamoiPlanks_Blocks.KAMOI_kae_bir.get(), KamoiPlanks_Blocks.KAMOI_ich_bir.get(),
				
				KamoiPlanks_Blocks.KAMOI_oak_jun.get(), KamoiPlanks_Blocks.KAMOI_spru_jun.get(), KamoiPlanks_Blocks.KAMOI_bir_jun.get(),
				KamoiPlanks_Blocks.KAMOI_jun_jun.get(), KamoiPlanks_Blocks.KAMOI_aca_jun.get(), KamoiPlanks_Blocks.KAMOI_doak_jun.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_jun.get(), KamoiPlanks_Blocks.KAMOI_cherry_jun.get(),
				KamoiPlanks_Blocks.KAMOI_saku_jun.get(), KamoiPlanks_Blocks.KAMOI_kae_jun.get(), KamoiPlanks_Blocks.KAMOI_ich_jun.get(),
				KamoiPlanks_Blocks.KAMOI_oak_aca.get(), KamoiPlanks_Blocks.KAMOI_spru_aca.get(), KamoiPlanks_Blocks.KAMOI_bir_aca.get(),
				KamoiPlanks_Blocks.KAMOI_jun_aca.get(), KamoiPlanks_Blocks.KAMOI_aca_aca.get(), KamoiPlanks_Blocks.KAMOI_doak_aca.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_aca.get(), KamoiPlanks_Blocks.KAMOI_cherry_aca.get(),
				KamoiPlanks_Blocks.KAMOI_saku_aca.get(), KamoiPlanks_Blocks.KAMOI_kae_aca.get(), KamoiPlanks_Blocks.KAMOI_ich_aca.get(),
				KamoiPlanks_Blocks.KAMOI_oak_doak.get(), KamoiPlanks_Blocks.KAMOI_spru_doak.get(), KamoiPlanks_Blocks.KAMOI_bir_doak.get(),
				KamoiPlanks_Blocks.KAMOI_jun_doak.get(), KamoiPlanks_Blocks.KAMOI_aca_doak.get(), KamoiPlanks_Blocks.KAMOI_doak_doak.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_doak.get(), KamoiPlanks_Blocks.KAMOI_cherry_doak.get(),
				KamoiPlanks_Blocks.KAMOI_saku_doak.get(), KamoiPlanks_Blocks.KAMOI_kae_doak.get(), KamoiPlanks_Blocks.KAMOI_ich_doak.get(),
				
				KamoiPlanks_Blocks.KAMOI_oak_mangrove.get(), KamoiPlanks_Blocks.KAMOI_spru_mangrove.get(), KamoiPlanks_Blocks.KAMOI_bir_mangrove.get(),
				KamoiPlanks_Blocks.KAMOI_jun_mangrove.get(), KamoiPlanks_Blocks.KAMOI_aca_mangrove.get(), KamoiPlanks_Blocks.KAMOI_doak_mangrove.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_mangrove.get(), KamoiPlanks_Blocks.KAMOI_cherry_mangrove.get(),
				KamoiPlanks_Blocks.KAMOI_saku_mangrove.get(), KamoiPlanks_Blocks.KAMOI_kae_mangrove.get(), KamoiPlanks_Blocks.KAMOI_ich_mangrove.get(),
				KamoiPlanks_Blocks.KAMOI_oak_cherry.get(), KamoiPlanks_Blocks.KAMOI_spru_cherry.get(), KamoiPlanks_Blocks.KAMOI_bir_cherry.get(),
				KamoiPlanks_Blocks.KAMOI_jun_cherry.get(), KamoiPlanks_Blocks.KAMOI_aca_cherry.get(), KamoiPlanks_Blocks.KAMOI_doak_cherry.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_cherry.get(), KamoiPlanks_Blocks.KAMOI_cherry_cherry.get(),
				KamoiPlanks_Blocks.KAMOI_saku_cherry.get(), KamoiPlanks_Blocks.KAMOI_kae_cherry.get(), KamoiPlanks_Blocks.KAMOI_ich_cherry.get(),
				
				KamoiPlanks_Blocks.KAMOI_oak_sakura.get(), KamoiPlanks_Blocks.KAMOI_spru_sakura.get(), KamoiPlanks_Blocks.KAMOI_bir_sakura.get(),
				KamoiPlanks_Blocks.KAMOI_jun_sakura.get(), KamoiPlanks_Blocks.KAMOI_aca_sakura.get(), KamoiPlanks_Blocks.KAMOI_doak_sakura.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_sakura.get(), KamoiPlanks_Blocks.KAMOI_cherry_sakura.get(),
				KamoiPlanks_Blocks.KAMOI_saku_sakura.get(), KamoiPlanks_Blocks.KAMOI_kae_sakura.get(), KamoiPlanks_Blocks.KAMOI_ich_sakura.get(),
				KamoiPlanks_Blocks.KAMOI_oak_kaede.get(), KamoiPlanks_Blocks.KAMOI_spru_kaede.get(), KamoiPlanks_Blocks.KAMOI_bir_kaede.get(),
				KamoiPlanks_Blocks.KAMOI_jun_kaede.get(), KamoiPlanks_Blocks.KAMOI_aca_kaede.get(), KamoiPlanks_Blocks.KAMOI_doak_kaede.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_kaede.get(), KamoiPlanks_Blocks.KAMOI_cherry_kaede.get(),
				KamoiPlanks_Blocks.KAMOI_saku_kaede.get(), KamoiPlanks_Blocks.KAMOI_kae_kaede.get(), KamoiPlanks_Blocks.KAMOI_ich_kaede.get(),
				KamoiPlanks_Blocks.KAMOI_oak_ichoh.get(), KamoiPlanks_Blocks.KAMOI_spru_ichoh.get(), KamoiPlanks_Blocks.KAMOI_bir_ichoh.get(),
				KamoiPlanks_Blocks.KAMOI_jun_ichoh.get(), KamoiPlanks_Blocks.KAMOI_aca_ichoh.get(), KamoiPlanks_Blocks.KAMOI_doak_ichoh.get(),
				KamoiPlanks_Blocks.KAMOI_mangrove_ichoh.get(), KamoiPlanks_Blocks.KAMOI_cherry_ichoh.get(),
				KamoiPlanks_Blocks.KAMOI_saku_ichoh.get(), KamoiPlanks_Blocks.KAMOI_kae_ichoh.get(), KamoiPlanks_Blocks.KAMOI_ich_ichoh.get(),
				
				KamoiPlaster_Blocks.KAMOI_dirt_oak.get(), KamoiPlaster_Blocks.KAMOI_dirt_spru.get(), KamoiPlaster_Blocks.KAMOI_dirt_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_dirt_jun.get(), KamoiPlaster_Blocks.KAMOI_dirt_aca.get(), KamoiPlaster_Blocks.KAMOI_dirt_doak.get(),
				KamoiPlaster_Blocks.KAMOI_dirt_mangrove.get(), KamoiPlaster_Blocks.KAMOI_dirt_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_dirt_sakura.get(), KamoiPlaster_Blocks.KAMOI_dirt_kaede.get(), KamoiPlaster_Blocks.KAMOI_dirt_ichoh.get(),
				
				KamoiPlaster_Blocks.KAMOI_black_oak.get(), KamoiPlaster_Blocks.KAMOI_black_spru.get(), KamoiPlaster_Blocks.KAMOI_black_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_black_jun.get(), KamoiPlaster_Blocks.KAMOI_black_aca.get(), KamoiPlaster_Blocks.KAMOI_black_doak.get(),
				KamoiPlaster_Blocks.KAMOI_black_mangrove.get(), KamoiPlaster_Blocks.KAMOI_black_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_black_sakura.get(), KamoiPlaster_Blocks.KAMOI_black_kaede.get(), KamoiPlaster_Blocks.KAMOI_black_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_blue_oak.get(), KamoiPlaster_Blocks.KAMOI_blue_spru.get(), KamoiPlaster_Blocks.KAMOI_blue_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_blue_jun.get(), KamoiPlaster_Blocks.KAMOI_blue_aca.get(), KamoiPlaster_Blocks.KAMOI_blue_doak.get(),
				KamoiPlaster_Blocks.KAMOI_blue_mangrove.get(), KamoiPlaster_Blocks.KAMOI_blue_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_blue_sakura.get(), KamoiPlaster_Blocks.KAMOI_blue_kaede.get(), KamoiPlaster_Blocks.KAMOI_blue_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_brown_oak.get(), KamoiPlaster_Blocks.KAMOI_brown_spru.get(), KamoiPlaster_Blocks.KAMOI_brown_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_brown_jun.get(), KamoiPlaster_Blocks.KAMOI_brown_aca.get(), KamoiPlaster_Blocks.KAMOI_brown_doak.get(),
				KamoiPlaster_Blocks.KAMOI_brown_mangrove.get(), KamoiPlaster_Blocks.KAMOI_brown_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_brown_sakura.get(), KamoiPlaster_Blocks.KAMOI_brown_kaede.get(), KamoiPlaster_Blocks.KAMOI_brown_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_cyan_oak.get(), KamoiPlaster_Blocks.KAMOI_cyan_spru.get(), KamoiPlaster_Blocks.KAMOI_cyan_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_cyan_jun.get(), KamoiPlaster_Blocks.KAMOI_cyan_aca.get(), KamoiPlaster_Blocks.KAMOI_cyan_doak.get(),
				KamoiPlaster_Blocks.KAMOI_cyan_mangrove.get(), KamoiPlaster_Blocks.KAMOI_cyan_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_cyan_sakura.get(), KamoiPlaster_Blocks.KAMOI_cyan_kaede.get(), KamoiPlaster_Blocks.KAMOI_cyan_ichoh.get(),
				
				KamoiPlaster_Blocks.KAMOI_gray_oak.get(), KamoiPlaster_Blocks.KAMOI_gray_spru.get(), KamoiPlaster_Blocks.KAMOI_gray_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_gray_jun.get(), KamoiPlaster_Blocks.KAMOI_gray_aca.get(), KamoiPlaster_Blocks.KAMOI_gray_doak.get(),
				KamoiPlaster_Blocks.KAMOI_gray_mangrove.get(), KamoiPlaster_Blocks.KAMOI_gray_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_gray_sakura.get(), KamoiPlaster_Blocks.KAMOI_gray_kaede.get(), KamoiPlaster_Blocks.KAMOI_gray_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_green_oak.get(), KamoiPlaster_Blocks.KAMOI_green_spru.get(), KamoiPlaster_Blocks.KAMOI_green_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_green_jun.get(), KamoiPlaster_Blocks.KAMOI_green_aca.get(), KamoiPlaster_Blocks.KAMOI_green_doak.get(),
				KamoiPlaster_Blocks.KAMOI_green_mangrove.get(), KamoiPlaster_Blocks.KAMOI_green_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_green_sakura.get(), KamoiPlaster_Blocks.KAMOI_green_kaede.get(), KamoiPlaster_Blocks.KAMOI_green_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_lightb_oak.get(), KamoiPlaster_Blocks.KAMOI_lightb_spru.get(), KamoiPlaster_Blocks.KAMOI_lightb_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_lightb_jun.get(), KamoiPlaster_Blocks.KAMOI_lightb_aca.get(), KamoiPlaster_Blocks.KAMOI_lightb_doak.get(),
				KamoiPlaster_Blocks.KAMOI_lightb_mangrove.get(), KamoiPlaster_Blocks.KAMOI_lightb_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_lightb_sakura.get(), KamoiPlaster_Blocks.KAMOI_lightb_kaede.get(), KamoiPlaster_Blocks.KAMOI_lightb_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_lightg_oak.get(), KamoiPlaster_Blocks.KAMOI_lightg_spru.get(), KamoiPlaster_Blocks.KAMOI_lightg_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_lightg_jun.get(), KamoiPlaster_Blocks.KAMOI_lightg_aca.get(), KamoiPlaster_Blocks.KAMOI_lightg_doak.get(),
				KamoiPlaster_Blocks.KAMOI_lightg_mangrove.get(), KamoiPlaster_Blocks.KAMOI_lightg_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_lightg_sakura.get(), KamoiPlaster_Blocks.KAMOI_lightg_kaede.get(), KamoiPlaster_Blocks.KAMOI_lightg_ichoh.get(),
				
				KamoiPlaster_Blocks.KAMOI_lime_oak.get(), KamoiPlaster_Blocks.KAMOI_lime_spru.get(), KamoiPlaster_Blocks.KAMOI_lime_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_lime_jun.get(), KamoiPlaster_Blocks.KAMOI_lime_aca.get(), KamoiPlaster_Blocks.KAMOI_lime_doak.get(),
				KamoiPlaster_Blocks.KAMOI_lime_mangrove.get(), KamoiPlaster_Blocks.KAMOI_lime_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_lime_sakura.get(), KamoiPlaster_Blocks.KAMOI_lime_kaede.get(), KamoiPlaster_Blocks.KAMOI_lime_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_magenta_oak.get(), KamoiPlaster_Blocks.KAMOI_magenta_spru.get(), KamoiPlaster_Blocks.KAMOI_magenta_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_magenta_jun.get(), KamoiPlaster_Blocks.KAMOI_magenta_aca.get(), KamoiPlaster_Blocks.KAMOI_magenta_doak.get(),
				KamoiPlaster_Blocks.KAMOI_magenta_mangrove.get(), KamoiPlaster_Blocks.KAMOI_magenta_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_magenta_sakura.get(), KamoiPlaster_Blocks.KAMOI_magenta_kaede.get(), KamoiPlaster_Blocks.KAMOI_magenta_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_orange_oak.get(), KamoiPlaster_Blocks.KAMOI_orange_spru.get(), KamoiPlaster_Blocks.KAMOI_orange_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_orange_jun.get(), KamoiPlaster_Blocks.KAMOI_orange_aca.get(), KamoiPlaster_Blocks.KAMOI_orange_doak.get(),
				KamoiPlaster_Blocks.KAMOI_orange_mangrove.get(), KamoiPlaster_Blocks.KAMOI_orange_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_orange_sakura.get(), KamoiPlaster_Blocks.KAMOI_orange_kaede.get(), KamoiPlaster_Blocks.KAMOI_orange_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_pink_oak.get(), KamoiPlaster_Blocks.KAMOI_pink_spru.get(), KamoiPlaster_Blocks.KAMOI_pink_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_pink_jun.get(), KamoiPlaster_Blocks.KAMOI_pink_aca.get(), KamoiPlaster_Blocks.KAMOI_pink_doak.get(),
				KamoiPlaster_Blocks.KAMOI_pink_mangrove.get(), KamoiPlaster_Blocks.KAMOI_pink_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_pink_sakura.get(), KamoiPlaster_Blocks.KAMOI_pink_kaede.get(), KamoiPlaster_Blocks.KAMOI_pink_ichoh.get(),
				
				KamoiPlaster_Blocks.KAMOI_purple_oak.get(), KamoiPlaster_Blocks.KAMOI_purple_spru.get(), KamoiPlaster_Blocks.KAMOI_purple_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_purple_jun.get(), KamoiPlaster_Blocks.KAMOI_purple_aca.get(), KamoiPlaster_Blocks.KAMOI_purple_doak.get(),
				KamoiPlaster_Blocks.KAMOI_purple_mangrove.get(), KamoiPlaster_Blocks.KAMOI_purple_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_purple_sakura.get(), KamoiPlaster_Blocks.KAMOI_purple_kaede.get(), KamoiPlaster_Blocks.KAMOI_purple_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_red_oak.get(), KamoiPlaster_Blocks.KAMOI_red_spru.get(), KamoiPlaster_Blocks.KAMOI_red_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_red_jun.get(), KamoiPlaster_Blocks.KAMOI_red_aca.get(), KamoiPlaster_Blocks.KAMOI_red_doak.get(),
				KamoiPlaster_Blocks.KAMOI_red_mangrove.get(), KamoiPlaster_Blocks.KAMOI_red_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_red_sakura.get(), KamoiPlaster_Blocks.KAMOI_red_kaede.get(), KamoiPlaster_Blocks.KAMOI_red_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_white_oak.get(), KamoiPlaster_Blocks.KAMOI_white_spru.get(), KamoiPlaster_Blocks.KAMOI_white_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_white_jun.get(), KamoiPlaster_Blocks.KAMOI_white_aca.get(), KamoiPlaster_Blocks.KAMOI_white_doak.get(),
				KamoiPlaster_Blocks.KAMOI_white_mangrove.get(), KamoiPlaster_Blocks.KAMOI_white_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_white_sakura.get(), KamoiPlaster_Blocks.KAMOI_white_kaede.get(), KamoiPlaster_Blocks.KAMOI_white_ichoh.get(),
				KamoiPlaster_Blocks.KAMOI_yellow_oak.get(), KamoiPlaster_Blocks.KAMOI_yellow_spru.get(), KamoiPlaster_Blocks.KAMOI_yellow_bir.get(), 
				KamoiPlaster_Blocks.KAMOI_yellow_jun.get(), KamoiPlaster_Blocks.KAMOI_yellow_aca.get(), KamoiPlaster_Blocks.KAMOI_yellow_doak.get(),
				KamoiPlaster_Blocks.KAMOI_yellow_mangrove.get(), KamoiPlaster_Blocks.KAMOI_yellow_cherry.get(),
				KamoiPlaster_Blocks.KAMOI_yellow_sakura.get(), KamoiPlaster_Blocks.KAMOI_yellow_kaede.get(), KamoiPlaster_Blocks.KAMOI_yellow_ichoh.get(),

				Kitchen_Blocks.KITCHEN.get(), Kitchen_Blocks.KIT_BOARD.get(), Kitchen_Blocks.KIT_SINK.get(), Kitchen_Blocks.KIT_COOKTOP.get(),
				Kitchen_Blocks.KIT_TANA.get(), Kitchen_Blocks.KIT_CHAWAN1.get(), Kitchen_Blocks.KIT_SHIKKI1.get(), Kitchen_Blocks.KIT_SARA1.get(),
				Kitchen_Blocks.KIT_TONSUI1.get(), Kitchen_Blocks.KIT_YUNOMI1.get(), Kitchen_Blocks.KIT_TCUP1.get(), Kitchen_Blocks.KIT_DRINKGLASS1.get(),
				Kitchen_Blocks.KIT_DONBURI1.get(), Kitchen_Blocks.KIT_SUSHIGETA1.get(), Kitchen_Blocks.USU_TSUKI.get(),
				
				Pantry_Blocks.BOX_H_EMPTY.get(), Pantry_Blocks.BOX_H_EMPTY2.get(), Pantry_Blocks.BOX_H_EMPTY3.get(),
				Pantry_Blocks.BOX_H_APPLE.get(), Pantry_Blocks.BOX_H_BEEF.get(), Pantry_Blocks.BOX_H_BEETROOT.get(), Pantry_Blocks.BOX_H_BREAD.get(),
				Pantry_Blocks.BOX_H_CARROT.get(), Pantry_Blocks.BOX_H_CHICKEN.get(), Pantry_Blocks.BOX_H_CHORUS.get(), Pantry_Blocks.BOX_H_COCO.get(),
				Pantry_Blocks.BOX_H_COD.get(), Pantry_Blocks.BOX_H_EGG.get(), Pantry_Blocks.BOX_H_FISH.get(), Pantry_Blocks.BOX_H_FLOUR.get(),
				Pantry_Blocks.BOX_H_MUTTON.get(), Pantry_Blocks.BOX_H_PORK.get(), Pantry_Blocks.BOX_H_POTATO.get(), Pantry_Blocks.BOX_H_RABBIT.get(),
				Pantry_Blocks.BOX_H_SALMON.get(), Pantry_Blocks.BOX_H_SWBERRY.get(),
				
				Pantry_Blocks.BOX_H_AZUKI.get(), Pantry_Blocks.BOX_H_CABBAGE.get(), Pantry_Blocks.BOX_H_HAKUSAI.get(), Pantry_Blocks.BOX_H_CHERRY.get(), 
				Pantry_Blocks.BOX_H_CITRUS.get(), Pantry_Blocks.BOX_H_CORN.get(), Pantry_Blocks.BOX_H_GRAPE.get(), Pantry_Blocks.BOX_H_GREENONION.get(), 
				Pantry_Blocks.BOX_H_ONION.get(), Pantry_Blocks.BOX_H_ORIENTCLAM.get(), Pantry_Blocks.BOX_H_RICE.get(), Pantry_Blocks.BOX_H_SOY.get(), 
				Pantry_Blocks.BOX_H_SPINACH.get(), Pantry_Blocks.BOX_H_SQUID.get(), Pantry_Blocks.BOX_H_TOMATO.get(), Pantry_Blocks.BOX_H_TAKENOKO.get(), 
				Pantry_Blocks.BOX_H_KURI.get(), Pantry_Blocks.BOX_H_TGREEN.get(), Pantry_Blocks.BOX_H_TRED.get(),
				Pantry_Blocks.BOX_H_BPEPPER.get(), Pantry_Blocks.BOX_H_CUMIN.get(), Pantry_Blocks.BOX_H_TURMERIC.get(), Pantry_Blocks.BOX_H_CHILI.get(),
				Pantry_Blocks.CHADUTSU.get(), Pantry_Blocks.CANTEA.get(), Pantry_Blocks.TAWARA.get(),
				
				Ranma_Blocks.RANMA_oak.get(), Ranma_Blocks.RANMA_spruce.get(), Ranma_Blocks.RANMA_birch.get(),
				Ranma_Blocks.RANMA_jungle.get(), Ranma_Blocks.RANMA_acacia.get(), Ranma_Blocks.RANMA_darkoak.get(),
				Ranma_Blocks.RANMA_mangrove.get(), Ranma_Blocks.RANMA_cherry.get(),
				Ranma_Blocks.RANMA_sakura.get(), Ranma_Blocks.RANMA_kaede.get(), Ranma_Blocks.RANMA_ichoh.get(),
				Ranma_Blocks.RANMAB_oak.get(), Ranma_Blocks.RANMAB_spruce.get(), Ranma_Blocks.RANMAB_birch.get(),
				Ranma_Blocks.RANMAB_jungle.get(), Ranma_Blocks.RANMAB_acacia.get(), Ranma_Blocks.RANMAB_darkoak.get(),
				Ranma_Blocks.RANMAB_mangrove.get(), Ranma_Blocks.RANMAB_cherry.get(),
				Ranma_Blocks.RANMAB_sakura.get(), Ranma_Blocks.RANMAB_kaede.get(), Ranma_Blocks.RANMAB_ichoh.get(),
				Ranma_Blocks.RANMAC_oak.get(), Ranma_Blocks.RANMAC_spruce.get(), Ranma_Blocks.RANMAC_birch.get(),
				Ranma_Blocks.RANMAC_jungle.get(), Ranma_Blocks.RANMAC_acacia.get(), Ranma_Blocks.RANMAC_darkoak.get(),
				Ranma_Blocks.RANMAC_mangrove.get(), Ranma_Blocks.RANMAC_cherry.get(),
				Ranma_Blocks.RANMAC_sakura.get(), Ranma_Blocks.RANMAC_kaede.get(), Ranma_Blocks.RANMAC_ichoh.get(),
				
				Ranma_Blocks.KANKI_oak.get(), Ranma_Blocks.KANKI_spruce.get(), Ranma_Blocks.KANKI_birch.get(),
				Ranma_Blocks.KANKI_jungle.get(), Ranma_Blocks.KANKI_acacia.get(), Ranma_Blocks.KANKI_darkoak.get(),
				Ranma_Blocks.KANKI_mangrove.get(), Ranma_Blocks.KANKI_cherry.get(),
				Ranma_Blocks.KANKI_sakura.get(), Ranma_Blocks.KANKI_kaede.get(), Ranma_Blocks.KANKI_ichoh.get(),
				Ranma_Blocks.KOUSHI_oak.get(), Ranma_Blocks.KOUSHI_spruce.get(), Ranma_Blocks.KOUSHI_birch.get(),
				Ranma_Blocks.KOUSHI_jungle.get(), Ranma_Blocks.KOUSHI_acacia.get(), Ranma_Blocks.KOUSHI_darkoak.get(),
				Ranma_Blocks.KOUSHI_mangrove.get(), Ranma_Blocks.KOUSHI_cherry.get(),
				Ranma_Blocks.KOUSHI_sakura.get(), Ranma_Blocks.KOUSHI_kaede.get(), Ranma_Blocks.KOUSHI_ichoh.get(),
				Ranma_Blocks.KOUSHIB_oak.get(), Ranma_Blocks.KOUSHIB_spruce.get(), Ranma_Blocks.KOUSHIB_birch.get(),
				Ranma_Blocks.KOUSHIB_jungle.get(), Ranma_Blocks.KOUSHIB_acacia.get(), Ranma_Blocks.KOUSHIB_darkoak.get(),
				Ranma_Blocks.KOUSHIB_mangrove.get(), Ranma_Blocks.KOUSHIB_cherry.get(),
				Ranma_Blocks.KOUSHIB_sakura.get(), Ranma_Blocks.KOUSHIB_kaede.get(), Ranma_Blocks.KOUSHIB_ichoh.get(),
				
				Ranma_Blocks.NOREN_white.get(), Ranma_Blocks.NOREN_orange.get(), Ranma_Blocks.NOREN_magenta.get(), Ranma_Blocks.NOREN_lightb.get(),
				Ranma_Blocks.NOREN_yellow.get(), Ranma_Blocks.NOREN_lime.get(), Ranma_Blocks.NOREN_pink.get(), Ranma_Blocks.NOREN_gray.get(),
				Ranma_Blocks.NOREN_lightg.get(), Ranma_Blocks.NOREN_cyan.get(), Ranma_Blocks.NOREN_purple.get(), Ranma_Blocks.NOREN_blue.get(),
				Ranma_Blocks.NOREN_brown.get(), Ranma_Blocks.NOREN_green.get(), Ranma_Blocks.NOREN_red.get(), Ranma_Blocks.NOREN_black.get(),
				
				School_Blocks.BLACKBOARD.get(),
				School_Blocks.BOARD_OAK.get(), School_Blocks.BOARD_SPRUCE.get(), School_Blocks.BOARD_BIRCH.get(),
				School_Blocks.BOARD_JUNGLE.get(), School_Blocks.BOARD_ACACIA.get(), School_Blocks.BOARD_DOAK.get(),
				School_Blocks.BOARD_MANGROVE.get(), School_Blocks.BOARD_CHERRY.get(),
				School_Blocks.BOARD_SAKURA.get(), School_Blocks.BOARD_KAEDE.get(), School_Blocks.BOARD_ICHOH.get(),
				
				School_Blocks.SCHOOLCHAIR.get(), School_Blocks.SCHOOLCHAIR_spruce.get(), School_Blocks.SCHOOLCHAIR_birch.get(),
				School_Blocks.SCHOOLCHAIR_jungle.get(), School_Blocks.SCHOOLCHAIR_acacia.get(), School_Blocks.SCHOOLCHAIR_darkoak.get(),
				School_Blocks.SCHOOLCHAIR_mangrove.get(), School_Blocks.SCHOOLCHAIR_cherry.get(), 
				School_Blocks.SCHOOLCHAIR_sakura.get(), School_Blocks.SCHOOLCHAIR_kaede.get(), School_Blocks.SCHOOLCHAIR_ichoh.get(),
				School_Blocks.SCHOOLDESK.get(), School_Blocks.SCHOOLDESK_spruce.get(), School_Blocks.SCHOOLDESK_birch.get(),
				School_Blocks.SCHOOLDESK_jungle.get(), School_Blocks.SCHOOLDESK_acacia.get(), School_Blocks.SCHOOLDESK_darkoak.get(),
				School_Blocks.SCHOOLDESK_mangrove.get(), School_Blocks.SCHOOLDESK_cherry.get(),
				School_Blocks.SCHOOLDESK_sakura.get(), School_Blocks.SCHOOLDESK_kaede.get(), School_Blocks.SCHOOLDESK_ichoh.get(),
				School_Blocks.TEACHERDESK.get(), School_Blocks.TEACHERDESK_spruce.get(), School_Blocks.TEACHERDESK_birch.get(),
				School_Blocks.TEACHERDESK_jungle.get(), School_Blocks.TEACHERDESK_acacia.get(), School_Blocks.TEACHERDESK_darkoak.get(),
				School_Blocks.TEACHERDESK_mangrove.get(), School_Blocks.TEACHERDESK_cherry.get(),
				School_Blocks.TEACHERDESK_sakura.get(), School_Blocks.TEACHERDESK_kaede.get(), School_Blocks.TEACHERDESK_ichoh.get(),
				
				Seasonal_Blocks.KUSATABA.get(), Seasonal_Blocks.WARATABA.get(), Seasonal_Blocks.KAYATABA.get(), Seasonal_Blocks.KUSATABADUMMY.get(),
				Seasonal_Blocks.KUSATABA_RF.get(), Seasonal_Blocks.WARATABA_RF.get(), Seasonal_Blocks.KAYATABA_RF.get(),
				Seasonal_Blocks.KUSATABA_STAIRS.get(), Seasonal_Blocks.WARATABA_STAIRS.get(), Seasonal_Blocks.KAYATABA_STAIRS.get(),
				Seasonal_Blocks.KADOMATSU.get(), Seasonal_Blocks.SHIMENAWA.get(), Seasonal_Blocks.KAGAMIMOCHI.get(),
				Seasonal_Blocks.HINAKAZARI.get(), Seasonal_Blocks.HINADAN.get(), Seasonal_Blocks.XMASTREE.get(), Seasonal_Blocks.XMASTREE_W.get(),
				Seasonal_Blocks.PRESENT_app.get(), Seasonal_Blocks.PRESENT_bok.get(), Seasonal_Blocks.PRESENT_dia.get(), Seasonal_Blocks.PRESENT_lap.get(),
				Seasonal_Blocks.PRESENT_bla.get(), Seasonal_Blocks.PRESENT_chc.get(), Seasonal_Blocks.PRESENT_chh.get(),
				
				Seasonal_Blocks.UCHIWA_white.get(), Seasonal_Blocks.UCHIWA_orange.get(), Seasonal_Blocks.UCHIWA_magenta.get(), Seasonal_Blocks.UCHIWA_lightb.get(),
				Seasonal_Blocks.UCHIWA_yellow.get(), Seasonal_Blocks.UCHIWA_lime.get(), Seasonal_Blocks.UCHIWA_pink.get(), Seasonal_Blocks.UCHIWA_gray.get(),
				Seasonal_Blocks.UCHIWA_lightg.get(), Seasonal_Blocks.UCHIWA_cyan.get(), Seasonal_Blocks.UCHIWA_purple.get(), Seasonal_Blocks.UCHIWA_blue.get(),
				Seasonal_Blocks.UCHIWA_brown.get(), Seasonal_Blocks.UCHIWA_green.get(), Seasonal_Blocks.UCHIWA_red.get(), Seasonal_Blocks.UCHIWA_black.get(),
				Seasonal_Blocks.WATAGASHI_block.get(), Seasonal_Blocks.WATAGASHI_apple.get(), Seasonal_Blocks.WATAGASHI_cherry.get(),
				Seasonal_Blocks.WATAGASHI_citrus.get(), Seasonal_Blocks.WATAGASHI_grape.get(), Seasonal_Blocks.WATAGASHI_tea.get(), 
				Seasonal_Blocks.KAKIGOURI_hata.get(),
				
				Slidedoor_Blocks.FUSUMA_white.get(), Slidedoor_Blocks.FUSUMA_orange.get(), Slidedoor_Blocks.FUSUMA_magenta.get(), Slidedoor_Blocks.FUSUMA_lightb.get(),
				Slidedoor_Blocks.FUSUMA_yellow.get(), Slidedoor_Blocks.FUSUMA_lime.get(), Slidedoor_Blocks.FUSUMA_pink.get(), Slidedoor_Blocks.FUSUMA_gray.get(),
				Slidedoor_Blocks.FUSUMA_lightg.get(), Slidedoor_Blocks.FUSUMA_cyan.get(), Slidedoor_Blocks.FUSUMA_purple.get(), Slidedoor_Blocks.FUSUMA_blue.get(),
				Slidedoor_Blocks.FUSUMA_brown.get(), Slidedoor_Blocks.FUSUMA_green.get(), Slidedoor_Blocks.FUSUMA_red.get(), Slidedoor_Blocks.FUSUMA_black.get(),
				Slidedoor_Blocks.FUSUMAB_white.get(), Slidedoor_Blocks.FUSUMAB_orange.get(), Slidedoor_Blocks.FUSUMAB_magenta.get(), Slidedoor_Blocks.FUSUMAB_lightb.get(),
				Slidedoor_Blocks.FUSUMAB_yellow.get(), Slidedoor_Blocks.FUSUMAB_lime.get(), Slidedoor_Blocks.FUSUMAB_pink.get(), Slidedoor_Blocks.FUSUMAB_gray.get(),
				Slidedoor_Blocks.FUSUMAB_lightg.get(), Slidedoor_Blocks.FUSUMAB_cyan.get(), Slidedoor_Blocks.FUSUMAB_purple.get(), Slidedoor_Blocks.FUSUMAB_blue.get(),
				Slidedoor_Blocks.FUSUMAB_brown.get(), Slidedoor_Blocks.FUSUMAB_green.get(), Slidedoor_Blocks.FUSUMAB_red.get(), Slidedoor_Blocks.FUSUMAB_black.get(),
				
				Slidedoor_Blocks.GARASUDO.get(), Slidedoor_Blocks.GARASUDO_SPRU.get(), Slidedoor_Blocks.GARASUDO_BIR.get(),
				Slidedoor_Blocks.GARASUDO_JUN.get(), Slidedoor_Blocks.GARASUDO_ACA.get(), Slidedoor_Blocks.GARASUDO_DOAK.get(),
				Slidedoor_Blocks.GARASUDO_MANGROVE.get(), Slidedoor_Blocks.GARASUDO_CHERRY.get(),
				Slidedoor_Blocks.GARASUDO_SAKU.get(), Slidedoor_Blocks.GARASUDO_KAE.get(), Slidedoor_Blocks.GARASUDO_ICH.get(),
				Slidedoor_Blocks.GARASUDOB.get(), Slidedoor_Blocks.GARASUDOB_SPRU.get(), Slidedoor_Blocks.GARASUDOB_BIR.get(),
				Slidedoor_Blocks.GARASUDOB_JUN.get(), Slidedoor_Blocks.GARASUDOB_ACA.get(), Slidedoor_Blocks.GARASUDOB_DOAK.get(),
				Slidedoor_Blocks.GARASUDOB_MANGROVE.get(), Slidedoor_Blocks.GARASUDOB_CHERRY.get(),
				Slidedoor_Blocks.GARASUDOB_SAKU.get(), Slidedoor_Blocks.GARASUDOB_KAE.get(), Slidedoor_Blocks.GARASUDOB_ICH.get(),
				Slidedoor_Blocks.GARASUDOH.get(), Slidedoor_Blocks.GARASUDOH_SPRU.get(), Slidedoor_Blocks.GARASUDOH_BIR.get(),
				Slidedoor_Blocks.GARASUDOH_JUN.get(), Slidedoor_Blocks.GARASUDOH_ACA.get(), Slidedoor_Blocks.GARASUDOH_DOAK.get(),
				Slidedoor_Blocks.GARASUDOH_MANGROVE.get(), Slidedoor_Blocks.GARASUDOH_CHERRY.get(),
				Slidedoor_Blocks.GARASUDOH_SAKU.get(), Slidedoor_Blocks.GARASUDOH_KAE.get(), Slidedoor_Blocks.GARASUDOH_ICH.get(),
				
				Slidedoor_Blocks.SHOUJI.get(), Slidedoor_Blocks.SHOUJI_SPRU.get(), Slidedoor_Blocks.SHOUJI_BIR.get(),
				Slidedoor_Blocks.SHOUJI_JUN.get(), Slidedoor_Blocks.SHOUJI_ACA.get(), Slidedoor_Blocks.SHOUJI_DOAK.get(),
				Slidedoor_Blocks.SHOUJI_MANGROVE.get(), Slidedoor_Blocks.SHOUJI_CHERRY.get(),
				Slidedoor_Blocks.SHOUJI_SAKU.get(), Slidedoor_Blocks.SHOUJI_KAE.get(), Slidedoor_Blocks.SHOUJI_ICH.get(),
				Slidedoor_Blocks.SHOUJIB.get(), Slidedoor_Blocks.SHOUJIB_SPRU.get(), Slidedoor_Blocks.SHOUJIB_BIR.get(),
				Slidedoor_Blocks.SHOUJIB_JUN.get(), Slidedoor_Blocks.SHOUJIB_ACA.get(), Slidedoor_Blocks.SHOUJIB_DOAK.get(),
				Slidedoor_Blocks.SHOUJIB_MANGROVE.get(), Slidedoor_Blocks.SHOUJIB_CHERRY.get(), 
				Slidedoor_Blocks.SHOUJIB_SAKU.get(), Slidedoor_Blocks.SHOUJIB_KAE.get(), Slidedoor_Blocks.SHOUJIB_ICH.get(),
				
				Slidedoor_Blocks.SHOUJIH.get(), Slidedoor_Blocks.SHOUJIH_SPRU.get(), Slidedoor_Blocks.SHOUJIH_BIR.get(),
				Slidedoor_Blocks.SHOUJIH_JUN.get(), Slidedoor_Blocks.SHOUJIH_ACA.get(), Slidedoor_Blocks.SHOUJIH_DOAK.get(),
				Slidedoor_Blocks.SHOUJIH_MANGROVE.get(), Slidedoor_Blocks.SHOUJIH_CHERRY.get(),
				Slidedoor_Blocks.SHOUJIH_SAKU.get(), Slidedoor_Blocks.SHOUJIH_KAE.get(), Slidedoor_Blocks.SHOUJIH_ICH.get(),
				Slidedoor_Blocks.SHOUJI_WIN.get(), Slidedoor_Blocks.SHOUJI_WIN_SPRU.get(), Slidedoor_Blocks.SHOUJI_WIN_BIR.get(),
				Slidedoor_Blocks.SHOUJI_WIN_JUN.get(), Slidedoor_Blocks.SHOUJI_WIN_ACA.get(), Slidedoor_Blocks.SHOUJI_WIN_DOAK.get(),
				Slidedoor_Blocks.SHOUJI_WIN_MANGROVE.get(), Slidedoor_Blocks.SHOUJI_WIN_CHERRY.get(),
				Slidedoor_Blocks.SHOUJI_WIN_SAKU.get(), Slidedoor_Blocks.SHOUJI_WIN_KAE.get(), Slidedoor_Blocks.SHOUJI_WIN_ICH.get(),
				Slidedoor_Blocks.SHOUJI_WINR.get(), Slidedoor_Blocks.SHOUJI_WINR_SPRU.get(), Slidedoor_Blocks.SHOUJI_WINR_BIR.get(),
				Slidedoor_Blocks.SHOUJI_WINR_JUN.get(), Slidedoor_Blocks.SHOUJI_WINR_ACA.get(), Slidedoor_Blocks.SHOUJI_WINR_DOAK.get(),
				Slidedoor_Blocks.SHOUJI_WINR_MANGROVE.get(), Slidedoor_Blocks.SHOUJI_WINR_CHERRY.get(),
				Slidedoor_Blocks.SHOUJI_WINR_SAKU.get(), Slidedoor_Blocks.SHOUJI_WINR_KAE.get(), Slidedoor_Blocks.SHOUJI_WINR_ICH.get(),
				
				Slidedoor_Blocks.AMADO_S.get(), Slidedoor_Blocks.TOBUKURO_S.get(), Slidedoor_Blocks.TOBUKURO_SL.get(),
				Slidedoor_Blocks.AMADOWIN_S.get(), Slidedoor_Blocks.TOBUKUROWIN_S.get(),
				Slidedoor_Blocks.AMADO.get(), Slidedoor_Blocks.TOBUKURO.get(), Slidedoor_Blocks.TOBUKURO_L.get(),
				Slidedoor_Blocks.AMADOWIN.get(), Slidedoor_Blocks.TOBUKUROWIN.get(),
				
				Unit_Blocks.UNITDESK.get(), Unit_Blocks.UNITDESK_spruce.get(), Unit_Blocks.UNITDESK_birch.get(),
				Unit_Blocks.UNITDESK_jungle.get(), Unit_Blocks.UNITDESK_acacia.get(), Unit_Blocks.UNITDESK_darkoak.get(),
				Unit_Blocks.UNITDESK_mangrove.get(), Unit_Blocks.UNITDESK_cherry.get(),
				Unit_Blocks.UNITDESK_sakura.get(), Unit_Blocks.UNITDESK_kaede.get(), Unit_Blocks.UNITDESK_ichoh.get(),
				Unit_Blocks.CAFETABLE.get(), Unit_Blocks.CAFETABLE_spruce.get(), Unit_Blocks.CAFETABLE_birch.get(),
				Unit_Blocks.CAFETABLE_jungle.get(), Unit_Blocks.CAFETABLE_acacia.get(), Unit_Blocks.CAFETABLE_darkoak.get(),
				Unit_Blocks.CAFETABLE_mangrove.get(), Unit_Blocks.CAFETABLE_cherry.get(),
				Unit_Blocks.CAFETABLE_sakura.get(), Unit_Blocks.CAFETABLE_kaede.get(), Unit_Blocks.CAFETABLE_ichoh.get(),
				
				Unit_Blocks.LOWDESK.get(), Unit_Blocks.LOWDESK_spruce.get(), Unit_Blocks.LOWDESK_birch.get(),
				Unit_Blocks.LOWDESK_jungle.get(), Unit_Blocks.LOWDESK_acacia.get(), Unit_Blocks.LOWDESK_darkoak.get(),
				Unit_Blocks.LOWDESK_mangrove.get(), Unit_Blocks.LOWDESK_cherry.get(),
				Unit_Blocks.LOWDESK_sakura.get(), Unit_Blocks.LOWDESK_kaede.get(), Unit_Blocks.LOWDESK_ichoh.get(),
				Unit_Blocks.LETTERTRAY.get(), Unit_Blocks.FUDETRAY.get(), 
				Unit_Blocks.WRITTEN_BOOK.get(), Unit_Blocks.WRITTEN_MAKIMONO.get(),
				
				Unit_Blocks.CHABUDAI.get(), Unit_Blocks.CHABUDAI_spruce.get(), Unit_Blocks.CHABUDAI_birch.get(),
				Unit_Blocks.CHABUDAI_jungle.get(), Unit_Blocks.CHABUDAI_acacia.get(), Unit_Blocks.CHABUDAI_darkoak.get(),
				Unit_Blocks.CHABUDAI_mangrove.get(), Unit_Blocks.CHABUDAI_cherry.get(),
				Unit_Blocks.CHABUDAI_sakura.get(), Unit_Blocks.CHABUDAI_kaede.get(), Unit_Blocks.CHABUDAI_ichoh.get(),
				Unit_Blocks.KOTATSU.get(), Unit_Blocks.KOTATSU_spruce.get(), Unit_Blocks.KOTATSU_birch.get(),
				Unit_Blocks.KOTATSU_jungle.get(), Unit_Blocks.KOTATSU_acacia.get(), Unit_Blocks.KOTATSU_darkoak.get(),
				Unit_Blocks.KOTATSU_mangrove.get(), Unit_Blocks.KOTATSU_cherry.get(),
				Unit_Blocks.KOTATSU_sakura.get(), Unit_Blocks.KOTATSU_kaede.get(), Unit_Blocks.KOTATSU_ichoh.get(),
				
				Unit_Blocks.KASA_white.get(), Unit_Blocks.KASA_orange.get(), Unit_Blocks.KASA_magenta.get(), Unit_Blocks.KASA_lightb.get(),
				Unit_Blocks.KASA_yellow.get(), Unit_Blocks.KASA_lime.get(), Unit_Blocks.KASA_pink.get(), Unit_Blocks.KASA_gray.get(),
				Unit_Blocks.KASA_lightg.get(), Unit_Blocks.KASA_cyan.get(), Unit_Blocks.KASA_purple.get(), Unit_Blocks.KASA_blue.get(),
				Unit_Blocks.KASA_brown.get(), Unit_Blocks.KASA_green.get(), Unit_Blocks.KASA_red.get(), Unit_Blocks.KASA_black.get(),
				Unit_Blocks.TEATABLE.get(), Unit_Blocks.ENDAI.get(), Unit_Blocks.ENDAI_r.get(),
				
				Unit_Blocks.CLOTHTABLE_oak.get(), Unit_Blocks.CLOTHTABLE_spruce.get(), Unit_Blocks.CLOTHTABLE_birch.get(),
				Unit_Blocks.CLOTHTABLE_jungle.get(), Unit_Blocks.CLOTHTABLE_acacia.get(), Unit_Blocks.CLOTHTABLE_darkoak.get(),
				Unit_Blocks.CLOTHTABLE_mangrove.get(), Unit_Blocks.CLOTHTABLE_cherry.get(),
				Unit_Blocks.CLOTHTABLE_sakura.get(), Unit_Blocks.CLOTHTABLE_kaede.get(), Unit_Blocks.CLOTHTABLE_ichoh.get(),
				Unit_Blocks.CLOTHTABLE_oaksub.get(), Unit_Blocks.CLOTHTABLE_sprucesub.get(), Unit_Blocks.CLOTHTABLE_birchsub.get(),
				Unit_Blocks.CLOTHTABLE_junglesub.get(), Unit_Blocks.CLOTHTABLE_acaciasub.get(), Unit_Blocks.CLOTHTABLE_darkoaksub.get(),
				Unit_Blocks.CLOTHTABLE_mangrovesub.get(), Unit_Blocks.CLOTHTABLE_cherrysub.get(),
				Unit_Blocks.CLOTHTABLE_sakurasub.get(), Unit_Blocks.CLOTHTABLE_kaedesub.get(), Unit_Blocks.CLOTHTABLE_ichohsub.get(),
				
				WallPanel_Blocks.PILLAR_oak.get(), WallPanel_Blocks.PILLAR_spru.get(), WallPanel_Blocks.PILLAR_bir.get(),
				WallPanel_Blocks.PILLAR_jun.get(), WallPanel_Blocks.PILLAR_aca.get(), WallPanel_Blocks.PILLAR_doak.get(),
				WallPanel_Blocks.PILLAR_mangrove.get(), WallPanel_Blocks.PILLAR_cherry.get(),
				WallPanel_Blocks.PILLARSLAB_oak.get(), WallPanel_Blocks.PILLARSLAB_spru.get(), WallPanel_Blocks.PILLARSLAB_bir.get(),
				WallPanel_Blocks.PILLARSLAB_jun.get(), WallPanel_Blocks.PILLARSLAB_aca.get(), WallPanel_Blocks.PILLARSLAB_doak.get(),
				WallPanel_Blocks.PILLARSLAB_mangrove.get(), WallPanel_Blocks.PILLARSLAB_cherry.get(),
				
				WallPanel_Blocks.WP_LOG_oak.get(),WallPanel_Blocks.WP_LOG_spru.get(), WallPanel_Blocks.WP_LOG_bir.get(),
				WallPanel_Blocks.WP_LOG_jun.get(), WallPanel_Blocks.WP_LOG_aca.get(), WallPanel_Blocks.WP_LOG_doak.get(),
				WallPanel_Blocks.WP_LOG_mangrove.get(), WallPanel_Blocks.WP_LOG_cherry.get(),
				WallPanel_Blocks.WP_PLANK_oak.get(), WallPanel_Blocks.WP_PLANK_spru.get(), WallPanel_Blocks.WP_PLANK_bir.get(),
				WallPanel_Blocks.WP_PLANK_jun.get(), WallPanel_Blocks.WP_PLANK_aca.get(), WallPanel_Blocks.WP_PLANK_doak.get(),
				WallPanel_Blocks.WP_PLANK_mangrove.get(), WallPanel_Blocks.WP_PLANK_cherry.get(), 
				WallPanel_Blocks.WP_BAMBOO.get(), WallPanel_Blocks.WP_BAMBOO_Y.get(), WallPanel_Blocks.WP_BAMBOO_K.get(),
				
				Window_Blocks.WINDOW_oak.get(), Window_Blocks.WINDOW_spruce.get(), Window_Blocks.WINDOW_birch.get(),
				Window_Blocks.WINDOW_jungle.get(), Window_Blocks.WINDOW_acacia.get(), Window_Blocks.WINDOW_darkoak.get(),
				Window_Blocks.WINDOW_mangrove.get(), Window_Blocks.WINDOW_cherry.get(),
				Window_Blocks.WINDOW_sakura.get(), Window_Blocks.WINDOW_kaede.get(), Window_Blocks.WINDOW_ichoh.get(),
				Window_Blocks.WINDOWB_oak.get(), Window_Blocks.WINDOWB_spruce.get(), Window_Blocks.WINDOWB_birch.get(),
				Window_Blocks.WINDOWB_jungle.get(), Window_Blocks.WINDOWB_acacia.get(), Window_Blocks.WINDOWB_darkoak.get(),
				Window_Blocks.WINDOWB_mangrove.get(), Window_Blocks.WINDOWB_cherry.get(),
				Window_Blocks.WINDOWB_sakura.get(), Window_Blocks.WINDOWB_kaede.get(), Window_Blocks.WINDOWB_ichoh.get(),
				
				Window_Blocks.WINDOWTALLBOT_oak.get(), Window_Blocks.WINDOWTALLBOT_spruce.get(), Window_Blocks.WINDOWTALLBOT_birch.get(),
				Window_Blocks.WINDOWTALLBOT_jungle.get(), Window_Blocks.WINDOWTALLBOT_acacia.get(), Window_Blocks.WINDOWTALLBOT_darkoak.get(),
				Window_Blocks.WINDOWTALLBOT_mangrove.get(), Window_Blocks.WINDOWTALLBOT_cherry.get(),
				Window_Blocks.WINDOWTALLBOT_sakura.get(), Window_Blocks.WINDOWTALLBOT_kaede.get(), Window_Blocks.WINDOWTALLBOT_ichoh.get(),
				Window_Blocks.WINDOWTALLTOP_oak.get(), Window_Blocks.WINDOWTALLTOP_spruce.get(), Window_Blocks.WINDOWTALLTOP_birch.get(),
				Window_Blocks.WINDOWTALLTOP_jungle.get(), Window_Blocks.WINDOWTALLTOP_acacia.get(), Window_Blocks.WINDOWTALLTOP_darkoak.get(),
				Window_Blocks.WINDOWTALLTOP_mangrove.get(), Window_Blocks.WINDOWTALLTOP_cherry.get(),
				Window_Blocks.WINDOWTALLTOP_sakura.get(), Window_Blocks.WINDOWTALLTOP_kaede.get(), Window_Blocks.WINDOWTALLTOP_ichoh.get(),
				Window_Blocks.WINDOWTALL_oak.get(), Window_Blocks.WINDOWTALL_spruce.get(), Window_Blocks.WINDOWTALL_birch.get(),
				Window_Blocks.WINDOWTALL_jungle.get(), Window_Blocks.WINDOWTALL_acacia.get(), Window_Blocks.WINDOWTALL_darkoak.get(),
				Window_Blocks.WINDOWTALL_mangrove.get(), Window_Blocks.WINDOWTALL_cherry.get(),
				Window_Blocks.WINDOWTALL_sakura.get(), Window_Blocks.WINDOWTALL_kaede.get(), Window_Blocks.WINDOWTALL_ichoh.get(),
				
				Window_Blocks.CURTAIN_white.get(), Window_Blocks.CURTAIN_orange.get(), Window_Blocks.CURTAIN_magenta.get(), Window_Blocks.CURTAIN_lightblue.get(),
				Window_Blocks.CURTAIN_yellow.get(), Window_Blocks.CURTAIN_lime.get(), Window_Blocks.CURTAIN_pink.get(), Window_Blocks.CURTAIN_gray.get(),
				Window_Blocks.CURTAIN_lightgray.get(), Window_Blocks.CURTAIN_cyan.get(), Window_Blocks.CURTAIN_purple.get(), Window_Blocks.CURTAIN_blue.get(),
				Window_Blocks.CURTAIN_brown.get(), Window_Blocks.CURTAIN_green.get(), Window_Blocks.CURTAIN_red.get(), Window_Blocks.CURTAIN_black.get(),
				Window_Blocks.CURTAINTALL_white.get(), Window_Blocks.CURTAINTALL_orange.get(), Window_Blocks.CURTAINTALL_magenta.get(), Window_Blocks.CURTAINTALL_lightblue.get(),
				Window_Blocks.CURTAINTALL_yellow.get(), Window_Blocks.CURTAINTALL_lime.get(), Window_Blocks.CURTAINTALL_pink.get(), Window_Blocks.CURTAINTALL_gray.get(),
				Window_Blocks.CURTAINTALL_lightgray.get(), Window_Blocks.CURTAINTALL_cyan.get(), Window_Blocks.CURTAINTALL_purple.get(), Window_Blocks.CURTAINTALL_blue.get(),
				Window_Blocks.CURTAINTALL_brown.get(), Window_Blocks.CURTAINTALL_green.get(), Window_Blocks.CURTAINTALL_red.get(), Window_Blocks.CURTAINTALL_black.get(),
				Window_Blocks.CURTAINL_white.get(), Window_Blocks.CURTAINL_orange.get(), Window_Blocks.CURTAINL_magenta.get(), Window_Blocks.CURTAINL_lightblue.get(),
				Window_Blocks.CURTAINL_yellow.get(), Window_Blocks.CURTAINL_lime.get(), Window_Blocks.CURTAINL_pink.get(), Window_Blocks.CURTAINL_gray.get(),
				Window_Blocks.CURTAINL_lightgray.get(), Window_Blocks.CURTAINL_cyan.get(), Window_Blocks.CURTAINL_purple.get(), Window_Blocks.CURTAINL_blue.get(),
				Window_Blocks.CURTAINL_brown.get(), Window_Blocks.CURTAINL_green.get(), Window_Blocks.CURTAINL_red.get(), Window_Blocks.CURTAINL_black.get(),
				
				Wood_Blocks.TAKE.get(), Wood_Blocks.KURIIGA_FALL.get(), Wood_Blocks.KURIIGA_BUSH.get(),
				Wood_Blocks.SAKURA_log.get(), Wood_Blocks.KAEDE_log.get(), Wood_Blocks.ICHOH_log.get(), Wood_Blocks.OAKKARE_log.get(),
				Wood_Blocks.SAKURA_nae.get(), Wood_Blocks.KAEDE_nae.get(), Wood_Blocks.ICHOH_nae.get(), Wood_Blocks.OAKKARE_nae.get(),
				Wood_Blocks.SAKURA_planks.get(), Wood_Blocks.KAEDE_planks.get(), Wood_Blocks.ICHOH_planks.get(),
				Wood_Blocks.SAKURA_slabhalf.get(), Wood_Blocks.KAEDE_slabhalf.get(), Wood_Blocks.ICHOH_slabhalf.get(),
				Wood_Blocks.SAKURA_stairs.get(), Wood_Blocks.KAEDE_stairs.get(), Wood_Blocks.ICHOH_stairs.get(),
				
				Wood_Blocks.PILLAR_saku.get(), Wood_Blocks.PILLAR_kae.get(), Wood_Blocks.PILLAR_ich.get(),
				Wood_Blocks.PILLARSLAB_saku.get(), Wood_Blocks.PILLARSLAB_kae.get(), Wood_Blocks.PILLARSLAB_ich.get(),
				Wood_Blocks.SAKURA_FENCE.get(), Wood_Blocks.KAEDE_FENCE.get(), Wood_Blocks.ICHOH_FENCE.get(),
				Wood_Blocks.SAKURA_FGATE.get(), Wood_Blocks.KAEDE_FGATE.get(), Wood_Blocks.ICHOH_FGATE.get(),
				
				Wood_Blocks.DOOR_SAKURA.get(), Wood_Blocks.DOOR_KAEDE.get(), Wood_Blocks.DOOR_ICHOH.get(),
				Wood_Blocks.SAKURA_TRAPDOOR.get(), Wood_Blocks.KAEDE_TRAPDOOR.get(), Wood_Blocks.ICHOH_TRAPDOOR.get(),
				Wood_Blocks.SAKURA_PLATE.get(), Wood_Blocks.KAEDE_PLATE.get(), Wood_Blocks.ICHOH_PLATE.get(),
				Wood_Blocks.SAKURA_BUTTON.get(), Wood_Blocks.KAEDE_BUTTON.get(), Wood_Blocks.ICHOH_BUTTON.get(),
				Wood_Blocks.WP_LOG_sakura.get(), Wood_Blocks.WP_LOG_kaede.get(), Wood_Blocks.WP_LOG_ichoh.get(),
				Wood_Blocks.WP_PLANK_sakura.get(), Wood_Blocks.WP_PLANK_kaede.get(), Wood_Blocks.WP_PLANK_ichoh.get());

	
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
		.add(Chinjufu_Blocks.BAUXITE_ORE.get(), Chinjufu_Blocks.BAUXITE_ORE_DEEP.get(), Chinjufu_Blocks.OIL_DRUM.get(),
				Chinjufu_Blocks.ALUMI_BLOCK.get(), Chinjufu_Blocks.STEEL_BLOCK.get(), Chinjufu_Blocks.COPPER_BLOCK.get(),
				Chinjufu_Blocks.GOLD_BLOCK.get(), Chinjufu_Blocks.NETHERITE_BLOCK.get(), Chinjufu_Blocks.REPORT_BOX.get(),
				
				Dish_Blocks.OSAUCE_bot_14.get(), Dish_Blocks.OSAUCE_bot_24.get(), Dish_Blocks.OSAUCE_bot_34.get(), Dish_Blocks.OSAUCE_bot_44.get(),
				Dish_Blocks.MAYO_bot_14.get(), Dish_Blocks.MAYO_bot_24.get(), Dish_Blocks.MAYO_bot_34.get(), Dish_Blocks.MAYO_bot_44.get(), 
				Dish_Blocks.SOYOIL_bot_12.get(), Dish_Blocks.SOYOIL_bot_22.get(),
				
				Dish_Blocks.ZUNDOU.get(), Dish_Blocks.ZUNDOU_MIZU.get(), Dish_Blocks.ZUNDOU_SHIO.get(), Dish_Blocks.ZUNDOU_MILK.get(), 
				Dish_Blocks.ZUNDOU_NCURRY.get(), Dish_Blocks.ZUNDOU_CURRY.get(), 
				Dish_Blocks.ZUNDOU_NCURRY_C.get(), Dish_Blocks.ZUNDOU_CURRY_C.get(), 
				Dish_Blocks.ZUNDOU_NCURRY_T.get(), Dish_Blocks.ZUNDOU_CURRY_T.get(), 
				Dish_Blocks.ZUNDOU_NSTEW.get(), Dish_Blocks.ZUNDOU_STEW.get(),
				
				Dish_Blocks.ZUNDOU_FISH.get(), Dish_Blocks.ZUNDOU_DASHI.get(), Dish_Blocks.ZUNDOU_UDON.get(), 
				Dish_Blocks.ZUNDOU_PASTA.get(), Dish_Blocks.ZUNDOU_RSOUP_nama.get(), Dish_Blocks.ZUNDOU_RSOUP.get(), 
				Dish_Blocks.ZUNDOU_RAMEN.get(), Dish_Blocks.ZUNDOU_AKU.get(), Dish_Blocks.ZUNDOU_ORIITO.get(), 
		
				Dish_Blocks.NABE_kara.get(), Dish_Blocks.NABETORI_nama.get(), Dish_Blocks.NABEMISO_nama.get(),
				Dish_Blocks.NABEGOHAN_nama.get(), Dish_Blocks.NABEGOHANTAKE_nama.get(), Dish_Blocks.NABEGOHANKURI_nama.get(),
				Dish_Blocks.NABESEKIHAN_nama.get(), Dish_Blocks.NABECORN_nama.get(), Dish_Blocks.NABESHIO_nama.get(),
				Dish_Blocks.NABENIMAME_nama.get(), Dish_Blocks.NABETOUFU_nama.get(),
				
				Dish_Blocks.NABEAZUKI_nama.get(), Dish_Blocks.NABEANKO_nama.get(), 
				Dish_Blocks.NABEPUDDING_nama.get(), Dish_Blocks.NABEPUDDING_green.get(), 
				Dish_Blocks.NABEPUDDING_red.get(), Dish_Blocks.NABEPUDDING_cacao.get(), 
				Dish_Blocks.NABE_CREAM.get(), Dish_Blocks.NABE_CREAM_sub.get(), 
				Dish_Blocks.KURI_NABE_nama.get(), Dish_Blocks.NABETENGUSA_nama.get(), 
	
				Dish_Blocks.NABETORI.get(), Dish_Blocks.NABEMISO.get(),
				Dish_Blocks.NABEZENZAI_M.get(), Dish_Blocks.NABEZENZAI_K.get(),
				Dish_Blocks.NABEGOHAN.get(), Dish_Blocks.NABEGOHAN_TAKE.get(), Dish_Blocks.NABEGOHAN_KURI.get(),
				Dish_Blocks.NABESEKIHAN.get(), Dish_Blocks.NABECORN.get(),
				Dish_Blocks.KEIRYO_CUP.get(),

				Dish_Blocks.FRYPAN_kara.get(), Dish_Blocks.FPTAMAGO_nama.get(), Dish_Blocks.FPGYUDON_nama.get(), 
				Dish_Blocks.FPOYAKODON_nama.get(), Dish_Blocks.FPKATSU_nama.get(), Dish_Blocks.FPKATSUDON_nama.get(), 
				Dish_Blocks.FPEGGBURG_nama.get(), Dish_Blocks.FPTOMATO_nama.get(), Dish_Blocks.FPKINOKO_nama.get(), 
				Dish_Blocks.FPSEAFOOD_nama.get(), Dish_Blocks.FPKINOKOAK_nama.get(), Dish_Blocks.FPCURRY_nama.get(), 

				Dish_Blocks.FPOSAUCE_nama.get(), 
				Dish_Blocks.OKONOMIYAKI_nama.get(), Dish_Blocks.OKONOMIS_nama.get(), Dish_Blocks.OKONOMIC_nama.get(), 
				Dish_Blocks.OKONOMISOBA_nama.get(), Dish_Blocks.OKONOMISOBAS_nama.get(), Dish_Blocks.OKONOMISOBAC_nama.get(),
				Dish_Blocks.YAKISOBA_nama.get(), Dish_Blocks.YAKISOBASHIO_nama.get(),
				
				Dish_Blocks.OKONOMIYAKI_click.get(), Dish_Blocks.OKONOMIS_click.get(), Dish_Blocks.OKONOMIC_click.get(),
				Dish_Blocks.OKONOMISOBA_click.get(), Dish_Blocks.OKONOMISOBAS_click.get(), Dish_Blocks.OKONOMISOBAC_click.get(),
				Dish_Blocks.YAKISOBA_click.get(), Dish_Blocks.YAKISOBASHIO_click.get(),
				Dish_Blocks.OKONOMISOBA_5.get(), Dish_Blocks.OKONOMISOBAS_5.get(), Dish_Blocks.OKONOMISOBAC_5.get(),
				
				Dish_Blocks.CURRY.get(), Dish_Blocks.CURRYSET.get(), 
				Dish_Blocks.CURRY_C.get(), Dish_Blocks.CURRYSET_C.get(), 
				Dish_Blocks.CURRY_T.get(), Dish_Blocks.CURRYSET_T.get(), Dish_Blocks.STEW.get(), 
				Dish_Blocks.UDON_SU.get(), Dish_Blocks.UDON_NIKU.get(), Dish_Blocks.UDON_TSUKIMI.get(), 
				Dish_Blocks.RAMEN_SHOUYU.get(), Dish_Blocks.RAMEN_MISO.get(), Dish_Blocks.RAMEN_SHIO.get(), 
				
				Dish_Blocks.TONSUITORI.get(),
				Dish_Blocks.GOHAN.get(), Dish_Blocks.GOHAN_TAKE.get(), Dish_Blocks.GOHAN_KURI.get(), Dish_Blocks.SEKIHAN.get(), 
				Dish_Blocks.RICE.get(), Dish_Blocks.DONBURI_MESHI.get(), 
				Dish_Blocks.DONBURI_GYU.get(), Dish_Blocks.DONBURI_OYAKO.get(), Dish_Blocks.DONBURI_KATSU.get(), Dish_Blocks.DONBURI_KAISEN.get(), 
				Dish_Blocks.HAKUSAIDUKE.get(), Dish_Blocks.TAMAGOYAKI.get(), 
				Dish_Blocks.TAMAGOYAKITEI.get(), Dish_Blocks.YAKIZAKANATEI.get(), Dish_Blocks.YAKIJYAKETEI.get(), 
				Dish_Blocks.TAMAGOYAKITEI_TAKE.get(), Dish_Blocks.YAKIZAKANATEI_TAKE.get(), Dish_Blocks.YAKIJYAKETEI_TAKE.get(), 
				Dish_Blocks.TAMAGOYAKITEI_KURI.get(), Dish_Blocks.YAKIZAKANATEI_KURI.get(), Dish_Blocks.YAKIJYAKETEI_KURI.get(), 
				Dish_Blocks.TAMAGOYAKITEI_SEKI.get(), Dish_Blocks.YAKIZAKANATEI_SEKI.get(), Dish_Blocks.YAKIJYAKETEI_SEKI.get(), 
				Dish_Blocks.CORNSOUP.get(), Dish_Blocks.EGGBURG.get(), Dish_Blocks.EGGBURGSET.get(), 
				
				Dish_Blocks.PASTATOMATO.get(), Dish_Blocks.PASTACHEESE.get(), Dish_Blocks.PASTAKINOKO.get(), Dish_Blocks.PASTASEAFOOD.get(),
				Dish_Blocks.OKONOMIYAKI.get(), Dish_Blocks.OKONOMIS.get(), Dish_Blocks.OKONOMIC.get(), 
				Dish_Blocks.OKONOMISOBA.get(), Dish_Blocks.OKONOMISOBAS.get(), Dish_Blocks.OKONOMISOBAC.get(), 
				Dish_Blocks.YAKISOBA.get(), Dish_Blocks.YAKISOBASHIO.get(), 
				Dish_Blocks.CHICKEN.get(), Dish_Blocks.CHICKEN_small.get(),
				
				Dish_Blocks.SHOUYUSARA_1.get(), Dish_Blocks.KETTLE_kara.get(), Dish_Blocks.KETTLE_full.get(), Dish_Blocks.KYUSU.get(), 
				Dish_Blocks.JPTEACUP.get(), Dish_Blocks.JPTEASET.get(), Dish_Blocks.CHAUKE_SENBEI.get(), Dish_Blocks.CHAUKE_MIKAN .get(), 
				Dish_Blocks.TEAPOT.get(), Dish_Blocks.TEACUP.get(), Dish_Blocks.TEASET.get(), Dish_Blocks.CHAUKE_SCONE.get(), 
				Dish_Blocks.SCONESET_kara.get(), Dish_Blocks.SCONESET_1.get(), 
				Dish_Blocks.ICECREAM.get(), Dish_Blocks.ICECREAM_GREEN.get(), Dish_Blocks.ICECREAM_RED.get(), Dish_Blocks.ICECREAM_CACAO.get(),
				Dish_Blocks.CUSTARD_PUDDING.get(), Dish_Blocks.GREENTEA_PUDDING.get(), Dish_Blocks.REDTEA_PUDDING.get(), Dish_Blocks.CACAO_PUDDING.get(),

				Dish_Blocks.NABEK_APPLE.get(), Dish_Blocks.NABEK_CHERRY.get(), Dish_Blocks.NABEK_CITRUS.get(), Dish_Blocks.NABEK_GRAPE.get(),
				Dish_Blocks.NABEK_MILK.get(), Dish_Blocks.NABEK_YOKAN.get(), Dish_Blocks.NABEK_MATCHA.get(),
				Dish_Blocks.KANTEN_APPLE.get(), Dish_Blocks.KANTEN_CHERRY.get(), Dish_Blocks.KANTEN_CITRUS.get(), Dish_Blocks.KANTEN_GRAPE.get(),
				Dish_Blocks.KANTEN_MILK.get(), Dish_Blocks.YOKAN.get(), Dish_Blocks.YOKAN_MATCHA.get(),
				
				Furniture_Blocks.CANDLE_white.get(), Furniture_Blocks.CANDLE_orange.get(), Furniture_Blocks.CANDLE_magenta.get(), Furniture_Blocks.CANDLE_lightb.get(), 
				Furniture_Blocks.CANDLE_yellow.get(), Furniture_Blocks.CANDLE_lime.get(), Furniture_Blocks.CANDLE_pink.get(), Furniture_Blocks.CANDLE_gray.get(), 
				Furniture_Blocks.CANDLE_lightg.get(), Furniture_Blocks.CANDLE_cyan.get(), Furniture_Blocks.CANDLE_purple.get(), Furniture_Blocks.CANDLE_blue.get(), 
				Furniture_Blocks.CANDLE_brown.get(), Furniture_Blocks.CANDLE_green.get(), Furniture_Blocks.CANDLE_red.get(), Furniture_Blocks.CANDLE_black.get(), 
				Furniture_Blocks.LAMP.get(), Furniture_Blocks.STANDARM.get(), Furniture_Blocks.STAND.get(),
				Furniture_Blocks.M_LAMP.get(), Furniture_Blocks.E_LIGHT.get(),
				
				Garden_Blocks.SHISHIODOSHI.get(), Garden_Blocks.SHISHIODOSHI2.get(), Garden_Blocks.TETSUSAKU_BOT.get(), 
				Garden_Blocks.CHOUZUBACHI.get(), Garden_Blocks.CHOUZUBACHI_gra.get(), Garden_Blocks.CHOUZUBACHI_dio.get(), Garden_Blocks.CHOUZUBACHI_and.get(), 
				Garden_Blocks.ISHITOUROU.get(), Garden_Blocks.ISHITOUROU_gra.get(), Garden_Blocks.ISHITOUROU_dio.get(), Garden_Blocks.ISHITOUROU_and.get(), 
				Garden_Blocks.LONGTOUROU.get(), Garden_Blocks.LONGTOUROU_gra.get(), Garden_Blocks.LONGTOUROU_dio.get(), Garden_Blocks.LONGTOUROU_and.get(),
				
				Garden_Blocks.NIWAISHI.get(), Garden_Blocks.NIWAISHI_gra.get(), 
				Garden_Blocks.NIWAISHI_dio.get(), Garden_Blocks.NIWAISHI_and.get(), 
				Garden_Blocks.NIWAISHI_slab.get(), Garden_Blocks.NIWAISHI_slab_gra.get(), 
				Garden_Blocks.NIWAISHI_slab_dio.get(), Garden_Blocks.NIWAISHI_slab_and.get(), 
				Garden_Blocks.MAKIBISHI.get(),
				Gate_Blocks.GATE_IRON.get(), Gate_Blocks.GATE_IRONGRILL.get(),
				
				Hakkou_Blocks.KOUBOBOT_full.get(), Hakkou_Blocks.NYUSANBOT_full.get(),
				Hakkou_Blocks.NAMASAKEBOT.get(), Hakkou_Blocks.SAKEBOT.get(), Hakkou_Blocks.JUKUSAKEBOT.get(),
				Hakkou_Blocks.NABEAMAZAKE_nama.get(), Hakkou_Blocks.NABEAMAZAKE.get(),
				Hakkou_Blocks.CIDERBOT.get(), Hakkou_Blocks.JUKUCIDERBOT.get(), Hakkou_Blocks.WINEBOT.get(),
				Hakkou_Blocks.JUKUWINEBOT.get(), Hakkou_Blocks.MEADBOT.get(), Hakkou_Blocks.JUKUMEADBOT.get(),
				Hakkou_Blocks.NAMASAKEGLASS.get(), Hakkou_Blocks.SAKEGLASS.get(), Hakkou_Blocks.JUKUSAKEGLASS.get(), Hakkou_Blocks.AMAZAKEGLASS.get(),
				Hakkou_Blocks.CIDERGLASS.get(), Hakkou_Blocks.JUKUCIDERGLASS.get(), Hakkou_Blocks.WINEGLASS.get(),
				Hakkou_Blocks.JUKUWINEGLASS.get(), Hakkou_Blocks.MEADGLASS.get(), Hakkou_Blocks.JUKUMEADGLASS.get(),
				
				Hakkou_Blocks.SHOUYU_bot_14.get(), Hakkou_Blocks.SHOUYU_bot_24.get(), Hakkou_Blocks.SHOUYU_bot_34.get(), Hakkou_Blocks.SHOUYU_bot_44.get(),
				Hakkou_Blocks.KOMEZU_bot_12.get(), Hakkou_Blocks.KOMEZU_bot_22.get(),
				Hakkou_Blocks.DASHI_bot_14.get(), Hakkou_Blocks.DASHI_bot_24.get(), Hakkou_Blocks.DASHI_bot_34.get(), Hakkou_Blocks.DASHI_bot_44.get(),
				Hakkou_Blocks.VANILLA_bot_14.get(), Hakkou_Blocks.VANILLA_bot_24.get(), Hakkou_Blocks.VANILLA_bot_34.get(), Hakkou_Blocks.VANILLA_bot_44.get(),
				Hakkou_Blocks.COLD_MILK.get(),
				
				Harbor_Blocks.KEIKAIBLOCK.get(), Harbor_Blocks.KEIRYUKUI.get(), Harbor_Blocks.KEIRYUKUI_b.get(),
				Harbor_Blocks.TRUSS.get(), Harbor_Blocks.TRUSS_white.get(), Harbor_Blocks.TRUSS_orange.get(), Harbor_Blocks.TRUSS_magenta.get(),
				Harbor_Blocks.TRUSS_lightb.get(), Harbor_Blocks.TRUSS_yellow.get(), Harbor_Blocks.TRUSS_lime.get(), Harbor_Blocks.TRUSS_pink.get(),
				Harbor_Blocks.TRUSS_gray.get(), Harbor_Blocks.TRUSS_cyan.get(), Harbor_Blocks.TRUSS_purple.get(), Harbor_Blocks.TRUSS_blue.get(),
				Harbor_Blocks.TRUSS_brown.get(), Harbor_Blocks.TRUSS_green.get(), Harbor_Blocks.TRUSS_red.get(), Harbor_Blocks.TRUSS_black.get(),
				Harbor_Blocks.AMP.get(), Harbor_Blocks.AMP_white.get(), Harbor_Blocks.AMP_orange.get(), Harbor_Blocks.AMP_magenta.get(),
				Harbor_Blocks.AMP_lightb.get(), Harbor_Blocks.AMP_yellow.get(), Harbor_Blocks.AMP_lime.get(), Harbor_Blocks.AMP_pink.get(),
				Harbor_Blocks.AMP_gray.get(), Harbor_Blocks.AMP_cyan.get(), Harbor_Blocks.AMP_purple.get(), Harbor_Blocks.AMP_blue.get(),
				Harbor_Blocks.AMP_brown.get(), Harbor_Blocks.AMP_green.get(), Harbor_Blocks.AMP_red.get(), Harbor_Blocks.AMP_black.get(),

				JP_Blocks.JPBLOCKDUMMY.get(), 
				JP_Blocks.KAWARA_white.get(), JP_Blocks.KAWARA_orange.get(), JP_Blocks.KAWARA_magenta.get(), JP_Blocks.KAWARA_lightb.get(),
				JP_Blocks.KAWARA_yellow.get(), JP_Blocks.KAWARA_lime.get(), JP_Blocks.KAWARA_pink.get(), JP_Blocks.KAWARA_gray.get(),
				JP_Blocks.KAWARA_lightg.get(), JP_Blocks.KAWARA_cyan.get(), JP_Blocks.KAWARA_purple.get(), JP_Blocks.KAWARA_blue.get(),
				JP_Blocks.KAWARA_brown.get(), JP_Blocks.KAWARA_green.get(), JP_Blocks.KAWARA_red.get(), JP_Blocks.KAWARA_black.get(),
				JP_Blocks.KAWARA_ST_white.get(), JP_Blocks.KAWARA_ST_orange.get(), JP_Blocks.KAWARA_ST_magenta.get(), JP_Blocks.KAWARA_ST_lightb.get(),
				JP_Blocks.KAWARA_ST_yellow.get(), JP_Blocks.KAWARA_ST_lime.get(), JP_Blocks.KAWARA_ST_pink.get(), JP_Blocks.KAWARA_ST_gray.get(),
				JP_Blocks.KAWARA_ST_lightg.get(), JP_Blocks.KAWARA_ST_cyan.get(), JP_Blocks.KAWARA_ST_purple.get(), JP_Blocks.KAWARA_ST_blue.get(),
				JP_Blocks.KAWARA_ST_brown.get(), JP_Blocks.KAWARA_ST_green.get(), JP_Blocks.KAWARA_ST_red.get(), JP_Blocks.KAWARA_ST_black.get(),
				JP_Blocks.KAWARA_SH_white.get(), JP_Blocks.KAWARA_SH_orange.get(), JP_Blocks.KAWARA_SH_magenta.get(), JP_Blocks.KAWARA_SH_lightb.get(),
				JP_Blocks.KAWARA_SH_yellow.get(), JP_Blocks.KAWARA_SH_lime.get(), JP_Blocks.KAWARA_SH_pink.get(), JP_Blocks.KAWARA_SH_gray.get(),
				JP_Blocks.KAWARA_SH_lightg.get(), JP_Blocks.KAWARA_SH_cyan.get(), JP_Blocks.KAWARA_SH_purple.get(), JP_Blocks.KAWARA_SH_blue.get(),
				JP_Blocks.KAWARA_SH_brown.get(), JP_Blocks.KAWARA_SH_green.get(), JP_Blocks.KAWARA_SH_red.get(), JP_Blocks.KAWARA_SH_black.get(),

				JP_Blocks.DIRTWALL.get(), JP_Blocks.DIRTWALL_stairs.get(), JP_Blocks.DIRTWALL_SH.get(),
				JP_Blocks.SHIKKUI_white.get(), JP_Blocks.SHIKKUI_orange.get(), JP_Blocks.SHIKKUI_magenta.get(), JP_Blocks.SHIKKUI_lightb.get(),
				JP_Blocks.SHIKKUI_yellow.get(), JP_Blocks.SHIKKUI_lime.get(), JP_Blocks.SHIKKUI_pink.get(), JP_Blocks.SHIKKUI_gray.get(),
				JP_Blocks.SHIKKUI_lightg.get(), JP_Blocks.SHIKKUI_cyan.get(), JP_Blocks.SHIKKUI_purple.get(), JP_Blocks.SHIKKUI_blue.get(),
				JP_Blocks.SHIKKUI_brown.get(), JP_Blocks.SHIKKUI_green.get(), JP_Blocks.SHIKKUI_red.get(), JP_Blocks.SHIKKUI_black.get(),
				JP_Blocks.SHIKKUI_ST_white.get(), JP_Blocks.SHIKKUI_ST_orange.get(), JP_Blocks.SHIKKUI_ST_magenta.get(), JP_Blocks.SHIKKUI_ST_lightb.get(),
				JP_Blocks.SHIKKUI_ST_yellow.get(), JP_Blocks.SHIKKUI_ST_lime.get(), JP_Blocks.SHIKKUI_ST_pink.get(), JP_Blocks.SHIKKUI_ST_gray.get(),
				JP_Blocks.SHIKKUI_ST_lightg.get(), JP_Blocks.SHIKKUI_ST_cyan.get(), JP_Blocks.SHIKKUI_ST_purple.get(), JP_Blocks.SHIKKUI_ST_blue.get(),
				JP_Blocks.SHIKKUI_ST_brown.get(), JP_Blocks.SHIKKUI_ST_green.get(), JP_Blocks.SHIKKUI_ST_red.get(), JP_Blocks.SHIKKUI_ST_black.get(),
				JP_Blocks.SHIKKUI_SH_white.get(), JP_Blocks.SHIKKUI_SH_orange.get(), JP_Blocks.SHIKKUI_SH_magenta.get(), JP_Blocks.SHIKKUI_SH_lightb.get(),
				JP_Blocks.SHIKKUI_SH_yellow.get(), JP_Blocks.SHIKKUI_SH_lime.get(), JP_Blocks.SHIKKUI_SH_pink.get(), JP_Blocks.SHIKKUI_SH_gray.get(),
				JP_Blocks.SHIKKUI_SH_lightg.get(), JP_Blocks.SHIKKUI_SH_cyan.get(), JP_Blocks.SHIKKUI_SH_purple.get(), JP_Blocks.SHIKKUI_SH_blue.get(),
				JP_Blocks.SHIKKUI_SH_brown.get(), JP_Blocks.SHIKKUI_SH_green.get(), JP_Blocks.SHIKKUI_SH_red.get(), JP_Blocks.SHIKKUI_SH_black.get(),
	
				JP_Blocks.NAMAKO_white.get(), JP_Blocks.NAMAKO_orange.get(), JP_Blocks.NAMAKO_magenta.get(), JP_Blocks.NAMAKO_lightb.get(),
				JP_Blocks.NAMAKO_yellow.get(), JP_Blocks.NAMAKO_lime.get(), JP_Blocks.NAMAKO_pink.get(), JP_Blocks.NAMAKO_gray.get(),
				JP_Blocks.NAMAKO_lightg.get(), JP_Blocks.NAMAKO_cyan.get(), JP_Blocks.NAMAKO_purple.get(), JP_Blocks.NAMAKO_blue.get(),
				JP_Blocks.NAMAKO_brown.get(), JP_Blocks.NAMAKO_green.get(), JP_Blocks.NAMAKO_red.get(), JP_Blocks.NAMAKO_black.get(),
				JP_Blocks.NAMAKO_ST_white.get(), JP_Blocks.NAMAKO_ST_orange.get(), JP_Blocks.NAMAKO_ST_magenta.get(), JP_Blocks.NAMAKO_ST_lightb.get(),
				JP_Blocks.NAMAKO_ST_yellow.get(), JP_Blocks.NAMAKO_ST_lime.get(), JP_Blocks.NAMAKO_ST_pink.get(), JP_Blocks.NAMAKO_ST_gray.get(),
				JP_Blocks.NAMAKO_ST_lightg.get(), JP_Blocks.NAMAKO_ST_cyan.get(), JP_Blocks.NAMAKO_ST_purple.get(), JP_Blocks.NAMAKO_ST_blue.get(),
				JP_Blocks.NAMAKO_ST_brown.get(), JP_Blocks.NAMAKO_ST_green.get(), JP_Blocks.NAMAKO_ST_red.get(), JP_Blocks.NAMAKO_ST_black.get(),
				JP_Blocks.NAMAKO_SH_white.get(), JP_Blocks.NAMAKO_SH_orange.get(), JP_Blocks.NAMAKO_SH_magenta.get(), JP_Blocks.NAMAKO_SH_lightb.get(),
				JP_Blocks.NAMAKO_SH_yellow.get(), JP_Blocks.NAMAKO_SH_lime.get(), JP_Blocks.NAMAKO_SH_pink.get(), JP_Blocks.NAMAKO_SH_gray.get(),
				JP_Blocks.NAMAKO_SH_lightg.get(), JP_Blocks.NAMAKO_SH_cyan.get(), JP_Blocks.NAMAKO_SH_purple.get(), JP_Blocks.NAMAKO_SH_blue.get(),
				JP_Blocks.NAMAKO_SH_brown.get(), JP_Blocks.NAMAKO_SH_green.get(), JP_Blocks.NAMAKO_SH_red.get(), JP_Blocks.NAMAKO_SH_black.get(),
	
				JP_Blocks.NAMAKOB_white.get(), JP_Blocks.NAMAKOB_orange.get(), JP_Blocks.NAMAKOB_magenta.get(), JP_Blocks.NAMAKOB_lightb.get(),
				JP_Blocks.NAMAKOB_yellow.get(), JP_Blocks.NAMAKOB_lime.get(), JP_Blocks.NAMAKOB_pink.get(), JP_Blocks.NAMAKOB_gray.get(),
				JP_Blocks.NAMAKOB_lightg.get(), JP_Blocks.NAMAKOB_cyan.get(), JP_Blocks.NAMAKOB_purple.get(), JP_Blocks.NAMAKOB_blue.get(),
				JP_Blocks.NAMAKOB_brown.get(), JP_Blocks.NAMAKOB_green.get(), JP_Blocks.NAMAKOB_red.get(), JP_Blocks.NAMAKOB_black.get(),
				JP_Blocks.NAMAKOB_ST_white.get(), JP_Blocks.NAMAKOB_ST_orange.get(), JP_Blocks.NAMAKOB_ST_magenta.get(), JP_Blocks.NAMAKOB_ST_lightb.get(),
				JP_Blocks.NAMAKOB_ST_yellow.get(), JP_Blocks.NAMAKOB_ST_lime.get(), JP_Blocks.NAMAKOB_ST_pink.get(), JP_Blocks.NAMAKOB_ST_gray.get(),
				JP_Blocks.NAMAKOB_ST_lightg.get(), JP_Blocks.NAMAKOB_ST_cyan.get(), JP_Blocks.NAMAKOB_ST_purple.get(), JP_Blocks.NAMAKOB_ST_blue.get(),
				JP_Blocks.NAMAKOB_ST_brown.get(), JP_Blocks.NAMAKOB_ST_green.get(), JP_Blocks.NAMAKOB_ST_red.get(), JP_Blocks.NAMAKOB_ST_black.get(),
				JP_Blocks.NAMAKOB_SH_white.get(), JP_Blocks.NAMAKOB_SH_orange.get(), JP_Blocks.NAMAKOB_SH_magenta.get(), JP_Blocks.NAMAKOB_SH_lightb.get(),
				JP_Blocks.NAMAKOB_SH_yellow.get(), JP_Blocks.NAMAKOB_SH_lime.get(), JP_Blocks.NAMAKOB_SH_pink.get(), JP_Blocks.NAMAKOB_SH_gray.get(),
				JP_Blocks.NAMAKOB_SH_lightg.get(), JP_Blocks.NAMAKOB_SH_cyan.get(), JP_Blocks.NAMAKOB_SH_purple.get(), JP_Blocks.NAMAKOB_SH_blue.get(),
				JP_Blocks.NAMAKOB_SH_brown.get(), JP_Blocks.NAMAKOB_SH_green.get(), JP_Blocks.NAMAKOB_SH_red.get(), JP_Blocks.NAMAKOB_SH_black.get(),
				
				JP_Blocks.DIRTWALL_WALL.get(),
				JP_Blocks.SHIKKUI_WALL_white.get(), JP_Blocks.SHIKKUI_WALL_orange.get(), JP_Blocks.SHIKKUI_WALL_magenta.get(), JP_Blocks.SHIKKUI_WALL_lightb.get(),
				JP_Blocks.SHIKKUI_WALL_yellow.get(), JP_Blocks.SHIKKUI_WALL_lime.get(), JP_Blocks.SHIKKUI_WALL_pink.get(), JP_Blocks.SHIKKUI_WALL_gray.get(),
				JP_Blocks.SHIKKUI_WALL_lightg.get(), JP_Blocks.SHIKKUI_WALL_cyan.get(), JP_Blocks.SHIKKUI_WALL_purple.get(), JP_Blocks.SHIKKUI_WALL_blue.get(),
				JP_Blocks.SHIKKUI_WALL_brown.get(),JP_Blocks.SHIKKUI_WALL_green.get(), JP_Blocks.SHIKKUI_WALL_red.get(), JP_Blocks.SHIKKUI_WALL_black.get(),
				JP_Blocks.NAMAKO_WALL_white.get(), JP_Blocks.NAMAKO_WALL_orange.get(), JP_Blocks.NAMAKO_WALL_magenta.get(), JP_Blocks.NAMAKO_WALL_lightb.get(),
				JP_Blocks.NAMAKO_WALL_yellow.get(), JP_Blocks.NAMAKO_WALL_lime.get(), JP_Blocks.NAMAKO_WALL_pink.get(), JP_Blocks.NAMAKO_WALL_gray.get(), 
				JP_Blocks.NAMAKO_WALL_lightg.get(), JP_Blocks.NAMAKO_WALL_cyan.get(), JP_Blocks.NAMAKO_WALL_purple.get(), JP_Blocks.NAMAKO_WALL_blue.get(),
				JP_Blocks.NAMAKO_WALL_brown.get(), JP_Blocks.NAMAKO_WALL_green.get(), JP_Blocks.NAMAKO_WALL_red.get(), JP_Blocks.NAMAKO_WALL_black.get(),
				JP_Blocks.NAMAKOB_WALL_white.get(), JP_Blocks.NAMAKOB_WALL_orange.get(), JP_Blocks.NAMAKOB_WALL_magenta.get(), JP_Blocks.NAMAKOB_WALL_lightb.get(),
				JP_Blocks.NAMAKOB_WALL_yellow.get(), JP_Blocks.NAMAKOB_WALL_lime.get(), JP_Blocks.NAMAKOB_WALL_pink.get(), JP_Blocks.NAMAKOB_WALL_gray.get(),
				JP_Blocks.NAMAKOB_WALL_lightg.get(), JP_Blocks.NAMAKOB_WALL_cyan.get(), JP_Blocks.NAMAKOB_WALL_purple.get(), JP_Blocks.NAMAKOB_WALL_blue.get(),
				JP_Blocks.NAMAKOB_WALL_brown.get(), JP_Blocks.NAMAKOB_WALL_green.get(), JP_Blocks.NAMAKOB_WALL_red.get(), JP_Blocks.NAMAKOB_WALL_black.get(),
				JP_Blocks.DIRTWALL_SAMA.get(),
				JP_Blocks.SHIKKUI_SAMA_white.get(), JP_Blocks.SHIKKUI_SAMA_orange.get(), JP_Blocks.SHIKKUI_SAMA_magenta.get(), JP_Blocks.SHIKKUI_SAMA_lightb.get(), 
				JP_Blocks.SHIKKUI_SAMA_yellow.get(), JP_Blocks.SHIKKUI_SAMA_lime.get(), JP_Blocks.SHIKKUI_SAMA_pink.get(), JP_Blocks.SHIKKUI_SAMA_gray.get(),
				JP_Blocks.SHIKKUI_SAMA_lightg.get(), JP_Blocks.SHIKKUI_SAMA_cyan.get(), JP_Blocks.SHIKKUI_SAMA_purple.get(), JP_Blocks.SHIKKUI_SAMA_blue.get(),
				JP_Blocks.SHIKKUI_SAMA_brown.get(), JP_Blocks.SHIKKUI_SAMA_green.get(), JP_Blocks.SHIKKUI_SAMA_red.get(), JP_Blocks.SHIKKUI_SAMA_black.get(),
				JP_Blocks.KAWARA_WALL_white.get(), JP_Blocks.KAWARA_WALL_orange.get(), JP_Blocks.KAWARA_WALL_magenta.get(), JP_Blocks.KAWARA_WALL_lightb.get(),
				JP_Blocks.KAWARA_WALL_yellow.get(), JP_Blocks.KAWARA_WALL_lime.get(),JP_Blocks.KAWARA_WALL_pink.get(), JP_Blocks.KAWARA_WALL_gray.get(),
				JP_Blocks.KAWARA_WALL_lightg.get(), JP_Blocks.KAWARA_WALL_cyan.get(), JP_Blocks.KAWARA_WALL_purple.get(), JP_Blocks.KAWARA_WALL_blue.get(),
				JP_Blocks.KAWARA_WALL_brown.get(), JP_Blocks.KAWARA_WALL_green.get(), JP_Blocks.KAWARA_WALL_red.get(), JP_Blocks.KAWARA_WALL_black.get(),
				
				Kitchen_Blocks.KIT_OVEN.get(), Kitchen_Blocks.KIT_OVEN_B.get(), Kitchen_Blocks.IRORI.get(), 
				Kitchen_Blocks.KIT_REIZOU.get(), Kitchen_Blocks.KIT_REIZOU_TOP.get(),
				Kitchen_Blocks.KIT_KANKI_1.get(), Kitchen_Blocks.KIT_HAIKIDUCT.get(), Kitchen_Blocks.KIT_DUCTEND_1.get(),
				
				School_Blocks.STOVECHIMNEY.get(), School_Blocks.STOVECHIMNEY_joint.get(), School_Blocks.STOVECHIMNEY_topk.get(),
				School_Blocks.CSTOVE_top.get(), School_Blocks.CSTOVE_bot.get(),
				
				Seasonal_Blocks.KAKIGOURI_block.get(), Seasonal_Blocks.KAKIGOURI_apple.get(), Seasonal_Blocks.KAKIGOURI_cherry.get(),
				Seasonal_Blocks.KAKIGOURI_citrus.get(), Seasonal_Blocks.KAKIGOURI_grape.get(), Seasonal_Blocks.KAKIGOURI_tea.get(),

				WallPanel_Blocks.BRICK_GRA.get(), WallPanel_Blocks.BRICK_DIO.get(), WallPanel_Blocks.BRICK_AND.get(),
				WallPanel_Blocks.BRICKGRA_CH.get(), WallPanel_Blocks.BRICKDIO_CH.get(), WallPanel_Blocks.BRICKAND_CH.get(),
				WallPanel_Blocks.BRICKGRA_CR.get(), WallPanel_Blocks.BRICKDIO_CR.get(), WallPanel_Blocks.BRICKAND_CR.get(),
				WallPanel_Blocks.BRICKGRA_MOS.get(), WallPanel_Blocks.BRICKDIO_MOS.get(), WallPanel_Blocks.BRICKAND_MOS.get(),
				WallPanel_Blocks.BRICKSTAIRS_GRA.get(), WallPanel_Blocks.BRICKSTAIRS_DIO.get(), WallPanel_Blocks.BRICKSTAIRS_AND.get(),
				WallPanel_Blocks.BGC_slabhalf.get(), WallPanel_Blocks.BDC_slabhalf.get(), WallPanel_Blocks.BAC_slabhalf.get(),
				WallPanel_Blocks.BRICK_STONE_PIL.get(), WallPanel_Blocks.BRICK_GRA_PIL.get(), WallPanel_Blocks.BRICK_DIO_PIL.get(), WallPanel_Blocks.BRICK_AND_PIL.get(),
				
				WallPanel_Blocks.WP_STONE.get(), WallPanel_Blocks.WP_STONE_M.get(),
				WallPanel_Blocks.WP_STONE_gra.get(), WallPanel_Blocks.WP_STONE_dio.get(), WallPanel_Blocks.WP_STONE_and.get(),
				WallPanel_Blocks.WP_STONE_B.get(), WallPanel_Blocks.WP_STONE_graB.get(), WallPanel_Blocks.WP_STONE_dioB.get(), WallPanel_Blocks.WP_STONE_andB.get(),
				WallPanel_Blocks.WP_STONE_P.get(), WallPanel_Blocks.WP_STONE_graP.get(), WallPanel_Blocks.WP_STONE_dioP.get(), WallPanel_Blocks.WP_STONE_andP.get(),
				WallPanel_Blocks.WP_BRICK.get(), WallPanel_Blocks.WP_SANDSTONE.get(), WallPanel_Blocks.WP_REDSANDSTONE.get(), 
				
				WallPanel_Blocks.WP_DEEPSLATE.get(), WallPanel_Blocks.WP_DEEPSLATE_B.get(), WallPanel_Blocks.WP_TUFF.get(),
				WallPanel_Blocks.WP_MUDPACK.get(), WallPanel_Blocks.WP_PRISMA.get(), WallPanel_Blocks.WP_OBSIDIAN.get(),
				
				WallPanel_Blocks.WP_CLAY.get(),
				WallPanel_Blocks.WP_CLAY_white.get(), WallPanel_Blocks.WP_CLAY_orange.get(), WallPanel_Blocks.WP_CLAY_magenta.get(), WallPanel_Blocks.WP_CLAY_lightb.get(),
				WallPanel_Blocks.WP_CLAY_yellow.get(), WallPanel_Blocks.WP_CLAY_lime.get(), WallPanel_Blocks.WP_CLAY_pink.get(), WallPanel_Blocks.WP_CLAY_gray.get(),
				WallPanel_Blocks.WP_CLAY_lightg.get(), WallPanel_Blocks.WP_CLAY_cyan.get(), WallPanel_Blocks.WP_CLAY_purple.get(), WallPanel_Blocks.WP_CLAY_blue.get(),
				WallPanel_Blocks.WP_CLAY_brown.get(), WallPanel_Blocks.WP_CLAY_green.get(), WallPanel_Blocks.WP_CLAY_red.get(), WallPanel_Blocks.WP_CLAY_black.get(),
				WallPanel_Blocks.WP_GLASS.get(),
				WallPanel_Blocks.WP_GLASS_white.get(), WallPanel_Blocks.WP_GLASS_orange.get(), WallPanel_Blocks.WP_GLASS_magenta.get(), WallPanel_Blocks.WP_GLASS_lightb.get(),
				WallPanel_Blocks.WP_GLASS_yellow.get(), WallPanel_Blocks.WP_GLASS_lime.get(), WallPanel_Blocks.WP_GLASS_pink.get(), WallPanel_Blocks.WP_GLASS_gray.get(),
				WallPanel_Blocks.WP_GLASS_lightg.get(), WallPanel_Blocks.WP_GLASS_cyan.get(), WallPanel_Blocks.WP_GLASS_purple.get(), WallPanel_Blocks.WP_GLASS_blue.get(),
				WallPanel_Blocks.WP_GLASS_brown.get(), WallPanel_Blocks.WP_GLASS_green.get(), WallPanel_Blocks.WP_GLASS_red.get(), WallPanel_Blocks.WP_GLASS_black.get(),
	
				WallPanel_Blocks.WP_NETHE_rack.get(), WallPanel_Blocks.WP_NETHE_b.get(), WallPanel_Blocks.WP_QUARTZ.get(), WallPanel_Blocks.WP_QUARTZ_PIL.get(),
				WallPanel_Blocks.WP_ENDSTONE.get(), WallPanel_Blocks.WP_ENDBRICKS.get(), WallPanel_Blocks.WP_PURPUR.get(), WallPanel_Blocks.WP_PURPUR_PIL.get(),
				
				WallPanel_Blocks.WP_DIRTWALL.get(), 
				WallPanel_Blocks.WP_PLASTER_white.get(), WallPanel_Blocks.WP_PLASTER_orange.get(), WallPanel_Blocks.WP_PLASTER_magenta.get(), WallPanel_Blocks.WP_PLASTER_lightb.get(), 
				WallPanel_Blocks.WP_PLASTER_yellow.get(), WallPanel_Blocks.WP_PLASTER_lime.get(), WallPanel_Blocks.WP_PLASTER_pink.get(), WallPanel_Blocks.WP_PLASTER_gray.get(),
				WallPanel_Blocks.WP_PLASTER_lightg.get(), WallPanel_Blocks.WP_PLASTER_cyan.get(), WallPanel_Blocks.WP_PLASTER_purple.get(), WallPanel_Blocks.WP_PLASTER_blue.get(),
				WallPanel_Blocks.WP_PLASTER_brown.get(), WallPanel_Blocks.WP_PLASTER_green.get(), WallPanel_Blocks.WP_PLASTER_red.get(), WallPanel_Blocks.WP_PLASTER_black.get(), 
				WallPanel_Blocks.WP_NAMAKO_white.get(), WallPanel_Blocks.WP_NAMAKO_orange.get(), WallPanel_Blocks.WP_NAMAKO_magenta.get(), WallPanel_Blocks.WP_NAMAKO_lightb.get(), 
				WallPanel_Blocks.WP_NAMAKO_yellow.get(), WallPanel_Blocks.WP_NAMAKO_lime.get(), WallPanel_Blocks.WP_NAMAKO_pink.get(), WallPanel_Blocks.WP_NAMAKO_gray.get(),
				WallPanel_Blocks.WP_NAMAKO_lightg.get(), WallPanel_Blocks.WP_NAMAKO_cyan.get(), WallPanel_Blocks.WP_NAMAKO_purple.get(), WallPanel_Blocks.WP_NAMAKO_blue.get(),
				WallPanel_Blocks.WP_NAMAKO_brown.get(), WallPanel_Blocks.WP_NAMAKO_green.get(), WallPanel_Blocks.WP_NAMAKO_red.get(), WallPanel_Blocks.WP_NAMAKO_black.get(), 
				WallPanel_Blocks.WP_NAMAKOB_white.get(), WallPanel_Blocks.WP_NAMAKOB_orange.get(), WallPanel_Blocks.WP_NAMAKOB_magenta.get(), WallPanel_Blocks.WP_NAMAKOB_lightb.get(), 
				WallPanel_Blocks.WP_NAMAKOB_yellow.get(), WallPanel_Blocks.WP_NAMAKOB_lime.get(), WallPanel_Blocks.WP_NAMAKOB_pink.get(), WallPanel_Blocks.WP_NAMAKOB_gray.get(),
				WallPanel_Blocks.WP_NAMAKOB_lightg.get(), WallPanel_Blocks.WP_NAMAKOB_cyan.get(), WallPanel_Blocks.WP_NAMAKOB_purple.get(), WallPanel_Blocks.WP_NAMAKOB_blue.get(),
				WallPanel_Blocks.WP_NAMAKOB_brown.get(), WallPanel_Blocks.WP_NAMAKOB_green.get(), WallPanel_Blocks.WP_NAMAKOB_red.get(), WallPanel_Blocks.WP_NAMAKOB_black.get());
											
		
		this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
		.add(Crop_Blocks.ENDEN.get(), Crop_Blocks.ENDEN_k.get(), Crop_Blocks.KAINASHI.get(),
				Garden_Blocks.SAMON.get(), Garden_Blocks.SAMON_B.get(),
				Seasonal_Blocks.SNOWCORE.get(), Seasonal_Blocks.SNOWMAN.get(), Seasonal_Blocks.SNOWMAN_COLOR.get(),
				Wood_Blocks.SUIDEN.get(), Wood_Blocks.FALL_LEAF.get());
		
		this.tag(BlockTags.MINEABLE_WITH_HOE)
		.add(Wood_Blocks.TAKENOKO.get(),
				Wood_Blocks.SAKURA_flow.get(), Wood_Blocks.KAEDE_leaf.get(), Wood_Blocks.ICHOH_leaf.get(), Wood_Blocks.OAKKARE_leaf.get(),
				Wood_Blocks.SAKURA_carpet.get(), Wood_Blocks.KAEDE_carpet.get(), Wood_Blocks.ICHOH_carpet.get(), Wood_Blocks.OCHIBA_carpet.get());
	}
	
	@Override
	public String getName() {
		return "ChinjufuMod Block Tags";
	}
}
