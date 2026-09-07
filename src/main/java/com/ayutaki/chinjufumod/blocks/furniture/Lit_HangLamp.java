package com.ayutaki.chinjufumod.blocks.furniture;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Lit_HangLamp extends Base_HangLamp {

	public Lit_HangLamp(String name) {
		super(name);
		/* Glow Stone=1.0F, Torch=0.9375F */
		setLightLevel(1.0F);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		CMEvents.soundStoneButton_Off(worldIn, pos);
		worldIn.setBlockState(pos, Lamp_Blocks.LAMP.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
		/** 'true' to not put anything on top. **/
		return true;
	}
}
