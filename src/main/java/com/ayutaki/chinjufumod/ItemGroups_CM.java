package com.ayutaki.chinjufumod;

import com.ayutaki.chinjufumod.registry.Items_Armor;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemGroups_CM {

	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChinjufuMod.MOD_ID);
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CHINJUFU = TABS.register("chinjufumod_1", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.chinjufumod.tab_chinjufumod"))
			.icon(() -> new ItemStack(Items_NoTab.EMBLEM_C.get()))
			.displayItems((parameters, output) -> {
				Items_Chinjufu.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
				
				//output.accept(Items_Chinjufu.BAUXITE.get());
				//output.accept(Items_Chinjufu.BAUXITE_ORE.get());
				//output.accept(Items_Chinjufu.BAUXITE_ORE_DEEP.get());
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TEATIME = TABS.register("chinjufumod_2", () -> CreativeModeTab.builder()
			.withTabsBefore(ItemGroups_CM.CHINJUFU.getKey())
			.title(Component.translatable("itemGroup.chinjufumod.tab_teatime"))
			.icon(() -> new ItemStack(Items_Teatime.TEACUP.get()))
			.displayItems((parameters, output) -> {
				Items_Teatime.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SEASONAL = TABS.register("chinjufumod_3", () -> CreativeModeTab.builder()
			.withTabsBefore(ItemGroups_CM.TEATIME.getKey())
			.title(Component.translatable("itemGroup.chinjufumod.tab_seasonal"))
			.icon(() -> new ItemStack(Items_Seasonal.HAMAKAZEYKT_CHESTPLATE.get()))
			.displayItems((parameters, output) -> {
				Items_Seasonal.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CMARMOR = TABS.register("chinjufumod_4", () -> CreativeModeTab.builder()
			.withTabsBefore(ItemGroups_CM.SEASONAL.getKey())
			.title(Component.translatable("itemGroup.chinjufumod.tab_cmarmor"))
			.icon(() -> new ItemStack(Items_Armor.FUBUKI_CHESTPLATE.get()))
			.displayItems((parameters, output) -> {
				Items_Armor.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
				Items_Weapon.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WADECO = TABS.register("chinjufumod_5", () -> CreativeModeTab.builder()
			.withTabsBefore(ItemGroups_CM.CMARMOR.getKey())
			.title(Component.translatable("itemGroup.chinjufumod.tab_cmodwadeco"))
			.icon(() -> new ItemStack(Items_Wadeco.FUSUMAB_cyan.get()))
			.displayItems((parameters, output) -> {
				Items_Wadeco.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WABLOCK = TABS.register("chinjufumod_6", () -> CreativeModeTab.builder()
			.withTabsBefore(ItemGroups_CM.WADECO.getKey())
			.title(Component.translatable("itemGroup.chinjufumod.tab_cmodwablock"))
			.icon(() -> new ItemStack(Items_Wablock.KAWARA_gray.get()))
			.displayItems((parameters, output) -> {
				Items_Wablock.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
			}).build());
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WALLPANEL = TABS.register("chinjufumod_7", () -> CreativeModeTab.builder()
			.withTabsBefore(ItemGroups_CM.WABLOCK.getKey())
			.title(Component.translatable("itemGroup.chinjufumod.tab_wallpanel"))
			.icon(() -> new ItemStack(Items_WallPanel.WP_STONE_graB.get()))
			.displayItems((parameters, output) -> {
				Items_WallPanel.ITEMS.getEntries().forEach(registryObject -> { output.accept(registryObject.get()); });
			}).build());
	
}
