package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.dish.BaseZundou_4Stage;
import com.ayutaki.chinjufumod.blocks.dish.Zundou;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Zundou_ColdMilk extends BaseZundou_4Stage {
	/** 1=COLD_MILK, 2=NYUSAN, 3=RENNET, 4=CURD **/
	public Zundou_ColdMilk(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);

		if (i == 1) {
			if (hItem == Items_Teatime.NYUSAN) {
				CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE);
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(2))); }
			
			if (hItem != Items_Teatime.NYUSAN) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i == 2) {
			if (hItem == Items_Teatime.RENNET) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(3))); }
			
			if (hItem != Items_Teatime.RENNET) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 4) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD);
				CMEvents.addEXP(1, worldIn, pos);
				
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
						.with(Zundou.H_FACING, state.get(H_FACING))
						.with(Zundou.STAGE_1_2, Integer.valueOf(2))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		int i = state.get(STAGE_1_4);
		if (waterOUT(state) && i == 3) { 
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.COLD_MILK, 300 + (50 * worldIn.getRandom().nextInt(5))); }
		
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.get(STAGE_1_4);
		if (waterOUT(state) && i == 3) { 
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.COLD_MILK, 300 + (50 * worldIn.getRandom().nextInt(5))); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_4);
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		if (waterOUT(state) && i == 3) { 
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.COLD_MILK, 300 + (50 * rand.nextInt(5)));
			worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4))); }

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Hakkou_Blocks.COLD_MILK, 60);
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
					.with(Zundou.H_FACING, state.get(H_FACING))
					.with(Zundou.STAGE_1_2, Integer.valueOf(2))); }
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.ZUNDOU);
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
