package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.dish.Nabe_NamaSoup;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Dashi;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_KouboNyusan;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Shouyu;
import com.ayutaki.chinjufumod.blocks.hakkou.Bot_Vanilla;
import com.ayutaki.chinjufumod.blocks.hakkou.Bottle_Sake;
import com.ayutaki.chinjufumod.blocks.hakkou.Cheese;
import com.ayutaki.chinjufumod.blocks.hakkou.Cheese_Curd;
import com.ayutaki.chinjufumod.blocks.hakkou.Glass_Cider;
import com.ayutaki.chinjufumod.blocks.hakkou.Glass_Mead;
import com.ayutaki.chinjufumod.blocks.hakkou.Glass_Sake;
import com.ayutaki.chinjufumod.blocks.hakkou.Glass_Wine;
import com.ayutaki.chinjufumod.blocks.hakkou.Kit_Cheese_AAA;
import com.ayutaki.chinjufumod.blocks.hakkou.Kit_Cheese_OAA;
import com.ayutaki.chinjufumod.blocks.hakkou.Kit_Cheese_Tana;
import com.ayutaki.chinjufumod.blocks.hakkou.Kit_Tana2;
import com.ayutaki.chinjufumod.blocks.hakkou.Kit_Tana2Sake;
import com.ayutaki.chinjufumod.blocks.hakkou.Mizuoke;
import com.ayutaki.chinjufumod.blocks.hakkou.Mizuoke_full;
import com.ayutaki.chinjufumod.blocks.hakkou.NabeAmazake;
import com.ayutaki.chinjufumod.blocks.hakkou.Tana_ChaNoriPepper;
import com.ayutaki.chinjufumod.blocks.hakkou.Tana_KinokoKonbuVanilla;
import com.ayutaki.chinjufumod.blocks.hakkou.Tana_Kouji;
import com.ayutaki.chinjufumod.blocks.hakkou.TaruF_Hakusai1;
import com.ayutaki.chinjufumod.blocks.hakkou.TaruF_Hakusai2;
import com.ayutaki.chinjufumod.blocks.hakkou.TaruF_ShouyuKomezu;
import com.ayutaki.chinjufumod.blocks.hakkou.TaruY_Cocoa;
import com.ayutaki.chinjufumod.blocks.hakkou.TaruY_WineCiderMead;
import com.ayutaki.chinjufumod.blocks.hakkou.Taru_Hakkou;
import com.ayutaki.chinjufumod.blocks.hakkou.Taru_Jukusei;
import com.ayutaki.chinjufumod.blocks.hakkou.Taru_Miso;
import com.ayutaki.chinjufumod.blocks.hakkou.Taru_ShuboMoromi;
import com.ayutaki.chinjufumod.blocks.hakkou.Zundou_ColdMilk;

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

