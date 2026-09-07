package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class Dish_Donburi extends BaseDishNeeds {

	public Dish_Donburi(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		/* DONMONO */
		if (this == Items_Teatime.DONBURI_MESHI.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5, 0)); }

		if (this == Items_Teatime.DONBURI_GYU.get() || this == Items_Teatime.DONBURI_OYAKO.get() ||
				this == Items_Teatime.DONBURI_KAISEN.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }

		if (this == Items_Teatime.DONBURI_KATSU.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3500, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3500, 0)); }
		
		
		/* UDON */
		if (this == Items_Teatime.UDON_SU.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5, 0)); }

		if (this == Items_Teatime.UDON_NIKU.get() || this == Items_Teatime.UDON_TSUKIMI.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }
		
		
		/* RAMEN */
		if (this == Items_Teatime.RAMEN_SHOUYU.get() || this == Items_Teatime.RAMEN_MISO.get() ||
				this == Items_Teatime.RAMEN_SHIO.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3500, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3500, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.DONBURI.get();
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, itemTip, tipFlag);
		
		if (this == Items_Teatime.DONBURI_GYU.get()) {
			itemTip.add(Component.translatable("tips.block_food_dongyu_1").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_KATSU.get()) {
			itemTip.add(Component.translatable("tips.block_food_donkatsu_1").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_OYAKO.get()) {
			itemTip.add(Component.translatable("tips.block_food_donoyako_1").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_KAISEN.get()) {
			itemTip.add(Component.translatable("tips.block_food_donkaisen_1").withStyle(ChatFormatting.GRAY)); }
	}
}
