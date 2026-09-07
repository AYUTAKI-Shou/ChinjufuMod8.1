package com.ayutaki.chinjufumod.world.generate;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Feature_CM {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, ChinjufuMod.MOD_ID);

	public static final RegistryObject<Feature<NoFeatureConfig>> TENGUSA_WARM = FEATURES.register("tengusa_warm", () -> new TengusaWarm(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> TENGUSA_LUKE = FEATURES.register("tengusa_luke", () -> new TengusaLuke(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> NORI = FEATURES.register("nori_feature", () -> new NoriFeature(NoFeatureConfig.CODEC));

	//public static final RegistryObject<TengusaWarm> TENGUSA_WARM = FEATURES.register("tengusa_warm", ()-> new TengusaWarm(NoFeatureConfig.CODEC));
	//public static final RegistryObject<TengusaLuke> TENGUSA_LUKE = FEATURES.register("tengusa_luke", ()-> new TengusaLuke(NoFeatureConfig.CODEC));
	//public static final RegistryObject<NoriFeature> NORI = FEATURES.register("nori_feature", ()-> new NoriFeature(NoFeatureConfig.CODEC));

}
