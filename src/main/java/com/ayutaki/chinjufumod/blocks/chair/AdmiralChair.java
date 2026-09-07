package com.ayutaki.chinjufumod.blocks.chair;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.entity.helper.SittableUtil;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AdmiralChair extends BaseStage2_Face {
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5625, 1.0);
	private static final AxisAlignedBB AABB_BASE = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.5625, 0.9);
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.5625, 0.0, 0.1, 2.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.5625, 0.0, 0.1, 2.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.5625, 0.0, 0.1, 2.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.5625, 0.0, 0.1, 2.0, 1.0);

	public AdmiralChair(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 5.5 * 0.0625)) {
			worldIn.updateComparatorOutputLevel(pos, this);
			CMEvents.soundSitChair(worldIn, pos);
			return true; }
		return false;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		if (!(entityIn instanceof SitableEntity)) {

			EnumFacing direction= state.getValue(H_FACING);
			switch (direction) {
			case NORTH:
			default:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NORTH);
				break;
			case SOUTH:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_SOUTH);
				break;
			case EAST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EAST);
				break;
			case WEST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WEST);
				break;
			}
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BASE);
		}
	}

	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
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

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof AdmiralChair_Top) {
			worldIn.destroyBlock(pos.up(), false); }
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.ADMIRALCHAIR, 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.ADMIRALCHAIR, 1, cloneMeta(state));
	}

	private int cloneMeta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		if (i == 1) { return 1; }
		else { return 2; }
	}
}
