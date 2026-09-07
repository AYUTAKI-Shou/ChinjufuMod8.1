package com.ayutaki.chinjufumod.items.addtab;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubBlockFace1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class Chinjufu_SubBlockFace1 extends Abstract_SubBlockFace1 {

	public Chinjufu_SubBlockFace1(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);
	}
	
	/* onItemUse */
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
	}
}
