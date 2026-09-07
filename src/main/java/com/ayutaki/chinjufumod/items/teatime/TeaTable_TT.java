package com.ayutaki.chinjufumod.items.teatime;

import com.ayutaki.chinjufumod.blocks.unitblock.Endai;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

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

public class TeaTable_TT extends IBR_Teatime {

	public TeaTable_TT(String name) {
		super(name, Unit_Blocks.ENDAI);
		setUnlocalizedName(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Unit_Blocks.ENDAI, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = Unit_Blocks.ENDAI.getDefaultState().withProperty(Endai.STAGE_0_2, Integer.valueOf(2));

			if (placeBlockAt(hStack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, putSTATE)) {
				putSTATE = worldIn.getBlockState(pos);
				CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			}
			return EnumActionResult.SUCCESS;
		}
		else { return EnumActionResult.FAIL; }
	}
}
