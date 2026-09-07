package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Frypan_Katsu extends BaseFrypan_2Cook {

	public Frypan_Katsu(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		int i = state.getValue(STAGE_1_2);
		
		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, blockTip, tipFlag);
		blockTip.add(Component.translatable("tips.take_break").withStyle(ChatFormatting.GRAY));
	}
}
