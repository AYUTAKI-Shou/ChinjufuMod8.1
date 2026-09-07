package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.items.dish.BaseDishAlways;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SakeGlass extends BaseDishAlways {

	public SakeGlass(Block block, Item.Properties props) {
		super(block, props.group(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Teatime.NAMASAKEGLASS) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2600, 0)); }
		if (this == Items_Teatime.SAKEGLASS) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 1));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2600, 0)); }
		if (this == Items_Teatime.JUKUSAKEGLASS) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 2));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2250, 0)); }

		if (this == Items_Teatime.CIDERGLASS || this == Items_Teatime.WINEGLASS) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2600, 0)); }
		if (this == Items_Teatime.JUKUCIDERGLASS || this == Items_Teatime.JUKUWINEGLASS) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 1));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2250, 0)); }

		if (this == Items_Teatime.MEADGLASS) {
			entityLiving.removePotionEffect(Effects.POISON);
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 0));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2600, 0)); }
		if (this == Items_Teatime.JUKUMEADGLASS) {
			entityLiving.removePotionEffect(Effects.POISON);
			entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2250, 1));
			entityLiving.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2250, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS;
	}
}
