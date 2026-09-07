package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.List;
import java.util.Set;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Cable extends Regi_addState {
	/* Property */
	public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
	private final Set<BlockPos> blocksNeedingUpdate = Sets.<BlockPos>newHashSet();
	private boolean canProvidePower = true;

	public Base_Cable(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(0);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.IRON;
	}

	/* Get Power */
	private IBlockState updatePowerStrength(World worldIn, BlockPos pos, IBlockState state) {
		state = this.calculateTargetStrength(worldIn, pos, state);
		List<BlockPos> list = Lists.newArrayList(this.blocksNeedingUpdate);
		this.blocksNeedingUpdate.clear();

		for (BlockPos blockpos : list) {
			worldIn.notifyNeighborsOfStateChange(blockpos, this, false); }

		return state;
	}

	private IBlockState calculateTargetStrength(World worldIn, BlockPos pos, IBlockState state) {
		this.canProvidePower = false;
		int bestPower = worldIn.isBlockIndirectlyGettingPowered(pos);
		this.canProvidePower = true;

		int wirePower = 0;
		for (EnumFacing direction : EnumFacing.values()) {
			BlockPos facePos = pos.offset(direction);
			wirePower = this.getWireSignal(worldIn, facePos, wirePower); }

		int max = Math.max(bestPower, wirePower);
		int limit = (max >= 15)? 15 : max;
		int fix = (limit >= 1)? 1 : 0;
		
		int i = limit - fix;
		IBlockState iblockState = state;
		
		if (((Integer)state.getValue(POWER)).intValue() != i) {
			if (worldIn.getBlockState(pos) == iblockState) {
				worldIn.setBlockState(pos, state.withProperty(POWER, Integer.valueOf(i)), 2); }

			this.blocksNeedingUpdate.add(pos);

			for (EnumFacing direction : EnumFacing.values()) {
				this.blocksNeedingUpdate.add(pos.offset(direction)); }
		}
		return state;
	}

	private int getWireSignal(World worldIn, BlockPos pos, int neighborPower) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block instanceof Base_Cable) {
			int i = ((Integer)worldIn.getBlockState(pos).getValue(POWER)).intValue();
			return (i > neighborPower)? i : neighborPower; }
		
		if (block instanceof BlockRedstoneWire) {
			int i = ((Integer)worldIn.getBlockState(pos).getValue(BlockRedstoneWire.POWER)).intValue();
			return (i > neighborPower)? i : neighborPower; }
		
		else {
			return neighborPower; }
	}
	
	private void updateNeighborsOfNeighboringWires(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block instanceof Base_Cable || block instanceof BlockRedstoneWire) {
			worldIn.notifyNeighborsOfStateChange(pos, this, false);

			for (EnumFacing direction : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this, false); }
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			this.updatePowerStrength(worldIn, pos, state);

			for (EnumFacing direction : EnumFacing.values()) {
				this.updateNeighborsOfNeighboringWires(worldIn, pos.offset(direction)); }
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		
		if (!worldIn.isRemote) {
			for (EnumFacing direction : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(direction), this, false); }

			this.updatePowerStrength(worldIn, pos, state);

			for (EnumFacing direction : EnumFacing.values()) {
				this.updateNeighborsOfNeighboringWires(worldIn, pos.offset(direction)); }
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			this.updatePowerStrength(worldIn, pos, state); }
	}
	
	@Override
	public int getStrongPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return !this.canProvidePower ? 0 : state.getWeakPower(worldIn, pos, side);
	}
	
	@Override
	public int getWeakPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (this.canProvidePower) {
			int i = ((Integer)state.getValue(POWER)).intValue();
			if (i == 0) { return 0; }
			else { return i; }
		}
		else { return 0; }
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return this.canProvidePower;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(POWER, Integer.valueOf(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(POWER)).intValue();
	}
	
	/* Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POWER });
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.SOLID;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
