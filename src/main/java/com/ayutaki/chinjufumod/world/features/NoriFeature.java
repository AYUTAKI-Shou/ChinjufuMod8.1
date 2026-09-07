package com.ayutaki.chinjufumod.world.features;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.crop.Nori;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NoriFeature extends Feature<NoneFeatureConfiguration> {

	public NoriFeature(Codec<NoneFeatureConfiguration> floatIn) {
		super(floatIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		boolean flag = false;

		for(int r = 0; r < 4; ++r) {
			Random rand = context.random();
			WorldGenLevel worldIn= context.level();
			BlockPos pos = context.origin();
			
			int i = rand.nextInt(4) - rand.nextInt(4);
			int j = rand.nextInt(4) - rand.nextInt(4);
			int k = worldIn.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
			BlockPos blockPos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
			
			boolean limit = (k < 63) && (k > 47);
			if (worldIn.getBlockState(blockPos).is(Blocks.WATER) && limit) {
				int STAGE = 1 + rand.nextInt(5);
				
				boolean north = Nori.canConnectTo(worldIn, blockPos, Direction.NORTH);
				boolean east = Nori.canConnectTo(worldIn, blockPos, Direction.EAST);
				boolean south = Nori.canConnectTo(worldIn, blockPos, Direction.SOUTH);
				boolean west = Nori.canConnectTo(worldIn, blockPos, Direction.WEST);
				
				BlockState blockState = Crop_Blocks.NORI.get().defaultBlockState()
						.setValue(Nori.NORTH, north).setValue(Nori.EAST, east).setValue(Nori.SOUTH, south).setValue(Nori.WEST, west)
						.setValue(Nori.STAGE_1_6, Integer.valueOf(STAGE))
						.setValue(Nori.WATERLOGGED, Boolean.valueOf(true));
				
				if (blockState.canSurvive(worldIn, blockPos)) {
					worldIn.setBlock(blockPos, blockState, 2);
					flag = true; }
			}
		}
		return flag;
	}
}
