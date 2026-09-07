package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Dish_Plate extends BaseDishNeeds {

	public Dish_Plate(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		/* CURRY */
		if (this == Items_Teatime.CURRY_T.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }
		
		if (this == Items_Teatime.CURRY.get() || this == Items_Teatime.CURRY_C.get()) {
			/** add Potion Effect +300 **/
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3600, 0)); }
		
		
		/* PASTA */
		if (this == Items_Teatime.PASTASEAFOOD.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3500, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3500, 0)); }
		
		if (this == Items_Teatime.PASTATOMATO.get() || this == Items_Teatime.PASTACHEESE.get() ||
				this == Items_Teatime.PASTAKINOKO.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }
		
		
		/* OKONOMIYAKI */
		if (this == Items_Teatime.OKONOMIC.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 8, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 0)); }
		
		if (this == Items_Teatime.OKONOMIYAKI.get() || this == Items_Teatime.OKONOMIS.get() ||
				this == Items_Teatime.YAKISOBA.get() || this == Items_Teatime.YAKISOBASHIO.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }
		
		
		/* OKONOMI_SOBA Cheese is lower. */
		if (this == Items_Teatime.OKONOMISOBAC.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 10, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 0)); }
		
		if (this == Items_Teatime.OKONOMISOBA.get() || this == Items_Teatime.OKONOMISOBAS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 12, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }
		
		
		/* Other */
		if (this == Items_Teatime.STEW.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 8, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3000, 0)); }

		if (this == Items_Teatime.RICE.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0)); }

		if (this == Items_Teatime.CORNSOUP.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 2, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)); }

		if (this == Items_Teatime.HAKUSAIDUKE.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 500, 0)); }

		if (this == Items_Teatime.TAMAGOYAKI.get() || this == Items_Teatime.CHICKEN_small.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0)); }

		if (this == Items_Teatime.EGGBURG.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.SARA.get();
	}
}
