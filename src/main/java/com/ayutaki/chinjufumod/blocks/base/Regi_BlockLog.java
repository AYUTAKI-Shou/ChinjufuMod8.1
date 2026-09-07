package com.ayutaki.chinjufumod.blocks.base;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class Regi_BlockLog extends BlockLog {

	public Regi_BlockLog(String name) {
		super();
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);

		setSoundType(SoundType.WOOD);
	}

	/* Abstract */
	protected abstract BlockStateContainer createBlockState();
	
	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
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
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}
}
