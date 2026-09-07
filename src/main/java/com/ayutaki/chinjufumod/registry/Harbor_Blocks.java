package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.harbor.Keikai;
import com.ayutaki.chinjufumod.blocks.harbor.Keiryu;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Amp;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Cable;

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

public class Harbor_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> KEIKAIBLOCK = register("block_keikai", Keikai::new, stoneState());

	public static final DeferredBlock<Block> KEIRYUKUI = register("block_keiryukui", Keiryu::new, metalState());
	public static final DeferredBlock<Block> KEIRYUKUI_b = register("block_keiryukui_b", Keiryu::new, metalState());

	public static final DeferredBlock<Block> TRUSS = register("block_ctruss", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_white = register("block_ctruss_white", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_orange = register("block_ctruss_orange", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_magenta = register("block_ctruss_magenta", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_lightb = register("block_ctruss_lightb", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_yellow = register("block_ctruss_yellow", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_lime = register("block_ctruss_lime", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_pink = register("block_ctruss_pink", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_gray = register("block_ctruss_gray", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_cyan = register("block_ctruss_cyan", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_purple = register("block_ctruss_purple", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_blue = register("block_ctruss_blue", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_brown = register("block_ctruss_brown", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_green = register("block_ctruss_green", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_red = register("block_ctruss_red", Truss_Cable::new, metalState());
	public static final DeferredBlock<Block> TRUSS_black = register("block_ctruss_black", Truss_Cable::new, metalState());	
	
	public static final DeferredBlock<Block> AMP = register("block_amp", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_white = register("block_amp_white", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_orange = register("block_amp_orange", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_magenta = register("block_amp_magenta", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_lightb = register("block_amp_lightblue", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_yellow = register("block_amp_yellow", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_lime = register("block_amp_lime", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_pink = register("block_amp_pink", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_gray = register("block_amp_gray", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_cyan = register("block_amp_cyan", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_purple = register("block_amp_purple", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_blue = register("block_amp_blue", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_brown = register("block_amp_brown", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_green = register("block_amp_green", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_red = register("block_amp_red", Truss_Amp::new, metalState());	
	public static final DeferredBlock<Block> AMP_black = register("block_amp_black", Truss_Amp::new, metalState());	
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties stoneState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE)
				.noOcclusion().isValidSpawn(Harbor_Blocks::neverEntity).isSuffocating(Harbor_Blocks::never);
	}
	
	private static Properties metalState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F, 6.0F).sound(SoundType.METAL)
				.noOcclusion().isValidSpawn(Harbor_Blocks::neverEntity).isSuffocating(Harbor_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
