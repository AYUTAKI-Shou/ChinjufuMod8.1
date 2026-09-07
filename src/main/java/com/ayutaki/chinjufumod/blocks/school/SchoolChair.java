package com.ayutaki.chinjufumod.blocks.school;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.entity.helper.SittableUtil;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SchoolChair extends Regi_addState {

	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.4375, 0.875);

	private static final AxisAlignedBB CHAIR_BASE = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.4375, 0.875);
	private static final AxisAlignedBB CHAIR_BACKREST_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.125, 0.4375, 0.125, 0.1875, 1.0, 0.875);
	private static final AxisAlignedBB CHAIR_BACKREST_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.125, 0.4375, 0.125, 0.1875, 1.0, 0.875);
	private static final AxisAlignedBB CHAIR_BACKREST_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.125, 0.4375, 0.125, 0.1875, 1.0, 0.875);
	private static final AxisAlignedBB CHAIR_BACKREST_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.125, 0.4375, 0.125, 0.1875, 1.0, 0.875);

	public SchoolChair(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState().withProperty(H_FACING, EnumFacing.NORTH));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 3.5 * 0.0625)) {
			worldIn.updateComparatorOutputLevel(pos, this);
			CMEvents.soundSitChair(worldIn, pos);
			return true;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	/* Collision */
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
			Entity entityIn, boolean t_f) {
		if (!(entityIn instanceof SitableEntity)) {

			EnumFacing direction = state.getValue(H_FACING);
			switch (direction) {
			case NORTH :
			default :
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_NORTH);
				break;
			case SOUTH :
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_SOUTH);
				break;
			case EAST :
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_EAST);
				break;
			case WEST :
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_WEST);
				break;
			}
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BASE);
		}
	}

	/*1.11.2用*/
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(H_FACING)).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING });
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return SittableUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
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
		stack.add(new ItemStack(Items_Chinjufu.SCHOOLCHAIR_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.SCHOOLCHAIR_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == School_Blocks.SCHOOLCHAIR) { return 0; }
		if (this == School_Blocks.SCHOOLCHAIR_s) { return 1; }
		if (this == School_Blocks.SCHOOLCHAIR_b) { return 2; }
		if (this == School_Blocks.SCHOOLCHAIR_j) { return 3; }
		if (this == School_Blocks.SCHOOLCHAIR_a) { return 4; }
		if (this == School_Blocks.SCHOOLCHAIR_d) { return 5; }
		if (this == School_Blocks.SCHOOLCHAIR_saku) { return 6; }
		if (this == School_Blocks.SCHOOLCHAIR_kae) { return 7; }
		else { return 8; } 
	}
}
