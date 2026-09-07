package com.ayutaki.chinjufumod.blocks.garden;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
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
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class IronFence_Bot extends BaseFacingSapo {

	public static final PropertyEnum<TypeLR> TYPE = PropertyEnum.create("type", TypeLR.class);

	private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB( 0.0D, 0.0D, 0.46875D, 1.0D, 1.6875D, 0.53125D);
	private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB( 0.46875D, 0.0D, 0.0D, 0.53125D, 1.6875D, 1.0D);
	private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB( 0.46875D, 0.0D, 0.0D, 0.53125D, 1.6875D, 1.0D);
	private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB( 0.0D, 0.0D, 0.46875D, 1.0D, 1.6875D, 0.53125D);

	public IronFence_Bot(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}
	
	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
		default : return AABB_NORTH;
		case SOUTH : return AABB_SOUTH;
		case EAST : return AABB_EAST;
		case WEST : return AABB_WEST;
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		EnumFacing direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
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
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(H_FACING);
		IBlockState left_block = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
		IBlockState right_block = worldIn.getBlockState(pos.offset(facing.rotateY()));
		boolean left = left_block.getBlock() instanceof IronFence_Bot && left_block.getValue(H_FACING).equals(facing);
		boolean right = right_block.getBlock() instanceof IronFence_Bot && right_block.getValue(H_FACING).equals(facing);

		 if (right) {

			if (left) { return state.withProperty(TYPE, TypeLR.BOTH); }

			else { return state.withProperty(TYPE, TypeLR.RIGHT); }
		}

		else if (left) {

			if (right) { return state.withProperty(TYPE, TypeLR.BOTH); }

			else { return state.withProperty(TYPE, TypeLR.LEFT); }
		}
		return state.withProperty(TYPE, TypeLR.DEFAULT);
	}


	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, TYPE });
	}

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof IronFence_Top) {
			worldIn.destroyBlock(pos.up(), false); }
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

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
		return new ItemStack(Items_Wadeco.TETSUSAKU_BOT, 1, 0);
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
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
