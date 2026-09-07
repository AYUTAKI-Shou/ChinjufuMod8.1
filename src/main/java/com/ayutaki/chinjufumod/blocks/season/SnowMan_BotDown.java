package com.ayutaki.chinjufumod.blocks.season;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class SnowMan_BotDown extends Base_SnowManBot {

	public SnowMan_BotDown(String name) {
		super(name);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/** TOP1 1=normal, 2=carrot, 3=Roma, 4=blank **/
		/** TOP2 White=0, Orange=1, Magenta=2, LightBlue=3 **/
		/** TOP3 Yellow=4, Lime=5, Pink=6, Gray=7, **/
		/** TOP4 LightGray=8, Cyan=9, Purple=10, Blue=11 **/
		/** TOP5 Brown=12, Green=13 Red=14, Black=15 **/
		
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (this == Seasonal_Blocks.SNOWMAN_BOT1D) {
			if (i == 1) { stack.add(new ItemStack(Items_Seasonal.SNOWMAN, 1, 0)); }
			if (i == 2) { 
				stack.add(new ItemStack(Items_Seasonal.SNOWMAN, 1, 0));
				stack.add(new ItemStack(Items.CARROT, 1, 0)); }
			if (i == 3) { 
				stack.add(new ItemStack(Items_Seasonal.SNOWMAN, 1, 0));
				stack.add(new ItemStack(Items.CARROT, 1, 0));
				stack.add(new ItemStack(Items_Teatime.FOOD_TOMATO, 1, 0)); }
			if (i == 4) { 
				stack.add(new ItemStack(Items_Seasonal.SNOWMAN, 1, 0));
				stack.add(new ItemStack(Items.CARROT, 1, 0));
				stack.add(new ItemStack(Items.BUCKET, 1, 0)); } }
		
		else {
			if (this == Seasonal_Blocks.SNOWMAN_BOT2D) {
				stack.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, i - 1)); }
			
			if (this == Seasonal_Blocks.SNOWMAN_BOT3D) {
				stack.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, i + 3)); }
			
			if (this == Seasonal_Blocks.SNOWMAN_BOT4D) {
				stack.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, i + 7)); }
			
			if (this == Seasonal_Blocks.SNOWMAN_BOT5D) {
				stack.add(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, i + 11)); }
			
			stack.add(new ItemStack(Items_Seasonal.SNOWMAN, 1, 0));
			stack.add(new ItemStack(Items.CARROT, 1, 0)); }
		
		return stack;
	}
}
