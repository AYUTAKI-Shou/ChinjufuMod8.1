package com.ayutaki.chinjufumod.blocks.base;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu_sub;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk_sub;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfStoneSlab;
import net.minecraft.block.BlockHalfStoneSlabNew;
import net.minecraft.block.BlockHalfWoodSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class Base_ConnectHalf extends Regi_addState {
	/* Property */
	public static final PropertyBool DOWN = PropertyBool.create("down");

	public Base_ConnectHalf(String name) {
		super(name);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Reaction to Neighboring blocks. */
	public boolean connectHalf(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		return block instanceof Chabudai || block instanceof LowDesk || block instanceof Kotatsu ||
					block instanceof Chabudai_sub || block instanceof LowDesk_sub || block instanceof Kotatsu_sub ||
					(block instanceof BaseFacingSlabW && state.getValue(BaseFacingSlabW.HALF) == SlabHalf.BOTTOM && state.getValue(BaseFacingSlabW.DOUBLE) == false) ||
					(block instanceof BaseSlabW && state.getValue(BaseSlabW.HALF) == SlabHalf.BOTTOM && state.getValue(BaseSlabW.DOUBLE) == false) ||
					(block instanceof Base_Slab_JP && state.getValue(Base_Slab_JP.HALF) == SlabHalf.BOTTOM && state.getValue(Base_Slab_JP.DOUBLE) == false) ||
					(block instanceof BlockHalfWoodSlab && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM) ||
					(block instanceof BlockHalfStoneSlab && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM) ||
					(block instanceof BlockHalfStoneSlabNew && state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.BOTTOM);
	}

	/* Rendering */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}
	
	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}
}
