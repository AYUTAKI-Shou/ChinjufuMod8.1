package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.world.generate.ConfigFeature_CM;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class Tree_Kaede extends Tree {

	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean b) {
		return (randomIn.nextInt(50) == 0)? ConfigFeature_CM.KAEDE_FANCY_CONFIG : ConfigFeature_CM.KAEDE_CONFIG;
	}
}
