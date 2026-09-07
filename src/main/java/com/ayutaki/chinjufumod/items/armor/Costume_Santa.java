package com.ayutaki.chinjufumod.items.armor;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.armor.model.Santa_Model;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Costume_Santa extends Abstract_WearItem {

	public Costume_Santa(String name, ArmorMaterial a_material, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(a_material, renderIndexIn, equipmentSlotIn);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, 
			EntityEquipmentSlot slotType, ModelBiped _default) {
		return (slotType == EntityEquipmentSlot.LEGS)? new Santa_Model(0.4F) : new Santa_Model(0.6F);
	}
	
	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {

		int k;
		k = repair.getMetadata();

		if (this == Items_Seasonal.AKASHISANTA_BOOTS || this == Items_Seasonal.SUZUYASANTA_BOOTS || this == Items_Seasonal
				.KUMANOSANTA_BOOTS || this == Items_Seasonal.RYUJOUSANTA_BOOTS || this == Items_Seasonal.TEITOKUSANTA_BOOTS) {

			if (k == 15) {
				return (repair.getItem() == Item.getItemFromBlock(Blocks.CARPET)); }

			if (k != 15) { return false; }
		}

		if (k == 14) {
			return (repair.getItem() == Item.getItemFromBlock(Blocks.CARPET)); }

		return false;
	}
}
