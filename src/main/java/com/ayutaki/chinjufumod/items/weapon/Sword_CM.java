package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class Sword_CM extends SwordItem {

	public Sword_CM(Tier tier, Properties props) {
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
	public void onCraftedBy(ItemStack hStack, Level worldIn, Player playerIn) {
		if (this == Items_Weapon.SWORD_sakura.get()) { hStack.enchant(Enchantments.FIRE_ASPECT, 2); }
		if (this == Items_Weapon.SWORD_kaede.get()) { hStack.enchant(Enchantments.SHARPNESS, 2); }
		if (this == Items_Weapon.SWORD_ichoh.get()) { hStack.enchant(Enchantments.BANE_OF_ARTHROPODS, 3); }
		else { }
		super.onCraftedBy(hStack, worldIn, playerIn);
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		if (this == Items_Weapon.SWORD_kaede.get()) { return material.getItem() == Items_Seasonal.KAEDE_planks.get(); }
		if (this == Items_Weapon.SWORD_ichoh.get()) { return material.getItem() == Items_Seasonal.ICHOH_planks.get(); }
		return material.getItem() == Items_Seasonal.SAKURA_planks.get();
	}
}
