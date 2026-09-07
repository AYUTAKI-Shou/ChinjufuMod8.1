package com.ayutaki.chinjufumod.handler;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.tags.BlockTags_CM;
import com.ayutaki.chinjufumod.tags.ItemFTags_CM;
import com.ayutaki.chinjufumod.tags.ItemVTags_CM;
import com.ayutaki.chinjufumod.world.BiomeModifiers_CM;
import com.ayutaki.chinjufumod.world.biome.BiomeUtils_CM;
import com.ayutaki.chinjufumod.world.features.FeaturesUtils_CM;
import com.ayutaki.chinjufumod.world.placement.PlacementUtils_CM;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ChinjufuMod.MOD_ID)
public class DatapackBuilt_CM {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, (bootstrap) -> FeaturesUtils_CM.bootstrap(bootstrap))
			.add(Registries.PLACED_FEATURE, (bootstrap) -> PlacementUtils_CM.bootstrap(bootstrap))
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, (bootstrap) -> BiomeModifiers_CM.bootstrap(bootstrap))
			.add(Registries.BIOME, (bootstrap) -> BiomeUtils_CM.bootstrap(bootstrap));
	
	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		PackOutput outPut = gen.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
		boolean include = event.includeServer();
		
		DatapackBuiltinEntriesProvider dataPack = new DatapackBuiltinEntriesProvider(outPut, provider, DatapackBuilt_CM.BUILDER, Set.of(ChinjufuMod.MOD_ID));
		provider = dataPack.getRegistryProvider();
		gen.addProvider(include, dataPack);
		
		BlockTags_CM blockTags = new BlockTags_CM(outPut, provider, helper);
		gen.addProvider(include, blockTags);
		gen.addProvider(include, new ItemVTags_CM(outPut, provider, blockTags.contentsGetter(), helper));
		gen.addProvider(include, new ItemFTags_CM(outPut, provider, blockTags.contentsGetter(), helper));
		gen.addProvider(include, new TagsBiome_CM(outPut, provider, helper));
	}
}
