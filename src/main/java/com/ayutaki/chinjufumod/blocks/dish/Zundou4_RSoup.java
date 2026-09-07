package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Zundou4_RSoup extends BaseZundou_4Stage {

	public Zundou4_RSoup(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);
		
		boolean tare = (hItem == Items_Teatime.SHOUYU_TARE || hItem == Items_Teatime.MISO_TARE || hItem == Items_Teatime.SHIO_TARE);
		
		if (tare) {
			if (hItem == Items_Teatime.SHOUYU_TARE) {
				/** Collect with an Item **/
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.SHOUYU_Rsoup); }
			
			if (hItem == Items_Teatime.MISO_TARE) { 
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MISO_Rsoup); }
			
			if (hItem == Items_Teatime.SHIO_TARE) { 
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.SHIO_Rsoup); }

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
						.with(Zundou.H_FACING, state.get(H_FACING))
						.with(Zundou.STAGE_1_2, Integer.valueOf(2)), 3); }
			else { //i != 4
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		
		if (!tare && hItem != Items_Teatime.SHOUYU_Rsoup && hItem != Items_Teatime.MISO_Rsoup && hItem != Items_Teatime.SHIO_Rsoup) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
					.with(Zundou.H_FACING, state.get(H_FACING))
					.with(Zundou.STAGE_1_2, Integer.valueOf(2))
					.with(Zundou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
		
		else { }
	}
}
