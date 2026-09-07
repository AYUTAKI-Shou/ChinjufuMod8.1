package com.ayutaki.chinjufumod.blocks.cmblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class AdmiralStampItem extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty AGE_1_16 = IntegerProperty.create("age", 1, 16);

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(11.0D, 0.0D, 11.0D, 15.0D, 4.0D, 15.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(1.0D, 0.0D, 11.0D, 5.0D, 4.0D, 15.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 5.0D, 4.0D, 5.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(11.0D, 0.0D, 1.0D, 15.0D, 4.0D, 5.0D);

	public AdmiralStampItem(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(AGE_1_16, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		ItemStack hStack = context.getItem();
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 16) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 15)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(2))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 15) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 14)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(3))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 14) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 13)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(4))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 13) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 12)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(5))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 12) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 11)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(6))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 11) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 10)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(7))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 10) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 9)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(8))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 9) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 8)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(9))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 8) &&
				(hStack.getDamage() < hStack.getMaxDamage() - 6)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(10))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if (hStack.getDamage() == hStack.getMaxDamage() - 6) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(11))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 6) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 5)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(12))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 5) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 4)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(13))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 4) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 3)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(14))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if ((hStack.getDamage() > hStack.getMaxDamage() - 3) &&
				(hStack.getDamage() <= hStack.getMaxDamage() - 2)) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(15))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		if (hStack.getDamage() > hStack.getMaxDamage() - 2) {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(16))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
		
		else {
			return this.getDefaultState().with(AGE_1_16, Integer.valueOf(1))
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite()); }
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, AGE_1_16, WATERLOGGED);
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
		return ToolType.AXE;
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
