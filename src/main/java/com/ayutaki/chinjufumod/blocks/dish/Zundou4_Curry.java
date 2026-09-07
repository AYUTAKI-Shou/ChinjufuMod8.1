package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Zundou4_Curry extends BaseZundou_4Stage {

	public Zundou4_Curry(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hItem == Items_Teatime.RICE) {
			/** Collect with an Item **/
			CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.CURRY, this.takeMeta());
			if (i == 1) { CMEvents.addEXP(1, worldIn, pos, playerIn); }

			if (i == 4) { 
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
						.withProperty(Zundou.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou.STAGE_1_3, Integer.valueOf(2))); }
			
			if (i != 4) { 
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		
		if (hItem != Items_Teatime.RICE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private int takeMeta() {
		if (this == Dish_Blocks.ZUNDOU_CURRY) { return 0; }
		if (this == Dish_Blocks.ZUNDOU_CURRY_C) { return 1; }
		else { return 2; }
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.ZUNDOU_CURRY, 1, cloneMeta())); }
		else { stack.add(new ItemStack(Items_Teatime.ZUNDOU, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.ZUNDOU_CURRY, 1, cloneMeta());
	}

	private int cloneMeta() {
		if (this == Dish_Blocks.ZUNDOU_CURRY) { return 0; }
		if (this == Dish_Blocks.ZUNDOU_CURRY_C) { return 1; }
		else { return 2; }
	}
}
