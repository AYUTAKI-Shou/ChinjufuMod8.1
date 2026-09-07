package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.furnace.CStove_Bot;
import com.ayutaki.chinjufumod.blocks.furnace.CStove_Top;
import com.ayutaki.chinjufumod.blocks.school.BlackBoard;
import com.ayutaki.chinjufumod.blocks.school.SchoolChair;
import com.ayutaki.chinjufumod.blocks.school.SchoolDesk;
import com.ayutaki.chinjufumod.blocks.school.StoveChimney;
import com.ayutaki.chinjufumod.blocks.school.StoveChimney_joint;
import com.ayutaki.chinjufumod.blocks.school.StoveChimney_top;
import com.ayutaki.chinjufumod.blocks.school.TeacherDesk;
import com.ayutaki.chinjufumod.blocks.school.WoodBoard;

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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class School_Blocks {
	/* 54 = 42 + (3 * 4) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> BLACKBOARD = register( "block_blackboard", BlackBoard::new, woodState());

	public static final DeferredBlock<Block> BOARD_OAK = register("block_board_oak", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_SPRUCE = register("block_board_spruce", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_BIRCH = register("block_board_birch", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_JUNGLE = register("block_board_jungle", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_ACACIA = register("block_board_acacia", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_DOAK = register("block_board_darkoak", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_MANGROVE = register("block_board_mangrove", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_CHERRY = register("block_board_cherry", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_PALEOAK = register("block_board_paleoak", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_SAKURA = register("block_board_sakura", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_KAEDE = register("block_board_kaede", WoodBoard::new, woodState());
	public static final DeferredBlock<Block> BOARD_ICHOH = register("block_board_ichoh", WoodBoard::new, woodState());
	
	public static final DeferredBlock<Block> SCHOOLCHAIR = register("block_schoolchair", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_spruce = register("block_schoolchair_s", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_birch = register("block_schoolchair_b", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_jungle = register("block_schoolchair_j", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_acacia = register("block_schoolchair_a", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_darkoak = register("block_schoolchair_d", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_mangrove = register("block_schoolchair_mangrove", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_cherry = register("block_schoolchair_cherry", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_paleoak = register("block_schoolchair_paleoak", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_sakura = register("block_schoolchair_saku", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_kaede = register("block_schoolchair_kae", SchoolChair::new, woodState());
	public static final DeferredBlock<Block> SCHOOLCHAIR_ichoh = register("block_schoolchair_ich", SchoolChair::new, woodState());

	public static final DeferredBlock<Block> SCHOOLDESK = register("block_schooldesk", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_spruce = register("block_schooldesk_s", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_birch = register("block_schooldesk_b", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_jungle = register("block_schooldesk_j", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_acacia = register("block_schooldesk_a", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_darkoak = register("block_schooldesk_d", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_mangrove = register("block_schooldesk_mangrove", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_cherry = register("block_schooldesk_cherry", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_paleoak = register("block_schooldesk_paleoak", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_sakura = register("block_schooldesk_saku", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_kaede = register("block_schooldesk_kae", SchoolDesk::new, woodState());
	public static final DeferredBlock<Block> SCHOOLDESK_ichoh = register("block_schooldesk_ich", SchoolDesk::new, woodState());

	public static final DeferredBlock<Block> TEACHERDESK = register("block_teacherdesk", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_spruce = register("block_teacherdesk_s", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_birch = register("block_teacherdesk_b", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_jungle = register("block_teacherdesk_j", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_acacia = register("block_teacherdesk_a", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_darkoak = register("block_teacherdesk_d", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_mangrove = register("block_teacherdesk_mangrove", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_cherry = register("block_teacherdesk_cherry", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_paleoak = register("block_teacherdesk_paleoak", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_sakura = register("block_teacherdesk_saku", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_kaede = register("block_teacherdesk_kae", TeacherDesk::new, woodState());
	public static final DeferredBlock<Block> TEACHERDESK_ichoh = register("block_teacherdesk_ich", TeacherDesk::new, woodState());
	
	public static final DeferredBlock<Block> STOVECHIMNEY = register("block_stovechimney", StoveChimney::new, metalState());
	public static final DeferredBlock<Block> STOVECHIMNEY_joint = register("block_stovechimney_joint", StoveChimney_joint::new, metalState());
	public static final DeferredBlock<Block> STOVECHIMNEY_topk = register("block_stovechimney_topk", StoveChimney_top::new, metalState());
	
	public static final DeferredBlock<Block> CSTOVE_top = register("block_cstove_top", CStove_Top::new, metalState().lightLevel(litBlockEmission(14)));
	public static final DeferredBlock<Block> CSTOVE_bot = register("block_cstove_bot", CStove_Bot::new, metalState());

	
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
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(School_Blocks::neverEntity).isSuffocating(School_Blocks::never);
	}
	
	private static Properties metalState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F, 6.0F).sound(SoundType.METAL)
				.noOcclusion().isValidSpawn(School_Blocks::neverEntity).isSuffocating(School_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
