package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class UnitDesk_sub extends BaseUnitDesk {

	public UnitDesk_sub(String name) {
		super(name);
		setLightOpacity(1);
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.UNITDESK_item, 1, i));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		return new ItemStack(Items_Chinjufu.UNITDESK_item, 1, i);
	}
}
