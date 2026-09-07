package com.ayutaki.chinjufumod.blocks.unitblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class LowDesk extends BaseUnitBlock {
	/* Collision */
	private static final VoxelShape TTTT = Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D);

	private static final VoxelShape FFFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D));

	private static final VoxelShape TTFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 3.0D, 6.0D, 16.0D));
	private static final VoxelShape FTFT = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(13.0D, 0.0D, 13.0D, 16.0D, 6.0D, 16.0D));
	private static final VoxelShape TFTF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 6.0D, 3.0D));
	private static final VoxelShape FFTT = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 6.0D, 3.0D));

	private static final VoxelShape FTFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D));
	private static final VoxelShape FFTF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D));
	private static final VoxelShape TFFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 6.0D, 2.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 6.0D, 16.0D));
	private static final VoxelShape FFFT = VoxelShapes.or(Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 6.0D, 2.0D),
			Block.makeCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 6.0D, 16.0D));

	public LowDesk(Block.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean east = state.get(EAST).booleanValue();
		boolean north = state.get(NORTH).booleanValue();
		boolean south = state.get(SOUTH).booleanValue();
		boolean west = state.get(WEST).booleanValue();

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
