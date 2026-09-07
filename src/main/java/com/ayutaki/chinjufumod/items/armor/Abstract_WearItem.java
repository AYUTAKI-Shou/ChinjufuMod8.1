package com.ayutaki.chinjufumod.items.armor;

import java.util.function.Consumer;

import com.ayutaki.chinjufumod.ItemGroups_CM;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Abstract_WearItem extends ArmorItem {

	public Abstract_WearItem(ArmorMaterial material, EquipmentSlot slot, Item.Properties props) {
		super(material, slot, props.tab(ItemGroups_CM.SEASONAL));
	}

	@OnlyIn(Dist.CLIENT)
	public abstract void initializeClient(Consumer<net.minecraftforge.client.IItemRenderProperties> consumer);
	
	/** net.minecraftforge.common.extensions.IForgeItem **/
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		ArmorItem armor = (ArmorItem) stack.getItem();
		String name = armor.getMaterial().getName();
		int index = name.indexOf(":");
		
		if (slot == EquipmentSlot.LEGS) {
			return "chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_2.png"; } 
		else {
			return "chinjufumod:textures/models/armor/" + name.substring(index + 1) + "_layer_1.png"; }
	}
}
