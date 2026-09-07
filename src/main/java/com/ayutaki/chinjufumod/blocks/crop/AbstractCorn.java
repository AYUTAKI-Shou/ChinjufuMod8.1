package com.ayutaki.chinjufumod.blocks.crop;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractCorn extends BushBlock implements BonemealableBlock {

	public AbstractCorn(BlockBehaviour.Properties props) {
		super(props);
	}

	protected int getBonemealAgeIncrease(Level worldIn) {
		return Mth.nextInt(worldIn.random, 2, 5);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return hasSufficientLight(worldIn, pos) && super.canSurvive(state, worldIn, pos);
	}

	protected static boolean hasSufficientLight(LevelReader worldIn, BlockPos pos) {
		return worldIn.getRawBrightness(pos, 0) >= 8;
	}

	@Override
	protected void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		if (worldIn instanceof ServerLevel server && entityIn instanceof Ravager && net.neoforged.neoforge.event.EventHooks.canEntityGrief(server, entityIn)) {
			server.destroyBlock(pos, true, entityIn); }

		super.entityInside(state, worldIn, pos, entityIn);
	}
	
	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource p_221046_, BlockPos pos, BlockState state) {
		return true;
	}
}
