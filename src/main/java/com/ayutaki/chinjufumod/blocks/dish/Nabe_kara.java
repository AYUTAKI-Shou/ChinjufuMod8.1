package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;
import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven;
import com.ayutaki.chinjufumod.blocks.furnace.Lit_Irori;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop_aida;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop_off;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop_on;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Nabe_kara extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool COOK = PropertyBool.create("cook");
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);

	private static final AxisAlignedBB MISO_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB MISO_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB MISO_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB MISO_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);

	private static final AxisAlignedBB MISOD_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB MISOD_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB MISOD_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB MISOD_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);

	private static final AxisAlignedBB MISODK_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);
	private static final AxisAlignedBB MISODK_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);
	private static final AxisAlignedBB MISODK_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);
	private static final AxisAlignedBB MISODK_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);

	/** 1=鍋空, 2=味噌鍋空, 3=ご飯鍋空, 4=塩鍋空 **/
	public Nabe_kara(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
		
		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(STAGE_1_4, Integer.valueOf(1))
				.withProperty(COOK, Boolean.valueOf(false))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(STAGE_1_4)).intValue() - 1 << 2;
		return i;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(STAGE_1_4, Integer.valueOf(1 + (meta >> 2)));
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (4 - ((Integer)state.getValue(STAGE_1_4)).intValue()) * 2;
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { COOK, DOWN, H_FACING, STAGE_1_4 });
	}

	/* Reaction to Neighboring blocks. */
	public boolean connectCook(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock() instanceof Kitchen_Oven || worldIn
				.getBlockState(pos).getBlock() instanceof Irori || worldIn
				.getBlockState(pos).getBlock() instanceof Lit_Irori || worldIn
				.getBlockState(pos).getBlock() instanceof Kit_Cooktop_on || worldIn
				.getBlockState(pos).getBlock() instanceof Kit_Cooktop_off || worldIn
				.getBlockState(pos).getBlock() instanceof Kit_Cooktop_aida || worldIn
				.getBlockState(pos).getBlock() instanceof BlockFurnace;
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(COOK, this.connectCook(worldIn, pos.down()))
				.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag = !((Boolean)state.getValue(COOK)).booleanValue();
		boolean flag2 = !((Boolean)state.getValue(DOWN)).booleanValue();
		/** 1=鍋空, 2=味噌鍋空, 3=ご飯鍋空, 4=塩鍋空 **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			switch (direction) {
			case NORTH:
			default: return AABB_NORTH;
			case SOUTH:return AABB_SOUTH;
			case WEST:return AABB_WEST;
			case EAST:return AABB_EAST;
			}
		}

		if (i == 4) {
			switch (direction) {
			case NORTH:
			default: return MISOD_NORTH;
			case SOUTH:return MISOD_SOUTH;
			case WEST:return MISOD_WEST;
			case EAST:return MISOD_EAST;
			}
		}

		else {
			switch (direction) {
			case NORTH:
			default: 
				return flag? (flag2? MISO_NORTH : MISODK_NORTH): MISOD_NORTH;
			case SOUTH:
				return flag? (flag2? MISO_SOUTH : MISODK_SOUTH): MISOD_SOUTH;
			case WEST:
				return flag? (flag2? MISO_WEST : MISODK_WEST): MISOD_WEST;
			case EAST:
				return flag? (flag2? MISO_EAST : MISODK_EAST): MISOD_EAST;
			}
		}
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
		return new ItemStack(Items_Teatime.NABE_kara, 1, 0);
	}
}
