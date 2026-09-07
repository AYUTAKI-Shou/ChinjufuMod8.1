package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.items.armor.Base_BattleshipItem;
import com.ayutaki.chinjufumod.items.armor.Base_CarrierItem;
import com.ayutaki.chinjufumod.items.armor.Base_CruiserItem;
import com.ayutaki.chinjufumod.items.armor.Base_DestroyerItem;
import com.ayutaki.chinjufumod.items.armor.Base_Submarine;
import com.ayutaki.chinjufumod.registry.Items_Armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class ShipTypes_CM {

	public static boolean typeDestroyer(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof Base_DestroyerItem && 
						playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() instanceof Base_DestroyerItem && 
						playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() instanceof Base_DestroyerItem &&
						playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() instanceof Base_DestroyerItem); }
	
	public static boolean typeCruiser(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof Base_CruiserItem && 
						playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() instanceof Base_CruiserItem && 
						playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() instanceof Base_CruiserItem &&
						playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() instanceof Base_CruiserItem); }
	
	public static boolean typeCarrier(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof Base_CarrierItem && 
						playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() instanceof Base_CarrierItem && 
						playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() instanceof Base_CarrierItem &&
						playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() instanceof Base_CarrierItem); }
	
	public static boolean typeBattleship(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof Base_BattleshipItem && 
						playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() instanceof Base_BattleshipItem && 
						playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() instanceof Base_BattleshipItem &&
						playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() instanceof Base_BattleshipItem); }
	
	public static boolean typeSubmarine(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof Base_Submarine && 
						playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() instanceof Base_Submarine && 
						playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() instanceof Base_Submarine &&
						playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() instanceof Base_Submarine); }
	
	/* floatplane */
	public static boolean typeYURA(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.YURA_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.YURA_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.YURA_LEGGINGS && 
					(playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.YURA_BOOTS || playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.YURA_BOOTS_KAI)); }
	
	public static boolean typeMOGAMI(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.MOGAMI_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.MOGAMI_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.MOGAMI_LEGGINGS && 
					(playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.MOGAMI_BOOTS || playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.MOGAMI_BOOTS_KAI)); }
	
	public static boolean typeTONE(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.TONE_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.TONE_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.TONE_LEGGINGS && 
					(playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.TONE_BOOTS || playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.TONE_BOOTS_KAI)); }
	
	public static boolean typeISE(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.ISE_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.ISE_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.ISE_LEGGINGS && 
					(playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.ISE_BOOTS || playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.ISE_BOOTS_KAI)); }
	
	public static boolean typeFUSOU(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.FUSOU_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.FUSOU_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.FUSOU_LEGGINGS && 
					(playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.FUSOU_BOOTS || playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.FUSOU_BOOTS_KAI)); }
	
	public static boolean typeI401(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.I401_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.I401_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.I401_LEGGINGS && 
					playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.I401_BOOTS); }
	
	public static boolean typeI13(PlayerEntity playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Items_Armor.I13_HELMET && 
					playerIn.getItemBySlot(EquipmentSlotType.CHEST).getItem() == Items_Armor.I13_CHESTPLATE && 
					playerIn.getItemBySlot(EquipmentSlotType.LEGS).getItem() == Items_Armor.I13_LEGGINGS && 
					playerIn.getItemBySlot(EquipmentSlotType.FEET).getItem() == Items_Armor.I13_BOOTS); }
}
