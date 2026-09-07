package com.ayutaki.chinjufumod.world.features;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Features_CM {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, ChinjufuMod.MOD_ID);

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TENGUSA_WARM = FEATURES.register(
			"tengusa_warm", () -> new TengusaWarm(NoneFeatureConfiguration.CODEC));
	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TENGUSA_LUKE = FEATURES.register(
			"tengusa_luke", () -> new TengusaLuke(NoneFeatureConfiguration.CODEC));

	public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> NORI_FEATURE = FEATURES.register(
			"nori_feature", () -> new NoriFeature(NoneFeatureConfiguration.CODEC));
}
