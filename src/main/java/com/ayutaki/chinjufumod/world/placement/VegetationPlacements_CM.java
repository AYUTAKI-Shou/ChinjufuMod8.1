package com.ayutaki.chinjufumod.world.placement;

import java.util.List;

import com.ayutaki.chinjufumod.world.features.VegetationFeatures_CM;
import com.google.common.collect.ImmutableList;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

public class VegetationPlacements_CM {

	/* Forest...VegetationPlacements */
	public static final ResourceKey<PlacedFeature> SAKURA_OAK_PLACE = PlacementUtils_CM.createKey("sakura_oak_place");
	public static final ResourceKey<PlacedFeature> SAKURA_OAK_HILLS_PLACE = PlacementUtils_CM.createKey("sakura_oak_hills_place");
	public static final ResourceKey<PlacedFeature> KAEDE_AUTUMNOAK_PLACE = PlacementUtils_CM.createKey("kaede_autumnoak_place");
	public static final ResourceKey<PlacedFeature> KAEDE_AUTUMNOAK_HILLS_PLACE = PlacementUtils_CM.createKey("kaede_autumnoak_hills_place");
	public static final ResourceKey<PlacedFeature> ICHOH_AUTUMNOAK_PLACE = PlacementUtils_CM.createKey("ichoh_autumnoak_place");
	public static final ResourceKey<PlacedFeature> ICHOH_AUTUMNOAK_HILLS_PLACE = PlacementUtils_CM.createKey("ichoh_autumnoak_hills_place");
	
	/** VegetationPlacements.PATCH_WATERLILLY **/
	public static final ResourceKey<PlacedFeature> TAKENOKO_PLACE = PlacementUtils_CM.createKey("takenoko_place");
	public static final ResourceKey<PlacedFeature> TAKENOKO_HILLS_PLACE = PlacementUtils_CM.createKey("takenoko_hills_place");
	public static final ResourceKey<PlacedFeature> KURIIGA_PLACE = PlacementUtils_CM.createKey("kuriiga_place");
	
	public static final ResourceKey<PlacedFeature> TENGUSA_W_PLACE = PlacementUtils_CM.createKey("tengusa_w_place");
	public static final ResourceKey<PlacedFeature> TENGUSA_L_PLACE = PlacementUtils_CM.createKey("tengusa_l_place");
	public static final ResourceKey<PlacedFeature> NORI_PLACE = PlacementUtils_CM.createKey("nori_place");
	
	
	/////* Bootstrap */////
	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configFeature = context.lookup(Registries.CONFIGURED_FEATURE);
		
		PlacementUtils_CM.registerList(context, SAKURA_OAK_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.SAKURA_OAK), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		PlacementUtils_CM.registerList(context, SAKURA_OAK_HILLS_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.SAKURA_OAK_HILLS), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		PlacementUtils_CM.registerList(context, KAEDE_AUTUMNOAK_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.KAEDE_AUTUMNOAK), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		PlacementUtils_CM.registerList(context, KAEDE_AUTUMNOAK_HILLS_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.KAEDE_AUTUMNOAK_HILLS), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		PlacementUtils_CM.registerList(context, ICHOH_AUTUMNOAK_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.ICHOH_AUTUMNOAK), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		PlacementUtils_CM.registerList(context, ICHOH_AUTUMNOAK_HILLS_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.ICHOH_AUTUMNOAK_HILLS), treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
		
		PlacementUtils_CM.registerList(context, TAKENOKO_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.TAKENOKO_CONFIG), 
				List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		PlacementUtils_CM.registerList(context, TAKENOKO_HILLS_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.TAKENOKO_HILLS_CONFIG), 
				List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
		PlacementUtils_CM.registerList(context, KURIIGA_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.KURIIGA_CONFIG), 
				List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

		PlacementUtils_CM.registerList(context, TENGUSA_W_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.TENGUSA_W_CONFIG), 
				List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(2), BiomeFilter.biome()));
		PlacementUtils_CM.registerList(context, TENGUSA_L_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.TENGUSA_L_CONFIG), 
				List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(1), BiomeFilter.biome()));
		PlacementUtils_CM.registerList(context, NORI_PLACE, configFeature.getOrThrow(VegetationFeatures_CM.NORI_CONFIG), 
				List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(4), BiomeFilter.biome()));
	}
	
	
	/* Share variables */
	private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier modifier) {
		return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread())
				.add(SurfaceWaterDepthFilter.forMaxDepth(0)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
	}
	
	public static List<PlacementModifier> treePlacement(PlacementModifier modifier) {
		return treePlacementBase(modifier).build();
	}
}
