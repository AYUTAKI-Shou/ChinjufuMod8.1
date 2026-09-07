package com.ayutaki.chinjufumod.blocks.season;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Present_B extends Base_Present {
	/**1=Blaze, 2=Choco, 3=HeartChoco, 4= **/
	public Present_B(String name) {
		super(name);
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}

	private ItemStack cloneStack(IBlockState state) {
		/**1=Blaze, 2=Choco, 3=HeartChoco, 4= **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return new ItemStack(Items_Seasonal.PRESENT_B, 1, 1); }
		if (i == 2) { return new ItemStack(Items_Seasonal.PRESENT_B, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Seasonal.PRESENT_B, 1, 3); }
		if (i == 4) { return new ItemStack(Blocks.AIR); }
		return null;
	}
}
