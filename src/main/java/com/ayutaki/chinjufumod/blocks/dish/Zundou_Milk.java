package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.hakkou.Zundou_ColdMilk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou_Milk extends BaseZundou_2Stage {
	/** 1=cold, 2=hot **/
	public Zundou_Milk(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);
		/** 1=cold, 2=hot **/
		
		if (i == 1) { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
		
		if (i == 2) {
			if (hItem != Items_Teatime.NYUSAN.get()) { CMEvents.textRequestCool(worldIn, pos, playerIn); }
			if (hItem == Items_Teatime.NYUSAN.get()) { CMEvents.textEarlyUse(worldIn, pos, playerIn); } }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterOUT(state)) { 
			int i = state.getValue(STAGE_1_2);
			
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) { 
			int i = state.getValue(STAGE_1_2);
			
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_2);
			/** 1=cold, 2=hot **/
			
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); }
			
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME);
				worldIn.setBlock(pos, Hakkou_Blocks.COLD_MILK.get().defaultBlockState()
						.setValue(Zundou_ColdMilk.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou_ColdMilk.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			else { }
		}
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
					.setValue(Zundou.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou.STAGE_1_2, Integer.valueOf(2))
					.setValue(Zundou.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}
}
