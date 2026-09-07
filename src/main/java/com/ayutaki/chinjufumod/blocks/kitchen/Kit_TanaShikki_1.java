package com.ayutaki.chinjufumod.blocks.kitchen;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class Kit_TanaShikki_1 extends Base_Tana6 {

	public Kit_TanaShikki_1(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_6);

		if (hItem != Items_Teatime.SHIKKI) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.SHIKKI);
				
				if (i == 1) { worldIn.setBlockState(pos, Kitchen_Blocks.KIT_TANA.getDefaultState()
						.with(Kit_Tana.H_FACING, state.get(H_FACING))); }
				else { //i != 1
					worldIn.setBlockState(pos, state.with(STAGE_1_6, Integer.valueOf(i - 1))); } }

			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.SHIKKI) {
			if (i == 6) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			else { //i != 6
				CMEvents.consume1_seWoodDish(worldIn, pos, playerIn, hand);			
				worldIn.setBlockState(pos, state.with(STAGE_1_6, Integer.valueOf(i + 1))); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
}
