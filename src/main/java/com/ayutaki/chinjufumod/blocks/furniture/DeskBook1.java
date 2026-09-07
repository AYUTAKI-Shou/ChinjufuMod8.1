package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DeskBook1 extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape SOUTH_1 = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 1.5D, 12.0D);
	private static final VoxelShape EAST_1 = Block.box(4.0D, 0.0D, 5.0D, 12.0D, 1.5D, 11.0D);
	private static final VoxelShape WEST_1 = Block.box(4.0D, 0.0D, 5.0D, 12.0D, 1.5D, 11.0D);
	private static final VoxelShape NORTH_1 = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 1.5D, 12.0D);
	
	private static final VoxelShape SOUTH_4 = Block.box(1.75D, 0.0D, 4.0D, 14.25D, 0.8D, 12.0D);
	private static final VoxelShape EAST_4 = Block.box(4.0D, 0.0D, 1.75D, 12.0D, 0.8D, 14.25D);
	private static final VoxelShape WEST_4 = Block.box(4.0D, 0.0D, 1.75D, 12.0D, 0.8D, 14.25D);
	private static final VoxelShape NORTH_4 = Block.box(1.75D, 0.0D, 4.0D, 14.25D, 0.8D, 12.0D);
	
	private static final VoxelShape SOUTH_1D = Block.box(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	private static final VoxelShape EAST_1D = Block.box(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape WEST_1D = Block.box(4.0D, -8.0D, 5.0D, 12.0D, 0.01D, 11.0D);
	private static final VoxelShape NORTH_1D = Block.box(5.0D, -8.0D, 4.0D, 11.0D, 0.01D, 12.0D);
	
	private static final VoxelShape SOUTH_4D = Block.box(1.75D, -8.0D, 4.0D, 14.25D, 0.01D, 12.0D);
	private static final VoxelShape EAST_4D = Block.box(4.0D, -8.0D, 1.75D, 12.0D, 0.01D, 14.25D);
	private static final VoxelShape WEST_4D = Block.box(4.0D, -8.0D, 1.75D, 12.0D, 0.01D, 14.25D);
	private static final VoxelShape NORTH_4D = Block.box(1.75D, -8.0D, 4.0D, 14.25D, 0.01D, 12.0D);
	
	public DeskBook1(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);
		
		if (hItem == Items_Chinjufu.SHOUHOU_empty.get()) {
			boolean open = (i != 1);
			
			CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, Furniture_Blocks.NOTEBOOK_B.get().defaultBlockState()
					.setValue(NoteBook_B.H_FACING, state.getValue(H_FACING))
					.setValue(NoteBook_B.OPEN, Boolean.valueOf(open))
					.setValue(NoteBook_B.DOWN, state.getValue(DOWN))
					.setValue(NoteBook_B.HAS_BOOK, Boolean.valueOf(false)), 3);
			return InteractionResult.SUCCESS; }
		
		else {
			if (hStack.isEmpty()) {
				worldIn.setBlock(pos, state.cycle(STAGE_1_4), 3);
				CMEvents.soundPage(worldIn, pos);
				if (i == 1 || i == 4) { CMEvents.soundWoodPlace(worldIn, pos); }
				return InteractionResult.SUCCESS; }
			
			else { //!empty
				return InteractionResult.PASS; }
		}
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		int i = state.getValue(STAGE_1_4);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

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
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.BOOK);
	}
}
