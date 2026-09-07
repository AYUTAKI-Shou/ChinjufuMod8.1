package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.season.SnowCore;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.TabBlock_noFuel;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SnowCore_TB extends TabBlock_noFuel {

	public SnowCore_TB(String name, Block putBlock) {
		super(name, putBlock);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Seasonal_Blocks.SNOWCORE, pos, false, facing, (Entity)null)) {

			if (block == Blocks.SNOW_LAYER) {
				IBlockState putSTATE = Seasonal_Blocks.SNOWCORE.getDefaultState().withProperty(SnowCore.STAGE_0_9, Integer.valueOf(1));
				worldIn.setBlockState(pos, putSTATE, 10); }
			
			if (block != Blocks.SNOW_LAYER) {
				IBlockState putSTATE = Seasonal_Blocks.SNOWCORE.getDefaultState().withProperty(SnowCore.STAGE_0_9, Integer.valueOf(0));
				worldIn.setBlockState(pos, putSTATE, 10); }

			CMEvents.ItemBlock_Snow(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_snowcore.name"));
	}
}
