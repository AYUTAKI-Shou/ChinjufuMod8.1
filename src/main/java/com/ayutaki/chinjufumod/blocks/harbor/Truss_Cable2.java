package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Harbor_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Truss_Cable2 extends Base_Cable {

	public Truss_Cable2(String name) {
		super(name);
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.TRUSS_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.TRUSS_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == Harbor_Blocks.TRUSS2_white) { return 0; }
		if (this == Harbor_Blocks.TRUSS2_orange) { return 1; }
		if (this == Harbor_Blocks.TRUSS2_magenta) { return 2; }
		if (this == Harbor_Blocks.TRUSS2_lightb) { return 3; }
		if (this == Harbor_Blocks.TRUSS2_yellow) { return 4; }
		if (this == Harbor_Blocks.TRUSS2_lime) { return 5; }
		if (this == Harbor_Blocks.TRUSS2_pink) { return 6; }
		if (this == Harbor_Blocks.TRUSS2_gray) { return 7; }
		if (this == Harbor_Blocks.TRUSS2) { return 8; }
		if (this == Harbor_Blocks.TRUSS2_cyan) { return 9; }
		if (this == Harbor_Blocks.TRUSS2_purple) { return 10; }
		if (this == Harbor_Blocks.TRUSS2_blue) { return 11; }
		if (this == Harbor_Blocks.TRUSS2_brown) { return 12; }
		if (this == Harbor_Blocks.TRUSS2_green) { return 13; }
		if (this == Harbor_Blocks.TRUSS2_red) { return 14; }
		else { return 15; }
	}
}
