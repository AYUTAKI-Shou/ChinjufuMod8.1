package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Wadeco;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Wadaiko_DT extends IBR_Wadeco {

	public Wadaiko_DT(String name) {
		super(name, JPDeco_Blocks.WADAIKO_BOT);
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

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(JPDeco_Blocks.WADAIKO_BOT, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			worldIn.setBlockState(pos, JPDeco_Blocks.WADAIKO_BOT.getDefaultState()
					.withProperty(BaseStage3_Face.H_FACING, direction)
					.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)), 10);
			worldIn.setBlockState(pos.up(), JPDeco_Blocks.WADAIKO_TOP.getDefaultState()
					.withProperty(BaseStage3_Face.H_FACING, direction)
					.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)), 10);

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_wadaiko.name"));
	}
}
