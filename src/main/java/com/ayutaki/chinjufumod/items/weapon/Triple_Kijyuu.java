package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.entity.AbstractAmmo_Kijyuu;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.world.phys.Vec3;

public class Triple_Kijyuu extends BowItem {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
	private final TimeUnit milliS = TimeUnit.MILLISECONDS;
	private int SHOOTCOUNT = 0;
	
	public Triple_Kijyuu(Item.Properties props) {
		super(props.tab(ItemGroups_CM.CMARMOR));
	}
	
	/* Power to be charged. */
	public static float getPowerForTime(int charge) {
		float f = (float)charge / 20.0F;
		f = (f + 2.0F) / 3.0F;
		if (f > 1.0F) { f = 1.0F; }
		return f;
	}

	/* Time to continue the action. 3 burst × 3 */
	@Override
	public int getUseDuration(ItemStack hStack) {
		return 64;
	}

	/* Action when using. */
	@Override
	public UseAnim getUseAnimation(ItemStack hStack) {
		return UseAnim.BOW;
	}

	/** Luck **/
	protected float playerLuck(Player playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		this.SHOOTCOUNT = 0; // Count reset.
		InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;

		if (!playerIn.getAbilities().instabuild && !flag) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.EMPTY_AMMO.get(), SoundSource.PLAYERS, 0.8F, 0.6F);
			playerIn.displayClientMessage(new TranslatableComponent("text.chinjufumod.rightclick.empty_ammo"), true);
			return InteractionResultHolder.fail(hStack); }
		
		else {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SET_GUN.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
			playerIn.startUsingItem(hand);
			return InteractionResultHolder.consume(hStack); }
	}

	public void onUseTick(Level worldIn, LivingEntity entityLiving, ItemStack hStack, int timeLeft) {
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			boolean mode = playerIn.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, hStack) > 0;
			ItemStack projectile = playerIn.getProjectile(hStack);
			
			int i = this.getUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);

			if (i < 0) return;
			
			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_K.get()); }
				
				float charge = getPowerForTime(i);
				if (charge >= 1.0F) {
					
					if(!playerIn.getCooldowns().isOnCooldown(this)) {
						playerIn.getCooldowns().addCooldown(this, 5);

						boolean mode1 = playerIn.getAbilities().instabuild || (projectile.getItem() instanceof Ammo_Kijyuu && ((Ammo_Kijyuu)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

						if (!worldIn.isClientSide) {
							int localCount = SHOOTCOUNT;
							
							Ammo_Kijyuu arrowItem = (Ammo_Kijyuu)(projectile.getItem() instanceof Ammo_Kijyuu ? projectile.getItem() : Items_Weapon.AMMUNITION_K.get());
							AbstractAmmo_Kijyuu abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
							abstractArrow = customAmmo(abstractArrow);

							/* Damage */
							int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, hStack);
							boolean LUCK = this.playerLuck(playerIn) > 0.0F;
							int A = LUCK? 2 : 6;
							double CRITICAL = (worldIn.random.nextInt(A) == 0)? 0.5D : 0.0D;
							double LEVEL = (playerIn.experienceLevel >= 25)? 1.0D : ((playerIn.experienceLevel >= 19 && playerIn.experienceLevel < 25)? 0.5D : 0.0D);
							
							abstractArrow.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 6.0F, 1.0F);
							if (j == 0) { abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + LEVEL + CRITICAL); }
							if (j > 0) { abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + (double)j * 0.5D + LEVEL + CRITICAL); }
							
							/* Enchant */
							int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, hStack);
							if (k > 0) { abstractArrow.setKnockback(k); }

							if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, hStack) > 0) { abstractArrow.setSecondsOnFire(100); }

							if (mode1 || playerIn.getAbilities().instabuild) {
								 abstractArrow.pickup = AbstractAmmo_Kijyuu.Pickup.CREATIVE_ONLY; }

							/* Spawn Entity. */
							worldIn.addFreshEntity(abstractArrow);
							localCount = localCount + 1;
							SHOOTCOUNT = localCount;
							worldIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_FIRE.get(), SoundSource.PLAYERS, 0.2F, 1.8F);
							
							if (!mode1 && !playerIn.getAbilities().instabuild) {
								scheduler.schedule(() -> this.shrinkAMMO(worldIn, playerIn, hStack), 60, milliS);
								scheduler.schedule(() -> this.dropSOUND(worldIn, playerIn), 90, milliS); }
						} //!worldIn.isClientSide
						scheduler.schedule(() -> this.ammoPART(worldIn, playerIn), 30, milliS);
					} //Cooldown
				} //charge
			}// !isEmpty
		} // entityLiving
	}
	
	private void ammoPART(Level worldIn, Player playerIn) {
		Vec3 pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
		worldIn.addParticle((ParticleOptions) ParticleTypes_CM.SHOOT_PT.get(), pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
	}

	private void shrinkAMMO(Level worldIn, Player playerIn, ItemStack hStack) {
		ItemStack projectile = playerIn.getProjectile(hStack);
		projectile.shrink(1);
		playerIn.drop(new ItemStack(Items_NoTab.CARTRIDGE_K.get()), false);
		
		if (projectile.isEmpty()) { 
			playerIn.getInventory().removeItem(projectile); 
			playerIn.stopUsingItem();
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}

	private void dropSOUND(Level worldIn, Player playerIn) {
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_CARTRIDGE_K.get(), 
				SoundSource.BLOCKS, 1.2F, 0.8F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + 1.0F * 0.5F);
	}

	/* Damage to the tool. [finish] */
	@Override
	public ItemStack finishUsingItem(ItemStack hStack, Level worldIn, LivingEntity entityLiving) {
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
		return hStack;
	}
	
	/* Damage to the tool. [release] */
	@Override
	public void releaseUsing(ItemStack hStack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}
	
	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOK = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_K.get();
	};

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMOK;
	}

	@Override
	public int getDefaultProjectileRange() {
		return 15;
	}
	
	public AbstractAmmo_Kijyuu customAmmo(AbstractAmmo_Kijyuu arrow) {
		return arrow;
	}

	/* Item repair material. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_3rensou_kijyuu").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_3rensou_kijyuu2").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_3rensou_kijyuu3").withStyle(ChatFormatting.DARK_GREEN));
	}
}
