package com.ayutaki.chinjufumod.items.crops;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.crop.Chanoki;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
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

public class ChanokiNae_TT extends IBR_Teatime {

	public ChanokiNae_TT(String name) {
		super(name, Crop_Blocks.CHANOKI);
		setUnlocalizedName(name);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean canPlace = (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable());
				
		if (state.getBlock() instanceof BlockDirt && !hStack.isEmpty() && canPlace) {

			worldIn.setBlockState(pos, Crop_Blocks.CHANOKI.getDefaultState().withProperty(Chanoki.STAGE_0_7, Integer.valueOf(0)), 10);
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}


	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_wood_chanoki_nae.name"));
	}
}
