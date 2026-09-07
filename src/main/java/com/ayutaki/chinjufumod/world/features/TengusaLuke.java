package com.ayutaki.chinjufumod.world.features;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.crop.Tengusa;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TengusaLuke extends Feature<NoneFeatureConfiguration> {

	public TengusaLuke(Codec<NoneFeatureConfiguration> floatIn) {
		super(floatIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		boolean flag = false;

		for(int r = 0; r < 2; ++r) {
			Random rand = context.random();
			WorldGenLevel worldIn= context.level();
			BlockPos pos = context.origin();
			
			int i = rand.nextInt(2) - rand.nextInt(2);
			int j = rand.nextInt(2) - rand.nextInt(2);
			int k = worldIn.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
			BlockPos blockPos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
			
			boolean limit = (k < 63) && (k > 47);
			if (worldIn.getBlockState(blockPos).is(Blocks.WATER) && limit) {
				int STAGE = rand.nextInt(3);
				BlockState blockState = Crop_Blocks.TENGUSA.get().defaultBlockState()
						.setValue(Tengusa.STAGE_0_7, Integer.valueOf(STAGE))
						.setValue(Tengusa.WATERLOGGED, Boolean.valueOf(true));
				
				if (blockState.canSurvive(worldIn, blockPos)) {
					worldIn.setBlock(blockPos, blockState, 2);
					flag = true; }
			}
		}
		return flag;
	}
}
