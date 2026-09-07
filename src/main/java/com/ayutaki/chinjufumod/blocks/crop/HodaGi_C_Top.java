package com.ayutaki.chinjufumod.blocks.crop;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class HodaGi_C_Top extends Base_HodaGi_Top {

	public HodaGi_C_Top(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_4);
		
		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hStack.isEmpty()) {

				CMEvents.take_KINOKO(worldIn, pos, playerIn);
				worldIn.setBlockState(pos, Crop_Blocks.HODAGI_A_TOP.getDefaultState()
						.with(Base_HodaGi_Top.H_FACING, state.get(H_FACING))
						.with(Base_HodaGi_Top.STAGE_1_4, Integer.valueOf(i - 1))
						.with(Base_HodaGi_Top.WATERLOGGED, state.get(WATERLOGGED))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
}
