package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class Zundou4_RSoup_nama extends BaseZundou_4LongCook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_RSoup_nama(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_4);
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/

		if (hStack.isEmpty()) {
			if (i == 3 || i == 4) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items.BONE_MEAL, 4);
	
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_RSOUP.getDefaultState()
						.with(Zundou4_RSoup.H_FACING, state.get(H_FACING))
						.with(Zundou4_RSoup.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
}
