package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WakeWater2 extends Base_WakeWater {

	public WakeWater2(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		worldIn.scheduleTick(pos, this, 10);
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.scheduleTick(pos, this, 10);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { worldIn.removeBlock(pos, false); }
		worldIn.scheduleTick(pos, this, 10);
		worldIn.setBlock(pos, Chinjufu_Blocks.WAKE_WATER3.get().defaultBlockState(), 3); //must '3'. 4 does not work.
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, Random rand) {
		if (rand.nextDouble() < 0.75D) {
			double x = (double)pos.getX();
			double y = (double)pos.getY() - 0.75D;
			double z = (double)pos.getZ();
			
			worldIn.playLocalSound(x, y, z, SoundEvents_CM.WATER_WAKE.get(), SoundSource.BLOCKS, 0.5F, 0.75F, false); }

		for (int i = 0; i < 10; ++i) {
			double d0 = (double)pos.getX() + (rand.nextDouble() - 1.0F) * 0.3D;
			double d1 = (double)pos.getY() + (rand.nextDouble() - 1.0F) * 0.3D;
			double d2 = (double)pos.getZ() + (rand.nextDouble() - 1.0F) * 0.3D;

			worldIn.addParticle(ParticleTypes.CLOUD, d0 + 0.7D, d1 - 0.4, d2 + 0.7D, 0.0D, 0.0D, 0.0D); }
	}
}
