package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.entity.AmmoEntity_Small;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class Ammo_Small extends Item implements ProjectileItem {

	public Ammo_Small(Item.Properties props) {
		super(props);
	}
	
	public AbstractAmmo_Entity createAmmo(Level worldIn, ItemStack stack, LivingEntity shooter) {
		return new AmmoEntity_Small(worldIn, shooter, stack.copyWithCount(1));
	}

	@Override
	public Projectile asProjectile(Level worldIn, Position pos, ItemStack stack, Direction facing) {
		AmmoEntity_Small arrow = new AmmoEntity_Small(worldIn, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1));
		arrow.pickup = AbstractAmmo_Entity.Pickup.ALLOWED;
		return arrow;
	}
	
	/*for 1.20.6 */ 
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.LivingEntity playerIn) {
		return false;
	} 
}
