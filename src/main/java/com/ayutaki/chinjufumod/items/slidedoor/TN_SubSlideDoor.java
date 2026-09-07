package com.ayutaki.chinjufumod.items.slidedoor;

import com.ayutaki.chinjufumod.blocks.slidedoor.BaseSlidedoor;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Item_Regi;
import com.ayutaki.chinjufumod.state.HalfState;
import com.ayutaki.chinjufumod.state.HingeState;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class TN_SubSlideDoor extends Item_Regi {

	public TN_SubSlideDoor(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* onItemUse */
	protected abstract Block int2Block(int k);
	
	
	/* Call this when you use the item. ex) Place a block. */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP) { return EnumActionResult.FAIL; }

		else {
			IBlockState state = worldIn.getBlockState(pos);
			Block block = state.getBlock();

			if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			ItemStack hStack = playerIn.getHeldItem(hand);
			int k = hStack.getMetadata();
			EnumFacing direction = EnumFacing.fromAngle((double)playerIn.rotationYaw);
			
			/** Put "this.block". **/
			if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {

				if (playerIn.isSneaking()) {
					placeDoor_right(worldIn, pos, direction, this.int2Block(k));
					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}

				/** デフォルトは left。右取手, 左蝶番で開くのが hinge=left **/
				else {
					placeDoor_left(worldIn, pos, direction, this.int2Block(k));
					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
			}

			else { return EnumActionResult.FAIL; }
		}
	}

	/* put_1 */
	public static void placeDoor_right(World worldIn, BlockPos pos, EnumFacing facing, Block door) {
		BlockPos upPos = pos.up();
		boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(upPos);
		
		IBlockState state = door.getDefaultState().withProperty(BaseSlidedoor.H_FACING, facing)
				.withProperty(BaseSlidedoor.HINGE, HingeState.RIGHT)
				.withProperty(BaseSlidedoor.POWERED, Boolean.valueOf(flag2))
				.withProperty(BaseSlidedoor.OPEN, Boolean.valueOf(flag2));
		worldIn.setBlockState(pos, state.withProperty(BaseSlidedoor.HALF, HalfState.LOWER), 2);
		worldIn.setBlockState(upPos, state.withProperty(BaseSlidedoor.HALF, HalfState.UPPER), 2);
		worldIn.notifyNeighborsOfStateChange(pos, door, false);
		worldIn.notifyNeighborsOfStateChange(upPos, door, false);
	}

	/* put_2 */
	public static void placeDoor_left(World worldIn, BlockPos pos, EnumFacing facing, Block door) {
		BlockPos upPos = pos.up();
		boolean flag2 = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(upPos);
		
		IBlockState state = door.getDefaultState().withProperty(BaseSlidedoor.H_FACING, facing)
				.withProperty(BaseSlidedoor.HINGE, HingeState.LEFT)
				.withProperty(BaseSlidedoor.POWERED, Boolean.valueOf(flag2))
				.withProperty(BaseSlidedoor.OPEN, Boolean.valueOf(flag2));
		worldIn.setBlockState(pos, state.withProperty(BaseSlidedoor.HALF, HalfState.LOWER), 2);
		worldIn.setBlockState(upPos, state.withProperty(BaseSlidedoor.HALF, HalfState.UPPER), 2);
		worldIn.notifyNeighborsOfStateChange(pos, door, false);
		worldIn.notifyNeighborsOfStateChange(upPos, door, false);
	}
}
