package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou4_RSoup_nama extends BaseZundou_4LongCook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_RSoup_nama(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/

		if (hStack.isEmpty()) {
			if (i == 3 || i == 4) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items.BONE_MEAL, 4);
	
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_RSOUP.get().defaultBlockState()
						.setValue(Zundou4_RSoup.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_RSoup.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
}
