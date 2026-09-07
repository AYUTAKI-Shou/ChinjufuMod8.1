package com.ayutaki.chinjufumod.blocks.ranma;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Ranma_noInfo extends BaseRanma {

	public Ranma_noInfo(BlockBehaviour.Properties props) {
		super(props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.wp_stage2").withStyle(ChatFormatting.GRAY));
	}
}
