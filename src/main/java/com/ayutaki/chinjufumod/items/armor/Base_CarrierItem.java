package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Base_CarrierItem extends Abstract_ArmorItem {

	public Base_CarrierItem(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props);
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_carrier").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_carrier2").applyTextStyle(TextFormatting.GRAY));
	}
}
