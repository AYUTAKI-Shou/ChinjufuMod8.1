package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NabeKantenMilk extends BaseNabe_Kanten {

	public NabeKantenMilk(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		Item hItem = hStack.getItem();
		boolean COMPLETE = (i == 7 || i == 15);
		
		if (COMPLETE) {
			if (hItem == Items.BOWL) {
				EnumFacing FACE = (i == 7)? EnumFacing.NORTH : EnumFacing.EAST;
				IBlockState emptyNABE = Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, FACE)
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4));
				
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, Items_NoTab.KANTEN_RAW, this.takeMeta());
				worldIn.setBlockState(pos, emptyNABE, 3);
			}
			
			if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		} //COMPLETE
		
		if (!COMPLETE) {
			boolean START = (i == 0 || i == 8);
			
			if (START) {
				if (hItem == Items.SUGAR) {
					
					int gHC = hStack.getCount();
					if (gHC >= 3) {
						CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
					
					else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
				
				if (hItem != Items.SUGAR) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			if (!START) { this.mixKANTEN(state, worldIn, pos, playerIn, hand); }
		} //notCOMPLETE
		
		/** SUCCESS to not put anything on top. **/
		return true;
	}
	
	private int takeMeta() {
		return 4;
	}
}
