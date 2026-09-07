package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChestLoot_CM {

	@SubscribeEvent
	public void loadLootTable(LootTableLoadEvent event) {
		LootFunction[] fun0 = new LootFunction[0];
		LootCondition[] con0 = new LootCondition[0];
		
		LootPool pool = event.getTable().getPool("main");
		if (pool == null) { return; }
		
		if (event.getName().equals(LootTableList.CHESTS_SPAWN_BONUS_CHEST)) {
			pool.addEntry(new LootEntryItem(Items_Chinjufu.ADMIRAL_STAMP_B, 100, 0, fun0, con0, "chinjufumod:item_admiralstamp_b"));
		}
		
		if (event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH)) {
			pool.addEntry(new LootEntryItem(Items_Chinjufu.WORK_ORDER, 10, 0, count(1, 3), con0, "chinjufumod:item_workorder"));
		}
		
		if (event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)) {
			pool.addEntry(new LootEntryItem(Items_NoTab.ADMIRAL_STAMP, 10, 0, fun0, con0, "chinjufumod:item_admiralstamp"));
			pool.addEntry(new LootEntryItem(Items_Chinjufu.WORK_ORDER, 10, 0, count(1, 3), con0, "chinjufumod:item_workorder"));
		}
		
		if (event.getName().equals(LootTableList.CHESTS_WOODLAND_MANSION)) {
			pool.addEntry(new LootEntryItem(Items_NoTab.ADMIRAL_STAMP, 100, 0, fun0, con0, "chinjufumod:item_admiralstamp"));
			pool.addEntry(new LootEntryItem(Items_Chinjufu.ADMIRAL_STAMP_B, 10, 0, fun0, con0, "chinjufumod:item_admiralstamp_b"));
		}
		
		
		if (event.getName().equals(LootTableList.GAMEPLAY_FISHING_FISH)) {
			pool.addEntry(new LootEntryItem(Items_Teatime.SHIO, 20, 0, metaCount(3, 2), con0, "chinjufumod:item_salt"));
		}
		
		if (event.getName().equals(LootTableList.GAMEPLAY_FISHING_JUNK)) {
			pool.addEntry(new LootEntryItem(Items_Teatime.SHIO, 10, 0, metaCount(3, 1), con0, "chinjufumod:item_salt"));
		}
	}
	
	private static LootFunction[] count(int countMIN, int countMAX) {
		return new LootFunction[] { 
				new SetCount(new LootCondition[0], new RandomValueRange(countMIN, countMAX)) };
	}
	
	private static LootFunction[] metaCount(int meta, int count) {
		return new LootFunction[] {
				new SetMetadata(new LootCondition[0], new RandomValueRange(meta)),
				new SetCount(new LootCondition[0], new RandomValueRange(count)) };
	}
}
