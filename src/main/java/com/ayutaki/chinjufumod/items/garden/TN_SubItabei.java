package com.ayutaki.chinjufumod.items.garden;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.garden.Itabei;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Item_Regi;
import com.ayutaki.chinjufumod.state.HalfState;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TN_SubItabei extends Item_Regi {

	public TN_SubItabei(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;
	}

	/* onItemUse */
	protected abstract Block int2Block(int k);
	
	
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
					placeTrue(worldIn, pos, direction, this.int2Block(k));
					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS; }
				
				else { 
					placeFalse(worldIn, pos, direction, this.int2Block(k));
					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS; }
			}

			else { return EnumActionResult.FAIL; }
		}
	}

	/* put_1 */
	public static void placeFalse(World worldIn, BlockPos pos, EnumFacing facing, Block block) {
		BlockPos upPos = pos.up();
		IBlockState state = block.getDefaultState().withProperty(Itabei.H_FACING, facing).withProperty(Itabei.CHECK, false);
		worldIn.setBlockState(pos, state.withProperty(Itabei.HALF, HalfState.LOWER), 2);
		worldIn.setBlockState(upPos, state.withProperty(Itabei.HALF, HalfState.UPPER), 2);
	}

	/* put_2 */
	public static void placeTrue(World worldIn, BlockPos pos, EnumFacing facing, Block block) {
		BlockPos upPos = pos.up();
		IBlockState state = block.getDefaultState().withProperty(Itabei.H_FACING, facing).withProperty(Itabei.CHECK,true);
		worldIn.setBlockState(pos, state.withProperty(Itabei.HALF, HalfState.LOWER), 2);
		worldIn.setBlockState(upPos, state.withProperty(Itabei.HALF, HalfState.UPPER), 2);
	}

	/* tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_kanyou.name"));
	}
}
