package com.ayutaki.chinjufumod.blocks.dish;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class MeasureCup extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(13.1D, 0.0D, 0.7D, 15.5D, 2.7D, 3.5D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(12.5D, 0.0D, 13.1D, 15.3D, 2.7D, 15.5D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(0.5D, 0.0D, 12.5D, 2.9D, 2.7D, 15.3D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.7D, 0.0D, 0.5D, 3.5D, 2.7D, 2.9D);

	public MeasureCup(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (hStack.isEmpty()) {
			CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.KEIRYO_CUP);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState()); }
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
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
