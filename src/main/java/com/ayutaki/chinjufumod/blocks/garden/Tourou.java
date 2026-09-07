package com.ayutaki.chinjufumod.blocks.garden;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Tourou extends Base_Ishitourou {

	/* Property */
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);

	public Tourou(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hItem == Items.FLINT_AND_STEEL) {
			CMEvents.soundFlint(worldIn, pos);

			worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
					.withProperty(Lit_Tourou.STAGE_1_4, Integer.valueOf(i)));
			CMEvents.toolDamege(1, playerIn, hStack); }

		if (hItem == Items_Teatime.Item_MATCH) {
			CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);	

			worldIn.setBlockState(pos, Garden_Blocks.LIT_ISHITOUROU.getDefaultState()
					.withProperty(Lit_Tourou.STAGE_1_4, Integer.valueOf(i))); }
		
		if (hItem != Items.FLINT_AND_STEEL && hItem != Items_Teatime.Item_MATCH) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
}
