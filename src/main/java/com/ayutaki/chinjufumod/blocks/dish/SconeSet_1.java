package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.blocks.base.BaseStage8_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class SconeSet_1 extends BaseStage8_FaceWater {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.5D, 13.0D);

	public SconeSet_1(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_8);

		/** Hand is Empty. **/
		if (hStack.isEmpty()) {
			CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, takeItem(state));
			
			if (i == 8) {
				worldIn.setBlockState(pos, Dish_Blocks.SCONESET_kara.getDefaultState()
						.with(BaseFacingWater.H_FACING, state.get(H_FACING))); }
			else { //i != 8
				worldIn.setBlockState(pos, state.with(STAGE_1_8, Integer.valueOf(i + 1))); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem(BlockState state) {
		int i = state.get(STAGE_1_8);
		
		if (i == 1) { return Items_Teatime.EGGSAND; }
		if (i == 2) { return Items_Teatime.CHICKENSAND; }
		if (i == 3) { return Items_Teatime.EGGSAND; }
		if (i == 4) { return Items_Teatime.CHICKENSAND; }
		if (i == 5) { return Items_Teatime.SCONE; }
		if (i == 6) { return Items_Teatime.SCONE; }
		if (i == 7) { return Items_Teatime.CAKE; }
		else { return Items_Teatime.CAKE; }
	}
	
	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (state.get(WATERLOGGED)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.SCONESET_kara.getDefaultState()
					.with(SconeSet_kara.H_FACING, state.get(H_FACING))
					.with(SconeSet_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
}
