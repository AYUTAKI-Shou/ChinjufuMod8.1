package com.ayutaki.chinjufumod.world.features;

import java.util.List;
import java.util.OptionalInt;

import com.ayutaki.chinjufumod.registry.Wood_Blocks;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class TreeFeatures_CM {
	
	/* Tree...TreeFeatures */
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_CONFIG = FeaturesUtils_CM.createKey("normal_oak_cm");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_FANCY_CONFIG = FeaturesUtils_CM.createKey("fancy_oak_cm");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_CONFIG = FeaturesUtils_CM.createKey("normal_sakura");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_FANCY_CONFIG = FeaturesUtils_CM.createKey("fancy_sakura");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_BEES_CONFIG = FeaturesUtils_CM.createKey("sakura_bees_0002");	
	public static final ResourceKey<ConfiguredFeature<?, ?>> KAEDE_CONFIG = FeaturesUtils_CM.createKey("normal_kaede");
	public static final ResourceKey<ConfiguredFeature<?, ?>> KAEDE_FANCY_CONFIG = FeaturesUtils_CM.createKey("fancy_kaede");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICHOH_CONFIG = FeaturesUtils_CM.createKey("normal_ichoh");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ICHOH_FANCY_CONFIG = FeaturesUtils_CM.createKey("fancy_ichoh");
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAKKARE_CONFIG = FeaturesUtils_CM.createKey("normal_autumnoak");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAKKARE_FANCY_CONFIG = FeaturesUtils_CM.createKey("fancy_autumnoak");
	
	
	/////* Bootstrap */////
	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		/* Tree */
		BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
		FeaturesUtils_CM.registerFC(context, OAK_CONFIG, Feature.TREE, createStraightTree(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 4, 2, 0, 2).build());
		FeaturesUtils_CM.registerFC(context, OAK_FANCY_CONFIG, Feature.TREE, createFancyTree(Blocks.OAK_LOG, Blocks.OAK_LEAVES).build());
		FeaturesUtils_CM.registerFC(context, SAKURA_CONFIG, Feature.TREE, createStraightTree(Wood_Blocks.SAKURA_log.get(), Wood_Blocks.SAKURA_flow.get(), 4, 2, 0, 2).build());
		FeaturesUtils_CM.registerFC(context, SAKURA_FANCY_CONFIG, Feature.TREE, createFancyTree(Wood_Blocks.SAKURA_log.get(), Wood_Blocks.SAKURA_flow.get()).build());
		FeaturesUtils_CM.registerFC(context, SAKURA_BEES_CONFIG, Feature.TREE, createStraightTree(Wood_Blocks.SAKURA_log.get(), Wood_Blocks.SAKURA_flow.get(), 4, 2, 0, 2).decorators(List.of(BEEHIVE_0002)).build());

		FeaturesUtils_CM.registerFC(context, KAEDE_CONFIG, Feature.TREE, createStraightTree(Wood_Blocks.KAEDE_log.get(), Wood_Blocks.KAEDE_leaf.get(), 4, 2, 0, 2).ignoreVines().build());
		FeaturesUtils_CM.registerFC(context, KAEDE_FANCY_CONFIG, Feature.TREE, createFancyTree(Wood_Blocks.KAEDE_log.get(), Wood_Blocks.KAEDE_leaf.get()).build());
		FeaturesUtils_CM.registerFC(context, ICHOH_CONFIG, Feature.TREE, createStraightTree(Wood_Blocks.ICHOH_log.get(), Wood_Blocks.ICHOH_leaf.get(), 4, 2, 0, 2).ignoreVines().build());
		FeaturesUtils_CM.registerFC(context, ICHOH_FANCY_CONFIG, Feature.TREE, createFancyTree(Wood_Blocks.ICHOH_log.get(), Wood_Blocks.ICHOH_leaf.get()).build());
		FeaturesUtils_CM.registerFC(context, OAKKARE_CONFIG, Feature.TREE, createStraightTree(Wood_Blocks.OAKKARE_log.get(), Wood_Blocks.OAKKARE_leaf.get(), 4, 2, 0, 2).ignoreVines().build());
		FeaturesUtils_CM.registerFC(context, OAKKARE_FANCY_CONFIG, Feature.TREE, createFancyTree(Wood_Blocks.OAKKARE_log.get(), Wood_Blocks.OAKKARE_leaf.get()).build());

	}
	
	
	/* Share variables */
	private static TreeConfiguration.TreeConfigurationBuilder createStraightTree(Block log, Block leaves, int high, int width, int zero, int fix) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
			new StraightTrunkPlacer(high, width, zero), BlockStateProvider.simple(leaves),
			new BlobFoliagePlacer(ConstantInt.of(fix), ConstantInt.of(0), 3),
			new TwoLayersFeatureSize(1, 0, 1)); }

	private static TreeConfiguration.TreeConfigurationBuilder createFancyTree(Block log, Block leaves) {
		return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log),
				new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(leaves),
				new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
				new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))); }
}
