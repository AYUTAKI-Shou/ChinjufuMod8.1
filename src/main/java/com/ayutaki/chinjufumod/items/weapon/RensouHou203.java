package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.AmmoEntity_Medium;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RensouHou203 extends Abstract_RensouHou {

	public RensouHou203(String name, int max) {
		super(name, max);
	}

	/* Called when the playerIn stops using an Item (stops holding the right mouse button). */
	public void onPlayerStoppedUsing(ItemStack hStack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer)entityLiving;
			boolean mode = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
			ItemStack projectile = this.findAmmo(playerIn);

			int i = this.getMaxItemUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);
			if (i < 0) return;

			if (!projectile.isEmpty() || mode) {

				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_M); }

				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
					boolean mode1 = playerIn.capabilities.isCreativeMode || (projectile.getItem() instanceof Ammo_Medium && ((Ammo_Medium) projectile.getItem()).isInfinite(projectile, hStack, playerIn));

					if (!worldIn.isRemote) {
						/* Ammo item instance. Entity to be fired. */
						Ammo_Medium arrowItem = (Ammo_Medium)(projectile.getItem() instanceof Ammo_Medium ? projectile.getItem() : Items_Weapon.AMMUNITION_M);
						AmmoEntity_Medium abstractArrow = (AmmoEntity_Medium) arrowItem.createAmmo(worldIn, projectile, playerIn);

						/* Move */
						abstractArrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.5F, 1.0F);

						/* Damage */
						abstractArrow.setIsCritical(true);
						boolean cruiser = ShipTypes_CM.typeCruiser(playerIn);
						/** addKOUKEI, fixFIT, shipType **/
						this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 2.0D, 1.0D, cruiser);
						this.setAmmoEnchant(hStack, abstractArrow);
						
						CMEvents.toolDamege(1, playerIn, hStack);

						if (mode1 || playerIn.capabilities.isCreativeMode) {
							abstractArrow.pickupStatus = AmmoEntity_Medium.PickupStatus.CREATIVE_ONLY; }

						worldIn.spawnEntity(abstractArrow);
						playerIn.getCooldownTracker().setCooldown(this, 12);
					}

					/* Firing sound effect. Small 0.5F, 1.4F, Medium 1.0F, 1.2F Large 1.2F, 1.0F */
					worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ,
							SoundEvents_CM.AM_FIRE, SoundCategory.PLAYERS, 1.0F, 1.2F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

					if (!mode1 && !playerIn.capabilities.isCreativeMode) {
						projectile.shrink(1);
						if (projectile.isEmpty()) { playerIn.inventory.deleteStack(projectile); }

						if (!worldIn.isRemote) {
							playerIn.dropItem(new ItemStack(Items_NoTab.CARTRIDGE_M), false);
							worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.AM_CARTRIDGE, 
									SoundCategory.BLOCKS, 1.0F, 1.2F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F); }
					}

					playerIn.addStat(StatList.getObjectUseStats(this));
				}
			}
		}
	}

	/* Ammo to be used. */
	protected boolean isArrow(ItemStack hStack) {
		return hStack.getItem() instanceof Ammo_Medium;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_rensouhou_medium.name"));
		itemTip.add(TextFormatting.DARK_GREEN + I18n.format("tips.item_rensouhou203.name"));
	}
}
