package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class AdmiralStamp extends Not_Fuel {

	public AdmiralStamp(Block blockIn, Item.Properties props) {
		super(blockIn, props);
	}
	
	/* from IForgeItem */
	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack) {
		return true;
	}
	
	@Override
	@Nullable
	public ItemStack getCraftingRemainingItem(ItemStack hStack) {
		ItemStack copy = hStack.copy();
		if (hStack.isDamageableItem()) {
			copy.setDamageValue(copy.getDamageValue() + 1);
			int damage = copy.getMaxDamage() - copy.getDamageValue();
			if(damage <= 0) { return ItemStack.EMPTY; }
		}
		return copy;
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack stack_1, ItemStack stack_2) {
		return false;
	} // for 1.20.6
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_admiral_stamp").withStyle(ChatFormatting.GRAY));
	} // for 1.20.6
}
