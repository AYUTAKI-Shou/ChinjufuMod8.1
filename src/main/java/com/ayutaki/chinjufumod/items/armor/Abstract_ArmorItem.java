package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.Config_CM;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Abstract_ArmorItem extends ItemArmor {

	public Abstract_ArmorItem(ArmorMaterial a_material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(a_material, renderIndexIn, equipmentSlotIn);
		setCreativeTab(ChinjufuModTabs.CMARMOR);
	}

	@SideOnly(Side.CLIENT)
	public abstract net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, 
			EntityEquipmentSlot slotType, ModelBiped _default);
	
	/* net.minecraft.item.Item.getArmorTexture */
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		ItemArmor armor = (ItemArmor) stack.getItem();
		String name = armor.getArmorMaterial().getName();
		int index = name.indexOf(":");
		
		String layer1 = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_1.png");
		String layer2 = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_2.png");
		String layerA = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_a.png");
		String layerB = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_b.png");
		String layerC = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_c.png");
		String layerD = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_d.png");
		
		int HELM = Config_CM.helmetTexture;
		int CHEST = Config_CM.chestplateTexture;
		int BOOTS = Config_CM.bootsTexture;
		int LEG = Config_CM.leggingsTexture;
		
		if (slot == EntityEquipmentSlot.HEAD) { return (HELM == 1)? layerA : ((HELM == 2)? layerC : layer1); }
		if (slot == EntityEquipmentSlot.CHEST) { return (CHEST == 1)? layerA : ((CHEST == 2)? layerC : layer1); }
		if (slot == EntityEquipmentSlot.FEET) { return (BOOTS == 1)? layerA : ((BOOTS == 2)? layerC : layer1); }
		if (slot == EntityEquipmentSlot.LEGS) { return (LEG == 1)? layerB : ((LEG == 2)? layerD : layer2); }
		else { return (CHEST == 1)? layerA : ((CHEST == 2)? layerC : layer1); }
	}
}
