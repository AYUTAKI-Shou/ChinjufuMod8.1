package com.ayutaki.chinjufumod.items.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractRensouHou_S extends Base_RensouHou {

	public AbstractRensouHou_S(Item.Properties props) {
		super(props);
	}
	
	/* Called when the player stops using an Item (stops holding the right mouse button). */
	@Override
	public void releaseUsing(ItemStack hStack, Level worldIn, LivingEntity entityIn, int timeLeft) {
		if (entityIn instanceof Player playerIn) {
			ItemStack projectile = playerIn.getProjectile(hStack);
			if (!projectile.isEmpty()) {
				int i = this.getUseDuration(hStack) - timeLeft;
				i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, true);
				if (i < 0) return;

				float f = getPowerForTime(i);
				if (!((double)f < 0.1)) {
					List<ItemStack> list = draw(hStack, projectile, playerIn);
					
					if (!worldIn.isClientSide() && !list.isEmpty()) {
						/* Move */
						this.shoot(worldIn, playerIn, playerIn.getUsedItemHand(), hStack, list, 3.0F, 1.0F, true, null); 
						playerIn.getCooldowns().addCooldown(this, 15);	
					}

					/* Firing sound effect. Small 0.5F, 1.4F, Medium 1.0F, 1.2F Large 1.2F, 1.0F */
					worldIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_FIRE.get(), 
							SoundSource.PLAYERS, 0.5F, 1.4F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
					
					Vec3 pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
					worldIn.addParticle((ParticleOptions) ParticleTypes_CM.SHOOTM_PT.get(), pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
					
					if (!hasInfiniteArrows(hStack, projectile, playerIn.hasInfiniteMaterials())) {
						/* Drop the Cartridge. */
						if (!worldIn.isClientSide) {
							playerIn.drop(new ItemStack(Items_NoTab.CARTRIDGE_S.get()), false);
							worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_CARTRIDGE.get(), 
									SoundSource.BLOCKS, 0.5F, 1.4F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F); }
					}
					
					playerIn.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	protected static boolean hasInfiniteArrows(ItemStack hStack, ItemStack projectile, boolean mode) {
		return mode || projectile.getItem() instanceof Ammo_Small && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
	}
	
	protected static List<ItemStack> draw(ItemStack hStack, ItemStack projectile, LivingEntity playerIn) {
		if (projectile.isEmpty()) { return List.of(); } 
		
		else {
			// int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, hStack);
			int j = 1;
			List<ItemStack> list = new ArrayList<>(j);
			ItemStack itemhStack = projectile.copy();
			boolean infinite = projectile.getItem() instanceof Ammo_Small arrow && arrow.isInfinite(projectile, hStack, playerIn);

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
	public static final Predicate<ItemStack> AMMOS = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_S.get();
	};

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMOS;
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
				CMEvents.toolDamegeLE(1, entityLiving, hStack);
				Projectile projectile = this.createProjectile(worldIn, entityLiving, hStack, itemhStack, crit);
				this.shootProjectile(entityLiving, projectile, i, scale, add, f4, entityIn);
				worldIn.addFreshEntity(projectile); }
		}
	}
}
