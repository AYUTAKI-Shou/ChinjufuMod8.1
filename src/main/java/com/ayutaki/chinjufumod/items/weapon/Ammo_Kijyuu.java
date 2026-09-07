package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Kijyuu;
import com.ayutaki.chinjufumod.entity.AmmoEntity_Kijyuu;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Ammo_Kijyuu extends Item implements ProjectileItem {

	public Ammo_Kijyuu(Item.Properties props) {
		super(props);
	}
	
	public AbstractAmmo_Kijyuu createAmmo(Level worldIn, ItemStack stack, LivingEntity shooter) {
		return new AmmoEntity_Kijyuu(worldIn, shooter, stack.copyWithCount(1));
	}

	@Override
	public Projectile asProjectile(Level worldIn, Position pos, ItemStack stack, Direction facing) {
		AmmoEntity_Kijyuu arrow = new AmmoEntity_Kijyuu(worldIn, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1));
		arrow.pickup = AbstractAmmo_Kijyuu.Pickup.ALLOWED;
		return arrow;
	}
	
	/*for 1.20.6 */ 
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.LivingEntity playerIn) {
		return false;
	} 
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_ammunition_kijyuu").withStyle(ChatFormatting.GOLD));
	}
}
