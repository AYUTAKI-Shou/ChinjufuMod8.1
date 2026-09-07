package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou_Aku extends BaseZundou_2Cook {
	/** 1=cold, 2=hot **/
	public Zundou_Aku(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (hItem == Items_Seasonal.KUSATABA.get()) {
			CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_ORIITO.get().defaultBlockState()
					.setValue(Zundou4_Oriito.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou4_Oriito.STAGE_1_4, Integer.valueOf(1)), 3); //Large Items cool it down.
		}
		
		if (hItem != Items_Seasonal.KUSATABA.get() && hItem != Items_Seasonal.WARAHAI.get()) { 
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, blockTip, tipFlag);
		blockTip.add(Component.translatable("tips.block_zundou_aku").withStyle(ChatFormatting.GRAY));
	}
}
