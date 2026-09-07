package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;

public class Dish_Kakigouri extends BaseDishAlways {

	public Dish_Kakigouri(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.SEASONAL));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Seasonal.KAKIGOURI_block.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1250, 0)); }

		if (this == Items_Seasonal.KAKIGOURI_apple.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1900, 0)); }
		
		if (this == Items_Seasonal.KAKIGOURI_cherry.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1900, 0)); }

		if (this == Items_Seasonal.KAKIGOURI_citrus.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1900, 0)); }
		
		if (this == Items_Seasonal.KAKIGOURI_grape.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1900, 0)); }

		if (this == Items_Seasonal.KAKIGOURI_tea.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1900, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS.get();
	}
}
