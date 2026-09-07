package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.util.ITooltipFlag;
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
	
	public Truss_Cable(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(WATERLOGGED, Boolean.valueOf(false))
				.with(POWER, Integer.valueOf(0)));
	}

	/* Get Power */
	private void updatePowerStrength(World worldIn, BlockPos pos, BlockState state) {
		int i = calculateTargetStrength(worldIn, pos);
		if (state.get(POWER) != i) {
			if (worldIn.getBlockState(pos) == state) {
				worldIn.setBlockState(pos, state.with(POWER, Integer.valueOf(i)), 2); }

			Set<BlockPos> set = Sets.newHashSet();
			set.add(pos);

			for (Direction direction : Direction.values()) {
				set.add(pos.offset(direction)); }

			for (BlockPos blockpos : set) {
				worldIn.notifyNeighborsOfStateChange(blockpos, this); }
		}
	}

	private int calculateTargetStrength(World worldIn, BlockPos pos) {
		this.shouldSignal = false;
		int bestPower = worldIn.getRedstonePowerFromNeighbors(pos);
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
				BlockPos facePos = pos.offset(direction);
				BlockState faceState = worldIn.getBlockState(facePos);
				wirePower = Math.max(wirePower, this.getWireSignal(facePos, faceState)); }

		 return Math.max(0, wirePower);
	 }
	
	private int getWireSignal(BlockPos pos, BlockState neighbor) {
		if (neighbor.getBlock() instanceof RedstoneWireBlock) { return neighbor.get(RedstoneWireBlock.POWER); }
		else { return (neighbor.getBlock() instanceof Truss_Cable) ? neighbor.get(POWER) : 0; }
	}
	
	private void checkCornerChangeAt(World worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		if (state.getBlock() instanceof Truss_Cable || state.getBlock() instanceof RedstoneWireBlock) {
			worldIn.notifyNeighborsOfStateChange(pos, this); //for 1.15.2

			for (Direction direction : Direction.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this); }
		}
	}

	private void updateNeighborsOfNeighboringWires(World worldIn, BlockPos pos) {
		for(Direction direction : Direction.values()) {
			this.checkCornerChangeAt(worldIn, pos.offset(direction)); }
	}
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean flag) {
		if (oldState.getBlock() != state.getBlock() && !worldIn.isRemote) {
			this.updatePowerStrength(worldIn, pos, state);

			for (Direction direction : Direction.values()) {
				this.updateNeighborsOfNeighboringWires(worldIn, pos.offset(direction)); }
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean flag) {
		if (!flag && state.getBlock() !=newState.getBlock()) {
			super.onReplaced(state, worldIn, pos, newState, flag);
			if (!worldIn.isRemote) {
				for (Direction direction : Direction.values()) {
					worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this); }

				this.updatePowerStrength(worldIn, pos, state);

				for (Direction direction : Direction.values()) {
					this.updateNeighborsOfNeighboringWires(worldIn, pos.offset(direction)); }
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean flag) {
		if (!worldIn.isRemote) {
			this.updatePowerStrength(worldIn, pos, state); }
	}

	@Override
	public int getStrongPower(BlockState state, IBlockReader worldIn, BlockPos pos, Direction face) {
		return !this.shouldSignal ? 0 : state.getWeakPower(worldIn, pos, face);
	}

	@Override
	public int getWeakPower(BlockState state, IBlockReader worldIn, BlockPos pos, Direction face) {
		if (this.shouldSignal) {
			int i = state.get(POWER);
			if (i == 0) { return 0; }
			else { return i; }
		}
		else { return 0; }
	}

	@Override
	public boolean canProvidePower(BlockState state) {
		return this.shouldSignal;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
				.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite());
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
		builder.add(H_FACING, WATERLOGGED, POWER);
	}
	
	/* Solid 0.0F -> 1.0F Transparent */
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
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

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return true;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_ctruss").applyTextStyle(TextFormatting.GRAY));
	}
}
