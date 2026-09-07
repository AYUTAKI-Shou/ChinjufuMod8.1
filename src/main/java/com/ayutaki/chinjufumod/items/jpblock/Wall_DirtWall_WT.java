package com.ayutaki.chinjufumod.items.jpblock;

import com.ayutaki.chinjufumod.blocks.jpblock.Wall_DirtWall;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Wablock;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Wall_DirtWall_WT extends IBR_Wablock {

	public Wall_DirtWall_WT(String name) {
		super(name, JPBlock_Blocks.DIRTWALL_WALL);
		setUnlocalizedName(name);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(JPBlock_Blocks.DIRTWALL_WALL, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = JPBlock_Blocks.DIRTWALL_WALL.getDefaultState()
					.withProperty(Wall_DirtWall.STAGE_0_1, Integer.valueOf(0));
			worldIn.setBlockState(pos, putSTATE, 10);
			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else {
			return EnumActionResult.FAIL;
		}
	}
}
