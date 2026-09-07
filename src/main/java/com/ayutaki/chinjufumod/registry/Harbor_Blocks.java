package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.harbor.Keikai;
import com.ayutaki.chinjufumod.blocks.harbor.Keiryu;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Amp;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Cable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Harbor_Blocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChinjufuMod.MOD_ID);

	public static final RegistryObject<Block> KEIKAIBLOCK = register("block_keikai", 
			() -> new Keikai(BlockBehaviour.Properties.of(Material.STONE).strength(1.0F, 6.0F).sound(SoundType.STONE).noOcclusion().isValidSpawn(Harbor_Blocks::neverEntity).isSuffocating(Harbor_Blocks::never)));

	public static final RegistryObject<Block> KEIRYUKUI = register("block_keiryukui", () -> keiryu());
	public static final RegistryObject<Block> KEIRYUKUI_b = register("block_keiryukui_b", () -> keiryu());

	public static final RegistryObject<Block> TRUSS = register("block_ctruss", () -> truss());
	public static final RegistryObject<Block> TRUSS_white = register("block_ctruss_white", () -> truss());
	public static final RegistryObject<Block> TRUSS_orange = register("block_ctruss_orange", () -> truss());
	public static final RegistryObject<Block> TRUSS_magenta = register("block_ctruss_magenta", () -> truss());
	public static final RegistryObject<Block> TRUSS_lightb = register("block_ctruss_lightb", () -> truss());
	public static final RegistryObject<Block> TRUSS_yellow = register("block_ctruss_yellow", () -> truss());
	public static final RegistryObject<Block> TRUSS_lime = register("block_ctruss_lime", () -> truss());
	public static final RegistryObject<Block> TRUSS_pink = register("block_ctruss_pink", () -> truss());
	public static final RegistryObject<Block> TRUSS_gray = register("block_ctruss_gray", () -> truss());
	public static final RegistryObject<Block> TRUSS_cyan = register("block_ctruss_cyan", () -> truss());
	public static final RegistryObject<Block> TRUSS_purple = register("block_ctruss_purple", () -> truss());
	public static final RegistryObject<Block> TRUSS_blue = register("block_ctruss_blue", () -> truss());
	public static final RegistryObject<Block> TRUSS_brown = register("block_ctruss_brown", () -> truss());
	public static final RegistryObject<Block> TRUSS_green = register("block_ctruss_green", () -> truss());
	public static final RegistryObject<Block> TRUSS_red = register("block_ctruss_red", () -> truss());
	public static final RegistryObject<Block> TRUSS_black = register("block_ctruss_black", () -> truss());
	
	public static final RegistryObject<Block> AMP = register("block_amp", () -> amp());
	public static final RegistryObject<Block> AMP_white = register("block_amp_white", () -> amp());
	public static final RegistryObject<Block> AMP_orange = register("block_amp_orange", () -> amp());
	public static final RegistryObject<Block> AMP_magenta = register("block_amp_magenta", () -> amp());
	public static final RegistryObject<Block> AMP_lightb = register("block_amp_lightblue", () -> amp());
	public static final RegistryObject<Block> AMP_yellow = register("block_amp_yellow", () -> amp());
	public static final RegistryObject<Block> AMP_lime = register("block_amp_lime", () -> amp());
	public static final RegistryObject<Block> AMP_pink = register("block_amp_pink", () -> amp());
	public static final RegistryObject<Block> AMP_gray = register("block_amp_gray", () -> amp());
	public static final RegistryObject<Block> AMP_cyan = register("block_amp_cyan", () -> amp());
	public static final RegistryObject<Block> AMP_purple = register("block_amp_purple", () -> amp());
	public static final RegistryObject<Block> AMP_blue = register("block_amp_blue", () -> amp());
	public static final RegistryObject<Block> AMP_brown = register("block_amp_brown", () -> amp());
	public static final RegistryObject<Block> AMP_green = register("block_amp_green", () -> amp());
	public static final RegistryObject<Block> AMP_red = register("block_amp_red", () -> amp());
	public static final RegistryObject<Block> AMP_black = register("block_amp_black", () -> amp());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties metalState() {
		return BlockBehaviour.Properties.of(Material.METAL).strength(1.0F, 6.0F).sound(SoundType.METAL)
				.noOcclusion().isValidSpawn(Harbor_Blocks::neverEntity).isSuffocating(Harbor_Blocks::never);
	}
	
	private static Truss_Cable truss() {
		return new Truss_Cable(metalState());
	}
	
	private static Truss_Amp amp() {
		return new Truss_Amp(metalState());
	}
	
	private static Keiryu keiryu() {
		return new Keiryu(metalState());
	}

	///* Register *///
	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
		return BLOCKS.register(name, block);
	}
}
