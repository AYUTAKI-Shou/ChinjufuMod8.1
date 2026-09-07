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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
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
		super(props.group(ItemGroups_CM.CMARMOR));
		
		this.addPropertyOverride(new ResourceLocation("pull"), (hStack, worldIn, entity) -> {
			return entity != null && entity.isHandActive() && entity.getActiveItemStack() == hStack ? 1.0F : 0.0F;
		});
	}

	/* Power to be charged. */
	public static float getArrowVelocity(int charge) {
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
	public UseAction getUseAction(ItemStack hStack) {
		return UseAction.BOW;
	}

	/** Luck **/
	protected float playerLuck(PlayerEntity playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean flag = !playerIn.findAmmo(hStack).isEmpty();

		this.SHOOTCOUNT = 0; // Count reset.
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;

		if (!playerIn.abilities.isCreativeMode && !flag) {
			worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.EMPTY_AMMO, SoundCategory.PLAYERS, 0.8F, 0.6F);
			return ActionResult.resultFail(hStack); }
		
		else {
			worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.SET_GUN, SoundCategory.PLAYERS, 0.8F, 0.8F);
			playerIn.setActiveHand(hand);
			return ActionResult.resultConsume(hStack);
		}
	}

	/* Called as the item is being used by an entity. */
	@Override
	public void onUse(World worldIn, LivingEntity entityLiving, ItemStack hStack, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			boolean mode = playerIn.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
			ItemStack projectile = playerIn.findAmmo(hStack);

			int i = this.getUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);

			if (i < 0) return;
			
			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_K); }
				
				float charge = getArrowVelocity(i);
				if (charge >= 1.0F) {
					
					if(!playerIn.getCooldownTracker().hasCooldown(this)) {
						playerIn.getCooldownTracker().setCooldown(this, 5);

						boolean mode1 = playerIn.abilities.isCreativeMode || (projectile.getItem() instanceof Ammo_Kijyuu && ((Ammo_Kijyuu)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

						if (!worldIn.isRemote) {
							int localCount = SHOOTCOUNT;
							
							Ammo_Kijyuu arrowItem = (Ammo_Kijyuu)(projectile.getItem() instanceof Ammo_Kijyuu ? projectile.getItem() : Items_Weapon.AMMUNITION_K);
							AbstractAmmo_Kijyuu abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
							abstractArrow = customAmmo(abstractArrow);

							/* Damage */
							int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, hStack);
							boolean LUCK = this.playerLuck(playerIn) > 0.0F;
							int A = LUCK? 2 : 6;
							double CRITICAL = (worldIn.rand.nextInt(A) == 0)? 0.5D : 0.0D;
							double LEVEL = (playerIn.experienceLevel >= 25)? 1.0D : ((playerIn.experienceLevel >= 19 && playerIn.experienceLevel < 25)? 0.5D : 0.0D);

							abstractArrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 6.0F, 1.0F);
							if (j == 0) { abstractArrow.setDamage(abstractArrow.getDamage() + LEVEL + CRITICAL); }
							if (j > 0) { abstractArrow.setDamage(abstractArrow.getDamage() + (double)j * 0.5D + LEVEL + CRITICAL); }
							
							/* Enchant */
							int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, hStack);
							if (k > 0) { abstractArrow.setKnockbackStrength(k); }

							if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, hStack) > 0) { abstractArrow.setFire(100); }

							if (mode1 || playerIn.abilities.isCreativeMode) {
								abstractArrow.pickupStatus = AbstractAmmo_Kijyuu.PickupStatus.CREATIVE_ONLY; }
							
							worldIn.addEntity(abstractArrow);
							localCount = localCount + 1;
							SHOOTCOUNT = localCount;
							worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.AM_FIRE, SoundCategory.PLAYERS, 0.2F, 1.8F);
							
							if (!mode1 && !playerIn.abilities.isCreativeMode) {
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
		Vec3d pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookVec().mul(-0.75D, -0.75D, -0.75D));
		worldIn.addParticle(ParticleTypes_CM.SHOOT_PT, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
	}

	private void shrinkAMMO(World worldIn, PlayerEntity playerIn, ItemStack hStack) {
		ItemStack projectile = playerIn.findAmmo(hStack);
		projectile.shrink(1);
		playerIn.dropItem(new ItemStack(Items_NoTab.CARTRIDGE_K), false);

		if (projectile.isEmpty()) { 
			playerIn.inventory.deleteStack(projectile); 
			playerIn.stopActiveHand();
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}
	
	private void dropSOUND(World worldIn, PlayerEntity playerIn) {
		worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.AM_CARTRIDGE_K, 
				SoundCategory.BLOCKS, 1.2F, 0.8F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
	}

	/* Damage to the tool. [finish] */
	@Override
	public ItemStack onItemUseFinish(ItemStack hStack, World worldIn, LivingEntity entityLiving) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
		return hStack;
	}
	
	/* Damage to the tool. [release] */
	@Override
	public void onPlayerStoppedUsing(ItemStack hStack, World worldIn, LivingEntity entityLiving, int timeLeft) { 
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}
	
	public AbstractAmmo_Kijyuu customAmmo(AbstractAmmo_Kijyuu arrow) {
		return arrow;
	}
	
	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}
	
	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOK = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_K;
	};

	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return AMMOK;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_3rensou_kijyuu").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_3rensou_kijyuu2").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_3rensou_kijyuu3").applyTextStyle(TextFormatting.DARK_GREEN));
	}
}
