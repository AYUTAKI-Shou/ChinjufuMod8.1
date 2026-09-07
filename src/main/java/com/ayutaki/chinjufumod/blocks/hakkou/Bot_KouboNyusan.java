package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class Bot_KouboNyusan extends BaseStage4_FaceWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(1.5D, 0.0D, 1.5D, 6.5D, 7.0D, 6.5D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(9.5D, 0.0D, 1.5D, 14.5D, 7.0D, 6.5D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(9.5D, 0.0D, 9.5D, 14.5D, 7.0D, 14.5D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(1.5D, 0.0D, 9.5D, 6.5D, 7.0D, 14.5D);

	public Bot_KouboNyusan(Block.Properties props) {
		super(props);
	}

	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.get(STAGE_1_4);
		if (i < 4) {
			if (rand.nextInt(4) == 0) {
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1))); } }

		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
}
