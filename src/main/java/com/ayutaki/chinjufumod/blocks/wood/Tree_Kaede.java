package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Random;

import com.ayutaki.chinjufumod.world.generate.ConfigFeature_CM;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class Tree_Kaede extends Tree {

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean b) {
		return randomIn.nextInt(50) == 0 ? TreeFeature.FANCY_TREE.withConfiguration(ConfigFeature_CM.KAEDE_FANCY_CONFIG) : TreeFeature.NORMAL_TREE.withConfiguration(ConfigFeature_CM.KAEDE_CONFIG);
	}
}
