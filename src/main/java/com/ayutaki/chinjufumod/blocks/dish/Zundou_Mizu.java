package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou_Mizu extends BaseZundou_2Cook {
	/** 1=cold, 2=hot **/
	public Zundou_Mizu(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);
		/** 1=cold, 2=hot **/

		/** 1=塩水, 2=塩湯 **/
		if (hItem == Items_Teatime.SHIO.get()) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_SHIO.get().defaultBlockState()
					.setValue(Zundou_Shio.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Shio.STAGE_1_2, Integer.valueOf(i)), 3);
		}
		
		/** 1=灰汁水, 2=灰汁湯 **/
		if (hItem == Items_Seasonal.WARAHAI.get()) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_AKU.get().defaultBlockState()
					.setValue(Zundou_Aku.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Aku.STAGE_1_2, Integer.valueOf(i)), 3);
		}
		
		/** 出汁へ **/
		if (hItem == Items_Teatime.DASHI_bot_14.get()) {
			if (i == 2) {
				CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE);
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_DASHI.get().defaultBlockState()
						.setValue(Zundou4_Dashi.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Dashi.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		/** うどん **/
		if (hItem == Items_Teatime.UDON_nama.get()) {
			if (i == 2) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_UDON.get().defaultBlockState()
						.setValue(Zundou4_Udon.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Udon.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		/** ラーメン**/
		if (hItem == Items_Teatime.RAMEN_nama.get()) {
			if (i == 2) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_RAMEN.get().defaultBlockState()
						.setValue(Zundou4_Ramen.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Ramen.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		if (hItem != Items_Teatime.SHIO.get() && hItem != Items_Seasonal.WARAHAI.get() && hItem != Items_Teatime.DASHI_bot_14.get() && 
				hItem != Items_Teatime.UDON_nama.get() && hItem != Items_Teatime.RAMEN_nama.get()) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
}
