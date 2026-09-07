package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Dish_Tonsui extends TTab_DishEat {

	public Dish_Tonsui(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 2, 0));
		entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 1000, 0));
		entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 1500, 0));
	}
	
	protected Item remainItem() {
		return Items_Teatime.TONSUI;
	}
}
