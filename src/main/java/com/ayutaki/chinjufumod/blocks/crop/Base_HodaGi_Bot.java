package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
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

public class Base_HodaGi_Bot extends BaseStage4_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.3125, 0.925, 0.75, 0.6875);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.3125, 0.925, 0.75, 0.6875);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.3125, 0.925, 0.75, 0.6875);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.3125, 0.925, 0.75, 0.6875);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Base_HodaGi_Bot(String name) {
		super(name);
		
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setTickRandomly(true);
	}

	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i != 4 && worldIn.getLightFromNeighbors(pos.up()) <= 11) {
			if (rand.nextInt(8) == 0) { 
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); } }

		else { }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		/** Data value of H_FACING **/
		return AABB[facing.getHorizontalIndex()];
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
	}

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof Base_HodaGi_Top) {
			worldIn.destroyBlock(pos.up(), false);
		}
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
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.HODAGI_BOT, 1, 0);
	}
}
