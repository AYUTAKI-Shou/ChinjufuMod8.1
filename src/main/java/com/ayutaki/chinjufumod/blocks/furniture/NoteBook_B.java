package com.ayutaki.chinjufumod.blocks.furniture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NoteBook_B extends Base_NoteBook {
	/* Collision */
	private static final VoxelShape SOUTH_1 = Block.makeCuboidShape(5.0D, 0.0D, 4.0D, 11.0D, 1.9D, 12.0D);
	private static final VoxelShape EAST_1 = Block.makeCuboidShape(4.0D, 0.0D, 5.0D, 12.0D, 1.9D, 11.0D);
	private static final VoxelShape WEST_1 = Block.makeCuboidShape(4.0D, 0.0D, 5.0D, 12.0D, 1.9D, 11.0D);
	private static final VoxelShape NORTH_1 = Block.makeCuboidShape(5.0D, 0.0D, 4.0D, 11.0D, 1.9D, 12.0D);
	
	private static final VoxelShape SOUTH_HB = Block.makeCuboidShape(1.75D, 0.0D, 6.0D, 14.25D, 0.5D, 14.0D);
	private static final VoxelShape EAST_HB = Block.makeCuboidShape(6.0D, 0.0D, 1.75D, 14.0D, 0.5D, 14.25D);
	private static final VoxelShape WEST_HB = Block.makeCuboidShape(2.0D, 0.0D, 1.75D, 10.0D, 0.5D, 14.25D);
	private static final VoxelShape NORTH_HB = Block.makeCuboidShape(1.75D, 0.0D, 2.0D, 14.25D, 0.5D, 10.0D);
	
	private static final VoxelShape SOUTH_1D = Block.makeCuboidShape(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	private static final VoxelShape EAST_1D = Block.makeCuboidShape(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape WEST_1D = Block.makeCuboidShape(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape NORTH_1D = Block.makeCuboidShape(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	
	private static final VoxelShape SOUTH_HBD = Block.makeCuboidShape(1.75D, -8.0D, 6.0D, 14.25D, 0.01D, 14.0D);
	private static final VoxelShape EAST_HBD = Block.makeCuboidShape(6.0D, -8.0D, 1.75D, 14.0D, 0.01D, 14.25D);
	private static final VoxelShape WEST_HBD = Block.makeCuboidShape(2.0D, -8.0D, 1.75D, 10.0D, 0.01D, 14.25D);
	private static final VoxelShape NORTH_HBD = Block.makeCuboidShape(1.75D, -8.0D, 2.0D, 14.25D, 0.01D, 10.0D);
	
	public NoteBook_B(Block.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		boolean notHas = !((Boolean)state.get(HAS_BOOK)).booleanValue() && !((Boolean)state.get(OPEN)).booleanValue();
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default: 
			return notHas? (notDown? NORTH_1 : NORTH_1D) : (notDown? NORTH_HB : NORTH_HBD);
		
		case SOUTH: 
			return notHas? (notDown? SOUTH_1 : SOUTH_1D) : (notDown? SOUTH_HB : SOUTH_HBD);
			
		case EAST: 
			return notHas? (notDown? EAST_1 : EAST_1D) : (notDown? EAST_HB : EAST_HBD);
			
		case WEST: 
			return notHas? (notDown? WEST_1 : WEST_1D) : (notDown? WEST_HB : WEST_HBD);
		}
	}
}
