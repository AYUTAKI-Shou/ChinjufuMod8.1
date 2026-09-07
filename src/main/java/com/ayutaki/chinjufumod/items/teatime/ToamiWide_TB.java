package com.ayutaki.chinjufumod.items.teatime;

import javax.annotation.Nonnull;

import com.ayutaki.chinjufumod.items.fuel.TabBlock_noFuel;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToamiWide_TB extends TabBlock_noFuel {

	public ToamiWide_TB(String name, Block putBlock) {
		super(name, putBlock);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		playerIn.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		if (!worldIn.isRemote) { playerIn.inventory.deleteStack(hStack); }
		return ActionResult.newResult(EnumActionResult.PASS, hStack);
	}
	
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		playerIn.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		playerIn.inventory.deleteStack(hStack);
		return EnumActionResult.PASS;
	}
}
