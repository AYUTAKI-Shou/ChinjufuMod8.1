package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.kansaiki.Fuel_KK;
import com.ayutaki.chinjufumod.items.kansaiki.Gyorai61cm;
import com.ayutaki.chinjufumod.items.kansaiki.KB_F4U;
import com.ayutaki.chinjufumod.items.kansaiki.KB_Ju87;
import com.ayutaki.chinjufumod.items.kansaiki.KB_Re2001;
import com.ayutaki.chinjufumod.items.kansaiki.KB_SBD;
import com.ayutaki.chinjufumod.items.kansaiki.KB_Suisei;
import com.ayutaki.chinjufumod.items.kansaiki.KB_Type99;
import com.ayutaki.chinjufumod.items.kansaiki.KB_TypeZero;
import com.ayutaki.chinjufumod.items.kansaiki.KK_Barracuda;
import com.ayutaki.chinjufumod.items.kansaiki.KK_Mosquito;
import com.ayutaki.chinjufumod.items.kansaiki.KK_Ryusei;
import com.ayutaki.chinjufumod.items.kansaiki.KK_Swordfish;
import com.ayutaki.chinjufumod.items.kansaiki.KK_TBF;
import com.ayutaki.chinjufumod.items.kansaiki.KK_Tenzan;
import com.ayutaki.chinjufumod.items.kansaiki.KK_Type97;
import com.ayutaki.chinjufumod.items.kansaiki.SB_Seiran;
import com.ayutaki.chinjufumod.items.kansaiki.SB_Zuiun;
import com.ayutaki.chinjufumod.items.sakuteki.Device_Radar;
import com.ayutaki.chinjufumod.items.sakuteki.Device_Sonar;
import com.ayutaki.chinjufumod.items.sakuteki.SouganKyou;
import com.ayutaki.chinjufumod.items.weapon.Ammo_Kijyuu;
import com.ayutaki.chinjufumod.items.weapon.Ammo_Large;
import com.ayutaki.chinjufumod.items.weapon.Ammo_Medium;
import com.ayutaki.chinjufumod.items.weapon.Ammo_Small;
import com.ayutaki.chinjufumod.items.weapon.Anchor;
import com.ayutaki.chinjufumod.items.weapon.CMTiers;
import com.ayutaki.chinjufumod.items.weapon.FirstAid;
import com.ayutaki.chinjufumod.items.weapon.KoukakuHou100;
import com.ayutaki.chinjufumod.items.weapon.RensouHou127;
import com.ayutaki.chinjufumod.items.weapon.RensouHou155;
import com.ayutaki.chinjufumod.items.weapon.RensouHou203;
import com.ayutaki.chinjufumod.items.weapon.RensouHou203SKC34;
import com.ayutaki.chinjufumod.items.weapon.RensouHou356;
import com.ayutaki.chinjufumod.items.weapon.RensouHou356S3;
import com.ayutaki.chinjufumod.items.weapon.RensouHou380;
import com.ayutaki.chinjufumod.items.weapon.RensouHou410;
import com.ayutaki.chinjufumod.items.weapon.Shield_CM;
import com.ayutaki.chinjufumod.items.weapon.ShigureHou;
import com.ayutaki.chinjufumod.items.weapon.Sword_CM;
import com.ayutaki.chinjufumod.items.weapon.Triple_Kijyuu;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_Weapon {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	public static final DeferredItem<Item> AMMUNITION_L = register("item_ammunition_kc", Ammo_Large::new, new Item.Properties());
	public static final DeferredItem<Item> AMMUNITION_M = register("item_ammunition_medium", Ammo_Medium::new, new Item.Properties());
	public static final DeferredItem<Item> AMMUNITION_S = register("item_ammunition_small", Ammo_Small::new, new Item.Properties());
	public static final DeferredItem<Item> AMMUNITION_K = register("item_ammunition_kijyuu", Ammo_Kijyuu::new, new Item.Properties());
	public static final DeferredItem<Item> KK_FUEL = register("item_kk_fuel", Fuel_KK::new, new Item.Properties());
	
	/* durability + 100 */
	public static final DeferredItem<Item> RENSOUHOU_127 = register("item_rensouhou127", RensouHou127::new, new Item.Properties().durability(1100));
	public static final DeferredItem<Item> SHIGUREHOU = register("item_shigurehou", ShigureHou::new, new Item.Properties().durability(1100));
	public static final DeferredItem<Item> KOUKAKUHOU_100 = register("item_koukakuhou100", KoukakuHou100::new, new Item.Properties().durability(1100));

	public static final DeferredItem<Item> RENSOUHOU_155 = register("item_rensouhou155", RensouHou155::new, new Item.Properties().durability(2960));
	public static final DeferredItem<Item> RENSOUHOU_203 = register("item_rensouhou203", RensouHou203::new, new Item.Properties().durability(2600));
	public static final DeferredItem<Item> RENSOUHOU_SKC = register("item_rensouhou203_skc34", RensouHou203SKC34::new, new Item.Properties().durability(2100));

	public static final DeferredItem<Item> RENSOUHOU_356 = register("item_rensouhou356", RensouHou356::new, new Item.Properties().durability(5100));
	public static final DeferredItem<Item> RENSOUHOU_356S3 = register("item_rensouhou356_s3", RensouHou356S3::new, new Item.Properties().durability(4850));
	public static final DeferredItem<Item> RENSOUHOU_380 = register("item_rensouhou380", RensouHou380::new, new Item.Properties().durability(4600));
	public static final DeferredItem<Item> RENSOUHOU_410 = register("item_rensouhou410", RensouHou410::new, new Item.Properties().durability(5100));

	public static final DeferredItem<Item> KIJYUU = register("item_3rensou_kijyuu", Triple_Kijyuu::new, new Item.Properties().durability(900));
	
	/* KANSAIKI */
	public static final DeferredItem<Item> TYPE97KK = register("item_kk_type97", KK_Type97::new, new Item.Properties().durability(1100));
	public static final DeferredItem<Item> TENZAN = register("item_kk_tenzan", KK_Tenzan::new, new Item.Properties().durability(1600));
	public static final DeferredItem<Item> RYUSEI = register("item_kk_ryusei", KK_Ryusei::new, new Item.Properties().durability(2100));
	public static final DeferredItem<Item> SWORDFISH = register("item_kk_swordfish", KK_Swordfish::new, new Item.Properties().durability(1100));
	public static final DeferredItem<Item> BARRACUDA = register("item_kk_barracuda", KK_Barracuda::new, new Item.Properties().durability(1700));
	public static final DeferredItem<Item> MOSQUITO = register("item_kk_mosquito", KK_Mosquito::new, new Item.Properties().durability(1300)); //wooden
	public static final DeferredItem<Item> TBF = register("item_kk_tbf", KK_TBF::new, new Item.Properties().durability(1900));
	
	public static final DeferredItem<Item> TYPE99 = register("item_kb_type99", KB_Type99::new, new Item.Properties().durability(1100));
	public static final DeferredItem<Item> SUISEI = register("item_kb_suisei", KB_Suisei::new, new Item.Properties().durability(1600));
	public static final DeferredItem<Item> TYPEZERO = register("item_kb_typezero", KB_TypeZero::new, new Item.Properties().durability(2100));
	public static final DeferredItem<Item> RE2001 = register("item_kb_re2001", KB_Re2001::new, new Item.Properties().durability(1300));
	public static final DeferredItem<Item> JU87 = register("item_kb_ju87", KB_Ju87::new, new Item.Properties().durability(1400)); //1400
	public static final DeferredItem<Item> SBD = register("item_kb_sbd", KB_SBD::new, new Item.Properties().durability(1600));
	public static final DeferredItem<Item> F4U = register("item_kb_f4u", KB_F4U::new, new Item.Properties().durability(2100));

	public static final DeferredItem<Item> ZUIUN = register("item_sb_zuiun", SB_Zuiun::new, new Item.Properties().durability(1100));
	public static final DeferredItem<Item> SEIRAN = register("item_sb_seiran", SB_Seiran::new, new Item.Properties().durability(1600));
	public static final DeferredItem<Item> GYORAI_61cm = register("item_gyorai_61cm", Gyorai61cm::new, new Item.Properties());

	public static final DeferredItem<Item> SHIELD_kuchiku = register("item_shield_kuchiku", Shield_CM::new, new Item.Properties().durability(1108));
	public static final DeferredItem<Item> SHIELD_yura = register("item_shield_yura", Shield_CM::new, new Item.Properties().durability(1780));
	public static final DeferredItem<Item> SHIELD_mogami = register("item_shield_mogami", Shield_CM::new, new Item.Properties().durability(2452));
	public static final DeferredItem<Item> SHIELD_kongou = register("item_shield_kongou", Shield_CM::new, new Item.Properties().durability(3124));

	public static final DeferredItem<Item> ANCHOR = register("item_anchor", (props) -> new Anchor(CMTiers.ANCHOR, 3.0F, -2.4F, props), new Item.Properties());
	public static final DeferredItem<Item> DAMECON = register("item_kit_firstaid", FirstAid::new, new Item.Properties());
	public static final DeferredItem<Item> MEGAMI = register("item_kit_goddess", FirstAid::new, new Item.Properties());
	public static final DeferredItem<Item> DEVICE_RADER = register("item_device_radar", Device_Radar::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> DEVICE_SONAR = register("item_device_sonar", Device_Sonar::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> SOUGANKYOU = register("item_binoculars", SouganKyou::new, new Item.Properties().stacksTo(1));

	public static final DeferredItem<Item> SWORD_sakura = register("item_sword_sakura", (props) -> new Sword_CM(CMTiers.SAKURA, 3.0F, -2.4F, props), new Item.Properties());
	public static final DeferredItem<Item> SWORD_ichoh = register("item_sword_ichoh", (props) -> new Sword_CM(CMTiers.ICHOH, 3.0F, -2.4F, props), new Item.Properties());
	public static final DeferredItem<Item> SWORD_kaede = register("item_sword_kaede", (props) -> new Sword_CM(CMTiers.KAEDE, 3.0F, -2.4F, props), new Item.Properties());
	
	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
