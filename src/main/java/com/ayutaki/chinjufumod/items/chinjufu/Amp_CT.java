package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_IronFace6;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Amp_CT extends IR_Chinjufu {

	public Amp_CT(String name) {
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
			return "item." + "block_amp_white";
		case 1:
			return "item." + "block_amp_orange";
		case 2:
			return "item." + "block_amp_magenta";
		case 3:
			return "item." + "block_amp_lightblue";
		case 4:
			return "item." + "block_amp_yellow";
		case 5:
			return "item." + "block_amp_lime";
		case 6:
			return "item." + "block_amp_pink";
		case 7:
			return "item." + "block_amp_gray";
		case 8:
			return "item." + "block_amp";
		case 9:
			return "item." + "block_amp_cyan";
		case 10:
			return "item." + "block_amp_purple";
		case 11:
			return "item." + "block_amp_blue";
		case 12:
			return "item." + "block_amp_brown";
		case 13:
			return "item." + "block_amp_green";
		case 14:
			return "item." + "block_amp_red";
		case 15:
			return "item." + "block_amp_black";
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

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {

			boolean powered = worldIn.isBlockPowered(pos);
			Block AMP = powered? takeBlock2(k) : int2Block(k);

			worldIn.setBlockState(pos, AMP.getDefaultState().withProperty(Base_IronFace6.FACING, facing), 2);
			CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
			
			return EnumActionResult.SUCCESS; }
			
		else { return EnumActionResult.FAIL; }
	}
	
	private Block int2Block(int k) {
		if (k == 0) { return Harbor_Blocks.AMP_white; }
		if (k == 1) { return Harbor_Blocks.AMP_orange; }
		if (k == 2) { return Harbor_Blocks.AMP_magenta; }
		if (k == 3) { return Harbor_Blocks.AMP_lightb; }
		if (k == 4) { return Harbor_Blocks.AMP_yellow; }
		if (k == 5) { return Harbor_Blocks.AMP_lime; }
		if (k == 6) { return Harbor_Blocks.AMP_pink; }
		if (k == 7) { return Harbor_Blocks.AMP_gray; }
		if (k == 8) { return Harbor_Blocks.AMP; }
		if (k == 9) { return Harbor_Blocks.AMP_cyan; }
		if (k == 10) { return Harbor_Blocks.AMP_purple; }
		if (k == 11) { return Harbor_Blocks.AMP_blue; }
		if (k == 12) { return Harbor_Blocks.AMP_brown; }
		if (k == 13) { return Harbor_Blocks.AMP_green; }
		if (k == 14) { return Harbor_Blocks.AMP_red; }
		else { return Harbor_Blocks.AMP_black; }
	}
	
	private Block takeBlock2(int k) {
		if (k == 0) { return Harbor_Blocks.AMP2_white; }
		if (k == 1) { return Harbor_Blocks.AMP2_orange; }
		if (k == 2) { return Harbor_Blocks.AMP2_magenta; }
		if (k == 3) { return Harbor_Blocks.AMP2_lightb; }
		if (k == 4) { return Harbor_Blocks.AMP2_yellow; }
		if (k == 5) { return Harbor_Blocks.AMP2_lime; }
		if (k == 6) { return Harbor_Blocks.AMP2_pink; }
		if (k == 7) { return Harbor_Blocks.AMP2_gray; }
		if (k == 8) { return Harbor_Blocks.AMP2; }
		if (k == 9) { return Harbor_Blocks.AMP2_cyan; }
		if (k == 10) { return Harbor_Blocks.AMP2_purple; }
		if (k == 11) { return Harbor_Blocks.AMP2_blue; }
		if (k == 12) { return Harbor_Blocks.AMP2_brown; }
		if (k == 13) { return Harbor_Blocks.AMP2_green; }
		if (k == 14) { return Harbor_Blocks.AMP2_red; }
		else { return Harbor_Blocks.AMP2_black; }
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_amp.name"));
	}
}
