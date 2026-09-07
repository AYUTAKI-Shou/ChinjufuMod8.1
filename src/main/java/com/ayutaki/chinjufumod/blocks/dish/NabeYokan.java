package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class NabeYokan extends BaseNabe_Kanten {
	
	public NabeYokan(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_0_15);

		Item hItem = hStack.getItem();
		boolean COMPLETE = (i == 7 || i == 15);

		if (COMPLETE) {
			if (hItem == Items.BOWL) {
				Direction FACE = (i == 7)? Direction.NORTH : Direction.EAST;
				BlockState emptyNABE = Dish_Blocks.NABE_kara.get().defaultBlockState()
						.setValue(Nabe_kara.H_FACING, FACE)
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4));
				
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, this.takeItem());
				worldIn.setBlock(pos, emptyNABE, 3);
			}
			
			if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		} //COMPLETE
		
		if (!COMPLETE) {
			if (this == Dish_Blocks.NABEK_YOKAN.get()) {
				boolean START = (i == 0 || i == 8);
				
				if (START) {
					if (hItem == Items_Teatime.MATCHA.get()) {
						CMEvents.consumeN_seSnowP(1, worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.NABEK_MATCHA.get().defaultBlockState()
								.setValue(STAGE_0_15, Integer.valueOf(i)), 3); }
					
					else { this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
				}
				
				if (!START) { this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
			} //isYOKAN
			
			if (this != Dish_Blocks.NABEK_YOKAN.get()) { 
				this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
		} //notCOMPLETE
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABEK_YOKAN.get()) { return Items_NoTab.RAW_YOKAN.get(); }
		else { return Items_NoTab.RAW_YOKAN_MATCHA.get(); }
	}
}
