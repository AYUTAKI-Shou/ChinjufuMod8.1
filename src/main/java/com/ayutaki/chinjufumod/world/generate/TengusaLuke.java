package com.ayutaki.chinjufumod.world.generate;

import java.util.Random;
import java.util.function.Function;

import com.ayutaki.chinjufumod.blocks.crop.Tengusa;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TengusaLuke extends Feature<NoFeatureConfig> {

	public TengusaLuke(Function<Dynamic<?>, ? extends NoFeatureConfig> floatIn) {
		super(floatIn);
	}
	
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> chunk, Random rand, BlockPos pos, NoFeatureConfig floatIn) {
		boolean flag = false;

		for(int r = 0; r < 2; ++r) {
			int i = rand.nextInt(2) - rand.nextInt(2);
			int j = rand.nextInt(2) - rand.nextInt(2);
			int k = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
			BlockPos blockPos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
	
			boolean limit = (k < 63) && (k > 47);
			if (worldIn.getBlockState(blockPos).getBlock() == Blocks.WATER && limit) {
				int stage = rand.nextInt(3);
				BlockState blockState = Crop_Blocks.TENGUSA.getDefaultState()
						.with(Tengusa.STAGE_0_7, Integer.valueOf(stage))
						.with(Tengusa.WATERLOGGED, Boolean.valueOf(true));
				
				if (blockState.isValidPosition(worldIn, blockPos)) {
					worldIn.setBlockState(blockPos, blockState, 2);
					flag = true; }
			}
		}
		return flag;
	}
}
