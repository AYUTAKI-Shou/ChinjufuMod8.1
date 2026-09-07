package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.items.dish.BaseDishAlways;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;

public class SakeGlass extends BaseDishAlways {

	public SakeGlass(Block block, Item.Properties props) {
		super(block, props);
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Teatime.NAMASAKEGLASS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.SAKEGLASS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 1));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.JUKUSAKEGLASS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 2));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2250, 0)); }

		if (this == Items_Teatime.CIDERGLASS.get() || this == Items_Teatime.WINEGLASS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.JUKUCIDERGLASS.get() || this == Items_Teatime.JUKUWINEGLASS.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 1));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2250, 0)); }

		if (this == Items_Teatime.MEADGLASS.get()) {
			entityLiving.removeEffect(MobEffects.POISON);
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2600, 0)); }
		if (this == Items_Teatime.JUKUMEADGLASS.get()) {
			entityLiving.removeEffect(MobEffects.POISON);
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2250, 1));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2250, 0)); }
	}

	@Override
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS.get();
	}
}
