package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RensouHou203SKC34 extends Abstract_RensouHou {

	public RensouHou203SKC34(Item.Properties props) {
		super(props);
	}
	
	/* Called when the player stops using an Item (stops holding the right mouse button). */
	@Override
	public void releaseUsing(ItemStack hStack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			boolean mode = playerIn.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, hStack) > 0;
			ItemStack projectile = playerIn.getProjectile(hStack);
			
			int i = this.getUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);

			if (i < 0) return;

			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_M.get()); }
				
				float charge = getPowerForTime(i);
				if (!((double)charge < 0.1D)) {
					boolean mode1 = playerIn.getAbilities().instabuild || (projectile.getItem() instanceof Ammo_Medium && ((Ammo_Medium)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

					if (!worldIn.isClientSide) {
						Ammo_Medium arrowItem = (Ammo_Medium)(projectile.getItem() instanceof Ammo_Medium ? projectile.getItem() : Items_Weapon.AMMUNITION_M.get());
						AbstractAmmo_Entity abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
						abstractArrow = customAmmo(abstractArrow);
						
						/* Move */
						abstractArrow.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 3.5F, 1.0F);

						/* Damage */
						abstractArrow.setCritArrow(true);
						boolean cruiser = ShipTypes_CM.typeCruiser(playerIn);
						/** addKOUKEI, fixFIT, shipType **/
						this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 3.0D, 1.0D, cruiser);
						this.setAmmoEnchant(hStack, abstractArrow);
						
						CMEvents.toolDamege(1, playerIn, hStack);
						
						if (mode1 || playerIn.getAbilities().instabuild) {
							 abstractArrow.pickup = AbstractAmmo_Entity.Pickup.CREATIVE_ONLY; }

						worldIn.addFreshEntity(abstractArrow);
						playerIn.getCooldowns().addCooldown(this, 12);
					} //!worldIn.isClientSide

					/* Firing sound effect. Small 0.5F, 1.4F, Medium 1.0F, 1.2F Large 1.2F, 1.0F */
					worldIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_FIRE.get(), 
							SoundSource.PLAYERS, 1.0F, 1.2F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
					
					Vec3 pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
					worldIn.addParticle((ParticleOptions) ParticleTypes_CM.SHOOTM_PT.get(), pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
					
					if (!mode1 && !playerIn.getAbilities().instabuild) {
						projectile.shrink(1);
						if (projectile.isEmpty()) { playerIn.getInventory().removeItem(projectile); }
						
						/* Drop the Cartridge. */
						if (!worldIn.isClientSide) {
							playerIn.drop(new ItemStack(Items_NoTab.CARTRIDGE_M.get()), false);
							worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_CARTRIDGE.get(), 
									SoundSource.BLOCKS, 1.0F, 1.2F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F); }
					}
					
					playerIn.awardStat(Stats.ITEM_USED.get(this));
				}//!((double)f < 0.1D)
			} //!projectile.isEmpty()	
		} //instanceof Player
	}
	

	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOM = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_M.get();
	};

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMOM;
	}

	@Override
	public int getDefaultProjectileRange() {
		return 17;
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_rensouhou_medium").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_rensouhou203_skc34").withStyle(ChatFormatting.DARK_GREEN));
	}
}
