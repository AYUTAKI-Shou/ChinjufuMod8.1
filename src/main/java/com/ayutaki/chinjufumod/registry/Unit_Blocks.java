package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.unitblock.CafeTable;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai;
import com.ayutaki.chinjufumod.blocks.unitblock.ClothTable;
import com.ayutaki.chinjufumod.blocks.unitblock.ClothTable_Sub;
import com.ayutaki.chinjufumod.blocks.unitblock.Endai;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk;
import com.ayutaki.chinjufumod.blocks.unitblock.TrayLetter;
import com.ayutaki.chinjufumod.blocks.unitblock.UnitDesk;
import com.ayutaki.chinjufumod.blocks.unitblock.Wagasa;
import com.ayutaki.chinjufumod.blocks.unitblock.WrittenBook;
import com.ayutaki.chinjufumod.blocks.unitblock.WrittenMakimono;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Unit_Blocks {
	/* 107 = 86 + (3 * 7) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> UNITDESK = register("block_unitdesk", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_spruce = register("block_unitdesk_spruce", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_birch = register("block_unitdesk_birch", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_jungle = register("block_unitdesk_jungle", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_acacia = register("block_unitdesk_acacia", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_darkoak = register("block_unitdesk_darkoak", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_mangrove = register("block_unitdesk_mangrove", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_cherry = register("block_unitdesk_cherry", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_paleoak = register("block_unitdesk_paleoak", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_sakura = register("block_unitdesk_sakura", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_kaede = register("block_unitdesk_kaede", UnitDesk::new, woodState());
	public static final DeferredBlock<Block> UNITDESK_ichoh = register("block_unitdesk_ichoh", UnitDesk::new, woodState());

	public static final DeferredBlock<Block> CAFETABLE = register("block_cafetable", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_spruce = register("block_cafetable_spruce", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_birch = register("block_cafetable_birch", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_jungle = register("block_cafetable_jungle", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_acacia = register("block_cafetable_acacia", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_darkoak = register("block_cafetable_darkoak", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_mangrove = register("block_cafetable_mangrove", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_cherry = register("block_cafetable_cherry", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_paleoak = register("block_cafetable_paleoak", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_sakura = register("block_cafetable_sakura", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_kaede = register("block_cafetable_kaede", CafeTable::new, woodState());
	public static final DeferredBlock<Block> CAFETABLE_ichoh = register("block_cafetable_ichoh", CafeTable::new, woodState());
	
	public static final DeferredBlock<Block> LOWDESK = register("block_lowdesk", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_spruce = register("block_lowdesk_spruce", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_birch = register("block_lowdesk_birch", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_jungle = register("block_lowdesk_jungle", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_acacia = register("block_lowdesk_acacia", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_darkoak = register("block_lowdesk_darkoak", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_mangrove = register("block_lowdesk_mangrove", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_cherry = register("block_lowdesk_cherry", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_paleoak = register("block_lowdesk_paleoak", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_sakura = register("block_lowdesk_sakura", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_kaede = register("block_lowdesk_kaede", LowDesk::new, woodState());
	public static final DeferredBlock<Block> LOWDESK_ichoh = register("block_lowdesk_ichoh", LowDesk::new, woodState());
	
	public static final DeferredBlock<Block> LETTERTRAY = register("block_lettertray_c", TrayLetter::new, bookState());
	public static final DeferredBlock<Block> FUDETRAY = register("block_fudetray_c", TrayLetter::new, bookState());
	public static final DeferredBlock<Block> WRITTEN_BOOK = register("block_written_book", WrittenBook::new, bookState());
	public static final DeferredBlock<Block> WRITTEN_MAKIMONO = register("block_written_makimono", WrittenMakimono::new, bookState());

	public static final DeferredBlock<Block> CHABUDAI = register("block_chabudai", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_spruce = register("block_chabudai_spruce", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_birch = register("block_chabudai_birch", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_jungle = register("block_chabudai_jungle", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_acacia = register("block_chabudai_acacia", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_darkoak = register("block_chabudai_darkoak", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_mangrove = register("block_chabudai_mangrove", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_cherry = register("block_chabudai_cherry", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_paleoak = register("block_chabudai_paleoak", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_sakura = register("block_chabudai_sakura", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_kaede = register("block_chabudai_kaede", Chabudai::new, woodState());
	public static final DeferredBlock<Block> CHABUDAI_ichoh = register("block_chabudai_ichoh", Chabudai::new, woodState());

	public static final DeferredBlock<Block> KOTATSU = register("block_kotatsu", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_spruce = register("block_kotatsu_spruce", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_birch = register("block_kotatsu_birch", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_jungle = register("block_kotatsu_jungle", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_acacia = register("block_kotatsu_acacia", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_darkoak = register("block_kotatsu_darkoak", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_mangrove = register("block_kotatsu_mangrove", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_cherry = register("block_kotatsu_cherry", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_paleoak = register("block_kotatsu_paleoak", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_sakura = register("block_kotatsu_sakura", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_kaede = register("block_kotatsu_kaede", Kotatsu::new, woodState());
	public static final DeferredBlock<Block> KOTATSU_ichoh = register("block_kotatsu_ichoh", Kotatsu::new, woodState());
	
	public static final DeferredBlock<Block> KASA_white = register("block_mkasa_white", Wagasa::new, wagasaState(MapColor.SNOW));
	public static final DeferredBlock<Block> KASA_orange = register("block_mkasa_orange", Wagasa::new, wagasaState(MapColor.COLOR_ORANGE));
	public static final DeferredBlock<Block> KASA_magenta = register("block_mkasa_magenta", Wagasa::new, wagasaState(MapColor.COLOR_MAGENTA));
	public static final DeferredBlock<Block> KASA_lightb = register("block_mkasa_lightb", Wagasa::new, wagasaState(MapColor.COLOR_LIGHT_BLUE));
	public static final DeferredBlock<Block> KASA_yellow = register("block_mkasa_yellow", Wagasa::new, wagasaState(MapColor.COLOR_YELLOW));
	public static final DeferredBlock<Block> KASA_lime = register("block_mkasa_lime", Wagasa::new, wagasaState(MapColor.COLOR_LIGHT_GREEN));
	public static final DeferredBlock<Block> KASA_pink = register("block_mkasa_pink", Wagasa::new, wagasaState(MapColor.COLOR_PINK));
	public static final DeferredBlock<Block> KASA_gray = register("block_mkasa_gray", Wagasa::new, wagasaState(MapColor.COLOR_GRAY));
	public static final DeferredBlock<Block> KASA_lightg = register("block_mkasa_lightg", Wagasa::new, wagasaState(MapColor.COLOR_LIGHT_GRAY));
	public static final DeferredBlock<Block> KASA_cyan = register("block_mkasa_cyan", Wagasa::new, wagasaState(MapColor.COLOR_CYAN));
	public static final DeferredBlock<Block> KASA_purple = register("block_mkasa_purple", Wagasa::new, wagasaState(MapColor.COLOR_PURPLE));
	public static final DeferredBlock<Block> KASA_blue = register("block_mkasa_blue", Wagasa::new, wagasaState(MapColor.COLOR_BLUE));
	public static final DeferredBlock<Block> KASA_brown = register("block_mkasa_brown", Wagasa::new, wagasaState(MapColor.COLOR_BROWN));
	public static final DeferredBlock<Block> KASA_green = register("block_mkasa_green", Wagasa::new, wagasaState(MapColor.COLOR_GREEN));
	public static final DeferredBlock<Block> KASA_red = register("block_mkasa_red", Wagasa::new, wagasaState(MapColor.COLOR_RED));
	public static final DeferredBlock<Block> KASA_black = register("block_mkasa_black", Wagasa::new, wagasaState(MapColor.COLOR_BLACK));

	public static final DeferredBlock<Block> TEATABLE = register("block_teatable", CafeTable::new, woodState());
	public static final DeferredBlock<Block> ENDAI = register("block_mendai", Endai::new, woodState());
	public static final DeferredBlock<Block> ENDAI_r = register("block_mendai_red", Endai::new, woodState());

	public static final DeferredBlock<Block> CLOTHTABLE_oak = register("block_clothtable_oak", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_spruce = register("block_clothtable_spruce", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_birch = register("block_clothtable_birch", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_jungle = register("block_clothtable_jungle", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_acacia = register("block_clothtable_acacia", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_darkoak = register("block_clothtable_darkoak", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_mangrove = register("block_clothtable_mangrove", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_cherry = register("block_clothtable_cherry", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_paleoak = register("block_clothtable_paleoak", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_sakura = register("block_clothtable_sakura", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_kaede = register("block_clothtable_kaede", ClothTable::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_ichoh = register("block_clothtable_ichoh", ClothTable::new, woodState());
	
	public static final DeferredBlock<Block> CLOTHTABLE_oaksub = register("block_clothtable_oaksub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_sprucesub = register("block_clothtable_sprucesub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_birchsub = register("block_clothtable_birchsub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_junglesub = register("block_clothtable_junglesub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_acaciasub = register("block_clothtable_acaciasub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_darkoaksub = register("block_clothtable_darkoaksub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_mangrovesub = register("block_clothtable_mangrovesub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_cherrysub = register("block_clothtable_cherrysub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_paleoaksub = register("block_clothtable_paleoaksub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_sakurasub = register("block_clothtable_sakurasub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_kaedesub = register("block_clothtable_kaedesub", ClothTable_Sub::new, woodState());
	public static final DeferredBlock<Block> CLOTHTABLE_ichohsub = register("block_clothtable_ichohsub", ClothTable_Sub::new, woodState());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Unit_Blocks::neverEntity).isSuffocating(Unit_Blocks::never);
	}
	
	private static Properties bookState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Unit_Blocks::neverEntity).isSuffocating(Unit_Blocks::never);
	}
	
	private static Properties wagasaState(MapColor color) {
		return BlockBehaviour.Properties.of().mapColor(color).strength(1.0F, 1.0F).sound(SoundType.WOOL)
				.noOcclusion().isValidSpawn(Unit_Blocks::neverEntity).isSuffocating(Unit_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
