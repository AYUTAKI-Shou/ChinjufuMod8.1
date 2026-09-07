package com.ayutaki.chinjufumod.items.armor;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.armor.model.Santa_Model;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Costume_Santa extends Abstract_WearItem {

	public Costume_Santa(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	@OnlyIn(Dist.CLIENT)
	public <Armor extends BipedModel<?>> Armor getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType slot, Armor defModel) {
		return (Armor) (slot == EquipmentSlotType.LEGS ? new Santa_Model(0.4F) : new Santa_Model(0.6F));
	}

	/* Items needed for repair. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		if (this == Items_Seasonal.AKASHISANTA_BOOTS || this == Items_Seasonal.SUZUYASANTA_BOOTS || this == Items_Seasonal
				.KUMANOSANTA_BOOTS || this == Items_Seasonal.RYUJOUSANTA_BOOTS || this == Items_Seasonal.TEITOKUSANTA_BOOTS) {
			return material.getItem() == Items.BLACK_CARPET; }

		else { return material.getItem() == Items.RED_CARPET; }
	}
}
