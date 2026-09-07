package com.ayutaki.chinjufumod.handler;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.world.BiomeModifiers_CM;
import com.ayutaki.chinjufumod.world.biome.BiomeUtils_CM;
import com.ayutaki.chinjufumod.world.features.FeaturesUtils_CM;
import com.ayutaki.chinjufumod.world.placement.PlacementUtils_CM;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DatapackBuilt_CM extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, (bootstrap) -> FeaturesUtils_CM.bootstrap(bootstrap))
			.add(Registries.PLACED_FEATURE, (bootstrap) -> PlacementUtils_CM.bootstrap(bootstrap))
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, (bootstrap) -> BiomeModifiers_CM.bootstrap(bootstrap))
			.add(Registries.BIOME, (bootstrap) -> BiomeUtils_CM.bootstrap(bootstrap));
	
	public DatapackBuilt_CM(PackOutput outPut, CompletableFuture<HolderLookup.Provider> registries) {
		super(outPut, registries, BUILDER, Set.of("minecraft", ChinjufuMod.MOD_ID));
	}
}
