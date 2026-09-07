package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.season.Hinadan;
import com.ayutaki.chinjufumod.blocks.season.Hinakazari;
import com.ayutaki.chinjufumod.blocks.season.Kadomatsu;
import com.ayutaki.chinjufumod.blocks.season.Kagamimochi;
import com.ayutaki.chinjufumod.blocks.season.Kakigouri;
import com.ayutaki.chinjufumod.blocks.season.Kouri_Hata;
import com.ayutaki.chinjufumod.blocks.season.KusaRoof;
import com.ayutaki.chinjufumod.blocks.season.KusaTaba;
import com.ayutaki.chinjufumod.blocks.season.PresentBox;
import com.ayutaki.chinjufumod.blocks.season.Shimenawa;
import com.ayutaki.chinjufumod.blocks.season.SnowCore;
import com.ayutaki.chinjufumod.blocks.season.SnowMan;
import com.ayutaki.chinjufumod.blocks.season.SnowMan_Color;
import com.ayutaki.chinjufumod.blocks.season.Uchiwa;
import com.ayutaki.chinjufumod.blocks.season.Watagashi_block;
import com.ayutaki.chinjufumod.blocks.season.XmasTree;
import com.ayutaki.chinjufumod.blocks.wood.WoodStairs_CM;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
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

