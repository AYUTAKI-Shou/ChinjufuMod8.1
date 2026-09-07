package com.ayutaki.chinjufumod.blocks.unitblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LowDesk extends BaseUnitBlock {
	/* Collision */
	private static final VoxelShape TTTT = Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D);

	private static final VoxelShape FFFF = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D),
			Block.box(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D),
			Block.box(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D));

	private static final VoxelShape TTFF = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 13.0D, 3.0D, 6.0D, 16.0D));
	private static final VoxelShape FTFT = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(13.0D, 0.0D, 13.0D, 16.0D, 6.0D, 16.0D));
	private static final VoxelShape TFTF = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 3.0D, 6.0D, 3.0D));
	private static final VoxelShape FFTT = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(13.0D, 0.0D, 0.0D, 16.0D, 6.0D, 3.0D));

	private static final VoxelShape FTFF = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D),
			Block.box(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D));
	private static final VoxelShape FFTF = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D),
			Block.box(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D));
	private static final VoxelShape TFFF = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D));
	private static final VoxelShape FFFT = Shapes.or(Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D),
			Block.box(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D));
	
	public LowDesk(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean east = state.getValue(EAST).booleanValue();
		boolean north = state.getValue(NORTH).booleanValue();
		boolean south = state.getValue(SOUTH).booleanValue();
		boolean west = state.getValue(WEST).booleanValue();

		if (east == false && north == false && south == false && west == false) { return FFFF; }
		
		if (east == true && north == true && south == false && west == false) { return TTFF; }
		if (east == false && north == true && south == false && west == true) { return FTFT; }
		if (east == true && north == false && south == true && west == false) { return TFTF; }
		if (east == false && north == false && south == true && west == true) { return FFTT; }

		if (east == false && north == true && south == false && west == false) { return FTFF; }
		if (east == true && north == false && south == false && west == false) { return TFFF; }
		if (east == false && north == false && south == false && west == true) { return FFFT; }
		if (east == false && north == false && south == true && west == false) { return FFTF; }

		else { return TTTT; }
	}
}
