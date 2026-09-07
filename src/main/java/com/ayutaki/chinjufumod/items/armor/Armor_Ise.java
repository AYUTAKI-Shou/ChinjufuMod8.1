package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.items.armor.model.Ise_Inner;
import com.ayutaki.chinjufumod.items.armor.model.Ise_Outer;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Armor_Ise extends Base_BattleshipItem {

	public Armor_Ise(String name, ArmorMaterial a_material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, a_material, renderIndexIn, equipmentSlotIn);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, 
			EntityEquipmentSlot slotType, ModelBiped _default) {
		return (slotType == EntityEquipmentSlot.LEGS)? new Ise_Inner(0.4F) : new Ise_Outer(0.55F);
	}
}
