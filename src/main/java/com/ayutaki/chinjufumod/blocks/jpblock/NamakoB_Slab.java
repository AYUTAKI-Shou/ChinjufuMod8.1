package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

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

public class NamakoB_Slab extends Base_Slab_JP {

	public NamakoB_Slab(String name) {
		super(name);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (this == JPBlock_Blocks.NAMAKOB_SH_white) { return MapColor.SNOW; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_orange) { return MapColor.ADOBE; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_magenta) { return MapColor.MAGENTA; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_lightb) { return MapColor.LIGHT_BLUE; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_yellow) { return MapColor.YELLOW; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_lime) { return MapColor.LIME; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_pink) { return MapColor.PINK; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_gray) { return MapColor.GRAY; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_lightg) { return MapColor.SILVER; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_cyan) { return MapColor.CYAN; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_purple) { return MapColor.PURPLE; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_blue) { return MapColor.BLUE; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_brown) { return MapColor.BROWN; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_green) { return MapColor.GREEN; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_red) { return MapColor.RED; }
		else { return MapColor.BLACK; }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean block2Item = ((this == JPBlock_Blocks.NAMAKOB_SH_white && hItem == Items_Wablock.NAMAKOB_SH_white) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_orange && hItem == Items_Wablock.NAMAKOB_SH_orange) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_magenta && hItem == Items_Wablock.NAMAKOB_SH_magenta) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_yellow && hItem == Items_Wablock.NAMAKOB_SH_yellow) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_lime && hItem == Items_Wablock.NAMAKOB_SH_lime) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_pink && hItem == Items_Wablock.NAMAKOB_SH_pink) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_gray && hItem == Items_Wablock.NAMAKOB_SH_gray) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_lightb && hItem == Items_Wablock.NAMAKOB_SH_lightb) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_lightg && hItem == Items_Wablock.NAMAKOB_SH_lightg) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_cyan && hItem == Items_Wablock.NAMAKOB_SH_cyan) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_purple && hItem == Items_Wablock.NAMAKOB_SH_purple) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_blue && hItem == Items_Wablock.NAMAKOB_SH_blue) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_brown && hItem == Items_Wablock.NAMAKOB_SH_brown) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_green && hItem == Items_Wablock.NAMAKOB_SH_green) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_red && hItem == Items_Wablock.NAMAKOB_SH_red) ||
				(this == JPBlock_Blocks.NAMAKOB_SH_black && hItem == Items_Wablock.NAMAKOB_SH_black));

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

		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int w = state.getValue(DOUBLE)? 2 : 1;
		stack.add(new ItemStack(cloneItem(), w, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}

	private Item cloneItem() {
		if (this == JPBlock_Blocks.NAMAKOB_SH_white) { return Items_Wablock.NAMAKOB_SH_white; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_orange) { return Items_Wablock.NAMAKOB_SH_orange; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_magenta) { return Items_Wablock.NAMAKOB_SH_magenta; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_lightb) { return Items_Wablock.NAMAKOB_SH_lightb; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_yellow) { return Items_Wablock.NAMAKOB_SH_yellow; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_lime) { return Items_Wablock.NAMAKOB_SH_lime; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_pink) { return Items_Wablock.NAMAKOB_SH_pink; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_gray) { return Items_Wablock.NAMAKOB_SH_gray; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_lightg) { return Items_Wablock.NAMAKOB_SH_lightg; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_cyan) { return Items_Wablock.NAMAKOB_SH_cyan; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_purple) { return Items_Wablock.NAMAKOB_SH_purple; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_blue) { return Items_Wablock.NAMAKOB_SH_blue; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_brown) { return Items_Wablock.NAMAKOB_SH_brown; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_green) { return Items_Wablock.NAMAKOB_SH_green; }
		if (this == JPBlock_Blocks.NAMAKOB_SH_red) { return Items_Wablock.NAMAKOB_SH_red; }
		else { return Items_Wablock.NAMAKOB_SH_black; }
	}
}
