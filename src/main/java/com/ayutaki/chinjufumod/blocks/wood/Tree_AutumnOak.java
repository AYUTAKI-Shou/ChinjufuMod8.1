package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Random;

import com.ayutaki.chinjufumod.world.features.ConfigFeature_CM;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class Tree_AutumnOak extends AbstractTreeGrower {
	
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random rand, boolean t_f) {
		return (rand.nextInt(20) == 0)? ConfigFeature_CM.OAKKARE_FANCY_CONFIG.getHolder().orElseThrow() : ConfigFeature_CM.OAKKARE_CONFIG.getHolder().orElseThrow();
	}
}
