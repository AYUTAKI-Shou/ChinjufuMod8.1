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

public class RensouHou356S3 extends Abstract_RensouHou {

	public RensouHou356S3(Item.Properties props) {
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
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_L); }

				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
					boolean mode1 = playerIn.abilities.isCreativeMode || (projectile.getItem() instanceof Ammo_Large && ((Ammo_Large)projectile.getItem()).isInfinite(projectile, hStack, playerIn));

					if (!worldIn.isRemote) {
						Ammo_Large arrowItem = (Ammo_Large)(projectile.getItem() instanceof Ammo_Large ? projectile.getItem() : Items_Weapon.AMMUNITION_L);
						AbstractAmmo_Entity abstractArrow = arrowItem.createAmmo(worldIn, projectile, playerIn);
						abstractArrow = customAmmo(abstractArrow);
						/* Move */
						boolean battleship = ShipTypes_CM.typeBattleship(playerIn);
						abstractArrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (battleship)? 4.0F : 3.0F, 1.0F);

						/* Damage */
						abstractArrow.setIsCritical(true);
						/** addKOUKEI, fixFIT, shipType **/
						this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 6.0D, 2.0D, battleship);
						this.setAmmoEnchant(hStack, abstractArrow);
						
						CMEvents.toolDamege(1, playerIn, hStack);

						if (mode1 || playerIn.abilities.isCreativeMode) {
							abstractArrow.pickupStatus = AbstractAmmo_Entity.PickupStatus.CREATIVE_ONLY; }
						
						worldIn.addEntity(abstractArrow);
						playerIn.getCooldownTracker().setCooldown(this, 15);
					}

					/* Firing sound effect. Small 0.5F, 1.4F, Medium 1.0F, 1.2F Large 1.2F, 1.0F */
					worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.AM_FIRE, 
							SoundCategory.PLAYERS, 1.2F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
					
					Vec3d pos = playerIn.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(playerIn.getLookVec().mul(-1.0D, -1.0D, -1.0D));
					worldIn.addParticle(ParticleTypes_CM.SHOOTL_PT, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);

					if (!mode1 && !playerIn.abilities.isCreativeMode) {
						projectile.shrink(1);
						if (projectile.isEmpty()) { playerIn.inventory.deleteStack(projectile); }
						
						/* Drop the Cartridge. */
						if (!worldIn.isRemote) {
							playerIn.dropItem(new ItemStack(Items_NoTab.CARTRIDGE_L), false);
							worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.AM_CARTRIDGE, 
									SoundCategory.BLOCKS, 1.2F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F); }
					}

					playerIn.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}


	/* Ammo to be used. */
	public static final Predicate<ItemStack> AMMOL = (projectile) -> {
		return projectile.getItem() == Items_Weapon.AMMUNITION_L;
	};

	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return AMMOL;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_rensouhou_large").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_rensouhou356_s3").applyTextStyle(TextFormatting.DARK_GREEN));
	}
}
