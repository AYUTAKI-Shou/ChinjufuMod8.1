package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;
import java.util.function.Predicate;

import com.ayutaki.chinjufumod.entity.AbstractKK_Entity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;

public abstract class Abstract_BowKansaiki extends BowItem {

	public Abstract_BowKansaiki(Item.Properties props) {
		super(props.repairable(Items_Chinjufu.ALUMINUM.get())); ///for 1.21.4
	}
	
	/* Abstract */
	public abstract boolean releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft);

	public abstract void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag);

	/* KANSAIKI */
	protected void setDamageMove(ItemStack hStack, Level worldIn, AbstractKK_Entity kansaiki, Player playerIn, int POWER, boolean shipType) {
		var lookup = worldIn.holderLookup(Registries.ENCHANTMENT);
		int j = EnchantmentHelper.getTagEnchantmentLevel(lookup.getOrThrow(Enchantments.POWER), hStack);
		if (j == 0) { kansaiki.setBaseDamage(kansaiki.getBaseDamage()); }
		if (j > 0) { kansaiki.setBaseDamage(kansaiki.getBaseDamage() + (double)j * 0.5D); }
		
		float basePower = shipType? 0.25F : 0.2F;
		kansaiki.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, basePower * POWER, 1.0F);
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
	public int getUseDuration(ItemStack stack, LivingEntity entityIn) {
		return 72000;
	}

	/* Action when using. for 1.21.4 */
	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.BOW;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		InteractionResult ret = net.neoforged.neoforge.event.EventHooks.onArrowNock(hStack, worldIn, playerIn, hand, flag); //neo
		if (ret != null) return ret;

		int life = hStack.getMaxDamage() - hStack.getDamageValue();
		if (life <= 1) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
			playerIn.awardStat(Stats.ITEM_USED.get(this));
			hStack.shrink(1); }
		
		if (!playerIn.getAbilities().instabuild && !flag) {
			CMEvents.soundError(worldIn, playerIn);
			playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.empty_fuel"), true);
			return InteractionResult.FAIL; }
		
		if (playerIn.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value())) {
			CMEvents.Item_Waterlogged(worldIn, playerIn);
			return InteractionResult.FAIL; }
		
		else {
			playerIn.startUsingItem(hand);
			return InteractionResult.CONSUME;
		}
	}

	public void onUseTick(Level worldIn, LivingEntity playerIn, ItemStack stack, int timeLeft) {
		int tickCount = playerIn.getTicksUsingItem();
		if (tickCount != 0 && tickCount % 3 == 0) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.KK_START.get(), SoundSource.PLAYERS, 0.5F, 1.2F); }
	}
	
	public AbstractKK_Entity customAmmo(AbstractKK_Entity arrow) {
		return arrow;
	}

	/* Ammo to be used. */
	public static final Predicate<ItemStack> FUEL = (projectile) -> {
		return projectile.getItem() == Items_Weapon.KK_FUEL.get();
	};

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return FUEL;
	}
}
