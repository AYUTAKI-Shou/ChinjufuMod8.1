package com.ayutaki.chinjufumod.blocks.crop;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class HodaGi_A_Top extends Base_HodaGi_Top {

	public HodaGi_A_Top(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hStack.isEmpty()) {
				CMEvents.take_KINOKO(worldIn, pos, playerIn);
				
				worldIn.setBlock(pos, Crop_Blocks.HODAGI_B_TOP.get().defaultBlockState()
						.setValue(Base_HodaGi_Top.H_FACING, state.getValue(H_FACING))
						.setValue(Base_HodaGi_Top.STAGE_1_4,Integer.valueOf(i - 1))
						.setValue(Base_HodaGi_Top.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
}
