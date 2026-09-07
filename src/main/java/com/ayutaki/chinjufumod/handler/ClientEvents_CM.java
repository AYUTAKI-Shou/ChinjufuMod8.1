package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.entity.render.RenderAmmo_Kijyuu;
import com.ayutaki.chinjufumod.entity.render.RenderAmmo_Large;
import com.ayutaki.chinjufumod.entity.render.RenderAmmo_Medium;
import com.ayutaki.chinjufumod.entity.render.RenderAmmo_Small;
import com.ayutaki.chinjufumod.entity.render.RenderGyorai61cm;
import com.ayutaki.chinjufumod.entity.render.RenderKB_F4U;
import com.ayutaki.chinjufumod.entity.render.RenderKB_Ju87;
import com.ayutaki.chinjufumod.entity.render.RenderKB_Re2001;
import com.ayutaki.chinjufumod.entity.render.RenderKB_SBD;
import com.ayutaki.chinjufumod.entity.render.RenderKB_Suisei;
import com.ayutaki.chinjufumod.entity.render.RenderKB_Type99;
import com.ayutaki.chinjufumod.entity.render.RenderKB_TypeZero;
import com.ayutaki.chinjufumod.entity.render.RenderKK_Barracuda;
import com.ayutaki.chinjufumod.entity.render.RenderKK_Mosquito;
import com.ayutaki.chinjufumod.entity.render.RenderKK_Ryusei;
import com.ayutaki.chinjufumod.entity.render.RenderKK_Swordfish;
import com.ayutaki.chinjufumod.entity.render.RenderKK_TBF;
import com.ayutaki.chinjufumod.entity.render.RenderKK_Tenzan;
import com.ayutaki.chinjufumod.entity.render.RenderKK_Type97;
import com.ayutaki.chinjufumod.entity.render.RenderSB_Seiran;
import com.ayutaki.chinjufumod.entity.render.RenderSB_Zuiun;
import com.ayutaki.chinjufumod.entity.render.SitableRenderer;
import com.ayutaki.chinjufumod.entity.render.ToamiRenderer;
import com.ayutaki.chinjufumod.gui.NoteScreen;
import com.ayutaki.chinjufumod.gui.ReizouScreen;
import com.ayutaki.chinjufumod.gui.ReizouTopScreen;
import com.ayutaki.chinjufumod.gui.TansuScreen;
import com.ayutaki.chinjufumod.items.armor.model.AkatsukiModel;
import com.ayutaki.chinjufumod.items.armor.model.BattleshipModel;
import com.ayutaki.chinjufumod.items.armor.model.GisouModel;
import com.ayutaki.chinjufumod.items.armor.model.I401_Model;
import com.ayutaki.chinjufumod.items.armor.model.IkkousenModel;
import com.ayutaki.chinjufumod.items.armor.model.Ise_Model;
import com.ayutaki.chinjufumod.items.armor.model.KasumiOuter;
import com.ayutaki.chinjufumod.items.armor.model.NagatoModel;
import com.ayutaki.chinjufumod.items.armor.model.RJModel;
import com.ayutaki.chinjufumod.items.armor.model.Ro500_Outer;
import com.ayutaki.chinjufumod.items.armor.model.Santa_Model;
import com.ayutaki.chinjufumod.items.armor.model.SendaiModel;
import com.ayutaki.chinjufumod.items.armor.model.SubmarineModel;
import com.ayutaki.chinjufumod.items.armor.model.ToneModel;
import com.ayutaki.chinjufumod.items.armor.model.UKIWA_Model;
import com.ayutaki.chinjufumod.items.armor.model.YUKATA_Model;
import com.ayutaki.chinjufumod.items.armor.model.YuraModel;
import com.ayutaki.chinjufumod.items.armor.model.ZuihouModel;
import com.ayutaki.chinjufumod.particle.AutumnParticle;
import com.ayutaki.chinjufumod.particle.Chalk_Particle;
import com.ayutaki.chinjufumod.particle.IchohParticle;
import com.ayutaki.chinjufumod.particle.KaedeParticle;
import com.ayutaki.chinjufumod.particle.Particle_Ammo;
import com.ayutaki.chinjufumod.particle.Particle_Mark;
import com.ayutaki.chinjufumod.particle.Particle_ShootK;
import com.ayutaki.chinjufumod.particle.Particle_ShootL;
import com.ayutaki.chinjufumod.particle.Particle_ShootM;
import com.ayutaki.chinjufumod.particle.SakuraParticle;
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
import com.ayutaki.chinjufumod.tileentity.render.BlackBoard_TERenderer;
import com.ayutaki.chinjufumod.tileentity.render.WoodBoard_TERenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents_CM {

	/* RenderTypes -> .json "render_type": "cutout", "render_type": "cutout_mipped", "render_type": "translucent", */
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		/** Some client setup code **/
		ChinjufuMod.LOGGER.info("Hello from ChinjufuMod Client Events!");
		/* ItemModels ...eating drinking */
		ItemModelsProperty_CM.register();
		MenuScreens.register(MenuTypes_CM.NOTE_MENU.get(), NoteScreen::new);
		MenuScreens.register(MenuTypes_CM.REIZOU_MENU.get(), ReizouScreen::new);
		MenuScreens.register(MenuTypes_CM.REIZOUTOP_MENU.get(), ReizouTopScreen::new);
		MenuScreens.register(MenuTypes_CM.TANSU_MENU.get(), TansuScreen::new);
	}
	
	@SubscribeEvent
	public static void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BlockEntity_CM.BLACKBOARD.get(), BlackBoard_TERenderer::new);
		event.registerBlockEntityRenderer(BlockEntity_CM.WOODBOARD.get(), WoodBoard_TERenderer::new);
	}
	
	/* EntityRender_CM */
	@SubscribeEvent
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		Minecraft mClient = Minecraft.getInstance();
		event.registerEntityRenderer(EntityTypes_CM.SITABLE.get(), SitableRenderer::new); 

		event.registerEntityRenderer(EntityTypes_CM.AMMO_L.get(), RenderAmmo_Large::new);
		event.registerEntityRenderer(EntityTypes_CM.AMMO_M.get(), RenderAmmo_Medium::new);
		event.registerEntityRenderer(EntityTypes_CM.AMMO_S.get(), RenderAmmo_Small::new);
		event.registerEntityRenderer(EntityTypes_CM.AMMO_K.get(), RenderAmmo_Kijyuu::new);
		
		event.registerEntityRenderer(EntityTypes_CM.TYPE97.get(), renderManager -> new RenderKK_Type97(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.TENZAN.get(), renderManager -> new RenderKK_Tenzan(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.RYUSEI.get(), renderManager -> new RenderKK_Ryusei(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.TBF.get(), renderManager -> new RenderKK_TBF(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.SWORDFISH.get(), renderManager -> new RenderKK_Swordfish(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.BARRACUDA.get(), renderManager -> new RenderKK_Barracuda(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.MOSQUITO.get(), renderManager -> new RenderKK_Mosquito(renderManager, mClient.getItemRenderer()));
		
		event.registerEntityRenderer(EntityTypes_CM.TYPE99.get(), renderManager -> new RenderKB_Type99(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.SUISEI.get(), renderManager -> new RenderKB_Suisei(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.TYPEZERO.get(), renderManager -> new RenderKB_TypeZero(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.JU87.get(), renderManager -> new RenderKB_Ju87(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.RE2001.get(), renderManager -> new RenderKB_Re2001(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.SBD.get(), renderManager -> new RenderKB_SBD(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.F4U.get(), renderManager -> new RenderKB_F4U(renderManager, mClient.getItemRenderer()));

		event.registerEntityRenderer(EntityTypes_CM.ZUIUN.get(), renderManager -> new RenderSB_Zuiun(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.SEIRAN.get(), renderManager -> new RenderSB_Seiran(renderManager, mClient.getItemRenderer()));
		event.registerEntityRenderer(EntityTypes_CM.GYORAI61.get(), renderManager -> new RenderGyorai61cm(renderManager, mClient.getItemRenderer()));

		event.registerEntityRenderer(EntityTypes_CM.TOAMI.get(), renderManager -> new ToamiRenderer(renderManager, mClient.getItemRenderer()));
	}
	
	/* ParticleManager_CM */
	@SubscribeEvent
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(ParticleTypes_CM.FALLSAKURA.get(), SakuraParticle.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.FALLKAEDE.get(), KaedeParticle.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.FALLICHOH.get(), IchohParticle.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.FALLKARE.get(), AutumnParticle.Provider::new);
		
		event.registerSpriteSet(ParticleTypes_CM.AMMO_PT.get(), Particle_Ammo.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.SHOOT_PT.get(), Particle_ShootK.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.SHOOTL_PT.get(), Particle_ShootL.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.SHOOTM_PT.get(), Particle_ShootM.Provider::new);
		event.registerSpriteSet(ParticleTypes_CM.MARK_PT.get(), Particle_Mark.Provider::new);
		
		event.registerSpriteSet(ParticleTypes_CM.CHALK_PT.get(), Chalk_Particle.Provider::new);
	}
	
	/* ArmorRender_CM */
	@SubscribeEvent
	public static void registerEntityRenderingHandler(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ArmorLayer_CM.GISOU_INNER, () -> LayerDefinition.create(GisouModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.GISOU_OUTER, () -> LayerDefinition.create(GisouModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.AKATSUKI_INNER, () -> LayerDefinition.create(AkatsukiModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.AKATSUKI_OUTER, () -> LayerDefinition.create(AkatsukiModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.KASUMI_OUTER, () -> LayerDefinition.create(KasumiOuter.createOuter(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SENDAI_INNER, () -> LayerDefinition.create(SendaiModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SENDAI_OUTER, () -> LayerDefinition.create(SendaiModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YURA_INNER, () -> LayerDefinition.create(YuraModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YURA_OUTER, () -> LayerDefinition.create(YuraModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.TONE_INNER, () -> LayerDefinition.create(ToneModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.TONE_OUTER, () -> LayerDefinition.create(ToneModel.createOuter(), 64, 120));

		event.registerLayerDefinition(ArmorLayer_CM.RJ_INNER, () -> LayerDefinition.create(RJModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_OUTER, () -> LayerDefinition.create(RJModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ZUIHOU_INNER, () -> LayerDefinition.create(ZuihouModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ZUIHOU_OUTER, () -> LayerDefinition.create(ZuihouModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.IKKOU_INNER, () -> LayerDefinition.create(IkkousenModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.IKKOU_OUTER, () -> LayerDefinition.create(IkkousenModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_INNER, () -> LayerDefinition.create(RJModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_OUTER, () -> LayerDefinition.create(RJModel.createOuter(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SENKAN_INNER, () -> LayerDefinition.create(BattleshipModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SENKAN_OUTER, () -> LayerDefinition.create(BattleshipModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ISE_INNER, () -> LayerDefinition.create(Ise_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ISE_OUTER, () -> LayerDefinition.create(Ise_Model.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.NAGATO_INNER, () -> LayerDefinition.create(NagatoModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.NAGATO_OUTER, () -> LayerDefinition.create(NagatoModel.createOuter(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SUBMARINE_INNER, () -> LayerDefinition.create(SubmarineModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SUBMARINE_OUTER, () -> LayerDefinition.create(SubmarineModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.UKIWA_INNER, () -> LayerDefinition.create(UKIWA_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.I401_INNER, () -> LayerDefinition.create(I401_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.I401_OUTER, () -> LayerDefinition.create(I401_Model.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RO500_OUTER, () -> LayerDefinition.create(Ro500_Outer.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RO500_OUTER_C, () -> LayerDefinition.create(UKIWA_Model.ro500_OuterC(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.YUKATA_INNER, () -> LayerDefinition.create(YUKATA_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YUKATA_OUTER, () -> LayerDefinition.create(YUKATA_Model.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SANTA_INNER, () -> LayerDefinition.create(Santa_Model.createInner(), 64, 32));
		event.registerLayerDefinition(ArmorLayer_CM.SANTA_OUTER, () -> LayerDefinition.create(Santa_Model.createOuter(), 64, 32));
	}
	
	
	/* TintIndex of Block. Small values version. , "tintindex": 1 */
	@SubscribeEvent
	public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
		
		/** 20 Water=1, 35 CornSoup=2 **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? BiomeColors.getAverageWaterColor(worldIn, pos) : ((tint == 2)? 16441700 : -1); },
				Garden_Blocks.CHOUZUBACHI.get(),
				Garden_Blocks.CHOUZUBACHI_gra.get(),
				Garden_Blocks.CHOUZUBACHI_dio.get(),
				Garden_Blocks.CHOUZUBACHI_and.get(),
				Garden_Blocks.SHISHIODOSHI.get(),
				Garden_Blocks.SHISHIODOSHI2.get(),
				Kitchen_Blocks.KIT_SINK.get(),
				Wood_Blocks.SUIDEN.get(),

				Hakkou_Blocks.MIZUOKE.get(),
				Hakkou_Blocks.MIZUOKE_full.get(),
				Hakkou_Blocks.HAKUSAI_TARU1.get(),
				Hakkou_Blocks.HAKUSAI_TARU2.get(),
				
				Dish_Blocks.KEIRYO_CUP.get(),
				Dish_Blocks.CURRY.get(),
				Dish_Blocks.CURRY_C.get(),
				Dish_Blocks.CURRY_T.get(),
				Dish_Blocks.CURRYSET.get(),
				Dish_Blocks.CURRYSET_C.get(),
				Dish_Blocks.CURRYSET_T.get(),
				Dish_Blocks.ZUNDOU_NCURRY.get(),
				Dish_Blocks.ZUNDOU_NCURRY_C.get(),
				Dish_Blocks.ZUNDOU_NCURRY_T.get(),
				Dish_Blocks.ZUNDOU_RAMEN.get(),
				Dish_Blocks.ZUNDOU_RSOUP_nama.get(),
				
				Dish_Blocks.KETTLE_full.get(),
				Dish_Blocks.ZUNDOU_MIZU.get(),
				Dish_Blocks.ZUNDOU_SHIO.get(),
				Dish_Blocks.ZUNDOU_FISH.get(),
				Dish_Blocks.ZUNDOU_UDON.get(),
				Dish_Blocks.ZUNDOU_PASTA.get(),
				
				Dish_Blocks.NABETORI_nama.get(),
				Dish_Blocks.NABEMISO_nama.get(),
				Dish_Blocks.NABEGOHAN_nama.get(),
				Dish_Blocks.NABEGOHANKURI_nama.get(),
				Dish_Blocks.NABESEKIHAN_nama.get(),
				Dish_Blocks.NABESHIO_nama.get(),
				Dish_Blocks.NABENIMAME_nama.get(),
				Dish_Blocks.NABEAZUKI_nama.get(), /** 7.4.1 **/
				Dish_Blocks.NABEANKO_nama.get(), /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_nama.get(), /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_green.get(), /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_red.get(), /** 7.4.1 **/
				Dish_Blocks.NABEPUDDING_cacao.get(), /** 7.4.1 **/
				Dish_Blocks.KURI_NABE_nama.get(),
				
				Dish_Blocks.NABECORN_nama.get(),
				Dish_Blocks.NABECORN.get(),
				Dish_Blocks.CORNSOUP.get(),
				Dish_Blocks.EGGBURGSET.get() );
		
		/** 21 Raw_Sake **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 12509680 : -1; },
				Hakkou_Blocks.NAMASAKEGLASS.get() );
		
		/** 22 Sake **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 9223890 : -1; },
				Hakkou_Blocks.SAKEGLASS.get() );
		
		/** 23 Aged_Sake **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 11164250 : -1; },
				Hakkou_Blocks.JUKUSAKEGLASS.get() );
		
		/** 24 Cider **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 15127945 : -1; },
				Hakkou_Blocks.CIDERGLASS.get() );
		
		/** 25 Aged_Cider **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 14588001 : -1; },
				Hakkou_Blocks.JUKUCIDERGLASS.get() );
		
		/** 26 Wine **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 8398655 : -1; },
				Hakkou_Blocks.WINEGLASS.get() );
		
		/** 27 Aged_Wine **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 5715258 : -1; },
				Hakkou_Blocks.JUKUWINEGLASS.get() );
		
		/** 28 Mead **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 13821640 : -1; },
				Hakkou_Blocks.MEADGLASS.get() );
		
		/** 29 Aged_Mead **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 13808770 : -1; },
				Hakkou_Blocks.JUKUMEADGLASS.get() );
		
		/** 30 Green_Tea=1, 32 MisoSoup=2 **/
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 16775010 : ((tint == 2)? 15772230 : -1); },
				Dish_Blocks.KYUSU.get(), 
				Dish_Blocks.JPTEACUP.get(),
				Dish_Blocks.JPTEASET.get(),
				Dish_Blocks.SUSHISET_salmon.get(),
				Dish_Blocks.SUSHISET_fish.get(),
				Dish_Blocks.SUSHISET_beef.get(),
				Dish_Blocks.SUSHISET_tamago.get(),
				Dish_Blocks.SUSHISET_4shoku.get(),
				
				Dish_Blocks.NABETORI.get(),
				Dish_Blocks.NABEMISO.get(),
				Dish_Blocks.TONSUITORI.get(),
				Dish_Blocks.MISOSOUP.get(),
				
				Dish_Blocks.TAMAGOYAKITEI.get(),
				Dish_Blocks.YAKIZAKANATEI.get(),
				Dish_Blocks.YAKIJYAKETEI.get(),
				Dish_Blocks.TAMAGOYAKITEI_TAKE.get(),
				Dish_Blocks.YAKIZAKANATEI_TAKE.get(),
				Dish_Blocks.YAKIJYAKETEI_TAKE.get(),
				Dish_Blocks.TAMAGOYAKITEI_KURI.get(),
				Dish_Blocks.YAKIZAKANATEI_KURI.get(),
				Dish_Blocks.YAKIJYAKETEI_KURI.get(),
				Dish_Blocks.TAMAGOYAKITEI_SEKI.get(), /** 7.4.1 **/
				Dish_Blocks.YAKIZAKANATEI_SEKI.get(), /** 7.4.1 **/
				Dish_Blocks.YAKIJYAKETEI_SEKI.get() /** 7.4.1 **/ );
		
		/** 31 Black_Tea **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 14493736 : -1; },
				Dish_Blocks.TEAPOT.get(), 
				Dish_Blocks.TEACUP.get(),
				Dish_Blocks.TEASET.get() );

		/** 33 Aku **/
		event.register((state, worldIn, pos, tint) -> { return (tint == 1)? 3297410 : -1; },
				Dish_Blocks.ZUNDOU_AKU.get(),
				Dish_Blocks.ZUNDOU_ORIITO.get() );

		/** 20 Water=1, 34 AMAZAKE=2 **/
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? BiomeColors.getAverageWaterColor(worldIn, pos) : ((tint == 2)? 16443100 : -1); },
				Hakkou_Blocks.NABEAMAZAKE_nama.get(),
				Hakkou_Blocks.NABEAMAZAKE.get(),
				Hakkou_Blocks.AMAZAKEGLASS.get() );
		
		/** 40 ENDEN=1, 41 ENDEN=2 **/
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 6587090 : ((tint == 2)? 8560880 : -1); },
				Crop_Blocks.ENDEN.get() );
		
		/** 42 KAINASHI=1 **/
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 15790315 : -1; },
				Crop_Blocks.KAINASHI.get() );
		
		/* BiomeColors.class 設置場所の色を拾う BiomeColors.getFoliageColor(worldIn, pos) */
		/* Biome.class 気温と降雨量から色指定 FoliageColor.get(Temperature, Downfall) 1.0F以上はクラッシュする */
		/** 1 Oak **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.get(0.7F, 0.6F) : -1; },
				Garden_Blocks.BONSAI_oak.get(),
				Garden_Blocks.KANYOU.get(),
				Garden_Blocks.IKEGAKILONG.get(),
				Garden_Blocks.IKEGAKI.get() );
		
		/** 2 Spruce **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.getEvergreenColor() : -1; },
				Garden_Blocks.BONSAI_spru.get(),
				Garden_Blocks.KANYOU_spruce.get(),
				Garden_Blocks.IKEGAKILONG_spruce.get(),
				Garden_Blocks.IKEGAKI_spruce.get() );

		/** 3 Birch **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.getBirchColor() : -1; },
				Garden_Blocks.BONSAI_bir.get(),
				Garden_Blocks.KANYOU_birch.get(),
				Garden_Blocks.IKEGAKILONG_birch.get(),
				Garden_Blocks.IKEGAKI_birch.get() );
		
		/** 4 Jungle **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.get(0.9F, 0.9F) : -1; }, //緑
				Garden_Blocks.BONSAI_jun.get(),
				Garden_Blocks.KANYOU_jungle.get(),
				Garden_Blocks.IKEGAKILONG_jungle.get(),
				Garden_Blocks.IKEGAKI_jungle.get() );
		
		/** 5 Acacia **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.get(0.9F, 0.0F) : -1; },
				Garden_Blocks.BONSAI_aca.get(),
				Garden_Blocks.KANYOU_acacia.get(),
				Garden_Blocks.IKEGAKILONG_acacia.get(),
				Garden_Blocks.IKEGAKI_acacia.get() );
		
		/** 6 DarkOak **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.get(0.7F, 0.8F) : -1; }, //黒
				Garden_Blocks.BONSAI_doak.get(),
				Garden_Blocks.KANYOU_darkoak.get(),
				Garden_Blocks.IKEGAKILONG_darkoak.get(),
				Garden_Blocks.IKEGAKI_darkoak.get() );
		
		/** MANGROVE **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.get(0.8F, 0.2F) : -1; },
				Garden_Blocks.BONSAI_mangrove.get(),
				Garden_Blocks.KANYOU_mangrove.get(),
				Garden_Blocks.IKEGAKILONG_mangrove.get(),
				Garden_Blocks.IKEGAKI_mangrove.get() );
		
		/** 7 KAEDE ---> Delete **/
		
		/** 8 Autumn_Oak (170,115,50)11170610 **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? 11170610 : -1; },
				Garden_Blocks.BONSAI_kare.get(),
				Garden_Blocks.KANYOU_kare.get(),
				Garden_Blocks.IKEGAKILONG_kare.get(),
				Garden_Blocks.IKEGAKI_kare.get(),
				Wood_Blocks.OAKKARE_leaf.get(),
				Wood_Blocks.OCHIBA_carpet.get() );
		
		/** Reizou 1=200,200,200 2=225,240,255**/
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 13158600 : ((tint == 2)? 14807295 : -1); },
				Kitchen_Blocks.KIT_REIZOU.get(),
				Kitchen_Blocks.KIT_REIZOU_TOP.get() );
		
		/* 200, 100 */
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 13158600 : ((tint == 2)? 6579300 : -1); },
				Chinjufu_Blocks.EMPTY_BOX.get(),
				Chinjufu_Blocks.AMMO_BOX.get(),
				Chinjufu_Blocks.BAUXITE_BOX.get(),

				Pantry_Blocks.BOX_H_EMPTY.get(),
				Pantry_Blocks.BOX_H_EMPTY2.get(),
				Pantry_Blocks.BOX_H_EMPTY3.get(),
				Pantry_Blocks.BOX_H_APPLE.get(),
				Pantry_Blocks.BOX_H_BEEF.get(),
				Pantry_Blocks.BOX_H_BEETROOT.get(),
				Pantry_Blocks.BOX_H_BREAD.get(),
				Pantry_Blocks.BOX_H_CARROT.get(),
				Pantry_Blocks.BOX_H_CHICKEN.get(),
				Pantry_Blocks.BOX_H_CHORUS.get(),
				Pantry_Blocks.BOX_H_COCO.get(),
				Pantry_Blocks.BOX_H_COD.get(),
				Pantry_Blocks.BOX_H_EGG.get(),
				Pantry_Blocks.BOX_H_FISH.get(),
				Pantry_Blocks.BOX_H_FLOUR.get(),
				Pantry_Blocks.BOX_H_MUTTON.get(),
				Pantry_Blocks.BOX_H_PORK.get(),
				Pantry_Blocks.BOX_H_POTATO.get(),
				Pantry_Blocks.BOX_H_RABBIT.get(),
				Pantry_Blocks.BOX_H_SALMON.get(),
				Pantry_Blocks.BOX_H_SWBERRY.get(),

				Pantry_Blocks.BOX_H_AZUKI.get(), /** 7.4.1 **/
				Pantry_Blocks.BOX_H_CABBAGE.get(),
				Pantry_Blocks.BOX_H_HAKUSAI.get(),
				Pantry_Blocks.BOX_H_CITRUS.get(),
				Pantry_Blocks.BOX_H_CORN.get(),
				Pantry_Blocks.BOX_H_GREENONION.get(),
				Pantry_Blocks.BOX_H_GRAPE.get(),
				Pantry_Blocks.BOX_H_ONION.get(),
				Pantry_Blocks.BOX_H_ORIENTCLAM.get(),
				Pantry_Blocks.BOX_H_RICE.get(),
				Pantry_Blocks.BOX_H_SOY.get(),
				Pantry_Blocks.BOX_H_SPINACH.get(),
				Pantry_Blocks.BOX_H_SQUID.get(),
				Pantry_Blocks.BOX_H_TOMATO.get(),
				Pantry_Blocks.BOX_H_CHERRY.get(),
				Pantry_Blocks.BOX_H_TAKENOKO.get(),
				Pantry_Blocks.BOX_H_KURI.get(),
				Pantry_Blocks.BOX_H_TGREEN.get(),
				Pantry_Blocks.BOX_H_TRED.get(),
				Pantry_Blocks.BOX_H_BPEPPER.get(),
				Pantry_Blocks.BOX_H_CUMIN.get(),
				Pantry_Blocks.BOX_H_TURMERIC.get(),
				Pantry_Blocks.BOX_H_CHILI.get(),
				Dish_Blocks.TENGUSA_WASH.get() /** 8.1 **/);
		
		/* 235 15461355 */
		event.register((state, worldIn, pos, tint) -> { 
			return (tint == 1)? 15461355 : -1; },
				Chair_Blocks.SOFA_white.get(),
				Chair_Blocks.SOFA_orange.get(),
				Chair_Blocks.SOFA_magenta.get(),
				Chair_Blocks.SOFA_lightb.get(),
				Chair_Blocks.SOFA_yellow.get(),
				Chair_Blocks.SOFA_lime.get(),
				Chair_Blocks.SOFA_pink.get(),
				Chair_Blocks.SOFA_gray.get(),
				Chair_Blocks.SOFA_lightg.get(),
				Chair_Blocks.SOFA_cyan.get(),
				Chair_Blocks.SOFA_purple.get(),
				Chair_Blocks.SOFA_blue.get(),
				Chair_Blocks.SOFA_brown.get(),
				Chair_Blocks.SOFA_green.get(),
				Chair_Blocks.SOFA_red.get(),
				Chair_Blocks.SOFA_black.get(),
				
				JPChair_Blocks.ZABUTON_white.get() ,
				JPChair_Blocks.ZABUTON_orange.get() ,
				JPChair_Blocks.ZABUTON_magenta.get() ,
				JPChair_Blocks.ZABUTON_lightb.get() ,
				JPChair_Blocks.ZABUTON_yellow.get() ,
				JPChair_Blocks.ZABUTON_lime.get() ,
				JPChair_Blocks.ZABUTON_pink.get() ,
				JPChair_Blocks.ZABUTON_gray.get() ,
				JPChair_Blocks.ZABUTON_lightg.get() ,
				JPChair_Blocks.ZABUTON_cyan.get() ,
				JPChair_Blocks.ZABUTON_purple.get() ,
				JPChair_Blocks.ZABUTON_blue.get() ,
				JPChair_Blocks.ZABUTON_brown.get() ,
				JPChair_Blocks.ZABUTON_green.get() ,
				JPChair_Blocks.ZABUTON_red.get() ,
				JPChair_Blocks.ZABUTON_black.get() ,
				JPChair_Blocks.WARAZABUTON.get() ,
				JPChair_Blocks.ZAISU_white.get() ,
				JPChair_Blocks.ZAISU_orange.get() ,
				JPChair_Blocks.ZAISU_magenta.get() ,
				JPChair_Blocks.ZAISU_lightb.get() ,
				JPChair_Blocks.ZAISU_yellow.get() ,
				JPChair_Blocks.ZAISU_lime.get() ,
				JPChair_Blocks.ZAISU_pink.get() ,
				JPChair_Blocks.ZAISU_gray.get() ,
				JPChair_Blocks.ZAISU_lightg.get() ,
				JPChair_Blocks.ZAISU_cyan.get() ,
				JPChair_Blocks.ZAISU_purple.get() ,
				JPChair_Blocks.ZAISU_blue.get() ,
				JPChair_Blocks.ZAISU_brown.get() ,
				JPChair_Blocks.ZAISU_green.get() ,
				JPChair_Blocks.ZAISU_red.get() ,
				JPChair_Blocks.ZAISU_black.get() );
	}
	
	/* TintIndex of ItemBlock */
	@SubscribeEvent
	public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {

		/** 20 Water waterColor(4159204) from Biome=1, 35 CornSoup=2 **/
		event.register((stack, tint) -> {
			return (tint == 1)? 4159204 : ((tint == 2)? 16441700 : -1); },
				Items_Seasonal.SUIDEN.get(),
				Items_Seasonal.KURI_NABE.get(),

				Items_Teatime.NABEMISO_nama.get(),
				Items_Teatime.NABECORN_nama.get(),
				Items_Teatime.NABECORN.get(),
				Items_Teatime.CORNSOUP.get(),
				Items_Teatime.EGGBURGSET.get() );
		
		/** 21 Raw_Sake **/
		event.register((stack, tint) -> { return (tint == 1)? 12509680 : -1; },
				Items_Teatime.NAMASAKEGLASS.get() );
		
		/** 22 Sake **/
		event.register((stack, tint) -> { return (tint == 1)? 9223890 : -1; },
				Items_Teatime.SAKEGLASS.get() );
		
		/** 23 Aged_Sake **/
		event.register((stack, tint) -> { return (tint == 1)? 11164250 : -1; },
				Items_Teatime.JUKUSAKEGLASS.get() );
		
		/** 24 Cider **/
		event.register((stack, tint) -> { return (tint == 1)? 15127945 : -1; },
				Items_Teatime.CIDERGLASS.get() );
		
		/** 25 Aged_Cider **/
		event.register((stack, tint) -> { return (tint == 1)? 14588001 : -1; },
				Items_Teatime.JUKUCIDERGLASS.get() );
		
		/** 26 Wine **/
		event.register((stack, tint) -> { return (tint == 1)? 8398655 : -1; },
				Items_Teatime.WINEGLASS.get() );
		
		/** 27 Aged_Wine **/
		event.register((stack, tint) -> { return (tint == 1)? 5715258 : -1; },
				Items_Teatime.JUKUWINEGLASS.get() );
		
		/** 28 Mead **/
		event.register((stack, tint) -> { return (tint == 1)? 13821640 : -1; },
				Items_Teatime.MEADGLASS.get() );
		
		/** 29 Aged_Mead **/
		event.register((stack, tint) -> { return (tint == 1)? 13808770 : -1; },
				Items_Teatime.JUKUMEADGLASS.get() );
		
		/** 30 Green_Tea=1, 32 MisoSoup=2 **/
		event.register((stack, tint) -> { 
			return (tint == 1)? 16775010 : ((tint == 2)? 15772230 : -1); },
				Items_Teatime.KYUSU.get(),
				Items_Teatime.JPTEACUP.get(),
				Items_Teatime.JPTEASET.get(),
				Items_Teatime.SUSHISET_salmon.get(),
				Items_Teatime.SUSHISET_fish.get(),
				Items_Teatime.SUSHISET_beef.get(),
				Items_Teatime.SUSHISET_tamago.get(),
				Items_Teatime.SUSHISET_4shoku.get(),
				
				Items_Teatime.NABETORI.get(),
				Items_Teatime.NABEMISO.get(),
				Items_Teatime.TONSUITORI.get(),
				Items_Teatime.MISOSOUP.get(),
				
				Items_Teatime.TAMAGOYAKITEI.get(),
				Items_Teatime.YAKIZAKANATEI.get(),
				Items_Teatime.YAKIJYAKETEI.get(),
				Items_Teatime.TAMAGOYAKITEI_TAKE.get(),
				Items_Teatime.YAKIZAKANATEI_TAKE.get(),
				Items_Teatime.YAKIJYAKETEI_TAKE.get(),
				Items_Teatime.TAMAGOYAKITEI_KURI.get(),
				Items_Teatime.YAKIZAKANATEI_KURI.get(),
				Items_Teatime.YAKIJYAKETEI_KURI.get(),
				Items_Teatime.TAMAGOYAKITEI_SEKI.get(), /** 7.4.1 **/
				Items_Teatime.YAKIZAKANATEI_SEKI.get(), /** 7.4.1 **/
				Items_Teatime.YAKIJYAKETEI_SEKI.get() /** 7.4.1 **/ );
		
		/** 31 Black_Tea **/
		event.register((stack, tint) -> { return (tint == 1)? 14493736 : -1; },
				Items_Teatime.TEAPOT.get(),
				Items_Teatime.TEACUP.get(),
				Items_Teatime.TEASET.get() );

		/** 34 AMAZAKE=2 **/
		event.register((stack, tint) -> { return (tint == 2)? 16443100 : -1; },
				Items_Teatime.NABEAMAZAKE.get(),
				Items_Teatime.AMAZAKEGLASS.get() );
		
		/** 40 ENDEN=1, 41 ENDEN=2 **/
		event.register((stack, tint) -> { 
			return (tint == 1)? 6587090 : ((tint == 2)? 8560880 : -1); },
				Items_Teatime.ENDEN.get() );
		
		/** BAKED_APPLE 200,100,50
		event.register((stack, tint) -> { return 13132850; },
				Items_Teatime.BAKED_APPLE.get() ); to texture **/
		
		/** 1 Oak **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.get(0.7F, 0.6F) : -1; },
				Items_Wadeco.BONSAI_oak.get(),
				Items_Wadeco.KANYOU.get(),
				Items_Wadeco.IKEGAKILONG.get(),
				Items_Wadeco.IKEGAKI.get() );
		
		/** 2 Spruce **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.getEvergreenColor() : -1; },
				Items_Wadeco.BONSAI_spru.get(),
				Items_Wadeco.KANYOU_spruce.get(),
				Items_Wadeco.IKEGAKILONG_spruce.get(),
				Items_Wadeco.IKEGAKI_spruce.get() );

		/** 3 Birch **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.getBirchColor() : -1; },
				Items_Wadeco.BONSAI_bir.get(),
				Items_Wadeco.KANYOU_birch.get(),
				Items_Wadeco.IKEGAKILONG_birch.get(),
				Items_Wadeco.IKEGAKI_birch.get() );
		
		/** 4 Jungle **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.get(0.9F, 0.9F) : -1; }, //緑
				Items_Wadeco.BONSAI_jun.get(),
				Items_Wadeco.KANYOU_jungle.get(),
				Items_Wadeco.IKEGAKILONG_jungle.get(),
				Items_Wadeco.IKEGAKI_jungle.get() );
		
		/** 5 Acacia **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.get(0.9F, 0.0F) : -1; },
				Items_Wadeco.BONSAI_aca.get(),
				Items_Wadeco.KANYOU_acacia.get(),
				Items_Wadeco.IKEGAKILONG_acacia.get(),
				Items_Wadeco.IKEGAKI_acacia.get() );
		
		/** 6 DarkOak **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.get(0.7F, 0.8F) : -1; }, //黒
				Items_Wadeco.BONSAI_doak.get(),
				Items_Wadeco.KANYOU_darkoak.get(),
				Items_Wadeco.IKEGAKILONG_darkoak.get(),
				Items_Wadeco.IKEGAKI_darkoak.get() );
		
		/** MANGROVE **/
		event.register((stack, tint) -> {
			return (tint == 1)? FoliageColor.get(0.8F, 0.2F) : -1; },
				Items_Wadeco.BONSAI_mangrove.get(),
				Items_Wadeco.KANYOU_mangrove.get(),
				Items_Wadeco.IKEGAKILONG_mangrove.get(),
				Items_Wadeco.IKEGAKI_mangrove.get() );
		
		/** 7 KAEDE ---> Delete **/
		
		/** 8 Autumn_Oak **/
		event.register((stack, tint) -> {
			return (tint == 1)? 11170610 : -1; },
				Items_Seasonal.BONSAI_kare.get(),
				Items_Seasonal.KANYOU_kare.get(),
				Items_Seasonal.IKEGAKILONG_kare.get(),
				Items_Seasonal.IKEGAKI_kare.get(),
				Items_Seasonal.OAKKARE_leaf.get(),
				Items_Seasonal.OCHIBA_carpet.get() );
		
		/** Reizou 1=200,200,200 2=225,240,255**/
		event.register((stack, tint) -> {
			return (tint == 1)? 13158600 : ((tint == 2)? 14807295 : -1); },
				Items_Teatime.KIT_REIZOU.get() );
		
		/* 200, 100 */
		event.register((stack, tint) -> {
			return (tint == 1)? 13158600 : ((tint == 2)? 6579300 : -1); },
				Items_NoTab.BOX_H_FISH.get(),
				Items_Chinjufu.EMPTY_BOX.get(),
				Items_Chinjufu.AMMO_BOX.get(),
				Items_Chinjufu.BAUXITE_BOX.get(),
				
				Items_Teatime.BOX_H_EMPTY.get(),
				Items_Teatime.BOX_H_APPLE.get(),
				Items_Teatime.BOX_H_BEEF.get(),
				Items_Teatime.BOX_H_BEETROOT.get(),
				Items_Teatime.BOX_H_BREAD.get(),
				Items_Teatime.BOX_H_CARROT.get(),
				Items_Teatime.BOX_H_CHICKEN.get(),
				Items_Teatime.BOX_H_CHORUS.get(),
				Items_Teatime.BOX_H_COCO.get(),
				Items_Teatime.BOX_H_COD.get(),
				Items_Teatime.BOX_H_EGG.get(),
				Items_Teatime.BOX_H_FLOUR.get(),
				Items_Teatime.BOX_H_MUTTON.get(),
				Items_Teatime.BOX_H_PORK.get(),
				Items_Teatime.BOX_H_POTATO.get(),
				Items_Teatime.BOX_H_RABBIT.get(),
				Items_Teatime.BOX_H_SALMON.get(),
				Items_Teatime.BOX_H_SWBERRY.get(),
				
				Items_Teatime.BOX_H_AZUKI.get(), /** 7.4.1 -6 **/
				Items_Teatime.BOX_H_CABBAGE.get(),
				Items_Teatime.BOX_H_HAKUSAI.get(),
				Items_Teatime.BOX_H_CITRUS.get(),
				Items_Teatime.BOX_H_CORN.get(),
				Items_Teatime.BOX_H_GREENONION.get(),
				Items_Teatime.BOX_H_GRAPE.get(),
				Items_Teatime.BOX_H_ONION.get(),
				Items_Teatime.BOX_H_ORIENTCLAM.get(),
				Items_Teatime.BOX_H_RICE.get(),
				Items_Teatime.BOX_H_SOY.get(),
				Items_Teatime.BOX_H_SPINACH.get(),
				Items_Teatime.BOX_H_SQUID.get(),
				Items_Teatime.BOX_H_TOMATO.get(),
				Items_Teatime.BOX_H_CHERRY.get(),
				Items_Teatime.BOX_H_TAKENOKO.get(),
				Items_Teatime.BOX_H_KURI.get(),
				Items_Teatime.BOX_H_TGREEN.get(),
				Items_Teatime.BOX_H_TRED.get(),
				Items_Teatime.BOX_H_BPEPPER.get(),
				Items_Teatime.BOX_H_CUMIN.get(),
				Items_Teatime.BOX_H_TURMERIC.get(),
				Items_Teatime.BOX_H_CHILI.get(),
				
				Items_Teatime.CROP_TENGUSA.get(), /** 8.1 **/
				Items_Teatime.TENGUSA_WASH.get(), /** 8.1 **/
				Items_Teatime.TENGUSA_DRY.get() /** 8.1 **/ );
		
		/* 235 15461355 */
		event.register((stack, tint) -> {
			return (tint == 1)? 15461355 : -1; },
				Items_Chinjufu.SOFA_white.get() ,
				Items_Chinjufu.SOFA_orange.get() ,
				Items_Chinjufu.SOFA_magenta.get() ,
				Items_Chinjufu.SOFA_lightb.get() ,
				Items_Chinjufu.SOFA_yellow.get() ,
				Items_Chinjufu.SOFA_lime.get() ,
				Items_Chinjufu.SOFA_pink.get() ,
				Items_Chinjufu.SOFA_gray.get() ,
				Items_Chinjufu.SOFA_lightg.get() ,
				Items_Chinjufu.SOFA_cyan.get() ,
				Items_Chinjufu.SOFA_purple.get() ,
				Items_Chinjufu.SOFA_blue.get() ,
				Items_Chinjufu.SOFA_brown.get() ,
				Items_Chinjufu.SOFA_green.get() ,
				Items_Chinjufu.SOFA_red.get() ,
				Items_Chinjufu.SOFA_black.get() ,
				
				Items_Wadeco.ZABUTON_white.get() ,
				Items_Wadeco.ZABUTON_orange.get() ,
				Items_Wadeco.ZABUTON_magenta.get() ,
				Items_Wadeco.ZABUTON_lightb.get() ,
				Items_Wadeco.ZABUTON_yellow.get() ,
				Items_Wadeco.ZABUTON_lime.get() ,
				Items_Wadeco.ZABUTON_pink.get() ,
				Items_Wadeco.ZABUTON_gray.get() ,
				Items_Wadeco.ZABUTON_lightg.get() ,
				Items_Wadeco.ZABUTON_cyan.get() ,
				Items_Wadeco.ZABUTON_purple.get() ,
				Items_Wadeco.ZABUTON_blue.get() ,
				Items_Wadeco.ZABUTON_brown.get() ,
				Items_Wadeco.ZABUTON_green.get() ,
				Items_Wadeco.ZABUTON_red.get() ,
				Items_Wadeco.ZABUTON_black.get() ,
				Items_Wadeco.WARAZABUTON.get() ,
				Items_Wadeco.ZAISU_white.get() ,
				Items_Wadeco.ZAISU_orange.get() ,
				Items_Wadeco.ZAISU_magenta.get() ,
				Items_Wadeco.ZAISU_lightb.get() ,
				Items_Wadeco.ZAISU_yellow.get() ,
				Items_Wadeco.ZAISU_lime.get() ,
				Items_Wadeco.ZAISU_pink.get() ,
				Items_Wadeco.ZAISU_gray.get() ,
				Items_Wadeco.ZAISU_lightg.get() ,
				Items_Wadeco.ZAISU_cyan.get() ,
				Items_Wadeco.ZAISU_purple.get() ,
				Items_Wadeco.ZAISU_blue.get() ,
				Items_Wadeco.ZAISU_brown.get() ,
				Items_Wadeco.ZAISU_green.get() ,
				Items_Wadeco.ZAISU_red.get() ,
				Items_Wadeco.ZAISU_black.get() );
	}
}
