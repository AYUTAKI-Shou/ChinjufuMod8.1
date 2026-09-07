package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WakeWater2 extends Base_WakeWater {

	public WakeWater2(String name) {
		super(name);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, 10);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		worldIn.scheduleUpdate(pos, this, 10);
		worldIn.setBlockState(pos, Chinjufu_Blocks.WAKE_WATER3.getDefaultState());
	}

	/* Play Sound & Particle */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextDouble() < 0.75D) {
			double x = pos.getX();
			double y = pos.getY() - 0.75D;
			double z = pos.getZ();
			/* 音源座標, 音源名, 音声カテゴリー, 音が聞こえる半径, 音の高さ */
			worldIn.playSound(x, y, z, SoundEvents_CM.WATER_WAKE, SoundCategory.BLOCKS, 0.5F, 0.75F, false); }

		/*la < 4 量*/
		for (int la = 0; la < 10; ++la) {
			World par1World = worldIn;
			Random par5Random = rand;
			/* 1.5F 消える速度？ 0.5D 範囲*/
			double d0 = (double)pos.getX() + (double) (par5Random.nextFloat() - 1.5F) * 0.4D;
			double d1 = (double)pos.getY() + (double) (par5Random.nextFloat() - 1.5F) * 0.4D;
			double d2 = (double)pos.getZ() + (double) (par5Random.nextFloat() - 1.5F) * 0.4D;

			par1World.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.9D, d1 - 0.14D, d2 + 0.9D, 0.0D, 0.0D, 0.0D); }
	}
}
