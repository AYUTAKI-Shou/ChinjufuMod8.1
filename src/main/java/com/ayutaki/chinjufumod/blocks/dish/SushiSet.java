package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.phys.shapes.VoxelShape;

public class SushiSet extends BaseFood_Stage5Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 5.0D, 14.0D, 3.0D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.box(3.5D, 0.0D, 0.0D, 11.0D, 3.0D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.box(2.0D, 0.0D, 3.5D, 16.0D, 3.0D, 11.0D);
	private static final VoxelShape AABB_EAST = Block.box(5.0D, 0.0D, 2.0D, 12.5D, 3.0D, 16.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(0.0D, -8.0D, 5.0D, 14.0D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.box(3.5D, -8.0D, 0.0D, 11.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(2.0D, -8.0D, 3.5D, 16.0D, 0.1D, 11.0D);
	private static final VoxelShape DOWN_EAST = Block.box(5.0D, -8.0D, 2.0D, 12.5D, 0.1D, 16.0D);
	
	public SushiSet(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hStack.isEmpty()) {

				if (this == Dish_Blocks.SUSHISET_4shoku.get()) {
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, this.take4shoku(state));
					worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3); }
				
				else { //!4shoku
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, this.takeItem());
					worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			}
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item take4shoku(BlockState state) {
		int i = state.getValue(STAGE_1_5);
		if (i == 1) { return Items_Teatime.SHOUYUSUSHI_S.get(); }
		if (i == 2) { return Items_Teatime.SHOUYUSUSHI_F.get(); }
		if (i == 3) { return Items_Teatime.SHOUYUSUSHI_B.get(); }
		else { return Items_Teatime.SHOUYUSUSHI_T.get(); }
	}
	
	private Item takeItem() {
		if (this == Dish_Blocks.SUSHISET_salmon.get()) { return Items_Teatime.SHOUYUSUSHI_S.get(); }
		if (this == Dish_Blocks.SUSHISET_fish.get()) { return Items_Teatime.SHOUYUSUSHI_F.get(); }
		if (this == Dish_Blocks.SUSHISET_beef.get()) { return Items_Teatime.SHOUYUSUSHI_B.get(); }
		else { return Items_Teatime.SHOUYUSUSHI_T.get(); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 5) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(5)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

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
