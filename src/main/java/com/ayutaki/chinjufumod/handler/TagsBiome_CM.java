package com.ayutaki.chinjufumod.handler;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TagsBiome_CM extends BiomeTagsProvider {

	public TagsBiome_CM(DataGenerator gen, @Nullable ExistingFileHelper helper) {
		super(gen, ChinjufuMod.MOD_ID, helper);
	}

	@Override
	protected void addTags() {
		tag(BiomeTags.IS_FOREST)
			.add(BiomeKey_CM.SAKURA_FOREST_KEY, BiomeKey_CM.SAKURA_HILLS_KEY,
					BiomeKey_CM.KAEDE_FOREST_KEY, BiomeKey_CM.KAEDE_HILLS_KEY, 
					BiomeKey_CM.ICHOH_FOREST_KEY, BiomeKey_CM.ICHOH_HILLS_KEY);
		
		tag(BiomeTags.IS_HILL)
			.add(BiomeKey_CM.SAKURA_HILLS_KEY,
					BiomeKey_CM.KAEDE_HILLS_KEY, 
					BiomeKey_CM.ICHOH_HILLS_KEY);
		
		tag(BiomeTags.HAS_VILLAGE_PLAINS)
			.add(BiomeKey_CM.SAKURA_FOREST_KEY,
					BiomeKey_CM.KAEDE_FOREST_KEY,
					BiomeKey_CM.ICHOH_FOREST_KEY);
		
		tag(Tags.Biomes.IS_SLOPE)
		.add(BiomeKey_CM.SAKURA_HILLS_KEY,
				BiomeKey_CM.KAEDE_HILLS_KEY, 
				BiomeKey_CM.ICHOH_HILLS_KEY);
		
		tag(Tags.Biomes.IS_RARE)
		.add(BiomeKey_CM.SAKURA_HILLS_KEY,
				BiomeKey_CM.KAEDE_HILLS_KEY, 
				BiomeKey_CM.ICHOH_HILLS_KEY);
	}
}
