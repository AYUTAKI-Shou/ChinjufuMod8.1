package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class Nabe_NamaGohan extends BaseNabe_nama {

	public Nabe_NamaGohan(Block.Properties props) {
		super(props);
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlockState(pos, this.takeBlock().getDefaultState()
						.with(BaseNabe.H_FACING, state.get(H_FACING))
						.with(BaseNabe.COOK, state.get(COOK))
						.with(BaseNabe.DOWN, state.get(DOWN))
						.with(BaseNabe.STAGE_1_4, Integer.valueOf(1)));
				CMEvents.addEXP(1, worldIn, pos); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
					.with(Nabe_kara.H_FACING, state.get(H_FACING))
					.with(Nabe_kara.COOK, state.get(COOK))
					.with(Nabe_kara.DOWN, state.get(DOWN))
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(3))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	private Block takeBlock() {
		if (this == Dish_Blocks.NABEGOHAN_nama) { return Dish_Blocks.NABEGOHAN; }
		if (this == Dish_Blocks.NABEGOHANTAKE_nama) { return Dish_Blocks.NABEGOHAN_TAKE; }
		if (this == Dish_Blocks.NABEGOHANKURI_nama) { return Dish_Blocks.NABEGOHAN_KURI; }
		else { return Dish_Blocks.NABESEKIHAN; }
	}
}
