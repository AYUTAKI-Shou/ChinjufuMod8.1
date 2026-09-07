package com.ayutaki.chinjufumod.blocks.unitblock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class UnitDesk extends BaseUnitBlock {
	/* Collision */
	private static final VoxelShape TTTT = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	private static final VoxelShape FFFF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 2.0D),
			Block.box(1.0D, 0.0D, 14.0D, 15.0D, 15.0D, 15.0D),
			Block.box(1.0D, 0.0D, 1.0D, 2.0D, 15.0D, 15.0D),
			Block.box(14.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D));

	private static final VoxelShape TTFF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 14.0D, 16.0D, 15.0D, 15.0D),
			Block.box(1.0D, 0.0D, 0.0D, 2.0D, 15.0D, 15.0D));
	private static final VoxelShape FTFT = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 14.0D, 15.0D, 15.0D, 15.0D),
			Block.box(14.0D, 0.0D, 0.0D, 15.0D, 15.0D, 15.0D));
	private static final VoxelShape TFTF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 16.0D, 15.0D, 2.0D),
			Block.box(1.0D, 0.0D, 1.0D, 2.0D, 15.0D, 16.0D));
	private static final VoxelShape FFTT = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 1.0D, 15.0D, 15.0D, 2.0D),
			Block.box(14.0D, 0.0D, 1.0D, 15.0D, 15.0D, 16.0D));

	private static final VoxelShape FTFF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 0.0D, 2.0D, 15.0D, 15.0D),
			Block.box(14.0D, 0.0D, 0.0D, 15.0D, 15.0D, 15.0D),
			Block.box(1.0D, 0.0D, 14.0D, 15.0D, 15.0D, 15.0D));
	private static final VoxelShape FFTF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 2.0D, 15.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 2.0D));
	private static final VoxelShape TFFF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 16.0D, 15.0D, 2.0D),
			Block.box(1.0D, 0.0D, 14.0D, 16.0D, 15.0D, 15.0D),
			Block.box(1.0D, 0.0D, 1.0D, 2.0D, 15.0D, 15.0D));
	private static final VoxelShape FFFT = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 1.0D, 15.0D, 15.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 15.0D, 15.0D, 15.0D),
			Block.box(14.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D));

	private static final VoxelShape FTTF = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 0.0D, 2.0D, 15.0D, 16.0D),
			Block.box(14.0D, 0.0D, 0.0D, 15.0D, 15.0D, 16.0D));
	private static final VoxelShape TFFT = VoxelShapes.or(Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 1.0D, 16.0D, 15.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 16.0D, 15.0D, 15.0D));

	public UnitDesk(AbstractBlock.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean east = state.getValue(EAST).booleanValue();
		boolean north = state.getValue(NORTH).booleanValue();
		boolean south = state.getValue(SOUTH).booleanValue();
		boolean west = state.getValue(WEST).booleanValue();

		if (east == true && north == true && south == false && west == false) { return TTFF; }
		if (east == false && north == true && south == false && west == true) { return FTFT; }
		if (east == true && north == false && south == true && west == false) { return TFTF; }
		if (east == false && north == false && south == true && west == true) { return FFTT; }

		if (east == false && north == true && south == false && west == false) { return FTFF; }
		if (east == true && north == false && south == false && west == false) { return TFFF; }
		if (east == false && north == false && south == false && west == true) { return FFFT; }
		if (east == false && north == false && south == true && west == false) { return FFTF; }
		if (east == false && north == true && south == true && west == false) { return FTTF; }
		if (east == true && north == false && south == false && west == true) { return TFFT; }

		if (east == false && north == true && south == true && west == true) { return FTTF; }
		if (east == true && north == true && south == true && west == false) { return FTTF; }
		if (east == true && north == true && south == false && west == true) { return TFFT; }
		if (east == true && north == false && south == true && west == true) { return TFFT; }
		if (east == true && north == true && south == true && west == true) { return TTTT; }

		else { return FFFF; }
	}
}
