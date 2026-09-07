package com.ayutaki.chinjufumod;

import java.util.Locale;

import org.slf4j.Logger;

import com.ayutaki.chinjufumod.event.LeftClickEvent_CM;
import com.ayutaki.chinjufumod.event.RightClickEvent_CM;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.EntityTypes_CM;
import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.network.OpenUI_BlackBoard;
import com.ayutaki.chinjufumod.network.OpenUI_MirrorScreen;
import com.ayutaki.chinjufumod.network.OpenUI_Sign;
import com.ayutaki.chinjufumod.network.OpenUI_WoodBoard;
import com.ayutaki.chinjufumod.network.Server_BlackBoardSend;
import com.ayutaki.chinjufumod.network.Server_CleanEraser;
import com.ayutaki.chinjufumod.network.Server_GenEraser;
import com.ayutaki.chinjufumod.network.Server_SignSend;
import com.ayutaki.chinjufumod.network.Server_WoodBoardSend;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeBookCategory;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeSerializers;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeType;
import com.ayutaki.chinjufumod.registry.Chair_Blocks;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Gate_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Harbor_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Armor;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_NoTabLater;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;
import com.ayutaki.chinjufumod.registry.Items_Weapon;
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
import com.ayutaki.chinjufumod.world.biome.SurfaceRule_CM;
import com.ayutaki.chinjufumod.world.biome.TBProviderIchoh_CM;
import com.ayutaki.chinjufumod.world.biome.TBProviderKaede_CM;
import com.ayutaki.chinjufumod.world.biome.TBProviderSakura_CM;
import com.ayutaki.chinjufumod.world.features.Features_CM;
import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ChinjufuMod.MOD_ID)
public class ChinjufuMod {

	public static final String MOD_ID = "chinjufumod";
	public static final Logger LOGGER = LogUtils.getLogger();

