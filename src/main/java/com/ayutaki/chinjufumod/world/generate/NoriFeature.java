package com.ayutaki.chinjufumod.world.generate;

import java.util.Random;
import java.util.function.Function;

import com.ayutaki.chinjufumod.blocks.crop.Nori;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NoriFeature extends Feature<NoFeatureConfig> {

	public NoriFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> floatIn) {
		super(floatIn);
	}
	
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> chunk, Random rand, BlockPos pos, NoFeatureConfig floatIn) {
		boolean flag = false;

		for(int r = 0; r < 4; ++r) {
			int i = rand.nextInt(4) - rand.nextInt(4);
			int j = rand.nextInt(4) - rand.nextInt(4);
			int k = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
			BlockPos blockPos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
	
			boolean limit = (k < 63) && (k > 47);
			if (worldIn.getBlockState(blockPos).getBlock() == Blocks.WATER && limit) {
				int STAGE = 1 + rand.nextInt(5);
				
				boolean north = Nori.canConnectTo(worldIn, blockPos, Direction.NORTH);
				boolean east = Nori.canConnectTo(worldIn, blockPos, Direction.EAST);
				boolean south = Nori.canConnectTo(worldIn, blockPos, Direction.SOUTH);
				boolean west = Nori.canConnectTo(worldIn, blockPos, Direction.WEST);
				
				BlockState blockState = Crop_Blocks.NORI.getDefaultState()
						.with(Nori.NORTH, north).with(Nori.EAST, east).with(Nori.SOUTH, south).with(Nori.WEST, west)
						.with(Nori.STAGE_1_6, Integer.valueOf(STAGE))
						.with(Nori.WATERLOGGED, Boolean.valueOf(true));
				
				if (blockState.isValidPosition(worldIn, blockPos)) {
					worldIn.setBlockState(blockPos, blockState, 2);
					flag = true; }
			}
		}
		return flag;
	}
}
