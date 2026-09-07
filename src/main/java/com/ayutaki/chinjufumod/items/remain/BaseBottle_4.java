package com.ayutaki.chinjufumod.items.remain;

import java.util.List;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class BaseBottle_4 extends Not_Fuel {

	public BaseBottle_4(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items.GLASS_BOTTLE));
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.block_bot_4").withStyle(ChatFormatting.GRAY));
		if (this == Items_NoTab.OSAUCE_bot_44.get()) {
			itemTip.add(Component.translatable("tips.block_osauce_bot").withStyle(ChatFormatting.GRAY)); }
	}
}
