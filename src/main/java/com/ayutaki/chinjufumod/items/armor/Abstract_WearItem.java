package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.ChinjufuModTabs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Abstract_WearItem extends ItemArmor {

	public Abstract_WearItem(ArmorMaterial a_material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(a_material, renderIndexIn, equipmentSlotIn);
		setCreativeTab(ChinjufuModTabs.SEASONAL);
	}

	@SideOnly(Side.CLIENT)
	public abstract net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, 
			EntityEquipmentSlot slotType, ModelBiped _default);
	
	/* net.minecraft.item.Item.getArmorTexture */
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		ItemArmor armor = (ItemArmor) stack.getItem();
		String name = armor.getArmorMaterial().getName();
		int index = name.indexOf(":");
		
		if (slot == EntityEquipmentSlot.LEGS) {
			return "chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_2.png"; } 
		else {
			return "chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_1.png"; }
	}
}
