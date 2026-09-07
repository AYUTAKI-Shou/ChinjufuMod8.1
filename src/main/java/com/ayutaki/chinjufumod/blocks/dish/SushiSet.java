package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class SushiSet extends BaseFood_Stage5WA {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 14.0D, 3.0D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(3.5D, 0.0D, 0.0D, 11.0D, 3.0D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(2.0D, 0.0D, 3.5D, 16.0D, 3.0D, 11.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(5.0D, 0.0D, 2.0D, 12.5D, 3.0D, 16.0D);

	private static final VoxelShape DOWN_SOUTH = Block.makeCuboidShape(0.0D, -8.0D, 5.0D, 14.0D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.makeCuboidShape(3.5D, -8.0D, 0.0D, 11.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.makeCuboidShape(2.0D, -8.0D, 3.5D, 16.0D, 0.1D, 11.0D);
	private static final VoxelShape DOWN_EAST = Block.makeCuboidShape(5.0D, -8.0D, 2.0D, 12.5D, 0.1D, 16.0D);

	public SushiSet(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hStack.isEmpty()) {
				
				if (this == Dish_Blocks.SUSHISET_4shoku) {
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, this.take4shoku(state));
					worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(i + 1))); }
				
				else { //!4shoku
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, this.takeItem());
					worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(i + 1))); }
			}
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item take4shoku(BlockState state) {
		int i = state.get(STAGE_1_5);
		if (i == 1) { return Items_Teatime.SHOUYUSUSHI_S; }
		if (i == 2) { return Items_Teatime.SHOUYUSUSHI_F; }
		if (i == 3) { return Items_Teatime.SHOUYUSUSHI_B; }
		else { return Items_Teatime.SHOUYUSUSHI_T; }
	}
	
	private Item takeItem() {
		if (this == Dish_Blocks.SUSHISET_salmon) { return Items_Teatime.SHOUYUSUSHI_S; }
		if (this == Dish_Blocks.SUSHISET_fish) { return Items_Teatime.SHOUYUSUSHI_F; }
		if (this == Dish_Blocks.SUSHISET_beef) { return Items_Teatime.SHOUYUSUSHI_B; }
		else { return Items_Teatime.SHOUYUSUSHI_T; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 5) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(5))); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return notDown? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return notDown? AABB_WEST : DOWN_WEST;
		case EAST:
			return notDown? AABB_EAST : DOWN_EAST;
		}
	}
}
