package com.ayutaki.chinjufumod.world.generate;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class OceanFeature_CM {
	/* To avoid NullPointerExceptions, I moved these into a separate class.
	This class does not use Deferred. */
	public static final ConfiguredFeature<?, ?> TENGUSA_W_CONFIG = register("tengusa_w_config", Feature_CM.TENGUSA_WARM.get()
			.configured(IFeatureConfig.NONE).count(2).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
	public static final ConfiguredFeature<?, ?> TENGUSA_L_CONFIG = register("tengusa_l_config", Feature_CM.TENGUSA_LUKE.get()
			.configured(IFeatureConfig.NONE).count(1).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
	public static final ConfiguredFeature<?, ?> NORI_CONFIG = register("nori_config", Feature_CM.NORI.get()
			.configured(IFeatureConfig.NONE).count(4).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

	///* Register *///
	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, feature);
	}
}
