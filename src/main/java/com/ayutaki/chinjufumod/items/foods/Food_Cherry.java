package com.ayutaki.chinjufumod.items.foods;

import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class Food_Cherry extends IG_Teatime {

	public Food_Cherry(Item.Properties props) {
		super(props);
	}
	
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		Player playerIn = entityLiving instanceof Player ? (Player)entityLiving : null;

		/** add Potion Effect. **/
		if (!worldIn.isClientSide) {
			entityLiving.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0)); }

		/** add Item **/
		if (playerIn != null) {
			playerIn.awardStat(Stats.ITEM_USED.get(this));

			if (!playerIn.getAbilities().instabuild) {
				ItemStack take = new ItemStack(Items_Teatime.SEEDS_CHERRY.get(), 1);
				if (stack.isEmpty()) { return take; }
				else if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }

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
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.EAT;
	}
	
	 public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		playerIn.startUsingItem(hand);
		return InteractionResultHolder.consume(playerIn.getItemInHand(hand));
	}
}