public class Hakkou_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> KOUBOBOT_full = register("block_bin_koubo_f", Bot_KouboNyusan::new, stoneState().randomTicks());
	public static final DeferredBlock<Block> NYUSANBOT_full = register("block_bin_nyusan_f", Bot_KouboNyusan::new, stoneState().randomTicks());

	public static final DeferredBlock<Block> MIZUOKE = register("block_mizuoke", Mizuoke::new, woodState().randomTicks());
	public static final DeferredBlock<Block> MIZUOKE_full = register("block_mizuoke_full", Mizuoke_full::new, woodState().randomTicks());

	/* Barrel */
	public static final DeferredBlock<Block> HAKKOU_TARU = register("block_taru_hakkou", Taru_Hakkou::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 4.2F)
			.sound(SoundType.WOOD).noOcclusion().isValidSpawn(Hakkou_Blocks::neverEntity).isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> KOUJI_TARU = register("block_taru_kouji_f", Tana_Kouji::new, taruRandom().isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> SHUBO_TARU = register("block_taru_shubo_f", Taru_ShuboMoromi::new, taruRandom());
	public static final DeferredBlock<Block> MOROMI_TARU = register("block_taru_moromi_f", Taru_ShuboMoromi::new, taruRandom());
	public static final DeferredBlock<Block> JUKUSEI_TARU = register("block_taru_jukusei_f", Taru_Jukusei::new, taruRandom());

	public static final DeferredBlock<Block> RINGOSHU_TARU = register("block_taru_ringoshu_f", TaruY_WineCiderMead::new, taruRandom());
	public static final DeferredBlock<Block> BUDOUSHU_TARU = register("block_taru_budoushu_f", TaruY_WineCiderMead::new, taruRandom());
	public static final DeferredBlock<Block> HACHIMITSU_TARU = register("block_taru_hachimitsushu_f", TaruY_WineCiderMead::new, taruRandom());

	/* Bottle */
	public static final DeferredBlock<Block> NAMASAKEBOT = register("block_bot_sakenama_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> SAKEBOT = register("block_bot_sake_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> JUKUSAKEBOT = register("block_bot_sakejuku_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> NABEAMAZAKE_nama = register("block_food_nabeaz_n", Nabe_NamaSoup::new, stoneState());
	public static final DeferredBlock<Block> NABEAMAZAKE = register("block_food_nabeaz_1", NabeAmazake::new, stoneState());

	public static final DeferredBlock<Block> CIDERBOT = register("block_bot_cider_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> JUKUCIDERBOT = register("block_bot_ciderjuku_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> WINEBOT = register("block_bot_wine_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> JUKUWINEBOT = register("block_bot_winejuku_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> MEADBOT = register("block_bot_mead_1", Bottle_Sake::new, stoneState());
	public static final DeferredBlock<Block> JUKUMEADBOT = register("block_bot_meadjuku_1", Bottle_Sake::new, stoneState());

	/* Glass */
	public static final DeferredBlock<Block> NAMASAKEGLASS = register("block_glass_sakenama", Glass_Sake::new, stoneState());
	public static final DeferredBlock<Block> SAKEGLASS = register("block_glass_sake", Glass_Sake::new, stoneState());
	public static final DeferredBlock<Block> JUKUSAKEGLASS = register("block_glass_sakejuku", Glass_Sake::new, stoneState());
	public static final DeferredBlock<Block> AMAZAKEGLASS = register("block_glass_amazake", Glass_Sake::new, stoneState());

	public static final DeferredBlock<Block> CIDERGLASS = register("block_glass_cider", Glass_Cider::new, stoneState());
	public static final DeferredBlock<Block> JUKUCIDERGLASS = register("block_glass_ciderjuku", Glass_Cider::new, stoneState());
	public static final DeferredBlock<Block> WINEGLASS = register("block_glass_wine", Glass_Wine::new, stoneState());
	public static final DeferredBlock<Block> JUKUWINEGLASS = register("block_glass_winejuku", Glass_Wine::new, stoneState());
	public static final DeferredBlock<Block> MEADGLASS = register("block_glass_mead", Glass_Mead::new, stoneState());
	public static final DeferredBlock<Block> JUKUMEADGLASS = register("block_glass_meadjuku", Glass_Mead::new, stoneState());

	/* Barrel2 */
	public static final DeferredBlock<Block> COCOA_TARU = register("block_taru_cocoa_f", TaruY_Cocoa::new, taruRandom());
	public static final DeferredBlock<Block> MISO_TARU = register("block_taru_miso_f", Taru_Miso::new, taruRandom());
	public static final DeferredBlock<Block> HAKUSAI_TARU1 = register("block_taru_hakusai_f", TaruF_Hakusai1::new, taruRandom());
	public static final DeferredBlock<Block> HAKUSAI_TARU2 = register("block_taru_hakusai_f2", TaruF_Hakusai2::new, taruRandom());
	public static final DeferredBlock<Block> SHOUYU_TARU = register("block_taru_shouyu_f", TaruF_ShouyuKomezu::new, taruRandom());
	public static final DeferredBlock<Block> KOMEZU_TARU = register("block_taru_komezu_f", TaruF_ShouyuKomezu::new, taruRandom());
	public static final DeferredBlock<Block> KINOKO_TARU = register("block_taru_kinoko_f", Tana_KinokoKonbuVanilla::new, taruRandom().isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> KONBU_TARU = register("block_taru_konbu_f", Tana_KinokoKonbuVanilla::new, taruRandom().isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> NORI_TARU = register("block_taru_nori_f", Tana_ChaNoriPepper::new, taruRandom().isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> KOUCHA_TARU = register("block_taru_koucha_f", Tana_ChaNoriPepper::new, taruRandom().isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> PEPPER_TARU = register("block_taru_pepper_f", Tana_ChaNoriPepper::new, taruRandom().isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> VANILLA_TARU = register("block_taru_vanilla_f", Tana_KinokoKonbuVanilla::new, taruRandom().isSuffocating(Hakkou_Blocks::never));

	public static final DeferredBlock<Block> SHOUYU_bot_14 = register("block_shouyu_bot", Bot_Shouyu::new, stoneState());
	public static final DeferredBlock<Block> SHOUYU_bot_24 = register("block_shouyu_bot_2", Bot_Shouyu::new, stoneState());
	public static final DeferredBlock<Block> SHOUYU_bot_34 = register("block_shouyu_bot_3", Bot_Shouyu::new, stoneState());
	public static final DeferredBlock<Block> SHOUYU_bot_44 = register("block_shouyu_bot_4", Bot_Shouyu::new, stoneState());

	public static final DeferredBlock<Block> KOMEZU_bot_12 = register("block_komezu_bot", Bot_Shouyu::new, stoneState());
	public static final DeferredBlock<Block> KOMEZU_bot_22 = register("block_komezu_bot_2", Bot_Shouyu::new, stoneState());

	public static final DeferredBlock<Block> DASHI_bot_14 = register("block_dashi_bot", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> DASHI_bot_24 = register("block_dashi_bot_2", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> DASHI_bot_34 = register("block_dashi_bot_3", Bot_Dashi::new, stoneState());
	public static final DeferredBlock<Block> DASHI_bot_44 = register("block_dashi_bot_4", Bot_Dashi::new, stoneState());

	public static final DeferredBlock<Block> VANILLA_bot_14 = register("block_vanilla_bot", Bot_Vanilla::new, stoneState());
	public static final DeferredBlock<Block> VANILLA_bot_24 = register("block_vanilla_bot_2", Bot_Vanilla::new, stoneState());
	public static final DeferredBlock<Block> VANILLA_bot_34 = register("block_vanilla_bot_3", Bot_Vanilla::new, stoneState());
	public static final DeferredBlock<Block> VANILLA_bot_44 = register("block_vanilla_bot_4", Bot_Vanilla::new, stoneState());

	public static final DeferredBlock<Block> COLD_MILK = register("block_zundou_coldmilk", 
			Zundou_ColdMilk::new, BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noCollission().strength(1.0F, 1.0F).sound(SoundType.METAL).noOcclusion().isValidSpawn(Hakkou_Blocks::neverEntity).isSuffocating(Hakkou_Blocks::never));
	public static final DeferredBlock<Block> CHEESE_CURD = register("block_food_cheesecurd", Cheese_Curd::new, baseState().randomTicks().sound(SoundType.SNOW));
	public static final DeferredBlock<Block> CHEESE = register("block_food_cheese_1", Cheese::new, baseState().sound(SoundType.WOOD));

	public static final DeferredBlock<Block> WINE_TANA = register("block_kit2_tana", Kit_Tana2::new, woodState());
	public static final DeferredBlock<Block> KIT_SAKENAMA = register("block_kit2_sakenama", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_SAKE = register("block_kit2_sake", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_SAKEJUKU = register("block_kit2_sakejuku", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_CIDER = register("block_kit2_cider", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_CIDERJUKU = register("block_kit2_ciderjuku", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_WINE = register("block_kit2_wine", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_WINEJUKU = register("block_kit2_winejuku", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_MEAD = register("block_kit2_mead", Kit_Tana2Sake::new, woodState());
	public static final DeferredBlock<Block> KIT_MEADJUKU = register("block_kit2_meadjuku", Kit_Tana2Sake::new, woodState());

	public static final DeferredBlock<Block> CHEESE_TANA = register("block_kit_cheese_tana", Kit_Cheese_Tana::new, woodState());
	public static final DeferredBlock<Block> CHEESE_OAA = register("block_kit_cheese_oaa", Kit_Cheese_OAA::new, woodState());
	public static final DeferredBlock<Block> CHEESE_AAA = register("block_kit_cheese_aaa", Kit_Cheese_AAA::new, woodState());

	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties baseState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 1.0F).noOcclusion()
				.isValidSpawn(Hakkou_Blocks::neverEntity).isSuffocating(Hakkou_Blocks::never);
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()
				.isValidSpawn(Hakkou_Blocks::neverEntity).isSuffocating(Hakkou_Blocks::never);
	}
	
	private static Properties taruRandom() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 4.2F).sound(SoundType.WOOD).noOcclusion()
				.isValidSpawn(Hakkou_Blocks::neverEntity).randomTicks();
	}

	private static Properties stoneState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noCollission().strength(1.0F, 1.0F).sound(SoundType.STONE).noOcclusion()
				.isValidSpawn(Hakkou_Blocks::neverEntity).isSuffocating(Hakkou_Blocks::never).isViewBlocking(Hakkou_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
