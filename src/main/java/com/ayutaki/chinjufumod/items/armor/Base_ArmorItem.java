package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.ConfigClient_CM;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Base_ArmorItem extends ArmorItem {

	public Base_ArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties props) {
		super(material, slot, props);
	}

	/** net.minecraftforge.common.extensions.IForgeItem **/
	@SuppressWarnings("removal")
	@Override
	public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
		ArmorItem armor = (ArmorItem) stack.getItem();
		String name = armor.getMaterial().getRegisteredName();
		int index = name.indexOf(":");
		
		ResourceLocation layer1 = new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_1.png");
		ResourceLocation layer2 = new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_2.png");
		ResourceLocation layerA = new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_a.png");
		ResourceLocation layerB = new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_b.png");
		ResourceLocation layerC = new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_c.png");
		ResourceLocation layerD = new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_d.png");
		
		int HELM = ConfigClient_CM.INSTANCE.helmetTexture.get();
		int CHEST = ConfigClient_CM.INSTANCE.chestplateTexture.get();
		int BOOTS = ConfigClient_CM.INSTANCE.bootsTexture.get();
		int LEG = ConfigClient_CM.INSTANCE.leggingsTexture.get();
		
		if (slot == EquipmentSlot.HEAD) { return (HELM == 1)? layerA : ((HELM == 2)? layerC : layer1); }
		if (slot == EquipmentSlot.CHEST) { return (CHEST == 1)? layerA : ((CHEST == 2)? layerC : layer1); }
		if (slot == EquipmentSlot.FEET) { return (BOOTS == 1)? layerA : ((BOOTS == 2)? layerC : layer1); }
		if (slot == EquipmentSlot.LEGS) { return (LEG == 1)? layerB : ((LEG == 2)? layerD : layer2); }
		else { return (CHEST == 1)? layerA : ((CHEST == 2)? layerC : layer1); }
	}
}
