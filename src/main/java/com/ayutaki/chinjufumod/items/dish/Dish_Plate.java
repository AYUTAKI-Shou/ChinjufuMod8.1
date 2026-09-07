package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Dish_Plate extends TTab_DishEat {

	public Dish_Plate(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		/* CURRY */
		if (this == Items_Teatime.CURRY_T) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }
		
		if (this == Items_Teatime.CURRY || this == Items_Teatime.CURRY_C) {
			/** add Potion Effect +300 **/
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3600, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3600, 0)); }
		
		
		/* PASTA */
		if (this == Items_Teatime.PASTASEAFOOD) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3500, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3500, 0)); }
		
		if (this == Items_Teatime.PASTATOMATO || this == Items_Teatime.PASTACHEESE ||
				this == Items_Teatime.PASTAKINOKO) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }
		
		
		/* OKONOMIYAKI */
		if (this == Items_Teatime.OKONOMIC) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 8, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 2400, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 2400, 0)); }
		
		if (this == Items_Teatime.OKONOMIYAKI || this == Items_Teatime.OKONOMIS ||
				this == Items_Teatime.YAKISOBA || this == Items_Teatime.YAKISOBASHIO) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }
		
		
		/* OKONOMI_SOBA Cheese is lower. */
		if (this == Items_Teatime.OKONOMISOBAC) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 10, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 2400, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 2400, 0)); }
		
		if (this == Items_Teatime.OKONOMISOBA || this == Items_Teatime.OKONOMISOBAS) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 12, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }
		
		
		/* Other */
		if (this == Items_Teatime.STEW) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 8, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3000, 0));
			entityLiving.addEffect(new EffectInstance(Effects.HEAL, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 3000, 0)); }

		if (this == Items_Teatime.RICE) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 3, 0)); }

		if (this == Items_Teatime.CORNSOUP) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 2, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 2000, 0)); }

		if (this == Items_Teatime.HAKUSAIDUKE) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 1, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 500, 0)); }

		if (this == Items_Teatime.TAMAGOYAKI || this == Items_Teatime.CHICKEN_small) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 3, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 200, 0)); }

		if (this == Items_Teatime.EGGBURG) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 5, 0));
			entityLiving.addEffect(new EffectInstance(Effects.REGENERATION, 300, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.SARA;
	}
}
