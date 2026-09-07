package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.gakki.Wadaiko_Large;
import com.ayutaki.chinjufumod.blocks.gakki.Wadaiko_Small;
import com.ayutaki.chinjufumod.blocks.jpdeco.Andon;
import com.ayutaki.chinjufumod.blocks.jpdeco.BambooCube;
import com.ayutaki.chinjufumod.blocks.jpdeco.BambooFence;
import com.ayutaki.chinjufumod.blocks.jpdeco.BambooFenceGate;
import com.ayutaki.chinjufumod.blocks.jpdeco.BambooPressurePlate;
import com.ayutaki.chinjufumod.blocks.jpdeco.BambooSlab;
import com.ayutaki.chinjufumod.blocks.jpdeco.BambooStairs;
import com.ayutaki.chinjufumod.blocks.jpdeco.BaseTatami;
import com.ayutaki.chinjufumod.blocks.jpdeco.Futon;
import com.ayutaki.chinjufumod.blocks.jpdeco.Tatami;
import com.ayutaki.chinjufumod.blocks.jpdeco.Tatami_Y;
import com.ayutaki.chinjufumod.blocks.season.Door_CM;
import com.ayutaki.chinjufumod.blocks.season.TrapDoor_CM;
import com.ayutaki.chinjufumod.blocks.season.WoodButton_CM;
import com.ayutaki.chinjufumod.state.TatamiType;

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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JPDeco_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> TATAMI_H = register("block_tatamih", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_white = register("block_tatamih_white", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_orange = register("block_tatamih_orange", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_magenta = register("block_tatamih_magenta", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_lightb = register("block_tatamih_lightb", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_yellow = register("block_tatamih_yellow", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_lime = register("block_tatamih_lime", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_pink = register("block_tatamih_pink", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_gray = register("block_tatamih_gray", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_lightg = register("block_tatamih_lightg", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_cyan = register("block_tatamih_cyan", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_purple = register("block_tatamih_purple", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_blue = register("block_tatamih_blue", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_brown = register("block_tatamih_brown", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_green = register("block_tatamih_green", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_red = register("block_tatamih_red", Tatami::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_H_black = register("block_tatamih_black", Tatami::new, tatamiState());

	public static final DeferredBlock<Block> TATAMI_HY = register("block_tatamih_y", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_white = register("block_tatamih_y_white", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_orange = register("block_tatamih_y_orange", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_magenta = register("block_tatamih_y_magenta", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_lightb = register("block_tatamih_y_lightb", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_yellow = register("block_tatamih_y_yellow", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_lime = register("block_tatamih_y_lime", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_pink = register("block_tatamih_y_pink", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_gray = register("block_tatamih_y_gray", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_lightg = register("block_tatamih_y_lightg", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_cyan = register("block_tatamih_y_cyan", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_purple = register("block_tatamih_y_purple", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_blue = register("block_tatamih_y_blue", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_brown = register("block_tatamih_y_brown", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_green = register("block_tatamih_y_green", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_red = register("block_tatamih_y_red", Tatami_Y::new, tatamiState());
	public static final DeferredBlock<Block> TATAMI_HY_black = register("block_tatamih_y_black", Tatami_Y::new, tatamiState());

	public static final DeferredBlock<Block> TAKECUBE = register("block_bamboo_cube", BambooCube::new, bambooCube());
	public static final DeferredBlock<Block> TAKECUBE_Y = register("block_bamboo_y_cube", BambooCube::new, bambooCube());
	public static final DeferredBlock<Block> TAKECUBE_K = register("block_bamboo_k_cube", BambooCube::new, bambooCube());
	public static final DeferredBlock<Block> TAKE_ST = register("block_bamboo_stairs", (props) -> new BambooStairs(TAKECUBE.get().defaultBlockState(), props), bambooStairs());
	public static final DeferredBlock<Block> TAKE_STY = register("block_bamboo_y_stairs", (props) -> new BambooStairs(TAKECUBE.get().defaultBlockState(), props), bambooStairs());
	public static final DeferredBlock<Block> TAKE_STK = register("block_bamboo_k_stairs", (props) -> new BambooStairs(TAKECUBE.get().defaultBlockState(), props), bambooStairs());
	public static final DeferredBlock<Block> TAKE_SH = register("block_bamboo_slab", BambooSlab::new, bambooSlab());
	public static final DeferredBlock<Block> TAKE_SHY = register("block_bamboo_y_slab", BambooSlab::new, bambooSlab());
	public static final DeferredBlock<Block> TAKE_SHK = register("block_bamboo_k_slab", BambooSlab::new, bambooSlab());

	public static final DeferredBlock<Block> TAKEFENCE = register("block_bamboo_fence", BambooFence::new, bambooNever());
	public static final DeferredBlock<Block> TAKEFENCE_Y = register("block_bamboo_y_fence", BambooFence::new, bambooNever());
	public static final DeferredBlock<Block> TAKEFENCE_K = register("block_bamboo_k_fence", BambooFence::new, bambooNever());
	public static final DeferredBlock<Block> TAKEFENCEGATE = register("block_bamboo_fencegate", (props) -> new BambooFenceGate(WoodType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKEFENCEGATE_Y = register("block_bamboo_y_fencegate", (props) -> new BambooFenceGate(WoodType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKEFENCEGATE_K = register("block_bamboo_k_fencegate", (props) -> new BambooFenceGate(WoodType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKEDOOR = register("block_bamboo_door", (props) -> new Door_CM(BlockSetType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKEDOOR_Y = register("block_bamboo_y_door", (props) -> new Door_CM(BlockSetType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKEDOOR_K = register("block_bamboo_k_door", (props) -> new Door_CM(BlockSetType.BAMBOO, props), bambooNever());

	public static final DeferredBlock<Block> TAKE_TRAPDOOR = register("block_bamboo_trapdoor", (props) -> new TrapDoor_CM(BlockSetType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKE_TRAPDOOR_Y = register("block_bamboo_y_trapdoor", (props) -> new TrapDoor_CM(BlockSetType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKE_TRAPDOOR_K = register("block_bamboo_k_trapdoor", (props) -> new TrapDoor_CM(BlockSetType.BAMBOO, props), bambooNever());
	public static final DeferredBlock<Block> TAKE_PLATE = register("block_bamboo_plate", (props) -> new BambooPressurePlate(BlockSetType.BAMBOO, props), plateState());
	public static final DeferredBlock<Block> TAKE_PLATE_Y = register("block_bamboo_y_plate", (props) -> new BambooPressurePlate(BlockSetType.BAMBOO, props), plateState());
	public static final DeferredBlock<Block> TAKE_PLATE_K = register("block_bamboo_k_plate", (props) -> new BambooPressurePlate(BlockSetType.BAMBOO, props), plateState());
	public static final DeferredBlock<Block> TAKE_BUTTON = register("block_bamboo_button", (props) -> new WoodButton_CM(BlockSetType.BAMBOO, 30, props), buttonState());
	public static final DeferredBlock<Block> TAKE_BUTTON_Y = register("block_bamboo_y_button", (props) -> new WoodButton_CM(BlockSetType.BAMBOO, 30, props), buttonState());
	public static final DeferredBlock<Block> TAKE_BUTTON_K = register("block_bamboo_k_button", (props) -> new WoodButton_CM(BlockSetType.BAMBOO, 30, props), buttonState());

	public static final DeferredBlock<Block> ANDON_white = register("block_andon_white", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_orange = register("block_andon_orange", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_magenta = register("block_andon_magenta", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_lightb = register("block_andon_lightb", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_yellow = register("block_andon_yellow", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_lime = register("block_andon_lime", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_pink = register("block_andon_pink", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_gray = register("block_andon_gray", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_lightg = register("block_andon_lightg", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_cyan = register("block_andon_cyan", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_purple = register("block_andon_purple", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_blue = register("block_andon_blue", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_brown = register("block_andon_brown", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_green = register("block_andon_green", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_red = register("block_andon_red", Andon::new, andon());
	public static final DeferredBlock<Block> ANDON_black = register("block_andon_black", Andon::new, andon());

	public static final DeferredBlock<Block> FUTON_white = register("block_futon_c_white", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_orange = register("block_futon_c_orange", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_magenta = register("block_futon_c_magenta", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_lightb = register("block_futon_c_lightb", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_yellow = register("block_futon_c_yellow", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_lime = register("block_futon_c_lime", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_pink = register("block_futon_c_pink", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_gray = register("block_futon_c_gray", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_lightg = register("block_futon_c_lightg", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_cyan = register("block_futon_c_cyan", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_purple = register("block_futon_c_purple", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_blue = register("block_futon_c_blue", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_brown = register("block_futon_c_brown", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_green = register("block_futon_c_green", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_red = register("block_futon_c_red", Futon::new, futon());
	public static final DeferredBlock<Block> FUTON_black = register("block_futon_c_black", Futon::new, futon());

	public static final DeferredBlock<Block> WADAIKO = register("block_wadaiko", Wadaiko_Large::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never));
	public static final DeferredBlock<Block> WADAIKO_small = register("block_wadaiko_small", Wadaiko_Small::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never));

	
	/* Share variables */
	private static ToIntFunction<BlockState> litBlockEmission(int value) {
		return (state) -> { return state.getValue(BlockStateProperties.LIT) ? value : 0; };
	}
	
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static boolean neverTatami(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.getValue(BaseTatami.TYPE) != TatamiType.BOTTOM && state.getValue(BaseTatami.TYPE) != TatamiType.TOP)? true : false;
	}

	private static Boolean neverEntityTatami(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(BaseTatami.TYPE) == TatamiType.BOTTOM)? (boolean)false : (boolean)true;
	}
	
	private static boolean neverSlab(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.getValue(BambooSlab.TYPE) == SlabType.DOUBLE)? true : false;
	}

	private static Boolean neverEntitySlab(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(BambooSlab.TYPE) == SlabType.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityStairs(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(BambooStairs.TYPE) == Half.BOTTOM)? (boolean)false : (boolean)true;
	}
	
	private static Properties tatamiState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.GRASS)
				.isValidSpawn(JPDeco_Blocks::neverEntityTatami).isSuffocating(JPDeco_Blocks::neverTatami);
	}

	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD);
	}

	private static Properties bambooCube() {
		return woodState().noOcclusion();
	}

	private static Properties bambooStairs() {
		return woodState().noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntityStairs).isSuffocating(JPDeco_Blocks::never);
	}

	private static Properties bambooSlab() {
		return woodState().noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntitySlab).isSuffocating(JPDeco_Blocks::neverSlab);
	}

	private static Properties bambooNever() {
		return woodState().noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never);
	}

	private static Properties plateState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD)
				.noCollission().noOcclusion().forceSolidOn().ignitedByLava().pushReaction(PushReaction.DESTROY)
				.isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never);
	}

	private static Properties buttonState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD)
				.noCollission().noOcclusion().pushReaction(PushReaction.DESTROY)
				.isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never);
	}

	private static Properties andon() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 1.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never).lightLevel(litBlockEmission(14));
	}

	private static Properties futon() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(1.0F, 1.0F).sound(SoundType.WOOL)
				.noOcclusion().isValidSpawn(JPDeco_Blocks::neverEntity).isSuffocating(JPDeco_Blocks::never);
	}


	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
