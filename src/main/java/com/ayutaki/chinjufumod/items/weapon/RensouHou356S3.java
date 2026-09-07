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

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RensouHou356S3 extends Abstract_RensouHou {

	public RensouHou356S3(Item.Properties props) {
		super(props);
	}

	/* Called when the player stops using an Item (stops holding the right mouse button). */
	@Override
	public void releaseUsing(ItemStack hStack, World worldIn, LivingEntity entityLiving, int timeLeft) {

		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			boolean mode = playerIn.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, hStack) > 0;
			ItemStack projectile = playerIn.getProjectile(hStack);

			int i = this.getUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);

			if (i < 0) return;

			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_L); }

				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
					boolean mode1 = playerIn.abilities.instabuild || (projectile.getItem() instanceof Ammo_Large && ((Ammo_Large)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

					if (!worldIn.isClientSide) {
						Ammo_Large arrowItem = (Ammo_Large)(projectile.getItem() instanceof Ammo_Large ? projectile.getItem() : Items_Weapon.AMMUNITION_L);
						AbstractAmmo_Entity abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
						abstractArrow = customAmmo(abstractArrow);
						/* Move */
						boolean battleship = ShipTypes_CM.typeBattleship(playerIn);
						abstractArrow.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, (battleship)? 4.0F : 3.0F, 1.0F);
						
						/* Damage */
						abstractArrow.setCritArrow(true);
						/** addKOUKEI, fixFIT, shipType **/
						this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 6.0D, 2.0D, battleship);
						this.setAmmoEnchant(hStack, abstractArrow);
						
						CMEvents.toolDamege(1, playerIn, hStack);

						if (mode1 || playerIn.abilities.instabuild) {
							 abstractArrow.pickup = AbstractAmmo_Entity.PickupStatus.CREATIVE_ONLY; }
						
						worldIn.addFreshEntity(abstractArrow);
						playerIn.getCooldowns().addCooldown(this, 15);
					}
					
					/* Firing sound effect. Small 0.5F, 1.4F, Medium 1.0F, 1.2F Large 1.2F, 1.0F */
					worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_FIRE, 
							SoundCategory.PLAYERS, 1.2F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
					
					Vector3d pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
					worldIn.addParticle(ParticleTypes_CM.SHOOTL_PT, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
					
					if (!mode1 && !playerIn.abilities.instabuild) {
						projectile.shrink(1);
						if (projectile.isEmpty()) { playerIn.inventory.removeItem(projectile); }
						
						/* Drop the Cartridge. */
						if (!worldIn.isClientSide) {
							playerIn.drop(new ItemStack(Items_NoTab.CARTRIDGE_L), false);
							worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.AM_CARTRIDGE, 
									SoundCategory.BLOCKS, 1.2F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F); }
					}

					playerIn.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOL = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_L;
	};

	@Override
	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return AMMOL;
	}

	@Override
	public int getDefaultProjectileRange() {
		return 20;
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_rensouhou_large").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_rensouhou356_s3").withStyle(TextFormatting.DARK_GREEN));
	}
}
