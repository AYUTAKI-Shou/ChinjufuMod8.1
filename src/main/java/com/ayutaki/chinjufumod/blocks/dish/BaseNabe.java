package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;
import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven;
import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven_B;
import com.ayutaki.chinjufumod.blocks.furnace.Lit_Irori;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop_aida;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop_off;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop_on;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseNabe extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool COOK = PropertyBool.create("cook");

	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);

	private static final AxisAlignedBB AABB_COOK_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB AABB_COOK_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB AABB_COOK_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);
	private static final AxisAlignedBB AABB_COOK_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, 0.0, 0.21875, 0.78125, 0.25, 0.78125);

	private static final AxisAlignedBB AABB_DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);
	private static final AxisAlignedBB AABB_DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);
	private static final AxisAlignedBB AABB_DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);
	private static final AxisAlignedBB AABB_DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, -0.5, 0.34375, 0.78125, 0.01, 0.90625);

	protected BaseNabe(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState().withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(STAGE_1_4, Integer.valueOf(1))
				.withProperty(COOK, Boolean.valueOf(false))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* Cooking */
	protected boolean cookingIn(World worldIn, BlockPos pos) {
		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();
		return (downBlock == Blocks.LIT_FURNACE || downBlock == Kitchen_Blocks.LIT_KITSTOVE || 
				downBlock == Kitchen_Blocks.LIT_KITOVEN || downBlock == Kitchen_Blocks.LIT_KITOVEN_B || 
				downBlock == Kitchen_Blocks.LIT_IRORI);
	}
	
	/* Steam effect. */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;
		
		boolean not_steam = (this == Dish_Blocks.NABEGOHAN || this == Dish_Blocks.NABEGOHAN_TAKE || 
				this == Dish_Blocks.NABEGOHAN_KURI || this == Dish_Blocks.NABESEKIHAN);
		if (not_steam) { }
		
		else {
			if (cookingIn(worldIn, pos)) {
				
				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D);
				}
				
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
			}
		}
	}

	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(STAGE_1_4)).intValue() - 1 << 2;
		return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(STAGE_1_4, Integer.valueOf(1 + (meta >> 2)));
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (4 - ((Integer)state.getValue(STAGE_1_4)).intValue()) * 2;
	}

	/* Reaction to Neighboring blocks. */
	public boolean connectCook(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock() instanceof Kitchen_Oven || worldIn
				.getBlockState(pos).getBlock() instanceof Kitchen_Oven_B || worldIn
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

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { COOK, DOWN, H_FACING, STAGE_1_4 });
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(COOK)).booleanValue();
		boolean flag2= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			/** !down= true : false **/
			return flag? (flag2? AABB_NORTH : AABB_DOWN_NORTH) : AABB_COOK_NORTH;
		case SOUTH:
			return flag? (flag2? AABB_SOUTH : AABB_DOWN_SOUTH) : AABB_COOK_SOUTH;
		case EAST:
			return flag? (flag2? AABB_EAST : AABB_DOWN_EAST) : AABB_COOK_EAST;
		case WEST:
			return flag? (flag2? AABB_WEST : AABB_DOWN_WEST) : AABB_COOK_WEST;
		}
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
