package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_Pillar;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class BlockPlank_Pillar extends Regi_Pillar {

	public BlockPlank_Pillar(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.WALLPANEL);

		setSoundType(SoundType.WOOD);
		setHardness(2.0F);
		setResistance(5.0F);
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
