package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.gate.Gate_irongrill;
import com.ayutaki.chinjufumod.blocks.gate.Gate_thick;

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

public class Gate_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> GATE_SPRUCE = register("block_gate_spruce", Gate_thick::new, woodState());
	public static final DeferredBlock<Block> GATE_SPRUCE_B = register("block_gate_spruce_b", Gate_thick::new, woodState());
	public static final DeferredBlock<Block> GATE_IRON = register("block_gate_iron", Gate_thick::new, ironState());
	public static final DeferredBlock<Block> GATE_IRONGRILL = register("block_gate_irongrill", Gate_irongrill::new, ironState());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties ironState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F, 20.0F).sound(SoundType.METAL)
				.noOcclusion().isValidSpawn(Gate_Blocks::neverEntity).isSuffocating(Gate_Blocks::never);
	}

	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 10.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Gate_Blocks::neverEntity).isSuffocating(Gate_Blocks::never);
	}
	
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
