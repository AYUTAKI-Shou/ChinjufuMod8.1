package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class Nabe_NamaSoup extends BaseNabe_nama {

	public Nabe_NamaSoup(Block.Properties props) {
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
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(takeInt()))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	private Block takeBlock() {
		if (this == Hakkou_Blocks.NABEAMAZAKE_nama) { return Hakkou_Blocks.NABEAMAZAKE; }
		if (this == Dish_Blocks.NABECORN_nama) { return Dish_Blocks.NABECORN; }
		if (this == Dish_Blocks.NABEMISO_nama) { return Dish_Blocks.NABEMISO; }
		else { return Dish_Blocks.NABETORI; }
	}
	
	private int takeInt() {
		if (this == Hakkou_Blocks.NABEAMAZAKE_nama) { return 4; }
		else { return 2; }
	}
}
