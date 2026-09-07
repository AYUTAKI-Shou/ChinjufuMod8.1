package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
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
import com.ayutaki.chinjufumod.blocks.wood.WoodSlab_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodStairs_CM;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JP_Blocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChinjufuMod.MOD_ID);

	public static Block JPBLOCKDUMMY = register("block_dummy", new JpBlockDummy(AbstractBlock.Properties.of(Material.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE).noOcclusion()));

	public static Block KAWARA_white = register("block_kawara_white", fullKawara(MaterialColor.SNOW));
	public static Block KAWARA_orange = register("block_kawara_orange", fullKawara(MaterialColor.COLOR_ORANGE));
	public static Block KAWARA_magenta = register("block_kawara_magenta", fullKawara(MaterialColor.COLOR_MAGENTA));
	public static Block KAWARA_lightb = register("block_kawara_lightb", fullKawara(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block KAWARA_yellow = register("block_kawara_yellow", fullKawara(MaterialColor.COLOR_YELLOW));
	public static Block KAWARA_lime = register("block_kawara_lime", fullKawara(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block KAWARA_pink = register("block_kawara_pink", fullKawara(MaterialColor.COLOR_PINK));
	public static Block KAWARA_gray = register("block_kawara_gray", fullKawara(MaterialColor.COLOR_GRAY));
	public static Block KAWARA_lightg = register("block_kawara_lightg", fullKawara(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block KAWARA_cyan = register("block_kawara_cyan", fullKawara(MaterialColor.COLOR_CYAN));
	public static Block KAWARA_purple = register("block_kawara_purple", fullKawara(MaterialColor.COLOR_PURPLE));
	public static Block KAWARA_blue = register("block_kawara_blue", fullKawara(MaterialColor.COLOR_BLUE));
	public static Block KAWARA_brown = register("block_kawara_brown", fullKawara(MaterialColor.COLOR_BROWN));
	public static Block KAWARA_green = register("block_kawara_green", fullKawara(MaterialColor.COLOR_GREEN));
	public static Block KAWARA_red = register("block_kawara_red", fullKawara(MaterialColor.COLOR_RED));
	public static Block KAWARA_black = register("block_kawara_black", fullKawara(MaterialColor.COLOR_BLACK));

	public static Block KAWARA_ST_white = register("block_kst_white", stairsKawara(MaterialColor.SNOW));
	public static Block KAWARA_ST_orange = register("block_kst_orange", stairsKawara(MaterialColor.COLOR_ORANGE));
	public static Block KAWARA_ST_magenta = register("block_kst_magenta", stairsKawara(MaterialColor.COLOR_MAGENTA));
	public static Block KAWARA_ST_lightb = register("block_kst_lightb", stairsKawara(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block KAWARA_ST_yellow = register("block_kst_yellow", stairsKawara(MaterialColor.COLOR_YELLOW));
	public static Block KAWARA_ST_lime = register("block_kst_lime", stairsKawara(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block KAWARA_ST_pink = register("block_kst_pink", stairsKawara(MaterialColor.COLOR_PINK));
	public static Block KAWARA_ST_gray = register("block_kst_gray", stairsKawara(MaterialColor.COLOR_GRAY));
	public static Block KAWARA_ST_lightg = register("block_kst_lightg", stairsKawara(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block KAWARA_ST_cyan = register("block_kst_cyan", stairsKawara(MaterialColor.COLOR_CYAN));
	public static Block KAWARA_ST_purple = register("block_kst_purple", stairsKawara(MaterialColor.COLOR_PURPLE));
	public static Block KAWARA_ST_blue = register("block_kst_blue", stairsKawara(MaterialColor.COLOR_BLUE));
	public static Block KAWARA_ST_brown = register("block_kst_brown", stairsKawara(MaterialColor.COLOR_BROWN));
	public static Block KAWARA_ST_green = register("block_kst_green", stairsKawara(MaterialColor.COLOR_GREEN));
	public static Block KAWARA_ST_red = register("block_kst_red", stairsKawara(MaterialColor.COLOR_RED));
	public static Block KAWARA_ST_black = register("block_kst_black", stairsKawara(MaterialColor.COLOR_BLACK));

	public static Block KAWARA_SH_white = register("block_ksh_white", slabKawara(MaterialColor.SNOW));
	public static Block KAWARA_SH_orange = register("block_ksh_orange", slabKawara(MaterialColor.COLOR_ORANGE));
	public static Block KAWARA_SH_magenta = register("block_ksh_magenta", slabKawara(MaterialColor.COLOR_MAGENTA));
	public static Block KAWARA_SH_lightb = register("block_ksh_lightb", slabKawara(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block KAWARA_SH_yellow = register("block_ksh_yellow", slabKawara(MaterialColor.COLOR_YELLOW));
	public static Block KAWARA_SH_lime = register("block_ksh_lime", slabKawara(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block KAWARA_SH_pink = register("block_ksh_pink", slabKawara(MaterialColor.COLOR_PINK));
	public static Block KAWARA_SH_gray = register("block_ksh_gray", slabKawara(MaterialColor.COLOR_GRAY));
	public static Block KAWARA_SH_lightg = register("block_ksh_lightg", slabKawara(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block KAWARA_SH_cyan = register("block_ksh_cyan", slabKawara(MaterialColor.COLOR_CYAN));
	public static Block KAWARA_SH_purple = register("block_ksh_purple", slabKawara(MaterialColor.COLOR_PURPLE));
	public static Block KAWARA_SH_blue = register("block_ksh_blue", slabKawara(MaterialColor.COLOR_BLUE));
	public static Block KAWARA_SH_brown = register("block_ksh_brown", slabKawara(MaterialColor.COLOR_BROWN));
	public static Block KAWARA_SH_green = register("block_ksh_green", slabKawara(MaterialColor.COLOR_GREEN));
	public static Block KAWARA_SH_red = register("block_ksh_red", slabKawara(MaterialColor.COLOR_RED));
	public static Block KAWARA_SH_black = register("block_ksh_black", slabKawara(MaterialColor.COLOR_BLACK));

	public static Block DIRTWALL = register("block_dirtwall", fullPlaster(MaterialColor.TERRACOTTA_ORANGE));
	public static Block DIRTWALL_stairs = register("block_dirtwall_st", stairsPlaster(MaterialColor.TERRACOTTA_ORANGE));
	public static Block DIRTWALL_SH = register("block_dirtwall_sh", slabPlaster(MaterialColor.TERRACOTTA_ORANGE));

	public static Block SHIKKUI_white = register("block_plaster_white", fullPlaster(MaterialColor.SNOW));
	public static Block SHIKKUI_orange = register("block_plaster_orange", fullPlaster(MaterialColor.COLOR_ORANGE));
	public static Block SHIKKUI_magenta = register("block_plaster_magenta", fullPlaster(MaterialColor.COLOR_MAGENTA));
	public static Block SHIKKUI_lightb = register("block_plaster_lightb", fullPlaster(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block SHIKKUI_yellow = register("block_plaster_yellow", fullPlaster(MaterialColor.COLOR_YELLOW));
	public static Block SHIKKUI_lime = register("block_plaster_lime", fullPlaster(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block SHIKKUI_pink = register("block_plaster_pink", fullPlaster(MaterialColor.COLOR_PINK));
	public static Block SHIKKUI_gray = register("block_plaster_gray", fullPlaster(MaterialColor.COLOR_GRAY));
	public static Block SHIKKUI_lightg = register("block_plaster_lightg", fullPlaster(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block SHIKKUI_cyan = register("block_plaster_cyan", fullPlaster(MaterialColor.COLOR_CYAN));
	public static Block SHIKKUI_purple = register("block_plaster_purple", fullPlaster(MaterialColor.COLOR_PURPLE));
	public static Block SHIKKUI_blue = register("block_plaster_blue", fullPlaster(MaterialColor.COLOR_BLUE));
	public static Block SHIKKUI_brown = register("block_plaster_brown", fullPlaster(MaterialColor.COLOR_BROWN));
	public static Block SHIKKUI_green = register("block_plaster_green", fullPlaster(MaterialColor.COLOR_GREEN));
	public static Block SHIKKUI_red = register("block_plaster_red", fullPlaster(MaterialColor.COLOR_RED));
	public static Block SHIKKUI_black = register("block_plaster_black", fullPlaster(MaterialColor.COLOR_BLACK));

	public static Block SHIKKUI_ST_white = register("block_pst_white", stairsPlaster(MaterialColor.SNOW));
	public static Block SHIKKUI_ST_orange = register("block_pst_orange", stairsPlaster(MaterialColor.COLOR_ORANGE));
	public static Block SHIKKUI_ST_magenta = register("block_pst_magenta", stairsPlaster(MaterialColor.COLOR_MAGENTA));
	public static Block SHIKKUI_ST_lightb = register("block_pst_lightb", stairsPlaster(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block SHIKKUI_ST_yellow = register("block_pst_yellow", stairsPlaster(MaterialColor.COLOR_YELLOW));
	public static Block SHIKKUI_ST_lime = register("block_pst_lime", stairsPlaster(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block SHIKKUI_ST_pink = register("block_pst_pink", stairsPlaster(MaterialColor.COLOR_PINK));
	public static Block SHIKKUI_ST_gray = register("block_pst_gray", stairsPlaster(MaterialColor.COLOR_GRAY));
	public static Block SHIKKUI_ST_lightg = register("block_pst_lightg", stairsPlaster(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block SHIKKUI_ST_cyan = register("block_pst_cyan", stairsPlaster(MaterialColor.COLOR_CYAN));
	public static Block SHIKKUI_ST_purple = register("block_pst_purple", stairsPlaster(MaterialColor.COLOR_PURPLE));
	public static Block SHIKKUI_ST_blue = register("block_pst_blue", stairsPlaster(MaterialColor.COLOR_BLUE));
	public static Block SHIKKUI_ST_brown = register("block_pst_brown", stairsPlaster(MaterialColor.COLOR_BROWN));
	public static Block SHIKKUI_ST_green = register("block_pst_green", stairsPlaster(MaterialColor.COLOR_GREEN));
	public static Block SHIKKUI_ST_red = register("block_pst_red", stairsPlaster(MaterialColor.COLOR_RED));
	public static Block SHIKKUI_ST_black = register("block_pst_black", stairsPlaster(MaterialColor.COLOR_BLACK));

	public static Block SHIKKUI_SH_white = register("block_psh_white", slabPlaster(MaterialColor.SNOW));
	public static Block SHIKKUI_SH_orange = register("block_psh_orange", slabPlaster(MaterialColor.COLOR_ORANGE));
	public static Block SHIKKUI_SH_magenta = register("block_psh_magenta", slabPlaster(MaterialColor.COLOR_MAGENTA));
	public static Block SHIKKUI_SH_lightb = register("block_psh_lightb", slabPlaster(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block SHIKKUI_SH_yellow = register("block_psh_yellow", slabPlaster(MaterialColor.COLOR_YELLOW));
	public static Block SHIKKUI_SH_lime = register("block_psh_lime", slabPlaster(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block SHIKKUI_SH_pink = register("block_psh_pink", slabPlaster(MaterialColor.COLOR_PINK));
	public static Block SHIKKUI_SH_gray = register("block_psh_gray", slabPlaster(MaterialColor.COLOR_GRAY));
	public static Block SHIKKUI_SH_lightg = register("block_psh_lightg", slabPlaster(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block SHIKKUI_SH_cyan = register("block_psh_cyan", slabPlaster(MaterialColor.COLOR_CYAN));
	public static Block SHIKKUI_SH_purple = register("block_psh_purple", slabPlaster(MaterialColor.COLOR_PURPLE));
	public static Block SHIKKUI_SH_blue = register("block_psh_blue", slabPlaster(MaterialColor.COLOR_BLUE));
	public static Block SHIKKUI_SH_brown = register("block_psh_brown", slabPlaster(MaterialColor.COLOR_BROWN));
	public static Block SHIKKUI_SH_green = register("block_psh_green", slabPlaster(MaterialColor.COLOR_GREEN));
	public static Block SHIKKUI_SH_red = register("block_psh_red", slabPlaster(MaterialColor.COLOR_RED));
	public static Block SHIKKUI_SH_black = register("block_psh_black", slabPlaster(MaterialColor.COLOR_BLACK));


	public static Block NAMAKO_white = register("block_namako_white", fullNamako(MaterialColor.SNOW));
	public static Block NAMAKO_orange = register("block_namako_orange", fullNamako(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKO_magenta = register("block_namako_magenta", fullNamako(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKO_lightb = register("block_namako_lightb", fullNamako(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKO_yellow = register("block_namako_yellow", fullNamako(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKO_lime = register("block_namako_lime", fullNamako(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKO_pink = register("block_namako_pink", fullNamako(MaterialColor.COLOR_PINK));
	public static Block NAMAKO_gray = register("block_namako_gray", fullNamako(MaterialColor.COLOR_GRAY));
	public static Block NAMAKO_lightg = register("block_namako_lightg", fullNamako(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKO_cyan = register("block_namako_cyan", fullNamako(MaterialColor.COLOR_CYAN));
	public static Block NAMAKO_purple = register("block_namako_purple", fullNamako(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKO_blue = register("block_namako_blue", fullNamako(MaterialColor.COLOR_BLUE));
	public static Block NAMAKO_brown = register("block_namako_brown", fullNamako(MaterialColor.COLOR_BROWN));
	public static Block NAMAKO_green = register("block_namako_green", fullNamako(MaterialColor.COLOR_GREEN));
	public static Block NAMAKO_red = register("block_namako_red", fullNamako(MaterialColor.COLOR_RED));
	public static Block NAMAKO_black = register("block_namako_black", fullNamako(MaterialColor.COLOR_BLACK));

	public static Block NAMAKO_ST_white = register("block_nst_white", stairsNamako(MaterialColor.SNOW));
	public static Block NAMAKO_ST_orange = register("block_nst_orange", stairsNamako(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKO_ST_magenta = register("block_nst_magenta", stairsNamako(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKO_ST_lightb = register("block_nst_lightb", stairsNamako(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKO_ST_yellow = register("block_nst_yellow", stairsNamako(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKO_ST_lime = register("block_nst_lime", stairsNamako(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKO_ST_pink = register("block_nst_pink", stairsNamako(MaterialColor.COLOR_PINK));
	public static Block NAMAKO_ST_gray = register("block_nst_gray", stairsNamako(MaterialColor.COLOR_GRAY));
	public static Block NAMAKO_ST_lightg = register("block_nst_lightg", stairsNamako(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKO_ST_cyan = register("block_nst_cyan", stairsNamako(MaterialColor.COLOR_CYAN));
	public static Block NAMAKO_ST_purple = register("block_nst_purple", stairsNamako(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKO_ST_blue = register("block_nst_blue", stairsNamako(MaterialColor.COLOR_BLUE));
	public static Block NAMAKO_ST_brown = register("block_nst_brown", stairsNamako(MaterialColor.COLOR_BROWN));
	public static Block NAMAKO_ST_green = register("block_nst_green", stairsNamako(MaterialColor.COLOR_GREEN));
	public static Block NAMAKO_ST_red = register("block_nst_red", stairsNamako(MaterialColor.COLOR_RED));
	public static Block NAMAKO_ST_black = register("block_nst_black", stairsNamako(MaterialColor.COLOR_BLACK));

	public static Block NAMAKO_SH_white = register("block_nsh_white", slabNamako(MaterialColor.SNOW));
	public static Block NAMAKO_SH_orange = register("block_nsh_orange", slabNamako(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKO_SH_magenta = register("block_nsh_magenta", slabNamako(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKO_SH_lightb = register("block_nsh_lightb", slabNamako(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKO_SH_yellow = register("block_nsh_yellow", slabNamako(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKO_SH_lime = register("block_nsh_lime", slabNamako(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKO_SH_pink = register("block_nsh_pink", slabNamako(MaterialColor.COLOR_PINK));
	public static Block NAMAKO_SH_gray = register("block_nsh_gray", slabNamako(MaterialColor.COLOR_GRAY));
	public static Block NAMAKO_SH_lightg = register("block_nsh_lightg", slabNamako(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKO_SH_cyan = register("block_nsh_cyan", slabNamako(MaterialColor.COLOR_CYAN));
	public static Block NAMAKO_SH_purple = register("block_nsh_purple", slabNamako(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKO_SH_blue = register("block_nsh_blue", slabNamako(MaterialColor.COLOR_BLUE));
	public static Block NAMAKO_SH_brown = register("block_nsh_brown", slabNamako(MaterialColor.COLOR_BROWN));
	public static Block NAMAKO_SH_green = register("block_nsh_green", slabNamako(MaterialColor.COLOR_GREEN));
	public static Block NAMAKO_SH_red = register("block_nsh_red", slabNamako(MaterialColor.COLOR_RED));
	public static Block NAMAKO_SH_black = register("block_nsh_black", slabNamako(MaterialColor.COLOR_BLACK));

	public static Block NAMAKOB_white = register("block_namako_b_white", fullNamakoBtype(MaterialColor.SNOW));
	public static Block NAMAKOB_orange = register("block_namako_b_orange", fullNamakoBtype(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKOB_magenta = register("block_namako_b_magenta", fullNamakoBtype(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKOB_lightb = register("block_namako_b_lightb", fullNamakoBtype(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKOB_yellow = register("block_namako_b_yellow", fullNamakoBtype(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKOB_lime = register("block_namako_b_lime", fullNamakoBtype(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKOB_pink = register("block_namako_b_pink", fullNamakoBtype(MaterialColor.COLOR_PINK));
	public static Block NAMAKOB_gray = register("block_namako_b_gray", fullNamakoBtype(MaterialColor.COLOR_GRAY));
	public static Block NAMAKOB_lightg = register("block_namako_b_lightg", fullNamakoBtype(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKOB_cyan = register("block_namako_b_cyan", fullNamakoBtype(MaterialColor.COLOR_CYAN));
	public static Block NAMAKOB_purple = register("block_namako_b_purple", fullNamakoBtype(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKOB_blue = register("block_namako_b_blue", fullNamakoBtype(MaterialColor.COLOR_BLUE));
	public static Block NAMAKOB_brown = register("block_namako_b_brown", fullNamakoBtype(MaterialColor.COLOR_BROWN));
	public static Block NAMAKOB_green = register("block_namako_b_green", fullNamakoBtype(MaterialColor.COLOR_GREEN));
	public static Block NAMAKOB_red = register("block_namako_b_red", fullNamakoBtype(MaterialColor.COLOR_RED));
	public static Block NAMAKOB_black = register("block_namako_b_black", fullNamakoBtype(MaterialColor.COLOR_BLACK));

	public static Block NAMAKOB_ST_white = register("block_nst_b_white", stairsNamakoBtype(MaterialColor.SNOW));
	public static Block NAMAKOB_ST_orange = register("block_nst_b_orange", stairsNamakoBtype(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKOB_ST_magenta = register("block_nst_b_magenta", stairsNamakoBtype(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKOB_ST_lightb = register("block_nst_b_lightb", stairsNamakoBtype(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKOB_ST_yellow = register("block_nst_b_yellow", stairsNamakoBtype(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKOB_ST_lime = register("block_nst_b_lime", stairsNamakoBtype(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKOB_ST_pink = register("block_nst_b_pink", stairsNamakoBtype(MaterialColor.COLOR_PINK));
	public static Block NAMAKOB_ST_gray = register("block_nst_b_gray", stairsNamakoBtype(MaterialColor.COLOR_GRAY));
	public static Block NAMAKOB_ST_lightg = register("block_nst_b_lightg", stairsNamakoBtype(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKOB_ST_cyan = register("block_nst_b_cyan", stairsNamakoBtype(MaterialColor.COLOR_CYAN));
	public static Block NAMAKOB_ST_purple = register("block_nst_b_purple", stairsNamakoBtype(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKOB_ST_blue = register("block_nst_b_blue", stairsNamakoBtype(MaterialColor.COLOR_BLUE));
	public static Block NAMAKOB_ST_brown = register("block_nst_b_brown", stairsNamakoBtype(MaterialColor.COLOR_BROWN));
	public static Block NAMAKOB_ST_green = register("block_nst_b_green", stairsNamakoBtype(MaterialColor.COLOR_GREEN));
	public static Block NAMAKOB_ST_red = register("block_nst_b_red", stairsNamakoBtype(MaterialColor.COLOR_RED));
	public static Block NAMAKOB_ST_black = register("block_nst_b_black", stairsNamakoBtype(MaterialColor.COLOR_BLACK));

	public static Block NAMAKOB_SH_white = register("block_nsh_b_white", slabNamakoBtype(MaterialColor.SNOW));
	public static Block NAMAKOB_SH_orange = register("block_nsh_b_orange", slabNamakoBtype(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKOB_SH_magenta = register("block_nsh_b_magenta", slabNamakoBtype(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKOB_SH_lightb = register("block_nsh_b_lightb", slabNamakoBtype(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKOB_SH_yellow = register("block_nsh_b_yellow", slabNamakoBtype(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKOB_SH_lime = register("block_nsh_b_lime", slabNamakoBtype(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKOB_SH_pink = register("block_nsh_b_pink", slabNamakoBtype(MaterialColor.COLOR_PINK));
	public static Block NAMAKOB_SH_gray = register("block_nsh_b_gray", slabNamakoBtype(MaterialColor.COLOR_GRAY));
	public static Block NAMAKOB_SH_lightg = register("block_nsh_b_lightg", slabNamakoBtype(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKOB_SH_cyan = register("block_nsh_b_cyan", slabNamakoBtype(MaterialColor.COLOR_CYAN));
	public static Block NAMAKOB_SH_purple = register("block_nsh_b_purple", slabNamakoBtype(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKOB_SH_blue = register("block_nsh_b_blue", slabNamakoBtype(MaterialColor.COLOR_BLUE));
	public static Block NAMAKOB_SH_brown = register("block_nsh_b_brown", slabNamakoBtype(MaterialColor.COLOR_BROWN));
	public static Block NAMAKOB_SH_green = register("block_nsh_b_green", slabNamakoBtype(MaterialColor.COLOR_GREEN));
	public static Block NAMAKOB_SH_red = register("block_nsh_b_red", slabNamakoBtype(MaterialColor.COLOR_RED));
	public static Block NAMAKOB_SH_black = register("block_nsh_b_black", slabNamakoBtype(MaterialColor.COLOR_BLACK));
	
	
	public static Block DIRTWALL_WALL = register("block_dirtwall_wall", wallPlaster(MaterialColor.TERRACOTTA_ORANGE));
	public static Block SHIKKUI_WALL_white = register("block_pwall_white", wallPlaster(MaterialColor.SNOW));
	public static Block SHIKKUI_WALL_orange = register("block_pwall_orange", wallPlaster(MaterialColor.COLOR_ORANGE));
	public static Block SHIKKUI_WALL_magenta = register("block_pwall_magenta", wallPlaster(MaterialColor.COLOR_MAGENTA));
	public static Block SHIKKUI_WALL_lightb = register("block_pwall_lightb", wallPlaster(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block SHIKKUI_WALL_yellow = register("block_pwall_yellow", wallPlaster(MaterialColor.COLOR_YELLOW));
	public static Block SHIKKUI_WALL_lime = register("block_pwall_lime", wallPlaster(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block SHIKKUI_WALL_pink = register("block_pwall_pink", wallPlaster(MaterialColor.COLOR_PINK));
	public static Block SHIKKUI_WALL_gray = register("block_pwall_gray", wallPlaster(MaterialColor.COLOR_GRAY));
	public static Block SHIKKUI_WALL_lightg = register("block_pwall_lightg", wallPlaster(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block SHIKKUI_WALL_cyan = register("block_pwall_cyan", wallPlaster(MaterialColor.COLOR_CYAN));
	public static Block SHIKKUI_WALL_purple = register("block_pwall_purple", wallPlaster(MaterialColor.COLOR_PURPLE));
	public static Block SHIKKUI_WALL_blue = register("block_pwall_blue", wallPlaster(MaterialColor.COLOR_BLUE));
	public static Block SHIKKUI_WALL_brown = register("block_pwall_brown", wallPlaster(MaterialColor.COLOR_BROWN));
	public static Block SHIKKUI_WALL_green = register("block_pwall_green", wallPlaster(MaterialColor.COLOR_GREEN));
	public static Block SHIKKUI_WALL_red = register("block_pwall_red", wallPlaster(MaterialColor.COLOR_RED));
	public static Block SHIKKUI_WALL_black = register("block_pwall_black", wallPlaster(MaterialColor.COLOR_BLACK));
	
	public static Block NAMAKO_WALL_white = register("block_nwall_white", wallNamako(MaterialColor.SNOW));
	public static Block NAMAKO_WALL_orange = register("block_nwall_orange", wallNamako(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKO_WALL_magenta = register("block_nwall_magenta", wallNamako(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKO_WALL_lightb = register("block_nwall_lightb", wallNamako(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKO_WALL_yellow = register("block_nwall_yellow", wallNamako(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKO_WALL_lime = register("block_nwall_lime", wallNamako(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKO_WALL_pink = register("block_nwall_pink", wallNamako(MaterialColor.COLOR_PINK));
	public static Block NAMAKO_WALL_gray = register("block_nwall_gray", wallNamako(MaterialColor.COLOR_GRAY));
	public static Block NAMAKO_WALL_lightg = register("block_nwall_lightg", wallNamako(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKO_WALL_cyan = register("block_nwall_cyan", wallNamako(MaterialColor.COLOR_CYAN));
	public static Block NAMAKO_WALL_purple = register("block_nwall_purple", wallNamako(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKO_WALL_blue = register("block_nwall_blue", wallNamako(MaterialColor.COLOR_BLUE));
	public static Block NAMAKO_WALL_brown = register("block_nwall_brown", wallNamako(MaterialColor.COLOR_BROWN));
	public static Block NAMAKO_WALL_green = register("block_nwall_green", wallNamako(MaterialColor.COLOR_GREEN));
	public static Block NAMAKO_WALL_red = register("block_nwall_red", wallNamako(MaterialColor.COLOR_RED));
	public static Block NAMAKO_WALL_black = register("block_nwall_black", wallNamako(MaterialColor.COLOR_BLACK));

	public static Block NAMAKOB_WALL_white = register("block_nwall_b_white", wallNamakoBtype(MaterialColor.SNOW));
	public static Block NAMAKOB_WALL_orange = register("block_nwall_b_orange", wallNamakoBtype(MaterialColor.COLOR_ORANGE));
	public static Block NAMAKOB_WALL_magenta = register("block_nwall_b_magenta", wallNamakoBtype(MaterialColor.COLOR_MAGENTA));
	public static Block NAMAKOB_WALL_lightb = register("block_nwall_b_lightb", wallNamakoBtype(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block NAMAKOB_WALL_yellow = register("block_nwall_b_yellow", wallNamakoBtype(MaterialColor.COLOR_YELLOW));
	public static Block NAMAKOB_WALL_lime = register("block_nwall_b_lime", wallNamakoBtype(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block NAMAKOB_WALL_pink = register("block_nwall_b_pink", wallNamakoBtype(MaterialColor.COLOR_PINK));
	public static Block NAMAKOB_WALL_gray = register("block_nwall_b_gray", wallNamakoBtype(MaterialColor.COLOR_GRAY));
	public static Block NAMAKOB_WALL_lightg = register("block_nwall_b_lightg", wallNamakoBtype(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block NAMAKOB_WALL_cyan = register("block_nwall_b_cyan", wallNamakoBtype(MaterialColor.COLOR_CYAN));
	public static Block NAMAKOB_WALL_purple = register("block_nwall_b_purple", wallNamakoBtype(MaterialColor.COLOR_PURPLE));
	public static Block NAMAKOB_WALL_blue = register("block_nwall_b_blue", wallNamakoBtype(MaterialColor.COLOR_BLUE));
	public static Block NAMAKOB_WALL_brown = register("block_nwall_b_brown", wallNamakoBtype(MaterialColor.COLOR_BROWN));
	public static Block NAMAKOB_WALL_green = register("block_nwall_b_green", wallNamakoBtype(MaterialColor.COLOR_GREEN));
	public static Block NAMAKOB_WALL_red = register("block_nwall_b_red", wallNamakoBtype(MaterialColor.COLOR_RED));
	public static Block NAMAKOB_WALL_black = register("block_nwall_b_black", wallNamakoBtype(MaterialColor.COLOR_BLACK));
	
	public static Block DIRTWALL_SAMA = register("block_dirtwall_sama", wallSama(MaterialColor.TERRACOTTA_ORANGE));
	public static Block SHIKKUI_SAMA_white = register("block_sama_white", wallSama(MaterialColor.SNOW));
	public static Block SHIKKUI_SAMA_orange = register("block_sama_orange", wallSama(MaterialColor.COLOR_ORANGE));
	public static Block SHIKKUI_SAMA_magenta = register("block_sama_magenta", wallSama(MaterialColor.COLOR_MAGENTA));
	public static Block SHIKKUI_SAMA_lightb = register("block_sama_lightb", wallSama(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block SHIKKUI_SAMA_yellow = register("block_sama_yellow", wallSama(MaterialColor.COLOR_YELLOW));
	public static Block SHIKKUI_SAMA_lime = register("block_sama_lime", wallSama(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block SHIKKUI_SAMA_pink = register("block_sama_pink", wallSama(MaterialColor.COLOR_PINK));
	public static Block SHIKKUI_SAMA_gray = register("block_sama_gray", wallSama(MaterialColor.COLOR_GRAY));
	public static Block SHIKKUI_SAMA_lightg = register("block_sama_lightg", wallSama(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block SHIKKUI_SAMA_cyan = register("block_sama_cyan", wallSama(MaterialColor.COLOR_CYAN));
	public static Block SHIKKUI_SAMA_purple = register("block_sama_purple", wallSama(MaterialColor.COLOR_PURPLE));
	public static Block SHIKKUI_SAMA_blue = register("block_sama_blue", wallSama(MaterialColor.COLOR_BLUE));
	public static Block SHIKKUI_SAMA_brown = register("block_sama_brown", wallSama(MaterialColor.COLOR_BROWN));
	public static Block SHIKKUI_SAMA_green = register("block_sama_green", wallSama(MaterialColor.COLOR_GREEN));
	public static Block SHIKKUI_SAMA_red = register("block_sama_red", wallSama(MaterialColor.COLOR_RED));
	public static Block SHIKKUI_SAMA_black = register("block_sama_black", wallSama(MaterialColor.COLOR_BLACK));
	
	public static Block KAWARA_WALL_white = register("block_kwall_white", wallKawara(MaterialColor.SNOW));
	public static Block KAWARA_WALL_orange = register("block_kwall_orange", wallKawara(MaterialColor.COLOR_ORANGE));
	public static Block KAWARA_WALL_magenta = register("block_kwall_magenta", wallKawara(MaterialColor.COLOR_MAGENTA));
	public static Block KAWARA_WALL_lightb = register("block_kwall_lightb", wallKawara(MaterialColor.COLOR_LIGHT_BLUE));
	public static Block KAWARA_WALL_yellow = register("block_kwall_yellow", wallKawara(MaterialColor.COLOR_YELLOW));
	public static Block KAWARA_WALL_lime = register("block_kwall_lime", wallKawara(MaterialColor.COLOR_LIGHT_GREEN));
	public static Block KAWARA_WALL_pink = register("block_kwall_pink", wallKawara(MaterialColor.COLOR_PINK));
	public static Block KAWARA_WALL_gray = register("block_kwall_gray", wallKawara(MaterialColor.COLOR_GRAY));
	public static Block KAWARA_WALL_lightg = register("block_kwall_lightg", wallKawara(MaterialColor.COLOR_LIGHT_GRAY));
	public static Block KAWARA_WALL_cyan = register("block_kwall_cyan", wallKawara(MaterialColor.COLOR_CYAN));
	public static Block KAWARA_WALL_purple = register("block_kwall_purple", wallKawara(MaterialColor.COLOR_PURPLE));
	public static Block KAWARA_WALL_blue = register("block_kwall_blue", wallKawara(MaterialColor.COLOR_BLUE));
	public static Block KAWARA_WALL_brown = register("block_kwall_brown", wallKawara(MaterialColor.COLOR_BROWN));
	public static Block KAWARA_WALL_green = register("block_kwall_green", wallKawara(MaterialColor.COLOR_GREEN));
	public static Block KAWARA_WALL_red = register("block_kwall_red", wallKawara(MaterialColor.COLOR_RED));
	public static Block KAWARA_WALL_black = register("block_kwall_black", wallKawara(MaterialColor.COLOR_BLACK));
	
	/* Share variables */
	private static boolean never(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	private static boolean neverSlab(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.getValue(WoodSlab_CM.TYPE) == SlabType.DOUBLE)? true : false;
	}

	private static Boolean neverEntitySlab(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(WoodSlab_CM.TYPE) == SlabType.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityStairs(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(WoodStairs_CM.HALF) == Half.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Properties stoneState(MaterialColor color) {
		return AbstractBlock.Properties.of(Material.STONE, color).strength(1.0F, 6.0F).sound(SoundType.STONE);
	}
	
	private static Full_Kawara fullKawara(MaterialColor color) {
		return new Full_Kawara(stoneState(color));
	}

	private static Stairs_Kawara stairsKawara(MaterialColor color) {
		return new Stairs_Kawara(JPBLOCKDUMMY.defaultBlockState(), stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntityStairs).isSuffocating(JP_Blocks::never));
	}

	private static Slab_Kawara slabKawara(MaterialColor color) {
		return new Slab_Kawara(stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntitySlab).isSuffocating(JP_Blocks::neverSlab));
	}

	private static Full_Plaster fullPlaster(MaterialColor color) {
		return new Full_Plaster(stoneState(color));
	}

	private static Stairs_Plaster stairsPlaster(MaterialColor color) {
		return new Stairs_Plaster(JPBLOCKDUMMY.defaultBlockState(), stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntityStairs).isSuffocating(JP_Blocks::never));
	}

	private static Slab_Plaster slabPlaster(MaterialColor color) {
		return new Slab_Plaster(stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntitySlab).isSuffocating(JP_Blocks::neverSlab));
	}

	private static Full_Namako fullNamako(MaterialColor color) {
		return new Full_Namako(stoneState(color));
	}

	private static Stairs_Namako stairsNamako(MaterialColor color) {
		return new Stairs_Namako(JPBLOCKDUMMY.defaultBlockState(), stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntityStairs).isSuffocating(JP_Blocks::never));
	}

	private static Slab_Namako slabNamako(MaterialColor color) {
		return new Slab_Namako(stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntitySlab).isSuffocating(JP_Blocks::neverSlab));
	}

	private static Full_Namako_B fullNamakoBtype(MaterialColor color) {
		return new Full_Namako_B(stoneState(color));
	}

	private static Stairs_Namako_B stairsNamakoBtype(MaterialColor color) {
		return new Stairs_Namako_B(JPBLOCKDUMMY.defaultBlockState(), stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntityStairs).isSuffocating(JP_Blocks::never));
	}

	private static Slab_Namako_B slabNamakoBtype(MaterialColor color) {
		return new Slab_Namako_B(stoneState(color).noOcclusion().isValidSpawn(JP_Blocks::neverEntitySlab).isSuffocating(JP_Blocks::neverSlab));
	}

	private static Wall_Plaster wallPlaster(MaterialColor color) {
		return new Wall_Plaster(stoneState(color));
	}
	
	private static Wall_Namako wallNamako(MaterialColor color) {
		return new Wall_Namako(stoneState(color));
	}
	
	private static Wall_NamakoB wallNamakoBtype(MaterialColor color) {
		return new Wall_NamakoB(stoneState(color));
	}
	
	private static Wall_Sama wallSama(MaterialColor color) {
		return new Wall_Sama(stoneState(color));
	}
	
	private static Wall_Kawara wallKawara(MaterialColor color) {
		return new Wall_Kawara(stoneState(color));
	}
	
	
	///* Register *///
	private static Block register(String name, Block block) {
		BLOCKS.register(name, () -> block);
		return block;
	}
}
