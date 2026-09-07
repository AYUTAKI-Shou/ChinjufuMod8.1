package com.ayutaki.chinjufumod.world.placement;

import java.util.List;

import com.ayutaki.chinjufumod.world.features.OverworldFeatures_CM;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

public class OverworldPlacements_CM {

	/* Surface...MiscOverworldPlacements */
	public static final ResourceKey<PlacedFeature> AUTUMN_PLACE = PlacementUtils_CM.createKey("autumn_place");
	
	/* Ore...OrePlacements */
	public static final ResourceKey<PlacedFeature> BAUXITEORE_UPPERPLACE = PlacementUtils_CM.createKey("ore_bauxite_upper");
	public static final ResourceKey<PlacedFeature> BAUXITEORE_MIDDLEPLACE = PlacementUtils_CM.createKey("ore_bauxite_middle");
	public static final ResourceKey<PlacedFeature> BAUXITEORE_SMALLPLACE = PlacementUtils_CM.createKey("ore_bauxite_small_place");
	
	
	/////* Bootstrap */////
	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configFeature = context.lookup(Registries.CONFIGURED_FEATURE);
		
		final Holder<ConfiguredFeature<?, ?>> AUTUMN_CONFIG = configFeature.getOrThrow(OverworldFeatures_CM.AUTUMN_CONFIG);
		PlacementUtils_CM.register(context, AUTUMN_PLACE, AUTUMN_CONFIG, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID,
				RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.MUD)), BiomeFilter.biome());
		
		
		Holder<ConfiguredFeature<?, ?>> BAUXITE_CONFIG = configFeature.getOrThrow(OverworldFeatures_CM.BAUXITE_ORE_CONFIG);
		Holder<ConfiguredFeature<?, ?>> BAUXITE_SMALL = configFeature.getOrThrow(OverworldFeatures_CM.BAUXITE_ORE_SMALL);

		PlacementUtils_CM.registerList(context, BAUXITEORE_UPPERPLACE, BAUXITE_CONFIG, 
				commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(64), VerticalAnchor.absolute(128))));
		PlacementUtils_CM.registerList(context, BAUXITEORE_MIDDLEPLACE, BAUXITE_CONFIG, 
				commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(60))));
		PlacementUtils_CM.registerList(context, BAUXITEORE_SMALLPLACE, BAUXITE_SMALL, 
				commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))));
	}
	
	
	/* Share variables */
	private static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier placement) {
		return orePlacement(CountPlacement.of(i), placement); }
	
	private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier placement) {
		return List.of(count, InSquarePlacement.spread(), placement, BiomeFilter.biome()); }
}
