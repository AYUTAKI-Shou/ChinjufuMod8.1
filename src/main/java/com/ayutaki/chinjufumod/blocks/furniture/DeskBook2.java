package com.ayutaki.chinjufumod.blocks.furniture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class DeskBook2 extends Base_DeskBook23 {
	/* Collision */
	private static final VoxelShape SOUTH_1 = Block.makeCuboidShape(8.5D, 0.0D, 2.0D, 14.0D, 8.0D, 3.5D);
	private static final VoxelShape EAST_1 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 3.5D, 8.0D, 7.5D);
	private static final VoxelShape WEST_1 = Block.makeCuboidShape(12.5D, 0.0D, 8.5D, 14.0D, 8.0D, 14.0D);
	private static final VoxelShape NORTH_1 = Block.makeCuboidShape(2.0D, 0.0D, 12.5D, 7.5D, 8.0D, 14.0D);
	
	private static final VoxelShape SOUTH_2 = Block.makeCuboidShape(8.5D, 0.0D, 2.0D, 14.0D, 8.0D, 5.0D);
	private static final VoxelShape EAST_2 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 5.0D, 8.0D, 7.5D);
	private static final VoxelShape WEST_2 = Block.makeCuboidShape(11.0D, 0.0D, 8.5D, 14.0D, 8.0D, 14.0D);
	private static final VoxelShape NORTH_2 = Block.makeCuboidShape(2.0D, 0.0D, 11.0D, 7.5D, 8.0D, 14.0D);
	
	private static final VoxelShape SOUTH_3 = Block.makeCuboidShape(8.5D, 0.0D, 2.0D, 14.0D, 8.0D, 6.5D);
	private static final VoxelShape EAST_3 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 6.5D, 8.0D, 7.5D);
	private static final VoxelShape WEST_3 = Block.makeCuboidShape(9.5D, 0.0D, 8.5D, 14.0D, 8.0D, 14.0D);
	private static final VoxelShape NORTH_3 = Block.makeCuboidShape(2.0D, 0.0D, 9.5D, 7.5D, 8.0D, 14.0D);
	
	private static final VoxelShape SOUTH_4 = Block.makeCuboidShape(8.5D, 0.0D, 2.0D, 14.0D, 8.0D, 8.0D);
	private static final VoxelShape EAST_4 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 8.0D, 8.0D, 7.5D);
	private static final VoxelShape WEST_4 = Block.makeCuboidShape(8.0D, 0.0D, 8.5D, 14.0D, 8.0D, 14.0D);
	private static final VoxelShape NORTH_4 = Block.makeCuboidShape(2.0D, 0.0D, 8.0D, 7.5D, 8.0D, 14.0D);
	
	private static final VoxelShape SOUTH_1D = Block.makeCuboidShape(8.5D, -8.0D, 2.0D, 14.0D, 0.01D, 3.5D);
	private static final VoxelShape EAST_1D = Block.makeCuboidShape(2.0D, -8.0D, 2.0D, 3.5D, 0.01D, 7.5D);
	private static final VoxelShape WEST_1D = Block.makeCuboidShape(12.5D, -8.0D, 8.5D, 14.0D, 0.01D, 14.0D);
	private static final VoxelShape NORTH_1D = Block.makeCuboidShape(2.0D, -8.0D, 12.5D, 7.5D, 0.01D, 14.0D);
	
	private static final VoxelShape SOUTH_2D = Block.makeCuboidShape(8.5D, -8.0D, 2.0D, 14.0D, 0.01D, 5.0D);
	private static final VoxelShape EAST_2D = Block.makeCuboidShape(2.0D, -8.0D, 2.0D, 5.0D, 0.01D, 7.5D);
	private static final VoxelShape WEST_2D = Block.makeCuboidShape(11.0D, -8.0D, 8.5D, 14.0D, 0.01D, 14.0D);
	private static final VoxelShape NORTH_2D = Block.makeCuboidShape(2.0D, -8.0D, 11.0D, 7.5D, 0.01D, 14.0D);
	
	private static final VoxelShape SOUTH_3D = Block.makeCuboidShape(8.5D, -8.0D, 2.0D, 14.0D, 0.01D, 6.5D);
	private static final VoxelShape EAST_3D = Block.makeCuboidShape(2.0D, -8.0D, 2.0D, 6.5D, 0.01D, 7.5D);
	private static final VoxelShape WEST_3D = Block.makeCuboidShape(9.5D, -8.0D, 8.5D, 14.0D, 0.01D, 14.0D);
	private static final VoxelShape NORTH_3D = Block.makeCuboidShape(2.0D, -8.0D, 9.5D, 7.5D, 0.01D, 14.0D);
	
	private static final VoxelShape SOUTH_4D = Block.makeCuboidShape(8.5D, -8.0D, 2.0D, 14.0D, 0.01D, 8.0D);
	private static final VoxelShape EAST_4D = Block.makeCuboidShape(2.0D, -8.0D, 2.0D, 8.0D, 0.01D, 7.5D);
	private static final VoxelShape WEST_4D = Block.makeCuboidShape(8.0D, -8.0D, 8.5D, 14.0D, 0.01D, 14.0D);
	private static final VoxelShape NORTH_4D = Block.makeCuboidShape(2.0D, -8.0D, 8.0D, 7.5D, 0.01D, 14.0D);
	
	public DeskBook2(Block.Properties props) {
		super(props);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();
		int i = state.get(STAGE_1_4);

		VoxelShape NORTH_S = (i == 1)? NORTH_1 : ((i == 2)? NORTH_2 : ((i == 3)? NORTH_3 : NORTH_4));
		VoxelShape SOUTH_S = (i == 1)? SOUTH_1 : ((i == 2)? SOUTH_2 : ((i == 3)? SOUTH_3 : SOUTH_4));
		VoxelShape EAST_S = (i == 1)? EAST_1 : ((i == 2)? EAST_2 : ((i == 3)? EAST_3 : EAST_4));
		VoxelShape WEST_S = (i == 1)? WEST_1 : ((i == 2)? WEST_2 : ((i == 3)? WEST_3 : WEST_4));
		
		VoxelShape NORTH_SD = (i == 1)? NORTH_1D : ((i == 2)? NORTH_2D : ((i == 3)? NORTH_3D : NORTH_4D));
		VoxelShape SOUTH_SD = (i == 1)? SOUTH_1D : ((i == 2)? SOUTH_2D : ((i == 3)? SOUTH_3D : SOUTH_4D));
		VoxelShape EAST_SD = (i == 1)? EAST_1D : ((i == 2)? EAST_2D : ((i == 3)? EAST_3D : EAST_4D));
		VoxelShape WEST_SD = (i == 1)? WEST_1D : ((i == 2)? WEST_2D : ((i == 3)? WEST_3D : WEST_4D));
		
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
