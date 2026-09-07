package com.ayutaki.chinjufumod.blocks.harbor;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_6FaceWater;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.ExperimentalRedstoneUtils;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.ticks.TickPriority;

public abstract class AbstractAmp extends Abstract_6FaceWater {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private int AMP_MAX = 15;
	
	public AbstractAmp(BlockBehaviour.Properties props) {
		super(props);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return true;
	}

	@Override
	protected void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		boolean flag = state.getValue(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		if (flag && !turnOn) {
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(false)), 2); } 
		
		else if (!flag) {
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(true)), 2);
			if (!turnOn) {
				worldIn.scheduleTick(pos, this, 2, TickPriority.VERY_HIGH); }
		}
	}

	@Override
	protected int getDirectSignal(BlockState state, BlockGetter worldIn, BlockPos pos, Direction side) {
		return state.getSignal(worldIn, pos, side);
	}

	@Override
	protected int getSignal(BlockState state, BlockGetter worldIn, BlockPos pos, Direction side) {
		if (!state.getValue(POWERED)) {
			return 0; }
		
		else {
			return state.getValue(FACING) == side ? this.getOutputSignal(worldIn, pos, state) : 0; }
	}

	@Override
	protected void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, @Nullable Orientation ori, boolean flag) {
		if (state.canSurvive(worldIn, pos)) {
			this.checkTickOnNeighbor(worldIn, pos, state); }
		
		else {
			BlockEntity tileEntity = state.hasBlockEntity() ? worldIn.getBlockEntity(pos) : null;
			dropResources(state, worldIn, pos, tileEntity);
			worldIn.removeBlock(pos, false);

			for (Direction direction : Direction.values()) {
				worldIn.updateNeighborsAt(pos.relative(direction), this); }
		}
	}

	protected void checkTickOnNeighbor(Level worldIn, BlockPos pos, BlockState state) {
		boolean powered = state.getValue(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		
		if (powered != turnOn && !worldIn.getBlockTicks().willTickThisTick(pos, this)) {
			TickPriority tickpriority = TickPriority.HIGH;
			if (this.shouldPrioritize(worldIn, pos, state)) {
				tickpriority = TickPriority.EXTREMELY_HIGH; }
			
			else if (powered) {
				tickpriority = TickPriority.VERY_HIGH; }

			worldIn.scheduleTick(pos, this, 2, tickpriority);
		}
	}

	protected boolean shouldTurnOn(Level worldIn, BlockPos pos, BlockState state) {
		return this.getInputSignal(worldIn, pos, state) > 0;
	}

	protected int getInputSignal(Level worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING);
		BlockPos facePos = pos.relative(direction);
		
		int i = worldIn.getSignal(facePos, direction);
		if (i >= AMP_MAX) { return i; }
		
		else {
			BlockState faceState = worldIn.getBlockState(facePos);
			Block block = faceState.getBlock();
			
			if (block instanceof RedStoneWireBlock) {
				return Math.max(i, faceState.getValue(RedStoneWireBlock.POWER)); }
			
			else { 
				return Math.max(i, faceState.is(Blocks.REDSTONE_WIRE) ? faceState.getValue(RedStoneWireBlock.POWER) : 0); }
		}
	}

	@Override
	protected boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		if (this.shouldTurnOn(worldIn, pos, state)) {
			worldIn.scheduleTick(pos, this, 1); }
	}

	@Override
	protected void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean flag) {
		this.updateNeighborsInFront(worldIn, pos, state);
	}

	@Override
	protected void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean flag) {
		if (!flag && !state.is(newState.getBlock())) {
			super.onRemove(state, worldIn, pos, newState, flag);
			this.updateNeighborsInFront(worldIn, pos, state); }
	}

	protected void updateNeighborsInFront(Level worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING);
		BlockPos facePos = pos.relative(direction.getOpposite());
		if (net.neoforged.neoforge.event.EventHooks.onNeighborNotify(worldIn, pos, worldIn.getBlockState(pos), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled())
			return;
		Orientation ori = ExperimentalRedstoneUtils.initialOrientation(worldIn, direction.getOpposite(), Direction.UP);
		worldIn.neighborChanged(facePos, this, ori);
		worldIn.updateNeighborsAtExceptFromFacing(facePos, this, direction, ori);
	}

	protected boolean sideInputDiodesOnly() {
		return false;
	}

	protected int getOutputSignal(BlockGetter level, BlockPos pos, BlockState state) {
		return AMP_MAX;
	}

	public static boolean isDiode(BlockState state) {
		return state.getBlock() instanceof AbstractAmp;
	}

	public boolean shouldPrioritize(BlockGetter level, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING).getOpposite();
		BlockState blockstate = level.getBlockState(pos.relative(direction));
		return isDiode(blockstate) && blockstate.getValue(FACING) != direction;
	}
}
