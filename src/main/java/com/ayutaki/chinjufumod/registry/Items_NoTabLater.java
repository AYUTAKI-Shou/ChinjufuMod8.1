package com.ayutaki.chinjufumod.registry;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.addinfo.AddInfo_NT;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Items_NoTabLater {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChinjufuMod.MOD_ID);

	/* Teatime */
	public static Item RAW_PUDDING = register("item_raw_pudding_custard", new AddInfo_NT(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara)));
	public static Item RAW_PUDDING_GREEN = register("item_raw_pudding_greentea", new AddInfo_NT(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara)));
	public static Item RAW_PUDDING_RED = register("item_raw_pudding_redtea", new AddInfo_NT(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara)));
	public static Item RAW_PUDDING_CACAO = register("item_raw_pudding_cacao", new AddInfo_NT(new Item
			.Properties().craftRemainder(Items_Teatime.NABE_kara)));
	
	
	///* Register *///
	private static Item register(String name, Item item) {
		ITEMS.register(name, () -> item);
		return item;
	}
}
