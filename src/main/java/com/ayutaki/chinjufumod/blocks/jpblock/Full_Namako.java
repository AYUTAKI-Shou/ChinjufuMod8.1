package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Full_Namako extends Base_Full_JP {

	public Full_Namako(BlockBehaviour.Properties props) {
		super(props);
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_namako").withStyle(ChatFormatting.GRAY));
	}
}
