package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.entity.AbstractKK_Entity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public abstract class Abstract_BowKansaiki extends BowItem {

	public Abstract_BowKansaiki(Item.Properties props) {
		super(props.tab(ItemGroups_CM.CMARMOR));
	}
	
	/* Abstract */
	public abstract void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft);

	public abstract void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag);

	/* KANSAIKI */
	protected void setDamageMove(ItemStack hStack, AbstractKK_Entity kansaiki, Player playerIn, int POWER, boolean shipType) {
		int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, hStack);
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

	/* Time to continue the action. */
	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	/* Action when using. */
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;

		int life = hStack.getMaxDamage() - hStack.getDamageValue();
		if (life <= 1) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
			playerIn.awardStat(Stats.ITEM_USED.get(this));
			hStack.shrink(1); }
		
		if (!playerIn.getAbilities().instabuild && !flag) {
			CMEvents.soundError(worldIn, playerIn);
			playerIn.displayClientMessage(new TranslatableComponent("text.chinjufumod.rightclick.empty_fuel"), true);
			return InteractionResultHolder.fail(hStack); }
		
		if (playerIn.isEyeInFluid(FluidTags.WATER)) {
			CMEvents.Item_Waterlogged(worldIn, playerIn);
			return InteractionResultHolder.fail(hStack); }
		
		else {
			playerIn.startUsingItem(hand);
			return InteractionResultHolder.consume(hStack);
		}
	}

	public void onUseTick(Level worldIn, LivingEntity playerIn, ItemStack stack, int timeLeft) {
		int tickCount = playerIn.getTicksUsingItem();
		if (tickCount != 0 && tickCount % 3 == 0) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.KK_START.get(), SoundSource.PLAYERS, 0.5F, 1.2F); }
	}
	
	public AbstractAmmo_Entity customAmmo(AbstractAmmo_Entity arrow) {
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
	
	/* Item repair material. */
	@Override
	public int getEnchantmentValue() {
		return 1;
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items_Chinjufu.ALUMINUM.get(); }
}
