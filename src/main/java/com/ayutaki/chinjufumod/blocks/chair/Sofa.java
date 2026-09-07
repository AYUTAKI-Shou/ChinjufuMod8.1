package com.ayutaki.chinjufumod.blocks.chair;

import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.state.TypeLR;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Sofa extends BaseSofa {
	/* Collision */
	private static final VoxelShape DEFAULT_SOUTH = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 14.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 12.0D, 2.0D),
			Block.box(14.0D, 6.0D, 2.0D, 16.0D, 12.0D, 14.0D),
			Block.box(0.0D, 6.0D, 2.0D, 2.0D, 12.0D, 14.0D));
	private static final VoxelShape DEFAULT_WEST = Shapes.or(Block.box(2.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(14.0D, 6.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 14.0D, 14.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 0.0D, 14.0D, 12.0D, 2.0D));
	private static final VoxelShape DEFAULT_NORTH = Shapes.or(Block.box(0.0D, 0.0D, 2.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 14.0D, 16.0D, 12.0D, 16.0D),
			Block.box(14.0D, 6.0D, 2.0D, 16.0D, 12.0D, 14.0D),
			Block.box(0.0D, 6.0D, 2.0D, 2.0D, 12.0D, 14.0D));
	private static final VoxelShape DEFAULT_EAST = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 14.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 2.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 14.0D, 14.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 0.0D, 14.0D, 12.0D, 2.0D));
	
	private static final VoxelShape LEFT_SOUTH = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 14.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 12.0D, 2.0D),
			Block.box(0.0D, 6.0D, 2.0D, 2.0D, 12.0D, 14.0D));
	private static final VoxelShape LEFT_WEST = Shapes.or(Block.box(2.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(14.0D, 6.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 0.0D, 14.0D, 12.0D, 2.0D));
	private static final VoxelShape LEFT_NORTH = Shapes.or(Block.box(0.0D, 0.0D, 2.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 14.0D, 16.0D, 12.0D, 16.0D),
			Block.box(14.0D, 6.0D, 2.0D, 16.0D, 12.0D, 14.0D));
	private static final VoxelShape LEFT_EAST = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 14.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 2.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 14.0D, 14.0D, 12.0D, 16.0D));
	
	private static final VoxelShape RIGHT_SOUTH = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 14.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 12.0D, 2.0D),
			Block.box(14.0D, 6.0D, 2.0D, 16.0D, 12.0D, 14.0D));
	private static final VoxelShape RIGHT_WEST = Shapes.or(Block.box(2.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(14.0D, 6.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 14.0D, 14.0D, 12.0D, 16.0D));
	private static final VoxelShape RIGHT_NORTH = Shapes.or(Block.box(0.0D, 0.0D, 2.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 14.0D, 16.0D, 12.0D, 16.0D),
			Block.box(0.0D, 6.0D, 2.0D, 2.0D, 12.0D, 14.0D));
	private static final VoxelShape RIGHT_EAST = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 14.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 2.0D, 12.0D, 16.0D),
			Block.box(2.0D, 6.0D, 0.0D, 14.0D, 12.0D, 2.0D));
	
	private static final VoxelShape BOTH_SOUTH = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 14.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 12.0D, 2.0D));
	private static final VoxelShape BOTH_WEST = Shapes.or(Block.box(2.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(14.0D, 6.0D, 0.0D, 16.0D, 12.0D, 16.0D));
	private static final VoxelShape BOTH_NORTH = Shapes.or(Block.box(0.0D, 0.0D, 2.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 14.0D, 16.0D, 12.0D, 16.0D));
	private static final VoxelShape BOTH_EAST = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 14.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 2.0D, 12.0D, 16.0D));


	public Sofa(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return InteractionResult.PASS; }

		else {
			CMEvents.soundKinuzure(worldIn, pos);
			return SitableEntity.create(worldIn, pos, 0.15, playerIn);
		}
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		TypeLR type = state.getValue(TYPE);

		switch (type) {
		case DEFAULT:
		default:
			switch (direction) {
			case NORTH:
			default: return DEFAULT_NORTH;
			case SOUTH: return DEFAULT_SOUTH;
			case EAST: return DEFAULT_EAST;
			case WEST: return DEFAULT_WEST;
			} // switch
			
		case LEFT:
			switch (direction) {
			case NORTH:
			default: return LEFT_NORTH;
			case SOUTH: return LEFT_SOUTH;
			case EAST: return LEFT_EAST;
			case WEST: return LEFT_WEST;
			} // switch
			
		case RIGHT:
			switch (direction) {
			case NORTH:
			default: return RIGHT_NORTH;
			case SOUTH: return RIGHT_SOUTH;
			case EAST: return RIGHT_EAST;
			case WEST: return RIGHT_WEST;
			} // switch
			
		case BOTH:			
			switch (direction) {
			case NORTH:
			default: return BOTH_NORTH;
			case SOUTH: return BOTH_SOUTH;
			case EAST: return BOTH_EAST;
			case WEST: return BOTH_WEST;
			} // switch
		}
	}
}
