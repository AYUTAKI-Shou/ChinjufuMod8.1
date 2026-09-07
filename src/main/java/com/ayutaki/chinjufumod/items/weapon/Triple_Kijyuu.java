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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Triple_Kijyuu extends BowItem {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
	private final TimeUnit milliS = TimeUnit.MILLISECONDS;
	private int SHOOTCOUNT = 0;
	
	public Triple_Kijyuu(Item.Properties props) {
		super(props.tab(ItemGroups_CM.CMARMOR));
	}

	/* Power to be charged. */
	public static float getArrowVelocity(int charge) {
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

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
	public UseAction getUseAnimation(ItemStack hStack) {
		return UseAction.BOW;
	}

	/** Luck **/
	protected float playerLuck(PlayerEntity playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}
	
	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		this.SHOOTCOUNT = 0; // Count reset.
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;
		
		if (!playerIn.abilities.instabuild && !flag) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.EMPTY_AMMO, SoundCategory.PLAYERS, 0.8F, 0.6F);
			playerIn.displayClientMessage(new TranslationTextComponent("text.chinjufumod.rightclick.empty_ammo"), true);
			return ActionResult.fail(hStack); }
		
		else {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SET_GUN, SoundCategory.PLAYERS, 0.8F, 0.8F);
			playerIn.startUsingItem(hand);
			return ActionResult.consume(hStack);
		}
	}
	
	public void onUseTick(World worldIn, LivingEntity entityLiving, ItemStack hStack, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			boolean mode = playerIn.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, hStack) > 0;
			ItemStack projectile = playerIn.getProjectile(hStack);

			int i = this.getUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);

			if (i < 0) return;
			
			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_K); }
				
				float charge = getPowerForTime(i);
				if (charge >= 1.0F) {
					
					if(!playerIn.getCooldowns().isOnCooldown(this)) {
						playerIn.getCooldowns().addCooldown(this, 5);

						boolean mode1 = playerIn.abilities.instabuild || (projectile.getItem() instanceof Ammo_Kijyuu && ((Ammo_Kijyuu)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

						if (!worldIn.isClientSide) {
							int localCount = SHOOTCOUNT;
							
							Ammo_Kijyuu arrowItem = (Ammo_Kijyuu)(projectile.getItem() instanceof Ammo_Kijyuu ? projectile.getItem() : Items_Weapon.AMMUNITION_K);
							AbstractAmmo_Kijyuu abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
							abstractArrow = customAmmo(abstractArrow);
							/* Move */
							abstractArrow.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 6.0F, 0.0F);
							
							/* Damage */
							int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, hStack);
							boolean LUCK = this.playerLuck(playerIn) > 0.0F;
							int A = LUCK? 2 : 6;
							double CRITICAL = (worldIn.random.nextInt(A) == 0)? 0.5D : 0.0D;
							double LEVEL = (playerIn.experienceLevel >= 25)? 1.0D : ((playerIn.experienceLevel >= 19 && playerIn.experienceLevel < 25)? 0.5D : 0.0D);
							
							if (j == 0) { abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + LEVEL + CRITICAL); }
							if (j > 0) { abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + (double)j * 0.5D + LEVEL + CRITICAL); }
							
							/* Enchant */
							int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, hStack);
							if (k > 0) { abstractArrow.setKnockback(k); }

							if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, hStack) > 0) { abstractArrow.setSecondsOnFire(100); }

							if (mode1 || playerIn.abilities.instabuild) {
								 abstractArrow.pickup = AbstractAmmo_Kijyuu.PickupStatus.CREATIVE_ONLY; }
							
							/* Spawn Entity. */
							worldIn.addFreshEntity(abstractArrow);
							localCount = localCount + 1;
							SHOOTCOUNT = localCount;
							worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_FIRE, SoundCategory.PLAYERS, 0.2F, 1.8F);
							
							if (!mode1 && !playerIn.abilities.instabuild) {
								scheduler.schedule(() -> this.shrinkAMMO(worldIn, playerIn, hStack), 60, milliS);
								scheduler.schedule(() -> this.dropSOUND(worldIn, playerIn), 90, milliS); }
						} //!worldIn.isClientSide
						scheduler.schedule(() -> this.ammoPART(worldIn, playerIn), 30, milliS);
					} //Cooldown
				} //charge
			}// !isEmpty
		} // entityLiving
	}
	
	private void ammoPART(World worldIn, PlayerEntity playerIn) {
		Vector3d pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
		worldIn.addParticle(ParticleTypes_CM.SHOOT_PT, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
	}

	private void shrinkAMMO(World worldIn, PlayerEntity playerIn, ItemStack hStack) {
		ItemStack projectile = playerIn.getProjectile(hStack);
		projectile.shrink(1);
		playerIn.drop(new ItemStack(Items_NoTab.CARTRIDGE_K), false);
		
		if (projectile.isEmpty()) { 
			playerIn.inventory.removeItem(projectile);
			playerIn.stopUsingItem(); 
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}

	private void dropSOUND(World worldIn, PlayerEntity playerIn) {
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_CARTRIDGE_K, 
				SoundCategory.BLOCKS, 1.2F, 0.8F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
	}

	/* Damage to the tool. [finish] */
	@Override
	public ItemStack finishUsingItem(ItemStack hStack, World worldIn, LivingEntity entityLiving) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
		return hStack;
	}
	
	/* Damage to the tool. [release] */
	@Override
	public void releaseUsing(ItemStack hStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}
	
	
	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOK = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_K;
	};

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMOK;
	}

	@Override
	public int getDefaultProjectileRange() {
		return 15;
	}

	public AbstractAmmo_Kijyuu customAmmo(AbstractAmmo_Kijyuu abstractArrow) {
		return abstractArrow;
	}

	/* Items needed for repair. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_3rensou_kijyuu").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_3rensou_kijyuu2").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_3rensou_kijyuu3").withStyle(TextFormatting.DARK_GREEN));
	}
}
/*
String str = String.valueOf(this.SHOOTCOUNT);
playerIn.displayClientMessage(new TranslationTextComponent(str), true);
*/