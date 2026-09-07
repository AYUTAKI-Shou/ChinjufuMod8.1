package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
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
	public Zundou_Mizu(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);
		/** 1=cold, 2=hot **/

		/** 1=塩水, 2=塩湯 **/
		if (hItem == Items_Teatime.SHIO) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_SHIO.defaultBlockState()
					.setValue(Zundou_Shio.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Shio.STAGE_1_2, Integer.valueOf(i)), 3);
		}
		
		/** 1=灰汁水, 2=灰汁湯 **/
		if (hItem == Items_Seasonal.WARAHAI) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_AKU.defaultBlockState()
					.setValue(Zundou_Aku.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Aku.STAGE_1_2, Integer.valueOf(i)), 3);
		}
		
		/** 出汁へ **/
		if (hItem == Items_Teatime.DASHI_bot_14) {
			if (i == 2) {
				CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE);
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_DASHI.defaultBlockState()
						.setValue(Zundou4_Dashi.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Dashi.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		/** うどん **/
		if (hItem == Items_Teatime.UDON_nama) {
			if (i == 2) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_UDON.defaultBlockState()
						.setValue(Zundou4_Udon.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Udon.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
			
			/** Too early to use **/
			else { //i != 2
				CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		}
		
		/** ラーメン**/
		if (hItem == Items_Teatime.RAMEN_nama) {
			if (i == 2) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_RAMEN.defaultBlockState()
						.setValue(Zundou4_Ramen.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Ramen.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
			
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
