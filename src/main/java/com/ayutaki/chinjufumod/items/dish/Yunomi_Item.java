package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;

public class Yunomi_Item extends BaseDishAlways {

	public Yunomi_Item(Block block, Item.Properties props) {
		super(block, props);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	protected void addEffect(LivingEntity entityLiving) {
		entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0));
	}

	@Override
	protected Item remainItem() {
		return Items_Teatime.YUNOMI.get();
	}
}
