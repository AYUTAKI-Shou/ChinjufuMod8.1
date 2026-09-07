package com.ayutaki.chinjufumod.items.armor;

import java.util.function.Consumer;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;
import com.ayutaki.chinjufumod.items.armor.model.BaseArmor;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class Costume_Santa extends Base_WearItem {

	public Costume_Santa(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties props) {
		super(material, slot, props);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void initializeClient(Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityIn, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defModel) {
				EntityModelSet models = Minecraft.getInstance().getEntityModels();
				ModelPart part = models.bakeLayer(slot == EquipmentSlot.LEGS ? ArmorLayer_CM.SANTA_INNER : ArmorLayer_CM.SANTA_OUTER);
				return new BaseArmor(part); }
		});
	}
	
	/* Item repair material. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		if (this == Items_Seasonal.AKASHISANTA_BOOTS.get() || this == Items_Seasonal.SUZUYASANTA_BOOTS.get() || this == Items_Seasonal
				.KUMANOSANTA_BOOTS.get() || this == Items_Seasonal.RYUJOUSANTA_BOOTS.get() || this == Items_Seasonal.TEITOKUSANTA_BOOTS.get()) {
			return material.getItem() == Items.BLACK_CARPET; }

		else { return material.getItem() == Items.RED_CARPET; }
	}
}
