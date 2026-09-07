package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.blocks.window.Curtain;
import com.ayutaki.chinjufumod.blocks.window.CurtainLarge;
import com.ayutaki.chinjufumod.blocks.window.CurtainTall;
import com.ayutaki.chinjufumod.blocks.window.Window;
import com.ayutaki.chinjufumod.blocks.window.WindowB;
import com.ayutaki.chinjufumod.blocks.window.WindowTall;
import com.ayutaki.chinjufumod.blocks.window.WindowTall_Bot;
import com.ayutaki.chinjufumod.blocks.window.WindowTall_Top;

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

public class Window_Blocks {
	/* 108 = 93 + (3 * 5) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> WINDOW_oak = register("block_window", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_spruce = register("block_window_spruce", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_birch = register("block_window_birch", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_jungle = register("block_window_jungle", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_acacia = register("block_window_acacia", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_darkoak = register("block_window_darkoak", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_mangrove = register("block_window_mangrove", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_cherry = register("block_window_cherry", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_paleoak = register("block_window_paleoak", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_sakura = register("block_window_sakura", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_kaede = register("block_window_kaede", Window::new, woodState());
	public static final DeferredBlock<Block> WINDOW_ichoh = register("block_window_ichoh", Window::new, woodState());

	public static final DeferredBlock<Block> WINDOWB_oak = register("block_windowb", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_spruce = register("block_windowb_spruce", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_birch = register("block_windowb_birch", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_jungle = register("block_windowb_jungle", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_acacia = register("block_windowb_acacia", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_darkoak = register("block_windowb_darkoak", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_mangrove = register("block_windowb_mangrove", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_cherry = register("block_windowb_cherry", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_paleoak = register("block_windowb_paleoak", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_sakura = register("block_windowb_sakura", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_kaede = register("block_windowb_kaede", WindowB::new, woodState());
	public static final DeferredBlock<Block> WINDOWB_ichoh = register("block_windowb_ichoh", WindowB::new, woodState());

	public static final DeferredBlock<Block> WINDOWTALLBOT_oak = register("block_windowtallbot", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_spruce = register("block_windowtallbot_spruce", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_birch = register("block_windowtallbot_birch", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_jungle = register("block_windowtallbot_jungle", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_acacia = register("block_windowtallbot_acacia", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_darkoak = register("block_windowtallbot_darkoak", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_mangrove = register("block_windowtallbot_mangrove", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_cherry = register("block_windowtallbot_cherry", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_paleoak = register("block_windowtallbot_paleoak", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_sakura = register("block_windowtallbot_sakura", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_kaede = register("block_windowtallbot_kaede", WindowTall_Bot::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLBOT_ichoh = register("block_windowtallbot_ichoh", WindowTall_Bot::new, tallTopBot());

	public static final DeferredBlock<Block> WINDOWTALLTOP_oak = register("block_windowtalltop", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_spruce = register("block_windowtalltop_spruce", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_birch = register("block_windowtalltop_birch", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_jungle = register("block_windowtalltop_jungle", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_acacia = register("block_windowtalltop_acacia", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_darkoak = register("block_windowtalltop_darkoak", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_mangrove = register("block_windowtalltop_mangrove", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_cherry = register("block_windowtalltop_cherry", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_paleoak = register("block_windowtalltop_paleoak", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_sakura = register("block_windowtalltop_sakura", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_kaede = register("block_windowtalltop_kaede", WindowTall_Top::new, tallTopBot());
	public static final DeferredBlock<Block> WINDOWTALLTOP_ichoh = register("block_windowtalltop_ichoh", WindowTall_Top::new, tallTopBot());

	public static final DeferredBlock<Block> WINDOWTALL_oak = register("block_windowtall", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_spruce = register("block_windowtall_spruce", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_birch = register("block_windowtall_birch", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_jungle = register("block_windowtall_jungle", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_acacia = register("block_windowtall_acacia", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_darkoak = register("block_windowtall_darkoak", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_mangrove = register("block_windowtall_mangrove", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_cherry = register("block_windowtall_cherry", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_paleoak = register("block_windowtall_paleoak", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_sakura = register("block_windowtall_sakura", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_kaede = register("block_windowtall_kaede", WindowTall::new, woodState());
	public static final DeferredBlock<Block> WINDOWTALL_ichoh = register("block_windowtall_ichoh", WindowTall::new, woodState());
	
	public static final DeferredBlock<Block> CURTAIN_white = register("block_curtain_white", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_orange = register("block_curtain_orange", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_magenta = register("block_curtain_magenta", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_lightblue = register("block_curtain_lightblue", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_yellow = register("block_curtain_yellow", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_lime = register("block_curtain_lime", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_pink = register("block_curtain_pink", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_gray = register("block_curtain_gray", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_lightgray = register("block_curtain_lightgray", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_cyan = register("block_curtain_cyan", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_purple = register("block_curtain_purple", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_blue = register("block_curtain_blue", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_brown = register("block_curtain_brown", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_green = register("block_curtain_green", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_red = register("block_curtain_red", Curtain::new, curtainState());
	public static final DeferredBlock<Block> CURTAIN_black = register("block_curtain_black", Curtain::new, curtainState());
	
	public static final DeferredBlock<Block> CURTAINTALL_white = register("block_curtaintall_white", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_orange = register("block_curtaintall_orange", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_magenta = register("block_curtaintall_magenta", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_lightblue = register("block_curtaintall_lightblue", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_yellow = register("block_curtaintall_yellow", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_lime = register("block_curtaintall_lime", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_pink = register("block_curtaintall_pink", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_gray = register("block_curtaintall_gray", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_lightgray = register("block_curtaintall_lightgray", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_cyan = register("block_curtaintall_cyan", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_purple = register("block_curtaintall_purple", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_blue = register("block_curtaintall_blue", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_brown = register("block_curtaintall_brown", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_green = register("block_curtaintall_green", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_red = register("block_curtaintall_red", CurtainTall::new, curtainState());
	public static final DeferredBlock<Block> CURTAINTALL_black = register("block_curtaintall_black", CurtainTall::new, curtainState());
	
	public static final DeferredBlock<Block> CURTAINL_white = register("block_curtainlarge_white", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_orange = register("block_curtainlarge_orange", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_magenta = register("block_curtainlarge_magenta", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_lightblue = register("block_curtainlarge_lightblue", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_yellow = register("block_curtainlarge_yellow", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_lime = register("block_curtainlarge_lime", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_pink = register("block_curtainlarge_pink", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_gray = register("block_curtainlarge_gray", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_lightgray = register("block_curtainlarge_lightgray", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_cyan = register("block_curtainlarge_cyan", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_purple = register("block_curtainlarge_purple", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_blue = register("block_curtainlarge_blue", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_brown = register("block_curtainlarge_brown", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_green = register("block_curtainlarge_green", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_red = register("block_curtainlarge_red", CurtainLarge::new, curtainState());
	public static final DeferredBlock<Block> CURTAINL_black = register("block_curtainlarge_black", CurtainLarge::new, curtainState());
	
	
	/* Share variables 要検証 Config_CM.INSTANCE.antiShadow.get() == true && */
	private static ToIntFunction<BlockState> litBlockEmission(int value) {
		return (state) -> { return (state.getValue(BaseStage3_FaceWater.STAGE_1_3) == 3) ? value : 0; };
	}
	
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Window_Blocks::neverEntity).isSuffocating(Window_Blocks::never);
	}
	
	private static Properties tallTopBot() {
		return woodState().lightLevel(litBlockEmission(1));
	}
	
	private static Properties curtainState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 1.0F).sound(SoundType.WOOL)
				.noOcclusion().isValidSpawn(Window_Blocks::neverEntity).isSuffocating(Window_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
