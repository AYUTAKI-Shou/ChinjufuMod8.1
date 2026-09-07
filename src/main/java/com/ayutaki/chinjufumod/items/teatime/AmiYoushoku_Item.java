package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.tags.ItemCMTags;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class AmiYoushoku_Item extends Not_Fuel {

	public AmiYoushoku_Item(Block blockIn, Item.Properties props) {
		super(blockIn, props.repairable(ItemCMTags.MATERIALS_YARN));
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.block_ami_youshoku").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.repair_ami").withStyle(ChatFormatting.GRAY));
	}
}
