package com.ayutaki.chinjufumod.items.armor;

import java.util.List;
import java.util.function.Consumer;

import com.ayutaki.chinjufumod.handler.ArmorLayer_CM;
import com.ayutaki.chinjufumod.items.armor.model.BaseArmor;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class Costume_YUKATA extends Base_WearItem {

	public Costume_YUKATA(Holder<ArmorMaterial> material, ArmorItem.Type slot, Item.Properties props) {
		super(material, slot, props);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void initializeClient(Consumer<net.minecraftforge.client.extensions.common.IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityIn, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defModel) {
				EntityModelSet models = Minecraft.getInstance().getEntityModels();
				ModelPart part = models.bakeLayer(slot == EquipmentSlot.LEGS ? ArmorLayer_CM.YUKATA_INNER : ArmorLayer_CM.YUKATA_OUTER);
				return new BaseArmor(part); }
		});
	}
	
	/* Items needed for repair. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		if (this == Items_Seasonal.YKTD_GETA.get()) { return material.getItem() == Items.DARK_OAK_SLAB; }

		if (this == Items_Seasonal.YKTO_GETA.get()) { return material.getItem() == Items.OAK_SLAB; }

		if (this == Items_Seasonal.IKADUCHIYKT_HELMET.get() || this == Items_Seasonal.INADUMAYKT_HELMET.get()) {
			return material.getItem() == Items_Seasonal.KAEDE_slabhalf.get(); }

		if (this == Items_Seasonal.KAWAKAZEYKT_HELMET.get() || this == Items_Seasonal.URAKAZEYKT_HELMET.get()) {
			return material.getItem() == Items.BIRCH_SLAB; }

		if (this == Items_Seasonal.OBOROYKT_HELMET.get()) { return material.getItem() == Items.DANDELION; }

		if (this == Items_Seasonal.HAMAKAZEYKT_HELMET.get()) { return material.getItem() == Items.POPPY; }

		else { return material.getItem() == Items_Seasonal.TANMONO.get(); }
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
