package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Dish_Tonsui extends BaseDishNeeds {

	public Dish_Tonsui(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 2, 0));
		entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 0));
		entityLiving.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1500, 0));
	}
	
	protected Item remainItem() {
		return Items_Teatime.TONSUI.get();
	}
}
