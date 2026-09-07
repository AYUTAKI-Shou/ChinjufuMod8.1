package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AmmoEntity_Large;
import com.ayutaki.chinjufumod.items.addtab.IR_Armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Ammo_Large extends IR_Armor {

	public Ammo_Large(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	public AmmoEntity_Large createAmmo(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		AmmoEntity_Large entitytippedarrow = new AmmoEntity_Large(worldIn, shooter);
		return entitytippedarrow;
	}

	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer playerIn) {
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
		return enchant <= 0 ? false : this.getClass() == Ammo_Large.class;
	}
}
