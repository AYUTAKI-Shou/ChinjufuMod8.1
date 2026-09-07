package com.ayutaki.chinjufumod.registry;

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
import com.ayutaki.chinjufumod.handler.TileEntity_CM;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Furniture_Blocks {
	@SuppressWarnings("deprecation")
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ChinjufuMod.MOD_ID);

	public static Block DRESSINGTABLE = register("block_dressingtable", dressingTable());
	public static Block DRESSINGTABLE_spruce = register("block_dressingtable_s", dressingTable());
	public static Block DRESSINGTABLE_birch = register("block_dressingtable_b", dressingTable());
	public static Block DRESSINGTABLE_jungle = register("block_dressingtable_j", dressingTable());
	public static Block DRESSINGTABLE_acacia = register("block_dressingtable_a", dressingTable());
	public static Block DRESSINGTABLE_darkoak = register("block_dressingtable_d", dressingTable());
	public static Block DRESSINGTABLE_sakura = register("block_dressingtable_saku", dressingTable());
	public static Block DRESSINGTABLE_kaede = register("block_dressingtable_kae", dressingTable());
	public static Block DRESSINGTABLE_ichoh = register("block_dressingtable_ich", dressingTable());

	public static Block LAMP = register("block_lamp", new LampHang(lampState()));
	public static Block STANDARM = register("block_standarm", new StandArm(lampState()));
	public static Block STAND = register("block_standbedroom", new StandBedroom(lampState()));
	public static Block M_LAMP = register("block_marinelamp", new LampMarine(lampState()));
	public static Block E_LIGHT = register("block_lightembed", new LightEmbed(lampState()));
	
	public static Block CANDLE_white = register("block_candle_white", candle());
	public static Block CANDLE_orange = register("block_candle_orange", candle());
	public static Block CANDLE_magenta = register("block_candle_magenta", candle());
	public static Block CANDLE_lightb = register("block_candle_lightb", candle());
	public static Block CANDLE_yellow = register("block_candle_yellow", candle());
	public static Block CANDLE_lime = register("block_candle_lime", candle());
	public static Block CANDLE_pink = register("block_candle_pink", candle());
	public static Block CANDLE_gray = register("block_candle_gray", candle());
	public static Block CANDLE_lightg = register("block_candle_lightg", candle());
	public static Block CANDLE_cyan = register("block_candle_cyan", candle());
	public static Block CANDLE_purple = register("block_candle_purple", candle());
	public static Block CANDLE_blue = register("block_candle_blue", candle());
	public static Block CANDLE_brown = register("block_candle_brown", candle());
	public static Block CANDLE_green = register("block_candle_green", candle());
	public static Block CANDLE_red = register("block_candle_red", candle());
	public static Block CANDLE_black = register("block_candle_black", candle());

	public static Block TANSU_OAK = register("block_tansu_oak", tansu());
	public static Block TANSU_SPRUCE = register("block_tansu_spruce", tansu());
	public static Block TANSU_BIRCH = register("block_tansu_birch", tansu());
	public static Block TANSU_JUNGLE = register("block_tansu_jungle", tansu());
	public static Block TANSU_ACACIA = register("block_tansu_acacia", tansu());
	public static Block TANSU_DOAK = register("block_tansu_doak", tansu());
	public static Block TANSU_SAKURA = register("block_tansu_sakura", tansu());
	public static Block TANSU_KAEDE = register("block_tansu_kaede", tansu());
	public static Block TANSU_ICHOH = register("block_tansu_ichoh", tansu());

	public static Block OFFICEDESK_OAK = register("block_officedesk_oak", officeDesk());
	public static Block OFFICEDESK_SPRUCE = register("block_officedesk_spruce", officeDesk());
	public static Block OFFICEDESK_BIRCH = register("block_officedesk_birch", officeDesk());
	public static Block OFFICEDESK_JUNGLE = register("block_officedesk_jungle", officeDesk());
	public static Block OFFICEDESK_ACACIA = register("block_officedesk_acacia", officeDesk());
	public static Block OFFICEDESK_DOAK = register("block_officedesk_darkoak", officeDesk());
	public static Block OFFICEDESK_SAKURA = register("block_officedesk_sakura", officeDesk());
	public static Block OFFICEDESK_KAEDE = register("block_officedesk_kaede", officeDesk());
	public static Block OFFICEDESK_ICHOH = register("block_officedesk_ichoh", officeDesk());
	
	public static Block DESKCLOTH_03 = register("block_deskcloth_03", deskCloth());
	public static Block DESKCLOTH_47 = register("block_deskcloth_47", deskCloth());
	public static Block DESKCLOTH_811 = register("block_deskcloth_811", deskCloth());
	public static Block DESKCLOTH_1215 = register("block_deskcloth_1215", deskCloth());
	public static Block DESKBOOK_1 = register("block_deskbook_1", new DeskBook1(bookState()));
	public static Block DESKBOOK_2 = register("block_deskbook_2", new DeskBook2(bookState()));
	public static Block DESKBOOK_3 = register("block_deskbook_3", new DeskBook3(bookState()));
	public static Block NOTEBOOK = register("block_notebook", new NoteBook(bookState()));
	public static Block NOTEBOOK_B = register("block_notebook_b", new NoteBook_B(bookState()));
	public static Block NOTEBOOK_2 = register("block_notebook_2", new NoteBook2(bookState()));
	public static Block NOTEBOOK_3 = register("block_notebook_3", new NoteBook3(bookState()));
	

	/* Share variables */
	private static DressingTable dressingTable() {
		return new DressingTable(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD).notSolid());
	}
	
	private static Properties lampState() {
		return Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F, 3.0F).sound(SoundType.CLOTH).notSolid();
	}
	
	private static Candle candle() {
		return new Candle(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F, 1.0F).sound(SoundType.STONE).notSolid());
	}

	private static Tansu tansu() {
		return new Tansu(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD), () -> { return TileEntity_CM.TANSU; });
	}
	
	private static OfficeDesk officeDesk() {
		return new OfficeDesk(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 3.0F).sound(SoundType.WOOD), () -> { return TileEntity_CM.OFFICEDESK; });
	}
	
	private static DeskCloth deskCloth() {
		return new DeskCloth(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F, 3.0F).sound(SoundType.CLOTH).notSolid());
	}
	
	private static Properties bookState() {
		return Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD).notSolid();
	}
	
	///* Register *///
	private static Block register(String name, Block block) {
		BLOCKS.register(name, () -> block);
		return block;
	}
}
