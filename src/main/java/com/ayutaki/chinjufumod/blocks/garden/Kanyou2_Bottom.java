package com.ayutaki.chinjufumod.blocks.garden;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kanyou2_Bottom extends Base_Kanyou {

	public Kanyou2_Bottom(String name) {
		super(name);
	}

	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D);
	}

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof Kanyou2_Top) {
			worldIn.destroyBlock(pos.up(), false);
		}
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
		int i = ((Integer)state.getValue(STAGE_0_9)).intValue();

		if (i == 0) { return new ItemStack(Items_Wadeco.KANYOU_BOT, 1, 0); }
		if (i == 1) { return new ItemStack(Items_Wadeco.KANYOU_BOT, 1, 1); }
		if (i == 2) { return new ItemStack(Items_Wadeco.KANYOU_BOT, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Wadeco.KANYOU_BOT, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Wadeco.KANYOU_BOT, 1, 4); }
		if (i == 5) { return new ItemStack(Items_Wadeco.KANYOU_BOT, 1, 5); }
		if (i == 6) { return new ItemStack(Items_Seasonal.SKANYOU_BOT, 1, 0); }
		if (i == 7) { return new ItemStack(Items_Seasonal.SKANYOU_BOT, 1, 1); }
		if (i == 8) { return new ItemStack(Items_Seasonal.SKANYOU_BOT, 1, 2); }
		else { return new ItemStack(Items_Seasonal.SKANYOU_BOT, 1, 3); }
	}
}
