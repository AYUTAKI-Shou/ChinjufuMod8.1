package com.ayutaki.chinjufumod.world.placement;

import com.ayutaki.chinjufumod.registry.Wood_Blocks;
import com.ayutaki.chinjufumod.world.features.TreeFeatures_CM;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TreePlacements_CM {

	/* Tree...TreePlacements */
	public static final ResourceKey<PlacedFeature> OAK_PLACE = PlacementUtils_CM.createKey("oak_place");
	public static final ResourceKey<PlacedFeature> OAK_FANCY_PLACE = PlacementUtils_CM.createKey("fancy_oak_place");
	public static final ResourceKey<PlacedFeature> SAKURA_PLACE = PlacementUtils_CM.createKey("sakura_place");
	public static final ResourceKey<PlacedFeature> SAKURA_FANCY_PLACE = PlacementUtils_CM.createKey("fancy_sakura_place");
	public static final ResourceKey<PlacedFeature> SAKURA_BEES_PLACE = PlacementUtils_CM.createKey("sakura_bees_place");

	public static final ResourceKey<PlacedFeature> KAEDE_PLACE = PlacementUtils_CM.createKey("kaede_place");
	public static final ResourceKey<PlacedFeature> KAEDE_FANCY_PLACE = PlacementUtils_CM.createKey("kaede_oak_place");
	public static final ResourceKey<PlacedFeature> ICHOH_PLACE = PlacementUtils_CM.createKey("ichoh_place");
	public static final ResourceKey<PlacedFeature> ICHOH_FANCY_PLACE = PlacementUtils_CM.createKey("fancy_ichoh_place");
	public static final ResourceKey<PlacedFeature> OAKKARE_PLACE = PlacementUtils_CM.createKey("autumnoak_place");
	public static final ResourceKey<PlacedFeature> OAKKARE_FANCY_PLACE = PlacementUtils_CM.createKey("fancy_autumnoak_place");
	
	
	/////* Bootstrap */////
	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configFeature = context.lookup(Registries.CONFIGURED_FEATURE);
		
		PlacementUtils_CM.register(context, OAK_PLACE, configFeature.getOrThrow(TreeFeatures_CM.OAK_CONFIG), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
		PlacementUtils_CM.register(context, OAK_FANCY_PLACE, configFeature.getOrThrow(TreeFeatures_CM.OAK_FANCY_CONFIG), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
		PlacementUtils_CM.register(context, SAKURA_PLACE, configFeature.getOrThrow(TreeFeatures_CM.SAKURA_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.SAKURA_nae.get()));
		PlacementUtils_CM.register(context, SAKURA_FANCY_PLACE, configFeature.getOrThrow(TreeFeatures_CM.SAKURA_FANCY_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.SAKURA_nae.get()));
		PlacementUtils_CM.register(context, SAKURA_BEES_PLACE, configFeature.getOrThrow(TreeFeatures_CM.SAKURA_BEES_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.SAKURA_nae.get()));
		PlacementUtils_CM.register(context, KAEDE_PLACE, configFeature.getOrThrow(TreeFeatures_CM.KAEDE_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.KAEDE_nae.get()));
		PlacementUtils_CM.register(context, KAEDE_FANCY_PLACE, configFeature.getOrThrow(TreeFeatures_CM.KAEDE_FANCY_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.KAEDE_nae.get()));
		PlacementUtils_CM.register(context, ICHOH_PLACE , configFeature.getOrThrow(TreeFeatures_CM.ICHOH_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.ICHOH_nae.get()));
		PlacementUtils_CM.register(context, ICHOH_FANCY_PLACE, configFeature.getOrThrow(TreeFeatures_CM.ICHOH_FANCY_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.ICHOH_nae.get()));
		PlacementUtils_CM.register(context, OAKKARE_PLACE, configFeature.getOrThrow(TreeFeatures_CM.OAKKARE_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.OAKKARE_nae.get()));
		PlacementUtils_CM.register(context, OAKKARE_FANCY_PLACE, configFeature.getOrThrow(TreeFeatures_CM.OAKKARE_FANCY_CONFIG), PlacementUtils.filteredByBlockSurvival(Wood_Blocks.OAKKARE_nae.get()));
	}
}
