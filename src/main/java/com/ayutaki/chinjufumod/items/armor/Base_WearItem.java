package com.ayutaki.chinjufumod.items.armor;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Base_WearItem extends ArmorItem {

	public Base_WearItem(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties props) {
		super(material, slot, props);
	}

	/** net.minecraftforge.common.extensions.IForgeItem **/
	@SuppressWarnings("removal")
	@Override
	public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
		ArmorItem armor = (ArmorItem) stack.getItem();
		String name = armor.getMaterial().getRegisteredName();
		int index = name.indexOf(":");
		
		if (slot == EquipmentSlot.LEGS) {
			return new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_2.png"); } 
		else {
			return new ResourceLocation("chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_1.png"); }
	}
}
