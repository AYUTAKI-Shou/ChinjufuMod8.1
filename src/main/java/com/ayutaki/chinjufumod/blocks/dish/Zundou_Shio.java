package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou_Shio extends BaseZundou_2Cook {
	/** 1=cold, 2=hot **/
	public Zundou_Shio(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);
		/** 1=cold, 2=hot **/

		if (i == 2) {
			if (hItem == Items_Teatime.PASTA_nama.get()) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_PASTA.get().defaultBlockState()
						.setValue(Zundou4_Pasta.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Pasta.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
	
			if (hItem == Items.COD) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_FISH.get().defaultBlockState()
						.setValue(Zundou4_Fish.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Fish.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
			
			if (hItem != Items_Teatime.PASTA_nama.get() && hItem != Items.COD && hItem != Items_Teatime.SHIO.get()) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to use **/
		else { //i != 2
			CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, blockTip, tipFlag);
		blockTip.add(Component.translatable("tips.block_zundou_shiomizu").withStyle(ChatFormatting.GRAY));
	}
}
