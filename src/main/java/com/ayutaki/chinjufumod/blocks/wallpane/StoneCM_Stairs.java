package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Base_StoneStairs;

import net.minecraft.block.state.IBlockState;

public class StoneCM_Stairs extends Base_StoneStairs {

	public StoneCM_Stairs(String name, IBlockState state) {
		super(name, state);
		setCreativeTab(ChinjufuModTabs.WALLPANEL);
	}
}
