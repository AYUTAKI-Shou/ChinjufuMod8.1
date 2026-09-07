package com.ayutaki.chinjufumod.handler;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.NoteTileEntity;
import com.ayutaki.chinjufumod.tileentity.OfficeDesk_TileEntity;
import com.ayutaki.chinjufumod.tileentity.Oven_TileEntity;
import com.ayutaki.chinjufumod.tileentity.ReizouTop_TileEntity;
import com.ayutaki.chinjufumod.tileentity.Reizou_TileEntity;
import com.ayutaki.chinjufumod.tileentity.Stove_TileEntity;
import com.ayutaki.chinjufumod.tileentity.Tansu_TileEntity;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntity_CM {

	@SuppressWarnings("deprecation")
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ChinjufuMod.MOD_ID);

	public static TileEntityType<Oven_TileEntity> KIT_OVEN = register("kitoven", Oven_TileEntity::new, Kitchen_Blocks.KIT_OVEN, Kitchen_Blocks.KIT_OVEN_B);
	public static TileEntityType<Stove_TileEntity> C_STOVE = register("cstove", Stove_TileEntity::new, School_Blocks.CSTOVE_top);

	public static TileEntityType<Tansu_TileEntity> TANSU = register("tansu", Tansu_TileEntity::new, 
			Furniture_Blocks.TANSU_OAK, Furniture_Blocks.TANSU_SPRUCE, Furniture_Blocks.TANSU_BIRCH,
			Furniture_Blocks.TANSU_JUNGLE, Furniture_Blocks.TANSU_ACACIA, Furniture_Blocks.TANSU_DOAK,
			Furniture_Blocks.TANSU_SAKURA, Furniture_Blocks.TANSU_KAEDE, Furniture_Blocks.TANSU_ICHOH);

	public static TileEntityType<Reizou_TileEntity> REIZOU = register("reizou", Reizou_TileEntity::new, Kitchen_Blocks.KIT_REIZOU);
	public static TileEntityType<ReizouTop_TileEntity> REIZOU_TOP = register("reizou_top", ReizouTop_TileEntity::new, Kitchen_Blocks.KIT_REIZOU_TOP);
	
	public static TileEntityType<OfficeDesk_TileEntity> OFFICEDESK = register("officedesk", OfficeDesk_TileEntity::new, 
			Furniture_Blocks.OFFICEDESK_OAK, Furniture_Blocks.OFFICEDESK_SPRUCE, Furniture_Blocks.OFFICEDESK_BIRCH,
			Furniture_Blocks.OFFICEDESK_JUNGLE, Furniture_Blocks.OFFICEDESK_ACACIA, Furniture_Blocks.OFFICEDESK_DOAK,
			Furniture_Blocks.OFFICEDESK_SAKURA, Furniture_Blocks.OFFICEDESK_KAEDE, Furniture_Blocks.OFFICEDESK_ICHOH);
	
	public static TileEntityType<NoteTileEntity> NOTE = register("note", NoteTileEntity::new, 
			Furniture_Blocks.NOTEBOOK, Furniture_Blocks.NOTEBOOK_B);
	
	public static TileEntityType<BlackBoard_TileEntity> BLACKBOARD = register("blackboard", BlackBoard_TileEntity::new, School_Blocks.BLACKBOARD);
	
	public static TileEntityType<WoodBoard_TileEntity> WOODBOARD = register("woodboard", WoodBoard_TileEntity::new, 
			School_Blocks.BOARD_OAK, School_Blocks.BOARD_SPRUCE, School_Blocks.BOARD_BIRCH,
			School_Blocks.BOARD_JUNGLE, School_Blocks.BOARD_ACACIA, School_Blocks.BOARD_DOAK,
			School_Blocks.BOARD_SAKURA, School_Blocks.BOARD_KAEDE, School_Blocks.BOARD_ICHOH);


	private static <T extends TileEntity> TileEntityType<T> register(String name, Supplier<T> factory, Block... blocks) {
		TileEntityType<T> tileEntityType = TileEntityType.Builder.create(factory, blocks).build(null);

		TILE_ENTITIES.register(name, () -> tileEntityType);
		return tileEntityType;
	}
}
