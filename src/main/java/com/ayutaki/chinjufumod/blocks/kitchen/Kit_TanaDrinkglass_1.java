package com.ayutaki.chinjufumod.blocks.kitchen;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Kit_TanaDrinkglass_1 extends Base_Tana9 {

	public Kit_TanaDrinkglass_1(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_9);

		if (hItem != Items_Teatime.DRINKGLASS.get()) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.DRINKGLASS.get());

				if (i == 1) { 
					worldIn.setBlock(pos, Kitchen_Blocks.KIT_TANA.get().defaultBlockState()
						.setValue(Kit_Tana.H_FACING, state.getValue(H_FACING)), 3); }
				else { //i != 1
					worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(i - 1)), 3); } }

			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.DRINKGLASS.get()) {
			if (i == 9) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			else { //i != 9
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);				
				worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(i + 1)), 3); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
}
