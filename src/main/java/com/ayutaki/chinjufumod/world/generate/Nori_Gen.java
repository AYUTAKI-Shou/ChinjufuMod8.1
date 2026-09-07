package com.ayutaki.chinjufumod.world.generate;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class Nori_Gen {

	public static void generate(final BiomeLoadingEvent biomeevent) {
		Category category = biomeevent.getCategory();
		BiomeGenerationSettingsBuilder builder = biomeevent.getGeneration();
		RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, biomeevent.getName());
		
		if (category == Biome.Category.NETHER || category == Biome.Category.THEEND) { }
		
		if (biomeKey == Biomes.STONE_SHORE) {
			builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, OceanFeature_CM.NORI_CONFIG); }
	}
}
