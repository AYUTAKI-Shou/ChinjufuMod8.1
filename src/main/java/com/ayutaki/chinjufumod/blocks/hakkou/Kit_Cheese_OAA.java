package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class Kit_Cheese_OAA extends BaseStage3_FaceWater {

	protected static final int COOK_TIME = 12000;
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);

	/* stage1=OAA, stage2=OBA, stage3=OBB */
	public Kit_Cheese_OAA(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_3);

		/** Hand is Empty. **/
		if (hStack.isEmpty() && hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) {
			/** stage1=OAA **/
			if (i == 1) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD);
				worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_TANA.getDefaultState()
						.with(BaseStage3_FaceWater.H_FACING, state.get(H_FACING))
						.with(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(2))
						.with(BaseStage3_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> OOA */
			
			/** stage2=OBA **/
			if (i == 2) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE);
				worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_TANA.getDefaultState()
						.with(BaseStage3_FaceWater.H_FACING, state.get(H_FACING))
						.with(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(2))
						.with(BaseStage3_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> OOA */
			
			/** stage3=OBB **/
			if (i == 3) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE);
				worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_TANA.getDefaultState()
						.with(BaseStage3_FaceWater.H_FACING, state.get(H_FACING))
						.with(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(3))
						.with(BaseStage3_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> OOB */
		}
		
		
		/** Hand is not empty. **/
		if (!hStack.isEmpty()) {
			
			if (hItem == Items_Teatime.CHEESE_CURD) {
				if (i == 1) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_AAA.getDefaultState()
							.with(BaseStage4_FaceWater.H_FACING, state.get(H_FACING))
							.with(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(1))
							.with(BaseStage4_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> AAA */
				
				if (i == 2) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_AAA.getDefaultState()
							.with(BaseStage4_FaceWater.H_FACING, state.get(H_FACING))
							.with(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(2))
							.with(BaseStage4_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> BAA */
				
				if (i == 3) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_AAA.getDefaultState()
							.with(BaseStage4_FaceWater.H_FACING, state.get(H_FACING))
							.with(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(3))
							.with(BaseStage4_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> BBA */
			}
			
			if (hItem == Items_Teatime.CHEESE) {
				if (i == 1) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_AAA.getDefaultState()
							.with(BaseStage4_FaceWater.H_FACING, state.get(H_FACING))
							.with(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(2))
							.with(BaseStage4_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> BAA */
				
				if (i == 2) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_AAA.getDefaultState()
							.with(BaseStage4_FaceWater.H_FACING, state.get(H_FACING))
							.with(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(3))
							.with(BaseStage4_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> BBA */
				
				if (i == 3) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_AAA.getDefaultState()
							.with(BaseStage4_FaceWater.H_FACING, state.get(H_FACING))
							.with(BaseStage4_FaceWater.STAGE_1_4, Integer.valueOf(4))
							.with(BaseStage4_FaceWater.WATERLOGGED, state.get(WATERLOGGED))); } /* -> BBB */
			}
			
			if (hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) { CMEvents.textFullItem(worldIn, pos, playerIn);
			}
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	protected boolean hasWater(IWorldReader worldIn, BlockPos pos) {
		for(BlockPos nearPos : BlockPos.getAllInBoxMutable(pos.add(-2, -2, -2), pos.add(2, 2, 2))) {
			if (worldIn.getFluidState(nearPos).isTagged(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}

	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.get(WATERLOGGED);
	}
	
	private boolean waterOUT(BlockState state) {
		return !state.get(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		int i = state.get(STAGE_1_3);
		if (waterOUT(state) && i < 3 && !hasWater(worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA, COOK_TIME + (500 * worldIn.getRandom().nextInt(5))); }

		if (waterIn(state)) { 
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA, 100); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.get(STAGE_1_3);
		if (waterOUT(state) && i < 3 && !hasWater(worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA, COOK_TIME + (500 * worldIn.getRandom().nextInt(5))); }

		if (waterIn(state)) { 
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA, 100); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		/* stage1=OAA, stage2=OBA, stage3=OBB */
		int i = state.get(STAGE_1_3);
		
		if (waterOUT(state) && i < 3 && !hasWater(worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA, COOK_TIME + (500 * rand.nextInt(5))); 
			worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 1)), 3); }

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_OAA, 100);

			CMEvents.dropN_ROTTENFOOD(2, worldIn, pos);
			worldIn.setBlockState(pos, Hakkou_Blocks.CHEESE_TANA.getDefaultState()
					.with(BaseStage3_FaceWater.H_FACING, state.get(H_FACING))
					.with(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(1))
					.with(BaseStage3_FaceWater.WATERLOGGED, state.get(WATERLOGGED)), 3); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CHEESE_TANA);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
