package com.ayutaki.chinjufumod.items.weapon;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.entity.AmmoEntity_Large;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class Ammo_Large extends Item implements ProjectileItem {

	public Ammo_Large(Item.Properties props) {
		super(props);
	}
	
	public AbstractAmmo_Entity createAmmo(Level worldIn, ItemStack hStack, LivingEntity shooter, @Nullable ItemStack eStack) {
		return new AmmoEntity_Large(worldIn, shooter, hStack.copyWithCount(1), eStack);
	}

	@Override
	public Projectile asProjectile(Level worldIn, Position pos, ItemStack hStack, Direction facing) {
		AmmoEntity_Large arrow = new AmmoEntity_Large(worldIn, pos.x(), pos.y(), pos.z(), hStack.copyWithCount(1), null);
		arrow.pickup = AbstractAmmo_Entity.Pickup.ALLOWED;
		return arrow;
	}
	
	/*for 1.20.6 */ 
	public boolean isInfinite(ItemStack hStack, ItemStack bow, net.minecraft.world.entity.LivingEntity playerIn) {
		return false;
	} 
}
