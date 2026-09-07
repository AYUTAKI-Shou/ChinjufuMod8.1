package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Abstract_RensouHou extends BowItem {

	public Abstract_RensouHou(Item.Properties props) {
		super(props.group(ItemGroups_CM.CMARMOR));
		
		this.addPropertyOverride(new ResourceLocation("pull"), (hStack, worldIn, entity) -> {
			return entity != null && entity.isHandActive() && entity.getActiveItemStack() == hStack ? 1.0F : 0.0F; });
	}

	/* Abstract */
	public abstract void onPlayerStoppedUsing(ItemStack hStack, World worldIn, LivingEntity entityLiving, int timeLeft);

	public abstract Predicate<ItemStack> getInventoryAmmoPredicate();

	@OnlyIn(Dist.CLIENT)
	public abstract void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag);

	/** Luck **/
	protected float playerLuck(PlayerEntity playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	/* Ammo */
	protected void setAmmoDamage(ItemStack hStack, World worldIn, AbstractAmmo_Entity abstractArrow, PlayerEntity playerIn, double addKOUKEI, double fixFIT, boolean shipType) {
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, hStack);
		
		double FIT = (shipType)? fixFIT : 0.0D;
		int pLevel = playerIn.experienceLevel;
		double LEVEL = (pLevel >= 25)? 1.5D : ((pLevel>= 19 && pLevel< 25)? 1.0D : ((pLevel>= 12 && pLevel< 19)? 0.5D : 0.0D));
		
		boolean LUCK = this.playerLuck(playerIn) > 0.0F;
		double criticalL = LUCK? ((worldIn.rand.nextInt(2) == 0)? 1.5D : 1.0D) : 1.0D;
		double NORMAL = (abstractArrow.getDamage() + addKOUKEI + FIT + LEVEL);
		double POWER = (abstractArrow.getDamage() + addKOUKEI + (double)j * 0.5D + FIT + LEVEL);

		if (j == 0) { abstractArrow.setDamage(NORMAL * criticalL); }
		if (j > 0) { abstractArrow.setDamage(POWER * criticalL); }
	}
	
	protected void setAmmoEnchant(ItemStack hStack, AbstractAmmo_Entity abstractArrow) {
		int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, hStack);
		if (k == 0) { abstractArrow.setKnockbackStrength(1); }
		if (k > 0) { abstractArrow.setKnockbackStrength(k + 1); }

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, hStack) > 0) { 
			abstractArrow.setFire(100); }
	}
	
	/* Power to be charged. */
	public static float getArrowVelocity(int charge) {
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		
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
	public UseAction getUseAction(ItemStack hStack) {
		return UseAction.BOW;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean flag = !playerIn.findAmmo(hStack).isEmpty();

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;

		if (!playerIn.abilities.isCreativeMode && !flag) {
			worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.EMPTY_AMMO, SoundCategory.PLAYERS, 0.8F, 0.6F);
			return ActionResult.resultFail(hStack); }
		
		else {
			if (!playerIn.getCooldownTracker().hasCooldown(this)) {
				worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.SET_GUN, SoundCategory.PLAYERS, 0.8F, 0.8F);
				playerIn.setActiveHand(hand);
				return ActionResult.resultConsume(hStack); }
			
			else { return ActionResult.resultFail(hStack); }
		}
	}

	public AbstractAmmo_Entity customAmmo(AbstractAmmo_Entity arrow) {
		return arrow;
	}
	
	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}
}
