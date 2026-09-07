package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.chair.Zabuton;
import com.ayutaki.chinjufumod.blocks.chair.Zaisu;

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

public class JPChair_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> ZABUTON_white = register("block_mzabuton_white", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_orange = register("block_mzabuton_orange", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_magenta = register("block_mzabuton_magenta", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_lightb = register("block_mzabuton_lightb", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_yellow = register("block_mzabuton_yellow", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_lime = register("block_mzabuton_lime", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_pink = register("block_mzabuton_pink", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_gray = register("block_mzabuton_gray", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_lightg = register("block_mzabuton_lightg", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_cyan = register("block_mzabuton_cyan", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_purple = register("block_mzabuton_purple", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_blue = register("block_mzabuton_blue", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_brown = register("block_mzabuton_brown", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_green = register("block_mzabuton_green", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_red = register("block_mzabuton_red", Zabuton::new, zabuton());
	public static final DeferredBlock<Block> ZABUTON_black = register("block_mzabuton_black", Zabuton::new, zabuton());

	public static final DeferredBlock<Block> WARAZABUTON = register("block_wara_zabuton", Zabuton::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.GRASS).noOcclusion().isValidSpawn(JPChair_Blocks::neverEntity).isSuffocating(JPChair_Blocks::never));

	public static final DeferredBlock<Block> ZAISU_white = register("block_zaisu_white", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_orange = register("block_zaisu_orange", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_magenta = register("block_zaisu_magenta", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_lightb = register("block_zaisu_lightb", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_yellow = register("block_zaisu_yellow", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_lime = register("block_zaisu_lime", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_pink = register("block_zaisu_pink", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_gray = register("block_zaisu_gray", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_lightg = register("block_zaisu_lightg", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_cyan = register("block_zaisu_cyan", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_purple = register("block_zaisu_purple", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_blue = register("block_zaisu_blue", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_brown = register("block_zaisu_brown", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_green = register("block_zaisu_green", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_red = register("block_zaisu_red", Zaisu::new, zaisu());
	public static final DeferredBlock<Block> ZAISU_black = register("block_zaisu_black", Zaisu::new, zaisu());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}

	private static Properties zabuton() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(1.0F, 1.0F).sound(SoundType.WOOL)
				.noOcclusion().isValidSpawn(JPChair_Blocks::neverEntity).isSuffocating(JPChair_Blocks::never);
	}

	private static Properties zaisu() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(JPChair_Blocks::neverEntity).isSuffocating(JPChair_Blocks::never);
	}
	
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
