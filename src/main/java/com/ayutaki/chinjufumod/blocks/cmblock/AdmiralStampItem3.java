package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class AdmiralStampItem3 extends Base_AdmiralStampItem {

	public AdmiralStampItem3(String name) {
		super(name);
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue(); //8-11
		stack.add(new ItemStack(Items_Chinjufu.ADMIRAL_STAMP_B, 1, i + 7));
		return stack;
	}
}
