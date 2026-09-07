package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.armor.model.AkatsukiInner;
import com.ayutaki.chinjufumod.items.armor.model.AkatsukiOuter;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Armor_AkatsukiKai extends Base_DestroyerItem {

	public Armor_AkatsukiKai(String name, ArmorMaterial a_material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, a_material, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onArmorTick(World worldIn, EntityPlayer playerIn, ItemStack armor) {
		if (playerIn != null) {
			if (playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof Armor_AkatsukiKai) {
				CMEvents.walkOnWater(worldIn, playerIn); }
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, 
			EntityEquipmentSlot slotType, ModelBiped _default) {
		return (slotType == EntityEquipmentSlot.LEGS)? new AkatsukiInner(0.4F) : new AkatsukiOuter(0.55F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		super.addInformation(stack, worldIn, itemTip, advanced);
		itemTip.add(I18n.format("tips.item_suijou_boots.name"));
	}
}
