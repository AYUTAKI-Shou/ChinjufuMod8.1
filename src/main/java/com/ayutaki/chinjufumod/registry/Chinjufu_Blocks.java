package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.cmblock.AdmiralStampItem;
import com.ayutaki.chinjufumod.blocks.cmblock.AlumiBlock;
import com.ayutaki.chinjufumod.blocks.cmblock.AmmoBauxiteBox;
import com.ayutaki.chinjufumod.blocks.cmblock.EmptyBox;
import com.ayutaki.chinjufumod.blocks.cmblock.Oil_Drum;
import com.ayutaki.chinjufumod.blocks.cmblock.Report_Box;
import com.ayutaki.chinjufumod.blocks.cmblock.WakeWater1;
import com.ayutaki.chinjufumod.blocks.cmblock.WakeWater2;
import com.ayutaki.chinjufumod.blocks.cmblock.WakeWater3;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Chinjufu_Blocks {
	/* 16 = 15 + ORE_DEEP + COPPER - B_STAMP */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);
	
	public static final DeferredBlock<Block> BAUXITE_ORE = register("block_bauxite_ore", 
			(props) -> new DropExperienceBlock(UniformInt.of(1, 3), props), oreState().strength(3.0F, 3.0F).sound(SoundType.STONE));
	public static final DeferredBlock<Block> BAUXITE_ORE_DEEP = register("block_bauxite_ore_deep", 
			(props) -> new DropExperienceBlock(UniformInt.of(1, 3), props), oreState().strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE));

	public static final DeferredBlock<Block> OIL_DRUM = register("block_fuel_can", Oil_Drum::new, metalState());

	public static final DeferredBlock<Block> EMPTY_BOX = register("block_empty_box", EmptyBox::new, boxState().isSuffocating(Chinjufu_Blocks::never));
	public static final DeferredBlock<Block> AMMO_BOX = register("block_ammunition_box", AmmoBauxiteBox::new, boxState());
	public static final DeferredBlock<Block> BAUXITE_BOX = register("block_bauxite_box", AmmoBauxiteBox::new, boxState());

	public static final DeferredBlock<Block> ALUMI_BLOCK = register("block_alumi_block", AlumiBlock::new, metalState());
	public static final DeferredBlock<Block> STEEL_BLOCK = register("block_steel_block", AlumiBlock::new, metalState());
	public static final DeferredBlock<Block> COPPER_BLOCK = register("block_copper_block", AlumiBlock::new, metalState());
	public static final DeferredBlock<Block> GOLD_BLOCK = register("block_gold_block", AlumiBlock::new, metalState());
	public static final DeferredBlock<Block> NETHERITE_BLOCK = register("block_netherite_block", AlumiBlock::new, metalState());
	
	public static final DeferredBlock<Block> I_ADMIRAL_STAMP = register("item_admiralstamp_b", AdmiralStampItem::new, stampState());
	public static final DeferredBlock<Block> REPORT_BOX = register("block_report_box", Report_Box::new, metalState());

	public static final DeferredBlock<Block> WAKE_WATER1 = register("block_wake_water1", WakeWater1::new, wakeState());
	public static final DeferredBlock<Block> WAKE_WATER2 = register("block_wake_water2", WakeWater2::new, wakeState());
	public static final DeferredBlock<Block> WAKE_WATER3 = register("block_wake_water3", WakeWater3::new, wakeState());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties oreState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops();
	}
	
	private static Properties metalState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(1.0F, 3.0F).noOcclusion()
				.isValidSpawn(Chinjufu_Blocks::neverEntity);
	}
	
	private static Properties boxState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(1.0F, 3.0F)
				.isValidSpawn(Chinjufu_Blocks::neverEntity);
	}
	
	private static Properties stampState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().sound(SoundType.WOOD).strength(0.5F).noOcclusion()
				.isValidSpawn(Chinjufu_Blocks::neverEntity).isSuffocating(Chinjufu_Blocks::never);
	}
	
	private static Properties wakeState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WATER).sound(SoundType.SNOW).strength(0.1F, 3.0F).noOcclusion()
				.isValidSpawn(Chinjufu_Blocks::neverEntity).isSuffocating(Chinjufu_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
