package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Dish_Shikki extends TTab_DishEat {

	public Dish_Shikki(Block block, Item.Properties props) {
		super(block, props);
	}

	protected void addEffect(LivingEntity entityLiving) {
		if (this == Items_Teatime.MISOSOUP) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 2, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 2000, 0)); }

		if (this != Items_Teatime.MISOSOUP) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 3, 0));
			entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 2000, 0)); }
	}
	
	protected Item remainItem() {
		return Items_Teatime.SHIKKI;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, itemTip, tipFlag);
		
		if (this == Items_Teatime.ZENZAI_M) {
			itemTip.add(new TranslationTextComponent("tips.block_food_zenzai_m").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.ZENZAI_K) {
			itemTip.add(new TranslationTextComponent("tips.block_food_zenzai_k").withStyle(TextFormatting.GRAY)); }
	}
}
