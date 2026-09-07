package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.furniture.Candle;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook2;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook3;
import com.ayutaki.chinjufumod.blocks.furniture.DeskCloth;
import com.ayutaki.chinjufumod.blocks.furniture.DressingTable;
import com.ayutaki.chinjufumod.blocks.furniture.LampHang;
import com.ayutaki.chinjufumod.blocks.furniture.LampMarine;
import com.ayutaki.chinjufumod.blocks.furniture.LightEmbed;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook2;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook3;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook_B;
import com.ayutaki.chinjufumod.blocks.furniture.OfficeDesk;
import com.ayutaki.chinjufumod.blocks.furniture.StandArm;
import com.ayutaki.chinjufumod.blocks.furniture.StandBedroom;
import com.ayutaki.chinjufumod.blocks.furniture.Tansu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;

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

public class Furniture_Blocks {
	/* 68 = 59 + (3 * 3) */
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> DRESSINGTABLE = register("block_dressingtable", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_spruce = register("block_dressingtable_s", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_birch = register("block_dressingtable_b", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_jungle = register("block_dressingtable_j", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_acacia = register("block_dressingtable_a", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_darkoak = register("block_dressingtable_d", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_mangrove = register("block_dressingtable_mangrove", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_cherry = register("block_dressingtable_cherry", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_paleoak = register("block_dressingtable_paleoak", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_sakura = register("block_dressingtable_saku", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_kaede = register("block_dressingtable_kae", DressingTable::new, woodState());
	public static final DeferredBlock<Block> DRESSINGTABLE_ichoh = register("block_dressingtable_ich", DressingTable::new, woodState());

	public static final DeferredBlock<Block> CANDLE_white = register("block_candle_white", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_orange = register("block_candle_orange", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_magenta = register("block_candle_magenta", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_lightb = register("block_candle_lightb", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_yellow = register("block_candle_yellow", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_lime = register("block_candle_lime", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_pink = register("block_candle_pink", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_gray = register("block_candle_gray", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_lightg = register("block_candle_lightg", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_cyan = register("block_candle_cyan", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_purple = register("block_candle_purple", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_blue = register("block_candle_blue", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_brown = register("block_candle_brown", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_green = register("block_candle_green", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_red = register("block_candle_red", Candle::new, candleState());
	public static final DeferredBlock<Block> CANDLE_black = register("block_candle_black", Candle::new, candleState());
	
	public static final DeferredBlock<Block> LAMP = register("block_lamp", LampHang::new, lampState().lightLevel(litBlockEmission(15)));
	public static final DeferredBlock<Block> STANDARM = register("block_standarm", StandArm::new, lampState().lightLevel(litBlockEmission(15)));
	public static final DeferredBlock<Block> STAND = register("block_standbedroom", StandBedroom::new, lampState().lightLevel(litBlockEmission(15)));
	public static final DeferredBlock<Block> M_LAMP = register("block_marinelamp", LampMarine::new, lampState().lightLevel(litBlockEmission(15)));
	public static final DeferredBlock<Block> E_LIGHT = register("block_lightembed", LightEmbed::new, lampState().lightLevel(lightBlock(15)));

	public static final DeferredBlock<Block> TANSU_OAK = register("block_tansu_oak", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_SPRUCE = register("block_tansu_spruce", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_BIRCH = register("block_tansu_birch", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_JUNGLE = register("block_tansu_jungle", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_ACACIA = register("block_tansu_acacia", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_DOAK = register("block_tansu_doak", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_MANGROVE = register("block_tansu_mangrove", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_CHERRY = register("block_tansu_cherry", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_PALEOAK = register("block_tansu_paleoak", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_SAKURA = register("block_tansu_sakura", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_KAEDE = register("block_tansu_kaede", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());
	public static final DeferredBlock<Block> TANSU_ICHOH = register("block_tansu_ichoh", (props) -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props), tansuState());

	public static final DeferredBlock<Block> OFFICEDESK_OAK = register("block_officedesk_oak", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_SPRUCE = register("block_officedesk_spruce", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_BIRCH = register("block_officedesk_birch", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_JUNGLE = register("block_officedesk_jungle", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_ACACIA = register("block_officedesk_acacia", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_DOAK = register("block_officedesk_darkoak", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_MANGROVE = register("block_officedesk_mangrove", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_CHERRY = register("block_officedesk_cherry", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_PALEOAK = register("block_officedesk_paleoak", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_SAKURA = register("block_officedesk_sakura", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_KAEDE = register("block_officedesk_kaede", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	public static final DeferredBlock<Block> OFFICEDESK_ICHOH = register("block_officedesk_ichoh", (props) -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props), woodState());
	
	public static final DeferredBlock<Block> DESKCLOTH_03 = register("block_deskcloth_03", DeskCloth::new, clothState());
	public static final DeferredBlock<Block> DESKCLOTH_47 = register("block_deskcloth_47", DeskCloth::new, clothState());
	public static final DeferredBlock<Block> DESKCLOTH_811 = register("block_deskcloth_811", DeskCloth::new, clothState());
	public static final DeferredBlock<Block> DESKCLOTH_1215 = register("block_deskcloth_1215", DeskCloth::new, clothState());
	public static final DeferredBlock<Block> DESKBOOK_1 = register("block_deskbook_1", DeskBook1::new, bookState());
	public static final DeferredBlock<Block> DESKBOOK_2 = register("block_deskbook_2", DeskBook2::new, bookState());
	public static final DeferredBlock<Block> DESKBOOK_3 = register("block_deskbook_3", DeskBook3::new, bookState());
	public static final DeferredBlock<Block> NOTEBOOK = register("block_notebook", NoteBook::new, bookState());
	public static final DeferredBlock<Block> NOTEBOOK_B = register("block_notebook_b", NoteBook_B::new, bookState());
	public static final DeferredBlock<Block> NOTEBOOK_2 = register("block_notebook_2", NoteBook2::new, bookState());
	public static final DeferredBlock<Block> NOTEBOOK_3 = register("block_notebook_3", NoteBook3::new, bookState());
	
	
	/* Share variables */
	private static ToIntFunction<BlockState> litBlockEmission(int value) {
		return (state) -> { return state.getValue(BlockStateProperties.LIT) ? value : 0; };
	}
	
	private static ToIntFunction<BlockState> lightBlock(int value) {
		return (state) -> { return value; };
	}
	
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties woodState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Furniture_Blocks::neverEntity).isSuffocating(Furniture_Blocks::never);
	}
	
	private static Properties lampState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 2.0F).sound(SoundType.METAL)
				.noOcclusion().isValidSpawn(Furniture_Blocks::neverEntity).isSuffocating(Furniture_Blocks::never);
	}
	
	private static Properties candleState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 1.0F).sound(SoundType.STONE)
				.noOcclusion().isValidSpawn(Furniture_Blocks::neverEntity).isSuffocating(Furniture_Blocks::never).lightLevel(litBlockEmission(13));
	}
	
	private static Properties tansuState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F, 3.0F).sound(SoundType.WOOD);
	}

	private static Properties clothState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(1.0F, 3.0F).sound(SoundType.WOOL)
				.noOcclusion().isValidSpawn(Furniture_Blocks::neverEntity).isSuffocating(Furniture_Blocks::never);
	}
	
	private static Properties bookState() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)
				.noOcclusion().isValidSpawn(Furniture_Blocks::neverEntity).isSuffocating(Furniture_Blocks::never);
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
