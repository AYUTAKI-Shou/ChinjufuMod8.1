package com.ayutaki.chinjufumod.items.armor;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class CMArmorMaterial {
	/*durabilityMultiplier -> .getDurability(33) */
	public static final Holder<ArmorMaterial> FUBUKI = register("chinjufumod:fubuki", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));

	public static final Holder<ArmorMaterial> KASUMI = register("chinjufumod:kasumi", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));

	public static final Holder<ArmorMaterial> SHIGURE = register("chinjufumod:shigure", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));

	public static final Holder<ArmorMaterial> SHIRATSUYU = register("chinjufumod:shiratsuyu", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));

	public static final Holder<ArmorMaterial> AKATSUKI = register("chinjufumod:akatsuki", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));

	/* 0.42×45=18.9 */
	public static final Holder<ArmorMaterial> YURA = register("chinjufumod:yura", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×48=20.1 */
	public static final Holder<ArmorMaterial> SENDAI = register("chinjufumod:sendai", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/* 0.42×50=21 */
	public static final Holder<ArmorMaterial> RJ = register("chinjufumod:ryujou", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×59=24.78 */
	public static final Holder<ArmorMaterial> ZUIHOU = register("chinjufumod:zuihou", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/* 0.42×50=21 */
	public static final Holder<ArmorMaterial> MOGAMI = register("chinjufumod:mogami", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×59=24.78 */
	public static final Holder<ArmorMaterial> TONE = register("chinjufumod:tone", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/* 0.42×72=30.24 */
	public static final Holder<ArmorMaterial> KAGA = register("chinjufumod:kaga", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×69=28.98 */
	public static final Holder<ArmorMaterial> AKAGI = register("chinjufumod:akagi", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 7);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/* 33÷82≒0.4 調整値+0.02 ダイヤモンド相当を前提 */
	public static final Holder<ArmorMaterial> KONGOU = register("chinjufumod:kongou", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 8);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	public static final Holder<ArmorMaterial> FUSOU = register("chinjufumod:fusou", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 8);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	public static final Holder<ArmorMaterial> NAGATO = register("chinjufumod:nagato", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 8);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/* 0.42×15=6.3 */
	public static final Holder<ArmorMaterial> I168 = register("chinjufumod:i168", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 4);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×21=8.8 */
	public static final Holder<ArmorMaterial> I13 = register("chinjufumod:i13", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 4);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.HELMET, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×13=5.46 */
	public static final Holder<ArmorMaterial> RO500 = register("chinjufumod:ro500", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 4);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/** 7.0.2 **/
	public static final Holder<ArmorMaterial> ISE = register("chinjufumod:ise", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 6);
		map.put(ArmorItem.Type.CHESTPLATE, 8);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 10, SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT));
	/* 0.42×20=8.4 */
	public static final Holder<ArmorMaterial> I401 = register("chinjufumod:i401", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 3);
		map.put(ArmorItem.Type.LEGGINGS, 4);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.HELMET, 3);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
	
	/* SantaCos */
	public static final Holder<ArmorMaterial> AKASHISANTA = register("chinjufumod:santaakashi", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> KUMANOSANTA = register("chinjufumod:santakumano", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> SUZUYASANTA = register("chinjufumod:santasuzuya", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> RYUJOUSANTA = register("chinjufumod:santaryujou", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> TEITOKUSANTA = register("chinjufumod:santattk", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	/* YUKATA */
	public static final Holder<ArmorMaterial> IKADUCHIYKT = register("chinjufumod:ykt_ikaduchi", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> INADUMAYKT = register("chinjufumod:ykt_inaduma", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> HAMAKAZEYKT = register("chinjufumod:ykt_hamakaze", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> URAKAZEYKT = register("chinjufumod:ykt_urakaze", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> KAWAKAZEYKT = register("chinjufumod:ykt_kawakaze", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> OBOROYKT = register("chinjufumod:ykt_oboro", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> TTOKUYKT = register("chinjufumod:ykt_ttoku", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));
	
	public static final Holder<ArmorMaterial> TTOKUYKTB = register("chinjufumod:ykt_ttokub", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> { 
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.LEGGINGS, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 2);
		map.put(ArmorItem.Type.HELMET, 1);
	}), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items_NoTab.EMBLEM_C.get()));

	/* Share variables */
	@SuppressWarnings("removal")
	private static Holder<ArmorMaterial> register(String key, EnumMap<ArmorItem.Type, Integer> type, int enchantability, 
		Holder<SoundEvent> equipSound, float tough, float knockbackResist, Supplier<Ingredient> repairItem) {
		List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(new ResourceLocation(key)));
		return register(key, type, enchantability, equipSound, tough, knockbackResist, repairItem, list);
	}

	@SuppressWarnings("removal")
	private static Holder<ArmorMaterial> register(String key, EnumMap<ArmorItem.Type, Integer> type, int enchantability, 
			Holder<SoundEvent> equipSound, float tough, float knockbackResist, Supplier<Ingredient> repairItem, List<ArmorMaterial.Layer> layer) {
		
		EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);
		for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) { enummap.put(armoritem$type, type.get(armoritem$type)); }

		return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
			new ResourceLocation(key),
			new ArmorMaterial(enummap, enchantability, equipSound, repairItem, layer, tough, knockbackResist));
	}
}
/* example
	public static final Holder<ArmorMaterial> IRON = register("iron", Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.BOOTS, 2);
		map.put(ArmorItem.Type.LEGGINGS, 5);
		map.put(ArmorItem.Type.CHESTPLATE, 6);
		map.put(ArmorItem.Type.HELMET, 2);
		map.put(ArmorItem.Type.BODY, 5); <- horse.armor
	}), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));
*/
