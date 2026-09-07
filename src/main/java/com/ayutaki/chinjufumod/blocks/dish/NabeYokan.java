package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class NabeYokan extends BaseNabe_Kanten {

	public NabeYokan(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_0_15);

		Item hItem = hStack.getItem();
		boolean COMPLETE = (i == 7 || i == 15);

		if (COMPLETE) {
			if (hItem == Items.BOWL) {
				Direction FACE = (i == 7)? Direction.NORTH : Direction.EAST;
				BlockState emptyNABE = Dish_Blocks.NABE_kara.getDefaultState()
						.with(Nabe_kara.H_FACING, FACE)
						.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4));
				
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, this.takeItem());
				worldIn.setBlockState(pos, emptyNABE, 3);
			}
			
			if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		} //COMPLETE
		
		if (!COMPLETE) {
			if (this == Dish_Blocks.NABEK_YOKAN) {
				boolean START = (i == 0 || i == 8);
				
				if (START) {
					if (hItem == Items_Teatime.MATCHA) {
						CMEvents.consumeN_seSnowP(1, worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, Dish_Blocks.NABEK_MATCHA.getDefaultState()
								.with(STAGE_0_15, Integer.valueOf(i)), 3); }
					
					else { this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
				}
				
				if (!START) { this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
			} //isYOKAN
			
			if (this != Dish_Blocks.NABEK_YOKAN) { 
				this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
		} //notCOMPLETE
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABEK_YOKAN) { return Items_NoTab.RAW_YOKAN; }
		else { return Items_NoTab.RAW_YOKAN_MATCHA; }
	}
}
