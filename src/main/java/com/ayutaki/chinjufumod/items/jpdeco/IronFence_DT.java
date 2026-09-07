package com.ayutaki.chinjufumod.items.jpdeco;

import com.ayutaki.chinjufumod.blocks.garden.IronFence_Bot;
import com.ayutaki.chinjufumod.blocks.garden.IronFence_Top;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Wadeco;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class IronFence_DT extends IBR_Wadeco {

	public IronFence_DT(String name) {
		super(name, Garden_Blocks.TETSUSAKU_BOT);
		setUnlocalizedName(name);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Garden_Blocks.TETSUSAKU_BOT, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = Garden_Blocks.TETSUSAKU_BOT.getDefaultState().withProperty(IronFence_Bot.H_FACING, direction);
			IBlockState state2 = Garden_Blocks.TETSUSAKU_TOP.getDefaultState().withProperty(IronFence_Top.H_FACING, direction);

			/** worldIn.setBlockState(pos, newState, flags) **/
			worldIn.setBlockState(pos, putSTATE);
			worldIn.setBlockState(pos.up(), state2);
			CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
}
