package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.amado.Amado;
import com.ayutaki.chinjufumod.blocks.amado.AmadoWindow;
import com.ayutaki.chinjufumod.blocks.amado.Tobukuro;
import com.ayutaki.chinjufumod.blocks.amado.TobukuroWindow;
import com.ayutaki.chinjufumod.blocks.amado.Tobukuro_L;
import com.ayutaki.chinjufumod.blocks.slidedoor.Fusuma;
import com.ayutaki.chinjufumod.blocks.slidedoor.Fusuma_B;
import com.ayutaki.chinjufumod.blocks.slidedoor.GlassDoor;
import com.ayutaki.chinjufumod.blocks.slidedoor.GlassDoorHalf;
import com.ayutaki.chinjufumod.blocks.slidedoor.Shouji;
import com.ayutaki.chinjufumod.blocks.slidedoor.ShoujiHalf;
import com.ayutaki.chinjufumod.blocks.slidedoor.ShoujiWindow;

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

public class Slidedoor_Blocks {
	/* 138 = 114 + (3 * 8) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> FUSUMA_white = register("block_fusuma", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_orange = register("block_fusuma_orange", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_magenta = register("block_fusuma_magenta", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_lightb = register("block_fusuma_lightb", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_yellow = register("block_fusuma_yellow", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_lime = register("block_fusuma_lime", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_pink = register("block_fusuma_pink", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_gray = register("block_fusuma_gray", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_lightg = register("block_fusuma_lightg", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_cyan = register("block_fusuma_cyan", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_purple = register("block_fusuma_purple", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_blue = register("block_fusuma_blue", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_brown = register("block_fusuma_brown", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_green = register("block_fusuma_green", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_red = register("block_fusuma_red", Fusuma::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMA_black = register("block_fusuma_black", Fusuma::new, woodSlidedoor());

	public static final DeferredBlock<Block> FUSUMAB_white = register("block_fusumab", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_orange = register("block_fusumab_orange", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_magenta = register("block_fusumab_magenta", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_lightb = register("block_fusumab_lightb", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_yellow = register("block_fusumab_yellow", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_lime = register("block_fusumab_lime", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_pink = register("block_fusumab_pink", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_gray = register("block_fusumab_gray", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_lightg = register("block_fusumab_lightg", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_cyan = register("block_fusumab_cyan", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_purple = register("block_fusumab_purple", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_blue = register("block_fusumab_blue", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_brown = register("block_fusumab_brown", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_green = register("block_fusumab_green", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_red = register("block_fusumab_red", Fusuma_B::new, woodSlidedoor());
	public static final DeferredBlock<Block> FUSUMAB_black = register("block_fusumab_black", Fusuma_B::new, woodSlidedoor());

	public static final DeferredBlock<Block> GARASUDO = register("block_garasudo", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_SPRU = register("block_garasudo_spruce", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_BIR = register("block_garasudo_birch", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_JUN = register("block_garasudo_jungle", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_ACA = register("block_garasudo_acacia", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_DOAK = register("block_garasudo_darkoak", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_MANGROVE = register("block_garasudo_mangrove", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_CHERRY = register("block_garasudo_cherry", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_PALEOAK = register("block_garasudo_paleoak", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_SAKU = register("block_garasudo_sakura", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_KAE = register("block_garasudo_kaede", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDO_ICH = register("block_garasudo_ichoh", GlassDoor::new, woodSlidedoor());

	public static final DeferredBlock<Block> GARASUDOB = register("block_garasudob", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_SPRU = register("block_garasudob_spruce", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_BIR = register("block_garasudob_birch", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_JUN = register("block_garasudob_jungle", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_ACA = register("block_garasudob_acacia", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_DOAK = register("block_garasudob_darkoak", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_MANGROVE = register("block_garasudob_mangrove", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_CHERRY = register("block_garasudob_cherry", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_PALEOAK = register("block_garasudob_paleoak", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_SAKU = register("block_garasudob_sakura", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_KAE = register("block_garasudob_kaede", GlassDoor::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOB_ICH = register("block_garasudob_ichoh", GlassDoor::new, woodSlidedoor());

	public static final DeferredBlock<Block> GARASUDOH = register("block_garasudohalf", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_SPRU = register("block_garasudohalf_spruce", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_BIR = register("block_garasudohalf_birch", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_JUN = register("block_garasudohalf_jungle", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_ACA = register("block_garasudohalf_acacia", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_DOAK = register("block_garasudohalf_darkoak", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_MANGROVE = register("block_garasudohalf_mangrove", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_CHERRY = register("block_garasudohalf_cherry", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_PALEOAK = register("block_garasudohalf_paleoak", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_SAKU = register("block_garasudohalf_sakura", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_KAE = register("block_garasudohalf_kaede", GlassDoorHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> GARASUDOH_ICH = register("block_garasudohalf_ichoh", GlassDoorHalf::new, woodSlidedoor());

	public static final DeferredBlock<Block> SHOUJI = register("block_shouji", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_SPRU = register("block_shouji_spruce", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_BIR = register("block_shouji_birch", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_JUN = register("block_shouji_jungle", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_ACA = register("block_shouji_acacia", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_DOAK = register("block_shouji_darkoak", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_MANGROVE = register("block_shouji_mangrove", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_CHERRY = register("block_shouji_cherry", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_PALEOAK = register("block_shouji_paleoak", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_SAKU = register("block_shouji_sakura", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_KAE = register("block_shouji_kaede", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_ICH = register("block_shouji_ichoh", Shouji::new, woodSlidedoor());

	public static final DeferredBlock<Block> SHOUJIB = register("block_shoujib", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_SPRU = register("block_shoujib_spruce", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_BIR = register("block_shoujib_birch", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_JUN = register("block_shoujib_jungle", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_ACA = register("block_shoujib_acacia", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_DOAK = register("block_shoujib_darkoak", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_MANGROVE = register("block_shoujib_mangrove", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_CHERRY = register("block_shoujib_cherry", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_PALEOAK = register("block_shoujib_paleoak", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_SAKU = register("block_shoujib_sakura", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_KAE = register("block_shoujib_kaede", Shouji::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIB_ICH = register("block_shoujib_ichoh", Shouji::new, woodSlidedoor());

	public static final DeferredBlock<Block> SHOUJIH = register("block_shoujihalf", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_SPRU = register("block_shoujihalf_spruce", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_BIR = register("block_shoujihalf_birch", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_JUN = register("block_shoujihalf_jungle", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_ACA = register("block_shoujihalf_acacia", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_DOAK = register("block_shoujihalf_darkoak", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_MANGROVE = register("block_shoujihalf_mangrove", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_CHERRY = register("block_shoujihalf_cherry", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_PALEOAK = register("block_shoujihalf_paleoak", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_SAKU = register("block_shoujihalf_sakura", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_KAE = register("block_shoujihalf_kaede", ShoujiHalf::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJIH_ICH = register("block_shoujihalf_ichoh", ShoujiHalf::new, woodSlidedoor());

	public static final DeferredBlock<Block> SHOUJI_WIN = register("block_shoujih", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_SPRU = register("block_shoujih_spruce", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_BIR = register("block_shoujih_birch", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_JUN = register("block_shoujih_jungle", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_ACA = register("block_shoujih_acacia", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_DOAK = register("block_shoujih_darkoak", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_MANGROVE = register("block_shoujih_mangrove", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_CHERRY = register("block_shoujih_cherry", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_PALEOAK = register("block_shoujih_paleoak", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_SAKU = register("block_shoujih_sakura", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_KAE = register("block_shoujih_kaede", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WIN_ICH = register("block_shoujih_ichoh", ShoujiWindow::new, woodSlidedoor());

	public static final DeferredBlock<Block> SHOUJI_WINR = register("block_shoujihr", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_SPRU = register("block_shoujihr_spruce", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_BIR = register("block_shoujihr_birch", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_JUN = register("block_shoujihr_jungle", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_ACA = register("block_shoujihr_acacia", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_DOAK = register("block_shoujihr_darkoak", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_MANGROVE = register("block_shoujihr_mangrove", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_CHERRY = register("block_shoujihr_cherry", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_PALEOAK = register("block_shoujihr_paleoak", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_SAKU = register("block_shoujihr_sakura", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_KAE = register("block_shoujihr_kaede", ShoujiWindow::new, woodSlidedoor());
	public static final DeferredBlock<Block> SHOUJI_WINR_ICH = register("block_shoujihr_ichoh", ShoujiWindow::new, woodSlidedoor());

	public static final DeferredBlock<Block> AMADO_S = register("block_amado_spruce", Amado::new, amadoState());
	public static final DeferredBlock<Block> TOBUKURO_S = register("block_tobukuro_spruce", Tobukuro::new, amadoState());
	public static final DeferredBlock<Block> TOBUKURO_SL = register("block_tobukuro_sprucel", Tobukuro_L::new, amadoState());

	public static final DeferredBlock<Block> AMADOWIN_S = register("block_amadowin_spruce", AmadoWindow::new, amadoState());
	public static final DeferredBlock<Block> TOBUKUROWIN_S = register("block_tobukurowin_spruce", TobukuroWindow::new, amadoState());

	public static final DeferredBlock<Block> AMADO = register("block_amado", Amado::new, amadoState());
	public static final DeferredBlock<Block> TOBUKURO = register("block_tobukuro", Tobukuro::new, amadoState());
	public static final DeferredBlock<Block> TOBUKURO_L = register("block_tobukuro_l", Tobukuro_L::new, amadoState());

	public static final DeferredBlock<Block> AMADOWIN = register("block_amadowin", AmadoWindow::new, amadoState());
	public static final DeferredBlock<Block> TOBUKUROWIN = register("block_tobukurowin", TobukuroWindow::new, amadoState());

	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	/* 要検証 Config_CM.INSTANCE.antiShadow.get() == true && */
	private static ToIntFunction<BlockState> litBlockEmission(int value) {
		return (state) -> { return value; };
	}
	
	private static Properties woodSlidedoor() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Slidedoor_Blocks::neverEntity).isSuffocating(Slidedoor_Blocks::never).lightLevel(litBlockEmission(1));
	}
	
	private static Properties amadoState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 5.0F).sound(SoundType.WOOD).noOcclusion()
				.isValidSpawn(Slidedoor_Blocks::neverEntity).isSuffocating(Slidedoor_Blocks::never); 
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
