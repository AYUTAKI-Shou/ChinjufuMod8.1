package com.ayutaki.chinjufumod.blocks.wallpane;

import net.minecraft.block.SoundType;

public class WallPane_Clay extends BaseSimpleWP {

	public WallPane_Clay(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setResistance(10.0F);
		setLightOpacity(1);
	}
}
