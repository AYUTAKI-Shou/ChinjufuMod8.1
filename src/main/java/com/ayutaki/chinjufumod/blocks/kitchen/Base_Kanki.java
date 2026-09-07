package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Kanki extends BaseStage3_Face {

	private static final AxisAlignedBB AABB_1_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.3125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_1_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.3125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_1_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.3125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_1_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.3125, 1.0, 1.0);

	private static final AxisAlignedBB AABB_23_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB AABB_23_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB AABB_23_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB AABB_23_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

	public Base_Kanki(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.IRON;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		switch (direction) {
		case NORTH:
		default:
			return (i == 1) ? AABB_1_NORTH : AABB_23_NORTH;
			
		case SOUTH:
			return (i == 1) ? AABB_1_SOUTH : AABB_23_SOUTH;

		case EAST:
			return (i == 1) ? AABB_1_EAST : AABB_23_EAST;

		case WEST:
			return (i == 1) ? AABB_1_WEST : AABB_23_WEST;
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1) ? AABB_1_NORTH : AABB_23_NORTH);
			break;
			
		case SOUTH:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1) ? AABB_1_SOUTH : AABB_23_SOUTH);
			break;

		case EAST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1) ? AABB_1_EAST : AABB_23_EAST);
			break;

		case WEST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1) ? AABB_1_WEST : AABB_23_WEST);
			break;
		}
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/*Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.KIT_KANKI_1, 1, 0);
	}
}
