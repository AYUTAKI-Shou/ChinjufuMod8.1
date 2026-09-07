package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class WallPane_Stage3Stone extends BaseStage3_WP {

	public WallPane_Stage3Stone(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty() && playerIn.isSneaking()) {
			CMEvents.soundStonePlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycle(STAGE_1_3));
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
