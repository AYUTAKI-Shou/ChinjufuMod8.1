package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Kit_Cheese_Tana extends BaseStage3_FaceWater {

	protected static final int COOK_TIME = 12000;
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape AABB_WEST = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);

	/* stage1=OOO, stage2=OOA, stage3=OOB */
	public Kit_Cheese_Tana(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_3);

		/** Hand is empty. **/
		if (hStack.isEmpty() && hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) {
			
			/** stage1=OOO **/
			if (i == 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			
			/** stage2=OOA **/
			if (i == 2) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD);
				worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(1)), 3); }
		
			/** stage3=OOB **/
			if (i == 3) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE);
				worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(1)), 3); }
		}
		
		/** Hand is not empty. **/
		if (!hStack.isEmpty()) {
			if (hItem == Items_Teatime.CHEESE_CURD) {
				if (i == 1) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(2)), 3); } /* -> OOA */

				if (i == 2) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_OAA.defaultBlockState()
							.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(1))
							.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> OAA */
				
				if (i == 3) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_OAA.defaultBlockState()
							.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(2))
							.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> OBA */
			}
			
			if (hItem == Items_Teatime.CHEESE) {
				if (i == 1) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(3)), 3); } /* -> OOB */
				
				if (i == 2) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_OAA.defaultBlockState()
							.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(2))
							.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> OBA */
				
				if (i == 3) {
					CMEvents.consume1_seCheese(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Hakkou_Blocks.CHEESE_OAA.defaultBlockState()
							.setValue(BaseStage3_FaceWater.H_FACING, state.getValue(H_FACING))
							.setValue(BaseStage3_FaceWater.STAGE_1_3, Integer.valueOf(3))
							.setValue(BaseStage3_FaceWater.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } /* -> OBB */
			}
			
			if (hItem != Items_Teatime.CHEESE_CURD && hItem != Items_Teatime.CHEESE) { //Don't use 'else'.
				CMEvents.textFullItem(worldIn, pos, playerIn); }	
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	protected boolean hasWater(IWorldReader worldIn, BlockPos pos) {
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
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		int i = state.getValue(STAGE_1_3);
		if (waterOUT(state) && i == 2 && !hasWater(worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_TANA, COOK_TIME + (500 * worldIn.getRandom().nextInt(5))); }

		if (waterIn(state) && i != 1) {
			worldIn.getBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_TANA, 100); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_3);
		if (waterOUT(state) && i == 2 && !hasWater(worldIn, pos)) { 
			worldIn.getBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_TANA, COOK_TIME + (500 * worldIn.getRandom().nextInt(5))); }

		if (waterIn(state) && i != 1) { 
			worldIn.getBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_TANA, 100); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		/* stage1=OOO, stage2=OOA, stage3=OOB */
		int i = state.getValue(STAGE_1_3);

		if (waterOUT(state) && i == 2 && !hasWater(worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_TANA, COOK_TIME + (500 * rand.nextInt(5))); 
			worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
		
		if (waterIn(state) && i != 1) {
			worldIn.getBlockTicks().scheduleTick(pos, Hakkou_Blocks.CHEESE_TANA, 100);
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(1)), 3); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_kit_cheese_tana").withStyle(TextFormatting.GRAY));
	}
}
