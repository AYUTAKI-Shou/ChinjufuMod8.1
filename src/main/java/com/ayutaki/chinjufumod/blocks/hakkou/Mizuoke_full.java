package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Mizuoke_full extends BaseStage2_FaceWater {
	/* Collision */
	private static final VoxelShape BASE = Block.box(5.0D, 0.5D, 5.0D, 11.0D, 1.5D, 11.0D);
	private static final VoxelShape BODY = Shapes.or(BASE, 
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 5.0D),
			Block.box(4.0D, 0.0D, 11.0D, 12.0D, 9.0D, 12.0D),
			Block.box(4.0D, 0.0D, 5.0D, 5.0D, 9.0D, 11.0D),
			Block.box(11.0D, 0.0D, 5.0D, 12.0D, 9.0D, 11.0D));
	
	private static final VoxelShape AABB_SOUTH = Shapes.or(BODY, 
			Block.box(3.5D, 13.0D, 7.5D, 12.5D, 14.0D, 8.5D),
			Block.box(4.0D, 9.0D, 7.0D, 5.0D, 15.0D, 9.0D),
			Block.box(11.0D, 9.0D, 7.0D, 12.0D, 15.0D, 9.0D));
	private static final VoxelShape AABB_WEST = Shapes.or(BODY, 
			Block.box(7.5D, 13.0D, 3.5D, 8.5D, 14.0D, 12.5D),
			Block.box(7.0D, 9.0D, 4.0D, 9.0D, 15.0D, 5.0D),
			Block.box(7.0D, 9.0D, 11.0D, 9.0D, 15.0D, 12.0D));
	private static final VoxelShape AABB_NORTH = Shapes.or(BODY, 
			Block.box(3.5D, 13.0D, 7.5D, 12.5D, 14.0D, 8.5D),
			Block.box(4.0D, 9.0D, 7.0D, 5.0D, 15.0D, 9.0D),
			Block.box(11.0D, 9.0D, 7.0D, 12.0D, 15.0D, 9.0D));
	private static final VoxelShape AABB_EAST = Shapes.or(BODY, 
			Block.box(7.5D, 13.0D, 3.5D, 8.5D, 14.0D, 12.5D),
			Block.box(7.0D, 9.0D, 4.0D, 9.0D, 15.0D, 5.0D),
			Block.box(7.0D, 9.0D, 11.0D, 9.0D, 15.0D, 12.0D));
	
	public Mizuoke_full(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);

		/** バケツ **/
		if (hItem == Items.BUCKET && !state.getValue(WATERLOGGED)) {
			CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items.WATER_BUCKET);

			if (i == 1) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(1)), 3); }

			else { //i != 1
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(2)), 3); }

			return InteractionResult.SUCCESS;
		}

		if (hItem == Items_Teatime.MIZUOKE.get() && !state.getValue(WATERLOGGED)) {
			CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MIZUOKE_full.get());

			if (i == 1) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(1)), 3); }

			else { //i != 1
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(2)), 3); }

			return InteractionResult.SUCCESS;
		}

		/** ガラス瓶 **/
		if (hItem == Items.GLASS_BOTTLE && !state.getValue(WATERLOGGED)) {
			CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand);

			if (i == 1) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(3)), 3); }

			else { //i != 1
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(4)), 3); }

			return InteractionResult.SUCCESS;
		}

		/** 計量カップ **/
		if (hItem == Items_Teatime.KEIRYO_CUP.get() && !state.getValue(WATERLOGGED)) {
			CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full.get());

			if (i == 1) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(3)), 3); }

			else { //i != 1
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
						.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(4)), 3); }

			return InteractionResult.SUCCESS;
		}
		
		/** 洗う **/
		else {

			if (hStack.is(ItemTags.DYEABLE)) {
				if (hStack.has(DataComponents.DYED_COLOR) && !worldIn.isClientSide) {
					CMEvents.soundWaterUse(worldIn, pos);
					
					if (i == 1) {
						worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
								.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
								.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(3)), 3); }
					else { //i != 1
						worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
								.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
								.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(4)), 3); }
					
					hStack.remove(DataComponents.DYED_COLOR);
					playerIn.awardStat(Stats.CLEAN_ARMOR);
				}
				return InteractionResult.SUCCESS;
			} //鎧

			if (hItem instanceof BannerItem) {
				BannerPatternLayers bannerLayers = hStack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
				
				if (!bannerLayers.layers().isEmpty() && !worldIn.isClientSide) {
					CMEvents.wash_Banner(worldIn, pos, playerIn, hand);
					
					if (i == 1) {
						worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
								.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
								.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(3)), 3); }
					else { //i != 1
						worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
								.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
								.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(4)), 3); } }
				
				return InteractionResult.SUCCESS;
			} //旗

			if (hItem instanceof BlockItem) {
				Block block = ((BlockItem)hItem).getBlock();
				if (block instanceof ShulkerBoxBlock && !worldIn.isClientSide()) {
					CMEvents.wash_Shulker(worldIn, pos, playerIn, hand);

					if (i == 1) {
						worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
								.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
								.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(3)), 3); }
					else { //i != 1
						worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
								.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
								.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(4)), 3); }
					return InteractionResult.SUCCESS;
				}
				else { return InteractionResult.CONSUME; }
			} //シェルカー

			if (hItem instanceof Base_Hake) {
				CMEvents.washHAKE_MIZUOKE(worldIn, pos, playerIn, hand);
				
				if (i == 1) {
					worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
							.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
							.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(3)), 3); }
				else { //i != 1
					worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE.get().defaultBlockState()
							.setValue(Mizuoke.H_FACING, state.getValue(H_FACING))
							.setValue(Mizuoke.STAGE_1_4, Integer.valueOf(4)), 3); }
				return InteractionResult.SUCCESS;
			} //色筆
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.MIZUOKE_full.get(), 10); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_2);
		
		if (i == 1 && state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.MIZUOKE_full.get(), 10);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); }
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_2);

		if (state.getValue(WATERLOGGED) || !worldIn.isRainingAt(pos.above()) || i == 2) { }
		
		else { //Rain == true
			if (rand.nextInt(1) == 0) { worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); } }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case EAST: return AABB_EAST;
		case WEST: return AABB_WEST;
		} // switch
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Teatime.MIZUOKE_full.get());
	}
}
