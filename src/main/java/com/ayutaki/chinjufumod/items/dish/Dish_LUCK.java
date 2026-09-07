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

public class Dish_LUCK extends BaseDishAlways {

	public Dish_LUCK(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}

	protected void addEffect(LivingEntity entityLiving) {
		/** Effect time of standard potions = 3600tick, 3min. **/
		boolean icecream = (this == Items_Teatime.ICECREAM || this == Items_Teatime.ICECREAM_GREEN || 
				this == Items_Teatime.ICECREAM_RED || this == Items_Teatime.ICECREAM_CACAO);
		
		int eTIME = icecream? 3600 : 4000;
		entityLiving.addEffect(new EffectInstance(Effects.LUCK, eTIME, 1));
	}
	
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS;
	}
}
