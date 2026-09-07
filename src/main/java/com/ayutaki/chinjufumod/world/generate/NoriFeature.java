package com.ayutaki.chinjufumod.world.generate;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.crop.Nori;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NoriFeature extends Feature<NoFeatureConfig> {

	public NoriFeature(Codec<NoFeatureConfig> floatIn) {
		super(floatIn);
	}
	
	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean flag = false;

		for(int r = 0; r < 4; ++r) {
			int i = rand.nextInt(4) - rand.nextInt(4);
			int j = rand.nextInt(4) - rand.nextInt(4);
			int k = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
			BlockPos blockPos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
	
			boolean limit = (k < 63) && (k > 47);
			if (worldIn.getBlockState(blockPos).is(Blocks.WATER) && limit) {
				int STAGE = 1 + rand.nextInt(5);
				
				boolean north = Nori.canConnectTo(worldIn, blockPos, Direction.NORTH);
				boolean east = Nori.canConnectTo(worldIn, blockPos, Direction.EAST);
				boolean south = Nori.canConnectTo(worldIn, blockPos, Direction.SOUTH);
				boolean west = Nori.canConnectTo(worldIn, blockPos, Direction.WEST);
				
				BlockState blockState = Crop_Blocks.NORI.defaultBlockState()
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
