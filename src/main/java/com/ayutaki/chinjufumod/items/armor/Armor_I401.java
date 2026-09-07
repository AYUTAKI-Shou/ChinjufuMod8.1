package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.items.armor.model.I401_Inner;
import com.ayutaki.chinjufumod.items.armor.model.I401_Outer;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Armor_I401 extends Base_Submarine {

	public Armor_I401(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props);
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <Armor extends BipedModel<?>> Armor getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType slotType, Armor defModel) {
		return (Armor) (slot == EquipmentSlotType.LEGS ? new I401_Inner(0.15F) : new I401_Outer(0.55F));
	}
}
