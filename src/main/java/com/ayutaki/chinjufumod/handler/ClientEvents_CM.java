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
import com.ayutaki.chinjufumod.items.armor.Armor_Akatsuki;
import com.ayutaki.chinjufumod.items.armor.Armor_AkatsukiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Battleship;
import com.ayutaki.chinjufumod.items.armor.Armor_BattleshipKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Carrier;
import com.ayutaki.chinjufumod.items.armor.Armor_CarrierKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Destroyer;
import com.ayutaki.chinjufumod.items.armor.Armor_DestroyerKai;
import com.ayutaki.chinjufumod.items.armor.Armor_I13;
import com.ayutaki.chinjufumod.items.armor.Armor_I168;
import com.ayutaki.chinjufumod.items.armor.Armor_I401;
import com.ayutaki.chinjufumod.items.armor.Armor_Ise;
import com.ayutaki.chinjufumod.items.armor.Armor_IseKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Kasumi;
import com.ayutaki.chinjufumod.items.armor.Armor_KasumiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Mogami;
import com.ayutaki.chinjufumod.items.armor.Armor_MogamiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Nagato;
import com.ayutaki.chinjufumod.items.armor.Armor_NagatoKai;
import com.ayutaki.chinjufumod.items.armor.Armor_RJ;
import com.ayutaki.chinjufumod.items.armor.Armor_RJKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Ro500;
import com.ayutaki.chinjufumod.items.armor.Armor_Sendai;
import com.ayutaki.chinjufumod.items.armor.Armor_SendaiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Tone;
import com.ayutaki.chinjufumod.items.armor.Armor_ToneKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Yura;
import com.ayutaki.chinjufumod.items.armor.Armor_YuraKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Zuihou;
import com.ayutaki.chinjufumod.items.armor.Armor_ZuihouKai;
import com.ayutaki.chinjufumod.items.armor.Costume_Santa;
import com.ayutaki.chinjufumod.items.armor.Costume_YUKATA;
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
import com.ayutaki.chinjufumod.registry.Items_Armor;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.JPChair_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;
import com.ayutaki.chinjufumod.tileentity.render.BlackBoard_TERenderer;
import com.ayutaki.chinjufumod.tileentity.render.WoodBoard_TERenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = ChinjufuMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvents_CM {

	/* RenderTypes -> .json "render_type": "cutout", "render_type": "cutout_mipped", "render_type": "translucent", */
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) 	{
		ChinjufuMod.LOGGER.info("HELLO FROM CLIENT SETUP");
		ChinjufuMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
	}
	
	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(MenuTypes_CM.NOTE_MENU.get(), NoteScreen::new);
		event.register(MenuTypes_CM.REIZOU_MENU.get(), ReizouScreen::new);
		event.register(MenuTypes_CM.REIZOUTOP_MENU.get(), ReizouTopScreen::new);
		event.register(MenuTypes_CM.TANSU_MENU.get(), TansuScreen::new);
	}
	
	@SubscribeEvent
	public static void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BlockEntity_CM.BLACKBOARD.get(), BlackBoard_TERenderer::new);
		event.registerBlockEntityRenderer(BlockEntity_CM.WOODBOARD.get(), WoodBoard_TERenderer::new);
	}
	
	/* EntityRender_CM */
	@SubscribeEvent
	public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EntityTypes_CM.SITABLE.get(), SitableRenderer::new);
		
		event.registerEntityRenderer(EntityTypes_CM.AMMO_L.get(), RenderAmmo_Large::new);
		event.registerEntityRenderer(EntityTypes_CM.AMMO_M.get(), RenderAmmo_Medium::new);
		event.registerEntityRenderer(EntityTypes_CM.AMMO_S.get(), RenderAmmo_Small::new);
		event.registerEntityRenderer(EntityTypes_CM.AMMO_K.get(), RenderAmmo_Kijyuu::new);
		
		event.registerEntityRenderer(EntityTypes_CM.TYPE97.get(), renderManager -> new RenderKK_Type97(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.TENZAN.get(), renderManager -> new RenderKK_Tenzan(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.RYUSEI.get(), renderManager -> new RenderKK_Ryusei(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.TBF.get(), renderManager -> new RenderKK_TBF(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.SWORDFISH.get(), renderManager -> new RenderKK_Swordfish(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.BARRACUDA.get(), renderManager -> new RenderKK_Barracuda(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.MOSQUITO.get(), renderManager -> new RenderKK_Mosquito(renderManager));
		
		event.registerEntityRenderer(EntityTypes_CM.TYPE99.get(), renderManager -> new RenderKB_Type99(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.SUISEI.get(), renderManager -> new RenderKB_Suisei(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.TYPEZERO.get(), renderManager -> new RenderKB_TypeZero(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.JU87.get(), renderManager -> new RenderKB_Ju87(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.RE2001.get(), renderManager -> new RenderKB_Re2001(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.SBD.get(), renderManager -> new RenderKB_SBD(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.F4U.get(), renderManager -> new RenderKB_F4U(renderManager));

		event.registerEntityRenderer(EntityTypes_CM.ZUIUN.get(), renderManager -> new RenderSB_Zuiun(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.SEIRAN.get(), renderManager -> new RenderSB_Seiran(renderManager));
		event.registerEntityRenderer(EntityTypes_CM.GYORAI61.get(), renderManager -> new RenderGyorai61cm<>(renderManager));
		
		event.registerEntityRenderer(EntityTypes_CM.TOAMI.get(), renderManager -> new ToamiRenderer<>(renderManager));
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
		/** 1 Oak (183,74,232)12012264**/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.get(0.7F, 0.6F) : -1; },
				Garden_Blocks.BONSAI_oak.get(),
				Garden_Blocks.KANYOU.get(),
				Garden_Blocks.IKEGAKILONG.get(),
				Garden_Blocks.IKEGAKI.get() );
		
		/** 2 Spruce **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.FOLIAGE_EVERGREEN : -1; },
				Garden_Blocks.BONSAI_spru.get(),
				Garden_Blocks.KANYOU_spruce.get(),
				Garden_Blocks.IKEGAKILONG_spruce.get(),
				Garden_Blocks.IKEGAKI_spruce.get() );

		/** 3 Birch **/
		event.register((state, worldIn, pos, tint) -> {
			return (tint == 1)? FoliageColor.FOLIAGE_BIRCH : -1; },
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

	/* TintIndex of ItemBlock -> items/moditem.json
	 * ,"tints": [ index_0, index_1, index_2 ... ]
	 * ,"tints": [ { "type": "minecraft:constant", "value": -1 }, { "type": "minecraft:constant", "value": 14493736 } ]
	@SubscribeEvent
	public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {} */
	
	/* ArmorRender_CM */
	@SubscribeEvent
	public static void registerEntityRenderingHandler(EntityRenderersEvent.RegisterLayerDefinitions event) {
		CubeDeformation inner = LayerDefinitions.INNER_ARMOR_DEFORMATION;
		CubeDeformation outer = LayerDefinitions.OUTER_ARMOR_DEFORMATION;
		
		event.registerLayerDefinition(ArmorLayer_CM.GISOU_INNER, () -> LayerDefinition.create(GisouModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.GISOU_OUTER, () -> LayerDefinition.create(GisouModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.AKATSUKI_INNER, () -> LayerDefinition.create(AkatsukiModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.AKATSUKI_OUTER, () -> LayerDefinition.create(AkatsukiModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.KASUMI_OUTER, () -> LayerDefinition.create(KasumiOuter.createOuter(outer), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SENDAI_INNER, () -> LayerDefinition.create(SendaiModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SENDAI_OUTER, () -> LayerDefinition.create(SendaiModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YURA_INNER, () -> LayerDefinition.create(YuraModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YURA_OUTER, () -> LayerDefinition.create(YuraModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.TONE_INNER, () -> LayerDefinition.create(ToneModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.TONE_OUTER, () -> LayerDefinition.create(ToneModel.createOuter(outer), 64, 120));

		event.registerLayerDefinition(ArmorLayer_CM.RJ_INNER, () -> LayerDefinition.create(RJModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_OUTER, () -> LayerDefinition.create(RJModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ZUIHOU_INNER, () -> LayerDefinition.create(ZuihouModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ZUIHOU_OUTER, () -> LayerDefinition.create(ZuihouModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.IKKOU_INNER, () -> LayerDefinition.create(IkkousenModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.IKKOU_OUTER, () -> LayerDefinition.create(IkkousenModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_INNER, () -> LayerDefinition.create(RJModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_OUTER, () -> LayerDefinition.create(RJModel.createOuter(outer), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SENKAN_INNER, () -> LayerDefinition.create(BattleshipModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SENKAN_OUTER, () -> LayerDefinition.create(BattleshipModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ISE_INNER, () -> LayerDefinition.create(Ise_Model.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ISE_OUTER, () -> LayerDefinition.create(Ise_Model.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.NAGATO_INNER, () -> LayerDefinition.create(NagatoModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.NAGATO_OUTER, () -> LayerDefinition.create(NagatoModel.createOuter(outer), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SUBMARINE_INNER, () -> LayerDefinition.create(SubmarineModel.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SUBMARINE_OUTER, () -> LayerDefinition.create(SubmarineModel.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.UKIWA_INNER, () -> LayerDefinition.create(UKIWA_Model.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.I401_INNER, () -> LayerDefinition.create(I401_Model.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.I401_OUTER, () -> LayerDefinition.create(I401_Model.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RO500_OUTER, () -> LayerDefinition.create(Ro500_Outer.createOuter(outer), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RO500_OUTER_C, () -> LayerDefinition.create(UKIWA_Model.ro500_OuterC(outer), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.YUKATA_INNER, () -> LayerDefinition.create(YUKATA_Model.createInner(inner), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YUKATA_OUTER, () -> LayerDefinition.create(YUKATA_Model.createOuter(outer), 64, 120));		
		event.registerLayerDefinition(ArmorLayer_CM.SANTA_INNER, () -> LayerDefinition.create(Santa_Model.createInner(inner), 64, 32));
		event.registerLayerDefinition(ArmorLayer_CM.SANTA_OUTER, () -> LayerDefinition.create(Santa_Model.createOuter(outer), 64, 32));
	}
	
	@SubscribeEvent
	public static void registerClientExtend(RegisterClientExtensionsEvent event) {
		/* YUKATA */
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.YKTD_GETA.get(), Items_Seasonal.IKADUCHIYKT_HELMET.get(), Items_Seasonal.IKADUCHIYKT_CHESTPLATE.get(), Items_Seasonal.IKADUCHIYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.INADUMAYKT_HELMET.get(), Items_Seasonal.INADUMAYKT_CHESTPLATE.get(), Items_Seasonal.INADUMAYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.HAMAKAZEYKT_HELMET.get(), Items_Seasonal.HAMAKAZEYKT_CHESTPLATE.get(), Items_Seasonal.HAMAKAZEYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.URAKAZEYKT_HELMET.get(), Items_Seasonal.URAKAZEYKT_CHESTPLATE.get(), Items_Seasonal.URAKAZEYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.KAWAKAZEYKT_HELMET.get(), Items_Seasonal.KAWAKAZEYKT_CHESTPLATE.get(), Items_Seasonal.KAWAKAZEYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.OBOROYKT_HELMET.get(), Items_Seasonal.OBOROYKT_CHESTPLATE.get(), Items_Seasonal.OBOROYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.YKTO_GETA.get(), Items_Seasonal.TTOKUYKT_CHESTPLATE.get(), Items_Seasonal.TTOKUYKT_LEGGINGS.get());
		event.registerItem(Costume_YUKATA.ArmorRender.INSTANCE, Items_Seasonal.TTOKUYKTB_CHESTPLATE.get(), Items_Seasonal.TTOKUYKTB_LEGGINGS.get());

		/* Santa */
		event.registerItem(Costume_Santa.ArmorRender.INSTANCE, Items_Seasonal.AKASHISANTA_BOOTS.get(), Items_Seasonal.AKASHISANTA_HELMET.get(), Items_Seasonal.AKASHISANTA_CHESTPLATE.get(), Items_Seasonal.AKASHISANTA_LEGGINGS.get());
		event.registerItem(Costume_Santa.ArmorRender.INSTANCE, Items_Seasonal.SUZUYASANTA_BOOTS.get(), Items_Seasonal.SUZUYASANTA_HELMET.get(), Items_Seasonal.SUZUYASANTA_CHESTPLATE.get(), Items_Seasonal.SUZUYASANTA_LEGGINGS.get());
		event.registerItem(Costume_Santa.ArmorRender.INSTANCE, Items_Seasonal.KUMANOSANTA_BOOTS.get(), Items_Seasonal.KUMANOSANTA_HELMET.get(), Items_Seasonal.KUMANOSANTA_CHESTPLATE.get(), Items_Seasonal.KUMANOSANTA_LEGGINGS.get());
		event.registerItem(Costume_Santa.ArmorRender.INSTANCE, Items_Seasonal.RJ_SANTA_BOOTS.get(), Items_Seasonal.RJ_SANTA_HELMET.get(), Items_Seasonal.RJ_SANTA_CHESTPLATE.get(), Items_Seasonal.RJ_SANTA_LEGGINGS.get());
		event.registerItem(Costume_Santa.ArmorRender.INSTANCE, Items_Seasonal.TEITOKUSANTA_BOOTS.get(), Items_Seasonal.TEITOKUSANTA_HELMET.get(), Items_Seasonal.TEITOKUSANTA_CHESTPLATE.get(), Items_Seasonal.TEITOKUSANTA_LEGGINGS.get());

		/* Destroyer */
		event.registerItem(Armor_Destroyer.ArmorRender.INSTANCE, Items_Armor.FUBUKI_BOOTS.get(), Items_Armor.FUBUKI_HELMET.get(), Items_Armor.FUBUKI_CHESTPLATE.get(), Items_Armor.FUBUKI_LEGGINGS.get());
		event.registerItem(Armor_DestroyerKai.ArmorRender.INSTANCE, Items_Armor.FUBUKI_BOOTS_KAI.get());
		event.registerItem(Armor_Kasumi.ArmorRender.INSTANCE, Items_Armor.KASUMI_BOOTS.get(), Items_Armor.KASUMI_HELMET.get(), Items_Armor.KASUMI_CHESTPLATE.get(), Items_Armor.KASUMI_LEGGINGS.get());
		event.registerItem(Armor_KasumiKai.ArmorRender.INSTANCE, Items_Armor.KASUMI_BOOTS_KAI.get());
		event.registerItem(Armor_Destroyer.ArmorRender.INSTANCE, Items_Armor.SHIRATSUYU_BOOTS.get(), Items_Armor.SHIRATSUYU_HELMET.get(), Items_Armor.SHIRATSUYU_CHESTPLATE.get(), Items_Armor.SHIRATSUYU_LEGGINGS.get());
		event.registerItem(Armor_DestroyerKai.ArmorRender.INSTANCE, Items_Armor.SHIRATSUYU_BOOTS_KAI.get());
		event.registerItem(Armor_Destroyer.ArmorRender.INSTANCE, Items_Armor.SHIGURE_BOOTS.get(), Items_Armor.SHIGURE_HELMET.get(), Items_Armor.SHIGURE_CHESTPLATE.get(), Items_Armor.SHIGURE_LEGGINGS.get());
		event.registerItem(Armor_DestroyerKai.ArmorRender.INSTANCE, Items_Armor.SHIGURE_BOOTS_KAI.get());
		event.registerItem(Armor_Akatsuki.ArmorRender.INSTANCE, Items_Armor.AKATSUKI_BOOTS.get(), Items_Armor.AKATSUKI_HELMET.get(), Items_Armor.AKATSUKI_CHESTPLATE.get(), Items_Armor.AKATSUKI_LEGGINGS.get());
		event.registerItem(Armor_AkatsukiKai.ArmorRender.INSTANCE, Items_Armor.AKATSUKI_BOOTS_KAI.get());
		
		/* Cruiser */
		event.registerItem(Armor_Sendai.ArmorRender.INSTANCE, Items_Armor.SENDAI_BOOTS.get(), Items_Armor.SENDAI_HELMET.get(), Items_Armor.SENDAI_CHESTPLATE.get(), Items_Armor.SENDAI_LEGGINGS.get());
		event.registerItem(Armor_SendaiKai.ArmorRender.INSTANCE, Items_Armor.SENDAI_BOOTS_KAI.get());
		event.registerItem(Armor_Yura.ArmorRender.INSTANCE, Items_Armor.YURA_BOOTS.get(), Items_Armor.YURA_HELMET.get(), Items_Armor.YURA_CHESTPLATE.get(), Items_Armor.YURA_LEGGINGS.get());
		event.registerItem(Armor_YuraKai.ArmorRender.INSTANCE, Items_Armor.YURA_BOOTS_KAI.get());
		event.registerItem(Armor_Mogami.ArmorRender.INSTANCE, Items_Armor.MOGAMI_BOOTS.get(), Items_Armor.MOGAMI_HELMET.get(), Items_Armor.MOGAMI_CHESTPLATE.get(), Items_Armor.MOGAMI_LEGGINGS.get());
		event.registerItem(Armor_MogamiKai.ArmorRender.INSTANCE, Items_Armor.MOGAMI_BOOTS_KAI.get());
		event.registerItem(Armor_Tone.ArmorRender.INSTANCE, Items_Armor.TONE_BOOTS.get(), Items_Armor.TONE_HELMET.get(), Items_Armor.TONE_CHESTPLATE.get(), Items_Armor.TONE_LEGGINGS.get());
		event.registerItem(Armor_ToneKai.ArmorRender.INSTANCE, Items_Armor.TONE_BOOTS_KAI.get());
		
		/* Aircraft carrier */
		event.registerItem(Armor_RJ.ArmorRender.INSTANCE, Items_Armor.RJ_BOOTS.get(), Items_Armor.RJ_HELMET.get(), Items_Armor.RJ_CHESTPLATE.get(), Items_Armor.RJ_LEGGINGS.get());
		event.registerItem(Armor_RJKai.ArmorRender.INSTANCE, Items_Armor.RJ_BOOTS_KAI.get());
		event.registerItem(Armor_Zuihou.ArmorRender.INSTANCE, Items_Armor.ZUIHOU_BOOTS.get(), Items_Armor.ZUIHOU_HELMET.get(), Items_Armor.ZUIHOU_CHESTPLATE.get(), Items_Armor.ZUIHOU_LEGGINGS.get());
		event.registerItem(Armor_ZuihouKai.ArmorRender.INSTANCE, Items_Armor.ZUIHOU_BOOTS_KAI.get());
		event.registerItem(Armor_Carrier.ArmorRender.INSTANCE, Items_Armor.AKAGI_BOOTS.get(), Items_Armor.AKAGI_HELMET.get(), Items_Armor.AKAGI_CHESTPLATE.get(), Items_Armor.AKAGI_LEGGINGS.get());
		event.registerItem(Armor_CarrierKai.ArmorRender.INSTANCE, Items_Armor.AKAGI_BOOTS_KAI.get());
		event.registerItem(Armor_Carrier.ArmorRender.INSTANCE, Items_Armor.KAGA_BOOTS.get(), Items_Armor.KAGA_HELMET.get(), Items_Armor.KAGA_CHESTPLATE.get(), Items_Armor.KAGA_LEGGINGS.get());
		event.registerItem(Armor_CarrierKai.ArmorRender.INSTANCE, Items_Armor.KAGA_BOOTS_KAI.get());
		
		/* Battleship */
		event.registerItem(Armor_Battleship.ArmorRender.INSTANCE, Items_Armor.KONGOU_BOOTS.get(), Items_Armor.KONGOU_HELMET.get(), Items_Armor.KONGOU_CHESTPLATE.get(), Items_Armor.KONGOU_LEGGINGS.get());
		event.registerItem(Armor_BattleshipKai.ArmorRender.INSTANCE, Items_Armor.KONGOU_BOOTS_KAI.get());
		event.registerItem(Armor_Battleship.ArmorRender.INSTANCE, Items_Armor.FUSOU_BOOTS.get(), Items_Armor.FUSOU_HELMET.get(), Items_Armor.FUSOU_CHESTPLATE.get(), Items_Armor.FUSOU_LEGGINGS.get());
		event.registerItem(Armor_BattleshipKai.ArmorRender.INSTANCE, Items_Armor.FUSOU_BOOTS_KAI.get());
		event.registerItem(Armor_Ise.ArmorRender.INSTANCE, Items_Armor.ISE_BOOTS.get(), Items_Armor.ISE_HELMET.get(), Items_Armor.ISE_CHESTPLATE.get(), Items_Armor.ISE_LEGGINGS.get());
		event.registerItem(Armor_IseKai.ArmorRender.INSTANCE, Items_Armor.ISE_BOOTS_KAI.get());
		event.registerItem(Armor_Nagato.ArmorRender.INSTANCE, Items_Armor.NAGATO_BOOTS.get(), Items_Armor.NAGATO_HELMET.get(), Items_Armor.NAGATO_CHESTPLATE.get(), Items_Armor.NAGATO_LEGGINGS.get());
		event.registerItem(Armor_NagatoKai.ArmorRender.INSTANCE, Items_Armor.NAGATO_BOOTS_KAI.get());
		
		/* Submarine */
		event.registerItem(Armor_I168.ArmorRender.INSTANCE, Items_Armor.I168_BOOTS.get(), Items_Armor.I168_HELMET.get(), Items_Armor.I168_CHESTPLATE.get(), Items_Armor.I168_LEGGINGS.get());
		event.registerItem(Armor_I401.ArmorRender.INSTANCE, Items_Armor.I401_BOOTS.get(), Items_Armor.I401_HELMET.get(), Items_Armor.I401_CHESTPLATE.get(), Items_Armor.I401_LEGGINGS.get());
		event.registerItem(Armor_I13.ArmorRender.INSTANCE, Items_Armor.I13_BOOTS.get(), Items_Armor.I13_HELMET.get(), Items_Armor.I13_CHESTPLATE.get(), Items_Armor.I13_LEGGINGS.get());
		event.registerItem(Armor_Ro500.ArmorRender.INSTANCE, Items_Armor.RO500_BOOTS.get(), Items_Armor.RO500_HELMET.get(), Items_Armor.RO500_CHESTPLATE.get(), Items_Armor.RO500_LEGGINGS.get());
	}
}
