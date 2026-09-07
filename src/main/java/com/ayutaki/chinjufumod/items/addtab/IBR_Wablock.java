package com.ayutaki.chinjufumod.items.addtab;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.items.base.ItemBlock_Regi;

import net.minecraft.block.Block;

public class IBR_Wablock extends ItemBlock_Regi {

	public IBR_Wablock(String name, Block putBlock) {
		super(name, putBlock);
		setCreativeTab(ChinjufuModTabs.WABLOCK);
	}
}
