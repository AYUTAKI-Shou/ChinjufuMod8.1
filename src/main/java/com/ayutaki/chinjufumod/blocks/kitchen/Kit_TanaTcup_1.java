package com.ayutaki.chinjufumod.blocks.kitchen;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

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
import net.minecraft.world.phys.shapes.VoxelShape;

public class Kit_TanaTcup_1 extends Base_Tana7 {

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	
	public Kit_TanaTcup_1(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_7);

		if (hItem != Items_Teatime.TCUP_kara.get() && hItem != Items_Teatime.TEAPOT_kara.get()) {
			if (hStack.isEmpty()) {
				
				if (i == 7) {
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.TEAPOT_kara.get());
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i - 1)), 3); }
				
				else { //i != 7
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.TCUP_kara.get());

					if (i == 1) { 
						worldIn.setBlock(pos, Kitchen_Blocks.KIT_TANA.get().defaultBlockState()
							.setValue(Kit_Tana.H_FACING, state.getValue(H_FACING)), 3); }
					else { //i != 1
						worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i - 1)), 3); } } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.TCUP_kara.get()) {
			if (i >= 6) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			else { //i < 6
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i + 1)), 3); } }
		
		if (hItem == Items_Teatime.TEAPOT_kara.get()) {
			if (i == 6) {
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i + 1)), 3); }
			else { //i != 6
				CMEvents.textNotHave(worldIn, pos, playerIn); } }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

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

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.KIT_TANA.get());
	}
}
