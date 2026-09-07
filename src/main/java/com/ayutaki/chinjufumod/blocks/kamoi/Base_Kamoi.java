package com.ayutaki.chinjufumod.blocks.kamoi;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Base_Kamoi extends BaseStage4_Face {

	private static final AxisAlignedBB BOTTOM = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	private static final AxisAlignedBB TOP = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB SOUTH_2 = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.5, 1.0, 1.0);
	private static final AxisAlignedBB NORTH_2 = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.5, 1.0, 1.0);
	private static final AxisAlignedBB WEST_2 = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.5, 1.0, 1.0);
	private static final AxisAlignedBB EAST_2 = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.5, 1.0, 1.0);

	public Base_Kamoi(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? BOTTOM : ((i == 2)? NORTH_2 : ((i == 3)? FULL : TOP));
			
		case SOUTH:
			return (i == 1)? BOTTOM : ((i == 2)? SOUTH_2 : ((i == 3)? FULL : TOP));

		case EAST:
			return (i == 1)? BOTTOM : ((i == 2)? EAST_2 : ((i == 3)? FULL : TOP));

		case WEST:
			return (i == 1)? BOTTOM : ((i == 2)? WEST_2 : ((i == 3)? FULL : TOP));
		}
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
