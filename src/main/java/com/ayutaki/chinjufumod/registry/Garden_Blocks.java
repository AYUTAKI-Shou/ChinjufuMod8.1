package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.garden.Bonsai;
import com.ayutaki.chinjufumod.blocks.garden.Chouzubachi;
import com.ayutaki.chinjufumod.blocks.garden.Ikegaki;
import com.ayutaki.chinjufumod.blocks.garden.IkegakiLong;
import com.ayutaki.chinjufumod.blocks.garden.IronFence;
import com.ayutaki.chinjufumod.blocks.garden.Itabei;
import com.ayutaki.chinjufumod.blocks.garden.Kanyou;
import com.ayutaki.chinjufumod.blocks.garden.Kido;
import com.ayutaki.chinjufumod.blocks.garden.Makibishi;
import com.ayutaki.chinjufumod.blocks.garden.Niwaishi;
import com.ayutaki.chinjufumod.blocks.garden.Niwaishi_slab;
import com.ayutaki.chinjufumod.blocks.garden.Samon;
import com.ayutaki.chinjufumod.blocks.garden.ShishiOdoshi_stage1;
import com.ayutaki.chinjufumod.blocks.garden.ShishiOdoshi_stage2;
import com.ayutaki.chinjufumod.blocks.garden.Sudare;
import com.ayutaki.chinjufumod.blocks.garden.Takeakari;
import com.ayutaki.chinjufumod.blocks.garden.Tourou;
import com.ayutaki.chinjufumod.blocks.garden.TourouLong;

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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Garden_Blocks {
	/* 106 = 88 + (3 * 6) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> SUDARE = register("block_sudare_1", Sudare::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 1.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never));

	public static final DeferredBlock<Block> SHISHIODOSHI = register("block_shishiodoshi", ShishiOdoshi_stage1::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.STONE).noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never));
	public static final DeferredBlock<Block> SHISHIODOSHI2 = register("block_shishiodoshi2", ShishiOdoshi_stage2::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.STONE).noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never));

	public static final DeferredBlock<Block> CHOUZUBACHI = register("block_chouzubachi_kara", Chouzubachi::new, chouzubachi());
	public static final DeferredBlock<Block> CHOUZUBACHI_gra = register("block_chouzu_gra_kara", Chouzubachi::new, chouzubachi());
	public static final DeferredBlock<Block> CHOUZUBACHI_dio = register("block_chouzu_dio_kara", Chouzubachi::new, chouzubachi());
	public static final DeferredBlock<Block> CHOUZUBACHI_and = register("block_chouzu_and_kara", Chouzubachi::new, chouzubachi());

	public static final DeferredBlock<Block> ISHITOUROU = register("block_ishitourou_stone", Tourou::new, tourou());
	public static final DeferredBlock<Block> ISHITOUROU_gra = register("block_ishitourou_gra", Tourou::new, tourou());
	public static final DeferredBlock<Block> ISHITOUROU_dio = register("block_ishitourou_dio", Tourou::new, tourou());
	public static final DeferredBlock<Block> ISHITOUROU_and = register("block_ishitourou_and", Tourou::new, tourou());

	public static final DeferredBlock<Block> LONGTOUROU = register("block_longtourou_stone", TourouLong::new, tourouLong());
	public static final DeferredBlock<Block> LONGTOUROU_gra = register("block_longtourou_gra", TourouLong::new, tourouLong());
	public static final DeferredBlock<Block> LONGTOUROU_dio = register("block_longtourou_dio", TourouLong::new, tourouLong());
	public static final DeferredBlock<Block> LONGTOUROU_and = register("block_longtourou_and", TourouLong::new, tourouLong());

	public static final DeferredBlock<Block> TAKEAKARI = register("block_takeakari", Takeakari::new, takeakari());
	public static final DeferredBlock<Block> TAKEAKARI_Y = register("block_takeakari_y", Takeakari::new, takeakari());
	public static final DeferredBlock<Block> TAKEAKARI_K = register("block_takeakari_k", Takeakari::new, takeakari());

	
	public static final DeferredBlock<Block> BONSAI_oak = register("block_bonsai_oak", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_spru = register("block_bonsai_spruce", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_bir = register("block_bonsai_birch", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_jun = register("block_bonsai_jungle", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_aca = register("block_bonsai_acacia", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_doak = register("block_bonsai_darkoak", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_mangrove = register("block_bonsai_mangrove", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_cherry = register("block_bonsai_cherry", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_paleoak = register("block_bonsai_paleoak", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_sakura = register("block_bonsai_sakura", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_kaede = register("block_bonsai_kaede", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_ichoh = register("block_bonsai_ichoh", Bonsai::new, bonsai());
	public static final DeferredBlock<Block> BONSAI_kare = register("block_bonsai_oakkare", Bonsai::new, bonsai());

	public static final DeferredBlock<Block> KANYOU = register("block_kanyouoak_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_spruce = register("block_kanyouspruce_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_birch = register("block_kanyoubirch_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_jungle = register("block_kanyoujungle_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_acacia = register("block_kanyouacacia_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_darkoak = register("block_kanyoudarkoak_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_mangrove = register("block_kanyoumangrove_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_cherry = register("block_kanyoucherry_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_paleoak = register("block_kanyoupaleoak_bot", Kanyou::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> KANYOU_sakura = register("block_kanyousakura_bot", Kanyou::new, woodColor(MapColor.COLOR_PINK));
	public static final DeferredBlock<Block> KANYOU_kaede = register("block_kanyoukaede_bot", Kanyou::new, woodColor(MapColor.COLOR_RED));
	public static final DeferredBlock<Block> KANYOU_ichoh = register("block_kanyouichoh_bot", Kanyou::new, woodColor(MapColor.COLOR_YELLOW));
	public static final DeferredBlock<Block> KANYOU_kare = register("block_kanyouoakkare_bot", Kanyou::new, woodColor(MapColor.COLOR_BROWN));

	public static final DeferredBlock<Block> IKEGAKI = register("block_low_oak", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_spruce = register("block_low_spruce", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_birch = register("block_low_birch", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_jungle = register("block_low_jungle", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_acacia = register("block_low_acacia", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_darkoak = register("block_low_darkoak", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_mangrove = register("block_low_mangrove", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_cherry = register("block_low_cherry", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_paleoak = register("block_low_paleoak", Ikegaki::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKI_sakura = register("block_low_sakura", Ikegaki::new, woodColor(MapColor.COLOR_PINK));
	public static final DeferredBlock<Block> IKEGAKI_kaede = register("block_low_kaede", Ikegaki::new, woodColor(MapColor.COLOR_RED));
	public static final DeferredBlock<Block> IKEGAKI_ichoh = register("block_low_ichoh", Ikegaki::new, woodColor(MapColor.COLOR_YELLOW));
	public static final DeferredBlock<Block> IKEGAKI_kare = register("block_low_oakkare", Ikegaki::new, woodColor(MapColor.COLOR_BROWN));

	public static final DeferredBlock<Block> IKEGAKILONG = register("block_longoak_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_spruce = register("block_longspruce_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_birch = register("block_longbirch_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_jungle = register("block_longjungle_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_acacia = register("block_longacacia_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_darkoak = register("block_longdarkoak_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_mangrove = register("block_longmangrove_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_cherry = register("block_longcherry_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_paleoak = register("block_longpaleoak_bot", IkegakiLong::new, woodColor(MapColor.PLANT));
	public static final DeferredBlock<Block> IKEGAKILONG_sakura = register("block_longsakura_bot", IkegakiLong::new, woodColor(MapColor.COLOR_PINK));
	public static final DeferredBlock<Block> IKEGAKILONG_kaede = register("block_longkaede_bot", IkegakiLong::new, woodColor(MapColor.COLOR_RED));
	public static final DeferredBlock<Block> IKEGAKILONG_ichoh = register("block_longichoh_bot", IkegakiLong::new, woodColor(MapColor.COLOR_YELLOW));
	public static final DeferredBlock<Block> IKEGAKILONG_kare = register("block_longoakkare_bot", IkegakiLong::new, woodColor(MapColor.COLOR_BROWN));

	public static final DeferredBlock<Block> TETSUSAKU_BOT = register("block_ironfence_bot", IronFence::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F, 10.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never));

	public static final DeferredBlock<Block> ITABEI = register("block_itabei", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_spruce = register("block_itabei_spruce", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_birch = register("block_itabei_birch", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_jungle = register("block_itabei_jungle", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_acacia = register("block_itabei_acacia", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_darkoak = register("block_itabei_darkoak", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_mangrove = register("block_itabei_mangrove", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_cherry = register("block_itabei_cherry", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_paleoak = register("block_itabei_paleoak", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_sakura = register("block_itabei_sakura", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_kaede = register("block_itabei_kaede", Itabei::new, heiKido());
	public static final DeferredBlock<Block> ITABEI_ichoh = register("block_itabei_ichoh", Itabei::new, heiKido());
	
	public static final DeferredBlock<Block> KIDO = register("block_kido", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_spruce = register("block_kido_spruce", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_birch = register("block_kido_birch", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_jungle = register("block_kido_jungle", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_acacia = register("block_kido_acacia", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_darkoak = register("block_kido_darkoak", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_mangrove = register("block_kido_mangrove", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_cherry = register("block_kido_cherry", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_paleoak = register("block_kido_paleoak", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_sakura = register("block_kido_sakura", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_kaede = register("block_kido_kaede", Kido::new, heiKido());
	public static final DeferredBlock<Block> KIDO_ichoh = register("block_kido_ichoh", Kido::new, heiKido());
	
	public static final DeferredBlock<Block> SAMON = register("block_samon", Samon::new, samon());
	public static final DeferredBlock<Block> SAMON_B = register("block_samon_black", Samon::new, samon());
	public static final DeferredBlock<Block> NIWAISHI = register("block_niwaishi", Niwaishi::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_gra = register("block_niwaishi_gra", Niwaishi::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_dio = register("block_niwaishi_dio", Niwaishi::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_and = register("block_niwaishi_and", Niwaishi::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_slab = register("block_niwaishi_slab", Niwaishi_slab::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_slab_gra = register("block_niwaishi_slab_gra", Niwaishi_slab::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_slab_dio = register("block_niwaishi_slab_dio", Niwaishi_slab::new, niwaishi());
	public static final DeferredBlock<Block> NIWAISHI_slab_and = register("block_niwaishi_slab_and", Niwaishi_slab::new, niwaishi());
	
	public static final DeferredBlock<Block> MAKIBISHI = register("block_makibishi", Makibishi::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F, 1.0F).sound(SoundType.METAL).noCollission().noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never));

	
	/* Share variables */
	private static ToIntFunction<BlockState> litBlockEmission(int value) {
		return (state) -> { return state.getValue(BlockStateProperties.LIT) ? value : 0; };
	}

	private static ToIntFunction<BlockState> litTourouLong(int value) {
		return (state) -> { return (state.getValue(TourouLong.HALF) == DoubleBlockHalf.UPPER && state.getValue(TourouLong.LIT)) ? value : 0; };
	}
	
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties woodStateNever() {
		return BlockBehaviour.Properties.of().strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never);
	}
	
	private static Properties stoneStateNever() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE)
				.noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never);
	}
	
	private static Properties chouzubachi() {
		return stoneStateNever().randomTicks();
	}

	private static Properties tourou() {
		return stoneStateNever().lightLevel(litBlockEmission(15));
	}

	private static Properties tourouLong() {
		return stoneStateNever().lightLevel(litTourouLong(15));
	}
	
	private static Properties takeakari() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 1.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never).lightLevel(litBlockEmission(14));
	}
	
	private static Properties bonsai() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 3.0F).sound(SoundType.STONE)
				.noOcclusion().isValidSpawn(Garden_Blocks::neverEntity).isSuffocating(Garden_Blocks::never);
	}

	private static Properties woodColor(MapColor color) {
		return woodStateNever().mapColor(color);
	}

	private static Properties samon() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(1.0F, 3.0F).sound(SoundType.SAND).isValidSpawn(Garden_Blocks::neverEntity);
	}
	
	private static Properties niwaishi() {
		return stoneStateNever();
	}

	private static Properties heiKido() {
		return woodStateNever().mapColor(MapColor.WOOD);
	}

	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
