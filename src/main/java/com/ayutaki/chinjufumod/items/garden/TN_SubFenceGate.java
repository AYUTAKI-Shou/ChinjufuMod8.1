package com.ayutaki.chinjufumod.items.garden;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Item_Regi;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TN_SubFenceGate extends Item_Regi {

	public TN_SubFenceGate(String name) {
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
		EnumFacing direction = EnumFacing.fromAngle((double)playerIn.rotationYaw);
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(this.int2Block(k), pos, false, facing, (Entity)null)) {

			placeGate(worldIn, pos, direction, this.int2Block(k));
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			
			return EnumActionResult.SUCCESS; }

		else { return EnumActionResult.FAIL; }
	}

	/* put_1 */
	public static void placeGate(World worldIn, BlockPos pos, EnumFacing facing, Block door) {

		BlockPos upPos = pos.up();
		boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(upPos);
		IBlockState state = door.getDefaultState().withProperty(BlockFenceGate.FACING, facing)
				.withProperty(BlockFenceGate.POWERED, Boolean.valueOf(flag2))
				.withProperty(BlockFenceGate.OPEN, Boolean.valueOf(flag2));
		worldIn.setBlockState(pos, state, 2);
		worldIn.notifyNeighborsOfStateChange(pos, door, false);
		worldIn.notifyNeighborsOfStateChange(upPos, door, false);
	}
}
