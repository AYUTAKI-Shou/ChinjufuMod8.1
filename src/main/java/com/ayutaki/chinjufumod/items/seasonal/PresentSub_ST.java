package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubStateFace4;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PresentSub_ST extends Abstract_SubStateFace4 {

	public PresentSub_ST(String name, Block putBlock) {
		super(name, putBlock);
		setCreativeTab(ChinjufuModTabs.SEASONAL);
	}

	/* onItemUse */
	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
	}
}
