package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Harbor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Truss_CT extends IR_Chinjufu {

	public Truss_CT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_ctruss_white";
		case 1:
			return "item." + "block_ctruss_orange";
		case 2:
			return "item." + "block_ctruss_magenta";
		case 3:
			return "item." + "block_ctruss_lightb";
		case 4:
			return "item." + "block_ctruss_yellow";
		case 5:
			return "item." + "block_ctruss_lime";
		case 6:
			return "item." + "block_ctruss_pink";
		case 7:
			return "item." + "block_ctruss_gray";
		case 8:
			return "item." + "block_ctruss";
		case 9:
			return "item." + "block_ctruss_cyan";
		case 10:
			return "item." + "block_ctruss_purple";
		case 11:
			return "item." + "block_ctruss_blue";
		case 12:
			return "item." + "block_ctruss_brown";
		case 13:
			return "item." + "block_ctruss_green";
		case 14:
			return "item." + "block_ctruss_red";
		case 15:
			return "item." + "block_ctruss_black";
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
	
	/* Call this when you use the item. ex) Place a block. */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		
		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {

			if (direction == EnumFacing.SOUTH || direction == EnumFacing.WEST) {
				worldIn.setBlockState(pos, takeBlock2(k).getDefaultState(), 2); }
			
			else {
				worldIn.setBlockState(pos, this.int2Block(k).getDefaultState(), 2); }
			
			CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS; }
			
		else { return EnumActionResult.FAIL; }
	}
	
	private Block int2Block(int k) {
		if (k == 0) { return Harbor_Blocks.TRUSS_white; }
		if (k == 1) { return Harbor_Blocks.TRUSS_orange; }
		if (k == 2) { return Harbor_Blocks.TRUSS_magenta; }
		if (k == 3) { return Harbor_Blocks.TRUSS_lightb; }
		if (k == 4) { return Harbor_Blocks.TRUSS_yellow; }
		if (k == 5) { return Harbor_Blocks.TRUSS_lime; }
		if (k == 6) { return Harbor_Blocks.TRUSS_pink; }
		if (k == 7) { return Harbor_Blocks.TRUSS_gray; }
		if (k == 8) { return Harbor_Blocks.TRUSS; }
		if (k == 9) { return Harbor_Blocks.TRUSS_cyan; }
		if (k == 10) { return Harbor_Blocks.TRUSS_purple; }
		if (k == 11) { return Harbor_Blocks.TRUSS_blue; }
		if (k == 12) { return Harbor_Blocks.TRUSS_brown; }
		if (k == 13) { return Harbor_Blocks.TRUSS_green; }
		if (k == 14) { return Harbor_Blocks.TRUSS_red; }
		else { return Harbor_Blocks.TRUSS_black; }
	}
	
	private Block takeBlock2(int k) {
		if (k == 0) { return Harbor_Blocks.TRUSS2_white; }
		if (k == 1) { return Harbor_Blocks.TRUSS2_orange; }
		if (k == 2) { return Harbor_Blocks.TRUSS2_magenta; }
		if (k == 3) { return Harbor_Blocks.TRUSS2_lightb; }
		if (k == 4) { return Harbor_Blocks.TRUSS2_yellow; }
		if (k == 5) { return Harbor_Blocks.TRUSS2_lime; }
		if (k == 6) { return Harbor_Blocks.TRUSS2_pink; }
		if (k == 7) { return Harbor_Blocks.TRUSS2_gray; }
		if (k == 8) { return Harbor_Blocks.TRUSS2; }
		if (k == 9) { return Harbor_Blocks.TRUSS2_cyan; }
		if (k == 10) { return Harbor_Blocks.TRUSS2_purple; }
		if (k == 11) { return Harbor_Blocks.TRUSS2_blue; }
		if (k == 12) { return Harbor_Blocks.TRUSS2_brown; }
		if (k == 13) { return Harbor_Blocks.TRUSS2_green; }
		if (k == 14) { return Harbor_Blocks.TRUSS2_red; }
		else { return Harbor_Blocks.TRUSS2_black; }
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_ctruss.name"));
	}
}
