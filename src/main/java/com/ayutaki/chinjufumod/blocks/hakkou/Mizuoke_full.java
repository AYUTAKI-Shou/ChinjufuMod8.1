package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Abstract_Hake;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class Mizuoke_full extends BaseStage2_FaceWater {
	/* Collision */
	private static final VoxelShape BASE = Block.makeCuboidShape(5.0D, 0.5D, 5.0D, 11.0D, 1.5D, 11.0D);
	private static final VoxelShape BODY = VoxelShapes.or(BASE, 
			Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 5.0D),
			Block.makeCuboidShape(4.0D, 0.0D, 11.0D, 12.0D, 9.0D, 12.0D),
			Block.makeCuboidShape(4.0D, 0.0D, 5.0D, 5.0D, 9.0D, 11.0D),
			Block.makeCuboidShape(11.0D, 0.0D, 5.0D, 12.0D, 9.0D, 11.0D));
	
	private static final VoxelShape AABB_SOUTH = VoxelShapes.or(BODY, 
			Block.makeCuboidShape(3.5D, 13.0D, 7.5D, 12.5D, 14.0D, 8.5D),
			Block.makeCuboidShape(4.0D, 9.0D, 7.0D, 5.0D, 15.0D, 9.0D),
			Block.makeCuboidShape(11.0D, 9.0D, 7.0D, 12.0D, 15.0D, 9.0D));
	private static final VoxelShape AABB_WEST = VoxelShapes.or(BODY, 
			Block.makeCuboidShape(7.5D, 13.0D, 3.5D, 8.5D, 14.0D, 12.5D),
			Block.makeCuboidShape(7.0D, 9.0D, 4.0D, 9.0D, 15.0D, 5.0D),
			Block.makeCuboidShape(7.0D, 9.0D, 11.0D, 9.0D, 15.0D, 12.0D));
	private static final VoxelShape AABB_NORTH = VoxelShapes.or(BODY, 
			Block.makeCuboidShape(3.5D, 13.0D, 7.5D, 12.5D, 14.0D, 8.5D),
			Block.makeCuboidShape(4.0D, 9.0D, 7.0D, 5.0D, 15.0D, 9.0D),
			Block.makeCuboidShape(11.0D, 9.0D, 7.0D, 12.0D, 15.0D, 9.0D));
	private static final VoxelShape AABB_EAST = VoxelShapes.or(BODY, 
			Block.makeCuboidShape(7.5D, 13.0D, 3.5D, 8.5D, 14.0D, 12.5D),
			Block.makeCuboidShape(7.0D, 9.0D, 4.0D, 9.0D, 15.0D, 5.0D),
			Block.makeCuboidShape(7.0D, 9.0D, 11.0D, 9.0D, 15.0D, 12.0D));

	public Mizuoke_full(Block.Properties props) {
		super(props);
	}

	/* RightClick Action Cauldron */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_2);

		/** バケツ **/
		if (hItem == Items.BUCKET && !state.get(WATERLOGGED)) {
			CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items.WATER_BUCKET);

			if (i == 1) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(1))); }

			else { //i != 1
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(2))); }

			return ActionResultType.SUCCESS;
		}

		if (hItem == Items_Teatime.MIZUOKE && !state.get(WATERLOGGED)) {
			CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MIZUOKE_full);

			if (i == 1) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(1))); }

			else { //i != 1
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(2))); }

			return ActionResultType.SUCCESS;
		}

		/** ガラス瓶 **/
		if (hItem == Items.GLASS_BOTTLE && !state.get(WATERLOGGED)) {
			CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand);

			if (i == 1) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(3))); }

			else { //i != 1
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(4))); }

			return ActionResultType.SUCCESS;
		}

		/** 計量カップ **/
		if (hItem == Items_Teatime.KEIRYO_CUP && !state.get(WATERLOGGED)) {
			CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full);

			if (i == 1) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(3))); }

			else { //i != 1
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
						.with(Mizuoke.H_FACING, state.get(H_FACING))
						.with(Mizuoke.STAGE_1_4, Integer.valueOf(4))); }

			return ActionResultType.SUCCESS;
		}

		/** 洗う **/
		else {
			if (hItem instanceof IDyeableArmorItem) {
				IDyeableArmorItem dyeableArmor = (IDyeableArmorItem)hItem;
				if (dyeableArmor.hasColor(hStack) && !worldIn.isRemote) {
					dyeableArmor.removeColor(hStack);
					CMEvents.soundWaterUse(worldIn, pos);

					if (i == 1) {
						worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
								.with(Mizuoke.H_FACING, state.get(H_FACING))
								.with(Mizuoke.STAGE_1_4, Integer.valueOf(3))); }
					else { //i != 1
						worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
								.with(Mizuoke.H_FACING, state.get(H_FACING))
								.with(Mizuoke.STAGE_1_4, Integer.valueOf(4))); }

					playerIn.addStat(Stats.CLEAN_ARMOR); }
				
				return ActionResultType.SUCCESS;
			} //鎧

			if (hItem instanceof BannerItem) {
				if (BannerTileEntity.getPatterns(hStack) > 0 && !worldIn.isRemote) {
					CMEvents.wash_Banner(worldIn, pos, playerIn, hand);
					
					if (i == 1) {
						worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
								.with(Mizuoke.H_FACING, state.get(H_FACING))
								.with(Mizuoke.STAGE_1_4, Integer.valueOf(3))); }
					else { //i != 1
						worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
								.with(Mizuoke.H_FACING, state.get(H_FACING))
								.with(Mizuoke.STAGE_1_4, Integer.valueOf(4))); } }
				
				return ActionResultType.SUCCESS;
			} //旗

			if (hItem instanceof BlockItem) {
				Block block = ((BlockItem)hItem).getBlock();
				
				if (block instanceof ShulkerBoxBlock && !worldIn.isRemote()) {
					CMEvents.wash_Shulker(worldIn, pos, playerIn, hand);
					
					if (i == 1) {
						worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
								.with(Mizuoke.H_FACING, state.get(H_FACING))
								.with(Mizuoke.STAGE_1_4, Integer.valueOf(3))); }
					else { //i != 1
						worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
								.with(Mizuoke.H_FACING, state.get(H_FACING))
								.with(Mizuoke.STAGE_1_4, Integer.valueOf(4))); }
					return ActionResultType.SUCCESS; }
				
				else { return ActionResultType.CONSUME; }
			} //シュルカー

			if (hItem instanceof Abstract_Hake) {
				CMEvents.washHAKE_MIZUOKE(worldIn, pos, playerIn, hand);
				
				if (i == 1) {
					worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
							.with(Mizuoke.H_FACING, state.get(H_FACING))
							.with(Mizuoke.STAGE_1_4, Integer.valueOf(3))); }
				else { //i != 1
					worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE.getDefaultState()
							.with(Mizuoke.H_FACING, state.get(H_FACING))
							.with(Mizuoke.STAGE_1_4, Integer.valueOf(4))); }
				return ActionResultType.SUCCESS;
			} //色筆
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.MIZUOKE_full, 10); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.MIZUOKE_full, 10); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_2);
		
		if (i == 1 && state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.MIZUOKE_full, 10);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2))); }
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_2);

		if (i == 1 && !state.get(WATERLOGGED) && worldIn.isRainingAt(pos.up())) {
			if (rand.nextInt(1) == 0) { worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2))); }
		}
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case EAST: return AABB_EAST;
		case WEST: return AABB_WEST;
		} // switch
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.MIZUOKE_full);
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

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
