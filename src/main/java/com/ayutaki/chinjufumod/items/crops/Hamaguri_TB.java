package com.ayutaki.chinjufumod.items.crops;

import javax.annotation.Nonnull;

import com.ayutaki.chinjufumod.items.fuel.TabBlock_noFuel;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Hamaguri_TB extends TabBlock_noFuel {

	public Hamaguri_TB(String name, Block putBlock) {
		super(name, putBlock);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		return ActionResult.newResult(EnumActionResult.PASS, hStack);
	}

	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}
}
