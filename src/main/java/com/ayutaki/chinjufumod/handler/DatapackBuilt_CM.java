package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.tags.BlockTags_CM;
import com.ayutaki.chinjufumod.tags.ItemFTags_CM;
import com.ayutaki.chinjufumod.tags.ItemVTags_CM;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ChinjufuMod.MOD_ID)
public class DatapackBuilt_CM {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		
		if (event.includeServer()) {
			BlockTags_CM blocktags = new BlockTags_CM(gen, helper);
			gen.addProvider(blocktags);
			gen.addProvider(new ItemVTags_CM(gen, blocktags, helper));
			gen.addProvider(new ItemFTags_CM(gen, blocktags, helper));
			
			gen.addProvider(new TagsBiome_CM(gen, helper));
		}
	}
}
