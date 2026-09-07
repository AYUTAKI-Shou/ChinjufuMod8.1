package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Stairs_JP;
import com.ayutaki.chinjufumod.blocks.jpblock.Full_Kawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Full_Namako;
import com.ayutaki.chinjufumod.blocks.jpblock.Full_Namako_B;
import com.ayutaki.chinjufumod.blocks.jpblock.Full_Plaster;
import com.ayutaki.chinjufumod.blocks.jpblock.JpBlockDummy;
import com.ayutaki.chinjufumod.blocks.jpblock.Slab_Kawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Slab_Namako;
import com.ayutaki.chinjufumod.blocks.jpblock.Slab_Namako_B;
import com.ayutaki.chinjufumod.blocks.jpblock.Slab_Plaster;
import com.ayutaki.chinjufumod.blocks.jpblock.Stairs_Kawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Stairs_Namako;
import com.ayutaki.chinjufumod.blocks.jpblock.Stairs_Namako_B;
import com.ayutaki.chinjufumod.blocks.jpblock.Stairs_Plaster;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Kawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Namako;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_NamakoB;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Plaster;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Sama;

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
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JP_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> JPBLOCKDUMMY = register("block_dummy", JpBlockDummy::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE).noOcclusion());

	public static final DeferredBlock<Block> KAWARA_white = register("block_kawara_white", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_orange = register("block_kawara_orange", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_magenta = register("block_kawara_magenta", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_lightb = register("block_kawara_lightb", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_yellow = register("block_kawara_yellow", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_lime = register("block_kawara_lime", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_pink = register("block_kawara_pink", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_gray = register("block_kawara_gray", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_lightg = register("block_kawara_lightg", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_cyan = register("block_kawara_cyan", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_purple = register("block_kawara_purple", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_blue = register("block_kawara_blue", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_brown = register("block_kawara_brown", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_green = register("block_kawara_green", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_red = register("block_kawara_red", Full_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_black = register("block_kawara_black", Full_Kawara::new, stoneState());

	public static final DeferredBlock<Block> KAWARA_ST_white = register("block_kst_white", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_orange = register("block_kst_orange", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_magenta = register("block_kst_magenta", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_lightb = register("block_kst_lightb", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_yellow = register("block_kst_yellow", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_lime = register("block_kst_lime", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_pink = register("block_kst_pink", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_gray = register("block_kst_gray", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_lightg = register("block_kst_lightg", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_cyan = register("block_kst_cyan", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_purple = register("block_kst_purple", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_blue = register("block_kst_blue", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_brown = register("block_kst_brown", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_green = register("block_kst_green", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_red = register("block_kst_red", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> KAWARA_ST_black = register("block_kst_black", (props) -> new Stairs_Kawara(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());

	public static final DeferredBlock<Block> KAWARA_SH_white = register("block_ksh_white", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_orange = register("block_ksh_orange", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_magenta = register("block_ksh_magenta", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_lightb = register("block_ksh_lightb", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_yellow = register("block_ksh_yellow", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_lime = register("block_ksh_lime", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_pink = register("block_ksh_pink", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_gray = register("block_ksh_gray", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_lightg = register("block_ksh_lightg", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_cyan = register("block_ksh_cyan", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_purple = register("block_ksh_purple", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_blue = register("block_ksh_blue", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_brown = register("block_ksh_brown", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_green = register("block_ksh_green", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_red = register("block_ksh_red", Slab_Kawara::new, slabPlaster());
	public static final DeferredBlock<Block> KAWARA_SH_black = register("block_ksh_black", Slab_Kawara::new, slabPlaster());

	public static final DeferredBlock<Block> DIRTWALL = register("block_dirtwall", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> DIRTWALL_stairs = register("block_dirtwall_st", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> DIRTWALL_SH = register("block_dirtwall_sh", Slab_Plaster::new, slabPlaster());

	public static final DeferredBlock<Block> SHIKKUI_white = register("block_plaster_white", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_orange = register("block_plaster_orange", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_magenta = register("block_plaster_magenta", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_lightb = register("block_plaster_lightb", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_yellow = register("block_plaster_yellow", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_lime = register("block_plaster_lime", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_pink = register("block_plaster_pink", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_gray = register("block_plaster_gray", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_lightg = register("block_plaster_lightg", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_cyan = register("block_plaster_cyan", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_purple = register("block_plaster_purple", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_blue = register("block_plaster_blue", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_brown = register("block_plaster_brown", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_green = register("block_plaster_green", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_red = register("block_plaster_red", Full_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_black = register("block_plaster_black", Full_Plaster::new, stoneState());

	public static final DeferredBlock<Block> SHIKKUI_ST_white = register("block_pst_white", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_orange = register("block_pst_orange", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_magenta = register("block_pst_magenta", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_lightb = register("block_pst_lightb", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_yellow = register("block_pst_yellow", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_lime = register("block_pst_lime", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_pink = register("block_pst_pink", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_gray = register("block_pst_gray", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_lightg = register("block_pst_lightg", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_cyan = register("block_pst_cyan", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_purple = register("block_pst_purple", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_blue = register("block_pst_blue", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_brown = register("block_pst_brown", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_green = register("block_pst_green", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_red = register("block_pst_red", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> SHIKKUI_ST_black = register("block_pst_black", (props) -> new Stairs_Plaster(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());

	public static final DeferredBlock<Block> SHIKKUI_SH_white = register("block_psh_white", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_orange = register("block_psh_orange", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_magenta = register("block_psh_magenta", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_lightb = register("block_psh_lightb", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_yellow = register("block_psh_yellow", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_lime = register("block_psh_lime", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_pink = register("block_psh_pink", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_gray = register("block_psh_gray", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_lightg = register("block_psh_lightg", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_cyan = register("block_psh_cyan", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_purple = register("block_psh_purple", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_blue = register("block_psh_blue", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_brown = register("block_psh_brown", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_green = register("block_psh_green", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_red = register("block_psh_red", Slab_Plaster::new, slabPlaster());
	public static final DeferredBlock<Block> SHIKKUI_SH_black = register("block_psh_black", Slab_Plaster::new, slabPlaster());

	public static final DeferredBlock<Block> NAMAKO_white = register("block_namako_white", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_orange = register("block_namako_orange", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_magenta = register("block_namako_magenta", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_lightb = register("block_namako_lightb", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_yellow = register("block_namako_yellow", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_lime = register("block_namako_lime", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_pink = register("block_namako_pink", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_gray = register("block_namako_gray", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_lightg = register("block_namako_lightg", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_cyan = register("block_namako_cyan", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_purple = register("block_namako_purple", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_blue = register("block_namako_blue", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_brown = register("block_namako_brown", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_green = register("block_namako_green", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_red = register("block_namako_red", Full_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_black = register("block_namako_black", Full_Namako::new, stoneState());

	public static final DeferredBlock<Block> NAMAKO_ST_white = register("block_nst_white", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_orange = register("block_nst_orange", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_magenta = register("block_nst_magenta", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_lightb = register("block_nst_lightb", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_yellow = register("block_nst_yellow", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_lime = register("block_nst_lime", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_pink = register("block_nst_pink", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_gray = register("block_nst_gray", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_lightg = register("block_nst_lightg", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_cyan = register("block_nst_cyan", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_purple = register("block_nst_purple", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_blue = register("block_nst_blue", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_brown = register("block_nst_brown", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_green = register("block_nst_green", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_red = register("block_nst_red", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKO_ST_black = register("block_nst_black", (props) -> new Stairs_Namako(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());

	public static final DeferredBlock<Block> NAMAKO_SH_white = register("block_nsh_white", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_orange = register("block_nsh_orange", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_magenta = register("block_nsh_magenta", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_lightb = register("block_nsh_lightb", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_yellow = register("block_nsh_yellow", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_lime = register("block_nsh_lime", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_pink = register("block_nsh_pink", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_gray = register("block_nsh_gray", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_lightg = register("block_nsh_lightg", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_cyan = register("block_nsh_cyan", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_purple = register("block_nsh_purple", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_blue = register("block_nsh_blue", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_brown = register("block_nsh_brown", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_green = register("block_nsh_green", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_red = register("block_nsh_red", Slab_Namako::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKO_SH_black = register("block_nsh_black", Slab_Namako::new, slabPlaster());

	public static final DeferredBlock<Block> NAMAKOB_white = register("block_namako_b_white", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_orange = register("block_namako_b_orange", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_magenta = register("block_namako_b_magenta", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_lightb = register("block_namako_b_lightb", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_yellow = register("block_namako_b_yellow", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_lime = register("block_namako_b_lime", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_pink = register("block_namako_b_pink", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_gray = register("block_namako_b_gray", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_lightg = register("block_namako_b_lightg", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_cyan = register("block_namako_b_cyan", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_purple = register("block_namako_b_purple", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_blue = register("block_namako_b_blue", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_brown = register("block_namako_b_brown", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_green = register("block_namako_b_green", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_red = register("block_namako_b_red", Full_Namako_B::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_black = register("block_namako_b_black", Full_Namako_B::new, stoneState());

	public static final DeferredBlock<Block> NAMAKOB_ST_white = register("block_nst_b_white", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_orange = register("block_nst_b_orange", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_magenta = register("block_nst_b_magenta", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_lightb = register("block_nst_b_lightb", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_yellow = register("block_nst_b_yellow", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_lime = register("block_nst_b_lime", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_pink = register("block_nst_b_pink", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_gray = register("block_nst_b_gray", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_lightg = register("block_nst_b_lightg", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_cyan = register("block_nst_b_cyan", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_purple = register("block_nst_b_purple", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_blue = register("block_nst_b_blue", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_brown = register("block_nst_b_brown", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_green = register("block_nst_b_green", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_red = register("block_nst_b_red", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());
	public static final DeferredBlock<Block> NAMAKOB_ST_black = register("block_nst_b_black", (props) -> new Stairs_Namako_B(JPBLOCKDUMMY.get().defaultBlockState(), props), stairsKawara());

	public static final DeferredBlock<Block> NAMAKOB_SH_white = register("block_nsh_b_white", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_orange = register("block_nsh_b_orange", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_magenta = register("block_nsh_b_magenta", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_lightb = register("block_nsh_b_lightb", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_yellow = register("block_nsh_b_yellow", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_lime = register("block_nsh_b_lime", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_pink = register("block_nsh_b_pink", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_gray = register("block_nsh_b_gray", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_lightg = register("block_nsh_b_lightg", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_cyan = register("block_nsh_b_cyan", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_purple = register("block_nsh_b_purple", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_blue = register("block_nsh_b_blue", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_brown = register("block_nsh_b_brown", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_green = register("block_nsh_b_green", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_red = register("block_nsh_b_red", Slab_Namako_B::new, slabPlaster());
	public static final DeferredBlock<Block> NAMAKOB_SH_black = register("block_nsh_b_black", Slab_Namako_B::new, slabPlaster());
	
	
	public static final DeferredBlock<Block> DIRTWALL_WALL = register("block_dirtwall_wall", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_white = register("block_pwall_white", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_orange = register("block_pwall_orange", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_magenta = register("block_pwall_magenta", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_lightb = register("block_pwall_lightb", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_yellow = register("block_pwall_yellow", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_lime = register("block_pwall_lime", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_pink = register("block_pwall_pink", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_gray = register("block_pwall_gray", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_lightg = register("block_pwall_lightg", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_cyan = register("block_pwall_cyan", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_purple = register("block_pwall_purple", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_blue = register("block_pwall_blue", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_brown = register("block_pwall_brown", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_green = register("block_pwall_green", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_red = register("block_pwall_red", Wall_Plaster::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_WALL_black = register("block_pwall_black", Wall_Plaster::new, stoneState());
	
	public static final DeferredBlock<Block> NAMAKO_WALL_white = register("block_nwall_white", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_orange = register("block_nwall_orange", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_magenta = register("block_nwall_magenta", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_lightb = register("block_nwall_lightb", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_yellow = register("block_nwall_yellow", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_lime = register("block_nwall_lime", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_pink = register("block_nwall_pink", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_gray = register("block_nwall_gray", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_lightg = register("block_nwall_lightg", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_cyan = register("block_nwall_cyan", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_purple = register("block_nwall_purple", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_blue = register("block_nwall_blue", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_brown = register("block_nwall_brown", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_green = register("block_nwall_green", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_red = register("block_nwall_red", Wall_Namako::new, stoneState());
	public static final DeferredBlock<Block> NAMAKO_WALL_black = register("block_nwall_black", Wall_Namako::new, stoneState());

	public static final DeferredBlock<Block> NAMAKOB_WALL_white = register("block_nwall_b_white", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_orange = register("block_nwall_b_orange", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_magenta = register("block_nwall_b_magenta", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_lightb = register("block_nwall_b_lightb", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_yellow = register("block_nwall_b_yellow", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_lime = register("block_nwall_b_lime", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_pink = register("block_nwall_b_pink", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_gray = register("block_nwall_b_gray", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_lightg = register("block_nwall_b_lightg", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_cyan = register("block_nwall_b_cyan", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_purple = register("block_nwall_b_purple", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_blue = register("block_nwall_b_blue", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_brown = register("block_nwall_b_brown", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_green = register("block_nwall_b_green", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_red = register("block_nwall_b_red", Wall_NamakoB::new, stoneState());
	public static final DeferredBlock<Block> NAMAKOB_WALL_black = register("block_nwall_b_black", Wall_NamakoB::new, stoneState());
	
	public static final DeferredBlock<Block> DIRTWALL_SAMA = register("block_dirtwall_sama", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_white = register("block_sama_white", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_orange = register("block_sama_orange", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_magenta = register("block_sama_magenta", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_lightb = register("block_sama_lightb", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_yellow = register("block_sama_yellow", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_lime = register("block_sama_lime", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_pink = register("block_sama_pink", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_gray = register("block_sama_gray", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_lightg = register("block_sama_lightg", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_cyan = register("block_sama_cyan", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_purple = register("block_sama_purple", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_blue = register("block_sama_blue", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_brown = register("block_sama_brown", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_green = register("block_sama_green", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_red = register("block_sama_red", Wall_Sama::new, stoneState());
	public static final DeferredBlock<Block> SHIKKUI_SAMA_black = register("block_sama_black", Wall_Sama::new, stoneState());
	
	public static final DeferredBlock<Block> KAWARA_WALL_white = register("block_kwall_white", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_orange = register("block_kwall_orange", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_magenta = register("block_kwall_magenta", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_lightb = register("block_kwall_lightb", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_yellow = register("block_kwall_yellow", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_lime = register("block_kwall_lime", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_pink = register("block_kwall_pink", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_gray = register("block_kwall_gray", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_lightg = register("block_kwall_lightg", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_cyan = register("block_kwall_cyan", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_purple = register("block_kwall_purple", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_blue = register("block_kwall_blue", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_brown = register("block_kwall_brown", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_green = register("block_kwall_green", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_red = register("block_kwall_red", Wall_Kawara::new, stoneState());
	public static final DeferredBlock<Block> KAWARA_WALL_black = register("block_kwall_black", Wall_Kawara::new, stoneState());

	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static boolean neverSlab(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.getValue(Base_Slab_JP.TYPE) == SlabType.DOUBLE)? true : false;
	}

	private static Boolean neverEntitySlab(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(Base_Slab_JP.TYPE) == SlabType.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityStairs(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(Base_Stairs_JP.HALF) == Half.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Properties stoneState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE);
	}
	
	private static Properties stairsKawara() {
		return stoneState().noOcclusion().isValidSpawn(JP_Blocks::neverEntityStairs).isSuffocating(JP_Blocks::never);
	}
	
	private static Properties slabPlaster() {
		return stoneState().noOcclusion().isValidSpawn(JP_Blocks::neverEntitySlab).isSuffocating(JP_Blocks::neverSlab);
	}
	
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
