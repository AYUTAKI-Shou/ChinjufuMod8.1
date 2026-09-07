package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WakeWater3 extends Base_WakeWater {

	public WakeWater3(String name) {
		super(name);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, 130);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		worldIn.scheduleUpdate(pos, this, 130);
		worldIn.setBlockToAir(pos);
	}

	/* Play Sound & Particle */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		/*la < 4 量*/
		for (int la = 0; la < 5; ++la) {
			World par1World = worldIn;
			Random par5Random = rand;
			/* 1.5F 消える速度？ 0.5D 範囲*/
			double d0 = (double)pos.getX() + (double) (par5Random.nextFloat() - 1.5F) * 0.4D;
			double d1 = (double)pos.getY() + (double) (par5Random.nextFloat() - 1.5F) * 0.4D;
			double d2 = (double)pos.getZ() + (double) (par5Random.nextFloat() - 1.5F) * 0.4D;

			par1World.spawnParticle(EnumParticleTypes.CLOUD, d0 + 0.6D, d1 - 0.4D, d2 + 0.6D, 0.0D, 0.0D, 0.0D); }
	}
}
