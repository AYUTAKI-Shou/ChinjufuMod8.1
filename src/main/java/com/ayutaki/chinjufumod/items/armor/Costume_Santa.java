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
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class Costume_Santa extends Base_WearItem {

	public Costume_Santa(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}
	
	public static final class ArmorRender implements IClientItemExtensions {
		public static final ArmorRender INSTANCE = new ArmorRender();
		
		@Override
		public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType type, Model original) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			boolean leg = (type == EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS);
			ModelPart part = models.bakeLayer(leg? ArmorLayer_CM.SANTA_INNER : ArmorLayer_CM.SANTA_OUTER);
			return new HumanoidArmorModel<>(part); }
	}
}
