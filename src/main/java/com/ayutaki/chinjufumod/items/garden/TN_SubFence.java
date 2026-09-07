package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Item_Regi;

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

public abstract class TN_SubFence extends Item_Regi {

	public TN_SubFence(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* onItemUse */
	protected abstract Block int2Block(int k);


	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(int2Block(k), pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = this.int2Block(k).getDefaultState();
			worldIn.setBlockState(pos, putSTATE, 10);
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			
			return EnumActionResult.SUCCESS; }

		else { return EnumActionResult.FAIL; }
	}
}
