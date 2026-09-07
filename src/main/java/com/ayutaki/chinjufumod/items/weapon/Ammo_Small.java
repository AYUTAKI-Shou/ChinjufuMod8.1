package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.entity.AmmoEntity_Small;
import com.ayutaki.chinjufumod.items.base.IG_Armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Ammo_Small extends IG_Armor {

	public Ammo_Small(Item.Properties props) {
		super(props);
	}

	public AbstractAmmo_Entity createAmmo(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new AmmoEntity_Small(worldIn, shooter);
	}

	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity playerIn) {
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.enchantment.Enchantments.INFINITY, bow);
		return enchant <= 0 ? false : this.getClass() == Ammo_Small.class;
	}
}
