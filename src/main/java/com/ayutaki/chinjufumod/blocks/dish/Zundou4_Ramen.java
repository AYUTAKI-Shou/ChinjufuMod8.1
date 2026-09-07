package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou4_Ramen extends BaseZundou_4Cook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_Ramen(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);
		
		boolean soup = (hItem == Items_Teatime.SHOUYU_Rsoup.get() || hItem == Items_Teatime.MISO_Rsoup.get() || hItem == Items_Teatime.SHIO_Rsoup.get() || 
				hItem == Items_Teatime.SARA.get());
		boolean other = (hItem == Items_Teatime.RAMEN_SHOUYU.get() || hItem == Items_Teatime.RAMEN_MISO.get() || hItem == Items_Teatime.RAMEN_SHIO.get() || 
				hItem == Items_Teatime.RAMEN_nama.get());
		
		if (!soup && !other) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
		else {
			if (i == 3 || i == 4) {

				if (hItem == Items_Teatime.SHOUYU_Rsoup.get()) {
					/** Collect with an Item **/
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_SHOUYU.get());
					CMEvents.addEXP(1, worldIn, pos); }
				
				if (hItem == Items_Teatime.MISO_Rsoup.get()) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_MISO.get());
					CMEvents.addEXP(1, worldIn, pos); }
				
				if (hItem == Items_Teatime.SHIO_Rsoup.get()) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_SHIO.get());
					CMEvents.addEXP(1, worldIn, pos); }
				
				if (hItem == Items_Teatime.SARA.get()) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.SOBA_PLATE.get()); }
				
				if (soup) {
					worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_MIZU.get().defaultBlockState()
							.setValue(Zundou_Mizu.H_FACING, state.getValue(H_FACING))
							.setValue(Zundou_Mizu.STAGE_1_2, Integer.valueOf(i - 2)), 3); }
				
				if (!soup) { }
			}
				
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.ZUNDOU_MIZU.get());
	}
}
