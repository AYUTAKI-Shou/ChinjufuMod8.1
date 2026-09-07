package com.ayutaki.chinjufumod.items.foods;

import com.ayutaki.chinjufumod.items.base.IG_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Food_Watagashi extends IG_Seasonal {

	public Food_Watagashi(Item.Properties props) {
		super(props);
	}

	/* Finish RightClick Action */
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		PlayerEntity playerIn = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;

		/** add Potion Effect. **/
		if (!worldIn.isClientSide) {
			entityLiving.addEffect(new EffectInstance(Effects.SATURATION, 1, 0));

			if (this == Items_Seasonal.FOOD_WATAGASHI) { 
				entityLiving.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 800, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_apple) { 
				entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_cherry) { 
				entityLiving.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_citrus) { 
				entityLiving.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_grape) { 
				entityLiving.addEffect(new EffectInstance(Effects.NIGHT_VISION, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_tea) { 
				entityLiving.addEffect(new EffectInstance(Effects.DIG_SPEED, 1200, 0)); }
		}
		
		/** add Item **/
		if (playerIn != null) {
			playerIn.awardStat(Stats.ITEM_USED.get(this));

			if (!playerIn.abilities.instabuild) {
				ItemStack take = new ItemStack(Items.STICK, 1);
				if (stack.isEmpty()) { return take; }
				else if (!playerIn.inventory.add(take)) { playerIn.drop(take, false); }

				stack.shrink(1);
			}
		}

		return stack;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
		playerIn.startUsingItem(hand);
		return ActionResult.consume(playerIn.getItemInHand(hand));
	}
}
