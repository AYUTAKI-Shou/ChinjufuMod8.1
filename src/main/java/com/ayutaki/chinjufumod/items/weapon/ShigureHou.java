package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ShigureHou extends AbstractRensouHou_S {

	public ShigureHou(Item.Properties props) {
		super(props);
	}

	/* Add Power and Enchantments */
	protected Projectile createProjectile(Level worldIn, LivingEntity entityLiving, ItemStack hStack, ItemStack projectile, boolean crit) {
		Ammo_Small arrowItem = (Ammo_Small)(projectile.getItem() instanceof Ammo_Small arrowItem1 ? arrowItem1 : Items_Weapon.AMMUNITION_S.get());
		AbstractAmmo_Entity abstractArrow = arrowItem.createAmmo(worldIn, projectile, entityLiving, hStack);
		abstractArrow = customAmmo(abstractArrow);

		/* Damage */
		abstractArrow.setCritArrow(true);
		Player playerIn = (Player)entityLiving;
		boolean destroyer = ShipTypes_CM.typeDestroyer(playerIn);
		/** addKOUKEI, fixFIT, shipType **/
		this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 0.0D, 0.5D, destroyer);
		this.setAmmoEnchant(hStack, worldIn, abstractArrow);

		return abstractArrow;
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_rensouhou_small").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_shigurehou").withStyle(ChatFormatting.DARK_GREEN));
	}
}
