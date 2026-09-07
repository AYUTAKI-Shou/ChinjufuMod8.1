package com.ayutaki.chinjufumod.blocks.crop;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SeedsBox extends BaseFacingSapo {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.375, 0.0, 0.25, 0.625, 0.5, 0.75);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public SeedsBox(String name) {
		super(name);
		setSoundType(SoundType.PLANT);
		setHardness(0.5F);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	@Override
	public java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		java.util.List<ItemStack> stack = new java.util.ArrayList<ItemStack>();
		Random rand = worldIn instanceof World ? ((World)worldIn).rand : new Random();
		int i = rand.nextInt(18);

		if (i == 0) { stack.add(new ItemStack(Items_Teatime.SEEDS_AZUKI, 1, 0)); }
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.SEEDS_CABBAGE, 1, 0)); }
		if (i == 2) { stack.add(new ItemStack(Items_Teatime.SEEDS_SOY, 1, 0)); }
		if (i == 3) { stack.add(new ItemStack(Items_Teatime.SEEDS_SPINACH, 1, 0)); }
		if (i == 4) { stack.add(new ItemStack(Items_Teatime.SEEDS_TOMATO, 1, 0)); }
		if (i == 5) { stack.add(new ItemStack(Items_Teatime.SEEDS_CORN, 1, 0)); }
		if (i == 6) { stack.add(new ItemStack(Items_Teatime.SEEDS_GREENONION, 1, 0)); }
		if (i == 7) { stack.add(new ItemStack(Items_Teatime.SEEDS_ONION, 1, 0)); }
		if (i == 8) { stack.add(new ItemStack(Items_Teatime.SEEDS_RICE, 1, 0)); }
		if (i == 9) { stack.add(new ItemStack(Items_Teatime.SEEDS_HAKUSAI, 1, 0)); }
		if (i == 10) { stack.add(new ItemStack(Items_Teatime.CHANOKI, 1, 0)); }
		if (i == 11) { stack.add(new ItemStack(Items_Teatime.BUDOUNOKI, 1, 0)); }
		if (i == 12) { stack.add(new ItemStack(Items_Teatime.MIKAN_NAE, 1, 0)); }

		if (i == 13) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 1, 0)); }
		if (i == 14) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 1, 1)); }
		if (i == 15) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 1, 2)); }
		if (i == 16) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 1, 3)); }
		if (i == 17) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 1, 4)); }
		if (i == 18) { stack.add(new ItemStack(Items.AIR, 1, 0)); }
		
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SEEDSBOX, 1, 0);
	}
	
	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
