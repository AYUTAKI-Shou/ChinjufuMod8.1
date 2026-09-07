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

public class Zundou4_Pasta extends BaseZundou_4Cook {

	public Zundou4_Pasta(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hItem == Items_Teatime.Item_SARA) {
			if (i == 3 || i == 4) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.PASTA, 2); //item_food_pasta_s
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Dish_Blocks.SHIOAKUNABE.getDefaultState()
						.withProperty(Zundou_MizuMilk.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou_MizuMilk.STAGE_1_4, Integer.valueOf(i - 2))); }
		
			else { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		if (hItem != Items_Teatime.Item_SARA && hItem != Items_Teatime.PASTA && k != 1) { 
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.ZUNDOUSHIO, 1, 0);
	}
}
