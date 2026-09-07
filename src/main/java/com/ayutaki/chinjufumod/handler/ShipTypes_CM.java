package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.items.armor.Base_BattleshipItem;
import com.ayutaki.chinjufumod.items.armor.Base_CarrierItem;
import com.ayutaki.chinjufumod.items.armor.Base_CruiserItem;
import com.ayutaki.chinjufumod.items.armor.Base_DestroyerItem;
import com.ayutaki.chinjufumod.items.armor.Base_Submarine;
import com.ayutaki.chinjufumod.registry.Items_Armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ShipTypes_CM {

	public static boolean typeDestroyer(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof Base_DestroyerItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof Base_DestroyerItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof Base_DestroyerItem &&
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof Base_DestroyerItem); }
	
	public static boolean typeCruiser(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof Base_CruiserItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof Base_CruiserItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof Base_CruiserItem &&
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof Base_CruiserItem); }
	
	public static boolean typeCarrier(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof Base_CarrierItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof Base_CarrierItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof Base_CarrierItem &&
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof Base_CarrierItem); }
	
	public static boolean typeBattleship(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof Base_BattleshipItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof Base_BattleshipItem && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof Base_BattleshipItem &&
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof Base_BattleshipItem); }
	
	public static boolean typeSubmarine(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof Base_Submarine && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof Base_Submarine && 
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof Base_Submarine &&
						playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof Base_Submarine); }
	/* floatplane */
	public static boolean typeYURA(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.YURA_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.YURA_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.YURA_LEGGINGS && 
					(playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.YURA_BOOTS || playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.YURA_BOOTS_KAI)); }
	
	public static boolean typeMOGAMI(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.MOGAMI_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.MOGAMI_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.MOGAMI_LEGGINGS && 
					(playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.MOGAMI_BOOTS || playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.MOGAMI_BOOTS_KAI)); }
	
	public static boolean typeTONE(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.TONE_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.TONE_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.TONE_LEGGINGS && 
					(playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.TONE_BOOTS || playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.TONE_BOOTS_KAI)); }
	
	public static boolean typeISE(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.ISE_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.ISE_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.ISE_LEGGINGS && 
					(playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.ISE_BOOTS || playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.ISE_BOOTS_KAI)); }
	
	public static boolean typeFUSOU(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.FUSOU_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.FUSOU_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.FUSOU_LEGGINGS && 
					(playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.FUSOU_BOOTS || playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.FUSOU_BOOTS_KAI)); }
	
	public static boolean typeI401(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.I401_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.I401_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.I401_LEGGINGS && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.I401_BOOTS); }
	
	public static boolean typeI13(EntityPlayer playerIn) {
		return (playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items_Armor.I13_HELMET && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items_Armor.I13_CHESTPLATE && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items_Armor.I13_LEGGINGS && 
					playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items_Armor.I13_BOOTS); }
}
