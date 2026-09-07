package com.ayutaki.chinjufumod.items.remain;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class PuddingRaw extends Item {

	public PuddingRaw(Item.Properties props) {
		super(props.craftRemainder(Items_Teatime.NABE_kara.get()));
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, itemTip, tipFlag);
		itemTip.add(Component.translatable("tips.item_raw_kanten").withStyle(ChatFormatting.GRAY));
	}
}
