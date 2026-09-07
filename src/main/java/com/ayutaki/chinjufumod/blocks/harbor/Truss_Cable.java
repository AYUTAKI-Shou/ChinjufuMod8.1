package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.google.common.collect.Sets;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Truss_Cable extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);
	private boolean shouldSignal = true;
	
	public Truss_Cable(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(POWER, Integer.valueOf(0)));
	}

	/* Get Power */
	private void updatePowerStrength(World worldIn, BlockPos pos, BlockState state) {
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
	}

	private int calculateTargetStrength(World worldIn, BlockPos pos) {
		this.shouldSignal = false;
		int bestPower = worldIn.getBestNeighborSignal(pos);
		this.shouldSignal = true;
		int wirePower = this.getIncomeWirePower(worldIn, pos);
		
		int max = Math.max(bestPower, wirePower);
		int limit = (max >= 15)? 15 : max;
		int fix = (limit >= 1)? 1 : 0;

		return limit - fix;
	}
	
	private int getIncomeWirePower(World worldIn, BlockPos pos) {
		int wirePower = 0;

		for (Direction direction : Direction.values()) {
			BlockPos facePos = pos.relative(direction);
			BlockState faceState = worldIn.getBlockState(facePos);
			wirePower = Math.max(wirePower, this.getWireSignal(facePos, faceState)); }

		return Math.max(0, wirePower);
	}
	
	private int getWireSignal(BlockPos pos, BlockState neighbor) {
		if (neighbor.getBlock() instanceof RedstoneWireBlock) { return neighbor.getValue(RedstoneWireBlock.POWER); }
		else { return (neighbor.getBlock() instanceof Truss_Cable) ? neighbor.getValue(POWER) : 0; }
	}
	
	private void checkCornerChangeAt(World worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() instanceof Truss_Cable || state.getBlock() instanceof RedstoneWireBlock) {
			worldIn.updateNeighborsAt(pos, this);

			for (Direction direction : Direction.values()) {
				worldIn.updateNeighborsAt(pos.relative(direction), this); }
		}
	}

	private void updateNeighborsOfNeighboringWires(World worldIn, BlockPos pos) {
		for(Direction direction : Direction.values()) {
			this.checkCornerChangeAt(worldIn, pos.relative(direction));
		}
	}
	
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean flag) {
		if (!oldState.is(state.getBlock()) && !worldIn.isClientSide) {
			this.updatePowerStrength(worldIn, pos, state);

			for (Direction direction : Direction.values()) {
				this.updateNeighborsOfNeighboringWires(worldIn, pos.relative(direction)); }
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean flag) {
		if (!flag && !state.is(newState.getBlock())) {
			super.onRemove(state, worldIn, pos, newState, flag);
			if (!worldIn.isClientSide) {
				for (Direction direction : Direction.values()) {
					worldIn.updateNeighborsAt(pos.relative(direction), this); }

				this.updatePowerStrength(worldIn, pos, state);

				for (Direction direction : Direction.values()) {
					this.updateNeighborsOfNeighboringWires(worldIn, pos.relative(direction)); }
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean flag) {
		if (!worldIn.isClientSide) {
			this.updatePowerStrength(worldIn, pos, state); }
	}

	@Override
	public int getDirectSignal(BlockState state, IBlockReader worldIn, BlockPos pos, Direction face) {
		return !this.shouldSignal ? 0 : state.getSignal(worldIn, pos, face);
	}

	@Override
	public int getSignal(BlockState state, IBlockReader worldIn, BlockPos pos, Direction face) {
		if (this.shouldSignal) {
			int i = state.getValue(POWER);
			if (i == 0) { return 0; }
			else { return i; }
		}
		else { return 0; }
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return this.shouldSignal;
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, WATERLOGGED, POWER);
	}
	
	/* Solid 0.0F -> 1.0F Transparent */
	@OnlyIn(Dist.CLIENT)
	public float getShadeBrightness(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}

	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_ctruss").withStyle(TextFormatting.GRAY));
	}
}
