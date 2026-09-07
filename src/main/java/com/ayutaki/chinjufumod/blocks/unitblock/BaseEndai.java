package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseEndai extends Regi_addState {
	/* Property */
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	/** 0=normal, 1=red, 2=teatable **/
	public static final PropertyInteger STAGE_0_2 = PropertyInteger.create("color", 0, 2);

	public BaseEndai(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(STAGE_0_2, Integer.valueOf(0)));
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();
		if (i == 1) { return MapColor.RED; }
		else { return MapColor.WOOD; }
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_2, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_2)).intValue();
	}

	@Override
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) { return true; }
		return false;
	}

	/* Connect the blocks. */
	private boolean canConnectTo(IBlockAccess worldIn, BlockPos source, EnumFacing direction, int i) {
		IBlockState state = worldIn.getBlockState(source.offset(direction));
		return state.getBlock() == this && ((Integer)state.getValue(STAGE_0_2)).intValue() == i;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();
		boolean south = canConnectTo(worldIn, pos, EnumFacing.SOUTH, i);
		boolean north = canConnectTo(worldIn, pos, EnumFacing.NORTH, i);
		boolean west = canConnectTo(worldIn, pos, EnumFacing.WEST, i);
		boolean east = canConnectTo(worldIn, pos, EnumFacing.EAST, i);
		return state.withProperty(NORTH, north).withProperty(EAST, east).withProperty(SOUTH, south).withProperty(WEST, west);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, STAGE_0_2 });
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
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

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
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
		stack.add(cloneState(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneState(state);
	}
	
	public ItemStack cloneState(IBlockState state) {
		/** 0=縁台, 1=縁台(赤), 2=ティーテーブル **/
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();

		if (i == 0) { return new ItemStack(Items_Wadeco.ENDAI, 1, 0); }
		if (i == 1) { return new ItemStack(Items_Wadeco.ENDAI, 1, 1); }
		else { return new ItemStack(Items_Teatime.TEATABLE, 1, 0); }
	}
}
