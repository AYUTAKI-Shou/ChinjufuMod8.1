package com.ayutaki.chinjufumod.blocks.cmblock;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AdmiralStampItem extends Abstract_WaterLogged {
	public static final MapCodec<AdmiralStampItem> CODEC = simpleCodec(AdmiralStampItem::new);
	@Override
	public MapCodec<? extends AdmiralStampItem> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty AGE_1_16 = IntegerProperty.create("age", 1, 16);
	
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(11.0D, 0.0D, 11.0D, 15.0D, 4.0D, 15.0D);
	private static final VoxelShape AABB_WEST = Block.box(1.0D, 0.0D, 11.0D, 5.0D, 4.0D, 15.0D);
	private static final VoxelShape AABB_NORTH = Block.box(1.0D, 0.0D, 1.0D, 5.0D, 4.0D, 5.0D);
	private static final VoxelShape AABB_EAST = Block.box(11.0D, 0.0D, 1.0D, 15.0D, 4.0D, 5.0D);
	
	public AdmiralStampItem(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		this.registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(AGE_1_16, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		ItemStack hStack = context.getItemInHand();
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 16) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 15)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(2))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 15) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 14)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(3))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 14) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 13)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(4))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 13) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 12)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(5))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 12) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 11)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(6))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 11) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 10)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(7))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 10) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 9)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(8))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 9) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 8)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(9))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 8) &&
				(hStack.getDamageValue() < hStack.getMaxDamage() - 6)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(10))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if (hStack.getDamageValue() == hStack.getMaxDamage() - 6) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(11))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 6) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 5)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(12))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 5) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 4)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(13))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 4) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 3)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(14))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if ((hStack.getDamageValue() > hStack.getMaxDamage() - 3) &&
				(hStack.getDamageValue() <= hStack.getMaxDamage() - 2)) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(15))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		if (hStack.getDamageValue() > hStack.getMaxDamage() - 2) {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(16))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
		
		else {
			return this.defaultBlockState().setValue(AGE_1_16, Integer.valueOf(1))
					.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite()); }
	}
	
	/* HORIZONTAL Property */
	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, AGE_1_16, WATERLOGGED);
	}
	
	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}
}
