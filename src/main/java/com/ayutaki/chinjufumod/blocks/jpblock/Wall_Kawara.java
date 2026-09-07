package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Wablock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Wall_Kawara extends Base_WallKawara {

	public Wall_Kawara(String name) {
		super(name);
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		stack.add(new ItemStack(Items_Wablock.KAWARA_WALL, 1, i));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		return new ItemStack(Items_Wablock.KAWARA_WALL, 1, i);
	}
}
