package com.ayutaki.chinjufumod.blocks.garden;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Takeakari extends Base_Takeakari {

	public Takeakari(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_3);
		
		if (hItem == Items.FLINT_AND_STEEL) {
			CMEvents.soundFlint(worldIn, pos);
			
			worldIn.setBlockState(pos, Lamp_Blocks.LIT_TAKEAKARI.getDefaultState()
					.withProperty(Lit_Takeakari.H_FACING, state.getValue(H_FACING))
					.withProperty(Lit_Takeakari.STAGE_1_3, Integer.valueOf(i)));			
			CMEvents.toolDamege(1, playerIn, hStack); }

		if (hItem == Items_Teatime.Item_MATCH) {
			CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);	
			worldIn.setBlockState(pos, Lamp_Blocks.LIT_TAKEAKARI.getDefaultState()
					.withProperty(Lit_Takeakari.H_FACING, state.getValue(H_FACING))
					.withProperty(Lit_Takeakari.STAGE_1_3, Integer.valueOf(i))); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
}
