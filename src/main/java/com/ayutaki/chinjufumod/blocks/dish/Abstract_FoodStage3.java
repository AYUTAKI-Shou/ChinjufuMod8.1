package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class Abstract_FoodStage3 extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_1_3 = PropertyInteger.create("stage", 1, 3);
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public Abstract_FoodStage3(String name) {
		super(name);
		
		setDefaultState(this.blockState.getBaseState().withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(STAGE_1_3, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}
	
	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(STAGE_1_3)).intValue() - 1 << 2;
		return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(STAGE_1_3, Integer.valueOf(1 + (meta >> 2)));
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (3 - ((Integer)state.getValue(STAGE_1_3)).intValue()) * 2;
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, H_FACING, STAGE_1_3 });
	}
}
