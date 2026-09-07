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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RensouHou155 extends Abstract_RensouHou {

	public RensouHou155(Item.Properties props) {
		super(props);
	}

	/* Called when the player stops using an Item (stops holding the right mouse button). */
	public void onPlayerStoppedUsing(ItemStack hStack, World worldIn, LivingEntity entityLiving, int timeLeft) {

		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			boolean mode = playerIn.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
			ItemStack projectile = playerIn.findAmmo(hStack);

			int i = this.getUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);

			if (i < 0) return;

			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_M); }

				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
					boolean mode1 = playerIn.abilities.isCreativeMode || (projectile.getItem() instanceof Ammo_Medium && ((Ammo_Medium)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

					if (!worldIn.isRemote) {
						Ammo_Medium arrowItem = (Ammo_Medium)(projectile.getItem() instanceof Ammo_Medium ? projectile.getItem() : Items_Weapon.AMMUNITION_M);
						AbstractAmmo_Entity abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
						abstractArrow = customAmmo(abstractArrow);

						/* Move */
						abstractArrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.5F, 1.0F);

						/* Damage */
						abstractArrow.setIsCritical(true);
						boolean cruiser = ShipTypes_CM.typeCruiser(playerIn);
						/** addKOUKEI, fixFIT, shipType **/
						this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 1.5D, 1.0D, cruiser);
						this.setAmmoEnchant(hStack, abstractArrow);
						
						CMEvents.toolDamege(1, playerIn, hStack);

						if (mode1 || playerIn.abilities.isCreativeMode) {
							abstractArrow.pickupStatus = AbstractAmmo_Entity.PickupStatus.CREATIVE_ONLY; }
						
						worldIn.addEntity(abstractArrow);
						playerIn.getCooldownTracker().setCooldown(this, 12);
					}

					/* Firing sound effect. Small 0.5F, 1.4F, Medium 1.0F, 1.2F Large 1.2F, 1.0F */
					worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.AM_FIRE, 
							SoundCategory.PLAYERS, 1.0F, 1.2F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
					
					Vec3d pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookVec().mul(-0.75D, -0.75D, -0.75D));
					worldIn.addParticle(ParticleTypes_CM.SHOOTM_PT, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);

					if (!mode1 && !playerIn.abilities.isCreativeMode) {
						projectile.shrink(1);
						if (projectile.isEmpty()) { playerIn.inventory.deleteStack(projectile); }
						
						/* Drop the Cartridge. */
						if (!worldIn.isRemote) {
							playerIn.dropItem(new ItemStack(Items_NoTab.CARTRIDGE_M), false);
							worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.AM_CARTRIDGE, 
									SoundCategory.BLOCKS, 1.0F, 1.2F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F); }
					}

					playerIn.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}


	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOM = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_M;
	};

	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return AMMOM;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_rensouhou_medium").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_rensouhou155").applyTextStyle(TextFormatting.DARK_GREEN));
	}
}
