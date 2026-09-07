package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

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
import com.ayutaki.chinjufumod.items.armor.MaterialArmor_CM;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_Armor {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	/* Destroyer */
	public static final DeferredItem<Item> FUBUKI_HELMET = register("item_fubuki_helmet", (props) -> new Armor_Destroyer(MaterialArmor_CM.FUBUKI, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> FUBUKI_CHESTPLATE = register("item_fubuki_chestplate", (props) -> new Armor_Destroyer(MaterialArmor_CM.FUBUKI, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> FUBUKI_LEGGINGS = register("item_fubuki_leggings", (props) -> new Armor_Destroyer(MaterialArmor_CM.FUBUKI, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> FUBUKI_BOOTS = register("item_fubuki_boots", (props) -> new Armor_Destroyer(MaterialArmor_CM.FUBUKI, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> FUBUKI_BOOTS_KAI = register("item_fubuki_bootskai", (props) -> new Armor_DestroyerKai(MaterialArmor_CM.FUBUKI, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> KASUMI_HELMET = register("item_kasumi_helmet", (props) -> new Armor_Kasumi(MaterialArmor_CM.KASUMI, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> KASUMI_CHESTPLATE = register("item_kasumi_chestplate", (props) -> new Armor_Kasumi(MaterialArmor_CM.KASUMI, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> KASUMI_LEGGINGS = register("item_kasumi_leggings", (props) -> new Armor_Kasumi(MaterialArmor_CM.KASUMI, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> KASUMI_BOOTS = register("item_kasumi_boots", (props) -> new Armor_Kasumi(MaterialArmor_CM.KASUMI, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> KASUMI_BOOTS_KAI = register("item_kasumi_bootskai", (props) -> new Armor_KasumiKai(MaterialArmor_CM.KASUMI, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> SHIRATSUYU_HELMET = register("item_shiratsuyu_helmet", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIRATSUYU, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> SHIRATSUYU_CHESTPLATE = register("item_shiratsuyu_chestplate", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIRATSUYU, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> SHIRATSUYU_LEGGINGS = register("item_shiratsuyu_leggings", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIRATSUYU, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> SHIRATSUYU_BOOTS = register("item_shiratsuyu_boots", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIRATSUYU, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> SHIRATSUYU_BOOTS_KAI = register("item_shiratsuyu_bootskai", (props) -> new Armor_DestroyerKai(MaterialArmor_CM.SHIRATSUYU, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> SHIGURE_HELMET = register("item_shigure_helmet", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIGURE, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> SHIGURE_CHESTPLATE = register("item_shigure_chestplate", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIGURE, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> SHIGURE_LEGGINGS = register("item_shigure_leggings", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIGURE, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> SHIGURE_BOOTS = register("item_shigure_boots", (props) -> new Armor_Destroyer(MaterialArmor_CM.SHIGURE, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> SHIGURE_BOOTS_KAI = register("item_shigure_bootskai", (props) -> new Armor_DestroyerKai(MaterialArmor_CM.SHIGURE, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> AKATSUKI_HELMET = register("item_akatsuki_helmet", (props) -> new Armor_Akatsuki(MaterialArmor_CM.AKATSUKI, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> AKATSUKI_CHESTPLATE = register("item_akatsuki_chestplate", (props) -> new Armor_Akatsuki(MaterialArmor_CM.AKATSUKI, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> AKATSUKI_LEGGINGS = register("item_akatsuki_leggings", (props) -> new Armor_Akatsuki(MaterialArmor_CM.AKATSUKI, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> AKATSUKI_BOOTS = register("item_akatsuki_boots", (props) -> new Armor_Akatsuki(MaterialArmor_CM.AKATSUKI, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> AKATSUKI_BOOTS_KAI = register("item_akatsuki_bootskai", (props) -> new Armor_AkatsukiKai(MaterialArmor_CM.AKATSUKI, ArmorType.BOOTS, props), new Item.Properties());
	
	/* Cruiser */
	public static final DeferredItem<Item> SENDAI_HELMET = register("item_sendai_helmet", (props) -> new Armor_Sendai(MaterialArmor_CM.SENDAI, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> SENDAI_CHESTPLATE = register("item_sendai_chestplate", (props) -> new Armor_Sendai(MaterialArmor_CM.SENDAI, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> SENDAI_LEGGINGS = register("item_sendai_leggings", (props) -> new Armor_Sendai(MaterialArmor_CM.SENDAI, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> SENDAI_BOOTS = register("item_sendai_boots", (props) -> new Armor_Sendai(MaterialArmor_CM.SENDAI, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> SENDAI_BOOTS_KAI = register("item_sendai_bootskai", (props) -> new Armor_SendaiKai(MaterialArmor_CM.SENDAI, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> YURA_HELMET = register("item_yura_helmet", (props) -> new Armor_Yura(MaterialArmor_CM.YURA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> YURA_CHESTPLATE = register("item_yura_chestplate", (props) -> new Armor_Yura(MaterialArmor_CM.YURA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> YURA_LEGGINGS = register("item_yura_leggings", (props) -> new Armor_Yura(MaterialArmor_CM.YURA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> YURA_BOOTS = register("item_yura_boots", (props) -> new Armor_Yura(MaterialArmor_CM.YURA, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> YURA_BOOTS_KAI = register("item_yura_bootskai", (props) -> new Armor_YuraKai(MaterialArmor_CM.YURA, ArmorType.BOOTS, props), new Item.Properties());

	/* Heavy Cruiser */
	public static final DeferredItem<Item> MOGAMI_HELMET = register("item_mogami_helmet", (props) -> new Armor_Mogami(MaterialArmor_CM.MOGAMI, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> MOGAMI_CHESTPLATE = register("item_mogami_chestplate", (props) -> new Armor_Mogami(MaterialArmor_CM.MOGAMI, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> MOGAMI_LEGGINGS = register("item_mogami_leggings", (props) -> new Armor_Mogami(MaterialArmor_CM.MOGAMI, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> MOGAMI_BOOTS = register("item_mogami_boots", (props) -> new Armor_Mogami(MaterialArmor_CM.MOGAMI, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> MOGAMI_BOOTS_KAI = register("item_mogami_bootskai", (props) -> new Armor_MogamiKai(MaterialArmor_CM.MOGAMI, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> TONE_HELMET = register("item_tone_helmet", (props) -> new Armor_Tone(MaterialArmor_CM.TONE, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> TONE_CHESTPLATE = register("item_tone_chestplate", (props) -> new Armor_Tone(MaterialArmor_CM.TONE, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> TONE_LEGGINGS = register("item_tone_leggings", (props) -> new Armor_Tone(MaterialArmor_CM.TONE, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> TONE_BOOTS = register("item_tone_boots", (props) -> new Armor_Tone(MaterialArmor_CM.TONE, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> TONE_BOOTS_KAI = register("item_tone_bootskai", (props) -> new Armor_ToneKai(MaterialArmor_CM.TONE, ArmorType.BOOTS, props), new Item.Properties());

	/* Aircraft carrier */
	public static final DeferredItem<Item> RJ_HELMET = register("item_ryujou_helmet", (props) -> new Armor_RJ(MaterialArmor_CM.RJ, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_CHESTPLATE = register("item_ryujou_chestplate", (props) -> new Armor_RJ(MaterialArmor_CM.RJ, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_LEGGINGS = register("item_ryujou_leggings", (props) -> new Armor_RJ(MaterialArmor_CM.RJ, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_BOOTS = register("item_ryujou_boots", (props) -> new Armor_RJ(MaterialArmor_CM.RJ, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> RJ_BOOTS_KAI = register("item_ryujou_bootskai", (props) -> new Armor_RJKai(MaterialArmor_CM.RJ, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> ZUIHOU_HELMET = register("item_zuihou_helmet", (props) -> new Armor_Zuihou(MaterialArmor_CM.ZUIHOU, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> ZUIHOU_CHESTPLATE = register("item_zuihou_chestplate", (props) -> new Armor_Zuihou(MaterialArmor_CM.ZUIHOU, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> ZUIHOU_LEGGINGS = register("item_zuihou_leggings", (props) -> new Armor_Zuihou(MaterialArmor_CM.ZUIHOU, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> ZUIHOU_BOOTS = register("item_zuihou_boots", (props) -> new Armor_Zuihou(MaterialArmor_CM.ZUIHOU, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> ZUIHOU_BOOTS_KAI = register("item_zuihou_bootskai", (props) -> new Armor_ZuihouKai(MaterialArmor_CM.ZUIHOU, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> AKAGI_HELMET = register("item_akagi_helmet", (props) -> new Armor_Carrier(MaterialArmor_CM.AKAGI, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> AKAGI_CHESTPLATE = register("item_akagi_chestplate", (props) -> new Armor_Carrier(MaterialArmor_CM.AKAGI, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> AKAGI_LEGGINGS = register("item_akagi_leggings", (props) -> new Armor_Carrier(MaterialArmor_CM.AKAGI, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> AKAGI_BOOTS = register("item_akagi_boots", (props) -> new Armor_Carrier(MaterialArmor_CM.AKAGI, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> AKAGI_BOOTS_KAI = register("item_akagi_bootskai", (props) -> new Armor_CarrierKai(MaterialArmor_CM.AKAGI, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> KAGA_HELMET = register("item_kaga_helmet", (props) -> new Armor_Carrier(MaterialArmor_CM.KAGA, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> KAGA_CHESTPLATE = register("item_kaga_chestplate", (props) -> new Armor_Carrier(MaterialArmor_CM.KAGA, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> KAGA_LEGGINGS = register("item_kaga_leggings", (props) -> new Armor_Carrier(MaterialArmor_CM.KAGA, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> KAGA_BOOTS = register("item_kaga_boots", (props) -> new Armor_Carrier(MaterialArmor_CM.KAGA, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> KAGA_BOOTS_KAI = register("item_kaga_bootskai", (props) -> new Armor_CarrierKai(MaterialArmor_CM.KAGA, ArmorType.BOOTS, props), new Item.Properties());

	/* Battleship */
	public static final DeferredItem<Item> KONGOU_HELMET = register("item_kongou_helmet", (props) -> new Armor_Battleship(MaterialArmor_CM.KONGOU, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> KONGOU_CHESTPLATE = register("item_kongou_chestplate", (props) -> new Armor_Battleship(MaterialArmor_CM.KONGOU, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> KONGOU_LEGGINGS = register("item_kongou_leggings", (props) -> new Armor_Battleship(MaterialArmor_CM.KONGOU, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> KONGOU_BOOTS = register("item_kongou_boots", (props) -> new Armor_Battleship(MaterialArmor_CM.KONGOU, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> KONGOU_BOOTS_KAI = register("item_kongou_bootskai", (props) -> new Armor_BattleshipKai(MaterialArmor_CM.KONGOU, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> FUSOU_HELMET = register("item_fusou_helmet", (props) -> new Armor_Battleship(MaterialArmor_CM.FUSOU, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> FUSOU_CHESTPLATE = register("item_fusou_chestplate", (props) -> new Armor_Battleship(MaterialArmor_CM.FUSOU, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> FUSOU_LEGGINGS = register("item_fusou_leggings", (props) -> new Armor_Battleship(MaterialArmor_CM.FUSOU, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> FUSOU_BOOTS = register("item_fusou_boots", (props) -> new Armor_Battleship(MaterialArmor_CM.FUSOU, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> FUSOU_BOOTS_KAI = register("item_fusou_bootskai", (props) -> new Armor_BattleshipKai(MaterialArmor_CM.FUSOU, ArmorType.BOOTS, props), new Item.Properties());
	
	public static final DeferredItem<Item> ISE_HELMET = register("item_ise_helmet", (props) -> new Armor_Ise(MaterialArmor_CM.ISE, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> ISE_CHESTPLATE = register("item_ise_chestplate", (props) -> new Armor_Ise(MaterialArmor_CM.ISE, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> ISE_LEGGINGS = register("item_ise_leggings", (props) -> new Armor_Ise(MaterialArmor_CM.ISE, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> ISE_BOOTS = register("item_ise_boots", (props) -> new Armor_Ise(MaterialArmor_CM.ISE, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> ISE_BOOTS_KAI = register("item_ise_bootskai", (props) -> new Armor_IseKai(MaterialArmor_CM.ISE, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> NAGATO_HELMET = register("item_nagato_helmet", (props) -> new Armor_Nagato(MaterialArmor_CM.NAGATO, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> NAGATO_CHESTPLATE = register("item_nagato_chestplate", (props) -> new Armor_Nagato(MaterialArmor_CM.NAGATO, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> NAGATO_LEGGINGS = register("item_nagato_leggings", (props) -> new Armor_Nagato(MaterialArmor_CM.NAGATO, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> NAGATO_BOOTS = register("item_nagato_boots", (props) -> new Armor_Nagato(MaterialArmor_CM.NAGATO, ArmorType.BOOTS, props), new Item.Properties());
	public static final DeferredItem<Item> NAGATO_BOOTS_KAI = register("item_nagato_bootskai", (props) -> new Armor_NagatoKai(MaterialArmor_CM.NAGATO, ArmorType.BOOTS, props), new Item.Properties());

	/* Submarine */
	public static final DeferredItem<Item> I168_HELMET = register("item_i168_helmet", (props) -> new Armor_I168(MaterialArmor_CM.I168, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> I168_CHESTPLATE = register("item_i168_chestplate", (props) -> new Armor_I168(MaterialArmor_CM.I168, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> I168_LEGGINGS = register("item_i168_leggings", (props) -> new Armor_I168(MaterialArmor_CM.I168, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> I168_BOOTS = register("item_i168_boots", (props) -> new Armor_I168(MaterialArmor_CM.I168, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> I401_HELMET = register("item_i401_helmet", (props) -> new Armor_I401(MaterialArmor_CM.I401, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> I401_CHESTPLATE = register("item_i401_chestplate", (props) -> new Armor_I401(MaterialArmor_CM.I401, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> I401_LEGGINGS = register("item_i401_leggings", (props) -> new Armor_I401(MaterialArmor_CM.I401, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> I401_BOOTS = register( "item_i401_boots", (props) -> new Armor_I401(MaterialArmor_CM.I401, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> I13_HELMET = register("item_i13_helmet", (props) -> new Armor_I13(MaterialArmor_CM.I13, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> I13_CHESTPLATE = register("item_i13_chestplate", (props) -> new Armor_I13(MaterialArmor_CM.I13, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> I13_LEGGINGS = register("item_i13_leggings", (props) -> new Armor_I13(MaterialArmor_CM.I13, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> I13_BOOTS = register( "item_i13_boots", (props) -> new Armor_I13(MaterialArmor_CM.I13, ArmorType.BOOTS, props), new Item.Properties());

	public static final DeferredItem<Item> RO500_HELMET = register("item_ro500_helmet", (props) -> new Armor_Ro500(MaterialArmor_CM.RO500, ArmorType.HELMET, props), new Item.Properties());
	public static final DeferredItem<Item> RO500_CHESTPLATE = register("item_ro500_chestplate", (props) -> new Armor_Ro500(MaterialArmor_CM.RO500, ArmorType.CHESTPLATE, props), new Item.Properties());
	public static final DeferredItem<Item> RO500_LEGGINGS = register("item_ro500_leggings", (props) -> new Armor_Ro500(MaterialArmor_CM.RO500, ArmorType.LEGGINGS, props), new Item.Properties());
	public static final DeferredItem<Item> RO500_BOOTS = register( "item_ro500_boots", (props) -> new Armor_Ro500(MaterialArmor_CM.RO500, ArmorType.BOOTS, props), new Item.Properties());

	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
