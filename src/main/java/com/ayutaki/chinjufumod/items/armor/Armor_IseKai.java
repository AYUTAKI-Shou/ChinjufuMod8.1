package com.ayutaki.chinjufumod.items.armor;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.armor.model.BaseArmor;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

public class Armor_IseKai extends Base_BattleshipItem {

	public Armor_IseKai(ArmorMaterial material, EquipmentSlot slot, Item.Properties props) {
		super(material, slot, props);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entity, int slot, boolean selected) {
		if (entity instanceof Player playerIn) {
			if (playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Armor_IseKai) {
				CMEvents.walkOnWater(worldIn, playerIn); }
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void initializeClient(Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Override
			public HumanoidModel<?> getArmorModel(LivingEntity entityIn, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defModel) {
				EntityModelSet entityModel = Minecraft.getInstance().getEntityModels();
				ModelPart part = entityModel.bakeLayer(slot == EquipmentSlot.LEGS ? ArmorLayer_CM.ISE_INNER : ArmorLayer_CM.ISE_OUTER);
				return new BaseArmor(part); }
		});
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, itemTip, tipFlag);
		itemTip.add(new TranslatableComponent("tips.item_suijou_boots").withStyle(ChatFormatting.GRAY));
	}
}
