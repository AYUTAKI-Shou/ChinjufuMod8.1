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
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {

		PlayerEntity playerIn = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;

		/** add Potion Effect. **/
		if (!worldIn.isRemote) {
			entityLiving.addPotionEffect(new EffectInstance(Effects.SATURATION, 1, 0));

			if (this == Items_Seasonal.FOOD_WATAGASHI) { 
				entityLiving.addPotionEffect(new EffectInstance(Effects.SPEED, 800, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_apple) { 
				entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_cherry) { 
				entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_citrus) { 
				entityLiving.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_grape) { 
				entityLiving.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1200, 0)); }
			if (this == Items_Seasonal.FOOD_WATAGASHI_tea) { 
				entityLiving.addPotionEffect(new EffectInstance(Effects.HASTE, 1200, 0)); }
		}
		
		/** add Item **/
		if (playerIn != null) {
			playerIn.addStat(Stats.ITEM_USED.get(this));

			if (!playerIn.abilities.isCreativeMode) {
				ItemStack take = new ItemStack(Items.STICK, 1);
				if (stack.isEmpty()) { return take; }
				else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
				
				stack.shrink(1);
			}
		}

		return stack;
	}

	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.EAT;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		playerIn.setActiveHand(hand);
		return ActionResult.resultSuccess(playerIn.getHeldItem(hand));
	}
}
