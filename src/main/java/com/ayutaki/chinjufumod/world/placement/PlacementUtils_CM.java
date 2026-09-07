package com.ayutaki.chinjufumod.world.placement;

import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class PlacementUtils_CM {

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		OverworldPlacements_CM.bootstrap(context);
		TreePlacements_CM.bootstrap(context);
		VegetationPlacements_CM.bootstrap(context);
	}
	
	public static ResourceKey<PlacedFeature> createKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ChinjufuMod.id(name));
	}
	
	public static void registerList(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> holder, List<PlacementModifier> list) {
		context.register(key, new PlacedFeature(holder, List.copyOf(list)));
	}

	public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> holder, PlacementModifier... list) {
		registerList(context, key, holder, List.of(list));
	}
}
