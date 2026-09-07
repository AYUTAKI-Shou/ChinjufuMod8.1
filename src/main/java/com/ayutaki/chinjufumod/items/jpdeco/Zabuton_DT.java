package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlabW;
import com.ayutaki.chinjufumod.blocks.base.BaseSlabW;
import com.ayutaki.chinjufumod.blocks.chair.Zabuton;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Wadeco;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfStoneSlab;
import net.minecraft.block.BlockHalfStoneSlabNew;
import net.minecraft.block.BlockHalfWoodSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Zabuton_DT extends IBR_Wadeco {

	public Zabuton_DT(String name) {
		super(name, JPDeco_Blocks.ZABUTON);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 100;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_mzabuton_white";
		case 1:
			return "item." + "block_mzabuton_orange";
		case 2:
			return "item." + "block_mzabuton_magenta";
		case 3:
			return "item." + "block_mzabuton_lightb";
		case 4:
			return "item." + "block_mzabuton_yellow";
		case 5:
			return "item." + "block_mzabuton_lime";
		case 6:
			return "item." + "block_mzabuton_pink";
		case 7:
			return "item." + "block_mzabuton_gray";
		case 8:
			return "item." + "block_mzabuton_lightg";
		case 9:
			return "item." + "block_mzabuton_cyan";
		case 10:
			return "item." + "block_mzabuton_purple";
		case 11:
			return "item." + "block_mzabuton_blue";
		case 12:
			return "item." + "block_mzabuton_brown";
		case 13:
			return "item." + "block_mzabuton_green";
		case 14:
			return "item." + "block_mzabuton_red";
		case 15:
			return "item." + "block_mzabuton_black";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
			items.add(new ItemStack(this, 1, 9));
			items.add(new ItemStack(this, 1, 10));
			items.add(new ItemStack(this, 1, 11));
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
		}
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		boolean flag = (block instanceof BaseFacingSlabW && state.getValue(BaseFacingSlabW.HALF) == SlabHalf.BOTTOM && state.getValue(BaseFacingSlabW.DOUBLE) == false) ||
				(block instanceof BaseSlabW && state.getValue(BaseSlabW.HALF) == SlabHalf.BOTTOM && state.getValue(BaseSlabW.DOUBLE) == false) ||
				(block instanceof Base_Slab_JP && state.getValue(Base_Slab_JP.HALF) == SlabHalf.BOTTOM && state.getValue(Base_Slab_JP.DOUBLE) == false) ||
				(block instanceof BlockHalfWoodSlab && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM) ||
				(block instanceof BlockHalfStoneSlab && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM) ||
				(block instanceof BlockHalfStoneSlabNew && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM);
		
		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(JPDeco_Blocks.ZABUTON, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = JPDeco_Blocks.ZABUTON.getDefaultState().withProperty(Zabuton.STAGE_0_15, Integer.valueOf(k));
			IBlockState state2 = JPDeco_Blocks.ZABUTON_down.getDefaultState().withProperty(Zabuton.STAGE_0_15, Integer.valueOf(k));
			worldIn.setBlockState(pos, flag? state2 : putSTATE, 10);

			CMEvents.ItemBlock_Cloth(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_mzabuton.name"));
	}
}
