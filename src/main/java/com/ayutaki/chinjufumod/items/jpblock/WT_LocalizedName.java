package com.ayutaki.chinjufumod.items.jpblock;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.items.base.Item_Regi;

public class WT_LocalizedName extends Item_Regi {

	public WT_LocalizedName(String name) {
		super(name);
		setUnlocalizedName(name);
		setCreativeTab(ChinjufuModTabs.WABLOCK);
	}
}
