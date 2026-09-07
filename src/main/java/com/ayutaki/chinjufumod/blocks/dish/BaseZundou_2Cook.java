package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BaseZundou_2Cook extends BaseZundou_2Stage {

	public BaseZundou_2Cook(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_2);
			
			if (cookingIn(worldIn, pos) && i == 1) {
				tick.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5))); }
			
			if (cookingOUT(worldIn, pos) && i != 1) {
				tick.scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { tick.scheduleTick(pos, this, 60); }

		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
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

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		
		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_2);
			/** 1=cold, 2=hot **/
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); }
			
			if (cookingOUT(worldIn, pos) && i != 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME);
				worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(1)), 3); }
			
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
