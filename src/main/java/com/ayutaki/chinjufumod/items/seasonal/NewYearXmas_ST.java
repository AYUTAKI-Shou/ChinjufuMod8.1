package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NewYearXmas_ST extends IBR_Seasonal {

	public NewYearXmas_ST(String name) {
		super(name, Seasonal_Blocks.NEWYEAR_XMAS);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/**1=門松, 2=鏡もち, 3=ノーマル, 4=ホワイト **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_kadomatsu";
		case 2:
			return "item." + "block_kagamimochi";
		case 3:
			return "item." + "block_xmastree";
		case 4:
			return "item." + "block_xmastree_w";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack hStack = playerIn.getHeldItem(hand);
			int k = hStack.getMetadata();

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Seasonal_Blocks.NEWYEAR_XMAS, pos, false, facing, (Entity)null)) {

			if (k == 2) {
				worldIn.setBlockState(pos, Seasonal_Blocks.NEWYEAR_XMAS.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2)), 10); }
			
			if (k != 2) {
				worldIn.setBlockState(pos, Seasonal_Blocks.NEWYEAR_XMAS.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(k)), 10);
				worldIn.setBlockState(pos.up(), Seasonal_Blocks.NEWYEAR_XMAS_TOP.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(k)), 10); }

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.block_kadomatsu.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.block_kagamimochi.name")); }
	}
}
