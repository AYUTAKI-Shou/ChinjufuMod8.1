package com.ayutaki.chinjufumod.blocks.wallpane;

import net.minecraft.block.SoundType;

public class WallPane_ClayColor extends BaseSimpleWP {

	public WallPane_ClayColor(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setResistance(10.0F);
		setLightOpacity(1);
	}
}
