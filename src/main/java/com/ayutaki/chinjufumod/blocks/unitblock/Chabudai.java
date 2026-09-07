package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Chabudai extends BaseUnitBlock {
	/* Collision */
	private static final VoxelShape TTTT = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 8.0D, 16.0D);

	private static final VoxelShape FFFF = Shapes.or(Block.box(0.0D, 7.0D, 5.0D, 1.0D, 8.0D, 11.0D),
			Block.box(1.0D, 7.0D, 4.0D, 2.0D, 8.0D, 12.0D),
			Block.box(2.0D, 7.0D, 3.0D, 3.0D, 8.0D, 13.0D),
			Block.box(3.0D, 7.0D, 2.0D, 4.0D, 8.0D, 14.0D),
			Block.box(4.0D, 7.0D, 1.0D, 5.0D, 8.0D, 15.0D),
			Block.box(5.0D, 7.0D, 0.0D, 11.0D, 8.0D, 16.0D),
			Block.box(11.0D, 7.0D, 1.0D, 12.0D, 8.0D, 15.0D),
			Block.box(12.0D, 7.0D, 2.0D, 13.0D, 8.0D, 14.0D),
			Block.box(13.0D, 7.0D, 3.0D, 14.0D, 8.0D, 13.0D),
			Block.box(14.0D, 7.0D, 4.0D, 15.0D, 8.0D, 12.0D),
			Block.box(15.0D, 7.0D, 5.0D, 16.0D, 8.0D, 11.0D),
			Block.box(3.5D, 0.0D, 3.5D, 5.0D, 7.0D, 5.0D),
			Block.box(3.5D, 0.0D, 11.0D, 5.0D, 7.0D, 12.5D),
			Block.box(11.0D, 0.0D, 3.5D, 12.5D, 7.0D, 5.0D),
			Block.box(11.0D, 0.0D, 11.0D, 12.5D, 7.0D, 12.5D));

	private static final VoxelShape TTFF = Shapes.or(Block.box(13.0D, 7.0D, 13.0D, 16.0D, 8.0D, 14.0D),
			Block.box(11.0D, 7.0D, 12.0D, 16.0D, 8.0D, 13.0D),
			Block.box(10.0D, 7.0D, 11.0D, 16.0D, 8.0D, 12.0D),
			Block.box(9.0D, 7.0D, 10.0D, 16.0D, 8.0D, 11.0D),
			Block.box(8.0D, 7.0D, 9.0D, 16.0D, 8.0D, 10.0D),
			Block.box(7.0D, 7.0D, 8.0D, 16.0D, 8.0D, 9.0D),
			Block.box(6.0D, 7.0D, 7.0D, 16.0D, 8.0D, 8.0D),
			Block.box(5.0D, 7.0D, 6.0D, 16.0D, 8.0D, 7.0D),
			Block.box(4.0D, 7.0D, 5.0D, 16.0D, 8.0D, 6.0D),
			Block.box(3.0D, 7.0D, 3.0D, 16.0D, 8.0D, 5.0D),
			Block.box(2.0D, 7.0D, 0.0D, 16.0D, 8.0D, 3.0D),
			Block.box(8.0D, 0.0D, 5.5D, 10.5D, 7.0D, 8.0D));
	private static final VoxelShape FTFT = Shapes.or(Block.box(0.0D, 7.0D, 13.0D, 3.0D, 8.0D, 14.0D),
			Block.box(0.0D, 7.0D, 12.0D, 5.0D, 8.0D, 13.0D),
			Block.box(0.0D, 7.0D, 11.0D, 6.0D, 8.0D, 12.0D),
			Block.box(0.0D, 7.0D, 10.0D, 7.0D, 8.0D, 11.0D),
			Block.box(0.0D, 7.0D, 9.0D, 8.0D, 8.0D, 10.0D),
			Block.box(0.0D, 7.0D, 8.0D, 9.0D, 8.0D, 9.0D),
			Block.box(0.0D, 7.0D, 7.0D, 10.0D, 8.0D, 8.0D),
			Block.box(0.0D, 7.0D, 6.0D, 11.0D, 8.0D, 7.0D),
			Block.box(0.0D, 7.0D, 5.0D, 12.0D, 8.0D, 6.0D),
			Block.box(0.0D, 7.0D, 3.0D, 13.0D, 8.0D, 5.0D),
			Block.box(0.0D, 7.0D, 0.0D, 14.0D, 8.0D, 3.0D),
			Block.box(5.5D, 0.0D, 5.5D, 8.0D, 7.0D, 8.0D));
	private static final VoxelShape TFTF = Shapes.or(Block.box(13.0D, 7.0D, 2.0D, 16.0D, 8.0D, 3.0D),
			Block.box(11.0D, 7.0D, 3.0D, 16.0D, 8.0D, 4.0D),
			Block.box(10.0D, 7.0D, 4.0D, 16.0D, 8.0D, 5.0D),
			Block.box(9.0D, 7.0D, 5.0D, 16.0D, 8.0D, 6.0D),
			Block.box(8.0D, 7.0D, 6.0D, 16.0D, 8.0D, 7.0D),
			Block.box(7.0D, 7.0D, 7.0D, 16.0D, 8.0D, 8.0D),
			Block.box(6.0D, 7.0D, 8.0D, 16.0D, 8.0D, 9.0D),
			Block.box(5.0D, 7.0D, 9.0D, 16.0D, 8.0D, 10.0D),
			Block.box(4.0D, 7.0D, 10.0D, 16.0D, 8.0D, 11.0D),
			Block.box(3.0D, 7.0D, 11.0D, 16.0D, 8.0D, 13.0D),
			Block.box(2.0D, 7.0D, 13.0D, 16.0D, 8.0D, 16.0D),
			Block.box(8.0D, 0.0D, 8.0D, 10.5D, 7.0D, 10.5D));
	private static final VoxelShape FFTT = Shapes.or(Block.box(0.0D, 7.0D, 2.0D, 3.0D, 8.0D, 3.0D),
			Block.box(0.0D, 7.0D, 3.0D, 5.0D, 8.0D, 4.0D),
			Block.box(0.0D, 7.0D, 4.0D, 6.0D, 8.0D, 5.0D),
			Block.box(0.0D, 7.0D, 5.0D, 7.0D, 8.0D, 6.0D),
			Block.box(0.0D, 7.0D, 6.0D, 8.0D, 8.0D, 7.0D),
			Block.box(0.0D, 7.0D, 7.0D, 9.0D, 8.0D, 8.0D),
			Block.box(0.0D, 7.0D, 8.0D, 10.0D, 8.0D, 9.0D),
			Block.box(0.0D, 7.0D, 9.0D, 11.0D, 8.0D, 10.0D),
			Block.box(0.0D, 7.0D, 10.0D, 12.0D, 8.0D, 11.0D),
			Block.box(0.0D, 7.0D, 11.0D, 13.0D, 8.0D, 13.0D),
			Block.box(0.0D, 7.0D, 13.0D, 14.0D, 8.0D, 16.0D),
			Block.box(5.5D, 0.0D, 8.0D, 8.0D, 7.0D, 10.5D));

	private static final VoxelShape FTTT = Shapes.or(Block.box(0.0D, 7.0D, 0.0D, 15.0D, 8.0D, 16.0D),
			Block.box(15.0D, 7.0D, 3.0D, 16.0D, 8.0D, 13.0D));
	private static final VoxelShape TTTF = Shapes.or(Block.box(1.0D, 7.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 7.0D, 3.0D, 1.0D, 8.0D, 13.0D));
	private static final VoxelShape TTFT = Shapes.or(Block.box(0.0D, 7.0D, 0.0D, 16.0D, 8.0D, 15.0D),
			Block.box(3.0D, 7.0D, 15.0D, 13.0D, 8.0D, 16.0D));
	private static final VoxelShape TFTT = Shapes.or(Block.box(0.0D, 7.0D, 1.0D, 16.0D, 8.0D, 16.0D),
			Block.box(3.0D, 7.0D, 0.0D, 13.0D, 8.0D, 1.0D));
	
	public Chabudai(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean east = state.getValue(EAST).booleanValue();
		boolean north = state.getValue(NORTH).booleanValue();
		boolean south = state.getValue(SOUTH).booleanValue();
		boolean west = state.getValue(WEST).booleanValue();

		if (east == true && north == true && south == false && west == false) { return TTFF; }
		if (east == false && north == true && south == false && west == true) { return FTFT; }
		if (east == true && north == false && south == true && west == false) { return TFTF; }
		if (east == false && north == false && south == true && west == true) { return FFTT; }

		if (east == false && north == true && south == true && west == true) { return FTTT; }
		if (east == true && north == true && south == true && west == false) { return TTTF; }
		if (east == true && north == true && south == false && west == true) { return TTFT; }
		if (east == true && north == false && south == true && west == true) { return TFTT; }

		if (east == true && north == true && south == true && west == true) { return TTTT; }

		return FFFF;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_chabudai").withStyle(ChatFormatting.GRAY));
	}
}
