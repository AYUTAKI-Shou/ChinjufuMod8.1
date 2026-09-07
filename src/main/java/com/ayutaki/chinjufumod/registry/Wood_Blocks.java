package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.kamoi.Base_Kamoi;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Ichoh;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Kaede;
import com.ayutaki.chinjufumod.blocks.kamoi.Kamoi_Sakura;
import com.ayutaki.chinjufumod.blocks.season.Door_CM;
import com.ayutaki.chinjufumod.blocks.season.PressurePlate_CM;
import com.ayutaki.chinjufumod.blocks.season.TrapDoor_CM;
import com.ayutaki.chinjufumod.blocks.season.WoodButton_CM;
import com.ayutaki.chinjufumod.blocks.season.WoodFenceGate_CM;
import com.ayutaki.chinjufumod.blocks.season.WoodFence_CM;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage3Stone;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage4;
import com.ayutaki.chinjufumod.blocks.wood.CM_SaplingBlock;
import com.ayutaki.chinjufumod.blocks.wood.Carpet_CM;
import com.ayutaki.chinjufumod.blocks.wood.FallLeaf;
import com.ayutaki.chinjufumod.blocks.wood.KuriIga_Bush;
import com.ayutaki.chinjufumod.blocks.wood.KuriIga_Fall;
import com.ayutaki.chinjufumod.blocks.wood.Suiden;
import com.ayutaki.chinjufumod.blocks.wood.Take_CM;
import com.ayutaki.chinjufumod.blocks.wood.Takenoko;
import com.ayutaki.chinjufumod.blocks.wood.TreeGrower_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodLeaf_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodLeaf_Kuri;
import com.ayutaki.chinjufumod.blocks.wood.WoodPillar_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodPlanks_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodSlab_CM;
import com.ayutaki.chinjufumod.blocks.wood.WoodStairs_CM;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Wood_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> SUIDEN = register("block_suiden", Suiden::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).randomTicks().strength(1.0F, 5.0F).sound(SoundType.GRASS).isValidSpawn(Wood_Blocks::neverEntity));

	public static final DeferredBlock<Block> TAKE = register("block_take", Take_CM::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(1.0F).sound(SoundType.BAMBOO).offsetType(BlockBehaviour.OffsetType.XZ)
					.isSuffocating(Wood_Blocks::never).isViewBlocking(Wood_Blocks::never).dynamicShape());
	public static final DeferredBlock<Block> TAKENOKO = register("block_takenoko", Takenoko::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().strength(1.0F).sound(SoundType.BAMBOO_SAPLING).offsetType(BlockBehaviour.OffsetType.XZ)
					.isSuffocating(Wood_Blocks::never).isViewBlocking(Wood_Blocks::never).dynamicShape());
	public static final DeferredBlock<Block> KURIIGA_FALL = register("block_chestnuts", KuriIga_Fall::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(0.3F).sound(SoundType.SNOW).offsetType(BlockBehaviour.OffsetType.XZ)
					.isSuffocating(Wood_Blocks::never).isViewBlocking(Wood_Blocks::never).dynamicShape());
	public static final DeferredBlock<Block> KURIIGA_BUSH = register("block_chestnuts_bush", KuriIga_Bush::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().strength(0.3F).sound(SoundType.SNOW).offsetType(BlockBehaviour.OffsetType.XZ)
					.isSuffocating(Wood_Blocks::never).isViewBlocking(Wood_Blocks::never).dynamicShape());

	public static final DeferredBlock<Block> FALL_LEAF = register("block_fall_leaf", FallLeaf::new, 
			BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).randomTicks().strength(0.75F, 5.0F).sound(SoundType.GRASS));

	public static final DeferredBlock<Block> SAKURA_log = register("block_tree_sakura_log", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> KAEDE_log = register("block_tree_kaede_log", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> ICHOH_log = register("block_tree_ichoh_log", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> OAKKARE_log = register("block_tree_oakkare_log", WoodPillar_CM::new, woodState());
	
	public static final DeferredBlock<Block> SAKURA_flow = register("block_tree_sakura_flow", WoodLeaf_CM::new, leavesRandom().mapColor(MapColor.COLOR_PINK));
	public static final DeferredBlock<Block> KAEDE_leaf = register("block_tree_kaede_leaf", WoodLeaf_CM::new, leavesRandom().mapColor(MapColor.COLOR_RED));
	public static final DeferredBlock<Block> ICHOH_leaf = register("block_tree_ichoh_leaf", WoodLeaf_CM::new, leavesRandom().mapColor(MapColor.COLOR_YELLOW));
	public static final DeferredBlock<Block> OAKKARE_leaf = register("block_tree_oakkare_leaf", WoodLeaf_Kuri::new, leavesRandom().mapColor(MapColor.COLOR_BROWN));

	public static final DeferredBlock<Block> SAKURA_nae = register("block_tree_sakura_nae", (props) -> new CM_SaplingBlock(TreeGrower_CM.SAKURA, props), naeRandom());
	public static final DeferredBlock<Block> KAEDE_nae = register("block_tree_kaede_nae", (props) -> new CM_SaplingBlock(TreeGrower_CM.KAEDE, props), naeRandom());
	public static final DeferredBlock<Block> ICHOH_nae = register("block_tree_ichoh_nae", (props) -> new CM_SaplingBlock(TreeGrower_CM.ICHOH, props), naeRandom());
	public static final DeferredBlock<Block> OAKKARE_nae = register("block_tree_oakkare_nae", (props) -> new CM_SaplingBlock(TreeGrower_CM.OAKKARE, props), naeRandom());

	public static final DeferredBlock<Block> SAKURA_planks = register("block_planks_sakura", WoodPlanks_CM::new, woodState());
	public static final DeferredBlock<Block> KAEDE_planks = register("block_planks_kaede", WoodPlanks_CM::new, woodState());
	public static final DeferredBlock<Block> ICHOH_planks = register("block_planks_ichoh", WoodPlanks_CM::new, woodState());

	public static final DeferredBlock<Block> SAKURA_slabhalf = register("block_slabhalf_sakura", WoodSlab_CM::new, woodenSlab());
	public static final DeferredBlock<Block> KAEDE_slabhalf = register("block_slabhalf_kaede", WoodSlab_CM::new, woodenSlab());
	public static final DeferredBlock<Block> ICHOH_slabhalf = register("block_slabhalf_ichoh", WoodSlab_CM::new, woodenSlab());

	public static final DeferredBlock<Block> SAKURA_stairs = register("block_stairs_sakura", (props) -> new WoodStairs_CM(SAKURA_planks.get().defaultBlockState(), props), woodenStairs());
	public static final DeferredBlock<Block> KAEDE_stairs = register("block_stairs_kaede", (props) -> new WoodStairs_CM(KAEDE_planks.get().defaultBlockState(), props), woodenStairs());
	public static final DeferredBlock<Block> ICHOH_stairs = register("block_stairs_ichoh", (props) -> new WoodStairs_CM(ICHOH_planks.get().defaultBlockState(), props), woodenStairs());

	public static final DeferredBlock<Block> PILLAR_saku = register("block_pillar_sakura", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_kae = register("block_pillar_kaede", WoodPillar_CM::new, woodState());
	public static final DeferredBlock<Block> PILLAR_ich = register("block_pillar_ichoh", WoodPillar_CM::new, woodState());

	public static final DeferredBlock<Block> PILLARSLAB_saku = register("block_kamoi_sakura", Kamoi_Sakura::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_kae = register("block_kamoi_kaede", Kamoi_Kaede::new, kamoiState());
	public static final DeferredBlock<Block> PILLARSLAB_ich = register("block_kamoi_ichoh", Kamoi_Ichoh::new, kamoiState());

	public static final DeferredBlock<Block> SAKURA_FENCE = register("block_fence_sakura", WoodFence_CM::new, woodNever());
	public static final DeferredBlock<Block> KAEDE_FENCE = register("block_fence_kaede", WoodFence_CM::new, woodNever());
	public static final DeferredBlock<Block> ICHOH_FENCE = register("block_fence_ichoh", WoodFence_CM::new, woodNever());
	public static final DeferredBlock<Block> SAKURA_FGATE = register("block_fencegate_sakura", (props) -> new WoodFenceGate_CM(WoodType.OAK, props), woodNever());
	public static final DeferredBlock<Block> KAEDE_FGATE = register("block_fencegate_kaede", (props) -> new WoodFenceGate_CM(WoodType.OAK, props), woodNever());
	public static final DeferredBlock<Block> ICHOH_FGATE = register("block_fencegate_ichoh", (props) -> new WoodFenceGate_CM(WoodType.OAK, props), woodNever());

	public static final DeferredBlock<Block> DOOR_SAKURA = register("block_door_sakura", (props) -> new Door_CM(BlockSetType.OAK, props), doorState());
	public static final DeferredBlock<Block> DOOR_KAEDE = register("block_door_kaede", (props) -> new Door_CM(BlockSetType.OAK, props), doorState());
	public static final DeferredBlock<Block> DOOR_ICHOH = register("block_door_ichoh", (props) -> new Door_CM(BlockSetType.OAK, props), doorState());

	public static final DeferredBlock<Block> SAKURA_TRAPDOOR = register("block_trapdoor_sakura", (props) -> new TrapDoor_CM(BlockSetType.OAK, props), woodNever());
	public static final DeferredBlock<Block> KAEDE_TRAPDOOR = register("block_trapdoor_kaede", (props) -> new TrapDoor_CM(BlockSetType.OAK, props), woodNever());
	public static final DeferredBlock<Block> ICHOH_TRAPDOOR = register("block_trapdoor_ichoh", (props) -> new TrapDoor_CM(BlockSetType.OAK, props), woodNever());
	public static final DeferredBlock<Block> SAKURA_PLATE = register("block_plate_sakura", (props) -> new PressurePlate_CM(BlockSetType.OAK, props), plateState());
	public static final DeferredBlock<Block> KAEDE_PLATE = register("block_plate_kaede", (props) -> new PressurePlate_CM(BlockSetType.OAK, props), plateState());
	public static final DeferredBlock<Block> ICHOH_PLATE = register("block_plate_ichoh", (props) -> new PressurePlate_CM(BlockSetType.OAK, props), plateState());
	public static final DeferredBlock<Block> SAKURA_BUTTON = register("block_button_sakura", (props) -> new WoodButton_CM(BlockSetType.OAK, 30, props), buttonState());
	public static final DeferredBlock<Block> KAEDE_BUTTON = register("block_button_kaede", (props) -> new WoodButton_CM(BlockSetType.OAK, 30, props), buttonState());
	public static final DeferredBlock<Block> ICHOH_BUTTON = register("block_button_ichoh", (props) -> new WoodButton_CM(BlockSetType.OAK, 30, props), buttonState());
	
	public static final DeferredBlock<Block> SAKURA_carpet = register("block_carpet_sakura", Carpet_CM::new, carpetState());
	public static final DeferredBlock<Block> KAEDE_carpet = register("block_carpet_kaede", Carpet_CM::new, carpetState());
	public static final DeferredBlock<Block> ICHOH_carpet = register("block_carpet_ichoh", Carpet_CM::new, carpetState());
	public static final DeferredBlock<Block> OCHIBA_carpet = register("block_carpet_ochiba", Carpet_CM::new, carpetState());
	
	public static final DeferredBlock<Block> WP_LOG_sakura = register("block_wp_log_sakura", WallPane_Stage3Stone::new, woodNever());
	public static final DeferredBlock<Block> WP_LOG_kaede = register("block_wp_log_kaede", WallPane_Stage3Stone::new, woodNever());
	public static final DeferredBlock<Block> WP_LOG_ichoh = register("block_wp_log_ichoh", WallPane_Stage3Stone::new, woodNever());

	public static final DeferredBlock<Block> WP_PLANK_sakura = register("block_wp_plank_sakura", WallPane_Stage4::new, woodNever());
	public static final DeferredBlock<Block> WP_PLANK_kaede = register("block_wp_plank_kaede", WallPane_Stage4::new, woodNever());
	public static final DeferredBlock<Block> WP_PLANK_ichoh = register("block_wp_plank_ichoh", WallPane_Stage4::new, woodNever());

	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static boolean neverSlab(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.getValue(SlabBlock.TYPE) == SlabType.DOUBLE)? true : false;
	}

	private static Boolean neverEntitySlab(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityStairs(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(WoodStairs_CM.HALF) == Half.BOTTOM)? (boolean)false : (boolean)true;
	}

	private static Boolean neverEntityKamoi(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (state.getValue(Base_Kamoi.STAGE_1_4) != 4)? (boolean)false : (boolean)true;
	}
	
	private static boolean ocelotOrParrot(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return entity == EntityType.OCELOT || entity == EntityType.PARROT;
	}
	
	private static Properties leavesRandom() {
		return Block.Properties.of().strength(0.2F).sound(SoundType.GRASS).noOcclusion()
				.isValidSpawn(Wood_Blocks::ocelotOrParrot).isSuffocating(Wood_Blocks::never).isViewBlocking(Wood_Blocks::never).randomTicks();
	}
	
	private static Properties naeRandom() {
		return Block.Properties.of().noCollission().strength(1.0F, 1.0F).sound(SoundType.WOOD).noOcclusion()
				.isValidSpawn(Wood_Blocks::neverEntity).isSuffocating(Wood_Blocks::never)
				.instabreak().pushReaction(PushReaction.DESTROY).randomTicks();
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD);
	}
	
	private static Properties kamoiState() {
		return woodState().noOcclusion().isValidSpawn(Wood_Blocks::neverEntityKamoi).isSuffocating(Wood_Blocks::never);
	}
	
	private static Properties woodNever() {
		return woodState().noOcclusion().isValidSpawn(Wood_Blocks::neverEntity).isSuffocating(Wood_Blocks::never);
	}

	private static Properties woodenSlab() {
		return woodState().noOcclusion().isValidSpawn(Wood_Blocks::neverEntitySlab).isSuffocating(Wood_Blocks::neverSlab);
	}

	private static Properties woodenStairs() {
		return woodState().noOcclusion().isValidSpawn(Wood_Blocks::neverEntityStairs).isSuffocating(Wood_Blocks::never);
	}

	private static Properties doorState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 10.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Wood_Blocks::neverEntity).isSuffocating(Wood_Blocks::never);
	}

	private static Properties plateState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD)
				.noCollission().noOcclusion().forceSolidOn().ignitedByLava().pushReaction(PushReaction.DESTROY)
				.isValidSpawn(Wood_Blocks::neverEntity).isSuffocating(Wood_Blocks::never);
	}

	private static Properties buttonState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD)
				.noCollission().noOcclusion().pushReaction(PushReaction.DESTROY)
				.isValidSpawn(Wood_Blocks::neverEntity).isSuffocating(Wood_Blocks::never);
	}

	private static Properties carpetState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 1.0F).sound(SoundType.GRASS)
				.noOcclusion().isValidSpawn(Wood_Blocks::neverEntity).isSuffocating(Wood_Blocks::never);
	}
	
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
