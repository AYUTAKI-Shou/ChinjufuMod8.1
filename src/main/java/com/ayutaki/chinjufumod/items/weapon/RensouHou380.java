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

public class RensouHou380 extends AbstractRensouHou_L {

	public RensouHou380(Item.Properties props) {
		super(props);
	}

	protected Projectile createProjectile(Level worldIn, LivingEntity entityLiving, ItemStack hStack, ItemStack projectile, boolean crit) {
		Ammo_Large arrowItem = (Ammo_Large)(projectile.getItem() instanceof Ammo_Large arrowItem1 ? arrowItem1 : Items_Weapon.AMMUNITION_L.get());
		AbstractAmmo_Entity abstractArrow = arrowItem.createAmmo(worldIn, projectile, entityLiving);
		abstractArrow = customAmmo(abstractArrow);

		/* Damage */
		abstractArrow.setCritArrow(true);
		Player playerIn = (Player)entityLiving;
		boolean battleship = ShipTypes_CM.typeBattleship(playerIn);
		/** addKOUKEI, fixFIT, shipType **/
		this.setAmmoDamage(hStack, worldIn, abstractArrow, playerIn, 5.5D, 2.0D, battleship);
		this.setAmmoEnchant(hStack, abstractArrow);

		return abstractArrow;
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_rensouhou_large").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_rensouhou380").withStyle(ChatFormatting.DARK_GREEN));
	}
}
