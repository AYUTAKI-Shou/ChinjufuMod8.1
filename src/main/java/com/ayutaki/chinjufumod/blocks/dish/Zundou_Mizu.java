package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class Zundou_Mizu extends BaseZundou_2Cook {
	/** 1=cold, 2=hot **/
	public Zundou_Mizu(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_2);
		/** 1=cold, 2=hot **/

		/** 1=塩水, 2=塩湯 **/
		if (hItem == Items_Teatime.SHIO) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_SHIO.getDefaultState()
					.with(Zundou_Shio.H_FACING, state.get(H_FACING))
					.with(Zundou_Shio.STAGE_1_2, Integer.valueOf(i)));
		}
		
		/** 1=灰汁水, 2=灰汁湯 **/
		if (hItem == Items_Seasonal.WARAHAI) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_AKU.getDefaultState()
					.with(Zundou_Aku.H_FACING, state.get(H_FACING))
					.with(Zundou_Aku.STAGE_1_2, Integer.valueOf(i)));
		}
		
		/** 出汁へ **/
		if (hItem == Items_Teatime.DASHI_bot_14) {
			if (i == 2) {
				CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE);
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_DASHI.getDefaultState()
						.with(Zundou4_Dashi.H_FACING, state.get(H_FACING))
						.with(Zundou4_Dashi.STAGE_1_4, Integer.valueOf(1))); }
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		/** うどん **/
		if (hItem == Items_Teatime.UDON_nama) {
			if (i == 2) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_UDON.getDefaultState()
						.with(Zundou4_Udon.H_FACING, state.get(H_FACING))
						.with(Zundou4_Udon.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
		
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		/** ラーメン**/
		if (hItem == Items_Teatime.RAMEN_nama) {
			if (i == 2) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_RAMEN.getDefaultState()
						.with(Zundou4_Ramen.H_FACING, state.get(H_FACING))
						.with(Zundou4_Ramen.STAGE_1_4, Integer.valueOf(1))); } //Large Items cool it down.
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		if (hItem != Items_Teatime.SHIO && hItem != Items_Seasonal.WARAHAI && hItem != Items_Teatime.DASHI_bot_14 && 
				hItem != Items_Teatime.UDON_nama && hItem != Items_Teatime.RAMEN_nama) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
}
