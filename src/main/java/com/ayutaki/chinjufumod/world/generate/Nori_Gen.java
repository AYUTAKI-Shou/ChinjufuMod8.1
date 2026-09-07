package com.ayutaki.chinjufumod.world.generate;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidWithNoiseConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class Nori_Gen {

	public static void addBush() {
		for (Biome biomeIn : ForgeRegistries.BIOMES) {
			
			if (biomeIn.getCategory() == Biome.Category.NETHER || biomeIn.getCategory() == Biome.Category.THEEND) { }

			if (biomeIn == Biomes.STONE_SHORE) {
				biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
						Feature_CM.NORI.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
						.withPlacement(Placement.TOP_SOLID_HEIGHTMAP_NOISE_BIASED
						.configure(new TopSolidWithNoiseConfig(10, 10.0D, 0.0D, Heightmap.Type.OCEAN_FLOOR_WG)))); }
		}
	}
}
//1441088434927584516: -630 100 -1750