public class Seasonal_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> KUSATABA = register("block_tabakusa", KusaTaba::new, kusaState());
	public static final DeferredBlock<Block> WARATABA = register("block_tabawara", KusaTaba::new, kusaState());
	public static final DeferredBlock<Block> KAYATABA = register("block_tabakaya", KusaTaba::new, kusaState());
	public static final DeferredBlock<Block> KUSATABADUMMY = register("block_tabakusadummy", Block::new, kusaState());

	public static final DeferredBlock<Block> KUSATABA_RF = register("block_tabakusa_roof", KusaRoof::new, kusaRoof());
	public static final DeferredBlock<Block> WARATABA_RF = register("block_tabawara_roof", KusaRoof::new, kusaRoof());
	public static final DeferredBlock<Block> KAYATABA_RF = register("block_tabakaya_roof", KusaRoof::new, kusaRoof());

	public static final DeferredBlock<Block> KUSATABA_STAIRS = register("block_tabakusa_stairs", (props) -> new WoodStairs_CM(KUSATABADUMMY.get().defaultBlockState(), props), kusaStairs());
	public static final DeferredBlock<Block> WARATABA_STAIRS = register("block_tabawara_stairs", (props) -> new WoodStairs_CM(KUSATABADUMMY.get().defaultBlockState(), props), kusaStairs());
	public static final DeferredBlock<Block> KAYATABA_STAIRS = register("block_tabakaya_stairs", (props) -> new WoodStairs_CM(KUSATABADUMMY.get().defaultBlockState(), props), kusaStairs());

	public static final DeferredBlock<Block> KADOMATSU = register("block_kadomatsu", Kadomatsu::new, baseState().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> SHIMENAWA = register("block_shimenawa", Shimenawa::new, baseState().sound(SoundType.GRASS));
	public static final DeferredBlock<Block> KAGAMIMOCHI = register("block_kagamimochi", Kagamimochi::new, baseState().sound(SoundType.WOOD));

	public static final DeferredBlock<Block> HINAKAZARI = register("block_hinakazari", Hinakazari::new, baseState().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> HINADAN = register("block_hinadan", Hinadan::new, baseState().sound(SoundType.WOOD));

	public static final DeferredBlock<Block> XMASTREE = register("block_xmastree", XmasTree::new, baseState().sound(SoundType.WOOD));
	public static final DeferredBlock<Block> XMASTREE_W = register("block_xmastree_w", XmasTree::new, baseState().sound(SoundType.WOOD));

	public static final DeferredBlock<Block> PRESENT_app = register("block_present_app", PresentBox::new, present());
	public static final DeferredBlock<Block> PRESENT_bok = register("block_present_bok", PresentBox::new, present());
	public static final DeferredBlock<Block> PRESENT_dia = register("block_present_dia", PresentBox::new, present());
	public static final DeferredBlock<Block> PRESENT_lap = register("block_present_lap", PresentBox::new, present());
	public static final DeferredBlock<Block> PRESENT_bla = register("block_present_bla", PresentBox::new, present());
	public static final DeferredBlock<Block> PRESENT_chc = register("block_present_chc", PresentBox::new, present());
	public static final DeferredBlock<Block> PRESENT_chh = register("block_present_chh", PresentBox::new, present());

	public static final DeferredBlock<Block> UCHIWA_white = register("block_uchiwa_white", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_orange = register("block_uchiwa_orange", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_magenta = register("block_uchiwa_magenta", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_lightb = register("block_uchiwa_lightb", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_yellow = register("block_uchiwa_yellow", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_lime = register("block_uchiwa_lime", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_pink = register("block_uchiwa_pink", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_gray = register("block_uchiwa_gray", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_lightg = register("block_uchiwa_lightg", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_cyan = register("block_uchiwa_cyan", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_purple = register("block_uchiwa_purple", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_blue = register("block_uchiwa_blue", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_brown = register("block_uchiwa_brown", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_green = register("block_uchiwa_green", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_red = register("block_uchiwa_red", Uchiwa::new, uchiwa());
	public static final DeferredBlock<Block> UCHIWA_black = register("block_uchiwa_black", Uchiwa::new, uchiwa());

	public static final DeferredBlock<Block> WATAGASHI_block = register("block_watagashi", Watagashi_block::new, wataame());
	public static final DeferredBlock<Block> WATAGASHI_apple = register("block_watagashi_yellow", Watagashi_block::new, wataame());
	public static final DeferredBlock<Block> WATAGASHI_cherry = register("block_watagashi_pink", Watagashi_block::new, wataame());
	public static final DeferredBlock<Block> WATAGASHI_citrus = register("block_watagashi_orange", Watagashi_block::new, wataame());
	public static final DeferredBlock<Block> WATAGASHI_grape = register("block_watagashi_red", Watagashi_block::new, wataame());
	public static final DeferredBlock<Block> WATAGASHI_tea = register("block_watagashi_green", Watagashi_block::new, wataame());
	
	public static final DeferredBlock<Block> KAKIGOURI_hata = register("block_kakigouri_hata", Kouri_Hata::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(1.0F, 1.0F).sound(SoundType.WOOL).noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never));
	public static final DeferredBlock<Block> KAKIGOURI_block = register("block_kakigouri_block1", Kakigouri::new, kakigouri());
	public static final DeferredBlock<Block> KAKIGOURI_apple = register("block_kakigouri_yellow1", Kakigouri::new, kakigouri());
	public static final DeferredBlock<Block> KAKIGOURI_cherry = register("block_kakigouri_pink1", Kakigouri::new, kakigouri());
	public static final DeferredBlock<Block> KAKIGOURI_citrus = register("block_kakigouri_orange1", Kakigouri::new, kakigouri());
	public static final DeferredBlock<Block> KAKIGOURI_grape = register("block_kakigouri_red1", Kakigouri::new, kakigouri());
	public static final DeferredBlock<Block> KAKIGOURI_tea = register("block_kakigouri_green1", Kakigouri::new, kakigouri());
	
	public static final DeferredBlock<Block> SNOWCORE = register("block_snowcore", (props) -> new SnowCore(new ColorRGBA(15792895), props), snowState());
	public static final DeferredBlock<Block> SNOWMAN = register("block_snowman", SnowMan::new, snowState());
	public static final DeferredBlock<Block> SNOWMAN_COLOR = register("block_snowman_color", SnowMan_Color::new, snowState());

	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}

	private static boolean neverSlab(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.getValue(KusaRoof.TYPE) == SlabType.DOUBLE)? true : false;
	}

	private static Boolean neverEntitySlab(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(KusaRoof.TYPE) == SlabType.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityStairs(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(WoodStairs_CM.HALF) == Half.BOTTOM)? (boolean)false : (boolean)true;
	}
	
	private static Properties baseState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).noOcclusion()
				.isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never);
	}
	
	private static Properties kusaState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(1.0F, 3.0F).sound(SoundType.GRASS);
	}
	
	private static Properties snowState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(1.0F, 1.0F).sound(SoundType.SNOW).noOcclusion()
				.isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never);
	}

	private static Properties kusaRoof() {
		return kusaState().noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntitySlab).isSuffocating(Seasonal_Blocks::neverSlab);
	}

	private static Properties kusaStairs() {
		return kusaState().noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntityStairs).isSuffocating(Seasonal_Blocks::never);
	}
	
	private static Properties present() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never);
	}
	
	private static Properties uchiwa() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(1.0F, 1.0F).sound(SoundType.WOOL)
				.noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never);
	}
	
	private static Properties wataame() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never);
	}

	private static Properties kakigouri() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).noCollission().strength(1.0F, 1.0F).sound(SoundType.STONE)
				.noOcclusion().isValidSpawn(Seasonal_Blocks::neverEntity).isSuffocating(Seasonal_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
