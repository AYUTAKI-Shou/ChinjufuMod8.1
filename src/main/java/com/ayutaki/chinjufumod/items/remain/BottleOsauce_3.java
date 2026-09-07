package com.ayutaki.chinjufumod.items.remain;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class BottleOsauce_3 extends BaseBottle_3 {

	public BottleOsauce_3(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.OSAUCE_bot_44.get()));
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, itemTip, tipFlag);
		itemTip.add(Component.translatable("tips.block_osauce_bot").withStyle(ChatFormatting.GRAY));
	}
}
