package com.ayutaki.chinjufumod.world.biome;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class BiomeUtils_CM {

	public static void bootstrap(BootstrapContext<Biome> context) {
		BiomeKey_CM.bootstrap(context);
	}

	@SuppressWarnings("removal")
	public static ResourceKey<Biome> createKey(String name) {
		return ResourceKey.create(Registries.BIOME, new ResourceLocation(ChinjufuMod.MOD_ID, name));
	}
}
