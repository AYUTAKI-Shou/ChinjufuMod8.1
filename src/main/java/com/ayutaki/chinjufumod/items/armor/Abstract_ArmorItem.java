package com.ayutaki.chinjufumod.items.armor;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ConfigClient_CM;
import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Abstract_ArmorItem extends ArmorItem {

	public Abstract_ArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props.tab(ItemGroups_CM.CMARMOR));
	}

	/* Abstract */
	@Nullable
	@Override
	@OnlyIn(Dist.CLIENT)
	public abstract <Armor extends BipedModel<?>> Armor getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType slot, Armor defModel);
	
	/* net.minecraftforge.common.extensions.IForgeItem */
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		ArmorItem armor = (ArmorItem) stack.getItem();
		String name = armor.getMaterial().getName();
		int index = name.indexOf(":");
		
		String layer1 = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_1.png");
		String layer2 = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_2.png");
		String layerA = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_a.png");
		String layerB = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_b.png");
		String layerC = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_c.png");
		String layerD = ("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_d.png");
		
		int HELM = ConfigClient_CM.getInstance().helmetTexture();
		int CHEST = ConfigClient_CM.getInstance().chestplateTexture();
		int BOOTS = ConfigClient_CM.getInstance().bootsTexture();
		int LEG = ConfigClient_CM.getInstance().leggingsTexture();
		
		if (slot == EquipmentSlotType.HEAD) { return (HELM == 1)? layerA : ((HELM == 2)? layerC : layer1); }
		if (slot == EquipmentSlotType.CHEST) { return (CHEST == 1)? layerA : ((CHEST == 2)? layerC : layer1); }
		if (slot == EquipmentSlotType.FEET) { return (BOOTS == 1)? layerA : ((BOOTS == 2)? layerC : layer1); }
		if (slot == EquipmentSlotType.LEGS) { return (LEG == 1)? layerB : ((LEG == 2)? layerD : layer2); }
		else { return (CHEST == 1)? layerA : ((CHEST == 2)? layerC : layer1); }
	}
}
