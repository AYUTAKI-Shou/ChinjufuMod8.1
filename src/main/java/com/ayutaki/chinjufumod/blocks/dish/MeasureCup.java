package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MeasureCup extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(13.1D, 0.0D, 0.7D, 15.5D, 2.7D, 3.5D);
	private static final VoxelShape AABB_WEST = Block.box(12.5D, 0.0D, 13.1D, 15.3D, 2.7D, 15.5D);
	private static final VoxelShape AABB_NORTH = Block.box(0.5D, 0.0D, 12.5D, 2.9D, 2.7D, 15.3D);
	private static final VoxelShape AABB_EAST = Block.box(0.7D, 0.0D, 0.5D, 3.5D, 2.7D, 2.9D);
	
	public MeasureCup(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.KEIRYO_CUP.get());
			worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3); }
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
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
		}
	}
}
