package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class Armor_Sendai extends Base_CruiserItem {

	public Armor_Sendai(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}

	public static final class ArmorRender extends Base_ArmorItem.BaseRender {
		public static final ArmorRender INSTANCE = new ArmorRender();
		
		@Override
		public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType type, Model original) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			boolean leg = (type == EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS);
			ModelPart part = models.bakeLayer(leg? ArmorLayer_CM.SENDAI_INNER : ArmorLayer_CM.SENDAI_OUTER);
			return new HumanoidArmorModel<>(part); }
	}
}
