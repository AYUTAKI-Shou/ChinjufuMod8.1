package com.ayutaki.chinjufumod.world;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.world.placement.OverworldPlacements_CM;
import com.ayutaki.chinjufumod.world.placement.VegetationPlacements_CM;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class BiomeModifiers_CM {

	public static final ResourceKey<BiomeModifier> ADD_BAUXITEORE_UPPER = createKey("add_ore_bauxite_upper");
	public static final ResourceKey<BiomeModifier> ADD_BAUXITEORE_MIDDLE = createKey("add_ore_bauxite_middle");
	public static final ResourceKey<BiomeModifier> ADD_BAUXITEORE_SMALL = createKey("add_ore_bauxite_small");
	
	public static final ResourceKey<BiomeModifier> ADD_TENGUSA_W = createKey("add_tengusa_warm");
	public static final ResourceKey<BiomeModifier> ADD_TENGUSA_L = createKey("add_tengusa_luke");
	public static final ResourceKey<BiomeModifier> ADD_NORI = createKey("add_nori");
	
	
	private static ResourceKey<BiomeModifier> createKey(final String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ChinjufuMod.id(name));
	}

	/////* Bootstap */////
	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		var biomes = context.lookup(Registries.BIOME);

		context.register(ADD_BAUXITEORE_UPPER, new BiomeModifiers.AddFeaturesBiomeModifier(
						biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
						HolderSet.direct(placedFeatures.getOrThrow(OverworldPlacements_CM.BAUXITEORE_UPPERPLACE)),
						GenerationStep.Decoration.UNDERGROUND_ORES));

		context.register(ADD_BAUXITEORE_MIDDLE, new BiomeModifiers.AddFeaturesBiomeModifier(
						biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
						HolderSet.direct(placedFeatures.getOrThrow(OverworldPlacements_CM.BAUXITEORE_MIDDLEPLACE)),
						GenerationStep.Decoration.UNDERGROUND_ORES));
		
		context.register(ADD_BAUXITEORE_SMALL, new BiomeModifiers.AddFeaturesBiomeModifier(
				biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
				HolderSet.direct(placedFeatures.getOrThrow(OverworldPlacements_CM.BAUXITEORE_SMALLPLACE)),
				GenerationStep.Decoration.UNDERGROUND_ORES));
		
		
		context.register(ADD_TENGUSA_W, new BiomeModifiers.AddFeaturesBiomeModifier(
				HolderSet.direct(biomes.getOrThrow(Biomes.WARM_OCEAN)),
				HolderSet.direct(placedFeatures.getOrThrow(VegetationPlacements_CM.TENGUSA_W_PLACE)),
				GenerationStep.Decoration.VEGETAL_DECORATION));
		context.register(ADD_TENGUSA_L, new BiomeModifiers.AddFeaturesBiomeModifier(
				HolderSet.direct(biomes.getOrThrow(Biomes.LUKEWARM_OCEAN)),
				HolderSet.direct(placedFeatures.getOrThrow(VegetationPlacements_CM.TENGUSA_L_PLACE)),
				GenerationStep.Decoration.VEGETAL_DECORATION));
		context.register(ADD_NORI, new BiomeModifiers.AddFeaturesBiomeModifier(
				HolderSet.direct(biomes.getOrThrow(Biomes.STONY_SHORE)),
				HolderSet.direct(placedFeatures.getOrThrow(VegetationPlacements_CM.NORI_PLACE)),
				GenerationStep.Decoration.VEGETAL_DECORATION));
	}
}
