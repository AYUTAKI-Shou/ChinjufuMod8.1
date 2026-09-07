package com.ayutaki.chinjufumod.blocks.harbor;

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
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.TickPriority;

public abstract class AbstractAmp extends Abstract_6FaceWater {

	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private int AMP_MAX = 15;
	
	protected AbstractAmp(BlockBehaviour.Properties props) {
		super(props);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		return true;
	}

	@Override
	protected void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		boolean powered = state.getValue(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		if (powered && !turnOn) {
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(false)), 2); } 
		
		else if (!powered) {
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(true)), 2);
			
			if (!turnOn) {
				worldIn.scheduleTick(pos, this, 2, TickPriority.VERY_HIGH); }
		}
	}

	@Override
	protected int getDirectSignal(BlockState state, BlockGetter worldIn, BlockPos pos, Direction face) {
		return state.getSignal(worldIn, pos, face);
	}

	@Override
	protected int getSignal(BlockState state, BlockGetter worldIn, BlockPos pos, Direction face) {
		if (!state.getValue(POWERED)) {
			return 0; } 
		
		else {
			return state.getValue(FACING) == face ? this.getOutputSignal(worldIn, pos, state) : 0; }
	}

	@Override
	protected void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos facePos, boolean flag) {
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
				return Math.max(i, (block instanceof Truss_Cable) ? faceState.getValue(Truss_Cable.POWER) : 0); }
		}
	}

	@Override
	protected boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack hStack) {
		if (this.shouldTurnOn(worldIn, pos, state)) {
			worldIn.scheduleTick(pos, this, 1); }
	}

	@Override
	protected void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState faceState, boolean flag) {
		this.updateNeighborsInFront(worldIn, pos, state);
	}

	@Override
	protected void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState faceState, boolean flag) {
		if (!flag && !state.is(faceState.getBlock())) {
			super.onRemove(state, worldIn, pos, faceState, flag);
			this.updateNeighborsInFront(worldIn, pos, state); }
	}

	protected void updateNeighborsInFront(Level worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING);
		BlockPos facePos = pos.relative(direction.getOpposite());
		if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.getBlockState(pos), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled()) {
			return; }
		worldIn.neighborChanged(facePos, this, pos);
		worldIn.updateNeighborsAtExceptFromFacing(facePos, this, direction);
	}

	protected boolean sideInputDiodesOnly() {
		return false;
	}

	protected int getOutputSignal(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return AMP_MAX;
	}

	public static boolean isDiode(BlockState state) {
		return state.getBlock() instanceof AbstractAmp;
	}

	public boolean shouldPrioritize(BlockGetter worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING).getOpposite();
		BlockState faceState = worldIn.getBlockState(pos.relative(direction));
		return isDiode(faceState) && faceState.getValue(FACING) != direction;
	}
}
