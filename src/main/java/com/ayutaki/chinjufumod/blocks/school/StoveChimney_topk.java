package com.ayutaki.chinjufumod.blocks.school;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_Name;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StoveChimney_topk extends Regi_Name {

	public StoveChimney_topk(String name) {
		super(name, Material.WOOD);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);

		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.BLACK;
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
		return "pickaxe";
	}
}
