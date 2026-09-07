package com.ayutaki.chinjufumod.handler;

import java.util.concurrent.CompletableFuture;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.tags.BlockTags_CM;
import com.ayutaki.chinjufumod.tags.ItemCMTags;
import com.ayutaki.chinjufumod.tags.ItemCTags_CM;
import com.ayutaki.chinjufumod.tags.ItemVTags_CM;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ChinjufuMod.MOD_ID)
public class DatapackGenerator_CM {

	/* GatherDataEvent -> GatherDataEvent.Client Neo */
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent.Client event) {
		DataGenerator gen= event.getGenerator();
		PackOutput outPut = gen.getPackOutput();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		
		DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuilt_CM(outPut, event.getLookupProvider());
		gen.addProvider(true, datapackProvider);
		
		BlockTags_CM blockTags = new BlockTags_CM(outPut, provider);
		gen.addProvider(true, blockTags);
		gen.addProvider(true, new ItemVTags_CM(outPut, provider, blockTags.contentsGetter()));
		gen.addProvider(true, new ItemCTags_CM(outPut, provider, blockTags.contentsGetter()));
		gen.addProvider(true, new ItemCMTags(outPut, provider, blockTags.contentsGetter()));
		
		gen.addProvider(true, new AssetEquipment_Data(outPut));
		gen.addProvider(true, new RecipeRunner_CM(outPut, provider));
	}
}
