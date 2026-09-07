package com.ayutaki.chinjufumod.blocks.wallpane;

import net.minecraft.block.SoundType;
import net.minecraft.util.BlockRenderLayer;

public class WallPane_GlassStained extends BaseSimpleWP {

	public WallPane_GlassStained(String name) {
		super(name);
		setSoundType(SoundType.GLASS);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
