package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.ranma.Koushi_B;
import com.ayutaki.chinjufumod.blocks.ranma.Noren;
import com.ayutaki.chinjufumod.blocks.ranma.Ranma;
import com.ayutaki.chinjufumod.blocks.ranma.Ranma_noInfo;

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

public class Ranma_Blocks {
	/* 88 = 70 + (3 * 6) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> RANMA_oak = register("block_ranma_oak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_spruce = register("block_ranma_spru", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_birch = register("block_ranma_bir", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_jungle = register("block_ranma_jun", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_acacia = register("block_ranma_aca", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_darkoak = register("block_ranma_doak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_mangrove = register("block_ranma_mangrove", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_cherry = register("block_ranma_cherry", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_paleoak = register("block_ranma_paleoak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_sakura = register("block_ranma_saku", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_kaede = register("block_ranma_kae", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMA_ichoh = register("block_ranma_ich", Ranma::new, woodNever());

	public static final DeferredBlock<Block> RANMAB_oak = register("block_ranmab_oak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_spruce = register("block_ranmab_spru", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_birch = register("block_ranmab_bir", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_jungle = register("block_ranmab_jun", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_acacia = register("block_ranmab_aca", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_darkoak = register("block_ranmab_doak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_mangrove = register("block_ranmab_mangrove", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_cherry = register("block_ranmab_cherry", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_paleoak = register("block_ranmab_paleoak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_sakura = register("block_ranmab_saku", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_kaede = register("block_ranmab_kae", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAB_ichoh = register("block_ranmab_ich", Ranma::new, woodNever());

	public static final DeferredBlock<Block> RANMAC_oak = register("block_ranmac_oak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_spruce = register("block_ranmac_spru", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_birch = register("block_ranmac_bir", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_jungle = register("block_ranmac_jun", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_acacia = register("block_ranmac_aca", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_darkoak = register("block_ranmac_doak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_mangrove = register("block_ranmac_mangrove", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_cherry = register("block_ranmac_cherry", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_paleoak = register("block_ranmac_paleoak", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_sakura = register("block_ranmac_saku", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_kaede = register("block_ranmac_kae", Ranma::new, woodNever());
	public static final DeferredBlock<Block> RANMAC_ichoh = register("block_ranmac_ich", Ranma::new, woodNever());

	public static final DeferredBlock<Block> KANKI_oak = register("block_kanki_oak", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_spruce = register("block_kanki_spru", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_birch = register("block_kanki_bir", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_jungle = register("block_kanki_jun", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_acacia = register("block_kanki_aca", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_darkoak = register("block_kanki_doak", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_mangrove = register("block_kanki_mangrove", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_cherry = register("block_kanki_cherry", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_paleoak = register("block_kanki_paleoak", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_sakura = register("block_kanki_saku", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_kaede = register("block_kanki_kae", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KANKI_ichoh = register("block_kanki_ich", Ranma_noInfo::new, woodNever());

	public static final DeferredBlock<Block> KOUSHI_oak = register("block_koushi_oak", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_spruce = register("block_koushi_spru", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_birch = register("block_koushi_bir", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_jungle = register("block_koushi_jun", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_acacia = register("block_koushi_aca", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_darkoak = register("block_koushi_doak", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_mangrove = register("block_koushi_mangrove", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_cherry = register("block_koushi_cherry", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_paleoak = register("block_koushi_paleoak", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_sakura = register("block_koushi_saku", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_kaede = register("block_koushi_kae", Ranma_noInfo::new, woodNever());
	public static final DeferredBlock<Block> KOUSHI_ichoh = register("block_koushi_ich", Ranma_noInfo::new, woodNever());

	public static final DeferredBlock<Block> KOUSHIB_oak = register("block_koushib_oak", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_spruce = register("block_koushib_spru", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_birch = register("block_koushib_bir", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_jungle = register("block_koushib_jun", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_acacia = register("block_koushib_aca", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_darkoak = register("block_koushib_doak", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_mangrove = register("block_koushib_mangrove", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_cherry = register("block_koushib_cherry", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_paleoak = register("block_koushib_paleoak", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_sakura = register("block_koushib_saku", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_kaede = register("block_koushib_kae", Koushi_B::new, woodNever());
	public static final DeferredBlock<Block> KOUSHIB_ichoh = register("block_koushib_ich", Koushi_B::new, woodNever());

	public static final DeferredBlock<Block> NOREN_white = register("block_noren_white", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_orange = register("block_noren_orange", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_magenta = register("block_noren_magenta", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_lightb = register("block_noren_lightb", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_yellow = register("block_noren_yellow", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_lime = register("block_noren_lime", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_pink = register("block_noren_pink", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_gray = register("block_noren_gray", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_lightg = register("block_noren_lightg", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_cyan = register("block_noren_cyan", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_purple = register("block_noren_purple", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_blue = register("block_noren_blue", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_brown = register("block_noren_brown", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_green = register("block_noren_green", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_red = register("block_noren_red", Noren::new, norenState());
	public static final DeferredBlock<Block> NOREN_black = register("block_noren_black", Noren::new, norenState());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties woodNever() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Ranma_Blocks::neverEntity).isSuffocating(Ranma_Blocks::never);
	}

	private static Properties norenState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Ranma_Blocks::neverEntity).isSuffocating(Ranma_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
