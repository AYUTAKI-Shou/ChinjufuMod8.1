package com.ayutaki.chinjufumod.blocks.wallpane;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseSlabW;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_WallPane;
import com.ayutaki.chinjufumod.registry.WallBrick_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
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

public class StoneCM_Slab extends BaseSlabW {

	public StoneCM_Slab(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.WALLPANEL);

		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean block2Item = ((this == WallBrick_Blocks.RGRA_slabhalf && hItem == Items_WallPane.RGRA_slabhalf) ||
				(this == WallBrick_Blocks.RDIO_slabhalf && hItem == Items_WallPane.RDIO_slabhalf) ||
				(this == WallBrick_Blocks.RAND_slabhalf && hItem == Items_WallPane.RAND_slabhalf) ||
				(this == WallBrick_Blocks.BGC_slabhalf && hItem == Items_WallPane.BGC_slabhalf) ||
				(this == WallBrick_Blocks.BDC_slabhalf && hItem == Items_WallPane.BDC_slabhalf) ||
				(this == WallBrick_Blocks.BAC_slabhalf && hItem == Items_WallPane.BAC_slabhalf));
				
		/* Slab */
		if (hItem instanceof ItemBlock) { 
			if (block2Item) {
				if (!state.getValue(DOUBLE)) {
					if (state.getValue(HALF) != SlabHalf.TOP && facing == EnumFacing.UP) {
						CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; }
					
					if (state.getValue(HALF) == SlabHalf.TOP && (double)hitY <= 0.6D) {
						CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; } } }
			return false; }

		/** 側面で設置可能にするため false **/
		return false;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int w = state.getValue(DOUBLE)? 2 : 1;
		stack.add(new ItemStack(cloneItem(), w, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn,
			BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}

	private Item cloneItem() {
		if (this == WallBrick_Blocks.RGRA_slabhalf) { return Items_WallPane.RGRA_slabhalf; }
		if (this == WallBrick_Blocks.RDIO_slabhalf) { return Items_WallPane.RDIO_slabhalf; }
		if (this == WallBrick_Blocks.RAND_slabhalf) { return Items_WallPane.RAND_slabhalf; }
		if (this == WallBrick_Blocks.BGC_slabhalf) { return Items_WallPane.BGC_slabhalf; }
		if (this == WallBrick_Blocks.BDC_slabhalf) { return Items_WallPane.BDC_slabhalf; }
		else { return Items_WallPane.BAC_slabhalf; }
	}
}
