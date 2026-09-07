package com.ayutaki.chinjufumod.items.foods;

import java.util.List;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class FoodEffects {
	public static final Consumable DEFAULT_FOOD = defaultFood().build();
	public static final Consumable DEFAULT_DRINK = defaultDrink().build();
	
	public static final Consumable ROTTEN_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8F)).build();

	/* Teatime */
	public static final Consumable PC_CHEESE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0), 1.0F)).build();
	
	public static final Consumable PC_PIZZA = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 60, 1)))).build();
	
	public static final Consumable PC_PIZZA_TS = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 1400, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 70, 1)))).build();

	public static final Consumable SHOUYUSUSHI_S = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1), 1.0F)).build();
	
	public static final Consumable SHOUYUSUSHI_F = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 0), 1.0F)).build();
	
	public static final Consumable SHOUYUSUSHI_B = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0), 1.0F)).build();
	
	public static final Consumable SHOUYUSUSHI_T = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 1.0F)).build();
	
	public static final Consumable FUTOMAKI = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 1.0F)).build();

	public static final Consumable ONIGIRISHAKE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 1), 1.0F)).build();

	/* DISH */
	public static final Consumable E_DR1000 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 1500)))).build();
	
	public static final Consumable E_DR2400 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 0), 
					new MobEffectInstance(MobEffects.HEAL, 1, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 2400)))).build();
	
	public static final Consumable E_DR3000 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 3000, 0), 
					new MobEffectInstance(MobEffects.HEAL, 1, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 3000)))).build();
	
	public static final Consumable E_DR3500 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 3500, 0), 
					new MobEffectInstance(MobEffects.HEAL, 1, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 3500)))).build();
	
	public static final Consumable E_DR3600 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0), 
					new MobEffectInstance(MobEffects.HEAL, 1, 0), 
					new MobEffectInstance(MobEffects.REGENERATION, 3600)))).build();

	public static final Consumable E_D500 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 500, 0), 1.0F)).build();
	public static final Consumable MOCHI_ANKO = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 750, 0), 1.0F)).build();
	public static final Consumable MOCHI_SAKURA = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 750, 0), 1.0F)).build();
	
	public static final Consumable E_D1000 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0), 1.0F)).build();
	
	public static final Consumable E_D2000 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0), 1.0F)).build();
	
	public static final Consumable E_R200 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 1.0F)).build();
	public static final Consumable E_R300 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1.0F)).build();
	
	public static final Consumable E_L3600 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.LUCK, 3600, 1), 1.0F)).build();
	
	public static final Consumable E_L4000 = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.LUCK, 4000, 1), 1.0F)).build();
	
	public static final Consumable PAN_APPLE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800, 0), 1.0F)).build();

	public static final Consumable PAN_CHERRY = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800, 0), 1.0F)).build();
	
	public static final Consumable PAN_CITRUS = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0), 1.0F)).build();

	public static final Consumable PAN_GRAPE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 800, 0), 1.0F)).build();

	public static final Consumable PAN_TEA = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 800, 0), 1.0F)).build();
	
	/* GLASS */
	public static final Consumable D_D2000 = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0), 1.0F)).build();
	
	public static final Consumable NAMASAKE = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 0), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)))).build();

	public static final Consumable SAKE = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 1), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)))).build();

	public static final Consumable JUKUSAKE = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 2), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2250, 0)))).build();

	public static final Consumable CIDERWINE = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 0), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)))).build();

	public static final Consumable JUKUCIDERWINE = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 1), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2250, 0)))).build();

	public static final Consumable MEAD = defaultDrink()
			.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.POISON))
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 0), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)))).build();

	public static final Consumable JUKUMEAD = defaultDrink()
			.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.POISON))
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
		new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 1), new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2250, 0)))).build();

	/* KANTEN */
	public static final Consumable KANTEN_APPLE = defaultFood()
		.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 0), 1.0F)).build();
	
	public static final Consumable KANTEN_CHERRY = defaultFood()
		.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 0), 1.0F)).build();

	public static final Consumable KANTEN_CITRUS = defaultFood()
		.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0), 1.0F)).build();
	
	public static final Consumable KANTEN_GRAPE = defaultFood()
		.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 3600, 0), 1.0F)).build();

	public static final Consumable KANTEN_MILK = defaultFood().onConsume(ClearAllStatusEffectsConsumeEffect.INSTANCE).build();

	public static final Consumable YOKAN = defaultFood()
		.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 0), 1.0F)).build();
	
	public static final Consumable YOKAN_MATCHA = defaultFood()
		.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 3800, 0), 1.0F)).build();
	
	
	/* Seasonal */
	public static final Consumable KURI_CHOCO = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 800, 0), 1.0F)).build();
	public static final Consumable CHOCO = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 0), 1.0F)).build();

	public static final Consumable SUGAR = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 800, 0), 1.0F)).build();
	
	public static final Consumable APPLE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)).build();
	
	public static final Consumable CHERRY = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 1.0F)).build();
	
	public static final Consumable CITRUS = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F)).build();

	public static final Consumable GRAPE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1200, 0), 1.0F)).build();
	
	public static final Consumable TEA = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 0), 1.0F)).build();
	
	public static final Consumable HEART = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.LUCK, 3000, 0), 1.0F)).build();
	
	
	public static final Consumable K_SUGAR = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1250, 0), 1.0F)).build();
	
	public static final Consumable K_APPLE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1900, 0), 1.0F)).build();
	
	public static final Consumable K_CHERRY = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1900, 0), 1.0F)).build();
	
	public static final Consumable K_CITRUS = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1900, 0), 1.0F)).build();

	public static final Consumable K_GRAPE = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1900, 0), 1.0F)).build();
	
	public static final Consumable K_TEA = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1900, 0), 1.0F)).build();
	
	
	/* Share variables */
	public static Consumable.Builder defaultFood() {
		return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
	}

	public static Consumable.Builder defaultDrink() {
		return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
	}
}
