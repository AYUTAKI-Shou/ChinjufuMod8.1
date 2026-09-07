package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;
import com.ayutaki.chinjufumod.items.color.Hake_Black;
import com.ayutaki.chinjufumod.items.color.Hake_Blue;
import com.ayutaki.chinjufumod.items.color.Hake_Brown;
import com.ayutaki.chinjufumod.items.color.Hake_Cyan;
import com.ayutaki.chinjufumod.items.color.Hake_Gray;
import com.ayutaki.chinjufumod.items.color.Hake_Green;
import com.ayutaki.chinjufumod.items.color.Hake_LightBlue;
import com.ayutaki.chinjufumod.items.color.Hake_LightGray;
import com.ayutaki.chinjufumod.items.color.Hake_Lime;
import com.ayutaki.chinjufumod.items.color.Hake_Magenta;
import com.ayutaki.chinjufumod.items.color.Hake_Orange;
import com.ayutaki.chinjufumod.items.color.Hake_Pink;
import com.ayutaki.chinjufumod.items.color.Hake_Purple;
import com.ayutaki.chinjufumod.items.color.Hake_Red;
import com.ayutaki.chinjufumod.items.color.Hake_White;
import com.ayutaki.chinjufumod.items.color.Hake_Yellow;
import com.ayutaki.chinjufumod.items.fuel.Fuel_100;
import com.ayutaki.chinjufumod.items.fuel.Fuel_150;
import com.ayutaki.chinjufumod.items.fuel.Fuel_200;
import com.ayutaki.chinjufumod.items.fuel.Fuel_300;
import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;
import com.ayutaki.chinjufumod.items.garden.ItemChisel;
import com.ayutaki.chinjufumod.items.garden.ItemKumade;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_Wadeco {
	/* 411 = 354 + (3 * 19) */
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	public static final DeferredItem<Item> GARASUDO = register("block_garasudo", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_SPRU = register("block_garasudo_spruce", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_BIR = register("block_garasudo_birch", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_JUN = register("block_garasudo_jungle", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_ACA = register("block_garasudo_acacia", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_DOAK = register("block_garasudo_darkoak", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_MANGROVE = register("block_garasudo_mangrove", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_CHERRY = register("block_garasudo_cherry", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDO_PALEOAK = register("block_garasudo_paleoak", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDO_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> GARASUDOB = register("block_garasudob", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_SPRU = register("block_garasudob_spruce", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_BIR = register("block_garasudob_birch", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_JUN = register("block_garasudob_jungle", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_ACA = register("block_garasudob_acacia", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_DOAK = register("block_garasudob_darkoak", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_MANGROVE = register("block_garasudob_mangrove", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_CHERRY = register("block_garasudob_cherry", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOB_PALEOAK = register("block_garasudob_paleoak", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOB_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> GARASUDOH = register("block_garasudohalf", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_SPRU = register("block_garasudohalf_spruce", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_BIR = register("block_garasudohalf_birch", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_JUN = register("block_garasudohalf_jungle", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_ACA = register("block_garasudohalf_acacia", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_DOAK = register("block_garasudohalf_darkoak", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_MANGROVE = register("block_garasudohalf_mangrove", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_CHERRY = register("block_garasudohalf_cherry", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GARASUDOH_PALEOAK = register("block_garasudohalf_paleoak", (props) -> new Not_Fuel(Slidedoor_Blocks.GARASUDOH_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUJI = register("block_shouji", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_SPRU = register("block_shouji_spruce", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_BIR = register("block_shouji_birch", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_JUN = register("block_shouji_jungle", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_ACA = register("block_shouji_acacia", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_DOAK = register("block_shouji_darkoak", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_MANGROVE = register("block_shouji_mangrove", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_CHERRY = register("block_shouji_cherry", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_PALEOAK = register("block_shouji_paleoak", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJI_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUJIB = register("block_shoujib", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_SPRU = register("block_shoujib_spruce", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_BIR = register("block_shoujib_birch", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_JUN = register("block_shoujib_jungle", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_ACA = register("block_shoujib_acacia", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_DOAK = register("block_shoujib_darkoak", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_MANGROVE = register("block_shoujib_mangrove", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_CHERRY = register("block_shoujib_cherry", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIB_PALEOAK = register("block_shoujib_paleoak", (props) -> new Fuel_200(Slidedoor_Blocks.SHOUJIB_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUJIH = register("block_shoujihalf", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_SPRU = register("block_shoujihalf_spruce", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_BIR = register("block_shoujihalf_birch", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_JUN = register("block_shoujihalf_jungle", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_ACA = register("block_shoujihalf_acacia", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_DOAK = register("block_shoujihalf_darkoak", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_MANGROVE = register("block_shoujihalf_mangrove", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_CHERRY = register("block_shoujihalf_cherry", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJIH_PALEOAK = register("block_shoujihalf_paleoak", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJIH_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHOUJI_WIN = register("block_shoujih", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_SPRU = register("block_shoujih_spruce", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_SPRU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_BIR = register("block_shoujih_birch", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_BIR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_JUN = register("block_shoujih_jungle", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_JUN.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_ACA = register("block_shoujih_acacia", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_ACA.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_DOAK = register("block_shoujih_darkoak", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_DOAK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_MANGROVE = register("block_shoujih_mangrove", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_MANGROVE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_CHERRY = register("block_shoujih_cherry", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_CHERRY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> SHOUJI_WIN_PALEOAK = register("block_shoujih_paleoak", (props) -> new Fuel_100(Slidedoor_Blocks.SHOUJI_WIN_PALEOAK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> RANMA_oak = register("block_ranma_oak", (props) -> new Fuel_150(Ranma_Blocks.RANMA_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_spruce = register("block_ranma_spru", (props) -> new Fuel_150(Ranma_Blocks.RANMA_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_birch = register("block_ranma_bir", (props) -> new Fuel_150(Ranma_Blocks.RANMA_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_jungle = register("block_ranma_jun", (props) -> new Fuel_150(Ranma_Blocks.RANMA_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_acacia = register("block_ranma_aca", (props) -> new Fuel_150(Ranma_Blocks.RANMA_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_darkoak = register("block_ranma_doak", (props) -> new Fuel_150(Ranma_Blocks.RANMA_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_mangrove = register("block_ranma_mangrove", (props) -> new Fuel_150(Ranma_Blocks.RANMA_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_cherry = register("block_ranma_cherry", (props) -> new Fuel_150(Ranma_Blocks.RANMA_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMA_paleoak = register("block_ranma_paleoak", (props) -> new Fuel_150(Ranma_Blocks.RANMA_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> RANMAB_oak = register("block_ranmab_oak", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_spruce = register("block_ranmab_spru", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_birch = register("block_ranmab_bir", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_jungle = register("block_ranmab_jun", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_acacia = register("block_ranmab_aca", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_darkoak = register("block_ranmab_doak", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_mangrove = register("block_ranmab_mangrove", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_cherry = register("block_ranmab_cherry", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAB_paleoak = register("block_ranmab_paleoak", (props) -> new Fuel_150(Ranma_Blocks.RANMAB_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> RANMAC_oak = register("block_ranmac_oak", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_spruce = register("block_ranmac_spru", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_birch = register("block_ranmac_bir", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_jungle = register("block_ranmac_jun", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_acacia = register("block_ranmac_aca", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_darkoak = register("block_ranmac_doak", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_mangrove = register("block_ranmac_mangrove", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_cherry = register("block_ranmac_cherry", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> RANMAC_paleoak = register("block_ranmac_paleoak", (props) -> new Not_Fuel(Ranma_Blocks.RANMAC_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KANKI_oak = register("block_kanki_oak", (props) -> new Fuel_150(Ranma_Blocks.KANKI_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_spruce = register("block_kanki_spru", (props) -> new Fuel_150(Ranma_Blocks.KANKI_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_birch = register("block_kanki_bir", (props) -> new Fuel_150(Ranma_Blocks.KANKI_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_jungle = register("block_kanki_jun", (props) -> new Fuel_150(Ranma_Blocks.KANKI_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_acacia = register("block_kanki_aca", (props) -> new Fuel_150(Ranma_Blocks.KANKI_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_darkoak = register("block_kanki_doak", (props) -> new Fuel_150(Ranma_Blocks.KANKI_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_mangrove = register("block_kanki_mangrove", (props) -> new Fuel_150(Ranma_Blocks.KANKI_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_cherry = register("block_kanki_cherry", (props) -> new Fuel_150(Ranma_Blocks.KANKI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANKI_paleoak = register("block_kanki_paleoak", (props) -> new Fuel_150(Ranma_Blocks.KANKI_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KOUSHI_oak = register("block_koushi_oak", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_spruce = register("block_koushi_spru", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_birch = register("block_koushi_bir", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_jungle = register("block_koushi_jun", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_acacia = register("block_koushi_aca", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_darkoak = register("block_koushi_doak", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_mangrove = register("block_koushi_mangrove", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_cherry = register("block_koushi_cherry", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHI_paleoak = register("block_koushi_paleoak", (props) -> new Fuel_150(Ranma_Blocks.KOUSHI_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KOUSHIB_oak = register("block_koushib_oak", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_spruce = register("block_koushib_spru", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_birch = register("block_koushib_bir", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_jungle = register("block_koushib_jun", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_acacia = register("block_koushib_aca", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_darkoak = register("block_koushib_doak", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_mangrove = register("block_koushib_mangrove", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_cherry = register("block_koushib_cherry", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KOUSHIB_paleoak = register("block_koushib_paleoak", (props) -> new Fuel_150(Ranma_Blocks.KOUSHIB_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> FUSUMA_white = register("block_fusuma", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_orange = register("block_fusuma_orange", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_magenta = register("block_fusuma_magenta", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_lightb = register("block_fusuma_lightb", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_yellow = register("block_fusuma_yellow", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_lime = register("block_fusuma_lime", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_pink = register("block_fusuma_pink", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_gray = register("block_fusuma_gray", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_lightg = register("block_fusuma_lightg", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_cyan = register("block_fusuma_cyan", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_purple = register("block_fusuma_purple", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_blue = register("block_fusuma_blue", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_brown = register("block_fusuma_brown", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_green = register("block_fusuma_green", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_red = register("block_fusuma_red", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMA_black = register("block_fusuma_black", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMA_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> FUSUMAB_white = register("block_fusumab", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_orange = register("block_fusumab_orange", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_magenta = register("block_fusumab_magenta", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_lightb = register("block_fusumab_lightb", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_yellow = register("block_fusumab_yellow", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_lime = register("block_fusumab_lime", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_pink = register("block_fusumab_pink", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_gray = register("block_fusumab_gray", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_lightg = register("block_fusumab_lightg", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_cyan = register("block_fusumab_cyan", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_purple = register("block_fusumab_purple", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_blue = register("block_fusumab_blue", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_brown = register("block_fusumab_brown", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_green = register("block_fusumab_green", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_red = register("block_fusumab_red", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUSUMAB_black = register("block_fusumab_black", (props) -> new Fuel_200(Slidedoor_Blocks.FUSUMAB_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SUDARE = register("block_sudare_1", (props) -> new Fuel_100(Garden_Blocks.SUDARE.get(), props), new Item.Properties());

	public static final DeferredItem<Item> NOREN_white = register("block_noren_white", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_orange = register("block_noren_orange", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_magenta = register("block_noren_magenta", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_lightb = register("block_noren_lightb", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_yellow = register("block_noren_yellow", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_lime = register("block_noren_lime", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_pink = register("block_noren_pink", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_gray = register("block_noren_gray", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_lightg = register("block_noren_lightg", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_cyan = register("block_noren_cyan", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_purple = register("block_noren_purple", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_blue = register("block_noren_blue", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_brown = register("block_noren_brown", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_green = register("block_noren_green", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_red = register("block_noren_red", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> NOREN_black = register("block_noren_black", (props) -> new Not_Fuel(Ranma_Blocks.NOREN_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WARAZABUTON = register("block_wara_zabuton", (props) -> new Fuel_100(JPChair_Blocks.WARAZABUTON.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ZABUTON_white = register("block_mzabuton_white", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_orange = register("block_mzabuton_orange", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_magenta = register("block_mzabuton_magenta", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_lightb = register("block_mzabuton_lightb", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_yellow = register("block_mzabuton_yellow", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_lime = register("block_mzabuton_lime", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_pink = register("block_mzabuton_pink", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_gray = register("block_mzabuton_gray", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_lightg = register("block_mzabuton_lightg", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_cyan = register("block_mzabuton_cyan", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_purple = register("block_mzabuton_purple", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_blue = register("block_mzabuton_blue", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_brown = register("block_mzabuton_brown", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_green = register("block_mzabuton_green", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_red = register("block_mzabuton_red", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZABUTON_black = register("block_mzabuton_black", (props) -> new Fuel_100(JPChair_Blocks.ZABUTON_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ZAISU_white = register("block_zaisu_white", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_orange = register("block_zaisu_orange", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_magenta = register("block_zaisu_magenta", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_lightb = register("block_zaisu_lightb", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_yellow = register("block_zaisu_yellow", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_lime = register("block_zaisu_lime", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_pink = register("block_zaisu_pink", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_gray = register("block_zaisu_gray", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_lightg = register("block_zaisu_lightg", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_cyan = register("block_zaisu_cyan", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_purple = register("block_zaisu_purple", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_blue = register("block_zaisu_blue", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_brown = register("block_zaisu_brown", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_green = register("block_zaisu_green", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_red = register("block_zaisu_red", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ZAISU_black = register("block_zaisu_black", (props) -> new Fuel_150(JPChair_Blocks.ZAISU_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TATAMI_H = register("block_tatamih", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_white = register("block_tatamih_white", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_orange = register("block_tatamih_orange", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_magenta = register("block_tatamih_magenta", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_lightb = register("block_tatamih_lightb", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_yellow = register("block_tatamih_yellow", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_lime = register("block_tatamih_lime", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_pink = register("block_tatamih_pink", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_gray = register("block_tatamih_gray", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_lightg = register("block_tatamih_lightg", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_cyan = register("block_tatamih_cyan", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_purple = register("block_tatamih_purple", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_blue = register("block_tatamih_blue", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_brown = register("block_tatamih_brown", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_green = register("block_tatamih_green", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_red = register("block_tatamih_red", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_H_black = register("block_tatamih_black", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_H_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TATAMI_HY = register("block_tatamih_y", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_white = register("block_tatamih_y_white", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_orange = register("block_tatamih_y_orange", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_magenta = register("block_tatamih_y_magenta", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_lightb = register("block_tatamih_y_lightb", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_yellow = register("block_tatamih_y_yellow", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_lime = register("block_tatamih_y_lime", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_pink = register("block_tatamih_y_pink", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_gray = register("block_tatamih_y_gray", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_lightg = register("block_tatamih_y_lightg", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_cyan = register("block_tatamih_y_cyan", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_purple = register("block_tatamih_y_purple", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_blue = register("block_tatamih_y_blue", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_brown = register("block_tatamih_y_brown", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_green = register("block_tatamih_y_green", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_red = register("block_tatamih_y_red", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TATAMI_HY_black = register("block_tatamih_y_black", (props) -> new Fuel_150(JPDeco_Blocks.TATAMI_HY_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TAKECUBE = register("block_bamboo_cube", (props) -> new Fuel_200(JPDeco_Blocks.TAKECUBE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKECUBE_Y = register("block_bamboo_y_cube", (props) -> new Fuel_200(JPDeco_Blocks.TAKECUBE_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKECUBE_K = register("block_bamboo_k_cube", (props) -> new Fuel_200(JPDeco_Blocks.TAKECUBE_K.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_ST = register("block_bamboo_stairs", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_ST.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_STY = register("block_bamboo_y_stairs", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_STY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_STK = register("block_bamboo_k_stairs", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_STK.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_SH = register("block_bamboo_slab", (props) -> new Fuel_100(JPDeco_Blocks.TAKE_SH.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_SHY = register("block_bamboo_y_slab", (props) -> new Fuel_100(JPDeco_Blocks.TAKE_SHY.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_SHK = register("block_bamboo_k_slab", (props) -> new Fuel_100(JPDeco_Blocks.TAKE_SHK.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TAKEFENCE = register("block_bamboo_fence", (props) -> new Fuel_150(JPDeco_Blocks.TAKEFENCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEFENCE_Y = register("block_bamboo_y_fence", (props) -> new Fuel_150(JPDeco_Blocks.TAKEFENCE_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEFENCE_K = register("block_bamboo_k_fence", (props) -> new Fuel_150(JPDeco_Blocks.TAKEFENCE_K.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEFENCEGATE = register("block_bamboo_fencegate", (props) -> new Fuel_150(JPDeco_Blocks.TAKEFENCEGATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEFENCEGATE_Y = register("block_bamboo_y_fencegate", (props) -> new Fuel_150(JPDeco_Blocks.TAKEFENCEGATE_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEFENCEGATE_K = register("block_bamboo_k_fencegate", (props) -> new Fuel_150(JPDeco_Blocks.TAKEFENCEGATE_K.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEDOOR = register("block_bamboo_door", (props) -> new Fuel_150(JPDeco_Blocks.TAKEDOOR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEDOOR_Y = register("block_bamboo_y_door", (props) -> new Fuel_150(JPDeco_Blocks.TAKEDOOR_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEDOOR_K = register("block_bamboo_k_door", (props) -> new Fuel_150(JPDeco_Blocks.TAKEDOOR_K.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TAKE_TRAPDOOR = register("block_bamboo_trapdoor", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_TRAPDOOR.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_TRAPDOOR_Y = register("block_bamboo_y_trapdoor", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_TRAPDOOR_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_TRAPDOOR_K = register("block_bamboo_k_trapdoor", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_TRAPDOOR_K.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_PLATE = register("block_bamboo_plate", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_PLATE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_PLATE_Y = register("block_bamboo_y_plate", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_PLATE_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_PLATE_K = register("block_bamboo_k_plate", (props) -> new Fuel_150(JPDeco_Blocks.TAKE_PLATE_K.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_BUTTON = register("block_bamboo_button", (props) -> new Fuel_100(JPDeco_Blocks.TAKE_BUTTON.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_BUTTON_Y = register("block_bamboo_y_button", (props) -> new Fuel_100(JPDeco_Blocks.TAKE_BUTTON_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKE_BUTTON_K = register("block_bamboo_k_button", (props) -> new Fuel_100(JPDeco_Blocks.TAKE_BUTTON_K.get(), props), new Item.Properties());

	public static final DeferredItem<Item> FUTON_white = register("block_futon_c_white", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_orange = register("block_futon_c_orange", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_magenta = register("block_futon_c_magenta", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_lightb = register("block_futon_c_lightb", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_yellow = register("block_futon_c_yellow", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_lime = register("block_futon_c_lime", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_pink = register("block_futon_c_pink", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_gray = register("block_futon_c_gray", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_lightg = register("block_futon_c_lightg", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_cyan = register("block_futon_c_cyan", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_purple = register("block_futon_c_purple", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_blue = register("block_futon_c_blue", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_brown = register("block_futon_c_brown", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_green = register("block_futon_c_green", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_red = register("block_futon_c_red", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> FUTON_black = register("block_futon_c_black", (props) -> new Fuel_150(JPDeco_Blocks.FUTON_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ANDON_white = register("block_andon_white", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_orange = register("block_andon_orange", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_magenta = register("block_andon_magenta", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_lightb = register("block_andon_lightb", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_yellow = register("block_andon_yellow", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_lime = register("block_andon_lime", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_pink = register("block_andon_pink", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_gray = register("block_andon_gray", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_lightg = register("block_andon_lightg", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_cyan = register("block_andon_cyan", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_purple = register("block_andon_purple", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_blue = register("block_andon_blue", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_brown = register("block_andon_brown", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_green = register("block_andon_green", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_red = register("block_andon_red", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ANDON_black = register("block_andon_black", (props) -> new Not_Fuel(JPDeco_Blocks.ANDON_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KASA_white = register("block_mkasa_white", (props) -> new Not_Fuel(Unit_Blocks.KASA_white.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_orange = register("block_mkasa_orange", (props) -> new Not_Fuel(Unit_Blocks.KASA_orange.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_magenta = register("block_mkasa_magenta", (props) -> new Not_Fuel(Unit_Blocks.KASA_magenta.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_lightb = register("block_mkasa_lightb", (props) -> new Not_Fuel(Unit_Blocks.KASA_lightb.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_yellow = register("block_mkasa_yellow", (props) -> new Not_Fuel(Unit_Blocks.KASA_yellow.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_lime = register("block_mkasa_lime", (props) -> new Not_Fuel(Unit_Blocks.KASA_lime.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_pink = register("block_mkasa_pink", (props) -> new Not_Fuel(Unit_Blocks.KASA_pink.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_gray = register("block_mkasa_gray", (props) -> new Not_Fuel(Unit_Blocks.KASA_gray.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_lightg = register("block_mkasa_lightg", (props) -> new Not_Fuel(Unit_Blocks.KASA_lightg.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_cyan = register("block_mkasa_cyan", (props) -> new Not_Fuel(Unit_Blocks.KASA_cyan.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_purple = register("block_mkasa_purple", (props) -> new Not_Fuel(Unit_Blocks.KASA_purple.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_blue = register("block_mkasa_blue", (props) -> new Not_Fuel(Unit_Blocks.KASA_blue.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_brown = register("block_mkasa_brown", (props) -> new Not_Fuel(Unit_Blocks.KASA_brown.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_green = register("block_mkasa_green", (props) -> new Not_Fuel(Unit_Blocks.KASA_green.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_red = register("block_mkasa_red", (props) -> new Not_Fuel(Unit_Blocks.KASA_red.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KASA_black = register("block_mkasa_black", (props) -> new Not_Fuel(Unit_Blocks.KASA_black.get(), props), new Item.Properties());

	public static final DeferredItem<Item> TOBUKURO_S = register("block_tobukuro_spruce", (props) -> new Fuel_300(Slidedoor_Blocks.TOBUKURO_S.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TOBUKUROWIN_S = register("block_tobukurowin_spruce", (props) -> new Fuel_150(Slidedoor_Blocks.TOBUKUROWIN_S.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TOBUKURO = register("block_tobukuro", (props) -> new Fuel_300(Slidedoor_Blocks.TOBUKURO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TOBUKUROWIN = register("block_tobukurowin", (props) -> new Fuel_150(Slidedoor_Blocks.TOBUKUROWIN.get(), props), new Item.Properties());

	public static final DeferredItem<Item> BONSAI_oak = register("block_bonsai_oak", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_oak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_spru = register("block_bonsai_spruce", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_spru.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_bir = register("block_bonsai_birch", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_bir.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_jun = register("block_bonsai_jungle", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_jun.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_aca = register("block_bonsai_acacia", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_aca.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_doak = register("block_bonsai_darkoak", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_doak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_mangrove = register("block_bonsai_mangrove", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_cherry = register("block_bonsai_cherry", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> BONSAI_paleoak = register("block_bonsai_paleoak", (props) -> new Not_Fuel(Garden_Blocks.BONSAI_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KANYOU = register("block_kanyouoak_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_spruce = register("block_kanyouspruce_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_birch = register("block_kanyoubirch_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_jungle = register("block_kanyoujungle_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_acacia = register("block_kanyouacacia_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_darkoak = register("block_kanyoudarkoak_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_mangrove = register("block_kanyoumangrove_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_cherry = register("block_kanyoucherry_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KANYOU_paleoak = register("block_kanyoupaleoak_bot", (props) -> new Not_Fuel(Garden_Blocks.KANYOU_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> IKEGAKI = register("block_low_oak", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_spruce = register("block_low_spruce", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_birch = register("block_low_birch", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_jungle = register("block_low_jungle", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_acacia = register("block_low_acacia", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_darkoak = register("block_low_darkoak", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_mangrove = register("block_low_mangrove", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_cherry = register("block_low_cherry", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKI_paleoak = register("block_low_paleoak", (props) -> new Fuel_150(Garden_Blocks.IKEGAKI_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> IKEGAKILONG = register("block_longoak_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_spruce = register("block_longspruce_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_birch = register("block_longbirch_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_jungle = register("block_longjungle_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_acacia = register("block_longacacia_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_darkoak = register("block_longdarkoak_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_mangrove = register("block_longmangrove_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_cherry = register("block_longcherry_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> IKEGAKILONG_paleoak = register("block_longpaleoak_bot", (props) -> new Fuel_300(Garden_Blocks.IKEGAKILONG_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ITABEI = register("block_itabei", (props) -> new Fuel_200(Garden_Blocks.ITABEI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_spruce = register("block_itabei_spruce", (props) -> new Fuel_200(Garden_Blocks.ITABEI_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_birch = register("block_itabei_birch", (props) -> new Fuel_200(Garden_Blocks.ITABEI_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_jungle = register("block_itabei_jungle", (props) -> new Fuel_200(Garden_Blocks.ITABEI_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_acacia = register("block_itabei_acacia", (props) -> new Fuel_200(Garden_Blocks.ITABEI_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_darkoak = register("block_itabei_darkoak", (props) -> new Fuel_200(Garden_Blocks.ITABEI_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_mangrove = register("block_itabei_mangrove", (props) -> new Fuel_200(Garden_Blocks.ITABEI_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_cherry = register("block_itabei_cherry", (props) -> new Fuel_200(Garden_Blocks.ITABEI_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ITABEI_paleoak = register("block_itabei_paleoak", (props) -> new Fuel_200(Garden_Blocks.ITABEI_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> KIDO = register("block_kido", (props) -> new Fuel_200(Garden_Blocks.KIDO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_spruce = register("block_kido_spruce", (props) -> new Fuel_200(Garden_Blocks.KIDO_spruce.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_birch = register("block_kido_birch", (props) -> new Fuel_200(Garden_Blocks.KIDO_birch.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_jungle = register("block_kido_jungle", (props) -> new Fuel_200(Garden_Blocks.KIDO_jungle.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_acacia = register("block_kido_acacia", (props) -> new Fuel_200(Garden_Blocks.KIDO_acacia.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_darkoak = register("block_kido_darkoak", (props) -> new Fuel_200(Garden_Blocks.KIDO_darkoak.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_mangrove = register("block_kido_mangrove", (props) -> new Fuel_200(Garden_Blocks.KIDO_mangrove.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_cherry = register("block_kido_cherry", (props) -> new Fuel_200(Garden_Blocks.KIDO_cherry.get(), props), new Item.Properties());
	public static final DeferredItem<Item> KIDO_paleoak = register("block_kido_paleoak", (props) -> new Fuel_200(Garden_Blocks.KIDO_paleoak.get(), props), new Item.Properties());

	public static final DeferredItem<Item> SHISHIODOSHI = register("block_shishiodoshi", (props) -> new Not_Fuel(Garden_Blocks.SHISHIODOSHI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHOUZUBACHI = register("block_chouzubachi_kara", (props) -> new Not_Fuel(Garden_Blocks.CHOUZUBACHI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHOUZUBACHI_gra = register("block_chouzu_gra_kara", (props) -> new Not_Fuel(Garden_Blocks.CHOUZUBACHI_gra.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHOUZUBACHI_dio = register("block_chouzu_dio_kara", (props) -> new Not_Fuel(Garden_Blocks.CHOUZUBACHI_dio.get(), props), new Item.Properties());
	public static final DeferredItem<Item> CHOUZUBACHI_and = register("block_chouzu_and_kara", (props) -> new Not_Fuel(Garden_Blocks.CHOUZUBACHI_and.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ISHITOUROU = register("block_ishitourou_stone", (props) -> new Not_Fuel(Garden_Blocks.ISHITOUROU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ISHITOUROU_gra = register("block_ishitourou_gra", (props) -> new Not_Fuel(Garden_Blocks.ISHITOUROU_gra.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ISHITOUROU_dio = register("block_ishitourou_dio", (props) -> new Not_Fuel(Garden_Blocks.ISHITOUROU_dio.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ISHITOUROU_and = register("block_ishitourou_and", (props) -> new Not_Fuel(Garden_Blocks.ISHITOUROU_and.get(), props), new Item.Properties());

	public static final DeferredItem<Item> LONGTOUROU = register("block_longtourou_stone", (props) -> new Not_Fuel(Garden_Blocks.LONGTOUROU.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LONGTOUROU_gra = register("block_longtourou_gra", (props) -> new Not_Fuel(Garden_Blocks.LONGTOUROU_gra.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LONGTOUROU_dio = register("block_longtourou_dio", (props) -> new Not_Fuel(Garden_Blocks.LONGTOUROU_dio.get(), props), new Item.Properties());
	public static final DeferredItem<Item> LONGTOUROU_and = register("block_longtourou_and", (props) -> new Not_Fuel(Garden_Blocks.LONGTOUROU_and.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEAKARI = register("block_takeakari", (props) -> new Fuel_100(Garden_Blocks.TAKEAKARI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEAKARI_Y = register("block_takeakari_y", (props) -> new Fuel_100(Garden_Blocks.TAKEAKARI_Y.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TAKEAKARI_K = register("block_takeakari_k", (props) -> new Fuel_100(Garden_Blocks.TAKEAKARI_K.get(), props), new Item.Properties());

	public static final DeferredItem<Item> WADAIKO = register("block_wadaiko", (props) -> new Fuel_300(JPDeco_Blocks.WADAIKO.get(), props), new Item.Properties());
	public static final DeferredItem<Item> WADAIKO_small = register("block_wadaiko_small", (props) -> new Fuel_150(JPDeco_Blocks.WADAIKO_small.get(), props), new Item.Properties());

	public static final DeferredItem<Item> ENDAI = register("block_mendai", (props) -> new Fuel_300(Unit_Blocks.ENDAI.get(), props), new Item.Properties());
	public static final DeferredItem<Item> ENDAI_r = register("block_mendai_red", (props) -> new Fuel_300(Unit_Blocks.ENDAI_r.get(), props), new Item.Properties());

	public static final DeferredItem<Item> GATE_SPRUCE = register("block_gate_spruce", (props) -> new Not_Fuel(Gate_Blocks.GATE_SPRUCE.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GATE_SPRUCE_B = register("block_gate_spruce_b", (props) -> new Not_Fuel(Gate_Blocks.GATE_SPRUCE_B.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GATE_IRON = register("block_gate_iron", (props) -> new Not_Fuel(Gate_Blocks.GATE_IRON.get(), props), new Item.Properties());
	public static final DeferredItem<Item> GATE_IRONGRILL = register("block_gate_irongrill", (props) -> new Not_Fuel(Gate_Blocks.GATE_IRONGRILL.get(), props), new Item.Properties());
	public static final DeferredItem<Item> TETSUSAKU_BOT = register("block_ironfence_bot", (props) -> new Not_Fuel(Garden_Blocks.TETSUSAKU_BOT.get(), props), new Item.Properties());

	public static final DeferredItem<Item> HAKE = register("item_hake", AddInfo_Item::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_white = register("item_hake_white", Hake_White::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_orange = register("item_hake_orange", Hake_Orange::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_magenta = register("item_hake_magenta", Hake_Magenta::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_lightb = register("item_hake_lightblue", Hake_LightBlue::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_yellow = register("item_hake_yellow", Hake_Yellow::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_lime = register("item_hake_lime", Hake_Lime::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_pink = register("item_hake_pink", Hake_Pink::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_gray = register("item_hake_gray", Hake_Gray::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_lightg = register("item_hake_lightgray", Hake_LightGray::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_cyan = register("item_hake_cyan", Hake_Cyan::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_purple = register("item_hake_purple", Hake_Purple::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_blue = register("item_hake_blue", Hake_Blue::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_brown = register("item_hake_brown", Hake_Brown::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_green = register("item_hake_green", Hake_Green::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_red = register("item_hake_red", Hake_Red::new, new Item.Properties());
	public static final DeferredItem<Item> HAKE_black = register("item_hake_black", Hake_Black::new, new Item.Properties());

	public static final DeferredItem<Item> KUMADE = register("item_kumade", ItemKumade::new, new Item.Properties());
	public static final DeferredItem<Item> NOMI = register("item_chisel", ItemChisel::new, new Item.Properties());

	public static final DeferredItem<Item> MAKIBISHI = register("block_makibishi", (props) -> new Not_Fuel(Garden_Blocks.MAKIBISHI.get(), props), new Item.Properties());


	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
