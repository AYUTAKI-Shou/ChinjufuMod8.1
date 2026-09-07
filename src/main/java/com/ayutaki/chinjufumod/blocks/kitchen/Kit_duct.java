package com.ayutaki.chinjufumod.blocks.kitchen;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_addState;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Kit_duct extends Regi_addState {

	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public Kit_duct(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);

		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(10.0F);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(UP, Boolean.valueOf(false))
				.withProperty(DOWN, Boolean.valueOf(false))
				.withProperty(NORTH, Boolean.valueOf(false))
				.withProperty(SOUTH, Boolean.valueOf(false))
				.withProperty(EAST, Boolean.valueOf(false))
				.withProperty(WEST, Boolean.valueOf(false)));
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.IRON;
	}

	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock() instanceof Kit_duct;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}

	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(UP, Boolean.valueOf(this.canConnectTo(worldIn, pos.up())))
				.withProperty(DOWN, Boolean.valueOf(this.canConnectTo(worldIn, pos.down())))
				.withProperty(NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north())))
				.withProperty(SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south())))
				.withProperty(EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east())))
				.withProperty(WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west())));
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { UP, DOWN, NORTH, SOUTH, EAST, WEST });
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
}