	/** use it in registry **/
	public static ResourceLocation id(String name) { 
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name.toLowerCase(Locale.ROOT)); }

	// The constructor for the mod class is the first code that is run when your mod is loaded.
	// FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
	public ChinjufuMod(IEventBus eventBus, ModContainer container) {
		container.registerConfig(ModConfig.Type.COMMON, Config_CM.SPEC, MOD_ID + "_21.4.toml");
		container.registerConfig(ModConfig.Type.CLIENT, ConfigClient_CM.SPEC, MOD_ID + "_21.4_client.toml");
		
		EntityTypes_CM.ENTITY_TYPES.register(eventBus);
		BlockEntity_CM.BLOCK_ENTITIES.register(eventBus);
		MenuTypes_CM.MENU_TYPES.register(eventBus);
		ParticleTypes_CM.PARTICLE_TYPES.register(eventBus);
		SoundEvents_CM.SOUNDS.register(eventBus);
		
		CM_RecipeBookCategory.RECIPE_CATEGORY.register(eventBus);
		CM_RecipeSerializers.SERIALIZERS.register(eventBus);
		CM_RecipeType.TYPES.register(eventBus);
		
		/** register Blocks **/
		Chair_Blocks.BLOCKS.register(eventBus);
		Chinjufu_Blocks.BLOCKS.register(eventBus);
		Crop_Blocks.BLOCKS.register(eventBus);
		Dish_Blocks.BLOCKS.register(eventBus);
		Furniture_Blocks.BLOCKS.register(eventBus);
		Garden_Blocks.BLOCKS.register(eventBus);
		Gate_Blocks.BLOCKS.register(eventBus);
		Hakkou_Blocks.BLOCKS.register(eventBus);
		Harbor_Blocks.BLOCKS.register(eventBus);
		JPChair_Blocks.BLOCKS.register(eventBus);
		JPDeco_Blocks.BLOCKS.register(eventBus);
		JP_Blocks.BLOCKS.register(eventBus);
		KamoiPlanks_Blocks.BLOCKS.register(eventBus);
		KamoiPlaster_Blocks.BLOCKS.register(eventBus);
		Kitchen_Blocks.BLOCKS.register(eventBus);
		Pantry_Blocks.BLOCKS.register(eventBus);
		Ranma_Blocks.BLOCKS.register(eventBus);
		School_Blocks.BLOCKS.register(eventBus);
		Seasonal_Blocks.BLOCKS.register(eventBus);
		Slidedoor_Blocks.BLOCKS.register(eventBus);
		Unit_Blocks.BLOCKS.register(eventBus);
		WallPanel_Blocks.BLOCKS.register(eventBus);
		Window_Blocks.BLOCKS.register(eventBus);
		Wood_Blocks.BLOCKS.register(eventBus);

		/** register Features **/
		Features_CM.FEATURES.register(eventBus);
		
		/** register Items **/
		Items_NoTab.ITEMS.register(eventBus);
		Items_Chinjufu.ITEMS.register(eventBus);
		Items_Teatime.ITEMS.register(eventBus);
		Items_Seasonal.ITEMS.register(eventBus);
		Items_Armor.ITEMS.register(eventBus);
		Items_Weapon.ITEMS.register(eventBus);
		Items_Wadeco.ITEMS.register(eventBus);
		Items_Wablock.ITEMS.register(eventBus);
		Items_WallPanel.ITEMS.register(eventBus);
		Items_NoTabLater.ITEMS.register(eventBus);
		
		ItemGroups_CM.TABS.register(eventBus);

		NeoForge.EVENT_BUS.register(this);
		eventBus.addListener(this::commonSetup);
		eventBus.addListener(this::setupPackets);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM COMMON SETUP");
		
		event.enqueueWork(() -> {
			/** Add Biome by terrablender.api **/
			if (Config_CM.INSTANCE.sakuraBiomeGene.get()) {
				Regions.register(new TBProviderSakura_CM(ChinjufuMod.id("overworld_sakura"), Config_CM.INSTANCE.sakuraBiomeChance.get())); }
			
			if (Config_CM.INSTANCE.kaedeBiomeGene.get()) {
				Regions.register(new TBProviderKaede_CM(ChinjufuMod.id("overworld_kaede"), Config_CM.INSTANCE.kaedeBiomeChance.get())); }
			
			if (Config_CM.INSTANCE.ichohBiomeGene.get()) {
				Regions.register(new TBProviderIchoh_CM(ChinjufuMod.id("overworld_ichoh"), Config_CM.INSTANCE.ichohBiomeChance.get())); }

			/** Change Surface by terrablender.api **/
			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, SurfaceRule_CM.makeRules());
		});
		
		/** etc **/
		NeoForge.EVENT_BUS.register(new RightClickEvent_CM());
		NeoForge.EVENT_BUS.register(new LeftClickEvent_CM());
	}
	
	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		LOGGER.info("HELLO from server starting");
	}
	
	public void setupPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MOD_ID);
		
		registrar.playToClient(OpenUI_BlackBoard.TYPE, OpenUI_BlackBoard.CODEC, OpenUI_BlackBoard::handle);
		registrar.playToServer(Server_BlackBoardSend.TYPE, Server_BlackBoardSend.CODEC, Server_BlackBoardSend::handle);
		registrar.playToClient(OpenUI_WoodBoard.TYPE, OpenUI_WoodBoard.CODEC, OpenUI_WoodBoard::handle);
		registrar.playToServer(Server_WoodBoardSend.TYPE, Server_WoodBoardSend.CODEC, Server_WoodBoardSend::handle);
		registrar.playToServer(Server_CleanEraser.TYPE, Server_CleanEraser.CODEC, Server_CleanEraser::handle);
		registrar.playToServer(Server_GenEraser.TYPE, Server_GenEraser.CODEC, Server_GenEraser::handle);
		
		registrar.playToClient(OpenUI_MirrorScreen.TYPE, OpenUI_MirrorScreen.CODEC, OpenUI_MirrorScreen::handle);
		
		registrar.playToClient(OpenUI_Sign.TYPE, OpenUI_Sign.CODEC, OpenUI_Sign::handle);
		registrar.playToServer(Server_SignSend.TYPE, Server_SignSend.CODEC, Server_SignSend::handle);
	}
}
