package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Kit_Cheese_OAA extends BaseStage3_FaceWater {

	protected static final int COOK_TIME = 12000;
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape AABB_WEST = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);

	/* stage1=OAA, stage2=OBA, stage3=OBB */
	public Kit_Cheese_OAA(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_3);

		/** Hand is empty. **/
		if (hStack.isEmpty() && hItem != Items_Teatime.CHEESE_CURD.get() && hItem != Items_Teatime.CHEESE.get()) {
			
			/** stage1=OAA **/
			if (i == 1) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD.get());
				worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_TANA.get().defaultBlockState()
						.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
						.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(2))
						.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
			
			/** stage2=OBA **/
			if (i == 2) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE.get());
				worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_TANA.get().defaultBlockState()
						.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
						.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(2))
						.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
			
			/** stage3=OBB **/
			if (i == 3) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE.get());
				worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_TANA.get().defaultBlockState()
						.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
						.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(3))
						.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		}
		
		/** Hand is not empty. **/
		if (!hStack.isEmpty()) {
			if (hItem == Items_Teatime.CHEESE_CURD.get()) {
				if (i == 1) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_AAA.get().defaultBlockState()
							.setValue(BaseStage4_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(1))
							.setValue(BaseStage4_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> AAA */
				
				if (i == 2) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_AAA.get().defaultBlockState()
							.setValue(BaseStage4_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(2))
							.setValue(BaseStage4_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> BAA */
				
				if (i == 3) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_AAA.get().defaultBlockState()
							.setValue(BaseStage4_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(3))
							.setValue(BaseStage4_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> BBA */
			}
			
			
			if (hItem == Items_Teatime.CHEESE.get()) {
				if (i == 1) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_AAA.get().defaultBlockState()
							.setValue(BaseStage4_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(2))
							.setValue(BaseStage4_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> BAA */
				
				if (i == 2) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_AAA.get().defaultBlockState()
							.setValue(BaseStage4_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(3))
							.setValue(BaseStage4_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> BBA */
				
				if (i == 3) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_AAA.get().defaultBlockState()
							.setValue(BaseStage4_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(4))
							.setValue(BaseStage4_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> BBB */
			}
			
			if (hItem != Items_Teatime.CHEESE_CURD.get() && hItem != Items_Teatime.CHEESE.get()) { //Don't use 'else'.
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}

		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
	
	protected static boolean hasWater(LevelReader worldIn, BlockPos pos) {
		for(BlockPos nearPos : BlockPos.betweenClosed(pos.offset(-2, -2, -2), pos.offset(2, 2, 2))) {
			if (worldIn.getFluidState(nearPos).is(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	private boolean waterOUT(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		int i = state.getValue(STAGE_1_3);
		if (waterOUT(state) && i < 3 && !hasWater(worldIn, pos)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA.get(), COOK_TIME + (100 * worldIn.getRandom().nextInt(5))); }

		if (waterIn(state)) { 
			worldIn.scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA.get(), 100); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_3);
		if (waterOUT(state) && i < 3 && !hasWater(worldIn, pos)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA.get(), COOK_TIME + (100 * worldIn.getRandom().nextInt(5))); }

		if (waterIn(state)) { 
			worldIn.scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA.get(), 100); }	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		/* stage1=OAA, stage2=OBA, stage3=OBB */
		int i = state.getValue(STAGE_1_3);

		if (waterOUT(state) && i < 3 && !hasWater(worldIn, pos)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA.get(), COOK_TIME + (100 * rand.nextInt(5)));
			worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA.get(), 100);

			CMEvents.dropN_ROTTENFOOD(2, worldIn, pos);
			worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_TANA.get().defaultBlockState()
					.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
					.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(1))
					.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
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

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CHEESE_TANA.get());
	}
}
