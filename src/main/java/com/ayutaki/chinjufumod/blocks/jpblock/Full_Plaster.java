package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.JP_Blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Full_Plaster extends Base_Full_JP {

	public Full_Plaster(BlockBehaviour.Properties props) {
		super(props);
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		if (this != JP_Blocks.DIRTWALL.get()) {
			blockTip.add(new TranslatableComponent("tips.block_plaster").withStyle(ChatFormatting.GRAY)); }
	}
}
