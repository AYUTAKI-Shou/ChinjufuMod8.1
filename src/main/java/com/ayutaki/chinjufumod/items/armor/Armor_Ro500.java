package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.ConfigClient_CM;
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

public class Armor_Ro500 extends Base_Submarine {

	public Armor_Ro500(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}

	public static final class ArmorRender extends Base_ArmorItem.BaseRender {
		public static final ArmorRender INSTANCE = new ArmorRender();
		
		@Override
		public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType type, Model original) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			boolean leg = (type == EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS);
			int ARMOR = ConfigClient_CM.INSTANCE.armorTexture.get();
			
			ModelPart part = models.bakeLayer(leg? ((ARMOR == 1)? ArmorLayer_CM.UKIWA_INNER : ArmorLayer_CM.SUBMARINE_INNER) : ((ARMOR == 2)? ArmorLayer_CM.RO500_OUTER_C : ArmorLayer_CM.RO500_OUTER));
			return new HumanoidArmorModel<>(part); }
	}
}
