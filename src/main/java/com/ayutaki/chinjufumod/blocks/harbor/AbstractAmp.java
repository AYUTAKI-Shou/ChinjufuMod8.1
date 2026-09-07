package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.Base_6FaceWater;

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
	
	protected AbstractAmp(Block.Properties props) {
		super(props);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return true;
	}

	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		boolean powered = state.get(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		if (powered && !turnOn) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(false)), 2); } 
		
		else if (!powered) {
			worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(true)), 2);
			if (!turnOn) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, 2, TickPriority.VERY_HIGH); }
		}
	}

	public int getStrongPower(BlockState state, IBlockReader worldIn, BlockPos pos, Direction side) {
		return state.getWeakPower(worldIn, pos, side);
	}

	public int getWeakPower(BlockState state, IBlockReader worldIn, BlockPos pos, Direction side) {
		if (!state.get(POWERED)) {
			return 0; } 
		
		else {
			return state.get(FACING) == side ? this.getOutputSignal(worldIn, pos, state) : 0; }
	}

	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (state.isValidPosition(worldIn, pos)) {
			this.checkTickOnNeighbor(worldIn, pos, state); } 
		
		else {
			TileEntity tileEntity = state.hasTileEntity() ? worldIn.getTileEntity(pos) : null;
			spawnDrops(state, worldIn, pos, tileEntity);
			worldIn.removeBlock(pos, false);

			for(Direction direction : Direction.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this); }
		}
	}

	protected void checkTickOnNeighbor(World worldIn, BlockPos pos, BlockState state) {
		boolean powered = state.get(POWERED);
		boolean turnOn = this.shouldTurnOn(worldIn, pos, state);
		
		if (powered != turnOn && !worldIn.getPendingBlockTicks().isTickPending(pos, this)) {
			TickPriority tickpriority = TickPriority.HIGH;
			if (this.isFacingTowardsRepeater(worldIn, pos, state)) {
				tickpriority = TickPriority.EXTREMELY_HIGH; } 
			
			else if (powered) {
				tickpriority = TickPriority.VERY_HIGH; }

			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 2, tickpriority); }
	}

	protected boolean shouldTurnOn(World worldIn, BlockPos pos, BlockState state) {
		return this.getInputSignal(worldIn, pos, state) > 0;
	}

	protected int getInputSignal(World worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.get(FACING);
		BlockPos facePos = pos.offset(direction);
		
		int i = worldIn.getRedstonePower(facePos, direction);
		if (i >= AMP_MAX) { return i; }
		
		else {
			BlockState faceState = worldIn.getBlockState(facePos);
			Block block = faceState.getBlock();
			
			if (block instanceof RedstoneWireBlock) {
				return Math.max(i, faceState.get(RedstoneWireBlock.POWER)); }
			
			else { 
				return Math.max(i, (block instanceof Truss_Cable) ? faceState.get(Truss_Cable.POWER) : 0); }
		}
	}

	public boolean canProvidePower(BlockState state) {
		return true;
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (this.shouldTurnOn(worldIn, pos, state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1); }
	}

	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		this.updateNeighborsInFront(worldIn, pos, state);
	}

	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!isMoving && state.getBlock() != newState.getBlock()) {
			super.onReplaced(state, worldIn, pos, newState, isMoving);
			this.updateNeighborsInFront(worldIn, pos, state); }
	}

	protected void updateNeighborsInFront(World worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.get(FACING);
		BlockPos facePos = pos.offset(direction.getOpposite());
		if (net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.getBlockState(pos), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled())
			return;
		worldIn.neighborChanged(facePos, this, pos);
		worldIn.notifyNeighborsOfStateExcept(facePos, this, direction);
	}

	protected boolean isAlternateInput(BlockState state) {
		return state.canProvidePower();
	}
	
	protected int getOutputSignal(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return AMP_MAX;
	}

	public static boolean isDiode(BlockState state) {
		return state.getBlock() instanceof AbstractAmp;
	}

	public boolean isFacingTowardsRepeater(IBlockReader worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.get(FACING).getOpposite();
		BlockState faceState = worldIn.getBlockState(pos.offset(direction));
		return isDiode(faceState) && faceState.get(FACING) != direction;
	}
}
