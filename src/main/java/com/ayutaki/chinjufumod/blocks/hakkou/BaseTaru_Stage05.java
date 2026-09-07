package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BaseTaru_Stage05 extends Base_Stage05 {
	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.makeCuboidShape(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.makeCuboidShape(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));

	public BaseTaru_Stage05(Block.Properties props) {
		super(props);
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100); }
		
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100); }
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.get(STAGE_0_5);
		if (i < 5) {
			if (rand.nextInt(4) == 0) {
				worldIn.setBlockState(pos, state.with(STAGE_0_5, Integer.valueOf(i + 1))); } }

		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
}
