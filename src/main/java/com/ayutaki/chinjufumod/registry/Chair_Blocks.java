package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.chair.AdmiralChair;
import com.ayutaki.chinjufumod.blocks.chair.Bench;
import com.ayutaki.chinjufumod.blocks.chair.CafeChair;
import com.ayutaki.chinjufumod.blocks.chair.DiningChair;
import com.ayutaki.chinjufumod.blocks.chair.LogChair;
import com.ayutaki.chinjufumod.blocks.chair.Sofa;
import com.ayutaki.chinjufumod.blocks.chair.Sofa_leather;

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

public class Chair_Blocks {
	/* 71 = 62 + (3 * 3) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> ADMIRALCHAIR = register("block_admiralchair", AdmiralChair::new, woodState());
	public static final DeferredBlock<Block> ADMIRALCHAIR_red = register("block_admiralchair_red", AdmiralChair::new, woodState());
	
	public static final DeferredBlock<Block> DININGCHAIR = register("block_diningchair", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_spruce = register("block_diningchair_s", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_birch = register("block_diningchair_b", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_jungle = register("block_diningchair_j", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_acacia = register("block_diningchair_a", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_darkoak = register("block_diningchair_d", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_mangrove = register("block_diningchair_mangrove", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_cherry = register("block_diningchair_cherry", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_paleoak = register("block_diningchair_paleoak", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_sakura = register("block_diningchair_saku", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_kaede = register("block_diningchair_kae", DiningChair::new, woodState());
	public static final DeferredBlock<Block> DININGCHAIR_ichoh = register("block_diningchair_ich", DiningChair::new, woodState());
	
	public static final DeferredBlock<Block> LOGCHAIR = register("block_logchair", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_spruce = register("block_logchair_spruce", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_birch = register("block_logchair_birch", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_jungle = register("block_logchair_jungle", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_acacia = register("block_logchair_acacia", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_darkoak = register("block_logchair_darkoak", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_mangrove = register("block_logchair_mangrove", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_cherry = register("block_logchair_cherry", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_paleoak = register("block_logchair_paleoak", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_sakura = register("block_logchair_sakura", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_kaede = register("block_logchair_kaede", LogChair::new, woodState());
	public static final DeferredBlock<Block> LOGCHAIR_ichoh = register("block_logchair_ichoh", LogChair::new, woodState());

	public static final DeferredBlock<Block> CAFECHAIR_white = register("block_cafechair_white", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_orange = register("block_cafechair_orange", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_magenta = register("block_cafechair_magenta", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_lightb = register("block_cafechair_lightb", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_yellow = register("block_cafechair_yellow", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_lime = register("block_cafechair_lime", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_pink = register("block_cafechair_pink", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_gray = register("block_cafechair_gray", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_lightg = register("block_cafechair_lightg", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_cyan = register("block_cafechair_cyan", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_purple = register("block_cafechair_purple", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_blue = register("block_cafechair_blue", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_brown = register("block_cafechair_brown", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_green = register("block_cafechair_green", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_red = register("block_cafechair_red", CafeChair::new, woodState());
	public static final DeferredBlock<Block> CAFECHAIR_black = register("block_cafechair_black", CafeChair::new, woodState());

	public static final DeferredBlock<Block> SOFA_leather = register("block_sofa_leather", Sofa_leather::new, woodState());
	public static final DeferredBlock<Block> SOFA_white = register("block_sofa_white", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_orange = register("block_sofa_orange", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_magenta = register("block_sofa_magenta", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_lightb = register("block_sofa_lightblue", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_yellow = register("block_sofa_yellow", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_lime = register("block_sofa_lime", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_pink = register("block_sofa_pink", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_gray = register("block_sofa_gray", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_lightg = register("block_sofa_lightgray", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_cyan = register("block_sofa_cyan", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_purple = register("block_sofa_purple", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_blue = register("block_sofa_blue", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_brown = register("block_sofa_brown", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_green = register("block_sofa_green", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_red = register("block_sofa_red", Sofa::new, woodState());
	public static final DeferredBlock<Block> SOFA_black = register("block_sofa_black", Sofa::new, woodState());

	public static final DeferredBlock<Block> BENCH = register("block_bench", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_spruce = register("block_bench_spru", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_birch = register("block_bench_bir", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_jungle = register("block_bench_jun", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_acacia = register("block_bench_aca", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_darkoak = register("block_bench_doak", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_mangrove = register("block_bench_mangrove", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_cherry = register("block_bench_cherry", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_paleoak = register("block_bench_paleoak", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_sakura = register("block_bench_saku", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_kaede = register("block_bench_kae", Bench::new, woodState());
	public static final DeferredBlock<Block> BENCH_ichoh = register("block_bench_ich", Bench::new, woodState());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Chair_Blocks::neverEntity).isSuffocating(Chair_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
