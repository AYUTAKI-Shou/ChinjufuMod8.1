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
		super(block, props.tab(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}

	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Teatime.NAMASAKEGLASS) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 0));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.SAKEGLASS) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 1));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.JUKUSAKEGLASS) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 2));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2250, 0)); }

		if (this == Items_Teatime.CIDERGLASS || this == Items_Teatime.WINEGLASS) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 0));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.JUKUCIDERGLASS || this == Items_Teatime.JUKUWINEGLASS) {
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 1));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2250, 0)); }

		if (this == Items_Teatime.MEADGLASS) {
			entityLiving.removeEffect(Effects.POISON);
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 0));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.JUKUMEADGLASS) {
			entityLiving.removeEffect(Effects.POISON);
			entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 2250, 1));
			entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 2250, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS;
	}
}
