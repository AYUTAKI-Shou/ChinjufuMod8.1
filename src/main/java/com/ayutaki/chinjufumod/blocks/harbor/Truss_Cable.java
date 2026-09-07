package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;

public class Truss_Cable extends Abstract_WaterLogged {
	public static final MapCodec<Truss_Cable> CODEC = simpleCodec(Truss_Cable::new);
	@Override
	public MapCodec<? extends Truss_Cable> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);
	private boolean shouldSignal = true;
	
	public Truss_Cable(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		this.registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(POWER, Integer.valueOf(0)));
	}

	/* Get Power */
	private void updatePowerStrength(Level worldIn, BlockPos pos, BlockState state, @Nullable Orientation orientation, boolean updateShape) {
		int i = calculateTargetStrength(worldIn, pos);
		if (state.getValue(POWER) != i) {
			if (worldIn.getBlockState(pos) == state) {
				worldIn.setBlock(pos, state.setValue(POWER, Integer.valueOf(i)), 2); }

			Set<BlockPos> set = Sets.newHashSet();
			set.add(pos);

			for (Direction direction : Direction.values()) {
				set.add(pos.relative(direction)); }

			for (BlockPos blockpos : set) {
				worldIn.updateNeighborsAt(blockpos, this); }
		}
	}//for 1.21.4
	
	private int calculateTargetStrength(Level worldIn, BlockPos pos) {
		this.shouldSignal = false;
		int bestPower = worldIn.getBestNeighborSignal(pos);
		this.shouldSignal = true;
		int wirePower = this.getIncomeWirePower(worldIn, pos);

		int max = Math.max(bestPower, wirePower);
		int limit = (max >= 15)? 15 : max;
		int fix = (limit >= 1)? 1 : 0;

		return limit - fix;
	}

	private int getIncomeWirePower(Level worldIn, BlockPos pos) {
		int wirePower = 0;

		for (Direction direction : Direction.values()) {
			BlockPos facePos = pos.relative(direction);
			BlockState faceState = worldIn.getBlockState(facePos);
			wirePower = Math.max(wirePower, this.getWireSignal(facePos, faceState)); }

		return Math.max(0, wirePower);
	}

	private int getWireSignal(BlockPos pos, BlockState neighbor) { //for 1.21.4
		if (neighbor.getBlock() instanceof RedStoneWireBlock) { return neighbor.getValue(RedStoneWireBlock.POWER); }
		else { return (neighbor.getBlock() instanceof Truss_Cable) ? neighbor.getValue(POWER) : 0; }
	}
	
	private void checkCornerChangeAt(Level worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() instanceof Truss_Cable || state.getBlock() instanceof RedStoneWireBlock) {
			worldIn.updateNeighborsAt(pos, this);

			for (Direction direction : Direction.values()) {
				worldIn.updateNeighborsAt(pos.relative(direction), this); }
		}
	}
	
	private void updateNeighborsOfNeighboringWires(Level worldIn, BlockPos pos) {
		for(Direction direction : Direction.values()) {
			this.checkCornerChangeAt(worldIn, pos.relative(direction));
		}
	}
	
	@Override
	protected void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean flag) {
		if (!oldState.is(state.getBlock()) && !worldIn.isClientSide) {
			this.updatePowerStrength(worldIn, pos, state, null, false);

			for (Direction direction : Direction.values()) {
				this.updateNeighborsOfNeighboringWires(worldIn, pos.relative(direction)); }
		}
	}

	@Override
	protected void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean flag) {
		if (!flag && !state.is(newState.getBlock())) {
			super.onRemove(state, worldIn, pos, newState, flag);
			if (!worldIn.isClientSide) {
				for (Direction direction : Direction.values()) {
					worldIn.updateNeighborsAt(pos.relative(direction), this); }

				this.updatePowerStrength(worldIn, pos, state, null, false);

				for (Direction direction : Direction.values()) {
					this.updateNeighborsOfNeighboringWires(worldIn, pos.relative(direction)); }
			}
		}
	}
	
	protected void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, @Nullable Orientation ori, boolean flag) {
		if (!worldIn.isClientSide) {
			this.updatePowerStrength(worldIn, pos, state, ori, false); }
	}

	@Override
	protected int getDirectSignal(BlockState state, BlockGetter worldIn, BlockPos pos, Direction side) {
		return !this.shouldSignal ? 0 : state.getSignal(worldIn, pos, side);
	}
	
	@Override
	protected int getSignal(BlockState state, BlockGetter worldIn, BlockPos pos, Direction side) {
		if (this.shouldSignal) {
			int i = state.getValue(POWER);
			if (i == 0) { return 0; }
			else { return i; }
		}
		else { return 0; }
	}
	
	@Override
	protected boolean isSignalSource(BlockState state) {
		return this.shouldSignal;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
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
		builder.add(H_FACING, WATERLOGGED, POWER);
	}

	/* Solid 0.0F -> 1.0F Transparent */
	@Override
	public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_ctruss").withStyle(ChatFormatting.GRAY));
	}
}
