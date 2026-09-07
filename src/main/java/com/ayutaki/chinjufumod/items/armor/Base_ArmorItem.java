package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ConfigClient_CM;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class Base_ArmorItem extends ArmorItem {

	public Base_ArmorItem(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}

	public static class BaseRender implements IClientItemExtensions {
		@Override
		public ResourceLocation getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, ResourceLocation _default) {
			ResourceLocation location = layer.textureId();
			String name = location.toString();
			int index = name.indexOf(":");
			
			int ARMOR = ConfigClient_CM.INSTANCE.armorTexture.getAsInt();
			String png = (ARMOR == 1)? "_a.png" : ((ARMOR == 2)? "_c.png" : ".png");
			
			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(ChinjufuMod.MOD_ID, 
							"textures/entity/equipment/" + type.getSerializedName() + "/" + name.substring(index + 1) + png);
			return texture;
		}
	}
}
