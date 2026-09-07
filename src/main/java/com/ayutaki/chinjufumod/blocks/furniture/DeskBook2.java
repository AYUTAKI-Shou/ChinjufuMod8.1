package com.ayutaki.chinjufumod.blocks.furniture;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class DeskBook2 extends Base_DeskBook23 {

	private static final AxisAlignedBB SOUTH_1 = new AxisAlignedBB(8.5 * cw, 0.0 * cw, 2.0 * cw, 14.0 * cw, 8.0 * cw, 3.5 * cw);
	private static final AxisAlignedBB EAST_1 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 2.0 * cw, 3.5 * cw, 8.0 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_1 = new AxisAlignedBB(12.5 * cw, 0.0 * cw, 8.5 * cw, 14.0 * cw, 8.0 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_1 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 12.5 * cw, 7.5 * cw, 8.0 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_2 = new AxisAlignedBB(8.5 * cw, 0.0 * cw, 2.0 * cw, 14.0 * cw, 8.0 * cw, 5.0 * cw);
	private static final AxisAlignedBB EAST_2 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 2.0 * cw, 5.0 * cw, 8.0 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_2 = new AxisAlignedBB(11.0 * cw, 0.0 * cw, 8.5 * cw, 14.0 * cw, 8.0 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_2 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 11.0 * cw, 7.5 * cw, 8.0 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_3 = new AxisAlignedBB(8.5 * cw, 0.0 * cw, 2.0 * cw, 14.0 * cw, 8.0 * cw, 6.5 * cw);
	private static final AxisAlignedBB EAST_3 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 2.0 * cw, 6.5 * cw, 8.0 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_3 = new AxisAlignedBB(9.5 * cw, 0.0 * cw, 8.5 * cw, 14.0 * cw, 8.0 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_3 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 9.5 * cw, 7.5 * cw, 8.0 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_4 = new AxisAlignedBB(8.5 * cw, 0.0 * cw, 2.0 * cw, 14.0 * cw, 8.0 * cw, 8.0 * cw);
	private static final AxisAlignedBB EAST_4 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 2.0 * cw, 8.0 * cw, 8.0 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_4 = new AxisAlignedBB(8.0 * cw, 0.0 * cw, 8.5 * cw, 14.0 * cw, 8.0 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_4 = new AxisAlignedBB(2.0 * cw, 0.0 * cw, 8.0 * cw, 7.5 * cw, 8.0 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_1D = new AxisAlignedBB(8.5 * cw, -8.0 * cw, 2.0 * cw, 14.0 * cw, 0.01 * cw, 3.5 * cw);
	private static final AxisAlignedBB EAST_1D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 2.0 * cw, 3.5 * cw, 0.01 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_1D = new AxisAlignedBB(12.5 * cw, -8.0 * cw, 8.5 * cw, 14.0 * cw, 0.01 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_1D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 12.5 * cw, 7.5 * cw, 0.01 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_2D = new AxisAlignedBB(8.5 * cw, -8.0 * cw, 2.0 * cw, 14.0 * cw, 0.01 * cw, 5.0 * cw);
	private static final AxisAlignedBB EAST_2D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 2.0 * cw, 5.0 * cw, 0.01 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_2D = new AxisAlignedBB(11.0 * cw, -8.0 * cw, 8.5 * cw, 14.0 * cw, 0.01 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_2D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 11.0 * cw, 7.5 * cw, 0.01 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_3D = new AxisAlignedBB(8.5 * cw, -8.0 * cw, 2.0 * cw, 14.0 * cw, 0.01 * cw, 6.5 * cw);
	private static final AxisAlignedBB EAST_3D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 2.0 * cw, 6.5 * cw, 0.01 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_3D = new AxisAlignedBB(9.5 * cw, -8.0 * cw, 8.5 * cw, 14.0 * cw, 0.01 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_3D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 9.5 * cw, 7.5 * cw, 0.01 * cw, 14.0 * cw);
	
	private static final AxisAlignedBB SOUTH_4D = new AxisAlignedBB(8.5 * cw, -8.0 * cw, 2.0 * cw, 14.0 * cw, 0.01 * cw, 8.0 * cw);
	private static final AxisAlignedBB EAST_4D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 2.0 * cw, 8.0 * cw, 0.01 * cw, 7.5 * cw);
	private static final AxisAlignedBB WEST_4D = new AxisAlignedBB(8.0 * cw, -8.0 * cw, 8.5 * cw, 14.0 * cw, 0.01 * cw, 14.0 * cw);
	private static final AxisAlignedBB NORTH_4D = new AxisAlignedBB(2.0 * cw, -8.0 * cw, 8.0 * cw, 7.5 * cw, 0.01 * cw, 14.0 * cw);
	
	public DeskBook2(String name) {
		super(name);
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		AxisAlignedBB NORTH_S = (i == 1)? NORTH_1 : ((i == 2)? NORTH_2 : ((i == 3)? NORTH_3 : NORTH_4));
		AxisAlignedBB SOUTH_S = (i == 1)? SOUTH_1 : ((i == 2)? SOUTH_2 : ((i == 3)? SOUTH_3 : SOUTH_4));
		AxisAlignedBB EAST_S = (i == 1)? EAST_1 : ((i == 2)? EAST_2 : ((i == 3)? EAST_3 : EAST_4));
		AxisAlignedBB WEST_S = (i == 1)? WEST_1 : ((i == 2)? WEST_2 : ((i == 3)? WEST_3 : WEST_4));
		
		AxisAlignedBB NORTH_SD = (i == 1)? NORTH_1D : ((i == 2)? NORTH_2D : ((i == 3)? NORTH_3D : NORTH_4D));
		AxisAlignedBB SOUTH_SD = (i == 1)? SOUTH_1D : ((i == 2)? SOUTH_2D : ((i == 3)? SOUTH_3D : SOUTH_4D));
		AxisAlignedBB EAST_SD = (i == 1)? EAST_1D : ((i == 2)? EAST_2D : ((i == 3)? EAST_3D : EAST_4D));
		AxisAlignedBB WEST_SD = (i == 1)? WEST_1D : ((i == 2)? WEST_2D : ((i == 3)? WEST_3D : WEST_4D));
		
		switch (direction) {
		case NORTH:
		default: 
			return notDown? NORTH_S : NORTH_SD;
		
		case SOUTH: 
			return notDown? SOUTH_S : SOUTH_SD;
			
		case EAST: 
			return notDown? EAST_S : EAST_SD;
			
		case WEST: 
			return notDown? WEST_S : WEST_SD;
		}
	}
}
