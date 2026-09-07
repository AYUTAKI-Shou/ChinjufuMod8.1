package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AmmoEntity_Medium;
import com.ayutaki.chinjufumod.items.addtab.IR_Armor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Ammo_Medium extends IR_Armor {

	public Ammo_Medium(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	public AmmoEntity_Medium createAmmo(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		AmmoEntity_Medium entitytippedarrow = new AmmoEntity_Medium(worldIn, shooter);
		return entitytippedarrow;
	}

	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer playerIn) {
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
		return enchant <= 0 ? false : this.getClass() == Ammo_Medium.class;
	}
}
