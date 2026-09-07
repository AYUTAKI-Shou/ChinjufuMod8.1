package com.ayutaki.chinjufumod.items.chinjufu;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubStateFace4;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AlumiBlock_CN extends Abstract_SubStateFace4 {

	public AlumiBlock_CN(String name) {
		super(name, Chinjufu_Blocks.ALUMI);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_alumi_block";
		case 2:
			return "item." + "block_steel_block";
		case 3:
			return "item." + "block_gold_block";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Chinjufu_Blocks.ALUMI;
	}
	
	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
	}
}
