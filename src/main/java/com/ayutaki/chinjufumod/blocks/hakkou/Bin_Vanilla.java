package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Bin_Vanilla extends BaseStage4_Face {

	private static final double cw = 0.0625;
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(2.0D * cw, 0.0D * cw, 2.0D * cw, 6.0D * cw, 7.0D * cw, 6.0D * cw);
	private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(10.0D * cw, 0.0D * cw, 2.0D * cw, 14.0D * cw, 7.0D * cw, 6.0D * cw);
	private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(10.0D * cw, 0.0D * cw, 10.0D * cw, 14.0D * cw, 7.0D * cw, 14.0D * cw);
	private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(2.0D * cw, 0.0D * cw, 10.0D * cw, 6.0D * cw, 7.0D * cw, 14.0D * cw);

	public Bin_Vanilla(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(0.5F);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing direction = state.getValue(H_FACING);
		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case EAST: return AABB_EAST;
		case WEST: return AABB_WEST;
		}
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

	/* Rendering */
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
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.VANILLA_bot_14, 1, 0)); }
		if (i == 2) { stack.add(new ItemStack(Items_NoTab.VANILLA_bot_24, 1, 0)); }
		if (i == 3) { stack.add(new ItemStack(Items_NoTab.VANILLA_bot_34, 1, 0)); }
		if (i == 4) { stack.add(new ItemStack(Items_NoTab.VANILLA_bot_44, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.VANILLA_bot_14, 1, 0);
	}
}
