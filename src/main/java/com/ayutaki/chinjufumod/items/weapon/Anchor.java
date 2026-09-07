package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Anchor extends SwordItem {

	public Anchor(Tier tier, Properties props) {
		super(tier, props);
	}

	public static ItemAttributeModifiers createAttributes(Tier tier, int i, float f) {
		return ItemAttributeModifiers.builder()
			.add(Attributes.ATTACK_DAMAGE,
				new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)((float)i + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)f, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND).build();
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player playerIn) {
		return !playerIn.isCreative();
	}

	@Override
	public boolean hurtEnemy(ItemStack hStack, LivingEntity entityIn, LivingEntity entityLiving) {
		CMEvents.toolDamegeLE(1, entityLiving, hStack);
		return true;
	}

	@Override
	public boolean canPerformAction(ItemStack hStack, net.minecraftforge.common.ToolAction toolAction) {
		return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
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