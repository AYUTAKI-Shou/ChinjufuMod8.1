package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class Base_RensouHou extends BowItem {

	public Base_RensouHou(Item.Properties props) {
		super(props.repairable(Items.IRON_INGOT)); ///for 1.21.4
	}
	
	/** Luck **/
	protected float playerLuck(Player playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	/* Ammo */
	protected void setAmmoDamage(ItemStack hStack, Level worldIn, AbstractAmmo_Entity abstractArrow, Player playerIn, double addKOUKEI, double fixFIT, boolean shipType) {
		var lookup = worldIn.holderLookup(Registries.ENCHANTMENT);
		int j = EnchantmentHelper.getTagEnchantmentLevel(lookup.getOrThrow(Enchantments.POWER), hStack);
		
		double FIT = (shipType)? fixFIT : 0.0D;
		int pLevel = playerIn.experienceLevel;
		double LEVEL = (pLevel >= 25)? 1.5D : ((pLevel>= 19 && pLevel< 25)? 1.0D : ((pLevel>= 12 && pLevel< 19)? 0.5D : 0.0D));
		
		boolean LUCK = this.playerLuck(playerIn) > 0.0F;
		double criticalL = LUCK? ((worldIn.random.nextInt(2) == 0)? 1.5D : 1.0D) : 1.0D;
		double NORMAL = (abstractArrow.getBaseDamage() + addKOUKEI + FIT + LEVEL);
		double POWER = (abstractArrow.getBaseDamage() + addKOUKEI + (double)j * 0.5D + FIT + LEVEL);

		if (j == 0) { abstractArrow.setBaseDamage(NORMAL * criticalL); }
		if (j > 0) { abstractArrow.setBaseDamage(POWER * criticalL); }
	}
	
	protected void setAmmoEnchant(ItemStack hStack, Level worldIn, AbstractAmmo_Entity abstractArrow) {
		var lookup = worldIn.holderLookup(Registries.ENCHANTMENT);
		int k = EnchantmentHelper.getTagEnchantmentLevel(lookup.getOrThrow(Enchantments.PUNCH), hStack);
		if (k == 0) { abstractArrow.setKnockback(1); }
		if (k > 0) { abstractArrow.setKnockback(k + 1); }

		if (EnchantmentHelper.getTagEnchantmentLevel(lookup.getOrThrow(Enchantments.FLAME), hStack) > 0) { 
			abstractArrow.igniteForSeconds(100); }
	}
	
	/* Power to be charged. */
	public static float getPowerForTime(int charge) {
		float f = (float)charge / 20.0F;
		f = (f + 2.0F) / 3.0F;
		if (f > 1.0F) { f = 1.0F; }
		return f;
	}

	/* Time to continue the action. for 1.21.4 */
	@Override
	public int getUseDuration(ItemStack hStack, LivingEntity entityIn) {
		return 72000;
	}

	/* Action when using. for 1.21.4 */
	@Override
	public ItemUseAnimation getUseAnimation(ItemStack hStack) {
		return ItemUseAnimation.BOW;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		InteractionResult ret = net.neoforged.neoforge.event.EventHooks.onArrowNock(hStack, worldIn, playerIn, hand, flag); //neo
		if (ret != null) return ret;

		if (!playerIn.getAbilities().instabuild && !flag) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.EMPTY_AMMO.get(), SoundSource.PLAYERS, 0.8F, 0.6F);
			playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.empty_ammo"), true);
			return InteractionResult.FAIL; }
		
		else {
			if (!playerIn.getCooldowns().isOnCooldown(hStack)) {
				worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SET_GUN.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
				playerIn.startUsingItem(hand);
				return InteractionResult.CONSUME; }
			
			else { return InteractionResult.FAIL; }
		}
	}

	public AbstractAmmo_Entity customAmmo(AbstractAmmo_Entity arrow) {
		return arrow;
	}
}
