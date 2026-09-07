package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

public class Anchor extends Item {

	public Anchor(ToolMaterial tier, float damage, float speed, Item.Properties props) {
		super(tier.applySwordProperties(props, damage, speed));
	}

	@Override
	public boolean hurtEnemy(ItemStack hStack, LivingEntity target, LivingEntity attacker) {
		return true;
	}
	
	@Override
	public void postHurtEnemy(ItemStack hStack, LivingEntity target, LivingEntity entityLiving) {
		CMEvents.toolDamegeLE(1, entityLiving, hStack);
	}
	
	@Override
	public void inventoryTick(ItemStack hStack, Level worldIn, Entity entity, int slot, boolean flag) {
		
		if (entity != null && entity instanceof Player) {
	 		Player playerIn = (Player)entity;
	 		boolean destroyer = ShipTypes_CM.typeDestroyer(playerIn);
	 		
				if (playerIn.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items_Weapon.ANCHOR.get()) {
					
					/** add Potion Effect. **/
					if (!worldIn.isClientSide && destroyer) {
						if (!(playerIn.hasEffect(MobEffects.DAMAGE_BOOST))) {
							playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
						} //!DAMAGE_BOOST
						
						if (playerIn.hasEffect(MobEffects.DAMAGE_BOOST)) { }
					}
					
					if (!destroyer) { }
				} //MAINHAND
				
				if (playerIn.getItemBySlot(EquipmentSlot.MAINHAND).getItem() != Items_Weapon.ANCHOR.get()) { }
		} //Player
	}
}