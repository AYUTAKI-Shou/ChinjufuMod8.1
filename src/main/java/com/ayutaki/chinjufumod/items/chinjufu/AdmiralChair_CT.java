package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

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

public class AdmiralChair_CT extends IBR_Chinjufu {

	public AdmiralChair_CT(String name) {
		super(name, Chinjufu_Blocks.ADMIRALCHAIR);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_admiralchair";
		case 2:
			return "item." + "block_admiralchair_red";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
		}
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Chinjufu_Blocks.ADMIRALCHAIR, pos, false, facing, (Entity)null)) {

			worldIn.setBlockState(pos, Chinjufu_Blocks.ADMIRALCHAIR.getDefaultState()
					.withProperty(BaseStage2_Face.H_FACING, direction)
					.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(k)), 10);
			worldIn.setBlockState(pos.up(), Chinjufu_Blocks.ADMIRALCHAIR_TOP.getDefaultState()
					.withProperty(BaseStage2_Face.H_FACING, direction)
					.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(k)), 10);

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS; }

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_isu.name"));
	}
}
