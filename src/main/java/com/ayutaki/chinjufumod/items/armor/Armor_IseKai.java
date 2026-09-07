package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.armor.model.Ise_Inner;
import com.ayutaki.chinjufumod.items.armor.model.Ise_Outer;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Armor_IseKai extends Base_BattleshipItem {

	public Armor_IseKai(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int slot, boolean selected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entity;

			if (playerIn.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() instanceof Armor_IseKai) {
				CMEvents.walkOnWater(worldIn, playerIn); }
		}
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <Armor extends BipedModel<?>> Armor getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlotType slotType, Armor defModel) {
		return (Armor) (slotType == EquipmentSlotType.LEGS ? new Ise_Inner(0.4F) : new Ise_Outer(0.55F));
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, itemTip, tipFlag);
		itemTip.add(new TranslationTextComponent("tips.item_suijou_boots").applyTextStyle(TextFormatting.GRAY));
	}
}
