package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou4_Pasta extends BaseZundou_4Cook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_Pasta(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
		
		if (hItem == Items_Teatime.SARA.get()) {
			if (i == 3 || i == 4) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.PASTA_sara.get());
				CMEvents.addEXP(1, worldIn, pos);
				
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_SHIO.get().defaultBlockState()
						.setValue(Zundou_Shio.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou_Shio.STAGE_1_2, Integer.valueOf(i - 2)), 3); }
				
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		if (hItem != Items_Teatime.SARA.get() && hItem != Items_Teatime.PASTA_nama.get()) { 
			CMEvents.textNotHave(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Teatime.ZUNDOU_SHIO.get());
	}
}
