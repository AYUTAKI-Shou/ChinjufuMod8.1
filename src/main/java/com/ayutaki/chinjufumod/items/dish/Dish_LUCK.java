package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;

public class Dish_LUCK extends BaseDishAlways {

	public Dish_LUCK(Block block, Item.Properties props) {
		super(block, props.tab(ItemGroups_CM.TEATIME));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}

	protected void addEffect(LivingEntity entityLiving) {
		/** Effect time of standard potions = 3600tick, 3min. **/
		boolean icecream = (this == Items_Teatime.ICECREAM.get() || this == Items_Teatime.ICECREAM_GREEN.get() || 
				this == Items_Teatime.ICECREAM_RED.get() || this == Items_Teatime.ICECREAM_CACAO.get());
		
		int eTIME = icecream? 3600 : 4000;
		entityLiving.addEffect(new MobEffectInstance(MobEffects.LUCK, eTIME, 1));
	}
	
	protected Item remainItem() {
		return Items_Teatime.DRINKGLASS.get();
	}
}
