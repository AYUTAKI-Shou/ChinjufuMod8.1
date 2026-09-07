package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Glass extends Base_ConnectHalf {

	/* Property */
	public static final PropertyInteger STAGE_1_15 = PropertyInteger.create("stage", 1, 15);

	public Base_Glass(String name) {
		super(name);

		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_1_15, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}

	/* Data value */
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_15)).intValue();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_15, Integer.valueOf(meta));
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (9 - ((Integer)state.getValue(STAGE_1_15)).intValue()) * 2;
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, STAGE_1_15 });
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
