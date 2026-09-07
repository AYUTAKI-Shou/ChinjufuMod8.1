package com.ayutaki.chinjufumod.world.features;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class OverworldFeatures_CM {
	
	/* Surface...MiscOverworldFeatures */
	public static final ResourceKey<ConfiguredFeature<?, ?>> AUTUMN_CONFIG = FeaturesUtils_CM.createKey("autumn");
	
	/* Ore...OreFeatures */
	public static final ResourceKey<ConfiguredFeature<?, ?>> BAUXITE_ORE_CONFIG = FeaturesUtils_CM.createKey("ore_bauxite_config");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BAUXITE_ORE_SMALL = FeaturesUtils_CM.createKey("ore_bauxite_small");
	
	
	/////* Bootstrap */////
	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeaturesUtils_CM.registerFC(context, AUTUMN_CONFIG, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Wood_Blocks.FALL_LEAF.get()), 
				BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.CLAY)), UniformInt.of(2, 3), 1));
		
		
		RuleTest RULE1 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
		RuleTest RULE2 = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
		List<OreConfiguration.TargetBlockState> list = List.of(OreConfiguration.target(RULE1, Chinjufu_Blocks.BAUXITE_ORE.get().defaultBlockState()), 
				OreConfiguration.target(RULE2, Chinjufu_Blocks.BAUXITE_ORE_DEEP.get().defaultBlockState()));
		
		FeaturesUtils_CM.registerFC(context, BAUXITE_ORE_CONFIG, Feature.ORE, new OreConfiguration(list, 8));
		FeaturesUtils_CM.registerFC(context, BAUXITE_ORE_SMALL, Feature.ORE, new OreConfiguration(list, 4));
	}
}
