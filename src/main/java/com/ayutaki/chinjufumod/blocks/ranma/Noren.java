package com.ayutaki.chinjufumod.blocks.ranma;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.state.TypeLR;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Noren extends BaseFacingSapo {

	public static final PropertyEnum<TypeLR> TYPE = PropertyEnum.create("type", TypeLR.class);

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.875, 0.0, 0.125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.875, 0.0, 0.125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.875, 0.0, 0.125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.875, 0.0, 0.125, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Noren(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(0.5F);
		setResistance(1.0F);
		setLightOpacity(1);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean t_f) {

		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(H_FACING);
		IBlockState left_block = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
		IBlockState right_block = worldIn.getBlockState(pos.offset(facing.rotateY()));
		boolean left = (left_block.getBlock() == this) && left_block.getValue(H_FACING).equals(facing);
		boolean right = (right_block.getBlock() == this) && right_block.getValue(H_FACING).equals(facing);

		 if (right) {

			if (left) { return state.withProperty(TYPE, TypeLR.BOTH); }

			else { return state.withProperty(TYPE, TypeLR.RIGHT); }
		}

		else if (left) {

			if (right) { return state.withProperty(TYPE, TypeLR.BOTH); }

			else { return state.withProperty(TYPE, TypeLR.LEFT); } }
		 
		return state.withProperty(TYPE, TypeLR.DEFAULT);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, TYPE }) ;
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

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Wadeco.NOREN_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Wadeco.NOREN_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == JPDeco_Blocks.NOREN_white) { return 0; }
		if (this == JPDeco_Blocks.NOREN_orange) { return 1; }
		if (this == JPDeco_Blocks.NOREN_magenta) { return 2; }
		if (this == JPDeco_Blocks.NOREN_lightb) { return 3; }
		if (this == JPDeco_Blocks.NOREN_yellow) { return 4; }
		if (this == JPDeco_Blocks.NOREN_lime) { return 5; }
		if (this == JPDeco_Blocks.NOREN_pink) { return 6; }
		if (this == JPDeco_Blocks.NOREN_gray) { return 7; }
		if (this == JPDeco_Blocks.NOREN_lightg) { return 8; }
		if (this == JPDeco_Blocks.NOREN_cyan) { return 9; }
		if (this == JPDeco_Blocks.NOREN_purple) { return 10; }
		if (this == JPDeco_Blocks.NOREN_blue) { return 11; }
		if (this == JPDeco_Blocks.NOREN_brown) { return 12; }
		if (this == JPDeco_Blocks.NOREN_green) { return 13; }
		if (this == JPDeco_Blocks.NOREN_red) { return 14; }
		else { return 15; }
	}
}
