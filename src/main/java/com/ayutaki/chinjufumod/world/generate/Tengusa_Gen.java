package com.ayutaki.chinjufumod.world.generate;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Tengusa_Gen {
	
	public static void generate(final BiomeLoadingEvent event) {
		BiomeCategory category = event.getCategory();
		BiomeGenerationSettingsBuilder builder = event.getGeneration();
		ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
		
		if (category == Biome.BiomeCategory.NETHER || category == Biome.BiomeCategory.THEEND) { }
		
		if (key == Biomes.WARM_OCEAN) {
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Placement_CM.TENGUSA_W_PLACE.getHolder().orElseThrow()); }
		
		if (key == Biomes.LUKEWARM_OCEAN) {
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Placement_CM.TENGUSA_L_PLACE.getHolder().orElseThrow()); }
	}
}
