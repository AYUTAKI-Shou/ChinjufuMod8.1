package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class AddInfo_ST extends IG_Seasonal {

	public AddInfo_ST(Item.Properties props) {
		super(props);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		/** Seasonal **/
		if (this == Items_Seasonal.KURI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_chestnut").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.KURI_SWEET.get() || this == Items_Seasonal.KURI_CHOCO.get()) {
			itemTip.add(new TranslatableComponent("tips.item_chestnutsweet").withStyle(ChatFormatting.GRAY)); }

		if (this == Items_Seasonal.COCOA_F.get()) {
			itemTip.add(new TranslatableComponent("tips.item_cocoa_ferm").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.COCOA_R.get()) {
			itemTip.add(new TranslatableComponent("tips.item_cocoa_roast").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.COCOA_M.get()) {
			itemTip.add(new TranslatableComponent("tips.item_cocoa_mass").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Seasonal.CHOCO_raw.get()) {
			itemTip.add(new TranslatableComponent("tips.item_choco_raw").withStyle(ChatFormatting.GRAY)); }
	}
}
