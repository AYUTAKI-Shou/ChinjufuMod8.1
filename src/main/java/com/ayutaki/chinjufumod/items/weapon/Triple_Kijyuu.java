package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Kijyuu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class Triple_Kijyuu extends Abstract_Kijyu {

	public Triple_Kijyuu(Item.Properties props) {
		super(props);
	}

	/** Luck **/
	protected float playerLuck(Player playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	protected Projectile createProjectile(Level worldIn, LivingEntity entityLiving, ItemStack hStack, ItemStack projectile, boolean crit) {
		Ammo_Kijyuu arrowItem = (Ammo_Kijyuu)(projectile.getItem() instanceof Ammo_Kijyuu ? projectile.getItem() : Items_Weapon.AMMUNITION_K.get());
		AbstractAmmo_Kijyuu abstractArrow = arrowItem.createAmmo(worldIn, projectile, entityLiving);
		abstractArrow = customAmmo(abstractArrow);

		/* Damage */
		int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER, hStack);
		
		Player playerIn = (Player)entityLiving;
		boolean LUCK = this.playerLuck(playerIn) > 0.0F;
		int A = LUCK? 2 : 6;
		double CRITICAL = (worldIn.random.nextInt(A) == 0)? 0.5D : 0.0D;
		double LEVEL = (playerIn.experienceLevel >= 25)? 1.0D : ((playerIn.experienceLevel >= 19 && playerIn.experienceLevel < 25)? 0.5D : 0.0D);
		
		if (j == 0) { abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + LEVEL + CRITICAL); }
		if (j > 0) { abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + (double)j * 0.5D + LEVEL + CRITICAL); }
		
		/* Enchant */
		int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH, hStack);
		if (k > 0) { abstractArrow.setKnockback(k); }

		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAME, hStack) > 0) { abstractArrow.igniteForSeconds(100); }

		return abstractArrow;
	}
}
