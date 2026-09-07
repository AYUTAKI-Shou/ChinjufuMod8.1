package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class WallPane_Stage3Stone extends BaseStage3_WP {

	public WallPane_Stage3Stone(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		if (hStack.isEmpty() && playerIn.isCrouching()) {
			CMEvents.soundStonePlace(worldIn, pos);
			worldIn.setBlock(pos, state.cycle(STAGE_1_3), 3);
			return InteractionResult.SUCCESS; }
		
		return InteractionResult.PASS;
	}
}
