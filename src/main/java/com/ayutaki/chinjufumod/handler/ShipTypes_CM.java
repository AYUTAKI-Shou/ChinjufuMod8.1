package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.items.armor.Base_BattleshipItem;
import com.ayutaki.chinjufumod.items.armor.Base_CarrierItem;
import com.ayutaki.chinjufumod.items.armor.Base_CruiserItem;
import com.ayutaki.chinjufumod.items.armor.Base_DestroyerItem;
import com.ayutaki.chinjufumod.items.armor.Base_Submarine;
import com.ayutaki.chinjufumod.registry.Items_Armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class ShipTypes_CM {

	public static boolean typeDestroyer(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Base_DestroyerItem && 
						playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Base_DestroyerItem && 
						playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Base_DestroyerItem &&
						playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Base_DestroyerItem); }
	
	public static boolean typeCruiser(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Base_CruiserItem && 
						playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Base_CruiserItem && 
						playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Base_CruiserItem &&
						playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Base_CruiserItem); }
	
	public static boolean typeCarrier(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Base_CarrierItem && 
						playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Base_CarrierItem && 
						playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Base_CarrierItem &&
						playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Base_CarrierItem); }
	
	public static boolean typeBattleship(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Base_BattleshipItem && 
						playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Base_BattleshipItem && 
						playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Base_BattleshipItem &&
						playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Base_BattleshipItem); }

	public static boolean typeSubmarine(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof Base_Submarine && 
						playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof Base_Submarine && 
						playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof Base_Submarine &&
						playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof Base_Submarine); }
	
	/* floatplane */
	public static boolean typeYURA(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.YURA_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.YURA_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.YURA_LEGGINGS.get() && 
					(playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.YURA_BOOTS.get() || playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.YURA_BOOTS_KAI.get())); }
	
	public static boolean typeMOGAMI(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.MOGAMI_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.MOGAMI_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.MOGAMI_LEGGINGS.get() && 
					(playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.MOGAMI_BOOTS.get() || playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.MOGAMI_BOOTS_KAI.get())); }
	
	public static boolean typeTONE(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.TONE_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.TONE_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.TONE_LEGGINGS.get() && 
					(playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.TONE_BOOTS.get() || playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.TONE_BOOTS_KAI.get())); }
	
	public static boolean typeISE(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.ISE_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.ISE_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.ISE_LEGGINGS.get() && 
					(playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.ISE_BOOTS.get() || playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.ISE_BOOTS_KAI.get())); }
	
	public static boolean typeFUSOU(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.FUSOU_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.FUSOU_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.FUSOU_LEGGINGS.get() && 
					(playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.FUSOU_BOOTS.get() || playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.FUSOU_BOOTS_KAI.get())); }
	
	public static boolean typeI401(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.I401_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.I401_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.I401_LEGGINGS.get() && 
					playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.I401_BOOTS.get()); }
	
	public static boolean typeI13(Player playerIn) {
		return (playerIn.getItemBySlot(EquipmentSlot.HEAD).getItem() == Items_Armor.I13_HELMET.get() && 
					playerIn.getItemBySlot(EquipmentSlot.CHEST).getItem() == Items_Armor.I13_CHESTPLATE.get() && 
					playerIn.getItemBySlot(EquipmentSlot.LEGS).getItem() == Items_Armor.I13_LEGGINGS.get() && 
					playerIn.getItemBySlot(EquipmentSlot.FEET).getItem() == Items_Armor.I13_BOOTS.get()); }
}
