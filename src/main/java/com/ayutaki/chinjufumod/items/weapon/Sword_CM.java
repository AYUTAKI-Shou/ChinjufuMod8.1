package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class Sword_CM extends Item {

	public Sword_CM(ToolMaterial tier, float damage, float speed, Item.Properties props) {
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
	public void onCraftedBy(ItemStack hStack, Level worldIn, Player playerIn) {
		var lookup = worldIn.holderLookup(Registries.ENCHANTMENT);
		
		if (this == Items_Weapon.SWORD_sakura.get()) { hStack.enchant(lookup.get(Enchantments.FIRE_ASPECT).orElseThrow(), 2); }
		if (this == Items_Weapon.SWORD_kaede.get()) { hStack.enchant(lookup.get(Enchantments.SHARPNESS).orElseThrow(), 2); }
		if (this == Items_Weapon.SWORD_ichoh.get()) { hStack.enchant(lookup.get(Enchantments.BANE_OF_ARTHROPODS).orElseThrow(), 3); }
		else { }
		super.onCraftedBy(hStack, worldIn, playerIn);
	}
}
