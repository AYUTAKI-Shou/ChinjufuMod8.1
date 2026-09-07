package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4WA;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class DeskBook1 extends BaseFood_Stage4WA {
	/* Collision */
	private static final VoxelShape SOUTH_1 = Block.makeCuboidShape(5.0D, 0.0D, 4.0D, 11.0D, 1.5D, 12.0D);
	private static final VoxelShape EAST_1 = Block.makeCuboidShape(4.0D, 0.0D, 5.0D, 12.0D, 1.5D, 11.0D);
	private static final VoxelShape WEST_1 = Block.makeCuboidShape(4.0D, 0.0D, 5.0D, 12.0D, 1.5D, 11.0D);
	private static final VoxelShape NORTH_1 = Block.makeCuboidShape(5.0D, 0.0D, 4.0D, 11.0D, 1.5D, 12.0D);
	
	private static final VoxelShape SOUTH_4 = Block.makeCuboidShape(1.75D, 0.0D, 4.0D, 14.25D, 0.8D, 12.0D);
	private static final VoxelShape EAST_4 = Block.makeCuboidShape(4.0D, 0.0D, 1.75D, 12.0D, 0.8D, 14.25D);
	private static final VoxelShape WEST_4 = Block.makeCuboidShape(4.0D, 0.0D, 1.75D, 12.0D, 0.8D, 14.25D);
	private static final VoxelShape NORTH_4 = Block.makeCuboidShape(1.75D, 0.0D, 4.0D, 14.25D, 0.8D, 12.0D);
	
	private static final VoxelShape SOUTH_1D = Block.makeCuboidShape(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	private static final VoxelShape EAST_1D = Block.makeCuboidShape(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape WEST_1D = Block.makeCuboidShape(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape NORTH_1D = Block.makeCuboidShape(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	
	private static final VoxelShape SOUTH_4D = Block.makeCuboidShape(1.75D, -8.0D, 4.0D, 14.25D, 0.01D, 12.0D);
	private static final VoxelShape EAST_4D = Block.makeCuboidShape(4.0D, -8.0D, 1.75D, 12.0D, 0.01D, 14.25D);
	private static final VoxelShape WEST_4D = Block.makeCuboidShape(4.0D, -8.0D, 1.75D, 12.0D, 0.01D, 14.25D);
	private static final VoxelShape NORTH_4D = Block.makeCuboidShape(1.75D, -8.0D, 4.0D, 14.25D, 0.01D, 12.0D);
	
	public DeskBook1(Block.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);
		
		if (hItem == Items_Chinjufu.SHOUHOU_empty) {
			boolean open = (i != 1);
			
			CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Furniture_Blocks.NOTEBOOK_B.getDefaultState()
					.with(NoteBook_B.H_FACING, state.get(H_FACING))
					.with(NoteBook_B.OPEN, Boolean.valueOf(open))
					.with(NoteBook_B.DOWN, state.get(DOWN))
					.with(NoteBook_B.HAS_BOOK, Boolean.valueOf(false)), 3);
			return ActionResultType.SUCCESS; }
		
		else {
			if (hStack.isEmpty()) {
				worldIn.setBlockState(pos, state.cycle(STAGE_1_4), 3);
				CMEvents.soundPage(worldIn, pos);
				if (i == 1 || i == 4) { CMEvents.soundWoodPlace(worldIn, pos); }
				return ActionResultType.SUCCESS; }
			
			else { //!empty
				return ActionResultType.PASS; }
		}
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		int i = state.get(STAGE_1_4);
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default: 
			return (i == 1)? (notDown? NORTH_1 : NORTH_1D) : (notDown? NORTH_4 : NORTH_4D);
		
		case SOUTH: 
			return (i == 1)? (notDown? SOUTH_1 : SOUTH_1D) : (notDown? SOUTH_4 : SOUTH_4D);
			
		case EAST: 
			return (i == 1)? (notDown? EAST_1 : EAST_1D) : (notDown? EAST_4 : EAST_4D);
			
		case WEST: 
			return (i == 1)? (notDown? WEST_1 : WEST_1D) : (notDown? WEST_4 : WEST_4D);
		}
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.BOOK);
	}
}
