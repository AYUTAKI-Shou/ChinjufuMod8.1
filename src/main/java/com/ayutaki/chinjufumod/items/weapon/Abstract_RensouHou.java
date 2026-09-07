package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Abstract_RensouHou extends BowItem {

	public Abstract_RensouHou(Item.Properties props) {
		super(props.tab(ItemGroups_CM.CMARMOR));
	}
	
	public abstract void releaseUsing(ItemStack hStack, Level worldIn, LivingEntity entityLiving, int timeLeft);
	
	public abstract int getDefaultProjectileRange();
	
	@OnlyIn(Dist.CLIENT)
	public abstract void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag);

	/** Luck **/
	protected float playerLuck(Player playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	/* Ammo */
	protected void setAmmoDamage(ItemStack hStack, Level worldIn, AbstractAmmo_Entity abstractArrow, Player playerIn, double addKOUKEI, double fixFIT, boolean shipType) {
		int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, hStack);
		
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
	
	protected void setAmmoEnchant(ItemStack hStack, AbstractAmmo_Entity abstractArrow) {
		int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, hStack);
		if (k == 0) { abstractArrow.setKnockback(1); }
		if (k > 0) { abstractArrow.setKnockback(k + 1); }

		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, hStack) > 0) { 
			abstractArrow.setSecondsOnFire(100); }
	}
	
	/* Power to be charged. */
	public static float getPowerForTime(int charge) {
		float f = (float)charge / 20.0F;
		f = (f + 2.0F) / 3.0F;
		if (f > 1.0F) { f = 1.0F; }
		return f;
	}

	/* Time to continue the action. */
	@Override
	public int getUseDuration(ItemStack hStack) {
		return 72000;
	}

	/* Action when using. */
	@Override
	public UseAnim getUseAnimation(ItemStack hStack) {
		return UseAnim.BOW;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;

		if (!playerIn.getAbilities().instabuild && !flag) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.EMPTY_AMMO.get(), SoundSource.PLAYERS, 0.8F, 0.6F);
			playerIn.displayClientMessage(new TranslatableComponent("text.chinjufumod.rightclick.empty_ammo"), true);
			return InteractionResultHolder.fail(hStack); }
		
		else {
			if (!playerIn.getCooldowns().isOnCooldown(this)) {
				worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SET_GUN.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
				playerIn.startUsingItem(hand);
				return InteractionResultHolder.consume(hStack); }
			
			else { return InteractionResultHolder.fail(hStack); }
		}
	}

	public AbstractAmmo_Entity customAmmo(AbstractAmmo_Entity arrow) {
		return arrow;
	}

	/* Item repair material. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}
}
