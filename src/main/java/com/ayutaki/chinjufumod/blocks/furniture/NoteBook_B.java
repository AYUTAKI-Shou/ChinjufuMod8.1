package com.ayutaki.chinjufumod.blocks.furniture;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NoteBook_B extends Base_NoteBook {
	/* Collision */
	private static final VoxelShape SOUTH_3 = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 1.9D, 12.0D);
	private static final VoxelShape EAST_3 = Block.box(4.0D, 0.0D, 5.0D, 12.0D, 1.9D, 11.0D);
	private static final VoxelShape WEST_3 = Block.box(4.0D, 0.0D, 5.0D, 12.0D, 1.9D, 11.0D);
	private static final VoxelShape NORTH_3 = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 1.9D, 12.0D);
	
	private static final VoxelShape SOUTH_4HB = Block.box(1.75D, 0.0D, 6.0D, 14.25D, 0.5D, 14.0D);
	private static final VoxelShape EAST_4HB = Block.box(6.0D, 0.0D, 1.75D, 14.0D, 0.5D, 14.25D);
	private static final VoxelShape WEST_4HB = Block.box(2.0D, 0.0D, 1.75D, 10.0D, 0.5D, 14.25D);
	private static final VoxelShape NORTH_4HB = Block.box(1.75D, 0.0D, 2.0D, 14.25D, 0.5D, 10.0D);
	
	private static final VoxelShape SOUTH_3D = Block.box(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	private static final VoxelShape EAST_3D = Block.box(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape WEST_3D = Block.box(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape NORTH_3D = Block.box(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	
	private static final VoxelShape SOUTH_4HBD = Block.box(1.75D, -8.0D, 6.0D, 14.25D, 0.01D, 14.0D);
	private static final VoxelShape EAST_4HBD = Block.box(6.0D, -8.0D, 1.75D, 14.0D, 0.01D, 14.25D);
	private static final VoxelShape WEST_4HBD = Block.box(2.0D, -8.0D, 1.75D, 10.0D, 0.01D, 14.25D);
	private static final VoxelShape NORTH_4HBD = Block.box(1.75D, -8.0D, 2.0D, 14.25D, 0.01D, 10.0D);

	public NoteBook_B(AbstractBlock.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notHas = !((Boolean)state.getValue(HAS_BOOK)).booleanValue() && !((Boolean)state.getValue(OPEN)).booleanValue();
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default: 
			return notHas? (notDown? NORTH_3 : NORTH_3D) : (notDown? NORTH_4HB : NORTH_4HBD);
		
		case SOUTH: 
			return notHas? (notDown? SOUTH_3 : SOUTH_3D) : (notDown? SOUTH_4HB : SOUTH_4HBD);
			
		case EAST: 
			return notHas? (notDown? EAST_3 : EAST_3D) : (notDown? EAST_4HB : EAST_4HBD);
			
		case WEST: 
			return notHas? (notDown? WEST_3 : WEST_3D) : (notDown? WEST_4HB : WEST_4HBD);
		}
	}
}
