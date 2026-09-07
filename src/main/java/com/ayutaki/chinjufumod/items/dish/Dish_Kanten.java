package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;

public class Dish_Kanten extends BaseDishAlways {

	public Dish_Kanten(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	protected void addEffect(LivingEntity entityLiving) {
		/** Effect time of standard potions = 3600tick, 3min. **/
		if (this == Items_Teatime.KANTEN_APPLE.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 0)); }
		
		if (this == Items_Teatime.KANTEN_CHERRY.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 0)); }

		if (this == Items_Teatime.KANTEN_CITRUS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0)); }
		
		if (this == Items_Teatime.KANTEN_GRAPE.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0)); }

		if (this == Items_Teatime.KANTEN_MILK.get()) {
			entityLiving.removeAllEffects(); }
		
		if (this == Items_Teatime.YOKAN.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0)); }
		
		if (this == Items_Teatime.YOKAN_MATCHA.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3800, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.SARA.get();
	}
}
