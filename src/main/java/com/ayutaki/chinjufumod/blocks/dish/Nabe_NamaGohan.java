package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class Nabe_NamaGohan extends BaseNabe_nama {
	
	public Nabe_NamaGohan(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, this.takeBlock().defaultBlockState()
						.setValue(BaseNabe.H_FACING, state.getValue(H_FACING))
						.setValue(BaseNabe.COOK, state.getValue(COOK))
						.setValue(BaseNabe.DOWN, state.getValue(DOWN))
						.setValue(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3);
				CMEvents.addEXP(1, worldIn, pos); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.COOK, state.getValue(COOK))
					.setValue(Nabe_kara.DOWN, state.getValue(DOWN))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	private Block takeBlock() {
		if (this == Dish_Blocks.NABEGOHAN_nama.get()) { return Dish_Blocks.NABEGOHAN.get(); }
		if (this == Dish_Blocks.NABEGOHANTAKE_nama.get()) { return Dish_Blocks.NABEGOHAN_TAKE.get(); }
		if (this == Dish_Blocks.NABEGOHANKURI_nama.get()) { return Dish_Blocks.NABEGOHAN_KURI.get(); }
		else { return Dish_Blocks.NABESEKIHAN.get(); }
	}
}
