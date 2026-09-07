package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Dish_Kakigouri extends BaseDishAlways {

	public Dish_Kakigouri(Block block, Item.Properties props) {
		super(block, props.group(ItemGroups_CM.SEASONAL));
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.EAT;
	}

	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Seasonal.KAKIGOURI_block) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SPEED, 1250, 0)); }

		if (this == Items_Seasonal.KAKIGOURI_apple) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1900, 0)); }
		
		if (this == Items_Seasonal.KAKIGOURI_cherry) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 1900, 0)); }

		if (this == Items_Seasonal.KAKIGOURI_citrus) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1900, 0)); }
		
		if (this == Items_Seasonal.KAKIGOURI_grape) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1900, 0)); }

		if (this == Items_Seasonal.KAKIGOURI_tea) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 1900, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS;
	}
}
