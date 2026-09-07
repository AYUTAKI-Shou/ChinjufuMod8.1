package com.ayutaki.chinjufumod.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {

	void init();
	void setup(final FMLCommonSetupEvent event);
}
