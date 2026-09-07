package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlabW;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Pantry_Box extends Box_Base {

	public Pantry_Box(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean block2Item = ((this == Pantry_Blocks.BOX_H_APPLE && hItem == Items_Teatime.BOX_H_APPLE) ||
				(this == Pantry_Blocks.BOX_H_BEEF && hItem == Items_Teatime.BOX_H_BEEF) ||
				(this == Pantry_Blocks.BOX_H_BEETROOT && hItem == Items_Teatime.BOX_H_BEETROOT) ||
				(this == Pantry_Blocks.BOX_H_BREAD && hItem == Items_Teatime.BOX_H_BREAD) ||
				(this == Pantry_Blocks.BOX_H_CARROT && hItem == Items_Teatime.BOX_H_CARROT) ||
				(this == Pantry_Blocks.BOX_H_CHICKEN && hItem == Items_Teatime.BOX_H_CHICKEN) ||
				(this == Pantry_Blocks.BOX_H_CHORUS && hItem == Items_Teatime.BOX_H_CHORUS) ||
				(this == Pantry_Blocks.BOX_H_EGG && hItem == Items_Teatime.BOX_H_EGG) ||
				(this == Pantry_Blocks.BOX_H_FISH && hItem == Items_Teatime.BOX_H_FISH) ||
				(this == Pantry_Blocks.BOX_H_MUTTON && hItem == Items_Teatime.BOX_H_MUTTON) ||
				(this == Pantry_Blocks.BOX_H_PORK && hItem == Items_Teatime.BOX_H_PORK) ||
				(this == Pantry_Blocks.BOX_H_POTATO && hItem == Items_Teatime.BOX_H_POTATO) ||
				(this == Pantry_Blocks.BOX_H_RABBIT && hItem == Items_Teatime.BOX_H_RABBIT) ||
				(this == Pantry_Blocks.BOX_H_SALMON && hItem == Items_Teatime.BOX_H_SALMON));
				
		if (hStack.isEmpty()) {
			boolean stateW = state.getValue(DOUBLE);
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), 8 * gHC, this.takeMeta());
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF && this != Pantry_Blocks.BOX_H_CHICKEN &&
					this != Pantry_Blocks.BOX_H_EGG && this != Pantry_Blocks.BOX_H_FISH && this != Pantry_Blocks.BOX_H_MUTTON &&
					this != Pantry_Blocks.BOX_H_PORK && this != Pantry_Blocks.BOX_H_RABBIT && this != Pantry_Blocks.BOX_H_SALMON);

			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY : Pantry_Blocks.BOX_H_EMPTY2);
			worldIn.setBlockState(pos, takeType.getDefaultState()
					.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
					.withProperty(BaseFacingSlabW.DOUBLE, state.getValue(DOUBLE))
					.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
			
			return true; } 
		
		/* Slab */
		if (hItem instanceof ItemBlock) { 
			if (block2Item) {
				if (!state.getValue(DOUBLE)) {
					if (state.getValue(HALF) != SlabHalf.TOP && facing == EnumFacing.UP) {
						CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; }
					
					if (state.getValue(HALF) == SlabHalf.TOP && (double)hitY <= 0.6D) {
						CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; } } }
			return false; }

		return true;
	}

	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_APPLE) { return Items.APPLE; }
		if (this == Pantry_Blocks.BOX_H_BEEF) { return Items.BEEF; }
		if (this == Pantry_Blocks.BOX_H_BEETROOT) { return Items.BEETROOT; }
		if (this == Pantry_Blocks.BOX_H_BREAD) { return Items.BREAD; }
		if (this == Pantry_Blocks.BOX_H_CARROT) { return Items.CARROT; }
		if (this == Pantry_Blocks.BOX_H_CHICKEN) { return Items.CHICKEN; }
		if (this == Pantry_Blocks.BOX_H_CHORUS) { return Items.CHORUS_FRUIT; }
		if (this == Pantry_Blocks.BOX_H_EGG) { return Items.EGG; }
		if (this == Pantry_Blocks.BOX_H_FISH) { return Items.FISH; }
		if (this == Pantry_Blocks.BOX_H_MUTTON) { return Items.MUTTON; }
		if (this == Pantry_Blocks.BOX_H_PORK) { return Items.PORKCHOP; }
		if (this == Pantry_Blocks.BOX_H_POTATO) { return Items.POTATO; }
		if (this == Pantry_Blocks.BOX_H_RABBIT) { return Items.RABBIT; }
		else { return Items.FISH; }
	}

	private int takeMeta() {
		if (this == Pantry_Blocks.BOX_H_SALMON) { return 1; }
		else { return 0; }
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

		if (this == Pantry_Blocks.BOX_H_APPLE) { stack.add(new ItemStack(Items.APPLE, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_BEEF) { stack.add(new ItemStack(Items.BEEF, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_BEETROOT) { stack.add(new ItemStack(Items.BEETROOT, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_BREAD) { stack.add(new ItemStack(Items.BREAD, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_CARROT) { stack.add(new ItemStack(Items.CARROT, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_CHICKEN) { stack.add(new ItemStack(Items.CHICKEN, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_CHORUS) { stack.add(new ItemStack(Items.CHORUS_FRUIT, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_EGG) { stack.add(new ItemStack(Items.EGG, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_FISH) { stack.add(new ItemStack(Items.FISH, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_MUTTON) { stack.add(new ItemStack(Items.MUTTON, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_PORK) { stack.add(new ItemStack(Items.PORKCHOP, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_POTATO) { stack.add(new ItemStack(Items.POTATO, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_RABBIT) { stack.add(new ItemStack(Items.RABBIT, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_SALMON) { stack.add(new ItemStack(Items.FISH, 8 * gHC, 1)); }
		
		stack.add(new ItemStack(Items_Teatime.BOX_H_EMPTY, gHC, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Pantry_Blocks.BOX_H_APPLE) { return Items_Teatime.BOX_H_APPLE; }
		if (this == Pantry_Blocks.BOX_H_BEEF) { return Items_Teatime.BOX_H_BEEF; }
		if (this == Pantry_Blocks.BOX_H_BEETROOT) { return Items_Teatime.BOX_H_BEETROOT; }
		if (this == Pantry_Blocks.BOX_H_BREAD) { return Items_Teatime.BOX_H_BREAD; }
		if (this == Pantry_Blocks.BOX_H_CARROT) { return Items_Teatime.BOX_H_CARROT; }
		if (this == Pantry_Blocks.BOX_H_CHICKEN) { return Items_Teatime.BOX_H_CHICKEN; }
		if (this == Pantry_Blocks.BOX_H_CHORUS) { return Items_Teatime.BOX_H_CHORUS; }
		if (this == Pantry_Blocks.BOX_H_EGG) { return Items_Teatime.BOX_H_EGG; }
		if (this == Pantry_Blocks.BOX_H_FISH) { return Items_Teatime.BOX_H_FISH; }
		if (this == Pantry_Blocks.BOX_H_MUTTON) { return Items_Teatime.BOX_H_MUTTON; }
		if (this == Pantry_Blocks.BOX_H_PORK) { return Items_Teatime.BOX_H_PORK; }
		if (this == Pantry_Blocks.BOX_H_POTATO) { return Items_Teatime.BOX_H_POTATO; }
		if (this == Pantry_Blocks.BOX_H_RABBIT) { return Items_Teatime.BOX_H_RABBIT; }
		else { return Items_Teatime.BOX_H_SALMON; }
	}
}
