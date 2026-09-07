package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Yunomi_TT extends BaseDishAlways {

	public Yunomi_TT(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}

	protected void addEffect(LivingEntity entityLiving) {
		entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 2000, 0));
	}
	
	protected Item remainItem() {
		return Items_Teatime.YUNOMI;
	}
}
