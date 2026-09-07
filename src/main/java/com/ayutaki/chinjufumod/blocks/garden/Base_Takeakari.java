package com.ayutaki.chinjufumod.blocks.garden;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Takeakari extends BaseStage3_Face {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.09375, 0.0, 0.40625, 0.28125, 0.5, 0.59375);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.09375, 0.0, 0.40625, 0.28125, 0.5, 0.59375);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.09375, 0.0, 0.40625, 0.28125, 0.5, 0.59375);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.09375, 0.0, 0.40625, 0.28125, 0.5, 0.59375);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Base_Takeakari(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
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

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = state.getValue(STAGE_1_3);
		
		stack.add(new ItemStack(Items_Wadeco.TAKEAKARI, 1, i - 1));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = state.getValue(STAGE_1_3);
		return new ItemStack(Items_Wadeco.TAKEAKARI, 1, i - 1);
	}
}
