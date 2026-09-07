package com.ayutaki.chinjufumod.blocks.wallpane;

import net.minecraft.block.SoundType;

public class WallPane_Plaster extends WallPane_Stage2 {

	public WallPane_Plaster(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setResistance(10.0F);
		setLightOpacity(1);
	}
}
