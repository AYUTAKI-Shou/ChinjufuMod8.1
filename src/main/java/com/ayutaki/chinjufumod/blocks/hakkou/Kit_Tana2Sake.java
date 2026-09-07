package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

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

public class Kit_Tana2Sake extends Base_WineTana {

	public Kit_Tana2Sake(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);

		if (hItem == this.takeItem()) {
			if (i == 4) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			
			else { // != 4
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);			
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1))); }
		}
		
		else { //!= this.takeItem()
			if (hStack.isEmpty()) {
				CMEvents.takeSAKEBottle_Fill(worldIn, pos, playerIn, hand, this.takeItem());
	
				if (i == 1) { worldIn.setBlockState(pos, Hakkou_Blocks.WINE_TANA.getDefaultState()
						.with(BaseFacingWater.H_FACING, state.get(H_FACING))); }
				
				else { // != 1
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i - 1))); } }
			
			else { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Hakkou_Blocks.KIT_SAKENAMA) { return Items_Teatime.NAMASAKEBOT; }
		if (this == Hakkou_Blocks.KIT_SAKE) { return Items_Teatime.SAKEBOT; }
		if (this == Hakkou_Blocks.KIT_SAKEJUKU) { return Items_Teatime.JUKUSAKEBOT; }
		if (this == Hakkou_Blocks.KIT_CIDER) { return Items_Teatime.CIDERBOT; }
		if (this == Hakkou_Blocks.KIT_CIDERJUKU) { return Items_Teatime.JUKUCIDERBOT; }
		if (this == Hakkou_Blocks.KIT_WINE) { return Items_Teatime.WINEBOT; }
		if (this == Hakkou_Blocks.KIT_WINEJUKU) { return Items_Teatime.JUKUWINEBOT; }
		if (this == Hakkou_Blocks.KIT_MEAD) { return Items_Teatime.MEADBOT; }
		else { return Items_Teatime.JUKUMEADBOT; }
	}
}
