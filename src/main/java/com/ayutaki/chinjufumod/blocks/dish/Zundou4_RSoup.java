package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou4_RSoup extends BaseZundou_4Stage {
	/* 色が安定しないため flow は却下, still のみ */
	public Zundou4_RSoup(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		int i = state.getValue(STAGE_1_4);
		boolean tare = (hItem == Items_Teatime.SHOUYU_TARE.get() || hItem == Items_Teatime.MISO_TARE.get() || hItem == Items_Teatime.SHIO_TARE.get());
		
		if (tare) {
			if (hItem == Items_Teatime.SHOUYU_TARE.get()) {
				/** Collect with an Item **/
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.SHOUYU_Rsoup.get()); }
			
			if (hItem == Items_Teatime.MISO_TARE.get()) { 
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MISO_Rsoup.get()); }
			
			if (hItem == Items_Teatime.SHIO_TARE.get()) { 
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.SHIO_Rsoup.get()); }

			if (i == 4) {
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
						.setValue(Zundou.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou.STAGE_1_2, Integer.valueOf(2)), 3); }
			else { //i != 4
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		
		if (!tare && hItem != Items_Teatime.SHOUYU_Rsoup.get() && hItem != Items_Teatime.MISO_Rsoup.get() && hItem != Items_Teatime.SHIO_Rsoup.get()) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
					.setValue(Zundou.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou.STAGE_1_2, Integer.valueOf(2))
					.setValue(Zundou.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}
}
