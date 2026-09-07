package com.ayutaki.chinjufumod.blocks.jpblock;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Base_StoneStairs;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Namako_Stairs extends Base_StoneStairs {

	public Namako_Stairs(String name, IBlockState state) {
		super(name, state);
		setCreativeTab(ChinjufuModTabs.WABLOCK);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (this == JPBlock_Blocks.NAMAKO_ST_white) { return MapColor.SNOW; }
		if (this == JPBlock_Blocks.NAMAKO_ST_orange) { return MapColor.ADOBE; }
		if (this == JPBlock_Blocks.NAMAKO_ST_magenta) { return MapColor.MAGENTA; }
		if (this == JPBlock_Blocks.NAMAKO_ST_lightb) { return MapColor.LIGHT_BLUE; }
		if (this == JPBlock_Blocks.NAMAKO_ST_yellow) { return MapColor.YELLOW; }
		if (this == JPBlock_Blocks.NAMAKO_ST_lime) { return MapColor.LIME; }
		if (this == JPBlock_Blocks.NAMAKO_ST_pink) { return MapColor.PINK; }
		if (this == JPBlock_Blocks.NAMAKO_ST_gray) { return MapColor.GRAY; }
		if (this == JPBlock_Blocks.NAMAKO_ST_lightg) { return MapColor.SILVER; }
		if (this == JPBlock_Blocks.NAMAKO_ST_cyan) { return MapColor.CYAN; }
		if (this == JPBlock_Blocks.NAMAKO_ST_purple) { return MapColor.PURPLE; }
		if (this == JPBlock_Blocks.NAMAKO_ST_blue) { return MapColor.BLUE; }
		if (this == JPBlock_Blocks.NAMAKO_ST_brown) { return MapColor.BROWN; }
		if (this == JPBlock_Blocks.NAMAKO_ST_green) { return MapColor.GREEN; }
		if (this == JPBlock_Blocks.NAMAKO_ST_red) { return MapColor.RED; }
		else { return MapColor.BLACK; }
	}
}
