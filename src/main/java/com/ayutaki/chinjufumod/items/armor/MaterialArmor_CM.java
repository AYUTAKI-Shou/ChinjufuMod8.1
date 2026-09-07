package com.ayutaki.chinjufumod.items.armor;

import java.util.EnumMap;

import com.ayutaki.chinjufumod.handler.AssetArmor_CM;
import com.ayutaki.chinjufumod.tags.ItemCMTags;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class MaterialArmor_CM {

	/* ArmorMaterial(int durability,
	Map<ArmorType, Integer> defense,
	int enchantmentValue,
	Holder<SoundEvent> equipSound,
	float toughness,
	float knockbackResistance,
	TagKey<Item> repairIngredient,
	ResourceKey<EquipmentAsset> assetId) */
	
	public static final ArmorMaterial FUBUKI = new ArmorMaterial(17, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.FUBUKI);

	public static final ArmorMaterial KASUMI = new ArmorMaterial(17, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.KASUMI);

	public static final ArmorMaterial SHIGURE = new ArmorMaterial(17, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.SHIGURE);

	public static final ArmorMaterial SHIRATSUYU = new ArmorMaterial(17, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.SHIRATSUYU);

	public static final ArmorMaterial AKATSUKI = new ArmorMaterial(17, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.AKATSUKI);

	/* 0.42×45=18.9 */
	public static final ArmorMaterial YURA = new ArmorMaterial(19, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.YURA);
	
	/* 0.42×48=20.1 */
	public static final ArmorMaterial SENDAI = new ArmorMaterial(20, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.SENDAI);
	
	/* 0.42×50=21 */
	public static final ArmorMaterial RJ = new ArmorMaterial(21, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.RJ);
	
	/* 0.42×59=24.78 */
	public static final ArmorMaterial ZUIHOU = new ArmorMaterial(25, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.ZUIHOU);
	
	/* 0.42×50=21 */
	public static final ArmorMaterial MOGAMI = new ArmorMaterial(21, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.MOGAMI);
	/* 0.42×59=24.78 */
	public static final ArmorMaterial TONE = new ArmorMaterial(25, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.TONE);
	
	/* 0.42×72=30.24 */
	public static final ArmorMaterial KAGA = new ArmorMaterial(30, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.KAGA);
	
	/* 0.42×69=28.98 */
	public static final ArmorMaterial AKAGI = new ArmorMaterial(29, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 7);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.AKAGI);
	
	/* 33÷82≒0.4 調整値+0.02 ダイヤモンド相当を前提 */
	public static final ArmorMaterial KONGOU = new ArmorMaterial(33, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 8);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.KONGOU);
	
	public static final ArmorMaterial FUSOU = new ArmorMaterial(33, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 8);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.FUSOU);
	
	public static final ArmorMaterial ISE = new ArmorMaterial(32, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 8);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.ISE);
	
	public static final ArmorMaterial NAGATO = new ArmorMaterial(35, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 6);
		map.put(ArmorType.CHESTPLATE, 8);
		map.put(ArmorType.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.NAGATO);
	
	/* 0.42×15=6.3 */
	public static final ArmorMaterial I168 = new ArmorMaterial(7, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 4);
		map.put(ArmorType.CHESTPLATE, 4);
		map.put(ArmorType.HELMET, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.I168);
	
	/* 0.42×21=8.8 */
	public static final ArmorMaterial I13 = new ArmorMaterial(9, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 2);
		map.put(ArmorType.LEGGINGS, 4);
		map.put(ArmorType.CHESTPLATE, 4);
		map.put(ArmorType.HELMET, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.I13);
	
	/* 0.42×13=5.46 */
	public static final ArmorMaterial RO500 = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 4);
		map.put(ArmorType.CHESTPLATE, 4);
		map.put(ArmorType.HELMET, 3);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.RO500);

	/* 0.42×20=8.4 */
	public static final ArmorMaterial I401 = new ArmorMaterial(8, Util.make(new EnumMap<>(ArmorType.class), map -> { 
		map.put(ArmorType.BOOTS, 3);
		map.put(ArmorType.LEGGINGS, 4);
		map.put(ArmorType.CHESTPLATE, 4);
		map.put(ArmorType.HELMET, 3);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_IRON_ARMOR, AssetArmor_CM.I401);

	
	///* SantaCos *///
	public static final ArmorMaterial AKASHISANTA = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_RED_CARPET, AssetArmor_CM.AKASHISANTA);

	public static final ArmorMaterial AKASHISANTA_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.MATERIALS_BOOTS, AssetArmor_CM.AKASHISANTA);
	
	
	public static final ArmorMaterial KUMANOSANTA = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_RED_CARPET, AssetArmor_CM.KUMANOSANTA);

	public static final ArmorMaterial KUMANOSANTA_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.MATERIALS_BOOTS, AssetArmor_CM.KUMANOSANTA);
	
	
	public static final ArmorMaterial SUZUYASANTA = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_RED_CARPET, AssetArmor_CM.SUZUYASANTA);

	public static final ArmorMaterial SUZUYASANTA_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.MATERIALS_BOOTS, AssetArmor_CM.SUZUYASANTA);
	
	
	public static final ArmorMaterial RJ_SANTA = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_RED_CARPET, AssetArmor_CM.RJ_SANTA);

	public static final ArmorMaterial RJ_SANTA_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.MATERIALS_BOOTS, AssetArmor_CM.RJ_SANTA);
	
	
	public static final ArmorMaterial TEITOKUSANTA = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_RED_CARPET, AssetArmor_CM.TEITOKUSANTA);

	public static final ArmorMaterial TEITOKUSANTA_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.MATERIALS_BOOTS, AssetArmor_CM.TEITOKUSANTA);
	
	
	///* YUKATA *///
	public static final ArmorMaterial IKADUCHIYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.IKADUCHIYKT);

	public static final ArmorMaterial IKADUCHIYKT_HELMET = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_KAEDE_SLAB, AssetArmor_CM.IKADUCHIYKT);
	
	public static final ArmorMaterial IKADUCHIYKT_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_DARKOAK_SLAB, AssetArmor_CM.IKADUCHIYKT);
	
	
	public static final ArmorMaterial INADUMAYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.INADUMAYKT);

	public static final ArmorMaterial INADUMAYKT_HELMET = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_KAEDE_SLAB, AssetArmor_CM.INADUMAYKT);
	
	
	public static final ArmorMaterial HAMAKAZEYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.HAMAKAZEYKT);

	public static final ArmorMaterial HAMAKAZEYKT_HELMET = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_POPPY, AssetArmor_CM.HAMAKAZEYKT);
	

	public static final ArmorMaterial URAKAZEYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.URAKAZEYKT);

	public static final ArmorMaterial URAKAZEYKT_HELMET = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_BIRCH_SLAB, AssetArmor_CM.URAKAZEYKT);
	

	public static final ArmorMaterial KAWAKAZEYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.KAWAKAZEYKT);

	public static final ArmorMaterial KAWAKAZEYKT_HELMET = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_BIRCH_SLAB, AssetArmor_CM.KAWAKAZEYKT);


	public static final ArmorMaterial OBOROYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.OBOROYKT);

	public static final ArmorMaterial OBOROYKT_HELMET = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_DANDELION, AssetArmor_CM.OBOROYKT);
	

	public static final ArmorMaterial TTOKUYKT = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.TTOKUYKT);

	public static final ArmorMaterial TTOKUYKT_BOOTS = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.BOOTS, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_OAK_SLAB, AssetArmor_CM.TTOKUYKT);

	
	public static final ArmorMaterial TTOKUYKTB = new ArmorMaterial(6, Util.make(new EnumMap<>(ArmorType.class), map -> {
		map.put(ArmorType.LEGGINGS, 2);
		map.put(ArmorType.CHESTPLATE, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemCMTags.REPAIRS_TANMONO, AssetArmor_CM.TTOKUYKTB);

}
