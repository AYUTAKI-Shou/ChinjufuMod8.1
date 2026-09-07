package com.ayutaki.chinjufumod.items.remain;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class IkaRaw extends Item {

	public IkaRaw(Item.Properties props) {
		super(props.craftRemainder(Items_Teatime.CUT_IKA.get()));
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_squid_raw").withStyle(ChatFormatting.GRAY));
	}
}
