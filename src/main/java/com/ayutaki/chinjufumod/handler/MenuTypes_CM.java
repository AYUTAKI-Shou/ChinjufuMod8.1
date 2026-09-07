package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.gui.NoteMenu;
import com.ayutaki.chinjufumod.gui.ReizouMenu;
import com.ayutaki.chinjufumod.gui.ReizouTopMenu;
import com.ayutaki.chinjufumod.gui.TansuMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MenuTypes_CM {

	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, ChinjufuMod.MOD_ID);

	public static final DeferredHolder<MenuType<?>, MenuType<NoteMenu>> NOTE_MENU = register("note_menu", NoteMenu::new);
	
	public static final DeferredHolder<MenuType<?>, MenuType<ReizouMenu>> REIZOU_MENU = register("reizou_45", ReizouMenu::new);
	public static final DeferredHolder<MenuType<?>, MenuType<ReizouTopMenu>> REIZOUTOP_MENU = register("reizou_27", ReizouTopMenu::new);
	
	public static final DeferredHolder<MenuType<?>, MenuType<TansuMenu>> TANSU_MENU = register("tansu_45", TansuMenu::new);
	
	private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> register(String name, MenuType.MenuSupplier<T> supplier) {
		return MENU_TYPES.register(name, () -> new MenuType<>(supplier, FeatureFlags.REGISTRY.allFlags()));
	}
}
