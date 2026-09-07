package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class Zundou4_Ramen extends BaseZundou_4Cook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_Ramen(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		boolean soup = ((hItem == Items_Teatime.RAMEN_nama && k >= 4 && k <= 6)|| hItem == Items_Teatime.Item_SARA);
		boolean other = (hItem == Items_Teatime.RAMEN || (hItem == Items_Teatime.RAMEN_nama && k == 7));
		
		if (!soup && !other) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }

		else {
			/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
			if (i == 3 || i == 4) {

				if (hItem == Items_Teatime.RAMEN_nama && k == 4) {
					/** Collect with an Item **/
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN, 0);
					CMEvents.addEXP(1, worldIn, pos, playerIn); }
				
				if (hItem == Items_Teatime.RAMEN_nama && k == 5) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN, 1);
					CMEvents.addEXP(1, worldIn, pos, playerIn); }
				
				if (hItem == Items_Teatime.RAMEN_nama && k == 6) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN, 2);
					CMEvents.addEXP(1, worldIn, pos, playerIn); }
				
				if (hItem == Items_Teatime.Item_SARA) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_nama, 7); } //item_food_sobaplate
				
				if (soup) {
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_MIZUMILK.getDefaultState()
							.withProperty(Zundou_MizuMilk.H_FACING, state.getValue(H_FACING))
							.withProperty(Zundou_MizuMilk.STAGE_1_4, Integer.valueOf(i - 2))); }
				
				if (!soup) { }
			}
				
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.ZUNDOU_MIZU, 1, 0);
	}
}
