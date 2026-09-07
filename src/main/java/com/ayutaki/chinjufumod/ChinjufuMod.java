package com.ayutaki.chinjufumod;

import org.slf4j.Logger;

import com.ayutaki.chinjufumod.event.LeftClickEvent_CM;
import com.ayutaki.chinjufumod.event.RightClickEvent_CM;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.CompostableItems_CM;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChinjufuMod.MOD_ID)
public class ChinjufuMod {
	
	// Define mod id in a common place for everything to reference
	public static final String MOD_ID = "chinjufumod";
	// Directly reference a slf4j logger
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final int NETWORK_VERSION = 1;
	@SuppressWarnings("removal")
	public static final SimpleChannel CHANNEL = ChannelBuilder.named(new ResourceLocation(MOD_ID, "net"))
			.networkProtocolVersion(NETWORK_VERSION)
			.clientAcceptedVersions(Channel.VersionTest.exact(NETWORK_VERSION))
			.serverAcceptedVersions(Channel.VersionTest.exact(NETWORK_VERSION))
			.simpleChannel();

	@SuppressWarnings("removal")
	public ChinjufuMod() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config_CM.SPEC, MOD_ID + "_20.6.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigClient_CM.SPEC, MOD_ID + "_20.6_client.toml");
		
		EntityTypes_CM.ENTITY_TYPES.register(eventBus);
		BlockEntity_CM.BLOCK_ENTITIES.register(eventBus);
		MenuTypes_CM.MENU_TYPES.register(eventBus);
		ParticleTypes_CM.PARTICLE_TYPES.register(eventBus);
		SoundEvents_CM.SOUNDS.register(eventBus);

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
		
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		this.network();
		eventBus.addListener(this::commonSetup);
		//eventBus.addListener(this::gatherData);
	}
	
	/* This will do the build after 'runData.' */
	@SuppressWarnings("removal")
	private void commonSetup(final FMLCommonSetupEvent event) {
		LOGGER.info("HELLO FROM COMMON SETUP");
		
		event.enqueueWork(() -> {
			/** Add Biome by terrablender.api **/
			if (Config_CM.INSTANCE.sakuraBiomeGene.get()) {
				Regions.register(new TBProviderSakura_CM(new ResourceLocation(MOD_ID, "overworld_sakura"), Config_CM.INSTANCE.sakuraBiomeChance.get())); }
			
			if (Config_CM.INSTANCE.kaedeBiomeGene.get()) {
				Regions.register(new TBProviderKaede_CM(new ResourceLocation(MOD_ID, "overworld_kaede"), Config_CM.INSTANCE.kaedeBiomeChance.get())); }
			
			if (Config_CM.INSTANCE.ichohBiomeGene.get()) {
				Regions.register(new TBProviderIchoh_CM(new ResourceLocation(MOD_ID, "overworld_ichoh"), Config_CM.INSTANCE.ichohBiomeChance.get())); }

			/** Change Surface by terrablender.api **/
			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, SurfaceRule_CM.makeRules());
		});
		
		/** etc **/
		CompostableItems_CM.register();
		MinecraftForge.EVENT_BUS.register(new RightClickEvent_CM());
		MinecraftForge.EVENT_BUS.register(new LeftClickEvent_CM());
	}
	
	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	private void network() {
		int id = 0;
		CHANNEL.messageBuilder(OpenUI_BlackBoard.class, id++).codec(OpenUI_BlackBoard.CODEC).consumerMainThread(OpenUI_BlackBoard::handle).add();
		CHANNEL.messageBuilder(Server_BlackBoardSend.class, id++).codec(Server_BlackBoardSend.CODEC).consumerMainThread(Server_BlackBoardSend::handle).add();
		CHANNEL.messageBuilder(OpenUI_WoodBoard.class, id++).codec(OpenUI_WoodBoard.CODEC).consumerMainThread(OpenUI_WoodBoard::handle).add();
		CHANNEL.messageBuilder(Server_WoodBoardSend.class, id++).codec(Server_WoodBoardSend.CODEC).consumerMainThread(Server_WoodBoardSend::handle).add();
		CHANNEL.messageBuilder(Server_CleanEraser.class, id++).codec(Server_CleanEraser.CODEC).consumerMainThread(Server_CleanEraser::handle).add();
		CHANNEL.messageBuilder(Server_GenEraser.class, id++).codec(Server_GenEraser.CODEC).consumerMainThread(Server_GenEraser::handle).add();

		CHANNEL.messageBuilder(OpenUI_MirrorScreen.class, id++).codec(OpenUI_MirrorScreen.CODEC).consumerMainThread(OpenUI_MirrorScreen::handle).add();

		CHANNEL.messageBuilder(OpenUI_Sign.class, id++).codec(OpenUI_Sign.CODEC).consumerMainThread(OpenUI_Sign::handle).add();
		CHANNEL.messageBuilder(Server_SignSend.class, id++).codec(Server_SignSend.CODEC).consumerMainThread(Server_SignSend::handle).add();
	}
}
