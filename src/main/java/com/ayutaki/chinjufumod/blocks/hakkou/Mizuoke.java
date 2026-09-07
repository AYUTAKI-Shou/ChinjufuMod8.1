package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Abstract_Hake;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Mizuoke extends BaseStage4_FaceWater {
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
	
	/* Property 空=1, 2=1.7, 3=3.4, 4=5.1, 水入り5=6.8, 水入り6=8.5 */
	public Mizuoke(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action net.minecraft.core.cauldron.CauldronInteraction */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);

		/** バケツ **/
		if (hItem == Items.WATER_BUCKET && !state.getValue(WATERLOGGED)) {
			CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
			
			if (i == 1) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
						.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(1)), 3); }

			else { //i != 1
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
						.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(2)), 3); }
			
			return InteractionResult.SUCCESS;
		}

		if (hItem == Items_Teatime.MIZUOKE_full.get() && !state.getValue(WATERLOGGED)) {
			CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
			
			if (i == 1) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
						.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(1)), 3); }

			else { //i != 1
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
						.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(2)), 3); }
			
			return InteractionResult.SUCCESS;
		}

		/** ガラス瓶 **/
		if (i > 2 && hItem == Items.GLASS_BOTTLE && !state.getValue(WATERLOGGED)) {
			CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 2)), 3);
			return InteractionResult.SUCCESS;
		}

		/** 水入りガラス瓶 **/
		if (hItem == Items.POTION && PotionUtils.getPotion(hStack) == Potions.WATER) {
			worldIn.playSound((Player)null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
			CMEvents.mode1Through_Consume(playerIn, hand, Items.GLASS_BOTTLE);

			if (i < 3) {worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 2)), 3); }
			if (i == 3) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
						.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(1)), 3); }

			if (i == 4) {
				worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
						.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(2)), 3); }

			return InteractionResult.SUCCESS;
		}

		/** 計量カップ **/
		if (i > 2 && hItem == Items_Teatime.KEIRYO_CUP.get() && !state.getValue(WATERLOGGED)) {
			CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full.get());
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 2)), 3);
			return InteractionResult.SUCCESS;
		}
		
		/** 洗う **/
		else {
			if (i > 2 && hItem instanceof DyeableLeatherItem) {
				DyeableLeatherItem dyeableArmor = (DyeableLeatherItem)hItem;
				if (dyeableArmor.hasCustomColor(hStack) && !worldIn.isClientSide) {
					dyeableArmor.clearColor(hStack);
					CMEvents.soundWaterUse(worldIn, pos);
					
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 2)), 3);
					playerIn.awardStat(Stats.CLEAN_ARMOR); }
				
				return InteractionResult.SUCCESS;
			} //鎧

			if (i > 2 && hItem instanceof BannerItem) {
				if (BannerBlockEntity.getPatternCount(hStack) > 0 && !worldIn.isClientSide) {
					CMEvents.wash_Banner(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 2)), 3); }
				
				return InteractionResult.SUCCESS;
			} //旗

			if (i > 2 && hItem instanceof BlockItem) {
				Block block = ((BlockItem)hItem).getBlock();
				
				if (block instanceof ShulkerBoxBlock && !worldIn.isClientSide()) {
					CMEvents.wash_Shulker(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 2)), 3);
					return InteractionResult.SUCCESS; }
				
				else { return InteractionResult.CONSUME; }
			} //シュルカー

			if (i > 2 && hItem instanceof Abstract_Hake) {
				CMEvents.washHAKE_MIZUOKE(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 2)), 3);
				return InteractionResult.SUCCESS;
			} //色筆
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.MIZUOKE.get(), 10); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.MIZUOKE.get(), 10); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.MIZUOKE.get(), 10);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
					.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
					.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(2))
					.setValue(Mizuoke_full.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_4);

		if (state.getValue(WATERLOGGED) || !worldIn.isRainingAt(pos.above())) { }
		
		else { //Rain == true
			if (rand.nextInt(1) == 0) {
				if (i == 4) {
					worldIn.setBlock(pos, Hakkou_Blocks.MIZUOKE_full.get().defaultBlockState()
							.setValue(Mizuoke_full.H_FACING, state.getValue(H_FACING))
							.setValue(Mizuoke_full.STAGE_1_2, Integer.valueOf(1))
							.setValue(Mizuoke_full.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
				
				else { //i != 4
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); } }
		}
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
	public boolean propagatesSkylightDown(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return true;
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.MIZUOKE.get());
	}
}
