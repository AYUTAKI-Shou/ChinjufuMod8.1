package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.block.AbstractBlock;
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

public class NabeKanten extends BaseNabe_Kanten {

	public NabeKanten(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_0_15);

		Item hItem = hStack.getItem();
		boolean COMPLETE = (i == 7 || i == 15);
		
		if (COMPLETE) {
			if (hItem == Items.BOWL) {
				Direction FACE = (i == 7)? Direction.NORTH : Direction.EAST;
				BlockState emptyNABE = Dish_Blocks.NABE_kara.defaultBlockState()
						.setValue(Nabe_kara.H_FACING, FACE)
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4));
				
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, this.takeItem());
				worldIn.setBlock(pos, emptyNABE, 3);
			}
			
			if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		} //COMPLETE
		
		if (!COMPLETE) {
			this.mixKANTEN(state, worldIn, pos, playerIn, hand);
		} //notCOMPLETE
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABEK_CHERRY) { return Items_NoTab.RAW_KANTEN_CHERRY; }
		if (this == Dish_Blocks.NABEK_CITRUS) { return Items_NoTab.RAW_KANTEN_CITRUS; }
		if (this == Dish_Blocks.NABEK_GRAPE) { return Items_NoTab.RAW_KANTEN_GRAPE; }
		else { return Items_NoTab.RAW_KANTEN_APPLE; }
	}
}
