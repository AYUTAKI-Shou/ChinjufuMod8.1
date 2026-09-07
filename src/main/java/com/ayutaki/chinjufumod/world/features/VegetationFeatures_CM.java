package com.ayutaki.chinjufumod.world.features;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Wood_Blocks;
import com.ayutaki.chinjufumod.world.placement.TreePlacements_CM;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VegetationFeatures_CM {

	/* Forest...VegetationFeatures */
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_OAK = FeaturesUtils_CM.createKey("sakura_oak");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_OAK_HILLS = FeaturesUtils_CM.createKey("sakura_oak_hills");
	public static final ResourceKey<ConfiguredFeature<?, ?>> KAEDE_AUTUMNOAK = FeaturesUtils_CM.createKey("kaede_autumnoak");
	public static final ResourceKey<ConfiguredFeature<?, ?>> KAEDE_AUTUMNOAK_HILLS = FeaturesUtils_CM.createKey("kaede_autumnoak_hills");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICHOH_AUTUMNOAK = FeaturesUtils_CM.createKey("ichoh_autumnoak");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICHOH_AUTUMNOAK_HILLS = FeaturesUtils_CM.createKey("ichoh_autumnoak_hills");
	
	/** VegetationFeatures.FLOWER_SWAMP Blocks.BLUE_ORCHID **/
	public static final ResourceKey<ConfiguredFeature<?, ?>>TAKENOKO_CONFIG = FeaturesUtils_CM.createKey("takenoko_config");
	public static final ResourceKey<ConfiguredFeature<?, ?>>TAKENOKO_HILLS_CONFIG = FeaturesUtils_CM.createKey("takenoko_hills_config");
	public static final ResourceKey<ConfiguredFeature<?, ?>>KURIIGA_CONFIG = FeaturesUtils_CM.createKey("kuriiga_config");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>>TENGUSA_W_CONFIG = FeaturesUtils_CM.createKey("tengusa_w_config");
	public static final ResourceKey<ConfiguredFeature<?, ?>>TENGUSA_L_CONFIG = FeaturesUtils_CM.createKey("tengusa_l_config");
	public static final ResourceKey<ConfiguredFeature<?, ?>>NORI_CONFIG = FeaturesUtils_CM.createKey("nori_config");
	
	
	/////* Bootstrap */////
	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
		
		Holder<PlacedFeature> OAK = placedFeature.getOrThrow(TreePlacements_CM.OAK_PLACE);
		Holder<PlacedFeature> OAK_F = placedFeature.getOrThrow(TreePlacements_CM.OAK_FANCY_PLACE);
		Holder<PlacedFeature> SAKURA = placedFeature.getOrThrow(TreePlacements_CM.SAKURA_PLACE);
		Holder<PlacedFeature> SAKURA_F = placedFeature.getOrThrow(TreePlacements_CM.SAKURA_FANCY_PLACE);
		Holder<PlacedFeature> SAKURA_B = placedFeature.getOrThrow(TreePlacements_CM.SAKURA_BEES_PLACE);
		FeaturesUtils_CM.registerFC(context, SAKURA_OAK, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(SAKURA, (float) 2 / 10 / 30 * 29), 
				new WeightedPlacedFeature(SAKURA_F, (float) 2 / 10 / 30),
				new WeightedPlacedFeature(SAKURA_B, (float) 2 / 10 / 300),
				new WeightedPlacedFeature(OAK_F, (float) (10 - 2) / 300)), OAK));

		FeaturesUtils_CM.registerFC(context, SAKURA_OAK_HILLS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(SAKURA, (float) 2/ 10 / 30 * 29), 
				new WeightedPlacedFeature(SAKURA_F, (float) 2 / 10 / 30),
				new WeightedPlacedFeature(SAKURA_B, (float) (10 - 2) / 200)), OAK));
		
		
		Holder<PlacedFeature> KAEDE = placedFeature.getOrThrow(TreePlacements_CM.KAEDE_PLACE);
		Holder<PlacedFeature> KAEDE_F = placedFeature.getOrThrow(TreePlacements_CM.KAEDE_FANCY_PLACE);
		Holder<PlacedFeature> ICHOH = placedFeature.getOrThrow(TreePlacements_CM.ICHOH_PLACE);
		Holder<PlacedFeature> ICHOH_F = placedFeature.getOrThrow(TreePlacements_CM.ICHOH_FANCY_PLACE);
		Holder<PlacedFeature> OAKKARE = placedFeature.getOrThrow(TreePlacements_CM.OAKKARE_PLACE);
		Holder<PlacedFeature> OAKKARE_F = placedFeature.getOrThrow(TreePlacements_CM.OAKKARE_FANCY_PLACE);
		FeaturesUtils_CM.registerFC(context, KAEDE_AUTUMNOAK, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(KAEDE, (float) 2 / 10), 
				new WeightedPlacedFeature(OAKKARE_F, (float) (10 - 2) / 300)), OAKKARE));

		FeaturesUtils_CM.registerFC(context, KAEDE_AUTUMNOAK_HILLS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(KAEDE, (float) 2 / 10 / 100 * 99), 
				new WeightedPlacedFeature(KAEDE_F, (float) 2 / 10 / 100),
				new WeightedPlacedFeature(OAKKARE_F, (float) (10 - 2) / 200)), OAKKARE));

		FeaturesUtils_CM.registerFC(context, ICHOH_AUTUMNOAK, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(ICHOH, (float) 2 / 10 / 30 * 29), 
				new WeightedPlacedFeature(ICHOH_F, (float) 2 / 10 / 30),
				new WeightedPlacedFeature(OAKKARE_F, (float) (10 - 2) / 300)), OAKKARE));

		FeaturesUtils_CM.registerFC(context, ICHOH_AUTUMNOAK_HILLS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
				new WeightedPlacedFeature(ICHOH, (float) 2 / 10 / 20 * 19), 
				new WeightedPlacedFeature(ICHOH_F, (float) 2 / 10 / 20),
				new WeightedPlacedFeature(OAKKARE_F, (float) (10 - 2) / 200)), OAKKARE));
		
		
		FeaturesUtils_CM.registerFC(context, TAKENOKO_CONFIG, Feature.RANDOM_PATCH, new RandomPatchConfiguration(1, 1, 0, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, 
				new SimpleBlockConfiguration(BlockStateProvider.simple(Wood_Blocks.TAKENOKO.get()))))); //64, 6, 2,
		FeaturesUtils_CM.registerFC(context, TAKENOKO_HILLS_CONFIG, Feature.RANDOM_PATCH, new RandomPatchConfiguration(1, 0, 0, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, 
				new SimpleBlockConfiguration(BlockStateProvider.simple(Wood_Blocks.TAKENOKO.get())))));
		FeaturesUtils_CM.registerFC(context, KURIIGA_CONFIG, Feature.RANDOM_PATCH, new RandomPatchConfiguration(3, 1, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, 
				new SimpleBlockConfiguration(BlockStateProvider.simple(Wood_Blocks.KURIIGA_BUSH.get())))));
		
		FeaturesUtils_CM.registerFC(context, TENGUSA_W_CONFIG, Features_CM.TENGUSA_WARM.get(), NoneFeatureConfiguration.INSTANCE);
		FeaturesUtils_CM.registerFC(context, TENGUSA_L_CONFIG, Features_CM.TENGUSA_LUKE.get(), NoneFeatureConfiguration.INSTANCE);
		FeaturesUtils_CM.registerFC(context, NORI_CONFIG, Features_CM.NORI_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);
	}
}
