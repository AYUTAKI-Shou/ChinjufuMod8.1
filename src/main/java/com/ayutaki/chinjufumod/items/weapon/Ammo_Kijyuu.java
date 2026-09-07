package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.AmmoEntity_Kijyuu;
import com.ayutaki.chinjufumod.items.addtab.IR_Armor;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Ammo_Kijyuu extends IR_Armor {

	public Ammo_Kijyuu(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	public AmmoEntity_Kijyuu createAmmo(World worldIn, ItemStack stack, EntityLivingBase shooter) {
		AmmoEntity_Kijyuu entitytippedarrow = new AmmoEntity_Kijyuu(worldIn, shooter);
		return entitytippedarrow;
	}

	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer playerIn) {
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
		return enchant <= 0 ? false : this.getClass() == Ammo_Kijyuu.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(TextFormatting.GOLD + I18n.format("tips.item_ammunition_kijyuu.name"));
	}
}
