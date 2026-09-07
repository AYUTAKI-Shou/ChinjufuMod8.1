package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Wagasa extends Regi_addState {
	/* Property */
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool WHICH = PropertyBool.create("which");
	
	public Wagasa(String name) {
		super(name);
		setSoundType(SoundType.CLOTH);
		setHardness(0.8F);
		setResistance(1.0F);
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(WHICH, false));
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (this == Garden_Blocks.KASA_white) { return MapColor.SNOW; }
		if (this == Garden_Blocks.KASA_orange) { return MapColor.ADOBE; }
		if (this == Garden_Blocks.KASA_magenta) { return MapColor.MAGENTA; }
		if (this == Garden_Blocks.KASA_lightb) { return MapColor.LIGHT_BLUE; }
		if (this == Garden_Blocks.KASA_yellow) { return MapColor.YELLOW; }
		if (this == Garden_Blocks.KASA_lime) { return MapColor.LIME; }
		if (this == Garden_Blocks.KASA_pink) { return MapColor.PINK; }
		if (this == Garden_Blocks.KASA_gray) { return MapColor.GRAY; }
		if (this == Garden_Blocks.KASA_lightg) { return MapColor.SILVER; }
		if (this == Garden_Blocks.KASA_cyan) { return MapColor.CYAN; }
		if (this == Garden_Blocks.KASA_purple) { return MapColor.PURPLE; }
		if (this == Garden_Blocks.KASA_blue) { return MapColor.BLUE; }
		if (this == Garden_Blocks.KASA_brown) { return MapColor.BROWN; }
		if (this == Garden_Blocks.KASA_green) { return MapColor.GREEN; }
		if (this == Garden_Blocks.KASA_red) { return MapColor.RED; }
		else { return MapColor.BLACK; }
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty() && playerIn.isSneaking()) {
			CMEvents.soundClothPlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycleProperty(WHICH));
			return true; }
		
		else { return false; }
	}
	
	@Override
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) { return true; }
		return false;
	}
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int i, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(WHICH, Boolean.valueOf(i == 1));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Boolean)state.getValue(WHICH)).booleanValue() ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(WHICH, Boolean.valueOf(meta == 1));
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(IBlockAccess worldIn, BlockPos source, EnumFacing direction, boolean which) {
		IBlockState state = worldIn.getBlockState(source.offset(direction));
		return state.getBlock() == this && state.getValue(WHICH) == which;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean which = state.getValue(WHICH);
		boolean south = canConnectTo(worldIn, pos, EnumFacing.SOUTH, which);
		boolean north = canConnectTo(worldIn, pos, EnumFacing.NORTH, which);
		boolean west = canConnectTo(worldIn, pos, EnumFacing.WEST, which);
		boolean east = canConnectTo(worldIn, pos, EnumFacing.EAST, which);
		return state.withProperty(NORTH, north).withProperty(EAST, east).withProperty(SOUTH, south).withProperty(WEST, west);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, WHICH });
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
		stack.add(new ItemStack(Items_Wadeco.KASA_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Wadeco.KASA_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == Garden_Blocks.KASA_white) { return 0; }
		if (this == Garden_Blocks.KASA_orange) { return 1; }
		if (this == Garden_Blocks.KASA_magenta) { return 2; }
		if (this == Garden_Blocks.KASA_lightb) { return 3; }
		if (this == Garden_Blocks.KASA_yellow) { return 4; }
		if (this == Garden_Blocks.KASA_lime) { return 5; }
		if (this == Garden_Blocks.KASA_pink) { return 6; }
		if (this == Garden_Blocks.KASA_gray) { return 7; }
		if (this == Garden_Blocks.KASA_lightg) { return 8; }
		if (this == Garden_Blocks.KASA_cyan) { return 9; }
		if (this == Garden_Blocks.KASA_purple) { return 10; }
		if (this == Garden_Blocks.KASA_blue) { return 11; }
		if (this == Garden_Blocks.KASA_brown) { return 12; }
		if (this == Garden_Blocks.KASA_green) { return 13; }
		if (this == Garden_Blocks.KASA_red) { return 14; }
		else { return 15; }
	}
}
