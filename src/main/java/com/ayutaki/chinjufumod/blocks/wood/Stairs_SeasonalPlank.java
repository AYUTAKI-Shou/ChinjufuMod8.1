package com.ayutaki.chinjufumod.blocks.wood;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_Stairs;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class Stairs_SeasonalPlank extends Regi_Stairs {

	public Stairs_SeasonalPlank(String name, IBlockState state) {
		super(name, state);
		setCreativeTab(ChinjufuModTabs.SEASONAL);

		setSoundType(SoundType.WOOD);
		setResistance(10.0F);
		setHardness(1.0F);
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
