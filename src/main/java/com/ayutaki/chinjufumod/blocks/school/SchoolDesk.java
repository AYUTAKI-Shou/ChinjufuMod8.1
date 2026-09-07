package com.ayutaki.chinjufumod.blocks.school;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SchoolDesk extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Shapes.or(Block.box(-3.0D, 15.0D, 0.0D, 19.0D, 16.0D, 16.0D), 
			Block.box(-2.0D, 11.0D, 1.0D, 18.0D, 15.0D, 15.0D),
			Block.box(-2.0D, 3.0D, 1.0D, 18.0D, 5.0D, 2.0D),
			Block.box(-2.0D, 0.0D, 1.0D, 0.0D, 15.0D, 15.0D),
			Block.box(16.0D, 0.0D, 1.0D, 18.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_WEST = Shapes.or(Block.box(0.0D, 15.0D, -3.0D, 16.0D, 16.0D, 19.0D), 
			Block.box(1.0D, 11.0D, -2.0D, 15.0D, 15.0D, 18.0D),
			Block.box(14.0D, 3.0D, -2.0D, 15.0D, 5.0D, 18.0D),
			Block.box(1.0D, 0.0D, -2.0D, 15.0D, 15.0D, 0.0D),
			Block.box(1.0D, 0.0D, 16.0D, 15.0D, 15.0D, 18.0D));
	private static final VoxelShape AABB_NORTH = Shapes.or(Block.box(-3.0D, 15.0D, 0.0D, 19.0D, 16.0D, 16.0D), 
			Block.box(-2.0D, 11.0D, 1.0D, 18.0D, 15.0D, 15.0D),
			Block.box(-2.0D, 3.0D, 14.0D, 18.0D, 5.0D, 15.0D),
			Block.box(-2.0D, 0.0D, 1.0D, 0.0D, 15.0D, 15.0D),
			Block.box(16.0D, 0.0D, 1.0D, 18.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_EAST = Shapes.or(Block.box(0.0D, 15.0D, -3.0D, 16.0D, 16.0D, 19.0D), 
			Block.box(1.0D, 11.0D, -2.0D, 15.0D, 15.0D, 18.0D),
			Block.box(1.0D, 3.0D, -2.0D, 2.0D, 5.0D, 18.0D),
			Block.box(1.0D, 0.0D, -2.0D, 15.0D, 15.0D, 0.0D),
			Block.box(1.0D, 0.0D, 16.0D, 15.0D, 15.0D, 18.0D));

	public SchoolDesk(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		Direction facing = playerIn.getDirection().getOpposite();
		BlockState upState = worldIn.getBlockState(pos.above());
		FluidState upFluid = worldIn.getFluidState(pos.above());
		
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean upAble = (upState.canBeReplaced() && upFluid.getType() == Fluids.EMPTY);
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty.get() || hItem == Items.BOOK);
		
		if (success) {
			if (hItem == Items_Chinjufu.SHOUHOU_empty.get() && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.get().defaultBlockState()
						.setValue(NoteBook.H_FACING, facing)
						.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
						.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
			
			
			if (hItem == Items.BOOK && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.get().defaultBlockState()
						.setValue(DeskBook1.H_FACING, facing)
						.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
						.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
			
			return InteractionResult.SUCCESS; }
		
		else { return InteractionResult.PASS; }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}
}
