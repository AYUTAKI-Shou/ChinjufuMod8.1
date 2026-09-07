package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.Base_IronFace6;
import com.ayutaki.chinjufumod.registry.Harbor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractAmp extends Base_IronFace6 {

	protected final boolean isRepeaterPowered;
	private int AMP_MAX = 15;
	
	private static final AxisAlignedBB UD = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
	private static final AxisAlignedBB NS = new AxisAlignedBB(0.0625D, 0.0625D, 0.0D, 0.9375D, 0.9375D, 1.0D);
	private static final AxisAlignedBB WE = new AxisAlignedBB(0.0D, 0.0625D, 0.0625D, 1.0D, 0.9375D, 0.9375D);
	
	public AbstractAmp(boolean powered, String name) {
		super(name);
		this.isRepeaterPowered = powered;
	}

	/* Collision */
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing direction = state.getValue(FACING);
		switch (direction) {
		default: 
		case UP: return UD;
		case DOWN: return UD;
		case NORTH: return NS;
		case SOUTH: return NS;
		case WEST: return WE;
		case EAST: return WE;
		}
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos) {
		return true;
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		boolean flag = this.shouldBePowered(worldIn, pos, state);

		if (this.isRepeaterPowered && !flag) {
			worldIn.setBlockState(pos, this.getUnpoweredState(state), 2); }
		
		else if (!this.isRepeaterPowered) {
			worldIn.setBlockState(pos, this.getPoweredState(state), 2);

			if (!flag) {
				worldIn.updateBlockTick(pos, this.getPoweredState(state).getBlock(), 2, -1); }
		}
	}

	private IBlockState getPoweredState(IBlockState state) {
		EnumFacing direction = (EnumFacing)state.getValue(FACING);
		return this.takeTrue().getDefaultState().withProperty(FACING, direction);
	}
	
	private Block takeTrue() {
		if (this == Harbor_Blocks.AMP_white) {return Harbor_Blocks.AMP2_white; } 
		if (this == Harbor_Blocks.AMP_orange) { return Harbor_Blocks.AMP2_orange; } 
		if (this == Harbor_Blocks.AMP_magenta) { return Harbor_Blocks.AMP2_magenta; } 
		if (this == Harbor_Blocks.AMP_lightb) { return Harbor_Blocks.AMP2_lightb; } 
		if (this == Harbor_Blocks.AMP_yellow) { return Harbor_Blocks.AMP2_yellow; } 
		if (this == Harbor_Blocks.AMP_lime) { return Harbor_Blocks.AMP2_lime; } 
		if (this == Harbor_Blocks.AMP_pink) { return Harbor_Blocks.AMP2_pink; } 
		if (this == Harbor_Blocks.AMP_gray) { return Harbor_Blocks.AMP2_gray; } 
		if (this == Harbor_Blocks.AMP_cyan) { return Harbor_Blocks.AMP2_cyan; } 
		if (this == Harbor_Blocks.AMP_purple) { return Harbor_Blocks.AMP2_purple; }
		if (this == Harbor_Blocks.AMP_blue) { return Harbor_Blocks.AMP2_blue; } 
		if (this == Harbor_Blocks.AMP_brown) { return Harbor_Blocks.AMP2_brown; } 
		if (this == Harbor_Blocks.AMP_green) { return Harbor_Blocks.AMP2_green; } 
		if (this == Harbor_Blocks.AMP_red) { return Harbor_Blocks.AMP2_red; }
		if (this == Harbor_Blocks.AMP_black) { return Harbor_Blocks.AMP2_black; } 
		else { return Harbor_Blocks.AMP2; } 
	}
	
	private IBlockState getUnpoweredState(IBlockState state) {
		EnumFacing direction = (EnumFacing)state.getValue(FACING);
		return this.falseFalse().getDefaultState().withProperty(FACING, direction);
	}
	
	private Block falseFalse() {
		if (this == Harbor_Blocks.AMP2_white) {return Harbor_Blocks.AMP_white; } 
		if (this == Harbor_Blocks.AMP2_orange) { return Harbor_Blocks.AMP_orange; } 
		if (this == Harbor_Blocks.AMP2_magenta) { return Harbor_Blocks.AMP_magenta; } 
		if (this == Harbor_Blocks.AMP2_lightb) { return Harbor_Blocks.AMP_lightb; } 
		if (this == Harbor_Blocks.AMP2_yellow) { return Harbor_Blocks.AMP_yellow; } 
		if (this == Harbor_Blocks.AMP2_lime) { return Harbor_Blocks.AMP_lime; } 
		if (this == Harbor_Blocks.AMP2_pink) { return Harbor_Blocks.AMP_pink; } 
		if (this == Harbor_Blocks.AMP2_gray) { return Harbor_Blocks.AMP_gray; } 
		if (this == Harbor_Blocks.AMP2_cyan) { return Harbor_Blocks.AMP_cyan; } 
		if (this == Harbor_Blocks.AMP2_purple) { return Harbor_Blocks.AMP_purple; }
		if (this == Harbor_Blocks.AMP2_blue) { return Harbor_Blocks.AMP_blue; } 
		if (this == Harbor_Blocks.AMP2_brown) { return Harbor_Blocks.AMP_brown; } 
		if (this == Harbor_Blocks.AMP2_green) { return Harbor_Blocks.AMP_green; } 
		if (this == Harbor_Blocks.AMP2_red) { return Harbor_Blocks.AMP_red; }
		if (this == Harbor_Blocks.AMP2_black) { return Harbor_Blocks.AMP_black; } 
		else { return Harbor_Blocks.AMP; } 
	}

	protected boolean isPowered(IBlockState state) {
		return this.isRepeaterPowered;
	}

	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return blockState.getWeakPower(blockAccess, pos, side);
	}

	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (!this.isPowered(blockState)) {
			return 0; }
		
		else {
			return blockState.getValue(FACING) == side ? this.getActiveSignal(blockAccess, pos, blockState) : 0; }
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (this.canBlockStay(worldIn, pos)) {
			this.updateState(worldIn, pos, state); }
		
		else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);

			for (EnumFacing direction : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this, false);
			}
		}
	}

	protected void updateState(World worldIn, BlockPos pos, IBlockState state) {
		boolean flag = this.shouldBePowered(worldIn, pos, state);

		if (this.isRepeaterPowered != flag && !worldIn.isBlockTickPending(pos, this)) {
			int i = -1;

			if (this.isFacingTowardsRepeater(worldIn, pos, state)) { i = -3; }
			
			else if (this.isRepeaterPowered) { i = -2; }
			
			worldIn.updateBlockTick(pos, this, 2, i); }
	}

	protected boolean shouldBePowered(World worldIn, BlockPos pos, IBlockState state) {
		return this.getInputSignal(worldIn, pos, state) > 0;
	}

	protected int getInputSignal(World worldIn, BlockPos pos, IBlockState state) {
		EnumFacing direction = (EnumFacing)state.getValue(FACING);
		BlockPos facePos = pos.offset(direction);
		
		int i = worldIn.getRedstonePower(facePos, direction);
		if (i >= AMP_MAX) { return i; }

		else {
			IBlockState faceState = worldIn.getBlockState(facePos);
			Block block = faceState.getBlock();
			
			if (block instanceof BlockRedstoneWire) { 
				return Math.max(i, ((Integer)faceState.getValue(BlockRedstoneWire.POWER)).intValue()); }
			
			else {
				return Math.max(i, (block instanceof Truss_Cable) ? ((Integer)faceState.getValue(Truss_Cable.POWER)).intValue() : 0); }
		}
	}

	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (this.shouldBePowered(worldIn, pos, state)) {
			worldIn.scheduleUpdate(pos, this, 1); }
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.updateNeighborsInFront(worldIn, pos, state);
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!(state.getBlock() instanceof Truss_Cable)) {
			super.breakBlock(worldIn, pos, state);
			this.updateNeighborsInFront(worldIn, pos, state); }
	}
	
	protected void updateNeighborsInFront(World worldIn, BlockPos pos, IBlockState state) {
		EnumFacing direction = (EnumFacing)state.getValue(FACING);
		BlockPos blockpos = pos.offset(direction.getOpposite());
		if(net.minecraftforge.event.ForgeEventFactory.onNeighborNotify(worldIn, pos, worldIn.getBlockState(pos), java.util.EnumSet.of(direction.getOpposite()), false).isCanceled())
			return;
		worldIn.neighborChanged(blockpos, this, pos);
		worldIn.notifyNeighborsOfStateExcept(blockpos, this, direction);
	}

	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (this.isRepeaterPowered) {
			for (EnumFacing direction : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this, false); }
		}
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}

	protected boolean isAlternateInput(IBlockState state) {
		return state.canProvidePower();
	}

	protected int getActiveSignal(IBlockAccess worldIn, BlockPos pos, IBlockState state) {
		return AMP_MAX;
	}

	public static boolean isDiode(IBlockState state) {
		return state.getBlock() instanceof AbstractAmp;
	}

	public boolean isSameDiode(IBlockState state) {
		return state.getBlock() instanceof AbstractAmp;
	}

	public boolean isFacingTowardsRepeater(World worldIn, BlockPos pos, IBlockState state) 	{
		EnumFacing direction = ((EnumFacing)state.getValue(FACING)).getOpposite();
		BlockPos blockpos = pos.offset(direction);

		if (isDiode(worldIn.getBlockState(blockpos))) {
			return worldIn.getBlockState(blockpos).getValue(FACING) != direction; }
		
		else {
			return false; }
	}

	public boolean isAssociatedBlock(Block other) {
		return this.isSameDiode(other.getDefaultState());
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* ======================================== FORGE START =====================================*/
	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
		if (super.rotateBlock(world, pos, axis)) {
			IBlockState state = world.getBlockState(pos);
			state = getUnpoweredState(state);
			world.setBlockState(pos, state);

			if (shouldBePowered(world, pos, state)) {
				world.scheduleUpdate(pos, this, 1);
			}
			return true;
		}
		return false;
	}
}
