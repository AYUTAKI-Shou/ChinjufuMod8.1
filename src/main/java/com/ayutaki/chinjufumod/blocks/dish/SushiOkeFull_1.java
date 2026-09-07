package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
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

public class SushiOkeFull_1 extends BaseFood_Stage9Water {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.5D, 16.0D);
	private static final VoxelShape AABB_DOWN = Block.box(0.0D, -8.0D, 0.0D, 16.0D, -0.1D, 16.0D);
	
	private static final VoxelShape AABB9_BOX = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 2.5D, 15.0D);
	private static final VoxelShape AABB9_DOWN = Block.box(1.0D, -8.0D, 1.0D, 15.0D, 0.1D, 15.0D);
	
	public SushiOkeFull_1(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_9);

		if (i == 9) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 9
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, takeItem(state));
				
				if (i == 8) {
					worldIn.setBlock(pos, Dish_Blocks.SUSHIOKE_FULL_9.get().defaultBlockState()
							.setValue(SushiOkeFull_9.H_FACING, state.getValue(H_FACING))
							.setValue(SushiOkeFull_9.DOWN, state.getValue(DOWN))
							.setValue(SushiOkeFull_9.STAGE_1_9, Integer.valueOf(1)), 3); }
				if (i < 8) {
					worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(i + 1)), 3); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem(BlockState state) {
		int i = state.getValue(STAGE_1_9);
		
		if (i == 1) { return Items_Teatime.SUSHI_S.get(); }
		if (i == 2) { return Items_Teatime.SUSHI_F.get(); }
		if (i == 3) { return Items_Teatime.SUSHI_B.get(); }
		if (i == 4) { return Items_Teatime.SUSHI_T.get(); }
		if (i == 5) { return Items_Teatime.SUSHI_S.get(); }
		if (i == 6) { return Items_Teatime.SUSHI_F.get(); }
		if (i == 7) { return Items_Teatime.SUSHI_B.get(); }
		else { return Items_Teatime.SUSHI_T.get(); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_9);
		
		if (waterIn(state, worldIn, pos) && i != 9) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.SUSHIOKE_FULL_9.get().defaultBlockState()
					.setValue(SushiOkeFull_9.H_FACING, state.getValue(H_FACING))
					.setValue(SushiOkeFull_9.DOWN, state.getValue(DOWN))
					.setValue(SushiOkeFull_9.STAGE_1_9, Integer.valueOf(9))
					.setValue(SushiOkeFull_9.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		int i = state.getValue(STAGE_1_9);

		if (i == 9) { return notDown? AABB9_BOX : AABB9_DOWN; }
		return notDown? AABB_BOX : AABB_DOWN;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		int i = state.getValue(STAGE_1_9);
		return (i != 9)? new ItemStack(Items_Teatime.SUSHIOKE_FULL_1.get()) : new ItemStack(Items.AIR);
	}
}
