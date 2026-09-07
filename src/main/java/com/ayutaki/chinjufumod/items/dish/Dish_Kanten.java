package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Dish_Kanten extends BaseDishAlways {

	public Dish_Kanten(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}

	protected void addEffect(LivingEntity entityLiving) {
		/** Effect time of standard potions = 3600tick, 3min. **/
		if (this == Items_Teatime.KANTEN_APPLE) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 3600, 0)); }
		
		if (this == Items_Teatime.KANTEN_CHERRY) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 3600, 0)); }

		if (this == Items_Teatime.KANTEN_CITRUS) {
			entityLiving.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 3600, 0)); }
		
		if (this == Items_Teatime.KANTEN_GRAPE) {
			entityLiving.addEffect(new EffectInstance(Effects.NIGHT_VISION, 3600, 0)); }

		if (this == Items_Teatime.KANTEN_MILK) {
			entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET)); }
		
		if (this == Items_Teatime.YOKAN) {
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3600, 0)); }
		
		if (this == Items_Teatime.YOKAN_MATCHA) {
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 3800, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.SARA;
	}
}
