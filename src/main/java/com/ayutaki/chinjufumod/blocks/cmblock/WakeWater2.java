package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WakeWater2 extends Base_WakeWater {

	public WakeWater2(Block.Properties props) {
		super(props);
	}

	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (true) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10); }
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (worldIn.isAreaLoaded(pos, 1)) { worldIn.removeBlock(pos, false); }

		worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);
		worldIn.setBlockState(pos, Chinjufu_Blocks.WAKE_WATER3.getDefaultState());
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextDouble() < 0.75D) {
			double x = (double)pos.getX();
			double y = (double)pos.getY() - 0.75D;
			double z = (double)pos.getZ();
			
			worldIn.playSound(x, y, z, SoundEvents_CM.WATER_WAKE, SoundCategory.BLOCKS, 0.5F, 0.75F, false); }

		for(int i = 0; i < 10; ++i) {
			double d0 = (double)pos.getX() + (rand.nextDouble() - 1.0F) * 0.3D;
			double d1 = (double)pos.getY() + (rand.nextDouble() - 1.0F) * 0.3D;
			double d2 = (double)pos.getZ() + (rand.nextDouble() - 1.0F) * 0.3D;

			worldIn.addParticle(ParticleTypes.CLOUD, d0 + 0.7D, d1 - 0.4, d2 + 0.7D, 0.0D, 0.0D, 0.0D); }
	}
}
