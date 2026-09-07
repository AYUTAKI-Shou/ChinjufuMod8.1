package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

public class Armor_RJKai extends Base_CarrierItem {

	public Armor_RJKai(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entity, int slot, boolean selected) {
		if (entity instanceof Player playerIn) {
			if (playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Armor_RJKai) {
				CMEvents.walkOnWater(worldIn, playerIn); }
		}
	}
	
	public static final class ArmorRender extends Base_ArmorItem.BaseRender {
		public static final ArmorRender INSTANCE = new ArmorRender();
		
		@Override
		public Model getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType type, Model original) {
			EntityModelSet models = Minecraft.getInstance().getEntityModels();
			boolean leg = (type == EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS);
			ModelPart part = models.bakeLayer(leg? ArmorLayer_CM.RJ_INNER : ArmorLayer_CM.RJ_OUTER);
			return new HumanoidArmorModel<>(part); }
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, itemTip, tipFlag);
		itemTip.add(Component.translatable("tips.item_suijou_boots").withStyle(ChatFormatting.GRAY));
	}
}
