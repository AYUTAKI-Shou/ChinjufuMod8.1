package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.items.armor.model.I401_Inner;
import com.ayutaki.chinjufumod.items.armor.model.I401_Outer;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Armor_I401 extends Base_Submarine {

	public Armor_I401(String name, ArmorMaterial a_material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, a_material, renderIndexIn, equipmentSlotIn);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot slotType, ModelBiped _default) {
		return (slotType == EntityEquipmentSlot.LEGS)? new I401_Inner(0.15F) : new I401_Outer(0.55F);
	}
}
