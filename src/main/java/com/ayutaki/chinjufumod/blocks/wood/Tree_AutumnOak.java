package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.world.generate.ConfigFeature_CM;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class Tree_AutumnOak extends Tree {

	@Nullable
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean b) {
		return (randomIn.nextInt(20) == 0)? ConfigFeature_CM.OAKKARE_FANCY_CONFIG : ConfigFeature_CM.OAKKARE_CONFIG;
	}
}
