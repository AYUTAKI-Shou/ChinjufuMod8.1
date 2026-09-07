package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou_NamaCurry extends BaseZundou_2Stage {

	public Zundou_NamaCurry(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		CMEvents.textRequestHeat(worldIn, pos, playerIn);
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterOUT(state)) { 
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) { 
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				int i = state.getValue(STAGE_1_2);
				/** 1=raw, 2=cook **/
				
				if (i == 1) {
					worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
					worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); }
				
				else { //i != 1
					worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
					worldIn.setBlock(pos, this.takeBlock().defaultBlockState()
							.setValue(BaseZundou_4Stage.H_FACING, state.getValue(H_FACING))
							.setValue(BaseZundou_4Stage.STAGE_1_4, Integer.valueOf(1)), 3);
					CMEvents.addEXP(1, worldIn, pos); } }
			
			else { }
		}
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
					.setValue(Zundou.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou.STAGE_1_2, Integer.valueOf(2))
					.setValue(Zundou.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	private Block takeBlock() {
		if (this == Dish_Blocks.ZUNDOU_NCURRY.get()) { return Dish_Blocks.ZUNDOU_CURRY.get(); }
		if (this == Dish_Blocks.ZUNDOU_NCURRY_C.get()) { return Dish_Blocks.ZUNDOU_CURRY_C.get(); }
		if (this == Dish_Blocks.ZUNDOU_NCURRY_T.get()) { return Dish_Blocks.ZUNDOU_CURRY_T.get(); }
		else { return Dish_Blocks.ZUNDOU_STEW.get(); }
	}
}
