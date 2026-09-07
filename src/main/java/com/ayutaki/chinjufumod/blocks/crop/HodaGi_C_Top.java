package com.ayutaki.chinjufumod.blocks.crop;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HodaGi_C_Top extends Base_HodaGi_Top {

	public HodaGi_C_Top(String name) {
		super(name);
	}

	/* RightClick Action */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hStack.isEmpty()) {
				CMEvents.take_KINOKO(worldIn, pos, playerIn);
				worldIn.setBlockState(pos, Crop_Blocks.HODAGI_A_TOP.getDefaultState()
						.withProperty(Base_HodaGi_Top.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_HodaGi_Top.STAGE_1_4, Integer.valueOf(i - 1))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}
}
