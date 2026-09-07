package com.ayutaki.chinjufumod.blocks.hakkou;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class Bot_Vanilla extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 6.0D, 7.0D, 6.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(10.0D, 0.0D, 2.0D, 14.0D, 7.0D, 6.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(10.0D, 0.0D, 10.0D, 14.0D, 7.0D, 14.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(2.0D, 0.0D, 10.0D, 6.0D, 7.0D, 14.0D);
	
	public Bot_Vanilla(Block.Properties props) {
		super(props);
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
