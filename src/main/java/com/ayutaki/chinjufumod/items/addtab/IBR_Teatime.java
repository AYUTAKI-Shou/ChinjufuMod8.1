package com.ayutaki.chinjufumod.items.addtab;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.items.base.ItemBlock_Regi;

import net.minecraft.block.Block;

public class IBR_Teatime extends ItemBlock_Regi {

	public IBR_Teatime(String name, Block putBlock) {
		super(name, putBlock);
		setCreativeTab(ChinjufuModTabs.TEATIME);
	}
}
