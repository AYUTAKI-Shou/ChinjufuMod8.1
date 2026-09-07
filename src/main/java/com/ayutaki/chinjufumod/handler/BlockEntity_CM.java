package com.ayutaki.chinjufumod.handler;

import java.util.Set;
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

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntity_CM {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ChinjufuMod.MOD_ID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Tansu_TileEntity>> TANSU = register("tansu", Tansu_TileEntity::new,
			() -> new Block[]{ Furniture_Blocks.TANSU_OAK.get(), Furniture_Blocks.TANSU_SPRUCE.get(), Furniture_Blocks.TANSU_BIRCH.get(),
					Furniture_Blocks.TANSU_JUNGLE.get(), Furniture_Blocks.TANSU_ACACIA.get(), Furniture_Blocks.TANSU_DOAK.get(),
					Furniture_Blocks.TANSU_MANGROVE.get(), Furniture_Blocks.TANSU_CHERRY.get(), Furniture_Blocks.TANSU_PALEOAK.get(),
					Furniture_Blocks.TANSU_SAKURA.get(), Furniture_Blocks.TANSU_KAEDE.get(), Furniture_Blocks.TANSU_ICHOH.get() });

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OfficeDesk_TileEntity>> OFFICEDESK = register("officedesk", OfficeDesk_TileEntity::new,
			() -> new Block[]{ Furniture_Blocks.OFFICEDESK_OAK.get(), Furniture_Blocks.OFFICEDESK_SPRUCE.get(), Furniture_Blocks.OFFICEDESK_BIRCH.get(),
					Furniture_Blocks.OFFICEDESK_JUNGLE.get(), Furniture_Blocks.OFFICEDESK_ACACIA.get(), Furniture_Blocks.OFFICEDESK_DOAK.get(),
					Furniture_Blocks.OFFICEDESK_MANGROVE.get(), Furniture_Blocks.OFFICEDESK_CHERRY.get(), Furniture_Blocks.OFFICEDESK_PALEOAK.get(),
					Furniture_Blocks.OFFICEDESK_SAKURA.get(), Furniture_Blocks.OFFICEDESK_KAEDE.get(), Furniture_Blocks.OFFICEDESK_ICHOH.get() });

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NoteTileEntity>> NOTE = register("note", NoteTileEntity::new,
			() -> new Block[]{ Furniture_Blocks.NOTEBOOK.get(), Furniture_Blocks.NOTEBOOK_B.get() });
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlackBoard_TileEntity>> BLACKBOARD = register("blackboard", BlackBoard_TileEntity::new, 
			() -> new Block[]{ School_Blocks.BLACKBOARD.get() });
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WoodBoard_TileEntity>> WOODBOARD = register("woodboard", WoodBoard_TileEntity::new,
			() -> new Block[]{ School_Blocks.BOARD_OAK.get(), School_Blocks.BOARD_SPRUCE.get(), School_Blocks.BOARD_BIRCH.get(),
					School_Blocks.BOARD_JUNGLE.get(), School_Blocks.BOARD_ACACIA.get(), School_Blocks.BOARD_DOAK.get(),
					School_Blocks.BOARD_MANGROVE.get(), School_Blocks.BOARD_CHERRY.get(), School_Blocks.BOARD_PALEOAK.get(),
					School_Blocks.BOARD_SAKURA.get(), School_Blocks.BOARD_KAEDE.get(), School_Blocks.BOARD_ICHOH.get() });
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Stove_TileEntity>> C_STOVE = register("cstove",Stove_TileEntity::new,
			() -> new Block[]{ School_Blocks.CSTOVE_top.get() });
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Reizou_TileEntity>> REIZOU = register("reizou", Reizou_TileEntity::new,
			() -> new Block[]{ Kitchen_Blocks.KIT_REIZOU.get() });
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ReizouTop_TileEntity>> REIZOU_TOP = register("reizou_top", ReizouTop_TileEntity::new,
			() -> new Block[]{ Kitchen_Blocks.KIT_REIZOU_TOP.get() });
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<Oven_TileEntity>> KIT_OVEN = register("kitoven", Oven_TileEntity::new,
			() -> new Block[]{ Kitchen_Blocks.KIT_OVEN.get(), Kitchen_Blocks.KIT_OVEN_B.get() });

	
	///* Register *///
	public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<Block[]> blocks) {
		return BLOCK_ENTITIES.register(name, () -> new BlockEntityType<>(supplier, Set.of(blocks.get())));
	}
}
