package com.ayutaki.chinjufumod.world.features;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Feature_CM {
	
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ChinjufuMod.MOD_ID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> TENGUSA_WARM = FEATURES.register(
			"tengusa_warm", () -> new TengusaWarm(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> TENGUSA_LUKE = FEATURES.register(
			"tengusa_luke", () -> new TengusaLuke(NoneFeatureConfiguration.CODEC));
	
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> NORI_FEATURE = FEATURES.register(
			"nori_feature", () -> new NoriFeature(NoneFeatureConfiguration.CODEC));
}
