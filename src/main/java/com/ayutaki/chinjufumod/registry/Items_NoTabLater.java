package com.ayutaki.chinjufumod.registry;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_Item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items_NoTabLater {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	/* Teatime */
	public static final RegistryObject<Item> RAW_PUDDING = register("item_raw_pudding_custard", () -> new AddInfo_Item(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara.get())));
	public static final RegistryObject<Item> RAW_PUDDING_GREEN = register("item_raw_pudding_greentea", () -> new AddInfo_Item(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara.get())));
	public static final RegistryObject<Item> RAW_PUDDING_RED = register("item_raw_pudding_redtea", () -> new AddInfo_Item(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara.get())));
	public static final RegistryObject<Item> RAW_PUDDING_CACAO = register("item_raw_pudding_cacao", () -> new AddInfo_Item(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara.get())));
	
	
	///* Register *///
	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}
}
