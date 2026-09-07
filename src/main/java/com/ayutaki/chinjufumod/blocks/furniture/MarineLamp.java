package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MarineLamp extends Base_MarineLamp {

	public MarineLamp(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		CMEvents.soundStoneButton_On(worldIn, pos);
		worldIn.setBlockState(pos, Lamp_Blocks.LIT_M_LAMP.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		/** 'true' to not put anything on top. **/
		return true;
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		IBlockState state = worldIn.getBlockState(pos.offset(facing.getOpposite()));

		if (state.getBlock() == Lamp_Blocks.M_LAMP) {
			EnumFacing direction = (EnumFacing)state.getValue(FACING);

			if (direction == facing) {
				return this.getDefaultState().withProperty(FACING, facing.getOpposite());
			}
		}
		return this.getDefaultState().withProperty(FACING, facing);
	}

	/* Tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_lamp.name"));
	}
}
