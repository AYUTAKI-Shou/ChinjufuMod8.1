package com.ayutaki.chinjufumod.blocks.furniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NoteBook extends Base_NoteBook {
	/* Collision */
	private static final VoxelShape SOUTH_1 = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 0.6D, 12.0D);
	private static final VoxelShape EAST_1 = Block.box(4.0D, 0.0D, 5.0D, 12.0D, 0.6D, 11.0D);
	private static final VoxelShape WEST_1 = Block.box(4.0D, 0.0D, 5.0D, 12.0D, 0.6D, 11.0D);
	private static final VoxelShape NORTH_1 = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 0.6D, 12.0D);
	
	private static final VoxelShape SOUTH_HB = Block.box(1.75D, 0.0D, 4.0D, 14.25D, 0.5D, 12.0D);
	private static final VoxelShape EAST_HB = Block.box(4.0D, 0.0D, 1.75D, 12.0D, 0.5D, 14.25D);
	private static final VoxelShape WEST_HB = Block.box(4.0D, 0.0D, 1.75D, 12.0D, 0.5D, 14.25D);
	private static final VoxelShape NORTH_HB = Block.box(1.75D, 0.0D, 4.0D, 14.25D, 0.5D, 12.0D);
	
	private static final VoxelShape SOUTH_1D = Block.box(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	private static final VoxelShape EAST_1D = Block.box(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape WEST_1D = Block.box(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape NORTH_1D = Block.box(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	
	private static final VoxelShape SOUTH_HBD = Block.box(1.75D, -8.0D, 4.0D, 14.25D, 0.01D, 12.0D);
	private static final VoxelShape EAST_HBD = Block.box(4.0D, -8.0D, 1.75D, 12.0D, 0.01D, 14.25D);
	private static final VoxelShape WEST_HBD = Block.box(4.0D, -8.0D, 1.75D, 12.0D, 0.01D, 14.25D);
	private static final VoxelShape NORTH_HBD = Block.box(1.75D, -8.0D, 4.0D, 14.25D, 0.01D, 12.0D);
	
	public NoteBook(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notHas = !((Boolean)state.getValue(HAS_BOOK)).booleanValue() && !((Boolean)state.getValue(OPEN)).booleanValue();
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

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
