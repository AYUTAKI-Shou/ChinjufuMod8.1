package com.ayutaki.chinjufumod.world.generate;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Feature_CM {
	
	@ObjectHolder(ChinjufuMod.MOD_ID + ":tengusa_warm")
	public static Feature<NoFeatureConfig> TENGUSA_WARM;
	@ObjectHolder(ChinjufuMod.MOD_ID + ":tengusa_luke")
	public static Feature<NoFeatureConfig> TENGUSA_LUKE;
	@ObjectHolder(ChinjufuMod.MOD_ID + ":nori_feature")
	public static Feature<NoFeatureConfig> NORI;

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
				new TengusaWarm(NoFeatureConfig::deserialize).setRegistryName(ChinjufuMod.MOD_ID + ":tengusa_warm"),
				new TengusaLuke(NoFeatureConfig::deserialize).setRegistryName(ChinjufuMod.MOD_ID + ":tengusa_luke"),
				new NoriFeature(NoFeatureConfig::deserialize).setRegistryName(ChinjufuMod.MOD_ID + ":nori_feature"));
	}
}
