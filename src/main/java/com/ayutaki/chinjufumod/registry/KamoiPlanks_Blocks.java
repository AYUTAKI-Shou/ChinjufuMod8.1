package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Acacia;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Birch;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Cherry;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_DarkOak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Ichoh;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Jungle;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Kaede;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Mangrove;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Oak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Paleoak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Sakura;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlanks_Spruce;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KamoiPlanks_Blocks {
	/* 144 = 81 + (3 * 9) + (3 * 12) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> KAMOI_oak_oak = register("block_ka_oak_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_oak = register("block_ka_spru_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_oak = register("block_ka_bir_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_oak = register("block_ka_jun_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_oak = register("block_ka_aca_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_oak = register("block_ka_doak_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_oak = register("block_ka_mangrove_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_oak = register("block_ka_cherry_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_oak = register("block_ka_paleoak_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_oak = register("block_ka_saku_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_oak = register("block_ka_kae_oak", KamoiPlanks_Oak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_oak = register("block_ka_ich_oak", KamoiPlanks_Oak::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_spru = register("block_ka_oak_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_spru = register("block_ka_spru_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_spru = register("block_ka_bir_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_spru = register("block_ka_jun_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_spru = register("block_ka_aca_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_spru = register("block_ka_doak_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_spru = register("block_ka_mangrove_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_spru = register("block_ka_cherry_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_spru = register("block_ka_paleoak_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_spru = register("block_ka_saku_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_spru = register("block_ka_kae_spru", KamoiPlanks_Spruce::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_spru = register("block_ka_ich_spru", KamoiPlanks_Spruce::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_bir = register("block_ka_oak_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_bir = register("block_ka_spru_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_bir = register("block_ka_bir_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_bir = register("block_ka_jun_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_bir = register("block_ka_aca_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_bir = register("block_ka_doak_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_bir = register("block_ka_mangrove_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_bir = register("block_ka_cherry_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_bir = register("block_ka_paleoak_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_bir = register("block_ka_saku_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_bir = register("block_ka_kae_bir", KamoiPlanks_Birch::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_bir = register("block_ka_ich_bir", KamoiPlanks_Birch::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_jun = register("block_ka_oak_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_jun = register("block_ka_spru_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_jun = register("block_ka_bir_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_jun = register("block_ka_jun_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_jun = register("block_ka_aca_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_jun = register("block_ka_doak_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_jun = register("block_ka_mangrove_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_jun = register("block_ka_cherry_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_jun = register("block_ka_paleoak_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_jun = register("block_ka_saku_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_jun = register("block_ka_kae_jun", KamoiPlanks_Jungle::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_jun = register("block_ka_ich_jun", KamoiPlanks_Jungle::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_aca = register("block_ka_oak_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_aca = register("block_ka_spru_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_aca = register("block_ka_bir_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_aca = register("block_ka_jun_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_aca = register("block_ka_aca_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_aca = register("block_ka_doak_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_aca = register("block_ka_mangrove_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_aca = register("block_ka_cherry_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_aca = register("block_ka_paleoak_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_aca = register("block_ka_saku_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_aca = register("block_ka_kae_aca", KamoiPlanks_Acacia::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_aca = register("block_ka_ich_aca", KamoiPlanks_Acacia::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_doak = register("block_ka_oak_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_doak = register("block_ka_spru_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_doak = register("block_ka_bir_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_doak = register("block_ka_jun_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_doak = register("block_ka_aca_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_doak = register("block_ka_doak_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_doak = register("block_ka_mangrove_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_doak = register("block_ka_cherry_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_doak = register("block_ka_paleoak_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_doak = register("block_ka_saku_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_doak = register("block_ka_kae_doak", KamoiPlanks_DarkOak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_doak = register("block_ka_ich_doak", KamoiPlanks_DarkOak::new, woodState());
	
	public static final DeferredBlock<Block> KAMOI_oak_mangrove = register("block_ka_oak_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_mangrove = register("block_ka_spru_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_mangrove = register("block_ka_bir_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_mangrove = register("block_ka_jun_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_mangrove = register("block_ka_aca_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_mangrove = register("block_ka_doak_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_mangrove = register("block_ka_mangrove_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_mangrove = register("block_ka_cherry_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_mangrove = register("block_ka_paleoak_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_mangrove = register("block_ka_saku_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_mangrove = register("block_ka_kae_mangrove", KamoiPlanks_Mangrove::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_mangrove = register("block_ka_ich_mangrove", KamoiPlanks_Mangrove::new, woodState());
	
	public static final DeferredBlock<Block> KAMOI_oak_cherry = register("block_ka_oak_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_cherry = register("block_ka_spru_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_cherry = register("block_ka_bir_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_cherry = register("block_ka_jun_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_cherry = register("block_ka_aca_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_cherry = register("block_ka_doak_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_cherry = register("block_ka_mangrove_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_cherry = register("block_ka_cherry_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_cherry = register("block_ka_paleoak_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_cherry = register("block_ka_saku_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_cherry = register("block_ka_kae_cherry", KamoiPlanks_Cherry::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_cherry = register("block_ka_ich_cherry", KamoiPlanks_Cherry::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_paleoak = register("block_ka_oak_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_paleoak = register("block_ka_spru_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_paleoak = register("block_ka_bir_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_paleoak = register("block_ka_jun_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_paleoak = register("block_ka_aca_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_paleoak = register("block_ka_doak_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_paleoak = register("block_ka_mangrove_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_paleoak = register("block_ka_cherry_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_paleoak = register("block_ka_paleoak_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_paleoak = register("block_ka_saku_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_paleoak = register("block_ka_kae_paleoak", KamoiPlanks_Paleoak::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_paleoak = register("block_ka_ich_paleoak", KamoiPlanks_Paleoak::new, woodState());
	
	public static final DeferredBlock<Block> KAMOI_oak_sakura = register("block_ka_oak_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_sakura = register("block_ka_spru_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_sakura = register("block_ka_bir_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_sakura = register("block_ka_jun_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_sakura = register("block_ka_aca_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_sakura = register("block_ka_doak_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_sakura = register("block_ka_mangrove_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_sakura = register("block_ka_cherry_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_sakura = register("block_ka_paleoak_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_sakura = register("block_ka_saku_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_sakura = register("block_ka_kae_saku", KamoiPlanks_Sakura::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_sakura = register("block_ka_ich_saku", KamoiPlanks_Sakura::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_kaede = register("block_ka_oak_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_kaede = register("block_ka_spru_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_kaede = register("block_ka_bir_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_kaede = register("block_ka_jun_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_kaede = register("block_ka_aca_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_kaede = register("block_ka_doak_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_kaede = register("block_ka_mangrove_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_kaede = register("block_ka_cherry_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_kaede = register("block_ka_paleoak_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_kaede = register("block_ka_saku_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_kaede = register("block_ka_kae_kae", KamoiPlanks_Kaede::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_kaede = register("block_ka_ich_kae", KamoiPlanks_Kaede::new, woodState());

	public static final DeferredBlock<Block> KAMOI_oak_ichoh = register("block_ka_oak_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_spru_ichoh = register("block_ka_spru_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_bir_ichoh = register("block_ka_bir_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_jun_ichoh = register("block_ka_jun_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_aca_ichoh = register("block_ka_aca_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_doak_ichoh = register("block_ka_doak_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_mangrove_ichoh = register("block_ka_mangrove_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_cherry_ichoh = register("block_ka_cherry_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_paleoak_ichoh = register("block_ka_paleoak_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_saku_ichoh = register("block_ka_saku_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_kae_ichoh = register("block_ka_kae_ich", KamoiPlanks_Ichoh::new, woodState());
	public static final DeferredBlock<Block> KAMOI_ich_ichoh = register("block_ka_ich_ich", KamoiPlanks_Ichoh::new, woodState());

	
	/* Share variables */
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
