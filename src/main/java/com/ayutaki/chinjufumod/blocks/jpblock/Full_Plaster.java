package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.List;

import com.ayutaki.chinjufumod.registry.JP_Blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Full_Plaster extends Base_Full_JP {

	public Full_Plaster(BlockBehaviour.Properties props) {
		super(props);
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		if (this != JP_Blocks.DIRTWALL.get()) {
			blockTip.add(Component.translatable("tips.block_plaster").withStyle(ChatFormatting.GRAY)); }
	}
}
