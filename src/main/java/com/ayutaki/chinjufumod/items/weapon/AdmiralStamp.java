package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.NoGroup_noFuel;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class AdmiralStamp extends NoGroup_noFuel {

	public AdmiralStamp(Block blockIn, Item.Properties props) {
		super(blockIn, props);
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}
	
	@Override
	@Nullable
	public ItemStack getContainerItem(ItemStack hStack) {

		ItemStack copy = hStack.copy();
		if (hStack.isDamageableItem()) {
			copy.setDamageValue(copy.getDamageValue() + 1);
			int damage = copy.getMaxDamage() - copy.getDamageValue();
			if(damage <= 0) { return ItemStack.EMPTY; }
		}
		return copy;
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_admiral_stamp").withStyle(ChatFormatting.GRAY));
	}
}
