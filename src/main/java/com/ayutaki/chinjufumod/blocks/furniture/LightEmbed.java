package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LightEmbed extends Regi_addState {
	/* Property */
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);

	/* Collision */
	private static final AxisAlignedBB AABB_1 = new AxisAlignedBB(0.0625D, 0.9375D, 0.0625D, 0.9375D, 1.21875D, 0.9375D);
	private static final AxisAlignedBB AABB_2 = new AxisAlignedBB(0.0625D, 0.9375D, 0.0625D, 0.9375D, 1.5D, 0.9375D);
	private static final AxisAlignedBB AABB_3 = new AxisAlignedBB(0.0625D, -0.5D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
	private static final AxisAlignedBB AABB_4 = new AxisAlignedBB(0.0625D, -0.21875D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
	
	public LightEmbed(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		/* Glow Stone=1.0F, Torch=0.9375F */
		setLightLevel(1.0F);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_1_4, Integer.valueOf(1))
				.withProperty(NORTH, Boolean.valueOf(false))
				.withProperty(EAST, Boolean.valueOf(false))
				.withProperty(SOUTH, Boolean.valueOf(false))
				.withProperty(WEST, Boolean.valueOf(false)));
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.IRON;
	}

	/* BlockState when it was placed.
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return null;
	} -> ItemBlock */
	
	/* Connect the blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		boolean south = worldIn.getBlockState(pos.south()) == this.getDefaultState().withProperty(STAGE_1_4, i);
		boolean north = worldIn.getBlockState(pos.north()) == this.getDefaultState().withProperty(STAGE_1_4, i);
		boolean west = worldIn.getBlockState(pos.west()) == this.getDefaultState().withProperty(STAGE_1_4, i);
		boolean east = worldIn.getBlockState(pos.east()) == this.getDefaultState().withProperty(STAGE_1_4, i);
		return state.withProperty(SOUTH, south)
				.withProperty(NORTH, north)
				.withProperty(WEST, west)
				.withProperty(EAST, east);
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_4, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_4)).intValue();
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
	
	@Override
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, WEST, SOUTH, STAGE_1_4 });
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = state.getValue(STAGE_1_4);

		if (i == 2) { return AABB_2; }
		if (i == 3) { return AABB_3; }
		if (i == 4) { return AABB_4; }
		return AABB_1 ;
	}
	
	/*Collision*/
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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
		return new ItemStack(Items_Chinjufu.E_LIGHT, 1, 0);
	}
}
