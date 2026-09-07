package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.Base_6FaceWater;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public abstract class AbstractAmp extends Base_6FaceWater {
	
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private int AMP_MAX = 15;
	
	protected AbstractAmp(AbstractBlock.Properties props) {
		super(props);
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return true;
	}

	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		boolean powered = state.getValue(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		if (powered && !turnOn) {
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(false)), 2); }
		
		else if (!powered) {
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(true)), 2);
			
			if (!turnOn) {
				worldIn.getBlockTicks().scheduleTick(pos, this, 2, TickPriority.VERY_HIGH); }
		}
	}

	public int getDirectSignal(BlockState state, IBlockReader worldIn, BlockPos pos, Direction face) {
		return state.getSignal(worldIn, pos, face);
	}

	public int getSignal(BlockState state, IBlockReader worldIn, BlockPos pos, Direction face) {
		if (!state.getValue(POWERED)) {
			return 0; }
		
		else {
			return state.getValue(FACING) == face ? this.getOutputSignal(worldIn, pos, state) : 0; }
	}

	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos facePos, boolean flag) {
		if (state.canSurvive(worldIn, pos)) {
			this.checkTickOnNeighbor(worldIn, pos, state); } 
		
		else {
			TileEntity tileEntity = state.hasTileEntity() ? worldIn.getBlockEntity(pos) : null;
			dropResources(state, worldIn, pos, tileEntity);
			worldIn.removeBlock(pos, false);

			for(Direction direction : Direction.values()) {
				worldIn.updateNeighborsAt(pos.relative(direction), this); }
		}
	}

	protected void checkTickOnNeighbor(World worldIn, BlockPos pos, BlockState state) {
		boolean powered = state.getValue(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		
		if (powered != turnOn && !worldIn.getBlockTicks().willTickThisTick(pos, this)) {
			TickPriority tickpriority = TickPriority.HIGH;
			if (this.shouldPrioritize(worldIn, pos, state)) {
				tickpriority = TickPriority.EXTREMELY_HIGH; }
			
			else if (powered) {
				tickpriority = TickPriority.VERY_HIGH; }
			
			worldIn.getBlockTicks().scheduleTick(pos, this, 2, tickpriority);
		}
	}

	protected boolean shouldTurnOn(World worldIn, BlockPos pos, BlockState state) {
		return this.getInputSignal(worldIn, pos, state) > 0;
	}

	protected int getInputSignal(World worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING);
		BlockPos facePos = pos.relative(direction);
		
		int i = worldIn.getSignal(facePos, direction);
		if (i >= AMP_MAX) { return i; }
		
		else {
			BlockState faceState = worldIn.getBlockState(facePos);
			Block block = faceState.getBlock();
			
			if (block instanceof RedstoneWireBlock) {
				return Math.max(i, faceState.getValue(RedstoneWireBlock.POWER)); }
			
			else { 
				return Math.max(i, (block instanceof Truss_Cable) ? faceState.getValue(Truss_Cable.POWER) : 0); }
		}
	}

	public boolean isSignalSource(BlockState state) {
		return true;
	}

	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack hStack) {
		if (this.shouldTurnOn(worldIn, pos, state)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 1); }
	}

	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState faceState, boolean flag) {
		this.updateNeighborsInFront(worldIn, pos, state);
	}

	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState faceState, boolean flag) {
		if (!flag && !state.is(faceState.getBlock())) {
			super.onRemove(state, worldIn, pos, faceState, flag);
			this.updateNeighborsInFront(worldIn, pos, state); }
	}

	protected void updateNeighborsInFront(World worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING);
		BlockPos facePos = pos.relative(direction.getOpposite());
		if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.getBlockState(pos), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled())
			return;
		worldIn.neighborChanged(facePos, this, pos);
		worldIn.updateNeighborsAtExceptFromFacing(facePos, this, direction);
	}

	protected boolean isAlternateInput(BlockState state) {
		return state.isSignalSource();
	}

	protected int getOutputSignal(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return AMP_MAX;
	}

	public static boolean isDiode(BlockState state) {
		return state.getBlock() instanceof AbstractAmp;
	}

	public boolean shouldPrioritize(IBlockReader worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.getValue(FACING).getOpposite();
		BlockState faceState = worldIn.getBlockState(pos.relative(direction));
		return isDiode(faceState) && faceState.getValue(FACING) != direction;
	}
}
