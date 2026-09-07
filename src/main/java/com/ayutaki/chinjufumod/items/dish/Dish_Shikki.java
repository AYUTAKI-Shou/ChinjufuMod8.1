package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

public class Dish_Shikki extends BaseDishNeeds {

	public Dish_Shikki(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Teatime.MISOSOUP.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 2, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)); }

		if (this != Items_Teatime.MISOSOUP.get()) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0));
			entityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2000, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.SHIKKI.get();
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, itemTip, tipFlag);
		
		if (this == Items_Teatime.ZENZAI_M.get()) {
			itemTip.add(Component.translatable("tips.block_food_zenzai_m").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.ZENZAI_K.get()) {
			itemTip.add(Component.translatable("tips.block_food_zenzai_k").withStyle(ChatFormatting.GRAY)); }
	}
}
