package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class Nabe_NamaGohan extends BaseNabe_nama {

	public Nabe_NamaGohan(AbstractBlock.Properties props) {
		super(props);
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.getBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, this.takeBlock().defaultBlockState()
						.setValue(BaseNabe.H_FACING, state.getValue(H_FACING))
						.setValue(BaseNabe.COOK, state.getValue(COOK))
						.setValue(BaseNabe.DOWN, state.getValue(DOWN))
						.setValue(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3);
				CMEvents.addEXP(1, worldIn, pos); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.COOK, state.getValue(COOK))
					.setValue(Nabe_kara.DOWN, state.getValue(DOWN))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	private Block takeBlock() {
		if (this == Dish_Blocks.NABEGOHAN_nama) { return Dish_Blocks.NABEGOHAN; }
		if (this == Dish_Blocks.NABEGOHANTAKE_nama) { return Dish_Blocks.NABEGOHAN_TAKE; }
		if (this == Dish_Blocks.NABEGOHANKURI_nama) { return Dish_Blocks.NABEGOHAN_KURI; }
		else { return Dish_Blocks.NABESEKIHAN; }
	}
}
