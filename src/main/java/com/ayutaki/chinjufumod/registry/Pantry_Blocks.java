package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.pantry.CanTea;
import com.ayutaki.chinjufumod.blocks.pantry.Chadutsu;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Box;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Empty;
import com.ayutaki.chinjufumod.blocks.pantry.Pantry_Sack;
import com.ayutaki.chinjufumod.blocks.pantry.Tawara;

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

public class Pantry_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> BOX_H_EMPTY = register("block_boxh_empty", Pantry_Empty::new, boxState());
	public static final DeferredBlock<Block> BOX_H_EMPTY2 = register("block_boxh_empty2", Pantry_Empty::new, boxState());
	public static final DeferredBlock<Block> BOX_H_EMPTY3 = register("block_boxh_empty3", Pantry_Empty::new, sackState());

	public static final DeferredBlock<Block> BOX_H_APPLE = register("block_boxh_apple", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_BEEF = register("block_boxh_beef", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_BEETROOT = register("block_boxh_beetroot", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_BREAD = register("block_boxh_bread", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_CARROT = register("block_boxh_carrot", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_CHICKEN = register("block_boxh_chicken", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_CHORUS = register("block_boxh_chorus", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_COCO = register("block_boxh_coco", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_COD = register("block_boxh_cod", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_EGG = register("block_boxh_egg", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_FISH = register("block_boxh_fish", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_FLOUR = register("block_boxh_flour", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_MUTTON = register("block_boxh_mutton", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_PORK = register("block_boxh_pork", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_POTATO = register("block_boxh_potato", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_RABBIT = register("block_boxh_rabbit", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_SALMON = register("block_boxh_salmon", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_SWBERRY = register("block_boxh_swberry", Pantry_Box::new, boxState());

	public static final DeferredBlock<Block> BOX_H_AZUKI = register("block_boxh_azuki", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_CABBAGE = register("block_boxh_cabbage", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_HAKUSAI = register("block_boxh_hakusai", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_CHERRY = register("block_boxh_cherry", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_CITRUS = register("block_boxh_citrus", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_CORN = register("block_boxh_corn", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_GRAPE = register("block_boxh_grape", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_GREENONION = register("block_boxh_greenonion", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_ONION = register("block_boxh_onion", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_ORIENTCLAM = register("block_boxh_hamaguri", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_RICE = register("block_boxh_rice", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_SOY = register("block_boxh_soy", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_SPINACH = register("block_boxh_spinach", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_SQUID = register("block_boxh_squid", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_TOMATO = register("block_boxh_tomato", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_TAKENOKO = register("block_boxh_takenoko", Pantry_Box::new, boxState());
	public static final DeferredBlock<Block> BOX_H_KURI = register("block_boxh_chestnut", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_TGREEN = register("block_boxh_tgreen", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_TRED = register("block_boxh_tred", Pantry_Sack::new, sackState());

	public static final DeferredBlock<Block> BOX_H_BPEPPER = register("block_boxh_bpepper", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_CUMIN = register("block_boxh_cumin", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_TURMERIC = register("block_boxh_turmeric", Pantry_Sack::new, sackState());
	public static final DeferredBlock<Block> BOX_H_CHILI = register("block_boxh_chili", Pantry_Sack::new, sackState());
	
	public static final DeferredBlock<Block> CHADUTSU = register("block_tea_chadutsu", Chadutsu::new, baseState().sound(SoundType.STONE).isSuffocating(Pantry_Blocks::never));
	public static final DeferredBlock<Block> CANTEA = register("block_tea_can", CanTea::new, baseState().sound(SoundType.STONE).isSuffocating(Pantry_Blocks::never));
	public static final DeferredBlock<Block> TAWARA = register("block_tawara_cm", Tawara::new, baseState().sound(SoundType.GRASS));

	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties baseState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).noOcclusion().isValidSpawn(Pantry_Blocks::neverEntity);
	}
	
	private static Properties boxState() {
		return baseState().sound(SoundType.WOOD);
	}

	private static Properties sackState() {
		return baseState().sound(SoundType.GRASS);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
