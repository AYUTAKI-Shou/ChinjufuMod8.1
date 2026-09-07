package com.ayutaki.chinjufumod.blocks.furniture;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class NoteBook2 extends Base_NoteBook23 {
	/* Collision */
	private static final VoxelShape SOUTH_1 = Block.box(8.0D, 0.0D, 2.0D, 14.0D, 0.7D, 10.0D);
	private static final VoxelShape EAST_1 = Block.box(2.0D, 0.0D, 2.0D, 10.0D, 0.7D, 8.0D);
	private static final VoxelShape WEST_1 = Block.box(6.0D, 0.0D, 8.0D, 14.0D, 0.7D, 14.0D);
	private static final VoxelShape NORTH_1 = Block.box(2.0D, 0.0D, 6.0D, 8.0D, 0.7D, 14.0D);
	
	private static final VoxelShape SOUTH_2 = Block.box(8.0D, 0.0D, 2.0D, 14.0D, 1.3D, 10.0D);
	private static final VoxelShape EAST_2 = Block.box(2.0D, 0.0D, 2.0D, 10.0D, 1.3D, 8.0D);
	private static final VoxelShape WEST_2 = Block.box(6.0D, 0.0D, 8.0D, 14.0D, 1.3D, 14.0D);
	private static final VoxelShape NORTH_2 = Block.box(2.0D, 0.0D, 6.0D, 8.0D, 1.3D, 14.0D);
	
	private static final VoxelShape SOUTH_3 = Block.box(8.0D, 0.0D, 2.0D, 14.0D, 1.9D, 10.0D);
	private static final VoxelShape EAST_3 = Block.box(2.0D, 0.0D, 2.0D, 10.0D, 1.9D, 8.0D);
	private static final VoxelShape WEST_3 = Block.box(6.0D, 0.0D, 8.0D, 14.0D, 1.9D, 14.0D);
	private static final VoxelShape NORTH_3 = Block.box(2.0D, 0.0D, 6.0D, 8.0D, 1.9D, 14.0D);
	
	private static final VoxelShape SOUTH_4 = Block.box(8.0D, 0.0D, 2.0D, 14.0D, 2.5D, 10.0D);
	private static final VoxelShape EAST_4 = Block.box(2.0D, 0.0D, 2.0D, 10.0D, 2.5D, 8.0D);
	private static final VoxelShape WEST_4 = Block.box(6.0D, 0.0D, 8.0D, 14.0D, 2.5D, 14.0D);
	private static final VoxelShape NORTH_4 = Block.box(2.0D, 0.0D, 6.0D, 8.0D, 2.5D, 14.0D);
	
	private static final VoxelShape SOUTH_D = Block.box(8.0D, -8.0D, 2.0D, 14.0D, 0.01D, 10.0D);
	private static final VoxelShape EAST_D = Block.box(2.0D, -8.0D, 2.0D, 10.0D, 0.01D, 8.0D);
	private static final VoxelShape WEST_D = Block.box(6.0D, -8.0D, 8.0D, 14.0D, 0.01D, 14.0D);
	private static final VoxelShape NORTH_D = Block.box(2.0D, -8.0D, 6.0D, 8.0D, 0.01D, 14.0D);

	public NoteBook2(AbstractBlock.Properties props) {
		super(props);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		int i = state.getValue(STAGE_1_4);

		VoxelShape NORTH_S = (i == 1)? NORTH_1 : ((i == 2)? NORTH_2 : ((i == 3)? NORTH_3 : NORTH_4));
		VoxelShape SOUTH_S = (i == 1)? SOUTH_1 : ((i == 2)? SOUTH_2 : ((i == 3)? SOUTH_3 : SOUTH_4));
		VoxelShape EAST_S = (i == 1)? EAST_1 : ((i == 2)? EAST_2 : ((i == 3)? EAST_3 : EAST_4));
		VoxelShape WEST_S = (i == 1)? WEST_1 : ((i == 2)? WEST_2 : ((i == 3)? WEST_3 : WEST_4));

		switch (direction) {
		case NORTH:
		default: 
			return notDown? NORTH_S : NORTH_D;
		
		case SOUTH: 
			return notDown? SOUTH_S : SOUTH_D;
			
		case EAST: 
			return notDown? EAST_S : EAST_D;
			
		case WEST: 
			return notDown? WEST_S : WEST_D;
		}
	}
}
