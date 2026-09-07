package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.remain.PuddingRaw;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Items_NoTabLater {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ChinjufuMod.MOD_ID);

	/* Teatime */
	public static final DeferredItem<Item> RAW_PUDDING = register("item_raw_pudding_custard", PuddingRaw::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_PUDDING_GREEN = register("item_raw_pudding_greentea", PuddingRaw::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_PUDDING_RED = register("item_raw_pudding_redtea", PuddingRaw::new, new Item.Properties());
	public static final DeferredItem<Item> RAW_PUDDING_CACAO = register("item_raw_pudding_cacao", PuddingRaw::new, new Item.Properties());

	
	///* Register *///
	private static DeferredItem<Item> register(String name, Function<Item.Properties, Item> function, Item.Properties props) {
		return ITEMS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.ITEM, ChinjufuMod.id(name)))));
	}
}
