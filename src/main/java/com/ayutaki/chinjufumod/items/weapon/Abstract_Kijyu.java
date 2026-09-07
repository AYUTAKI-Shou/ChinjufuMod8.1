package com.ayutaki.chinjufumod.items.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class Abstract_Kijyu extends Base_Kijyu {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
	private final TimeUnit milliS = TimeUnit.MILLISECONDS;
	
	public Abstract_Kijyu(Item.Properties props) {
		super(props);
	}
	
	@Override
	public void onUseTick(Level worldIn, LivingEntity entityLiving, ItemStack hStack, int timeLeft) {
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			boolean mode = playerIn.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
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
						List<ItemStack> list = draw(hStack, projectile, playerIn);
						
						if (!worldIn.isClientSide) {
							int localCount = SHOOTCOUNT;

							/* Spawn Entity. */
							this.shoot(worldIn, playerIn, playerIn.getUsedItemHand(), hStack, list, 6.0F, 1.0F, true, null); 
							localCount = localCount + 1;
							SHOOTCOUNT = localCount;
							worldIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_FIRE.get(), SoundSource.PLAYERS, 0.2F, 1.8F);
							
							if (!mode1 && !playerIn.getAbilities().instabuild) {
								scheduler.schedule(() -> this.dropCARTRIDGE(worldIn, playerIn, hStack), 60, milliS);
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

	private void dropCARTRIDGE(Level worldIn, Player playerIn, ItemStack hStack) {
		playerIn.drop(new ItemStack(Items_NoTab.CARTRIDGE_K.get()), false);

		ItemStack projectile = playerIn.getProjectile(hStack);
		if (projectile.isEmpty()) {
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

	protected static boolean hasInfiniteArrows(ItemStack hStack, ItemStack projectile, boolean mode) {
		return mode || projectile.getItem() instanceof Ammo_Kijyuu && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
	}
	
	protected static List<ItemStack> draw(ItemStack hStack, ItemStack projectile, LivingEntity playerIn) {
		if (projectile.isEmpty()) { return List.of(); } 
		
		else {
			// int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, hStack);
			int j = 1;
			List<ItemStack> list = new ArrayList<>(j);
			ItemStack itemhStack = projectile.copy();
			boolean infinite = projectile.getItem() instanceof Ammo_Kijyuu arrow && arrow.isInfinite(projectile, hStack, playerIn);

			for (int k = 0; k < j; k++) {
				list.add(useAmmo(hStack, k == 0 ? projectile : itemhStack, playerIn, k > 0 || infinite)); }

			return list;
		}
	}

	protected static ItemStack useAmmo(ItemStack hStack, ItemStack projectile, LivingEntity playerIn, boolean mode) {
		boolean flag = !mode && !hasInfiniteArrows(hStack, projectile, playerIn.hasInfiniteMaterials());
		if (!flag) {
			ItemStack itemhStack1 = projectile.copyWithCount(1);
			itemhStack1.set(DataComponents.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
			return itemhStack1; } 
		
		else {
			ItemStack itemhStack = projectile.split(1);
			if (projectile.isEmpty() && playerIn instanceof Player player) { player.getInventory().removeItem(projectile); }
			return itemhStack; }
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

	/* Add projectile */
	protected abstract Projectile createProjectile(Level worldIn, LivingEntity playerIn, ItemStack hStack, ItemStack projectile, boolean crit);
	
	@Override
	protected void shootProjectile(LivingEntity playerIn, Projectile projectile, int i, float scale, float add, float floatY, @Nullable LivingEntity entityIn) {
		projectile.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot() + floatY, 0.0F, scale, add);
	}

	protected void shoot(Level worldIn, LivingEntity entityLiving, InteractionHand hand, ItemStack hStack, List<ItemStack> hStackList,
		float scale, float add, boolean crit, @Nullable LivingEntity entityIn) {
		//float f = 10.0F;
		float f1 = hStackList.size() == 1 ? 0.0F : 20.0F / (float)(hStackList.size() - 1);
		float f2 = (float)((hStackList.size() - 1) % 2) * f1 / 2.0F;
		float f3 = 1.0F;

		for (int i = 0; i < hStackList.size(); i++) {
			ItemStack itemhStack = hStackList.get(i);
			if (!itemhStack.isEmpty()) {
				float f4 = f2 + f3 * (float)((i + 1) / 2) * f1;
				f3 = -f3;

				Projectile projectile = this.createProjectile(worldIn, entityLiving, hStack, itemhStack, crit);
				this.shootProjectile(entityLiving, projectile, i, scale, add, f4, entityIn);
				worldIn.addFreshEntity(projectile); }
		}
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack hStack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_3rensou_kijyuu").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_3rensou_kijyuu2").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_3rensou_kijyuu3").withStyle(ChatFormatting.DARK_GREEN));
	}
}
