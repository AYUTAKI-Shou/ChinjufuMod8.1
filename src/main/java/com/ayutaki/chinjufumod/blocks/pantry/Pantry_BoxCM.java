package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlabW;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Pantry_BoxCM extends Box_Base {

	public Pantry_BoxCM(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean block2Item = ((this == Pantry_Blocks.BOX_H_CABBAGE && hItem == Items_Teatime.BOX_H_CABBAGE) ||
				(this == Pantry_Blocks.BOX_H_HAKUSAI && hItem == Items_Teatime.BOX_H_HAKUSAI) ||
				(this == Pantry_Blocks.BOX_H_CHERRY && hItem == Items_Teatime.BOX_H_CHERRY) ||
				(this == Pantry_Blocks.BOX_H_CITRUS && hItem == Items_Teatime.BOX_H_CITRUS) ||
				(this == Pantry_Blocks.BOX_H_CORN && hItem == Items_Teatime.BOX_H_CORN) ||
				(this == Pantry_Blocks.BOX_H_GREENONION && hItem == Items_Teatime.BOX_H_GREENONION) ||
				(this == Pantry_Blocks.BOX_H_GRAPE && hItem == Items_Teatime.BOX_H_GRAPE) ||
				(this == Pantry_Blocks.BOX_H_ONION && hItem == Items_Teatime.BOX_H_ONION) ||
				(this == Pantry_Blocks.BOX_H_ORIENTCLAM && hItem == Items_Teatime.BOX_H_ORIENTCLAM) ||
				(this == Pantry_Blocks.BOX_H_SPINACH && hItem == Items_Teatime.BOX_H_SPINACH) ||
				(this == Pantry_Blocks.BOX_H_SQUID && hItem == Items_Teatime.BOX_H_SQUID) ||
				(this == Pantry_Blocks.BOX_H_TOMATO && hItem == Items_Teatime.BOX_H_TOMATO) ||
				(this == Pantry_Blocks.BOX_H_TAKENOKO && hItem == Items_Teatime.BOX_H_TAKENOKO));
		
		if (hStack.isEmpty()) {
			int amount = (this == Pantry_Blocks.BOX_H_SQUID || this == Pantry_Blocks.BOX_H_HAKUSAI)? 2 : 
				((this == Pantry_Blocks.BOX_H_CABBAGE || this == Pantry_Blocks.BOX_H_GREENONION)? 4 : 8);
			
			boolean stateW = state.getValue(DOUBLE);
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), amount * gHC, 0);
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_CHERRY && this != Pantry_Blocks.BOX_H_SQUID);
			
			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY : Pantry_Blocks.BOX_H_EMPTY2);
			worldIn.setBlockState(pos, takeType.getDefaultState()
					.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
					.withProperty(BaseFacingSlabW.DOUBLE, state.getValue(DOUBLE))
					.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
			
			return true; } 
		
		if (hItem instanceof ItemBlock) { 
			if (block2Item) {
				if (!state.getValue(DOUBLE)) {
					if (state.getValue(HALF) != SlabHalf.TOP && facing == EnumFacing.UP) {
						CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state .cycleProperty(DOUBLE), 2);
						return true; }
					
					if (state.getValue(HALF) == SlabHalf.TOP && (double)hitY <= 0.6D) {
						CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; } } }
			return false; }

		return true;
	}

	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_CABBAGE) { return Items_Teatime.FOOD_CABBAGE; }
		if (this == Pantry_Blocks.BOX_H_HAKUSAI) { return Items_Teatime.FOOD_HAKUSAI; }
		if (this == Pantry_Blocks.BOX_H_CHERRY) { return Items_Teatime.FOOD_CHERRY; }
		if (this == Pantry_Blocks.BOX_H_CITRUS) { return Items_Teatime.FOOD_MIKAN; }
		if (this == Pantry_Blocks.BOX_H_CORN) { return Items_Teatime.FOOD_CORN; }
		if (this == Pantry_Blocks.BOX_H_GREENONION) { return Items_Teatime.FOOD_GREENONION; }
		if (this == Pantry_Blocks.BOX_H_GRAPE) { return Items_Teatime.FOOD_GRAPE; }
		if (this == Pantry_Blocks.BOX_H_ONION) { return Items_Teatime.FOOD_ONION; }
		if (this == Pantry_Blocks.BOX_H_ORIENTCLAM) { return Items_Teatime.HAMAGURI; }
		if (this == Pantry_Blocks.BOX_H_SPINACH) { return Items_Teatime.FOOD_SPINACH; }
		if (this == Pantry_Blocks.BOX_H_SQUID) { return Items_Teatime.IKA; }
		if (this == Pantry_Blocks.BOX_H_TOMATO) { return Items_Teatime.FOOD_TOMATO; }
		else { return Items_Seasonal.TAKENOKO; }
	}
	
	/*Drop Item and Clone Item.*/
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		int gHC = state.getValue(DOUBLE)? 2 : 1;
		return new ItemStack(cloneItem(), gHC, 0);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int gHC = state.getValue(DOUBLE)? 2 : 1;

		if (this == Pantry_Blocks.BOX_H_CABBAGE) { stack.add(new ItemStack(Items_Teatime.FOOD_CABBAGE, 4 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_HAKUSAI) { stack.add(new ItemStack(Items_Teatime.FOOD_HAKUSAI, 2 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_CHERRY) { stack.add(new ItemStack(Items_Teatime.FOOD_CHERRY, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_CITRUS) { stack.add(new ItemStack(Items_Teatime.FOOD_MIKAN, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_CORN) { stack.add(new ItemStack(Items_Teatime.FOOD_CORN, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_GREENONION) { stack.add(new ItemStack(Items_Teatime.FOOD_GREENONION, 4 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_GRAPE) { stack.add(new ItemStack(Items_Teatime.FOOD_GRAPE, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_ONION) { stack.add(new ItemStack(Items_Teatime.FOOD_ONION, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_ORIENTCLAM) { stack.add(new ItemStack(Items_Teatime.HAMAGURI, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_SPINACH) { stack.add(new ItemStack(Items_Teatime.FOOD_SPINACH, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_SQUID) { stack.add(new ItemStack(Items_Teatime.IKA, 2 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_TOMATO) { stack.add(new ItemStack(Items_Teatime.FOOD_TOMATO, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_TAKENOKO) { stack.add(new ItemStack(Items_Seasonal.TAKENOKO, 8 * gHC, 0)); }
		
		stack.add(new ItemStack(Items_Teatime.BOX_H_EMPTY,gHC, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Pantry_Blocks.BOX_H_CABBAGE) { return Items_Teatime.BOX_H_CABBAGE; }
		if (this == Pantry_Blocks.BOX_H_HAKUSAI) { return Items_Teatime.BOX_H_HAKUSAI; }
		if (this == Pantry_Blocks.BOX_H_CHERRY) { return Items_Teatime.BOX_H_CHERRY; }
		if (this == Pantry_Blocks.BOX_H_CITRUS) { return Items_Teatime.BOX_H_CITRUS; }
		if (this == Pantry_Blocks.BOX_H_CORN) { return Items_Teatime.BOX_H_CORN; }
		if (this == Pantry_Blocks.BOX_H_GREENONION) { return Items_Teatime.BOX_H_GREENONION; }
		if (this == Pantry_Blocks.BOX_H_GRAPE) { return Items_Teatime.BOX_H_GRAPE; }
		if (this == Pantry_Blocks.BOX_H_ONION) { return Items_Teatime.BOX_H_ONION; }
		if (this == Pantry_Blocks.BOX_H_ORIENTCLAM) { return Items_Teatime.BOX_H_ORIENTCLAM; }
		if (this == Pantry_Blocks.BOX_H_SPINACH) { return Items_Teatime.BOX_H_SPINACH; }
		if (this == Pantry_Blocks.BOX_H_SQUID) { return Items_Teatime.BOX_H_SQUID; }
		if (this == Pantry_Blocks.BOX_H_TOMATO) { return Items_Teatime.BOX_H_TOMATO; }
		else { return Items_Teatime.BOX_H_TAKENOKO; }
	}
}
