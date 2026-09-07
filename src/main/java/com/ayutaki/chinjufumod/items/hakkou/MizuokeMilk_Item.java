package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumables;

public class MizuokeMilk_Item extends Item {

	public MizuokeMilk_Item(Item.Properties props) {
		super(props.craftRemainder(Items_Teatime.MIZUOKE.get())
				.component(DataComponents.CONSUMABLE, Consumables.MILK_BUCKET).usingConvertsTo(Items_Teatime.MIZUOKE.get()));
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_mizuoke_milk").withStyle(ChatFormatting.GRAY));
	}
}
