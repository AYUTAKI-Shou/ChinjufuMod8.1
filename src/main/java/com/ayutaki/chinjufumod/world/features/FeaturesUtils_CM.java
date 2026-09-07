package com.ayutaki.chinjufumod.world.features;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FeaturesUtils_CM {

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		OverworldFeatures_CM.bootstrap(context);
		TreeFeatures_CM.bootstrap(context);
		VegetationFeatures_CM.bootstrap(context);
	}
	
	@SuppressWarnings("removal")
	public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ChinjufuMod.MOD_ID, name));
	}
	
	public static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Feature<NoneFeatureConfiguration> feature) {
		registerFC(context, key, feature, FeatureConfiguration.NONE);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerFC(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC featureConfig) {
		context.register(key, new ConfiguredFeature(feature, featureConfig));
	}
}
