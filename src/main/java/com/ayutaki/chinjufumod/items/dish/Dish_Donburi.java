package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Dish_Donburi extends TTab_DishEat {

	public Dish_Donburi(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		/* DONMONO */
		if (this == Items_Teatime.DONBURI_MESHI) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 5, 0)); }

		if (this == Items_Teatime.DONBURI_GYU || this == Items_Teatime.DONBURI_OYAKO || 
				this == Items_Teatime.DONBURI_KAISEN) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 3000, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }

		if (this == Items_Teatime.DONBURI_KATSU) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 3500, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3500, 0)); }
		
		
		/* UDON */
		if (this == Items_Teatime.UDON_SU) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 5, 0)); }

		if (this == Items_Teatime.UDON_NIKU || this == Items_Teatime.UDON_TSUKIMI) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 3000, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }
		
		
		/* RAMEN */
		if (this == Items_Teatime.RAMEN_SHOUYU || this == Items_Teatime.RAMEN_MISO ||
				this == Items_Teatime.RAMEN_SHIO) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 3500, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3500, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.DONBURI;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, itemTip, tipFlag);
		
		if (this == Items_Teatime.DONBURI_GYU) {
			itemTip.add(new TranslationTextComponent("tips.block_food_dongyu_1").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_KATSU) {
			itemTip.add(new TranslationTextComponent("tips.block_food_donkatsu_1").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_OYAKO) {
			itemTip.add(new TranslationTextComponent("tips.block_food_donoyako_1").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.DONBURI_KAISEN) {
			itemTip.add(new TranslationTextComponent("tips.block_food_donkaisen_1").applyTextStyle(TextFormatting.GRAY)); }
	}
}
