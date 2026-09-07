package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class Costume_YUKATA extends Base_WearItem {

	public Costume_YUKATA(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}
	
	public static final class ArmorRender implements IClientItemExtensions {
		public static final ArmorRender INSTANCE = new ArmorRender();
		
		@Override
		public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType type, Model original) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			boolean leg = (type == EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS);
			ModelPart part = models.bakeLayer(leg? ArmorLayer_CM.YUKATA_INNER : ArmorLayer_CM.YUKATA_OUTER);
			return new HumanoidArmorModel<>(part); }
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		if (this != Items_Seasonal.YKTD_GETA.get() && this != Items_Seasonal.YKTO_GETA.get() &&
				this != Items_Seasonal.IKADUCHIYKT_HELMET.get() && this != Items_Seasonal.INADUMAYKT_HELMET.get() &&
				this != Items_Seasonal.KAWAKAZEYKT_HELMET.get() && this != Items_Seasonal.URAKAZEYKT_HELMET.get() &&
				this != Items_Seasonal.OBOROYKT_HELMET.get() && this != Items_Seasonal.HAMAKAZEYKT_HELMET.get()) {
			itemTip.add(Component.translatable("tips.item_ykt").withStyle(ChatFormatting.GRAY)); }
	}
}
