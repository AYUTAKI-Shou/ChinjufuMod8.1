package com.ayutaki.chinjufumod.blocks.cmblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class WakeWater3 extends Base_WakeWater {

	public WakeWater3(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		tick.scheduleTick(pos, this, 130);
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.scheduleTick(pos, this, 130);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (worldIn.isAreaLoaded(pos, 1)) { worldIn.removeBlock(pos, false); }
		worldIn.scheduleTick(pos, this, 130);
		worldIn.removeBlock(pos, false);
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		for(int i = 0; i < 10; ++i) {
			double d0 = (double)pos.getX() + (rand.nextDouble() - 1.0F) * 0.3D;
			double d1 = (double)pos.getY() + (rand.nextDouble() - 1.0F) * 0.3D;
			double d2 = (double)pos.getZ() + (rand.nextDouble() - 1.0F) * 0.3D;

			worldIn.addParticle(ParticleTypes.CLOUD, d0 + 0.7D, d1 - 0.2, d2 + 0.7D, 0.0D, 0.0D, 0.0D); }
	}
}
