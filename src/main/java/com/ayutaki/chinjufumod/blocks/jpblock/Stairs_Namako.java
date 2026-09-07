package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class Stairs_Namako extends Base_Stairs_JP {

	public Stairs_Namako(BlockState state, BlockBehaviour.Properties props) {
		super(state, props);
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_namako").withStyle(ChatFormatting.GRAY));
	}
}
