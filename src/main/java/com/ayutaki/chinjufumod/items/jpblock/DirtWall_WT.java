package com.ayutaki.chinjufumod.items.jpblock;

import com.ayutaki.chinjufumod.blocks.wallpane.Bricks_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Wablock;
import com.ayutaki.chinjufumod.registry.WallBrick_Blocks;

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

public class DirtWall_WT extends IBR_Wablock {

	public DirtWall_WT(String name) {
		super(name, WallBrick_Blocks.ROCK);
		setUnlocalizedName(name);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(WallBrick_Blocks.ROCK, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = WallBrick_Blocks.ROCK.getDefaultState()
					.withProperty(Bricks_CM.STAGE_1_14, Integer.valueOf(13));

			if (placeBlockAt(hStack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, putSTATE)) {
				putSTATE = worldIn.getBlockState(pos);
				worldIn.setBlockState(pos, putSTATE, 10);
				CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			}
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
}
