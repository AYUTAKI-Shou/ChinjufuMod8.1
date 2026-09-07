package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.armor.Armor_Akatsuki;
import com.ayutaki.chinjufumod.items.armor.Armor_AkatsukiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Battleship;
import com.ayutaki.chinjufumod.items.armor.Armor_BattleshipKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Carrier;
import com.ayutaki.chinjufumod.items.armor.Armor_CarrierKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Destroyer;
import com.ayutaki.chinjufumod.items.armor.Armor_DestroyerKai;
import com.ayutaki.chinjufumod.items.armor.Armor_I13;
import com.ayutaki.chinjufumod.items.armor.Armor_I168;
import com.ayutaki.chinjufumod.items.armor.Armor_I401;
import com.ayutaki.chinjufumod.items.armor.Armor_Ise;
import com.ayutaki.chinjufumod.items.armor.Armor_IseKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Kasumi;
import com.ayutaki.chinjufumod.items.armor.Armor_KasumiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Mogami;
import com.ayutaki.chinjufumod.items.armor.Armor_MogamiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Nagato;
import com.ayutaki.chinjufumod.items.armor.Armor_NagatoKai;
import com.ayutaki.chinjufumod.items.armor.Armor_RJ;
import com.ayutaki.chinjufumod.items.armor.Armor_RJKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Ro500;
import com.ayutaki.chinjufumod.items.armor.Armor_Sendai;
import com.ayutaki.chinjufumod.items.armor.Armor_SendaiKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Tone;
import com.ayutaki.chinjufumod.items.armor.Armor_ToneKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Yura;
import com.ayutaki.chinjufumod.items.armor.Armor_YuraKai;
import com.ayutaki.chinjufumod.items.armor.Armor_Zuihou;
import com.ayutaki.chinjufumod.items.armor.Armor_ZuihouKai;
import com.ayutaki.chinjufumod.items.armor.CMArmorMaterial;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items_Armor {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	/* Destroyer */
	public static final RegistryObject<Item> FUBUKI_HELMET = register("item_fubuki_helmet", () -> new Armor_Destroyer(CMArmorMaterial.FUBUKI, ArmorItem.Type.HELMET, helmet(17)));
	public static final RegistryObject<Item> FUBUKI_CHESTPLATE = register("item_fubuki_chestplate", () -> new Armor_Destroyer(CMArmorMaterial.FUBUKI, ArmorItem.Type.CHESTPLATE, chestPlate(17)));
	public static final RegistryObject<Item> FUBUKI_LEGGINGS = register("item_fubuki_leggings", () -> new Armor_Destroyer(CMArmorMaterial.FUBUKI, ArmorItem.Type.LEGGINGS, leggings(17)));
	public static final RegistryObject<Item> FUBUKI_BOOTS = register("item_fubuki_boots", () -> new Armor_Destroyer(CMArmorMaterial.FUBUKI, ArmorItem.Type.BOOTS, boots(17)));
	public static final RegistryObject<Item> FUBUKI_BOOTS_KAI = register("item_fubuki_bootskai", () -> new Armor_DestroyerKai(CMArmorMaterial.FUBUKI, ArmorItem.Type.BOOTS, boots(17)));

	public static final RegistryObject<Item> KASUMI_HELMET = register("item_kasumi_helmet", () -> new Armor_Kasumi(CMArmorMaterial.KASUMI, ArmorItem.Type.HELMET, helmet(17)));
	public static final RegistryObject<Item> KASUMI_CHESTPLATE = register("item_kasumi_chestplate", () -> new Armor_Kasumi(CMArmorMaterial.KASUMI, ArmorItem.Type.CHESTPLATE, chestPlate(17)));
	public static final RegistryObject<Item> KASUMI_LEGGINGS = register("item_kasumi_leggings", () -> new Armor_Kasumi(CMArmorMaterial.KASUMI, ArmorItem.Type.LEGGINGS, leggings(17)));
	public static final RegistryObject<Item> KASUMI_BOOTS = register("item_kasumi_boots", () -> new Armor_Kasumi(CMArmorMaterial.KASUMI, ArmorItem.Type.BOOTS, boots(17)));
	public static final RegistryObject<Item> KASUMI_BOOTS_KAI = register("item_kasumi_bootskai", () -> new Armor_KasumiKai(CMArmorMaterial.KASUMI, ArmorItem.Type.BOOTS, boots(17)));

	public static final RegistryObject<Item> SHIRATSUYU_HELMET = register("item_shiratsuyu_helmet", () -> new Armor_Destroyer(CMArmorMaterial.SHIRATSUYU, ArmorItem.Type.HELMET, helmet(17)));
	public static final RegistryObject<Item> SHIRATSUYU_CHESTPLATE = register("item_shiratsuyu_chestplate", () -> new Armor_Destroyer(CMArmorMaterial.SHIRATSUYU, ArmorItem.Type.CHESTPLATE, chestPlate(17)));
	public static final RegistryObject<Item> SHIRATSUYU_LEGGINGS = register("item_shiratsuyu_leggings", () -> new Armor_Destroyer(CMArmorMaterial.SHIRATSUYU, ArmorItem.Type.LEGGINGS, leggings(17)));
	public static final RegistryObject<Item> SHIRATSUYU_BOOTS = register("item_shiratsuyu_boots", () -> new Armor_Destroyer(CMArmorMaterial.SHIRATSUYU, ArmorItem.Type.BOOTS, boots(17)));
	public static final RegistryObject<Item> SHIRATSUYU_BOOTS_KAI = register("item_shiratsuyu_bootskai", () -> new Armor_DestroyerKai(CMArmorMaterial.SHIRATSUYU, ArmorItem.Type.BOOTS, boots(17)));

	public static final RegistryObject<Item> SHIGURE_HELMET = register("item_shigure_helmet", () -> new Armor_Destroyer(CMArmorMaterial.SHIGURE, ArmorItem.Type.HELMET, helmet(17)));
	public static final RegistryObject<Item> SHIGURE_CHESTPLATE = register("item_shigure_chestplate", () -> new Armor_Destroyer(CMArmorMaterial.SHIGURE, ArmorItem.Type.CHESTPLATE, chestPlate(17)));
	public static final RegistryObject<Item> SHIGURE_LEGGINGS = register("item_shigure_leggings", () -> new Armor_Destroyer(CMArmorMaterial.SHIGURE, ArmorItem.Type.LEGGINGS, leggings(17)));
	public static final RegistryObject<Item> SHIGURE_BOOTS = register("item_shigure_boots", () -> new Armor_Destroyer(CMArmorMaterial.SHIGURE, ArmorItem.Type.BOOTS, boots(17)));
	public static final RegistryObject<Item> SHIGURE_BOOTS_KAI = register("item_shigure_bootskai", () -> new Armor_DestroyerKai(CMArmorMaterial.SHIGURE, ArmorItem.Type.BOOTS, boots(17)));

	public static final RegistryObject<Item> AKATSUKI_HELMET = register("item_akatsuki_helmet", () -> new Armor_Akatsuki(CMArmorMaterial.AKATSUKI, ArmorItem.Type.HELMET, helmet(17)));
	public static final RegistryObject<Item> AKATSUKI_CHESTPLATE = register("item_akatsuki_chestplate", () -> new Armor_Akatsuki(CMArmorMaterial.AKATSUKI, ArmorItem.Type.CHESTPLATE, chestPlate(17)));
	public static final RegistryObject<Item> AKATSUKI_LEGGINGS = register("item_akatsuki_leggings", () -> new Armor_Akatsuki(CMArmorMaterial.AKATSUKI, ArmorItem.Type.LEGGINGS, leggings(17)));
	public static final RegistryObject<Item> AKATSUKI_BOOTS = register("item_akatsuki_boots", () -> new Armor_Akatsuki(CMArmorMaterial.AKATSUKI, ArmorItem.Type.BOOTS, boots(17)));
	public static final RegistryObject<Item> AKATSUKI_BOOTS_KAI = register("item_akatsuki_bootskai", () -> new Armor_AkatsukiKai(CMArmorMaterial.AKATSUKI, ArmorItem.Type.BOOTS, boots(17)));
	
	/* Cruiser */
	public static final RegistryObject<Item> SENDAI_HELMET = register("item_sendai_helmet", () -> new Armor_Sendai(CMArmorMaterial.SENDAI, ArmorItem.Type.HELMET, helmet(20)));
	public static final RegistryObject<Item> SENDAI_CHESTPLATE = register("item_sendai_chestplate", () -> new Armor_Sendai(CMArmorMaterial.SENDAI, ArmorItem.Type.CHESTPLATE, chestPlate(20)));
	public static final RegistryObject<Item> SENDAI_LEGGINGS = register("item_sendai_leggings", () -> new Armor_Sendai(CMArmorMaterial.SENDAI, ArmorItem.Type.LEGGINGS, leggings(20)));
	public static final RegistryObject<Item> SENDAI_BOOTS = register("item_sendai_boots", () -> new Armor_Sendai(CMArmorMaterial.SENDAI, ArmorItem.Type.BOOTS, boots(20)));
	public static final RegistryObject<Item> SENDAI_BOOTS_KAI = register("item_sendai_bootskai", () -> new Armor_SendaiKai(CMArmorMaterial.SENDAI, ArmorItem.Type.BOOTS, boots(20)));

	public static final RegistryObject<Item> YURA_HELMET = register("item_yura_helmet", () -> new Armor_Yura(CMArmorMaterial.YURA, ArmorItem.Type.HELMET, helmet(19)));
	public static final RegistryObject<Item> YURA_CHESTPLATE = register("item_yura_chestplate", () -> new Armor_Yura(CMArmorMaterial.YURA, ArmorItem.Type.CHESTPLATE, chestPlate(19)));
	public static final RegistryObject<Item> YURA_LEGGINGS = register("item_yura_leggings", () -> new Armor_Yura(CMArmorMaterial.YURA, ArmorItem.Type.LEGGINGS, leggings(19)));
	public static final RegistryObject<Item> YURA_BOOTS = register("item_yura_boots", () -> new Armor_Yura(CMArmorMaterial.YURA, ArmorItem.Type.BOOTS, boots(19)));
	public static final RegistryObject<Item> YURA_BOOTS_KAI = register("item_yura_bootskai", () -> new Armor_YuraKai(CMArmorMaterial.YURA, ArmorItem.Type.BOOTS, boots(19)));

	/* Heavy Cruiser */
	public static final RegistryObject<Item> MOGAMI_HELMET = register("item_mogami_helmet", () -> new Armor_Mogami(CMArmorMaterial.MOGAMI, ArmorItem.Type.HELMET, helmet(21)));
	public static final RegistryObject<Item> MOGAMI_CHESTPLATE = register("item_mogami_chestplate", () -> new Armor_Mogami(CMArmorMaterial.MOGAMI, ArmorItem.Type.CHESTPLATE, chestPlate(21)));
	public static final RegistryObject<Item> MOGAMI_LEGGINGS = register("item_mogami_leggings", () -> new Armor_Mogami(CMArmorMaterial.MOGAMI, ArmorItem.Type.LEGGINGS, leggings(21)));
	public static final RegistryObject<Item> MOGAMI_BOOTS = register("item_mogami_boots", () -> new Armor_Mogami(CMArmorMaterial.MOGAMI, ArmorItem.Type.BOOTS, boots(21)));
	public static final RegistryObject<Item> MOGAMI_BOOTS_KAI = register("item_mogami_bootskai", () -> new Armor_MogamiKai(CMArmorMaterial.MOGAMI, ArmorItem.Type.BOOTS, boots(21)));

	public static final RegistryObject<Item> TONE_HELMET = register("item_tone_helmet", () -> new Armor_Tone(CMArmorMaterial.TONE, ArmorItem.Type.HELMET, helmet(25)));
	public static final RegistryObject<Item> TONE_CHESTPLATE = register("item_tone_chestplate", () -> new Armor_Tone(CMArmorMaterial.TONE, ArmorItem.Type.CHESTPLATE, chestPlate(25)));
	public static final RegistryObject<Item> TONE_LEGGINGS = register("item_tone_leggings", () -> new Armor_Tone(CMArmorMaterial.TONE, ArmorItem.Type.LEGGINGS, leggings(25)));
	public static final RegistryObject<Item> TONE_BOOTS = register("item_tone_boots", () -> new Armor_Tone(CMArmorMaterial.TONE, ArmorItem.Type.BOOTS, boots(25)));
	public static final RegistryObject<Item> TONE_BOOTS_KAI = register("item_tone_bootskai", () -> new Armor_ToneKai(CMArmorMaterial.TONE, ArmorItem.Type.BOOTS, boots(25)));

	/* Aircraft carrier */
	public static final RegistryObject<Item> RJ_HELMET = register("item_ryujou_helmet", () -> new Armor_RJ(CMArmorMaterial.RJ, ArmorItem.Type.HELMET, helmet(21)));
	public static final RegistryObject<Item> RJ_CHESTPLATE = register("item_ryujou_chestplate", () -> new Armor_RJ(CMArmorMaterial.RJ, ArmorItem.Type.CHESTPLATE, chestPlate(21)));
	public static final RegistryObject<Item> RJ_LEGGINGS = register("item_ryujou_leggings", () -> new Armor_RJ(CMArmorMaterial.RJ, ArmorItem.Type.LEGGINGS, leggings(21)));
	public static final RegistryObject<Item> RJ_BOOTS = register("item_ryujou_boots", () -> new Armor_RJ(CMArmorMaterial.RJ, ArmorItem.Type.BOOTS, boots(21)));
	public static final RegistryObject<Item> RJ_BOOTS_KAI = register("item_ryujou_bootskai", () -> new Armor_RJKai(CMArmorMaterial.RJ, ArmorItem.Type.BOOTS, boots(21)));

	public static final RegistryObject<Item> ZUIHOU_HELMET = register("item_zuihou_helmet", () -> new Armor_Zuihou(CMArmorMaterial.ZUIHOU, ArmorItem.Type.HELMET, helmet(25)));
	public static final RegistryObject<Item> ZUIHOU_CHESTPLATE = register("item_zuihou_chestplate", () -> new Armor_Zuihou(CMArmorMaterial.ZUIHOU, ArmorItem.Type.CHESTPLATE, chestPlate(25)));
	public static final RegistryObject<Item> ZUIHOU_LEGGINGS = register("item_zuihou_leggings", () -> new Armor_Zuihou(CMArmorMaterial.ZUIHOU, ArmorItem.Type.LEGGINGS, leggings(25)));
	public static final RegistryObject<Item> ZUIHOU_BOOTS = register("item_zuihou_boots", () -> new Armor_Zuihou(CMArmorMaterial.ZUIHOU, ArmorItem.Type.BOOTS, boots(25)));
	public static final RegistryObject<Item> ZUIHOU_BOOTS_KAI = register("item_zuihou_bootskai", () -> new Armor_ZuihouKai(CMArmorMaterial.ZUIHOU, ArmorItem.Type.BOOTS, boots(25)));

	public static final RegistryObject<Item> AKAGI_HELMET = register("item_akagi_helmet", () -> new Armor_Carrier(CMArmorMaterial.AKAGI, ArmorItem.Type.HELMET, helmet(29)));
	public static final RegistryObject<Item> AKAGI_CHESTPLATE = register("item_akagi_chestplate", () -> new Armor_Carrier(CMArmorMaterial.AKAGI, ArmorItem.Type.CHESTPLATE, chestPlate(29)));
	public static final RegistryObject<Item> AKAGI_LEGGINGS = register("item_akagi_leggings", () -> new Armor_Carrier(CMArmorMaterial.AKAGI, ArmorItem.Type.LEGGINGS, leggings(29)));
	public static final RegistryObject<Item> AKAGI_BOOTS = register("item_akagi_boots", () -> new Armor_Carrier(CMArmorMaterial.AKAGI, ArmorItem.Type.BOOTS, boots(29)));
	public static final RegistryObject<Item> AKAGI_BOOTS_KAI = register("item_akagi_bootskai", () -> new Armor_CarrierKai(CMArmorMaterial.AKAGI, ArmorItem.Type.BOOTS, boots(29)));

	public static final RegistryObject<Item> KAGA_HELMET = register("item_kaga_helmet", () -> new Armor_Carrier(CMArmorMaterial.KAGA, ArmorItem.Type.HELMET, helmet(30)));
	public static final RegistryObject<Item> KAGA_CHESTPLATE = register("item_kaga_chestplate", () -> new Armor_Carrier(CMArmorMaterial.KAGA, ArmorItem.Type.CHESTPLATE, chestPlate(30)));
	public static final RegistryObject<Item> KAGA_LEGGINGS = register("item_kaga_leggings", () -> new Armor_Carrier(CMArmorMaterial.KAGA, ArmorItem.Type.LEGGINGS, leggings(30)));
	public static final RegistryObject<Item> KAGA_BOOTS = register("item_kaga_boots", () -> new Armor_Carrier(CMArmorMaterial.KAGA, ArmorItem.Type.BOOTS, boots(30)));
	public static final RegistryObject<Item> KAGA_BOOTS_KAI = register("item_kaga_bootskai", () -> new Armor_CarrierKai(CMArmorMaterial.KAGA, ArmorItem.Type.BOOTS, boots(30)));

	/* Battleship */
	public static final RegistryObject<Item> KONGOU_HELMET = register("item_kongou_helmet", () -> new Armor_Battleship(CMArmorMaterial.KONGOU, ArmorItem.Type.HELMET, helmet(33)));
	public static final RegistryObject<Item> KONGOU_CHESTPLATE = register("item_kongou_chestplate", () -> new Armor_Battleship(CMArmorMaterial.KONGOU, ArmorItem.Type.CHESTPLATE, chestPlate(33)));
	public static final RegistryObject<Item> KONGOU_LEGGINGS = register("item_kongou_leggings", () -> new Armor_Battleship(CMArmorMaterial.KONGOU, ArmorItem.Type.LEGGINGS, leggings(33)));
	public static final RegistryObject<Item> KONGOU_BOOTS = register("item_kongou_boots", () -> new Armor_Battleship(CMArmorMaterial.KONGOU, ArmorItem.Type.BOOTS, boots(33)));
	public static final RegistryObject<Item> KONGOU_BOOTS_KAI = register("item_kongou_bootskai", () -> new Armor_BattleshipKai(CMArmorMaterial.KONGOU, ArmorItem.Type.BOOTS, boots(33)));

	public static final RegistryObject<Item> FUSOU_HELMET = register("item_fusou_helmet", () -> new Armor_Battleship(CMArmorMaterial.FUSOU, ArmorItem.Type.HELMET, helmet(33)));
	public static final RegistryObject<Item> FUSOU_CHESTPLATE = register("item_fusou_chestplate", () -> new Armor_Battleship(CMArmorMaterial.FUSOU, ArmorItem.Type.CHESTPLATE, chestPlate(33)));
	public static final RegistryObject<Item> FUSOU_LEGGINGS = register("item_fusou_leggings", () -> new Armor_Battleship(CMArmorMaterial.FUSOU, ArmorItem.Type.LEGGINGS, leggings(33)));
	public static final RegistryObject<Item> FUSOU_BOOTS = register("item_fusou_boots", () -> new Armor_Battleship(CMArmorMaterial.FUSOU, ArmorItem.Type.BOOTS, boots(33)));
	public static final RegistryObject<Item> FUSOU_BOOTS_KAI = register("item_fusou_bootskai", () -> new Armor_BattleshipKai(CMArmorMaterial.FUSOU, ArmorItem.Type.BOOTS, boots(33)));
	
	public static final RegistryObject<Item> ISE_HELMET = register("item_ise_helmet", () -> new Armor_Ise(CMArmorMaterial.ISE, ArmorItem.Type.HELMET, helmet(32)));
	public static final RegistryObject<Item> ISE_CHESTPLATE = register("item_ise_chestplate", () -> new Armor_Ise(CMArmorMaterial.ISE, ArmorItem.Type.CHESTPLATE, chestPlate(32)));
	public static final RegistryObject<Item> ISE_LEGGINGS = register("item_ise_leggings", () -> new Armor_Ise(CMArmorMaterial.ISE, ArmorItem.Type.LEGGINGS, leggings(32)));
	public static final RegistryObject<Item> ISE_BOOTS = register("item_ise_boots", () -> new Armor_Ise(CMArmorMaterial.ISE, ArmorItem.Type.BOOTS, boots(32)));
	public static final RegistryObject<Item> ISE_BOOTS_KAI = register("item_ise_bootskai", () -> new Armor_IseKai(CMArmorMaterial.ISE, ArmorItem.Type.BOOTS, boots(32)));

	public static final RegistryObject<Item> NAGATO_HELMET = register("item_nagato_helmet", () -> new Armor_Nagato(CMArmorMaterial.NAGATO, ArmorItem.Type.HELMET, helmet(35)));
	public static final RegistryObject<Item> NAGATO_CHESTPLATE = register("item_nagato_chestplate", () -> new Armor_Nagato(CMArmorMaterial.NAGATO, ArmorItem.Type.CHESTPLATE, chestPlate(35)));
	public static final RegistryObject<Item> NAGATO_LEGGINGS = register("item_nagato_leggings", () -> new Armor_Nagato(CMArmorMaterial.NAGATO, ArmorItem.Type.LEGGINGS, leggings(35)));
	public static final RegistryObject<Item> NAGATO_BOOTS = register("item_nagato_boots", () -> new Armor_Nagato(CMArmorMaterial.NAGATO, ArmorItem.Type.BOOTS, boots(35)));
	public static final RegistryObject<Item> NAGATO_BOOTS_KAI = register("item_nagato_bootskai", () -> new Armor_NagatoKai(CMArmorMaterial.NAGATO, ArmorItem.Type.BOOTS, boots(35)));

	/* Submarine */
	public static final RegistryObject<Item> I168_HELMET = register("item_i168_helmet", () -> new Armor_I168(CMArmorMaterial.I168, ArmorItem.Type.HELMET, helmet(7)));
	public static final RegistryObject<Item> I168_CHESTPLATE = register("item_i168_chestplate", () -> new Armor_I168(CMArmorMaterial.I168, ArmorItem.Type.CHESTPLATE, chestPlate(7)));
	public static final RegistryObject<Item> I168_LEGGINGS = register("item_i168_leggings", () -> new Armor_I168(CMArmorMaterial.I168, ArmorItem.Type.LEGGINGS, leggings(7)));
	public static final RegistryObject<Item> I168_BOOTS = register("item_i168_boots", () -> new Armor_I168(CMArmorMaterial.I168, ArmorItem.Type.BOOTS, boots(7)));

	public static final RegistryObject<Item> I401_HELMET = register("item_i401_helmet", () -> new Armor_I401(CMArmorMaterial.I401, ArmorItem.Type.HELMET, helmet(8)));
	public static final RegistryObject<Item> I401_CHESTPLATE = register("item_i401_chestplate", () -> new Armor_I401(CMArmorMaterial.I401, ArmorItem.Type.CHESTPLATE, chestPlate(8)));
	public static final RegistryObject<Item> I401_LEGGINGS = register("item_i401_leggings", () -> new Armor_I401(CMArmorMaterial.I401, ArmorItem.Type.LEGGINGS, leggings(8)));
	public static final RegistryObject<Item> I401_BOOTS = register( "item_i401_boots", () -> new Armor_I401(CMArmorMaterial.I401, ArmorItem.Type.BOOTS, boots(8)));

	public static final RegistryObject<Item> I13_HELMET = register("item_i13_helmet", () -> new Armor_I13(CMArmorMaterial.I13, ArmorItem.Type.HELMET, helmet(9)));
	public static final RegistryObject<Item> I13_CHESTPLATE = register("item_i13_chestplate", () -> new Armor_I13(CMArmorMaterial.I13, ArmorItem.Type.CHESTPLATE, chestPlate(9)));
	public static final RegistryObject<Item> I13_LEGGINGS = register("item_i13_leggings", () -> new Armor_I13(CMArmorMaterial.I13, ArmorItem.Type.LEGGINGS, leggings(9)));
	public static final RegistryObject<Item> I13_BOOTS = register( "item_i13_boots", () -> new Armor_I13(CMArmorMaterial.I13, ArmorItem.Type.BOOTS, boots(9)));

	public static final RegistryObject<Item> RO500_HELMET = register("item_ro500_helmet", () -> new Armor_Ro500(CMArmorMaterial.RO500, ArmorItem.Type.HELMET, helmet(6)));
	public static final RegistryObject<Item> RO500_CHESTPLATE = register("item_ro500_chestplate", () -> new Armor_Ro500(CMArmorMaterial.RO500, ArmorItem.Type.CHESTPLATE, chestPlate(6)));
	public static final RegistryObject<Item> RO500_LEGGINGS = register("item_ro500_leggings", () -> new Armor_Ro500(CMArmorMaterial.RO500, ArmorItem.Type.LEGGINGS, leggings(6)));
	public static final RegistryObject<Item> RO500_BOOTS = register( "item_ro500_boots", () -> new Armor_Ro500(CMArmorMaterial.RO500, ArmorItem.Type.BOOTS, boots(6)));

	
	/* Share variables */
	private static Item.Properties helmet(int i) {
		return new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(i));
	}
	
	private static Item.Properties chestPlate(int i) {
		return new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(i));
	}
	
	private static Item.Properties leggings(int i) {
		return new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(i));
	}
	
	private static Item.Properties boots(int i) {
		return new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(i));
	}
	
	///* Register *///
	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}
}
